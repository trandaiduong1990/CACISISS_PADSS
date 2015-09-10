package org.transinfo.cacis.action.useraccess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.useraccess.PasswordChangeManager;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.useraccess.PasswordChangeForm;
import org.transinfo.cacis.util.EncryptUtility;

public class PasswordDispatchAction extends BaseDispatchAction {
    
          	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dispatchAction update method");

		ActionErrors errors = null;
              
		// Token Validation
	     /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
		PasswordChangeForm objForm = (PasswordChangeForm) form;
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","update");
                        if(objForm.getFirstTimeLogin()!=null && objForm.getFirstTimeLogin().equals("Y")){
                            return mapping.findForward("ftlchange");
                        }else{
			   return mapping.getInputForward();
                        }
		}

		PasswordChangeManager objManager = new PasswordChangeManager();		
                UserMasterDto objDto = null;
                boolean boolUpdate = false;
		try
		{
                        		                              
                        System.out.println("IssuerId from Dispatch==>"+request.getParameter("issuerId"));  
                        System.out.println("UserId from Dispatch==>"+request.getParameter("userId"));
                        
                        objDto = new UserMasterDto();
                        objDto = objManager.get(request.getParameter("issuerId"), request.getParameter("userId"));
                                                                     
                       if(objDto!=null)
                       {
                        if(objForm.getNewPassword()!=null && objForm.getNewPassword().equals(objForm.getConfirmPwd()))
                        {                            
                            if(objForm.getOldPassword()!=null && EncryptUtility.encrPassword(objForm.getOldPassword()).equals(objDto.getPassword()))
                            {                               
                                objDto.setPassword(EncryptUtility.encrPassword(objForm.getNewPassword()));
                                objDto.setHintQuestion(objForm.getHintQuestion());
                                objDto.setHintAnswer(objForm.getHintAnswer());
                                objDto.setLastUpdatedBy(objForm.getUserId());  
                                if(objForm.getFirstTimeLogin()!=null && objForm.getFirstTimeLogin().equals("Y"))
                                {
                                    System.out.println("First Time login.....yes");
                                    objDto.setFirstTimeLogin('N');
                                }
                                System.out.println("Updating user master........");
                                boolUpdate = objManager.update(objDto);
                                
                            }else{
                                   System.out.println("Incorrect Password");
                                   errors = new ActionErrors();
                                   errors.add("Error", new ActionError("error.incorrect"));
                                   saveErrors(request, errors);
                                   request.setAttribute("ACTION","update");
                                   return mapping.findForward("failure");	
                            }
                            
                        }else{  
                            System.out.println("Password mismatch with confirm password");
                            errors = new ActionErrors();
                            errors.add("Error", new ActionError("error.mismatch"));
                            saveErrors(request, errors);
                            request.setAttribute("ACTION","update");
                            return mapping.findForward("failure");	
                       } 
                       
                    }else{
                        System.out.println("No record found with this issuerId or userId");
                        errors = new ActionErrors();
			errors.add("Error", new ActionError("error.norecordfound"));
			saveErrors(request, errors);
                        request.setAttribute("ACTION","update");
                       return mapping.findForward("failure");	
                    }
                        
		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		               		
		if (!boolUpdate)
		{
			System.out.println("PasswordDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
                        saveErrors(request, errors);
			request.setAttribute("ACTION","update");
			
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Success", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
                        System.out.println("PasswordDispatchAction:update() successful");
		}
		
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("success");
	}
        
      
	
}

