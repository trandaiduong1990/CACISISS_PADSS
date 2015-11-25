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
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAccountDetailsDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.AgeingAction;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;

public class CollectionAccountDetailsDAOImpl extends BaseDAOImpl implements CollectionAccountDetailsDAO {
	private Logger logger = Logger.getLogger(CollectionAccountDetailsDAOImpl.class);

	@Override
	public boolean update(CollectionDto objDto) throws TPlusException {

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
					.println("Error in CollectionAccountDetailsDAOImpl update method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionAccountDetailsDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}


	@Override
	public Collection search(CollectionDto objDto, String customerName, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select c.cardNo, ap.customerName, ca.creditLimit, cs.statAmt, c.createdDate, c.colectionId ");
			sbf.append("From CollectionDto c, CardsDto card, ApplicationProcessDto ap, CustomerAccountDto ca, CustomerStatement cs  Where ");
			sbf.append("c.cardNo = card.cardNumber and card.customerId = ap.customerId and ");
			sbf.append("c.cardNo = cs.cardNo and c.accountId = ca.accountId ");
			
			if(objDto.getCardNo()!=null && objDto.getCardNo()!=0) {
				sbf.append("and c.cardNo = " + objDto.getCardNo() + " ");
			}
			if(customerName != null && !customerName.equals("")) {
				sbf.append("and ap.customerName = '" + customerName + "' ");
			}
			if(objDto.getStatus()!=null && !objDto.getStatus().equals("")) {
				sbf.append("and c.status = '" + objDto.getStatus() + "' ");
			}
			if(objDto.getCurrentCollector()!=null && !objDto.getCurrentCollector().equals("")) {
				sbf.append("and c.colectRef = 'A' and c.currentCollector = '" + objDto.getCurrentCollector() + "' ");
			}
			
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out
					.println("Error in CollectionAccountDetailsDAOImpl search method : "
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionAccountDetailsDAOImpl search  method :"
							+ ex);
		}

		return objSearchCollection;
	}


	@Override
	public CollectionDto getCollection(String colectId)	throws TPlusException {
		CollectionDto collection = new CollectionDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			collection = (CollectionDto) session.get(
					CollectionDto.class, colectId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getCollection method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getCollection" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return collection;
	}

	@Override
	public Map getStatusList() throws TPlusException {
		Map statusList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CodeMasterDto dto where dto.id.groupId='COL_ACCOUNT' "
							+ "order by dto.codeDesc ");

			List policyList = qry.list();

			for (Iterator it = policyList.iterator(); it.hasNext();) {
				CodeMasterDto objDto = new CodeMasterDto();
				objDto = (CodeMasterDto) it.next();
				statusList.put(objDto.getId().getCodeId(),
						objDto.getCodeDesc());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getStatusList "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getStatusList "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return statusList;
	}


	@Override
	public Map getAgentList(String issuerId) throws TPlusException {
		Map agentNameList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CollectionAgentDto dto where dto.status = 'A' and dto.issuerId='"
							+ issuerId + "' order by dto.agentName");

			List agentList = qry.list();

			for (Iterator it = agentList.iterator(); it.hasNext();) {
				CollectionAgentDto objDto = new CollectionAgentDto();
				objDto = (CollectionAgentDto) it.next();
				agentNameList.put(objDto.getAgentId(),
						objDto.getAgentName() + " " + objDto.getAgentId());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getAgentList "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getAgentList "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return agentNameList;
	}


	@Override
	public Map getUserBranchList(String issuerId) throws TPlusException {
		Map userBranchList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from UserMasterDto um where um.id.userId in (select distinct dto.userId from AssignUserDto dto where dto.issuerId='"
							+ issuerId + "' and dto.roleId = 'CM') ");

			List userList = qry.list();

			for (Iterator it = userList.iterator(); it.hasNext();) {
				UserMasterDto objDto = new UserMasterDto();
				objDto = (UserMasterDto) it.next();
				userBranchList.put(objDto.getId().getUserId(),
						objDto.getFirstName() + " " + objDto.getLastName());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getUserBranchList "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getUserBranchList "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return userBranchList;
	}

}
