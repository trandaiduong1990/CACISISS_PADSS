package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductLimitDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
@SuppressWarnings("serial")
public class CardProductLimitSearchForm extends ValidatorForm {

	private String cardProductId;
	private String issuerId;

	@SuppressWarnings("unchecked")
	private Map cardProductList;

	public CardProductLimitSearchForm() {
		// getPreListData();
	}

	public CardProductLimitSearchForm(String cardProductId) {
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

	@SuppressWarnings("unchecked")
	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	@SuppressWarnings("unchecked")
	public Map getCardProductList() {
		return cardProductList;
	}

	public void getPreListData() {

		try {
			CardProductLimitDAO objDAO = DAOFactory.getInstance().getCardProductLimitDAO();
			if (cardProductList == null) {

				cardProductList = objDAO.cardProductListData(issuerId);
			} else {
				System.out.println("CardProductList");
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CardProductLimitSearchForm:"
							+ e.getMessage());
		}
	}

}
