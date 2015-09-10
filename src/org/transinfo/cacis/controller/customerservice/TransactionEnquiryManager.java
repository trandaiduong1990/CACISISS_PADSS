package org.transinfo.cacis.controller.customerservice;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.TransactionEnquiryDAO;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquiryNonReconTranxlogSearchDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquirySearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;

public class TransactionEnquiryManager {

	private TransactionEnquiryDAO objTransactionEnquiryDAO;

	public TransactionEnquiryManager() throws Exception {
		objTransactionEnquiryDAO = DAOFactory.getInstance()
				.getTransactionEnquiryDAO();

	}

	public TranxEnquirySearchDto search(
			TranxEnquirySearchDto objTranxEnquirySearchDto, int pageNo)
			throws TPlusException {

		try {
			objTranxEnquirySearchDto = objTransactionEnquiryDAO.search(
					objTranxEnquirySearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in TransactionEnquiryManager search method" + e);
		}

		return objTranxEnquirySearchDto;

	}

	public TranxEnquiryNonReconTranxlogSearchDto searchNonReconTranx(
			TranxEnquiryNonReconTranxlogSearchDto objEnquiryNonReconTranxlogSearchDto, int pageNo)
			throws TPlusException {

		try {
			
			objEnquiryNonReconTranxlogSearchDto = objTransactionEnquiryDAO.searchNonReconTranx(objEnquiryNonReconTranxlogSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in TransactionEnquiryManager search method" + e);
		}

		return objEnquiryNonReconTranxlogSearchDto;

	}

	public TransactionLogDto getTransactionDto(String tranxId)
			throws TPlusException {
		TransactionLogDto objTransactionLogDto = null;

		try {
			objTransactionLogDto = objTransactionEnquiryDAO
					.getTransactionDto(tranxId);

		} catch (Exception e) {
			throw new TPlusException(
					"TransactionEnquiryManager getTransactionDto method" + e);
		}

		return objTransactionLogDto;
	}

	public DisputeManualReconDto getDisputeManualReconDto(String tranxId)
			throws TPlusException {
		DisputeManualReconDto objDisputeManualReconDto = null;

		try {
			objDisputeManualReconDto = objTransactionEnquiryDAO
					.getDisputeManualReconDto(tranxId);

		} catch (Exception e) {
			throw new TPlusException(
					"TransactionEnquiryManager getDisputeManualReconDto method"
							+ e);
		}

		return objDisputeManualReconDto;
	}

	public boolean saveManualRecon(
			DisputeManualReconDto objDisputeManualReconDto)
			throws TPlusException {

		boolean boolInsert = false;

		try {

			boolInsert = objTransactionEnquiryDAO
					.saveManualRecon(objDisputeManualReconDto);

		} catch (Exception e) {
			throw new TPlusException(
					"TransactionEnquiryManager saveManualRecon method" + e);
		}
		return boolInsert;
	}

	public boolean TranxRevert(String tranxId, String remarks, String user)
			throws TPlusException {

		boolean boolInsert = false;

		try {

			boolInsert = objTransactionEnquiryDAO
					.TranxRevert(tranxId, remarks, user);

		} catch (Exception e) {
			throw new TPlusException(
					"TransactionEnquiryManager TranxRevert method" + e);
		}
		return boolInsert;
	}

}
