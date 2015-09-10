package org.transinfo.cacis.dataacess.daoimpl.oracle.csr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.csr.CsrDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.csr.CallRecordLogDto;
import org.transinfo.cacis.dto.csr.CallTypeDto;
import org.transinfo.cacis.dto.csr.CsrDto;
import org.transinfo.cacis.dto.csr.CsrSearchDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CreditSplitDto;
import org.transinfo.cacis.formbean.customerservice.LimitForm;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings("unchecked")
public class CsrDAOImpl extends BaseDAOImpl implements CsrDAO {

	/*
	 * This method is used for getting the Records callCenter
	 */

	public Collection callCenterSearch(CsrSearchDto objSearchDto)
			throws TPlusException {

		Collection searchList = new ArrayList();
		// Transaction tx = null;
		// CommonDataBean objCommBean =null;

		try {
			StringBuffer sbf = new StringBuffer();
			// Session session =HibernetFactory.currentSession();
			// tx =session.beginTransaction();

			sbf
					.append("select cr.cardNumber,app.customerName, app.idNumber, cr.cardExpDate,cr.cardStatus ");
			sbf
					.append("from ApplicationProcessDto app left join fetch app.customerCards cr   ");
			// this is used for when enters only referenc No
			if (objSearchDto.getCallRefNo() != null
					&& !objSearchDto.getCallRefNo().equals("")) {
				sbf
						.append("where cr.cardNumber =(select crl.cardNumber from CallRecordLogDto crl where upper(crl.referenceNo)= '"
								+ objSearchDto.getCallRefNo().toUpperCase() + "') ");
			} else {
				sbf.append("where cr.cardNumber like  '%"
						+ objSearchDto.getSearchFiled() + "%' ");
			}
			sbf.append("OR upper(app.idNumber) like  '%"
					+ objSearchDto.getSearchFiled().toUpperCase() + "%' ");
			sbf.append("OR upper(app.customerName) like  '%"
					+ objSearchDto.getSearchFiled().toUpperCase() + "%' ");

			searchList = getList(sbf.toString());

			/*
			 * Query qry = session.createQuery(sbf.toString()); List resultList
			 * = qry.list();
			 * 
			 * for(Iterator it = resultList.iterator();it.hasNext();){ Object
			 * obj[]= (Object[])it.next(); objCommBean = new CommonDataBean();
			 * objCommBean.setColumn1(String.valueOf(obj[0]));
			 * objCommBean.setColumn2((String)(obj[1]));
			 * objCommBean.setColumn3((String)(obj[2]));
			 * objCommBean.setColumn4((String)obj[3]);
			 * objCommBean.setColumn5((String)obj[4]);
			 * searchList.add(objCommBean); }
			 * 
			 * tx.commit();
			 */

		} catch (Exception e) {
			/*
			 * if(tx!=null) { tx.rollback(); }
			 */
			System.out
					.println("Error while getting callCenterSearch  list in CsrDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving callCenterSearch list in CsrDAOImpl"
							+ e);
		}
		/*
		 * finally{
		 * 
		 * HibernetFactory.closeSession(); }
		 */
		return searchList;
	}

