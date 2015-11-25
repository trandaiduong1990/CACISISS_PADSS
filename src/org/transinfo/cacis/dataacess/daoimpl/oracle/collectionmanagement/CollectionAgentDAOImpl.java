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
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionConfigDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyPolicyDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.administration.LicenseMasterDto;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicySearchDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;

public class CollectionAgentDAOImpl extends BaseDAOImpl implements CollectionAgentDAO {
	private Logger logger = Logger.getLogger(CollectionAgentDAOImpl.class);

	@Override
	public boolean add(CollectionAgentDto objDto) throws TPlusException {

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
					.println("Error in CollectionAgentDAOImpl upload method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionAgentDAOImpl upload  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolCreate;
	}


	@Override
	public boolean update(CollectionAgentDto objDto) throws TPlusException {

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
					.println("Error in CollectionAgentDAOImpl update method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionAgentDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}

	@Override
	public Map agentList(String issuerId) throws TPlusException {

		Map agentNameList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CollectionAgentDto where status = 'A' and issuerId='"
							+ issuerId + "' order by agentName");

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
					.println("while retrieving agentList in BaseDAOIMpl "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving agentList in BaseDAOIMpl "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return agentNameList;
	}


	@Override
	public Collection search(CollectionAgentDto objDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select ca.agentName, ca.contactName, ca.contactPhoneNo, ca.emailId, ca.agentId ");
			sbf.append("From CollectionAgentDto ca Where ca.status ='A' and ca.issuerId='" + objDto.getIssuerId() + "' ");
			
			if(objDto.getAgentId() != null && !objDto.getAgentId().equals("")) {
				sbf.append("and ca.agentId = '" + objDto.getAgentId() + "' ");
			}
			
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out
					.println("Error in CollectionAgentDAOImpl search method : "
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionAgentDAOImpl search  method :"
							+ ex);
		}

		return objSearchCollection;
	}


	@Override
	public CollectionAgentDto getCollectionAgent(String agentId)
			throws TPlusException {
		CollectionAgentDto collectionAgent = new CollectionAgentDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			collectionAgent = (CollectionAgentDto) session.get(
					CollectionAgentDto.class, agentId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getCollectionAgent method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getCollectionAgent" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return collectionAgent;
	}


	@Override
	public boolean checkExitsRecord(CollectionAgentSetupForm objForm)
			throws TPlusException {
		boolean check = false;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From CollectionAgentDto where agentId=:id and issuerId=:issuerId");
			qry.setString("id", objForm.getAgentId());
			qry.setString("issuerId", objForm.getIssuerId());

			List list = qry.list();
			check = ((Integer) list.get(0)).intValue() == 0;
			System.out
					.println("After CollectionAgentDAOImpl checkExistrecord()"
							+ check);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in CollectionAgentDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CollectionAgentDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}

		return check;
	}


	@Override
	public Map getAgentType() throws TPlusException {
		Map agentTypeList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CodeMasterDto dto where dto.id.groupId = 'AGENT_TYPE' ");

			List agentList = qry.list();

			for (Iterator it = agentList.iterator(); it.hasNext();) {
				CodeMasterDto objDto = new CodeMasterDto();
				objDto = (CodeMasterDto) it.next();
				agentTypeList.put(objDto.getId().getCodeId(),
						objDto.getCodeDesc());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getAgentType "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getAgentType "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return agentTypeList;
	}
}
