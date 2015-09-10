package org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.CardsRenewalDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalHistoryDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.dto.letters.DispatchLetterSearchDto;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;
import org.transinfo.cacis.model.CustomerService;

@SuppressWarnings("unchecked")
public class CardsRenewalDAOImpl extends BaseDAOImpl implements CardsRenewalDAO {

	/*
	 * this method is used for getting the cards to renewal from cards_renewal
	 * table
	 */
	public Collection renewalList(int pageNo) throws TPlusException {

		Collection objRenewalCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select crd.cardNumber,crd.cardExpireDate, ");
			sbf
					.append("to_char(crd.updatedDate,'dd-MM-yyyy'),crd.cardsToRenewal ");
			sbf.append("From CardsRenewalDto crd ");
			objRenewalCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {

			System.out
					.println("Error in CardsRenewalDAOImpl renewalList method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardsRenewalDAOImpl renewalList  method :" + e);

		}
		return objRenewalCollection;

	}

	/* this method is used for renewal all the cards */
	public boolean renewalProcess(CardsRenewalDto objDto) throws TPlusException {

		boolean renewal = false;
		int count = 0;
		StringBuffer sbf = null;
		Transaction tx = null;
		CardEmbossingDto objCardEb = null;
		// CardActivityDto objCardActivity = null;
		CardsRenewalHistoryDto objCardRenHist = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// getting the cards to renewal from the cards_renewal table
			sbf = new StringBuffer();
			sbf
					.append("select crd.cardNumber,crd.cardExpireDate,crd.customerId from CardsRenewalDto crd ");
			sbf.append("where crd.issuerId ='" + objDto.getIssuerId() + "' ");

			Query qry = session.createQuery(sbf.toString());
			List resList = qry.list();

			for (Iterator it = resList.iterator(); it.hasNext();) {
				Object obj[] = (Object[]) it.next();

				// generating the newExpire date using old expire date
				// SQLQuery newExpDateList
				// =session.createSQLQuery("SELECT LTRIM(TO_CHAR(TO_NUMBER('0607')+100*2,'0000'))AS EXPDATE FROM DUAL").addScalar("EXPDATE",
				// Hibernate.STRING);
				String expdate = (String) ((SQLQuery) session.createSQLQuery(
						"SELECT LTRIM(TO_CHAR(TO_NUMBER('" + (String) obj[1]
								+ "')+100*2,'0000'))AS EXPDATE FROM DUAL")
						.addScalar("EXPDATE", Hibernate.STRING)).list().get(0);

				// updating cards table to set new expire date
				sbf = new StringBuffer();
				sbf
						.append("UPDATE CardsDto SET cardExpDate =:cardexpdate WHERE cardnumber=:cardnumber ");
				count = session.createQuery(sbf.toString()).setString(
						"cardexpdate", expdate).setLong("cardnumber",
						Long.parseLong(String.valueOf(obj[0]))).executeUpdate();

				if (count > 0) {
					// iserting into cardsEmbossing table
					objCardEb = new CardEmbossingDto();

					// objCardEb.setCardNumber(Long.parseLong(String.valueOf(obj[0])));
					// objCardEb.setCustomerId((String)obj[2]);
					objCardEb.setIssuerId(objDto.getIssuerId());
					// objCardEb.setMagStripeData("Embossing Data");
					objCardEb.setStatus(CommonCodes.CARD_PROCESS_NEW);
					objCardEb.setLastUpdatedBy(objDto.getUserId());
					objCardEb.setUpdatedDate(objDto.getUpdatedDate());

					session.save(objCardEb);

					// inserting into LettersDispatch table
					/*objDispLetters = new DispatchLetterSearchDto();
					objDispLetters
							.setDispatchId(IdsGenartion.GenLetterDispId());
					objDispLetters
							.setLetterId(CommonCodes.CARDRENEWAL_APPLICATION);
					objDispLetters.setCardNumber(Long.parseLong(String
							.valueOf(obj[0])));
					objDispLetters.setIssuerId(objDto.getIssuerId());
					objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
					objDispLetters.setLastUpdateDate(objDto.getUpdatedDate());
					objDispLetters.setLastUpdatedBy(objDto.getUserId());

					session.save(objDispLetters);*/

					// for cards_Renewal_History table insertion
					objCardRenHist = new CardsRenewalHistoryDto();
					objCardRenHist.setCardNumber(Long.parseLong(String
							.valueOf(obj[0])));
					objCardRenHist.setIssuerId(objDto.getIssuerId());
					objCardRenHist.setCardExpireDate(expdate);
					objCardRenHist.setUpdatedDate(objDto.getUpdatedDate());
					objCardRenHist.setUserId(objDto.getUserId());

					session.save(objCardRenHist);

					// Inserting Data into CardActivity Table
					/*
					 * objCardActivity = new CardActivityDto();
					 * 
					 * objCardActivity.setDateTime(objDto.getDateTime());
					 * objCardActivity.setCardNumber(Long.parseLong(String
					 * .valueOf(obj[0])));
					 * objCardActivity.setActivity(objDto.getActivity());
					 * objCardActivity.setStationIp(objDto.getStationIp());
					 * objCardActivity.setUserId(objDto.getUserId());
					 * objCardActivity.setLastUpdatedBy(objDto.getUserId());
					 * objCardActivity.setUpdatedDate(objDto.getUpdatedDate());
					 * 
					 * session.save(objCardActivity);
					 */

					// deleting the renewaled cards frorm Cards_renewal table
					sbf = new StringBuffer();
					sbf
							.append("DELETE CardsRenewalDto  WHERE cardnumber=:cardnumber ");
					count = session.createQuery(sbf.toString()).setLong(
							"cardnumber",
							Long.parseLong(String.valueOf(obj[0])))
							.executeUpdate();

				}
				// to show the renewal failed message with cardnumber
				/*
				 * else{ throw newTPlusException(TPlusCodes.APPLICATION_ERROR,
				 * "Error:card renwal fail"
				 * +Long.parseLong(String.valueOf(obj[0]))); }
				 */
			}

			tx.commit();
			if (count > 0)
				renewal = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error in CardsRenewalDAOImpl renewalProcess Method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: Error in CardsRenewalDAOImpl renewalProcess Method"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return renewal;
	}

