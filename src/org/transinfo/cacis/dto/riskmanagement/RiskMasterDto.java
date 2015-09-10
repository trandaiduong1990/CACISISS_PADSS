package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;

public class RiskMasterDto implements Serializable{
        
        private String riskId;
        private String description;        
        private Date updatedDate = new Date();
        private String userId;

       

        public RiskMasterDto(){}

     
               
        public String getRiskId() {
            return riskId;
        }                
        public void setRiskId(String riskId) {
            this.riskId = riskId;
        }
       
        
        public String getDescription() {
            return description;
        }       
        public void setDescription(String description) {
            this.description = description;
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
