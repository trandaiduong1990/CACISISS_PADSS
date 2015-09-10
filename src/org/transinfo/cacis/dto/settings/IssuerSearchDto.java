package org.transinfo.cacis.dto.settings;

import java.io.Serializable;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class IssuerSearchDto implements Serializable {

	  private String issuerId;
	  private String issuerName;
	  private String  status;
	 
	
	  
   public IssuerSearchDto() {
	 
   }

   public IssuerSearchDto(String issuerId, String issuerName,String  status) {
      setIssuerId(issuerId);
      setIssuerName(issuerName);
      setStatus(status);
   }

public String getIssuerId() {
	return issuerId;
}

public void setIssuerId(String issuerId) {
	this.issuerId = issuerId;
}

public String getIssuerName() {
	return issuerName;
}

public void setIssuerName(String issuerName) {
	this.issuerName = issuerName;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}




}
