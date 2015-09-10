package org.transinfo.cacis.dto.useraccess;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

public class RoleFunctionSetDto extends ValidatorActionForm{
        
        private Date updatedDate = new Date();
        private String userId;
        
                                             	
        public RoleFunctionSetDto(){}
        
        public static class Id implements Serializable{
            private String issuerId;
            private String roleId;  
            private String functionId;
            
            public String getIssuerId() {
		return issuerId;
            }
            public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
            }
        
            public String getRoleId() {
            	return roleId;
            }
            public void setRoleId(String roleId) {
		this.roleId = roleId;
            }
        
            public String getFunctionId() {
		return functionId;
            }
            public void setFunctionId(String functionId) {
		this.functionId = functionId;
            }     
        }
        
        public Id id = new Id();

        /**
	 * @return Returns the id.
	 */
	public Id getId() {
            
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Id id) {
		this.id = id;
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
