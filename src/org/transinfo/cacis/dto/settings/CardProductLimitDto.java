package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked","unused"})
public class CardProductLimitDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cardProductId;
	private CardProductDto cardProduct;
	private float minSalary;
	private float maxCardLimitPerSalary;
	private float maxCashLimiPerCrl;
	private int maxSuppCreditLimit;
	private int maxSuppCashLimit;
	private int tempCreditLimitRaise;
	private int tempCashLimitRaise;
	private Date updatedDate = new Date();
	private String userId;
	private float maxCreditAmtPerTrnx;
	private float maxCashLimitPerTrnx;
	private float maxCreditLimitPerDay;
	private int maxCreditTrnxPerDay;
	private float maxCashLimitPerDay;
	private int maxCashTrnxPerDay;

	private float eComAmt;

	private Map cardpromotions = new HashMap();
	private String[] selectedPromotions = {};

	private float maxCardLimitPerSalaryInt;
	private float maxCashLimiPerCrlInt;
	private int maxSuppCreditLimitInt;
	private int maxSuppCashLimitInt;
	private int tempCreditLimitRaiseInt;
	private int tempCashLimitRaiseInt;
	private float maxCreditAmtPerTrnxInt;
	private float maxCashLimitPerTrnxInt;
	private float maxCreditLimitPerDayInt;
	private int maxCreditTrnxPerDayInt;
	private float maxCashLimitPerDayInt;
	private int maxCashTrnxPerDayInt;
	private float eComAmtInt;

	private float eComAmtPerDay;
	private float eComAmtPerDayInt;

	private int eComTranxPerDay;
	private int eComTranxPerDayInt;

	public CardProductLimitDto() {
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public CardProductDto getCardProduct() {
		return cardProduct;
	}

	public void setCardProduct(CardProductDto cardProduct) {
		this.cardProduct = cardProduct;
	}

	public float getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(float minSalary) {
		this.minSalary = minSalary;
	}

	public float getMaxCardLimitPerSalary() {
		return maxCardLimitPerSalary;
	}

	public void setMaxCardLimitPerSalary(float maxCardLimitPerSalary) {
		this.maxCardLimitPerSalary = maxCardLimitPerSalary;
	}

	public float getMaxCashLimiPerCrl() {
		return maxCashLimiPerCrl;
	}

	public void setMaxCashLimiPerCrl(float maxCashLimiPerCrl) {
		this.maxCashLimiPerCrl = maxCashLimiPerCrl;
	}

	public int getMaxSuppCreditLimit() {
		return maxSuppCreditLimit;
	}

	public void setMaxSuppCreditLimit(int maxSuppCreditLimit) {
		this.maxSuppCreditLimit = maxSuppCreditLimit;
	}

	public int getMaxSuppCashLimit() {
		return maxSuppCashLimit;
	}

	public void setMaxSuppCashLimit(int maxSuppCashLimit) {
		this.maxSuppCashLimit = maxSuppCashLimit;
	}

	public int getTempCreditLimitRaise() {
		return tempCreditLimitRaise;
	}

	public void setTempCreditLimitRaise(int tempCreditLimitRaise) {
		this.tempCreditLimitRaise = tempCreditLimitRaise;
	}

	public int getTempCashLimitRaise() {
		return tempCashLimitRaise;
	}

	public void setTempCashLimitRaise(int tempCashLimitRaise) {
		this.tempCashLimitRaise = tempCashLimitRaise;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public float getMaxCreditAmtPerTrnx() {
		return maxCreditAmtPerTrnx;
	}

	public void setMaxCreditAmtPerTrnx(float maxCreditAmtPerTrnx) {
		this.maxCreditAmtPerTrnx = maxCreditAmtPerTrnx;
	}

	public float getMaxCashLimitPerTrnx() {
		return maxCashLimitPerTrnx;
	}

	public void setMaxCashLimitPerTrnx(float maxCashLimitPerTrnx) {
		this.maxCashLimitPerTrnx = maxCashLimitPerTrnx;
	}

	public float getMaxCreditLimitPerDay() {
		return maxCreditLimitPerDay;
	}

	public void setMaxCreditLimitPerDay(float maxCreditLimitPerDay) {
		this.maxCreditLimitPerDay = maxCreditLimitPerDay;
	}

	public int getMaxCreditTrnxPerDay() {
		return maxCreditTrnxPerDay;
	}

	public void setMaxCreditTrnxPerDay(int maxCreditTrnxPerDay) {
		this.maxCreditTrnxPerDay = maxCreditTrnxPerDay;
	}

	public float getMaxCashLimitPerDay() {
		return maxCashLimitPerDay;
	}

	public void setMaxCashLimitPerDay(float maxCashLimitPerDay) {
		this.maxCashLimitPerDay = maxCashLimitPerDay;
	}

	public int getMaxCashTrnxPerDay() {
		return maxCashTrnxPerDay;
	}

	public void setMaxCashTrnxPerDay(int maxCashTrnxPerDay) {
		this.maxCashTrnxPerDay = maxCashTrnxPerDay;
	}

	public Map getCardpromotions() {
		return cardpromotions;
	}

	public void setCardpromotions(Map cardpromotions) {
		this.cardpromotions = cardpromotions;
	}

	public String[] getSelectedPromotions() {

		String parms[]= new String[getCardpromotions().size()];
		Set ss = getCardpromotions().keySet();
		Iterator it = ss.iterator();
		int parmsSize =0;

		while(it.hasNext()){
			String a = (String)it.next();
			parms[parmsSize]=a;
			System.out.println("the value is" +  parms[parmsSize] );
			parmsSize++;
		} 

		return this.selectedPromotions= parms;
	}

	public void setSelectedPromotions(String[] selectedPromotions) {
		this.selectedPromotions = selectedPromotions;

		for(int i=0; i<selectedPromotions.length;i++)
		{
			getCardpromotions().put(selectedPromotions[i],new CardProductPromotionDto(new Date(),new Date(),getUserId()));
		}
	}

	public float geteComAmt() {
		return eComAmt;
	}

	public void seteComAmt(float eComAmt) {
		this.eComAmt = eComAmt;
	}

	public float getMaxCardLimitPerSalaryInt() {
		return maxCardLimitPerSalaryInt;
	}

	public void setMaxCardLimitPerSalaryInt(float maxCardLimitPerSalaryInt) {
		this.maxCardLimitPerSalaryInt = maxCardLimitPerSalaryInt;
	}

	public float getMaxCashLimiPerCrlInt() {
		return maxCashLimiPerCrlInt;
	}

	public void setMaxCashLimiPerCrlInt(float maxCashLimiPerCrlInt) {
		this.maxCashLimiPerCrlInt = maxCashLimiPerCrlInt;
	}

	public int getMaxSuppCreditLimitInt() {
		return maxSuppCreditLimitInt;
	}

	public void setMaxSuppCreditLimitInt(int maxSuppCreditLimitInt) {
		this.maxSuppCreditLimitInt = maxSuppCreditLimitInt;
	}

	public int getMaxSuppCashLimitInt() {
		return maxSuppCashLimitInt;
	}

	public void setMaxSuppCashLimitInt(int maxSuppCashLimitInt) {
		this.maxSuppCashLimitInt = maxSuppCashLimitInt;
	}

	public int getTempCreditLimitRaiseInt() {
		return tempCreditLimitRaiseInt;
	}

	public void setTempCreditLimitRaiseInt(int tempCreditLimitRaiseInt) {
		this.tempCreditLimitRaiseInt = tempCreditLimitRaiseInt;
	}

	public int getTempCashLimitRaiseInt() {
		return tempCashLimitRaiseInt;
	}

	public void setTempCashLimitRaiseInt(int tempCashLimitRaiseInt) {
		this.tempCashLimitRaiseInt = tempCashLimitRaiseInt;
	}

	public float getMaxCreditAmtPerTrnxInt() {
		return maxCreditAmtPerTrnxInt;
	}

	public void setMaxCreditAmtPerTrnxInt(float maxCreditAmtPerTrnxInt) {
		this.maxCreditAmtPerTrnxInt = maxCreditAmtPerTrnxInt;
	}

	public float getMaxCashLimitPerTrnxInt() {
		return maxCashLimitPerTrnxInt;
	}

	public void setMaxCashLimitPerTrnxInt(float maxCashLimitPerTrnxInt) {
		this.maxCashLimitPerTrnxInt = maxCashLimitPerTrnxInt;
	}

	public float getMaxCreditLimitPerDayInt() {
		return maxCreditLimitPerDayInt;
	}

	public void setMaxCreditLimitPerDayInt(float maxCreditLimitPerDayInt) {
		this.maxCreditLimitPerDayInt = maxCreditLimitPerDayInt;
	}

	public int getMaxCreditTrnxPerDayInt() {
		return maxCreditTrnxPerDayInt;
	}

	public void setMaxCreditTrnxPerDayInt(int maxCreditTrnxPerDayInt) {
		this.maxCreditTrnxPerDayInt = maxCreditTrnxPerDayInt;
	}

	public float getMaxCashLimitPerDayInt() {
		return maxCashLimitPerDayInt;
	}

	public void setMaxCashLimitPerDayInt(float maxCashLimitPerDayInt) {
		this.maxCashLimitPerDayInt = maxCashLimitPerDayInt;
	}

	public int getMaxCashTrnxPerDayInt() {
		return maxCashTrnxPerDayInt;
	}

	public void setMaxCashTrnxPerDayInt(int maxCashTrnxPerDayInt) {
		this.maxCashTrnxPerDayInt = maxCashTrnxPerDayInt;
	}

	public float geteComAmtInt() {
		return eComAmtInt;
	}

	public void seteComAmtInt(float eComAmtInt) {
		this.eComAmtInt = eComAmtInt;
	}

	public float geteComAmtPerDay() {
		return eComAmtPerDay;
	}

	public void seteComAmtPerDay(float eComAmtPerDay) {
		this.eComAmtPerDay = eComAmtPerDay;
	}

	public float geteComAmtPerDayInt() {
		return eComAmtPerDayInt;
	}

	public void seteComAmtPerDayInt(float eComAmtPerDayInt) {
		this.eComAmtPerDayInt = eComAmtPerDayInt;
	}

	public int geteComTranxPerDay() {
		return eComTranxPerDay;
	}

	public void seteComTranxPerDay(int eComTranxPerDay) {
		this.eComTranxPerDay = eComTranxPerDay;
	}

	public int geteComTranxPerDayInt() {
		return eComTranxPerDayInt;
	}

	public void seteComTranxPerDayInt(int eComTranxPerDayInt) {
		this.eComTranxPerDayInt = eComTranxPerDayInt;
	}

}
