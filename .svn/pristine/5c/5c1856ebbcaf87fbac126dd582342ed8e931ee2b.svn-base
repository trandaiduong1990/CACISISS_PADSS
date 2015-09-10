package org.transinfo.cacis.dto.disputemanagement;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.formbean.common.DateForm;

public class DocumentUploadSearchDto extends ValidatorActionForm {

	private String issuerId;

	private long cardNumber;

	private String claimNumber;

	DateForm claimDateForm = new DateForm();

	private Date claimDate;

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public long getCardNumber() {
		return new Long(cardNumber).longValue();
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {

		this.claimDate = claimDate;

		if (claimDate != null) {
			this.claimDateForm = new DateForm(claimDate);
		}
	}

	public DateForm getClaimDateForm() {
		return claimDateForm;
	}

	public void setClaimDateForm(DateForm claimDateForm) {
		this.claimDateForm = claimDateForm;
		claimDate = claimDateForm.toDate();
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
}
