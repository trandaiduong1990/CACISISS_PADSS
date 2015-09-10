package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRateDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
@SuppressWarnings( { "unchecked", "serial" })
public class CardProductRateSearchForm extends ValidatorForm {

	private String cardProductId;
	private String issuerId;
	private Map cardProductList;

	public CardProductRateSearchForm() {
		// getPreListData();
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public void getPreListData() {

		try {
			CardProductRateDAO objDAO = DAOFactory.getInstance()
					.getCardProductRateDAO();
			if (cardProductList == null) {
				cardProductList = objDAO.cardProductListData(issuerId);
			} else {
				System.out.println("cardProductList alredy existed");
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting  cardProductList   in CardProductRateSetupForm:"
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
}