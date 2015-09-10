package org.transinfo.cacis.action.authorization;

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
import org.transinfo.cacis.controller.authorization.ServerParamManager;
import org.transinfo.cacis.dto.authorization.ServerParamFormDto;
import org.transinfo.cacis.dto.authorization.ServerParamSearchDto;
import org.transinfo.cacis.formbean.authorization.ServerParamForm;
import org.transinfo.cacis.formbean.authorization.ServerParamSearchForm;

public class ServerParamDispatchAction extends BaseDispatchAction {

        
      
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
		ServerParamForm objForm = (ServerParamForm) form;
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","update");
			return mapping.getInputForward();
		}

		// DTO Creation
		ServerParamFormDto objDto = new ServerParamFormDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		ServerParamManager objManager = new ServerParamManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate)
		{
			System.out.println("ServerParamDispatchAction: update record fail");
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

		System.out.println("ServerParamDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("success");
	}
        
        
                
        
	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

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
                
        ServerParamSearchForm objForm = (ServerParamSearchForm) form;            
        ServerParamSearchDto objDto = new ServerParamSearchDto();
                        
        try
        {
            BeanUtils.copyProperties(objDto, objForm);
        }
        catch (Exception e)
        {            
           System.out.println("Error converting to form bean: " + e);           
           throw new TPlusException("Could not populate the form bean: " + e);
        }
        ServerParamManager objManager = new ServerParamManager();
        Collection tagFieldForm = objManager.get(objDto,pageNo);

		request.setAttribute("SEARCHLIST", tagFieldForm);
		request.setAttribute("PAGENO",new Integer(pageNo).toString());

		//System.out.println("LIST SIZE"+tagFieldForm.size());
        System.out.println("TagFieldFormAction: execute() successful");

        return mapping.findForward("success");
    }
   
}

