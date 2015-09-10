package org.transinfo.cacis.bgprocess;

import java.util.Date;

import org.apache.log4j.Logger;
import org.transinfo.cacis.bgprocess.core.CardClosing;

public class CardClosingProcess {
	
	private static final Logger cardCloseLog = Logger.getLogger("CardCloseLog");
	
	public void doClosing(String date){
		
		CardClosing objCardClosing = new CardClosing();
		
		cardCloseLog.info("Process start time " + new Date());
		System.out.println("Process start time " + new Date());
		
		try {
			
			objCardClosing.closingProcess(date);
			
		} catch (Exception e) {
			cardCloseLog.error(new Object(), e);
		}
		
		cardCloseLog.info("Process finished time " + new Date());
		System.out.println("Process finished time " + new Date());
		
	}
	
	// for testing and manual running
	public static void main(String[] args) {
		
		cardCloseLog.info("Process start time  Manually Started at " + new Date());
		
		String strDate = "";
		CardClosingProcess objCardClosingProcess = new CardClosingProcess();
		
		if((args != null) && (args.length == 1)){
			strDate = args[0];

			cardCloseLog.info("Manually passed Date is " + strDate);
		}
		
		objCardClosingProcess.doClosing(strDate);
		
		cardCloseLog.info("Process Manuall task Finished at " + new Date());
	}

}
