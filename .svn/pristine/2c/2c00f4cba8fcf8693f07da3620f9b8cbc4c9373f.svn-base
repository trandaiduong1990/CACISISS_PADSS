
package org.transinfo.cacis.dataacess.dao.useraccess;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.useraccess.RoleMasterDto;

public interface RoleFunctionSetupDAO extends BaseDAO
{
    public Collection search(RoleMasterDto objSearchDto, int pageNo)throws TPlusException;
    public RoleMasterDto getRoleMasterForm(String issuerId, String roleId,String userType) throws TPlusException;
    public boolean add(RoleMasterDto objDto)throws TPlusException;
    public boolean update(RoleMasterDto objDto)throws TPlusException;
    public Map getFunctionList(String issuerId, String roleId,String userType)throws TPlusException;
}