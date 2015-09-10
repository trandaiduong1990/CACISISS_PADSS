package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CustomerTypeDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class CustomerTypeSearchForm extends ValidatorForm  {

   private String customerTypeId;
   private String issuerId;
   private Map customerTypeList;

   public CustomerTypeSearchForm() {
	 //  getPreListData();
   }

   public CustomerTypeSearchForm(String customerTypeId) {
	  setCustomerTypeId(customerTypeId);

   }

	public String getCustomerTypeId() {
		return customerTypeId;
	}
	
	public void setCustomerTypeId(String customerTypeId) {
		this.customerTypeId = customerTypeId;
	}
	
	public void setCustomerTypeList(Map customerTypeList) {
		this.customerTypeList = customerTypeList;
	}
   
	public void getPreListData(){ 
	 
	try{
		  CustomerTypeDAO objDAO = DAOFactory.getInstance().getCustomerTypeDAO();
 		
		  if(customerTypeList==null)  {
 			
 			customerTypeList = objDAO.customerTypeListData(issuerId);
 				
	     }else{
		   System.out.println("CustomerTypeLis existed");
	     }
      }catch(Exception e){
	      System.out.println("Error while getting PreList in CustomerTypeSearchForm:"+e.getMessage());
	    }
	}

	public Map getCustomerTypeList() {
		return customerTypeList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
}
