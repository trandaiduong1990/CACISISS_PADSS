package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxActionDAO;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxActionDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class RiskTranxActionManager {

    private RiskTranxActionDAO objRiskActionDAO;

    public RiskTranxActionManager()throws Exception {
        objRiskActionDAO = DAOFactory.getInstance().getRiskTranxActionDAO();
    }


    public Collection search(RiskTranxActionDto objSearchtDto, int pageNo)throws TPlusException
    {

        Collection searchLst= null;

        try
        {
            searchLst = objRiskActionDAO.search(objSearchtDto,pageNo);

        } catch (Exception e) {
            System.out.println("Error while search operation"+e);
            throw new TPlusException("Error in RiskActionForm search method"+e.getMessage());
        }
        return searchLst;

    }

    public RiskTranxActionDto getRiskActionForm(String riskId) throws TPlusException
    {
        boolean success = false;
        RiskTranxActionDto  riskActionDto = null;
        try
        {
            riskActionDto = objRiskActionDAO.getRiskActionForm(riskId);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RiskActionForm get method"+e);
        }
        return riskActionDto;
    }
    
   
    public boolean add(RiskTranxActionDto objDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objRiskActionDAO.add(objDto);
        } catch (Exception e) {
            throw new TPlusException("Error in RiskActionForm Add method");
        }
        return success;
    }

     public boolean update(RiskTranxActionDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objRiskActionDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RiskActionForm update method"+e);
        }
        return success;
    }

   
    public static void main(String s[])throws Exception
    {

	RiskTranxActionManager riskMgr = new RiskTranxActionManager();
	
          
   }

}