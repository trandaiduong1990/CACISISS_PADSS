package org.transinfo.cacis.controller.useraccess;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.PasswordChangeDAO;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class PasswordChangeManager {

    private PasswordChangeDAO objPwdDAO;

    public PasswordChangeManager()throws Exception {
        objPwdDAO = DAOFactory.getInstance().getPasswordChangeDAO();
    }

    
     public UserMasterDto get(String issuerId, String userId)throws TPlusException
    {
        boolean success = false;
        UserMasterDto  userDto = null;
        try
        {
            userDto = objPwdDAO.get(issuerId, userId);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in manager get method"+e);
        }
        return userDto;
    }

    
    public boolean update(UserMasterDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objPwdDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in UserMasterForm update method"+e);
        }
        return success;
    }


    public static void main(String s[])throws Exception
    {

	PasswordChangeManager pwdMgr = new PasswordChangeManager();
	UserMasterDto objDto = new UserMasterDto();
        

                 
    }

}