	public CardsRenewalDto getOpenRenewalSubmission(String cardNo)
			throws TPlusException {

		CardsRenewalDto objCardsRenewalDto = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("FROM CardsRenewalDto crdo ");
			sbf.append("where crdo.cardNumber = " + cardNo + " ");
			sbf.append("and (crdo.status = 0 or crdo.status = 1) ");

			Query qry = session.createQuery(sbf.toString());
			List listDocs = qry.list();

			if (listDocs.size() > 0) {
				objCardsRenewalDto = (CardsRenewalDto) listDocs.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error in CardReplacementDAOImpl getOpenReplacementFormSubmission method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardReplacementDAOImpl getOpenReplacementFormSubmission  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objCardsRenewalDto;

	}

	public Collection search(CardsRenewalDto objSearchDto)
			throws TPlusException {

		Collection objSearchCollection = null;

		try {

			if (objSearchDto.getCardNumber() > 0) {
				CustomerService objCustServ = new CustomerService();
				CustomerServiceDataBean objService = objCustServ
						.getCustomerServiceData(objSearchDto.getCardNumber());
				objSearchCollection = new ArrayList();
				objSearchCollection.add(objService);
			}

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

	public boolean add(CardsRenewalDto objCarRepDto) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		CardActivityDto objCardActivity = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.save(objCarRepDto);

			// inserting into CardActivites Table
			objCardActivity = new CardActivityDto();
			objCardActivity.setDateTime(new Date());
			objCardActivity.setCardNumber(objCarRepDto.getCardNumber());
			objCardActivity.setActivity("Card Renewal Request Submitted");
			objCardActivity.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());
			objCardActivity.setUserId(objCarRepDto.getUserId());
			objCardActivity.setReason("Card Renewal");
			objCardActivity.setLastUpdatedBy(objCarRepDto.getUserId());
			objCardActivity.setUpdatedDate(objCarRepDto.getUpdatedDate());

			session.save(objCardActivity);

			tx.commit();
			bolExecute = true;
		} catch (Exception e) {
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

	public Collection processSearch(CardsRenewalDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {
			sbf
					.append("select crd.cardRenewalSerial,crd.cardNumber,apdo.customerName, ");
			sbf
					.append("crd.cardExpireDate, to_char(crd.updatedDate,'dd-MM-yyyy') ");
			sbf.append("FROM CardsRenewalDto crd, ApplicationProcessDto apdo ");
			sbf.append("where crd.customerId = apdo.customerId ");
			sbf.append("and crd.status = " + CommonCodes.APPLICATIONSTATUS_NEW
					+ " ");

			if (objSearchDto.getCardNumber() > 0) {
				sbf.append("and crd.cardNumber = "
						+ objSearchDto.getCardNumber() + " ");
			}
			
			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and apdo.branchId = '" + objSearchDto.getBranchId() + "' ");
			}
			
			sbf.append("and apdo.issuerId = '" + objSearchDto.getIssuerId() + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error in  CardRenewalDAOImpl processSearch Method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in  CardRenewalDAOImpl processSearch  Method" + e);
		}

		return objSearchCollection;
	}

	public boolean accept(CardsRenewalDto objRepDto) throws TPlusException {

		boolean accept = false;
		Transaction tx = null;
		int count;
		CardActivityDto objCardActivity = null;
		DispatchLetterSearchDto objDispLetters = null;
		CardsDto objCardsDto = null;
		CardsRenewalHistoryDto objCardsRenewalHistoryDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// updating card renewal
			String sql = "UPDATE CardsRenewalDto SET status =:appacceptstatus WHERE cardRenewalSerial=:sno";
			count = session.createQuery(sql).setString("sno",
					objRepDto.getCardRenewalSerial()).setInteger(
					"appacceptstatus", CommonCodes.APPLICATIONSTATUS_ACCEPTED)
					.executeUpdate();

			if (count > 0) {

				objCardsDto = (CardsDto) session.get(CardsDto.class, objRepDto
						.getCardNumber());

				// inserting into REPLACEMENT_LOG table
				objCardsRenewalHistoryDto = new CardsRenewalHistoryDto();
				objCardsRenewalHistoryDto.setCardNumber(objCardsDto
						.getCardNumber());
				objCardsRenewalHistoryDto.setIssuerId(objRepDto.getIssuerId());
				objCardsRenewalHistoryDto.setCardExpireDate(objCardsDto
						.getCardExpDate());
				objCardsRenewalHistoryDto.setUpdatedDate(new Date());
				objCardsRenewalHistoryDto.setUserId(objRepDto.getUserId());
				session.save(objCardsRenewalHistoryDto);

				// inserting into LettersDispatch table
				objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters.setLetterId(CommonCodes.CARDRENEWAL_APPLICATION);
				objDispLetters.setApplicationId(objRepDto
						.getCardRenewalSerial());
				objDispLetters.setCardNumber(objCardsDto.getCardNumber());
				objDispLetters.setIssuerId(objRepDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_RENEWAl_ACCEPTED);
				objDispLetters.setLastUpdateDate(new Date());
				objDispLetters.setLastUpdatedBy(objRepDto.getUserId());
				session.save(objDispLetters);

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objRepDto.getCardNumber());
				objCardActivity
						.setActivity("CardRenewal Application Acccepted");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objRepDto.getUserId());
				objCardActivity.setReason("");
				objCardActivity.setLastUpdatedBy(objRepDto.getUserId());
				objCardActivity.setUpdatedDate(new Date());
				session.save(objCardActivity);
			}

			if (count > 0) {
				tx.commit();
				accept = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting renewal application in CardRenewalDAOImpl AcceptMethod"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error while accepting renewal application in CardRenewalDAOImpl AcceptMethod"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	public boolean reject(CardsRenewalDto objRepDto) throws TPlusException {

		boolean reject = false;
		Transaction tx = null;
		int count;
		CardActivityDto objCardActivity = null;
		DispatchLetterSearchDto objDispLetters = null;
		CardsDto objCardsDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// updating card renewal
			String sql = "UPDATE CardsRenewalDto SET status =:appacceptstatus WHERE cardRenewalSerial=:sno";
			count = session.createQuery(sql).setString("sno",
					objRepDto.getCardRenewalSerial()).setInteger(
					"appacceptstatus", CommonCodes.APPLICATIONSTATUS_REJECTED)
					.executeUpdate();

			if (count > 0) {

				objCardsDto = (CardsDto) session.get(CardsDto.class, objRepDto
						.getCardNumber());

				// inserting into LettersDispatch table
				objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters.setLetterId(CommonCodes.CARDRENEWAL_APPLICATION);
				objDispLetters.setApplicationId(objRepDto
						.getCardRenewalSerial());
				objDispLetters.setCardNumber(objCardsDto.getCardNumber());
				objDispLetters.setIssuerId(objRepDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_RENEWAl_REJECTED);
				objDispLetters.setLastUpdateDate(new Date());
				objDispLetters.setLastUpdatedBy(objRepDto.getUserId());
				session.save(objDispLetters);

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objRepDto.getCardNumber());
				objCardActivity
						.setActivity("CardRenewal Application Rejected");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objRepDto.getUserId());
				objCardActivity.setReason("");
				objCardActivity.setLastUpdatedBy(objRepDto.getUserId());
				objCardActivity.setUpdatedDate(new Date());
				session.save(objCardActivity);
			}

			if (count > 0) {
				tx.commit();
				reject = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting renewal application in CardRenewalDAOImpl reject"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error while accepting renewal application in CardRenewalDAOImpl reject"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return reject;
	}
}
