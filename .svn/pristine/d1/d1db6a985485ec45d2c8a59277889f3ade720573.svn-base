package org.transinfo.cacis.formbean.customerservice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.struts.validator.ValidatorForm;

public class CreditSplitForm extends ValidatorForm {
	
	//these ar actual values to calculate
	  private String creditLimit;
	  private String cashLimit;
	 
	  private String cardsSize;
	 //this cardno for csr screen to get the data
	  private String cardNumber;
	 
	  private LimitForm limitForm0 = new LimitForm();
	  private LimitForm limitForm1 = new LimitForm();
	  private LimitForm limitForm2 = new LimitForm();
	  private LimitForm limitForm3 = new LimitForm();
	  private LimitForm limitForm4 = new LimitForm();
	
	  private Set limitFormsList = new HashSet();

public CreditSplitForm(){
	
}


public LimitForm getLimitForm1() {
	return limitForm1;
}

public void setLimitForm1(LimitForm limitForm1) {
	this.limitForm1 = limitForm1;
}

public LimitForm getLimitForm2() {
	return limitForm2;
}

public void setLimitForm2(LimitForm limitForm2) {
	this.limitForm2 = limitForm2;
}

public LimitForm getLimitForm3() {
	return limitForm3;
}

public void setLimitForm3(LimitForm limitForm3) {
	this.limitForm3 = limitForm3;
}



public LimitForm getLimitForm0() {
	return limitForm0;
}



public void setLimitForm0(LimitForm limitForm0) {
	this.limitForm0 = limitForm0;
}


public String getCreditLimit() {
	return creditLimit;
}


public void setCreditLimit(String creditLimit) {
	this.creditLimit = creditLimit;
}


public LimitForm getLimitForm4() {
	return limitForm4;
}


public void setLimitForm4(LimitForm limitForm4) {
	this.limitForm4 = limitForm4;
}


public String getCardsSize() {
	return cardsSize;
}


public void setCardsSize(String cardsSize) {
	this.cardsSize = cardsSize;
}


public Set getLimitFormsList() {
	return limitFormsList;
}

/* for CsrCreditSplit when ever copying list from dto to form this executes
 * getting all the limitForm objects from Set and setting to CreditSplitForm's
 * LimitForms(0,1--4) 
 * */
public void setLimitFormsList(Set formsList) {
	//this.limitFormsList = formsList;
	 
     Iterator it =formsList.iterator(); 
      int limitIndex = 0; 
      while (it.hasNext()) 
      { 
       LimitForm objlimitForm =(LimitForm)it.next();
        if(limitIndex==0)this.limitForm0        = objlimitForm;
      	else if(limitIndex==1)this.limitForm1  = objlimitForm;
      	else if(limitIndex==2)this.limitForm2  = objlimitForm;
     	else if(limitIndex==3)this.limitForm3  = objlimitForm;
     	else if(limitIndex==4)this.limitForm4  = objlimitForm; 
        limitIndex++; 
       }
    
}


public String getCardNumber() {
	return cardNumber;
}


public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
}


public String getCashLimit() {
	return cashLimit;
}


public void setCashLimit(String cashLimit) {
	this.cashLimit = cashLimit;
}





}
