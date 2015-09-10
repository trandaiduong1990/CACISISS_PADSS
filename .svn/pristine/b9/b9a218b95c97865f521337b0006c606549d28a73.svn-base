package org.transinfo.cacis.billing.core;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.transinfo.cacis.billing.calculation.BillingCalculation;
import org.transinfo.cacis.billing.report.BillingReport;
import org.transinfo.cacis.billing.utils.BillingUtil;
import org.transinfo.cacis.controller.billing.BillingManager;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;

@SuppressWarnings("unchecked")
public class BillingBuilding {
	
	private static final Logger billLog = Logger.getLogger("BillLog");
	
	public void prepareBillingSystem(int dayOfMonthPassed){
		
		try{
			
		// billing date
		Date billingDate = new Date();
		if(dayOfMonthPassed != -1){
			billingDate = BillingUtil.getBillingDate(dayOfMonthPassed);
		}
		billLog.info("Billing Date is "+ BillingUtil.formattedDate(billingDate));
		
		// month folder create
		BillingUtil.monthFolderCreation(billingDate);
			
		int dayOfMonth = -1;
		
		// get billing day of month
		if(dayOfMonthPassed == -1){
			dayOfMonth = BillingUtil.getDayOfMonth();
		}else{
			dayOfMonth = dayOfMonthPassed;
		}
		billLog.info("Day of Month is "+ dayOfMonth);
		
		// initiate the billing manager class
		BillingManager objBillingManager = new BillingManager();
		
		// get cycle no by day of month
		int cycleNo = objBillingManager.getCycleNo(dayOfMonth);
		billLog.info("Cycle no for Day of Month is "+ cycleNo);
		
		if(cycleNo != -1){
			
			// get all accounts for this cycle no
			List accountList = objBillingManager.getAccountList(cycleNo);
			billLog.info("No of accounts for this cycle no is "+ accountList.size());
			
			if(accountList.size() > 0){
				
				// date folder create
				BillingUtil.dateFolderCreation(dayOfMonth);
				
				Iterator itr = accountList.iterator(); 
				while(itr.hasNext()) {
					CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto)itr.next();
					String accountID = objCustomerAccountDto.getAccountId();
					billLog.info("Account no is " + accountID + " - " +objCustomerAccountDto.getAccountName());
					
					// get all the credit card for this account
					List cardsList = objBillingManager.getCreditCardListForAccount(accountID);
					billLog.info("No of credit cards for this account no is "+ cardsList.size());
					
					if(cardsList.size() > 0){
						
						// join card number to use IN clause
						String joinedCardNos = BillingUtil.getJoinedCardNos(cardsList);
						billLog.info("Joined cards are "+ joinedCardNos);
						
						// get transaction records
						List tranxList = objBillingManager.getTranxRecordsForCards(joinedCardNos, billingDate);
						billLog.info("No of transactions for all cards are "+ tranxList.size());
						
						if(tranxList.size() > 0){
							
							// object creation
							BillingReport objBillingReport = new BillingReport();
							
							// do the billing calculations and update the transactions
							Map<String, Object> billingCalculation = BillingCalculation.getCalculatedValue(tranxList, objCustomerAccountDto, billingDate, joinedCardNos);
							
							if(!billingCalculation.isEmpty()){
								
								// get report parameters
								Map reportParams = BillingUtil.getReportParams(billingCalculation);
								
								// account folder create
								String path = BillingUtil.accountNoFolderCreation(accountID);
								
								// loop cards and get transaction records per card as result set
								Iterator itrCards = cardsList.iterator(); 
								while(itrCards.hasNext()) {
									String cardNo = String.valueOf(itrCards.next());
									billLog.info("Credit Cards no ("+ cardNo + ")");
									
									ResultSet objResultSet = objBillingReport.getReportData(billingDate, cardNo);
									if(objResultSet != null){
										
										if (!objResultSet.isBeforeFirst()) {
											billLog.info("No transactions records found for this card ("+ cardNo + ") ");
										}else{
											billLog.info("transactions records found for this card ("+ cardNo + ") ");
											
											// report generate and save it into folder
											objBillingReport.exportReport(objResultSet, path, cardNo, reportParams);
										}
										
									}else{
										billLog.info("Result set is NULL");
									}
								}
								
								// update customer_account
								boolean isSuccessUpdate = objBillingManager.updateDatabase(accountID, billingCalculation);
								if(isSuccessUpdate){
									billLog.info("Account " + accountID + " updated and inserted successfuly");
								}else{
									billLog.info("Account " + accountID + " updated failed");
									billLog.info("Account parameters are: \n");
									
									for (Map.Entry<String, Object> entry : billingCalculation.entrySet()) {
										billLog.info("\t"+ entry.getKey() + " --> " + entry.getValue());
									}
								}
								
							}else{
								billLog.info("Something wrong with calculation method for account (" + accountID + ") ");
							}
							
						}else{
							billLog.info("No transactions for this billing month from "+ BillingUtil.getStrStartDate(billingDate) + " to "+ BillingUtil.getStrEndDate(billingDate));
						}
						
					}else{
						billLog.info("Credit Cards NOT found for this Account no ("+ accountID + ")");
					}
		
				} 
				
			}else{
				billLog.info("Accounts NOT found for this cycle no ("+ cycleNo + ")");
			}
			
		}else{
			billLog.info("Cycle no is NOT configured to this day of month ("+ dayOfMonth + ")");
		}
		
		}catch (Exception e) {
			billLog.error(new Object(), e);
		}
	}

}
