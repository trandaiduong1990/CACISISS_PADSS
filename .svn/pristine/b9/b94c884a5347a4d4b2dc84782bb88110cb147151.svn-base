package org.transinfo.cacis.formbean.riskmanagement;

import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxActionDAO;

public class RiskTranxActionForm extends ValidatorForm{

        private String riskId;
        private String action;
        private String email;
        private String smsAlert;
        private String status;
        private Date updatedDate = new Date();
        private String userId;
        
        private Map riskIdList;
        private Map actionList;
        private Map smsAlertList;
        private Map statusList;
                
        public RiskTranxActionForm(){
          //  getPreListData();
        }

        
        public String getRiskId() {
            return riskId;
        }                
        public void setRiskId(String riskId) {
            this.riskId = riskId;
        }
       
         public String getAction() {
            return action;
        }        
       
        public void setAction(String action) {
            this.action = action;
        }
        
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
        
       
        public String getSmsAlert() {
            return smsAlert;
        }       
        
        public void setSmsAlert(String smsAlert) {
            this.smsAlert = smsAlert;
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


        public Map getRiskIdList() {
            return riskIdList;
        }    
   
        public void setRiskIdList(Map riskIdList) {
            this.riskIdList = riskIdList;
        }    
          
    
        public Map getActionList() {
            return actionList;
        }
    
   
        public void setActionList(Map actionList) {
            this.actionList = actionList;
        }
    
    
        public Map getSmsAlertList() {
            return smsAlertList;
        }
    
        public void setSmsAlertList(Map smsAlertList) {
            this.smsAlertList = smsAlertList;
        }
    
   
        public Map getStatusList() {
            return statusList;
        }
    
        public void setStatusList(Map statusList) {
            this.statusList = statusList;
        }

      
       public void getPreListData()
       {

        try
        {
            
            RiskTranxActionDAO objRiskActionDAO = DAOFactory.getInstance().getRiskTranxActionDAO();
            if(riskIdList==null)
            {
                riskIdList = objRiskActionDAO.getRiskId();
                System.out.println("riskIdList==>"+riskIdList.size());
            }
            
            if(actionList==null)
            {
                actionList = objRiskActionDAO.statusListData("RISKACTION");
                System.out.println("actionList==>"+actionList.size());
            }
            
            if(smsAlertList==null)
            {
                smsAlertList = objRiskActionDAO.statusListData("YESNO");
                System.out.println("smsAlertList==>"+smsAlertList.size());
            }
                                 
            if(statusList==null) {
                statusList = objRiskActionDAO.statusListData("CODE_AI");
                System.out.println("statusList==>"+statusList.size());
            }

        }catch(Exception e){
            System.out.println("Error while getting  PreListData:"+e);
        }
    }

    
    
}




