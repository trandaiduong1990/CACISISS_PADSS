package org.transinfo.cacis.controller.alert;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.alert.AlertMasterDAO;
import org.transinfo.cacis.dto.alert.AlertMasterDto;
import org.transinfo.cacis.dto.alert.AlertMasterSearchDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class AlertMasterManager {

    private AlertMasterDAO objAlertMasterDAO;

    public AlertMasterManager()throws Exception {
        objAlertMasterDAO = DAOFactory.getInstance().getAlertMasterDAO();
    }


    public Collection getAlertList(AlertMasterSearchDto objSearchtDto, int pageNo)throws TPlusException
    {

        Collection searchLst= null;

        try
        {
            searchLst = objAlertMasterDAO.search(objSearchtDto,pageNo);

        } catch (Exception e) {
            System.out.println("Error while search operation"+e);
            throw new TPlusException("Error in AlertMasterForm search method"+e);
        }
        return searchLst;

    }

     public AlertMasterDto get(String alertCode)throws TPlusException
    {
        boolean success = false;
        AlertMasterDto  alertDto = null;
        try
        {
            alertDto = objAlertMasterDAO.getAlertMasterForm(alertCode);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in AlertMasterForm get method"+e);
        }
        return alertDto;
    }

    public boolean add(AlertMasterDto objDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objAlertMasterDAO.add(objDto);
        } catch (Exception e) {
            throw new TPlusException("Error in Add method");
        }
        return success;
    }

     public boolean update(AlertMasterDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objAlertMasterDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in AlertMasterForm update method"+e);
        }
        return success;
    }

    public boolean delete(AlertMasterDto objDto)throws TPlusException
    {
        boolean success = false;
        Date today = new Date();
       // objBlackListCardDto.setApplStatus(CommonCodes.APPLICATIONSTATUS_CLOSED);
        //objBlackListCardDto.setCloseDate(today);
        try
        {
            success = objAlertMasterDAO.delete(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in AlertMasterForm delete method"+e);
        }
        return success;
    }


    public static void main(String s[])throws Exception
    {

	AlertMasterManager alertMgr = new AlertMasterManager();
	AlertMasterDto objDto = new AlertMasterDto();

                objDto.setAlertCode("123");
                objDto.setAlertDesc("Desc");
                objDto.setEmailSubject("Hello");
                objDto.setEmailBody("Body");
                objDto.setEmailSignature("Singature");
                objDto.setStatus("status");
                objDto.setUpdatedDate(objDto.getUpdatedDate());
                objDto.setUserId(objDto.getUserId());

                alertMgr.add(objDto);
                //alertMgr.delete(objDto);
 	}

}