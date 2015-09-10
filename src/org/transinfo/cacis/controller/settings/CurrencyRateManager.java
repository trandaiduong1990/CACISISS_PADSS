package org.transinfo.cacis.controller.settings;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CurrencyRateDAO;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.dto.settings.CurrencyRateDto;

@SuppressWarnings( { "unchecked" })
public class CurrencyRateManager {

	private Logger logger = Logger.getLogger(CurrencyRateManager.class);

	private CurrencyRateDAO objCurrencyRateDAO;

	public CurrencyRateManager() throws Exception {
		objCurrencyRateDAO = DAOFactory.getInstance().getCurrencyRateDAO();
	}

	public Collection getCurrencyRateList(CurrencyRateDto objSearchtDto,
			int pageNo) throws TPlusException {

		Collection searchList = null;

		try {

			searchList = objCurrencyRateDAO.search(objSearchtDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in controller search method" + e);
		}
		return searchList;
	}

	/*public boolean add(CurrencyRateDto objDto) throws TPlusException {

		boolean boolAdd = false;
		try {

			if (!validate(objDto, objCurrencyRateDAO.ADD)) {
				System.out.println("\n\n Record Already Exists");
			} else {
				boolAdd = objCurrencyRateDAO.add(objDto);
			}

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CurrencyRateManager add method"
					+ e);
		}
		return boolAdd;
	}*/
	
	public boolean add(CurrencyRateDto objDto) throws TPlusException {

		boolean boolAdd = false;
		try {

			boolAdd = objCurrencyRateDAO.add(objDto);
			
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CurrencyRateManager add method"
					+ e);
		}
		return boolAdd;
	}

	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		CurrencyRateDto objDto = (CurrencyRateDto) obj;

		if (mode == 0 && objCurrencyRateDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objCurrencyRateDAO.checkExistrecord(objDto) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public CurrencyRateDto getCurrencyRateDto(String currId, String issuerId)
			throws TPlusException {
		CurrencyRateDto objDto = null;

		try {
			objDto = objCurrencyRateDAO.getCurrencyRateDto(currId, issuerId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CurrencyRateManager getCurrencyRateDto method"
							+ e);
		}
		return objDto;
	}

	public boolean update(CurrencyRateDto objDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			boolUpdate = objCurrencyRateDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CurrencyRateManager update method" + e);
		}
		return boolUpdate;
	}

	public boolean delete(CurrencyRateDto objDto) throws TPlusException {
		boolean success = false;

		try {
			success = objCurrencyRateDAO.delete(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in delete method" + e);
		}
		return success;
	}

	public CurrencyDto getCurrencyDto(String currId)
			throws TPlusException {
		CurrencyDto objDto = null;

		try {
			objDto = objCurrencyRateDAO.getCurrencyDto(currId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CurrencyRateManager getCurrencyDto method"
							+ e);
		}
		return objDto;
	}

}
