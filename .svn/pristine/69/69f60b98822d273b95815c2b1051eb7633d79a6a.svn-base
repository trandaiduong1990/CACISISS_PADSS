package org.transinfo.cacis.dto.riskmanagement;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings({ "unchecked", "serial" })
public class RiskCountryDto implements Serializable{

	private String status;
	private Date updatedDate = new Date();
	private String userId;

	//private Map selectedCities = new TreeMap();
	//private Map riskCities = new HashMap();

	public RiskCountryDto(){}

	public static class Id implements Serializable{

		private long cardNo;
		private String countryCode;

		public Id(){}

		public long getCardNo() {
			//return cardNo;
			return new Long(cardNo).longValue();
		}
		public void setCardNo(long cardNo) {
			this.cardNo = cardNo;
		}

		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
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
	 * @param id The id to set.
	 */
	public void setId(Id id) {
		this.id = id;
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

}
