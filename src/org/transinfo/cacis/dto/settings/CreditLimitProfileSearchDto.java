package org.transinfo.cacis.dto.settings;

import java.util.Collection;

public class CreditLimitProfileSearchDto {
	
	private String scoreId;
	private String cardProductId;
	private Collection searchList;
	private int totalCount;
	private String pageNo;
	
	public String getScoreId() {
		return scoreId;
	}
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	public String getCardProductId() {
		return cardProductId;
	}
	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}
	public Collection getSearchList() {
		return searchList;
	}
	public void setSearchList(Collection searchList) {
		this.searchList = searchList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	
}
