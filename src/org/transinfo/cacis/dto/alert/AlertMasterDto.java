package org.transinfo.cacis.dto.alert;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class AlertMasterDto implements Serializable{
        private String alertCode;	
        private String alertDesc;
	private String emailSubject;
        private String emailBody; 
        private String emailSignature;
        private String status;
        private Date updatedDate = new Date();
        private String userId;
	
           
        private Map selectedList = new TreeMap();
        private Map alertAdminUser = new HashMap();
       
        
        public String getAlertCode() {
		return alertCode;
	}
        		
        public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}
        
        public String getAlertDesc() {
		return alertDesc;
	}
        
        public void setAlertDesc(String alertDesc) {
		this.alertDesc = alertDesc;
	}
                        
        public String getEmailSubject() {
		return emailSubject;
	}
               
        public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
                       
        public String getEmailBody() {
		return emailBody;
	}
            
        public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
        
        public String getEmailSignature() {
		return emailSignature;
	}
            
        public void setEmailSignature(String emailSignature) {
		this.emailSignature = emailSignature;
	}
        
        public String getStatus() {
		return status;
	}
            
        public void setStatus(String status) {
		this.status = status;
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
                                           
        public Map getAlertAdminUser() {
		return alertAdminUser;
	}
                   
        public void setAlertAdminUser(Map alertAdminUser) {
		this.alertAdminUser = alertAdminUser;
                
	} 
        
        public Map getSelectedList() {
		return selectedList;
	}

        public void setSelectedList(Map selectedList) {
		this.selectedList = selectedList;
	}
        
}

