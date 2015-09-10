package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.MCCDAO;

public class MCCSetupForm extends ValidatorForm{
	
	private String merchantId;
	private String issuerId;
	private String merchantType;
	private String floorLimit;
	private String currencyCode;
	private String userId;
    
	 private Map currencyList;
	
	public MCCSetupForm(){
		//getPreListData();
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getFloorLimit() {
		return floorLimit;
	}
	public void setFloorLimit(String floorLimit) {
		this.floorLimit = floorLimit;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	
	
	public void getPreListData(){
		 
		try{
			MCCDAO objDAO = DAOFactory.getInstance().getMCCDAO();
			
	 		if(currencyList==null)  {
	 			currencyList = objDAO.currencyListData();
		     }
	 		else{
			   System.out.println("Cusrrency List exisated");
		     }
	      }catch(Exception e){
		      System.out.println("Error while getting preList in MCCsetupForm:"+e.getMessage());
		    }
		}

	public void setCurrencyList(Map currencyList) {
		this.currencyList = currencyList;
	}

	public Map getCurrencyList() {
		return currencyList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
