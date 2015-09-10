
package org.transinfo.cacis.dataacess.dao.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestSearchDto;

public interface PhotocopyRequestDAO extends BaseDAO
{
	public Collection search(OriginalRequestSearchDto objSearchDto,int pageNo)throws TPlusException;
	public OriginalRequestDto getOriginalRequest(long settlementId)throws TPlusException;
    public boolean update(OriginalRequestDto objOriReqDto)throws TPlusException;
    public int checkExistrecord(OriginalRequestDto objOriReqDto)throws TPlusException;
}