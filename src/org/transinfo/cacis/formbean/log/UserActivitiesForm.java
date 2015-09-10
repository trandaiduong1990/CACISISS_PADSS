package org.transinfo.cacis.formbean.log;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class UserActivitiesForm extends ValidatorForm{

        private String userActivityId;
        private Date dateTime;
	private String userId;
	private String stationIp;
        private String issuerId;
        private String activity;
        
               
        public UserActivitiesForm() {}
            
                        
       public String getUserActivityId() {
		return userActivityId;
	}
        
        public void setUserActivityId(String userActivityId) {
	       this.userActivityId = userActivityId;
	}
	
        public Date getDateTime() {
		return dateTime;
	}
        
        public void setDateTime(Date dateTime) {
               this.dateTime = dateTime;
	}
        
        public String getUserId() {
		return userId;
	}
        
        public void setUserId(String userId) {
	      this.userId = userId;
	}
        
        public String getStationIp() {
		return stationIp;
	}
        
        public void setStationIp(String stationIp) {
	      this.stationIp = stationIp;
	}
        
        public String getIssuerId() {
		return issuerId;
	}
       
        public void setIssuerId(String issuerId) {
	      this.issuerId = issuerId;
	}
        
        public String getActivity() {
		return activity;
	}                
        public void setActivity(String activity) {
	      this.activity = activity;
	}
       
}




