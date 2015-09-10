package org.transinfo.cacis.controller.administration;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.administration.LicenseMasterDAO;
import org.transinfo.cacis.dto.administration.LicenseMasterDto;
import org.transinfo.cacis.dto.administration.LicenseMasterSearchDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class LicenseMasterManager {

    private LicenseMasterDAO objLicenseMasterDAO;

    public LicenseMasterManager()throws Exception {
        objLicenseMasterDAO = DAOFactory.getInstance().getLicenseMasterDAO();
    }

    
     public LicenseMasterDto get()throws TPlusException
    {
        boolean success = false;
        LicenseMasterDto  licenseDto = null;
        try
        {
            licenseDto = objLicenseMasterDAO.get();
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in manager get method"+e);
        }
        return licenseDto;
    }


    public boolean upload(LicenseMasterDto objDto)
    throws TPlusException {

        boolean success = false;
        try {
            success = objLicenseMasterDAO.upload(objDto);
        } catch (Exception e) {
            throw new TPlusException("Error in Add method");
        }
        return success;
    }


    public boolean update(LicenseMasterDto objDto)throws TPlusException
    {
        boolean success = false;
        try
        {
            success = objLicenseMasterDAO.update(objDto);
        }
        catch (Exception e)
        {
            throw new TPlusException("Error in LicenseMasterForm update method"+e);
        }
        return success;
    }


    public static void main(String s[])throws Exception
    {

	LicenseMasterManager licenseMgr = new LicenseMasterManager();
	LicenseMasterDto objDto = new LicenseMasterDto();
        LicenseMasterSearchDto searchDto = new LicenseMasterSearchDto();

         objDto.setSerialNo("1234567");
         objDto.setLicenseKey("SAFDASE5323WE42FASS34");
         objDto.setIpAddress("1236352536");
         objDto.setUpdatedDate(objDto.getUpdatedDate());
         objDto.setUserId("krr");


        // licenseMgr.upload(objDto);
         
    }

}