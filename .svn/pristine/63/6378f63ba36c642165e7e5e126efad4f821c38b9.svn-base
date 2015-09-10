package org.transinfo.cacis.bgprocess.core;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.bgprocess.utils.BgprocessUtils;
import org.transinfo.cacis.bgprocess.utils.DateValidator;
import org.transinfo.cacis.controller.bgprocess.BgProcessManger;
import org.transinfo.cacis.dto.customerservice.CardChangeCloseDto;

@SuppressWarnings("unchecked")
public class CardChangeClosing {
	
	private static final Logger cardChangeCloseLog = Logger.getLogger("CardChangeCloseLog");
	
	public void closingProcess(String date) throws TPlusException{
		
		Date processDate = null;
		
		if(date != null && !"".equals(date)){
			cardChangeCloseLog.info("Manual Batch Process. Parsed Date is " + date);
			
			// validate the date format
			DateValidator objDateValidator = new DateValidator();
			boolean isValidDate = objDateValidator.validate(date);
			
			if(isValidDate){
				cardChangeCloseLog.info(date + " is valid date");
				
				// change the string to date
				processDate = BgprocessUtils.strintToDate(date);
			}else{
				cardChangeCloseLog.warn(date + " is NOT valid date");
				
				// wrong date format. terminate process
				throw new TPlusException(date + " is NOT valid date");
			}
		}else{
			processDate = new Date();
		}
		
		// get cut off date
		Date cutOffDate = BgprocessUtils.getCutOffDate(processDate);
		cardChangeCloseLog.info("Cut off date is " + cutOffDate);
		
		// initiate manager class
		BgProcessManger objBgProcessManger = new BgProcessManger();
		
		// get all closing cards
		List changeClosingCards = objBgProcessManger.getChangeClosingCardsList(cutOffDate);
		
		if(changeClosingCards.size() > 0){
			cardChangeCloseLog.info("No of changed cards to close is " + changeClosingCards.size());
			
			Iterator itr = changeClosingCards.iterator(); 
			while(itr.hasNext()) {
				CardChangeCloseDto objCardChangeCloseDto =  (CardChangeCloseDto) itr.next();
				String cardNo = String.valueOf(objCardChangeCloseDto.getCard().getCardNumber());
				
				// log the date into log file
				cardChangeCloseLog.info("Changed Closing card details :: Serial No: " + objCardChangeCloseDto.getSerialNo() + ", Card No: " + cardNo);
				
				try{
					
					// update the table
					boolean updateRes = objBgProcessManger.updateChangeCardClosing(objCardChangeCloseDto.getSerialNo(), cardNo);
					
					if(updateRes){
						// update success
						cardChangeCloseLog.info("Updating Serial No: " + objCardChangeCloseDto.getSerialNo() + ", Card No: " + cardNo + " Success ");
					}else{
						// update failed
						cardChangeCloseLog.info("Updating Serial No: " + objCardChangeCloseDto.getSerialNo() + ", Card No: " + cardNo + " Failed ");
					}
					
				}catch (Exception e) {
					// update exception
					cardChangeCloseLog.error("Updating Exception on Serial No: " + objCardChangeCloseDto.getSerialNo() + ", Card No: " + cardNo);
					cardChangeCloseLog.error(new Object(), e);
				}
			}
			
		}else{
			// no cards to close
			cardChangeCloseLog.info("No Changed cards to close on this date " + processDate);
		}
		
	}

}
