package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CustomerGroupFeeDAO;

@SuppressWarnings( { "unchecked", "serial" })
public class CustomerGroupFeeSetupForm extends ValidatorForm {

	private String id="";
	private String cardProduct;
	private String customerType;
	private String annualFee;
	private String joinFee;
	private String issuerId;
	private String annualFeeSup;
	private String joinFeeSup;

	private Map cardProductList;
	private Map customerTypeList;

	public CustomerGroupFeeSetupForm() {
		// getPreListData();
	}

	public void getPreListData() {

		try {

			CustomerGroupFeeDAO objDAO = DAOFactory.getInstance().getCustomeGroupFeeDAO();

			if (cardProductList == null) {
				cardProductList = objDAO.cardProductListData(issuerId);
			}
			if (customerTypeList == null) {
				customerTypeList = objDAO.customerTypeListData(issuerId);
			}
		} catch (Exception e) {
			System.out
			.println("Error while getting  PreListData in CardProductSetup:"
					+ e.getMessage());
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardProduct() {
		return cardProduct;
	}

	public void setCardProduct(String cardProduct) {
		this.cardProduct = cardProduct;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(String annualFee) {
		this.annualFee = annualFee;
	}

	public String getJoinFee() {
		return joinFee;
	}

	public void setJoinFee(String joinFee) {
		this.joinFee = joinFee;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public Map getCustomerTypeList() {
		return customerTypeList;
	}

	public void setCustomerTypeList(Map customerTypeList) {
		this.customerTypeList = customerTypeList;
	}

	public String getAnnualFeeSup() {
		return annualFeeSup;
	}

	public void setAnnualFeeSup(String annualFeeSup) {
		this.annualFeeSup = annualFeeSup;
	}

	public String getJoinFeeSup() {
		return joinFeeSup;
	}

	public void setJoinFeeSup(String joinFeeSup) {
		this.joinFeeSup = joinFeeSup;
	}

}
