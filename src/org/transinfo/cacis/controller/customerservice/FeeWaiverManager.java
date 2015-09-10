package org.transinfo.cacis.controller.customerservice;

import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.FeeWaiverDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.transaction.CustomerFeeDto;

@SuppressWarnings("unchecked")
public class FeeWaiverManager {

	private FeeWaiverDAO objDAO;

	public FeeWaiverManager() throws Exception {
		objDAO = DAOFactory.getInstance().getFeeWaiverDAO();
	}

	public CardsDto getCardDto(String cardNo) throws TPlusException {

		CardsDto objCardDto = null;

		try {
			objCardDto = objDAO.getCardDto(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardChangeManager getCardDto method" + e);
		}
		return objCardDto;
	}

	public List getFeeTranx(String cardNo) throws TPlusException {

		List feeTranxList = null;

		try {

			feeTranxList = objDAO.getFeeTranx(cardNo);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getFeeTranx method" + e);
		}

		return feeTranxList;

	}

	public List getDebitFeeTranx(String cardNo) throws TPlusException {

		List feeTranxList = null;

		try {

			feeTranxList = objDAO.getDebitFeeTranx(cardNo);

		} catch (Exception e) {
			throw new TPlusException( "Error in CardHolderStatementManager getFeeTranx method" + e);
		}

		return feeTranxList;

	}

	public CustomerFeeDto getCustomerFee(String feeId) throws TPlusException {
		
		CustomerFeeDto objDto = null;

		try {
			
			objDto = objDAO.getCustomerFee(feeId);

		} catch (Exception e) {
			throw new TPlusException("Error in CardProductManager CardProductDto method" + e);
		}
		
		return objDto;
	}

	public boolean updateDB(String feeId, String userId) throws TPlusException {

		boolean replace = false;

		try {
			replace = objDAO.updateDB(feeId, userId);
		} catch (Exception e) {
			throw new TPlusException("Error in CardChangeManager add method" + e);
		}
		
		return replace;
	}

	public boolean updateDBDebit(String feeId, String userId) throws TPlusException {

		boolean replace = false;

		try {
			replace = objDAO.updateDBDebit(feeId, userId);
		} catch (Exception e) {
			throw new TPlusException("Error in CardChangeManager add method" + e);
		}
		
		return replace;
	}

}
