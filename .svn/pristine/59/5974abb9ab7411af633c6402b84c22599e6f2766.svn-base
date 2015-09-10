package org.transinfo.cacis.dataacess.dao.cardproduction;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.CardStatusRemarksDto;

public interface CardDAO {

	public CardsDto getCard(String cardNo) throws TPlusException;

	public CardsDto getCardByEncryptedData(String ecardNo) throws TPlusException;
	
	public String getCardType(String cardNo) throws TPlusException;

	public boolean isCardEmbossed(String cardNo) throws TPlusException;

	public CardStatusRemarksDto getCardStatusRemarks(long cardStatusId,
			String cardNo) throws TPlusException;

}
