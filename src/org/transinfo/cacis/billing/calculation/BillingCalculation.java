package org.transinfo.cacis.billing.calculation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.transinfo.cacis.billing.utils.BillingComparator;
import org.transinfo.cacis.billing.utils.BillingUtil;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.controller.billing.BillingManager;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.settings.CardProductRateDto;
import org.transinfo.cacis.dto.transaction.FeeCreditGLDto;
import org.transinfo.cacis.dto.transaction.TransactionCreditGLDto;

@SuppressWarnings("unchecked")
public class BillingCalculation {
	
	private static final Logger billLog = Logger.getLogger("BillLog");
	
	public static Map getCalculatedValue(List tranxList, CustomerAccountDto objCustomerAccountDto, Date billingDate, String joinedCardNos){
		Map billingCalculation = new HashMap();

		//// create the required local variables & assign the values

		try{
			
			// variables for this month bill calculations
			double billPayments = 0;
			double billCreditsReturnedPurchases = 0;
			
			double billPurchases = 0;
			double billCashAdvances = 0;
			double billCashAdvanceFees = 0;
			
			double billLateFee = 0;
			double billOverLimitFee = 0;
			
			double billPurchaseFinanceCharges = 0;
			double billCashFinanceCharges = 0;
			
			double billPurchaseBalance = 0;
			double billCashBalance = 0;
			double tempBillPurchaseBalance = 0;
			
			// for late fee and over limit fee
			boolean isLateFeeApplicable = true;
			boolean isOverLimitFeeApplicable = true;
			
			double billMinimumPayment = 0;
			double billNewBalance = 0;
			double billPreBalance = 0;
			
			// previous bill month end balances
			double previousPurchaseBalance = 0;
			Float objPreviousPurchaseBalance = objCustomerAccountDto.getPreviousPurhcaseBalance();
			if(objPreviousPurchaseBalance != null){
				previousPurchaseBalance = objPreviousPurchaseBalance.floatValue();
			}else{
				billLog.info("Account " + objCustomerAccountDto.getAccountId() +" objPreviousPurchaseBalance value is NULL");
			}
			
			double previousCashBalance = 0;
			Float objPreviousCashBalance = objCustomerAccountDto.getPreviousCashBalance();
			if(objPreviousCashBalance != null){
				previousCashBalance = objPreviousCashBalance.floatValue();
			}else{
				billLog.info("Account " + objCustomerAccountDto.getAccountId() +" objPreviousCashBalance value is NULL");
			}
			
			double previousTotalBalance = 0;
			Float objPreviousTotalBalance = objCustomerAccountDto.getPreviousBalance();
			if(objPreviousTotalBalance != null){
				previousTotalBalance = objPreviousTotalBalance.floatValue();
			}else{
				billLog.info("Account " + objCustomerAccountDto.getAccountId() +" objPreviousTotalBalance value is NULL");
			}
			
			double previousMinPayment = 0;
			Float objPreviousMinPayment = objCustomerAccountDto.getCurrentMinPaymentDue();
			if(objPreviousMinPayment != null){
				previousMinPayment = objPreviousMinPayment.floatValue();
			}else{
				billLog.info("Account " + objCustomerAccountDto.getAccountId() +" objPreviousMinPayment value is NULL");
			}
			
			double creditLimit = 0;
			Float objcreditLimit = objCustomerAccountDto.getCreditLimit();
			if(objcreditLimit != null){
				creditLimit = objcreditLimit.floatValue();
			}else{
				billLog.info("Account " + objCustomerAccountDto.getAccountId() +" objcreditLimit value is NULL");
			}
			
			// assign previous balances
			billPurchaseBalance = previousPurchaseBalance;
			billCashBalance = previousCashBalance;
	
			// per product for calculation
			float creditFinanceCharge = 0;
			float latePaymentFee = 0;
			int minRepaymentPercent = 0;
			float minRepaymentAmt = 0;
			int cashAdvanceCharge = 0;
			float cashFinaceCharge = 0;
			float cashChargeAmt = 0;
			float overLimitCharge = 0;
			int gracePeriod = 0;
			String minAmtCalMethod = "";
			float tranxFee = 0;
			
			CardProductRateDto objCardProductRateDto = null;
			
			// to store the cash transactions for calculation
			List cashTranxWithdrawal = new ArrayList();
			List cashTranxPayments = new ArrayList();
			List tempCashAdjPays = new ArrayList();
			List tempCashList = new ArrayList();
			
			// for appending transaction IDs to update
			StringBuffer tranxCreditIds = new StringBuffer();
			StringBuffer feesCreditIds = new StringBuffer();
		
			// just for an information in later
			billLog.info("Account ID is " + objCustomerAccountDto.getAccountId());
			//objCustomerAccountDto.printToLog(billLog);
		
			// initiate the manager class
			BillingManager objBillingManager = new BillingManager();
			
			// get one card number & get Product rates. Since for one account all cards will be same card product
			String[] cardArray = joinedCardNos.split(",");
			String cardNo = cardArray[0];
			cardNo = cardNo.substring(1, (cardNo.length()-1));
			objCardProductRateDto = objBillingManager.getCardProductRateDto(cardNo);
			
			// just for an information in later
			//billLog.info(objCardProductRateDto.toString());
			
			if(objCardProductRateDto != null){
				
				// assign the values
				creditFinanceCharge = objCardProductRateDto.getCreditFinanceCharge();
				latePaymentFee = objCardProductRateDto.getLatePaymentFee();
				minRepaymentPercent = objCardProductRateDto.getMinRepaymentPercent();
				minRepaymentAmt = objCardProductRateDto.getMinRepaymentAmt();
				cashAdvanceCharge = objCardProductRateDto.getCashAdvanceCharge();
				cashFinaceCharge = objCardProductRateDto.getCashFinaceCharge();
				cashChargeAmt = objCardProductRateDto.getCashChargeAmt();
				
				Float objOverLimitCharge = objCardProductRateDto.getOverLimitCharge();
				if(objOverLimitCharge != null){
					overLimitCharge = objOverLimitCharge.floatValue();
				}else{
					billLog.info("CardProductRate " + objCardProductRateDto.getCardProductId() +" objOverLimitCharge value is NULL");
				}
				
				Integer objGracePeriod = objCardProductRateDto.getGracePeriod();
				if(objGracePeriod != null){
					gracePeriod = objGracePeriod.intValue();
				}else{
					billLog.info("CardProductRate " + objCardProductRateDto.getCardProductId() +" objGracePeriod value is NULL");
				}
				
				minAmtCalMethod = objCardProductRateDto.getMinAmtCalMethod();
				if(minAmtCalMethod != null){
					// no action
				}else{
					minAmtCalMethod = ICacisiss.IBilling.MIN_CAl_METHOD_PER;
					billLog.info("CardProductRate " + objCardProductRateDto.getCardProductId() +" minAmtCalMethod value is NULL");
				}
				
				Float objTranxFee = objCardProductRateDto.getTranxFee();
				if(objTranxFee != null){
					tranxFee = objTranxFee.floatValue();
				}else{
					billLog.info("CardProductRate " + objCardProductRateDto.getCardProductId() +" objTranxFee value is NULL");
				}
				
				// previous transactions date
				Date leadingPurchaseTranxDate = BillingUtil.getStartDate(billingDate);
				Date leadingCashTranxDate = BillingUtil.getStartDate(billingDate);
				
				// calculate the bill and assign the values into variables
				Iterator itr = tranxList.iterator(); 
				while(itr.hasNext()) {
					TransactionCreditGLDto objTransactionCreditGLDto = (TransactionCreditGLDto)itr.next();
					
					// transaction ID
					String tranxId = objTransactionCreditGLDto.getTranxCreditGLId();
					// transaction type. SALE, CASH, REFUND
					String tranxType = objTransactionCreditGLDto.getTrnxType();
					// transaction date
					Date tranxDate = objTransactionCreditGLDto.getDateTime();
					// transaction amount
					double tranxAmt = Double.valueOf(objTransactionCreditGLDto.getAmount()).doubleValue();
					// GL type
					String glType = objTransactionCreditGLDto.getGlType();
					
					// check the transaction type
					if(tranxType != null){
						
						if(ICacisiss.IBilling.TRNX_SALE.equals(tranxType) || ICacisiss.IBilling.TRNX_REFUND.equals(tranxType)){
							// this is either purchase or refund
							
							long daysInCycle = 0;
							double tranxFC = 0;
							
							if(previousTotalBalance == 0){
								// purchases will not get finance charges within grace period
								
								// check that this transaction belongs within grace period
								boolean isBelongsGracePeriod = isTranxWithinGracePeriod(billingDate, gracePeriod, tranxDate);
								if(!isBelongsGracePeriod){
									// finance charges applicable. Calculate it
									
									if(billPurchaseBalance >= 0){
										
										// calculate number of days between previous and next transactions
										daysInCycle = getDaysDifferent(leadingPurchaseTranxDate, tranxDate);
										
										// calculate finance charges
										tranxFC = calculateFCForPurchases(billPurchaseBalance, creditFinanceCharge, daysInCycle);
										
										// add this with bill finance charges
										billPurchaseFinanceCharges = billPurchaseFinanceCharges + tranxFC;
									
									}
								}
								
							}else{
								// all transactions will get finance charges. Calculate it
								
								if(billPurchaseBalance >= 0){
									
									// calculate number of days between previous and next transactions
									daysInCycle = getDaysDifferent(leadingPurchaseTranxDate, tranxDate);
									
									// calculate finance charges
									tranxFC = calculateFCForPurchases(billPurchaseBalance, creditFinanceCharge, daysInCycle);
									
									// add this with bill finance charges
									billPurchaseFinanceCharges = billPurchaseFinanceCharges + tranxFC;
								
								}
							}
	
							// assign the leading transaction date
							leadingPurchaseTranxDate = objTransactionCreditGLDto.getDateTime();
							
							// add finance charges to bill purchase balance
							billPurchaseBalance = billPurchaseBalance + tranxFC;
							
							// check the type and sum with month amount
							if(ICacisiss.IBilling.TRNX_SALE.equals(tranxType)){
								billPurchases = billPurchases + tranxAmt;
								
								// summation this amount with month balance
								billPurchaseBalance = billPurchaseBalance + tranxAmt;
							}else if(ICacisiss.IBilling.TRNX_REFUND.equals(tranxType)){
								billCreditsReturnedPurchases = billCreditsReturnedPurchases + tranxAmt;
								
								// summation this amount with month balance
								billPurchaseBalance = billPurchaseBalance - tranxAmt;
							}
							
						}else if(ICacisiss.IBilling.TRNX_CASH.equals(tranxType)){
							// this is cash. may be cash withdrawal or payments
							
							// check for credit or debit
							if(ICacisiss.IBilling.DEBIT_GL.equals(glType)){
								
								// cash withdrawal. insert into list
								cashTranxWithdrawal.add(objTransactionCreditGLDto);
								
							}else if(ICacisiss.IBilling.CREDIT_GL.equals(glType)){
								// check for late fees
								
								// check that this transaction belongs within grace period
								if(isLateFeeApplicable){
									boolean isBelongsGracePeriod = isTranxWithinGracePeriod(billingDate, gracePeriod, tranxDate);
									
									if(isBelongsGracePeriod){
										
										if((billPayments + tranxAmt) >= previousMinPayment){
											isLateFeeApplicable = false;
										}
										
									}else{
										billLateFee = latePaymentFee;
										isLateFeeApplicable = false;
									}
								}
								
								// summation this amount with bill payments
								billPayments = billPayments + tranxAmt;
								
								// reduce the purchase balance
								billPurchaseBalance = billPurchaseBalance - tranxAmt;
								
								// insert into array list
								cashTranxPayments.add(objTransactionCreditGLDto);
							}
							
						}
						
						// check for over limit charges. It is one time charge
						if(isOverLimitFeeApplicable){
							
							double totalBalnceAftTranx = billPurchaseBalance;
							
							if(totalBalnceAftTranx > creditLimit){
								billOverLimitFee = overLimitCharge;
								isOverLimitFeeApplicable = false;
							}
							
						}
	
						// append transaction IDs for updating
						tranxCreditIds.append("'");
						tranxCreditIds.append(tranxId);
						tranxCreditIds.append("'");
						tranxCreditIds.append(",");
						
					}
				}
				
				// get end of billing date
				Date endOfBillingDate = BillingUtil.getEndDate(billingDate);
				
				// calculation from last transaction to end of billing date
				if(billPurchaseBalance >= 0){
					
					// calculate number of days between previous and next transactions
					long daysInCycle = getDaysDifferent(leadingPurchaseTranxDate, endOfBillingDate);
					
					// calculate finance charges
					double tranxFC = calculateFCForPurchases(billPurchaseBalance, creditFinanceCharge, daysInCycle);
					
					// add this with bill finance charges
					billPurchaseFinanceCharges = billPurchaseFinanceCharges + tranxFC;
					
					// add finance charges to bill purchase balance
					billPurchaseBalance = billPurchaseBalance + tranxFC;
					
					// check for over limit charges. It is one time charge
					if(isOverLimitFeeApplicable){
						
						double totalBalnceAftTranx = billPurchaseBalance;
						
						if(totalBalnceAftTranx > creditLimit){
							billOverLimitFee = overLimitCharge;
							isOverLimitFeeApplicable = false;
						}
						
					}
				
				}
				
				// calculate the late fee
				if(isLateFeeApplicable){
					if(previousMinPayment > billPayments){
						billLateFee = latePaymentFee;
						isLateFeeApplicable = false;
					}
				}
							
				// calculations for cash withdrawals
				if((billPurchaseBalance >= 0) || (cashTranxPayments.size() == 0)){
					
					// no complicate calculation. just loop and calculate cash finance charge
					Iterator cashItr = cashTranxWithdrawal.iterator(); 
					while(cashItr.hasNext()) {
						TransactionCreditGLDto objTransactionCreditGLDto = (TransactionCreditGLDto)cashItr.next();
						
						// transaction type. SALE, CASH, REFUND
						String tranxType = objTransactionCreditGLDto.getTrnxType();
						// transaction date
						Date tranxDate = objTransactionCreditGLDto.getDateTime();
						// transaction amount
						double tranxAmt = Double.valueOf(objTransactionCreditGLDto.getAmount()).doubleValue();
						
						// check the transaction type
						if(tranxType != null){
							// calculate finance charges
							
							// calculate number of days between previous and next transactions
							long daysInCycle = getDaysDifferent(leadingCashTranxDate, tranxDate);
							
							// calculate finance charges
							double tranxCashFC = calculateFCForCash(billCashBalance, cashFinaceCharge, daysInCycle);
							
							// calculate cash advance fees
							double cashAdvanceFeeTranx = calculateCashAdvanceFee(tranxAmt, cashAdvanceCharge);
							
							double cashAdvanceFeeAmtForTanx = 0;
							// check for minimum cash advance fees satisfaction
							if(cashAdvanceFeeTranx > cashChargeAmt){
								cashAdvanceFeeAmtForTanx = cashAdvanceFeeTranx;
							}else{
								cashAdvanceFeeAmtForTanx = cashChargeAmt;
							}

							// add this with bill finance charges
							billCashFinanceCharges = billCashFinanceCharges + tranxCashFC;
							
							// summation to cash advance fees
							billCashAdvanceFees = billCashAdvanceFees + cashAdvanceFeeAmtForTanx;
							
							// summation this amount with bill cash advances
							billCashAdvances = billCashAdvances + tranxAmt;
							
							// add amounts with balance
							billCashBalance = billCashBalance + tranxAmt + tranxCashFC + cashAdvanceFeeAmtForTanx;
							
							// assign the leading transaction date
							leadingCashTranxDate = objTransactionCreditGLDto.getDateTime();
							
						}
						
					}
					
					// calculation from last transaction to end of billing date
					if(billCashBalance > 0){
						
						// calculate number of days between previous and next transactions
						long daysInCycle = getDaysDifferent(leadingCashTranxDate, endOfBillingDate);
						
						// calculate finance charges
						double tranxCashFC = calculateFCForCash(billCashBalance, cashFinaceCharge, daysInCycle);
						
						// add this with bill finance charges
						billCashFinanceCharges = billCashFinanceCharges + tranxCashFC;
						
						// add amounts with balance
						billCashBalance = billCashBalance + tranxCashFC;
						
					}
					
				}else{
					
					// headache calculation goes here
					tempBillPurchaseBalance = billPurchaseBalance;
					
					for(int i = (cashTranxPayments.size() - 1); i >= 0; i-- ){
						TransactionCreditGLDto objTransactionCreditGLDto = (TransactionCreditGLDto)cashTranxPayments.get(i);
						
						// transaction amount
						double tranxAmt = Double.valueOf(objTransactionCreditGLDto.getAmount()).doubleValue();
						
						// temp summation
						double tempSum = tempBillPurchaseBalance + tranxAmt;
						
						if(tempSum >= 0){
							
							// change the object transaction amount
							objTransactionCreditGLDto.setAmount(String.valueOf(tempBillPurchaseBalance*(-1)));
							// add the object into temp list
							tempCashAdjPays.add(objTransactionCreditGLDto);
							// break the loop
							break;
							
						}else{

							// summation this amount with tempBillPurchaseBalance
							tempBillPurchaseBalance = tempBillPurchaseBalance + tranxAmt;
							
							// add the object into temp list and continue looping
							tempCashAdjPays.add(objTransactionCreditGLDto);
							
						}
					}
					
					// join both arrays into new array
					tempCashList.addAll(tempCashAdjPays);
					tempCashList.addAll(cashTranxWithdrawal);
					
					// sort this list by time
					Collections.sort(tempCashList, new BillingComparator());
					
					// loop this array and do the calculation
					Iterator cashTranxItr = tempCashList.iterator(); 
					while(cashTranxItr.hasNext()) {
						TransactionCreditGLDto objTransactionCreditGLDto = (TransactionCreditGLDto)cashTranxItr.next();
						
						// transaction date
						Date tranxDate = objTransactionCreditGLDto.getDateTime();
						// transaction amount
						double tranxAmt = Double.valueOf(objTransactionCreditGLDto.getAmount()).doubleValue();
						// GL type. D, C
						String glType = objTransactionCreditGLDto.getGlType();
						
						// calculate finance changes
						if(billCashBalance >= 0){
							
							// calculate number of days between previous and next transactions
							long daysInCycle = getDaysDifferent(leadingCashTranxDate, tranxDate);
							
							// calculate finance charges
							double tranxCashFC = calculateFCForCash(billCashBalance, cashFinaceCharge, daysInCycle);
							
							// add this with bill finance charges
							billCashFinanceCharges = billCashFinanceCharges + tranxCashFC;
							
							// add amounts with balance
							billCashBalance = billCashBalance + tranxCashFC;
						
						}
						
						// check for credit or debit
						if(ICacisiss.IBilling.DEBIT_GL.equals(glType)){
							
							// summation this amount with bill cash advances
							billCashAdvances = billCashAdvances + tranxAmt;
							
							// calculate cash advance fees
							double cashAdvanceFeeTranx = calculateCashAdvanceFee(tranxAmt, cashAdvanceCharge);
							
							double cashAdvanceFeeAmtForTanx = 0;
							// check for minimum cash advance fees satisfaction
							if(cashAdvanceFeeTranx > cashChargeAmt){
								cashAdvanceFeeAmtForTanx = cashAdvanceFeeTranx;
							}else{
								cashAdvanceFeeAmtForTanx = cashChargeAmt;
							}
							
							// summation to cash advance fees
							billCashAdvanceFees = billCashAdvanceFees + cashAdvanceFeeAmtForTanx;
							
							// summation this amount with billCashBalance
							billCashBalance = billCashBalance + tranxAmt + cashAdvanceFeeAmtForTanx;
							
						}else if(ICacisiss.IBilling.CREDIT_GL.equals(glType)){
	
							// summation this amount with billCashBalance
							billCashBalance = billCashBalance - tranxAmt;
							
						}
						
						// assign the leading transaction date
						leadingCashTranxDate = objTransactionCreditGLDto.getDateTime();
						
					}
					
					// calculation from last transaction to end of billing date
					if(billCashBalance > 0){
						
						// calculate number of days between previous and next transactions
						long daysInCycle = getDaysDifferent(leadingCashTranxDate, endOfBillingDate);
						
						// calculate finance charges
						double tranxCashFC = calculateFCForCash(billCashBalance, cashFinaceCharge, daysInCycle);
						
						// add this with bill finance charges
						billCashFinanceCharges = billCashFinanceCharges + tranxCashFC;
						
						// add amounts with balance
						billCashBalance = billCashBalance + tranxCashFC;
					
					}
					
				}
				
				// check for balances
				if((billPurchaseBalance < 0) && (previousCashBalance > 0 || cashTranxWithdrawal.size() > 0)){
					if(billCashBalance >= 0){
						billPurchaseBalance = 0;
					}else{
						billPurchaseBalance = billCashBalance;
						billCashBalance = 0;
					}
				}
				
				// get the records for cards from FEE_CREDIT_GL table & do the calculation with final values
				List feesCreditList = objBillingManager.getFeeRecordsForCards(joinedCardNos, billingDate);
				if(feesCreditList.size() > 0){
					
					// iterate and do the calculations
					Iterator feesItr = feesCreditList.iterator(); 
					while(feesItr.hasNext()) {
						FeeCreditGLDto objFeeCreditGLDto = (FeeCreditGLDto)feesItr.next();
	
						// transaction ID
						String tranxId = objFeeCreditGLDto.getFeeCreditGLId();
						// transaction type. SALE, CASH, REFUND
						String tranxType = objFeeCreditGLDto.getTrnxType();
						// transaction amount
						double tranxAmt = Double.valueOf(objFeeCreditGLDto.getAmount()).doubleValue();
						// GL type. D, C
						String glType = objFeeCreditGLDto.getGlType();
	
						if(tranxType != null){
							
							// check for credit or debit
							if(ICacisiss.IBilling.DEBIT_GL.equals(glType)){
								
								// summation this amount with billCashBalance
								billPurchaseBalance = billPurchaseBalance + tranxAmt;
								
							}else if(ICacisiss.IBilling.CREDIT_GL.equals(glType)){
		
								// don't know what to do
								
							}
							
							// append transaction IDs for updating
							feesCreditIds.append("'");
							feesCreditIds.append(tranxId);
							feesCreditIds.append("'");
							feesCreditIds.append(",");	
						
						}
					}
				}
				
				// update the database for transactions and fees records
				if((tranxCreditIds.toString().length() > 0) || (feesCreditIds.toString().length() > 0)){
					
					String tranxIds = "";
					String feeIds = "";
					
					if(tranxCreditIds.toString().length() > 0){
						billLog.info("tranxCreditIds = " + tranxCreditIds.toString());
						tranxIds = tranxCreditIds.toString().substring(0, (tranxCreditIds.toString().length() - 1));
					}
	
					if(feesCreditIds.toString().length() > 0){
						billLog.info("feesCreditIds = " + feesCreditIds.toString());
						feeIds = feesCreditIds.toString().substring(0, (feesCreditIds.toString().length() - 1));
					}
					
					// update the records both transaction and fees records
					boolean isSuccessUpdateRecords = objBillingManager.updateTranxFeeRecords(tranxIds, feeIds);
					if(isSuccessUpdateRecords){
						billLog.info("Records update success");
					}else{
						billLog.info("Records update failed");
					}
					
				}
	
				// calculate the minimum payment amount
				// some more variables for minimum payments calculations
				double totalFC = billPurchaseFinanceCharges + billCashFinanceCharges;
				double outstandingAmt = billPurchaseBalance + billCashBalance + billLateFee + billOverLimitFee;
				
				// total final purchase balance after add late fee and over limit fees
				double newBillPurchaseBalance = billPurchaseBalance + billLateFee + billOverLimitFee;
				
				// total balances
				billNewBalance = outstandingAmt;
				billPreBalance = previousPurchaseBalance+previousCashBalance;

				double minPayCalculated = 0;
				
				if(ICacisiss.IBilling.MIN_CAl_METHOD_PER.equals(minAmtCalMethod)){
					
					minPayCalculated = calculateMinimumAmtByPer(outstandingAmt, totalFC, minRepaymentPercent);
					
				}else if(ICacisiss.IBilling.MIN_CAl_METHOD_OCC.equals(minAmtCalMethod)){
					
					minPayCalculated = calculateMinimumAmtByOCC(outstandingAmt, tranxFee, creditFinanceCharge);
					
				}
				
				double tempMinPay = 0;
				if(minPayCalculated > minRepaymentAmt){
					tempMinPay = minPayCalculated;
				}else{
					tempMinPay = minRepaymentAmt;
				}
				
				if(outstandingAmt > creditLimit){
					tempMinPay = tempMinPay + (outstandingAmt - creditLimit);
				}
				
				billMinimumPayment = tempMinPay;
				
				Date gracePeriodDueDate = BillingUtil.getGracePeriodEndDate(billingDate, gracePeriod);
				
				// insert variables into HashMap
				billingCalculation.put(ICacisiss.IBilling.IParams.ACCOUNT_ID, objCustomerAccountDto.getAccountId());
				billingCalculation.put(ICacisiss.IBilling.IParams.PRE_BILLING_DATE, billingDate);
				billingCalculation.put(ICacisiss.IBilling.IParams.DUE_DATE, gracePeriodDueDate);
				billingCalculation.put(ICacisiss.IBilling.IParams.GRACE_PERIOD, gracePeriod);
				billingCalculation.put(ICacisiss.IBilling.IParams.PRE_PURCHASE_BALANCE, previousPurchaseBalance);
				billingCalculation.put(ICacisiss.IBilling.IParams.PRE_CASH_BALANCE, previousCashBalance);
				billingCalculation.put(ICacisiss.IBilling.IParams.CASH_ADVANCES, billCashAdvances);
				billingCalculation.put(ICacisiss.IBilling.IParams.LATE_FEE, billLateFee);
				billingCalculation.put(ICacisiss.IBilling.IParams.OVER_LIMIT_FEE, billOverLimitFee);
				billingCalculation.put(ICacisiss.IBilling.IParams.FINANCE_CHANGRE, totalFC);
				billingCalculation.put(ICacisiss.IBilling.IParams.NEW_PURCHASE_BALANCE, newBillPurchaseBalance);
				billingCalculation.put(ICacisiss.IBilling.IParams.NEW_CASH_BALANCE, billCashBalance);
				
				billingCalculation.put(ICacisiss.IBilling.IParams.PAYMENTS, billPayments);
				billingCalculation.put(ICacisiss.IBilling.IParams.CREDIT_RETURNED_PURCHASES, billCreditsReturnedPurchases);
				billingCalculation.put(ICacisiss.IBilling.IParams.PURCHASES, billPurchases);
				billingCalculation.put(ICacisiss.IBilling.IParams.CASH_ADVANCES, billCashAdvances);
				billingCalculation.put(ICacisiss.IBilling.IParams.CASH_ADVANCE_FEE, billCashAdvanceFees);
				billingCalculation.put(ICacisiss.IBilling.IParams.CASH_ADVANCE_FEE, billCashAdvanceFees);
				billingCalculation.put(ICacisiss.IBilling.IParams.MINIMUM_PAYMENT, billMinimumPayment);
	
				billingCalculation.put(ICacisiss.IBilling.IParams.PRE_BALANCE, billPreBalance);
				billingCalculation.put(ICacisiss.IBilling.IParams.NEW_BALANCE, billNewBalance);
				
			}else{
				billLog.error("Card Product Rate is NOT cofigured to " + cardNo + "'s card product");
			}
		
		}catch (Exception e) {
			billLog.error(e);
		}
		
		return billingCalculation;
	}
	
