package org.transinfo.cacis.formbean.cardproduction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CreditScoringDAO;

@SuppressWarnings("serial")
public class CreditScoringSetupForm extends ValidatorForm {
	
	private String isChecked; 
	private String scoreId;
	private String issuerId;
	private String scoreName;
	private String fieldColumn;
	private String nFields;
	private String rangeAvl;
	private String minScore;
	private String maxScore;
	
	private String c1;
	private String c1s1;
	
	private String c2;
	private String c2s2;
	
	private String c3;
	private String c3s3;
	
	private String c4;
	private String c4s4;
	
	private String c5;
	private String c5s5;
	
	private String c6;
	private String c6s6;
	
	private String c7;
	private String c7s7;
	
	private String c8;
	private String c8s8;
	
	private String c9;
	private String c9s9;
	
	private String c10;
	private String c10s10;
	
	private String status;
	private String lastUpdatedDate;
	private String lastUpdatedBy;
	private String creditName;
	
	private List creditScoringList;
	
	private Map scoreNameList;
	
	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getScoreName() {
		return scoreName;
	}

	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}

	public String getFieldColumn() {
		return fieldColumn;
	}

	public void setFieldColumn(String fieldColumn) {
		this.fieldColumn = fieldColumn;
	}

	public String getnFields() {
		return nFields;
	}

	public void setnFields(String nFields) {
		this.nFields = nFields;
	}

	public String getRangeAvl() {
		return rangeAvl;
	}

	public void setRangeAvl(String rangeAvl) {
		this.rangeAvl = rangeAvl;
	}

	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}

	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC1s1() {
		return c1s1;
	}

	public void setC1s1(String c1s1) {
		this.c1s1 = c1s1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC2s2() {
		return c2s2;
	}

	public void setC2s2(String c2s2) {
		this.c2s2 = c2s2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC3s3() {
		return c3s3;
	}

	public void setC3s3(String c3s3) {
		this.c3s3 = c3s3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC4s4() {
		return c4s4;
	}

	public void setC4s4(String c4s4) {
		this.c4s4 = c4s4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getC5s5() {
		return c5s5;
	}

	public void setC5s5(String c5s5) {
		this.c5s5 = c5s5;
	}

	public String getC6() {
		return c6;
	}

	public void setC6(String c6) {
		this.c6 = c6;
	}

	public String getC6s6() {
		return c6s6;
	}

	public void setC6s6(String c6s6) {
		this.c6s6 = c6s6;
	}

	public String getC7() {
		return c7;
	}

	public void setC7(String c7) {
		this.c7 = c7;
	}

	public String getC7s7() {
		return c7s7;
	}

	public void setC7s7(String c7s7) {
		this.c7s7 = c7s7;
	}

	public String getC8() {
		return c8;
	}

	public void setC8(String c8) {
		this.c8 = c8;
	}

	public String getC8s8() {
		return c8s8;
	}

	public void setC8s8(String c8s8) {
		this.c8s8 = c8s8;
	}

	public String getC9() {
		return c9;
	}

	public void setC9(String c9) {
		this.c9 = c9;
	}

	public String getC9s9() {
		return c9s9;
	}

	public void setC9s9(String c9s9) {
		this.c9s9 = c9s9;
	}

	public String getC10() {
		return c10;
	}

	public void setC10(String c10) {
		this.c10 = c10;
	}

	public String getC10s10() {
		return c10s10;
	}

	public void setC10s10(String c10s10) {
		this.c10s10 = c10s10;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public List getCreditScoringList() {
		return creditScoringList;
	}
	
	public CreditScoringSetupForm getCreditScoringList(int index) {
		
		if(this.creditScoringList == null) {
			this.creditScoringList = new ArrayList();
		}
		
		while(index >= this.creditScoringList.size()) {
			this.creditScoringList.add(new CreditScoringSetupForm());
		}
		
		return (CreditScoringSetupForm)creditScoringList.get(index);
	}

	public void setCreditScoringList(List creditScoringList) {
		this.creditScoringList = creditScoringList;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public Map getScoreNameList() {
		return scoreNameList;
	}

	public void setScoreNameList(Map scoreNameList) {
		this.scoreNameList = scoreNameList;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public void getPreListData() {

		try{
			CreditScoringDAO objDAO = DAOFactory.getInstance().getCreditScoringDAO();
	 		
			if(scoreNameList == null)  {
				scoreNameList = objDAO.getScoreNameList();	
		    }

		} catch(Exception e) {
		      System.out.println("Error while getting PreList in CreditScoringSearchForm:" + e.getMessage());
		}
	}
	
}
