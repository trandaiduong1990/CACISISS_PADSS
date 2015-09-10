package org.transinfo.cacis.dto.accounting;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CustomerStatement implements Serializable {
	
	private String statId;
	private Long cardNo;
	private String accId;
	private String cycleNo;
	private Date genDate;
	private String prevStatId;
	private Double prevStatAmt;
	private Double prevStatOutStandAmt;
	private Double prevPayAmt;
	private Double statAmt;
	private Double statMinAmt;
	private Date statDueDate;
	private Double statFeeAmt;
	private Double statIntAmt;
	private Date statCreatedDate;
	private Double outStandStatAmt;
	private Double outStandPurchaseAmt;
	private Double outStandCashAmt;
	private String statGen;
	
	public String getStatId() {
		return statId;
	}
	public void setStatId(String statId) {
		this.statId = statId;
	}
	public Long getCardNo() {
		return cardNo;
	}
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public String getCycleNo() {
		return cycleNo;
	}
	public void setCycleNo(String cycleNo) {
		this.cycleNo = cycleNo;
	}
	public Date getGenDate() {
		return genDate;
	}
	public void setGenDate(Date genDate) {
		this.genDate = genDate;
	}
	public String getPrevStatId() {
		return prevStatId;
	}
	public void setPrevStatId(String prevStatId) {
		this.prevStatId = prevStatId;
	}
	public Double getPrevStatAmt() {
		return prevStatAmt;
	}
	public void setPrevStatAmt(Double prevStatAmt) {
		this.prevStatAmt = prevStatAmt;
	}
	public Double getPrevStatOutStandAmt() {
		return prevStatOutStandAmt;
	}
	public void setPrevStatOutStandAmt(Double prevStatOutStandAmt) {
		this.prevStatOutStandAmt = prevStatOutStandAmt;
	}
	public Double getPrevPayAmt() {
		return prevPayAmt;
	}
	public void setPrevPayAmt(Double prevPayAmt) {
		this.prevPayAmt = prevPayAmt;
	}
	public Double getStatAmt() {
		return statAmt;
	}
	public void setStatAmt(Double statAmt) {
		this.statAmt = statAmt;
	}
	public Double getStatMinAmt() {
		return statMinAmt;
	}
	public void setStatMinAmt(Double statMinAmt) {
		this.statMinAmt = statMinAmt;
	}
	public Date getStatDueDate() {
		return statDueDate;
	}
	public void setStatDueDate(Date statDueDate) {
		this.statDueDate = statDueDate;
	}
	public Double getStatFeeAmt() {
		return statFeeAmt;
	}
	public void setStatFeeAmt(Double statFeeAmt) {
		this.statFeeAmt = statFeeAmt;
	}
	public Double getStatIntAmt() {
		return statIntAmt;
	}
	public void setStatIntAmt(Double statIntAmt) {
		this.statIntAmt = statIntAmt;
	}
	public Date getStatCreatedDate() {
		return statCreatedDate;
	}
	public void setStatCreatedDate(Date statCreatedDate) {
		this.statCreatedDate = statCreatedDate;
	}
	public Double getOutStandStatAmt() {
		return outStandStatAmt;
	}
	public void setOutStandStatAmt(Double outStandStatAmt) {
		this.outStandStatAmt = outStandStatAmt;
	}
	public Double getOutStandPurchaseAmt() {
		return outStandPurchaseAmt;
	}
	public void setOutStandPurchaseAmt(Double outStandPurchaseAmt) {
		this.outStandPurchaseAmt = outStandPurchaseAmt;
	}
	public Double getOutStandCashAmt() {
		return outStandCashAmt;
	}
	public void setOutStandCashAmt(Double outStandCashAmt) {
		this.outStandCashAmt = outStandCashAmt;
	}
	public String getStatGen() {
		return statGen;
	}
	public void setStatGen(String statGen) {
		this.statGen = statGen;
	}

}