	private static long getDaysDifferent(Date startDate, Date endDate){
		long diffDays = 0;
		
		// make Hour, Minute, Second and Millisecond as 0
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		startCal.set(Calendar.HOUR_OF_DAY, 0);
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
		startCal.set(Calendar.MILLISECOND,0);

		// make Hour, Minute, Second and Millisecond as 0
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		endCal.set(Calendar.HOUR_OF_DAY, 0);
		endCal.set(Calendar.MINUTE, 0);
		endCal.set(Calendar.SECOND, 0);
		endCal.set(Calendar.MILLISECOND,0);
		
		// Get the represented date in milliseconds
		long startMilliSecs = startCal.getTime().getTime();
		long endMilliSecs = endCal.getTime().getTime();
		
		// Calculate difference in milliseconds
		long diff = endMilliSecs - startMilliSecs;
		
		// Calculate difference in days
		diffDays = diff / (24 * 60 * 60 * 1000);
		
		return diffDays;
	}
	
	private static double calculateFCForPurchases(double avgDailyBalance, double interestRate, long daysInCycle){
		double fc = 0;
		
		fc = (avgDailyBalance*interestRate*daysInCycle)/(365*100);
		fc = round(fc, 4);

		return fc;
	}
	
	private static double calculateFCForCash(double avgDailyBalance, double interestRate, long daysInCycle){
		double fc = 0;
		
		fc = (avgDailyBalance*interestRate*daysInCycle)/(12*100);
		fc = round(fc, 4);

		return fc;
	}
	
