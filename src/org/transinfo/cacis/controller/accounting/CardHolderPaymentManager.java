package org.transinfo.cacis.controller.accounting;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.accounting.CardHolderPaymentDAO;
import org.transinfo.cacis.dto.accounting.CardHolderPaymentDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;

@SuppressWarnings("unchecked")
public class CardHolderPaymentManager {

	private CardHolderPaymentDAO objDAO;

	public CardHolderPaymentManager()throws Exception {
		objDAO = DAOFactory.getInstance().getCardHolderPaymentDAO();

	}

	public Collection search(CardHolderPaymentDto objDto)  throws TPlusException {

		Collection searchLst= null;

		try {
			searchLst = objDAO.search(objDto);

		} catch (Exception e) {
			System.out.println("Error in CardHolderPaymentManager search method"+e.getMessage());
			throw new TPlusException("Error in CardHolderPaymentManager search method:" +e);
		}
		return searchLst;

	}
	/*
	 * method for CardGradeForm
	 */
	public boolean cardHolderPayment(CardHolderPaymentDto objDto, String userId)  throws TPlusException {

		boolean success=false;

		OriginalRequestDto objSettlementDto = new OriginalRequestDto();

		Date dateTime = new Date();

		//objSettlementDto.setSettlementId(objDto.getSettlementId());
		//objSettlementDto.setRefNumber(objDto.getRefNumber());

		objSettlementDto.setCardNumber(objDto.getCardNumber());
		objSettlementDto.setIssuerId(objDto.getIssuerId());
		objSettlementDto.setAmountCurr(objDto.getAmountCurr());
		objSettlementDto.setCurrencyCode(objDto.getCurrencyCode());
		objSettlementDto.setDateTime(dateTime);

		try {
			success = objDAO.cardHolderPayment(objSettlementDto, userId);
		} catch (Exception e) {
			throw new TPlusException("Error in CardHolderPaymentManager cardHolderPayment mehod:" +e);
		}
		return success;
	}	
	/*
	 * for validating the records existed or not
	 */
	public boolean validate(Object obj,int mode )throws TPlusException	{
		boolean rtnMessage = true;
		if(mode==0 && objDAO.checkExistrecord(obj)>0){
			rtnMessage = false;
		}
		if(mode==1 && objDAO.checkExistrecord(obj)==0)
		{
			rtnMessage = false;
		}

		return rtnMessage;
	}
}
