package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings( { "serial", "unchecked" })
public class TranxEnquiryNonReconTranxlogSearchDto implements Serializable {

	private String accountId;

	private Collection searchList;
	private int totalCount;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Collection getSearchList() {
		return searchList;
	}
	public void setSearchList(Collection searchList) {
		this.searchList = searchList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
