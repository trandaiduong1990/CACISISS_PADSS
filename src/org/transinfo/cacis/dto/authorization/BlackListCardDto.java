package org.transinfo.cacis.dto.authorization;

import java.io.Serializable;
import java.util.Date;

public class BlackListCardDto implements Serializable{
	
	    private String issuerId;
	    private long cardNumber;
	    private long cardStatusId;
        private Date updatedDate = new Date();
        private String userId;
        
        private String referenceId;
		
        public long getCardNumber() {
			return cardNumber;
		}
		public void setCardNumber(long cardNumber) {
			this.cardNumber = cardNumber;
		}
		public long getCardStatusId() {
			return cardStatusId;
		}
		public void setCardStatusId(long cardStatusId) {
			this.cardStatusId = cardStatusId;
		}
		public String getIssuerId() {
			return issuerId;
		}
		public void setIssuerId(String issuerId) {
			this.issuerId = issuerId;
		}
	
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public Date getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}
		public String getReferenceId() {
			return referenceId;
		}
		public void setReferenceId(String referenceId) {
			this.referenceId = referenceId;
		}
	
     
}
