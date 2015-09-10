package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardPromotionDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class CardPromotionSearchForm extends ValidatorForm  {

		   private String promotionId;
		   private Map cardPromotionList;
           private String issuerId;  

		   public CardPromotionSearchForm() {
			//   getPreListData();
		   }
		 

		public String getPromotionId() {
			return promotionId;
		}
		
		public void setPromotionId(String promotionId) {
			this.promotionId = promotionId;
		}
		
		public Map getCardPromotionList() {
			return cardPromotionList;
		}
		
		public void setCardPromotionList(Map cardPromotionList) {
			this.cardPromotionList = cardPromotionList;
		}

    public void getPreListData(){
	 
	try{
		CardPromotionDAO objDAO = DAOFactory.getInstance().getCardPromotionDAO();
 		if(cardPromotionList==null)  {
 			cardPromotionList= objDAO.cardPromotionListData();
	     }else{
		   System.out.println("cardPromotionList existed");
	     }
      }catch(Exception e){
	      System.out.println("Error while getting  cardPromotionList in CardPromotionSearchForm:"+e.getMessage());
	    }
	}


	public String getIssuerId() {
		return issuerId;
	}


	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

  


}
