package org.transinfo.cacis.dto.common;

import java.io.Serializable;

import org.transinfo.cacis.dto.applicationforms.CardGradeFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;

@SuppressWarnings("serial")
public class AddressDto implements Serializable {

	private long addressId;
	private String addressType;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private long postalCode;
	private String phone;
	private String fax;
	private String township;
	private String homeTp;

	// this for ApplicationAddress to ApplicationForm many-to-one
	private ApplicationFormDto appFormDto;
	// this for ApplicationAddress to CardGradeForm many-to-one
	private CardGradeFormDto cardGradeFormDto;

	public AddressDto() {
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

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(long postalCode) {
		this.postalCode = postalCode;
	}

	public ApplicationFormDto getAppFormDto() {
		return appFormDto;
	}

	public void setAppFormDto(ApplicationFormDto appFormDto) {
		this.appFormDto = appFormDto;
	}

	public CardGradeFormDto getCardGradeFormDto() {
		return cardGradeFormDto;
	}

	public void setCardGradeFormDto(CardGradeFormDto cardGradeFormDto) {
		this.cardGradeFormDto = cardGradeFormDto;
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
