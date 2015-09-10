
package org.transinfo.cacis.dataacess.dao.switching;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.switching.SwitchDto;
import org.transinfo.cacis.dto.switching.SwitchSearchDto;

public interface SwitchDAO extends BaseDAO
{
    public Collection get(SwitchSearchDto objSearchDto, int pageNo)throws TPlusException; 
    public SwitchDto getSwitchForm(String swType) throws TPlusException; 
    public boolean add(SwitchDto objDto)throws TPlusException;
    public boolean update(SwitchDto objDto)throws TPlusException;
    public boolean delete(SwitchDto objDto)throws TPlusException;    
}