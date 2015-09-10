package org.transinfo.cacis.controller.authorization;
import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.ServerParamDAO;
import org.transinfo.cacis.dto.authorization.ServerParamFormDto;
import org.transinfo.cacis.dto.authorization.ServerParamSearchDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class ServerParamManager
{

	 private ServerParamDAO objDAO;

	    public ServerParamManager()throws Exception {
	    	objDAO = DAOFactory.getInstance().getServerParamDAO();

	    }

	    public Collection get(ServerParamSearchDto objDto, int pageNo)
	                    throws TPlusException {

			Collection searchLst= null;

	        try {
	            searchLst = objDAO.getServerParam(objDto, pageNo);

	        } catch (Exception e) {
				System.out.println("Controller"+e);
	            throw new TPlusException("Error in GetAll method");
	        }
	        return searchLst;
	    }
            
	  
	    public boolean update(ServerParamFormDto objDto)
	                    throws TPlusException {

	        boolean success;
	        try {

		     /*	if(!validate(objDto, objDAO.UPDATE))
				{

				 System.out.println("Record Not Exists");
			} */

           
            
	            success = objDAO.update(objDto);
	        } catch (Exception e) {
	            throw new TPlusException("Error in GetAll method");
	        }
	        return success;
	    }   

	   
/*
 
   public boolean validate(Object obj,int mode )throws TPlusException
	    {
	        boolean rtnMessage = true;

	         TagFieldFormatDto objTagField = (TagFieldFormatDto)obj;

	        System.out.println(objTagField.getTagName());

	        if(mode==0 && objDAO.checkExistrecord(objTagField)>0)
	        {
	            rtnMessage = false;
	        }
	        if(mode==1 && objDAO.checkExistrecord(objTagField)==0)
	        {
				rtnMessage = false;
	        }

	        return rtnMessage;
	    }

 */

	  public static void main(String s[])throws Exception
	    {
		  ServerParamManager objManager = new  ServerParamManager();
		  ServerParamSearchDto objDto = new ServerParamSearchDto();
		   //adding

                objDto.setIssuerId("ASP");
                
                Collection col = null;
                //col = objManager.search(objDto,0);		
                System.out.println("Size of Collection ==>"+col.size());               
		//objManager.update(objDto);
		
	   }

}