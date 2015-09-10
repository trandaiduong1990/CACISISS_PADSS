package org.transinfo.cacis.formbean.settings;

import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CreditLimitProfileDAO;

@SuppressWarnings("serial")
public class CreditLimitProfileSetupForm extends ValidatorForm {

	private String sNo;
	private String issuerId;
	private String description;
	private String spf1;
	private String spt1;
	private String creditLimit1;
	
	private String spf2;
	private String spt2;
	private String creditLimit2;
	
	private String spf3;
	private String spt3;
	private String creditLimit3;
	
	private String spf4;
	private String spt4;
	private String creditLimit4;
	
	private String spf5;
	private String spt5;
	private String creditLimit5;
	
	private String spf6;
	private String spt6;
	private String creditLimit6;
	
	private String spf7;
	private String spt7;
	private String creditLimit7;
	
	private String spf8;
	private String spt8;
	private String creditLimit8;
	
	private String status;
	private Date lastUpdatedDate;
	private String lastUpdatedBy;
	
	private Map creditLimitProfileList;
	private Map cardProductList;
	
	private String totalScoringPoint;
	private String lowerLimit;
	private String upperLimit;
	private String cardProductId;
	private String scoreId;
	private String screen;
	
	public String getsNo() {
		return sNo;
	}
	public void setsNo(String sNo) {
		this.sNo = sNo;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSpf1() {
		return spf1;
	}
	public void setSpf1(String spf1) {
		this.spf1 = spf1;
	}
	public String getSpt1() {
		return spt1;
	}
	public void setSpt1(String spt1) {
		this.spt1 = spt1;
	}
	public String getCreditLimit1() {
		return creditLimit1;
	}
	public void setCreditLimit1(String creditLimit1) {
		this.creditLimit1 = creditLimit1;
	}
	public String getSpf2() {
		return spf2;
	}
	public void setSpf2(String spf2) {
		this.spf2 = spf2;
	}
	public String getSpt2() {
		return spt2;
	}
	public void setSpt2(String spt2) {
		this.spt2 = spt2;
	}
	public String getCreditLimit2() {
		return creditLimit2;
	}
	public void setCreditLimit2(String creditLimit2) {
		this.creditLimit2 = creditLimit2;
	}
	public String getSpf3() {
		return spf3;
	}
	public void setSpf3(String spf3) {
		this.spf3 = spf3;
	}
	public String getSpt3() {
		return spt3;
	}
	public void setSpt3(String spt3) {
		this.spt3 = spt3;
	}
	public String getCreditLimit3() {
		return creditLimit3;
	}
	public void setCreditLimit3(String creditLimit3) {
		this.creditLimit3 = creditLimit3;
	}
	public String getSpf4() {
		return spf4;
	}
	public void setSpf4(String spf4) {
		this.spf4 = spf4;
	}
	public String getSpt4() {
		return spt4;
	}
	public void setSpt4(String spt4) {
		this.spt4 = spt4;
	}
	public String getCreditLimit4() {
		return creditLimit4;
	}
	public void setCreditLimit4(String creditLimit4) {
		this.creditLimit4 = creditLimit4;
	}
	public String getSpf5() {
		return spf5;
	}
	public void setSpf5(String spf5) {
		this.spf5 = spf5;
	}
	public String getSpt5() {
		return spt5;
	}
	public void setSpt5(String spt5) {
		this.spt5 = spt5;
	}
	public String getCreditLimit5() {
		return creditLimit5;
	}
	public void setCreditLimit5(String creditLimit5) {
		this.creditLimit5 = creditLimit5;
	}
	public String getSpf6() {
		return spf6;
	}
	public void setSpf6(String spf6) {
		this.spf6 = spf6;
	}
	public String getSpt6() {
		return spt6;
	}
	public void setSpt6(String spt6) {
		this.spt6 = spt6;
	}
	public String getCreditLimit6() {
		return creditLimit6;
	}
	public void setCreditLimit6(String creditLimit6) {
		this.creditLimit6 = creditLimit6;
	}
	public String getSpf7() {
		return spf7;
	}
	public void setSpf7(String spf7) {
		this.spf7 = spf7;
	}
	public String getSpt7() {
		return spt7;
	}
	public void setSpt7(String spt7) {
		this.spt7 = spt7;
	}
	public String getCreditLimit7() {
		return creditLimit7;
	}
	public void setCreditLimit7(String creditLimit7) {
		this.creditLimit7 = creditLimit7;
	}
	public String getSpf8() {
		return spf8;
	}
	public void setSpf8(String spf8) {
		this.spf8 = spf8;
	}
	public String getSpt8() {
		return spt8;
	}
	public void setSpt8(String spt8) {
		this.spt8 = spt8;
	}
	public String getCreditLimit8() {
		return creditLimit8;
	}
	public void setCreditLimit8(String creditLimit8) {
		this.creditLimit8 = creditLimit8;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
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
	public String getTotalScoringPoint() {
		return totalScoringPoint;
	}
	public void setTotalScoringPoint(String totalScoringPoint) {
		this.totalScoringPoint = totalScoringPoint;
	}
	public String getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public String getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}
	public String getCardProductId() {
		return cardProductId;
	}
	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}
	public String getScoreId() {
		return scoreId;
	}
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
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
