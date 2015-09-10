package org.transinfo.cacis.dataacess.dao.customerservice;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquiryNonReconTranxlogSearchDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquirySearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;

public interface TransactionEnquiryDAO extends BaseDAO {

	public TranxEnquirySearchDto search(
			TranxEnquirySearchDto objTranxEnquirySearchDto, int pageNo)
			throws TPlusException;
	
	public TranxEnquiryNonReconTranxlogSearchDto searchNonReconTranx(
			TranxEnquiryNonReconTranxlogSearchDto objTranxEnquirySearchDto, int pageNo)
			throws TPlusException;

	public TransactionLogDto getTransactionDto(String tranxId)
			throws TPlusException;

	public DisputeManualReconDto getDisputeManualReconDto(String tranxId)
			throws TPlusException;

	public boolean saveManualRecon(
			DisputeManualReconDto objDisputeManualReconDto)
			throws TPlusException;
	
	public boolean TranxRevert(String tranxId, String remarks, String user)
	throws TPlusException;

}
