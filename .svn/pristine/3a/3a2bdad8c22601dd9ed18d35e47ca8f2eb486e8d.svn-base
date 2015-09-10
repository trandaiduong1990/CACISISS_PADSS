package org.transinfo.cacis.dto.help;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

public class HelpDto extends ValidatorActionForm {

	// private String screenId;

	private String screenName;

	private int seqNo;

	// private String fieldName;

	private String fieldDesc;

	private String lastUpdatedBy;

	private Date lastUpdateDate;

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
	}

	public HelpDto() {
	}

	public HelpDto(String screenId, String fieldName) {
		this.id.screenId = screenId;
		this.id.fieldName = fieldName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

}
