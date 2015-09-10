
package org.transinfo.cacis.dataacess.dao.authorization;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.authorization.SMSServiceDto;
import org.transinfo.cacis.dto.authorization.SMSServiceSearchDto;

public interface SMSServiceDAO extends BaseDAO
{
    public Collection search(SMSServiceSearchDto objSMSServiceSearchDto, int pageNo)throws TPlusException;
    public boolean add(SMSServiceDto objSMSServiceDto)throws TPlusException;
    public SMSServiceDto getSMSService(long cardNumber)throws TPlusException;
    public boolean update(SMSServiceDto objSMSServiceDto)throws TPlusException;
    public int checkExistRecord(SMSServiceDto objSMSServiceDto)throws TPlusException;
    public int checkDuplicateRecord(SMSServiceDto objSMSServiceDto)throws TPlusException;
}