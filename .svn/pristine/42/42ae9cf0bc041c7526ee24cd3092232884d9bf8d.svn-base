package org.transinfo.cacis.formbean.batchprocess;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardBatchDAO;

@SuppressWarnings("serial")
public class CardBatchForm extends ValidatorForm {
	
	private String batchId;
	private String batchName;
	private String cardProductId;
	private String cardProductName;
	private String branchId;
	private String branchName;
	private String noOfCard;
	private String status;
	private String updatedDate;
	private String updatedBy;
	private String statusDesc;
	private String userId;
	private String screenType;
	private String authorizedBy;
	private String authorizedDate;
	private String userName;
	private String pwd;
	private String authForm;
	private String createdBy;
	private String createdDate;
	private String issuerId;
	
	Map cardProductList;
	Map branchList;
	
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
	public String getNoOfCard() {
		return noOfCard;
	}

	public void setNoOfCard(String noOfCard) {
		this.noOfCard = noOfCard;
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public Map getBranchList() {
		return branchList;
	}

	public void setBranchList(Map branchList) {
		this.branchList = branchList;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}
	
	public String getCardProductName() {
		return cardProductName;
	}

	public void setCardProductName(String cardProductName) {
		this.cardProductName = cardProductName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public String getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(String authorizedDate) {
		this.authorizedDate = authorizedDate;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getAuthForm() {
		return authForm;
	}

	public void setAuthForm(String authForm) {
		this.authForm = authForm;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public void getPreListData() {

		try {
			CardBatchDAO objCardBatchDAO = DAOFactory.getInstance()
					.getCardBatchDAO();

			if (cardProductList == null) {
				cardProductList = objCardBatchDAO.cardProductList(issuerId);
			}
			
			if (branchList == null) {
				branchList = objCardBatchDAO.branchList(issuerId);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting  PreListData in Card Product List:"
							+ e);
		}
	}
}
