package org.transinfo.cacis.dataacess.dao.customerservice;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.csr.NonReconTransactionLogDto;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.customerservice.AddProductSearchDto;
import org.transinfo.cacis.dto.customerservice.AddProductSetupDto;
import org.transinfo.cacis.dto.customerservice.NonReconTranxEnquirySearchDto;
import org.transinfo.cacis.dto.customerservice.TranxEnquirySearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;

public interface AddProductDAO extends BaseDAO {

	public AddProductSearchDto search(
			AddProductSearchDto objAddProductSearchDto, int pageNo)
			throws TPlusException;

	public AddProductSetupDto get(AddProductSetupDto objAddProductSetupDto,
			String customerId) throws TPlusException;

	public boolean addProduct(AddProductSetupDto objAddProductSetupDto,
			String userID) throws TPlusException;
}
