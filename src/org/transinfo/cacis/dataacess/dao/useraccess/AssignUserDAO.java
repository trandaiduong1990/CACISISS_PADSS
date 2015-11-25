
package org.transinfo.cacis.dataacess.dao.useraccess;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.useraccess.AssignUserDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.dto.useraccess.UserSetupSearchDto;

public interface AssignUserDAO extends BaseDAO
{
    public Collection search(AssignUserDto objSearchDto, int pageNo)throws TPlusException;
    public boolean add(AssignUserDto objDto)throws TPlusException;
    public boolean update(AssignUserDto objDto)throws TPlusException;
    public Map getRoleId(String groupId)throws TPlusException;
	public AssignUserDto getAssignUserForm(String assignId)throws TPlusException;
}