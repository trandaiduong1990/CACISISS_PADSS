package org.transinfo.cacis.formbean.common;

import java.io.Serializable;


public class AddressForm implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String addressId;
	private String addressType;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country="LA";
	private String postalCode;
	private String phone;
	private String fax;
	private String township;
	private String homeTp;
	
	/*private String address1="address1";
	private String address2 ="address2";
	private String city ="hyd";
	private String state ="AP";
	private String country="india" ;
	private String postalCode ="43543" ;
	private String phone ="3443";
	private String fax ="43252";*/

	public AddressForm(){}

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

	public String getAddressId() {
		
		return addressId ; 
		
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public String getHomeTp() {
		return homeTp;
	}

	public void setHomeTp(String homeTp) {
		this.homeTp = homeTp;
	}

	


}
