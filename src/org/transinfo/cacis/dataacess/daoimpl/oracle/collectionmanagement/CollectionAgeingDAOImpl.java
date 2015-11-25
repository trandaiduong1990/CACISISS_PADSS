package org.transinfo.cacis.dataacess.daoimpl.oracle.collectionmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;

public class CollectionAgeingDAOImpl extends BaseDAOImpl implements CollectionAgeingDAO {
	private Logger logger = Logger.getLogger(CollectionAgeingDAOImpl.class);

	@Override
	public boolean add(CollectionAgeingDto objDto) throws TPlusException {

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
					.println("Error in CollectionAgeingDAOImpl add method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionAgeingDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolCreate;
	}


	@Override
	public boolean update(CollectionAgeingDto objDto) throws TPlusException {

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
					.println("Error in CollectionAgeingDAOImpl update method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionAgeingDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}


	@Override
	public Collection search(CollectionAgeingDto objDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select ca.ageingPolicy, ca.startDays, ca.endDays, ca.lastUpdatedDate ");
			sbf.append("From CollectionAgeingDto ca Where ca.issuerId='" + objDto.getIssuerId() + "' ");
			
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out
					.println("Error in CollectionAgeingDAOImpl search method : "
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionAgeingDAOImpl search  method :"
							+ ex);
		}

		return objSearchCollection;
	}


	@Override
	public CollectionAgeingDto getCollectionAgeing(String ageingPolicy)
			throws TPlusException {
		CollectionAgeingDto collectionAgeing = new CollectionAgeingDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			collectionAgeing = (CollectionAgeingDto) session.get(
					CollectionAgeingDto.class, ageingPolicy);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getCollectionAgeing method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getCollectionAgeing" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return collectionAgeing;
	}


	@Override
	public boolean checkExist(CollectionAgeingSetupForm objForm)
			throws TPlusException {
		boolean check = false;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From CollectionAgeingDto where ageingPolicy=:id and issuerId=:issuerId");
			qry.setString("id", objForm.getAgeingPolicy());
			qry.setString("issuerId", objForm.getIssuerId());

			List list = qry.list();
			check = ((Integer) list.get(0)).intValue() == 0;
			System.out
					.println("After DelinquencyFeeSetupDAOImpt checkExistrecord()"
							+ check);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in CollectionAgeingDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CollectionAgeingDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}

		return check;
	}


	@Override
	public boolean checkOverlap(CollectionAgeingSetupForm objForm)
			throws TPlusException {
		boolean check = false;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From CollectionAgeingDto where "
							+ objForm.getStartDays() + " < endDays and "
							+ objForm.getEndDays() + " > startDays and issuerId=:issuerId "
						    + "and ageingPolicy<>'" + objForm.getAgeingPolicy() + "' ");
			qry.setString("issuerId", objForm.getIssuerId());

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
	public boolean delete(CollectionAgeingDto objDto) throws TPlusException {
		boolean bolExecute=false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();

			tx = session.beginTransaction();

			session.delete(objDto);
			tx.commit();

			bolExecute = true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while deleting" + ex.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

}
