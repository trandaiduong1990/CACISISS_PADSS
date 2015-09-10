
package org.transinfo.cacis.dataacess.dao.alert;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.alert.AlertMasterDto;
import org.transinfo.cacis.dto.alert.AlertMasterSearchDto;

public interface AlertMasterDAO extends BaseDAO
{
    public Collection search(AlertMasterSearchDto objSearchDto, int pageNo)throws TPlusException;
    public AlertMasterDto getAlertMasterForm(String alertCode) throws TPlusException; 
    public boolean add(AlertMasterDto objDto)throws TPlusException;
    public boolean update(AlertMasterDto objDto)throws TPlusException;
    public boolean delete(AlertMasterDto objDto)throws TPlusException;
    public Map getUserList(String alertCode)throws TPlusException;
}