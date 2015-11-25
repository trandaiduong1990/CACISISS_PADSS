package org.transinfo.cacis.dto.useraccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.transinfo.cacis.formbean.common.DateForm;

public class UserLevelDto extends ValidatorActionForm{
	private String userType;
	private String userTypeDesc;
	private String levelId;
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserTypeDesc() {
		return userTypeDesc;
	}
	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	
	
}
