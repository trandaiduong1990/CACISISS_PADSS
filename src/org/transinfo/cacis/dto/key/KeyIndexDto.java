package org.transinfo.cacis.dto.key;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class KeyIndexDto implements Serializable {

	private int keyIndex;
	private String status;
	private Date updatedDate = new Date();
	private String userId;
	
	private String cardProductName;

	public static class Id implements Serializable {

		private String issuerId;
		private String cardProductId;
		private String keyType;
		private String transactionChannel;

		public Id() {
		}

		public Id(String issuerId, String cardProductId, String keyType, String transactionChannel) {
			this.issuerId = issuerId;
			this.cardProductId = cardProductId;
			this.keyType = keyType;
			this.transactionChannel = transactionChannel;
		}

		public String getIssuerId() {
			return issuerId;
		}

		public void setIssuerId(String issuerId) {
			this.issuerId = issuerId;
		}

		public String getCardProductId() {
			return cardProductId;
		}

		public void setCardProductId(String cardProductId) {
			this.cardProductId = cardProductId;
		}

		public String getKeyType() {
			return keyType;
		}

		public void setKeyType(String keyType) {
			this.keyType = keyType;
		}

		public String getTransactionChannel() {
			return transactionChannel;
		}

		public void setTransactionChannel(String transactionChannel) {
			this.transactionChannel = transactionChannel;
		}

	}

	public Id id = new Id();

	/**
	 * @return Returns the id.
	 */
	public Id getId() {

		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Id id) {
		this.id = id;
	}

	public int getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return the cardProductName
	 */
	public String getCardProductName() {
		return cardProductName;
	}

	/**
	 * @param cardProductName the cardProductName to set
	 */
	public void setCardProductName(String cardProductName) {
		this.cardProductName = cardProductName;
	}

}
