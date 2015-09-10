package org.transinfo.cacis.formbean.settings;

import java.util.Collection;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CreditLimitProfileDAO;

@SuppressWarnings("serial")
public class CreditLimitProfileSearchForm extends ValidatorForm {
	
	private String scoreId;
	private String cardProductId;
	private Collection searchList;
	private String totalCount;
	private String pageNo;
	
	private Map creditLimitProfileList;
	private Map cardProductList;
	private String screen;

	public String getScoreId() {
		return scoreId;
	}
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	public String getCardProductId() {
		return cardProductId;
	}
	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}
	public Map getCreditLimitProfileList() {
		return creditLimitProfileList;
	}
	public void setCreditLimitProfileList(Map creditLimitProfileList) {
		this.creditLimitProfileList = creditLimitProfileList;
	}
	public Map getCardProductList() {
		return cardProductList;
	}
	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}
	public Collection getSearchList() {
		return searchList;
	}
	public void setSearchList(Collection searchList) {
		this.searchList = searchList;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	
	public void getPreListData() {

		try{
			CreditLimitProfileDAO objDAO = DAOFactory.getInstance().getCreditLimitProfileDAO();
	 		
			if(creditLimitProfileList == null)  {
				creditLimitProfileList = objDAO.getScreditNameList();	
		    }
			
			if(cardProductList == null)  {
				cardProductList = objDAO.getCardProductNameList();	
		    }

		} catch(Exception e) {
		      System.out.println("Error while getting PreList in CreditLimitProfileSearchForm:" + e.getMessage());
		}
	}
}
