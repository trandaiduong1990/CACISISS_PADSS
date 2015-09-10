package org.transinfo.cacis.formbean.customerservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardChangeDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;

@SuppressWarnings("unchecked")
public class CardChangeProcessForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cardNo;
	private Map productList;
	private String changeProductID;
	private String customerName;
	private String nric;
	
	private CustomerInfoDto customer;
	private CardsDto card;
	private String existCardStatus;
	private String userId;
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Map getProductList() {
		return productList;
	}
	public void setProductList(Map productList) {
		this.productList = productList;
	}
	public String getChangeProductID() {
		return changeProductID;
	}
	public void setChangeProductID(String changeProductID) {
		this.changeProductID = changeProductID;
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
	public CustomerInfoDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerInfoDto customer) {
		this.customer = customer;
	}
	public CardsDto getCard() {
		return card;
	}
	public void setCard(CardsDto card) {
		this.card = card;
	}
	public String getExistCardStatus() {
		return existCardStatus;
	}
	public void setExistCardStatus(String existCardStatus) {
		this.existCardStatus = existCardStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void getPreListData(String issuerId) {

		try {

			CardChangeDAO objDAO = DAOFactory.getInstance().getCardChangeDAO();
			if (productList == null || productList.isEmpty()) {
				productList = objDAO.cardProductListData(issuerId);
			}

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:" + e.getMessage());
		}

	}
	
	// Reset form fields.
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		cardNo = null;
		customerName = null;
		nric = null;
		changeProductID = null;
	}
}
