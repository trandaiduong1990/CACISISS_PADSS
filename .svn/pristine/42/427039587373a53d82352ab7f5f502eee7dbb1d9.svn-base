package org.transinfo.cacis.controller.useraccess;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.RoleFunctionSetupDAO;
import org.transinfo.cacis.dto.useraccess.RoleMasterDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class RoleFunctionManager {

    private RoleFunctionSetupDAO objRoleFunctionDAO;

    public RoleFunctionManager()throws Exception {
        objRoleFunctionDAO = DAOFactory.getInstance().getRoleFunctionSetupDAO();
    }


    public Collection search(RoleMasterDto objSearchtDto, int pageNo)throws TPlusException
    {

        Collection searchLst= null;

        try
        {
            searchLst = objRoleFunctionDAO.search(objSearchtDto,pageNo);

        } catch (Exception e) {
            System.out.println("Error while search operation"+e);
            throw new TPlusException("Error in RoleFunctionSetupForm search method"+e);
        }
        return searchLst;
    }


     public RoleMasterDto get(String issuerId, String roleId,String userType)throws TPlusException
    {
        boolean success = false;
        RoleMasterDto  roleDto = null;
        try
        {
            roleDto = objRoleFunctionDAO.getRoleMasterForm(issuerId, roleId,userType);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RoleFunctionSetupForm get method"+e);
        }
        return roleDto;
    }

    public boolean add(RoleMasterDto objDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objRoleFunctionDAO.add(objDto);
        } catch (Exception e) {
            throw new TPlusException("Error in Add method");
        }
        return success;
    }

     public boolean update(RoleMasterDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objRoleFunctionDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RoleFunctionSetupForm update method"+e);
        }
        return success;
    }


}