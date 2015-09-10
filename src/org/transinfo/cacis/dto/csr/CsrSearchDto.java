package org.transinfo.cacis.dto.csr;

import java.io.Serializable;

public class CsrSearchDto implements Serializable{

	//	serachFiled can be CardNo or Nric or  CustomerName
	private String  searchFiled;
	private String quickCardNo;
	private String callRefNo;
	
	public CsrSearchDto(){}

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
