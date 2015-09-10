package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxPeriodDAO;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxPeriodDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class RiskTranxPeriodManager {

    private RiskTranxPeriodDAO objRiskPeriodDAO;

    public RiskTranxPeriodManager()throws Exception {
        objRiskPeriodDAO = DAOFactory.getInstance().getRiskTranxPeriodDAO();
    }


    public Collection search(RiskTranxPeriodDto objSearchtDto, int pageNo)throws TPlusException
    {

        Collection searchLst= null;

        try
        {
            searchLst = objRiskPeriodDAO.search(objSearchtDto,pageNo);

        } catch (Exception e) {
            System.out.println("Error while search operation"+e);
            throw new TPlusException("Error in RiskTranxPeriodForm search method"+e.getMessage());
        }
        return searchLst;

    }

     public RiskTranxPeriodDto getRiskTranxPeriodForm(String issuerId, int period)throws TPlusException
    {
        boolean success = false;
        RiskTranxPeriodDto  riskPeriodDto = null;
        try
        {
            riskPeriodDto = objRiskPeriodDAO.getRiskTranxPeriodForm(issuerId, period);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RiskTranxPeriodForm get method"+e);
        }
        return riskPeriodDto;
    }

    public boolean add(RiskTranxPeriodDto objDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objRiskPeriodDAO.add(objDto);
        } catch (Exception e) {
            throw new TPlusException("Error in RiskTranxPeriodForm Add method");
        }
        return success;
    }

     public boolean update(RiskTranxPeriodDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objRiskPeriodDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RiskTranxPeriodForm update method"+e);
        }
        return success;
    }

   
    public static void main(String s[])throws Exception
    {

	RiskTranxPeriodManager riskMgr = new RiskTranxPeriodManager();
	RiskTranxPeriodDto objDto = new RiskTranxPeriodDto();
        
          try{
               // riskMgr.search(objDto, 0);
               
          }catch(Exception ex){
              System.out.println(ex.getMessage());
          }
 	}

}