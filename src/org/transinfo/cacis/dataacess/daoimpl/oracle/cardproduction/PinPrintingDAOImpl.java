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
import org.transinfo.cacis.dataacess.dao.cardproduction.PinPrintingDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CardDeliverDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingSearchDto;
import org.transinfo.cacis.dto.cardproduction.ResendPinPrintingSearchDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;

@SuppressWarnings("unchecked")
public class PinPrintingDAOImpl extends BaseDAOImpl implements PinPrintingDAO {

	public Collection search(PinPrintingSearchDto objSearchDto, int pageNo)
	throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("Select ");
			sbf.append("cb.pinPringId, cb.cardNumber, ");
			sbf.append("apdto.customerName, ");
			sbf.append("cp.cardProductName, cps.descriptin, ca.maskedCardNo ");
			sbf.append("FROM PinPrintingDto cb, CardProductDto cp, ");
			sbf.append("CardsDto ca, CardProcessStatusDto cps, ");
			sbf.append("ApplicationProcessDto apdto ");
			sbf.append("where ");
			sbf.append("cb.cardNumber = ca.cardNumber ");
			sbf.append("and ca.cardProductId = cp.cardProductId ");
			sbf.append("and cb.status = cps.statusId ");
			sbf.append("and ca.customerId = apdto.customerId ");
			sbf.append("and cb.pinResend is null ");

			if (objSearchDto.getCardNumber() != null && !objSearchDto.getCardNumber().equals("")) {
				//sbf.append("and cb.cardNumber = " + objSearchDto.getCardNumber() + " ");
				sbf.append("and ca.encryptedCardNo = '" + CardEncryption.encrypt(objSearchDto.getCardNumber()) + "' ");
			}

			if (objSearchDto.getStartDate() != null && !objSearchDto.getStartDate().equals("")) {
				sbf.append("and cb.updatedDate >= TO_DATE('" + objSearchDto.getStartDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getEndDate() != null && !objSearchDto.getEndDate().equals("")) {
				sbf.append("and cb.updatedDate <= TO_DATE('" + objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getCardPrintingStatus() == 8) {
				sbf.append("and cb.status = 8 ");
			} else if (objSearchDto.getCardPrintingStatus() == 9) {
				sbf.append("and cb.status = 9 ");
			} else {
				sbf.append("and cb.status = 9 ");
			}

			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and ca.branchId = '" + objSearchDto.getBranchId() + "' ");
			}

			sbf.append("and apdto.issuerId = '" + objSearchDto.getIssuerId() + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out.println("Error:in PinPrintingDAOImpl search method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in PinPrintingDAOImpl search method" + e);
		}

		return objSearchCollection;
	}

	/*
	 * this mehod is for updating the cards and cardembossing tables and
	 * inserting data into cardActvites table
	 */
	public boolean save(PinPrintingSearchDto objSearchDto)
	throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;
		CardActivityDto objCardActivity = null;
		CardDeliverDto objCardDeliver = null;
		//DispatchLetterSearchDto objDispLetters = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String selectCards[] = objSearchDto.getSelectedCards();

