package org.transinfo.cacis.formbean.letters;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.letters.ApplicationProcessSearchDAO;

public class ApplicationProcessSearch extends ValidatorForm{

	private String cardNumber;
	private String letterId;
	private String letterCategory;
    private Map letterTypesList;

	private String transDateFrom;
	private String transDateTo;
	public ApplicationProcessSearch() {
    }

    public void getPreListData()
    {
        try{
            ApplicationProcessSearchDAO objApplicationProcessSearchDAO = DAOFactory.getInstance().getApplicationProcessSearchDAO();

             if(letterTypesList==null)  {
             	letterTypesList = objApplicationProcessSearchDAO.letterTypesList(letterCategory);
             }
        }catch(Exception e){
            System.out.println("Error while getting WithdrawalLimitRules formbean PreListData:"+e);
        }
    }

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getLetterId() {
		return letterId;
	}

	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}

	public String getLetterCategory() {
		return letterCategory;
	}

	public void setLetterCategory(String letterCategory) {
		this.letterCategory = letterCategory;
	}

	public Map getLetterTypesList() {
		return letterTypesList;
	}

	public void setLetterTypesList(Map letterTypesList) {
		this.letterTypesList = letterTypesList;
	}

	public String getTransDateFrom() {
		return transDateFrom;
	}

	public void setTransDateFrom(String transDateFrom) {
		this.transDateFrom = transDateFrom;
	}

	public String getTransDateTo() {
		return transDateTo;
	}

	public void setTransDateTo(String transDateTo) {
		this.transDateTo = transDateTo;
	}


}




