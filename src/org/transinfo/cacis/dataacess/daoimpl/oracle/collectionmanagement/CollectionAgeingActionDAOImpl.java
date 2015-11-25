package org.transinfo.cacis.dataacess.daoimpl.oracle.collectionmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.AgeingAction;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;

public class CollectionAgeingActionDAOImpl extends BaseDAOImpl implements CollectionAgeingActionDAO {
	private Logger logger = Logger.getLogger(CollectionAgeingActionDAOImpl.class);

	@Override
	public boolean add(CollectionAgeingActionDto objDto) throws TPlusException {

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
	public boolean update(CollectionAgeingActionDto objDto) throws TPlusException {

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
	public Collection search(CollectionAgeingActionDto objDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select a.collectionAgeingDto.ageingPolicy, a.days, a.phone, a.remainder, ");
			sbf.append("a.remainderType, a.tempCardOff, a.writeOff, a.sno ");
			sbf.append("From CollectionAgeingActionDto a Where a.issuerId='" + objDto.getIssuerId() + "' ");
			
			if(objDto.getCollectionAgeingDto()!= null) {
				if(objDto.getCollectionAgeingDto().getAgeingPolicy()!= null &&
						!objDto.getCollectionAgeingDto().getAgeingPolicy().equals("")) {
					sbf.append("And a.collectionAgeingDto.ageingPolicy='" + objDto.getCollectionAgeingDto().getAgeingPolicy() + "' ");
				}
			}
			
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
	public CollectionAgeingActionDto getCollectionAgeingAction(String sno)
			throws TPlusException {
		CollectionAgeingActionDto collectionAgeing = new CollectionAgeingActionDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			collectionAgeing = (CollectionAgeingActionDto) session.get(
					CollectionAgeingActionDto.class, sno);
			collectionAgeing.getCollectionAgeingDto();
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
					.createQuery("Select count(*) From CollectionAgeingActionDto where ageingPolicy=:id and issuerId=:issuerId");
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
	public boolean checkOverlap(int day, String issuerId)
			throws TPlusException {
		boolean check = false;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From CollectionAgeingActionDto where days=:days and issuerId=:issuerId");
			qry.setInteger("days", day);
			qry.setString("issuerId", issuerId);

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
	public boolean delete(CollectionAgeingActionDto objDto) throws TPlusException {
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


	@Override
	public Map getAgeingList(String issuerId) throws TPlusException {
		Map ageingList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CollectionAgeingDto where issuerId='"
							+ issuerId + "' order by ageingPolicy");

			List policyList = qry.list();

			for (Iterator it = policyList.iterator(); it.hasNext();) {
				CollectionAgeingDto objDto = new CollectionAgeingDto();
				objDto = (CollectionAgeingDto) it.next();
				ageingList.put(objDto.getAgeingPolicy(),
						objDto.getAgeingPolicy());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getAgeingList in BaseDAOIMpl "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getAgeingList in BaseDAOIMpl "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return ageingList;
	}


	@Override
	public Map getRemainderTypeList(String groupId) throws TPlusException {
		Map remainderTypeList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("From CodeMasterDto cm where cm.id.groupId = '"
							+ groupId + "' order by cm.codeDesc");

			List policyList = qry.list();

			for (Iterator it = policyList.iterator(); it.hasNext();) {
				CodeMasterDto objDto = new CodeMasterDto();
				objDto = (CodeMasterDto) it.next();
				remainderTypeList.put(objDto.getId().getCodeId(),
						objDto.getCodeDesc());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getAgeingList in BaseDAOIMpl "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getAgeingList in BaseDAOIMpl "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return remainderTypeList;
	}

}
