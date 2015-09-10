package org.transinfo.cacis.formbean.settings;

import org.apache.struts.validator.ValidatorForm;

public class CardTypeSetupForm extends ValidatorForm {
	
	private String cardTypeId;
	private String issuerId;
	private String cardType;
	private String masterKey;
	private String pek;
	private String kek;
	private String userId;
	
	public CardTypeSetupForm(){}
	
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardTypeId() {
		return cardTypeId;
	}
	public void setCardTypeId(String cardTypeId) {
		this.cardTypeId = cardTypeId;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getKek() {
		return kek;
	}
	public void setKek(String kek) {
		this.kek = kek;
	}
	public String getMasterKey() {
		return masterKey;
	}
	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}
	public String getPek() {
		return pek;
	}
	public void setPek(String pek) {
		this.pek = pek;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
