package org.transinfo.cacis.controller.authorization;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.LoginParamDAO;
import org.transinfo.cacis.dto.authorization.LoginParamDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class LoginParamManager {

    private LoginParamDAO objLoginParamDAO;

    public LoginParamManager()throws Exception {
        objLoginParamDAO = DAOFactory.getInstance().getLoginParamDAO();
    }

    
     public LoginParamDto get(String issuerid)throws TPlusException
    {
        boolean success = false;
        LoginParamDto  loginParamDto = null;
        try
        {
            loginParamDto = objLoginParamDAO.get(issuerid);
            
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in manager get method"+e);
        }
        return loginParamDto;
    }


    public boolean add(LoginParamDto objDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objLoginParamDAO.add(objDto);
        } catch (Exception e) {
            throw new TPlusException("Error in manager upload method");
        }
        return success;
    }


    public boolean update(LoginParamDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objLoginParamDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in LoginParamForm update method"+e);
        }
        return success;
    }


    public static void main(String s[])throws Exception
    {

	LoginParamManager loginMgr = new LoginParamManager();
	LoginParamDto objDto = new LoginParamDto();
        
         
    }

}