package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;

import org.transinfo.cacis.formbean.common.DateForm;

public class WriteOffMasterDto implements Cloneable, Serializable {

	private int dpd0;

	private int dpd30;

	private int dpd60;

	private int dpd90;

	private int dpd120;

	private DateForm lastUpdatedDateForm;

	private Date lastUpdatedDate = new Date();

	private String lastUpdatedBy;

	private String issuerId;

	public WriteOffMasterDto() {
	}

	public int getDpd0() {
		return dpd0;
	}

	public void setDpd0(int dpd0) {
		this.dpd0 = dpd0;
	}

	public int getDpd120() {
		return dpd120;
	}

	public void setDpd120(int dpd120) {
		this.dpd120 = dpd120;
	}

	public int getDpd30() {
		return dpd30;
	}

	public void setDpd30(int dpd30) {
		this.dpd30 = dpd30;
	}

	public int getDpd60() {
		return dpd60;
	}

	public void setDpd60(int dpd60) {
		this.dpd60 = dpd60;
	}

	public int getDpd90() {
		return dpd90;
	}

	public void setDpd90(int dpd90) {
		this.dpd90 = dpd90;
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
