package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings({ "unchecked", "serial" })
public class CurrencyDto implements Serializable{
	
	private String currencyCode;
	private String currencySymbol;
	private String currencyName;
	private String lastUpdatedBy;
	private Date   lastUpdatedDate;
	
	//for Excell data list
	private ArrayList currRateList = new ArrayList();
	private ArrayList currCodeList = new ArrayList();
	public CurrencyDto(){}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public ArrayList getCurrRateList() {
		return currRateList;
	}

	public void setCurrRateList(ArrayList currRateList) {
		this.currRateList = currRateList;
	}

	public ArrayList getCurrCodeList() {
		return currCodeList;
	}

	public void setCurrCodeList(ArrayList currCodeList) {
		this.currCodeList = currCodeList;
	}

	
	
	

}
