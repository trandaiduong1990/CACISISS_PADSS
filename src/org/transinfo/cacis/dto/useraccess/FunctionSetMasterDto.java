package org.transinfo.cacis.dto.useraccess;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorActionForm;

public class FunctionSetMasterDto extends ValidatorActionForm{


        private String functionDesc;

        public FunctionSetMasterDto(){}

        public static class Id implements Serializable{

            private String screenId;
            private String functionId;

            public String getScreenId() {
		      return screenId;
	        }
	        public void setScreenId(String screenId) {
		      this.screenId = screenId;
            }
            public String getFunctionId() {
		      return functionId;
            }
            public void setFunctionId(String functionId) {
		        this.functionId = functionId;
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

	public String getFunctionDesc() {
		return functionDesc;
    }
	public void setFunctionDesc(String functionDesc) {
	 	this.functionDesc = functionDesc;
    }


}
