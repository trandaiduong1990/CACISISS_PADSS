package org.transinfo.cacis.controller.customerservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CustomerScreenProcessDAO;
import org.transinfo.cacis.dto.batchprocess.CardATMLinkDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CustomerLimitsDto;
import org.transinfo.cacis.dto.customerservice.CustomerScreenProcessDto;
import org.transinfo.cacis.dto.customerservice.CustomerScreenSearchDto;

@SuppressWarnings("unchecked")
public class CustomerScreenProcessManager {

	private static CustomerScreenProcessDAO objDAO;

	public CustomerScreenProcessManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCustomerScreenProcessDAO();

	}

	public Collection search(CustomerScreenSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {

			searchLst = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CustomerScreenProcessManager search method " + e);
		}

		return searchLst;

	}

	public CustomerScreenProcessDto getApplicationProcessDto(String customerId)
			throws TPlusException {

		CustomerScreenProcessDto objProcessDto = null;

		try {
			objProcessDto = objDAO.getApplicationProcessDto(customerId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CustomerScreenProcessManager getApplicationProcessDto method"
							+ e);
		}

		return objProcessDto;
	}

	public Collection getAllCardsByAccountID(String accountId)
			throws TPlusException {

		Collection searchLst = null;

		try {

			searchLst = objDAO.getAllCardsByAccountID(accountId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CustomerScreenProcessManager getAllCardsByAccountID method " + e);
		}

		return searchLst;

	}

	public CustomerLimitsDto getCustomerLimitsDto(String cardNo)
			throws TPlusException {

		CustomerLimitsDto objCustomerLimitsDto = null;

		try {
			objCustomerLimitsDto = objDAO.getCustomerLimitsDto(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CustomerScreenProcessManager getCustomerLimitsDto method"
							+ e);
		}

		return objCustomerLimitsDto;
	}

	public CardATMLinkDto getCardATMLinkDto(String cardNo)
			throws TPlusException {

		CardATMLinkDto objAtmLinkDto = null;

		try {
			objAtmLinkDto = objDAO.getCardATMLinkDto(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CustomerScreenProcessManager getCardATMLinkDto method"
							+ e);
		}

		return objAtmLinkDto;
	}

	public Double getTotNonReconAmt(String accountId)
			throws TPlusException {

		Double amt = 0.0;

		try {
			amt = objDAO.getTotNonReconAmt(accountId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CustomerScreenProcessManager getTotNonReconAmt method"
							+ e);
		}

		return amt;
	}

	public List getSuppCards(String cardNo, String accountNo)
			throws TPlusException {

		List suppList = new ArrayList();

		try {
			suppList = objDAO.getSuppCards(cardNo, accountNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CustomerScreenProcessManager getSuppCards method"
							+ e);
		}

		return suppList;
	}

	public List getOtherCards(String cardNo, String accountNo)
			throws TPlusException {

		List suppList = new ArrayList();

		try {
			suppList = objDAO.getOtherCards(cardNo, accountNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CustomerScreenProcessManager getOtherCards method"
							+ e);
		}

		return suppList;
	}

	public Collection customerHistory(String custIdNumber)
			throws TPlusException {

		Collection historyList = null;

		try {
			historyList = objDAO.customerHistoryNew(custIdNumber);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in ApplicationProcessManager customerHistory method"
							+ e);
		}
		return historyList;

	}

	public Collection getCardDetails(String appId) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.getCardDetails(appId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CustomerScreenProcessManager search method" + e);
		}
		return searchLst;

	}
	
	public boolean update(CardATMLinkDto objCardATMLinkDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			
			boolUpdate = objDAO.update(objCardATMLinkDto);

		} catch (Exception e) {
			throw new TPlusException("Error in CustomerScreenProcessManager update method" + e);
		}
		return boolUpdate;
	}
	
	public boolean updateCU(ApplicationProcessDto objAppProcessDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			
			boolUpdate = objDAO.updateCU(objAppProcessDto);

		} catch (Exception e) {
			throw new TPlusException("Error in updateCU update method" + e);
		}
		return boolUpdate;
	}

}
