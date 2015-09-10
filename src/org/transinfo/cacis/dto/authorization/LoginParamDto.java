package org.transinfo.cacis.dto.authorization;

import java.io.Serializable;
import java.util.Date;

public class LoginParamDto implements Serializable{

	private String issuerId;	
	private int pwdExpiryPeriod;
	private int maxLoginFailed;
	private int ftlValidityDays;
	private String lockUser;        
	private Date updatedDate = new Date();
	private String userId;
	private int pwdExpiryRemainderDays = 10; //this field not yet added to table

	public LoginParamDto(){}


	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public int getPwdExpiryPeriod() {
		return pwdExpiryPeriod;
	}

	public void setPwdExpiryPeriod(int pwdExpiryPeriod) {
		this.pwdExpiryPeriod = pwdExpiryPeriod;
	}

	public int getMaxLoginFailed() {
		return maxLoginFailed;
	}

	public void setMaxLoginFailed(int maxLoginFailed) {
		this.maxLoginFailed = maxLoginFailed;
	}

	public int getFtlValidityDays() {
		return ftlValidityDays;
	}

	public void setFtlValidityDays(int ftlValidityDays) {
		this.ftlValidityDays = ftlValidityDays;
	}

	public String getLockUser() {
		return lockUser;
	}

	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
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

	public int getPwdExpiryRemainderDays() {
		return pwdExpiryRemainderDays;
	}                   
	public void setPwdExpiryRemainderDays(int pwdExpiryRemainderDays) {
		this.pwdExpiryRemainderDays = pwdExpiryRemainderDays;
	}
}

