package org.transinfo.cacis.action.excell;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.excell.UploadManager;
import org.transinfo.cacis.dto.excell.UploadDto;
import org.transinfo.cacis.formbean.excell.UploadForm;


public class UploadAction extends BaseAction {

    public ActionForward executeAction(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                         throws TPlusException,Exception {

   	if(request.getParameter("method")!=null && ((String)request.getParameter("method")).equals("List"))
    	{
   		try
    		{
    			UploadForm objForm = (UploadForm)form;
    		    objForm.setUploadType((String)request.getParameter("uploadType"));
    	        return mapping.findForward("success");

    		}
    		catch(Exception exp)
    		{
    			 System.out.println("Error in Search Branch Action Calling PreList: " + exp);
    			 throw new TPlusException("Error in Search Branch Action Calling PreList " + exp);
    		}
    	}
  
  	else{
    	
    	        ActionErrors errors = null;
    	
    	    	UploadForm objForm = (UploadForm)form;
    	    	 
    	    	//to generate the respone excell file
    	    	objForm.setUploadResPath((String)request.getParameter("uploadResPath")); 
    			//FormFile myFile = objForm.getTheFile();
    			
    			/*System.err.println("myFile Name is:" + myFile.getFileName());
    			InputStream is = objForm.getTheFile().getInputStream();
    			String uploadFileName =myFile.getFileName();
    			String uploadType =objForm.getUploadType();*/
    			
    			 // DTO Creation
    	        UploadDto objDto = new UploadDto();
    	      
    	        try
    	        {
    	            BeanUtils.copyProperties(objDto, objForm);
    	             
    	        }
    	        catch (Exception e)
    	        {
    	        	  System.out.println("Error converting to form bean in BranchDispatchAction add method: " + e.getMessage());
    	              throw new TPlusException("Could not populate the form bean in BranchDispatchAction add method " + e);
    	        }
    	        
    	        System.out.println("############################################33");
    	        System.out.println("form in Actionfilename" +objForm.getUploadResPath());
    	        System.out.println("############################################33");
    	       
    	        System.out.println("############################################33");
    	        System.out.println("Dto in Actionfilename" +objDto.getUploadResPath());
    	        System.out.println("############################################33");
    	        
    	       UploadManager manager = new UploadManager();
    	 // boolean success = manager.upload(is,uploadType,uploadFileName);
    	   boolean success = manager.upload(objDto);
    	     //   boolean success=true;
    	   
    	  if(!success)
               {
    	         errors = new ActionErrors();
                 errors.add("Error", new ActionError("error.uploadfailed"));
    	         saveErrors(request, errors);
               }
                 else{
                 errors = new ActionErrors();
    	         errors.add("Error", new ActionError("error.uploadSuccess"));
    	         saveErrors(request, errors);
              }
    	 
    		return mapping.findForward("success");
    	}
    }
 }

 