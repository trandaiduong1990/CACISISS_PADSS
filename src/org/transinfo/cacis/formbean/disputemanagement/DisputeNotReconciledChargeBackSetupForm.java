package org.transinfo.cacis.formbean.disputemanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeManagementDAO;

@SuppressWarnings( { "unchecked", "serial" })
public class DisputeNotReconciledChargeBackSetupForm extends ValidatorForm {

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

	private DisputeCleaningMasterForm disputeCleaningMasterForm;

	private String pageNo;

	private String disType;
	private String disGroup;
	private String disReason;
	private String disAmt;
	private String disCurr;
	private String cardHolder;
	private String memMsgText;

	private String docUpload;

	private Map disGroupList;
	private Map disReasonList;
	private Map currList;

	private String addButton = "Y";

	private String cbType;

	public void getPreListData() {

		try {

			DisputeManagementDAO objDisputeManagementDAO = DAOFactory.getInstance().getDisputeManagementDAO();
			
			if (disGroupList == null) {
				disGroupList = objDisputeManagementDAO.disputeGroupListData();
			}
			
			if (disReasonList == null) {
				disReasonList = objDisputeManagementDAO.disputeGroupReasonListData("");
			}
			
			if (currList == null) {
				currList = objDisputeManagementDAO.currencyListData();
			}
			
		} catch (Exception e) {
			System.out.println("PreListData in DisputeChargeBackSetupForm:"
					+ e.getMessage());
		}
	}

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

	public String getDisReason() {
		return disReason;
	}

	public void setDisReason(String disReason) {
		this.disReason = disReason;
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

	public String getMemMsgText() {
		return memMsgText;
	}

	public void setMemMsgText(String memMsgText) {
		this.memMsgText = memMsgText;
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

	public DisputeCleaningMasterForm getDisputeCleaningMasterForm() {
		return disputeCleaningMasterForm;
	}

	public void setDisputeCleaningMasterForm(
			DisputeCleaningMasterForm disputeCleaningMasterForm) {
		this.disputeCleaningMasterForm = disputeCleaningMasterForm;
	}

	public String getAddButton() {
		return addButton;
	}

	public void setAddButton(String addButton) {
		this.addButton = addButton;
	}

	public String getDocUpload() {
		return docUpload;
	}

	public void setDocUpload(String docUpload) {
		this.docUpload = docUpload;
	}

	public String getCbType() {
		return cbType;
	}

	public void setCbType(String cbType) {
		this.cbType = cbType;
	}

}
