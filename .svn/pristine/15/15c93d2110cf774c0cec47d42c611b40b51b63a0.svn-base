package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskCountryDAO;
import org.transinfo.cacis.dto.riskmanagement.RiskCountryDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class RiskCountryManager {

    private RiskCountryDAO objRiskCntryDAO;

    public RiskCountryManager()throws Exception {
        objRiskCntryDAO = DAOFactory.getInstance().getRiskCountryDAO();
    }


    public Collection search(RiskCountryDto objSearchtDto, int pageNo)throws TPlusException
    {

        Collection searchLst= null;

        try
        {
            searchLst = objRiskCntryDAO.search(objSearchtDto,pageNo);

        } catch (Exception e) {
            System.out.println("Error while search operation"+e);
            throw new TPlusException("Error in RiskCountryForm search method"+e.getMessage());
        }
        return searchLst;

    }

    public RiskCountryDto getRiskCountryForm(long cardNo, String countryCode) throws TPlusException
    {
        boolean success = false;
        RiskCountryDto  riskCntryDto = null;
        try
        {
            riskCntryDto = objRiskCntryDAO.getRiskCountryForm(cardNo, countryCode);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RiskCountryForm get method"+e);
        }
        return riskCntryDto;
    }
    
    
    public Map getCities(RiskCountryDto objDto) throws TPlusException
    {
        Map citiesList = null;
        RiskCountryDto  riskCntryDto = null;
        try
        {
            citiesList = objRiskCntryDAO.getCityList(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in getCities get method"+e);
        }
        return citiesList;
    }

    public boolean add(RiskCountryDto objDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objRiskCntryDAO.add(objDto);
        } catch (Exception e) {
            throw new TPlusException("Error in RiskCountryForm Add method");
        }
        return success;
    }

     public boolean update(RiskCountryDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objRiskCntryDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in RiskCountryForm update method"+e);
        }
        return success;
    }

   
    public static void main(String s[])throws Exception
    {

	RiskCountryManager riskMgr = new RiskCountryManager();
        RiskCountryDto riskDto = new RiskCountryDto();
        
        riskDto.id.setCardNo(Long.parseLong("4563270000000030"));
        riskDto.id.setCountryCode("IN");
	riskDto.setStatus("A");
        riskDto.setUpdatedDate(new Date());  
        riskDto.setUserId("Ramesh");
        
        riskMgr.add(riskDto);
   }

}