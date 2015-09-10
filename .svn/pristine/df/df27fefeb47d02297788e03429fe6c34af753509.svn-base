package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;

public class RiskTranxPeriodDto implements Serializable{
	
    
        
        private int tranxNo;       
        private Date updatedDate = new Date();
        private String userId;
       
                	
        public RiskTranxPeriodDto(){}
        
       public static class Id implements Serializable{
           private String issuerId;
           private int period;
           
           public String getIssuerId() {
               return issuerId;
           }           
           public void setIssuerId(String issuerId) {
               this.issuerId = issuerId;
           }
           
           public int getPeriod() {
		return period;
           }        
           public void setPeriod(int period) {
	       this.period = period;
           }
        
                                 
       }
                
	public Id id = new Id();

        /**
	 * @return Returns the id.
	 */
	public Id getId() {            
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Id id) {
		this.id = id;
	}
	
        public int getTranxNo() {
		return tranxNo;
	}        
        public void setTranxNo(int tranxNo) {
               this.tranxNo = tranxNo;
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
        
        
}
