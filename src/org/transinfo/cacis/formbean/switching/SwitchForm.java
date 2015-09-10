package org.transinfo.cacis.formbean.switching;

import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.switching.SwitchDAO;


public class SwitchForm extends ValidatorForm{

    
        private String swName;	
        private String swType;
	private String swIpAddress;
        private String swPort; 
        private String swTimeOut1;
        private String swTimeOut2;
        private Date updatedDate = new Date();
        private String userId;

    private Map swTypeList;
        
    public SwitchForm()
    {
        //getPreListData();
    }

    /**
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

         public String getSwName() {
		return swName;
	}
        		
        public void setSwName(String swName) {
		this.swName = swName;
	}
        
        public String getSwType() {
		return swType;
	}
        
        public void setSwType(String swType) {
		this.swType = swType;
	}
                        
        public String getSwIpAddress() {
		return swIpAddress;
	}
               
        public void setSwIpAddress(String swIpAddress) {
		this.swIpAddress = swIpAddress;
	}
                       
        public String getSwPort() {
		return swPort;
	}
            
        public void setSwPort(String swPort) {
		this.swPort = swPort;
	}
        
        public String getSwTimeOut1() {
		return swTimeOut1;
	}
            
        public void setSwTimeOut1(String swTimeOut1) {
		this.swTimeOut1 = swTimeOut1;
	}
        
        public String getSwTimeOut2() {
		return swTimeOut2;
	}
            
        public void setSwTimeOut2(String swTimeOut2) {
		this.swTimeOut2 = swTimeOut2;
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

        public Map getSwTypeList() {
		return swTypeList;
	}

        public void setSwTypeList(Map swTypeList) {
		this.swTypeList = swTypeList;
	}
        
        

        public void getPreListData()
        {
	        try{
	            SwitchDAO objSwitchDAO = DAOFactory.getInstance().getSwitchDAO();
	            if(swTypeList==null)  {
	                swTypeList = objSwitchDAO.switchTypeList();
                        System.out.println("swTypeList==>"+swTypeList.size());
	            }

	        }catch(Exception e){
	            System.out.println("Error while getting  PreListData:"+e);
	        }
        }
}




