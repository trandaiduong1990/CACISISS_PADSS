package org.transinfo.cacis.formbean.authorization;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

public class ServerParamMainForm extends ValidatorActionForm{

	    
        private String issuerId;	
        private String paramName;
	private String paramValue;
        private String paramType;             
        private Date updatedDate = new Date();
        private String userId;
	
        
        public String getIssuerId() {
		return issuerId;
	}
        		
        public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
        
        public String getParamName() {
		return paramName;
	}
        
        public void setParamName(String paramName) {
		this.paramName = paramName;
	}
                        
        public String getParamValue() {
		return paramValue;
	}
               
        public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
                       
        public String getParamType() {
		return paramType;
	}
            
        public void setParamType(String paramType) {
		this.paramType = paramType;
	}
                        
        public Date getUpdatedDate() {
		return updatedDate;
	}
        
        public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
        
        public String getUserId() {
		return userId;
	}
                   
        public void setUserId(String userId) {
		this.userId = userId;
	}
        
}
