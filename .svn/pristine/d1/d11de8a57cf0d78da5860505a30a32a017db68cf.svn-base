package org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplicationProcessDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings("unchecked")
public class ApplicationProcessDAOImpl extends BaseDAOImpl implements
		ApplicationProcessDAO {

	public Collection search(ApplicationProcessSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		// CommonDataBean objCommBean =null;
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {
			/*
			 * sbf.append("Select distinct af.applicationId, af.customerName , ct.cardType, "
			 * );sbf.append(
			 * "cts.cardProductName, cpt.cardProductType, af.updatedDate ");
			 * sbf.append(
			 * "From ApplicationFormDto af, CardProductDto cts,ApplicationCardProductsDto acp,CardProductTypeDto cpt, CardTypeDto ct "
			 * ); sbf.append("WHERE ");
			 * sbf.append("af.applicationId = acp.application_Id ");
			 * sbf.append("and cts.cardProductId = acp.cardProductId ");
			 * sbf.append("and cts.cardProductTypeId = cpt.cardProductTypeId ");
			 * sbf.append("and cts.cardTypeId = ct.cardTypeId ");
			 * sbf.append("and  af.applicationStatus =0 ");sbf.append(
			 * "group by af.applicationId,af.customerName, cts.cardProductName,ct.cardType, cpt.cardProductType,af.upDatedDate "
			 * ); sbf.append("order by af.applicationId,af.upDatedDate");
			 */

			sbf
					.append("select apd.applicationId,apd.customerName,to_char(apd.updatedDate,'dd-mm-yyyy') FROM  ApplicationFormDto apd ");
			sbf.append("where (apd.applicationStatus = "
					+ CommonCodes.APPLICATIONSTATUS_NEW
					+ " OR apd.applicationStatus = "
					+ CommonCodes.APPLICATIONSTATUS_PENDING + ") ");

			if (objSearchDto.getCustomerName() != null
					&& !objSearchDto.getCustomerName().equals("")) {
				sbf.append("and upper(apd.customerName) like '%"
						+ objSearchDto.getCustomerName().toUpperCase() + "%' ");

			}
			if (objSearchDto.getIdNumber() != null
					&& !objSearchDto.getIdNumber().equals("")) {

				sbf.append("and upper(apd.idNumber) like '%" + objSearchDto.getIdNumber().toUpperCase()
						+ "%'");

			}
			
			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and apd.branchId ='" + objSearchDto.getBranchId()+ "'");
			}
			
			sbf.append("and apd.issuerId ='" + objSearchDto.getIssuerId()+ "'");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

			/*
			 * sbf.append("FROM  ApplicationFormDto af where af.applicationStatus =:applicationstatus "
			 * ); Session session = HibernetFactory.currentSession(); Query qry
			 * = session.createQuery(sbf.toString()); // List listAppProcess =
			 * qry.list(); objSearchCollection =
			 * (ArrayList)qry.setInteger("applicationstatus",CommonCodes.
			 * APPLICATIONSTATUS_NEW).list();
			 * 
			 * for(Iterator it = listAppProcess.iterator();it.hasNext();){
			 * Object obj[]= (Object[])it.next(); //
			 * System.out.println("Data"+obj.length); //
			 * System.out.println("Data"+(String)obj[0]); //objCommBean = new
			 * CommonDataBean (); //ArrayList arlresult = (ArrayList)it.next();
			 * //objCommBean.setColumn1((String)it.next());
			 * /*objCommBean.setColumn2((String)arlresult.get(1));
			 * objCommBean.setColumn3((String)arlresult.get(2));
			 * objCommBean.setColumn4((String)arlresult.get(3));
			 * objCommBean.setColumn5((String)arlresult.get(4));
			 * objCommBean.setColumn6((String)arlresult.get(5));
			 * objSearchCollection.add(objCommBean);
			 */

			// }

		} catch (Exception e) {
			System.out
					.println("Error in while retrieving data in ApplicationProcessDAOImpl search method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving data in ApplicationProcessDAOImpl in search method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	/* to update the ApplicationForms table during the application process */
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
			System.out.println("in ApplicationProcessDAOImpl update method:"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplicationProcessDAOImpl update method:" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return update;

	}

	public ApplicationProcessDto getApplicationProcessDto(String applicationId)
			throws TPlusException {

		ApplicationFormDto apd = null;
		Transaction tx = null;
		ApplicationProcessDto objAppProcessDto = new ApplicationProcessDto();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			/*
			 * String sql =
			 * "from ApplicationFormDto af where applicationStatus =:applicationstatus and applicaionId =:applicationid"
			 * ; List appList =
			 * session.createQuery(sql).setString("applicationid"
			 * ,objAppProcessDto.getApplicationId())
			 * .setInteger("applicationstatus",CommonCodes.
			 * APPLICATIONSTATUS_NEW).list(); apd
			 * =(ApplicationFormDto)appList.get(0);
			 */
			// List qry =
			// session.createQuery("from ApplicationFormDto appl left join fetch appl.appDocProofs left join fetch appl.appCardProducts where appl.applicationId ='3'").list();
			apd = (ApplicationFormDto) session.get(ApplicationFormDto.class,
					applicationId);

			tx.commit();
			BeanUtils.copyProperties(objAppProcessDto, apd);
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting ApplicationProcessDto data in getApplicationProcessDto method"
							+ e.getMessage());
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: Error while getting ApplicationProcessDto data in getApplicationProcessDto method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objAppProcessDto;

	}

	public boolean accept(ApplicationProcessDto objAppProcessDto,
			CustomerAddressDto supplAddressDto) throws TPlusException {

		int count;
		boolean accept = false;
		Transaction tx = null;

		CardEmbossingDto objCardEb = null;
		CardActivityDto objCardActivity = null;
		//CustomerLimitsDto objCustomerLimits = null;

		Set listCards = null;
		int appStstus = 1;
		String appId = objAppProcessDto.getApplicationId();
		String cycleNo = String.valueOf(objAppProcessDto.getCycleNo());
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.save(objAppProcessDto);

			if (objAppProcessDto.getCheckedSupplCardRequired() == 'Y') {
				ApplicationProcessDto objApplicationProcessDtoSuppl = new ApplicationProcessDto();
				objApplicationProcessDtoSuppl.setCustomerId(IdsGenartion
						.GenerateCustomerId());
				objApplicationProcessDtoSuppl.setApplicationId(objAppProcessDto
						.getApplicationId());
				objApplicationProcessDtoSuppl.setIssuerId(objAppProcessDto
						.getIssuerId());
				objApplicationProcessDtoSuppl.setCustomerStatus(1);
				objApplicationProcessDtoSuppl.setBranchId(objAppProcessDto
						.getBranchId());
				objApplicationProcessDtoSuppl.setCustomerName(objAppProcessDto
						.getSupplCustomerName());
				objApplicationProcessDtoSuppl.setSurName(objAppProcessDto
						.getSupplSurName());
				objApplicationProcessDtoSuppl.setEmbossingName(objAppProcessDto
						.getSupplEmbossingName());
				objApplicationProcessDtoSuppl
						.setCustomerTypeId(objAppProcessDto.getCustomerTypeId());
				objApplicationProcessDtoSuppl.setDob(objAppProcessDto
						.getSupplDob());
				objApplicationProcessDtoSuppl.setPob(objAppProcessDto
						.getSupplPob());
				objApplicationProcessDtoSuppl.setGender(objAppProcessDto
						.getSupplGender());
				objApplicationProcessDtoSuppl.setMaritalStatus(objAppProcessDto
						.getSupplMaritalStatus());
				objApplicationProcessDtoSuppl.setIdNumber(objAppProcessDto
						.getSupplIdNumber());
				objApplicationProcessDtoSuppl.setIdDate(objAppProcessDto
						.getSupplIdDate());
				objApplicationProcessDtoSuppl.setExpDate(objAppProcessDto
						.getSupplIdExpire());
				objApplicationProcessDtoSuppl.setIdPlace(objAppProcessDto
						.getSupplIdPlace());
				objApplicationProcessDtoSuppl.setNationality(objAppProcessDto
						.getSupplNationality());
				objApplicationProcessDtoSuppl.setEmail(objAppProcessDto
						.getSupplEmail());
				objApplicationProcessDtoSuppl.setIncome(objAppProcessDto
						.getSupplIncome());
				objApplicationProcessDtoSuppl.setUpdatedDate(new Date());
				objApplicationProcessDtoSuppl.setUserId(objAppProcessDto
						.getUserId());
				objApplicationProcessDtoSuppl
						.setParenetCustomerId(objAppProcessDto.getCustomerId());
				objApplicationProcessDtoSuppl
						.setSelectedAppCardProducts(objAppProcessDto
								.getSelectedAppCardProducts());
				objApplicationProcessDtoSuppl.setBillingTo("S");
				// objApplicationProcessDtoSuppl.getCustomerAccount().add(objAppProcessDto.getCustomerAccount());
				supplAddressDto.setAddressType("S");
				objApplicationProcessDtoSuppl.getApplicationAddress().add(
						supplAddressDto);
				// objApplicationProcessDtoSuppl.getCustomerAccount().add(objAppProcessDto.getCustomerAccount());

				session.save(objApplicationProcessDtoSuppl);
			}

			// updating in ApplicationForms table
			String appSql = "UPDATE ApplicationFormDto SET applicationStatus =:appacceptstatus,updatedDate=:update,cycleNo=:cycno WHERE applicationId=:applicationid";
			count = session.createQuery(appSql)
							.setString("applicationid", appId)
							.setString("cycno", cycleNo)
							.setDate("update", new Date())
							.setInteger("appacceptstatus", appStstus)
							.executeUpdate();

			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate,updatedDate=:update WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql)
							.setString("applicationid", appId)
							.setInteger("appacceptstatus", appStstus)
							.setDate("closedate", new Date())
							.setDate("update", new Date())
							.executeUpdate();

			// getting Account object
			Set set = objAppProcessDto.getCustomerAccount();
			for (Iterator it = set.iterator(); it.hasNext();) {
				CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
				listCards = custAcctDto.getCustomerCards();
				System.out.println("Card Size=" + listCards.size());
			}

			for (Iterator it = listCards.iterator(); it.hasNext();) {
				CardsDto objCards = (CardsDto) it.next();

				// iserting into customer_limits table
				/*objCustomerLimits = new CustomerLimitsDto();
				objCustomerLimits.setCardNumber(objCards.getCardNumber());
				objCustomerLimits.setAmtPerTranx(objAppProcessDto
						.getAmtPerTranx());
				objCustomerLimits.setDailyLimitCount(objAppProcessDto
						.getDailyLimitCount());
				objCustomerLimits.setDailyLimitAmt(objAppProcessDto
						.getDailyLimitAmt());
				objCustomerLimits.setMonthlyLimitCount(objAppProcessDto
						.getMonthlyLimitCount());
				objCustomerLimits.setMonthlyLimitAmt(objAppProcessDto
						.getMonthlyLimitAmt());
				objCustomerLimits.setCurrencyCode(String
						.valueOf(objAppProcessDto.getCurrencyCode()));
				objCustomerLimits.setIssuerId(objAppProcessDto.getIssuerId());

				session.save(objCustomerLimits);*/

				// iserting into cardsEmbossing table
				objCardEb = new CardEmbossingDto();
				// objCardEb.setCardNumber(objCards.getCardNumber());
				// objCardEb.setCustomerId(objCards.getCustomerId());
				objCardEb.setIssuerId(objCards.getIssuerId());
				// objCardEb.setMagStripeData("Embossing Data");
				objCardEb.setStatus(CommonCodes.CARD_PROCESS_NEW);
				objCardEb.setLastUpdatedBy(objAppProcessDto.getUserId());
				objCardEb.setUpdatedDate(objAppProcessDto.getUpdatedDate());

				session.save(objCardEb);

				// iserting into cardsEmbossing table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCards.getCardNumber());
				objCardActivity.setActivity("New Card Creation");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objAppProcessDto.getUserId());
				objCardActivity.setReason(objAppProcessDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objAppProcessDto.getUserId());
				objCardActivity.setUpdatedDate(objAppProcessDto
						.getUpdatedDate());

				session.save(objCardActivity);

			}

			// session.flush();
			tx.commit();
			if (count > 0)
				accept = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting the  data in ApplicationProcessDAOImpl Accept Method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the  data in  ApplicaionProcessDAOImpl Accept method"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	public boolean reject(ApplicationProcessDto objprocessDto)
			throws TPlusException {

		boolean reject = false;
		int count;
		Transaction tx = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String appSql = "UPDATE ApplicationFormDto SET applicationStatus =:apprejectstatus,updatedDate=:update WHERE applicationId=:applicationid";
			count = session.createQuery(appSql)
							.setString("applicationid", objprocessDto.getApplicationId())
							.setDate("update", new Date())
							.setInteger("apprejectstatus", objprocessDto.getApplicationStatus())
							.executeUpdate();

			// updating Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:apprejectstatus,closeDate =:closedate,updatedDate=:update WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql)
							.setString("applicationid", objprocessDto.getApplicationId())
							.setDate("update", new Date())
							.setInteger("apprejectstatus", objprocessDto.getApplicationStatus())
							.setDate("closedate", objprocessDto.getUpdatedDate())
							.executeUpdate();

			// inserting into LettersDispatch table
			/*objDispLetters = new DispatchLetterSearchDto();
			objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
			objDispLetters.setLetterId(CommonCodes.NEWCARD_REJECT_APPLICATION);
			objDispLetters.setIssuerId(objprocessDto.getIssuerId());
			objDispLetters.setApplicationId(objprocessDto.getApplicationId());
			objDispLetters.setStatus(CommonCodes.CARD_PROCESS_NEW);
			objDispLetters.setLastUpdateDate(objprocessDto.getUpdatedDate());
			objDispLetters.setLastUpdatedBy(objprocessDto.getUserId());

			session.save(objDispLetters);*/

			tx.commit();
			System.out.println("rejct Count=" + count);
			if (count > 0)
				reject = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while rejecting the applicationform in ApplicaionProcessDAOImpl in reject method:"
							+ e.getMessage());
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error:  while rejecting the applicationform in ApplicaionProcessDAOImpl in reject method:"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return reject;
	}

	public boolean pending(ApplicationProcessDto objprocessDto)
			throws TPlusException {

		boolean pending = false;
		int count;
		Transaction tx = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String appSql = "UPDATE ApplicationFormDto SET applicationStatus =:apppendingstatus,updatedDate=:update WHERE applicationId=:applicationid";
			count = session.createQuery(appSql)
							.setString("applicationid", objprocessDto.getApplicationId())
							.setDate("update", new Date())
							.setInteger("apppendingstatus", objprocessDto.getApplicationStatus())
							.executeUpdate();

			// updating Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:apppendingstatus,updatedDate=:update WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql)
							.setString("applicationid", objprocessDto.getApplicationId())
							.setDate("update", new Date())
							.setInteger("apppendingstatus", objprocessDto.getApplicationStatus())
							.executeUpdate();

			// inserting into LettersDispatch table
			/*objDispLetters = new DispatchLetterSearchDto();
			objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
			objDispLetters.setLetterId(CommonCodes.NEWCARD_PENDING_APPLICATION);
			objDispLetters.setIssuerId(objprocessDto.getIssuerId());
			objDispLetters.setApplicationId(objprocessDto.getApplicationId());
			objDispLetters.setStatus(CommonCodes.CARD_PROCESS_NEW);
			objDispLetters.setLastUpdateDate(objprocessDto.getUpdatedDate());
			objDispLetters.setLastUpdatedBy(objprocessDto.getUserId());

			session.save(objDispLetters);*/

			tx.commit();
			System.out.println("pending Count=" + count);
			if (count > 0)
				pending = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while rejecting the applicationform in ApplicaionProcessDAOImpl in pending method:"
							+ e.getMessage());
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error:  while rejecting the applicationform in ApplicaionProcessDAOImpl in pending method:"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return pending;
	}

	public Collection customerHistory(String custIdNumber, String appId)
			throws TPlusException {

		Collection historyCollection = new ArrayList();
		Transaction tx = null;
		CommonDataBean objCommBean = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "select ch.applicationId,ch.applicationStatus,ch.cardStatus, ch.id.cardNumber,ch.idNumber,ch.creditLimit,ch.cardType,ch.id.cardProduct,ch.cardProductType,ch.actionDate From CustomerHistoryDto ch where ch.idNumber =:idnumber and applicationId <>:appId ";
			List resultList = session.createQuery(sql)
					.setString("appId", appId).setString("idnumber",
							custIdNumber).list();
			for (Iterator it = resultList.iterator(); it.hasNext();) {
				Object obj[] = (Object[]) it.next();
				objCommBean = new CommonDataBean();
				objCommBean.setColumn1((String) obj[0]);
				objCommBean.setColumn2((String) obj[1]);
				objCommBean.setColumn3((String) obj[2]);
				objCommBean.setColumn4((String) obj[3]);
				objCommBean.setColumn5((String) obj[4]);
				objCommBean.setColumn6((String) obj[5]);
				objCommBean.setColumn7((String) obj[6]);
				objCommBean.setColumn8((String) obj[7]);
				objCommBean.setColumn9((String) obj[8]);
				objCommBean.setColumn10(DateUtil
						.convertDateToDateString((Date) obj[9]));
				historyCollection.add(objCommBean);

			}

			// CustomerHistoryDto objCust =(CustomerHistoryDto)arl.get(0);
			// historyCollection = (ArrayList)qry.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while geting cusomer history  list in ApplicaionProcessDAOImpl:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving CustomerHistory in ApplicaionProcessDAOImpl:"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return historyCollection;
	}

	/*
	 * public boolean save(ApplicationFormDto objDto) throws TPlusException{
	 * 
	 * boolean bolExecute=false; Transaction tx=null; try { Session session
	 * =HibernetFactory.currentSession(); tx =session.beginTransaction();
	 * session.save(objDto); tx.commit(); bolExecute=true;
	 * 
	 * } catch (Exception e) { if(tx!=null) { tx.rollback(); }
	 * System.out.println("Exception in ApplicationProcessDAOImpl saveMethod"
	 * +e.getMessage()); throw newTPlusException(TPlusCodes.APPLICATION_ERROR,
	 * "Error: Exception in ApplicationProcessDAOImpl save Method"+e); } finally
	 * { HibernetFactory.closeSession(); }
	 * 
	 * return bolExecute;
	 * 
	 * 
	 * }
	 */

	public static void main(String s[]) throws Exception {/*
														 * ApplicationProcessDAOImpl
														 * appImpl = new
														 * ApplicationProcessDAOImpl
														 * ();
														 * ApplicationProcessSearchDto
														 * objAppSearchDto = new
														 * ApplicationProcessSearchDto
														 * (); //
														 * objAppSearchDto
														 * .setCustomerName
														 * ("styam"); //
														 * objAppSearchDto
														 * .setIdNumber("111");
														 * //appImpl.search(
														 * objAppSearchDto);
														 * appImpl
														 * .customerHistory
														 * ("111");
														 */
	}
	


	public int isDuplicateNRC(String nrc, String suppNrc, String appId) throws TPlusException {

		int res = 0;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();

			sbf.append("select count(*) from ApplicationFormDto apd where ( apd.idNumber= '" + nrc + "' ");
			
			String suppId = suppNrc;
			if(suppId != null && !"".equals(suppId)){
				sbf.append("or apd.supplIdNumber == '" + suppId + "' ) ");
			}else{
				sbf.append(" ) ");
			}
			
			if(appId != null && !"".equals(appId)){
				sbf.append("and apd.applicationId <> '" + appId + "' ");
			}

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out
					.println("After ApplicationFormDAOIMPL checkExistrecord() count:"
							+ res);
		}

		catch (Exception e) {
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
	
	public boolean isDuplicateNRCNewMethod(String nrc, String suppNrc, String appId) throws TPlusException {

		boolean res = false;
		ApplicationFormDto objApplicationFormDto = null;
		
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();

			sbf.append("from ApplicationFormDto apd where ( apd.idNumber= '" + nrc + "' ");
			
			String suppId = suppNrc;
			if(suppId != null && !"".equals(suppId)){
				sbf.append("or apd.supplIdNumber == '" + suppId + "' ) ");
			}else{
				sbf.append(" ) ");
			}
			
			if(appId != null && !"".equals(appId)){
				sbf.append("and apd.applicationId <> '" + appId + "' ");
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
					sbf.append("where cdto.customerId = appdto.customerId and appdto.idNumber='"+nrc+"'");
					
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

}
