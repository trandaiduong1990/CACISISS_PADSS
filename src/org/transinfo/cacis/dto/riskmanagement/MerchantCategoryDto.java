package org.transinfo.cacis.dto.riskmanagement;

import org.apache.struts.validator.ValidatorActionForm;

public class MerchantCategoryDto extends ValidatorActionForm{

	private String mcc;
	private String description;
	private String lastUpdatedBy;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
}
