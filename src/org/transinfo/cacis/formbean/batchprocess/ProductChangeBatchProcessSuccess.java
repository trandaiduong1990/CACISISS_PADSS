package org.transinfo.cacis.formbean.batchprocess;

public class ProductChangeBatchProcessSuccess {

	private String oldCardNo;
	private String customerName;
	private String idNumber;
	private String newCardNumber;
	private String cardExpDate;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCardExpDate() {
		return cardExpDate;
	}

	public void setCardExpDate(String cardExpDate) {
		this.cardExpDate = cardExpDate;
	}

	public String getOldCardNo() {
		return oldCardNo;
	}

	public void setOldCardNo(String oldCardNo) {
		this.oldCardNo = oldCardNo;
	}

	public String getNewCardNumber() {
		return newCardNumber;
	}

	public void setNewCardNumber(String newCardNumber) {
		this.newCardNumber = newCardNumber;
	}

}
