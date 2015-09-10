package org.transinfo.cacis.controller.customerservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardChangeDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;

@SuppressWarnings("unchecked")
public class CardChangeManager {

	private CardChangeDAO objDAO;

	public CardChangeManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardChangeDAO();

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

	public CustomerInfoDto getCustomerInfoDto(String customerID)
			throws TPlusException {

		CustomerInfoDto objCustomerInfoDto = null;

		try {
			objCustomerInfoDto = objDAO.getCustomerInfoDto(customerID);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardChangeManager getCustomerInfoDto method" + e);
		}
		return objCustomerInfoDto;
	}

	public CardChangeDto getCardChangeDto(String cardNo) throws TPlusException {

		CardChangeDto objCardChangeDto = null;

		try {
			objCardChangeDto = objDAO.getCardChangeDto(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardChangeManager getCardChangeDto method" + e);
		}
		return objCardChangeDto;
	}

	public boolean add(CardChangeDto obCardChangeDto) throws TPlusException {

		boolean replace = false;

		try {
			replace = objDAO.add(obCardChangeDto);
		} catch (Exception e) {
			throw new TPlusException("Error in CardChangeManager add method"
					+ e);
		}
		return replace;
	}

	public Collection processSearch(CardChangeDto objDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.processSearch(objDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardChangeManager processSearch method" + e);
		}
		return searchLst;

	}

	public boolean accept(CardChangeDto objDto) throws TPlusException {
		boolean repAccept = false;
		try {
			repAccept = objDAO.accept(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardChangeManager accept method" + e);
		}
		return repAccept;
	}
	
	public boolean reject(CardChangeDto objDto) throws TPlusException {
		boolean repAccept = false;
		try {
			repAccept = objDAO.reject(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardChangeManager accept method" + e);
		}
		return repAccept;
	}
	
	public Map getProductListForChange(String issuerID, String cardNo) throws TPlusException {
		Map res = new HashMap();
		try {

			res = objDAO.getProductListForChange(issuerID, cardNo);

		} catch (Exception e) {
			throw new TPlusException("Error in CardChangeManager getProductListForChange method"
					+ e);
		}
		return res;
	}

}
