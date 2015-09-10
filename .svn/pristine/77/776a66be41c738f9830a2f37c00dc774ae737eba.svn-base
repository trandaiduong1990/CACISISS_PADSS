package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CustomerScreenProcessDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.batchprocess.CardATMLinkDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CustomerLimitsDto;
import org.transinfo.cacis.dto.customerservice.CustomerScreenProcessDto;
import org.transinfo.cacis.dto.customerservice.CustomerScreenSearchDto;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings("unchecked")
public class CustomerScreenProcessDAOImpl extends BaseDAOImpl implements
CustomerScreenProcessDAO {

	public Collection search(CustomerScreenSearchDto objSearchDto, int pageNo)
	throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select apd.customerId, ");
			sbf.append("apd.customerName, cd.cardNumber, cd.status ");
			sbf.append("from CardsDto cd, ApplicationProcessDto apd, CardATMLinkDto cal ");
			sbf.append("where cd.customerId = apd.customerId ");
			sbf.append("and cd.issuerId = apd.issuerId ");
			sbf.append("and cd.cardNumber = cal.cardNumber ");

			if (objSearchDto.getCustomerName() != null && !objSearchDto.getCustomerName().equals("")) {
				sbf.append("and ( lower(apd.customerName) like '%" + objSearchDto.getCustomerName().toLowerCase() + "%' ) ");
			}

			if (objSearchDto.getCardNo() != null && !objSearchDto.getCardNo().equals("")) {
				sbf.append("and cd.cardNumber = " + objSearchDto.getCardNo() + " ");
			}

			if (objSearchDto.getStrFromDate() != null && !objSearchDto.getStrFromDate().equals("")) {
				String strFromDate = objSearchDto.getStrFromDate();
				sbf.append("and ( cd.effectiveDate >= TO_DATE('" + strFromDate + "', 'DD/MM/YYYY')) ");
			}

			if (objSearchDto.getStrToDate() != null && !objSearchDto.getStrToDate().equals("")) {
				String strToDate = objSearchDto.getStrToDate();
				sbf.append("and ( cd.effectiveDate <= TO_DATE('" + strToDate + "', 'DD/MM/YYYY')) ");
			}

			if (objSearchDto.getEmail() != null && !objSearchDto.getEmail().equals("")) {
				sbf.append("and apd.email = '" + objSearchDto.getEmail() + "' ");
			}

			if (objSearchDto.getAutoAccNo() != null && !objSearchDto.getAutoAccNo().equals("")) {
				sbf.append("and cal.autoPayAccount = '" + objSearchDto.getAutoAccNo() + "' ");
			}

			if (objSearchDto.getDob() != null && !objSearchDto.getDob().equals("")) {
				String strToDate = objSearchDto.getDob();
				sbf.append("and ( apd.dob = TO_DATE('" + strToDate + "', 'DD/MM/YYYY')) ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving data in CustomerScreenProcessDAOImpl in search method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objSearchCollection;
	}

	public Collection getAllCardsByAccountID(String accountId)
	throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		//StringBuffer sbf = new StringBuffer();
		try {

			/*sbf.append("select cd.cardNumber, cd.cardExpDate, cd.cardStatus, cd.eComEnable, cd.status, cht.description,  ");
			sbf.append("cd.cashUsed, cd.purchaseUsed ");
			sbf.append("from CardsDto cd, CardHolderTypeDto cht ");
			sbf.append("where cd.cardHolderType = cht.cardHolderTypeId ");
			sbf.append("and cd.accountId = '"+accountId+"' ");

			objSearchCollection = getSearchList(sbf.toString(), 0);*/

			Session session = HibernetFactory.currentSession();
			
			Query q = session.getNamedQuery("getCards");
            q.setString("accId", accountId);
            objSearchCollection = q.list();

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving data in CustomerScreenProcessDAOImpl in search method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objSearchCollection;
	}

	public CustomerScreenProcessDto getApplicationProcessDto(String customerId)
	throws TPlusException {

		ApplicationProcessDto objApplicationProcessDto = null;
		CustomerScreenProcessDto objAppProcessDto = new CustomerScreenProcessDto();

		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objApplicationProcessDto = (ApplicationProcessDto) session.get(
					ApplicationProcessDto.class, customerId);

			BeanUtils
			.copyProperties(objAppProcessDto, objApplicationProcessDto);

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: Error while getting CustomerScreenProcessDto data in getApplicationProcessDto method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objAppProcessDto;

	}

	public CardATMLinkDto getCardATMLinkDto(String cardNo)
	throws TPlusException {

		CardATMLinkDto objAtmLinkDto = null;

		//Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();

			objAtmLinkDto = (CardATMLinkDto) session.get(CardATMLinkDto.class, Long.valueOf(cardNo));

			//tx.commit();

		} catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: Error while getting CustomerScreenProcessDto data in getCardATMLinkDto method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objAtmLinkDto;

	}

	public CustomerLimitsDto getCustomerLimitsDto(String cardNo)
	throws TPlusException {

		CustomerLimitsDto objCustomerLimitsDto = null;

		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCustomerLimitsDto = (CustomerLimitsDto) session.get(CustomerLimitsDto.class,
					Long.valueOf(cardNo));

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(
					TPlusCodes.APPLICATION_ERROR,
					"Error: Error while getting CustomerScreenProcessDto data in getCustomerLimitsDto method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objCustomerLimitsDto;

	}

	public List getSuppCards(String cardNo, String accountNo) throws TPlusException {

		List cardSuppList = new ArrayList();
		//Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();
			Query qry = session.createQuery("from CardsDto cdto where cdto.cardNumber <> " + cardNo + " and cdto.accountId = '" + accountNo + "' and cdto.cardHolderType=2 ");
			cardSuppList = qry.list();

			//List listcardPromotions = qry.list();
			/*for (Iterator it = listcardPromotions.iterator(); it.hasNext();) {
				CardPromotionDto objDto = new CardPromotionDto();
				objDto = (CardPromotionDto) it.next();
				cardPromotionList.add(objDto);
			}*/
			//tx.commit();

		} catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl cardPromotionList  method :"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return cardSuppList;
	}

	public List getOtherCards(String cardNo, String accountNo) throws TPlusException {

		List cardSuppList = new ArrayList();
		//Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();
			Query qry = session.createQuery("from CardsDto cdto where cdto.cardNumber <> " + cardNo + " and cdto.accountId = '" + accountNo + "' ");
			cardSuppList = qry.list();

			//List listcardPromotions = qry.list();
			/*for (Iterator it = listcardPromotions.iterator(); it.hasNext();) {
				CardPromotionDto objDto = new CardPromotionDto();
				objDto = (CardPromotionDto) it.next();
				cardPromotionList.add(objDto);
			}*/
			//tx.commit();

		} catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl cardPromotionList  method :"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return cardSuppList;
	}

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

	public Collection customerHistoryNew(String custIdNumber)
	throws TPlusException {

		Collection historyCollection = new ArrayList();
		CommonDataBean objCommBean = null;
		try {

			Session session = HibernetFactory.currentSession();

			StringBuffer stbf = new StringBuffer();
			stbf.append("select cdto.cardNumber, cdto.cardProductsDto.cardProductName, cdto.cardProductsDto.cardProductType.cardProductType ");
			stbf.append("from CardsDto cdto, ApplicationProcessDto appdto ");
			stbf.append("where cdto.customerId = appdto.customerId ");
			stbf.append("and appdto.csn=:csnNo");

			List resultList = session.createQuery(stbf.toString()).setString("csnNo", custIdNumber).list();

			for (Iterator it = resultList.iterator(); it.hasNext();) {
				Object obj[] = (Object[]) it.next();
				objCommBean = new CommonDataBean();
				objCommBean.setColumn1(String.valueOf((Long) obj[0]));
				objCommBean.setColumn2((String) obj[1]);
				objCommBean.setColumn3((String) obj[2]);
				historyCollection.add(objCommBean);

			}

		} catch (Exception e) {
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

	public Collection getCardDetails(String appID) throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
			.append("select cd.cardNumber, cd.custAccountDto.creditLimit, cd.custAccountDto.creditLimit-cd.custAccountDto.limitUsed, ");
			sbf
			.append("cd.custAccountDto.limitUsed, to_char(cd.custAccountDto.lastStatementDueDate, 'DD/MM/YYYY') ");
			sbf
			.append("from ApplicationFormDto csd, CardsDto cd, ApplicationProcessDto apd ");
			sbf
			.append("where apd.applicationId = csd.applicationId  and apd.issuerId = csd.issuerId ");
			sbf
			.append("and cd.customerId = apd.customerId and cd.issuerId = apd.issuerId ");
			sbf.append("and csd.applicationId ='" + appID + "' ");

			objSearchCollection = getSearchList(sbf.toString(), 0);

		} catch (Exception e) {
			System.out
			.println("Error in while retrieving data in CustomerScreenProcessDAOImpl search method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving data in CustomerScreenProcessDAOImpl in search method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objSearchCollection;
	}

	public boolean update(CardATMLinkDto objCardATMLinkDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objCardATMLinkDto);

			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CustomerScreenProcessDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CustomerScreenProcessDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	public boolean updateCU(ApplicationProcessDto objAppProcessDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objAppProcessDto);

			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CustomerScreenProcessDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CustomerScreenProcessDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	public Double getTotNonReconAmt(String accountId) throws TPlusException{

		Double amt = 0.0;
		
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			sbf.append("select sum(tdto.tranxCurrCovAmt) from TransactionLogDto tdto ");
			sbf.append("where tdto.cardNumber in (select cdto.cardNumber from CardsDto cdto where cdto.accountId='"+accountId+"') ");
			sbf.append("and tdto.responseCode='00' ");
			sbf.append("and tdto.deleted='N' ");
			sbf.append("and tdto.recon='N' ");
			sbf.append("and tdto.amount>0 ");
			sbf.append("and tdto.tranxCode in ('CASH','SALE') ");

			Query qry = session.createQuery(sbf.toString());
			List listCards = qry.list();

			if (listCards.size() > 0) {
				
				if((String) listCards.get(0) != null){
					amt = Double.valueOf((String) listCards.get(0));
				}
			}

		} catch (Exception e) {
			System.out.println("Error in while retrieving data in CustomerScreenProcessDAOImpl search method" + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving data in CustomerScreenProcessDAOImpl in search method" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return amt;
	}

}
