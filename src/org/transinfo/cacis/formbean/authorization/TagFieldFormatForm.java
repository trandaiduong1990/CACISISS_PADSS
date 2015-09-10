package org.transinfo.cacis.formbean.authorization;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


public class TagFieldFormatForm extends ValidatorForm{

    /**
     * Persistent Instance variables. This data is directly
     * mapped to the columns of database table.
     */
        private String tagName;	
        private String tagDesc;
	private String tagFormat;
        private String tagAttribute;
        private String tagLength;
        private String tagLeftField;
        private String tagBinary;        
        private String tagResponse;        
        private Date updatedDate = new Date();
        private String userId;
	
        
        public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.tagName=null;    
        }
        
        public String getTagName() {
		return tagName;
	}
        		
        public void setTagName(String tagName) {
		this.tagName = tagName;
	}
        
        public String getTagDesc() {
		return tagDesc;
	}
        
        public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
        
        public String getTagFormat() {
		return tagFormat;
	}
        
        public void setTagFormat(String tagFormat) {
		this.tagFormat = tagFormat;
	}
        
        public String getTagAttribute() {
		return tagAttribute;
	}
               
        public void setTagAttribute(String tagAttribute) {
		this.tagAttribute = tagAttribute;
	}
        
        public String  getTagLength() {
		return tagLength;
	}
        
        public void setTagLength(String tagLength) {
		this.tagLength = tagLength;
	}
        
        public String getTagLeftField() {
		return tagLeftField;
	}
            
        public void setTagLeftField(String tagLeftField) {
		this.tagLeftField = tagLeftField;
	}
        
        public String getTagBinary() {
		return tagBinary;
	}
        
        public void setTagBinary(String tagBinary) {
		this.tagBinary = tagBinary;
	}
        
        public String getTagResponse() {
		return tagResponse;
	}
            
        public void setTagResponse(String tagResponse) {
		this.tagResponse = tagResponse;
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




