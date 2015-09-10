package org.transinfo.cacis.dto.useraccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.formbean.common.DateForm;

public class UserMasterDto extends ValidatorActionForm{

	private String firstName;
	private String lastName;
	private String roleId;
	private String emailId;
	private String department;
	private String phone;
	private String password;
	private String prevPassword1;
	private String prevPassword2;
	private Date pwdModifiedOn;
	private DateForm pwdModifiedOnForm = new DateForm();
	private int loginFailCount;
	private String hintQuestion;
	private String hintAnswer;
	private String status;
	private String otherAddress;
	private String permanentAddress;
	private char firstTimeLogin;
	private Date ftlExpiryDate;
	private DateForm ftlExpiryDateForm = new DateForm();
	private String userStationIp;
	private String userType;
	private Date lastUpdatedDate;
	private DateForm lastUpdatedDateForm = new DateForm();
	private String lastUpdatedBy;
	private int passwordModifiedDays = 30; //Added temporarily
	private String passwordExpiry;
	private boolean userLogin;

	private Map screenFunctionList;

	private String branchId;


	public static class Id implements Serializable
	{

		private String issuerId;
		private String userId;

		public Id(String issuerId, String userId) {
			this.issuerId = issuerId;
			this.userId = userId;

		}
		public Id() {}

		public String getIssuerId()
		{
			return issuerId;
		}
		public void setIssuerId(String issuerId)
		{
			this.issuerId = issuerId;
		}

		public String getUserId()
		{
			return userId;
		}

		public void setUserId(String userId)
		{
			this.userId = userId;
		}

		public int hashCode() {
			return issuerId.hashCode() + userId.hashCode();
		}
		public boolean equals(Object other)
		{
			if (other instanceof Id)
			{
				Id that = (Id) other;
				return that.issuerId.equals(this.issuerId) &&
						that.userId.equals(this.userId) ;
			}
			else
			{
				return false;
			}
		}

	}

	public Id id = new Id();

	public Id getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Id id) {
		this.id = id;
	}


	public UserMasterDto(){}

	public UserMasterDto(String issuerId, String userId)
	{
		this.id.issuerId = issuerId;
		this.id.userId = userId;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public char getFirstTimeLogin() {
		return firstTimeLogin;
	}
	public void setFirstTimeLogin(char firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}
	public Date getFtlExpiryDate() {
		return ftlExpiryDate;
	}
	public void setFtlExpiryDate(Date ftlExpiryDate) {
		this.ftlExpiryDate = ftlExpiryDate;
		if (ftlExpiryDate != null) {
			this.ftlExpiryDateForm = new DateForm(ftlExpiryDate);
		}
	}
	public DateForm getFtlExpiryDateForm() {
		return ftlExpiryDateForm;
	}
	public void setFtlExpiryDateForm(DateForm ftlExpiryDateForm) {
		this.ftlExpiryDateForm = ftlExpiryDateForm;
		ftlExpiryDate = ftlExpiryDateForm.toDate();
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate = new Date();
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
		if (lastUpdatedDate != null) {
			this.lastUpdatedDateForm = new DateForm(lastUpdatedDate);
		}
	}
	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}
	public void setLastUpdatedDateForm(DateForm lastUpdatedDateForm) {
		this.lastUpdatedDateForm = lastUpdatedDateForm;
		lastUpdatedDate = lastUpdatedDateForm.toDate();
	}
	public int getLoginFailCount() {
		return loginFailCount;
	}
	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	public String getOtherAddress() {
		return otherAddress;
	}
	public void setOtherAddress(String otherAddress) {
		this.otherAddress = otherAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPrevPassword1() {
		return prevPassword1;
	}
	public void setPrevPassword1(String prevPassword1) {
		this.prevPassword1 = prevPassword1;
	}
	public String getPrevPassword2() {
		return prevPassword2;
	}
	public void setPrevPassword2(String prevPassword2) {
		this.prevPassword2 = prevPassword2;
	}
	public Date getPwdModifiedOn() {
		return pwdModifiedOn = new Date();
	}
	public void setPwdModifiedOn(Date pwdModifiedOn) {
		this.pwdModifiedOn = pwdModifiedOn;
		if (pwdModifiedOn != null) {
			this.pwdModifiedOnForm = new DateForm(pwdModifiedOn);
		}
	}
	public DateForm getPwdModifiedOnForm() {
		return pwdModifiedOnForm;
	}
	public void setPwdModifiedOnForm(DateForm pwdModifiedOnForm) {
		this.pwdModifiedOnForm = pwdModifiedOnForm;
		pwdModifiedOn = pwdModifiedOnForm.toDate();
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserStationIp() {
		return userStationIp;
	}
	public void setUserStationIp(String userStationIp) {
		this.userStationIp = userStationIp;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getPasswordModifiedDays() {
		return passwordModifiedDays;
	}
	public void setPasswordModifiedDays(int passwordModifiedDays) {
		this.passwordModifiedDays = passwordModifiedDays;
	}

	public String getPasswordExpiry() {
		return passwordExpiry;
	}
	public void setPasswordExpiry(String passwordExpiry) {
		this.passwordExpiry = passwordExpiry;
	}


	public Map getScreenFunctionList() {
		return screenFunctionList;
	}
	public void setScreenFunctionList(Map screenFunctionList) {
		this.screenFunctionList = screenFunctionList;
	}

	public boolean isUserLogin() {
		return userLogin;
	}
	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}


	/**
	 * Setter for property screenFunctionList.
	 * @param screenFunctionList New value of property screenFunctionList.
	 */
	public void setScreenFunctionList(java.util.HashMap screenFunctionList) {
		this.screenFunctionList = screenFunctionList;
	}

	public boolean hasScreen(String screenName){
		return this.screenFunctionList.containsKey(screenName);
	}

	public boolean hasFunction(String screenName, String functionName){
		ArrayList objFunctionList = new ArrayList();
		try{
			if(this.screenFunctionList.containsKey(screenName)){
				objFunctionList = (ArrayList)this.screenFunctionList.get(screenName);
			}
		}catch(Exception ex){
			System.out.println("Exception while checking function exist or not in the has map");
		}
		return objFunctionList.contains(functionName);
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}



}
