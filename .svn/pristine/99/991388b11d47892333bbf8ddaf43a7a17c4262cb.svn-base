package org.transinfo.cacis.action.switching;

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
import org.transinfo.cacis.controller.switching.SwitchManager;
import org.transinfo.cacis.dto.switching.SwitchDto;
import org.transinfo.cacis.dto.switching.SwitchSearchDto;
import org.transinfo.cacis.formbean.switching.SwitchForm;
import org.transinfo.cacis.formbean.switching.SwitchSearchForm;

@SuppressWarnings( { "deprecation", "unchecked" })
public class SwitchDispatchAction extends BaseDispatchAction {

	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		int pageNo = 0;

		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("NEXT")) {
			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) + 1;

			}
		}
		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("PREV")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) - 1;
			}
		}

		SwitchSearchForm objForm = (SwitchSearchForm) form;
		SwitchSearchDto objDto = new SwitchSearchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		SwitchManager objManager = new SwitchManager();
		Collection switchForm = objManager.getSwitchList(objDto, pageNo);

		request.setAttribute("SEARCHLIST", switchForm);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		// System.out.println("LIST SIZE"+switchForm.size());
		System.out.println("SwitchDispatchAction: get() successful");

		return mapping.findForward("success");
	}

	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addnew method()");

		// ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		SwitchForm objNewForm = new SwitchForm();
		SwitchForm objForm = (SwitchForm) form;
		try {
			BeanUtils.copyProperties(objForm, objNewForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		objForm.getPreListData();

		System.out.println("Add New Successful");
		// Success

		// resetToken(request);
		return mapping.findForward("change");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addmethod()");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		SwitchForm objForm = (SwitchForm) form;
		objForm.getPreListData();

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		SwitchDto objDto = new SwitchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		SwitchManager objManager = new SwitchManager();
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
		return mapping.findForward("change");
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
		SwitchForm objForm = (SwitchForm) form;
		objForm.getPreListData();

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// DTO Creation
		SwitchDto objDto = new SwitchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		SwitchManager objManager = new SwitchManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			System.out.println("SwitchDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("AlertFormDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("change");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction reject method");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		SwitchForm objForm = (SwitchForm) form;
		objForm.getPreListData();

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// DTO Creation
		SwitchDto objDto = new SwitchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		SwitchManager objManager = new SwitchManager();

		boolean boolDlete = objManager.delete(objDto);
		String nextaction = "delete";

		if (!boolDlete) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "success";
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
			return mapping.findForward(nextaction);
		}

		System.out.println("Record Delete" + boolDlete);
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		SwitchManager objManager = new SwitchManager();
		SwitchForm objForm = (SwitchForm) form;
		objForm.getPreListData();

		// DTO Creation
		SwitchDto objDto = new SwitchDto();
		objDto = objManager.get(request.getParameter("swType"));
		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("change");
	}

}
