package org.transinfo.cacis.dataacess.dao.cardproduction;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingSearchDto;
import org.transinfo.cacis.dto.cardproduction.ResendPinPrintingSearchDto;

@SuppressWarnings("unchecked")
public interface PinPrintingDAO extends BaseDAO {

	public Collection search(PinPrintingSearchDto objSearchDt, int pageNo)
			throws TPlusException;

	public boolean save(PinPrintingSearchDto objSearchDto)
			throws TPlusException;

	public boolean updateObjects(String pinPrinSerialNo, String userId)
			throws TPlusException;

    public PinPrintingDto getPinPrint(String cardNo)throws TPlusException;

    public PinPrintingDto getPinPrintNotProcessed(String cardNo)throws TPlusException;
    
	public CardsDto getCard(String pinPrintSerialNo)throws TPlusException;

    public boolean isEmbossed(String cardNo)throws TPlusException;

	public Collection searchResendList(ResendPinPrintingSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean updateResendObjects(String pinPrinSerialNo, String userId)
			throws TPlusException;
	
	public boolean updateCARDSandCARDDATA(CardsDto objCardsDto) throws TPlusException;

}
