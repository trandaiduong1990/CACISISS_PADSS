package org.transinfo.cacis.dto.useraccess;

import org.apache.struts.validator.ValidatorActionForm;

public class UserSetupSearchDto extends ValidatorActionForm{

	private String issuerId;
	private String searchUserId;
        private String searchRoleId;
        private String searchStatus;
        private String userType;
        
        
        public UserSetupSearchDto(){}
        
        public UserSetupSearchDto(String issuerId, String searchUserId){
            this.issuerId = issuerId;
            this.searchUserId = searchUserId;            
        }
        	
       public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
        
	public String getSearchUserId() {
		return searchUserId;
	}
	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}
                
        public String getSearchRoleId() {
		return searchRoleId;
	}
	public void setSearchRoleId(String searchRoleId) {
		this.searchRoleId = searchRoleId;
	}
        
        public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
        
        public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
       
}
