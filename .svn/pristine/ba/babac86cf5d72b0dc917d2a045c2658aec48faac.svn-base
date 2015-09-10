package org.transinfo.cacis.controller.authorization;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.BlackListCardDAO;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class BlackListCardManager {

    private BlackListCardDAO objBlackListCardDAO;

    public BlackListCardManager()throws Exception {
        objBlackListCardDAO = DAOFactory.getInstance().getBlackListDAO();
    }


    public Collection search(BlackListCardDto objBlackListDto, int pageNo)throws TPlusException
    {

        Collection searchLst= null;

        try
        {
            searchLst = objBlackListCardDAO.search(objBlackListDto,pageNo);

        } catch (Exception e) {
            System.out.println("Error while search operation"+e);
            throw new TPlusException("Error in BlackListForm search method"+e);
        }
        return searchLst;

    }

    public boolean add(BlackListCardDto objBlackListCardDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objBlackListCardDAO.add(objBlackListCardDto);
        } catch (Exception e) {
            throw new TPlusException("Error in Add method");
        }
        return success;
    }

    public boolean delete(BlackListCardDto objBlackListCardDto)throws TPlusException
    {
        boolean success = false;
        Date today = new Date();
       // objBlackListCardDto.setApplStatus(CommonCodes.APPLICATIONSTATUS_CLOSED);
        //objBlackListCardDto.setCloseDate(today);
        try
        {
            success = objBlackListCardDAO.delete(objBlackListCardDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in BlackListCardForm delete method"+e);
        }
        return success;
    }


}