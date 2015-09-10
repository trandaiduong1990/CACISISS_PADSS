package org.transinfo.cacis.bgprocess.core;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.bgprocess.utils.BgprocessUtils;
import org.transinfo.cacis.bgprocess.utils.DateValidator;
import org.transinfo.cacis.controller.bgprocess.BgProcessManger;
import org.transinfo.cacis.dto.cardproduction.CardDataDto;

@SuppressWarnings("unchecked")
public class CardClosing {
	
	private static final Logger cardCloseLog = Logger.getLogger("CardCloseLog");
	
	public void closingProcess(String date) throws TPlusException{
		
		Date processDate = null;
		
		if(date != null && !"".equals(date)){
			cardCloseLog.info("Manual Batch Process. Parsed Date is " + date);
			
			// validate the date format
			DateValidator objDateValidator = new DateValidator();
			boolean isValidDate = objDateValidator.validate(date);
			
			if(isValidDate){
				cardCloseLog.info(date + " is valid date");
				
				// change the string to date
				processDate = BgprocessUtils.strintToDate(date);
			}else{
				cardCloseLog.warn(date + " is NOT valid date");
				
				// wrong date format. terminate process
				throw new TPlusException(date + " is NOT valid date");
			}
		}else{
			processDate = new Date();
		}
		
		// get cut off date
		Date cutOffDate = BgprocessUtils.getCutOffDate(processDate);
		cardCloseLog.info("Cut off date is " + cutOffDate);
		
		// initiate manager class
		BgProcessManger objBgProcessManger = new BgProcessManger();
		
		// get all closing cards
		List closingCards = objBgProcessManger.getClosingCardsList(cutOffDate);
		
		if(closingCards.size() > 0){
			cardCloseLog.info("No of cards to close is " + closingCards.size());
			
			Iterator itr = closingCards.iterator(); 
			while(itr.hasNext()) {
				CardDataDto objCardDataDto =  (CardDataDto) itr.next();
				
				// log the date into log file
				cardCloseLog.info("Closing card details :: Serial No: " + objCardDataDto.getCardDataSerialNo() + ", Card No: " + objCardDataDto.getCardNumber());
				
				try{
					
					// update the table
					boolean updateRes = objBgProcessManger.updateCardClosing(objCardDataDto.getCardDataSerialNo());
					
					if(updateRes){
						// update success
						cardCloseLog.info("Updating Serial No: " + objCardDataDto.getCardDataSerialNo() + ", Card No: " + objCardDataDto.getCardNumber() + " Success ");
					}else{
						// update failed
						cardCloseLog.info("Updating Serial No: " + objCardDataDto.getCardDataSerialNo() + ", Card No: " + objCardDataDto.getCardNumber() + " Failed ");
					}
					
				}catch (Exception e) {
					// update exception
					cardCloseLog.error("Updating Exception on Serial No: " + objCardDataDto.getCardDataSerialNo() + ", Card No: " + objCardDataDto.getCardNumber());
					cardCloseLog.error(new Object(), e);
				}
			}
			
		}else{
			// no cards to close
			cardCloseLog.info("No cards to close on this date " + processDate);
		}
		
	}

}
