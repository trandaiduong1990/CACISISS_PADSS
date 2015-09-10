package org.transinfo.cacis.dataacess.dao.disputemanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.disputemanagement.DocumentUploadDto;
import org.transinfo.cacis.dto.disputemanagement.DocumentUploadSearchDto;

public interface DocumentUploadDAO extends BaseDAO
{
	public Collection search(DocumentUploadSearchDto objSearchDto,int pageNo)throws TPlusException;
    public boolean upload(DocumentUploadDto objDto)throws TPlusException;
    public boolean remove(DocumentUploadDto objDto)throws TPlusException;
    public Collection getDocumentUpload(DocumentUploadDto objSearchDto,int pageNo)throws TPlusException;
}