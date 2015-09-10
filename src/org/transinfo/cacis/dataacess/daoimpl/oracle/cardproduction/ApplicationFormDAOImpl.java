package org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction;

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
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplicationFormDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormSearchDto;
import org.transinfo.cacis.dto.common.ApplicationMasterDto;

@SuppressWarnings("unchecked")
public class ApplicationFormDAOImpl extends BaseDAOImpl implements
		ApplicationFormDAO {

	private Logger logger = Logger.getLogger(ApplicationFormDAOImpl.class);

	public Collection search(ApplicationFormSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {
			sbf.append("select apd.applicationId,apd.customerName,to_char(apd.updatedDate,'dd-mm-yyyy'), apd.applicationStatus ");
			sbf.append("FROM  ApplicationFormDto apd ");
			sbf.append("WHERE 1=1 ");

			if (objSearchDto.getCustomerName() != null
					&& !objSearchDto.getCustomerName().equals("")) {
				sbf.append("and upper(apd.customerName) like '%"
						+ objSearchDto.getCustomerName().toUpperCase() + "%' ");
			}
			
			if (objSearchDto.getIdNumber() != null
					&& !objSearchDto.getIdNumber().equals("")) {
					sbf.append("and upper(apd.idNumber) like '%"
							+ objSearchDto.getIdNumber().toUpperCase() + "%'");
			}

			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and apd.branchId = '" + objSearchDto.getBranchId() + "' ");
			}
			
			sbf.append("and apd.issuerId ='" + objSearchDto.getIssuerId()+ "' ");
			sbf.append("order by apd.applicationId desc ");
			
			// Session session = HibernetFactory.currentSession();
			// Query qry = session.createQuery(sbf.toString());
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Exceptionin ApplicationFormDAOImpl search method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplicationFormDAOImpl search method " + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public ApplicationFormDto getApplicationForm(String applicationId)
			throws TPlusException {

		ApplicationFormDto apd = null;

		try {
			System.out.println("getApplForm");
			System.out.println("++++DAOIMPL+++++appID: ++"+applicationId);
			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
			// List qry =
			// session.createQuery("from ApplicationFormDto appl left join fetch appl.appDocProofs left join fetch appl.appCardProducts where appl.applicationId ='3'").list();
			apd = (ApplicationFormDto) session.get(ApplicationFormDto.class,
					applicationId);
			// session.load(cpd1,strCardProductId);

			tx.commit();
		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Exceptionin ApplicationFormDAOImpl getApplicationForm method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplicationFormDAOImpl getApplicationForm method "
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return apd;

	}

	public boolean add(ApplicationFormDto objDto) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		ApplicationMasterDto objAppMaster = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// inserting into Application_master Table
			objAppMaster = new ApplicationMasterDto();
			System.out
					.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("APPL Id=" + objDto.getApplicationId());
			objAppMaster.setApplicationId(objDto.getApplicationId());
			objAppMaster.setIssuerId(objDto.getIssuerId());
			objAppMaster.setApplicationStatus(objDto.getApplicationStatus());
			objAppMaster.setApplicationType(objDto.getApplicationType());
			objAppMaster.setIdNumber(objDto.getIdNumber());
			objAppMaster.setOpenDate(objDto.getOpenDate());
			objAppMaster.setUserId(objDto.getUserId());
			objAppMaster.setUpdatedDate(objDto.getUpdatedDate());
			System.out.println("Bfr Saving AppMaster");
			session.save(objAppMaster);

			System.out.println("Bfr Saving AppForm");
			// inserting into Applicationforms table
			session.save(objDto);

			System.out.println("Aft Saving AppForm");
			tx.commit();
			// tx.rollback();
			bolExecute = true;

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Exceptionin ApplicationFormDAOImpl add method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplicationFormDAOImpl add method " + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;

	}

	public boolean update(ApplicationFormDto objDto) throws TPlusException {

		Transaction tx = null;
		boolean update = false;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);
			tx.commit();
			update = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("in ApplicationFormDAOIMpl update method:"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplicationFormDAOIMpl update method:" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return update;

	}

	public boolean delete(ApplicationFormDto objDto) throws TPlusException {

		boolean delete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE ApplicationFormDto SET applicationStatus =:applicationstatus WHERE applicationId=:applicationid";

			int count = session.createQuery(sql).setInteger(
					"applicationstatus", objDto.getApplicationStatus())
					.setString("applicationid", objDto.getApplicationId())
					.executeUpdate();

			System.out.println("Delete Count=" + count);
			if (count > 0)
				delete = true;
			tx.commit();
		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("in ApplicationFormDAOIMpl delete method:"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplicationFormDAOIMpl delete method:" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return delete;

	}

	public int checkExistrecord(Object commonObj) throws TPlusException {

		int res = 0;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			if (commonObj instanceof ApplicationFormDto) {
				ApplicationFormDto objDto = (ApplicationFormDto) commonObj;
				sbf
						.append("select count(*) from ApplicationFormDto apd where apd.applicationId= '"
								+ objDto.getApplicationId() + "' ");
			} else if (commonObj instanceof ApplicationMasterDto) {
				ApplicationMasterDto objMstDto = (ApplicationMasterDto) commonObj;
				sbf
						.append("select count(*) from ApplicationMasterDto mst where mst.idNumber ='"
								+ objMstDto.getIdNumber() + "' ");
				sbf.append("and mst.applicationType ="
						+ objMstDto.getApplicationType() + " ");
				sbf.append("and mst.closeDate is  null ");
			}
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out
					.println("After ApplicationFormDAOIMPL checkExistrecord() count:"
							+ res);
			logger.info("After ApplicationFormDAOIMPL checkExistrecord() count:"
					+ res);
		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error while checking the Exising record in ApplicationFormDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error while checking the Exising record in ApplicationFormDAOImpl"
							+ e);
		}finally { 
			HibernetFactory.closeSession(); 
		}
		 

		return res;

	}

	public int isDuplicateNRC(Object commonObj) throws TPlusException {

		int res = 0;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			if (commonObj instanceof ApplicationFormDto) {
				ApplicationFormDto objDto = (ApplicationFormDto) commonObj;
				sbf.append("select count(*) from ApplicationFormDto apd where ( apd.idNumber= '" + objDto.getIdNumber() + "' ");
				
				String suppId = objDto.getSupplIdNumber();
				if(suppId != null && !"".equals(suppId)){
					sbf.append("or apd.supplIdNumber = '" + suppId + "' ) ");
				}else{
					sbf.append(" ) ");
				}
				
				String appId = objDto.getApplicationId();
				if(appId != null && !"".equals(appId)){
					sbf.append("and apd.applicationId <> '" + objDto.getApplicationId() + "' ");
				}
			}
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out
					.println("After ApplicationFormDAOIMPL checkExistrecord() count:"
							+ res);
			logger.info("After ApplicationFormDAOIMPL checkExistrecord() count:"
					+ res);
		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error while checking the Exising record in ApplicationFormDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error while checking the Exising record in ApplicationFormDAOImpl"
							+ e);
		}finally { 
			HibernetFactory.closeSession(); 
		}
		 

		return res;

	}

	public boolean isDuplicateNRCNewMethod(Object commonObj) throws TPlusException {

		boolean res = false;
		ApplicationFormDto objApplicationFormDto = null;
		
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			
			ApplicationFormDto objDto = (ApplicationFormDto) commonObj;
			sbf.append("from ApplicationFormDto apd where ( apd.idNumber= '" + objDto.getIdNumber() + "' ");
			
			String suppId = objDto.getSupplIdNumber();
			if(suppId != null && !"".equals(suppId)){
				sbf.append("or apd.supplIdNumber = '" + suppId + "' ) ");
			}else{
				sbf.append(" ) ");
			}
			
			String appId = objDto.getApplicationId();
			if(appId != null && !"".equals(appId)){
				sbf.append("and apd.applicationId <> '" + objDto.getApplicationId() + "' ");
			}
				
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			
			if(list != null && list.size() > 0){
				objApplicationFormDto = (ApplicationFormDto)list.get(0);
				
				int appStatus = objApplicationFormDto.getApplicationStatus();
				if(appStatus == 0 || appStatus == 1 || appStatus == 5){
					res = true;
				}else if(appStatus == 4){
					sbf = new StringBuffer();
					sbf.append("select cdto.status ");
					sbf.append("from CardsDto cdto, ApplicationProcessDto appdto ");
					sbf.append("where cdto.customerId = appdto.customerId and appdto.idNumber='"+objDto.getIdNumber()+"'");
					
					Query qry2 = session.createQuery(sbf.toString());
					List list2 = qry2.list();
					if(list2 != null && list2.size() > 0){
						
						String cardStatus = "";
						for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
							cardStatus = (String) iterator.next();
							if("A".equals(cardStatus)){
								res = true;
								break;
							}
						}
					}
				}
			}
			
		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error while checking the Exising record in ApplicationFormDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error while checking the Exising record in ApplicationFormDAOImpl"
							+ e);
		}finally { 
			HibernetFactory.closeSession(); 
		}
		 

		return res;

	}

	public int checkExistrecordOnUpdate(Object commonObj) throws TPlusException {

		int res = 0;
		
		try {
			
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			
			ApplicationFormDto objDto = (ApplicationFormDto) commonObj;
			
			sbf.append("select count(*) from ApplicationMasterDto mst where mst.idNumber ='" + objDto.getIdNumber() + "' ");
			sbf.append("and mst.applicationType =" + objDto.getApplicationType() + " ");
			sbf.append("and mst.closeDate is  null ");
			sbf.append("and mst.applicationId <> '" + objDto.getApplicationId() + "' ");
			
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out
					.println("After ApplicationFormDAOIMPL checkExistrecord() count:"
							+ res);
			logger.info("After ApplicationFormDAOIMPL checkExistrecord() count:"
					+ res);
		}catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error while checking the Exising record in ApplicationFormDAOImpl"
							+ e);
		}finally { 
			HibernetFactory.closeSession(); 
		}

		return res;

	}

}
