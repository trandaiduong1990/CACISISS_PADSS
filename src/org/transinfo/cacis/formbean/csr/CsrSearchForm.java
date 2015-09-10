package org.transinfo.cacis.formbean.csr;

import org.apache.struts.validator.ValidatorActionForm;

public class CsrSearchForm extends ValidatorActionForm {
	
//serachFiled can be CardNo or Nric or  CustomerName
	private String  searchFiled;
	private String quickCardNo;
	private String callRefNo;
	public CsrSearchForm(){}

	public String getQuickCardNo() {
		return quickCardNo;
	}

	public void setQuickCardNo(String quickCardNo) {
		this.quickCardNo = quickCardNo;
	}

	public String getSearchFiled() {
		return searchFiled;
	}

	public void setSearchFiled(String searchFiled) {
		this.searchFiled = searchFiled;
	}

	public String getCallRefNo() {
		return callRefNo;
	}

	public void setCallRefNo(String callRefNo) {
		this.callRefNo = callRefNo;
	}

	

}
