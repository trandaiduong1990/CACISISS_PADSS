package org.transinfo.cacis.controller.authorization;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.BlackListCardDAO;
import org.transinfo.cacis.dataacess.dao.authorization.BlockMerchantDAO;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;
import org.transinfo.cacis.dto.authorization.BlockMerchantDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class BlockMerchantManager {

    private BlockMerchantDAO objBlockMerchantDAO;

    public BlockMerchantManager()throws Exception {
        objBlockMerchantDAO = DAOFactory.getInstance().getBlockMerchantDAO();
    }


    public Collection search(BlockMerchantDto objBlockMerchantDto, int pageNo)throws TPlusException
    {

        Collection searchLst= null;

        try
        {
            searchLst = objBlockMerchantDAO.search(objBlockMerchantDto,pageNo);

        } catch (Exception e) {
            System.out.println("Error while BlockMerchantManager search operation"+e);
            throw new TPlusException("Error in BlockMerchantManager search method"+e);
        }
        return searchLst;

    }

    public boolean add(BlockMerchantDto objBlockMerchantDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objBlockMerchantDAO.add(objBlockMerchantDto);
        } catch (Exception e) {
            throw new TPlusException("Error in Add method");
        }
        return success;
    }

    public boolean delete(BlockMerchantDto objBlockMerchantDto)throws TPlusException
    {
        boolean success = false;
        Date today = new Date();
       // objBlackListCardDto.setApplStatus(CommonCodes.APPLICATIONSTATUS_CLOSED);
        //objBlackListCardDto.setCloseDate(today);
        try
        {
            success = objBlockMerchantDAO.delete(objBlockMerchantDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in BlockMerchantForm delete method"+e);
        }
        return success;
    }


}