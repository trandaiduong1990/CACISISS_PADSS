package org.transinfo.cacis.dataacess.daoimpl.oracle.accounting;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.accounting.CardHolderPaymentDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.accounting.CardHolderPaymentDto;
import org.transinfo.cacis.dto.accounting.CustomerPayment;
import org.transinfo.cacis.dto.accounting.CustomerStatement;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.AccountAdjustmentDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;
import org.transinfo.cacis.model.CustomerService;
import org.transinfo.cacis.util.DBUtil;

@SuppressWarnings("unchecked")
public class CardHolderPaymentDAOImpl extends BaseDAOImpl implements  CardHolderPaymentDAO {

	private Logger logger = Logger.getLogger(CardHolderPaymentDAOImpl.class);


	/*
	 *  (non-Javadoc)
	 * @see org.transinfo.cacis.dataacess.dao.applicationforms.CardHolderPaymentDAO
	 * 	 * #search(org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms.CardHolderPaymentDto)
	 * this method is for Get all the  Data(for all customer service screens) using 
	 *  model's(CustomerSevice class getCustomerServiceData() method)
	 */ 
	public Collection search(CardHolderPaymentDto objDto)  throws TPlusException {

		Collection objSearchCollection =null;

		try	{

			if(objDto.getCardNumber()!=null){
				CustomerService objCustServ = new CustomerService();
				CustomerServiceDataBean objService = objCustServ.getCustomerServiceData(Long.parseLong(objDto.getCardNumber()));
				objSearchCollection = new ArrayList();
				objSearchCollection.add(objService);
			}

		}catch (Exception e)
		{
			System.out.println("Error in CardHolderPaymentDAOImpl search method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardHolderPaymentDAOImpl search  method :"+e);
		}
		finally
		{
		}
		return  objSearchCollection ;
	}
	/*
	 *  (non-Javadoc)
	 * @see org.transinfo.cacis.dataacess.dao.applicationforms.CardHolderPaymentDAO
	 * #add(org.transinfo.cacis.dto.applicationforms.CardHolderPaymentDto)
	 * this mehod is for inserting data into  settlements table tables 
	 */
	public boolean cardHolderPayment(OriginalRequestDto  objSettlementDto, String userId) throws TPlusException{

		boolean bolExecute=false;
		Transaction tx = null;
		try
		{
			Session session =HibernetFactory.currentSession();
			tx =session.beginTransaction();

			// saving to settlement tables table
			//session.save(objSettlementDto);

			String cardNo = objSettlementDto.getCardNumber();
			double payAmt = objSettlementDto.getAmountCurr();

			CardsDto objCardsDto = (CardsDto) session.get(CardsDto.class, Long.valueOf(cardNo));

			String lastStatementID = objCardsDto.getLastStatementId();

			float preLimitUsed = objCardsDto.getCustAccountDto().getLimitUsed();
			BigDecimal bigPreLimitUsed = BigDecimal.valueOf(Double.valueOf(String.valueOf(preLimitUsed)));
			bigPreLimitUsed = bigPreLimitUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			
			double purchaseUsed = objCardsDto.getCustAccountDto().getSaleUsed().doubleValue();
			BigDecimal bigPrePurchaseUsed = BigDecimal.valueOf(purchaseUsed);
			bigPrePurchaseUsed = bigPrePurchaseUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			double cashUsed = objCardsDto.getCustAccountDto().getCashUsed().doubleValue();
			BigDecimal bigPreCashUsed = BigDecimal.valueOf(cashUsed);
			bigPreCashUsed = bigPreCashUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			BigDecimal bigPayAmt = BigDecimal.valueOf(payAmt);
			bigPayAmt = bigPayAmt.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			BigDecimal bigLimitUsed = bigPreLimitUsed.subtract(bigPayAmt);

			//double purchaseAmt = 0;
			//double cashAmt = 0;

			Connection con = session.connection();
			CallableStatement cs = con.prepareCall("call CustomerPayment(?,?,?,?,?,?,?,?,?)");

			cs.setLong(1, Long.valueOf(cardNo));
			cs.setDouble(2, payAmt);
			cs.setString(3, "PAY");
			cs.registerOutParameter(4, OracleTypes.DOUBLE);
			cs.registerOutParameter(5, OracleTypes.DOUBLE);
			cs.registerOutParameter(6, OracleTypes.DOUBLE);
			cs.registerOutParameter(7, OracleTypes.DOUBLE);
			cs.registerOutParameter(8, OracleTypes.DOUBLE);
			cs.registerOutParameter(9, OracleTypes.DOUBLE);
			cs.execute();

			Double accPurUsed = cs.getDouble(4);
			Double accCashUsed = cs.getDouble(5);
			Double cardPurUsed = cs.getDouble(6);
			Double cardCashUsed = cs.getDouble(7);
			Double finalStatPurUsed = cs.getDouble(8);
			Double finalStatCashUsed = cs.getDouble(9);
			
			/*if (con != null) {
                con.commit();
            }*/

			// update cards
			objCardsDto.setCashUsed(cardCashUsed);
			objCardsDto.setPurchaseUsed(cardPurUsed);

			session.update(objCardsDto);

			CustomerStatement objCustomerStatement = null;
			// get last statement
			if(lastStatementID != null && !"".equals(lastStatementID)){
				objCustomerStatement = (CustomerStatement) session.get(CustomerStatement.class, lastStatementID);
			}

			if(objCustomerStatement != null){
				objCustomerStatement.setOutStandCashAmt(finalStatCashUsed);
				objCustomerStatement.setOutStandPurchaseAmt(finalStatPurUsed);
				objCustomerStatement.setOutStandStatAmt(finalStatCashUsed+finalStatPurUsed);

				session.update(objCustomerStatement);
			}

			AccountAdjustmentDto objAccountAdjustmentDto = new AccountAdjustmentDto();
			objAccountAdjustmentDto.setCard(objCardsDto);
			objAccountAdjustmentDto.setCardNo(Long.valueOf(cardNo));
			objAccountAdjustmentDto.setAdjustmentAmount(String.valueOf(payAmt));
			objAccountAdjustmentDto.setCreditOrDebit("C");
			objAccountAdjustmentDto.setEffectiveDate(new Date());
			objAccountAdjustmentDto.setReason("Admin Payment");
			objAccountAdjustmentDto.setAmtSrc("Admin");
			objAccountAdjustmentDto.setChgType("PAY");
			objAccountAdjustmentDto.setUpdatedDate(new Date());
			objAccountAdjustmentDto.setUserId(userId);
			objAccountAdjustmentDto.setPreLimitUsed(bigPreLimitUsed.doubleValue());
			objAccountAdjustmentDto.setLimitUsed(bigLimitUsed.doubleValue());
			objAccountAdjustmentDto.setPrePurchaseUsed(bigPrePurchaseUsed.doubleValue());
			objAccountAdjustmentDto.setPreCashUsed(bigPreCashUsed.doubleValue());
			objAccountAdjustmentDto.setPurchaseUsed(accPurUsed);
			objAccountAdjustmentDto.setCashUsed(accCashUsed);

			session.save(objAccountAdjustmentDto);

			/*
			if(cashUsed <= 0){
				purchaseAmt = payAmt;
			}else{
				if(purchaseUsed <= 0){
					if(payAmt > cashUsed){
						cashAmt = cashUsed;
						purchaseAmt = payAmt-cashUsed;
					}else{
						cashAmt = payAmt;
					}
				}else{
					if(payAmt > purchaseUsed){
						purchaseAmt = purchaseUsed;

						double tmpBal = payAmt-purchaseUsed;
						if(tmpBal > cashUsed){
							purchaseAmt = payAmt-cashUsed;
							cashAmt = cashUsed;
						}else{
							cashAmt = tmpBal;
						}

					}else{
						purchaseAmt = payAmt;
					}
				}
			}*/

			/*BigDecimal bigpurchaseAmt = BigDecimal.valueOf(purchaseAmt);
			bigpurchaseAmt = bigpurchaseAmt.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			BigDecimal bigcashAmt = BigDecimal.valueOf(cashAmt);
			bigcashAmt = bigcashAmt.setScale(2, BigDecimal.ROUND_HALF_EVEN);

			purchaseAmt = bigpurchaseAmt.doubleValue();
			cashAmt = bigcashAmt.doubleValue();*/

			CustomerPayment objCustomerPayment = new CustomerPayment();

			String paymentId = DBUtil.getSequenceNextValue("SEQ_CUSTOMER_PAYMENT");
			objCustomerPayment.setPaymentId(paymentId);

			objCustomerPayment.setCardNo(Long.valueOf(cardNo));
			objCustomerPayment.setAccountId(objCardsDto.getAccountId());
			objCustomerPayment.setPayMode("CCC");
			objCustomerPayment.setPayAmt(payAmt);
			objCustomerPayment.setPaySrc(ICacisiss.IFee.FEE_SRC);
			objCustomerPayment.setPayDate(new Date());
			objCustomerPayment.setAmtSign("C");
			objCustomerPayment.setBilled("N");
			objCustomerPayment.setUpdateBy(userId);
			objCustomerPayment.setUpdateDate(new Date());

			session.save(objCustomerPayment);

			String sql = "UPDATE CustomerAccountDto SET limitUsed =limitUsed-"+payAmt+", saleUsed="+accPurUsed.doubleValue()+", cashUsed="+accCashUsed.doubleValue()+" WHERE accountId=:accountId";
			int count = session.createQuery(sql)
			.setString("accountId", objCardsDto.getAccountId())
			.executeUpdate();

			if(count > 0){
				tx.commit();
				bolExecute=true;
			}
		}

		catch (Exception e)
		{
			System.out.println("Error: in CardHolderPaymentDAOIMPL cardHolderPayment method" +e.getMessage());
			logger.error(new Object(), e);
			if(tx!=null)
			{
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error:  in CardHolderPaymentDAOIMPL cardHolderPayment method"+e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return 	bolExecute;

	}
	public int checkExistrecord(Object commonObj)throws TPlusException{

		int res=0;
		try
		{ 
			StringBuffer sbf = new StringBuffer();
			Session session =HibernetFactory.currentSession();

			if(commonObj instanceof CardsDto){
				CardsDto objDto =(CardsDto)commonObj;
				sbf.append("select count(*) from CardsDto cr where cr.cardNumber= "+objDto.getCardNumber()+" ");
			}

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			res = ((Integer)list.get(0)).intValue();
			System.out.println("After CardHolderPaymentDAOIMPL checkExistrecord()" +res);
		}

		catch (Exception e)
		{
			System.out.println("Error in CardHolderPaymentDAOIMPL checkExistrecord method :" +e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error:  in CardHolderPaymentDAOIMPL checkExistrecord method :"+e);
		}

		finally
		{
			HibernetFactory.closeSession();
		}

		return 	res;
	}
}
