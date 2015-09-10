package org.transinfo.cacis.dto.cardproduction;


import java.util.Date;

public class CustomerDocProofDto {

	private Date       updatedDate;
	private String     lastUpdatedBy;
	
	
	public CustomerDocProofDto(){}

	public CustomerDocProofDto(Date updatedDate, String lastUpdatedBy)
	{

		this.updatedDate = updatedDate;
		this.lastUpdatedBy = lastUpdatedBy;

	}


	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	

}

