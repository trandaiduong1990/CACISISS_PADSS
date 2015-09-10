package org.transinfo.cacis.formbean.settings;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.IssuerDAO;

public class IssuerSetupForm  extends ValidatorForm {
		
	
	  private static final long serialVersionUID = 1L;
		private String  issuerId;
		 private String  issuerName;
		 private String  status;
		 private String  issuerLogo;
		 private String  address1;
		 private String  address2;
		 private String  city;
		 private String  state;
		 private String  country;
		 private String  postalCode;
		 private String  contactName;
		 private String  contactPhone;
		 private String  contactMobile ;
		 private String  contactFax ;
		 private String  contactEmail;
		 private String  userId;
		
		 private Map countryList;
		 private Map statusList;	
		 
		 public IssuerSetupForm(){
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
		public String getContactMobile() {
			return contactMobile;
		}
		public void setContactMobile(String contactMobile) {
			this.contactMobile = contactMobile;
		}
		public String getContactName() {
			return contactName;
		}
		public void setContactName(String contactName) {
			this.contactName = contactName;
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
		public String getIssuerLogo() {
			return issuerLogo;
		}
		public void setIssuerLogo(String issuerLogo) {
			this.issuerLogo = issuerLogo;
		}
		public String getIssuerName() {
			return issuerName;
		}
		public void setIssuerName(String issuerName) {
			this.issuerName = issuerName;
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
		public String getContactPhone() {
			return contactPhone;
		}
		public void setContactPhone(String contactPhone) {
			this.contactPhone = contactPhone;
		}
			
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Map getCountryList() {
		return countryList;
	}
	public void setCountryList(Map countryList) {
		this.countryList = countryList;
	}
	public Map getStatusList() {
		return statusList;
	}
	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}
	
	public void getPreListData(){
		 
		try{
		
		IssuerDAO objDAO= DAOFactory.getInstance().getIssuerDAO();
		
		if(countryList==null)  {
			countryList= objDAO.countryListData();
		  }
		 if(statusList==null)  {
			 statusList= objDAO.statusListData("CODE_AI");
		  }
	 }catch(Exception e)
	   {
		  System.out.println("Error while getting  PreListData in IssuerSetUpForm:"+e.getMessage());
		}
		
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}



