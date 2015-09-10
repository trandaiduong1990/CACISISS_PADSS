package org.transinfo.cacis.dto.transaction;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TransactionCreditGLDto implements Serializable {
	
	private String tranxCreditGLId;
	private int transactionId;
	private String issuerId;
	private Date dateTime;
	private String cardNo;
	private String trnxType;
	private String glType;
	private String amount;
	private String balance;
	private String refNo;
	private String billed;
	
	private String trnxTypeDes;

	public String getTranxCreditGLId() {
		return tranxCreditGLId;
	}
	public void setTranxCreditGLId(String tranxCreditGLId) {
		this.tranxCreditGLId = tranxCreditGLId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getTrnxType() {
		return trnxType;
	}
	public void setTrnxType(String trnxType) {
		this.trnxType = trnxType;
	}
	public String getGlType() {
		return glType;
	}
	public void setGlType(String glType) {
		this.glType = glType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getBilled() {
		return billed;
	}
	public void setBilled(String billed) {
		this.billed = billed;
	}
	public String getTrnxTypeDes() {
		return trnxTypeDes;
	}
	public void setTrnxTypeDes(String trnxTypeDes) {
		this.trnxTypeDes = trnxTypeDes;
	}

}
