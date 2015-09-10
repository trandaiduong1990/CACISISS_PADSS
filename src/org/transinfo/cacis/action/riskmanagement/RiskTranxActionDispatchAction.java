package org.transinfo.cacis.action.riskmanagement;

import java.util.Collection;

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
import org.transinfo.cacis.controller.riskmanagement.RiskTranxActionManager;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxActionDto;
import org.transinfo.cacis.formbean.riskmanagement.RiskTranxActionForm;


public class RiskTranxActionDispatchAction extends BaseDispatchAction {

       
        public ActionForward search(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                         throws TPlusException,Exception {


   		int pageNo=0;

   		if(request.getParameter("mode")!=null && ((String)request.getParameter("mode")).equals("NEXT"))
		{
			if(request.getParameter("pageNo") != null)
			{
				pageNo = Integer.parseInt((String)request.getParameter("pageNo"))+1;

			}
	 	}
	 	if(request.getParameter("mode")!=null && ((String)request.getParameter("mode")).equals("PREV"))
		{

			if(request.getParameter("pageNo") != null)
			{
				pageNo = Integer.parseInt((String)request.getParameter("pageNo"))-1;
			}
	 	}

            RiskTranxActionForm objForm = (RiskTranxActionForm) form;
            RiskTranxActionDto objDto = new RiskTranxActionDto();

            try
            {
        
                BeanUtils.copyProperties(objDto, objForm);
            
            }
            catch (Exception e)
            {
                System.out.println("Error converting to form bean: " + e);
                throw new TPlusException("Could not populate the form bean: " + e);
            }
            RiskTranxActionManager objManager = new RiskTranxActionManager();
            Collection riskActionForm = objManager.search(objDto,pageNo);

		request.setAttribute("SEARCHLIST", riskActionForm);
		request.setAttribute("PAGENO",new Integer(pageNo).toString());
		

		//System.out.println("LIST SIZE"+userForm.size());
            System.out.println("RiskTranxAction: execute() successful");

            return mapping.findForward("success");
        }
    
         
        public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addNew method()");

		ActionErrors errors = null;

		// Token Validation
	      /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
                RiskTranxActionForm objNewForm = new RiskTranxActionForm();
		RiskTranxActionForm objForm = (RiskTranxActionForm) form;
               
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

		System.out.println(" we are in DispatchAction: addmethod()");

		ActionErrors errors = null;

		// Token Validation
	      /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
		RiskTranxActionForm objForm = (RiskTranxActionForm) form;
                objForm.getPreListData();
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","add");
			return mapping.getInputForward();
		}

		// DTO Creation
		RiskTranxActionDto objDto = new RiskTranxActionDto();
		try
		{                       
			BeanUtils.copyProperties(objDto, objForm);
                                                
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
               
                                                                                                              
		// Action Execution
		RiskTranxActionManager objManager = new RiskTranxActionManager();
		boolean boolAdd = objManager.add(objDto);

		if(!boolAdd)
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("Record Added" + boolAdd);
		// Success

		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("change");
	}



	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in DipatchAction update method");

		ActionErrors errors = null;

		// Token Validation
	     /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
		RiskTranxActionForm objForm = (RiskTranxActionForm) form;
                objForm.getPreListData();
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","update");
			return mapping.getInputForward();
		}

		// DTO Creation
		RiskTranxActionDto objDto = new RiskTranxActionDto();
		try
		{                     
			BeanUtils.copyProperties(objDto, objForm);  
                                                                                                                             
		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		RiskTranxActionManager objManager = new RiskTranxActionManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate)
		{
			System.out.println("RiskTranxActionDispatchAction: update record fail");
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

		System.out.println("RiskTranxActionDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("change");
	}

	
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		RiskTranxActionManager objManager = new RiskTranxActionManager();
		RiskTranxActionForm objForm = (RiskTranxActionForm) form;
                objForm.getPreListData();
                
		// DTO Creation
		RiskTranxActionDto objDto = new RiskTranxActionDto();
                                                           
		objDto = objManager.getRiskActionForm(request.getParameter("riskId"));
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

