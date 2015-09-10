package org.transinfo.cacis.billing;

import java.util.Date;

import org.apache.log4j.Logger;
import org.transinfo.cacis.billing.core.BillingBuilding;
import org.transinfo.cacis.billing.report.BillingReport;
import org.transinfo.cacis.billing.utils.BillingParams;

public class BillingSystem {
	
	private static final Logger billLog = Logger.getLogger("BillLog");
	
	public void doBilling(int dayOfMonth){
		
		billLog.info("Billing System Started at " + new Date());
		
		try {
			//loading properties file
			BillingParams.loadProperties();
			
			// create input stream from xml file
			BillingReport.createInputStream();
		} catch (Exception e) {
			billLog.error(new Object(), e);
		}
		
		BillingBuilding objBillingBuilding = new BillingBuilding();
		objBillingBuilding.prepareBillingSystem(dayOfMonth);
		
		billLog.info("Billing System Finished at " + new Date());
		
	}
	
	// for testing and manual running
	public static void main(String[] args) {
		
		billLog.info("Billing System Manually Started at " + new Date());
		
		int dayOfMonth = -1;
		BillingSystem objBillingSystem = new BillingSystem();
		
		if((args != null) && (args.length == 1)){
			
			String strDayOfMonth = args[0];
			dayOfMonth = Integer.valueOf(strDayOfMonth).intValue();

			billLog.info("Manually passed day of month is " + dayOfMonth);
		}
		
		objBillingSystem.doBilling(dayOfMonth);
		
		billLog.info("Billing System Manuall task Finished at " + new Date());
	}

}
