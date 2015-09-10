package org.transinfo.cacis.formbean.disputemanagement;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

@SuppressWarnings( { "serial" })
public class IncomingCTFProcessForm extends ValidatorForm {

	private FormFile file;

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
}
