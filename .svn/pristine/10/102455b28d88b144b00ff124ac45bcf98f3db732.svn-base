package org.transinfo.cacis.formbean.settings;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductLimitDAO;
import org.transinfo.cacis.dto.settings.CardProductDto;

@SuppressWarnings("serial")
public class CardProductLimitSetupForm extends ValidatorForm {

	private String cardProductId;
	private String issuerId;
	private String minSalary;
	private String maxCardLimitPerSalary;
	private String maxCashLimiPerCrl;
	private String maxSuppCreditLimit;
	private String maxSuppCashLimit;
	private String tempCreditLimitRaise;
	private String tempCashLimitRaise;
	private String maxCreditAmtPerTrnx;
	private String maxCashLimitPerTrnx;
	private String maxCreditLimitPerDay;
	private String maxCreditTrnxPerDay;
	private String maxCashLimitPerDay;
	private String maxCashTrnxPerDay;

	private String eComAmt;
	
	private CardProductDto cardProduct = new CardProductDto();

	@SuppressWarnings("unchecked")
	private Map cardProductList;
	
	private String[] selectedPromotions = {};

	@SuppressWarnings("unchecked")
	private ArrayList promotionsList;
	
	private String maxCardLimitPerSalaryInt;
	private String maxCashLimiPerCrlInt;
	private String maxSuppCreditLimitInt;
	private String maxSuppCashLimitInt;
	private String tempCreditLimitRaiseInt;
	private String tempCashLimitRaiseInt;
	private String maxCreditAmtPerTrnxInt;
	private String maxCashLimitPerTrnxInt;
	private String maxCreditLimitPerDayInt;
	private String maxCreditTrnxPerDayInt;
	private String maxCashLimitPerDayInt;
	private String maxCashTrnxPerDayInt;
	private String eComAmtInt;

	private String eComAmtPerDay;
	private String eComAmtPerDayInt;

	private String eComTranxPerDay;
	private String eComTranxPerDayInt;

	public CardProductLimitSetupForm() {
		// getPreListData();
	}

