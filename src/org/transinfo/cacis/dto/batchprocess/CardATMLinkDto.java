package org.transinfo.cacis.dto.batchprocess;

import java.io.Serializable;

import org.transinfo.cacis.dto.cardproduction.CardsDto;

@SuppressWarnings("serial")
public class CardATMLinkDto implements Serializable {
	
	private long cardNumber;
	private CardsDto card;
	private String savingAccount;
	private String currentAccount;
	private Character defaultAccount;

	private Float collectoralAmt;
	private String collectoralAccount;
	private String autoPayAccount;
	private Character autoPayAccounton;

	private Character autoPayDisable;
	
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public CardsDto getCard() {
		return card;
	}
	public void setCard(CardsDto card) {
		this.card = card;
	}
	public String getSavingAccount() {
		return savingAccount;
	}
	public void setSavingAccount(String savingAccount) {
		this.savingAccount = savingAccount;
	}
	public String getCurrentAccount() {
		return currentAccount;
	}
	public void setCurrentAccount(String currentAccount) {
		this.currentAccount = currentAccount;
	}
	public Character getDefaultAccount() {
		return defaultAccount;
	}
	public void setDefaultAccount(Character defaultAccount) {
		this.defaultAccount = defaultAccount;
	}
	public Float getCollectoralAmt() {
		return collectoralAmt;
	}
	public void setCollectoralAmt(Float collectoralAmt) {
		this.collectoralAmt = collectoralAmt;
	}
	public String getCollectoralAccount() {
		return collectoralAccount;
	}
	public void setCollectoralAccount(String collectoralAccount) {
		this.collectoralAccount = collectoralAccount;
	}
	public String getAutoPayAccount() {
		return autoPayAccount;
	}
	public void setAutoPayAccount(String autoPayAccount) {
		this.autoPayAccount = autoPayAccount;
	}
	public Character getAutoPayAccounton() {
		return autoPayAccounton;
	}
	public void setAutoPayAccounton(Character autoPayAccounton) {
		this.autoPayAccounton = autoPayAccounton;
	}
	public Character getAutoPayDisable() {
		return autoPayDisable;
	}
	public void setAutoPayDisable(Character autoPayDisable) {
		this.autoPayDisable = autoPayDisable;
	}

}
