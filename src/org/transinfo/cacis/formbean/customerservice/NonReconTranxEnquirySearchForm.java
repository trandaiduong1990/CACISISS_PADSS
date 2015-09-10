package org.transinfo.cacis.formbean.customerservice;

import java.util.Collection;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.NonReconTransactionEnquiryDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.TransactionEnquiryDAO;

@SuppressWarnings( { "serial", "unchecked" })
public class NonReconTranxEnquirySearchForm extends ValidatorForm {
	private String cardNo;
	private String authCode;
	private String startDate;
	private String endDate;

	private Collection searchList;
	private String totalCount;

	private String pageNo;

	private Map responseCodes;
	
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Collection getSearchList() {
		return searchList;
	}

	public void setSearchList(Collection searchList) {
		this.searchList = searchList;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	
	public void getPreListData() {

		try {
			
			NonReconTransactionEnquiryDAO objNonTransactionEnquiryDAO = DAOFactory.getInstance().getNonReconTransactionEnquiryDAO();
			
			if (responseCodes == null) {
				responseCodes = objNonTransactionEnquiryDAO.responseCodesList();
			} else {
				System.out.println("CardProductList");
			}
			
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CardProductSearchForm:"
							+ e.getMessage());
		}
	}

	public Map getResponseCodes() {
		return responseCodes;
	}

	public void setResponseCodes(Map responseCodes) {
		this.responseCodes = responseCodes;
	}

}
