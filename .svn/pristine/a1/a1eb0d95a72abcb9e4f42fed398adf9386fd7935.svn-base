package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CurrencyRateDAO;
import org.transinfo.cacis.dto.settings.CurrencyDto;

@SuppressWarnings( { "serial", "unchecked" })
public class CurrencyRateForm extends ValidatorForm {

	private String issuerId;
	private CurrencyDto currency;
	private String rate;
	private String userId;
	private String currencyId;

	private Map currencyList;

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public CurrencyDto getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDto currency) {
		this.currency = currency;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public Map getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(Map currencyList) {
		this.currencyList = currencyList;
	}

	public void getPreListData() {

		try {

			CurrencyRateDAO objDAO = DAOFactory.getInstance()
					.getCurrencyRateDAO();
			if (currencyList == null || currencyList.isEmpty()) {
				currencyList = objDAO.currencyListData();
			}

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:"
					+ e.getMessage());
		}

	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		currencyId = null;
		rate = null;
	}
}
