package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DisputeGroupsDto implements Serializable {

	private String groupId;
	private String groupName;
	private String groupDescription;
	private String scheme;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
}