			for (int i = 0; i < selectCards.length; i++) {

				String embosql = "UPDATE PinPrintingDto SET status=:status WHERE cardNumber=:cardnumber";
				count = session.createQuery(embosql).setLong("cardnumber",
						Long.parseLong(selectCards[i])).setInteger("status",
								CommonCodes.CARD_PROCESS_CREATED).executeUpdate();

				if (count > 0) {
					String cardsql = "UPDATE CardsDto SET cardStatusId =:status WHERE cardNumber=:cardnumber";
					count = session.createQuery(cardsql).setLong("cardnumber",
							Long.parseLong(selectCards[i])).setInteger(
									"status", CommonCodes.PIN_MADE).executeUpdate();
				}

				// this for card_deliver table

				String sbf = "select cp.issuerId , cp.customerId from PinPrintingDto cp where cp.cardNumber = "
					+ selectCards[i] + " ";
				Query qry = session.createQuery(sbf);
				List result = qry.list();
				for (Iterator it = result.iterator(); it.hasNext();) {
					objCardDeliver = new CardDeliverDto();
					Object obj[] = (Object[]) it.next();
					objCardDeliver.setIssuerId((String) obj[0]);
					objCardDeliver.setCustomerId((String) obj[1]);
					objCardDeliver
					.setCardNumber(Long.parseLong(selectCards[i]));
					objCardDeliver.setStatus(CommonCodes.CARD_PROCESS_NEW);
					objCardDeliver.setLastUpdatedBy(objSearchDto
							.getLastUpdatedBy());
					objCardDeliver
					.setUpdatedDate(objSearchDto.getUpdatedDate());

					session.save(objCardDeliver);

					// inserting into LettersDispatch table
					/*objDispLetters = new DispatchLetterSearchDto();
					objDispLetters
							.setDispatchId(IdsGenartion.GenLetterDispId());
					objDispLetters.setLetterId(CommonCodes.NEWCARD_APPLICATION);
					objDispLetters.setIssuerId((String) obj[0]);
					// objDispLetters.setIssuerId(objSearchDto.getIssuerId());
					objDispLetters
							.setCardNumber(Long.parseLong(selectCards[i]));
					objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
					objDispLetters.setLastUpdateDate(objSearchDto
							.getUpdatedDate());
					objDispLetters.setLastUpdatedBy(objSearchDto
							.getLastUpdatedBy());

					session.save(objDispLetters);*/
				}

				// this for card Acivities table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(Long.parseLong(selectCards[i]));
				objCardActivity.setActivity("Pin is Printed");
				objCardActivity.setStationIp(java.net.InetAddress
						.getLocalHost().getHostAddress());
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
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while save method in PinPrintingDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public static void main(String args[]) throws Exception {

		PinPrintingDAOImpl objCard = new PinPrintingDAOImpl();
		PinPrintingSearchDto objSearch = new PinPrintingSearchDto();
		String selectCards[] = { "1724570000000010", "4563270000000020" };
		// objSearch.setCardEmbossingStaus(1);
		// objSearch.setFromCardNumber("132");
		// objSearch.setToCardNumber("1.72457000000001E15");
		objSearch.setSelectedCards(selectCards);
		// objCard.search(objSearch);
		objCard.save(objSearch);
	}

	public boolean updateObjects(String pinPrinSerialNo, String userId)
	throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;

		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		CardDeliverDto objCardDeliverDto = null;
		PinPrintingDto objPinPrintingDto = null;
		CardsDto objCardsDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objPinPrintingDto = (PinPrintingDto) session.get(
					PinPrintingDto.class, pinPrinSerialNo);
			objCardsDto = (CardsDto) session.get(CardsDto.class,
					objPinPrintingDto.getCardNumber());

			long cardNo = objCardsDto.getCardNumber();

			String embosql = "UPDATE PinPrintingDto SET status=:status WHERE pinPringId=:pinPringId";
			count = session.createQuery(embosql).setString("pinPringId",
					pinPrinSerialNo).setInteger("status",
							CommonCodes.CARD_PROCESS_PINMAILED).executeUpdate();

			if (count > 0) {
				String cardsql = "UPDATE CardsDto SET cardStatusId =:status WHERE cardNumber=:cardnumber";
				count = session.createQuery(cardsql).setLong("cardnumber",
						cardNo).setInteger("status", CommonCodes.PIN_MADE)
						.executeUpdate();
			}

			// inserting into Card_Deliver table
			objCardDeliverDto = new CardDeliverDto();
			objCardDeliverDto.setCardNumber(cardNo);
			objCardDeliverDto.setIssuerId(objCardsDto.getIssuerId());
			objCardDeliverDto.setCustomerId(objCardsDto.getCustomerId());
			objCardDeliverDto.setLastUpdatedBy(userId);
			objCardDeliverDto.setUpdatedDate(new Date());
			objCardDeliverDto.setStatus(CommonCodes.CARD_PROCESS_NOT_DELIVERED);
			session.save(objCardDeliverDto);

			// inserting into LettersDispatch table
			/*objDispLetters = new DispatchLetterSearchDto();
			objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
			objDispLetters.setLetterId(CommonCodes.NEWCARD_APPLICATION);
			objDispLetters.setIssuerId(objCardsDto.getIssuerId());
			objDispLetters.setCardNumber(cardNo);
			objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
			objDispLetters.setLastUpdateDate(new Date());
			objDispLetters.setLastUpdatedBy(userId);
			session.save(objDispLetters);*/

			// this for CardActivties table
			objCardActivity = new CardActivityDto();
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(cardNo);
			objCardActivity.setActivity("Card PIN printed");
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
			System.out.println("in PinPrintingDAOImpl updateObjects method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in PinPrintingDAOImpl updateObjects method" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public PinPrintingDto getPinPrint(String cardNo) throws TPlusException {

		PinPrintingDto objPinPrintingDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objPinPrintingDto = (PinPrintingDto) session.get(
					PinPrintingDto.class, Long.valueOf(cardNo));

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in PinPrintingDAOImpl getPinPrint method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in PinPrintingDAOImpl getPinPrint  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objPinPrintingDto;

	}

	public CardsDto getCard(String pinPrintSerialNo) throws TPlusException {

		CardsDto objCardsDto = null;
		PinPrintingDto objPinPrintingDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objPinPrintingDto = (PinPrintingDto) session.get(
					PinPrintingDto.class, pinPrintSerialNo);
			objCardsDto = (CardsDto) session.get(CardsDto.class,
					objPinPrintingDto.getCardNumber());

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in CardEmbossingDAOImpl getCard method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardEmbossingDAOImpl getCard  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objCardsDto;

	}

	public boolean isEmbossed(String cardNo) throws TPlusException {

		boolean isEmbossed = false;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("FROM PinPrintingDto ppdo ");
			sbf.append("where ppdo.cardNumber = " + cardNo + " ");

			Query qry = session.createQuery(sbf.toString());
			List listDocs = qry.list();

			if (listDocs.size() > 0) {
				isEmbossed = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getKeyIndexForm method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getKeyIndexForm" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return isEmbossed;
	}

	public PinPrintingDto getPinPrintNotProcessed(String cardNo)
	throws TPlusException {

		PinPrintingDto objPinPrintingDto = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("FROM PinPrintingDto ppdo ");
			sbf.append("where ppdo.cardNumber = " + cardNo + " ");
			sbf.append("and ppdo.pinResend = 'N' ");

			Query qry = session.createQuery(sbf.toString());
			List listDocs = qry.list();

			if (listDocs.size() > 0) {
				objPinPrintingDto = (PinPrintingDto) listDocs.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error in PinPrintingDAOImpl getPinPrint method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in PinPrintingDAOImpl getPinPrint  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objPinPrintingDto;

	}

	public Collection searchResendList(ResendPinPrintingSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("Select ");
			sbf.append("cb.pinPringId, cb.cardNumber, ");
			sbf.append("apdto.customerName, ");
			sbf.append("cp.cardProductName, cps.descriptin, ca.maskedCardNo ");
			sbf.append("FROM PinPrintingDto cb, CardProductDto cp, ");
			sbf.append("CardsDto ca, CardProcessStatusDto cps, ");
			sbf.append("ApplicationProcessDto apdto ");
			sbf.append("where ");
			sbf.append("cb.cardNumber = ca.cardNumber ");
			sbf.append("and ca.cardProductId = cp.cardProductId ");
			sbf.append("and cb.status = cps.statusId ");
			sbf.append("and ca.customerId = apdto.customerId ");

			if (objSearchDto.getCardNumber() != null && !objSearchDto.getCardNumber().equals("")) {
				//sbf.append("and cb.cardNumber = " + objSearchDto.getCardNumber() + " ");
				sbf.append("and ca.encryptedCardNo = '" + CardEncryption.encrypt(objSearchDto.getCardNumber()) + "' ");
			}

			if (objSearchDto.getStartDate() != null && !objSearchDto.getStartDate().equals("")) {
				sbf.append("and cb.updatedDate >= TO_DATE('" + objSearchDto.getStartDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getEndDate() != null && !objSearchDto.getEndDate().equals("")) {
				sbf.append("and cb.updatedDate <= TO_DATE('" + objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getCardPrintingStatus() == 8) {
				sbf.append("and cb.pinResend = 'Y' ");
				sbf.append("and cb.status = 8 ");
			} else if (objSearchDto.getCardPrintingStatus() == 9) {
				sbf.append("and cb.pinResend = 'N' ");
				sbf.append("and cb.status = 9 ");
			} else {
				sbf.append("and cb.pinResend = 'N' ");
				sbf.append("and cb.status = 9 ");
			}

			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and ca.branchId = '" + objSearchDto.getBranchId() + "' ");
			}

			sbf.append("and apdto.issuerId = '" + objSearchDto.getIssuerId() + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
			.println("Error:in PinPrintingDAOImpl searchResendList method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in PinPrintingDAOImpl searchResendList method" + e);
		}

		return objSearchCollection;
	}

	public boolean updateResendObjects(String pinPrinSerialNo, String userId)
	throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;

		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		PinPrintingDto objPinPrintingDto = null;
		CardsDto objCardsDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objPinPrintingDto = (PinPrintingDto) session.get(
					PinPrintingDto.class, pinPrinSerialNo);
			objCardsDto = (CardsDto) session.get(CardsDto.class,
					objPinPrintingDto.getCardNumber());

			int reSendCount = objPinPrintingDto.getResendCount() + 1;

			long cardNo = objCardsDto.getCardNumber();

			String embosql = "UPDATE PinPrintingDto SET pinResend=:status, resendCount=:count, status=:pstatus WHERE pinPringId=:pinPringId";
			count = session.createQuery(embosql)
			.setString("pinPringId", pinPrinSerialNo)
			.setInteger("pstatus", CommonCodes.CARD_PROCESS_PINMAILED)
			.setInteger("count", reSendCount)
			.setCharacter("status", CommonCodes.CARD_PROCESS_RESEND_PINMAILED)
			.executeUpdate();

			// inserting into LettersDispatch table
			/*objDispLetters = new DispatchLetterSearchDto();
			objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
			objDispLetters.setLetterId(CommonCodes.NEWCARD_APPLICATION);
			objDispLetters.setIssuerId(objCardsDto.getIssuerId());
			objDispLetters.setCardNumber(cardNo);
			objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
			objDispLetters.setLastUpdateDate(new Date());
			objDispLetters.setLastUpdatedBy(userId);
			session.save(objDispLetters);*/

			// this for CardActivties table
			objCardActivity = new CardActivityDto();
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(cardNo);
			objCardActivity.setActivity("Card Resend PIN printed");
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
			.println("in PinPrintingDAOImpl updateResendObjects method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in PinPrintingDAOImpl updateResendObjects method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public boolean updateCARDSandCARDDATA(CardsDto objCardsDto)
	throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int cardUpdate = 0;
		int carddataUpdate = 0;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			long cardNo = objCardsDto.getCardNumber();

			String cardsql = "UPDATE CardsDto SET pvvOffSet=:newpvvOffSet, pinBlock=:newpinBlock, wrongPinCount=:pincount, pinDisabled=:pindisabled  WHERE cardNumber=:cardNo";
			cardUpdate = session.createQuery(cardsql)
			.setLong("cardNo", cardNo)
			.setString("newpinBlock", objCardsDto.getPinBlock())
			.setLong("newpvvOffSet", objCardsDto.getPvvOffSet())
			.setInteger("pincount", 0)
			.setString("pindisabled", "Y")
			.executeUpdate();

			String carddataql = "UPDATE CardDataDto SET pvvOffSet=:newpvvOffSet WHERE closingDate is null and cardNumber=:cardNo";
			carddataUpdate = session.createQuery(carddataql)
			.setLong("cardNo", cardNo)
			.setLong("newpvvOffSet", objCardsDto.getPvvOffSet())
			.executeUpdate();

			tx.commit();

			if (cardUpdate > 0 && carddataUpdate > 0){
				bolExecute = true;
			}else{
				if (tx != null) {
					tx.rollback();
				}
			}

		}

		catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("in PinPrintingDAOImpl updateResendObjects method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in PinPrintingDAOImpl updateResendObjects method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

}
