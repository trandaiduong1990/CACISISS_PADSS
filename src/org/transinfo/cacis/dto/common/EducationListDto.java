package org.transinfo.cacis.dto.common;

import java.io.Serializable;

public class EducationListDto implements Serializable {

	
	private static final long serialVersionUID = 1L;
    private String id;
    private String description;
    
    public EducationListDto(){}
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 
}