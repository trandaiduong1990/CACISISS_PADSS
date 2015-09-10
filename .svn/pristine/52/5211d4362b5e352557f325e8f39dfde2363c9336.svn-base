package org.transinfo.cacis.formbean.letters;

import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.letters.DispatchLetterDAO;

public class ChLetterHistSearch extends ValidatorActionForm{

	private String applicationId;
	private String cardNumber;
	private String letterId;
	private String status;
	private String dispatchId;
	private String applSource;
	private String issuerId;
	private String dispatchDate;


    private Map letterTypesList;


	public ChLetterHistSearch(){
		getPreListData();
	}

	public Map getLetterTypesList() {
		return letterTypesList;
	}

	public void setLetterTypesList(Map letterTypesList) {
		this.letterTypesList = letterTypesList;
	}

	public void getPreListData() {
		try{
            DispatchLetterDAO objDispLetterDAO = DAOFactory.getInstance().getDispatchLetterDAO();
            if(letterTypesList==null)  {
            	letterTypesList = objDispLetterDAO.letterTypesList("ALL");
            }
        }catch(Exception e){
            System.out.println("Error while getting  PreListData:"+e);
        }
	}

	public String getApplSource() {
		return applSource;
	}

	public void setApplSource(String applSource) {
		this.applSource = applSource;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}


}
