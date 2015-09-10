package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Collection;

public class ProductTranxSearchDto implements Serializable {

	private String cardProductId;
	private String channelId;
	private String issuerId;
	private int totalCount;
	
	private Collection searchList;
	
	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Collection getSearchList() {
		return searchList;
	}

	public void setSearchList(Collection searchList) {
		this.searchList = searchList;
	}

}