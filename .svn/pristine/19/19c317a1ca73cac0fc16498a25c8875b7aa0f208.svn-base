package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.transinfo.cacis.formbean.customerservice.CreditSplitForm;

public class CreditSplitDto implements Serializable{
	
	
	 private int cardsSize;
	 private String cardNumber;
	 private String creditLimit;
	 private String cashLimit;
	
	 private Set limitFormsList = new HashSet();
	
	 public CreditSplitDto(){}
	
	public CreditSplitDto(CreditSplitForm objSplitForm){
		addLimitForms(objSplitForm);
	}

/*this is to add all the Limitforms of CreditSplitForm to CreditSplitForm's Set
 * beanutil copy's only this Set to Dto Set*/	
 public void addLimitForms(CreditSplitForm objSplitForm){
	 
	 objSplitForm.getLimitFormsList().add(objSplitForm.getLimitForm0());
	 objSplitForm.getLimitFormsList().add(objSplitForm.getLimitForm1());
	 objSplitForm.getLimitFormsList().add(objSplitForm.getLimitForm2());
	 objSplitForm.getLimitFormsList().add(objSplitForm.getLimitForm3());
	 objSplitForm.getLimitFormsList().add(objSplitForm.getLimitForm4());
  }

  public Set getLimitFormsList() {
	return limitFormsList;
  }

  public void setLimitFormsList(Set limitFormsList) {
	this.limitFormsList = limitFormsList;
  }

public String getCardNumber() {
	return cardNumber;
}

public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
}



public String getCreditLimit() {
	return creditLimit;
}

public void setCreditLimit(String creditLimit) {
	this.creditLimit = creditLimit;
}

public int getCardsSize() {
	return cardsSize;
}

public void setCardsSize(int cardsSize) {
	this.cardsSize = cardsSize;
}

public String getCashLimit() {
	return cashLimit;
}

public void setCashLimit(String cashLimit) {
	this.cashLimit = cashLimit;
}
 }