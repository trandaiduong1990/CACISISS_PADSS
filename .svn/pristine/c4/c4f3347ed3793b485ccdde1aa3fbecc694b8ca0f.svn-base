package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SalaryProfileDto implements Serializable {

	private long minSalary;
	private long maxSalary;
	private Date updatedDate = new Date();
	private String userId;

	public static class Id implements Serializable {

		private CardProductDto cardProduct;
		private long creditLimit;
		private long cashAdvanceLimit;

		public Id() {
		}

		public Id(CardProductDto cardProduct, long creditLimit,
				long cashAdvanceLimit) {
			this.cardProduct = cardProduct;
			this.creditLimit = creditLimit;
			this.cashAdvanceLimit = cashAdvanceLimit;
		}

		public CardProductDto getCardProduct() {
			return cardProduct;
		}

		public void setCardProduct(CardProductDto cardProduct) {
			this.cardProduct = cardProduct;
		}

		public long getCreditLimit() {
			return creditLimit;
		}

		public void setCreditLimit(long creditLimit) {
			this.creditLimit = creditLimit;
		}

		public long getCashAdvanceLimit() {
			return cashAdvanceLimit;
		}

		public void setCashAdvanceLimit(long cashAdvanceLimit) {
			this.cashAdvanceLimit = cashAdvanceLimit;
		}

	}

	public Id id = new Id();

	public long getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(long minSalary) {
		this.minSalary = minSalary;
	}

	public long getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(long maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

}
