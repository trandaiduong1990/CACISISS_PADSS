package org.transinfo.cacis.dataacess.daoimpl.oracle.collectionmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyPolicyForm;

public class DelinquencyFeeSetupDAOImpt extends BaseDAOImpl implements
		DelinquencyFeeSetupDAO {
	private Logger logger = Logger.getLogger(DelinquencyFeeSetupDAOImpt.class);

	@Override
	public Collection search(DelinquencyFeeSetupSearchDto objDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select p.description, dto.agingBeginDays, dto.agingEndDays, dto.lastUpdatedDate, dto.lastUpdatedBy, dto.feeId ");
			sbf.append("From DelinquencyFeeSetupDto dto, DelinquencyPolicyDto p Where dto.status ='N' and dto.issuerId='"
					+ objDto.getIssuerId()
					+ "' and dto.policyId='"
					+ objDto.getPolicyId()
					+ "' and dto.policyId = p.policyId order by p.description, dto.agingBeginDays");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out
					.println("Error in DelinquencyFeeSetupDAOImpt search method : "
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyFeeSetupDAOImpt search  method :"
							+ ex);
		}

		return objSearchCollection;
	}

	@Override
	public boolean create(DelinquencyFeeSetupDto objDto) throws TPlusException {

		boolean boolCreate = false;
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDto);

			tx.commit();
			boolCreate = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyFeeSetupDAOImpt add method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyFeeSetupDAOImpt add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolCreate;
	}

	@Override
	public DelinquencyFeeSetupDto getFeeDetail(String id) throws TPlusException {

		DelinquencyFeeSetupDto objDto = new DelinquencyFeeSetupDto();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("From DelinquencyFeeSetupDto where feeId='"
							+ id + "'");
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (DelinquencyFeeSetupDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyFeeSetupDAOImpt getFeeDetail method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyFeeSetupDAOImpt getFeeDetail  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public boolean update(DelinquencyFeeSetupDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objDto);

			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyFeeSetupDAOImpt update method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyFeeSetupDAOImpt update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}

	@Override
	public boolean checkOverlap(DelinquencyFeeSetupForm objForm, Integer no)
			throws TPlusException {

		boolean overlap = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select MAX(agingEndDays) from DelinquencyFeeSetupDto where policyId=:id and issuerId=:issuerId and status='N'");
			qry.setString("id", objForm.getPolicyId());
			qry.setString("issuerId", objForm.getIssuerId());
			List list = qry.list();

			Query qryCount = session
					.createQuery("select COUNT(*) from DelinquencyFeeSetupDto where policyId=:id and issuerId=:issuerId and status='N'");
			qry.setString("id", objForm.getPolicyId());
			qry.setString("issuerId", objForm.getIssuerId());
			List totalFee = qry.list();

			if (no == 1 && ((Integer) totalFee.size()) == 1) {
				overlap = true;
			} else {
				overlap = list.get(0) == null
						|| (((Integer) list.get(0)).intValue() < Integer
								.parseInt(objForm.getAgingBeginDays()));
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyFeeSetupDAOImpt checkOverlap method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyFeeSetupDAOImpt checkOverlap  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return overlap;
	}

	@Override
	public boolean checkUpdateUser(DelinquencyFeeSetupForm objForm)
			throws TPlusException {

		boolean sameUser = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from DelinquencyFeeSetupDto where feeId=:id and agingBeginDays=:begin and agingEndDays=:end");
			qry.setString("id", objForm.getFeeId());
			qry.setString("begin", objForm.getAgingBeginDays());
			qry.setString("end", objForm.getAgingEndDays());
			List list = qry.list();
			sameUser = ((Integer) list.get(0)).intValue() == 1;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyFeeSetupDAOImpt checkUpdateUser method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyFeeSetupDAOImpt checkUpdateUser  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return sameUser;
	}

	@Override
	public String getPolicyName(DelinquencyFeeSetupForm objForm)
			throws TPlusException {

		Transaction tx = null;
		DelinquencyPolicyDto objPolicy = new DelinquencyPolicyDto();
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("from DelinquencyPolicyDto where policyId=:id");
			qry.setString("id", objForm.getPolicyId());
			List list = qry.list();
			objPolicy = (DelinquencyPolicyDto) list.get(0);

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyFeeSetupDAOImpt getPolicyName method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyFeeSetupDAOImpt getPolicyName  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objPolicy.getDescription();
	}

	@Override
	public String agingBeginEndRange(DelinquencyFeeSetupForm objForm)
			throws TPlusException {

		String range = "";
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			Query qry = session
					.createQuery("Select agingBeginDays, agingEndDays From DelinquencyFeeSetupDto where policyId=:id and issuerId=:issuerId and agingEndDays = (select MAX(agingEndDays) from DelinquencyFeeSetupDto where policyId=:id and issuerId=:issuerId)");
			qry.setString("id", objForm.getPolicyId());
			qry.setString("issuerId", objForm.getIssuerId());
			List list = qry.list();

			if (list.size() > 0) {
				for (Object o : list) {
					Object[] obj = (Object[]) o;
					range += "[" + obj[0] + ", " + obj[1] + "]";
				}
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyFeeSetupDAOImpt agingBeginEndRange method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyFeeSetupDAOImpt agingBeginEndRange  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return range;
	}
}
