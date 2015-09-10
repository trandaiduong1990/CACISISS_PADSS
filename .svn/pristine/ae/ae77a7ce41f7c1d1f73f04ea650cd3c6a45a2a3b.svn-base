package org.transinfo.cacis.formbean.riskmanagement;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.CardholderLimitAdjustmentDAO;
import org.transinfo.cacis.formbean.common.DateForm;

public class CardholderLimitAdjustment extends ValidatorForm{

	private String temporaryLimitId;
	private String creditLimit;
	private String cashLimit;
	private String cardNumber;
	DateForm fromDateForm = new DateForm();
	DateForm toDateForm = new DateForm();
	private String amountPerTranx;
	private String dailyLimitCount;
	private String dailyLimitAmount;
	private String monthlyLimitCount;
	private String monthlyLimitAmount;
	private String currCode;
    private String issuerId;
    private String status;
	//DateForm lastUpdatedDateForm = new DateForm();
    //private String lastUpdatedBy;

    private Map yearList;
    private Map monthList;
    private Map dayList;
    private Map currList;


    public String getAmountPerTranx() {
		return amountPerTranx;
	}

	public void setAmountPerTranx(String amountPerTranx) {
		this.amountPerTranx = amountPerTranx;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public Map getDayList() {
		return dayList;
	}

	public void setDayList(Map dayList) {
		this.dayList = dayList;
	}

	public DateForm getFromDateForm() {
		return fromDateForm;
	}

	public void setFromDateForm(DateForm fromDate) {
		this.fromDateForm = fromDate;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	/*public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}

	public void setLastUpdatedDateForm(DateForm lastUpdatedDateForm) {
		this.lastUpdatedDateForm = lastUpdatedDateForm;
	}*/

	public Map getMonthList() {
		return monthList;
	}

	public void setMonthList(Map monthList) {
		this.monthList = monthList;
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

	public String getTemporaryLimitId() {
		return temporaryLimitId;
	}

	public void setTemporaryLimitId(String temporaryLimitId) {
		this.temporaryLimitId = temporaryLimitId;
	}

	public DateForm getToDateForm() {
		return toDateForm;
	}

	public void setToDateForm(DateForm toDate) {
		this.toDateForm = toDate;
	}

	public Map getYearList() {
		return yearList;
	}

	public void setYearList(Map yearList) {
		this.yearList = yearList;
	}

    public void getPreListData()
    {
        try{
            CardholderLimitAdjustmentDAO objCardholderLimitAdjustmentDAO = DAOFactory.getInstance().getCardholderLimitAdjustmentDAO();
            if(currList==null)  {
            	currList = objCardholderLimitAdjustmentDAO.currencyListData();
            }
            if(yearList==null)  {
                yearList = objCardholderLimitAdjustmentDAO.yearListData();
             }
             if(monthList==null)  {
                monthList = objCardholderLimitAdjustmentDAO.monthListData();
             }
             if(dayList==null)  {
                dayList = objCardholderLimitAdjustmentDAO.dateListData();
             }
        }catch(Exception e){
            System.out.println("Error while getting WithdrawalLimitRules formbean PreListData:"+e);
        }
    }

	public String getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(String cashLimit) {
		this.cashLimit = cashLimit;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}




