package org.transinfo.cacis.action.authorization;

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
import org.transinfo.cacis.controller.authorization.TagFieldFormatManager;
import org.transinfo.cacis.dto.authorization.TagFieldFormatDto;
import org.transinfo.cacis.formbean.authorization.TagFieldFormatForm;

public class TagFieldFormDispatchAction extends BaseDispatchAction {
    
    
        public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addnew method()");

		ActionErrors errors = null;

		// Token Validation
	      /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
                TagFieldFormatForm objNewForm = new TagFieldFormatForm();
		TagFieldFormatForm objForm = (TagFieldFormatForm) form;
                try
		{
			BeanUtils.copyProperties(objForm, objNewForm);
                        
                } catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		

	 	System.out.println("Add New Successful");
		// Success

		//resetToken(request);
		return mapping.findForward("change");

        }
        

	public ActionForward add(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
                                 throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: add method()");

		ActionErrors errors = null;

		// Token Validation
		/*if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		}*/


		// Form Validations
		TagFieldFormatForm objForm = (TagFieldFormatForm) form;
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","add");
			return mapping.getInputForward();
		}

		// DTO Creation
		TagFieldFormatDto objDto = new TagFieldFormatDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);
                       // System.out.println("CardNUMBer="+objForm.getCardNumber()+"  "+objForm.getIssuerId());
                        objDto.setTagName(objForm.getTagName());
                        
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		TagFieldFormatManager objManager = new TagFieldFormatManager();
		boolean boolAdd = objManager.add(objDto);

		if(!boolAdd)
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("Record Added" + boolAdd);
		// Success
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("change");
	}

        
        public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction update method");

		ActionErrors errors = null;

		// Token Validation
	     /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		}
                  */

		// Form Validations
		TagFieldFormatForm objForm = (TagFieldFormatForm) form;
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","update");
			return mapping.getInputForward();
		}

		// DTO Creation
		TagFieldFormatDto objDto = new TagFieldFormatDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		TagFieldFormatManager objManager = new TagFieldFormatManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate)
		{
			System.out.println("TagFieldFormatDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION","update");
			saveErrors(request, errors);
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("TagFieldFormatDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("change");
	}
        
        
        
        public ActionForward delete(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction delete method");

		ActionErrors errors = null;

		// Token Validation
	/*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			System.out.println("I am in Token");
			return mapping.findForward("token");
		} */


		// Form Validations
                TagFieldFormatForm objForm = (TagFieldFormatForm) form;		
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","update");
			System.out.println("I am in Errors");
			return mapping.getInputForward();

		}

		// DTO Creation
		TagFieldFormatDto objDto = new TagFieldFormatDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setTagName(objForm.getTagName());            
		}
		catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		TagFieldFormatManager objManager = new TagFieldFormatManager();

		boolean boolDlete = objManager.delete(objDto);
                System.out.println("boolDlete in Despatch===>"+boolDlete);
		String nextaction = "delete";

		if (!boolDlete)
		{                    
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "success";
			request.setAttribute("ACTION","update");
			System.out.println("I am in boolDlete"+boolDlete);
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
                        System.out.println("Forwarding to next Action......"+nextaction);   
                        objForm.setTagName("");
                        return mapping.findForward(nextaction);
                        //return mapping.getInputForward();
		}

		System.out.println("Record Delete" + boolDlete);
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward(nextaction);
	}
        
        
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		TagFieldFormatManager objManager = new TagFieldFormatManager();
		TagFieldFormatForm objForm = (TagFieldFormatForm) form;

		// DTO Creation
		TagFieldFormatDto objDto = new TagFieldFormatDto();
		objDto = objManager.get(request.getParameter("tagName"));
		try
		{
			BeanUtils.copyProperties(objForm, objDto);
		}
		catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		//Success
		saveToken(request);
		request.setAttribute("ACTION","update");
		return mapping.findForward("change");
	}
        
               
}

