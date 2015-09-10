
package org.transinfo.cacis.dataacess.dao.useraccess;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.dto.useraccess.UserSetupSearchDto;

public interface UserSetupDAO extends BaseDAO
{
    public Collection search(UserSetupSearchDto objSearchDto, int pageNo)throws TPlusException;
    public UserMasterDto getUserMasterForm(String issuerId, String userId) throws TPlusException; 
    public boolean add(UserMasterDto objDto)throws TPlusException;
    public boolean update(UserMasterDto objDto)throws TPlusException;
    public boolean delete(UserMasterDto objDto)throws TPlusException;
    public Map getRoleId(String issuerId,String userType)throws TPlusException;
    public String getUserName(String issuerId, String userId)throws TPlusException;
    public String getIssuerName(String issuerId)throws TPlusException;
}