package org.transinfo.cacis.dto.settings;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("serial")
public class CardProductRulesDto implements Serializable {

	private String cardProductId;
	private String ca3rdInterface;
	private String caDelivery;
	private String caTerminal;
	private String caEmbPINGen;
	private int caDaDays;
	private String cdBranch;
	private String cdMail;
	private String cbLost;
	private String cbStolen;
	private String crOcUsage;
	private int crNodays;
	private String crOcRep;
	private String crOcNc;
	private String pdDelivery;
	private String pdTerminal;
	private String pdBranch;
	private int pdDaDays;
	private String fbDom;
	private String fbIntl;
	private int fbNoTranx;
	private int fbTranxMaxAmt;
	private String fbSms;
	private int olMinAmt;
	private String olMccList;
	private Date updatedDate = new Date();
	private String userId;

	private Map selectedListSet;

	public CardProductRulesDto() {
		// TODO Auto-generated constructor stub
	}

	public String getCardProductId() {
		return cardProductId;
	}

	public void setCardProductId(String cardProductId) {
		this.cardProductId = cardProductId;
	}

	public String getCa3rdInterface() {
		return ca3rdInterface;
	}

	public void setCa3rdInterface(String ca3rdInterface) {
		this.ca3rdInterface = ca3rdInterface;
	}

	public String getCaDelivery() {
		return caDelivery;
	}

	public void setCaDelivery(String caDelivery) {
		this.caDelivery = caDelivery;
	}

	public String getCaTerminal() {
		return caTerminal;
	}

	public void setCaTerminal(String caTerminal) {
		this.caTerminal = caTerminal;
	}

	public String getCaEmbPINGen() {
		return caEmbPINGen;
	}

	public void setCaEmbPINGen(String caEmbPINGen) {
		this.caEmbPINGen = caEmbPINGen;
	}

	public int getCaDaDays() {
		return caDaDays;
	}

	public void setCaDaDays(int caDaDays) {
		this.caDaDays = caDaDays;
	}

	public String getCdBranch() {
		return cdBranch;
	}

	public void setCdBranch(String cdBranch) {
		this.cdBranch = cdBranch;
	}

	public String getCdMail() {
		return cdMail;
	}

	public void setCdMail(String cdMail) {
		this.cdMail = cdMail;
	}

	public String getCbLost() {
		return cbLost;
	}

	public void setCbLost(String cbLost) {
		this.cbLost = cbLost;
	}

	public String getCbStolen() {
		return cbStolen;
	}

	public void setCbStolen(String cbStolen) {
		this.cbStolen = cbStolen;
	}

	public String getCrOcUsage() {
		return crOcUsage;
	}

	public void setCrOcUsage(String crOcUsage) {
		this.crOcUsage = crOcUsage;
	}

	public int getCrNodays() {
		return crNodays;
	}

	public void setCrNodays(int crNodays) {
		this.crNodays = crNodays;
	}

	public String getCrOcRep() {
		return crOcRep;
	}

	public void setCrOcRep(String crOcRep) {
		this.crOcRep = crOcRep;
	}

	public String getPdDelivery() {
		return pdDelivery;
	}

	public void setPdDelivery(String pdDelivery) {
		this.pdDelivery = pdDelivery;
	}

	public String getPdTerminal() {
		return pdTerminal;
	}

	public void setPdTerminal(String pdTerminal) {
		this.pdTerminal = pdTerminal;
	}

	public String getPdBranch() {
		return pdBranch;
	}

	public void setPdBranch(String pdBranch) {
		this.pdBranch = pdBranch;
	}

	public int getPdDaDays() {
		return pdDaDays;
	}

	public void setPdDaDays(int pdDaDays) {
		this.pdDaDays = pdDaDays;
	}

	public String getFbDom() {
		return fbDom;
	}

	public void setFbDom(String fbDom) {
		this.fbDom = fbDom;
	}

	public String getFbIntl() {
		return fbIntl;
	}

	public void setFbIntl(String fbIntl) {
		this.fbIntl = fbIntl;
	}

	public int getFbNoTranx() {
		return fbNoTranx;
	}

	public void setFbNoTranx(int fbNoTranx) {
		this.fbNoTranx = fbNoTranx;
	}

	public int getFbTranxMaxAmt() {
		return fbTranxMaxAmt;
	}

	public void setFbTranxMaxAmt(int fbTranxMaxAmt) {
		this.fbTranxMaxAmt = fbTranxMaxAmt;
	}

	public String getFbSms() {
		return fbSms;
	}

	public void setFbSms(String fbSms) {
		this.fbSms = fbSms;
	}

	public int getOlMinAmt() {
		return olMinAmt;
	}

	public void setOlMinAmt(int olMinAmt) {
		this.olMinAmt = olMinAmt;
	}

	public String getOlMccList() {
		return olMccList;
	}

	public void setOlMccList(String olMccList) {
		this.olMccList = olMccList;
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

	public String getCrOcNc() {
		return crOcNc;
	}

	public void setCrOcNc(String crOcNc) {
		this.crOcNc = crOcNc;
	}

	public Map getSelectedListSet() {
		return selectedListSet;
	}

	public void setSelectedListSet(Map selectedListSet) {
		this.selectedListSet = selectedListSet;
	}

}
