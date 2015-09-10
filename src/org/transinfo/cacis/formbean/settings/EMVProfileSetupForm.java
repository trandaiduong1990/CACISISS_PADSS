package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.EMVProfileDAO;

import com.opensymphony.oscache.util.StringUtil;

@SuppressWarnings("serial")
public class EMVProfileSetupForm extends ValidatorForm {

	private String emvProfileName;
	private String applType;
	private String applCryptogram;
	private String issuerAuthentication = "N";
	private String fallbackAllowed = "N";
	private String scriptadviceSupported = "N";
	private String offlineAllowed = "N";
	private String offlineFloorLimit;
	private String offlineMaxPin;
	private String status;
	private String screen;
	
	private Map applicationTypeList;
	private Map applCryptogramList;
	
	public String getIssuerAuthentication() {
		return issuerAuthentication;
	}
	public void setIssuerAuthentication(String issuerAuthentication) {
		this.issuerAuthentication = issuerAuthentication;
	}
	public String getFallbackAllowed() {
		return fallbackAllowed;
	}
	public void setFallbackAllowed(String fallbackAllowed) {
		this.fallbackAllowed = fallbackAllowed;
	}
	public String getScriptadviceSupported() {
		return scriptadviceSupported;
	}
	public void setScriptadviceSupported(String scriptadviceSupported) {
		this.scriptadviceSupported = scriptadviceSupported;
	}
	public String getOfflineAllowed() {
		return offlineAllowed;
	}
	public void setOfflineAllowed(String offlineAllowed) {
		this.offlineAllowed = offlineAllowed;
	}
	public String getOfflineFloorLimit() {
		return offlineFloorLimit;
	}
	public void setOfflineFloorLimit(String offlineFloorLimit) {
		this.offlineFloorLimit = offlineFloorLimit;
	}
	public String getOfflineMaxPin() {
		return offlineMaxPin;
	}
	public void setOfflineMaxPin(String offlineMaxPin) {
		this.offlineMaxPin = offlineMaxPin;
	}
	
	public String getEmvProfileName() {
		return emvProfileName;
	}
	public void setEmvProfileName(String emvProfileName) {
		this.emvProfileName = emvProfileName;
	}
	public String getApplType() {
		return applType;
	}
	public void setApplType(String applType) {
		this.applType = applType;
	}
	public String getApplCryptogram() {
		return applCryptogram;
	}
	public void setApplCryptogram(String applCryptogram) {
		this.applCryptogram = applCryptogram;
	}
	public Map getApplicationTypeList() {
		return applicationTypeList;
	}
	public void setApplicationTypeList(Map applicationTypeList) {
		this.applicationTypeList = applicationTypeList;
	}
	public Map getApplCryptogramList() {
		return applCryptogramList;
	}
	public void setApplCryptogramList(Map applCryptogramList) {
		this.applCryptogramList = applCryptogramList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public void getPreListData() {
		
		try {
			EMVProfileDAO objEmvProfileDAO = DAOFactory.getInstance()
					.getEMVProfileDAO();
			
			if (applicationTypeList == null) {
				applicationTypeList = objEmvProfileDAO.codeMasterList();
			}
			
			applCryptogramList = objEmvProfileDAO.getCryptogramList(applType);
			
//			if (!StringUtils.isBlank(applType)) {
//				applCryptogramList = objEmvProfileDAO.getCryptogramList(applType);
//			} else {
//				applCryptogramList = null;
//			}
			
		} catch (Exception e) {
			System.out
			.println("Error while getting  PreListData in EmvProfileForm:"
					+ e);
		}
	}
}
