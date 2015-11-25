package org.transinfo.cacis.dto.cardproduction;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings( { "unchecked", "serial", "unused" })
public class ApplicationTypeDto implements Serializable {
	private int applicationTypeId;
	private String description;
	public int getApplicationTypeId() {
		return applicationTypeId;
	}
	public void setApplicationTypeId(int applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
