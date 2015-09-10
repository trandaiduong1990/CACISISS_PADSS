package org.transinfo.cacis.bgprocess;

import java.util.Date;

import org.apache.log4j.Logger;
import org.transinfo.cacis.bgprocess.core.CardChangeClosing;

public class CardChangeClosingProcess {
	
	private static final Logger cardChangeCloseLog = Logger.getLogger("CardChangeCloseLog");
	
	public void doChangeClosing(String date){
		
		CardChangeClosing objCardChangeClosing = new CardChangeClosing();
		
		cardChangeCloseLog.info("Process start time " + new Date());
		
		try {
			
			objCardChangeClosing.closingProcess(date);
			
		} catch (Exception e) {
			cardChangeCloseLog.error(new Object(), e);
		}
		
		cardChangeCloseLog.info("Process finished time " + new Date());
		
	}
	
	// for testing and manual running
	public static void main(String[] args) {
		
		cardChangeCloseLog.info("Process start time  Manually Started at " + new Date());
		
		String strDate = "";
		CardChangeClosingProcess objCardChangeClosingProcess = new CardChangeClosingProcess();
		
		if((args != null) && (args.length == 1)){
			strDate = args[0];

			cardChangeCloseLog.info("Manually passed Date is " + strDate);
		}
		
		objCardChangeClosingProcess.doChangeClosing(strDate);
		
		cardChangeCloseLog.info("Process Manuall task Finished at " + new Date());
	}

}
