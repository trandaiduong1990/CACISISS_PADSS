package org.transinfo.cacis.dto.letters;

import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings("serial")
public class LetterTemplateSearchDto implements Serializable {

	private String letterId;
	private Collection searchList;
	private int totalCount;

	public String getLetterId() {
		return letterId;
	}

	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}

	public Collection getSearchList() {
		return searchList;
	}

	public void setSearchList(Collection searchList) {
		this.searchList = searchList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
