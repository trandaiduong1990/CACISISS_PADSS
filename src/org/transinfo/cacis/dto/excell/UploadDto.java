package org.transinfo.cacis.dto.excell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.upload.FormFile;

public class UploadDto implements Serializable{
	
	
	 private static final long serialVersionUID = 1L;
	 private long uploadId;
	 private String uploadType;
	 private String uploadFileName;
	 private String status;
	 private String issuerId;
	 private String userId;
	 private String uploadResPath;
     private Date  updatedDate = new Date();
   
    private FormFile theFile;
    private ArrayList dtoList = new ArrayList();
	
    public UploadDto(){}
	
    public ArrayList getDtoList() {
		return dtoList;
	}
	public void setDtoList(ArrayList dtoList) {
		this.dtoList = dtoList;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
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

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public FormFile getTheFile() {
		return theFile;
	}

	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getUploadId() {
		return uploadId;
	}

	public void setUploadId(long uploadId) {
		this.uploadId = uploadId;
	}

	public String getUploadResPath() {
		return uploadResPath;
	}

	public void setUploadResPath(String uploadResPath) {
		this.uploadResPath = uploadResPath;
	}
	

}
