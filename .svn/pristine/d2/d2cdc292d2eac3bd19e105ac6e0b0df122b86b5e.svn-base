package org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CardEncryption;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardEmbossingDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardDeliverDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;

@SuppressWarnings( { "unchecked" })
public class CardEmbossingDAOImpl extends BaseDAOImpl implements
		CardEmbossingDAO {

	public Collection search(CardEmbossingSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {
			sbf.append("Select ");
			sbf.append("cb.cardEmbossId, cb.card.cardNumber, ");
			sbf.append("apdto.customerName, ");
			sbf.append("cp.cardProductName, cps.descriptin, cb.card.maskedCardNo ");
			sbf.append("FROM CardEmbossingDto cb, CardProductDto cp, ");
			sbf.append("CardsDto ca, CardProcessStatusDto cps, ");
			sbf.append("ApplicationProcessDto apdto ");
			sbf.append("where ");
			sbf.append("cb.card.cardNumber = ca.cardNumber and ");
			sbf.append("ca.cardProductId = cp.cardProductId and ");
			sbf.append("cb.status = cps.statusId and ");
			sbf.append("ca.customerId = apdto.customerId and ");
			sbf.append("cb.newCardFor is null ");
			
			if (objSearchDto.getCardproduct() != null && !objSearchDto.getCardproduct().equals("")) {
				sbf.append("and ca.cardProductId = '" + objSearchDto.getCardproduct() + "' ");
			}

			if (objSearchDto.getCardNumber() != null && !objSearchDto.getCardNumber().equals("")) {
				sbf.append("and cb.card.encryptedCardNo = '" + CardEncryption.encrypt(objSearchDto.getCardNumber()) + "' ");
			}

			if (objSearchDto.getStartDate() != null && !objSearchDto.getStartDate().equals("")) {
				sbf.append("and cb.updatedDate >= TO_DATE('" + objSearchDto.getStartDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getEndDate() != null && !objSearchDto.getEndDate().equals("")) {
				sbf.append("and cb.updatedDate <= TO_DATE('" + objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getCardEmbossingStaus() == 6) {
				sbf.append("and cb.status = 6 ");
			} else if (objSearchDto.getCardEmbossingStaus() == 7) {
				sbf.append("and cb.status = 7 ");
			} else {
				sbf.append("and cb.status = 7 ");
			}

			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and ca.branchId = '" + objSearchDto.getBranchId() + "' ");
			}
			
			sbf.append("and apdto.issuerId = '" + objSearchDto.getIssuerId() + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out.println("Error in CardEmbossingDAOImpl search method"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl search method" + e);
		}

		return objSearchCollection;
	}

	/*
	 * this mehod is for updating the cards and cardembossing tables and
	 * inserting data into cardActvites table
	 */
	public boolean save(CardEmbossingSearchDto objSearchDto)
			throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;

		CardActivityDto objCardActivity = null;
		PinPrintingDto objPinPrinting = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String selectCards[] = objSearchDto.getSelectedCards();

			for (int i = 0; i < selectCards.length; i++) {

				String embosql = "UPDATE CardEmbossingDto SET status=:status WHERE cardNumber=:cardnumber";
				count = session.createQuery(embosql).setLong("cardnumber",
						Long.parseLong(selectCards[i])).setInteger("status",
						CommonCodes.CARD_PROCESS_CREATED).executeUpdate();

				if (count > 0) {
					String cardsql = "UPDATE CardsDto SET cardStatusId =:status WHERE cardNumber=:cardnumber";
					count = session.createQuery(cardsql).setLong("cardnumber",
							Long.parseLong(selectCards[i])).setInteger(
							"status", CommonCodes.CARD_DATAEXPORT)
							.executeUpdate();
				}

				// the card_printing table

				String sbf = "select cb.issuerId , cb.customerId from CardEmbossingDto cb where cb.cardNumber = "
						+ selectCards[i] + " ";
				Query qry = session.createQuery(sbf);
				List result = qry.list();
				for (Iterator it = result.iterator(); it.hasNext();) {
					objPinPrinting = new PinPrintingDto();
					Object obj[] = (Object[]) it.next();
					objPinPrinting.setIssuerId((String) obj[0]);
					objPinPrinting.setCustomerId((String) obj[1]);

					objPinPrinting
							.setCardNumber(Long.parseLong(selectCards[i]));
					objPinPrinting
							.setStatus(CommonCodes.CARD_PROCESS_NOT_PINMAILED);
					objPinPrinting.setLastUpdatedBy(objSearchDto
							.getLastUpdatedBy());
					objPinPrinting
							.setUpdatedDate(objSearchDto.getUpdatedDate());
					session.save(objPinPrinting);
				}
				// this for CardActivties table
				objCardActivity = new CardActivityDto();
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(Long.parseLong(selectCards[i]));
				objCardActivity.setActivity("CardCreated");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objSearchDto.getLastUpdatedBy());
				objCardActivity.setReason(" ");
				objCardActivity.setLastUpdatedBy(objSearchDto
						.getLastUpdatedBy());
				objCardActivity.setUpdatedDate(objSearchDto.getUpdatedDate());

				session.save(objCardActivity);

			}

			tx.commit();
			System.out.println("the count is" + count);
			if (count > 0)
				bolExecute = true;

		}

		catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			System.out.println("in CardEmbossingDAOImpl save method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl save method" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public static void main(String args[]) throws Exception {

		CardEmbossingDAOImpl objCard = new CardEmbossingDAOImpl();

		CardsDto objCardsDto = new CardsDto();
		objCardsDto.setCardNumber(Long.valueOf("6221590000000109"));

		ApplicationProcessDto objApplicationProcessDto = new ApplicationProcessDto();
		objApplicationProcessDto.setCustomerId("C0908216890");

		// inserting into cardsEmbossing table
		CardEmbossingDto objCardEb = new CardEmbossingDto();
		objCardEb.setCard(objCardsDto);
		objCardEb.setCustomer(objApplicationProcessDto);
		objCardEb.setIssuerId("Issuer1");
		objCardEb.setTrack1("track1");
		objCardEb.setTrack2("track1");
		objCardEb.setStatus(CommonCodes.CARD_PROCESS_NEW);
		objCardEb.setLastUpdatedBy(objApplicationProcessDto.getUserId());
		objCardEb.setUpdatedDate(new Date());

		objCard.add(objCardEb);

	}

	public boolean add(CardEmbossingDto objCardEmbossingDto)
			throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objCardEmbossingDto);

			tx.commit();
			boolAdd = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CardTypeDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardTypeDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	public CardsDto getCard(String embossSerialNo) throws TPlusException {

		CardsDto objCardsDto = null;
		CardEmbossingDto objCardEmbossingDto = null;
		//Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();

			objCardEmbossingDto = (CardEmbossingDto) session.get(CardEmbossingDto.class, embossSerialNo);
			objCardsDto = objCardEmbossingDto.getCard();
			objCardsDto.getCustomerId();
			objCardsDto.getCardProductsDto();
			//tx.commit();

		} catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
			System.out.println("Error in CardEmbossingDAOImpl getCard method : " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: in CardEmbossingDAOImpl getCard  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objCardsDto;

	}

	public CardEmbossingDto getCardEmboss(String embossSerialNo)
			throws TPlusException {

		CardEmbossingDto objCardEmbossingDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardEmbossingDto = (CardEmbossingDto) session.get(
					CardEmbossingDto.class, embossSerialNo);

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error in CardEmbossingDAOImpl getCardEmboss method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl getCardEmboss  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objCardEmbossingDto;

	}

	public boolean updateObjects(String embossSerialNo, String userId,
			Date embossDate) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;

		CardActivityDto objCardActivity = null;
		PinPrintingDto objPinPrinting = null;
		CardDeliverDto objCardDeliverDto = null;
		CardEmbossingDto objCardEmbossingDto = null;
		CardsDto objCardsDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardEmbossingDto = (CardEmbossingDto) session.get(
					CardEmbossingDto.class, embossSerialNo);
			objCardsDto = objCardEmbossingDto.getCard();

			long cardNo = objCardsDto.getCardNumber();

			String embosql = "UPDATE CardEmbossingDto SET status=:status, updatedDate=:updatedDate WHERE cardEmbossId=:cardEmbossId";
			count = session.createQuery(embosql).setString("cardEmbossId",
					embossSerialNo).setTimestamp("updatedDate", embossDate)
					.setInteger("status", CommonCodes.CARD_PROCESS_EMBOSSED)
					.executeUpdate();

			if (count > 0) {
				String cardsql = "UPDATE CardsDto SET cardStatusId =:status WHERE cardNumber=:cardnumber";
				count = session.createQuery(cardsql).setLong("cardnumber",
						cardNo).setInteger("status",
						CommonCodes.CARD_DATAEXPORT).executeUpdate();
			}

			// check the PIN mailer required or not
			// then insert into PIN_PRINTING table if required. Otherwise into
			// CARD_DELIVER table
			String pinRequired = objCardsDto.getCardProductsDto()
					.getPinRequired();
			String pinmailerRequired = objCardsDto.getCardProductsDto()
					.getPinMailerRequired();
			if ((pinRequired != null && "Y".equals(pinRequired))
					&& (pinmailerRequired != null && "Y"
							.equals(pinmailerRequired))) {
				// insert into PIN_PRINTING table
				objPinPrinting = new PinPrintingDto();
				objPinPrinting.setCardNumber(cardNo);
				objPinPrinting.setIssuerId(objCardsDto.getIssuerId());
				objPinPrinting.setCustomerId(objCardsDto.getCustomerId());
				objPinPrinting
						.setStatus(CommonCodes.CARD_PROCESS_NOT_PINMAILED);
				objPinPrinting.setLastUpdatedBy(userId);
				objPinPrinting.setUpdatedDate(new Date());
				objPinPrinting.setPinResend(null);
				objPinPrinting.setResendCount(0);
				session.save(objPinPrinting);
			} else {
				// insert into CARD_DELIVER table
				objCardDeliverDto = new CardDeliverDto();
				objCardDeliverDto.setCardNumber(cardNo);
				objCardDeliverDto.setIssuerId(objCardsDto.getIssuerId());
				objCardDeliverDto.setCustomerId(objCardsDto.getCustomerId());
				objCardDeliverDto.setLastUpdatedBy(userId);
				objCardDeliverDto.setUpdatedDate(new Date());
				objCardDeliverDto
						.setStatus(CommonCodes.CARD_PROCESS_NOT_DELIVERED);
				session.save(objCardDeliverDto);
			}

			// this for CardActivties table
			objCardActivity = new CardActivityDto();
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(cardNo);
			objCardActivity.setActivity("CardCreated");
			objCardActivity.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());
			objCardActivity.setUserId(userId);
			objCardActivity.setReason(" ");
			objCardActivity.setLastUpdatedBy(userId);
			objCardActivity.setUpdatedDate(new Date());

			session.save(objCardActivity);

			tx.commit();
			System.out.println("the count is" + count);
			if (count > 0)
				bolExecute = true;

		}

		catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			System.out.println("in CardEmbossingDAOImpl updateObjects method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl updateObjects method" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public ApplicationProcessDto getCustomerByCusId(String custId)
			throws TPlusException {

		ApplicationProcessDto objApplicationProcessDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objApplicationProcessDto = (ApplicationProcessDto) session.get(
					ApplicationProcessDto.class, custId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error in CardEmbossingDAOImpl getCustomerByCusId method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl getCustomerByCusId  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objApplicationProcessDto;

	}

	public Collection searchReplacement(CardEmbossingSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {
			sbf.append("Select ");
			sbf.append("cb.cardEmbossId, cb.card.cardNumber, ");
			sbf.append("apdto.customerName, ");
			sbf.append("cp.cardProductName, cps.descriptin, cb.card.maskedCardNo ");
			sbf.append("FROM CardEmbossingDto cb, CardProductDto cp, ");
			sbf.append("CardsDto ca, CardProcessStatusDto cps, ");
			sbf.append("ApplicationProcessDto apdto ");
			sbf.append("where ");
			sbf.append("cb.card.cardNumber = ca.cardNumber and ");
			sbf.append("ca.cardProductId = cp.cardProductId and ");
			sbf.append("cb.status = cps.statusId and ");
			sbf.append("ca.customerId = apdto.customerId and ");
			sbf.append("cb.newCardFor = '"
					+ CommonCodes.NEW_CARD_FOR_REPLACEMENT + "' ");
			
			if (objSearchDto.getCardproduct() != null && !objSearchDto.getCardproduct().equals("")) {
				sbf.append("and ca.cardProductId = '" + objSearchDto.getCardproduct() + "' ");
			}

			if (objSearchDto.getCardNumber() != null && !objSearchDto.getCardNumber().equals("")) {
				//sbf.append("and cb.card.cardNumber = " + objSearchDto.getCardNumber() + " ");
				sbf.append("and cb.card.encryptedCardNo = '" + CardEncryption.encrypt(objSearchDto.getCardNumber()) + "' ");
			}

			if (objSearchDto.getStartDate() != null && !objSearchDto.getStartDate().equals("")) {
				sbf.append("and cb.updatedDate >= TO_DATE('" + objSearchDto.getStartDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getEndDate() != null && !objSearchDto.getEndDate().equals("")) {
				sbf.append("and cb.updatedDate <= TO_DATE('" + objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getCardEmbossingStaus() == 6) {
				sbf.append("and cb.status = 6 ");
			} else if (objSearchDto.getCardEmbossingStaus() == 7) {
				sbf.append("and cb.status = 7 ");
			} else {
				sbf.append("and cb.status = 7 ");
			}

			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and ca.branchId = '" + objSearchDto.getBranchId() + "' ");
			}
			
			sbf.append("and apdto.issuerId = '" + objSearchDto.getIssuerId() + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error in CardEmbossingDAOImpl searchReplacement method"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl searchReplacement method"
							+ e);
		}

		return objSearchCollection;
	}

	public boolean updateObjectsReplacement(String embossSerialNo,
			String userId, Date embossDate) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;

		CardActivityDto objCardActivity = null;
		PinPrintingDto objPinPrinting = null;
		CardDeliverDto objCardDeliverDto = null;
		CardEmbossingDto objCardEmbossingDto = null;
		CardsDto objCardsDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardEmbossingDto = (CardEmbossingDto) session.get(
					CardEmbossingDto.class, embossSerialNo);
			objCardsDto = objCardEmbossingDto.getCard();

			long cardNo = objCardsDto.getCardNumber();

			String embosql = "UPDATE CardEmbossingDto SET status=:status, updatedDate=:updatedDate WHERE cardEmbossId=:cardEmbossId";
			count = session.createQuery(embosql).setString("cardEmbossId",
					embossSerialNo).setDate("updatedDate", embossDate)
					.setInteger("status", CommonCodes.CARD_PROCESS_EMBOSSED)
					.executeUpdate();

			if (count > 0) {
				String cardsql = "UPDATE CardsDto SET cardStatusId =:status WHERE cardNumber=:cardnumber";
				count = session.createQuery(cardsql).setLong("cardnumber",
						cardNo).setInteger("status",
						CommonCodes.CARD_DATAEXPORT).executeUpdate();
			}

			// check the PIN mailer required or not
			// then insert into PIN_PRINTING table if required. Otherwise into
			// CARD_DELIVER table
			String newOrSameCard = objCardsDto.getCardProductsDto()
					.getNewOrSameCardnumberForReplacement();
			String pinRequired = objCardsDto.getCardProductsDto()
					.getPinRequired();
			String pinmailerRequired = objCardsDto.getCardProductsDto()
					.getPinMailerRequired();
			if ((pinRequired != null && "Y".equals(pinRequired))
					&& (pinmailerRequired != null && "Y"
							.equals(pinmailerRequired))
					&& (newOrSameCard != null && "N".equals(newOrSameCard))) {
				// insert into PIN_PRINTING table
				objPinPrinting = new PinPrintingDto();
				objPinPrinting.setCardNumber(cardNo);
				objPinPrinting.setIssuerId(objCardsDto.getIssuerId());
				objPinPrinting.setCustomerId(objCardsDto.getCustomerId());
				objPinPrinting
						.setStatus(CommonCodes.CARD_PROCESS_NOT_PINMAILED);
				objPinPrinting.setLastUpdatedBy(userId);
				objPinPrinting.setUpdatedDate(new Date());
				objPinPrinting.setPinResend(null);
				objPinPrinting.setResendCount(0);
				session.save(objPinPrinting);
			} else {
				// insert into CARD_DELIVER table
				objCardDeliverDto = new CardDeliverDto();
				objCardDeliverDto.setCardNumber(cardNo);
				objCardDeliverDto.setIssuerId(objCardsDto.getIssuerId());
				objCardDeliverDto.setCustomerId(objCardsDto.getCustomerId());
				objCardDeliverDto.setLastUpdatedBy(userId);
				objCardDeliverDto.setUpdatedDate(new Date());
				objCardDeliverDto
						.setStatus(CommonCodes.CARD_PROCESS_NOT_DELIVERED);
				session.save(objCardDeliverDto);
			}

			// this for CardActivties table
			objCardActivity = new CardActivityDto();
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(cardNo);
			objCardActivity.setActivity("ReplacementCardCreated");
			objCardActivity.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());
			objCardActivity.setUserId(userId);
			objCardActivity.setReason(" ");
			objCardActivity.setLastUpdatedBy(userId);
			objCardActivity.setUpdatedDate(new Date());

			session.save(objCardActivity);

			tx.commit();
			System.out.println("the count is" + count);
			if (count > 0)
				bolExecute = true;

		}

		catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("in CardEmbossingDAOImpl updateObjectsReplacement method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl updateObjectsReplacement method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public Collection searchRenewal(CardEmbossingSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("Select ");
			sbf.append("cb.cardEmbossId, cb.card.cardNumber, ");
			sbf.append("apdto.customerName, ");
			sbf.append("cp.cardProductName, cps.descriptin, cb.card.maskedCardNo ");
			sbf.append("FROM CardEmbossingDto cb, CardProductDto cp, ");
			sbf.append("CardsDto ca, CardProcessStatusDto cps, ");
			sbf.append("ApplicationProcessDto apdto ");
			sbf.append("where ");
			sbf.append("cb.card.cardNumber = ca.cardNumber and ");
			sbf.append("ca.cardProductId = cp.cardProductId and ");
			sbf.append("cb.status = cps.statusId and ");
			sbf.append("ca.customerId = apdto.customerId and ");
			sbf.append("cb.newCardFor = '" + CommonCodes.NEW_CARD_FOR_RENEWAL
					+ "' ");
			
			if (objSearchDto.getCardproduct() != null && !objSearchDto.getCardproduct().equals("")) {
				sbf.append("and ca.cardProductId = '" + objSearchDto.getCardproduct() + "' ");
			}

			if (objSearchDto.getCardNumber() != null && !objSearchDto.getCardNumber().equals("")) {
				//sbf.append("and cb.card.cardNumber = " + objSearchDto.getCardNumber() + " ");
				sbf.append("and cb.card.encryptedCardNo = '" + CardEncryption.encrypt(objSearchDto.getCardNumber()) + "' ");
			}

			if (objSearchDto.getStartDate() != null && !objSearchDto.getStartDate().equals("")) {
				sbf.append("and cb.updatedDate >= TO_DATE('" + objSearchDto.getStartDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getEndDate() != null && !objSearchDto.getEndDate().equals("")) {
				sbf.append("and cb.updatedDate <= TO_DATE('" + objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getCardEmbossingStaus() == 6) {
				sbf.append("and cb.status = 6 ");
			} else if (objSearchDto.getCardEmbossingStaus() == 7) {
				sbf.append("and cb.status = 7 ");
			} else {
				sbf.append("and cb.status = 7 ");
			}

			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and ca.branchId = '" + objSearchDto.getBranchId() + "' ");
			}
			
			sbf.append("and apdto.issuerId = '" + objSearchDto.getIssuerId() + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error in CardEmbossingDAOImpl searchReplacement method"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl searchReplacement method"
							+ e);
		}

		return objSearchCollection;
	}

	public boolean updateObjectsRenewal(String embossSerialNo, String userId,
			Date embossDate) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;

		CardActivityDto objCardActivity = null;
		CardDeliverDto objCardDeliverDto = null;
		CardEmbossingDto objCardEmbossingDto = null;
		CardsDto objCardsDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardEmbossingDto = (CardEmbossingDto) session.get(
					CardEmbossingDto.class, embossSerialNo);
			objCardsDto = objCardEmbossingDto.getCard();

			long cardNo = objCardsDto.getCardNumber();

			String embosql = "UPDATE CardEmbossingDto SET status=:status, updatedDate=:updatedDate WHERE cardEmbossId=:cardEmbossId";
			count = session.createQuery(embosql).setString("cardEmbossId",
					embossSerialNo).setDate("updatedDate", embossDate)
					.setInteger("status", CommonCodes.CARD_PROCESS_EMBOSSED)
					.executeUpdate();

			// insert into CARD_DELIVER table
			objCardDeliverDto = new CardDeliverDto();
			objCardDeliverDto.setCardNumber(cardNo);
			objCardDeliverDto.setIssuerId(objCardsDto.getIssuerId());
			objCardDeliverDto.setCustomerId(objCardsDto.getCustomerId());
			objCardDeliverDto.setLastUpdatedBy(userId);
			objCardDeliverDto.setUpdatedDate(new Date());
			objCardDeliverDto.setStatus(CommonCodes.CARD_PROCESS_NOT_DELIVERED);
			session.save(objCardDeliverDto);

			// this for CardActivties table
			objCardActivity = new CardActivityDto();
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(cardNo);
			objCardActivity.setActivity("RenewalCardCreated");
			objCardActivity.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());
			objCardActivity.setUserId(userId);
			objCardActivity.setReason(" ");
			objCardActivity.setLastUpdatedBy(userId);
			objCardActivity.setUpdatedDate(new Date());
			session.save(objCardActivity);

			if (count > 0) {
				tx.commit();
				bolExecute = true;
			}

		}

		catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("in CardEmbossingDAOImpl updateObjectsRenewal method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl updateObjectsRenewal method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

}
