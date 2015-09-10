package org.transinfo.cacis.action.disputemanagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.disputemanagement.DisputeClaimFormManager;
import org.transinfo.cacis.dto.disputemanagement.DisputeClaimFormDto;
import org.transinfo.cacis.formbean.disputemanagement.DisputeClaimSetupForm;

public class DisputeClaimFormDispatchAction extends BaseDispatchAction{
	
/*to call the DocPrlist to get the Documents from using reasonCode*/	
	public ActionForward DocPreList(ActionMapping mapping,
             ActionForm form,
             HttpServletRequest request,
             HttpServletResponse response)
     throws TPlusException,Exception {
		
		//Form Validations
	DisputeClaimSetupForm objForm = (DisputeClaimSetupForm)form;
		
	     objForm.getDocPreListData();
	     objForm.getPreListData();
	  
	     request.setAttribute("DOCLIST","");
		return mapping.findForward("success");
		}

	/*
	   * this method is used for adding the BranchDetails
	   */
	
  public ActionForward add(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
                       throws TPlusException,Exception {

    
	       ActionErrors errors = null;
  	
		// Token Validation
	   if(!isTokenValid(request))
		{	
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		}
    
	    //Form Validations
	 
	  DisputeClaimSetupForm objForm = (DisputeClaimSetupForm)form;
	   errors = objForm.validate(mapping,request);
      
      if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
	System.out.println("Number of Docs value is"+objForm.getNubmerOfDocs()!=null && !objForm.getNubmerOfDocs().equals(""));
	if(objForm.getNubmerOfDocs()!=null && !objForm.getNubmerOfDocs().equals("")){
	    	objForm.getDocPreListData();
		    request.setAttribute("DOCLIST",""); 
	     } 
	        objForm.getPreListData();
			return mapping.findForward("success");
		}
           
       // DTO Creation
      DisputeClaimFormDto objDto = new DisputeClaimFormDto();
    
      try
      {
          BeanUtils.copyProperties(objDto, objForm);
          
      }
      catch (Exception e)
      {
      	  System.out.println("Error converting to form bean in DisputeClaimFormDispatchAction add method: " + e.getMessage());
            throw new TPlusException("Could not populate the form bean in DisputeClaimFormDispatchAction add method " + e);
      }
   
      DisputeClaimFormManager objManager = new DisputeClaimFormManager();
     
      //this for validating transactionAmount and TranxDates
       boolean validate = objManager.validate(objDto,0);
       if(!validate){
        	 errors = new ActionErrors();
             errors.add("Error", new ActionError("error.tranxamtanddate"));
	         saveErrors(request, errors);
	       /*  objForm.getPreListData();
	          objForm.getDocPreListData();
		      request.setAttribute("DOCLIST","");*/
		    return mapping.findForward("validate");
           }
       //Action Execution 
      boolean boolAdd = objManager.add(objDto);
          
      if(!boolAdd)
         {
	         errors = new ActionErrors();
             errors.add("Error", new ActionError("error.claimcreationfail"));
	         saveErrors(request, errors);
         }
           else{
             errors = new ActionErrors();
	         errors.add("Error", new ActionError("error.claimcreationSuccess"));
	         saveErrors(request, errors);
        }
		//Success
      resetToken(request);
      objForm.getPreListData();
      objForm.getDocPreListData();
      request.setAttribute("DOCLIST","");
      return mapping.findForward("success");
  }
  
  /*
   * this method is used for showing   the ClaimDetails to Create the Claim
   */     
 public ActionForward createClaim(ActionMapping mapping,
          ActionForm form,
          HttpServletRequest request,
          HttpServletResponse response)
      throws TPlusException,Exception {
	  
	   
	 DisputeClaimSetupForm objForm = (DisputeClaimSetupForm)form;
	  // DTO Creation
     DisputeClaimFormDto objDto = new DisputeClaimFormDto();
     
         try {
            
        	BeanUtils.copyProperties(objDto,objForm);
            
        // Action execution
          DisputeClaimFormManager objManager = new DisputeClaimFormManager();
          objDto = objManager.createClaim(objDto);
                  
          BeanUtils.copyProperties(objForm,objDto);
            
         } catch (Exception e){
           	  System.out.println("Error converting to form bean in BranchDispatchAction in change method: " + e.getMessage());
              throw new TPlusException("Could not populate the form bean in BranchDispatchActionchange method: " + e);
            }
               
          //Success
         saveToken(request);
         objForm.getPreListData();
         return mapping.findForward("success");
    }  
  

}
