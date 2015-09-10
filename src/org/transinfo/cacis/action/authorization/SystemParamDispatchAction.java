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
import org.transinfo.cacis.controller.authorization.SystemParamManager;
import org.transinfo.cacis.dto.authorization.SystemParamDto;
import org.transinfo.cacis.formbean.authorization.SystemParamForm;

@SuppressWarnings("deprecation")
public class SystemParamDispatchAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: upload method()");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		SystemParamForm objForm = (SystemParamForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		SystemParamDto objDto = new SystemParamDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		SystemParamManager objManager = new SystemParamManager();
		boolean boolAdd = objManager.add(objDto);

		if (!boolAdd) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("Record Added" + boolAdd);
		// Success

		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dispatchAction update method");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		SystemParamForm objForm = (SystemParamForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// DTO Creation
		SystemParamDto objDto = new SystemParamDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		SystemParamManager objManager = new SystemParamManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			System.out.println("SystemParamDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("SystemParamDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		SystemParamManager objManager = new SystemParamManager();
		SystemParamForm objForm = (SystemParamForm) form;

		// DTO Creation
		SystemParamDto objDto = new SystemParamDto();
		objDto = objManager.get("ASP");

		try {
			if (objDto != null) {
				System.out.println("Setting form properties.......");

				BeanUtils.copyProperties(objForm, objDto);
				request.setAttribute("ACTION", "update");
			} else {
				request.setAttribute("ACTION", "add");
			}
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}

}
