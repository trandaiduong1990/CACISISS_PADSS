package org.transinfo.cacis.formbean.disputemanagement;

import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings( { "serial", "unchecked" })
public class DisputeNotReconsiledTranxSearchForm extends ValidatorForm {

	private String cardNo;
	private String authCode;
	private String arn;
	private String amtFrom;
	private String amtTo;

	private Collection searchList;
	private String totalCount;

	private String pageNo;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getArn() {
		return arn;
	}

	public void setArn(String arn) {
		this.arn = arn;
	}

	public String getAmtFrom() {
		return amtFrom;
	}

	public void setAmtFrom(String amtFrom) {
		this.amtFrom = amtFrom;
	}

	public String getAmtTo() {
		return amtTo;
	}

	public void setAmtTo(String amtTo) {
		this.amtTo = amtTo;
	}

	public Collection getSearchList() {
		return searchList;
	}

	public void setSearchList(Collection searchList) {
		this.searchList = searchList;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
