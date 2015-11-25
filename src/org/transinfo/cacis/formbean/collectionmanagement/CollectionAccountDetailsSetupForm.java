package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAccountDetailsDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;

@SuppressWarnings("serial")
public class CollectionAccountDetailsSetupForm extends ValidatorForm {
	private String cardNo;
	private String customerName;
	private String status;
	private String agentId;
	private String issuerId;
	private String colectId;
	private String orgColectionAmt;
	private String reclassification;
	private String dateAssigned;
	private String amountAssigned;
	private String writeOffAmt;
	private String writeOffDate;
	private String recoveryAmt;
	private String lastRecoveryDate;
	private String amountToRecover;
	private String recovedFullDate;
	private String installmentPayment;
	private String noOfInstallment;
	private String interestRate;
	private String minPaymentAmt;
	private String note;
	private String currentCollector1;
	private String currentCollector2;
	private String colectRef = "A";
	private String creditLimit;
	private String dueAmt;
	private String currentInstallmentNo;
	Map userList;
	Map agentList;

	public String getCurrentCollector2() {
		return currentCollector2;
	}

	public void setCurrentCollector2(String currentCollector2) {
		this.currentCollector2 = currentCollector2;
	}

	public String getCurrentInstallmentNo() {
		return currentInstallmentNo;
	}

	public void setCurrentInstallmentNo(String currentInstallmentNo) {
		this.currentInstallmentNo = currentInstallmentNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Map getAgentList() {
		return agentList;
	}

	public void setAgentList(Map agentList) {
		this.agentList = agentList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getColectId() {
		return colectId;
	}

	public void setColectId(String colectId) {
		this.colectId = colectId;
	}

	public String getOrgColectionAmt() {
		return orgColectionAmt;
	}

	public void setOrgColectionAmt(String orgColectionAmt) {
		this.orgColectionAmt = orgColectionAmt;
	}

	public String getReclassification() {
		return reclassification;
	}

	public void setReclassification(String reclassification) {
		this.reclassification = reclassification;
	}

	public String getDateAssigned() {
		return dateAssigned;
	}

	public void setDateAssigned(String dateAssigned) {
		this.dateAssigned = dateAssigned;
	}

	public String getAmountAssigned() {
		return amountAssigned;
	}

	public void setAmountAssigned(String amountAssigned) {
		this.amountAssigned = amountAssigned;
	}

	public String getWriteOffAmt() {
		return writeOffAmt;
	}

	public void setWriteOffAmt(String writeOffAmt) {
		this.writeOffAmt = writeOffAmt;
	}

	public String getWriteOffDate() {
		return writeOffDate;
	}

	public void setWriteOffDate(String writeOffDate) {
		this.writeOffDate = writeOffDate;
	}

	public String getRecoveryAmt() {
		return recoveryAmt;
	}

	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}

	public String getLastRecoveryDate() {
		return lastRecoveryDate;
	}

	public void setLastRecoveryDate(String lastRecoveryDate) {
		this.lastRecoveryDate = lastRecoveryDate;
	}

	public String getAmountToRecover() {
		return amountToRecover;
	}

	public void setAmountToRecover(String amountToRecover) {
		this.amountToRecover = amountToRecover;
	}

	public String getRecovedFullDate() {
		return recovedFullDate;
	}

	public void setRecovedFullDate(String recovedFullDate) {
		this.recovedFullDate = recovedFullDate;
	}

	public String getInstallmentPayment() {
		return installmentPayment;
	}

	public void setInstallmentPayment(String installmentPayment) {
		this.installmentPayment = installmentPayment;
	}

	public String getNoOfInstallment() {
		return noOfInstallment;
	}

	public void setNoOfInstallment(String noOfInstallment) {
		this.noOfInstallment = noOfInstallment;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getMinPaymentAmt() {
		return minPaymentAmt;
	}

	public void setMinPaymentAmt(String minPaymentAmt) {
		this.minPaymentAmt = minPaymentAmt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCurrentCollector1() {
		return currentCollector1;
	}

	public void setCurrentCollector1(String currentCollector1) {
		this.currentCollector1 = currentCollector1;
	}

	public String getColectRef() {
		return colectRef;
	}

	public void setColectRef(String colectRef) {
		this.colectRef = colectRef;
	}

	public Map getUserList() {
		return userList;
	}

	public void setUserList(Map userList) {
		this.userList = userList;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getDueAmt() {
		return dueAmt;
	}

	public void setDueAmt(String dueAmt) {
		this.dueAmt = dueAmt;
	}

	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
		colectRef = null;
	}*/
	
	public void getPreListData() {

		try {
			CollectionAccountDetailsDAO objDAO = DAOFactory.getInstance()
					.getCollectionAccountDetailsDAO();

			if (userList == null) {
				userList = objDAO.getUserBranchList(issuerId);
			}
			if (agentList == null) {
				agentList = objDAO.getAgentList(issuerId);
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}
}
