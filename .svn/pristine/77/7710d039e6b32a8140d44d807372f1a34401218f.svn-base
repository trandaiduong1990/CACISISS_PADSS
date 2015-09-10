package org.transinfo.cacis.formbean.useraccess;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.AdminLoginDAO;

@SuppressWarnings( { "unchecked", "serial" })
public class AdminLoginForm extends ValidatorForm {

	private String issuerId;
	private String password;
	// private String remoteIp = "111.111.111.111";
	private String remoteIp;
	private int pwdExpiryPeriod;
	private int maxLoginFailed;
	private int ftlValidityDays;
	private String lockUser;
	private Date updatedDate;
	private String userId;
	private int pwdExpiryRemainderDays;
	private String language;

	private Map issuerList = new TreeMap();

	public AdminLoginForm() {
		getPreListData("ASP");
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Map getIssuerList() {
		System.out.println("ISSUER SIZE11=" + issuerList.size());
		return issuerList;
	}

	public void setIssuerList(Map issuerList) {
		this.issuerList = issuerList;
	}

	public void getPreListData(String issuerType) {

		try {

			AdminLoginDAO objAdminLoginDAO = DAOFactory.getInstance()
					.getAdminLoginDAO();

			issuerList = objAdminLoginDAO.issuerListData(issuerType);
			System.out.println("ISSUER SIZE=" + issuerList.size());

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:" + e);
		}
	}

	public static void main(String s[]) {
		AdminLoginForm alf = new AdminLoginForm();
		alf.getPreListData("ISSUERS");
		System.out.println(alf.getIssuerList().size());
	}

}
