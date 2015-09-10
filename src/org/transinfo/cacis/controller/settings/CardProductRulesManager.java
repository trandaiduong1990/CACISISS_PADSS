package org.transinfo.cacis.controller.settings;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRulesDAO;
import org.transinfo.cacis.dto.settings.CardProductRulesDto;
import org.transinfo.cacis.dto.settings.CardProductRulesSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings( { "static-access", "unchecked" })
public class CardProductRulesManager {

	private Logger logger = Logger.getLogger(CardProductRulesManager.class);

	private CardProductRulesDAO objDAO;

	public CardProductRulesManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardProductRulesDAO();

	}

	public Collection search(CardProductRulesSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;

		try {
			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRulesManager search method" + e);
		}
		return searchList;

	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public boolean add(CardProductRulesDto objCardProductRulesDto) throws TPlusException {
		boolean checkInsert = false;

		try {
			checkInsert = objDAO.add(objCardProductRulesDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRulesManager add method" + e);
		}
		return checkInsert;
	}


	public boolean checkExistRecord(CardProductRulesDto objDto) throws TPlusException {
		boolean exist = false;
		try {
			exist = objDAO.checkExistRecord(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRulesManager checkExistRecord method" + e);
		}
		return exist;
	}

	public boolean update(CardProductRulesDto objDto) throws TPlusException {
		boolean update = false;
		try {
			update = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRulesManager update method" + e);
		}
		return update;
	}

	public CardProductRulesDto getCardProductRulesDto(String cardProductId) throws TPlusException {
		CardProductRulesDto objDto;
		try {
			objDto = objDAO.getCardProductRulesDto(cardProductId);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRulesManager update method" + e);
		}
		return objDto;
	}
	
	public Map getMccMasterList(String cardProductId) throws TPlusException {
		Map functionList = new TreeMap();
		try {
			functionList = objDAO.getMccMasterList(cardProductId);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRulesManager getMccMasterList method" + e);
		}
		return functionList;
	}

}