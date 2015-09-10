package org.transinfo.cacis.formbean.accounting;

import java.util.List;

import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings({ "unchecked", "serial" })
public class CardHolderStatementForm extends ValidatorForm{
	
	private String cardNumber;
	
	private String unbilled="N";
	private String current="N";
	private String previous="N";

	private String enableLink;
	
	private List postTranx;
	private List paymentTranx;
	private List feeTranx;
	
	private CustomerStatementForm customerStatement;

	private String outAmtDue;
	private String outMinPayDue;

	private List statTranx;
	private List statPay;
	private List statInt;
	private List statFee;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public List getPostTranx() {
		return postTranx;
	}

	public void setPostTranx(List postTranx) {
		this.postTranx = postTranx;
	}

	public List getPaymentTranx() {
		return paymentTranx;
	}

	public void setPaymentTranx(List paymentTranx) {
		this.paymentTranx = paymentTranx;
	}

	public List getFeeTranx() {
		return feeTranx;
	}

	public void setFeeTranx(List feeTranx) {
		this.feeTranx = feeTranx;
	}

	public String getUnbilled() {
		return unbilled;
	}

	public void setUnbilled(String unbilled) {
		this.unbilled = unbilled;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getEnableLink() {
		return enableLink;
	}

	public void setEnableLink(String enableLink) {
		this.enableLink = enableLink;
	}

	public CustomerStatementForm getCustomerStatement() {
		return customerStatement;
	}

	public void setCustomerStatement(CustomerStatementForm customerStatement) {
		this.customerStatement = customerStatement;
	}

	public String getOutAmtDue() {
		return outAmtDue;
	}

	public void setOutAmtDue(String outAmtDue) {
		this.outAmtDue = outAmtDue;
	}

	public String getOutMinPayDue() {
		return outMinPayDue;
	}

	public void setOutMinPayDue(String outMinPayDue) {
		this.outMinPayDue = outMinPayDue;
	}

	public List getStatTranx() {
		return statTranx;
	}

	public void setStatTranx(List statTranx) {
		this.statTranx = statTranx;
	}

	public List getStatPay() {
		return statPay;
	}

	public void setStatPay(List statPay) {
		this.statPay = statPay;
	}

	public List getStatInt() {
		return statInt;
	}

	public void setStatInt(List statInt) {
		this.statInt = statInt;
	}

	public List getStatFee() {
		return statFee;
	}

	public void setStatFee(List statFee) {
		this.statFee = statFee;
	}
}
