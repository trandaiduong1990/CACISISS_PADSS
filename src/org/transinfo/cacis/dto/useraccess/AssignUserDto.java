package org.transinfo.cacis.dto.useraccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto.Id;
import org.transinfo.cacis.formbean.common.DateForm;

public class AssignUserDto extends ValidatorActionForm{

	private String assignId;
	private String roleId;
	private String userId;
	private String issuerId;
	private int lower;
	private int upper;
	private String status;
	private Date lastUpdatedDate;
	private String lastUpdatedBy;
	
	public String getAssignId() {
		return assignId;
	}

	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public int getLower() {
		return lower;
	}

	public void setLower(int lower) {
		this.lower = lower;
	}

	public int getUpper() {
		return upper;
	}

	public void setUpper(int upper) {
		this.upper = upper;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}
