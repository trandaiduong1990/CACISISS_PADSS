package org.transinfo.cacis.dto.settings;

import java.io.Serializable;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class CardProductRateSearchDto implements Serializable {

	  private String cardProductId;
	  

   public CardProductRateSearchDto() {
   }

   public CardProductRateSearchDto(String cardProductId) {
      setCardProductId(cardProductId);
     
   }

public String getCardProductId() {
	return cardProductId;
}

public void setCardProductId(String cardProductId) {
	this.cardProductId = cardProductId;
}




   

}