	/*
	 * This method is used for inserting the Record into Call_Record_Log table
	 */
	public void callRecordLog(CallRecordLogDto objDto) throws TPlusException {

		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDto);
			tx.commit();

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CsrDAOImpl callRecordLog method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CsrDAOImpl callRecordLog  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
	}

	// this for checking the any previousCalls exists for this refNo before
	// inserting new record*/
	public CommonDataBean previousCallsCheck(String refNo)
			throws TPlusException {

		Transaction tx = null;
		CommonDataBean objCommBean = new CommonDataBean();
		try {

			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// to get the prev,original refnumbers using refnumber
			sbf.append("select prevReferenceNo,originalRefNo ");
			sbf.append("from CallRecordLogDto where referenceNo = '" + refNo
					+ "' and callStatus = '" + CommonCodes.CALLSTATUS_OPEN
					+ "' ");

			Query qry = session.createQuery(sbf.toString());
			List resultList = qry.list();

			for (Iterator it = resultList.iterator(); it.hasNext();) {
				Object obj[] = (Object[]) it.next();
				objCommBean.setColumn1((String) obj[0]);
				objCommBean.setColumn2((String) obj[1]);
			}
			/*
			 * already this call contains open call then get the refnumber using
			 * above oroginal refno and prevrefno insert refnumber in prevRefno
			 * field
			 */
			if (objCommBean.getColumn1() != null) {
				String insRefNo = (String) session.createQuery(
						"select referenceNo from CallRecordLogDto where prevReferenceNo = '"
								+ objCommBean.getColumn1()
								+ "' and originalRefNo = '"
								+ objCommBean.getColumn2()
								+ "' and callStatus = '"
								+ CommonCodes.CALLSTATUS_OPEN + "'").list()
						.get(0);
				objCommBean.setColumn3(insRefNo);
			}
			tx.commit();

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CsrDAOImpl callRecordLog method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CsrDAOImpl callRecordLog  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objCommBean;
	}

	// this for showing the all the previousCalls data for this Call when
	// recording the call
	public Collection previousCallData(CallRecordLogDto objRecLogDto)
			throws TPlusException {

		Collection searchList = new ArrayList();
		// Transaction tx = null;
		// CommonDataBean objCommBean =null;

		try {
			StringBuffer sbf = new StringBuffer();
			// Session session =HibernetFactory.currentSession();
			// tx =session.beginTransaction();

			sbf
					.append("select a.referenceNo,a.callType,a.callDescription,to_char(a.callStartTime,'dd-mm-yyyy HH24:MI:SS'),a.originalRefNo,a.prevReferenceNo ");
			sbf.append("from CallRecordLogDto a ,CallRecordLogDto b ");
			sbf.append("where a.referenceNo = b.prevReferenceNo ");
			sbf.append("and a.callStatus ='" + CommonCodes.CALLSTATUS_OPEN
					+ "' ");
			sbf.append("and a.originalRefNo ='"
					+ objRecLogDto.getOriginalRefNo() + "' ");

			searchList = getList(sbf.toString());
			/*
			 * Query qry = session.createQuery(sbf.toString()); List resultList
			 * = qry.list();
			 * 
			 * for(Iterator it = resultList.iterator();it.hasNext();){ Object
			 * obj[]= (Object[])it.next(); objCommBean = new CommonDataBean();
			 * objCommBean.setColumn1((String)obj[0]);
			 * objCommBean.setColumn2((String)(obj[1]));
			 * objCommBean.setColumn3((String)(obj[2]));
			 * objCommBean.setColumn4((String)obj[3]);
			 * objCommBean.setColumn5((String)obj[4]);
			 * objCommBean.setColumn6((String)obj[5]);
			 * searchList.add(objCommBean); }
			 * 
			 * tx.commit();
			 */

		} catch (Exception e) {
			/*
			 * if(tx!=null) { tx.rollback(); }
			 */
			System.out
					.println("Error while getting previousCallData  list in CsrDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving previousCallData list in CsrDAOImpl"
							+ e);
		}
		/*
		 * finally{
		 * 
		 * HibernetFactory.closeSession(); }
		 */
		return searchList;
	}

	/*
	 * This method is used for updating the Record into Call_Record_Log table
	 */
	public boolean callRecordUpdate(CallRecordLogDto objDto)
			throws TPlusException {

		boolean bolUpdate = false;
		Transaction tx = null;

		try {
			// StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);

			// updating table if currnet call is closing all its previous calls
			// has to be close
			if (objDto.getCallStatus().equals(CommonCodes.CALLSTATUS_CLOSE)) {
				String cardsql = "UPDATE CallRecordLogDto SET callStatus =:callstatus WHERE originalRefNo=:originalrefno";
				int count = session.createQuery(cardsql).setString(
						"originalrefno", objDto.getOriginalRefNo()).setString(
						"callstatus", CommonCodes.CALLSTATUS_CLOSE)
						.executeUpdate();
			}
			tx.commit();
			bolUpdate = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error in CsrDAOImpl callRecordLogUpdate method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CsrDAOImpl callRecordLogUpdate  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolUpdate;
	}

	// this for showing the all the Open Calls data
	public Collection openCalls() throws TPlusException {

		Collection openCallsList = new ArrayList();

		try {
			StringBuffer sbf = new StringBuffer();

			sbf
					.append("select crd.cardNumber,app.customerName,crd.referenceNo,crd.callType,to_char(crd.callStartTime,'dd-mm-yyyy HH24:MI:SS') ");
			sbf
					.append("from CallRecordLogDto crd,ApplicationProcessDto app, CardsDto cr  ");
			sbf.append("where crd.callStatus = '" + CommonCodes.CALLSTATUS_OPEN
					+ "' ");
			sbf.append("and crd.cardNumber = cr.cardNumber ");
			sbf.append("and app.customerId = cr.customerId ");

			openCallsList = getList(sbf.toString());

			/*
			 * Transaction tx = null; CommonDataBean objCommBean =null; Session
			 * session =HibernetFactory.currentSession(); tx
			 * =session.beginTransaction(); Query qry =
			 * session.createQuery(sbf.toString()); List resultList =
			 * qry.list();
			 * 
			 * for(Iterator it = resultList.iterator();it.hasNext();){ Object
			 * obj[]= (Object[])it.next(); objCommBean = new CommonDataBean();
			 * objCommBean.setColumn1(String.valueOf(obj[0]));
			 * objCommBean.setColumn2((String)(obj[1]));
			 * objCommBean.setColumn3((String)(obj[2]));
			 * objCommBean.setColumn4((String)(obj[3]));
			 * objCommBean.setColumn5((String)obj[4]);
			 * openCallsList.add(objCommBean); }
			 * 
			 * tx.commit();
			 */

		} catch (Exception e) {
			// if(tx!=null)
			// {
			// tx.rollback();
			// }
			System.out
					.println("Error while getting openCalls  list in CsrDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving openCalls list in CsrDAOImpl" + e);
		}
		// finally{

		// HibernetFactory.closeSession();
		// }
		return openCallsList;
	}

	/*
	 * This method is used for getting the CustomerSummary List
	 */
	public Collection csrSummary(CsrDto objSearchDto) throws TPlusException {

		Collection customeSummaryList = new ArrayList();
		CommonDataBean objCommBean = null;
		Transaction tx = null;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			if (objSearchDto.getCardNumber() > 0) {

				sbf.append("select app.customerName,app.embossingName, app.idNumber, app.expDate, app.dob,app.referenceCompany,");
				sbf.append("cr.cardNumber,cp.cardProductName, cr.creditLimit,cr.cardExpDate, cr.cardStatus,cht.description,  ");
				sbf.append("add.address1 ,add.address2,add.city,add.state,add.country,add.postalCode,add.phone,add.fax,add.township, cr.maskedCardNo ");
				sbf.append("From ApplicationProcessDto app, CardsDto cr, CardProductDto cp,CustomerAddressDto add,CardHolderTypeDto cht ");
				sbf.append("where ");
				sbf.append("cr.customerId = app.customerId and ");
				sbf.append("cr.cardProductId =cp.cardProductId and ");
				sbf.append("cr.cardHolderType = cht.cardHolderTypeId and ");
				sbf.append("app.customerId = add.appFormProcssDto.customerId and ");
				sbf.append("app.billingTo = add.addressType and ");
				sbf.append("cr.cardNumber = " + objSearchDto.getCardNumber() + " ");

				Query qry = session.createQuery(sbf.toString());
				List resultList = qry.list();

				for (Iterator it = resultList.iterator(); it.hasNext();) {
					Object obj[] = (Object[]) it.next();
					objCommBean = new CommonDataBean();

					objCommBean.setColumn1((String) obj[0]);
					objCommBean.setColumn2((String) obj[1]);
					objCommBean.setColumn3((String) obj[2]);
					
					if(obj[3] != null){
						objCommBean.setColumn4(DateUtil.convertDateToDateString((Date) obj[3]));
					}else{
						objCommBean.setColumn14("");
					}
					
					if(obj[4] != null){
						objCommBean.setColumn5(DateUtil.convertDateToDateString((Date) obj[4]));
					}else{
						objCommBean.setColumn5("");
					}
					
					objCommBean.setColumn6((String) obj[5]);

					objCommBean.setColumn7(String.valueOf(obj[6]));
					objCommBean.setColumn8((String) obj[7]);
					objCommBean.setColumn9(String.valueOf(obj[8]));
					objCommBean.setColumn10((String) obj[9]);
					objCommBean.setColumn11((String) obj[10]);
					objCommBean.setColumn12((String) obj[11]);

					objCommBean.setColumn13((String) obj[12]);
					objCommBean.setColumn14((String) obj[13]);
					objCommBean.setColumn15((String) obj[14]);
					objCommBean.setColumn16((String) obj[15]);
					objCommBean.setColumn17((String) obj[16]);
					objCommBean.setColumn18(String.valueOf(obj[17]));
					objCommBean.setColumn19((String) obj[18]);
					objCommBean.setColumn20((String) obj[19]);
					objCommBean.setColumn21((String) obj[20]);
					objCommBean.setColumn22((String) obj[21]);

					customeSummaryList.add(objCommBean);
				}

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CsrDAOImpl csrSummary method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CsrDAOImpl csrSummary  method :" + e);

		}
		return customeSummaryList;

	}

	public CustomerInfoDto customerInfo(CsrDto objSearchDto)
			throws TPlusException {

		Transaction tx = null;
		CustomerInfoDto cfDto = null;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// cfDto =
			// (CustomerInfoDto)session.get(CustomerInfoDto.class,objSearchDto.getCustomerId());
			sbf
					.append("from CustomerInfoDto cf where cf.customerId =(select cr.customerId from CardsDto cr where cr.cardNumber = "
							+ objSearchDto.getCardNumber() + " ) ");
			Query qy = session.createQuery(sbf.toString());
			cfDto = (CustomerInfoDto) qy.list().get(0);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting customerInfo  list in CsrDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving customerInfo list in CsrDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cfDto;
	}

	public Collection accountInfo(CsrDto objSearchDto) throws TPlusException {
		Collection accountDetails = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {
			if (objSearchDto.getCardNumber() > 0) {

				sbf
						.append("select acc.creditLimit,acc.cashLimit,acc.previousBalance, acc.limitUsed,acc.amountCredited, ");
				sbf
						.append("acc.amountDebited,  acc.authorizationAmt,acc.lastStatementDate,acc.lastStatementDueDate,acc.currentMinPaymentDue, ");
				sbf.append("(acc.creditLimit-acc.limitUsed) as avabalance ");
				sbf
						.append("from CardsDto ca left  join  fetch ca.custAccountDto acc ");
				sbf.append("where ca.cardNumber ="
						+ objSearchDto.getCardNumber() + " ");
				accountDetails = getSearchList(sbf.toString(), 0);
			}

		} catch (Exception e) {

			System.out
					.println("Error while getting accountDetails  list in CsrDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving accountDetails list in CsrDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accountDetails;
	}

	public Map cardDetails(CsrDto objSearchDto) throws TPlusException {

		Map CardDetailsList = new TreeMap();
		ArrayList allCards = new ArrayList();
		ArrayList primaryCards = new ArrayList();
		ArrayList otherCards = new ArrayList();
		ArrayList supplyCards = new ArrayList();
		Transaction tx = null;
		CommonDataBean objCommBean = null;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// getting the customerId using cardnumber
			sbf
					.append("select cr.customerId,cr.cardHolderType from CardsDto cr where cr.cardNumber= "
							+ objSearchDto.getCardNumber() + " ");
			Query qy = session.createQuery(sbf.toString());
			List lst = qy.list();
			Object custIdObj[] = (Object[]) lst.get(0);
			// checking enter card is primary card or not
			if ((String.valueOf(custIdObj[1]).equals(String
					.valueOf(CommonCodes.PRIMARYCARD_HOLDER)))) {// if enter
																	// card is
																	// primary
																	// card
				// getting all other cardstype for this customer except this
				// card
				sbf = new StringBuffer();
				sbf
						.append("select  cr.cardHolderType,cr.cardNumber from CardsDto cr where cr.customerId = '"
								+ custIdObj[0]
								+ "' and cr.cardNumber != "
								+ objSearchDto.getCardNumber() + " ");
				Query qy1 = session.createQuery(sbf.toString());
				List ls1 = qy1.list();
				// with out this card adding all cards(supply and othercards for
				// this customer) to array
				for (int i = 0; i < ls1.size(); i++) {
					Object allObj[] = (Object[]) ls1.get(i);
					allCards.add(allObj[1]);
				}
				// selecting data for all cards
				if (allCards.size() > 0) {

					sbf = new StringBuffer();
					sbf
							.append("select cr.cardNumber,cp.cardProductName,cr.cardExpDate,cr.creditLimit,cr.cardStatus,cr.cardHolderType, ");
					sbf
							.append("(cr.custAccountDto.creditLimit-cr.custAccountDto.limitUsed) as avabalance, cr.custAccountDto.limitUsed ");
					sbf.append("from CardsDto cr,CardProductDto cp ");
					sbf.append("where ");
					sbf.append("cr.cardProductId = cp.cardProductId and ");
					sbf.append("cr.cardNumber in(:cardsno) ");

					Query cardsQy = session.createQuery(sbf.toString())
							.setParameterList("cardsno", allCards);
					List resList = cardsQy.list();

					for (Iterator it = resList.iterator(); it.hasNext();) {
						Object obj[] = (Object[]) it.next();
						objCommBean = new CommonDataBean();
						objCommBean.setColumn1(String.valueOf(obj[0]));
						objCommBean.setColumn2((String) obj[1]);
						objCommBean.setColumn3(String.valueOf(obj[2]));
						objCommBean.setColumn4((String) obj[3]);
						objCommBean.setColumn5((String) obj[4]);
						objCommBean.setColumn6(String.valueOf(obj[6]));
						objCommBean.setColumn7(String.valueOf(obj[7]));
						// this for othercards records(again if it is primary
						// card then it is othercard for this customer)
						if ((String.valueOf(obj[5]).equals(String
								.valueOf(CommonCodes.PRIMARYCARD_HOLDER)))) {
							otherCards.add(objCommBean);
						} else { // this for supply cards records
							supplyCards.add(objCommBean);
						}
					}
					// this for other cards records adding to map
					CardDetailsList.put("OtherCards", otherCards);
					// this for supply cards records adding to map
					CardDetailsList.put("SupplyCards", supplyCards);
				}
			} else {// if enter card is supply card for showing primary card
					// details

				sbf = new StringBuffer();
				sbf
						.append("select cr.cardNumber,cp.cardProductName,cr.cardExpDate,cr.creditLimit,cr.cardStatus,cr.cardHolderType, ");
				sbf
						.append("(cr.custAccountDto.creditLimit-cr.custAccountDto.limitUsed) as avabalance, cr.custAccountDto.limitUsed ");
				sbf.append("from CardsDto cr,CardProductDto cp ");
				sbf.append("where ");
				sbf.append("cr.cardProductId = cp.cardProductId ");
				sbf.append("and cr.customerId =(select c.customerId from CardsDto c where c.cardNumber="+objSearchDto.getCardNumber()+") ");
				sbf.append("and cr.cardProductId = (select c.cardProductId from CardsDto c where c.cardNumber="+objSearchDto.getCardNumber()+") ");
				sbf.append("and cr.cardHolderType = 1 ");
				/*sbf
						.append("cr.customerId =(select sc.appProcessDto.customerId from CardsDto c,SupplementaryCardHolderDto sc ");
				sbf
						.append("where c.customerId = sc.supplementaryId  and c.cardNumber="
								+ objSearchDto.getCardNumber() + ") ");*/

				Query primaryCardQy = session.createQuery(sbf.toString());
				List resPrimaryList = primaryCardQy.list();

				for (Iterator it = resPrimaryList.iterator(); it.hasNext();) {
					Object obj[] = (Object[]) it.next();
					objCommBean = new CommonDataBean();
					objCommBean.setColumn1(String.valueOf(obj[0]));
					objCommBean.setColumn2((String) obj[1]);
					objCommBean.setColumn3(String.valueOf(obj[2]));
					objCommBean.setColumn4((String) obj[3]);
					objCommBean.setColumn5((String) obj[4]);
					objCommBean.setColumn6(String.valueOf(obj[6]));
					objCommBean.setColumn7(String.valueOf(obj[7]));
					// adding all the bean objects to arraylist
					primaryCards.add(objCommBean);
				}
				// adding the primary cards records to map
				CardDetailsList.put("PrimaryCards", primaryCards);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting CardDetails  list in CsrDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving CardDetails list in CsrDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return CardDetailsList;
	}

	/*
	 * for showing the Card Activity Details from Card_Activities table
	 */

	public Collection cardActivities(CsrDto objSearchDto) throws TPlusException {

		Collection cardActities = new ArrayList();
		CommonDataBean objCommBean = null;
		Transaction tx = null;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			if (objSearchDto.getCardNumber() > 0) {

				sbf
						.append("select to_char(cad.dateTime,'dd-MM-yyyy HH24:mi:ss'),cad.activity, cad.stationIp,cad.reason From CardActivityDto cad ");
				sbf.append("where cad.cardNumber =:cardnumber ");
				List resultList = session.createQuery(sbf.toString()).setLong(
						"cardnumber", objSearchDto.getCardNumber()).list();
				for (Iterator it = resultList.iterator(); it.hasNext();) {
					Object obj[] = (Object[]) it.next();
					objCommBean = new CommonDataBean();
					objCommBean.setColumn1((String) obj[0]);
					objCommBean.setColumn2((String) obj[1]);
					objCommBean.setColumn3((String) obj[2]);
					objCommBean.setColumn4((String) obj[3]);
					cardActities.add(objCommBean);
					// cardActities = getSearchList(sbf.toString(),0);
				}
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting cardActities  list in CsrDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardActities list in CsrDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cardActities;
	}

	/*
	 * This method is used for getting the Card tranx details from TranxLog
	 * table
	 */
	public Collection tranxHistory(CsrDto objSearchDto) throws TPlusException {

		Collection tranxHistoryList = new ArrayList();
		CommonDataBean objCommBean = null;
		Transaction tx = null;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			if (objSearchDto.getCardNumber() > 0) {

				sbf.append("select tl.dateTime,tl.merchantName,tl.amount,tl.currencyCode, ");
				sbf.append("tl.cardHolderTranxAmt,tl.cardHolderTranxCurr,tl.referenceNo From TransactionLogDto tl where tl.cardNumber =:cardnumber ");

				if (objSearchDto.getTransDateFrom() == null
						&& objSearchDto.getTransDateTo() == null) {
					sbf
							.append("and tl.dateTime between trunc(add_months(sysdate ,-1)) and sysdate   ");
				} else {
					sbf
							.append("and tl.dateTime  between to_date('"
									+ objSearchDto.getTransDateFrom()
									+ "','dd/mm/yyyy') and trunc(to_date('"
									+ objSearchDto.getTransDateTo()
									+ "','dd/mm/yyyy')" +
											"+1)");
				}

				List resultList = session.createQuery(sbf.toString()).setLong(
						"cardnumber", objSearchDto.getCardNumber()).list();

				for (Iterator it = resultList.iterator(); it.hasNext();) {
					Object obj[] = (Object[]) it.next();
					objCommBean = new CommonDataBean();
					/*objCommBean.setColumn1(DateUtil
							.convertDateToDateString((Date) obj[0]));*/
					objCommBean.setColumn1(DateUtil
									.convertTranxDateToDateString((Date) obj[0]));
					objCommBean.setColumn2((String) obj[1]);
					objCommBean.setColumn3(String.valueOf(obj[2]));
					objCommBean.setColumn4((String) obj[3]);
					objCommBean.setColumn5(String.valueOf(obj[4]));
					objCommBean.setColumn6((String) obj[5]);
					objCommBean.setColumn7((String) obj[6]);

					tranxHistoryList.add(objCommBean);
					// tranxHistoryList = getSearchList(sbf.toString(),0);
				}
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CsrDAOImpl in  tranxHistory method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CsrDAOImpl tranxHistory  method :" + e);

		}
		return tranxHistoryList;
	}

	/*
	 * This is for getting thee Current Pin Count form cards Table
	 */

	public Collection csrResetPinCount(CsrDto objSearchDto)
			throws TPlusException {

		Collection currentPinCount = new ArrayList();
		CommonDataBean objCommBean = null;
		Transaction tx = null;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			if (objSearchDto.getCardNumber() > 0) {

				sbf
						.append("select cr.wrongPinCount,cr.cardStatusId From CardsDto cr ");
				sbf.append("where cr.cardNumber =:cardnumber ");
				List resultList = session.createQuery(sbf.toString()).setLong(
						"cardnumber", objSearchDto.getCardNumber()).list();
				// Integer currentPin = (Integer)resultList.get(0);
				// objCommBean.setColumn1(String.valueOf(currentPin));
				for (Iterator it = resultList.iterator(); it.hasNext();) {
					Object obj[] = (Object[]) it.next();
					objCommBean = new CommonDataBean();
					objCommBean.setColumn1(String.valueOf(obj[0]));
					objCommBean.setColumn2(String.valueOf(obj[1]));
					currentPinCount.add(objCommBean);
				}
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting csrResetPinCount   in CsrDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving csrResetPinCount in CsrDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return currentPinCount;
	}

	/* this for preList for calltype from callTypes table */
	public Map callTypeListData() throws TPlusException {

		Map callTypeList = new TreeMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From  CallTypeDto ");
			List listCountries = qry.list();
			for (Iterator it = listCountries.iterator(); it.hasNext();) {
				CallTypeDto objDto = new CallTypeDto();
				objDto = (CallTypeDto) it.next();
				callTypeList.put(new Integer(objDto.getCallTypeId()), objDto
						.getCallType());
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("while retrieving callTypeListData in CsrDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving callTypeListData in CsrDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return callTypeList;
	}

	/*
	 * this method is used for getting the supplucards List for this primaryCard
	 * to CreditSplitt
	 */

	public CreditSplitDto creditSplittData(CreditSplitDto objSplittDto)
			throws TPlusException {
		Transaction tx = null;
		LimitForm objLimitform = null;
		// CreditSplitDto objSplittDto = new CreditSplitDto();
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			sbf
					.append("select cr.cardNumber,cr.cardStatus,app.customerName,cr.creditLimitPercent,ca.creditLimit,cr.cardHolderType,cr.cashLimitPercent,ca.cashLimit ");
			sbf
					.append("from ApplicationProcessDto app left join fetch app.customerCards cr left join fetch app.customerAccount ca  ");
			sbf.append("where ");
			sbf
					.append("app.customerId = (select crd.customerId  from CardsDto crd where  crd.cardNumber = "
							+ objSplittDto.getCardNumber() + ")");

			Query cardsQy = session.createQuery(sbf.toString());
			List resList = cardsQy.list();
			System.out.println("All CardsList list size" + resList.size());

			for (Iterator it = resList.iterator(); it.hasNext();) {
				Object obj[] = (Object[]) it.next();
				if (String.valueOf(obj[0]).equals(objSplittDto.getCardNumber())
						|| String
								.valueOf(obj[5])
								.equals(
										String
												.valueOf(CommonCodes.SUPPLEMENTARYCARD_HOLDER))) {
					objLimitform = new LimitForm();

					objLimitform.setCardNumber(String.valueOf(obj[0]));
					if (String.valueOf(obj[0]).equals(
							objSplittDto.getCardNumber())) {

						objLimitform.setCardHolderType("PRIMARY");
						/* cashlimitpercent from cards * cashlimit from account */
						double currentCashLimit = (((Float.valueOf(String
								.valueOf(obj[6])).floatValue())) * ((Float
								.valueOf(String.valueOf(obj[7])).floatValue()))) / 100;
						objLimitform.setCurrentCashLimit(String
								.valueOf(currentCashLimit));
						objLimitform.setCurrentCashLimitRatio(String
								.valueOf(obj[6]));

						// setting cashlimit to creditsplitdto
						objSplittDto.setCashLimit(String.valueOf(obj[7]));
					} else {
						objLimitform.setCardHolderType("SUPPLEMENTARY");
					}
					objLimitform.setCardStatus((String) obj[1]);
					objLimitform.setCustomerName((String) obj[2]);

					// calculating currentCreditLimit value /*
					// creditlimitpercent from cards * creditlimit from account
					// */
					double currCreditLimit = (((Float.valueOf(String
							.valueOf(obj[3])).floatValue())) * ((Float
							.valueOf(String.valueOf(obj[4])).floatValue()))) / 100;

					objLimitform.setCurrentLimit(String
							.valueOf(currCreditLimit));
					objLimitform.setCurrentRatio(String.valueOf(obj[3]));

					// adding form to Set
					objSplittDto.getLimitFormsList().add(objLimitform);

					// setting creditlimit to creditsplitdto
					objSplittDto.setCreditLimit(String.valueOf(obj[4]));

				}
			}
			// this is used for setting the cardslist size
			objSplittDto.setCardsSize(resList.size());

			tx.commit();

		} catch (Exception e) {
			System.out.println("in CardDeliverDAOImpl creditSplittData method"
					+ e.getMessage());

			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  in CardDeliverDAOImpl creditSplittData method"
							+ e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}
		return objSplittDto;
	}

	public int checkExistrecord(Object commonObj) throws TPlusException {

		int res = 0;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();

			if (commonObj instanceof CsrSearchDto) {
				CsrSearchDto objDto = (CsrSearchDto) commonObj;
				if (objDto.getCallRefNo() != null
						&& !objDto.getCallRefNo().equals("")) {
					sbf
							.append("select count(*) from CallRecordLogDto crl where upper(crl.referenceNo) = '"
									+ objDto.getCallRefNo().toUpperCase() + "' ");
				} else {
					sbf
							.append("select count(*) from CardsDto cr where upper(cr.cardNumber) = "
									+ objDto.getQuickCardNo() + " ");
				}
			}

			// checking cardholderType for (csr CreditSplit screen)if primary
			// retunrs 1 supply returns 2
			if (commonObj instanceof CreditSplitDto) {
				CreditSplitDto objDto = (CreditSplitDto) commonObj;
				sbf
						.append("select cr.cardHolderType from CardsDto cr where upper(cr.cardNumber) = "
								+ objDto.getCardNumber() + " ");
			}

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After CsrDAOImpl checkExistrecord()" + res);
		}

		catch (Exception e) {
			System.out.println("while checking Existing Record in CsrDAOImpl :"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  while checking Existing Record in CsrDAOImpl:" + e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;
	}
}
