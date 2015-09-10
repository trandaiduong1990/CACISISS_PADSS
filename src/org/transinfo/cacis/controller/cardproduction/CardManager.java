package org.transinfo.cacis.controller.cardproduction;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.CardStatusRemarksDto;

public class CardManager {

	private CardDAO objCardDAO;

	public CardManager() throws Exception {
		objCardDAO = DAOFactory.getInstance().getCardDAO();

	}
	
	public CardsDto getCard(String cardNo) throws TPlusException {
		CardsDto objCardsDto = null;

		try {
			objCardsDto = objCardDAO.getCard(cardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardManager getCard method:" + e);
		}

		return objCardsDto;
	}
	
	public CardsDto getCardByEncryptedData(String ecardNo) throws TPlusException {
		CardsDto objCardsDto = null;

		try {
			objCardsDto = objCardDAO.getCardByEncryptedData(ecardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardManager getCard method:" + e);
		}

		return objCardsDto;
	}
	
	public String getCardType(String cardNo) throws TPlusException {
		String res = null;

		try {
			res = objCardDAO.getCardType(cardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardManager getCard method:" + e);
		}

		return res;
	}
	
	public boolean isCardEmbossed(String cardNo) throws TPlusException {

		boolean res = false;
		
		try {
			res = objCardDAO.isCardEmbossed(cardNo);
		} catch (Exception e) {
			throw new TPlusException("Error in PinPrintingManager getPinPrint mehod"
					+ e);
		}
		
		return res;
	}
	
	public CardStatusRemarksDto getCardStatusRemarks(long cardStatusID,
			String cardNo) throws TPlusException {

		CardStatusRemarksDto searchRes = null;

		try {
			searchRes = objCardDAO.getCardStatusRemarks(cardStatusID, cardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager getCardStatusRemarks method:"
							+ e);
		}
		return searchRes;

	}

}
