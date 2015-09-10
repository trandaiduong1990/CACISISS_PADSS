package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductFeeDAO;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class CardProductFeeSearchForm extends ValidatorForm  {

   
	private static final long serialVersionUID = 1L;
    private String cardProductId;
    private String issuerId;
    private Map cardProductList;


   public CardProductFeeSearchForm() {
	 // getPreListData();
   }

	public String getCardProductId() {
		return cardProductId;
	}
	
	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}
	public Map getCardProductList() {
		return cardProductList;
	}
	
	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

  public void getPreListData(){
	 
	try{
		CardProductFeeDAO objDAO = DAOFactory.getInstance().getCardProductFeeDAO();
 		if(cardProductList==null)  {
 		     cardProductList=objDAO.cardProductListData(issuerId);
	     }else{
		   System.out.println("cardProductList alredy existed");
	     }
      }catch(Exception e){
	      System.out.println("Error while getting  cardProductList   in CardProductFeeSearchForm:"+e.getMessage());
	    }
	}

 public String getIssuerId() {
	return issuerId;
 }

 public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
   


}
