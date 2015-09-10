package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.PinResendDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingDto;
import org.transinfo.cacis.dto.common.ApplicationMasterDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.dto.customerservice.CardCloseDto;
import org.transinfo.cacis.dto.customerservice.CardLimitAdjustmentDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementDto;
import org.transinfo.cacis.dto.customerservice.CardStatusRemarksDto;
import org.transinfo.cacis.dto.customerservice.CreditSplitDto;
import org.transinfo.cacis.dto.customerservice.PinResendDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.transaction.CustomerFeeDto;
import org.transinfo.cacis.dto.transaction.FeeDebitGLDto;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;
import org.transinfo.cacis.formbean.customerservice.LimitForm;
import org.transinfo.cacis.model.CustomerService;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings("unchecked")
public class PinResendDAOImpl extends BaseDAOImpl implements PinResendDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #search(org.transinfo.cacis.dto.customerservice.CardReplacementDto) this
	 * method is for Get all the Data(for all customer service screens) using
	 * model's(CustomerSevice class getCustomerServiceData() method)
	 */
	public Collection search(String cardNo)
			throws TPlusException {

		Collection objSearchCollection = null;

		try {

			CustomerService objCustServ = new CustomerService();
			CustomerServiceDataBean objService = objCustServ.getCustomerServiceData(cardNo);
			objSearchCollection = new ArrayList();
			objSearchCollection.add(objService);
			

		} catch (Exception e) {
			System.out
					.println("Error in CardReplacementDAOImpl search method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardReplacementDAOImpl search  method :" + e);
		} finally {
		}
		return objSearchCollection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #add(org.transinfo.cacis.dto.customerservice.CardReplacementDto) this
	 * mehod is for updating the cards table and inserting data into
	 * cardreplacement_forms ,BlackListCards,Application_Master and cardActvites
	 * tables
	 */
	public boolean add(CardReplacementDto objCarRepDto) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;
		CardActivityDto objCardActivity = null;
		BlackListCardDto objBlackListCard = null;
		ApplicationMasterDto objAppMaster = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// saving to CardReplacement_forms table
			session.save(objCarRepDto);

			// updating cards table
			String cardsql = "UPDATE CardsDto SET cardStatusId =:cardstatus WHERE cardNumber=:cardnumber";
			count = session.createQuery(cardsql).setLong("cardnumber",
					objCarRepDto.getCardNumber()).setLong("cardstatus",
					objCarRepDto.getReasonCode()).executeUpdate();

			if (count > 0) {

				// inserting into Application_master Table
				objAppMaster = new ApplicationMasterDto();
				objAppMaster.setApplicationId(objCarRepDto.getApplicationId());
				objAppMaster.setIssuerId(objCarRepDto.getIssuerId());
				objAppMaster.setApplicationStatus(objCarRepDto
						.getApplicationStatus());
				objAppMaster.setApplicationType(objCarRepDto
						.getApplicationType());
				objAppMaster.setIdNumber(objCarRepDto.getIdNumber());
				objAppMaster.setOpenDate(objCarRepDto.getUpdatedDate());
				objAppMaster.setUserId(objCarRepDto.getUserId());
				objAppMaster.setUpdatedDate(objCarRepDto.getUpdatedDate());

				session.save(objAppMaster);

				// inserting into BlackListCard Table
				objBlackListCard = new BlackListCardDto();
				objBlackListCard.setCardNumber(objCarRepDto.getCardNumber());
				objBlackListCard.setCardStatusId(objCarRepDto.getReasonCode());
				objBlackListCard.setIssuerId(objCarRepDto.getIssuerId());
				objBlackListCard.setUserId(objCarRepDto.getUserId());
				objBlackListCard.setUpdatedDate(objCarRepDto.getUpdatedDate());

				session.save(objBlackListCard);
				// inserting into CardActivites Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCarRepDto.getCardNumber());
				objCardActivity
						.setActivity("CardReplacement Application Created");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objCarRepDto.getUserId());
				objCardActivity.setReason(objCarRepDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCarRepDto.getUserId());
				objCardActivity.setUpdatedDate(objCarRepDto.getUpdatedDate());

				session.save(objCardActivity);

			}
			tx.commit();
			if (count > 0)
				bolExecute = true;
		}

		catch (Exception e) {
			System.out
					.println(" Error while add method in CardReplacementDAOIMPL"
							+ e.getMessage());

			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while add method in CardReplacementDAOIMPL" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	// methods implementation for CardReplacementProcess
	/*
	 * this method is used to get the data for dipalying the replacementcards
	 * list from cardreplacement_forms table to aceept or reject process
	 */

	public Collection processSearch(CardReplacementDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {
			sbf
					.append("select crd.applicationId,crd.cardNumber,crd.replaceReason, ");
			sbf
					.append("to_char(crd.updatedDate,'dd-MM-yyyy') FROM  CardReplacementDto crd where crd.applicationStatus = "
							+ CommonCodes.APPLICATIONSTATUS_NEW + " ");

			if (objSearchDto.getCardNumber() > 0) {
				sbf.append("and crd.cardNumber = "
						+ objSearchDto.getCardNumber() + " ");
			}
			if (objSearchDto.getIssuerId() != null
					&& !objSearchDto.getIssuerId().equals("")) {
				sbf.append("and crd.issuerId = '" + objSearchDto.getIssuerId()
						+ "' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error in  CardReplacementDAOImpl processSearch Method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in  CardReplacementDAOImpl processSearch  Method"
							+ e);
		}

		return objSearchCollection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #getCardReplacementDto(java.lang.String) this for getting the
	 * CardReplacmentDto Data
	 */

	public CardReplacementDto getCardReplacementDto(String applicationId)
			throws TPlusException {

		CardReplacementDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardReplacementDto) session.get(CardReplacementDto.class,
					applicationId);
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting CardReplacementDto data in getCardReplacementDto method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardReplacementDto data in getCardReplacementDto method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO#
	 * accept(org.transinfo.cacis.dto.customerservice.CardReplacementDto) this
	 * method is used for aceepting the card replacementform
	 */
	public boolean accept(CardReplacementDto objRepDto) throws TPlusException {

		boolean accept = false;
		Transaction tx = null;
		int count;
		@SuppressWarnings("unused")
		CardsDto objCardsDto = null;
		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// updating cardrepalcmentdto
			String sql = "UPDATE CardReplacementDto SET applicationStatus =:appacceptstatus  WHERE applicationId=:applicationid";
			count = session.createQuery(sql).setString("applicationid",
					objRepDto.getApplicationId()).setInteger("appacceptstatus",
					CommonCodes.APPLICATIONSTATUS_ACCEPTED).executeUpdate();

			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objRepDto.getApplicationId()).setInteger("appacceptstatus",
					CommonCodes.APPLICATIONSTATUS_ACCEPTED).setDate(
					"closedate", objRepDto.getUpdatedDate()).executeUpdate();

			if (count > 0) {

				// commented by nishandan on 31-10-2011 since we are going to
				// have separate batch process
				/*
				 * // caraNumber Generation CardGeneration objCardGen =
				 * CardGeneration.getInstance(); ArrayList sendList = new
				 * ArrayList(); sendList.add("0");
				 * sendList.add(objRepDto.getCardProductId());
				 * sendList.add(objRepDto.getBranchId());
				 * sendList.add(objRepDto.getIssuerId());
				 * 
				 * ArrayList recvList = objCardGen.cardGeneration(sendList); //
				 * Inserting Data into Cards Table objCardsDto = new CardsDto();
				 * 
				 * // setting the account object to cards //
				 * System.out.println("the Account id in DAOIMPL" //
				 * +objRepDto.getCustomerAccountDto().getAccountId());
				 * objCardsDto
				 * .setCustAccountDto(objRepDto.getCustomerAccountDto());
				 * System.out
				 * .println("the After Generating new CardNumber in DAOIMPL" +
				 * (String) recvList.get(0));
				 * 
				 * objCardsDto.setCardNumber(Long.parseLong((String) recvList
				 * .get(0))); objCardsDto.setCardExpDate((String)
				 * recvList.get(1)); objCardsDto.setCvki(((Integer)
				 * recvList.get(2)).intValue()); objCardsDto.setPvki(((Integer)
				 * recvList.get(3)).intValue());
				 * 
				 * objCardsDto.setIssuerId(objRepDto.getIssuerId());
				 * objCardsDto.setCardProductId(objRepDto.getCardProductId());
				 * objCardsDto.setCardHolderType(objRepDto.getCardHolderType());
				 * objCardsDto.setCardStatusId(CommonCodes.CARD_NEW);
				 * objCardsDto.setCustomerId(objRepDto.getCustomerId());
				 * objCardsDto.setEffectiveDate(new Date());
				 * objCardsDto.setPinDisabled('N'); objCardsDto.setStatus("A");
				 * objCardsDto.setLastUpdatedBy(objRepDto.getUserId());
				 * objCardsDto.setUpdatedDate(objRepDto.getUpdatedDate()); //
				 * Setting remaining card data by generaing random numbers
				 * objCardGen.getCardData(objCardsDto);
				 * 
				 * session.save(objCardsDto);
				 * 
				 * // Inserting Data into CardEmbossing Table objCardEb = new
				 * CardEmbossingDto();
				 * objCardEb.setCardNumber(objCardsDto.getCardNumber());
				 * objCardEb.setCustomerId(objCardsDto.getCustomerId());
				 * objCardEb.setIssuerId(objCardsDto.getIssuerId());
				 * objCardEb.setMagStripeData("Embossing Data");
				 * objCardEb.setStatus(CommonCodes.CARD_PROCESS_NEW);
				 * objCardEb.setLastUpdatedBy(objRepDto.getUserId());
				 * objCardEb.setUpdatedDate(objRepDto.getUpdatedDate());
				 * 
				 * session.save(objCardEb);
				 */
				// comment end

				// inserting into LettersDispatch table
				/*objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters.setLetterId(CommonCodes.CARDREPLACE_APPLICATION);
				objDispLetters.setApplicationId(objRepDto.getApplicationId());
				objDispLetters.setCardNumber(objCardsDto.getCardNumber());
				objDispLetters.setIssuerId(objRepDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
				objDispLetters.setLastUpdateDate(objRepDto.getUpdatedDate());
				objDispLetters.setLastUpdatedBy(objRepDto.getUserId());

				session.save(objDispLetters);*/

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objRepDto.getCardNumber());
				objCardActivity
						.setActivity("CardReplacement Application Acccepted");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objRepDto.getUserId());
				objCardActivity.setReason(objRepDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objRepDto.getUserId());
				objCardActivity.setUpdatedDate(objRepDto.getUpdatedDate());

				session.save(objCardActivity);
			}
			session.flush();
			tx.commit();
			if (count > 0)
				accept = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting replacementform in CardReplacementDAOImpl AcceptMethod"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the replacementform in CardReplacementDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO#
	 * reject(org.transinfo.cacis.dto.customerservice.CardReplacementDto) this
	 * method is used for rejecting the card replacementform
	 */
	public boolean reject(CardReplacementDto objRepDto) throws TPlusException {

		boolean reject = false;
		Transaction tx = null;
		int count;
		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// updating cardReplacements_forms
			String sql = "UPDATE CardReplacementDto SET applicationStatus =:apprejectstatus WHERE applicationId=:applicationid";
			count = session.createQuery(sql).setString("applicationid",
					objRepDto.getApplicationId()).setInteger("apprejectstatus",
					CommonCodes.APPLICATIONSTATUS_REJECTED).executeUpdate();

			// updating Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:apprejectstatus,closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objRepDto.getApplicationId()).setInteger("apprejectstatus",
					CommonCodes.APPLICATIONSTATUS_REJECTED).setDate(
					"closedate", objRepDto.getUpdatedDate()).executeUpdate();

			if (count > 0) {

				// inserting into LettersDispatch table
				/*objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters
						.setLetterId(CommonCodes.CARDREPLACE_REJECT_APPLICATION);
				objDispLetters.setApplicationId(objRepDto.getApplicationId());
				objDispLetters.setCardNumber(objRepDto.getCardNumber());
				objDispLetters.setIssuerId(objRepDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_PROCESS_NEW);
				objDispLetters.setLastUpdateDate(objRepDto.getUpdatedDate());
				objDispLetters.setLastUpdatedBy(objRepDto.getUserId());

				session.save(objDispLetters);*/

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objRepDto.getCardNumber());
				objCardActivity
						.setActivity("CardReplacment Application Rejected");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objRepDto.getUserId());
				objCardActivity.setReason(objRepDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objRepDto.getUserId());
				objCardActivity.setUpdatedDate(objRepDto.getUpdatedDate());

				session.save(objCardActivity);
			}

			session.flush();
			tx.commit();
			System.out.println("reject Count=" + count);
			if (count > 0)
				reject = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while rejecting the cardReplacementForm in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while rejecting the cardReplacementForm in CardReplacementDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return reject;
	}

	public boolean cardreceived(CardReplacementDto objCarRepDto)
			throws TPlusException {

		boolean activate = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE CardsDto SET cardStatusId =:cardactivatesataus WHERE cardNumber=:cardnumber";

			int count = session.createQuery(sql).setLong("cardnumber",
					objCarRepDto.getCardNumber()).setLong("cardactivatesataus",
					CommonCodes.CARD_ACTIVE).executeUpdate();

			// Inserting Data into CardActivities Table
			if (count > 0) {
				CardActivityDto objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCarRepDto.getCardNumber());
				objCardActivity.setActivity("Card Received Confirmation");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objCarRepDto.getUserId());
				objCardActivity.setReason(objCarRepDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCarRepDto.getUserId());
				objCardActivity.setUpdatedDate(objCarRepDto.getUpdatedDate());

				session.save(objCardActivity);
			}
			session.flush();
			tx.commit();
			System.out.println("cardreceived Count=" + count);
			if (count > 0)
				activate = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while cardreceived confirmation  in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while cardreceived confirmation in CardReplacementDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return activate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #cardstop(org.transinfo.cacis.dto.customerservice.CardReplacementDto)
	 * this method is used for cardstoping
	 */

	public boolean cardstop(CardReplacementDto objCardRepDto)
			throws TPlusException {

		boolean stop = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE CardsDto SET cardStatusId =:cardstopsataus, blockReason=:blockreason WHERE cardNumber=:cardnumber";

			int count = session.createQuery(sql).setLong("cardnumber",
					objCardRepDto.getCardNumber()).setLong("cardstopsataus",
					objCardRepDto.getCardStatusId()).setString("blockreason",
					String.valueOf(objCardRepDto.getReasonCode()))
					.executeUpdate();

			if (count > 0) {
				// Inserting Data into CardActivity Table
				CardActivityDto objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCardRepDto.getCardNumber());
				objCardActivity.setActivity("Card Stoped");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objCardRepDto.getUserId());
				objCardActivity.setReason(String.valueOf(objCardRepDto
						.getReasonCode()));
				objCardActivity.setLastUpdatedBy(objCardRepDto.getUserId());
				objCardActivity.setUpdatedDate(objCardRepDto.getUpdatedDate());

				session.save(objCardActivity);

				// Inserting Data into CardStatus remarks Table
				CardStatusRemarksDto objCardStatusRemarksDto = new CardStatusRemarksDto();
				objCardStatusRemarksDto.setStatusNo(objCardRepDto
						.getCardStatusId());
				objCardStatusRemarksDto.setCardNo(String.valueOf(objCardRepDto
						.getCardNumber()));
				objCardStatusRemarksDto.setRemarks(objCardRepDto.getRemarks());

				session.save(objCardStatusRemarksDto);

			}
			session.flush();
			tx.commit();
			System.out.println("stop Count=" + count);
			if (count > 0)
				stop = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while cardstop  in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while cardstop  in CardReplacementDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return stop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #cardactivate(org.transinfo.cacis.dto.customerservice.CardReplacementDto)
	 * this method is used for cardactivate
	 */
	public boolean cardactivate(CardReplacementDto objCarRepDto)
			throws TPlusException {

		boolean activate = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE CardsDto SET cardStatusId =:cardactivatesataus WHERE cardNumber=:cardnumber";

			int count = session.createQuery(sql).setLong("cardnumber",
					objCarRepDto.getCardNumber()).setLong("cardactivatesataus",
					CommonCodes.CARD_ACTIVE).executeUpdate();

			// Inserting Data into CardActivities Table
			if (count > 0) {
				CardActivityDto objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCarRepDto.getCardNumber());
				objCardActivity.setActivity("Card Activated");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objCarRepDto.getUserId());
				objCardActivity.setReason(objCarRepDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCarRepDto.getUserId());
				objCardActivity.setUpdatedDate(objCarRepDto.getUpdatedDate());

				session.save(objCardActivity);
			}
			session.flush();
			tx.commit();
			System.out.println("activate Count=" + count);
			if (count > 0)
				activate = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while activating the card in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while activating the card in CardReplacementDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return activate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #
	 * resetpincount(org.transinfo.cacis.dto.customerservice.CardReplacementDto)
	 * this method is used for reseting the card pin count
	 */
	public boolean resetpincount(CardReplacementDto objCarRepDto)
			throws TPlusException {

		boolean resetpincount = false;
		Transaction tx = null;
		CardActivityDto objCardActivity = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE CardsDto SET wrongPinCount =:pincount, pinReset=:pinResetCol WHERE cardNumber=:cardnumber";

			int count = session.createQuery(sql).setLong("cardnumber",
					objCarRepDto.getCardNumber()).setLong("pincount",
					CommonCodes.CARD_PIN_COUNT).setCharacter("pinResetCol",
					CommonCodes.CARD_PIN_RESET).executeUpdate();

			if (count > 0) {
				// Inserting Data into CardActivities Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCarRepDto.getCardNumber());
				objCardActivity.setActivity("Card Pin Count Setted");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objCarRepDto.getUserId());
				objCardActivity.setReason(objCarRepDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCarRepDto.getUserId());
				objCardActivity.setUpdatedDate(objCarRepDto.getUpdatedDate());

				session.save(objCardActivity);
			}
			session.flush();
			tx.commit();
			System.out.println("resetpincount Count=" + count);
			if (count > 0)
				resetpincount = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while setting the card pin count  in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while setting the card pin count in CardReplacementDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return resetpincount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #
	 * billingaddchange(org.transinfo.cacis.dto.customerservice.CardReplacementDto
	 * ) this to setting the new billing address
	 */
	public boolean billingaddchange(CardReplacementDto objCarRepDto)
			throws TPlusException {

		boolean billingaddchage = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// here we are updating the data into the Customer_address table
			session.update(objCarRepDto.getCustomerAddDto());
			session.flush();
			tx.commit();
			billingaddchage = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while setting  new billing address  in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while setting  new billing address in CardReplacementDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return billingaddchage;
	}

	// methods implementation for CardCloseForm

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #add(org.transinfo.cacis.dto.customerservice.CardCloseDto) this mehod is
	 * for updating the cards table and inserting data into
	 * cardreplacement_forms ,Application_Master and cardActvites tables
	 */
	public boolean cardCloseAdd(CardCloseDto objCardcloseDto)
			throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		CardActivityDto objCardActivity = null;
		ApplicationMasterDto objAppMaster = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// saving to CardClose_forms table
			session.save(objCardcloseDto);

			// inserting into Application_master Table
			objAppMaster = new ApplicationMasterDto();
			objAppMaster.setApplicationId(objCardcloseDto.getApplicationId());
			objAppMaster.setIssuerId(objCardcloseDto.getIssuerId());
			objAppMaster.setApplicationStatus(objCardcloseDto
					.getApplicationStatus());
			objAppMaster.setApplicationType(objCardcloseDto
					.getApplicationType());
			objAppMaster.setIdNumber(objCardcloseDto.getIdNumber());
			objAppMaster.setOpenDate(objCardcloseDto.getUpdatedDate());
			objAppMaster.setUserId(objCardcloseDto.getUserId());
			objAppMaster.setUpdatedDate(objCardcloseDto.getUpdatedDate());

			session.save(objAppMaster);

			// inserting into CardActivites Table
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(objCardcloseDto.getCardNumber());
			objCardActivity.setActivity("CardClose Application Created");
			objCardActivity.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());
			objCardActivity.setUserId(objCardcloseDto.getUserId());
			objCardActivity.setReason(objCardcloseDto.getRemarks());
			objCardActivity.setLastUpdatedBy(objCardcloseDto.getUserId());
			objCardActivity.setUpdatedDate(objCardcloseDto.getUpdatedDate());

			session.save(objCardActivity);

			tx.commit();
			bolExecute = true;
		}

		catch (Exception e) {
			System.out
					.println("Exception in while cardclsoeAdd method in CardReplacementDAOIMPL"
							+ e.getMessage());

			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while cardclsoeAdd method in CardReplacementDAOIMPL"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	// methods implementation for CardCloseForm Process

	/*
	 * this method is used to get the data for dipalying the closingcards list
	 * from cardclose_forms table to aceept or reject processes
	 */

	public Collection cardCloseProcessSearch(CardCloseDto objCardCloseDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {
			sbf.append("select ccd.applicationId,ccd.cardNumber,");
			sbf
					.append("to_char(ccd.updatedDate,'dd-MM-yyyy') FROM  CardCloseDto ccd where ccd.applicationStatus = "
							+ CommonCodes.APPLICATIONSTATUS_NEW + " ");

			if (objCardCloseDto.getCardNumber() > 0) {
				sbf.append("and ccd.cardNumber = "
						+ objCardCloseDto.getCardNumber() + " ");
			}
			if (objCardCloseDto.getIssuerId() != null
					&& !objCardCloseDto.getIssuerId().equals("")) {
				sbf.append(" and ccd.issuerId = '"
						+ objCardCloseDto.getIssuerId() + "' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error in  CardReplacementDAOImpl cardCloseProcessSearch Method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in  CardReplacementDAOImpl cardCloseProcessSearch  Method"
							+ e);
		}

		return objSearchCollection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #getCardCloseDto(java.lang.String) this for getting the CardCloseDto Data
	 */

	public CardCloseDto getCardCloseDto(String applicationId)
			throws TPlusException {

		CardCloseDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardCloseDto) session.get(CardCloseDto.class,
					applicationId);
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting CardCloseDto data in getCardCloseDto method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardCloseDto data in getCardCloseDtoo method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO#
	 * accept(org.transinfo.cacis.dto.customerservice.CardCloseDto) this method
	 * is used for aceepting the card cardCloseform
	 */
	public boolean cardCloseAccept(CardCloseDto objCardcloseDto)
			throws TPlusException {

		boolean accept = false;
		Transaction tx = null;
		int count = 0;
		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// updating Cards table
			// this is for supplementary card to stop
			if (objCardcloseDto.getCardsToStop().length < 0) {
				String cardsql = "UPDATE CardsDto SET cardStatusId =:cardclosestatus WHERE cardNumber=:cardnumber";
				count = session.createQuery(cardsql).setLong("cardnumber",
						objCardcloseDto.getCardNumber()).setLong(
						"cardclosestatus", CommonCodes.CARD_CLOSE)
						.executeUpdate();
			}
			// this is for primary card and its supplymentary cards to stop
			else {
				for (int i = 0; i < objCardcloseDto.getCardsToStop().length; i++) {
					String cardsSql = "UPDATE CardsDto SET cardStatusId =:cardclosestatus WHERE cardNumber in(:cardnumbers)";
					count = session.createQuery(cardsSql).setParameterList(
							"cardnumbers", objCardcloseDto.getCardsToStop())
							.setLong("cardclosestatus", CommonCodes.CARD_CLOSE)
							.executeUpdate();
				}
			}

			// updating cardclose_forms table
			String sql = "UPDATE CardCloseDto  SET applicationStatus =:appacceptstatus WHERE applicationId=:applicationid";
			count = session.createQuery(sql).setString("applicationid",
					objCardcloseDto.getApplicationId()).setInteger(
					"appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED)
					.executeUpdate();

			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objCardcloseDto.getApplicationId()).setInteger(
					"appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED)
					.setDate("closedate", objCardcloseDto.getUpdatedDate())
					.executeUpdate();

			if (count > 0) {

				// inserting into LettersDispatch table
				/*objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters.setLetterId(CommonCodes.CARDCLOSE_APPLICATION);
				objDispLetters.setApplicationId(objCardcloseDto
						.getApplicationId());
				objDispLetters.setCardNumber(objCardcloseDto.getCardNumber());
				objDispLetters.setIssuerId(objCardcloseDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
				objDispLetters.setLastUpdateDate(objCardcloseDto
						.getUpdatedDate());
				objDispLetters.setLastUpdatedBy(objCardcloseDto.getUserId());

				session.save(objDispLetters);*/

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCardcloseDto.getCardNumber());
				objCardActivity.setActivity("CardClose Application Accepted");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objCardcloseDto.getUserId());
				objCardActivity.setReason(objCardcloseDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCardcloseDto.getUserId());
				objCardActivity
						.setUpdatedDate(objCardcloseDto.getUpdatedDate());

				session.save(objCardActivity);
			}
			session.flush();
			tx.commit();
			System.out.println("cardCloseAccept  Count=" + count);
			if (count > 0)
				accept = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting the  data in CardReplacementDAOImpl cardCloseAccept"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the cardCloseform in CardReplacementDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO#
	 * reject(org.transinfo.cacis.dto.customerservice.CardCloseDto) this method
	 * is used for rejecting the card closeform
	 */
	public boolean cardCloseReject(CardCloseDto objCloseDto)
			throws TPlusException {

		boolean reject = false;
		Transaction tx = null;
		int count;
		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// updating in cardclose_forms table
			String sql = "UPDATE CardCloseDto SET applicationStatus =:apprejectstatus WHERE applicationId=:applicationid";
			count = session.createQuery(sql).setString("applicationid",
					objCloseDto.getApplicationId()).setInteger(
					"apprejectstatus", CommonCodes.APPLICATIONSTATUS_REJECTED)
					.executeUpdate();

			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto SET applicationStatus =:apprejectstatus , closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objCloseDto.getApplicationId()).setInteger(
					"apprejectstatus", CommonCodes.APPLICATIONSTATUS_REJECTED)
					.setDate("closedate", objCloseDto.getUpdatedDate())
					.executeUpdate();

			if (count > 0) {

				// inserting into LettersDispatch table
				/*objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters
						.setLetterId(CommonCodes.CARDCLOSE_REJECT_APPLICATION);
				objDispLetters.setApplicationId(objCloseDto.getApplicationId());
				objDispLetters.setCardNumber(objCloseDto.getCardNumber());
				objDispLetters.setIssuerId(objCloseDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
				objDispLetters.setLastUpdateDate(objCloseDto.getUpdatedDate());
				objDispLetters.setLastUpdatedBy(objCloseDto.getUserId());

				session.save(objDispLetters);*/

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCloseDto.getCardNumber());
				objCardActivity.setActivity("CardClose Application Rejected");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId("APSUPERADMIN");
				objCardActivity.setReason(objCloseDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCloseDto.getUserId());
				objCardActivity.setUpdatedDate(objCloseDto.getUpdatedDate());

				session.save(objCardActivity);
			}

			session.flush();
			tx.commit();
			System.out.println("cardCloseReject  Count=" + count);
			if (count > 0)
				reject = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while rejecting the cardCloseForm in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while rejecting the cardCloseForm in CardReplacementDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return reject;
	}

	// methods implementation for PinResendForm

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #add(org.transinfo.cacis.dto.customerservice.PinResendDto) this mehod is
	 * for updating the cards table and inserting data into PinResend_forms
	 * ,Application_Master and cardActvites tables
	 */
	public boolean pinResendAdd(PinResendDto objPinResendDto)
			throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		CardActivityDto objCardActivity = null;
		ApplicationMasterDto objAppMaster = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// saving to PinResend_forms table
			session.save(objPinResendDto);

			// inserting into Application_master Table
			objAppMaster = new ApplicationMasterDto();
			objAppMaster.setApplicationId(objPinResendDto.getApplicationId());
			objAppMaster.setIssuerId(objPinResendDto.getIssuerId());
			objAppMaster.setApplicationStatus(objPinResendDto
					.getApplicationStatus());
			objAppMaster.setApplicationType(objPinResendDto
					.getApplicationType());
			objAppMaster.setIdNumber(objPinResendDto.getIdNumber());
			objAppMaster.setOpenDate(objPinResendDto.getUpdatedDate());
			objAppMaster.setUserId(objPinResendDto.getUserId());
			objAppMaster.setUpdatedDate(objPinResendDto.getUpdatedDate());

			session.save(objAppMaster);

			// inserting into CardActivites Table
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(objPinResendDto.getCardNumber());
			objCardActivity.setActivity("PinResend Application Created");
			objCardActivity.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());
			objCardActivity.setUserId(objPinResendDto.getUserId());
			objCardActivity.setReason(objPinResendDto.getRemarks());
			objCardActivity.setLastUpdatedBy(objPinResendDto.getUserId());
			objCardActivity.setUpdatedDate(objPinResendDto.getUpdatedDate());

			session.save(objCardActivity);

			tx.commit();
			bolExecute = true;
		}

		catch (Exception e) {
			System.out
					.println("Exception  while pinResendAdd method in CardReplacementDAOIMPL"
							+ e.getMessage());

			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while pinResendAdd method in CardReplacementDAOIMPL"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	// methods implementation for PinResendForm Process

	/*
	 * this method is used to get the data for dipalying thepinresendcards list
	 * from pinresend_forms table to aceept or reject processes
	 */

	public Collection pinResendProcessSearch(PinResendDto objPinResendDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {
			
			sbf.append("select prd.applicationId, prd.cardNumber,");
			sbf.append("to_char(prd.updatedDate,'dd-MM-yyyy'), cd.maskedCardNo, cd.encryptedCardNo ");

			sbf.append("FROM PinResendDto prd, ApplicationProcessDto apdo, CardsDto cd ");
			sbf.append("where prd.customerId = apdo.customerId ");
			sbf.append("and prd.cardNumber = cd.cardNumber ");
			sbf.append("and prd.applicationStatus = " + CommonCodes.APPLICATIONSTATUS_NEW + " ");

			if (objPinResendDto.getCardNumber() > 0) {
				sbf.append("and cd.encryptedCardNo = '" + objPinResendDto.getEncryptedCardno() + "' ");
			}
			
			if (objPinResendDto.getIssuerId() != null && !objPinResendDto.getIssuerId().equals("")) {
				sbf.append(" and prd.issuerId = '" + objPinResendDto.getIssuerId() + "' ");
			}
			
			if(!"ALL".equalsIgnoreCase(objPinResendDto.getBranchId())){
				sbf.append("and apdo.branchId = '" + objPinResendDto.getBranchId() + "' ");
			}
			
			sbf.append("and apdo.issuerId = '" + objPinResendDto.getIssuerId() + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out.println("Error in  CardReplacementDAOImpl pinResendProcessSearch Method" + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: in  CardReplacementDAOImpl pinResendProcessSearch  Method" + e);
		}

		return objSearchCollection;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #getCardCloseDto(java.lang.String) this for getting the PinResendDto Data
	 */

	public PinResendDto getPinResendDto(String applicationId)
			throws TPlusException {

		PinResendDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (PinResendDto) session.get(PinResendDto.class,
					applicationId);
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting PinResendDto data in getPinResendDto method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting PinResendDto data in getPinResendDtoo method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #accept(org.transinfo.cacis.dto.customerservice.PinResendDto) this method
	 * is used for aceepting the pinResendform
	 */
	public boolean pinResendAccept(PinResendDto objPinResendDto, String userId)
			throws TPlusException {

		boolean accept = false;
		Transaction tx = null;
		int count;
		CardActivityDto objCardActivity = null;
		PinPrintingDto objPinPrintingDto = null;
		//DispatchLetterSearchDto objDispLetters = null;

		// RePIN Fee
		CustomerFeeDto objCustomerFeeDtoRePIN = null;
		FeeDebitGLDto objDebitGLDtoRePIN = null;
		
		long cardNo = objPinResendDto.getCardNumber();
		float rePinFeeAmt = 0;
		int cardHolderType = 0;
		String accountId = "";
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			CardsDto objCardsDto = (CardsDto) session.get(CardsDto.class, cardNo);
			cardHolderType = objCardsDto.getCardHolderType();
			accountId = objCardsDto.getAccountId();
			
			CardProductDto objCardProductDto = (CardProductDto) session.get(CardProductDto.class, objCardsDto.getCardProductId());
			String cardType = objCardProductDto.getCardProductType().getCardProductType();
			
			Query qry = session.createQuery("from CardProductFeeDto cpf where cpf.displayStatus='Active' and cpf.cardProduct.cardProductId= '" + objCardsDto.getCardProductId() + "'");
			List resPrdFee = qry.list();
			CardProductFeeDto objCardProductFeeDto = (CardProductFeeDto) resPrdFee.get(0);
			
			if(objCardProductFeeDto != null){
				
				if (cardHolderType == 1) {
					rePinFeeAmt = objCardProductFeeDto.getRepinFeePrimary();
				} else {
					rePinFeeAmt = objCardProductFeeDto.getRepinFeeSecondary();
				}
				
			}

			// updating PinResendDto
			String sql = "UPDATE PinResendDto  SET applicationStatus =:appacceptstatus, feeApplicable=:feesapp  WHERE applicationId=:applicationid";
			count = session.createQuery(sql).setString("applicationid",objPinResendDto.getApplicationId())
					.setInteger("appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED)
					.setString("feesapp", objPinResendDto.getFeeApplicable())
					.executeUpdate();

			if(count > 0){
			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objPinResendDto.getApplicationId()).setInteger(
					"appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED)
					.setDate("closedate", objPinResendDto.getUpdatedDate())
					.executeUpdate();
			}

			if (count > 0) {
				
				// inserting into PIN_PRINTING table
				objPinPrintingDto = new PinPrintingDto();
				objPinPrintingDto.setCardNumber(objPinResendDto.getCardNumber());
				objPinPrintingDto.setIssuerId(objPinResendDto.getIssuerId());
				objPinPrintingDto.setCustomerId(objPinResendDto.getCustomerId());
				objPinPrintingDto.setStatus(CommonCodes.CARD_PROCESS_NOT_PINMAILED);
				objPinPrintingDto.setLastUpdatedBy(objPinResendDto.getIssuerId());
				objPinPrintingDto.setUpdatedDate(new Date());
				objPinPrintingDto.setPinResend(CommonCodes.CARD_PROCESS_RESEND_NOT_PINMAILED);
				objPinPrintingDto.setResendCount(0);
				session.save(objPinPrintingDto);

				// inserting into LettersDispatch table
				/*objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters.setLetterId(CommonCodes.PINRESEND_APPLICATION);
				objDispLetters.setApplicationId(objPinResendDto
						.getApplicationId());
				objDispLetters.setCardNumber(objPinResendDto.getCardNumber());
				objDispLetters.setIssuerId(objPinResendDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
				objDispLetters.setLastUpdateDate(objPinResendDto
						.getUpdatedDate());
				objDispLetters.setLastUpdatedBy(objPinResendDto.getUserId());

				session.save(objDispLetters);*/

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objPinResendDto.getCardNumber());
				objCardActivity.setActivity("PinResend Application Accept");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objPinResendDto.getUserId());
				objCardActivity.setReason(objPinResendDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objPinResendDto.getUserId());
				objCardActivity
						.setUpdatedDate(objPinResendDto.getUpdatedDate());

				session.save(objCardActivity);
				
				if(ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){
					System.out.println("********CREDIT*******FEE APPLICALE**************"+objPinResendDto.getFeeApplicable());
					//to check pin resend charge
					if(objPinResendDto.getFeeApplicable().equalsIgnoreCase("Y")){

					// joining Fee
					objCustomerFeeDtoRePIN = new CustomerFeeDto();
					objCustomerFeeDtoRePIN.setCardNo(cardNo);
					objCustomerFeeDtoRePIN.setAccountId(accountId);
					objCustomerFeeDtoRePIN.setRefId("RPF");
					objCustomerFeeDtoRePIN.setFeeAmt(rePinFeeAmt);
					objCustomerFeeDtoRePIN.setAmountSign("D");
					objCustomerFeeDtoRePIN.setFeeDate(new Date());
					objCustomerFeeDtoRePIN.setFeeSrc(ICacisiss.IFee.FEE_SRC);
					objCustomerFeeDtoRePIN.setBilled("N");
					objCustomerFeeDtoRePIN.setCreatedDate(new Date());
					objCustomerFeeDtoRePIN.setCreatedBy(userId);
					objCustomerFeeDtoRePIN.setFeeWaived("N");

					session.save(objCustomerFeeDtoRePIN);
					}

				}else{
					System.out.println("********DEBIT*******FEE APPLICALE**************"+objPinResendDto.getFeeApplicable());

					//to check pin resend charge
					if(objPinResendDto.getFeeApplicable().equalsIgnoreCase("Y")){
					
					// Joining Fee
					objDebitGLDtoRePIN = new FeeDebitGLDto();
					objDebitGLDtoRePIN.setDateTime(new Date());
					objDebitGLDtoRePIN.setCardNo(String.valueOf(cardNo));
					objDebitGLDtoRePIN.setTrnxType("RPF");
					objDebitGLDtoRePIN.setGlType("D");
					objDebitGLDtoRePIN.setAmount(String.valueOf(rePinFeeAmt));
					objDebitGLDtoRePIN.setBilled("N");
					objDebitGLDtoRePIN.setFeeWaived("N");
					
					session.save(objDebitGLDtoRePIN);
					}

				}
			}

			tx.commit();
			System.out.println("pinResendAccept  Count=" + count);
			if (count > 0)
				accept = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting the  data in PinResendDAOImpl pinResendAccept"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the pinResendAccept in CardReplacementDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO#
	 * reject(org.transinfo.cacis.dto.customerservice.PinResendDto) this method
	 * is used for rejecting the pinResendform
	 */
	public boolean pinResendReject(PinResendDto objPinResendDto)
			throws TPlusException {

		boolean reject = false;
		Transaction tx = null;
		int count;
		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// updating in cardclose_forms table
			String sql = "UPDATE PinResendDto SET applicationStatus =:apprejectstatus WHERE applicationId=:applicationid";
			count = session.createQuery(sql).setString("applicationid",
					objPinResendDto.getApplicationId()).setInteger(
					"apprejectstatus", CommonCodes.APPLICATIONSTATUS_REJECTED)
					.executeUpdate();

			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto SET applicationStatus =:apprejectstatus , closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objPinResendDto.getApplicationId()).setInteger(
					"apprejectstatus", CommonCodes.APPLICATIONSTATUS_REJECTED)
					.setDate("closedate", objPinResendDto.getUpdatedDate())
					.executeUpdate();

			if (count > 0) {

				// inserting into LettersDispatch table
				/*objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters
						.setLetterId(CommonCodes.PINRESEND_REJECT_APPLICATION);
				objDispLetters.setApplicationId(objPinResendDto
						.getApplicationId());
				objDispLetters.setCardNumber(objPinResendDto.getCardNumber());
				objDispLetters.setIssuerId(objPinResendDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_PROCESS_NEW);
				objDispLetters.setLastUpdateDate(objPinResendDto
						.getUpdatedDate());
				objDispLetters.setLastUpdatedBy(objPinResendDto.getUserId());

				session.save(objDispLetters);*/

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objPinResendDto.getCardNumber());
				objCardActivity.setActivity("Pin Resend Application Rejected");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objPinResendDto.getUserId());
				objCardActivity.setReason(objPinResendDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objPinResendDto.getUserId());
				objCardActivity
						.setUpdatedDate(objPinResendDto.getUpdatedDate());

				session.save(objCardActivity);
			}

			tx.commit();
			System.out.println("pinResendReject  Count=" + count);
			if (count > 0)
				reject = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while rejecting the  pinResendForm in CardReplacementDAOImpl pinResendReject method"
							+ e);
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error:  while rejecting the  pinResendForm in CardReplacementDAOImpl pinResendReject method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return reject;
	}

	// methods implementation for CardLimitAdjustmentForm

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #add(org.transinfo.cacis.dto.customerservice.CardLimitAdjustmentDto) this
	 * mehod is for inserting data into cardLimitAdjust_forms
	 * ,Application_Master and cardActvites tables
	 */
	public boolean cardLimitAdd(CardLimitAdjustmentDto objLimitDto)
			throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		CardActivityDto objCardActivity = null;
		ApplicationMasterDto objAppMaster = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// saving to cardLimitAdjust_forms table
			session.save(objLimitDto);

			// inserting into Application_master Table
			objAppMaster = new ApplicationMasterDto();
			objAppMaster.setApplicationId(objLimitDto.getApplicationId());
			objAppMaster.setIssuerId(objLimitDto.getIssuerId());
			objAppMaster.setApplicationStatus(objLimitDto
					.getApplicationStatus());
			objAppMaster.setApplicationType(objLimitDto.getApplicationType());
			objAppMaster.setIdNumber(objLimitDto.getIdNumber());
			objAppMaster.setOpenDate(objLimitDto.getUpdatedDate());
			objAppMaster.setUserId(objLimitDto.getUserId());
			objAppMaster.setUpdatedDate(objLimitDto.getUpdatedDate());

			session.save(objAppMaster);

			// inserting into CardActivites Table
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(objLimitDto.getCardNumber());
			objCardActivity
					.setActivity("cardLimitAdjustment Application Created");
			objCardActivity.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());
			objCardActivity.setUserId(objLimitDto.getUserId());
			objCardActivity.setReason(objLimitDto.getRemarks());
			objCardActivity.setLastUpdatedBy(objLimitDto.getUserId());
			objCardActivity.setUpdatedDate(objLimitDto.getUpdatedDate());

			session.save(objCardActivity);

			tx.commit();
			bolExecute = true;
		}

		catch (Exception e) {
			System.out
					.println("Exception in while cardLimitAdjsumentAdd method in CardReplacementDAOIMPL"
							+ e.getMessage());

			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while cardLimitAdd method in CardReplacementDAOIMPL"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	// methods implementation for cardLimitAdjustFormProcess

	/*
	 * this method is used to get the data for dipalying the
	 * cardLimitAdjustcards list from cardLimitAdjust_forms table to aceept or
	 * reject processes
	 */

	public Collection cardLimitProcessSearch(
			CardLimitAdjustmentDto objCardLimitDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {
			sbf.append("select cla.applicationId,cla.cardNumber,");
			sbf
					.append("to_char(cla.updatedDate,'dd-MM-yyyy') FROM  CardLimitAdjustmentDto cla where cla.applicationStatus = "
							+ CommonCodes.APPLICATIONSTATUS_NEW + " ");

			if (objCardLimitDto.getCardNumber() > 0) {
				sbf.append("and cla.cardNumber = "
						+ objCardLimitDto.getCardNumber() + " ");
			}
			if (objCardLimitDto.getIssuerId() != null
					&& !objCardLimitDto.getIssuerId().equals("")) {
				sbf.append(" and cla.issuerId = '"
						+ objCardLimitDto.getIssuerId() + "' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error in  CardReplacementDAOImpl cardLimitProcessSearch Method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in  CardReplacementDAOImpl cardLimitProcessSearch  Method"
							+ e);
		}

		return objSearchCollection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #getCardLimitAdjustmentDto(java.lang.String) this for getting the
	 * CardLimitAdjustmentDto Data
	 */

	public CardLimitAdjustmentDto getCardLimitAdjustmentDto(String applicationId)
			throws TPlusException {

		CardLimitAdjustmentDto objCardLimitDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objCardLimitDto = (CardLimitAdjustmentDto) session.get(
					CardLimitAdjustmentDto.class, applicationId);
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting CardLimitAdjustmentDto data in getCardLimitAdjustmentDto method"
							+ e.getMessage());
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardLimitAdjustmentDto data in getCardLimitAdjustmentDto method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objCardLimitDto;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #accept(org.transinfo.cacis.dto.customerservice.CardLimitAdjustmentDto)
	 * this method is used for aceepting the CardLimitAdjustmentform
	 */
	public boolean cardLimitAccept(CardLimitAdjustmentDto objCardLimitDto)
			throws TPlusException {

		boolean accept = false;
		Transaction tx = null;
		int count = 0;
		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// updating the Customer_Account table
			session.update(objCardLimitDto.getCustomerAccountDto());

			// updating in cardLimitAdjsut_forms table
			String sql = "UPDATE CardLimitAdjustmentDto SET applicationStatus =:appacceptstatus WHERE applicationId=:applicationid";
			count = session.createQuery(sql).setString("applicationid",
					objCardLimitDto.getApplicationId()).setInteger(
					"appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED)
					.executeUpdate();

			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objCardLimitDto.getApplicationId()).setInteger(
					"appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED)
					.setDate("closedate", objCardLimitDto.getUpdatedDate())
					.executeUpdate();

			if (count > 0) {

				// inserting into LettersDispatch table
				/*objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters
						.setLetterId(CommonCodes.CARDLIMITADJUST_APPLICATION);
				objDispLetters.setApplicationId(objCardLimitDto
						.getApplicationId());
				objDispLetters.setCardNumber(objCardLimitDto.getCardNumber());
				objDispLetters.setIssuerId(objCardLimitDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
				objDispLetters.setLastUpdateDate(objCardLimitDto
						.getUpdatedDate());
				objDispLetters.setLastUpdatedBy(objCardLimitDto.getUserId());

				session.save(objDispLetters);*/

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCardLimitDto.getCardNumber());
				objCardActivity
						.setActivity("CardLimitAdjustment Application Accepted");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objCardLimitDto.getUserId());
				objCardActivity.setReason(objCardLimitDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCardLimitDto.getUserId());
				objCardActivity
						.setUpdatedDate(objCardLimitDto.getUpdatedDate());

				session.save(objCardActivity);
			}

			tx.commit();
			if (count > 0)
				accept = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting the  data in CardReplacementDAOImpl cardLimitAdjustmentAccept"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the cardLimitAdjusmentform in CardReplacementDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO
	 * #reject(org.transinfo.cacis.dto.customerservice.CardLimitAdjustmentDto)
	 * this method is used for rejecting the cardLimitAdjustmentform
	 */
	public boolean cardLimitReject(CardLimitAdjustmentDto objCardLimitDto)
			throws TPlusException {

		boolean reject = false;
		Transaction tx = null;
		int count;
		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// updating in cardLimitAdjsut_forms table
			String sql = "UPDATE CardLimitAdjustmentDto SET applicationStatus =:apprejectstatus WHERE applicationId=:applicationid";
			count = session.createQuery(sql).setString("applicationid",
					objCardLimitDto.getApplicationId()).setInteger(
					"apprejectstatus", CommonCodes.APPLICATIONSTATUS_REJECTED)
					.executeUpdate();

			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto SET applicationStatus =:apprejectstatus , closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objCardLimitDto.getApplicationId()).setInteger(
					"apprejectstatus", CommonCodes.APPLICATIONSTATUS_REJECTED)
					.setDate("closedate", objCardLimitDto.getUpdatedDate())
					.executeUpdate();

			if (count > 0) {

				// inserting into LettersDispatch table
				/*objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters
						.setLetterId(CommonCodes.CARDLIMITADJUST_REJECT_APPLICATION);
				objDispLetters.setApplicationId(objCardLimitDto
						.getApplicationId());
				objDispLetters.setCardNumber(objCardLimitDto.getCardNumber());
				objDispLetters.setIssuerId(objCardLimitDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_PROCESS_NEW);
				objDispLetters.setLastUpdateDate(objCardLimitDto
						.getUpdatedDate());
				objDispLetters.setLastUpdatedBy(objCardLimitDto.getUserId());

				session.save(objDispLetters);*/

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCardLimitDto.getCardNumber());
				objCardActivity
						.setActivity("CardLimitAdjustment Application Rejected");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objCardLimitDto.getUserId());
				objCardActivity.setReason(objCardLimitDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCardLimitDto.getUserId());
				objCardActivity
						.setUpdatedDate(objCardLimitDto.getUpdatedDate());

				session.save(objCardActivity);
			}

			tx.commit();
			System.out.println(" cardLimitAdjustReject  Count=" + count);
			if (count > 0)
				reject = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while rejecting the cardLimitAdjustmentForm in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while rejecting the cardLimitAdjustmentForm in CardReplacementDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return reject;
	}

	/* this for updating Cards table with entered CreditSplit values */
	public boolean creditSplitUpdate(CreditSplitDto objSplit)
			throws TPlusException {

		boolean split = false;
		Transaction tx = null;
		int count = 0;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			Set setLimitForms = objSplit.getLimitFormsList();
			for (Iterator it = setLimitForms.iterator(); it.hasNext();) {
				LimitForm objlimitForm = (LimitForm) it.next();

				if (objlimitForm.getCardNumber() != null
						&& !objlimitForm.getNewLimitRatio().equals("")) {
					String creditSql = "UPDATE CardsDto SET creditLimitPercent =:creditlimitpercent WHERE cardNumber=:cardnumber";
					count = session.createQuery(creditSql).setInteger(
							"creditlimitpercent",
							Integer.valueOf(objlimitForm.getNewLimitRatio())
									.intValue()).setLong(
							"cardnumber",
							Long.valueOf(objlimitForm.getCardNumber())
									.longValue()).executeUpdate();
				}
				if (objlimitForm.getCardNumber() != null
						&& objlimitForm.getNewCashLimitRatio() != null) {
					String cashSql = "UPDATE CardsDto SET cashLimitPercent =:cashlimitpercent WHERE cardNumber=:cardnumber";
					count = session
							.createQuery(cashSql)
							.setInteger(
									"cashlimitpercent",
									Integer
											.valueOf(
													objlimitForm
															.getNewCashLimitRatio())
											.intValue()).setLong(
									"cardnumber",
									Long.valueOf(objlimitForm.getCardNumber())
											.longValue()).executeUpdate();
				}

			}
			tx.commit();
			System.out.println("split Count=" + count);
			if (count > 0)
				split = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while creditSplitUpdate  in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while creditSplitUpdate  in CardReplacementDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return split;
	}

	/*
	 * for showing the Customer History Details
	 */

	public Collection customerHistory(String custIdNumber)
			throws TPlusException {

		Collection historyCollection = new ArrayList();
		Transaction tx = null;
		CommonDataBean objCommBean = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "select ch.applicationId,ch.applicationStatus,ch.cardStatus, ch.id.cardNumber,ch.idNumber,ch.creditLimit,ch.cardType,ch.id.cardProduct,ch.cardProductType,ch.actionDate From CustomerHistoryDto ch where ch.idNumber =:idnumber";
			List resultList = session.createQuery(sql).setString("idnumber",
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
					.println("Error while geting cusomer history  list in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving CustomerHistory in CardReplacementDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return historyCollection;
	}

	/*
	 * for showing the Card Activity Details
	 */

	public Collection cardActivities(long cardNumber) throws TPlusException {

		Collection cardActities = new ArrayList();
		Transaction tx = null;
		CommonDataBean objCommBean = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "select cad.dateTime,cad.activity, cad.stationIp,cad.userId From CardActivityDto cad where cad.cardNumber =:cardnumber";
			List resultList = session.createQuery(sql).setLong("cardnumber",
					cardNumber).list();
			for (Iterator it = resultList.iterator(); it.hasNext();) {
				Object obj[] = (Object[]) it.next();
				objCommBean = new CommonDataBean();
				objCommBean.setColumn1(DateUtil
						.convertDateToDateString((Date) obj[0]));
				objCommBean.setColumn2((String) obj[1]);
				objCommBean.setColumn3((String) obj[2]);
				objCommBean.setColumn4((String) obj[3]);
				cardActities.add(objCommBean);

			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while geting cardActities  list in CardReplacementDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardActities in CardReplacementDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cardActities;
	}

	public int checkExistrecord(Object commonObj) throws TPlusException {

		int res = 0;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();

			if (commonObj instanceof CardReplacementDto) {
				CardReplacementDto objDto = (CardReplacementDto) commonObj;
				sbf
						.append("select count(*) from CardReplacementDto crd where crd.cardNumber="
								+ objDto.getCardNumber()
								+ " and crd.applicationStatus="
								+ objDto.getApplicationStatus() + " ");
			} else if (commonObj instanceof CardCloseDto) {
				CardCloseDto objDto = (CardCloseDto) commonObj;
				sbf
						.append("select count(*) from CardCloseDto ccd   where ccd.cardNumber="
								+ objDto.getCardNumber()
								+ " and ccd.applicationStatus="
								+ objDto.getApplicationStatus() + " ");
			} else if (commonObj instanceof PinResendDto) {
				PinResendDto objDto = (PinResendDto) commonObj;
				sbf
						.append("select count(*) from PinResendDto prd   where prd.cardNumber="
								+ objDto.getCardNumber()
								+ " and prd.applicationStatus="
								+ objDto.getApplicationStatus() + " ");
			} else if (commonObj instanceof CardLimitAdjustmentDto) {
				CardLimitAdjustmentDto objDto = (CardLimitAdjustmentDto) commonObj;
				sbf
						.append("select count(*) from CardLimitAdjustmentDto cla  where cla.cardNumber="
								+ objDto.getCardNumber()
								+ " and cla.applicationStatus="
								+ objDto.getApplicationStatus() + " ");
			} else if (commonObj instanceof CardsDto) {
				CardsDto objDto = (CardsDto) commonObj;
				sbf
						.append("select count(*) from CardsDto cr where cr.cardNumber= "
								+ objDto.getCardNumber() + " ");
			}
			// this method is currently not using
			else if (commonObj instanceof ApplicationMasterDto) {
				ApplicationMasterDto objDto = (ApplicationMasterDto) commonObj;
				sbf
						.append("select count(*) from ApplicationMasterDto mst where mst.idNumber ='"
								+ objDto.getIdNumber() + "' ");
				sbf.append("and mst.applicationType ="
						+ objDto.getApplicationType() + " ");
				sbf.append("and mst.closeDate is  null ");
			}

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After CardReplacmentDAOImpl checkExistrecord()"
					+ res);
		}

		catch (Exception e) {
			System.out
					.println("Error in CardReplacementDAOImpl checkExistrecord method :"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  in CardReplacementDAOImpl checkExistrecord method :"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;
	}

	public static void main(String args[]) throws Exception {

		PinResendDAOImpl objCardRepImpl = new PinResendDAOImpl();
		CardReplacementDto objRepDto = new CardReplacementDto();
		objRepDto.setCardNumber(new Long("1724570000000022").longValue());
		objRepDto.setReasonCode(25);
		//objRepDto.setReplaceReason("Card Damaged");
		//objRepDto.setUserId("ASPSUPERADMIN");
		// objSearchDto.setUpdatedDate(new Date());*/
		// objCardRepImpl.processSearch(objRepDto,0);
		// objSearchDto =objCardRep.getCardReplacementDto("A0605130156");
		// objCardRep.cardstop(objSearchDto);
		// objCardRep.cardactivate(objSearchDto);

		// testing for billing addres change
		ApplicationProcessDto objcustomerDto = new ApplicationProcessDto();
		CustomerAddressDto objAdd = new CustomerAddressDto();
		objAdd.setAddressId(111);
		objAdd.setAddress1("address1");
		objAdd.setAddress2("address2");
		objAdd.setCity("hyd");
		objAdd.setState("AP");
		objAdd.setCountry("india");
		objAdd.setPostalCode(435);
		objAdd.setPhone("43534");
		objAdd.setFax("435456");
		objRepDto.setCustomerAddDto(objAdd);
		objcustomerDto.setCustomerId("C0605133984");
		objAdd.setAppFormProcssDto(objcustomerDto);
		// objCardRepImpl.billingaddchange(objRepDto);
		CardCloseDto objcarCloseDto = new CardCloseDto();
		objcarCloseDto.setIssuerId("Issuer1");
		objcarCloseDto.setCardNumber(Long.parseLong("1724570000000022"));
		objcarCloseDto.setApplicationId("A060513762");
		objcarCloseDto.setUpdatedDate(new Date());
		// objCardRepImpl.cardCloseProcessSearch(objcarCloseDto,0);
		// objCardRepImpl.cardCloseAccept(objcarCloseDto);
		// objCardRepImpl.cardCloseReject(objcarCloseDto);

		// validation check
		ApplicationMasterDto objMstDto = new ApplicationMasterDto();
		objMstDto.setApplicationType(6);
		objMstDto.setIdNumber("111");
		objCardRepImpl.checkExistrecord(objMstDto);

	}

	public CardStatusRemarksDto getCardStatusRemarks(long cardStatusId,
			String cardNo) throws TPlusException {

		CardStatusRemarksDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CardStatusRemarksDto where cardNo='"
							+ cardNo + "' and statusNo=" + cardStatusId);
			List listCard = qry.list();
			if (listCard.size() > 0) {
				objDto = (CardStatusRemarksDto) listCard.get(0);
			}
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting CardStatusRemarksDto data in getCardStatusRemarks method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardStatusRemarksDto data in getCardStatusRemarks method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;

	}

	public CardsDto getCard(String cardNo) throws TPlusException {

		CardsDto objCardsDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardsDto = (CardsDto) session.get(CardsDto.class, Long
					.valueOf(cardNo));

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in PinResendDAOImpl getCard method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in PinResendDAOImpl getCard  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objCardsDto;

	}
	
	public PinResendDto getPinResendApp(String cardNo) throws TPlusException {

		PinResendDto objPinResendDto = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			sbf.append("from PinResendDto prdo ");
			sbf.append("where prdo.cardNumber="+ Long.valueOf(cardNo) + " ");
			sbf.append("and prdo.applicationStatus=0 ");
			
			Query qry = session.createQuery(sbf.toString());
			List listApp = qry.list();
			
			if (listApp.size() > 0) {
				objPinResendDto = (PinResendDto) listApp.get(0);
			}
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting PinResendDto data in getPinResendApp method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting PinResendDto data in getPinResendApp method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objPinResendDto;

	}

}
