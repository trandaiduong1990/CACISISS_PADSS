package org.transinfo.cacis.formbean.cardproduction;

public class CardDeliverSuccess {
	
	private long cardNumber;
	private String custName;
	private String cardExpDate;
	private String cardProduct;

	private String maskedCardno;
	
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCardExpDate() {
		return cardExpDate;
	}
	public void setCardExpDate(String cardExpDate) {
		this.cardExpDate = cardExpDate;
	}
	public String getCardProduct() {
		return cardProduct;
	}
	public void setCardProduct(String cardProduct) {
		this.cardProduct = cardProduct;
	}
	public String getMaskedCardno() {
		return maskedCardno;
	}
	public void setMaskedCardno(String maskedCardno) {
		this.maskedCardno = maskedCardno;
	}

}
