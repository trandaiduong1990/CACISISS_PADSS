//Package structure
package org.transinfo.cacis.report.databean;

//Java specific imports
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings( { "serial", "unchecked" })
public class ReportViewBean extends
		org.transinfo.cacis.report.databean.CommonDataBean implements
		Serializable {
	private String CardNo = "";
	private String FromDate = "";
	private String ToDate = "";
	private String RefNo = "";
	private String ResponseCode = "";
	private String ApprovalCode = "";
	private String TranxCode = "";

	private String strCycleNo = "";
	private String strDayOfMonth = "";
	private String strMessage = "";
	private String strReportCode = "";
	private String authPerson = "";
	private String appNos = "";
	private String newNos = "";
	
	private String cardProduct = "";
	private String customerName = "";
	private String gender = "";
	private String dob = "";
	private String tranxStatus = "";
	private String tranxType = "";
	private String expireDate = "";
	private String cardProductType = "";

	private String oldCardNo = "";
	
	private String cardType;
	private String recon;;
	
	private ArrayList TranxCodeList = null;

	public ArrayList getTranxCodeList() {
		return TranxCodeList;
	}

	public void setTranxCodeList(ArrayList TranxCodeList) {
		this.TranxCodeList = TranxCodeList;
	}

	/**
	 * Getter for property CardNo.
	 * 
	 * @return Value of property CardNo.
	 */
	public java.lang.String getCardNo() {
		return CardNo;
	}

	/**
	 * Setter for property CardNo.
	 * 
	 * @param CardNo
	 *            New value of property CardNo.
	 */
	public void setCardNo(java.lang.String CardNo) {
		this.CardNo = CardNo;
	}

	/**
	 * Getter for property FromDate.
	 * 
	 * @return Value of property FromDate.
	 */
	public java.lang.String getFromDate() {
		return FromDate;
	}

	/**
	 * Setter for property FromDate.
	 * 
	 * @param FromDate
	 *            New value of property FromDate.
	 */
	public void setFromDate(java.lang.String FromDate) {
		this.FromDate = FromDate;
	}

	/**
	 * Getter for property ToDate.
	 * 
	 * @return Value of property ToDate.
	 */
	public java.lang.String getToDate() {
		return ToDate;
	}

	/**
	 * Setter for property ToDate.
	 * 
	 * @param ToDate
	 *            New value of property ToDate.
	 */
	public void setToDate(java.lang.String ToDate) {
		this.ToDate = ToDate;
	}

	/**
	 * Getter for property RefNo.
	 * 
	 * @return Value of property RefNo.
	 */
	public java.lang.String getRefNo() {
		return RefNo;
	}

	/**
	 * Setter for property RefNo.
	 * 
	 * @param RefNo
	 *            New value of property RefNo.
	 */
	public void setRefNo(java.lang.String RefNo) {
		this.RefNo = RefNo;
	}

	/**
	 * Getter for property ResponseCode.
	 * 
	 * @return Value of property ResponseCode.
	 */
	public java.lang.String getResponseCode() {
		return ResponseCode;
	}

	/**
	 * Setter for property ResponseCode.
	 * 
	 * @param ResponseCode
	 *            New value of property ResponseCode.
	 */
	public void setResponseCode(java.lang.String ResponseCode) {
		this.ResponseCode = ResponseCode;
	}

	/**
	 * Getter for property ApprovalCode.
	 * 
	 * @return Value of property ApprovalCode.
	 */
	public java.lang.String getApprovalCode() {
		return ApprovalCode;
	}

	/**
	 * Setter for property ApprovalCode.
	 * 
	 * @param ApprovalCode
	 *            New value of property ApprovalCode.
	 */
	public void setApprovalCode(java.lang.String ApprovalCode) {
		this.ApprovalCode = ApprovalCode;
	}

	/**
	 * Getter for property TranxCode.
	 * 
	 * @return Value of property TranxCode.
	 */
	public java.lang.String getTranxCode() {
		return TranxCode;
	}

	/**
	 * Setter for property TranxCode.
	 * 
	 * @param TranxCode
	 *            New value of property TranxCode.
	 */
	public void setTranxCode(java.lang.String TranxCode) {
		this.TranxCode = TranxCode;
	}

	public String getDayOfMonth() {
		return strDayOfMonth;
	}

	public void setDayOfMonth(String strDayOfMonth) {
		this.strDayOfMonth = strDayOfMonth;
	}

	public String getMessage() {
		return this.strMessage;
	}

	public void setMessage(String strMessage) {
		this.strMessage = strMessage;
	}

	public String getCycleNo() {
		return this.strCycleNo;
	}

	/**
	 * Gets the value for strMerId
	 * 
	 * @param None
	 * @return String
	 * @exception None
	 */
	public void setCycleNo(String strCycleNo) {
		this.strCycleNo = strCycleNo;
	}

	/**
	 * Getter for property strReportCode.
	 * 
	 * @return Value of property strReportCode.
	 * 
	 */
	public java.lang.String getReportCode() {
		return strReportCode;
	}

	/**
	 * Setter for property strReportCode.
	 * 
	 * @param strReportCode
	 *            New value of property strReportCode.
	 * 
	 */
	public void setReportCode(java.lang.String strReportCode) {
		this.strReportCode = strReportCode;
	}

	public String getAuthPerson() {
		return authPerson;
	}

	public void setAuthPerson(String authPerson) {
		this.authPerson = authPerson;
	}

	public String getAppNos() {
		return appNos;
	}

	public void setAppNos(String appNos) {
		this.appNos = appNos;
	}

	public String getNewNos() {
		return newNos;
	}

	public void setNewNos(String newNos) {
		this.newNos = newNos;
	}

	public String getCardProduct() {
		return cardProduct;
	}

	public void setCardProduct(String cardProduct) {
		this.cardProduct = cardProduct;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getTranxStatus() {
		return tranxStatus;
	}

	public void setTranxStatus(String tranxStatus) {
		this.tranxStatus = tranxStatus;
	}

	public String getTranxType() {
		return tranxType;
	}

	public void setTranxType(String tranxType) {
		this.tranxType = tranxType;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getCardProductType() {
		return cardProductType;
	}

	public void setCardProductType(String cardProductType) {
		this.cardProductType = cardProductType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getOldCardNo() {
		return oldCardNo;
	}

	public void setOldCardNo(String oldCardNo) {
		this.oldCardNo = oldCardNo;
	}

	public String getRecon() {
		return recon;
	}

	public void setRecon(String recon) {
		this.recon = recon;
	}

}// end bean