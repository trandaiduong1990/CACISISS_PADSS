package org.transinfo.cacis.formbean.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRulesDAO;

public class CardProductRulesSetupForm extends ValidatorForm {
	private String cardProductId;
	private String ca3rdInterface = "N";
	private String caDelivery = "N";
	private String caTerminal = "N";
	private String caEmbPINGen = "N";
	private String caDaDays;
	private String cdBranch = "N";
	private String cdMail = "N";
	private String cbLost = "N";
	private String cbStolen = "N";
	private String crOcUsage = "N";
	private String crOcNc = "N";
	private String crNodays;
	private String crOcRep = "N";
	private String pdDelivery = "N";
	private String pdTerminal = "N";
	private String pdBranch = "N";
	private String pdDaDays;
	private String fbDom = "N";
	private String fbIntl = "N";
	private String fbNoTranx;
	private String fbTranxMaxAmt;
	private String fbSms = "N";
	private String olMinAmt;
	private String issuerId;
	private Map cardProductList;
	private String[] selMccList;
	private Map selectedListSet = new HashMap();
	private Map mccMasterList;

	public void getPreListData() {

		try {
			CardProductRulesDAO objDAO = DAOFactory.getInstance()
					.getCardProductRulesDAO();
			if (cardProductList == null) {
				cardProductList = objDAO.cardProductListData(issuerId);
			} else {
				System.out.println("cardProductList existed");
			}

			if (mccMasterList == null) {
				mccMasterList = objDAO.getMccMasterList(cardProductId);
			} else {
				System.out.println("mccMasterList existed");
			}

		} catch (Exception e) {
			System.out
					.println("Error while getPreListData in ProductTranxSetupForm "
							+ e.getMessage());
		}
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

	public String getCaDaDays() {
		return caDaDays;
	}

	public void setCaDaDays(String caDaDays) {
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

	public String getCrNodays() {
		return crNodays;
	}

	public void setCrNodays(String crNodays) {
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

	public String getPdDaDays() {
		return pdDaDays;
	}

	public void setPdDaDays(String pdDaDays) {
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

	public String getFbNoTranx() {
		return fbNoTranx;
	}

	public void setFbNoTranx(String fbNoTranx) {
		this.fbNoTranx = fbNoTranx;
	}

	public String getFbTranxMaxAmt() {
		return fbTranxMaxAmt;
	}

	public void setFbTranxMaxAmt(String fbTranxMaxAmt) {
		this.fbTranxMaxAmt = fbTranxMaxAmt;
	}

	public String getFbSms() {
		return fbSms;
	}

	public void setFbSms(String fbSms) {
		this.fbSms = fbSms;
	}

	public String getOlMinAmt() {
		return olMinAmt;
	}

	public void setOlMinAmt(String olMinAmt) {
		this.olMinAmt = olMinAmt;
	}

	public Map getCardProductList() {
		return cardProductList;
	}

	public void setCardProductList(Map cardProductList) {
		this.cardProductList = cardProductList;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String[] getSelMccList() {
		return selMccList;
	}

	public void setSelMccList(String[] selMccList) {
		this.selMccList = selMccList;
	}

	public Map getSelectedListSet() {
		return selectedListSet;
	}

	public void setSelectedListSet(Map selectedListSet) {
		this.selectedListSet = selectedListSet;
	}

	public Map getMccMasterList() {
		return mccMasterList;
	}

	public void setMccMasterList(Map mccMasterList) {
		this.mccMasterList = mccMasterList;
	}

	public String getCrOcNc() {
		return crOcNc;
	}

	public void setCrOcNc(String crOcNc) {
		this.crOcNc = crOcNc;
	}

}
