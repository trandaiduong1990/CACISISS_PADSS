package org.transinfo.cacis.formbean.alert;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.alert.AlertMasterDAO;


/**
 * AlertMasterFormDto Value Object.
 * This class is value object representing database table ALERT_MASTER
 * This class is intented to be used together with associated Dao object.
 */

public class AlertMasterForm extends ValidatorForm{

    /**
     * Persistent Instance variables. This data is directly
     * mapped to the columns of database table.
     */
        private String alertCode;
        private String alertDesc;
        private String emailSubject;
        private String emailBody;
        private String emailSignature;
        private String status;
        private Date updatedDate = new Date();
        private String userId;

    private Map statusList;        
    private Map alertUserList; 
    private Map selectedList = new TreeMap();
    private String[] selUserList;
     
    public AlertMasterForm()
    {
        //getPreListData();
    }

    /**
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

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
        
        public Map getStatusList() {
		return statusList;
	}

        public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}

        public Map getAlertUserList() {
		return alertUserList;
	}

        public void setAlertUserList(Map alertUserList) {
		this.alertUserList = alertUserList;
	}
        
        public Map getSelectedList() {
		return selectedList;
	}

        public void setSelectedList(Map selectedList) {
		this.selectedList = selectedList;
	}
        
        public String[] getSelUserList() {
		return selUserList;
	}

        public void setSelUserList(String[] selUserList) {
		this.selUserList = selUserList;
	} 

        public void getPreListData()
        {
	        try{
	            AlertMasterDAO objAlertMasterDAO = DAOFactory.getInstance().getAlertMasterDAO();
                    
                    if(statusList==null) {
                        statusList = objAlertMasterDAO.statusListData("CODE_AI");
                        System.out.println("statusList==>"+statusList.size());
                    }
                    
	            if(alertUserList==null)  {
	                alertUserList = objAlertMasterDAO.getUserList(alertCode);
                        System.out.println("alertCode from form==>"+alertCode);
                        System.out.println("alertUserList in form==>"+alertUserList.size());
	            }

	        }catch(Exception e){
	            System.out.println("Error while getting  PreListData:"+e);
	        }
        }
}




