package org.transinfo.cacis.controller.customerservice;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.AddProductDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.NonReconTransactionEnquiryDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.TransactionEnquiryDAO;
import org.transinfo.cacis.dto.csr.NonReconTransactionLogDto;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.customerservice.AddProductSearchDto;
import org.transinfo.cacis.dto.customerservice.AddProductSetupDto;
import org.transinfo.cacis.dto.customerservice.NonReconTranxEnquirySearchDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquirySearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;

public class AddProductManager {

	private AddProductDAO objAddProductDAO;

	public AddProductManager() throws Exception {
		objAddProductDAO = DAOFactory.getInstance()
				.getAddProductDAO();

	}

	public AddProductSearchDto search(
			AddProductSearchDto objAddProductSearchDto, int pageNo)
			throws TPlusException {

		try {
			objAddProductSearchDto = objAddProductDAO.search(
					objAddProductSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in AddProductManager search method" + e);
		}

		return objAddProductSearchDto;

	}

	public AddProductSetupDto get(AddProductSetupDto objAddProductSetupDto, String customerId) throws TPlusException {
		try {
			objAddProductSetupDto = objAddProductDAO.get(
					objAddProductSetupDto, customerId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in AddProductManager get method" + e);
		}

		return objAddProductSetupDto;
	}

	public boolean addProduct(AddProductSetupDto objAddProductSetupDto, String userID) throws TPlusException {
		boolean checkInsert = false;
		try {
			checkInsert = objAddProductDAO.addProduct(objAddProductSetupDto, userID);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in AddProductManager addProduct method" + e);
		}
		return checkInsert;
	}

}
