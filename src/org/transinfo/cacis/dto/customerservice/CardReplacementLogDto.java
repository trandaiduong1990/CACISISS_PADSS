package org.transinfo.cacis.dto.customerservice;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CardReplacementLogDto implements Serializable {
	
	private String replacementLogSerialNo;
	private String oldCardNo;
	private String newCardNo;
	private Date issueDate;
	private Date expireDate;
	private String nameOnCard;
	private Character flag;
	private Date updatedDate = new Date();
	private String lastUpdatedBy;
	
	public String getReplacementLogSerialNo() {
		return replacementLogSerialNo;
	}
	public void setReplacementLogSerialNo(String replacementLogSerialNo) {
		this.replacementLogSerialNo = replacementLogSerialNo;
	}
	public String getOldCardNo() {
		return oldCardNo;
	}
	public void setOldCardNo(String oldCardNo) {
		this.oldCardNo = oldCardNo;
	}
	public String getNewCardNo() {
		return newCardNo;
	}
	public void setNewCardNo(String newCardNo) {
		this.newCardNo = newCardNo;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public Character getFlag() {
		return flag;
	}
	public void setFlag(Character flag) {
		this.flag = flag;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}
