package org.transinfo.cacis.formbean.log;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.log.UserActivitiesDAO;

public class UserActivitiesSearchForm extends ValidatorForm{

        private String startDate;
        private String endDate;	
        private String issuerId;
        private String userId;

        private Map userList;
        
        public UserActivitiesSearchForm() {}
 

        public String getStartDate() {
		return startDate;
	}
       
        public void setStartDate(String startDate) {
	      this.startDate = startDate;
	}
        
        public String getEndDate() {
		return endDate;
	}
       
        public void setEndDate(String endDate) {
	      this.endDate = endDate;
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


       public void getPreListData()
       {
        try{
	    
            UserActivitiesDAO objUserActivitiesDAO = DAOFactory.getInstance().getUserActivitiesDAO();
            if(userList==null)  {
                userList = objUserActivitiesDAO.getUserList(issuerId);
                System.out.println("userList size==>"+userList.size());
            }
  
        }catch(Exception e){
            System.out.println("Error while getting  PreListData:"+e);
        }
       }        

       /** Getter for property userList.
        * @return Value of property userList.
        */
       public Map getUserList() {
           return userList;
       }       
 
       /** Setter for property userList.
        * @param userList New value of property userList.
        */
       public void setUserList(Map userList) {
           this.userList = userList;
       }       
       
}




