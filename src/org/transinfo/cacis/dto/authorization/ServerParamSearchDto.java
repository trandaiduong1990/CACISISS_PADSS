package org.transinfo.cacis.dto.authorization;

import java.io.Serializable;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class ServerParamSearchDto implements Serializable {

    private String issuerId;
    private String paramType;
    

    public ServerParamSearchDto() {}

   
     public String getIssuerId() {
	  return issuerId;
     }
        		
     public void setIssuerId(String issuerId) {
	  this.issuerId = issuerId;
     }
        
     public String getParamType() {
	  return paramType;
     }
            
     public void setParamType(String paramType) {
	  this.paramType = paramType;
     }

}
