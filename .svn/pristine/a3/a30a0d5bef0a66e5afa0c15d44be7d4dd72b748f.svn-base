package org.transinfo.cacis.dataacess.daoimpl.oracle.billing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.billing.utils.BillingUtil;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.billing.BillingDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.settings.CardProductRateDto;
import org.transinfo.cacis.dto.transaction.AccountBillingGLDto;

@SuppressWarnings("unchecked")
public class BillingDAOImpl extends BaseDAOImpl implements BillingDAO {

	private static final Logger billLog = Logger.getLogger("BillLog");
	
	public int getCycleNo(int dayOfMonth){
		int cycleNo = -1;
		
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select cdo.cycleNo ");
			sbf.append("from CycleDto cdo ");
			sbf.append("where cdo.dayOfMonth = " + dayOfMonth);

			Query qry = session.createQuery(sbf.toString());
			List listCycls = qry.list();

			if (listCycls.size() > 0) {
				cycleNo = ((Integer) listCycls.get(0)).intValue();
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getCycleNo method" + e);
			billLog.error("Error in getCycleNo method" + e);
			
		} finally {
			HibernetFactory.closeSession();
		}
		
		return cycleNo;
	}

	public List getAccountList(int cycleNo) throws TPlusException {
		List accList = new ArrayList();

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CustomerAccountDto cado ");
			sbf.append("where cado.cycleNo = " + cycleNo);

			Query qry = session.createQuery(sbf.toString());
			accList = qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			billLog.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BillingDAOImpl getCardProductRateDto method :"
							+ e);

		} finally {
			HibernetFactory.closeSession();
		}

		return accList;
	}

	public List getCreditCardListForAccount(String accountId)
			throws TPlusException {
		List cardList = new ArrayList();

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select cdo.cardNumber ");
			sbf.append("from CardsDto cdo, CardProductDto cpdo, ");
			sbf.append("CardProductTypeDto cptdo ");
			sbf.append("where cdo.cardProductId = cpdo.cardProductId ");
			sbf.append("and cpdo.cardProductType = cptdo.cardProductTypeId ");
			sbf.append("and cdo.cardStatusId = 0 ");
			sbf.append("and cdo.status <> 'C' ");
			sbf.append("and cptdo.cardProductType = '"
					+ ICacisiss.IBilling.CREDIT_CARD + "' ");
			sbf.append("and cdo.accountId = '" + accountId + "' ");

			Query qry = session.createQuery(sbf.toString());
			cardList = qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			billLog.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BillingDAOImpl getCardProductRateDto method :"
							+ e);

		} finally {
			HibernetFactory.closeSession();
		}

		return cardList;
	}

	public List getTranxRecordsForCards(String joinedCardNos, Date billingDate)
			throws TPlusException {
		List tranxList = new ArrayList();

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from TransactionCreditGLDto tcdo ");
			sbf.append("where tcdo.billed = 'N' ");
			sbf.append("and tcdo.cardNo IN (" + joinedCardNos + ") ");
			sbf.append("and tcdo.dateTime >= :startDate ");
			sbf.append("and tcdo.dateTime <= :endDate ");
			sbf.append("order by tcdo.dateTime ");

			Query qry = session.createQuery(sbf.toString())
							.setDate("startDate", BillingUtil.getStartDate(billingDate))
							.setDate("endDate", BillingUtil.getEndDate(billingDate));
			
			tranxList = qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			billLog.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BillingDAOImpl getCardProductRateDto method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return tranxList;
	}

	public CardProductRateDto getCardProductRateDto(String cardNo)
			throws TPlusException {

		CardProductRateDto objCardProductRateDto = null;
		CardsDto objCardsDto = null;

		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardsDto = (CardsDto) session.get(CardsDto.class, Long
					.valueOf(cardNo));
			String cardProductId = objCardsDto.getCardProductId();

			sbf.append("from CardProductRateDto cprdo ");
			sbf.append("where cprdo.cardProduct.cardProductId = '" + cardProductId + "' ");
			sbf.append("and cprdo.startDt <= :startDate ");
			sbf.append("and cprdo.endDt >= :endDate ");

			Query qry = session.createQuery(sbf.toString())
							.setDate("startDate", new Date())
							.setDate("endDate", new Date());
			
			List proRateList = qry.list();

			if (proRateList.size() > 0) {
				objCardProductRateDto = (CardProductRateDto) proRateList.get(0);
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			billLog.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BillingDAOImpl getCardProductRateDto method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objCardProductRateDto;
	}

	public List getFeeRecordsForCards(String joinedCardNos, Date billingDate) {
		List feeList = new ArrayList();

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from FeeCreditGLDto fcdo ");
			sbf.append("where fcdo.billed = 'N' ");
			sbf.append("and fcdo.cardNo IN (" + joinedCardNos + ") ");
			sbf.append("and fcdo.dateTime >= :startDate ");
			sbf.append("and fcdo.dateTime <= :endDate ");
			sbf.append("order by fcdo.dateTime ");

			Query qry = session.createQuery(sbf.toString())
							.setDate("startDate", BillingUtil.getStartDate(billingDate))
							.setDate("endDate", BillingUtil.getEndDate(billingDate));
			
			feeList = qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getTranxRecordsForCards method" + e);
			billLog.error("Error in getTranxRecordsForCards method" + e);

		} finally {
			HibernetFactory.closeSession();
		}

		return feeList;
	}

	public boolean updateDatabase(String accountID, Map params) {

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		int count = 0;
		boolean res = false;
		AccountBillingGLDto objAccountBillingGLDto = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// insert into ACCOUNT_BILLING_GL table
			objAccountBillingGLDto = new AccountBillingGLDto();
			objAccountBillingGLDto.setAccountId((String) params.get(ICacisiss.IBilling.IParams.ACCOUNT_ID));
			objAccountBillingGLDto.setBillingDate((Date) params.get(ICacisiss.IBilling.IParams.PRE_BILLING_DATE));
			objAccountBillingGLDto.setDueDate((Date) params.get(ICacisiss.IBilling.IParams.DUE_DATE));
			objAccountBillingGLDto.setGracePeriod((Integer) params.get(ICacisiss.IBilling.IParams.GRACE_PERIOD));
			objAccountBillingGLDto.setPreviousPurchaseBalance((Double) params.get(ICacisiss.IBilling.IParams.PRE_PURCHASE_BALANCE));
			objAccountBillingGLDto.setPreviousCashBalance((Double) params.get(ICacisiss.IBilling.IParams.PRE_CASH_BALANCE));
			objAccountBillingGLDto.setCashAdvances((Double) params.get(ICacisiss.IBilling.IParams.CASH_ADVANCES));
			objAccountBillingGLDto.setLateFee((Double) params.get(ICacisiss.IBilling.IParams.LATE_FEE));
			objAccountBillingGLDto.setOverLimitFee((Double) params.get(ICacisiss.IBilling.IParams.OVER_LIMIT_FEE));
			objAccountBillingGLDto.setFinanceCharges((Double) params.get(ICacisiss.IBilling.IParams.FINANCE_CHANGRE));
			objAccountBillingGLDto.setNewPurchaseBalance((Double) params.get(ICacisiss.IBilling.IParams.NEW_PURCHASE_BALANCE));
			objAccountBillingGLDto.setNewCashBalance((Double) params.get(ICacisiss.IBilling.IParams.NEW_CASH_BALANCE));
			objAccountBillingGLDto.setMinimumPayment((Double) params.get(ICacisiss.IBilling.IParams.MINIMUM_PAYMENT));
			
			// for information
			//billLog.info(objAccountBillingGLDto.toString());
			
			// save into session
			session.save(objAccountBillingGLDto);
			
			float prePurchaseBal = ((Double)params.get(ICacisiss.IBilling.IParams.NEW_PURCHASE_BALANCE)).floatValue();
			float preCashBal = ((Double)params.get(ICacisiss.IBilling.IParams.NEW_CASH_BALANCE)).floatValue();
			float totBalance = prePurchaseBal + preCashBal;
			float minPay = ((Double)params.get(ICacisiss.IBilling.IParams.MINIMUM_PAYMENT)).floatValue();
			
			// updating in CUSTOMER_ACCOUNT table
			sbf.append("UPDATE CustomerAccountDto ");
			sbf.append("SET ");
			sbf.append("previousPurhcaseBalance=:prePurchaseBalance, ");
			sbf.append("previousCashBalance=:preCashBalance, ");
			sbf.append("previousBalance=:preTotBalance, ");
			sbf.append("currentMinPaymentDue=:minPayment ");
			sbf.append("WHERE ");
			sbf.append("accountId=:accId ");

			count = session.createQuery(sbf.toString())
						.setString("accId", accountID)
						.setFloat("prePurchaseBalance", prePurchaseBal)
						.setFloat("preCashBalance", preCashBal)
						.setFloat("preTotBalance", totBalance)
						.setFloat("minPayment", minPay)
						.executeUpdate();

			// commit the transaction to database
			tx.commit();
			if (count > 0) {
				res = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in updateAccount method" + e);
			billLog.error("Error in updateAccount method" + e);

		} finally {
			HibernetFactory.closeSession();
		}

		return res;
	}
	
	public boolean updateTranxFeeRecords(String tranxIds, String feeIds) {

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		int countT = 0;
		int countF = 0;
		boolean res = false;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			if(tranxIds.length() > 0){
				
				// updating in TRANSACTION_CREDIT_GL table
				sbf.append("UPDATE TransactionCreditGLDto ");
				sbf.append("SET ");
				sbf.append("billed = 'Y' ");
				sbf.append("WHERE ");
				sbf.append("tranxCreditGLId IN (" + tranxIds + ") ");
	
				countT = session.createQuery(sbf.toString()).executeUpdate();
				
			}else{
				countT = 1;
			}
			

			if(feeIds.length() > 0){
				
				// updating in FEE_CREDIT_GL table
				sbf = new StringBuffer();
				sbf.append("UPDATE FeeCreditGLDto ");
				sbf.append("SET ");
				sbf.append("billed = 'Y' ");
				sbf.append("WHERE ");
				sbf.append("feeCreditGLId IN (" + feeIds + ") ");
	
				countF = session.createQuery(sbf.toString()).executeUpdate();
			
			}else{
				countF = 1;
			}

			tx.commit();
			if (countT > 0 && countF > 0) {
				res = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in updateAccount method" + e);
			billLog.error("Error in updateAccount method" + e);

		} finally {
			HibernetFactory.closeSession();
		}

		return res;
	}
	
	// for testing
	public static void main(String[] args) {
		BillingDAOImpl objBillingDAOImpl = new BillingDAOImpl();
		try {
			objBillingDAOImpl.getCardProductRateDto("6221590000000018");
		} catch (TPlusException e) {
			e.printStackTrace();
		}
	}

}
