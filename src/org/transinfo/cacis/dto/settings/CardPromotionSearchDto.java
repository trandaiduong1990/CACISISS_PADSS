package org.transinfo.cacis.dto.settings;

import java.io.Serializable;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class CardPromotionSearchDto implements Serializable {

	private String promotionId;
   

   public CardPromotionSearchDto() {
	   System.out.println("CardPromotionSearchDto ");
   }

   public CardPromotionSearchDto(String promotionId) {
	   System.out.println("Search CardPromotionSearchDto"+promotionId);
	   setPromotionId(promotionId);

   }

public String getPromotionId() {
	return promotionId;
}

public void setPromotionId(String promotionId) {
	this.promotionId = promotionId;
}


}
