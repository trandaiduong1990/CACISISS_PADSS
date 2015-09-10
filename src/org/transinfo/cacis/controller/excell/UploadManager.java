package org.transinfo.cacis.controller.excell;
import java.io.InputStream;
import java.util.ArrayList;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.excell.UploadDAO;
import org.transinfo.cacis.dto.excell.UploadDto;
import org.transinfo.cacis.model.excell.UploadData;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class UploadManager {

   private UploadDAO objDAO;
   
    public  UploadManager()throws Exception {
    	objDAO = DAOFactory.getInstance().getUploadDAO();
      
      }
  
  
 public boolean upload(UploadDto objDto)  throws TPlusException {

        boolean bolUpload = false;
        ArrayList dtoList = new   ArrayList();
        UploadData objUploadData = new UploadData();
    
        
    try {
        	InputStream objInputStream = objDto.getTheFile().getInputStream();
			String uploadFileName      = objDto.getTheFile().getFileName();
			String uploadType          = objDto.getUploadType();
			//to insert uploadlog table
			objDto.setUploadFileName(uploadFileName);
			objDto.setStatus(CommonCodes.UPLOAD_SUCCESS);
		 //validating 	
		if(!validate(objDto,objDAO.ADD))
			{
    			  System.out.println("\n\n Excell File Already Processed ");
			}else{
		 	
		//calls the excell reading logic and set the data to corresponding dto's and returns Dto's ArrayList 		 
             dtoList    = objUploadData.execute(objInputStream,uploadType,uploadFileName);
                          objDto.setDtoList(dtoList);
             bolUpload  = objDAO.add(objDto);
          }
        } catch (Exception e) {
            
            	 throw new TPlusException("Error in UploadManager add method" +e);
         }
               
       return bolUpload;
    }

        
        /*
         * this method is used for calling the checkexistrecord method in Impl class
         */
    public boolean validate(Object obj,int mode )throws TPlusException
        {
            boolean rtnMessage = true;

            UploadDto objDto = (UploadDto)obj;
         

            if(mode==0 && objDAO.checkExistrecord(objDto)>0)
            {
                rtnMessage = false;
            }
            if(mode==1 && objDAO.checkExistrecord(objDto)==0)
            {
       			rtnMessage = false;
            }

            return rtnMessage;
        }  
  public static void main(String args[]){
	  try{
		  UploadManager obj = new UploadManager();
		  UploadDto objDto = new UploadDto();
		  obj.upload(objDto);
			 
	  }catch(Exception e){
		  System.out.println("Erro" +e);
	  }
	 
  }
 
}