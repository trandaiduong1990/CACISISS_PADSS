package org.transinfo.cacis.controller.customerservice;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.NonReconTransactionEnquiryDAO;
import org.transinfo.cacis.dto.customerservice.NonReconTranxEnquirySearchDto;

public class NonReconTransactionEnquiryManager {

	private NonReconTransactionEnquiryDAO objNonReconTransactionEnquiryDAO;

	public NonReconTransactionEnquiryManager() throws Exception {
		objNonReconTransactionEnquiryDAO = DAOFactory.getInstance().getNonReconTransactionEnquiryDAO();

	}

	public NonReconTranxEnquirySearchDto search(
			NonReconTranxEnquirySearchDto objTranxEnquirySearchDto, int pageNo)
			throws TPlusException {

		try {
			objTranxEnquirySearchDto = objNonReconTransactionEnquiryDAO.search(
					objTranxEnquirySearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in NonReconTransactionEnquiryManager search method" + e);
		}

		return objTranxEnquirySearchDto;

	}
}
