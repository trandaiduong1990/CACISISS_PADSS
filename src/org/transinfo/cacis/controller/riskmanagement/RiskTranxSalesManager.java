package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxSaleCashDAO;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxSaleCashDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class RiskTranxSalesManager {

    private RiskTranxSaleCashDAO objRiskTranxDAO;

    public RiskTranxSalesManager()throws Exception {
        objRiskTranxDAO = DAOFactory.getInstance().getRiskTranxSaleCashDAO();
    }


    public Collection search(RiskTranxSaleCashDto objSearchtDto, int pageNo)throws TPlusException
    {

        Collection searchLst= null;

        try
        {
            searchLst = objRiskTranxDAO.search(objSearchtDto,pageNo);

        } catch (Exception e) {
            System.out.println("Error while search operation"+e);
            throw new TPlusException("Error in RiskTranxForm search method"+e.getMessage());
        }
        return searchLst;

    }

     public RiskTranxSaleCashDto getRiskTranxForm(int id)throws TPlusException
    {
        boolean success = false;
        RiskTranxSaleCashDto  riskDto = null;
        try
        {
            riskDto = objRiskTranxDAO.getRiskTranxForm(id);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RiskTranxForm get method"+e);
        }
        return riskDto;
    }

    public boolean add(RiskTranxSaleCashDto objDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objRiskTranxDAO.add(objDto);
        } catch (Exception e) {
            throw new TPlusException("Error in RiskTranxForm Add method");
        }
        return success;
    }

     public boolean update(RiskTranxSaleCashDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objRiskTranxDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RiskTranxForm update method"+e);
        }
        return success;
    }

   
    public static void main(String s[])throws Exception
    {

	RiskTranxSalesManager riskMgr = new RiskTranxSalesManager();
	RiskTranxSaleCashDto objDto = new RiskTranxSaleCashDto();
        
          try{
               // riskMgr.search(objDto, 0);
               
          }catch(Exception ex){
              System.out.println(ex.getMessage());
          }
 	}

}