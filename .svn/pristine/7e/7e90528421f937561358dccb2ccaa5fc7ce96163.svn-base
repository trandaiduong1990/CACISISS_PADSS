package org.transinfo.cacis.dto.useraccess;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorActionForm;

public class PermissionMatrixDto extends ValidatorActionForm{

	
                       	
        public PermissionMatrixDto(){}
        
        public static class Id implements Serializable{
            
            private String userType;
            private String screenAccessible;
            
            public String getUserType() {
		return userType;
	    }
	    public void setUserType(String userType) {
		this.userType = userType;
            }
            public String getScreenAccessible() {
		return screenAccessible;
            }
            public void setScreenAccessible(String screenAccessible) {
		this.screenAccessible = screenAccessible;
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
	
	
}
