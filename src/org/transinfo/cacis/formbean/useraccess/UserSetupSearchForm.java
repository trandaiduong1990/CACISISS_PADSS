package org.transinfo.cacis.formbean.useraccess;

import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.UserSetupDAO;

@SuppressWarnings({ "unchecked", "serial" })
public class UserSetupSearchForm extends ValidatorForm {

	private String issuerId;
	private String searchUserId;
	private String searchRoleId;
	private String searchStatus;
	private String userType;

	private Map roleList;
	private Map statusList;

	public UserSetupSearchForm() {
		System.out.println("In Costructor");
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getSearchUserId() {
		return searchUserId;
	}

	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	public String getSearchRoleId() {
		return searchRoleId;
	}

	public void setSearchRoleId(String searchRoleId) {
		this.searchRoleId = searchRoleId;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Map getRoleList() {
		return roleList;
	}

	public void setRoleList(Map roleList) {
		this.roleList = roleList;
	}

	public Map getStatusList() {
		return statusList;
	}

	public void setStatusList(Map statusList) {
		this.statusList = statusList;
	}

	public void getPreListData() {
		try {

			System.out
					.println("issuerId,userType" + issuerId + "  " + userType);
			UserSetupDAO objUserSetupDAO = DAOFactory.getInstance()
					.getUserSetupDAO();

			roleList = objUserSetupDAO.getRoleId(issuerId, userType);

			statusList = objUserSetupDAO.statusListData("USERSTATUS");

		} catch (Exception e) {
			System.out.println("Error while getting  PreListData:" + e);
		}
	}

}
