package org.transinfo.cacis.dto.authorization;

import java.io.Serializable;

/*
 * This a bean - and also a Value - or Data Transfer Object
 */
public class TagFieldFormatSearchDto implements Serializable {

    private String searchTagName;
   

    public TagFieldFormatSearchDto() {}

   
    public String getSearchTagName() {
	return searchTagName;
    }

    public void setSearchTagName(String searchTagName) {
	this.searchTagName = searchTagName;
    }






   


}
