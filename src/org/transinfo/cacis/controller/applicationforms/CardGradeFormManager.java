package org.transinfo.cacis.controller.applicationforms;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.CardGradeFormDAO;
import org.transinfo.cacis.dto.applicationforms.CardGradeFormDto;
import org.transinfo.cacis.dto.applicationforms.CardGradeFormSearchDto;
import org.transinfo.cacis.dto.common.ApplicationMasterDto;

public class CardGradeFormManager {
	
	private CardGradeFormDAO objDAO;

	    public CardGradeFormManager()throws Exception {
	    	objDAO = DAOFactory.getInstance().getCardGradeFormDAO();
	        
	    }
	
public Collection search(CardGradeFormDto objDto)  throws TPlusException {

			Collection searchLst= null;

	        try {
	            searchLst = objDAO.search(objDto);

	        } catch (Exception e) {
				System.out.println("Error in CardGradeFormManager search method"+e.getMessage());
	            throw new TPlusException("Error in CardGradeFormManager search method:" +e);
	        }
	        return searchLst;

	    }
/*
* method for CardGradeForm
*/
public boolean add(CardGradeFormDto objCardGradeDto)  throws TPlusException {

        boolean success=false;
       
        objCardGradeDto.setApplicationId(IdsGenartion.GenerateApplicationId());
        objCardGradeDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_NEW);
        objCardGradeDto.setApplicationType(CommonCodes.APPLICATIONTYPE_CARDGRADE);
        
      try {
     	 //this is to validate whether this type of application already existed or not in application_master
 	     //if exists show message this type of record already opend.
 	    ApplicationMasterDto objMstDto = new ApplicationMasterDto();
 	               objMstDto.setApplicationType(objCardGradeDto.getApplicationType());
 	               objMstDto.setIdNumber(objCardGradeDto.getIdNumber());
     	
 	    if(!validate(objMstDto,objDAO.ADD))
				{
				  System.out.println("Record Already Exists");
				}else{
     	          success = objDAO.add(objCardGradeDto);
				}
     	
      } catch (Exception e) {
     			System.out.println("Error in CardGradeFormManager CardGradeFormCard add mehod:"+e.getMessage());
     		 throw new TPlusException("Error in CardGradeFormManager CardGradeForm add mehod:" +e);
     		 }
     	 return success;
 }	

//methods for CardGradeFormProcess

public Collection processSearch(CardGradeFormSearchDto objDto,int pageNo)  throws TPlusException {

		Collection searchLst= null;
		
		try {
		searchLst = objDAO.processSearch(objDto,pageNo);
		
		} catch (Exception e) {
		  System.out.println("CardGradeFormManager processSearch method"+e.getMessage());
		  throw new TPlusException("Error in CardGradeFormManager processSearch method" +e);
		}
		return searchLst;

}
public CardGradeFormDto getCardGradeFormDto(String applicationId) throws TPlusException {
    
	CardGradeFormDto objCarRepDto= null;

       try {
    	   objCarRepDto = objDAO.getCardGradeFormDto(applicationId);
            } catch (Exception e) {
            System.out.println("Error in CardGradeFormManager getCardGradeFormDto method"+e.getMessage());
            throw new TPlusException("Error in CardGradeFormManager getCardGradeFormDto method" +e);
                     }
      return objCarRepDto;
     }
public boolean update(CardGradeFormDto objDto)throws TPlusException
{
    boolean success = false;
    try
    {
    	 if(!validate(objDto,objDAO.UPDATE))
			{

			 System.out.println("Record Not Exists");
		 }else{
              success = objDAO.update(objDto);
		    }
    }
    catch (Exception e)
    {
        throw new TPlusException("Error in CardGradeFormManager update method"+e);
    }
    return success;
}

public boolean accept(CardGradeFormDto objDto) throws TPlusException{
	 
	   boolean accept=false;
	   Date today = new Date();
	   objDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_ACCEPTED);
	   objDto.setCloseDate(today);
	   objDto.setCardStatusId(CommonCodes.CARD_NEW);
	   objDto.setCardProcessStatus(CommonCodes.CARD_PROCESS_NEW);
	 
	   try {
		      if(!validate(objDto,objDAO.UPDATE)){
        			 System.out.println("Record Not Exists");
		       }else{
        		      accept = objDAO.accept(objDto);
		            }
           }catch (Exception e) {
     	      System.out.println("Error in CardGradeFormManager accept method"+e.getMessage());
              throw new TPlusException("Error in CardGradeFormManager accept method" +e);
             }
   return accept;
 }
 
public  boolean reject(CardGradeFormDto objDto) throws TPlusException{
 	
	   boolean reject=false;
 	   Date today = new Date();
	   objDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_REJECTED);
	   objDto.setCloseDate(today);
    
	   try {
     	 if(!validate(objDto,objDAO.UPDATE))
			{
			 System.out.println("Record Not Exists");
		   }else{
     	   reject = objDAO.reject(objDto);
		  }
         } catch (Exception e) {
         	 System.out.println("Error in CardGradeFormManager Reject method"+e.getMessage());
            throw new TPlusException("Error in CardGradeFormManager Reject method" +e);
          }
        return reject;
	    }
/*
 * for validating the records existed or not
 */
public boolean validate(Object obj,int mode )throws TPlusException	{
		    boolean rtnMessage = true;
		    if(mode==0 && objDAO.checkExistrecord(obj)>0){
			        rtnMessage = false;
			    }
			    if(mode==1 && objDAO.checkExistrecord(obj)==0)
			    {
					rtnMessage = false;
			    }
			
			    return rtnMessage;
			}
}
