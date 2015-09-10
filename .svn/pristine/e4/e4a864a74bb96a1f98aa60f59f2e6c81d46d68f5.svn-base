package org.transinfo.cacis.controller.switching;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.switching.SwitchDAO;
import org.transinfo.cacis.dto.switching.SwitchDto;
import org.transinfo.cacis.dto.switching.SwitchSearchDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class SwitchManager {

    private SwitchDAO objSwitchDAO;

    public SwitchManager()throws Exception {
        objSwitchDAO = DAOFactory.getInstance().getSwitchDAO();
    }


    public Collection getSwitchList(SwitchSearchDto objSearchtDto, int pageNo)throws TPlusException
    {

        Collection searchLst= null;

        try
        {
            searchLst = objSwitchDAO.get(objSearchtDto,pageNo);

        } catch (Exception e) {
            System.out.println("Error while search operation"+e);
            throw new TPlusException("Error in SwitchForm search method"+e);
        }
        return searchLst;
    }


    public SwitchDto get(String swType)throws TPlusException
    {
        boolean success = false;
        SwitchDto  swDto = null;
        try
        {
            swDto = objSwitchDAO.getSwitchForm(swType);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in SwitchForm get method"+e);
        }
        return swDto;
    }

    public boolean add(SwitchDto objDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objSwitchDAO.add(objDto);
        } catch (Exception e) {
            throw new TPlusException("Error in Add method");
        }
        return success;
    }


    public boolean update(SwitchDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objSwitchDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in SwitchForm update method"+e);
        }
        return success;
    }

    public boolean delete(SwitchDto objDto)throws TPlusException
    {
        boolean success = false;

        try
        {
            success = objSwitchDAO.delete(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in SwitchForm delete method"+e);
        }
        return success;
    }


    public static void main(String s[])throws Exception
    {

	SwitchManager swMgr = new SwitchManager();
	SwitchDto objDto = new SwitchDto();
    SwitchSearchDto searchDto = new SwitchSearchDto();

                searchDto.setSwitchType("y");

                objDto.setSwName("Sw");
                objDto.setSwType("y");
                objDto.setSwIpAddress("123432423");
                objDto.setSwPort("port");
                objDto.setSwTimeOut1(1);
                objDto.setSwTimeOut2(2);
                objDto.setUpdatedDate(objDto.getUpdatedDate());
                objDto.setUserId("Ramesh");

              swMgr.getSwitchList(searchDto,0);

              //swMgr.add(objDto);
                //swMgr.delete(objDto);
 	}

}