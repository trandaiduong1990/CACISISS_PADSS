package org.transinfo.cacis.formbean.riskmanagement;

import java.util.HashMap;

import org.apache.struts.validator.ValidatorActionForm;

public class WithdrawalLimitRulesSearch extends ValidatorActionForm{

	private String ruleId;
    private String tranxType;
    
    private HashMap tranxTypeList;

	public WithdrawalLimitRulesSearch(){}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getTranxType() {
		return tranxType;
	}

	public void setTranxType(String tranxType) {
		this.tranxType = tranxType;
	}

	public HashMap getTranxTypeList() {
		return tranxTypeList;
	}

	public void setTranxTypeList(HashMap tranxTypeList) {
		this.tranxTypeList = tranxTypeList;
	}
}
