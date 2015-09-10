package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import oracle.jdbc.driver.OracleTypes;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.AccountAdjustmentDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.accounting.CustomerPayment;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.customerservice.AccountAdjustmentDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.util.DBUtil;

@SuppressWarnings({"unchecked","unused"})
public class AccountAdjustmentDAOImpl extends BaseDAOImpl implements AccountAdjustmentDAO {

	public CardsDto getCardDto(String cardNo) throws TPlusException {

		CardsDto objDto = null;
		Transaction tx = null;


		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardsDto) session.get(CardsDto.class, Long
					.valueOf(cardNo).longValue());
			//objDto.getCardProductsDto().getCardProductName();
			//objDto.getCardProductsDto().getCardType().getCardType();
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out
			.println("Error while getting CardsDto data in getCardDto() - AccountAdjustmentDAOImpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardsDto data in getCardDto() - AccountAdjustmentDAOImpl"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	public CardProductDto getCardProductDto(String cardProductId) throws TPlusException{
		CardProductDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardProductDto) session.get(CardProductDto.class, cardProductId);
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out
			.println("Error while getting CardProductDto data in getCardProductDto() - AccountAdjustmentDAOImpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardProductDto data in getCardProductDto() - AccountAdjustmentDAOImpl"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}



		return objDto;
	}


	public CustomerAccountDto getCustomerAccountDto(String accountId)
	throws TPlusException {
		System.out.println("+++++++++++getCustomerAccountDto() in AccountAdjustmentDAOImpl++++++++++++++");


		CustomerAccountDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CustomerAccountDto) session.get(CustomerAccountDto.class,
					accountId);
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while getting CustomerAccountDto data in getCustomerAccountDto method  DAO impl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CustomerAccountDto data in getCustomerAccountDto method DAO impl"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	public AccountAdjustmentDto getAccountAdjustmentDto(String cardNo) throws TPlusException {
		System.out.println("+++++++++++getAccountAdjustmentDto() in AccountAdjustmentDAOImpl++++++++++++++");

