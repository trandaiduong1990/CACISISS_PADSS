package org.transinfo.cacis.controller.customerservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.AccountAdjustmentDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.customerservice.AccountAdjustmentDto;
import org.transinfo.cacis.dto.settings.CardProductDto;

@SuppressWarnings("unchecked")
public class AccountAdjustmentManager {

	private AccountAdjustmentDAO objDAO;

	public AccountAdjustmentManager() throws Exception {
		objDAO = DAOFactory.getInstance().getAccountAdjustmentDAO();

	}

	public CardsDto getCardDto(String cardNo) throws TPlusException {

		CardsDto objCardDto = null;

		try {
			objCardDto = objDAO.getCardDto(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in AccountAdjustmentManager getCardDto method" + e);
		}
		return objCardDto;
	}
	
	public CardProductDto getCardProductDto(String cardProductId) throws TPlusException {

		CardProductDto objCardProductDto = null;

		try {
			objCardProductDto = objDAO.getCardProductDto(cardProductId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in AccountAdjustmentManager getCardProductDto method" + e);
		}
		return objCardProductDto;
	}

	public CustomerAccountDto getCustomerAccountDto(String accountId)
			throws TPlusException {

		CustomerAccountDto objCustomerAccountDto = null;

		try {
			objCustomerAccountDto = objDAO.getCustomerAccountDto(accountId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in AccountAdjustmentManager getCustomerAccountDto method" + e);
		}
		return objCustomerAccountDto;
	}

	public AccountAdjustmentDto getAccountAdjustmentDto(String cardNo) throws TPlusException {

		AccountAdjustmentDto objAccountAdjustmentDto = null;

		try {
			objAccountAdjustmentDto = objDAO.getAccountAdjustmentDto(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in objAccountAdjustmentManager getobjAccountAdjustmentDto method" + e);
		}
		return objAccountAdjustmentDto;
	}

	public boolean add(AccountAdjustmentDto objAccountAdjustmentDto) throws TPlusException {

		boolean replace = false;

		try {
			replace = objDAO.add(objAccountAdjustmentDto);
		} catch (Exception e) {
			throw new TPlusException("Error in AccountAdjustmentManager add method"
					+ e);
		}
		return replace;
	}

	public Collection processSearch(AccountAdjustmentDto objDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.processSearch(objDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in AccountAdjustmentManager processSearch method" + e);
		}
		return searchLst;

	}

	public boolean accept(AccountAdjustmentDto objDto) throws TPlusException {
		boolean repAccept = false;
		try {
			repAccept = objDAO.accept(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in AccountAdjustmentManager accept method" + e);
		}
		return repAccept;
	}
	
	public boolean reject(AccountAdjustmentDto objDto) throws TPlusException {
		boolean repAccept = false;
		try {
			repAccept = objDAO.reject(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in AccountAdjustmentManager reject method" + e);
		}
		return repAccept;
	}
	
	public Map getProductListForChange(String issuerID, String cardNo) throws TPlusException {
		Map res = new HashMap();
		try {

			res = objDAO.getProductListForChange(issuerID, cardNo);

		} catch (Exception e) {
			throw new TPlusException("Error in AccountAdjustmentManager getProductListForChange method"
					+ e);
		}
		return res;
	}

}
