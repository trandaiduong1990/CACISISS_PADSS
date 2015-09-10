package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
@SuppressWarnings( { "unchecked", "serial" })
public class CardProductSearchForm extends ValidatorForm {

	private String cardProductId;
	private String issuerId;

	private Map cardProductList;

	public CardProductSearchForm() {
		// getPreListData();
	}

	public CardProductSearchForm(String cardProductId) {
		System.out.println("Search CardTypeSearchForm Form" + cardProductId);
		setCardProductId(cardProductId);

	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public Map getCardProductList() {
		return cardProductList;
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
					.println("Error while getting PreList in CardProductSearchForm:"
							+ e.getMessage());
		}
	}

}
