package org.transinfo.cacis.formbean.batchprocess;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings( { "serial", "unchecked" })
public class ReplacementCardBPForm extends ValidatorForm {

	private String issuerId;
	private String userId;
	private String authUserId;
	private String authPassword;
	private String batchId;
	private Collection appList;
	private Collection successAppsPrimaryList;
	private Collection successAppsSuppList;

	private String cardNos;
	private String totalNoOfApps;
	private String appIds;
	private String newCardNos;

	public String getIssuerId() {
		return issuerId;
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

	public String getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(String authUserId) {
		this.authUserId = authUserId;
	}

	public String getAuthPassword() {
		return authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Collection getAppList() {
		return appList;
	}

	public void setAppList(Collection appList) {
		this.appList = appList;
	}

	public Collection getSuccessAppsPrimaryList() {
		return successAppsPrimaryList;
	}

	public void setSuccessAppsPrimaryList(Collection successAppsPrimaryList) {
		this.successAppsPrimaryList = successAppsPrimaryList;
	}

	public Collection getSuccessAppsSuppList() {
		return successAppsSuppList;
	}

	public void setSuccessAppsSuppList(Collection successAppsSuppList) {
		this.successAppsSuppList = successAppsSuppList;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		authUserId = null;
		authPassword = null;
	}

	public String getCardNos() {
		return cardNos;
	}

	public void setCardNos(String cardNos) {
		this.cardNos = cardNos;
	}

	public String getTotalNoOfApps() {
		return totalNoOfApps;
	}

	public void setTotalNoOfApps(String totalNoOfApps) {
		this.totalNoOfApps = totalNoOfApps;
	}

	public String getAppIds() {
		return appIds;
	}

	public void setAppIds(String appIds) {
		this.appIds = appIds;
	}

	public String getNewCardNos() {
		return newCardNos;
	}

	public void setNewCardNos(String newCardNos) {
		this.newCardNos = newCardNos;
	}

}
