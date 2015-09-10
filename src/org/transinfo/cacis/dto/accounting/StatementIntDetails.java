package org.transinfo.cacis.dto.accounting;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StatementIntDetails implements Serializable {
	
	private String idetailId;
	private String interestId;
	private String statId;
	private String statGen;
	
	public String getIdetailId() {
		return idetailId;
	}
	public void setIdetailId(String idetailId) {
		this.idetailId = idetailId;
	}
	public String getInterestId() {
		return interestId;
	}
	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}
	public String getStatId() {
		return statId;
	}
	public void setStatId(String statId) {
		this.statId = statId;
	}
	public String getStatGen() {
		return statGen;
	}
	public void setStatGen(String statGen) {
		this.statGen = statGen;
	}	

}
