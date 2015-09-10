package org.transinfo.cacis.billing.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;

@SuppressWarnings("unchecked")
public class BillingUtil {
	
	private static final Logger billLog = Logger.getLogger("BillLog");
	private static String dateFormat = "yyyy.MM.dd HH.mm.ss";
	private static DateFormat df = new SimpleDateFormat(dateFormat);
	private static DateFormat monthfolderdf = new SimpleDateFormat("yyyyMM");
	private static String defaultLocation = ICacisiss.IBilling.BILL_DEFAULT_LOC;
	
	private static String billPath = defaultLocation;
	private static String billDatePath = defaultLocation;
	
	public static String sqlDateFormat = "YYYY.MM.DD hh24.mi.ss";
	
	public static Date getBillingDate(int dayOfMonth){

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		cal.set(Calendar.DATE, dayOfMonth);
        
        return cal.getTime();
		
	}
	
	public static int getDayOfMonth(){
		int dayOfMonth = 0;
		
		Calendar cal = Calendar.getInstance();
		dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		
		return dayOfMonth;
	}
	
	public static String getJoinedCardNos(List cardList){
		
		StringBuilder sb = new StringBuilder();
		
		Iterator itr = cardList.iterator(); 
		while(itr.hasNext()) {
			sb.append("'");
			sb.append(itr.next());
			sb.append("',");
		}
		
		return sb.toString().substring(0, sb.toString().length() - 1);
	}	

	public static Date getStartDate(Date billingDate){

		Calendar cal = Calendar.getInstance();
		cal.setTime(billingDate);
		
		cal.add(Calendar.MONTH, -1);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        
        return cal.getTime();
		
	}
	
	public static String getStrStartDate(Date billingDate){

		Calendar cal = Calendar.getInstance();
		cal.setTime(billingDate);
		
		cal.add(Calendar.MONTH, -1);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        
        return df.format(cal.getTime());
		
	}
	
