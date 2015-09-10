package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;

public class CityMasterDto implements Serializable{

    
	private String cityDesc;
        private Date updatedDate = new Date();
        private String userId;
        
        	
        public CityMasterDto(){}
        
        public static class Id implements Serializable{
            
            private String countryCode;
            private String cityCode;
            
            public Id(){}
            
            public String getCountryCode() {
		return countryCode;
	    }
	    public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	    }
            
	    public String getCityCode() {
		return cityCode;
	    }
	    public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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
        
        public String getCityDesc() {
		return cityDesc;
	}
	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
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
