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
import org.transinfo.cacis.controller.authorization.BlackListCardManager;
import org.transinfo.cacis.controller.authorization.BlockMerchantManager;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;
import org.transinfo.cacis.dto.authorization.BlockMerchantDto;
import org.transinfo.cacis.formbean.authorization.BlackListCardForm;
import org.transinfo.cacis.formbean.authorization.BlockMerchantForm;
import org.transinfo.cacis.formbean.authorization.BlockMerchantSearchForm;

public class BlockMerchantFormDispatchAction extends BaseDispatchAction {

    
        public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in BlockMerchantFormDispatchAction: addNewmethod()");

		ActionErrors errors = null;

		// Token Validation
	      /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
		//BlockMerchantSearchForm
		BlockMerchantForm  objNewForm=new BlockMerchantForm();
		BlockMerchantForm  objForm= (BlockMerchantForm)form;
		
                //BlackListCardForm objNewForm = new BlackListCardForm();
                //BlackListCardForm objForm = (BlackListCardForm) form;
                try
		{
			BeanUtils.copyProperties(objForm, objNewForm);
                        
                } catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
                
		objForm.getPreListData();

	 	System.out.println("Add New Successful");
		// Success

		//resetToken(request);
		return mapping.findForward("change");

        }    
    
        
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in BlockMerchantFormDispatchAction: add method()");

		ActionErrors errors = null;

		// Token Validation
		/*if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		}*/


		// Form Validations
		BlockMerchantForm objForm = (BlockMerchantForm) form;
		//BlackListCardForm objForm = (BlackListCardForm) form;
                objForm.getPreListData();
                
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","add");
			return mapping.getInputForward();
		}

		// DTO Creation
		BlockMerchantDto objDto=new BlockMerchantDto();
		
		//BlackListCardDto objDto = new BlackListCardDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);
         } catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		//BlackListCardManager objManager = new BlackListCardManager();
		BlockMerchantManager objManager =new BlockMerchantManager();
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
		//No need
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
		//BlackListCardForm objForm = (BlackListCardForm) form;
		BlockMerchantForm objForm =(BlockMerchantForm) form;
                //objForm.getPreListData();
                
		// DTO Creation
		//BlackListCardDto objDto = new BlackListCardDto();
		BlockMerchantDto objDto = new BlockMerchantDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);
			
		}
		catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		//BlackListCardManager objManager = new BlackListCardManager();
		BlockMerchantManager objManager =new BlockMerchantManager();
		
		boolean boolDlete = objManager.delete(objDto);
		String nextaction = "delete";

		if (!boolDlete)
		{

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateStatusfailed"));
			saveErrors(request, errors);
			nextaction = "success";
			request.setAttribute("ACTION","update");
			System.out.println("I am in boolDlete"+boolDlete);
                        
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateStatusSuccess"));
			saveErrors(request, errors);
                        return mapping.findForward(nextaction);
		}

		System.out.println("Record delete" + boolDlete);
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward(nextaction);
	}


}

