package org.transinfo.cacis.util;

public class EmbossFileContent {
	
	private String cardNo;
	private String cardHolderName;
	private String embossName;
	private String expiryDate;
	private String cvv2;
	private String track1data;
	private String track2data;
	private String imagePath;
	private String issuingDate;
	private String icvvData;

	private String address1="";
	private String address2="";
	private String city="";
	private String stateCountry="";
	
	private int rowNumber;
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getEmbossName() {
		return embossName;
	}
	public void setEmbossName(String embossName) {
		this.embossName = embossName;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getTrack1data() {
		return track1data;
	}
	public void setTrack1data(String track1data) {
		this.track1data = track1data;
	}
	public String getTrack2data() {
		return track2data;
	}
	public void setTrack2data(String track2data) {
		this.track2data = track2data;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getIssuingDate() {
		return issuingDate;
	}
	public void setIssuingDate(String issuingDate) {
		this.issuingDate = issuingDate;
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
	public String getStateCountry() {
		return stateCountry;
	}
	public void setStateCountry(String stateCountry) {
		this.stateCountry = stateCountry;
	}
	public String getIcvvData() {
		return icvvData;
	}
	public void setIcvvData(String icvvData) {
		this.icvvData = icvvData;
	}

}
