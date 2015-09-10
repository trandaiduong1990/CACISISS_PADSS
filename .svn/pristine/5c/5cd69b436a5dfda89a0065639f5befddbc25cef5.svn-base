package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CustomerTypeDAO;

public class CustomerTypeSetupForm  extends ValidatorForm{

	
	private static final long serialVersionUID = 1L;
	private String customerTypeId;
	private String issuerId;
	private String customerType;
	private String userId;
	
	 private String  status;
     private Map statusList;	
	   
	public CustomerTypeSetupForm(){
		  getPreListData();
	}
	
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerTypeId() {
		return customerTypeId;
	}
	public void setCustomerTypeId(String customerTypeId) {
		this.customerTypeId = customerTypeId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}
	public void getPreListData(){
		 
		try{
			CustomerTypeDAO objDAO= DAOFactory.getInstance().getCustomerTypeDAO();
			 		
	 		 if(statusList==null)  {
	 			statusList= objDAO.statusListData("CODE_AI");
	 		  }
	      }catch(Exception e){
		      System.out.println("Error while getting  preList in CustomerTypeSetup:"+e.getMessage());
		    }
		}
}
