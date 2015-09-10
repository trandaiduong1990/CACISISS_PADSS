
package org.transinfo.cacis.dataacess.dao.useraccess;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;

public interface AdminLoginDAO extends BaseDAO
{    
    public AdminLoginDto loadSystemParam(AdminLoginDto loginDto) throws TPlusException;     
    public UserMasterDto getUserData(AdminLoginDto loginDto) throws TPlusException; 
    public boolean isValidUser()throws TPlusException;
    public boolean isPasswordMatch(AdminLoginDto loginDto) throws TPlusException;
    public boolean checkAndUpdatePasswordAttempts(AdminLoginDto loginDto) throws TPlusException;
    public boolean checkPasswordExpiry() throws TPlusException;
    public void resetPasswordAttempts(AdminLoginDto loginDto) throws TPlusException;
    public void loadUserFunctionList(AdminLoginDto loginDto)throws TPlusException;
    public void logActivity(AdminLoginDto loginDto)throws TPlusException;
    public void loadProperties()throws TPlusException;
    public void initHSM()throws TPlusException;
}