	public void getPreListData() {

		try {

			CardProductLimitDAO objDAO = DAOFactory.getInstance()
					.getCardProductLimitDAO();

			if (cardProductList == null) {
				cardProductList = objDAO.cardProductListData(issuerId);
			}
			if(promotionsList==null)  {
     			promotionsList=  objDAO.cardPromotionList();
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting  PreListData in CardProductSetup:"
							+ e.getMessage());
		}

	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	@SuppressWarnings("unchecked")
	public Map getCardProductList() {
		return cardProductList;
	}

	@SuppressWarnings("unchecked")
	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public String getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	public String getMaxCardLimitPerSalary() {
		return maxCardLimitPerSalary;
	}

	public void setMaxCardLimitPerSalary(String maxCardLimitPerSalary) {
		this.maxCardLimitPerSalary = maxCardLimitPerSalary;
	}

	public String getMaxCashLimiPerCrl() {
		return maxCashLimiPerCrl;
	}

	public void setMaxCashLimiPerCrl(String maxCashLimiPerCrl) {
		this.maxCashLimiPerCrl = maxCashLimiPerCrl;
	}

	public String getMaxSuppCreditLimit() {
		return maxSuppCreditLimit;
	}

	public void setMaxSuppCreditLimit(String maxSuppCreditLimit) {
		this.maxSuppCreditLimit = maxSuppCreditLimit;
	}

	public String getMaxSuppCashLimit() {
		return maxSuppCashLimit;
	}

	public void setMaxSuppCashLimit(String maxSuppCashLimit) {
		this.maxSuppCashLimit = maxSuppCashLimit;
	}

	public String getTempCreditLimitRaise() {
		return tempCreditLimitRaise;
	}

	public void setTempCreditLimitRaise(String tempCreditLimitRaise) {
		this.tempCreditLimitRaise = tempCreditLimitRaise;
	}

	public String getTempCashLimitRaise() {
		return tempCashLimitRaise;
	}

	public void setTempCashLimitRaise(String tempCashLimitRaise) {
		this.tempCashLimitRaise = tempCashLimitRaise;
	}

	public String getMaxCreditAmtPerTrnx() {
		return maxCreditAmtPerTrnx;
	}

	public void setMaxCreditAmtPerTrnx(String maxCreditAmtPerTrnx) {
		this.maxCreditAmtPerTrnx = maxCreditAmtPerTrnx;
	}

	public String getMaxCashLimitPerTrnx() {
		return maxCashLimitPerTrnx;
	}

	public void setMaxCashLimitPerTrnx(String maxCashLimitPerTrnx) {
		this.maxCashLimitPerTrnx = maxCashLimitPerTrnx;
	}

	public String getMaxCreditLimitPerDay() {
		return maxCreditLimitPerDay;
	}

	public void setMaxCreditLimitPerDay(String maxCreditLimitPerDay) {
		this.maxCreditLimitPerDay = maxCreditLimitPerDay;
	}

	public String getMaxCreditTrnxPerDay() {
		return maxCreditTrnxPerDay;
	}

	public void setMaxCreditTrnxPerDay(String maxCreditTrnxPerDay) {
		this.maxCreditTrnxPerDay = maxCreditTrnxPerDay;
	}

	public String getMaxCashLimitPerDay() {
		return maxCashLimitPerDay;
	}

	public void setMaxCashLimitPerDay(String maxCashLimitPerDay) {
		this.maxCashLimitPerDay = maxCashLimitPerDay;
	}

	public String getMaxCashTrnxPerDay() {
		return maxCashTrnxPerDay;
	}

	public void setMaxCashTrnxPerDay(String maxCashTrnxPerDay) {
		this.maxCashTrnxPerDay = maxCashTrnxPerDay;
	}

	public CardProductDto getCardProduct() {
		return cardProduct;
	}

	public void setCardProduct(CardProductDto cardProduct) {
		this.cardProduct = cardProduct;
	}

	public String[] getSelectedPromotions() {
		return selectedPromotions;
	}

	public void setSelectedPromotions(String[] selectedPromotions) {
		this.selectedPromotions = selectedPromotions;
	}

	public ArrayList getPromotionsList() {
		return promotionsList;
	}

	public void setPromotionsList(ArrayList promotionsList) {
		this.promotionsList = promotionsList;
	}

	public String geteComAmt() {
		return eComAmt;
	}

	public void seteComAmt(String eComAmt) {
		this.eComAmt = eComAmt;
	}

	public String getMaxCardLimitPerSalaryInt() {
		return maxCardLimitPerSalaryInt;
	}

	public void setMaxCardLimitPerSalaryInt(String maxCardLimitPerSalaryInt) {
		this.maxCardLimitPerSalaryInt = maxCardLimitPerSalaryInt;
	}

	public String getMaxCashLimiPerCrlInt() {
		return maxCashLimiPerCrlInt;
	}

	public void setMaxCashLimiPerCrlInt(String maxCashLimiPerCrlInt) {
		this.maxCashLimiPerCrlInt = maxCashLimiPerCrlInt;
	}

	public String getMaxSuppCreditLimitInt() {
		return maxSuppCreditLimitInt;
	}

	public void setMaxSuppCreditLimitInt(String maxSuppCreditLimitInt) {
		this.maxSuppCreditLimitInt = maxSuppCreditLimitInt;
	}

	public String getMaxSuppCashLimitInt() {
		return maxSuppCashLimitInt;
	}

	public void setMaxSuppCashLimitInt(String maxSuppCashLimitInt) {
		this.maxSuppCashLimitInt = maxSuppCashLimitInt;
	}

	public String getTempCreditLimitRaiseInt() {
		return tempCreditLimitRaiseInt;
	}

	public void setTempCreditLimitRaiseInt(String tempCreditLimitRaiseInt) {
		this.tempCreditLimitRaiseInt = tempCreditLimitRaiseInt;
	}

	public String getTempCashLimitRaiseInt() {
		return tempCashLimitRaiseInt;
	}

	public void setTempCashLimitRaiseInt(String tempCashLimitRaiseInt) {
		this.tempCashLimitRaiseInt = tempCashLimitRaiseInt;
	}

	public String getMaxCreditAmtPerTrnxInt() {
		return maxCreditAmtPerTrnxInt;
	}

	public void setMaxCreditAmtPerTrnxInt(String maxCreditAmtPerTrnxInt) {
		this.maxCreditAmtPerTrnxInt = maxCreditAmtPerTrnxInt;
	}

	public String getMaxCashLimitPerTrnxInt() {
		return maxCashLimitPerTrnxInt;
	}

	public void setMaxCashLimitPerTrnxInt(String maxCashLimitPerTrnxInt) {
		this.maxCashLimitPerTrnxInt = maxCashLimitPerTrnxInt;
	}

	public String getMaxCreditLimitPerDayInt() {
		return maxCreditLimitPerDayInt;
	}

	public void setMaxCreditLimitPerDayInt(String maxCreditLimitPerDayInt) {
		this.maxCreditLimitPerDayInt = maxCreditLimitPerDayInt;
	}

	public String getMaxCreditTrnxPerDayInt() {
		return maxCreditTrnxPerDayInt;
	}

	public void setMaxCreditTrnxPerDayInt(String maxCreditTrnxPerDayInt) {
		this.maxCreditTrnxPerDayInt = maxCreditTrnxPerDayInt;
	}

	public String getMaxCashLimitPerDayInt() {
		return maxCashLimitPerDayInt;
	}

	public void setMaxCashLimitPerDayInt(String maxCashLimitPerDayInt) {
		this.maxCashLimitPerDayInt = maxCashLimitPerDayInt;
	}

	public String getMaxCashTrnxPerDayInt() {
		return maxCashTrnxPerDayInt;
	}

	public void setMaxCashTrnxPerDayInt(String maxCashTrnxPerDayInt) {
		this.maxCashTrnxPerDayInt = maxCashTrnxPerDayInt;
	}

	public String geteComAmtInt() {
		return eComAmtInt;
	}

	public void seteComAmtInt(String eComAmtInt) {
		this.eComAmtInt = eComAmtInt;
	}

	public String geteComAmtPerDay() {
		return eComAmtPerDay;
	}

	public void seteComAmtPerDay(String eComAmtPerDay) {
		this.eComAmtPerDay = eComAmtPerDay;
	}

	public String geteComAmtPerDayInt() {
		return eComAmtPerDayInt;
	}

	public void seteComAmtPerDayInt(String eComAmtPerDayInt) {
		this.eComAmtPerDayInt = eComAmtPerDayInt;
	}

	public String geteComTranxPerDay() {
		return eComTranxPerDay;
	}

	public void seteComTranxPerDay(String eComTranxPerDay) {
		this.eComTranxPerDay = eComTranxPerDay;
	}

	public String geteComTranxPerDayInt() {
		return eComTranxPerDayInt;
	}

	public void seteComTranxPerDayInt(String eComTranxPerDayInt) {
		this.eComTranxPerDayInt = eComTranxPerDayInt;
	}
}
