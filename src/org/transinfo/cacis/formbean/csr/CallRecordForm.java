package org.transinfo.cacis.formbean.csr;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.csr.CsrDAO;

public class CallRecordForm extends ValidatorForm {
  
	private String referenceNo;
	private String originalRefNo;
	private String prevReferenceNo;
	private String cardNumber;
	private String callTypeId;
	private String callDescription;
	private String callStatus;
	private String issuerId;
	private String userId;
	//	for prelist data
	private Map callTypes;
	
	public CallRecordForm(){
		getPreListData();
	}

	
	public String getCallTypeId() {
		return callTypeId;
	}

	public void setCallTypeId(String callTypeId) {
		this.callTypeId = callTypeId;
	}

	public Map getCallTypes() {
		return callTypes;
	}

	public void setCallTypes(Map callTypes) {
		this.callTypes = callTypes;
	}

	
	public void getPreListData(){
		 
		try{
	
		CsrDAO objDAO =DAOFactory.getInstance().getCsrDAO();
	    if(callTypes==null)  {
	    	callTypes =objDAO.callTypeListData();
	 	  }
	  
	 }catch(Exception e)
	   {
		  System.out.println("Error while getting  PreListData in CallRecordForm:"+e.getMessage());
		}
		
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getCallStatus() {
		return callStatus;
	}


	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}


	public String getCallDescription() {
		return callDescription;
	}


	public void setCallDescription(String callDescription) {
		this.callDescription = callDescription;
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


	


	public String getOriginalRefNo() {
		return originalRefNo;
	}


	public void setOriginalRefNo(String originalRefNo) {
		this.originalRefNo = originalRefNo;
	}





	public String getReferenceNo() {
		return referenceNo;
	}


	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}


	public String getPrevReferenceNo() {
		return prevReferenceNo;
	}


	public void setPrevReferenceNo(String prevReferenceNo) {
		this.prevReferenceNo = prevReferenceNo;
	}
}
