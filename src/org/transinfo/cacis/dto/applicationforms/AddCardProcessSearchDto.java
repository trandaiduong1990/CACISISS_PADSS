package org.transinfo.cacis.dto.applicationforms;

import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings({ "serial", "unchecked" })
public class AddCardProcessSearchDto implements Serializable {

	private String customerName;
	private String nric;

	private Collection searchList;
	private int totalCount;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
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
