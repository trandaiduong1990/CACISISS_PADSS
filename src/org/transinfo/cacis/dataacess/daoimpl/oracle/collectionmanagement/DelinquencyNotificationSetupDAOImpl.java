package org.transinfo.cacis.dataacess.daoimpl.oracle.collectionmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyNotificationSetupDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSetupForm;

public class DelinquencyNotificationSetupDAOImpl extends BaseDAOImpl implements
		DelinquencyNotificationSetupDAO {
	private Logger logger = Logger
			.getLogger(DelinquencyNotificationSetupDAOImpl.class);

	@Override
	public Collection search(DelinquencyNotificationSetupSearchDto objDto,
			int pageNo) throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select p.description, dto.agingSeverity, dto.lastUpdatedDate, dto.lastUpdatedBy, dto.notificationId ");
			sbf.append("From DelinquencyNotificationSetupDto dto, DelinquencyPolicyDto p Where dto.status ='N' and dto.issuerId='"
					+ objDto.getIssuerId()
					+ "' and dto.policyId='"
					+ objDto.getPolicyId()
					+ "' and dto.policyId = p.policyId order by p.description, dto.agingSeverity");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out
					.println("Error in DelinquencyNotificationSetupDAOImpl search method : "
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyNotificationSetupDAOImpl search  method :"
							+ ex);
		}

		return objSearchCollection;
	}

	@Override
	public boolean checkExitsRecord(DelinquencyNotificationSetupForm objForm,
			Integer no) throws TPlusException {
		boolean check = false;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From DelinquencyNotificationSetupDto where policyId=:id and issuerId=:issuerId and agingSeverity=:agingSeverity");
			qry.setString("id", objForm.getPolicyId());
			qry.setString("issuerId", objForm.getIssuerId());
			qry.setString("agingSeverity", objForm.getAgingSeverity());

			List list = qry.list();
			check = ((Integer) list.get(0)).intValue() == 0;
			System.out
					.println("After DelinquencyFeeSetupDAOImpt checkExistrecord()"
							+ check);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in DelinquencyFeeSetupDAOImpt checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in DelinquencyFeeSetupDAOImpt checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}

		return check;
	}

	@Override
	public boolean create(DelinquencyNotificationSetupDto objDto)
			throws TPlusException {

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
	public String getPolicyName(String policyId) throws TPlusException {

		Transaction tx = null;
		DelinquencyPolicyDto objPolicy = new DelinquencyPolicyDto();
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("from DelinquencyPolicyDto where policyId=:id");
			qry.setString("id", policyId);
			List list = qry.list();
			objPolicy = (DelinquencyPolicyDto) list.get(0);

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyNotificationSetupDAOImpl getPolicyName method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyNotificationSetupDAOImpl getPolicyName  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objPolicy.getDescription();
	}

	@Override
	public DelinquencyNotificationSetupDto getNotificationDetail(String id)
			throws TPlusException {

		DelinquencyNotificationSetupDto objDto = new DelinquencyNotificationSetupDto();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("From DelinquencyNotificationSetupDto where notificationId='"
							+ id + "'");
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (DelinquencyNotificationSetupDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyNotificationSetupDAOImpl getNotificationDetail method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyNotificationSetupDAOImpl getNotificationDetail  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public boolean update(DelinquencyNotificationSetupDto objDto) throws TPlusException {

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
					.println("Error in DelinquencyNotificationSetupDAOImpl update method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyNotificationSetupDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}
}
