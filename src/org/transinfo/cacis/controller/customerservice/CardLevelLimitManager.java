package org.transinfo.cacis.controller.customerservice;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardLevelLimitDAO;
import org.transinfo.cacis.dto.cardproduction.CustomerLimitsDto;
import org.transinfo.cacis.dto.customerservice.CardLevelLimitSearchDto;
import org.transinfo.cacis.dto.settings.CardProductLimitDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings( { "unchecked" })
public class CardLevelLimitManager {

	private Logger logger = Logger.getLogger(CardLevelLimitManager.class);

	private CardLevelLimitDAO objDAO;

	public CardLevelLimitManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardLevelLimitDAO();

	}

	public Collection search(CardLevelLimitSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;

		try {

			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductManager search method" + e);
		}
		return searchList;

	}

	public CustomerLimitsDto getCustomerLimitDto(String cardNo)
			throws TPlusException {

		CustomerLimitsDto objCustomerLimitsDto = null;

		try {

			objCustomerLimitsDto = objDAO.getCustomerLimitDto(cardNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductManager CardProductDto method" + e);
		}
		return objCustomerLimitsDto;
	}

	public boolean update(CustomerLimitsDto objDto) throws TPlusException {

		boolean boolUpdate = false;

		try {

			boolUpdate = objDAO.update(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductManager update method" + e);
		}
		return boolUpdate;
	}

	public CardProductLimitDto getCardProductLimitDto(String cardNo)
			throws TPlusException {
		
		CardProductLimitDto objDto = null;

		try {
			objDto = objDAO.getCardProductLimitDto(cardNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductManager CardProductDto method" + e);
		}
		return objDto;
	}

}