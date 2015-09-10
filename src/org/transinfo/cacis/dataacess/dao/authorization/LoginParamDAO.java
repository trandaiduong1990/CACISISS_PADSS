
package org.transinfo.cacis.dataacess.dao.authorization;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.authorization.LoginParamDto;

public interface LoginParamDAO extends BaseDAO
{
    
    public LoginParamDto get(String issuerid) throws TPlusException; 
    public boolean add(LoginParamDto objDto)throws TPlusException;
    public boolean update(LoginParamDto objDto)throws TPlusException;
    
}