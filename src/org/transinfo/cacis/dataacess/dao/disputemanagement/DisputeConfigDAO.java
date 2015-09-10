package org.transinfo.cacis.dataacess.dao.disputemanagement;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.disputemanagement.DisputeConfigMasterDto;

public interface DisputeConfigDAO extends BaseDAO
{
	public DisputeConfigMasterDto getDisputeConfig(String issuerId)throws TPlusException;
    public boolean add(DisputeConfigMasterDto objDto)throws TPlusException;
    public boolean update(DisputeConfigMasterDto objDto)throws TPlusException;
    public int checkExistrecord(DisputeConfigMasterDto objDto)throws TPlusException;
}