package org.transinfo.cacis.formbean.cardproduction;

import java.util.Collection;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDeliverDAO;

@SuppressWarnings( { "unchecked", "serial" })
public class CardDeliverSearch extends ValidatorForm {

	private String fromCardNumber;
	private String toCardNumber;
	private String cardDeliverStatus;
	private String selectedCards[] = {};
	private String selectedCardDeliverSerialNos[] = {};
	private Map cardProcessStatusList;

	private String cardNumber;
	private String startDate;
	private String endDate;

	private Collection successCardsList;
	private String cardNos;

	private String branchId;
	private String issuerId;
	
	public CardDeliverSearch() {
		// getPreListData();
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

	public void getPreListData() {

		try {

			CardDeliverDAO objDAO = DAOFactory.getInstance().getCardDeliverDAO();

			if (cardProcessStatusList == null) {
				cardProcessStatusList = objDAO.cardDeliverProcessStatusList();
			}

		} catch (Exception e) {
			System.out
					.println("Error while getting  PreListData in CardDeliverSearch:"
							+ e.getMessage());
		}

	}

	public Map getCardProcessStatusList() {
		return cardProcessStatusList;
	}

	public void setCardProcessStatusList(Map cardProcessStatusList) {
		this.cardProcessStatusList = cardProcessStatusList;
	}

	public String getCardDeliverStatus() {
		return cardDeliverStatus;
	}

	public void setCardDeliverStatus(String cardDeliverStatus) {
		this.cardDeliverStatus = cardDeliverStatus;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public String[] getSelectedCardDeliverSerialNos() {
		return selectedCardDeliverSerialNos;
	}

	public void setSelectedCardDeliverSerialNos(
			String[] selectedCardDeliverSerialNos) {
		this.selectedCardDeliverSerialNos = selectedCardDeliverSerialNos;
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

}
