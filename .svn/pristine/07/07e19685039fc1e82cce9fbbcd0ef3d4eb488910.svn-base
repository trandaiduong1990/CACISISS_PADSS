package org.transinfo.cacis.formbean.riskmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.WithdrawalLimitRulesDAO;
import org.transinfo.cacis.formbean.common.DateForm;

public class WithdrawalLimitRules extends ValidatorForm{

	private String ruleId;
	private String cardProductId;
	private String custTypeId;
	private String mcc;
	private String amountPerTranx;
	private String dailyLimitCount;
	private String dailyLimitAmount;
	private String monthlyLimitCount;
	private String monthlyLimitAmount;
	private String currCode;
	private String tranxCode;
	DateForm lastUpdatedDateForm = new DateForm();
    private String lastUpdatedBy;
    private String issuerId;

    private Map custTypeList;
    private Map merchantCategoryList;
    private Map cardProductList;
    private Map currList;
    private Map tranxCodeList;
    
    public WithdrawalLimitRules() {
    }

    public void getPreListData()
    {
        try{
            WithdrawalLimitRulesDAO objWithdrawalLimitRulesDAO = DAOFactory.getInstance().getWithdrawalLimitRulesDAO();
            if(custTypeList==null)  {
            	custTypeList = objWithdrawalLimitRulesDAO.customerTypeListData(issuerId);
            }
            if(merchantCategoryList==null){
            	merchantCategoryList = objWithdrawalLimitRulesDAO.merchantCategoryListData();
            }
            if(cardProductList==null)  {
                cardProductList = objWithdrawalLimitRulesDAO.cardProductListData(issuerId);
            }
            if(currList==null)  {
            	currList = objWithdrawalLimitRulesDAO.currencyListData();
            }
            if(tranxCodeList==null)  {
            	tranxCodeList = objWithdrawalLimitRulesDAO.tranxCodeListData();
            }
        }catch(Exception e){
            System.out.println("Error while getting WithdrawalLimitRules formbean PreListData:"+e);
        }
    }


	public String getAmountPerTranx() {
		return amountPerTranx;
	}


	public void setAmountPerTranx(String amountPerTranx) {
		this.amountPerTranx = amountPerTranx;
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


	public String getCurrCode() {
		return currCode;
	}


	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}


	public Map getCurrList() {
		return currList;
	}


	public void setCurrList(Map currList) {
		this.currList = currList;
	}


	public String getCustTypeId() {
		return custTypeId;
	}


	public void setCustTypeId(String custTypeId) {
		this.custTypeId = custTypeId;
	}


	public Map getCustTypeList() {
		return custTypeList;
	}


	public void setCustTypeList(Map custTypeList) {
		this.custTypeList = custTypeList;
	}


	public String getDailyLimitAmount() {
		return dailyLimitAmount;
	}


	public void setDailyLimitAmount(String dailyLimitAmount) {
		this.dailyLimitAmount = dailyLimitAmount;
	}


	public String getDailyLimitCount() {
		return dailyLimitCount;
	}


	public void setDailyLimitCount(String dailyLimitCount) {
		this.dailyLimitCount = dailyLimitCount;
	}


	public String getIssuerId() {
		return issuerId;
	}


	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}


	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}


	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}


	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}


	public void setLastUpdatedDateForm(DateForm lastUpdatedDate) {
		this.lastUpdatedDateForm = lastUpdatedDate;
	}


	public String getMcc() {
		return mcc;
	}


	public void setMcc(String mcc) {
		this.mcc = mcc;
	}


	public Map getMerchantCategoryList() {
		return merchantCategoryList;
	}


	public void setMerchantCategoryList(Map merchantCategoryList) {
		this.merchantCategoryList = merchantCategoryList;
	}


	public String getMonthlyLimitAmount() {
		return monthlyLimitAmount;
	}


	public void setMonthlyLimitAmount(String monthlyLimitAmount) {
		this.monthlyLimitAmount = monthlyLimitAmount;
	}


	public String getMonthlyLimitCount() {
		return monthlyLimitCount;
	}


	public void setMonthlyLimitCount(String monthlyLimitCount) {
		this.monthlyLimitCount = monthlyLimitCount;
	}


	public String getRuleId() {
		return ruleId;
	}


	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}


	public String getTranxCode() {
		return tranxCode;
	}


	public void setTranxCode(String tranxCode) {
		this.tranxCode = tranxCode;
	}


	public Map getTranxCodeList() {
		return tranxCodeList;
	}


	public void setTranxCodeList(Map tranxCodeList) {
		this.tranxCodeList = tranxCodeList;
	}


}