		AccountAdjustmentDto objDto = null;
		Transaction tx = null;
		System.out.println("getting AccountAdjustmentDto - AccountAdjustmentDAOImpl Card No : " + Long.valueOf(cardNo));

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			try{
				objDto = (AccountAdjustmentDto) session.get(AccountAdjustmentDto.class, Long
						.valueOf(cardNo).longValue());}
			catch(Exception e){
				System.out.println("exception in session.get  - AccountAdjustmentDAOImpl " +e);

			}
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out
			.println("Error while getting AccountAdjustmentDto data in getAccountAdjustmentDto method in DAO impl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardChangeDto data in getCardChangeDto method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	public boolean add(AccountAdjustmentDto objAccountAdjustmentDto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;

		Double adjustmentAmount = Double.valueOf(objAccountAdjustmentDto.getAdjustmentAmount());
		String creditOrDebit = objAccountAdjustmentDto.getCreditOrDebit();

		//double purchaseAmt = 0;
		//double cashAmt = 0;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			CardsDto objCardsDto = (CardsDto) session.get(CardsDto.class, Long.valueOf(objAccountAdjustmentDto.getCardNo()));

			float preLimitUsed = objCardsDto.getCustAccountDto().getLimitUsed();
			BigDecimal bigPreLimitUsed = BigDecimal.valueOf(Double.valueOf(String.valueOf(preLimitUsed)));
			bigPreLimitUsed = bigPreLimitUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			double purchaseUsed = objCardsDto.getCustAccountDto().getSaleUsed().doubleValue();
			BigDecimal bigPrePurchaseUsed = BigDecimal.valueOf(purchaseUsed);
			bigPrePurchaseUsed = bigPrePurchaseUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			double cashUsed = objCardsDto.getCustAccountDto().getCashUsed().doubleValue();
			BigDecimal bigPreCashUsed = BigDecimal.valueOf(cashUsed);
			bigPreCashUsed = bigPreCashUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			BigDecimal bigPayAmt = BigDecimal.valueOf(adjustmentAmount);
			bigPayAmt = bigPayAmt.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			Connection con = session.connection();
			CallableStatement cs = con.prepareCall("call CARDLIMITUSED(?,?,?,?,?,?,?,?,?)");

			String sign = "";
			if("C".equals(creditOrDebit)){
				sign = "-";
			}else{
				sign = "+";
			}
			
			cs.setLong(1, objCardsDto.getCardNumber());
			cs.setString(2, sign);
			cs.setString(3, objAccountAdjustmentDto.getTranxType());
			cs.setDouble(4, bigPayAmt.doubleValue());
			cs.registerOutParameter(5, OracleTypes.DOUBLE);
			cs.registerOutParameter(6, OracleTypes.DOUBLE);
			cs.registerOutParameter(7, OracleTypes.DOUBLE);
			cs.registerOutParameter(8, OracleTypes.DOUBLE);
			cs.registerOutParameter(9, OracleTypes.DOUBLE);
			cs.execute();
			
			Double accPurUsed = cs.getDouble(5);
			Double accCashUsed = cs.getDouble(6);
			Double cardPurUsed = cs.getDouble(7);
			Double cardCashUsed = cs.getDouble(8);
			Double limitUsed = cs.getDouble(9);

			// update cards
			objCardsDto.setCashUsed(cardCashUsed);
			objCardsDto.setPurchaseUsed(cardPurUsed);

			session.update(objCardsDto);

			objAccountAdjustmentDto.setCard(objCardsDto);
			objAccountAdjustmentDto.setAdjustmentAmount(String.valueOf(adjustmentAmount));
			objAccountAdjustmentDto.setCreditOrDebit(creditOrDebit);
			objAccountAdjustmentDto.setEffectiveDate(new Date());
			//objAccountAdjustmentDto.setReason("Admin Payment");
			objAccountAdjustmentDto.setAmtSrc("Admin Adj - "+objAccountAdjustmentDto.getTranxType());
			objAccountAdjustmentDto.setChgType("ADJ");
			objAccountAdjustmentDto.setUpdatedDate(new Date());
			objAccountAdjustmentDto.setUserId(objAccountAdjustmentDto.getUserId());
			objAccountAdjustmentDto.setPreLimitUsed(bigPreLimitUsed.doubleValue());
			objAccountAdjustmentDto.setLimitUsed(limitUsed.doubleValue());
			objAccountAdjustmentDto.setPrePurchaseUsed(bigPrePurchaseUsed.doubleValue());
			objAccountAdjustmentDto.setPreCashUsed(bigPreCashUsed.doubleValue());
			objAccountAdjustmentDto.setPurchaseUsed(accPurUsed);
			objAccountAdjustmentDto.setCashUsed(accCashUsed);

			session.save(objAccountAdjustmentDto);

			//Add entry in Customer Payment table
			Date  paymentDate = new Date();

			CustomerPayment objCustomerPayment= new CustomerPayment();

			String paymentId = DBUtil.getSequenceNextValue("SEQ_CUSTOMER_PAYMENT");
			objCustomerPayment.setPaymentId(paymentId);
			objCustomerPayment.setCardNo(Long.valueOf(objAccountAdjustmentDto.getCardNo()));
			objCustomerPayment.setAccountId(objCardsDto.getAccountId());
			objCustomerPayment.setPayMode("ADJ");
			objCustomerPayment.setPayAmt(Double.valueOf(objAccountAdjustmentDto.getAdjustmentAmount()));
			objCustomerPayment.setPayAdjAmt(Double.valueOf(objAccountAdjustmentDto.getAdjustmentAmount()));
			objCustomerPayment.setPaySrc("Account Adjustment");	
			objCustomerPayment.setPayDate(paymentDate);
			objCustomerPayment.setAmtSign(objAccountAdjustmentDto.getCreditOrDebit());
			objCustomerPayment.setBilled("N");
			objCustomerPayment.setFullpay("N");
			objCustomerPayment.setUpdateBy(objAccountAdjustmentDto.getUserId());
			objCustomerPayment.setUpdateDate(paymentDate);

			session.save(objCustomerPayment);

			String sql = "UPDATE CustomerAccountDto SET limitUsed ="+limitUsed.doubleValue()+", saleUsed="+accPurUsed.doubleValue()+", cashUsed="+accCashUsed.doubleValue()+" WHERE accountId=:accountId";
			int count = session.createQuery(sql)
			.setString("accountId", objCardsDto.getAccountId())
			.executeUpdate();

			if(count>0){
				tx.commit();
				boolAdd = true;
			}

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in AccountAdjustmentDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in AccountAdjustmentDtoImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	public Collection processSearch(AccountAdjustmentDto objSearchDto, int pageNo)
	throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {
			sbf.append("select ccd.cardNo, ccd.customerName, ccd.nric, ");
			sbf.append("to_char(ccd.updatedDate,'dd-MM-yyyy') FROM CardChangeDto ccd where 1=1 and ccd.status = 0 ");
			/*
			if (objSearchDto.getCardNo() > 0) {
				sbf.append("and ccd.cardNo like '%" + objSearchDto.getCardNo()
						+ "%' ");
			}
			if (objSearchDto.getCustomerName() != null
					&& !objSearchDto.getCustomerName().equals("")) {
				sbf.append("and lower(ccd.customerName) like '%"
						+ objSearchDto.getCustomerName().toLowerCase() + "%' ");
			}
			if (objSearchDto.getNric() != null
					&& !objSearchDto.getNric().equals("")) {
				sbf.append("and lower(ccd.nric) like '%"
						+ objSearchDto.getNric().toLowerCase() + "%' ");
			}

			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and ccd.customer.branchId = '" + objSearchDto.getBranchId() + "' ");
			}

			sbf.append("and ccd.customer.issuerId = '" + objSearchDto.getIssuerId() + "' ");
			 */
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
			.println("Error in  AccountAdjustmentDAOImpl processSearch Method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in  AccountAdjustmentDAOImpl processSearch  Method" + e);
		}

		return objSearchCollection;
	}

