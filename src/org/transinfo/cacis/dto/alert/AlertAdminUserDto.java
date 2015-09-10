package org.transinfo.cacis.dto.alert;

import java.io.Serializable;
import java.util.Date;

/*
 * This is a bean - and also a Value - or Data Transfer Object
 */
public class AlertAdminUserDto implements Serializable {

    private String alertCode;
    private String adminUserId;
    private String adminStatus;      //="A";
    private Date updatedDate = new Date();
    private String userId;

    public AlertAdminUserDto() {}

   
     public String getAlertCode() {
	   return alertCode;
     }
        		
     public void setAlertCode(String alertCode) {
	   this.alertCode = alertCode;
     }
               
     public String getAdminUserId() {
	   return adminUserId;
     }
        		
     public void setAdminUserId(String adminUserId) {
	   this.adminUserId = adminUserId;
     }
     
     public String getAdminStatus() {
	   return adminStatus;
     }
        		
     public void setAdminStatus(String adminStatus) {
	   this.adminStatus = adminStatus;
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
