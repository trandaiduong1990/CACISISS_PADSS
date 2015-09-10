package org.transinfo.cacis.dto.authorization;

import java.io.Serializable;
import java.util.Date;

public class BlockMerchantDto implements Serializable{
	
	    private String issuerId;
	    private long searchMerchantId;
	    private String blockStatus;
	    private String reason;
	    private String merchantName;
        private Date lastUpdatedDate = new Date();
        private String userId;
   
		
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
		public Date getLastUpdatedDate() {
			return lastUpdatedDate;
		}
		public void setLastUpdatedDate(Date lastUpdatedDate) {
			this.lastUpdatedDate = lastUpdatedDate;
		}
		
		public long getSearchMerchantId() {
			return searchMerchantId;
		}
		public void setSearchMerchantId(long searchMerchantId) {
			this.searchMerchantId = searchMerchantId;
		}
		public String getBlockStatus() {
			return blockStatus;
		}
		public void setBlockStatus(String blockStatus) {
			this.blockStatus = blockStatus;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getMerchantName() {
			return merchantName;
		}
		public void setMerchantName(String merchantName) {
			this.merchantName = merchantName;
		}
	
     
}
