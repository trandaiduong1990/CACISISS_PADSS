package org.transinfo.cacis.formbean.disputemanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings( { "serial", "unchecked" })
public class ChargeBackResendSetupForm extends ValidatorForm {

	private String cardNo;
	private String terminalId;
	private String authCode;
	private String refNo;
	private String mcc;
	private String arn;
	private String amtFrom;
	private String amtTo;
	private String startDate;
	private String endDate;

	private String pageNo;
	private String disType;

	private String disCaseNo;
	private String disAmt;
	private String disCurr;
	private String cardHolder;
	private String disReason;
	private String status;
	private String user;
	private String attachingDoc;
	private String memMsgText;
	private String remarks;
	private String disGroup;
	private String disCreatedDate;
	private String disArn;

	private String statusSymbol;

	private Map disGroupList;
	private Map disReasonList;
	private Map currList;
	
	private String edisGroup;
	private String edisReason;
	private String edisAmt;
	private String edisCurr;
	private String ememMsgText;
	private String edisArn;

	private String addButton = "Y";

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getDisAmt() {
		return disAmt;
	}

	public void setDisAmt(String disAmt) {
		this.disAmt = disAmt;
	}

	public String getDisCurr() {
		return disCurr;
	}

	public void setDisCurr(String disCurr) {
		this.disCurr = disCurr;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getDisReason() {
		return disReason;
	}

	public void setDisReason(String disReason) {
		this.disReason = disReason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAttachingDoc() {
		return attachingDoc;
	}

	public void setAttachingDoc(String attachingDoc) {
		this.attachingDoc = attachingDoc;
	}

	public String getMemMsgText() {
		return memMsgText;
	}

	public void setMemMsgText(String memMsgText) {
		this.memMsgText = memMsgText;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDisCaseNo() {
		return disCaseNo;
	}

	public void setDisCaseNo(String disCaseNo) {
		this.disCaseNo = disCaseNo;
	}

	public String getStatusSymbol() {
		return statusSymbol;
	}

	public void setStatusSymbol(String statusSymbol) {
		this.statusSymbol = statusSymbol;
	}

	public Map getDisGroupList() {
		return disGroupList;
	}

	public void setDisGroupList(Map disGroupList) {
		this.disGroupList = disGroupList;
	}

	public Map getDisReasonList() {
		return disReasonList;
	}

	public void setDisReasonList(Map disReasonList) {
		this.disReasonList = disReasonList;
	}

	public Map getCurrList() {
		return currList;
	}

	public void setCurrList(Map currList) {
		this.currList = currList;
	}

	public String getDisType() {
		return disType;
	}

	public void setDisType(String disType) {
		this.disType = disType;
	}

	public String getDisGroup() {
		return disGroup;
	}

	public void setDisGroup(String disGroup) {
		this.disGroup = disGroup;
	}

	public String getDisCreatedDate() {
		return disCreatedDate;
	}

	public void setDisCreatedDate(String disCreatedDate) {
		this.disCreatedDate = disCreatedDate;
	}

	public String getDisArn() {
		return disArn;
	}

	public void setDisArn(String disArn) {
		this.disArn = disArn;
	}

	public String getEdisAmt() {
		return edisAmt;
	}

	public void setEdisAmt(String edisAmt) {
		this.edisAmt = edisAmt;
	}

	public String getEdisCurr() {
		return edisCurr;
	}

	public void setEdisCurr(String edisCurr) {
		this.edisCurr = edisCurr;
	}

	public String getEdisReason() {
		return edisReason;
	}

	public void setEdisReason(String edisReason) {
		this.edisReason = edisReason;
	}

	public String getEmemMsgText() {
		return ememMsgText;
	}

	public void setEmemMsgText(String ememMsgText) {
		this.ememMsgText = ememMsgText;
	}

	public String getEdisGroup() {
		return edisGroup;
	}

	public void setEdisGroup(String edisGroup) {
		this.edisGroup = edisGroup;
	}

	public String getEdisArn() {
		return edisArn;
	}

	public void setEdisArn(String edisArn) {
		this.edisArn = edisArn;
	}

	public String getAddButton() {
		return addButton;
	}

	public void setAddButton(String addButton) {
		this.addButton = addButton;
	}

}
