package org.transinfo.cacis.dataacess.dao.riskmanagement;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.WriteOffMasterDto;

public interface WriteOffDAO extends BaseDAO
{
	public WriteOffMasterDto getWriteOff(String issuerId)throws TPlusException;
    public boolean add(WriteOffMasterDto objDto)throws TPlusException;
    public boolean update(WriteOffMasterDto objDto)throws TPlusException;
    public int checkExistrecord(WriteOffMasterDto objDto)throws TPlusException;
}