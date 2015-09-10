package org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CardEncryption;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDeliverDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CardDeliverDto;
import org.transinfo.cacis.dto.cardproduction.CardDeliverSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;

@SuppressWarnings("unchecked")
public class CardDeliverDAOImpl extends BaseDAOImpl implements CardDeliverDAO {
	
	private Logger logger = Logger.getLogger(CardDeliverDAOImpl.class);

	public Collection search(CardDeliverSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("Select ");
			sbf.append("cd.cardDeliverId, cd.cardNumber, ");
			sbf.append("apdto.customerName, ");
			sbf.append("cp.cardProductName, cps.descriptin, ca.maskedCardNo ");
			sbf.append("FROM CardDeliverDto cd, CardProductDto cp, ");
			sbf.append("CardsDto ca, CardProcessStatusDto cps, ");
			sbf.append("ApplicationProcessDto apdto ");
			sbf.append("where ");
			sbf.append("cd.cardNumber = ca.cardNumber and ");
			sbf.append("ca.cardProductId = cp.cardProductId and ");
			sbf.append("cd.status = cps.statusId and ");
			sbf.append("ca.customerId = apdto.customerId ");

			if (objSearchDto.getCardNumber() != null && !objSearchDto.getCardNumber().equals("")) {
				//sbf.append("and cd.cardNumber = " + objSearchDto.getCardNumber() + " ");
				sbf.append("and ca.encryptedCardNo = '" + CardEncryption.encrypt(objSearchDto.getCardNumber()) + "' ");
			}

			if (objSearchDto.getStartDate() != null && !objSearchDto.getStartDate().equals("")) {
				sbf.append("and cd.updatedDate >= TO_DATE('" + objSearchDto.getStartDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getEndDate() != null && !objSearchDto.getEndDate().equals("")) {
				sbf.append("and cd.updatedDate <= TO_DATE('" + objSearchDto.getEndDate() + "', 'dd/MM/yyyy') ");
			}

			if (objSearchDto.getCardDeliverStatus() == 10) {
				sbf.append("and cd.status = 10 ");
			} else if (objSearchDto.getCardDeliverStatus() == 11) {
				sbf.append("and cd.status = 11 ");
			} else {
				sbf.append("and cd.status = 11 ");
			}

			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and ca.branchId = '" + objSearchDto.getBranchId() + "' ");
			}
			
			sbf.append("and apdto.issuerId = '" + objSearchDto.getIssuerId() + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out.println("Error in CardDeliverDAOImpl search method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: Error in CardDeliverDAOImpl search method" + e);
		} finally {
		}
		return objSearchCollection;
	}

	/*
	 * this mehod is for updating the cards and cardembossing tables and
	 * inserting data into cardActvites table
	 */
	public boolean save(CardDeliverSearchDto objSearchDto)
			throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;
		CardActivityDto objCardActivity = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String selectCards[] = objSearchDto.getSelectedCards();

			for (int i = 0; i < selectCards.length; i++) {

				String embosql = "UPDATE CardDeliverDto SET status=:status WHERE cardNumber=:cardnumber";
				count = session.createQuery(embosql).setLong("cardnumber",
						Long.parseLong(selectCards[i])).setInteger("status",
						CommonCodes.CARD_PROCESS_CREATED).executeUpdate();

				if (count > 0) {
					String cardsql = "UPDATE CardsDto SET cardStatusId =:status WHERE cardNumber=:cardnumber";
					count = session.createQuery(cardsql).setLong("cardnumber",
							Long.parseLong(selectCards[i])).setInteger(
							"status", CommonCodes.CARD_DELIVER).executeUpdate();
				}
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(Long.parseLong(selectCards[i]));
				objCardActivity.setActivity("Card is Deliverd");
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
			System.out.println("the card deliver count is" + count);
			if (count > 0)
				bolExecute = true;

		}

