package org.transinfo.cacis.dataacess.daoimpl.oracle.batchprocess;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.batchprocess.CardBatchProcessADO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.dto.applicationforms.SupplementaryFormDto;
import org.transinfo.cacis.dto.authorization.SystemParamDto;
import org.transinfo.cacis.dto.batchprocess.CardATMLinkDto;
import org.transinfo.cacis.dto.batchprocess.CardApplLinkDto;
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationTypeDto;
import org.transinfo.cacis.dto.cardproduction.CardDataDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.cardproduction.CustomerLimitsDto;
import org.transinfo.cacis.dto.cardproduction.SupplementaryCardHolderDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.dto.customerservice.CardChangeCloseDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CustomerGroupFeeDto;
import org.transinfo.cacis.dto.transaction.CustomerFeeDto;
import org.transinfo.cacis.dto.transaction.DebitCardFeeDto;
import org.transinfo.cacis.dto.transaction.FeeCreditGLDto;
import org.transinfo.cacis.dto.transaction.FeeDebitGLDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;

@SuppressWarnings({"unchecked","unused"})
public class CardbatchProcessDAOImpl extends BaseDAOImpl implements
CardBatchProcessADO {

	private Logger logger = Logger.getLogger(CardbatchProcessDAOImpl.class);

	public Collection list(String issuerID, int pageNo, BranchDto objBranchDto, String getAll) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
			.append(" select afd.applicationId, afd.customerName, afd.idNumber, to_char(afd.updatedDate,'dd-MM-yyyy') ");
			sbf
			.append("from ApplicationFormDto afd where afd.applicationStatus = 1 and afd.issuerId = '"
					+ issuerID + "'");
				if(!getAll.equals("ALL")) {
					sbf.append("and afd.branchId = '" + objBranchDto.getBranchId() + "' ");
				}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}
	
	public Collection list(String issuerID, int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
			.append(" select afd.applicationId, afd.customerName, afd.idNumber, to_char(afd.updatedDate,'dd-MM-yyyy') ");
			sbf
			.append("from ApplicationFormDto afd where afd.applicationStatus = 1 and afd.issuerId = '"
					+ issuerID + "'");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public Collection listNewProduct(String issuerID, int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append(" select afd.sno, afd.customerName, afd.nric, to_char(afd.lastUpdatedDate,'dd-MM-yyyy') ");
			sbf.append("from AddProductDto afd where afd.status = 1 ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out.println("Error while retrieving the CardbatchProcessDAOImpl list Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving the CardbatchProcessDAOImpl list Info " + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public int getTotalCardsApps(String issuerID) throws TPlusException {

		int noOfApps = 0;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from ApplicationFormDto afdo ");
			sbf.append("where afdo.applicationStatus = 1 ");
			sbf.append("and afdo.issuerId = '" + issuerID + "' ");

			Query qry = session.createQuery(sbf.toString());
			List recList = qry.list();

			if (recList.size() > 0) {
				noOfApps = (Integer) recList.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
					+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return noOfApps;
	}

	public int getTotalCardsAppsNewProduct(String issuerID) throws TPlusException {

		int noOfApps = 0;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from AddProductDto afdo ");
			sbf.append("where afdo.status = 1 ");

			Query qry = session.createQuery(sbf.toString());
			List recList = qry.list();

			if (recList.size() > 0) {
				noOfApps = (Integer) recList.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
					+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return noOfApps;
	}

	public ArrayList getAllApplication(String issuerID) throws TPlusException {

		ArrayList objSearchCollection = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from ApplicationFormDto afd ");
			sbf.append("where afd.applicationStatus = 1 and afd.issuerId = '"
					+ issuerID + "'");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public ArrayList getAllApplicationNewProduct(String issuerID) throws TPlusException {

		ArrayList objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();

		try {

			Session session = HibernetFactory.currentSession();

			sbf.append("from AddProductDto afd ");
			sbf.append("where afd.status = 1 ");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();


		} catch (Exception e) {
			System.out.println("Error while retrieving the CardbatchProcessDAOImpl list Info " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving the CardbatchProcessDAOImpl list Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean embooActivityLimitSet(
			ApplicationProcessDto objApplicationProcessDto)
					throws TPlusException {

		boolean res = false;
		Transaction tx = null;
		CardEmbossingDto objCardEb = null;
		CardActivityDto objCardActivity = null;
		CustomerLimitsDto objCustomerLimits = null;
		Set listCards = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			Set set = objApplicationProcessDto.getCustomerAccount();
			for (Iterator it = set.iterator(); it.hasNext();) {
				CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
				listCards = custAcctDto.getCustomerCards();
				System.out.println("Card Size=" + listCards.size());
			}

			// update customer
			session.update(objApplicationProcessDto);

			for (Iterator it = listCards.iterator(); it.hasNext();) {
				CardsDto objCards = (CardsDto) it.next();

				// iserting into customer_limits table
				/*objCustomerLimits = new CustomerLimitsDto();
				objCustomerLimits.setCardNumber(objCards.getCardNumber());
				objCustomerLimits.setAmtPerTranx(objApplicationProcessDto
						.getAmtPerTranx());
				objCustomerLimits.setDailyLimitCount(objApplicationProcessDto
						.getDailyLimitCount());
				objCustomerLimits.setDailyLimitAmt(objApplicationProcessDto
						.getDailyLimitAmt());
				objCustomerLimits.setMonthlyLimitCount(objApplicationProcessDto
						.getMonthlyLimitCount());
				objCustomerLimits.setMonthlyLimitAmt(objApplicationProcessDto
						.getMonthlyLimitAmt());
				objCustomerLimits.setCurrencyCode(String
						.valueOf(objApplicationProcessDto.getCurrencyCode()));
				objCustomerLimits.setIssuerId(objApplicationProcessDto
						.getIssuerId());

				session.save(objCustomerLimits);*/

				// iserting into cardsEmbossing table
				objCardEb = new CardEmbossingDto();
				// objCardEb.setCardNumber(objCards.getCardNumber());
				objCardEb.setCard(objCards);
				objCardEb.setCustomer(objApplicationProcessDto);
				objCardEb.setIssuerId(objCards.getIssuerId());
				objCardEb.setTrack1("testTrack1");
				objCardEb.setTrack2("testTrack2");
				objCardEb.setStatus(CommonCodes.CARD_PROCESS_NEW);
				objCardEb
				.setLastUpdatedBy(objApplicationProcessDto.getUserId());
				objCardEb.setUpdatedDate(new Date());

				session.save(objCardEb);

				// iserting into cardsEmbossing table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCards.getCardNumber());
				objCardActivity.setActivity("New Card Creation");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objApplicationProcessDto.getUserId());
				objCardActivity
				.setReason(objApplicationProcessDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objApplicationProcessDto
						.getUserId());
				objCardActivity.setUpdatedDate(objApplicationProcessDto
						.getUpdatedDate());

				session.save(objCardActivity);

			}

			tx.commit();
			res = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public boolean addBatch(CardBatchDto objBatchDto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objBatchDto);

			tx.commit();
			boolAdd = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl addBatch method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl addBatch  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	public ApplicationProcessDto getCustomer(String appId)
			throws TPlusException {

		ApplicationProcessDto objDto = null;
		List objSearchCollection = new ArrayList();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			System.out.println("+++++++++++++++++++++++"+appId);
			sbf.append("from ApplicationProcessDto apd ");
			sbf.append("where apd.applicationId = '" + appId + "'");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			if (objSearchCollection.size() > 0) {
				objDto = (ApplicationProcessDto) objSearchCollection.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getCustomer method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getCustomer  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	public CustomerGroupFeeDto getCustomerGF(String cardProductId, String custType) throws TPlusException {

		CustomerGroupFeeDto objDto = null;
		List objSearchCollection = new ArrayList();
		//Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();

			sbf.append("from CustomerGroupFeeDto cgf ");
			sbf.append("where cgf.cardProduct.cardProductId = '" + cardProductId + "' and cgf.customerType.customerTypeId = '" + custType + "' ");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			if (objSearchCollection.size() > 0) {
				objDto = (CustomerGroupFeeDto) objSearchCollection.get(0);
			}

			//tx.commit();

		} catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
			System.out
			.println("Error in CardbatchProcessDAOImpl getCustomerGF method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getCustomerGF  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	public ApplicationProcessDto getPrimaryCustomer(String appId)
			throws TPlusException {

		ApplicationProcessDto objDto = null;
		ArrayList objSearchCollection = new ArrayList();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from ApplicationProcessDto apd ");
			sbf.append("where apd.applicationId = '" + appId + "'");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			if (objSearchCollection.size() > 0) {
				//System.out.println("********getPrimaryCustomer************objSearchCollection not zero");
				Iterator itr = objSearchCollection.iterator();
				while (itr.hasNext()) {
					ApplicationProcessDto element = (ApplicationProcessDto) itr
							.next();
					if (element.getParenetCustomerId() == null) {
						System.out.println("********getPrimaryCustomer************Break- so Not NULL");

						objDto = element;
						break;
					}
					System.out.println("********getPrimaryCustomer************ NOT Break so NUll");
				}
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getPrimaryCustomer method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getPrimaryCustomer  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	public ApplicationProcessDto getSupplCustomer(String appId)
			throws TPlusException {

		ApplicationProcessDto objDto = null;
		ArrayList objSearchCollection = new ArrayList();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from ApplicationProcessDto apd ");
			sbf.append("where apd.applicationId = '" + appId + "'");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			if (objSearchCollection.size() > 0) {
				Iterator itr = objSearchCollection.iterator();
				while (itr.hasNext()) {
					ApplicationProcessDto element = (ApplicationProcessDto) itr
							.next();
					if (element.getParenetCustomerId() != null) {
						objDto = element;
						break;
					}
				}
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getSupplCustomer method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getSupplCustomer  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	public ArrayList getCustomerList(String appId) throws TPlusException {

		ArrayList objSearchCollection = new ArrayList();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from ApplicationProcessDto apd ");
			sbf.append("where apd.applicationId = '" + appId + "'");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getCustomerList method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getCustomerList  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;

	}

	public boolean updateApplication(ApplicationFormDto objApplicationFormDto)
			throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objApplicationFormDto);
			session.flush();
			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl updateApplication method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl updateApplication  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	public boolean batchProcess(ApplicationProcessDto objApplicationProcessDto,
			ApplicationProcessDto objApplicationProcessDtoSuppl,
			String batchId, String userId, String isUrgent, CustomerGroupFeeDto objCustomerGroupFeeDto) throws TPlusException {

		logger.error("Inside the 'batchProcess' method");

		int count = 0;
		boolean res = false;
		Transaction tx = null;

		CardEmbossingDto objCardEb = null;
		CardActivityDto objCardActivity = null;
		CustomerLimitsDto objCustomerLimits = null;
		CardATMLinkDto objCardATMLinkDto = null;
		CardDataDto objCardDataDto = null;
		CardProductDto objCardProductDto = null;

		CardProductFeeDto objCardProductFeeDto = null;

		// Joining Fee
		CustomerFeeDto objCustomerFeeDtoJoin = null;
		FeeDebitGLDto objDebitGLDtoJoin = null;

		// Annual Fee
		CustomerFeeDto objCustomerFeeDtoAnnual = null;
		FeeDebitGLDto objDebitGLDtoAnnual = null;

		// Urgent Fee
		CustomerFeeDto objCustomerFeeDtoUrgent = null;
		FeeDebitGLDto objDebitGLDtoUrgent = null;

		Set listCardsPri = null;
		Set listCardsSupp = null;
		List listCards = new ArrayList();
		String serviceCode = "";
		//int cardClose = 0;

		int cardHolderType = 0;
		long cardNo;
		String accountId = "";
		float joinFeeAmt = 0;
		float annualFeeAmt = 0;
		float urgentFeeAmt = 0;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			if(objCustomerGroupFeeDto == null){
				Query qry = session.createQuery("from CardProductFeeDto cpf where cpf.displayStatus='Active' and cpf.cardProduct.cardProductId= '" + objApplicationProcessDto.getSelectedAppCardProducts() + "'");
				List resPrdFee = qry.list();
				objCardProductFeeDto = (CardProductFeeDto) resPrdFee.get(0);
			}



			objCardProductDto = (CardProductDto) session.get(CardProductDto.class, objApplicationProcessDto.getSelectedAppCardProducts());
			// get product service code
			serviceCode = objCardProductDto.getServiceCode();
			logger.error("Service Code :: "+serviceCode);

			String cardType = objCardProductDto.getCardProductType().getCardProductType();

			Set set = objApplicationProcessDto.getCustomerAccount();
			logger.error("Customer Account Size :: "+set.size());
			for (Iterator it = set.iterator(); it.hasNext();) {
				CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
				listCardsPri = custAcctDto.getCustomerCards();

				accountId = custAcctDto.getAccountId();
			}

			logger.error("listCardsPri size :: "+listCardsPri.size());
			listCards.addAll(listCardsPri);

			logger.error("CheckedSupplCardRequired :: "+objApplicationProcessDto.getCheckedSupplCardRequired());
			if (objApplicationProcessDto.getCheckedSupplCardRequired() == 'Y') {
				Set set2 = objApplicationProcessDtoSuppl.getCustomerAccount();
				for (Iterator it = set2.iterator(); it.hasNext();) {
					CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
					listCardsSupp = custAcctDto.getCustomerCards();
				}
				if (listCardsSupp != null) {
					listCards.addAll(listCardsSupp);
				}
			}

			if(listCardsSupp != null){
				logger.error("listCardsSupp :: "+listCardsSupp.size());
			}
			logger.error("listCards :: "+listCards.size());

			if("Y".equals(objCardProductDto.geteComEnable())){

			}

			// update customer
			session.update(objApplicationProcessDto);

			for (Iterator it = listCards.iterator(); it.hasNext();) {
				CardsDto objCards = (CardsDto) it.next();
				cardHolderType = objCards.getCardHolderType();
				logger.error("cardHolderType :: "+cardHolderType);

				cardNo = objCards.getCardNumber();
				//accountId = objCards.getAccountId();

				if (cardHolderType == 1) {

					if(objCustomerGroupFeeDto != null){

						joinFeeAmt = objCustomerGroupFeeDto.getJoinFee().floatValue();
						annualFeeAmt = objCustomerGroupFeeDto.getAnnualFee().floatValue();

						if(objCardProductFeeDto != null){
							urgentFeeAmt = objCardProductFeeDto.getUrgentFeePrimary();
						}

					}else if(objCardProductFeeDto != null){

						joinFeeAmt = objCardProductFeeDto.getJoinFeePrimary();
						annualFeeAmt = objCardProductFeeDto.getAnnualFeePrimary();
						urgentFeeAmt = objCardProductFeeDto.getUrgentFeePrimary();

					}

				} else {

					if(objCustomerGroupFeeDto != null){

						joinFeeAmt = objCustomerGroupFeeDto.getJoinFeeSup().floatValue();
						annualFeeAmt = objCustomerGroupFeeDto.getAnnualFeeSup().floatValue();

						if(objCardProductFeeDto != null){
							urgentFeeAmt = objCardProductFeeDto.getUrgentFeeSecondary();
						}

					}else if(objCardProductFeeDto != null){

						joinFeeAmt = objCardProductFeeDto.getJoinFeeSecondary();
						annualFeeAmt = objCardProductFeeDto.getAnnualFeeSecondary();
						urgentFeeAmt = objCardProductFeeDto.getUrgentFeeSecondary();

					}

				}

				// get System parameter
				/*SystemParamDto objSystemParamDto = (SystemParamDto) session
						.get(SystemParamDto.class, "ASP");
				cardClose = objSystemParamDto.getCardClose();

				Date now = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(now);
				calendar.add(Calendar.DATE, cardClose);
				Date closeDate = calendar.getTime();*/

				logger.error("Track1 :: "+objCards.getTrack1());
				logger.error("Track1 :: "+objCards.getTrack2());

				// inserting into CARD_DATA table
				objCardDataDto = new CardDataDto();
				objCardDataDto.setCardNumber(objCards.getCardNumber());
				objCardDataDto.setExpDate(objCards.getCardExpDate());
				objCardDataDto.setServiceCode(serviceCode);
				objCardDataDto.setPvvOffSet(objCards.getPvvOffSet());
				objCardDataDto.setCvv(objCards.getCvv());
				objCardDataDto.setCvv2(objCards.getCvv2());
				objCardDataDto.setStatus(CommonCodes.CARD_STATUS_NEW);
				objCardDataDto.setTrack1(objCards.getTrack1());
				objCardDataDto.setTrack2(objCards.getTrack2());
				objCardDataDto.setIssueDate(new Date());
				objCardDataDto.setUpdatedDate(new Date());
				objCardDataDto.setUpdatedBy(userId);
				objCardDataDto.setIcvv(objCards.getIcvv());
				//objCardDataDto.setClosingDate(closeDate);
				session.save(objCardDataDto);

				// inserting into customer_limits table
				/*objCustomerLimits = new CustomerLimitsDto();
				objCustomerLimits.setCardNumber(objCards.getCardNumber());
				objCustomerLimits.setAmtPerTranx(objApplicationProcessDto
						.getAmtPerTranx());
				objCustomerLimits.setDailyLimitCount(objApplicationProcessDto
						.getDailyLimitCount());
				objCustomerLimits.setDailyLimitAmt(objApplicationProcessDto
						.getDailyLimitAmt());
				objCustomerLimits.setMonthlyLimitCount(objApplicationProcessDto
						.getMonthlyLimitCount());
				objCustomerLimits.setMonthlyLimitAmt(objApplicationProcessDto
						.getMonthlyLimitAmt());
				objCustomerLimits.setCurrencyCode(String
						.valueOf(objApplicationProcessDto.getCurrencyCode()));
				objCustomerLimits.setIssuerId(objApplicationProcessDto
						.getIssuerId());

				session.save(objCustomerLimits);*/

				// inserting into cardsEmbossing table
				objCardEb = new CardEmbossingDto();
				objCardEb.setCard(objCards);

				if (cardHolderType == 1) {
					objCardEb.setCustomer(objApplicationProcessDto);
				} else {
					objCardEb.setCustomer(objApplicationProcessDtoSuppl);
				}

				objCardEb.setIssuerId(objCards.getIssuerId());
				objCardEb.setTrack1(objCards.getTrack1());
				objCardEb.setTrack2(objCards.getTrack2());
				objCardEb.setStatus(CommonCodes.CARD_PROCESS_NOT_EMBOSSED);
				objCardEb
				.setLastUpdatedBy(objApplicationProcessDto.getUserId());
				objCardEb.setUpdatedDate(new Date());

				session.save(objCardEb);

				// inserting into cardsEmbossing table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCards.getCardNumber());
				objCardActivity.setActivity("New Card Creation");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objApplicationProcessDto.getUserId());
				objCardActivity
				.setReason(objApplicationProcessDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objApplicationProcessDto
						.getUserId());
				objCardActivity.setUpdatedDate(new Date());

				session.save(objCardActivity);

				// inserting into Card_ATM_Link table
				//if (objApplicationProcessDto.getCheckedAtmLink() == 'Y') {
				objCardATMLinkDto = new CardATMLinkDto();
				objCardATMLinkDto.setCardNumber(objCards.getCardNumber());
				objCardATMLinkDto.setCard(objCards);
				objCardATMLinkDto.setSavingAccount(objApplicationProcessDto.getSavingAccount());
				objCardATMLinkDto.setCurrentAccount(objApplicationProcessDto.getCheckingAccount());
				objCardATMLinkDto.setDefaultAccount(objApplicationProcessDto.getDefaultAccount());
				objCardATMLinkDto.setCollectoralAccount(objApplicationProcessDto.getCollectoralAccount());
				objCardATMLinkDto.setCollectoralAmt(objApplicationProcessDto.getCollectoralAmt());
				objCardATMLinkDto.setAutoPayAccount(objApplicationProcessDto.getAutoPayAccount());
				objCardATMLinkDto.setAutoPayAccounton(objApplicationProcessDto.getCheckedautoPayAccountOn());

				session.save(objCardATMLinkDto);
				//}

				if(ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){

					// joining Fee
					objCustomerFeeDtoJoin = new CustomerFeeDto();
					objCustomerFeeDtoJoin.setCardNo(cardNo);
					objCustomerFeeDtoJoin.setAccountId(accountId);
					objCustomerFeeDtoJoin.setRefId("JF");
					objCustomerFeeDtoJoin.setFeeAmt(joinFeeAmt);
					objCustomerFeeDtoJoin.setAmountSign("D");
					objCustomerFeeDtoJoin.setFeeDate(new Date());
					objCustomerFeeDtoJoin.setFeeSrc(ICacisiss.IFee.FEE_SRC);
					objCustomerFeeDtoJoin.setBilled("N");
					objCustomerFeeDtoJoin.setCreatedDate(new Date());
					objCustomerFeeDtoJoin.setCreatedBy(userId);
					objCustomerFeeDtoJoin.setFeeWaived("N");

					session.save(objCustomerFeeDtoJoin);

					// Annual Fee
					objCustomerFeeDtoAnnual = new CustomerFeeDto();
					objCustomerFeeDtoAnnual.setCardNo(cardNo);
					objCustomerFeeDtoAnnual.setAccountId(accountId);
					objCustomerFeeDtoAnnual.setRefId("AF");
					objCustomerFeeDtoAnnual.setFeeAmt(annualFeeAmt);
					objCustomerFeeDtoAnnual.setAmountSign("D");
					objCustomerFeeDtoAnnual.setFeeDate(new Date());
					objCustomerFeeDtoAnnual.setFeeSrc(ICacisiss.IFee.FEE_SRC);
					objCustomerFeeDtoAnnual.setBilled("N");
					objCustomerFeeDtoAnnual.setCreatedDate(new Date());
					objCustomerFeeDtoAnnual.setCreatedBy(userId);
					objCustomerFeeDtoAnnual.setFeeWaived("N");
					objCustomerFeeDtoAnnual.setAnnualFeeDate(new Date());

					session.save(objCustomerFeeDtoAnnual);

					// Urgent Processing Fee
					if("Y".equals(isUrgent)){

						objCustomerFeeDtoUrgent = new CustomerFeeDto();
						objCustomerFeeDtoUrgent.setCardNo(cardNo);
						objCustomerFeeDtoUrgent.setAccountId(accountId);
						objCustomerFeeDtoUrgent.setRefId("UF");
						objCustomerFeeDtoUrgent.setFeeAmt(urgentFeeAmt);
						objCustomerFeeDtoUrgent.setAmountSign("D");
						objCustomerFeeDtoUrgent.setFeeDate(new Date());
						objCustomerFeeDtoUrgent.setFeeSrc(ICacisiss.IFee.FEE_SRC);
						objCustomerFeeDtoUrgent.setBilled("N");
						objCustomerFeeDtoUrgent.setCreatedDate(new Date());
						objCustomerFeeDtoUrgent.setCreatedBy(userId);
						objCustomerFeeDtoUrgent.setFeeWaived("N");

						session.save(objCustomerFeeDtoUrgent);
					}

				}else{

					// Joining Fee
					objDebitGLDtoJoin = new FeeDebitGLDto();
					objDebitGLDtoJoin.setDateTime(new Date());
					objDebitGLDtoJoin.setCardNo(String.valueOf(cardNo));
					objDebitGLDtoJoin.setTrnxType("JF");
					objDebitGLDtoJoin.setGlType("D");
					objDebitGLDtoJoin.setAmount(String.valueOf(joinFeeAmt));
					objDebitGLDtoJoin.setBilled("N");
					objDebitGLDtoJoin.setFeeWaived("N");
					objDebitGLDtoJoin.setCreatedBy(userId);

					session.save(objDebitGLDtoJoin);

					// Annual Fee
					objDebitGLDtoAnnual = new FeeDebitGLDto();
					objDebitGLDtoAnnual.setDateTime(new Date());
					objDebitGLDtoAnnual.setCardNo(String.valueOf(cardNo));
					objDebitGLDtoAnnual.setTrnxType("AF");
					objDebitGLDtoAnnual.setGlType("D");
					objDebitGLDtoAnnual.setAmount(String.valueOf(annualFeeAmt));
					objDebitGLDtoAnnual.setBilled("N");
					objDebitGLDtoAnnual.setFeeWaived("N");
					objDebitGLDtoAnnual.setAnnualFeeDate(new Date());
					objDebitGLDtoAnnual.setCreatedBy(userId);

					session.save(objDebitGLDtoAnnual);

					// Urgent Processing Fee
					if("Y".equals(isUrgent)){

						objDebitGLDtoUrgent = new FeeDebitGLDto();
						objDebitGLDtoUrgent.setDateTime(new Date());
						objDebitGLDtoUrgent.setCardNo(String.valueOf(cardNo));
						objDebitGLDtoUrgent.setTrnxType("UF");
						objDebitGLDtoUrgent.setGlType("D");
						objDebitGLDtoUrgent.setAmount(String.valueOf(urgentFeeAmt));
						objDebitGLDtoUrgent.setBilled("N");
						objDebitGLDtoUrgent.setFeeWaived("N");
						objDebitGLDtoUrgent.setCreatedBy(userId);

						session.save(objDebitGLDtoUrgent);

					}

				}

			}

			// updating in ApplicationForms table
			String appSql = "UPDATE ApplicationFormDto SET applicationStatus =:appacceptstatus, batchId=:processBatchId WHERE applicationId=:applicationid";
			count = session.createQuery(appSql).setString("applicationid",
					objApplicationProcessDto.getApplicationId()).setString(
							"processBatchId", batchId).setInteger("appacceptstatus",
									CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
									.executeUpdate();

			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objApplicationProcessDto.getApplicationId()).setInteger(
							"appacceptstatus",
							CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED).setDate(
									"closedate", new Date()).executeUpdate();

			tx.commit();
			if (count > 0) {
				res = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public boolean batchProcessNewProduct(ApplicationProcessDto objApplicationProcessDto, AddProductDto objAddProductDto,  String batchId, 
			String userId, CustomerGroupFeeDto objCustomerGroupFeeDto) throws TPlusException {

		logger.error("Inside the 'batchProcess' method");

		int count = 0;
		boolean res = false;
		Transaction tx = null;

		Set listCardsPri = null;
		List listCards = new ArrayList();

		CardEmbossingDto objCardEb = null;
		CardActivityDto objCardActivity = null;
		CustomerLimitsDto objCustomerLimits = null;
		CardATMLinkDto objCardATMLinkDto = null;
		CardDataDto objCardDataDto = null;
		CardProductDto objCardProductDto = null;
		CustomerAccountDto objCustomerAccountDto = null;

		CardProductFeeDto objCardProductFeeDto = null;

		// Joining Fee
		CustomerFeeDto objCustomerFeeDtoJoin = null;
		FeeDebitGLDto objDebitGLDtoJoin = null;

		// Annual Fee
		CustomerFeeDto objCustomerFeeDtoAnnual = null;
		FeeDebitGLDto objDebitGLDtoAnnual = null;

		// Urgent Fee
		CustomerFeeDto objCustomerFeeDtoUrgent = null;
		FeeDebitGLDto objDebitGLDtoUrgent = null;

		String serviceCode = "";

		int cardHolderType = 0;
		long cardNo;
		float joinFeeAmt = 0;
		float annualFeeAmt = 0;
		float urgentFeeAmt = 0;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			if(objCustomerGroupFeeDto == null){
				Query qry = session.createQuery("from CardProductFeeDto cpf where cpf.displayStatus='Active' and cpf.cardProduct.cardProductId= '" + objAddProductDto.getNewCardProduct() + "'");
				List resPrdFee = qry.list();
				objCardProductFeeDto = (CardProductFeeDto) resPrdFee.get(0);
			}

			// get the product object
			objCardProductDto = (CardProductDto) session.get(CardProductDto.class, objAddProductDto.getNewCardProduct());

			// get customer account
			String accountId = objAddProductDto.getAccountId();
			objCustomerAccountDto = (CustomerAccountDto) session.get(CustomerAccountDto.class, accountId);

			// get product service code
			serviceCode = objCardProductDto.getServiceCode();
			logger.error("Service Code :: "+serviceCode);

			String cardType = objCardProductDto.getCardProductType().getCardProductType();

			Set set = objApplicationProcessDto.getCustomerAccount();
			logger.error("Customer Account Size :: "+set.size());
			for (Iterator it = set.iterator(); it.hasNext();) {
				CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
				listCardsPri = custAcctDto.getCustomerCards();
			}

			logger.error("listCardsPri size :: "+listCardsPri.size());
			listCards.addAll(listCardsPri);

			for (Iterator it = listCards.iterator(); it.hasNext();) {
				CardsDto objCardsDto = (CardsDto) it.next();

				if (objCardsDto.getTrack1() != null) {

					cardHolderType = objCardsDto.getCardHolderType();
					logger.error("cardHolderType :: "+cardHolderType);

					cardNo = objCardsDto.getCardNumber();

					objCardsDto.setAccountId(accountId);

					objCardsDto.setCustAccountDto(objCustomerAccountDto);
					objCardsDto.setCardProductsDto(objCardProductDto);

					objCardsDto.setCashUsed(0.0);
					objCardsDto.setPurchaseUsed(0.0);
					objCardsDto.setCreditLimitPercent(0);
					objCardsDto.setCashLimitPercent(0);

					// save card object
					session.save(objCardsDto);

					if (cardHolderType == 1) {

						if(objCustomerGroupFeeDto != null){

							joinFeeAmt = objCustomerGroupFeeDto.getJoinFee().floatValue();
							annualFeeAmt = objCustomerGroupFeeDto.getAnnualFee().floatValue();

							if(objCardProductFeeDto != null){
								urgentFeeAmt = objCardProductFeeDto.getUrgentFeePrimary();
							}

						}else if(objCardProductFeeDto != null){

							joinFeeAmt = objCardProductFeeDto.getJoinFeePrimary();
							annualFeeAmt = objCardProductFeeDto.getAnnualFeePrimary();
							urgentFeeAmt = objCardProductFeeDto.getUrgentFeePrimary();

						}

					} else {

						if(objCustomerGroupFeeDto != null){

							joinFeeAmt = objCustomerGroupFeeDto.getJoinFeeSup().floatValue();
							annualFeeAmt = objCustomerGroupFeeDto.getAnnualFeeSup().floatValue();

							if(objCardProductFeeDto != null){
								urgentFeeAmt = objCardProductFeeDto.getUrgentFeeSecondary();
							}

						}else if(objCardProductFeeDto != null){

							joinFeeAmt = objCardProductFeeDto.getJoinFeeSecondary();
							annualFeeAmt = objCardProductFeeDto.getAnnualFeeSecondary();
							urgentFeeAmt = objCardProductFeeDto.getUrgentFeeSecondary();

						}

					}

					logger.error("Track1 :: "+objCardsDto.getTrack1());
					logger.error("Track1 :: "+objCardsDto.getTrack2());

					// inserting into CARD_DATA table
					objCardDataDto = new CardDataDto();
					objCardDataDto.setCardNumber(objCardsDto.getCardNumber());
					objCardDataDto.setExpDate(objCardsDto.getCardExpDate());
					objCardDataDto.setServiceCode(serviceCode);
					objCardDataDto.setPvvOffSet(objCardsDto.getPvvOffSet());
					objCardDataDto.setCvv(objCardsDto.getCvv());
					objCardDataDto.setCvv2(objCardsDto.getCvv2());
					objCardDataDto.setStatus(CommonCodes.CARD_STATUS_NEW);
					objCardDataDto.setTrack1(objCardsDto.getTrack1());
					objCardDataDto.setTrack2(objCardsDto.getTrack2());
					objCardDataDto.setIssueDate(new Date());
					objCardDataDto.setUpdatedDate(new Date());
					objCardDataDto.setUpdatedBy(userId);
					objCardDataDto.setIcvv(objCardsDto.getIcvv());

					session.save(objCardDataDto);

					// inserting into cardsEmbossing table
					objCardEb = new CardEmbossingDto();
					objCardEb.setCard(objCardsDto);

					if (cardHolderType == 1) {
						objCardEb.setCustomer(objApplicationProcessDto);
					}

					objCardEb.setIssuerId(objCardsDto.getIssuerId());
					objCardEb.setTrack1(objCardsDto.getTrack1());
					objCardEb.setTrack2(objCardsDto.getTrack2());
					objCardEb.setStatus(CommonCodes.CARD_PROCESS_NOT_EMBOSSED);
					objCardEb.setLastUpdatedBy(objApplicationProcessDto.getUserId());
					objCardEb.setUpdatedDate(new Date());

					session.save(objCardEb);

					// inserting into cardsEmbossing table
					objCardActivity = new CardActivityDto();
					objCardActivity.setDateTime(new Date());
					objCardActivity.setCardNumber(objCardsDto.getCardNumber());
					objCardActivity.setActivity("New Card Creation");
					objCardActivity.setStationIp(InetAddress.getLocalHost().getHostAddress());
					objCardActivity.setUserId(objApplicationProcessDto.getUserId());
					objCardActivity.setReason(objApplicationProcessDto.getRemarks());
					objCardActivity.setLastUpdatedBy(objApplicationProcessDto.getUserId());
					objCardActivity.setUpdatedDate(new Date());

					session.save(objCardActivity);

					// inserting into Card_ATM_Link table
					objCardATMLinkDto = new CardATMLinkDto();
					objCardATMLinkDto.setCardNumber(objCardsDto.getCardNumber());
					objCardATMLinkDto.setCard(objCardsDto);
					objCardATMLinkDto.setSavingAccount(objApplicationProcessDto.getSavingAccount());
					objCardATMLinkDto.setCurrentAccount(objApplicationProcessDto.getCheckingAccount());
					objCardATMLinkDto.setDefaultAccount(objApplicationProcessDto.getDefaultAccount());
					objCardATMLinkDto.setCollectoralAccount(objApplicationProcessDto.getCollectoralAccount());
					objCardATMLinkDto.setCollectoralAmt(objApplicationProcessDto.getCollectoralAmt());
					objCardATMLinkDto.setAutoPayAccount(objApplicationProcessDto.getAutoPayAccount());
					objCardATMLinkDto.setAutoPayAccounton(objApplicationProcessDto.getCheckedautoPayAccountOn());

					session.save(objCardATMLinkDto);

					if(ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){

						// joining Fee
						objCustomerFeeDtoJoin = new CustomerFeeDto();
						objCustomerFeeDtoJoin.setCardNo(cardNo);
						objCustomerFeeDtoJoin.setAccountId(accountId);
						objCustomerFeeDtoJoin.setRefId("JF");
						objCustomerFeeDtoJoin.setFeeAmt(joinFeeAmt);
						objCustomerFeeDtoJoin.setAmountSign("D");
						objCustomerFeeDtoJoin.setFeeDate(new Date());
						objCustomerFeeDtoJoin.setFeeSrc(ICacisiss.IFee.FEE_SRC);
						objCustomerFeeDtoJoin.setBilled("N");
						objCustomerFeeDtoJoin.setCreatedDate(new Date());
						objCustomerFeeDtoJoin.setCreatedBy(userId);
						objCustomerFeeDtoJoin.setFeeWaived("N");

						session.save(objCustomerFeeDtoJoin);

						// Annual Fee
						objCustomerFeeDtoAnnual = new CustomerFeeDto();
						objCustomerFeeDtoAnnual.setCardNo(cardNo);
						objCustomerFeeDtoAnnual.setAccountId(accountId);
						objCustomerFeeDtoAnnual.setRefId("AF");
						objCustomerFeeDtoAnnual.setFeeAmt(annualFeeAmt);
						objCustomerFeeDtoAnnual.setAmountSign("D");
						objCustomerFeeDtoAnnual.setFeeDate(new Date());
						objCustomerFeeDtoAnnual.setFeeSrc(ICacisiss.IFee.FEE_SRC);
						objCustomerFeeDtoAnnual.setBilled("N");
						objCustomerFeeDtoAnnual.setCreatedDate(new Date());
						objCustomerFeeDtoAnnual.setCreatedBy(userId);
						objCustomerFeeDtoAnnual.setFeeWaived("N");
						objCustomerFeeDtoAnnual.setAnnualFeeDate(new Date());

						session.save(objCustomerFeeDtoAnnual);

					}else{

						// Joining Fee
						objDebitGLDtoJoin = new FeeDebitGLDto();
						objDebitGLDtoJoin.setDateTime(new Date());
						objDebitGLDtoJoin.setCardNo(String.valueOf(cardNo));
						objDebitGLDtoJoin.setTrnxType("JF");
						objDebitGLDtoJoin.setGlType("D");
						objDebitGLDtoJoin.setAmount(String.valueOf(joinFeeAmt));
						objDebitGLDtoJoin.setBilled("N");
						objDebitGLDtoJoin.setFeeWaived("N");
						objDebitGLDtoJoin.setCreatedBy(userId);

						session.save(objDebitGLDtoJoin);

						// Annual Fee
						objDebitGLDtoAnnual = new FeeDebitGLDto();
						objDebitGLDtoAnnual.setDateTime(new Date());
						objDebitGLDtoAnnual.setCardNo(String.valueOf(cardNo));
						objDebitGLDtoAnnual.setTrnxType("AF");
						objDebitGLDtoAnnual.setGlType("D");
						objDebitGLDtoAnnual.setAmount(String.valueOf(annualFeeAmt));
						objDebitGLDtoAnnual.setBilled("N");
						objDebitGLDtoAnnual.setFeeWaived("N");
						objDebitGLDtoAnnual.setAnnualFeeDate(new Date());
						objDebitGLDtoAnnual.setCreatedBy(userId);

						session.save(objDebitGLDtoAnnual);

					}

				}

			}

			// updating in ApplicationForms table
			String appSql = "UPDATE AddProductDto SET status =:stat WHERE sno=:seqno";
			count = session.createQuery(appSql)
					.setInteger("seqno",objAddProductDto.getSno())
					.setLong("stat",CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
					.executeUpdate();

			tx.commit();
			if (count > 0) {
				res = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public SupplementaryCardHolderDto getSuppCardHolder(String appId,
			String suppNric) throws TPlusException {

		SupplementaryCardHolderDto objDto = null;
		List objSearchCollection = new ArrayList();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from SupplementaryCardHolderDto achd ");
			sbf.append("where achd.applicationId = '" + appId + "' ");
			sbf.append("and achd.supplIdNumber = '" + suppNric + "' ");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			if (objSearchCollection.size() > 0) {
				objDto = (SupplementaryCardHolderDto) objSearchCollection
						.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getSuppCardHolder method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getSuppCardHolder  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	public Collection listReplacementCardApps(String issuerID, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			sbf.append("select crdo.applicationId, cido.customerName, ");
			sbf.append("crdo.cardNumber, crdo.remarks, ");
			sbf.append("to_char(crdo.updatedDate,'dd-MM-yyyy'), cdo.maskedCardNo ");
			sbf.append("from CardReplacementDto crdo, CardsDto cdo, CustomerInfoDto cido ");
			sbf.append("where crdo.cardNumber = cdo.cardNumber ");
			sbf.append("and cdo.customerId = cido.customerId ");
			sbf.append("and crdo.applicationStatus = 1 ");
			sbf.append("and crdo.issuerId = '" + issuerID + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out.println("Error while retrieving the CardbatchProcessDAOImpl listReplacementCardApps Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving the CardbatchProcessDAOImpl listReplacementCardApps Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public int getTotalReplacementCardsApps(String issuerID)
			throws TPlusException {

		int noOfApps = 0;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from CardReplacementDto crdo ");
			sbf.append("where crdo.applicationStatus = 1 ");
			sbf.append("and crdo.issuerId = '" + issuerID + "' ");

			Query qry = session.createQuery(sbf.toString());
			List recList = qry.list();

			if (recList.size() > 0) {
				noOfApps = (Integer) recList.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
					+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return noOfApps;
	}

	public ArrayList getAllReplacementApplication(String issuerID)
			throws TPlusException {

		ArrayList objSearchCollection = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardReplacementDto crdo ");
			sbf.append("where crdo.applicationStatus = 1 ");
			sbf.append("and crdo.issuerId = '" + issuerID + "' ");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public ApplicationProcessDto getCustomerById(String customerID)
			throws TPlusException {
		System.out.println("+++++DAOIMPL+++====="+ customerID);

		ApplicationProcessDto objDto = null;
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objDto = (ApplicationProcessDto) session.get(ApplicationProcessDto.class, customerID);

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getCustomerByID method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getCustomerByID  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	public boolean replacementBatchProcessForSameCardNo(
			ApplicationProcessDto objApplicationProcessDto,
			CardReplacementDto objCardReplacementDto, String batchId,
			String userId, CardsDto objCardsDto) throws TPlusException {

		int count = 0;
		boolean res = false;

		Transaction tx = null;

		CardEmbossingDto objCardEmbossingDto = null;
		CardActivityDto objCardActivityDto = null;
		FeeCreditGLDto objFeeCreditGLDto = null;
		DebitCardFeeDto objDebitCardFeeDto = null;
		CardProductFeeDto objCardProductFeeDto = null;
		CardDataDto objCardDataDto = null;

		// Replacement Fee
		CustomerFeeDto objCustomerFeeDtoRep = null;
		FeeDebitGLDto objDebitGLDtoRep = null;

		// Urgent Fee
		CustomerFeeDto objCustomerFeeDtoUrgent = null;
		FeeDebitGLDto objDebitGLDtoUrgent = null;

		int cardClose = 0;
		float repFee = 0;
		float urgentFeeAmt = 0;

		String isUrgent = "";

		try {

			int cardHolderType = objCardsDto.getCardHolderType();
			long cardNo = objCardsDto.getCardNumber();
			String accountId = objCardsDto.getAccountId();

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objCardsDto);

			String issuerID = objCardsDto.getIssuerId();
			String serviceCode = objCardsDto.getCardProductsDto().getServiceCode();
			String cardType = objCardsDto.getCardProductsDto().getCardProductType().getCardProductType();

			isUrgent = objCardReplacementDto.getImmeidateProcess();

			String track1data = objCardsDto.getTrack1();
			String track2data = objCardsDto.getTrack2();

			// get System parameter
			SystemParamDto objSystemParamDto = (SystemParamDto) session.get(SystemParamDto.class, "ASP");
			cardClose = objSystemParamDto.getCardClose();

			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.DATE, cardClose);
			Date closeDate = calendar.getTime();

			// close existing card from CARD_DATA table
			String cardDataSql = "UPDATE CardDataDto SET closingDate =:closeDate, updatedDate=:modifyDate, updatedBy=:modifyBy WHERE cardNumber=:oldCardNo and closingDate IS NULL";
			count = session.createQuery(cardDataSql)
					.setLong("oldCardNo", objCardReplacementDto.getCardNumber())
					.setString("modifyBy", userId)
					.setDate("modifyDate", new Date())
					.setDate("closeDate", closeDate)
					.executeUpdate();

			if (count > 0) {

				// inserting into CARD_DATA table
				objCardDataDto = new CardDataDto();
				objCardDataDto.setCardNumber(objCardsDto.getCardNumber());
				objCardDataDto.setExpDate(objCardsDto.getCardExpDate());
				objCardDataDto.setServiceCode(serviceCode);
				objCardDataDto.setPvvOffSet(objCardsDto.getPvvOffSet());
				objCardDataDto.setCvv(objCardsDto.getCvv());
				objCardDataDto.setCvv2(objCardsDto.getCvv2());
				objCardDataDto.setIcvv(objCardsDto.getIcvv());
				objCardDataDto.setStatus(CommonCodes.CARD_STATUS_NEW);
				objCardDataDto.setTrack1(track1data);
				objCardDataDto.setTrack2(track2data);
				objCardDataDto.setIssueDate(new Date());
				objCardDataDto.setUpdatedDate(new Date());
				objCardDataDto.setUpdatedBy(userId);
				//objCardDataDto.setClosingDate(closeDate);
				session.save(objCardDataDto);

				// inserting into cardsEmbossing table
				objCardEmbossingDto = new CardEmbossingDto();
				objCardEmbossingDto.setCard(objCardsDto);
				objCardEmbossingDto.setCustomer(objApplicationProcessDto);
				objCardEmbossingDto.setIssuerId(issuerID);
				objCardEmbossingDto.setTrack1(track1data);
				objCardEmbossingDto.setTrack2(track2data);
				objCardEmbossingDto.setStatus(CommonCodes.CARD_PROCESS_NOT_EMBOSSED);
				objCardEmbossingDto.setNewCardFor(CommonCodes.NEW_CARD_FOR_REPLACEMENT);
				objCardEmbossingDto.setLastUpdatedBy(objApplicationProcessDto.getUserId());
				objCardEmbossingDto.setUpdatedDate(new Date());
				session.save(objCardEmbossingDto);

				// inserting into cardsActivity table
				objCardActivityDto = new CardActivityDto();
				objCardActivityDto.setDateTime(new Date());
				objCardActivityDto.setCardNumber(objCardReplacementDto.getCardNumber());
				objCardActivityDto.setActivity("Card Replacement Embossing");
				objCardActivityDto.setStationIp(InetAddress.getLocalHost().getHostAddress());
				objCardActivityDto.setUserId(objApplicationProcessDto.getUserId());
				objCardActivityDto.setReason(objCardReplacementDto.getRemarks());
				objCardActivityDto.setLastUpdatedBy(objApplicationProcessDto.getUserId());
				objCardActivityDto.setUpdatedDate(new Date());
				session.save(objCardActivityDto);

				StringBuffer sbf = new StringBuffer();
				sbf.append("from CardProductFeeDto cpfdo ");
				sbf.append("where cpfdo.cardProduct.cardProductId = '" + objCardsDto.getCardProductId() + "' ");
				sbf.append("and cpfdo.displayStatus = 'Active' ");

				Query qry = session.createQuery(sbf.toString());
				List resList = qry.list();
				if (resList.size() > 0) {
					objCardProductFeeDto = (CardProductFeeDto) resList.get(0);
				}

				if(objCardProductFeeDto != null){
					repFee = objCardProductFeeDto.getReplacementFee();
					if (cardHolderType == 1) {
						urgentFeeAmt = objCardProductFeeDto.getUrgentFeePrimary();
					} else {
						urgentFeeAmt = objCardProductFeeDto.getUrgentFeeSecondary();
					}
				}

				if ("Y".equals(objCardReplacementDto.getFeeApplicable())) {

					if(ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){

						// replacement Fee
						objCustomerFeeDtoRep = new CustomerFeeDto();
						objCustomerFeeDtoRep.setCardNo(cardNo);
						objCustomerFeeDtoRep.setAccountId(accountId);
						objCustomerFeeDtoRep.setRefId("RF");
						objCustomerFeeDtoRep.setFeeAmt(repFee);
						objCustomerFeeDtoRep.setAmountSign("D");
						objCustomerFeeDtoRep.setFeeDate(new Date());
						objCustomerFeeDtoRep.setFeeSrc(ICacisiss.IFee.FEE_SRC);
						objCustomerFeeDtoRep.setBilled("N");
						objCustomerFeeDtoRep.setCreatedDate(new Date());
						objCustomerFeeDtoRep.setCreatedBy(userId);
						objCustomerFeeDtoRep.setFeeWaived("N");

						session.save(objCustomerFeeDtoRep);

					}else{

						// replacement Fee
						objDebitGLDtoRep = new FeeDebitGLDto();
						objDebitGLDtoRep.setDateTime(new Date());
						objDebitGLDtoRep.setCardNo(String.valueOf(cardNo));
						objDebitGLDtoRep.setTrnxType("RF");
						objDebitGLDtoRep.setGlType("D");
						objDebitGLDtoRep.setAmount(String.valueOf(repFee));
						objDebitGLDtoRep.setBilled("N");
						objDebitGLDtoRep.setFeeWaived("N");
						objDebitGLDtoRep.setCreatedBy(userId);

						session.save(objDebitGLDtoRep);

					}

				}

				if(ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){

					// Urgent Processing Fee
					if("Y".equals(isUrgent)){

						objCustomerFeeDtoUrgent = new CustomerFeeDto();
						objCustomerFeeDtoUrgent.setCardNo(cardNo);
						objCustomerFeeDtoUrgent.setAccountId(accountId);
						objCustomerFeeDtoUrgent.setRefId("UF");
						objCustomerFeeDtoUrgent.setFeeAmt(urgentFeeAmt);
						objCustomerFeeDtoUrgent.setAmountSign("D");
						objCustomerFeeDtoUrgent.setFeeDate(new Date());
						objCustomerFeeDtoUrgent.setFeeSrc(ICacisiss.IFee.FEE_SRC);
						objCustomerFeeDtoUrgent.setBilled("N");
						objCustomerFeeDtoUrgent.setCreatedDate(new Date());
						objCustomerFeeDtoUrgent.setCreatedBy(userId);
						objCustomerFeeDtoUrgent.setFeeWaived("N");

						session.save(objCustomerFeeDtoUrgent);
					}

				}else{

					// Urgent Processing Fee
					if("Y".equals(isUrgent)){

						objDebitGLDtoUrgent = new FeeDebitGLDto();
						objDebitGLDtoUrgent.setDateTime(new Date());
						objDebitGLDtoUrgent.setCardNo(String.valueOf(cardNo));
						objDebitGLDtoUrgent.setTrnxType("UF");
						objDebitGLDtoUrgent.setGlType("D");
						objDebitGLDtoUrgent.setAmount(String.valueOf(urgentFeeAmt));
						objDebitGLDtoUrgent.setBilled("N");
						objDebitGLDtoUrgent.setFeeWaived("N");
						objDebitGLDtoUrgent.setCreatedBy(userId);

						session.save(objDebitGLDtoUrgent);
					}

				}

				// updating in Replacement log table
				String repSql = "UPDATE CardReplacementLogDto SET newCardNo =:replacecard, flag=:finishedstatus WHERE oldCardNo=:precard and flag=:status";
				count = session.createQuery(repSql).setCharacter("status", '0')
						.setString(
								"precard",
								String.valueOf(objCardReplacementDto
										.getCardNumber())).setCharacter(
												"finishedstatus", '1').setString(
														"replacecard",
														String.valueOf(objCardReplacementDto
																.getCardNumber())).executeUpdate();

				if (count > 0) {
					// updating in ApplicationForms table
					String appSql = "UPDATE CardReplacementDto SET applicationStatus =:appacceptstatus WHERE applicationId=:applicationid";
					count = session.createQuery(appSql).setString("applicationid",
							objCardReplacementDto.getApplicationId()).setInteger(
									"appacceptstatus",
									CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
									.executeUpdate();

					if (count > 0) {
						// updating in Application_master table
						String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
						count = session
								.createQuery(appMtsql)
								.setString("applicationid",
										objCardReplacementDto.getApplicationId())
										.setInteger(
												"appacceptstatus",
												CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
												.setDate("closedate", new Date()).executeUpdate();
					}
				}


				if (count > 0) {
					tx.commit();
					res = true;
				}

			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public boolean replacementBatchProcessForNewCardNo(
			ApplicationProcessDto objApplicationProcessDto,
			CardReplacementDto objCardReplacementDto, String batchId,
			CardsDto objCardsDto2, String userId,
			ApplicationProcessDto objApplicationProcessDtoOriginal)
					throws TPlusException {

		int count = 0;
		boolean res = false;

		Transaction tx = null;

		CardEmbossingDto objCardEmbossingDto = null;
		CardActivityDto objCardActivityDto = null;
		FeeCreditGLDto objFeeCreditGLDto = null;
		DebitCardFeeDto objDebitCardFeeDto = null;
		CardProductFeeDto objCardProductFeeDto = null;
		CustomerLimitsDto objCustomerLimits = null;
		CardATMLinkDto objOldCardATMLinkDto = null;
		CardATMLinkDto objNewCardATMLinkDto = null;
		CardsDto objCardsDto = null;
		CardDataDto objCardDataDto = null;

		// Replacement Fee
		CustomerFeeDto objCustomerFeeDtoRep = null;
		FeeDebitGLDto objDebitGLDtoRep = null;

		// Urgent Fee
		CustomerFeeDto objCustomerFeeDtoUrgent = null;
		FeeDebitGLDto objDebitGLDtoUrgent = null;

		int cardClose = 0;
		float repFee = 0;
		float urgentFeeAmt = 0;

		String isUrgent = "";

		String cardType = "";
		String issuerID = "";
		Set listCards = null;
		String serviceCode = "";

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			issuerID = objApplicationProcessDto.getIssuerId();

			Set set = objApplicationProcessDto.getCustomerAccount();
			for (Iterator it = set.iterator(); it.hasNext();) {
				CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
				listCards = custAcctDto.getCustomerCards();
			}

			if(listCards == null){
				listCards = objCardsDto2.getCustAccountDto().getCustomerCards();
			}

			// update customer
			// session.update(objApplicationProcessDto);

			objCardsDto = (CardsDto) session.get(CardsDto.class,objCardReplacementDto.getCardNumber());

			String lastStatementId = objCardsDto.getLastStatementId();
			Date lastStatementDate = objCardsDto.getLastStatementDate();

			cardType = objCardsDto.getCardProductsDto().getCardProductType().getCardProductType();

			int cardHolderType = objCardsDto.getCardHolderType();
			long cardNo = objCardsDto.getCardNumber();
			String accountId = objCardsDto.getAccountId();

			for (Iterator it = listCards.iterator(); it.hasNext();) {
				CardsDto objCards = (CardsDto) it.next();

				if (objCards.getTrack1() != null) {

					//objCards.setCustomerId(objCardsDto.getCustomerId());
					objCards.setCustAccountDto(objCardsDto.getCustAccountDto());
					objCards.setCardProductsDto(objCardsDto.getCardProductsDto());

					// set old card no cycle data
					objCards.setCycleNo(objCardsDto2.getCycleNo());

					// assign last statement details
					objCards.setLastStatementId(lastStatementId);
					objCards.setLastStatementDate(lastStatementDate);

					// set cash used & purchase used
					/*objCards.setCashUsed(0.0);
					objCards.setPurchaseUsed(0.0);*/

					objCards.setCashUsed(objCardsDto.getCashUsed());
					objCards.setPurchaseUsed(objCardsDto.getPurchaseUsed());

					// save card object
					session.save(objCards);

					// get product service code
					serviceCode = objCards.getCardProductsDto().getServiceCode();

					// get System parameter
					SystemParamDto objSystemParamDto = (SystemParamDto) session.get(SystemParamDto.class, "ASP");
					cardClose = objSystemParamDto.getCardClose();

					Date now = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(now);
					calendar.add(Calendar.DATE, cardClose);
					Date closeDate = calendar.getTime();


					// close existing card from CARD_DATA table
					String cardDataSql = "UPDATE CardDataDto SET closingDate =:closeDate, updatedDate=:modifyDate, updatedBy=:modifyBy WHERE cardNumber=:oldCardNo and closingDate IS NULL";
					count = session.createQuery(cardDataSql)
							.setLong("oldCardNo", objCardReplacementDto.getCardNumber())
							.setString("modifyBy", userId)
							.setDate("modifyDate", new Date())
							.setDate("closeDate", closeDate)
							.executeUpdate();

					if (count > 0) {

						// inserting into CARD_DATA table
						objCardDataDto = new CardDataDto();
						objCardDataDto.setCardNumber(objCards.getCardNumber());
						objCardDataDto.setExpDate(objCards.getCardExpDate());
						objCardDataDto.setServiceCode(serviceCode);
						objCardDataDto.setPvvOffSet(objCards.getPvvOffSet());
						objCardDataDto.setCvv(objCards.getCvv());
						objCardDataDto.setCvv2(objCards.getCvv2());
						objCardDataDto.setIcvv(objCards.getIcvv());
						objCardDataDto.setStatus(CommonCodes.CARD_STATUS_NEW);
						objCardDataDto.setTrack1(objCards.getTrack1());
						objCardDataDto.setTrack2(objCards.getTrack2());
						objCardDataDto.setIssueDate(new Date());
						objCardDataDto.setUpdatedDate(new Date());
						objCardDataDto.setUpdatedBy(userId);
						//objCardDataDto.setClosingDate(closeDate);
						session.save(objCardDataDto);

						/*// inserting into customer_limits table
							objCustomerLimits = new CustomerLimitsDto();
							objCustomerLimits.setCardNumber(objCards.getCardNumber());
							objCustomerLimits.setAmtPerTranx(objApplicationProcessDto.getAmtPerTranx());
							objCustomerLimits.setDailyLimitCount(objApplicationProcessDto.getDailyLimitCount());
							objCustomerLimits.setDailyLimitAmt(objApplicationProcessDto.getDailyLimitAmt());
							objCustomerLimits.setMonthlyLimitCount(objApplicationProcessDto.getMonthlyLimitCount());
							objCustomerLimits.setMonthlyLimitAmt(objApplicationProcessDto.getMonthlyLimitAmt());
							objCustomerLimits.setCurrencyCode(String.valueOf(objApplicationProcessDto.getCurrencyCode()));
							objCustomerLimits.setIssuerId(objApplicationProcessDto.getIssuerId());
							session.save(objCustomerLimits);*/

						// inserting into cardsEmbossing table
						objCardEmbossingDto = new CardEmbossingDto();
						objCardEmbossingDto.setCard(objCards);
						objCardEmbossingDto.setCustomer(objApplicationProcessDtoOriginal);
						objCardEmbossingDto.setIssuerId(objCards.getIssuerId());
						objCardEmbossingDto.setTrack1(objCards.getTrack1());
						objCardEmbossingDto.setTrack2(objCards.getTrack2());
						objCardEmbossingDto.setStatus(CommonCodes.CARD_PROCESS_NOT_EMBOSSED);
						objCardEmbossingDto.setNewCardFor(CommonCodes.NEW_CARD_FOR_REPLACEMENT);
						objCardEmbossingDto.setLastUpdatedBy(objApplicationProcessDto.getUserId());
						objCardEmbossingDto.setUpdatedDate(new Date());
						session.save(objCardEmbossingDto);

						// inserting into cardsActivity table
						objCardActivityDto = new CardActivityDto();
						objCardActivityDto.setDateTime(new Date());
						objCardActivityDto.setCardNumber(objCards.getCardNumber());
						objCardActivityDto.setActivity("Replacement New Card Creation");
						objCardActivityDto.setStationIp(InetAddress.getLocalHost().getHostAddress());
						objCardActivityDto.setUserId(objApplicationProcessDto.getUserId());
						objCardActivityDto.setReason(objApplicationProcessDto.getRemarks());
						objCardActivityDto.setLastUpdatedBy(objApplicationProcessDto.getUserId());
						objCardActivityDto.setUpdatedDate(new Date());
						session.save(objCardActivityDto);

						objOldCardATMLinkDto = (CardATMLinkDto) session.get(CardATMLinkDto.class, objCardReplacementDto.getCardNumber());
						// inserting into Card_ATM_Link table
						if (objOldCardATMLinkDto != null) {
							objNewCardATMLinkDto = new CardATMLinkDto();
							objNewCardATMLinkDto.setCardNumber(objCards.getCardNumber());
							objNewCardATMLinkDto.setCard(objCards);
							objNewCardATMLinkDto.setSavingAccount(objOldCardATMLinkDto.getSavingAccount());
							objNewCardATMLinkDto.setCurrentAccount(objOldCardATMLinkDto.getCurrentAccount());
							objNewCardATMLinkDto.setDefaultAccount(objOldCardATMLinkDto.getDefaultAccount());
							objNewCardATMLinkDto.setCollectoralAccount(objOldCardATMLinkDto.getCollectoralAccount());
							objNewCardATMLinkDto.setCollectoralAmt(objOldCardATMLinkDto.getCollectoralAmt());
							objNewCardATMLinkDto.setAutoPayAccount(objOldCardATMLinkDto.getAutoPayAccount());
							objNewCardATMLinkDto.setAutoPayAccounton(objOldCardATMLinkDto.getAutoPayAccounton());
							session.save(objNewCardATMLinkDto);
						}

						StringBuffer sbf = new StringBuffer();
						sbf.append("from CardProductFeeDto cpfdo ");
						sbf.append("where cpfdo.cardProduct.cardProductId = '" + objCardsDto.getCardProductId() + "' ");
						sbf.append("and cpfdo.displayStatus = 'Active' ");

						Query qry = session.createQuery(sbf.toString());
						List resList = qry.list();
						if (resList.size() > 0) {
							objCardProductFeeDto = (CardProductFeeDto) resList.get(0);
						}

						if(objCardProductFeeDto != null){
							repFee = objCardProductFeeDto.getReplacementFee();
							if (cardHolderType == 1) {
								urgentFeeAmt = objCardProductFeeDto.getUrgentFeePrimary();
							} else {
								urgentFeeAmt = objCardProductFeeDto.getUrgentFeeSecondary();
							}
						}

						if ("Y".equals(objCardReplacementDto.getFeeApplicable())) {

							if(ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){

								// replacement Fee
								objCustomerFeeDtoRep = new CustomerFeeDto();
								objCustomerFeeDtoRep.setCardNo(cardNo);
								objCustomerFeeDtoRep.setAccountId(accountId);
								objCustomerFeeDtoRep.setRefId("RF");
								objCustomerFeeDtoRep.setFeeAmt(repFee);
								objCustomerFeeDtoRep.setAmountSign("D");
								objCustomerFeeDtoRep.setFeeDate(new Date());
								objCustomerFeeDtoRep.setFeeSrc(ICacisiss.IFee.FEE_SRC);
								objCustomerFeeDtoRep.setBilled("N");
								objCustomerFeeDtoRep.setCreatedDate(new Date());
								objCustomerFeeDtoRep.setCreatedBy(userId);
								objCustomerFeeDtoRep.setFeeWaived("N");

								session.save(objCustomerFeeDtoRep);

							}else{

								// replacement Fee
								objDebitGLDtoRep = new FeeDebitGLDto();
								objDebitGLDtoRep.setDateTime(new Date());
								objDebitGLDtoRep.setCardNo(String.valueOf(cardNo));
								objDebitGLDtoRep.setTrnxType("RF");
								objDebitGLDtoRep.setGlType("D");
								objDebitGLDtoRep.setAmount(String.valueOf(repFee));
								objDebitGLDtoRep.setBilled("N");
								objDebitGLDtoRep.setFeeWaived("N");
								objDebitGLDtoRep.setCreatedBy(userId);

								session.save(objDebitGLDtoRep);

							}

						}

						if(ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){

							// Urgent Processing Fee
							if("Y".equals(isUrgent)){

								objCustomerFeeDtoUrgent = new CustomerFeeDto();
								objCustomerFeeDtoUrgent.setCardNo(cardNo);
								objCustomerFeeDtoUrgent.setAccountId(accountId);
								objCustomerFeeDtoUrgent.setRefId("UF");
								objCustomerFeeDtoUrgent.setFeeAmt(urgentFeeAmt);
								objCustomerFeeDtoUrgent.setAmountSign("D");
								objCustomerFeeDtoUrgent.setFeeDate(new Date());
								objCustomerFeeDtoUrgent.setFeeSrc(ICacisiss.IFee.FEE_SRC);
								objCustomerFeeDtoUrgent.setBilled("N");
								objCustomerFeeDtoUrgent.setCreatedDate(new Date());
								objCustomerFeeDtoUrgent.setCreatedBy(userId);
								objCustomerFeeDtoUrgent.setFeeWaived("N");

								session.save(objCustomerFeeDtoUrgent);
							}

						}else{

							// Urgent Processing Fee
							if("Y".equals(isUrgent)){

								objDebitGLDtoUrgent = new FeeDebitGLDto();
								objDebitGLDtoUrgent.setDateTime(new Date());
								objDebitGLDtoUrgent.setCardNo(String.valueOf(cardNo));
								objDebitGLDtoUrgent.setTrnxType("UF");
								objDebitGLDtoUrgent.setGlType("D");
								objDebitGLDtoUrgent.setAmount(String.valueOf(urgentFeeAmt));
								objDebitGLDtoUrgent.setBilled("N");
								objDebitGLDtoUrgent.setFeeWaived("N");
								objDebitGLDtoUrgent.setCreatedBy(userId);

								session.save(objDebitGLDtoUrgent);
							}

						}

						// updating in Replacement log table
						String repSql = "UPDATE CardReplacementLogDto SET newCardNo =:replacecard, flag=:finishedstatus WHERE oldCardNo=:precard and flag=:status";
						count = session
								.createQuery(repSql)
								.setCharacter("status", '0')
								.setString("precard", String.valueOf(objCardReplacementDto.getCardNumber()))
								.setCharacter("finishedstatus", '1')
								.setString("replacecard", String.valueOf(objCards.getCardNumber()))
								.executeUpdate();

						if (count > 0) {
							// updating in ApplicationForms table
							String appSql = "UPDATE CardReplacementDto SET applicationStatus =:appacceptstatus WHERE applicationId=:applicationid";
							count = session
									.createQuery(appSql)
									.setString("applicationid", objCardReplacementDto.getApplicationId())
									.setInteger( "appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
									.executeUpdate();

							if (count > 0) {
								// updating in Application_master table
								String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
								count = session
										.createQuery(appMtsql)
										.setString("applicationid", objCardReplacementDto.getApplicationId())
										.setInteger("appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
										.setDate("closedate", new Date())
										.executeUpdate();

								// update old cards to close
								appMtsql = "UPDATE CardsDto SET status =:cstatus,lastStatementId =:lastStatId,lastStatementDate =:lastStatDate,cashUsed =:cused,purchaseUsed =:pused WHERE cardNumber=:ocardNumber";
								count = session
										.createQuery(appMtsql)
										.setLong("ocardNumber", objCardReplacementDto.getCardNumber())
										.setString("cstatus", CommonCodes.CARD_SCLOSED)
										.setString("lastStatId", null)
										.setString("lastStatDate", null)
										.setDouble("cused", 0)
										.setDouble("pused", 0)
										.executeUpdate();

								CardActivityDto objCardActivity = new CardActivityDto();
								objCardActivity.setDateTime(new Date());
								objCardActivity.setCardNumber(objCardReplacementDto.getCardNumber());
								objCardActivity.setActivity("Card Close Due to Card Replacement");
								objCardActivity.setStationIp(InetAddress.getLocalHost().getHostAddress());
								objCardActivity.setUserId(userId);
								objCardActivity.setReason("Card Replacement New Card Created");
								objCardActivity.setLastUpdatedBy(userId);
								objCardActivity.setUpdatedDate(new Date());

								session.save(objCardActivity);

							}
						}

						if (count > 0) {
							tx.commit();
							res = true;
						}
					}
				}

			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public static void main(String[] args) throws TPlusException {
		CardbatchProcessDAOImpl obj = new CardbatchProcessDAOImpl();
		obj.getTotalReplacementCardsApps("Issuer1");
	}

	public Collection listProductChange(String issuerID, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select ccdo.cardNo, ccdo.customerName, ccdo.nric, ");
			sbf.append("cpdo1.cardProductName, cpdo2.cardProductName, ");
			sbf.append("to_char(ccdo.updatedDate,'dd-MM-yyyy') ");
			sbf.append("from CardChangeDto ccdo, ");
			sbf.append("CardProductDto cpdo1, CardProductDto cpdo2 ");
			sbf.append("where ccdo.existCardProduct = cpdo1.cardProductId ");
			sbf.append("and ccdo.changeCardProduct = cpdo2.cardProductId ");
			sbf.append("and ccdo.status = 1 ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public int getTotalProductChangeCardsApps(String issuerID)
			throws TPlusException {

		int noOfApps = 0;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from CardChangeDto ccdo ");
			sbf.append("where ccdo.status = 1 ");

			Query qry = session.createQuery(sbf.toString());
			List recList = qry.list();

			if (recList.size() > 0) {
				noOfApps = (Integer) recList.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
					+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return noOfApps;
	}

	public ArrayList getAllProductChangeApplication(String issuerID)
			throws TPlusException {

		ArrayList objSearchCollection = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardChangeDto ccdo ");
			sbf.append("where ccdo.status = 1 ");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public ApplicationProcessDto getCustomerByCard(String cardNo)
			throws TPlusException {

		ApplicationProcessDto objApplicationProcessDto = null;
		CardsDto objCardsDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardsDto = (CardsDto) session.get(CardsDto.class, Long
					.valueOf(cardNo));

			objApplicationProcessDto = (ApplicationProcessDto) session.get(
					ApplicationProcessDto.class, objCardsDto.getCustomerId());

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getCustomerByCard method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getCustomerByCard  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objApplicationProcessDto;

	}

	public boolean productChangeBatchProcess(
			ApplicationProcessDto objApplicationProcessDto,
			CardChangeDto objCardChangeDto, String batchId, String userId,
			ApplicationProcessDto objApplicationProcessDtoOriginal)
					throws TPlusException {

		logger.error("Inside the productChangeBatchProcess method");

		int count = 0;
		boolean res = false;

		Transaction tx = null;

		CardEmbossingDto objCardEmbossingDto = null;
		CardActivityDto objCardActivityDto = null;
		CustomerLimitsDto objCustomerLimits = null;
		CardATMLinkDto objOldCardATMLinkDto = null;
		CardATMLinkDto objNewCardATMLinkDto = null;
		SystemParamDto objSystemParamDto = null;
		CardChangeCloseDto objCardChangeCloseDto = null;
		CardsDto objOldCardsDto = null;
		CardDataDto objCardDataDto = null;
		ApplicationProcessDto objProcessDto = null;
		CardProductDto objCardProductDto = null;

		Set listCards = null;
		String issuerID = "";
		String serviceCode = "";
		//int cardClose = 0;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			issuerID = objApplicationProcessDto.getIssuerId();
			logger.error("Issuer ID "+ issuerID);

			Set set = objApplicationProcessDto.getCustomerAccount();
			logger.error("Set size "+ set.size());
			for (Iterator it = set.iterator(); it.hasNext();) {
				CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
				listCards = custAcctDto.getCustomerCards();
			}

			// update customer
			// session.update(objApplicationProcessDto);

			logger.error("Old Card Number "+ objCardChangeDto.getCardNo());
			objOldCardsDto = (CardsDto) session.get(CardsDto.class,objCardChangeDto.getCardNo());

			// get product service code
			objCardProductDto = (CardProductDto) session.get(CardProductDto.class,objCardChangeDto.getChangeCardProduct());
			serviceCode = objCardProductDto.getServiceCode();
			logger.error("Sevice code "+ serviceCode);

			// get System parameter
			objSystemParamDto = (SystemParamDto) session.get(SystemParamDto.class, "ASP");
			logger.error("SystemParamDto object "+ objSystemParamDto);
			/*cardClose = objSystemParamDto.getCardClose();

			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.DATE, cardClose);
			Date cardCloseDate = calendar.getTime();*/

			int closeDays = objSystemParamDto.getChangeCardCloseInterval();
			logger.error("Close Days "+ closeDays);

			Date nowDate = new Date();
			Calendar calendarNow = Calendar.getInstance();
			calendarNow.setTime(nowDate);
			calendarNow.add(Calendar.DATE, closeDays);
			Date closeDate = calendarNow.getTime();
			logger.error("Close Date "+ closeDate);

			logger.error("Card list "+ listCards);
			for (Iterator it = listCards.iterator(); it.hasNext();) {
				CardsDto objCards = (CardsDto) it.next();

				if (objCards.getTrack1() != null) {

					logger.error("New Card Number track "+ objCards.getTrack1());

					// updating the CARDCHANGELOG table
					String repSql = "UPDATE CardChangeLogDto SET newCardNo =:changecard, flag=:finishedstatus WHERE oldCarddNo=:precard and flag=:status";
					count = session
							.createQuery(repSql)
							.setCharacter("status", '0')
							//.setString("precard", String.valueOf(objCardChangeDto.getCardNo()))
							.setString("precard", String.valueOf(objCards.getOldCradNo()))
							.setCharacter("finishedstatus", '1')
							.setString("changecard", String.valueOf(objCards.getCardNumber()))
							.executeUpdate();


					// code commented by nishandan on 28-09-2012 since they will close manually
					/*if(count > 0){

							// close existing card from CARD_DATA table
							String cardDataSql = "UPDATE CardDataDto SET closingDate =:closeDate, updatedDate=:modifyDate, updatedBy=:modifyBy WHERE cardNumber=:oldCardNo and closingDate IS NULL";
							count = session.createQuery(cardDataSql)
											.setLong("oldCardNo", Long.valueOf(objCards.getOldCradNo()))
											.setString("modifyBy", userId)
											.setDate("modifyDate", new Date())
											.setDate("closeDate", closeDate)
											.executeUpdate();*/

					// commented end

					if (count > 0) {

						//objCards.setCustomerId(objOldCardsDto.getCustomerId());
						objCards.setCustAccountDto(objOldCardsDto.getCustAccountDto());
						objCards.setCardProductsDto(objCardProductDto);

						// set cash used & purchase used
						objCards.setCashUsed(0.0);
						objCards.setPurchaseUsed(0.0);

						// cycle no assign
						objCards.setCycleNo(objOldCardsDto.getCycleNo());

						session.save(objCards);

						logger.error("New Card Number "+ objCards.getCardNumber());
						// inserting into CARD_DATA table
						objCardDataDto = new CardDataDto();
						objCardDataDto.setCardNumber(objCards.getCardNumber());
						objCardDataDto.setExpDate(objCards.getCardExpDate());
						objCardDataDto.setServiceCode(serviceCode);
						objCardDataDto.setPvvOffSet(objCards.getPvvOffSet());
						objCardDataDto.setCvv(objCards.getCvv());
						objCardDataDto.setCvv2(objCards.getCvv2());
						objCardDataDto.setIcvv(objCards.getIcvv());
						objCardDataDto.setStatus(CommonCodes.CARD_STATUS_NEW);
						objCardDataDto.setTrack1(objCards.getTrack1());
						objCardDataDto.setTrack2(objCards.getTrack2());
						objCardDataDto.setIssueDate(new Date());
						objCardDataDto.setUpdatedDate(new Date());
						objCardDataDto.setUpdatedBy(userId);
						//objCardDataDto.setClosingDate(cardCloseDate);
						session.save(objCardDataDto);

						// inserting into customer_limits table
						/*objCustomerLimits = new CustomerLimitsDto();
							objCustomerLimits.setCardNumber(objCards.getCardNumber());
							objCustomerLimits.setAmtPerTranx(objApplicationProcessDto.getAmtPerTranx());
							objCustomerLimits.setDailyLimitCount(objApplicationProcessDto.getDailyLimitCount());
							objCustomerLimits.setDailyLimitAmt(objApplicationProcessDto.getDailyLimitAmt());
							objCustomerLimits.setMonthlyLimitCount(objApplicationProcessDto.getMonthlyLimitCount());
							objCustomerLimits.setMonthlyLimitAmt(objApplicationProcessDto.getMonthlyLimitAmt());
							objCustomerLimits.setCurrencyCode(String.valueOf(objApplicationProcessDto.getCurrencyCode()));
							objCustomerLimits.setIssuerId(issuerID);
							session.save(objCustomerLimits);*/

						logger.error("Customer ID "+ objCards.getCustomerId());
						objProcessDto = (ApplicationProcessDto)session.get(ApplicationProcessDto.class, objCards.getCustomerId());

						// inserting into cardsEmbossing table
						objCardEmbossingDto = new CardEmbossingDto();
						objCardEmbossingDto.setCard(objCards);
						objCardEmbossingDto.setCustomer(objProcessDto);
						objCardEmbossingDto.setIssuerId(issuerID);
						objCardEmbossingDto.setTrack1(objCards.getTrack1());
						objCardEmbossingDto.setTrack2(objCards.getTrack2());
						objCardEmbossingDto.setStatus(CommonCodes.CARD_PROCESS_NOT_EMBOSSED);
						objCardEmbossingDto.setLastUpdatedBy(objApplicationProcessDto.getUserId());
						objCardEmbossingDto.setUpdatedDate(new Date());
						session.save(objCardEmbossingDto);

						// inserting into cardsActivity table
						objCardActivityDto = new CardActivityDto();
						objCardActivityDto.setDateTime(new Date());
						objCardActivityDto.setCardNumber(objCards.getCardNumber());
						objCardActivityDto.setActivity("New Card Creation");
						objCardActivityDto.setStationIp(InetAddress.getLocalHost().getHostAddress());
						objCardActivityDto.setUserId(objApplicationProcessDto.getUserId());
						objCardActivityDto.setReason(objApplicationProcessDto.getRemarks());
						objCardActivityDto.setLastUpdatedBy(objApplicationProcessDto.getUserId());
						objCardActivityDto.setUpdatedDate(new Date());
						session.save(objCardActivityDto);

						objOldCardATMLinkDto = (CardATMLinkDto) session.get(CardATMLinkDto.class, Long.valueOf(objCards.getOldCradNo()));
						// inserting into Card_ATM_Link table
						if (objOldCardATMLinkDto != null) {
							objNewCardATMLinkDto = new CardATMLinkDto();
							objNewCardATMLinkDto.setCardNumber(objCards.getCardNumber());
							objNewCardATMLinkDto.setCard(objCards);
							objNewCardATMLinkDto.setSavingAccount(objOldCardATMLinkDto.getSavingAccount());
							objNewCardATMLinkDto.setCurrentAccount(objOldCardATMLinkDto.getCurrentAccount());
							objNewCardATMLinkDto.setDefaultAccount(objOldCardATMLinkDto.getDefaultAccount());
							objNewCardATMLinkDto.setCollectoralAccount(objOldCardATMLinkDto.getCollectoralAccount());
							objNewCardATMLinkDto.setCollectoralAmt(objOldCardATMLinkDto.getCollectoralAmt());
							objNewCardATMLinkDto.setAutoPayAccount(objOldCardATMLinkDto.getAutoPayAccount());
							objNewCardATMLinkDto.setAutoPayAccounton(objOldCardATMLinkDto.getAutoPayAccounton());
							session.save(objNewCardATMLinkDto);
						}

						logger.error("Existes card Status "+ objCardChangeDto.getExistCardStatus());
						char requiredCardStatus = objCardChangeDto.getExistCardStatus();
						if (requiredCardStatus == 'C') {
							objOldCardsDto = (CardsDto) session.get(CardsDto.class,Long.valueOf(objCards.getOldCradNo()));

							objCardChangeCloseDto = new CardChangeCloseDto();
							objCardChangeCloseDto.setCard(objOldCardsDto);
							objCardChangeCloseDto.setStatus(CommonCodes.APPLICATIONSTATUS_NEW);
							objCardChangeCloseDto.setClosingDate(closeDate);
							objCardChangeCloseDto.setUpdatedDate(new Date());
							objCardChangeCloseDto.setUserId(objApplicationProcessDto.getUserId());
							session.save(objCardChangeCloseDto);
						}
					}
				}
				//}
			}

			if(count > 0){
				// updating in card change form table
				String repSql = "UPDATE CardChangeDto SET status =:prostatus WHERE cardNo=:oldcardno ";
				count = session.createQuery(repSql)
						.setLong("oldcardno", objCardChangeDto.getCardNo())
						.setInteger("prostatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
						.executeUpdate();
			}

			logger.error("Count "+ count);
			if (count > 0) {
				tx.commit();
				res = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public Collection listSupplementaryCardApps(String issuerID, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select scdo.applicationId, cido.customerName, ");
			sbf.append("scdo.principleChCardNo, scdo.remarks, ");
			sbf.append("to_char(scdo.lastUpdatedDate,'dd-MM-yyyy') ");
			sbf.append("from SupplementaryFormDto scdo, ");
			sbf.append("CardsDto cdo, CustomerInfoDto cido ");
			sbf.append("where scdo.principleChCardNo = cdo.cardNumber ");
			sbf.append("and cdo.customerId = cido.customerId ");
			sbf.append("and scdo.applStatus = 1 ");
			sbf.append("and scdo.issuerId = '" + issuerID + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl listSupplementaryCardApps Info"
					+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl listSupplementaryCardApps Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public int getTotalSupllementaryCardsApps(String issuerID)
			throws TPlusException {

		int noOfApps = 0;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from SupplementaryFormDto scdo ");
			sbf.append("where scdo.applStatus = 1 ");
			sbf.append("and scdo.issuerId = '" + issuerID + "' ");

			Query qry = session.createQuery(sbf.toString());
			List recList = qry.list();

			if (recList.size() > 0) {
				noOfApps = (Integer) recList.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
					+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getTotalReplacementCardsApps Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return noOfApps;
	}

	public ArrayList getAllSupplementaryApplication(String issuerID)
			throws TPlusException {

		ArrayList objSearchCollection = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from SupplementaryFormDto scdo ");
			sbf.append("where scdo.applStatus = 1 ");
			sbf.append("and scdo.issuerId = '" + issuerID + "' ");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean supplementaryBatchProcess(
			ApplicationProcessDto objApplicationProcessDto,
			SupplementaryFormDto objSupplementaryFormDto, String batchId,
			CardsDto objCardsDto3, String userId,
			ApplicationProcessDto objApplicationProcessDtoOriginal, CustomerGroupFeeDto objCustomerGroupFeeDto)
					throws TPlusException {

		int count = 0;
		boolean res = false;

		Transaction tx = null;

		CardEmbossingDto objCardEmbossingDto = null;
		CardActivityDto objCardActivityDto = null;
		CustomerLimitsDto objCustomerLimits = null;
		CardDataDto objCardDataDto = null;
		CardsDto objCardsDto2 = null;
		CardsDto objCardsDtoSave = null;
		CardATMLinkDto objNewCardATMLinkDto = null;
		CardATMLinkDto objOldCardATMLinkDto = null;

		String issuerID = "";
		String serviceCode = "";
		Set listCards = null;
		//int cardClose = 0;

		CardProductFeeDto objCardProductFeeDto = null;

		// Joining Fee
		CustomerFeeDto objCustomerFeeDtoJoin = null;
		FeeDebitGLDto objDebitGLDtoJoin = null;

		// Annual Fee
		CustomerFeeDto objCustomerFeeDtoAnnual = null;
		FeeDebitGLDto objDebitGLDtoAnnual = null;

		long cardNo;
		String accountId = "";
		float joinFeeAmt = 0;
		float annualFeeAmt = 0;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			if(objCustomerGroupFeeDto == null){
				Query qry = session.createQuery("from CardProductFeeDto cpf where cpf.displayStatus='Active' and cpf.cardProduct.cardProductId= '" + objApplicationProcessDto.getSelectedAppCardProducts() + "'");
				List resPrdFee = qry.list();
				objCardProductFeeDto = (CardProductFeeDto) resPrdFee.get(0);
			}

			objCardsDto2 = (CardsDto) session.get(CardsDto.class,objSupplementaryFormDto.getPrincipleChCardNo());

			// get product service code
			serviceCode = objCardsDto2.getCardProductsDto().getServiceCode();

			String cardType = objCardsDto2.getCardProductsDto().getCardProductType().getCardProductType();

			issuerID = objApplicationProcessDto.getIssuerId();

			Set set = objApplicationProcessDto.getCustomerAccount();
			for (Iterator it = set.iterator(); it.hasNext();) {
				CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
				listCards = custAcctDto.getCustomerCards();

				accountId = custAcctDto.getAccountId();
			}

			// update customer
			// session.update(objApplicationProcessDto);

			for (Iterator it = listCards.iterator(); it.hasNext();) {
				CardsDto objCards = (CardsDto) it.next();

				if (objCards.getTrack1() != null) {
					objCardsDtoSave = objCards;
					break;
				}
			}

			cardNo = objCardsDtoSave.getCardNumber();

			//objCardsDtoSave.setCustomerId(objCardsDto2.getCustomerId());
			objCardsDtoSave.setCustAccountDto(objCardsDto2.getCustAccountDto());
			objCardsDtoSave.setCardProductsDto(objCardsDto2.getCardProductsDto());
			objCardsDtoSave.setCycleNo(objCardsDto2.getCycleNo());

			// set cash used & purchase used
			objCardsDtoSave.setCashUsed(0.0);
			objCardsDtoSave.setPurchaseUsed(0.0);

			session.save(objCardsDtoSave);

			// get System parameter
			/*SystemParamDto objSystemParamDto = (SystemParamDto) session.get(SystemParamDto.class, "ASP");
			cardClose = objSystemParamDto.getCardClose();

			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.DATE, cardClose);
			Date closeDate = calendar.getTime();*/

			// inserting into CARD_DATA table
			objCardDataDto = new CardDataDto();
			objCardDataDto.setCardNumber(objCardsDtoSave.getCardNumber());
			objCardDataDto.setExpDate(objCardsDtoSave.getCardExpDate());
			objCardDataDto.setServiceCode(serviceCode);
			objCardDataDto.setPvvOffSet(objCardsDtoSave.getPvvOffSet());
			objCardDataDto.setCvv(objCardsDtoSave.getCvv());
			objCardDataDto.setCvv2(objCardsDtoSave.getCvv2());
			objCardDataDto.setIcvv(objCardsDtoSave.getIcvv());
			objCardDataDto.setStatus(CommonCodes.CARD_STATUS_NEW);
			objCardDataDto.setTrack1(objCardsDtoSave.getTrack1());
			objCardDataDto.setTrack2(objCardsDtoSave.getTrack2());
			objCardDataDto.setIssueDate(new Date());
			objCardDataDto.setUpdatedDate(new Date());
			objCardDataDto.setUpdatedBy(userId);
			//objCardDataDto.setClosingDate(closeDate);
			session.save(objCardDataDto);

			// inserting into customer_limits table
			/*objCustomerLimits = new CustomerLimitsDto();
			objCustomerLimits.setCardNumber(objCardsDtoSave.getCardNumber());
			objCustomerLimits.setAmtPerTranx(objApplicationProcessDto.getAmtPerTranx());
			objCustomerLimits.setDailyLimitCount(objApplicationProcessDto.getDailyLimitCount());
			objCustomerLimits.setDailyLimitAmt(objApplicationProcessDto.getDailyLimitAmt());
			objCustomerLimits.setMonthlyLimitCount(objApplicationProcessDto.getMonthlyLimitCount());
			objCustomerLimits.setMonthlyLimitAmt(objApplicationProcessDto.getMonthlyLimitAmt());
			objCustomerLimits.setCurrencyCode(String.valueOf(objApplicationProcessDto.getCurrencyCode()));
			objCustomerLimits.setIssuerId(issuerID);
			session.save(objCustomerLimits);*/

			// inserting into cardsEmbossing table
			objCardEmbossingDto = new CardEmbossingDto();
			objCardEmbossingDto.setCard(objCardsDtoSave);
			objCardEmbossingDto.setCustomer(objApplicationProcessDtoOriginal);
			objCardEmbossingDto.setIssuerId(objCardsDtoSave.getIssuerId());
			objCardEmbossingDto.setTrack1(objCardsDtoSave.getTrack1());
			objCardEmbossingDto.setTrack2(objCardsDtoSave.getTrack2());
			objCardEmbossingDto.setStatus(CommonCodes.CARD_PROCESS_NOT_EMBOSSED);
			objCardEmbossingDto.setLastUpdatedBy(objApplicationProcessDto.getUserId());
			objCardEmbossingDto.setUpdatedDate(new Date());
			session.save(objCardEmbossingDto);

			// inserting into cardsActivity table
			objCardActivityDto = new CardActivityDto();
			objCardActivityDto.setDateTime(new Date());
			objCardActivityDto.setCardNumber(objCardsDtoSave.getCardNumber());
			objCardActivityDto.setActivity("New Card Creation");
			objCardActivityDto.setStationIp(InetAddress.getLocalHost().getHostAddress());
			objCardActivityDto.setUserId(objApplicationProcessDto.getUserId());
			objCardActivityDto.setReason(objApplicationProcessDto.getRemarks());
			objCardActivityDto.setLastUpdatedBy(objApplicationProcessDto.getUserId());
			objCardActivityDto.setUpdatedDate(new Date());
			session.save(objCardActivityDto);

			objOldCardATMLinkDto = (CardATMLinkDto) session.get(CardATMLinkDto.class, objSupplementaryFormDto.getPrincipleChCardNo());
			// inserting into Card_ATM_Link table
			if (objOldCardATMLinkDto != null) {
				objNewCardATMLinkDto = new CardATMLinkDto();
				objNewCardATMLinkDto.setCardNumber(objCardsDtoSave.getCardNumber());
				objNewCardATMLinkDto.setCard(objCardsDtoSave);
				objNewCardATMLinkDto.setSavingAccount(objOldCardATMLinkDto.getSavingAccount());
				objNewCardATMLinkDto.setCurrentAccount(objOldCardATMLinkDto.getCurrentAccount());
				objNewCardATMLinkDto.setDefaultAccount(objOldCardATMLinkDto.getDefaultAccount());
				objNewCardATMLinkDto.setCollectoralAccount(objOldCardATMLinkDto.getCollectoralAccount());
				objNewCardATMLinkDto.setCollectoralAmt(objOldCardATMLinkDto.getCollectoralAmt());
				objNewCardATMLinkDto.setAutoPayAccount(objOldCardATMLinkDto.getAutoPayAccount());
				objNewCardATMLinkDto.setAutoPayAccounton(objOldCardATMLinkDto.getAutoPayAccounton());
				session.save(objNewCardATMLinkDto);
			}

			if(objCustomerGroupFeeDto != null){

				joinFeeAmt = objCustomerGroupFeeDto.getJoinFeeSup().floatValue();
				annualFeeAmt = objCustomerGroupFeeDto.getAnnualFeeSup().floatValue();

			}else if(objCardProductFeeDto != null){

				joinFeeAmt = objCardProductFeeDto.getJoinFeeSecondary();
				annualFeeAmt = objCardProductFeeDto.getAnnualFeeSecondary();

			}

			if(ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){

				// joining Fee
				objCustomerFeeDtoJoin = new CustomerFeeDto();
				objCustomerFeeDtoJoin.setCardNo(cardNo);
				objCustomerFeeDtoJoin.setAccountId(accountId);
				objCustomerFeeDtoJoin.setRefId("JF");
				objCustomerFeeDtoJoin.setFeeAmt(joinFeeAmt);
				objCustomerFeeDtoJoin.setAmountSign("D");
				objCustomerFeeDtoJoin.setFeeDate(new Date());
				objCustomerFeeDtoJoin.setFeeSrc(ICacisiss.IFee.FEE_SRC);
				objCustomerFeeDtoJoin.setBilled("N");
				objCustomerFeeDtoJoin.setCreatedDate(new Date());
				objCustomerFeeDtoJoin.setCreatedBy(userId);
				objCustomerFeeDtoJoin.setFeeWaived("N");

				session.save(objCustomerFeeDtoJoin);

				// Annual Fee
				objCustomerFeeDtoAnnual = new CustomerFeeDto();
				objCustomerFeeDtoAnnual.setCardNo(cardNo);
				objCustomerFeeDtoAnnual.setAccountId(accountId);
				objCustomerFeeDtoAnnual.setRefId("AF");
				objCustomerFeeDtoAnnual.setFeeAmt(annualFeeAmt);
				objCustomerFeeDtoAnnual.setAmountSign("D");
				objCustomerFeeDtoAnnual.setFeeDate(new Date());
				objCustomerFeeDtoAnnual.setFeeSrc(ICacisiss.IFee.FEE_SRC);
				objCustomerFeeDtoAnnual.setBilled("N");
				objCustomerFeeDtoAnnual.setCreatedDate(new Date());
				objCustomerFeeDtoAnnual.setCreatedBy(userId);
				objCustomerFeeDtoAnnual.setFeeWaived("N");
				objCustomerFeeDtoAnnual.setAnnualFeeDate(new Date());

				session.save(objCustomerFeeDtoAnnual);

			}else{

				// Joining Fee
				objDebitGLDtoJoin = new FeeDebitGLDto();
				objDebitGLDtoJoin.setDateTime(new Date());
				objDebitGLDtoJoin.setCardNo(String.valueOf(cardNo));
				objDebitGLDtoJoin.setTrnxType("JF");
				objDebitGLDtoJoin.setGlType("D");
				objDebitGLDtoJoin.setAmount(String.valueOf(joinFeeAmt));
				objDebitGLDtoJoin.setBilled("N");
				objDebitGLDtoJoin.setFeeWaived("N");
				objDebitGLDtoJoin.setCreatedBy(userId);

				session.save(objDebitGLDtoJoin);

				// Annual Fee
				objDebitGLDtoAnnual = new FeeDebitGLDto();
				objDebitGLDtoAnnual.setDateTime(new Date());
				objDebitGLDtoAnnual.setCardNo(String.valueOf(cardNo));
				objDebitGLDtoAnnual.setTrnxType("AF");
				objDebitGLDtoAnnual.setGlType("D");
				objDebitGLDtoAnnual.setAmount(String.valueOf(annualFeeAmt));
				objDebitGLDtoAnnual.setBilled("N");
				objDebitGLDtoAnnual.setFeeWaived("N");
				objDebitGLDtoAnnual.setAnnualFeeDate(new Date());
				objDebitGLDtoAnnual.setCreatedBy(userId);

				session.save(objDebitGLDtoAnnual);

			}

			// updating in supplementary table
			String appSql = "UPDATE SupplementaryFormDto SET applStatus =:appacceptstatus WHERE applicationId=:applicationid";
			count = session.createQuery(appSql)
					.setString("applicationid", objSupplementaryFormDto.getApplicationId())
					.setInteger("appacceptstatus",CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
					.executeUpdate();

			if (count > 0) {
				// updating in Application_master table
				String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
				count = session.createQuery(appMtsql)
						.setString("applicationid", objSupplementaryFormDto.getApplicationId())
						.setInteger("appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
						.setDate("closedate", new Date())
						.executeUpdate();
			}

			if (count > 0) {
				tx.commit();
				res = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public Collection listRenewalCardApps(String issuerID, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select crdo.cardNumber, cido.customerName, ");
			sbf.append("crdo.cardExpireDate, ");
			sbf.append("to_char(crdo.updatedDate,'dd-MM-yyyy') ");
			sbf.append("from CardsRenewalDto crdo, CustomerInfoDto cido ");
			sbf.append("where crdo.customerId = cido.customerId ");
			sbf.append("and crdo.status = 1 ");
			sbf.append("and crdo.issuerId = '" + issuerID + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl listRenewalCardApps Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl listRenewalCardApps Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public int getTotalRenewalCardsApps(String issuerID) throws TPlusException {

		int noOfApps = 0;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from CardsRenewalDto crdo ");
			sbf.append("where crdo.status = 1 ");
			sbf.append("and crdo.issuerId = '" + issuerID + "' ");

			Query qry = session.createQuery(sbf.toString());
			List recList = qry.list();

			if (recList.size() > 0) {
				noOfApps = (Integer) recList.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getTotalRenewalCardsApps Info"
					+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getTotalRenewalCardsApps Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return noOfApps;
	}

	public ArrayList getAllRenewalApplication(String issuerID)
			throws TPlusException {

		ArrayList objSearchCollection = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardsRenewalDto crdo ");
			sbf.append("where crdo.status = 1 ");
			sbf.append("and crdo.issuerId = '" + issuerID + "' ");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getAllRenewalApplication "
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getAllRenewalApplication "
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean renewalBatchProcess(CardsRenewalDto objCardsRenewalDto,
			String batchId, CardsDto objCardsDto,
			ApplicationProcessDto objApplicationProcessDto, String userId)
					throws TPlusException {

		int count = 0;
		boolean res = false;

		Transaction tx = null;

		CardEmbossingDto objCardEmbossingDto = null;
		CardActivityDto objCardActivityDto = null;
		FeeCreditGLDto objFeeCreditGLDto = null;
		DebitCardFeeDto objDebitCardFeeDto = null;
		CardProductFeeDto objCardProductFeeDto = null;
		CardDataDto objCardDataDto = null;
		CardProductDto objCardProductDto = null;

		// customer fee add
		CustomerFeeDto objCustomerFeeDto = null;

		String issuerID = "";
		String cardType = "";
		String serviceCode = "";
		int cardClose = 0;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			issuerID = objCardsDto.getIssuerId();

			objCardProductDto = (CardProductDto) session.get(
					CardProductDto.class, objCardsDto.getCardProductId());
			serviceCode = objCardProductDto.getServiceCode();

			// update card
			session.update(objCardsDto);

			cardType = objCardProductDto.getCardProductType()
					.getCardProductType();

			if (objCardsDto.getTrack1() != null) {

				// get System parameter
				SystemParamDto objSystemParamDto = (SystemParamDto) session
						.get(SystemParamDto.class, "ASP");
				cardClose = objSystemParamDto.getCardClose();

				Date now = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(now);
				calendar.add(Calendar.DATE, cardClose);
				Date closeDate = calendar.getTime();

				// close existing card from CARD_DATA table
				String cardDataSql = "UPDATE CardDataDto SET closingDate =:closeDate, updatedDate=:modifyDate, updatedBy=:modifyBy WHERE cardNumber=:oldCardNo and closingDate IS NULL";
				count = session.createQuery(cardDataSql)
						.setLong("oldCardNo", objCardsRenewalDto.getCardNumber())
						.setString("modifyBy", userId)
						.setDate("modifyDate", new Date())
						.setDate("closeDate", closeDate)
						.executeUpdate();

				if (count > 0) {

					// inserting into CARD_DATA table
					objCardDataDto = new CardDataDto();
					objCardDataDto.setCardNumber(objCardsDto.getCardNumber());
					objCardDataDto.setExpDate(objCardsDto.getCardExpDate());
					objCardDataDto.setServiceCode(serviceCode);
					objCardDataDto.setPvvOffSet(objCardsDto.getPvvOffSet());
					objCardDataDto.setCvv(objCardsDto.getCvv());
					objCardDataDto.setCvv2(objCardsDto.getCvv2());
					objCardDataDto.setIcvv(objCardsDto.getIcvv());
					objCardDataDto.setStatus(CommonCodes.CARD_STATUS_NEW);
					objCardDataDto.setTrack1(objCardsDto.getTrack1());
					objCardDataDto.setTrack2(objCardsDto.getTrack2());
					objCardDataDto.setIssueDate(new Date());
					objCardDataDto.setUpdatedDate(new Date());
					objCardDataDto.setUpdatedBy(userId);
					//objCardDataDto.setClosingDate(closeDate);
					session.save(objCardDataDto);

					// inserting into cardsEmbossing table
					objCardEmbossingDto = new CardEmbossingDto();
					objCardEmbossingDto.setCard(objCardsDto);
					objCardEmbossingDto.setCustomer(objApplicationProcessDto);
					objCardEmbossingDto.setIssuerId(objCardsDto.getIssuerId());
					objCardEmbossingDto.setTrack1(objCardsDto.getTrack1());
					objCardEmbossingDto.setTrack2(objCardsDto.getTrack2());
					objCardEmbossingDto
					.setStatus(CommonCodes.CARD_PROCESS_NOT_EMBOSSED);
					objCardEmbossingDto
					.setNewCardFor(CommonCodes.NEW_CARD_FOR_RENEWAL);
					objCardEmbossingDto.setLastUpdatedBy(objApplicationProcessDto
							.getUserId());
					objCardEmbossingDto.setUpdatedDate(new Date());
					session.save(objCardEmbossingDto);

					// inserting into cardsActivity table
					objCardActivityDto = new CardActivityDto();
					objCardActivityDto.setDateTime(new Date());
					objCardActivityDto.setCardNumber(objCardsDto.getCardNumber());
					objCardActivityDto.setActivity("Renewal Card Creation");
					objCardActivityDto.setStationIp(InetAddress.getLocalHost()
							.getHostAddress());
					objCardActivityDto.setUserId(objApplicationProcessDto
							.getUserId());
					objCardActivityDto.setReason(objApplicationProcessDto
							.getRemarks());
					objCardActivityDto.setLastUpdatedBy(objApplicationProcessDto
							.getUserId());
					objCardActivityDto.setUpdatedDate(new Date());
					session.save(objCardActivityDto);

					/*StringBuffer sbf = new StringBuffer();
					sbf.append("from CardProductFeeDto cpfdo ");
					sbf.append("where cpfdo.cardProduct.cardProductId = '"
							+ objCardsDto.getCardProductId() + "' ");
					sbf.append("and cpfdo.displayStatus = 'Active' ");

					Query qry = session.createQuery(sbf.toString());
					List resList = qry.list();
					if (resList.size() > 0) {
						objCardProductFeeDto = (CardProductFeeDto) resList.get(0);
					}

					String renewalFees = "";
					if (objCardsDto.getCardHolderType() == 1) {
						renewalFees = String.valueOf(objCardProductFeeDto
								.getAnnualFeePrimary());
					} else {
						renewalFees = String.valueOf(objCardProductFeeDto
								.getAnnualFeeSecondary());
					}

					if (CommonCodes.CPT_CREDIT.equals(cardType)) {
						CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto) session
						.get(CustomerAccountDto.class, objCardsDto
								.getAccountId());// objCardsDto.getCustAccountDto();
						float preBalance = objCustomerAccountDto
						.getPreviousBalance();
						float availableBalance = preBalance
						- Float.valueOf(renewalFees);

						objFeeCreditGLDto = new FeeCreditGLDto();
						objFeeCreditGLDto.setIssuerId(issuerID);
						objFeeCreditGLDto.setDateTime(new Date());
						objFeeCreditGLDto.setCardNo(String
								.valueOf(objCardsRenewalDto.getCardNumber()));
						objFeeCreditGLDto
						.setTrnxType(CommonCodes.TRANX_RENEWAL_FEE);
						objFeeCreditGLDto.setGlType(CommonCodes.GL_DEBIT);
						objFeeCreditGLDto.setAmount(renewalFees);
						objFeeCreditGLDto.setBalance(String
								.valueOf(availableBalance));
						objFeeCreditGLDto
						.setBilled(CommonCodes.GL_RECORD_NOT_BILLED);
						session.save(objFeeCreditGLDto);

						float limitUsed = objCustomerAccountDto.getLimitUsed();
						limitUsed = limitUsed + Float.valueOf(renewalFees).floatValue();
						objCustomerAccountDto.setLimitUsed(limitUsed);

						// updating CustomerAccount balance
						objCustomerAccountDto.setPreviousBalance(availableBalance);
						session.update(objCustomerAccountDto);
					} else {
						objDebitCardFeeDto = new DebitCardFeeDto();
						objDebitCardFeeDto.setIssuerId(issuerID);
						objDebitCardFeeDto.setDateTime(new Date());
						objDebitCardFeeDto.setCardNo(String
								.valueOf(objCardsRenewalDto.getCardNumber()));
						objDebitCardFeeDto
						.setTrnxType(CommonCodes.TRANX_RENEWAL_FEE);
						objDebitCardFeeDto.setGlType(CommonCodes.GL_DEBIT);
						objDebitCardFeeDto.setAmount(renewalFees);
						objDebitCardFeeDto
						.setStatus(CommonCodes.DEBIT_PROCESS_STATUS_NO);
						objDebitCardFeeDto
						.setProcessingHost(CommonCodes.PH_CORE_BANKING);
						session.save(objDebitCardFeeDto);
					}

					objCustomerFeeDto = new CustomerFeeDto();
					objCustomerFeeDto.setCardNo(objCardsRenewalDto.getCardNumber());
					objCustomerFeeDto.setAccountId(objCardsDto.getAccountId());
					objCustomerFeeDto.setRefId(CommonCodes.TRANX_RENEWAL_FEE);
					objCustomerFeeDto.setFeeAmt(Float.valueOf(renewalFees));
					objCustomerFeeDto.setAmountSign(CommonCodes.GL_DEBIT);
					objCustomerFeeDto.setFeeDate(new Date());
					objCustomerFeeDto.setFeeSrc(CommonCodes.FEE_SRC_ADMIN);
					objCustomerFeeDto.setBilled(CommonCodes.GL_RECORD_NOT_BILLED);
					objCustomerFeeDto.setCreatedDate(new Date());
					objCustomerFeeDto.setCreatedBy(userId);

					session.save(objCustomerFeeDto);*/

					// updating in ApplicationForms table
					String appSql = "UPDATE CardsRenewalDto SET status =:appacceptstatus WHERE cardRenewalSerial=:cardRenewalSerial";
					count = session.createQuery(appSql).setString(
							"cardRenewalSerial",
							objCardsRenewalDto.getCardRenewalSerial()).setInteger(
									"appacceptstatus",
									CommonCodes.APPLICATIONSTATUS_ACCEPTED_PROCESSED)
									.executeUpdate();

					if (count > 0) {
						tx.commit();
						res = true;
					}
				}

			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl list Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl list Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public ArrayList getAllSupplCardsList(String accId) throws TPlusException {

		ArrayList objSearchCollection = new ArrayList();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardsDto cdo ");
			sbf.append("where cdo.cardHolderType = 2 and cdo.accountId = '" + accId + "'");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList) qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getAllSupplCardsList method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getAllSupplCardsList  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;

	}

	@Override
	public ApplicationFormDto getApplicationForm(String applicationId)
			throws TPlusException {
		ApplicationFormDto objDto = null;
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objDto = (ApplicationFormDto) session.get(ApplicationFormDto.class, applicationId);

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getApplicationForm method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getApplicationForm  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;
	}

	@Override
	public boolean addCardApplLink(CardApplLinkDto objCardApplLinkDto)
			throws TPlusException {
		boolean boolAdd = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objCardApplLinkDto);

			tx.commit();
			boolAdd = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl addCardApplLink method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl addCardApplLink  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;
	}

	@Override
	public ArrayList<CardBatchDto> getCardBatch() throws TPlusException {
		ArrayList<CardBatchDto> objSearchCollection = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardBatchDto dto ");
			sbf.append("where dto.status = 'N' ");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList<CardBatchDto>) qry.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getCardBatch Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getCardBatch Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	@Override
	public ArrayList<ApplicationFormDto> getApplicationFormByBatchId(
			String batchId) throws TPlusException {
		ArrayList<ApplicationFormDto> objSearchCollection = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from ApplicationFormDto dto ");
			sbf.append("where dto.applicationId in (select ca.applicationId from CardApplLinkDto ca where ca.batchId = '" + batchId + "') ");

			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList<ApplicationFormDto>) qry.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getCardBatch Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getCardBatch Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}
	
	@Override
	public CardBatchDto getCardBatchDto(String batchId)
			throws TPlusException {
		CardBatchDto objCardBatchDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardBatchDto = (CardBatchDto) session.get(CardBatchDto.class, batchId);

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardbatchProcessDAOImpl getCardBatchDto method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardbatchProcessDAOImpl getCardBatchDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objCardBatchDto;
	}

	@Override
	public String getUserType(String userId) throws TPlusException {
		String userType = null;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select u.userType ");
			sbf.append("from UserMasterDto u ");
			sbf.append("where u.id.userId = '" + userId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List recList = qry.list();

			if (recList.size() > 0) {
				userType = (String) recList.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getUserType Info"
					+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getUserType Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return userType;
	}

	@Override
	public Map getApplTypleList() throws TPlusException {
		Map applTypeList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from ApplicationTypeDto dto ");

			List applicationType = qry.list();

			for (Iterator it = applicationType.iterator(); it.hasNext();) {
				ApplicationTypeDto objDto = new ApplicationTypeDto();
				objDto = (ApplicationTypeDto) it.next();
				applTypeList.put(objDto.getApplicationTypeId(),
						objDto.getDescription());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getApplTypleList "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getApplTypleList "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return applTypeList;
	}

	@Override
	public Map getCardBatchStatusList() throws TPlusException {
		Map cardBatchStatusList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CodeMasterDto dto where dto.id.groupId = 'BATCH_STATUS' ");

			List agentList = qry.list();

			for (Iterator it = agentList.iterator(); it.hasNext();) {
				CodeMasterDto objDto = new CodeMasterDto();
				objDto = (CodeMasterDto) it.next();
				cardBatchStatusList.put(objDto.getId().getCodeId(),
						objDto.getCodeDesc());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getCardBatchStatusList "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getCardBatchStatusList "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return cardBatchStatusList;
	}

	@Override
	public String getApplicationType(int applicationType) throws TPlusException {
		String description = null;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		ApplicationTypeDto objDto = null;
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			
			objDto = (ApplicationTypeDto) session.get(
					ApplicationTypeDto.class, applicationType);

			description = objDto.getDescription();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getApplicationType Info"
					+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getApplicationType Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return description;
	}

	@Override
	public Map getBranch(BranchDto objBranchDto, String getAll)
			throws TPlusException {
		Map branchList = new LinkedHashMap();
		Transaction tx = null;
		StringBuffer sb = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			if(getAll.equals("ALL")) {
				sb.append("from BranchDto order by branchName ");
			} else {
				if(objBranchDto.getAccessAllBranch().equals("Y")) {
					sb.append("from BranchDto order by branchName ");
				} else {
					sb.append("from BranchDto where branchId='" + objBranchDto.getBranchId() + "' order by branchName ");
				}
			}
			
			Query qry = session.createQuery(sb.toString());

			List agentList = qry.list();

			for (Iterator it = agentList.iterator(); it.hasNext();) {
				BranchDto objDto = new BranchDto();
				objDto = (BranchDto) it.next();
				branchList.put(objDto.getBranchId(), objDto.getBranchName());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getBranch in BaseDAOIMpl "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getBranch in BaseDAOIMpl "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return branchList;
	}

}
