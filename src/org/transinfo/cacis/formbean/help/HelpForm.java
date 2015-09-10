package org.transinfo.cacis.formbean.help;

import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.formbean.common.DateForm;

public class HelpForm extends ValidatorForm {

	private String screenId;

	private String screenName;

	private String seqNo;

	private String fieldName;

	private String fieldDesc;

	DateForm lastUpdatedDateForm = new DateForm();

	private String lastUpdatedBy;

	public HelpForm() {
	}
	
	/*public HelpForm(String screenId, String fieldName) {
		this.id.screenId = screenId;
		this.id.fieldName = fieldName;
	}
	
	public static class Id implements Serializable {

		private String screenId;

		private String fieldName;

		public Id(String screenId, String fieldName) {
			this.screenId = screenId;
			this.fieldName = fieldName;
		}

		public Id() {
		}

		public String getScreenId() {
			return screenId;
		}

		public void setScreenId(String screenId) {
			this.screenId = screenId;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public int hashCode() {
			return screenId.hashCode() + fieldName.hashCode();
		}

		public boolean equals(Object other) {
			if (other instanceof Id) {
				Id that = (Id) other;
				return that.screenId.equals(this.screenId)
						&& that.fieldName.equals(this.fieldName);
			} else {
				return false;
			}
		}

	}

	public Id id = new Id();

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}*/
	
	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public DateForm getLastUpdatedDateForm() {
		return lastUpdatedDateForm;
	}

	public void setLastUpdatedDateForm(DateForm lastUpdatedDate) {
		this.lastUpdatedDateForm = lastUpdatedDate;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}
}
