package org.transinfo.cacis.formbean.useraccess;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.AssignUserDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.UserSetupDAO;

@SuppressWarnings({ "unchecked", "serial" })
public class AssignUserForm extends ValidatorForm {
	private String assignId;
	private String issuerId;
	private String lower;
	private String roleId;
	private String upper;
	private String userId;

	private Map roleList;
	private Map userList;

	public AssignUserForm() {
		System.out.println("In Costructor");
	}

	public String getAssignId() {
		return assignId;
	}

	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getLower() {
		return lower;
	}

	public void setLower(String lower) {
		this.lower = lower;
	}

	public String getUpper() {
		return upper;
	}

	public void setUpper(String upper) {
		this.upper = upper;
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

	public Map getRoleList() {
		return roleList;
	}

	public void setRoleList(Map roleList) {
		this.roleList = roleList;
	}

	public Map getUserList() {
		return userList;
	}

	public void setUserList(Map userList) {
		this.userList = userList;
	}

	public void getPreListData() {
		try {

			AssignUserDAO objAssignUserDAO = DAOFactory.getInstance()
					.getAssignUserDAO();

			roleList = objAssignUserDAO.getRoleId("ASSIGNROLE");

			userList = objAssignUserDAO.getUserList(issuerId);

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:" + e);
		}
	}

}
