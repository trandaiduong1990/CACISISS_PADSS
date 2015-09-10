package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.transinfo.cacis.dto.cardproduction.CustomerHistoryDto.Id;

@SuppressWarnings( { "unchecked", "serial" })
public class CardProductTranxDto implements Serializable {

	private String userId;
	private Date updatedDate = new Date();
	private TranxTypeDto tranxId;
	public Id id = new Id();
	
	public CardProductTranxDto() {
		// TODO Auto-generated constructor stub
	}
	
	public static class Id implements Serializable {

		private String productId;
		private String channel;

		public Id() {
		}
		
		public Id(String productId, String channel) {
			this.productId = productId;
			this.channel = channel;
		}


		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public TranxTypeDto getTranxId() {
		return tranxId;
	}

	public void setTranxId(TranxTypeDto tranxId) {
		this.tranxId = tranxId;
	}
	
}
