package org.transinfo.cacis.dto.alert;

import java.io.Serializable;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class AlertMasterSearchDto implements Serializable {

    private String searchAlertCode;
    

    public AlertMasterSearchDto() {}

   
     public String getSearchAlertCode() {
	   return searchAlertCode;
     }
        		
     public void setSearchAlertCode(String searchAlertCode) {
	   this.searchAlertCode = searchAlertCode;
     }
             
}
