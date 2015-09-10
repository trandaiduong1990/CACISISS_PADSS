package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;

public class CardPromotionDto   implements Serializable  {
	
	
	private static final long serialVersionUID = 1L;
	private String       promotionId;
	private String       promotionType;
	private String       status;
	private Date         updatedDate =new Date();
	private String       userId;
	
	public CardPromotionDto(){}

	
	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

}