	private static double calculateMinimumAmtByPer(double outstandingAmt, double financeCharges, double percentage){
		double minAmt = 0;
		
		minAmt = ((outstandingAmt+financeCharges)*5)/100;
		minAmt = round(minAmt, 4);

		return minAmt;
	}
	
	private static double calculateMinimumAmtByOCC(double outstandingAmt, double tranxFee, double percentage){
		double minAmt = 0;
		
		minAmt = tranxFee + ((percentage*outstandingAmt)/(100*12)) + ((1*outstandingAmt)/(100*12));
		minAmt = round(minAmt, 4);

		return minAmt;
	}
	
	private static double calculateCashAdvanceFee(double amount, double interestRate){
		double cashAF = 0;
		
		cashAF = (amount*interestRate)/(12*100);
		cashAF = round(cashAF, 4);

		return cashAF;
	}
	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	private static boolean isTranxWithinGracePeriod(Date billingDate, int gracePeriod, Date tranxDate){
		boolean yesOrNo = false;
		
		Date lastBillingDate = BillingUtil.getStartDate(billingDate);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(lastBillingDate);
		cal.add(Calendar.DATE, gracePeriod+1);
		cal.add(Calendar.SECOND, -1);
		
		Date gracePeriodFinishDate = cal.getTime();
		
		if(tranxDate.before(gracePeriodFinishDate)){
			yesOrNo = true;
		}
		
		return yesOrNo;
	}

}