	public boolean accept(AccountAdjustmentDto objAccountAdjustmentDto) throws TPlusException {

		boolean accept = false;
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			/*
			String sql = "UPDATE CardChangeDto SET status =:status  WHERE cardNo=:cardNo";
			count = session.createQuery(sql).setInteger("status",
					CommonCodes.CARD_CHANGE_ACCEPTED).setLong("cardNo",
							objAccountAdjustmentDto.getCardNo()).executeUpdate();

			if (count > 0) {

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objAccountAdjustmentDto.getCardNo());
				objCardActivity.setActivity("Card Change Application Acccepted");
				objCardActivity.setStationIp(InetAddress.getLocalHost().getHostAddress());
				objCardActivity.setUserId(objAccountAdjustmentDto.getUserId());
				objCardActivity.setLastUpdatedBy(objAccountAdjustmentDto.getUserId());
				objCardActivity.setUpdatedDate(new Date());
				session.save(objCardActivity);

				// inserting into CardChanegLog table
				objCardChangeLogDto = new CardChangeLogDto();
				objCardChangeLogDto.setOldCarddNo(String.valueOf(objAccountAdjustmentDto.getCardNo()));
				objCardChangeLogDto.setOldCardProduct(objAccountAdjustmentDto.getExistCardProduct());
				objCardChangeLogDto.setNewCardProduct(objAccountAdjustmentDto.getChangeCardProduct());
				objCardChangeLogDto.setFlag(CommonCodes.LOG_OLD_NO_INSERTED);
				objCardChangeLogDto.setLastUpdatedDate(new Date());
				objCardChangeLogDto.setLastUpdateBy(objAccountAdjustmentDto.getUserId());
				session.save(objCardChangeLogDto);

				objCardsDto = (CardsDto) session.get(CardsDto.class, objAccountAdjustmentDto.getCardNo());
				String accId = objCardsDto.getAccountId();
				// get supplementary cards and insert into CardChangeLog table
				sbf.append("from CardsDto cdo ");
				sbf.append("where cdo.cardHolderType = 2 and cdo.accountId = '" + accId + "'");

				Query qry = session.createQuery(sbf.toString());
				objSearchCollection = (ArrayList) qry.list();

				for (Iterator it = objSearchCollection.iterator(); it.hasNext();) {
					CardsDto objCards = (CardsDto) it.next();

					// inserting into CardChanegLog table
					objCardChangeLogDto = new CardChangeLogDto();
					objCardChangeLogDto.setOldCarddNo(String.valueOf(objCards.getCardNumber()));
					objCardChangeLogDto.setOldCardProduct(objAccountAdjustmentDto.getExistCardProduct());
					objCardChangeLogDto.setNewCardProduct(objAccountAdjustmentDto.getChangeCardProduct());
					objCardChangeLogDto.setFlag(CommonCodes.LOG_OLD_NO_INSERTED);
					objCardChangeLogDto.setLastUpdatedDate(new Date());
					objCardChangeLogDto.setLastUpdateBy(objAccountAdjustmentDto.getUserId());

					session.save(objCardChangeLogDto);

				}
			}
			session.flush();

			tx.commit();
			if (count > 0)
				accept = true;
			 */
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while accepting card change in CardChangeDAOImpl AcceptMethod"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the card change in CardChangeDAOImpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	public boolean reject(AccountAdjustmentDto objAccountAdjustmentDto) throws TPlusException {

		boolean accept = false;
		Transaction tx = null;
		int count;

		CardActivityDto objCardActivity = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			/*
			String sql = "UPDATE CardChangeDto SET status =:status  WHERE cardNo=:cardNo";
			count = session.createQuery(sql).setInteger("status",
					CommonCodes.CARD_CHANGE_REJECTED).setLong("cardNo",
							objAccountAdjustmentDto.getCardNo()).executeUpdate();

			if (count > 0) {

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objAccountAdjustmentDto.getCardNo());
				objCardActivity.setActivity("Card Change Application Rejected");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objAccountAdjustmentDto.getUserId());
				objCardActivity.setLastUpdatedBy(objAccountAdjustmentDto.getUserId());
				objCardActivity.setUpdatedDate(new Date());

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
					.println("Error while accepting card change in objAccountAdjustmentDAOIMPL RejectMethod"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the card change in objAccountAdjustmentDAOImpl"
							+ e);
			 */
		} finally {

			HibernetFactory.closeSession();
		}

		return accept;
	}

	public Map getProductListForChange(String issuerID, String cardNo) throws TPlusException {

		Map cardProductList = new TreeMap();
		Transaction tx = null;
		StringBuffer sbf =new StringBuffer();
		CardsDto objCardsDto = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objCardsDto = (CardsDto)session.get(CardsDto.class, Long.valueOf(cardNo));
			String cardProductType = objCardsDto.getCardProductsDto().getCardProductType().getCardProductTypeId();

			sbf.append("From CardProductDto where issuerId ='" + issuerID + "' and cardProductType = "+cardProductType+" ");

			Query qry = session.createQuery(sbf.toString());

			List listCardproducts = qry.list();
			for (Iterator it = listCardproducts.iterator(); it.hasNext();) {
				CardProductDto objDto = new CardProductDto();
				objDto = (CardProductDto) it.next();
				cardProductList.put(objDto.getCardProductId(), objDto.getCardProductName());
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("while retrieving getProductListForChange in CardChangeDAOImpl"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getProductListForChange in CardChangeDAOImpl"
					+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cardProductList;
	}

}