	public static Date getEndDate(Date billingDate){

		Calendar cal = Calendar.getInstance();
		cal.setTime(billingDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        
        cal.add(Calendar.SECOND, -1);
        
        return cal.getTime();
		
	}
	
	public static String getStrEndDate(Date billingDate){

		Calendar cal = Calendar.getInstance();
		cal.setTime(billingDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        
        cal.add(Calendar.SECOND, -1);
        
        return df.format(cal.getTime());
		
	}	

	public static Date getGracePeriodEndDate(Date billingDate, int gracePeriod){

		Calendar cal = Calendar.getInstance();
		cal.setTime(billingDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        
        cal.add(Calendar.DATE, gracePeriod);
        
        return cal.getTime();
		
	}
	
	public static void dateFolderCreation(int dayOfMonth){
		
		try{
			
			String path = billPath + String.valueOf(dayOfMonth);
			
			File dir = new File(path);
			if(dir.exists()){
				billLog.info("Folder location '"+ path + "' exists");
				billDatePath = path+File.separator;
			}else{
				billLog.info("Folder location '"+ path + "' NOT exists");
				boolean isDayOfMonthFolderSuccess = dir.mkdir();
				if(isDayOfMonthFolderSuccess){
					billLog.info("Folder '" + dayOfMonth + "' creation success");
					billDatePath = path+File.separator;
				}else{
					billLog.info("Folder '" + dayOfMonth + "' creation NOT success");
				}
			}
			
		}catch (Exception e) {
			billLog.error(e);
		}
	}
	
	private static void dateFolderCreationOnDefaultLoc(Date billingDate){
		File defDir = new File(defaultLocation);
		if(defDir.exists()){
			billLog.info("Folder location '"+ defaultLocation + "' exists");
			monthSubFolderCreation(billingDate, defaultLocation);
		}else{
			billLog.info("Folder location '"+ defaultLocation + "' NOT exists");
			boolean isSuccess = defDir.mkdirs();
			if(isSuccess){
				billLog.info("Default location folder '" + defaultLocation + "' creation success");
				monthSubFolderCreation(billingDate, defaultLocation);
			}else{
				billLog.info("Default location folder '" + defaultLocation + "' creation NOT success");
			}
		}
	}
	
	public static void monthFolderCreation(Date billingDate){
		
		try{
			
			String configuredFolderLoc = BillingParams.billReportLocation;
			
			// check that bill report location is configured or NOT on the properties file
			if(configuredFolderLoc != null){
				File dir = new File(configuredFolderLoc);
				// check for File exists
				if(dir.exists()){
					// check that file or directory
					if(dir.isDirectory()){
						billLog.info("Folder location '"+ configuredFolderLoc + "' exists");
						String monthFolder = monthfolderdf.format(billingDate);
						
						File monthDir = new File(configuredFolderLoc+monthFolder);
						if(monthDir.exists()){
							billLog.info("Sub Folder '"+ monthFolder + "' exists on '" + configuredFolderLoc + "'. No need to create");
							billPath = configuredFolderLoc+monthFolder+File.separator;
						}else{
							boolean isSuccess = monthDir.mkdir();
							if(isSuccess){
								billLog.info("Sub folder '" + monthFolder + "' creation success");
								billPath = configuredFolderLoc+monthFolder+File.separator;
							}else{
								billLog.info("Sub folder '" + monthFolder + "' creation NOT success");
								dateFolderCreationOnDefaultLoc(billingDate);
							}
						}
					}else{
						billLog.warn("'" + configuredFolderLoc + "' is NOT directory");
						dateFolderCreationOnDefaultLoc(billingDate);
					}
				}else{
					billLog.warn("Folder location '"+ configuredFolderLoc + "' NOT exists");
					dateFolderCreationOnDefaultLoc(billingDate);
				}
			}else{
				billLog.warn("Bill report Location is NOT configured on propreties File");
				dateFolderCreationOnDefaultLoc(billingDate);
			}
			
		}catch (Exception e) {
			billLog.error(e);
		}
	}
	
	private static void monthSubFolderCreation(Date billingDate, String rootPath){
		String subFolder = monthfolderdf.format(billingDate);
		
		File subDir = new File(rootPath+subFolder);
		if(subDir.exists()){
			billLog.info("Sub Folder '"+ subFolder + "' exists on '" + defaultLocation + "'. No need to create");
			billPath = defaultLocation+subFolder+File.separator;
		}else{
			boolean isSubFolderSuccess = subDir.mkdir();
			if(isSubFolderSuccess){
				billLog.info("Sub folder '" + subFolder + "' creation success");
				billPath = defaultLocation+subFolder+File.separator;
			}else{
				billLog.info("Sub folder '" + subFolder + "' creation NOT success");
			}
		}
	}
	
	public static String accountNoFolderCreation(String accountID){
		
		String path = ICacisiss.IBilling.BILL_DEFAULT_LOC;
		
		try{
			
			path = billDatePath + accountID;
			
			File dir = new File(path);
			if(dir.exists()){
				billLog.info("Folder location '"+ path + "' exists");
			}else{
				billLog.info("Folder location '"+ path + "' NOT exists");
				boolean isAccountNoFolderSuccess = dir.mkdir();
				if(isAccountNoFolderSuccess){
					billLog.info("Folder '" + accountID + "' creation success");
				}else{
					billLog.info("Folder '" + accountID + "' creation NOT success");
				}
			}
			
		}catch (Exception e) {
			billLog.error(e);
		}
		
		return path;
	}
	
	public static String formattedDate(Date date){
		return df.format(date);
	}
	
	public static String getMonthFolderDateFormat(Date billingDate)
	{

		 String strOutDt = monthfolderdf.format(billingDate);
		 return strOutDt;
	}
	
	public static List getFeeRecordsForCards(String joinedCardNos, Date billingDate){
		List feeList = new ArrayList();
		
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from FeeCreditGLDto fcdo ");
			sbf.append("where fcdo.billed = 'N' ");
			sbf.append("and fcdo.cardNo IN ("+ joinedCardNos +") ");
			sbf.append("and fcdo.dateTime >= "+ getStartDate(billingDate) + " ");
			sbf.append("and fcdo.dateTime <= "+ getEndDate(billingDate) + " ");
			sbf.append("order by fcdo.dateTime ");

			Query qry = session.createQuery(sbf.toString());
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

	public static Map getReportParams(Map billingCalculation){
		Map reportParams = new HashMap();
		
		String preBillingDate = formattedDate((Date)billingCalculation.get(ICacisiss.IBilling.IParams.PRE_BILLING_DATE));
		String dueDate = formattedDate((Date)billingCalculation.get(ICacisiss.IBilling.IParams.DUE_DATE));
		String gracePeriod = String.valueOf(((Integer)billingCalculation.get(ICacisiss.IBilling.IParams.GRACE_PERIOD)));

		// assign required parameters for report
		reportParams.put(ICacisiss.IBilling.IReprtParams.BANKNAME, "Sample Bank");
		reportParams.put(ICacisiss.IBilling.IReprtParams.LOGO_PATH, "test path");
		
		reportParams.put(ICacisiss.IBilling.IReprtParams.PRE_BILLING_DATE, preBillingDate);
		reportParams.put(ICacisiss.IBilling.IReprtParams.DUE_DATE, dueDate);
		reportParams.put(ICacisiss.IBilling.IReprtParams.GRACE_PERIOD, gracePeriod);
		reportParams.put(ICacisiss.IBilling.IReprtParams.PRE_BALANCE, billingCalculation.get(ICacisiss.IBilling.IParams.PRE_BALANCE));
		reportParams.put(ICacisiss.IBilling.IReprtParams.PAYMENTS, billingCalculation.get(ICacisiss.IBilling.IParams.PAYMENTS));
		reportParams.put(ICacisiss.IBilling.IReprtParams.PURCHASE_OTHER_CHARGES, billingCalculation.get(ICacisiss.IBilling.IParams.PURCHASES));
		reportParams.put(ICacisiss.IBilling.IReprtParams.CREDITS, billingCalculation.get(ICacisiss.IBilling.IParams.CREDIT_RETURNED_PURCHASES));
		reportParams.put(ICacisiss.IBilling.IReprtParams.CASH_ADVANCE, billingCalculation.get(ICacisiss.IBilling.IParams.CASH_ADVANCES));
		reportParams.put(ICacisiss.IBilling.IReprtParams.CASH_ADVANCE_FEE, billingCalculation.get(ICacisiss.IBilling.IParams.CASH_ADVANCE_FEE));
		reportParams.put(ICacisiss.IBilling.IReprtParams.LATE_FEE, billingCalculation.get(ICacisiss.IBilling.IParams.LATE_FEE));
		reportParams.put(ICacisiss.IBilling.IReprtParams.OVER_LIMIT_FEE, billingCalculation.get(ICacisiss.IBilling.IParams.OVER_LIMIT_FEE));
		reportParams.put(ICacisiss.IBilling.IReprtParams.FINANCE_CHARGES, billingCalculation.get(ICacisiss.IBilling.IParams.FINANCE_CHANGRE));
		reportParams.put(ICacisiss.IBilling.IReprtParams.NEW_BALANCE, billingCalculation.get(ICacisiss.IBilling.IParams.NEW_BALANCE));
		reportParams.put(ICacisiss.IBilling.IReprtParams.MINIMUM_PAYMENT, billingCalculation.get(ICacisiss.IBilling.IParams.MINIMUM_PAYMENT));
		
		return reportParams;
	}
	
	public static void main(String[] args) {
		System.out.println(BillingUtil.getDayOfMonth());
	}

}
