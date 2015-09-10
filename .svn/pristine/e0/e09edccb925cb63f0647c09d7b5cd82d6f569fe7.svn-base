package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardTypeDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class CardTypeSearchForm extends ValidatorForm  {

  
  private static final long serialVersionUID = 1L;
 
  private String cardTypeId;
   private String issuerId;
   
   private Map cardTypeList;


   public CardTypeSearchForm() {
	  // getPreListData();
   }
	

		public String getCardTypeId() {
			return cardTypeId;
		}
		
		public void setCardTypeId(String cardTypeId) {
			this.cardTypeId = cardTypeId;
		}
		
		public void setCardTypeList(Map cardTypeList) {
			this.cardTypeList = cardTypeList;
		}
  public void getPreListData(){
	 
	try{
		CardTypeDAO objDAO= DAOFactory.getInstance().getCardTypeDAO();
 		if(cardTypeList==null)  {
	   
 			cardTypeList = objDAO.cardTypeListData(issuerId);
	     }else{
		   System.out.println("CardType");
	     }
      }catch(Exception e){
	      System.out.println("Error while getting  PreList in CardTypeSearchForm:"+e.getMessage());
	    }
	}


public Map getCardTypeList() {
	return cardTypeList;
}


public String getIssuerId() {
	return issuerId;
}


public void setIssuerId(String issuerId) {
	this.issuerId = issuerId;
}



   


}