		catch (Exception e) {
			System.out
					.println("Exception in  save method in CardDeliverDAOImpl"
							+ e.getMessage());

			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while save method in CardDeliverDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	// this for CardReceived Link in this cardstatus is set to active (bcz cards
	// were received)
	public boolean received(CardDeliverSearchDto objSearchDto)
			throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;
		CardActivityDto objCardActivity = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String selectCards[] = objSearchDto.getSelectedCards();

			for (int i = 0; i < selectCards.length; i++) {

				String embosql = "UPDATE CardDeliverDto SET status=:status WHERE cardNumber=:cardnumber";
				count = session.createQuery(embosql).setLong("cardnumber",
						Long.parseLong(selectCards[i])).setInteger("status",
						CommonCodes.CARD_PROCESS_RECEIVED).executeUpdate();

				if (count > 0) {
					String cardsql = "UPDATE CardsDto SET cardStatusId =:status WHERE cardNumber=:cardnumber";
					count = session.createQuery(cardsql).setLong("cardnumber",
							Long.parseLong(selectCards[i])).setInteger(
							"status", CommonCodes.CARD_ACTIVE).executeUpdate();
				}
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(Long.parseLong(selectCards[i]));
				objCardActivity.setActivity("Card is Received");
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
			System.out.println("the card received count is" + count);
			if (count > 0)
				bolExecute = true;

		}

		catch (Exception e) {
			System.out
					.println("Exception in received method in CardDeliverDAOImpl"
							+ e.getMessage());

			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while received method in CardDeliverDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public static void main(String args[]) throws Exception {

		CardDeliverDAOImpl objCard = new CardDeliverDAOImpl();
		CardDeliverSearchDto objSearch = new CardDeliverSearchDto();
		String selectCards[] = { "1724570000000010", "4563270000000020" };
		// objSearch.setCardEmbossingStaus(1);
		// objSearch.setFromCardNumber("132");
		// objSearch.setToCardNumber("1.72457000000001E15");
		objSearch.setSelectedCards(selectCards);
		// objCard.search(objSearch);
		objCard.save(objSearch);
	}

	public CardsDto getCard(String cardDeliverSerialNo) throws TPlusException {

		CardsDto objCardsDto = null;
		CardDeliverDto objCardDeliverDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardDeliverDto = (CardDeliverDto) session.get(
					CardDeliverDto.class, cardDeliverSerialNo);
			objCardsDto = (CardsDto) session.get(CardsDto.class,
					objCardDeliverDto.getCardNumber());

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

	public boolean updateObjects(String cardDeliverSerialNo, String userId)
			throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		int count = 0;

		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		CardDeliverDto objCardDeliverDto = null;
		CardsDto objCardsDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardDeliverDto = (CardDeliverDto) session.get(CardDeliverDto.class, cardDeliverSerialNo);
			objCardsDto = (CardsDto) session.get(CardsDto.class,objCardDeliverDto.getCardNumber());
			
			String activationOn = objCardsDto.getCardProductsDto().getCardActivationOn();
			long cardNo = objCardsDto.getCardNumber();
			
			if(activationOn != null && "D".equals(activationOn)){
				String cardsql = "UPDATE CardsDto SET cardStatusId =:status WHERE cardNumber=:cardnumber";
				count = session.createQuery(cardsql)
								.setLong("cardnumber",cardNo)
								.setInteger("status", CommonCodes.CARD_ACTIVE)
								.executeUpdate();
			}else{
				String cardsql = "UPDATE CardsDto SET cardStatusId =:status WHERE cardNumber=:cardnumber";
				count = session.createQuery(cardsql)
								.setLong("cardnumber", cardNo)
								.setInteger("status", CommonCodes.CARD_DELIVER)
								.executeUpdate();
			}

			if (count > 0) {
				String embosql = "UPDATE CardDeliverDto SET status=:status WHERE cardDeliverId=:cardDeliverId";
				count = session.createQuery(embosql)
								.setString("cardDeliverId",cardDeliverSerialNo)
								.setInteger("status",CommonCodes.CARD_PROCESS_DELIVERED)
								.executeUpdate();				
			}

			// this for CardActivties table
			objCardActivity = new CardActivityDto();
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(cardNo);
			objCardActivity.setActivity("Card Delivered");
			objCardActivity.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());
			objCardActivity.setUserId(userId);
			objCardActivity.setReason(" ");
			objCardActivity.setLastUpdatedBy(userId);
			objCardActivity.setUpdatedDate(new Date());
			session.save(objCardActivity);

			// inserting into LettersDispatch table
			/*objDispLetters = new DispatchLetterSearchDto();
			objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
			objDispLetters.setLetterId(CommonCodes.NEWCARD_APPLICATION);
			objDispLetters.setIssuerId(objCardsDto.getIssuerId());
			objDispLetters.setCardNumber(cardNo);
			objDispLetters.setStatus(CommonCodes.CARD_PROCESS_DELIVERED);
			objDispLetters.setLastUpdateDate(new Date());
			objDispLetters.setLastUpdatedBy(userId);
			session.save(objDispLetters);*/

			tx.commit();
			System.out.println("the count is" + count);
			if (count > 0)
				bolExecute = true;

		}

		catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(new Object(), e);
			System.out.println("in PinPrintingDAOImpl updateObjects method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in PinPrintingDAOImpl updateObjects method" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

}
