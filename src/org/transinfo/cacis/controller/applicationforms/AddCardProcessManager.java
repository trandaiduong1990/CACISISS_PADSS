package org.transinfo.cacis.controller.applicationforms;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.AddCardProcessDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.AddProductDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.NonReconTransactionEnquiryDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.TransactionEnquiryDAO;
import org.transinfo.cacis.dto.applicationforms.AddCardProcessSearchDto;
import org.transinfo.cacis.dto.applicationforms.AddCardProcessSetupDto;
import org.transinfo.cacis.dto.csr.NonReconTransactionLogDto;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.customerservice.AddProductSearchDto;
import org.transinfo.cacis.dto.customerservice.AddProductSetupDto;
import org.transinfo.cacis.dto.customerservice.NonReconTranxEnquirySearchDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquirySearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;

public class AddCardProcessManager {

	private AddCardProcessDAO objAddCardProcessDAO;

	public AddCardProcessManager() throws Exception {
		objAddCardProcessDAO = DAOFactory.getInstance()
				.getAddCardProcessDAO();

	}

	public AddCardProcessSearchDto search(
			AddCardProcessSearchDto objAddProductSearchDto, int pageNo)
			throws TPlusException {

		try {
			objAddProductSearchDto = objAddCardProcessDAO.search(
					objAddProductSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in AddCardProcessManager search method" + e);
		}

		return objAddProductSearchDto;

	}

	public AddCardProcessSetupDto getAddProductForm(AddCardProcessSetupDto objAddCardProcessSetupDto) throws TPlusException {
		try {
			objAddCardProcessSetupDto = objAddCardProcessDAO.getAddProductForm(objAddCardProcessSetupDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in AddCardProcessManager getAddProductForm method" + e);
		}
		return objAddCardProcessSetupDto;
	}

	public boolean reject(AddCardProcessSetupDto objAddCardProcessSetupDto) throws TPlusException {
		boolean checkUpdate;
		try {
			checkUpdate = objAddCardProcessDAO.reject(objAddCardProcessSetupDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in AddCardProcessManager update method" + e);
		}
		return checkUpdate;
	}

	public boolean accept(AddCardProcessSetupDto objAddCardProcessSetupDto) throws TPlusException {
		boolean checkInsert;
		try {
			checkInsert = objAddCardProcessDAO.accept(objAddCardProcessSetupDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in AddCardProcessManager insertCustomerAccount method" + e);
		}
		return checkInsert;
	}

}
