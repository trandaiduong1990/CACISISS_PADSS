package org.transinfo.cacis.formbean.settings;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.BranchDAO;
import org.transinfo.cacis.dataacess.dao.settings.CardProductDAO;
import org.transinfo.cacis.dataacess.dao.settings.ProductTranxDAO;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class ProductTranxSearchForm extends  ValidatorForm  {

	private String cardProductId;
	private String channelId;
	private String issuerId;
	private String totalCount;
	private Map cardProductList;
	private Map chanelList;

	public ProductTranxSearchForm() {
		// getPreListData();
	}

	public void getPreListData(){

		try{
			ProductTranxDAO objDAO = DAOFactory.getInstance().getProductTranxDAO();
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
			System.out.println("Error while getPreListData in ProductTranxSearchForm "+e.getMessage());
		}
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
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

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}



}
