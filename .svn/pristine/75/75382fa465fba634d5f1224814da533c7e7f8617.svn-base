package org.transinfo.cacis.formbean.applicationforms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.AddCardProcessDAO;
import org.transinfo.cacis.dataacess.dao.settings.CardProductDAO;

@SuppressWarnings({ "serial", "unchecked" })
public class AddCardProcessSetupForm extends ValidatorForm {
	private String customerName;
	private String nric;
	private String accountType;
	private String creditLimit;
	private String currency;
	private String cycle = "25";
	private List<Integer> cycleList = new ArrayList<Integer>();
	private Collection searchList;
	private String totalCount;
	private String issuerId;
	private String cardProductId;
	private String sno;
	private String customerId;

	private Map cardProductList;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public List<Integer> getCycleList() {
		for(int i=1;i<=30;i++) {
			cycleList.add(i);
		}
		return cycleList;
	}

	public void setCycleList(List<Integer> cycleList) {
		this.cycleList = cycleList;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
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

	public void getPreListData() {

		try {
			CardProductDAO objDAO = DAOFactory.getInstance()
					.getCardProductDAO();
			if (cardProductList == null) {

				cardProductList = objDAO.cardProductListData(issuerId);
			} else {
				System.out.println("CardProductList");
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in AddCardProcessSetupForm:"
							+ e.getMessage());
		}
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	
}
