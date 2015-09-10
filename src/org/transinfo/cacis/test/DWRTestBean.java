package org.transinfo.cacis.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings( { "unchecked", "serial" })
public class DWRTestBean implements Serializable {

	private String supplCustomerName;
	private String supplSureName;
	private Set applicationAddress = new HashSet();
	private Map appCardProducts = new HashMap();
	private int applicationType;

	public String getSupplCustomerName() {
		return supplCustomerName;
	}

	public void setSupplCustomerName(String supplCustomerName) {
		this.supplCustomerName = supplCustomerName;
	}

	public String getSupplSureName() {
		return supplSureName;
	}

	public void setSupplSureName(String supplSureName) {
		this.supplSureName = supplSureName;
	}

	public Set getApplicationAddress() {
		return applicationAddress;
	}

	public void setApplicationAddress(Set applicationAddress) {
		this.applicationAddress = applicationAddress;
	}

	public Map getAppCardProducts() {
		return appCardProducts;
	}

	public void setAppCardProducts(Map appCardProducts) {
		this.appCardProducts = appCardProducts;
	}

	public int getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(int applicationType) {
		this.applicationType = applicationType;
	}

}
