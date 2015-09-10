package org.transinfo.cacis.formbean.accounting;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.accounting.CardHolderPaymentDAO;
import org.transinfo.cacis.formbean.common.AddressForm;

@SuppressWarnings({"serial","unchecked"})
public class CardHolderPaymentForm extends ValidatorForm{

	//private String     settlementId;
	//private String   refNumber;
	private String   issuerId;
	private String   cardNumber;
	private String   currencyCode;
	private String   amountCurr;
	private String   userId;
	private String creditLimit;
	//	for prelist data
	private Map  countryList;
	//this for address
	AddressForm requestAddress = new AddressForm();

	private String butaction="SAVE";
	public CardHolderPaymentForm(){}




	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getIssuerId() {
		return issuerId;
	}


	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public AddressForm getRequestAddress() {
		return requestAddress;
	}


	public void setRequestAddress(AddressForm requestAddress) {
		this.requestAddress = requestAddress;
	}

	public void getPreListData(){

		try{

			CardHolderPaymentDAO objDAO =DAOFactory.getInstance().getCardHolderPaymentDAO();

			if(countryList==null)  {
				countryList= objDAO.countryListData();
			}else
			{
				System.out.println("Country ListData Existed:");
			}

		}catch(Exception e)
		{
			System.out.println("Error while getting  PreListData:"+e.getMessage());
		}

	}



	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public void setCountryList(Map countryList) {
		this.countryList = countryList;
	}


	public Map getCountryList() {
		return countryList;
	}




	public String getAmountCurr() {
		return amountCurr;
	}




	public void setAmountCurr(String amountCurr) {
		this.amountCurr = amountCurr;
	}

	//	 Reset form fields.
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		cardNumber = null;
		amountCurr =null;
	}




	public String getCreditLimit() {
		return creditLimit;
	}




	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}


	public String getButaction() {
		return butaction;
	}




	public void setButaction(String butaction) {
		this.butaction = butaction;
	}
}
