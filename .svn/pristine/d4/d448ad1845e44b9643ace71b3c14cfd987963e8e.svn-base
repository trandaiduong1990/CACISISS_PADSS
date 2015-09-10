package org.transinfo.cacis.formbean.cardproduction;

import java.util.Collection;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardEmbossingDAO;

@SuppressWarnings({ "unchecked", "serial" })
public class RenewalCardEmbossingSearch extends ValidatorForm {

	private String fromCardNumber;
	private String toCardNumber;
	private String cardEmbossingStaus;
	private String selectedCards[] = {};
	private String selectedEmbossSerialNos[] = {};
	private Map cardProcessStatusList;

	private String cardNumber;
	private String startDate;
	private String endDate;
	
	private Collection successCardsList;
	private String cardNos;
	private String emossFile;
	
	private String branchId;
	private String issuerId;

	private String cardproduct;
	private Map cardProductList;

	public RenewalCardEmbossingSearch() {
		// getPreListData();
	}

	public String getCardEmbossingStaus() {
		return cardEmbossingStaus;
	}

	public void setCardEmbossingStaus(String cardEmbossingStaus) {
		this.cardEmbossingStaus = cardEmbossingStaus;
	}

	public String getFromCardNumber() {
		return fromCardNumber;
	}

	public void setFromCardNumber(String fromCardNumber) {
		this.fromCardNumber = fromCardNumber;
	}

	public String getToCardNumber() {
		return toCardNumber;
	}

	public void setToCardNumber(String toCardNumber) {
		this.toCardNumber = toCardNumber;
	}

	public String[] getSelectedCards() {
		return selectedCards;
	}

	public void setSelectedCards(String[] selectedCards) {
		this.selectedCards = selectedCards;
	}

	public Map getCardProcessStatusList() {
		return cardProcessStatusList;
	}

	public void setCardProcessStatusList(Map cardProcessStatusList) {
		this.cardProcessStatusList = cardProcessStatusList;
	}

	public void getPreListData() {

		try {

			CardEmbossingDAO objDAO = DAOFactory.getInstance()
					.getCardEmbossingDAO();

			if (cardProcessStatusList == null) {
				cardProcessStatusList = objDAO.cardProcessStatusList();
			}
			if (cardProductList == null) {
				cardProductList = objDAO.cardProductListData(issuerId);
			}

		} catch (Exception e) {
			System.out
					.println("Error while getting  PreListData in CardEmbossingSearchform :"
							+ e.getMessage());
		}

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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Collection getSuccessCardsList() {
		return successCardsList;
	}

	public void setSuccessCardsList(Collection successCardsList) {
		this.successCardsList = successCardsList;
	}

	public String getCardNos() {
		return cardNos;
	}

	public void setCardNos(String cardNos) {
		this.cardNos = cardNos;
	}

	public String getEmossFile() {
		return emossFile;
	}

	public void setEmossFile(String emossFile) {
		this.emossFile = emossFile;
	}

	public String[] getSelectedEmbossSerialNos() {
		return selectedEmbossSerialNos;
	}

	public void setSelectedEmbossSerialNos(String[] selectedEmbossSerialNos) {
		this.selectedEmbossSerialNos = selectedEmbossSerialNos;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getCardproduct() {
		return cardproduct;
	}

	public void setCardproduct(String cardproduct) {
		this.cardproduct = cardproduct;
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}
}
