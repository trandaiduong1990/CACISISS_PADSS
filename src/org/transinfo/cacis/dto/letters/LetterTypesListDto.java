package org.transinfo.cacis.dto.letters;

import org.apache.struts.validator.ValidatorActionForm;

public class LetterTypesListDto extends ValidatorActionForm{

	private String letterTypes;
	private String letterTypesId;
	private String letterTypesName;
	private String applSource;
	private String category;

	public LetterTypesListDto(){}

	public String getLetterTypes() {
		return letterTypes;
	}

	public void setLetterTypes(String letterTypes) {
		this.letterTypes = letterTypes;
	}

	public String getLetterTypesId() {
		return letterTypesId;
	}

	public void setLetterTypesId(String letterTypesId) {
		this.letterTypesId = letterTypesId;
	}

	public String getLetterTypesName() {
		return letterTypesName;
	}

	public void setLetterTypesName(String letterTypesName) {
		this.letterTypesName = letterTypesName;
	}

	public String getApplSource() {
		return applSource;
	}

	public void setApplSource(String applSource) {
		this.applSource = applSource;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
