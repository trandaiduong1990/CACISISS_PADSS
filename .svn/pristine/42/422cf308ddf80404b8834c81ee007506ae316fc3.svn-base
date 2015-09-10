package org.transinfo.cacis.formbean.authorization;

import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.LoginParamDAO;
public class LoginParamForm extends ValidatorActionForm{

	    
        private String issuerId;	
        private String pwdExpiryPeriod;
	private String maxLoginFailed;
        private String ftlValidityDays;
        private String lockUser;                
        private String userId;
        
        private Map optionList;
                
        public LoginParamForm(){
            getPreListData();
        }
        
        
        public String getIssuerId() {
		return issuerId;
	}
        		
        public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
        
        public String getPwdExpiryPeriod() {
		return pwdExpiryPeriod;
	}
        
        public void setPwdExpiryPeriod(String pwdExpiryPeriod) {
		this.pwdExpiryPeriod = pwdExpiryPeriod;
	}
                        
        public String getMaxLoginFailed() {
		return maxLoginFailed;
	}
               
        public void setMaxLoginFailed(String maxLoginFailed) {
		this.maxLoginFailed = maxLoginFailed;
	}
                       
        public String getftlValidityDays() {
		return ftlValidityDays;
	}
            
        public void setFtlValidityDays(String ftlValidityDays) {
		this.ftlValidityDays = ftlValidityDays;
	}
        
        public String getLockUser() {
		return lockUser;
	}
            
        public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}
        public String getUserId() {
		return userId;
	}
                   
        public void setUserId(String userId) {
		this.userId = userId;
	}
        
        public Map getOptionList() {
		return optionList;
	}
                   
        public void setOptionList(Map optionList) {
		this.optionList = optionList;
	}
        
        public void getPreListData()
       {
        try{
            
            LoginParamDAO objLoginParamDAO = DAOFactory.getInstance().getLoginParamDAO();
            if(optionList==null)  {
                optionList = objLoginParamDAO.statusListData("YESNO");
            }
                       
        }catch(Exception e){
            System.out.println("Error while getting  PreListData:"+e);
        }
    }
        
}
