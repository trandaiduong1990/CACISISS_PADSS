package org.transinfo.cacis.bgprocess.core;

import java.net.InetAddress;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.bgprocess.utils.BgprocessUtils;
import org.transinfo.cacis.bgprocess.utils.DateValidator;
import org.transinfo.cacis.controller.bgprocess.BgProcessManger;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;

@SuppressWarnings("unchecked")
public class CardRenewal {

	private static final Logger cardRenewalLog = Logger.getLogger("CardRenewalLog");

	public void renewalProcess(String date) throws TPlusException {

		Date processDate = null;

		if (date != null && !"".equals(date)) {
			cardRenewalLog.info("Manual Batch Process. Parsed Date is " + date);

			// validate the date format
			DateValidator objDateValidator = new DateValidator();
			boolean isValidDate = objDateValidator.validate(date);

			if (isValidDate) {
				cardRenewalLog.info(date + " is valid date");

				// change the string to date
				processDate = BgprocessUtils.strintToDate(date);
			} else {
				cardRenewalLog.warn(date + " is NOT valid date");

				// wrong date format. terminate process
				throw new TPlusException(date + " is NOT valid date");
			}
		} else {
			processDate = new Date();
		}
		
		// get start date
		Date startDate = BgprocessUtils.getStartDate(processDate);
		cardRenewalLog.info("Start date is " + startDate);

		// get end date
		Date endDate = BgprocessUtils.getEndDate(processDate);
		cardRenewalLog.info("End date is " + endDate);
		
		// initiate manager class
		BgProcessManger objBgProcessManger = new BgProcessManger();
		
		// get all renewal cards
		List renewalCards = objBgProcessManger.getRenewalCardsList(startDate, endDate);
		
		if(renewalCards.size() > 0){
			cardRenewalLog.info("No of cards to renew is " + renewalCards.size());
			
			String cardNo = "";
			CardsRenewalDto objCardsRenewalDto = null;
			CardActivityDto objCardActivity = null;
			
			Iterator itr = renewalCards.iterator(); 
			while(itr.hasNext()) {
				CardsDto objCardsDto =  (CardsDto) itr.next();
				cardNo = String.valueOf(objCardsDto.getCardNumber());
				
				// log the date into log file
				cardRenewalLog.info("Renewal card details :: Card No: " + objCardsDto.getCardNumber() + ", Expire Date : " + objCardsDto.getCardExpDate());
				
				try{
					
					// validate against cardrenewal_forms table for existing data
					int renewalStatus = objBgProcessManger.renewalProcessStatus(cardNo);
					
					if(renewalStatus == 0){
						
						// inserting into table
						objCardsRenewalDto = new CardsRenewalDto();
						objCardsRenewalDto.setCardNumber(objCardsDto.getCardNumber());
						objCardsRenewalDto.setIssuerId(objCardsDto.getIssuerId());
						objCardsRenewalDto.setCustomerId(objCardsDto.getCustomerId());
						objCardsRenewalDto.setCardExpireDate(objCardsDto.getCardExpDate());
						objCardsRenewalDto.setUserId("BatchProcess");
						objCardsRenewalDto.setUpdatedDate(new Date());
						objCardsRenewalDto.setStatus(0);
						
						// inserting into CardActivites Table
						objCardActivity = new CardActivityDto();
						objCardActivity.setDateTime(new Date());
						objCardActivity.setCardNumber(objCardsDto.getCardNumber());
						objCardActivity.setActivity("Card Renewal By Process");
						objCardActivity.setStationIp(InetAddress.getLocalHost().getHostAddress());
						objCardActivity.setUserId("BatchProcess");
						objCardActivity.setReason("Card Renewal");
						objCardActivity.setLastUpdatedBy("BatchProcess");
						objCardActivity.setUpdatedDate(new Date());
						
						boolean insertRes = objBgProcessManger.insertRenewalAndActivity(objCardsRenewalDto, objCardActivity);
						if(insertRes){
							// insert success
							cardRenewalLog.info("Card No: " + objCardsDto.getCardNumber() + " is inserted succesfully into cardrenewal table");
						}else{
							// insert failed
							cardRenewalLog.info("Card No: " + objCardsDto.getCardNumber() + " is NOT inserted succesfully into cardrenewal table");
						}
					}else{
						// card number is in renewal process
						cardRenewalLog.info("Card No: " + objCardsDto.getCardNumber() + " is in Renewal process already");
					}
				
				}catch (Exception e) {
					// update exception
					cardRenewalLog.error("Exception on Card No: " + objCardsDto.getCardNumber());
					cardRenewalLog.error(new Object(), e);
				}
				
			}
			
		}else{
			// no cards to close
			cardRenewalLog.info("No cards to renew on this date " + processDate);
		}

	}

}
