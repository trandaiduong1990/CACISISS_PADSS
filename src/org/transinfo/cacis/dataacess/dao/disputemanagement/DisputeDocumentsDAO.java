package org.transinfo.cacis.dataacess.dao.disputemanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsSearchDto;

public interface DisputeDocumentsDAO extends BaseDAO
{
	public Collection search(DisputeDocumentsSearchDto objSearchDto,int pageNo)throws TPlusException;
    public boolean add(DisputeDocumentsDto objDto)throws TPlusException;
    public boolean delete(DisputeDocumentsDto objDto)throws TPlusException;
}