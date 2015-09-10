package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRulesDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class CardProductRulesSearchForm extends ValidatorForm {

	private String cardProductId;
	private String issuerId;
	private String totalCount;
	private Map cardProductList;

	public CardProductRulesSearchForm() {
		// getPreListData();
	}

	public void getPreListData() {

		try {
			CardProductRulesDAO objDAO = DAOFactory.getInstance()
					.getCardProductRulesDAO();
			if (cardProductList == null) {
				cardProductList = objDAO.cardProductListData(issuerId);
			} else {
				System.out.println("cardProductList existed");
			}

		} catch (Exception e) {
			System.out
					.println("Error while getPreListData in CardProductRulesSearchForm "
							+ e.getMessage());
		}
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

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

}
