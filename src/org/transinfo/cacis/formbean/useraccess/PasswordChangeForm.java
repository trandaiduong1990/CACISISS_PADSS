package org.transinfo.cacis.formbean.useraccess;

import org.apache.struts.validator.ValidatorForm;


public class PasswordChangeForm extends ValidatorForm{

        private String issuerId;
	private String userId;
        private String oldPassword;
        private String newPassword;
        private String confirmPwd;       
        private String hintQuestion;
	private String hintAnswer;
        private String firstTimeLogin;
        

        public PasswordChangeForm() {}
        
                       
        public String getIssuerId() {
		return this.issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
        
        public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
        
        public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
        
        public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
        
        public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
                        
        public String getHintAnswer() {
		return hintAnswer;
	}
	public void setHintAnswer(String hintAnswer) {
		this.hintAnswer = hintAnswer;
	}
	public String getHintQuestion() {
		return hintQuestion;
	}
	public void setHintQuestion(String hintQuestion) {
		this.hintQuestion = hintQuestion;
	}

        /** Getter for property firstTimeLogin.
         * @return Value of property firstTimeLogin.
         */
        public String getFirstTimeLogin() {
            return firstTimeLogin;
        }        
       
        /** Setter for property firstTimeLogin.
         * @param firstTimeLogin New value of property firstTimeLogin.
         */
        public void setFirstTimeLogin(String firstTimeLogin) {
            this.firstTimeLogin = firstTimeLogin;
        }
        
}




