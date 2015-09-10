package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.EMVProfileDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.EMVProfileDto;
import org.transinfo.cacis.dto.settings.EMVProfileSearchDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;

/**
 * EMVProfileDAOImpl
 * 
 * @author hoang-vu
 * 
 */
public class EMVProfileDAOImpl extends BaseDAOImpl implements EMVProfileDAO {

	private Logger logger = Logger.getLogger(CardProductDAOImpl.class);
	
	@Override
	public boolean create(EMVProfileDto objEMVProfileDto) throws TPlusException {
		
		boolean boolCreate = false;
		Transaction tx = null;
		
		objEMVProfileDto.setStatus("A");
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objEMVProfileDto);
			
			tx.commit();
			boolCreate = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in EMVProfileDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in EMVProfileDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolCreate;
	}

	@Override
	public int checkExitsRecord(EMVProfileDto objDto) throws TPlusException {
		
		int resCord = 0;
		
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From EMVProfileDto emvp where emvp.emvProfileName=:abc ");
			qry.setString("abc", objDto.getEmvProfileName());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out.println("After EMVProfileDAOImpl checkExistrecord()"
					+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in EMVProfileDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in EMVProfileDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return resCord;
	}

	@Override
	public Collection search(EMVProfileSearchDto objDto, int pageNo) throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		
		try {
			
			sbf.append("Select emvp.emvProfileName, emvp.applType, emvp.applCryptogram, emvp.issuerAuthentication  ");
			sbf.append("From EMVProfileDto emvp ");
			sbf.append("Where emvp.emvProfileName='" + objDto.getEmvProfileName() + "' ");
			sbf.append("And emvp.status='A' ");
			
			if (objDto.getApplType() != null
					&& !objDto.getApplType().equals("")) {
				sbf.append("And emvp.applType = '" + objDto.getApplType() + "' ");
			}
			
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in EMVProfileDAOImpl search method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in EMVProfileDAOImpl search  method :" + ex);
		}
		
		return objSearchCollection;
	}

	@Override
	public EMVProfileDto getEMVProfileDto(String emvProfileName)
			throws TPlusException {

		EMVProfileDto objDto = new EMVProfileDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From EMVProfileDto emvp ");
			sbf.append("Where emvp.emvProfileName = '"
					+ emvProfileName + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (EMVProfileDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in EMVProfileDAOImpl getEMVProfileDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in EMVProfileDAOImpl getEMVProfileDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public boolean update(EMVProfileDto objDto) throws TPlusException {

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
			System.out.println("Error in EMVProfileDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in EMVProfileDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolUpdate;
	}

	@Override
	public boolean delete(EMVProfileDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		
		objDto.setStatus("D");
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE EMVProfileDto SET status=:status WHERE emvProfileName=:emvprofilename";

			int count = session.createQuery(sql).setString("emvprofilename",
					objDto.getEmvProfileName()).setString("status",
					objDto.getStatus()).executeUpdate();

			tx.commit();
			if (count > 0)
				boolDelete = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in EMVProfileDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in EMVProfileDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolDelete;
	}

	@Override
	public int checkEmvApplTypeCryto(EMVProfileDto objDto)
			throws TPlusException {

		int resCord = 0;
		
		try {
			Session session = HibernetFactory.currentSession();
			
			Query qry = session
					.createQuery("Select count(*) From EMVProfileDto emvp where emvp.applType=:appltype and emvp.applCryptogram=:applcry");
			qry.setString("appltype", objDto.getApplType());
			qry.setString("applcry", objDto.getApplCryptogram());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out.println("After EMVProfileDAOImpl checkEmvApplTypeCryto()"
					+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in EMVProfileDAOImpl checkEmvApplTypeCryto method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in EMVProfileDAOImpl checkEmvApplTypeCryto method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return resCord;
	}

	@Override
	public Map getCryptogramList(String applType) throws TPlusException {

		Map cryptograms = new HashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("from CodeMasterDto dto where dto.id.groupId = '" + applType + "'");

			List lstCodeMaster = qry.list();

			for (Iterator it = lstCodeMaster.iterator(); it.hasNext();) {
				CodeMasterDto objCodeMaster = new CodeMasterDto();
				objCodeMaster = (CodeMasterDto) it.next();
				cryptograms.put(objCodeMaster.id.getCodeId(), objCodeMaster.getCodeDesc());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("while retrieving cryptogramList in BaseDAOIMpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving cryptogramList in BaseDAOIMpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return cryptograms;
	}

}
