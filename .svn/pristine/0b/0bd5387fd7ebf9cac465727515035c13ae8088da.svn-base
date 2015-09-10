package org.transinfo.cacis.controller.settings;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductDAO;
import org.transinfo.cacis.dataacess.dao.settings.ProductTranxDAO;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CardProductSearchDto;
import org.transinfo.cacis.dto.settings.CardProductTranxDto;
import org.transinfo.cacis.dto.settings.ProductTranxSearchDto;
import org.transinfo.cacis.dto.settings.TranxTypeDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings( { "static-access", "unchecked" })
public class ProductTranxManager {

	private Logger logger = Logger.getLogger(ProductTranxManager.class);

	private ProductTranxDAO objDAO;

	public ProductTranxManager() throws Exception {
		objDAO = DAOFactory.getInstance().getProductTranxDAO();

	}

	public Collection search(ProductTranxSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;

		try {
			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in ProductTranxManager search method" + e);
		}
		return searchList;

	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public boolean addProductTranx(CardProductTranxDto objCardProductTranxDto) throws TPlusException {
		boolean checkInsert = false;

		try {
			checkInsert = objDAO.addProductTranx(objCardProductTranxDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in ProductTranxManager addProductTranx method" + e);
		}
		return checkInsert;
	}

	public TranxTypeDto getTranxTypeDto(String tranxType) throws TPlusException {
		TranxTypeDto objTranxTypeDto;
		try {
			objTranxTypeDto = objDAO.getTranxTypeDto(tranxType);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in ProductTranxManager getTranxTypeDto method" + e);
		}
		return objTranxTypeDto;
	}

	public boolean checkExistRecord(CardProductTranxDto objDto) throws TPlusException {
		boolean exist = false;
		try {
			exist = objDAO.checkExistRecord(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in ProductTranxManager checkExistRecord method" + e);
		}
		return exist;
	}

	public boolean update(CardProductTranxDto objDto) throws TPlusException {
		boolean update = false;
		try {
			update = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in ProductTranxManager update method" + e);
		}
		return update;
	}

}