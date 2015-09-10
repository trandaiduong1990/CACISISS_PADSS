package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.BranchDAO;

public class BranchSetupForm extends  ValidatorForm{
	
	
	 private static final long serialVersionUID = 1L;
	 private String  branchId;
	 private String  issuerId;
	 private String  branchName;
	 private String  status;
	 private String  address1;
	 private String  address2;
	 private String  city;
	 private String  state;
	 private String  country;
	 private String  postalCode;
	 private String  contactPhone;
	 private String  contactFax ;
	 private String  contactPerson;
	 private String  contactEmail;
	 private String  userId;
	
	  private Map countryList;
	  private Map statusList;
	  
	 public BranchSetupForm(){
		 getPreListData();
	 }
	 
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactFax() {
		return contactFax;
	}
	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
 
	public Map getCountryList() {
		return countryList;
	}

	public void setCountryList(Map countryList) {
		this.countryList = countryList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}
	public void getPreListData(){
		 
		try{
			BranchDAO objDAO= DAOFactory.getInstance().getBranchDAO();
			
			if(countryList==null)  {
				countryList=objDAO.countryListData();
			  }
			  if(statusList==null)  {
				 statusList= objDAO.statusListData("CODE_AI");
			  }
	    	 }catch(Exception e){
			  System.out.println("Error while getting  PreListData in BranchSetupForm:"+e.getMessage());
			}
			
		}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	 // Reset form fields.
	  public void reset(ActionMapping mapping, HttpServletRequest request)
		  {
		      branchId =null;
			  issuerId =null;
			  branchName =null;
			
			/*   address1;
			  address2;
			  city;
			 state;
			 country;
			  postalCode;
			  contactPhone;
			  contactFax ;
			  contactPerson;
			  contactEmail;
			  userId;*/
		    
		  }
}
