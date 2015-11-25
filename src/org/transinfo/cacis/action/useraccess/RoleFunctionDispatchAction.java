package org.transinfo.cacis.action.useraccess;

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
import org.transinfo.cacis.controller.useraccess.RoleFunctionManager;
import org.transinfo.cacis.dto.useraccess.RoleFunctionSetDto;
import org.transinfo.cacis.dto.useraccess.RoleMasterDto;
import org.transinfo.cacis.formbean.useraccess.RoleFunctionSetupForm;


public class RoleFunctionDispatchAction extends BaseDispatchAction {


       public ActionForward search(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
                          throws TPlusException,Exception {

            int pageNo=0;
            System.out.println("in Search");
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

//    	RoleFunctionSetupForm objForm = (RoleFunctionSetupForm) form;
	 	RoleFunctionSetupForm objForm = new RoleFunctionSetupForm();
        RoleMasterDto objDto = new RoleMasterDto();
        objForm.getPreListData();

        try
        {
            BeanUtils.copyProperties(objDto, objForm);
            objDto.id.setIssuerId((String)request.getSession().getAttribute("ISSUER"));
        }
        catch (Exception e)
        {
           System.out.println("Error converting to form bean: " + e);
           throw new TPlusException("Could not populate the form bean: " + e);
        }

        RoleFunctionManager objManager = new RoleFunctionManager();
        Collection roleFunctionForm = objManager.search(objDto,pageNo);

		request.setAttribute("SEARCHLIST",roleFunctionForm);
		request.setAttribute("PAGENO",new Integer(pageNo).toString());

        System.out.println("RoleFunctionFormAction: execute() successful");

        return mapping.findForward("success");
    }


        public ActionForward addNew(ActionMapping mapping, ActionForm form,
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

		RoleFunctionSetupForm objForm = (RoleFunctionSetupForm) form;                
                try
		{
			objForm.setRoleId("");
                        objForm.setRoleDesc("");
                        objForm.setStatus("");
            objForm.setUserType("");
                        
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
		RoleFunctionSetupForm objForm = (RoleFunctionSetupForm) form;
                objForm.getPreListData();

		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","add");
			return mapping.getInputForward();
		}

		// DTO Creation
		RoleMasterDto objDto = new RoleMasterDto();
		try
		{

			BeanUtils.copyProperties(objDto, objForm);
                        objDto.id.setIssuerId(objForm.getIssuerId());
                        objDto.id.setRoleId(objForm.getRoleId());

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

                RoleFunctionSetDto functionSetDto = new RoleFunctionSetDto();
                String[] selList = objForm.getSelFuncList();

                if(selList != null)
                {

                for(int intIndex=0; intIndex<selList.length; intIndex++){
                  System.out.println("Selected List:==>"+selList[intIndex]);
                  objDto.getRoleFunctionSet().put(selList[intIndex], functionSetDto);
                }

                functionSetDto.id.setIssuerId(objForm.getIssuerId());
                functionSetDto.id.setRoleId(objForm.getRoleId());
                functionSetDto.setUserId(objForm.getUserId());

                }else
                {
                    System.out.println("selList is Null");
                }

		// Action Execution
		RoleFunctionManager objManager = new RoleFunctionManager();
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
		RoleFunctionSetupForm objForm = (RoleFunctionSetupForm) form;
                objForm.getPreListData();
                
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","update");
			return mapping.getInputForward();
		}

		// DTO Creation
		RoleMasterDto objDto = new RoleMasterDto();
		try
		{

			BeanUtils.copyProperties(objDto, objForm);
                        objDto.id.setIssuerId(objForm.getIssuerId());
                        objDto.id.setRoleId(objForm.getRoleId());



		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

                RoleFunctionSetDto functionSetDto = new RoleFunctionSetDto();
                String[] selList = objForm.getSelFuncList();

                if(selList != null)
                {

                for(int intIndex=0; intIndex<selList.length; intIndex++){
                  System.out.println("Selected List:==>"+selList[intIndex]);
                  objDto.getRoleFunctionSet().put(selList[intIndex], functionSetDto);
                }

                functionSetDto.id.setIssuerId(objForm.getIssuerId());
                functionSetDto.id.setRoleId(objForm.getRoleId());
                functionSetDto.setUserId(objForm.getUserId());

                }else{
                         System.out.println("selList is Null");
                }

		// Action Execution
		RoleFunctionManager objManager = new RoleFunctionManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate)
		{
			System.out.println("RoleFunctionDispatchAction: update record fail");
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

		System.out.println("RoleFunctionDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("change");
	}


	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		RoleFunctionManager objManager = new RoleFunctionManager();
		RoleFunctionSetupForm objForm = (RoleFunctionSetupForm) form;
                objForm.getPreListData();

		// DTO Creation
		RoleMasterDto objDto = new RoleMasterDto();
		objDto = objManager.get(request.getParameter("issuerId"), request.getParameter("roleId"),request.getParameter("userType"));
		try
		{
			BeanUtils.copyProperties(objForm, objDto);
                        objForm.setLastUpdatedDate(objDto.getUpdatedDate().toString());

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

