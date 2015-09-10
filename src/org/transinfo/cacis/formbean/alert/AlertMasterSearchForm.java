package org.transinfo.cacis.formbean.alert;

import org.apache.struts.validator.ValidatorActionForm;

public class AlertMasterSearchForm extends ValidatorActionForm{

	
    private String searchAlertCode;
    
    
    public AlertMasterSearchForm() {}

   
     public String getSearchAlertCode() {
	   return searchAlertCode;
     }
        		
     public void setSearchAlertCode(String searchAlertCode) {
	   this.searchAlertCode = searchAlertCode;
     }
}
