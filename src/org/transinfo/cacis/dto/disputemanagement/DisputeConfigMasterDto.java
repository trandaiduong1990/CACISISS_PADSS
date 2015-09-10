package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.formbean.common.DateForm;

public class DisputeConfigMasterDto implements Cloneable, Serializable {

	private double minDisputeAmt;
	
	private double financeCharge;

	private char issueTempDispatchChargeAmt;

	private int transactionValidationPeriod;

	private int copyReqProcessingDays;

	private int chargebackProcessingDays;

	private DateForm lastUpdatedDateForm;

	private Date lastUpdatedDate = new Date();

	private String lastUpdatedBy;

	private String issuerId;

	public int getChargebackProcessingDays() {
		return chargebackProcessingDays;
	}

	public void setChargebackProcessingDays(int chargebackProcessingDays) {
		this.chargebackProcessingDays = chargebackProcessingDays;
	}

	public int getCopyReqProcessingDays() {
		return copyReqProcessingDays;
	}

	public void setCopyReqProcessingDays(int copyReqProcessingDays) {
		this.copyReqProcessingDays = copyReqProcessingDays;
	}

	public double getFinanceCharge() {
		return financeCharge;
	}

	public void setFinanceCharge(double financeCharge) {
		this.financeCharge = financeCharge;
	}

	public char getIssueTempDispatchChargeAmt() {
		return issueTempDispatchChargeAmt;
	}

	public void setIssueTempDispatchChargeAmt(char issueTempDispatchChargeAmt) {
		this.issueTempDispatchChargeAmt = issueTempDispatchChargeAmt;
	}

	public double getMinDisputeAmt() {
		return minDisputeAmt;
	}

	public void setMinDisputeAmt(double minDisputeAmt) {
		this.minDisputeAmt = minDisputeAmt;
	}

	public int getTransactionValidationPeriod() {
		return transactionValidationPeriod;
	}

	public void setTransactionValidationPeriod(int transactionValidationPeriod) {
		this.transactionValidationPeriod = transactionValidationPeriod;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDateIn) {
		lastUpdatedDate = lastUpdatedDateIn;
		if (lastUpdatedDate != null) {
			lastUpdatedDateForm = new DateForm(lastUpdatedDate);
		}
	}

	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}

	public void setLastUpdatedDateForm(DateForm lastUpdatedDateForm) {
		this.lastUpdatedDateForm = lastUpdatedDateForm;
	}
}
