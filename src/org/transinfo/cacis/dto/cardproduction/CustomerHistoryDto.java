package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;

public class CustomerHistoryDto {

	private String applicationId;
	private String applicationStatus;
	private String cardStatus;
	private String idNumber;
	private String creditLimit;
	private String cardType;
	private String cardProductType;
	private Date   actionDate;

	public CustomerHistoryDto(){}

	  public static class Id implements Serializable {

		private String cardNumber;
		private String cardProduct;

		public Id() {}

		public Id(String cardNumber,String cardProduct)
		{


			this.cardNumber = cardNumber;
			this.cardProduct = cardProduct;
		}

			public String getCardNumber() {
				return cardNumber;
			}
			public void setCardNumber(String cardNumber) {
				this.cardNumber = cardNumber;
			}

			public String getCardProduct() {
				return cardProduct;
			}
			public void setCardProduct(String cardProduct) {
				this.cardProduct = cardProduct;
			}



		public int hashCode() {
			return cardNumber.hashCode() + cardProduct.hashCode();
		}
		public boolean equals(Object other) {
			if (other instanceof Id) {
				Id that = (Id) other;
				return that.cardNumber.equals(this.cardNumber) &&
					that.cardProduct.equals(this.cardProduct);
			}
			else {
				return false;
			}
		}
	}

	private Id id = new Id();


	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getCardProductType() {
		return cardProductType;
	}
	public void setCardProductType(String cardProductType) {
		this.cardProductType = cardProductType;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return Returns the id.
	 */
	public Id getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Id id) {
		this.id = id;
	}

}
