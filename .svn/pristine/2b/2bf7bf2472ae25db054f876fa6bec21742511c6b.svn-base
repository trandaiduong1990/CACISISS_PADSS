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
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyPolicyDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicySearchDto;

public class DelinquencyPolicyDAOImpl extends BaseDAOImpl implements
		DelinquencyPolicyDAO {
	private Logger logger = Logger.getLogger(DelinquencyPolicyDAOImpl.class);

	@Override
	public Collection search(DelinquencyPolicySearchDto objDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select policyId, description, lastUpdatedDate ");
			sbf.append("From DelinquencyPolicyDto Where status !='D' and issuerId='"
					+ objDto.getIssuerId() + "' ");
			if (objDto.getPolicyId() != null
					&& !objDto.getPolicyId().equals(""))
				sbf.append("and policyId='" + objDto.getPolicyId() + "' ");
			sbf.append("  order by policyId");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out
					.println("Error in DelinquencyPolicyDAOImpl search method : "
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyPolicyDAOImpl search  method :" + ex);
		}

		return objSearchCollection;
	}

	@Override
	public int checkExitsRecord(DelinquencyPolicyDto objPolicy)
			throws TPlusException {
		int resCord = 0;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From DelinquencyPolicyDto where policyId=:id and issuerId=:issuerId");
			qry.setString("id", objPolicy.getPolicyId());
			qry.setString("issuerId", objPolicy.getIssuerId());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out
					.println("After DelinquencyPolicyDAOImpl checkExistrecord()"
							+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in DelinquencyPolicyDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in DelinquencyPolicyDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}

		return resCord;
	}

	@Override
	public boolean create(DelinquencyPolicyDto objDto) throws TPlusException {

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
					.println("Error in DelinquencyPolicyDAOImpl add method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyPolicyDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolCreate;
	}

	@Override
	public DelinquencyPolicyDto getPolicyDetail(String id)
			throws TPlusException {

		DelinquencyPolicyDto objDto = new DelinquencyPolicyDto();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("From DelinquencyPolicyDto where policyId='"
							+ id + "'");
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (DelinquencyPolicyDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in DelinquencyPolicyDAOImpl getPolicyDetail method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyPolicyDAOImpl getPolicyDetail  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}
	
	@Override
	public boolean update(DelinquencyPolicyDto objDto) throws TPlusException {

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
			System.out.println("Error in DelinquencyPolicyDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DelinquencyPolicyDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}
}
