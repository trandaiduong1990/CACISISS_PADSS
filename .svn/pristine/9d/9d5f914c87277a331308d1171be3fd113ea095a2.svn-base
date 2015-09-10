package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;

public class RiskCitiesDto implements Serializable{

	
        private String status;
        private Date updatedDate = new Date();
        private String userId;
        
        	
        public RiskCitiesDto(){}
        
        public static class Id implements Serializable{
            
            private long cardNo;
            private String countryCode;
            private String city;
            
            public Id(){}
            
            public long getCardNo() {
		return cardNo;
	    }
	    public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	    }
            
            public String getCountryCode() {
                return countryCode;
            }            
            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }
            
	    public String getCity() {
		return city;
	    }
	    public void setCity(String city) {
		this.city = city;
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
        
        public String getStatus() {
            return status;
        }                
        public void setStatus(String status) {
            this.status = status;
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
