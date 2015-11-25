package org.transinfo.cacis.action.useraccess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.useraccess.AssignUserManager;
import org.transinfo.cacis.controller.useraccess.UserSetupManager;
import org.transinfo.cacis.dto.useraccess.AssignUserDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.useraccess.AssignUserForm;
import org.transinfo.cacis.formbean.useraccess.UserSetupForm;
import org.transinfo.cacis.util.EncryptUtility;

@SuppressWarnings("deprecation")
public class AssignUserDispatchAction extends BaseDispatchAction {

	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerid = (String) request.getSession().getAttribute("ISSUER");

		System.out.println(" we are in DispatchAction: addNew method()");
		
		AssignUserForm objNewForm = new AssignUserForm();
		AssignUserForm objForm = (AssignUserForm) form;
		
		BeanUtils.copyProperties(objForm, objNewForm);
		
		objForm.setIssuerId(issuerid);
		objForm.getPreListData();
		request.setAttribute("ACTION", "add");
		return mapping.findForward("change");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: add method()");

		ActionErrors errors = null;

		AssignUserForm objForm = (AssignUserForm) form;
		objForm.getPreListData();
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		if(!objForm.getLower().equals("") || !objForm.getUpper().equals("")) {
			int lower = !objForm.getLower().equals("") ?Integer.parseInt(objForm.getLower()):0;
			int upper = !objForm.getUpper().equals("") ?Integer.parseInt(objForm.getUpper()):0;
			if((!objForm.getUpper().equals("") && objForm.getLower().equals("")) || (lower >= upper)) {
				errors = new ActionErrors();
				errors.add("Compare", new ActionMessage("assignuser.compare") );
				saveErrors(request, errors);
				request.setAttribute("ACTION", "add");
				return mapping.getInputForward();
			}
		}
		// DTO Creation
		AssignUserDto objDto = new AssignUserDto();
		try {
			String userId = (String) request.getSession(false).getAttribute("USERID");
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedBy(userId);
			objDto.setLastUpdatedDate(new Date());
			objDto.setStatus("A");
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		AssignUserManager objManager = new AssignUserManager();
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


	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction delete method");

		// Form Validations
		AssignUserForm objForm = (AssignUserForm) form;
		objForm.getPreListData();
		
		ActionErrors errors = null;

		// DTO Creation
		AssignUserDto objDto = new AssignUserDto();

		// Action Execution
		AssignUserManager objManager = new AssignUserManager();
		
		objDto = objManager.getAssignUserForm(objForm.getAssignId());
		objDto.setLastUpdatedDate(new Date());
		objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
		objDto.setStatus("D");

		boolean boolDlete = objManager.update(objDto);
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
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction update method");

		ActionErrors errors = null;

		// Form Validations
		AssignUserForm objForm = (AssignUserForm) form;
		objForm.getPreListData();
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		if(!objForm.getLower().equals("") || !objForm.getUpper().equals("")) {
			int lower = !objForm.getLower().equals("") ?Integer.parseInt(objForm.getLower()):0;
			int upper = !objForm.getUpper().equals("") ?Integer.parseInt(objForm.getUpper()):0;
			if((!objForm.getUpper().equals("") && objForm.getLower().equals("")) || (lower >= upper)) {
				errors = new ActionErrors();
				errors.add("Compare", new ActionMessage("assignuser.compare") );
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.getInputForward();
			}
		}
		// DTO Creation
		AssignUserDto objDto = new AssignUserDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedDate(new Date());
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setStatus("A");
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		AssignUserManager objManager = new AssignUserManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			System.out.println("AssignUserDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("AssignUserDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("change");
	}


	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String assignId = (String) request.getParameter("assignId");
		AssignUserManager objManager = new AssignUserManager();
		AssignUserForm objForm = (AssignUserForm) form;
		objForm.setAssignId(assignId);
		
		try {
			AssignUserDto objDto = new AssignUserDto();
			objDto = objManager.getAssignUserForm(assignId);
			BeanUtils.copyProperties(objForm, objDto);
			objForm.getPreListData();
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Success
		//saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("change");
	}

}
