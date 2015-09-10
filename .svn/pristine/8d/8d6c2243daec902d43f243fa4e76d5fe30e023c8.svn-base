package org.transinfo.cacis.formbean.settings;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.ProductTranxDAO;

public class ProductTranxSetupForm extends ValidatorForm {
	private String cardProductId;
	private String channelId;
	private String issuerId;
	private String tranxType;
	private Map cardProductList;
	private Map chanelList;
	private ArrayList tranxTypeList;

	public void getPreListData(){
		 
		try{
			ProductTranxDAO objDAO = DAOFactory.getInstance().getProductTranxDAO();
	 		if(tranxTypeList==null)  {
	 			tranxTypeList=objDAO.tranxTypeList("T");
	 		 }else{
			   System.out.println("tranxTypeList existed");
		     }
	 		if(cardProductList==null)  {
	 			cardProductList=objDAO.cardProductListData(issuerId);
	 		 }else{
			   System.out.println("cardProductList existed");
		     }
	 		
	 		if(chanelList==null)  {
	 			chanelList=objDAO.statusListData("CHANNELS");
	 		 }else{
			   System.out.println("chanelList existed");
		     }
	      }catch(Exception e){
		      System.out.println("Error while getPreListData in ProductTranxSetupForm "+e.getMessage());
		    }
		}
	
	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public Map getChanelList() {
		return chanelList;
	}

	public void setChanelList(Map chanelList) {
		this.chanelList = chanelList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public ArrayList getTranxTypeList() {
		return tranxTypeList;
	}

	public void setTranxTypeList(ArrayList tranxTypeList) {
		this.tranxTypeList = tranxTypeList;
	}

	public String getTranxType() {
		return tranxType;
	}

	public void setTranxType(String tranxType) {
		this.tranxType = tranxType;
	}

}
