package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DisputeManualReconDto implements Serializable {

	private long tranxlogId;
	private String remarks;
	private String updatedBy;
	private Date updatedDate;

	public long getTranxlogId() {
		return tranxlogId;
	}

	public void setTranxlogId(long tranxlogId) {
		this.tranxlogId = tranxlogId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
