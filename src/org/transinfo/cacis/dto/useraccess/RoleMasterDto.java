package org.transinfo.cacis.dto.useraccess;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts.validator.ValidatorActionForm;

@SuppressWarnings("unchecked")
public class RoleMasterDto extends ValidatorActionForm {

	private String userType;
	private String roleDesc;
	private String status;
	private Date updatedDate = new Date();
	private String userId;

	private Map roleFunctionSet = new HashMap();
	private Map selectedListSet = new TreeMap();

	public RoleMasterDto() {
	}

	public static class Id implements Serializable {

		private String issuerId;
		private String roleId;

		public Id() {
		}

		public Id(String issuerId, String roleId) {
			this.issuerId = issuerId;
			this.roleId = roleId;
		}

		public String getIssuerId() {
			return issuerId;
		}

		public void setIssuerId(String issuerId) {
			this.issuerId = issuerId;
		}

		public String getRoleId() {
			return roleId;
		}

		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}
	}

	public Id id = new Id();

	/**
	 * @return Returns the id.
	 */
	public Id getId() {

		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Id id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map getRoleFunctionSet() {
		return roleFunctionSet;
	}

	public void setRoleFunctionSet(Map roleFunctionSet) {
		this.roleFunctionSet = roleFunctionSet;
	}

	public Map getSelectedListSet() {
		return selectedListSet;
	}

	public void setSelectedListSet(Map selectedListSet) {
		this.selectedListSet = selectedListSet;
		// System.out.println("In MAP SIze="+selectedListSet.size());
	}

}
