package org.transinfo.cacis.util;

import org.transinfo.cacis.dto.cardproduction.CardsDto;

public class TrackNumberCombine {
	
	public String combineTrack1Data(CardsDto objCardsDto, String nameOnCard, String serviceCode){
		String track1data = "";
		
		String cardNumber = String.valueOf(objCardsDto.getCardNumber());
		
		String CVV = String.valueOf(objCardsDto.getCvv());
		CVV = StringUtil.LPAD(CVV, 3, "0");
		
		String expiryDateTrack = objCardsDto.getCardExpDate();
		expiryDateTrack = StringUtil.changeExpDateToTrack(expiryDateTrack);
		
		track1data = cardNumber + "^" + nameOnCard + "^" + expiryDateTrack + serviceCode + "00" + CVV + "000000";
		
		return track1data;
	}
	
	public String combineTrack2Data(CardsDto objCardsDto, String serviceCode){
		String track1data = "";
		
		String cardNumber = String.valueOf(objCardsDto.getCardNumber());
		
		String CVV = String.valueOf(objCardsDto.getCvv());
		CVV = StringUtil.LPAD(CVV, 3, "0");
		
		String expiryDateTrack = objCardsDto.getCardExpDate();
		expiryDateTrack = StringUtil.changeExpDateToTrack(expiryDateTrack);
		
		track1data = cardNumber + "=" + expiryDateTrack + serviceCode + CVV;
		
		return track1data;
	}

}
