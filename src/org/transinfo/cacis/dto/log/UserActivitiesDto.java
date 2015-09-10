package org.transinfo.cacis.dto.log;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UserActivitiesDto implements Serializable {
	
	private int userActivityId;
	private Date dateTime = new Date();
	private String userId;
	private String stationIp;
	private String issuerId;
	private String activity;

	public int getUserActivityId() {
		return userActivityId;
	}

	public void setUserActivityId(int userActivityId) {
		this.userActivityId = userActivityId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStationIp() {
		return stationIp;
	}

	public void setStationIp(String stationIp) {
		this.stationIp = stationIp;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

}
