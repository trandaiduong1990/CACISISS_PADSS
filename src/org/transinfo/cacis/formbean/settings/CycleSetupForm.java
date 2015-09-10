package org.transinfo.cacis.formbean.settings;

import org.apache.struts.validator.ValidatorForm;

public class CycleSetupForm extends ValidatorForm {

	 private String cycleNo;
	 private String issuerId;
	 private String dayOfMonth;
	 private String userId;
	 
	// private HashMap issuerList;
	 
	 public CycleSetupForm(){
	//	 getPreListData();
	 }
	 
	public String getCycleNo() {
		return cycleNo;
	}
	public void setCycleNo(String cycleNo) {
		this.cycleNo = cycleNo;
	}
	public String getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
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

/*	public HashMap getIssuerList() {
		return issuerList;
	}

	public void setIssuerList(HashMap issuerList) {
		this.issuerList = issuerList;
	}
	public void getPreListData(){
		 
		try{
	 		if(issuerList==null)  {
			    CycleManager objManager = new CycleManager();
			    objManager.issuerPreListData(this);
		     }else{
			   System.out.println("IssuerList");
		     }
	      }catch(Exception e){
		      System.out.println("Error while getting  IssuerList:"+e.getMessage());
		    }
		}
	*/
}
