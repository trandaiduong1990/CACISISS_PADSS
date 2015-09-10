
package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;


public class CardProductPromotionDto implements Serializable{
	
	private String     promotionId;
	private String     promotionType;
	private Date       updatedDate;
	private String     lastUpdatedBy;
	private String     productId;
	private Date       startDate;
	private Date       endDate;

	public CardProductPromotionDto(){}

	public CardProductPromotionDto(Date startDate,Date endDate,String lastUpdatedBy)
	{

		this.startDate = startDate;
		this.endDate = endDate;
		this.lastUpdatedBy ="ASPSUPERADMIN";


	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getUpdatedDate() {
		return new Date();
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}


}
