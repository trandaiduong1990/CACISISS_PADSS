package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.BranchDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class BranchSearchForm extends  ValidatorForm  {

   private String branchId;
   private String issuerId;
   
   private Map branchList;

   public BranchSearchForm() {
	    // getPreListData();
   }

   public BranchSearchForm(String branchId) {
	   setBranchId(branchId);

   }

  public void getPreListData(){
	 
	try{
		BranchDAO objDAO= DAOFactory.getInstance().getBranchDAO();
 		if(branchList==null)  {
 			
 			branchList=objDAO.branchListData(issuerId);
	     
 		 }else{
		   System.out.println("BranchList existed");
	     }
      }catch(Exception e){
	      System.out.println("Error while getting BranchList in BranchSearchForm "+e.getMessage());
	    }
	}

	public String getBranchId() {
		return branchId;
	}
	
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	public Map getBranchList() {
		return branchList;
	}
	
	public void setBranchList(Map branchList) {
		this.branchList = branchList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}


}
