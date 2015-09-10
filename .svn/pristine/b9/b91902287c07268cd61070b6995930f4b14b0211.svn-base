package org.transinfo.cacis.bgprocess;

import java.util.Date;

import org.apache.log4j.Logger;
import org.transinfo.cacis.bgprocess.core.CardRenewal;

public class CardRenewalProcess {
	
	private static final Logger cardRenewalLog = Logger.getLogger("CardRenewalLog");
	
	public void doRenewal(String date){
		
		CardRenewal objCardRenewal = new CardRenewal();
		
		cardRenewalLog.info("Process start time " + new Date());
		
		try {
			
			objCardRenewal.renewalProcess(date);
			
		} catch (Exception e) {
			cardRenewalLog.error(new Object(), e);
		}
		
		cardRenewalLog.info("Process finished time " + new Date());
		
	}
	
	// for testing and manual running
	public static void main(String[] args) {
		
		cardRenewalLog.info("Process start time  Manually Started at " + new Date());
		
		String strDate = "";
		CardRenewalProcess objCardRenewalProcess = new CardRenewalProcess();
		
		if((args != null) && (args.length == 1)){
			strDate = args[0];

			cardRenewalLog.info("Manually passed Date is " + strDate);
		}
		
		objCardRenewalProcess.doRenewal(strDate);
		
		cardRenewalLog.info("Process Manuall task Finished at " + new Date());
	}

}
