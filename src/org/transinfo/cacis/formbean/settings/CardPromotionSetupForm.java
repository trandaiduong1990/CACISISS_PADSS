package org.transinfo.cacis.formbean.settings;

import org.apache.struts.validator.ValidatorForm;

public class CardPromotionSetupForm  extends ValidatorForm{

	private String promotionId;
	private String promotionType;
	private String userId;
	
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
}
