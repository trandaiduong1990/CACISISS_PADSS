package org.transinfo.cacis.dto.collectionmanagement;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("serial")
public class CollectionDto implements Serializable {
	private String colectionId;
	private Long cardNo;
	private String status;
	private long orgColectionAmt;
	private String reclassification;
	private Date dateAssigned;
	private String amountAssigned;
	private int writeOffAmt;
	private Date writeOffDate;
	private int recoveryAmt;
	private Date lastRecoveryDate;
	private int amountToRecover;
	private Date recovedFullDate;
	private String installmentPayment;
	private int noOfInstallment;
	private Double interestRate;
	private long minPaymentAmt;
	private String note;
	private Date lastUpdatedDate;
	private String lastUpdatedBy;
	private String accountId;
	private String currentCollector;
	private String colectRef;
	private Date createdDate;
	
	public CollectionDto() {
		// TODO Auto-generated constructor stub
	}

	public String getColectionId() {
		return colectionId;
	}

	public void setColectionId(String colectionId) {
		this.colectionId = colectionId;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getOrgColectionAmt() {
		return orgColectionAmt;
	}

	public void setOrgColectionAmt(long orgColectionAmt) {
		this.orgColectionAmt = orgColectionAmt;
	}

	public String getReclassification() {
		return reclassification;
	}

	public void setReclassification(String reclassification) {
		this.reclassification = reclassification;
	}

	public Date getDateAssigned() {
		return dateAssigned;
	}

	public void setDateAssigned(Date dateAssigned) {
		this.dateAssigned = dateAssigned;
	}

	public String getAmountAssigned() {
		return amountAssigned;
	}

	public void setAmountAssigned(String amountAssigned) {
		this.amountAssigned = amountAssigned;
	}

	public int getWriteOffAmt() {
		return writeOffAmt;
	}

	public void setWriteOffAmt(int writeOffAmt) {
		this.writeOffAmt = writeOffAmt;
	}

	public Date getWriteOffDate() {
		return writeOffDate;
	}

	public void setWriteOffDate(Date writeOffDate) {
		this.writeOffDate = writeOffDate;
	}

	public int getRecoveryAmt() {
		return recoveryAmt;
	}

	public void setRecoveryAmt(int recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}

	public Date getLastRecoveryDate() {
		return lastRecoveryDate;
	}

	public void setLastRecoveryDate(Date lastRecoveryDate) {
		this.lastRecoveryDate = lastRecoveryDate;
	}

	public int getAmountToRecover() {
		return amountToRecover;
	}

	public void setAmountToRecover(int amountToRecover) {
		this.amountToRecover = amountToRecover;
	}

	public Date getRecovedFullDate() {
		return recovedFullDate;
	}

	public void setRecovedFullDate(Date recovedFullDate) {
		this.recovedFullDate = recovedFullDate;
	}

	public String getInstallmentPayment() {
		return installmentPayment;
	}

	public void setInstallmentPayment(String installmentPayment) {
		this.installmentPayment = installmentPayment;
	}

	public int getNoOfInstallment() {
		return noOfInstallment;
	}

	public void setNoOfInstallment(int noOfInstallment) {
		this.noOfInstallment = noOfInstallment;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public long getMinPaymentAmt() {
		return minPaymentAmt;
	}

	public void setMinPaymentAmt(long minPaymentAmt) {
		this.minPaymentAmt = minPaymentAmt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCurrentCollector() {
		return currentCollector;
	}

	public void setCurrentCollector(String currentCollector) {
		this.currentCollector = currentCollector;
	}

	public String getColectRef() {
		return colectRef;
	}

	public void setColectRef(String colectRef) {
		this.colectRef = colectRef;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
