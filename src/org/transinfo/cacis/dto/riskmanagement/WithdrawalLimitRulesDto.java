package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.formbean.common.DateForm;

/**
 * SupplemntaryFormDto Value Object. This class is value object representing
 * database table SUPPLEMENTARY_FORMS This class is intented to be used together
 * with associated Dao object.
 */

public class WithdrawalLimitRulesDto implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Persistent Instance variables. This data is directly mapped to the
	 * columns of database table.
	 */

	private long ruleId;
	private String cardProductId;
	private String custTypeId;
	private String mcc;
	private long amountPerTranx;
	private int dailyLimitCount;
	private long dailyLimitAmount;
	private int monthlyLimitCount;
	private long monthlyLimitAmount;
	private String currCode;
	private String tranxCode;
	private DateForm lastUpdatedDateForm;
	private Date lastUpdatedDate = new Date();
	private String lastUpdatedBy;
	private String issuerId;

	public WithdrawalLimitRulesDto() {
	}

	public WithdrawalLimitRulesDto(int ruleIdIn) {
		this.ruleId = ruleIdIn;
	}

	public long getAmountPerTranx() {
		return new Long(amountPerTranx).longValue();
	}

	public void setAmountPerTranx(long amountPerTranx) {
		this.amountPerTranx = amountPerTranx;
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getCustTypeId() {
		return custTypeId;
	}

	public void setCustTypeId(String custTypeId) {
		this.custTypeId = custTypeId;
	}

	public long getDailyLimitAmount() {
		return new Long(dailyLimitAmount).longValue();
	}

	public void setDailyLimitAmount(long dailyLimitAmount) {
		this.dailyLimitAmount = dailyLimitAmount;
	}

	public int getDailyLimitCount() {
		return dailyLimitCount;
	}

	public void setDailyLimitCount(int dailyLimitCount) {
		this.dailyLimitCount = dailyLimitCount;
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

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public long getMonthlyLimitAmount() {
		return new Long(monthlyLimitAmount).longValue();
	}

	public void setMonthlyLimitAmount(long monthlyLimitAmount) {
		this.monthlyLimitAmount = monthlyLimitAmount;
	}

	public int getMonthlyLimitCount() {
		return monthlyLimitCount;
	}

	public void setMonthlyLimitCount(int monthlyLimitCount) {
		this.monthlyLimitCount = monthlyLimitCount;
	}

	public long getRuleId() {
		return new Long(ruleId).longValue();
	}

	public void setRuleId(long ruleId) {
		this.ruleId = ruleId;
	}

	public String getTranxCode() {
		return tranxCode;
	}

	public void setTranxCode(String tranxCode) {
		this.tranxCode = tranxCode;
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
}
