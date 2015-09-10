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
import org.transinfo.cacis.controller.riskmanagement.CardholderLimitAdjustmentManager;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.CardholderLimitAdjustmentDto;
import org.transinfo.cacis.formbean.riskmanagement.CardholderLimitAdjustment;

public class CardholderLimitAdjustmentDispatchAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addmethod()");
		CardholderLimitAdjustmentManager objManager = new CardholderLimitAdjustmentManager();
		CardholderLimitAdjustmentDto objDto = new CardholderLimitAdjustmentDto();
		CardholderLimitAdjustment objForm = (CardholderLimitAdjustment) form;
		objForm.getPreListData();
		boolean boolAdd = true;
		boolean overlap = false;
		ActionErrors errors = null;

		// DTO Creation
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: "
					+ e);
		}

		// to avoid start date and end date overlaping...
		// if overlap, give error message and allow user to change
		// if overlap => overlap = true
		overlap = objManager.validate(objDto, BaseDAO.ADD);

		if (overlap) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.overlap"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		} else {
			// Form Validations
			errors = objForm.validate(mapping, request);
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				request.setAttribute("ACTION", "add");
				return mapping.getInputForward();
			}

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
		CardholderLimitAdjustment objForm = (CardholderLimitAdjustment) form;
		objForm.getPreListData();
		CardholderLimitAdjustmentDto objDto = new CardholderLimitAdjustmentDto();
		CardholderLimitAdjustmentManager objManager = new CardholderLimitAdjustmentManager();
		ActionErrors errors = null;
		boolean overlap = false;
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
		
		// to avoid start date and end date overlaping...
		// if overlap, give error message and allow user to change
		// slightly different from the add validate... NOT =:temporarylimitid
		// if overlap => overlap = true
		overlap = objManager.validate(objDto, BaseDAO.UPDATE);
		if(overlap){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.overlap"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		} else {
			// Form Validations
			errors = objForm.validate(mapping, request);
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.getInputForward();
			}

			// Action Execution
			boolUpdate = objManager.update(objDto);	
		}


		if (!boolUpdate) {
			System.out
					.println("CardholderLimitAdjustmentDispatchAction: update record fail");
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
				.println("CardholderLimitAdjustmentDispatchAction:update() successful");
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
		CardholderLimitAdjustment objForm = (CardholderLimitAdjustment) form;
		objForm.getPreListData();

		// DTO Creation
		CardholderLimitAdjustmentDto objDto = new CardholderLimitAdjustmentDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		CardholderLimitAdjustmentManager objManager = new CardholderLimitAdjustmentManager();

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
		CardholderLimitAdjustmentManager objManager = new CardholderLimitAdjustmentManager();
		CardholderLimitAdjustment objForm = (CardholderLimitAdjustment) form;
		objForm.getPreListData();
		// DTO Creation
		CardholderLimitAdjustmentDto objDto = new CardholderLimitAdjustmentDto();
		objDto = objManager.get(request.getParameter("temporaryLimitId"));
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
		CardholderLimitAdjustment objOldForm = (CardholderLimitAdjustment) form;
		CardholderLimitAdjustment objForm = new CardholderLimitAdjustment();
		try {
			BeanUtils.copyProperties(objOldForm, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
		}
		return mapping.findForward("listPage");
	}
}
