package org.transinfo.cacis.controller.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.SalaryProfileDAO;
import org.transinfo.cacis.dto.settings.SalaryProfileDto;

@SuppressWarnings( { "unchecked" })
public class SalaryProfileManager {

	private Logger logger = Logger.getLogger(SalaryProfileManager.class);

	private SalaryProfileDAO objSalaryProfileDAO;

	public SalaryProfileManager() throws Exception {
		objSalaryProfileDAO = DAOFactory.getInstance().getSalaryProfileDAO();
	}

	public Collection getSalaryProfileList(String cardProduct)
			throws TPlusException {

		Collection searchList = null;

		try {

			searchList = objSalaryProfileDAO.search(cardProduct);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in controller search method" + e);
		}
		return searchList;
	}

	public Map getAddproductList(String issuerId) throws TPlusException {

		Map searchList = null;

		try {

			searchList = objSalaryProfileDAO.getAddproductList(issuerId);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in controller search method" + e);
		}
		return searchList;
	}

	public Map getSearchproductList(String issuerId) throws TPlusException {

		Map searchList = null;

		try {

			searchList = objSalaryProfileDAO.getSearchproductList(issuerId);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in controller search method" + e);
		}
		return searchList;
	}

	public boolean add(SalaryProfileDto objSalaryProfileDto)
			throws TPlusException {

		boolean boolAdd = false;

		try {
			boolAdd = objSalaryProfileDAO.add(objSalaryProfileDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CardTypeManager add method" + e);
		}
		return boolAdd;

	}

	public List getSalaryProfileListUpdate(String cardProduct)
			throws TPlusException {

		List searchList = new ArrayList();

		try {

			searchList = objSalaryProfileDAO
					.getSalaryProfileListUpdate(cardProduct);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in controller search method" + e);
		}
		return searchList;
	}

	public boolean delete(String cardproduct) throws TPlusException {

		boolean boolDel = false;

		try {
			boolDel = objSalaryProfileDAO.delete(cardproduct);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CardTypeManager add method" + e);
		}
		return boolDel;

	}

	public List getSalaryProfileListAppProcess(String cardProduct, String income)
			throws TPlusException {

		List searchList = new ArrayList();

		try {

			searchList = objSalaryProfileDAO
					.getSalaryProfileListAppProcess(cardProduct, income);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in controller search method" + e);
		}
		return searchList;
	}

}
