package org.transinfo.cacis.dto.settings;

import java.io.Serializable;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class CustomerTypeSearchDto implements Serializable {

   private String customerTypeId;
   private String issuerId;

   public CustomerTypeSearchDto() {
	  
   }

  
	public String getCustomerTypeId() {
		return customerTypeId;
	}
	
	public void setCustomerTypeId(String customerTypeId) {
		this.customerTypeId = customerTypeId;
	}
	
	public String getIssuerId() {
		return issuerId;
	}
	
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	

}
