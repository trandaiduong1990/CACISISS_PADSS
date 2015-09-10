package org.transinfo.cacis.action.riskmanagement;

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
import org.transinfo.cacis.controller.riskmanagement.WithdrawalLimitRulesManager;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.WithdrawalLimitRulesDto;
import org.transinfo.cacis.formbean.riskmanagement.WithdrawalLimitRules;

public class WithdrawalLimitRulesDispatchAction extends BaseDispatchAction {
	
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			WithdrawalLimitRules objOldForm = (WithdrawalLimitRules) form;
			WithdrawalLimitRules objForm = new WithdrawalLimitRules();
			objForm.setIssuerId(objOldForm.getIssuerId());
			try {
				BeanUtils.copyProperties(objOldForm, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: "
						+ e);
			}
			objOldForm.getPreListData();
		} catch (Exception exp) {
			System.out.println("Error converting to form bean: " + exp);
		}
		request.setAttribute("ACTION", "add");
		return mapping.findForward("success");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		System.out.println(" we are in DispatchAction: addmethod()");
		WithdrawalLimitRulesManager objManager = new WithdrawalLimitRulesManager();
		WithdrawalLimitRulesDto objDto = new WithdrawalLimitRulesDto();
		WithdrawalLimitRules objForm = (WithdrawalLimitRules) form;
		objForm.getPreListData();
		boolean boolAdd = true;
		boolean idDuplicate = false;
		ActionErrors errors = null;
		
		// Form Validations
		errors = objForm.validate(mapping, request);
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		System.out.println("proceed");
		// DTO Creation
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: "
					+ e);
		}

		// to avoid violation of the primary key, if ruleId duplicate then
		// give error message and allow user to change
		// id exist => idDuplicate = true
		idDuplicate = objManager.validate(objDto, BaseDAO.ADD);

		if (idDuplicate) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.idduplicate"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		} else {
			// Action Execution
			boolAdd = objManager.add(objDto);
		}

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
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		System.out.println("we are in dipatchAction update method");
		WithdrawalLimitRules objForm = (WithdrawalLimitRules) form;
		objForm.getPreListData();
		WithdrawalLimitRulesDto objDto = new WithdrawalLimitRulesDto();
		WithdrawalLimitRulesManager objManager = new WithdrawalLimitRulesManager();
		ActionErrors errors = null;
		boolean idExist = false;
		boolean boolUpdate = false;
		
		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}
		
		// DTO Creation
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		
		idExist = objManager.validate(objDto, BaseDAO.UPDATE);
		if(idExist){
			// Form Validations
			errors = objForm.validate(mapping, request);
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.getInputForward();
			}

			// Action Execution
			boolUpdate = objManager.update(objDto);	
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.idnotexist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		if (!boolUpdate) {
			System.out
					.println("WithdrawalLimitRulesDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out
				.println("WithdrawalLimitRulesDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		System.out.println("we are in dipatchAction delete method");
		ActionErrors errors = null;

		// Form Validations
		WithdrawalLimitRules objForm = (WithdrawalLimitRules) form;
		objForm.getPreListData();

		// DTO Creation
		WithdrawalLimitRulesDto objDto = new WithdrawalLimitRulesDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		WithdrawalLimitRulesManager objManager = new WithdrawalLimitRulesManager();

		boolean boolDlete = objManager.delete(objDto);
		String nextaction = "listPage";

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
		}

		System.out.println("Record delete" + boolDlete);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		WithdrawalLimitRulesManager objManager = new WithdrawalLimitRulesManager();
		WithdrawalLimitRules objForm = (WithdrawalLimitRules) form;
		objForm.getPreListData();
		// DTO Creation
		WithdrawalLimitRulesDto objDto = new WithdrawalLimitRulesDto();
		objDto = objManager.get(request.getParameter("ruleId"));
		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}
	
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WithdrawalLimitRules objOldForm = (WithdrawalLimitRules) form;
		WithdrawalLimitRules objForm = new WithdrawalLimitRules();
		try {
			BeanUtils.copyProperties(objOldForm, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
		}
		return mapping.findForward("listPage");
	}
}
