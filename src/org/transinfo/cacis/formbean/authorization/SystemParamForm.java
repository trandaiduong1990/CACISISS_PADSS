package org.transinfo.cacis.formbean.authorization;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings("serial")
public class SystemParamForm extends ValidatorForm {

	private String issuerId = "ASP";
	private String mailServerAddress;
	private String mailServerPort;
	private String fromName;
	private String fromMail;
	private String voicePort;
	private String serverPort;
	private String dataTimeout;
	private String noWorkerThread;
	private String debugLevel;
	private String userId;
	private String renewalTimeInterval;
	private String changeCardCloseInterval;
	private String cardClose;
	private String expireDateExtend;

	public SystemParamForm() {
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getMailServerAddress() {
		return mailServerAddress;
	}

	public void setMailServerAddress(String mailServerAddress) {
		this.mailServerAddress = mailServerAddress;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getVoicePort() {
		return voicePort;
	}

	public void setVoicePort(String voicePort) {
		this.voicePort = voicePort;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getDataTimeout() {
		return dataTimeout;
	}

	public void setDataTimeout(String dataTimeout) {
		this.dataTimeout = dataTimeout;
	}

	public String getNoWorkerThread() {
		return noWorkerThread;
	}

	public void setNoWorkerThread(String noWorkerThread) {
		this.noWorkerThread = noWorkerThread;
	}

	public String getDebugLevel() {
		return debugLevel;
	}

	public void setDebugLevel(String debugLevel) {
		this.debugLevel = debugLevel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRenewalTimeInterval() {
		return renewalTimeInterval;
	}

	public void setRenewalTimeInterval(String renewalTimeInterval) {
		this.renewalTimeInterval = renewalTimeInterval;
	}

	public String getChangeCardCloseInterval() {
		return changeCardCloseInterval;
	}

	public void setChangeCardCloseInterval(String changeCardCloseInterval) {
		this.changeCardCloseInterval = changeCardCloseInterval;
	}

	public String getCardClose() {
		return cardClose;
	}

	public void setCardClose(String cardClose) {
		this.cardClose = cardClose;
	}

	public String getExpireDateExtend() {
		return expireDateExtend;
	}

	public void setExpireDateExtend(String expireDateExtend) {
		this.expireDateExtend = expireDateExtend;
	}

}
