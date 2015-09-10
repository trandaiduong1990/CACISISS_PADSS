package org.transinfo.cacis.dto.useraccess;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorActionForm;

public class CodeMasterDto extends ValidatorActionForm {

	private String codeDesc;

	public CodeMasterDto() {
	}

	public static class Id implements Serializable {

		private String groupId;
		private String codeId;

		public Id() {
		}

		public String getGroupId() {
			return groupId;
		}

		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}

		public String getCodeId() {
			return codeId;
		}

		public void setCodeId(String codeId) {
			this.codeId = codeId;
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

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

}
