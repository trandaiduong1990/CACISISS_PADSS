package org.transinfo.cacis.dto.accounting;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StatementPayDetails implements Serializable {
	
	private String pdetailId;
	private String paymentId;
	private String statId;
	private String statGen;
	
	public String getPdetailId() {
		return pdetailId;
	}
	public void setPdetailId(String pdetailId) {
		this.pdetailId = pdetailId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
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
