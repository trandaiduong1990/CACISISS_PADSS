package org.transinfo.cacis.action.collectionmanagement;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.controller.batchprocess.CardBatchManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyPolicyManager;
import org.transinfo.cacis.dataacess.daoimpl.oracle.batchprocess.CardBatchDAOImpl;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicySearchDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.formbean.batchprocess.CardBatchForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyPolicyForm;
import org.transinfo.cacis.util.DateUtil;

public class DelinquencyPolicyDispatchAction extends BaseDispatchAction {
	private Logger logger = Logger
			.getLogger(DelinquencyPolicyDispatchAction.class);

	/**
	 * this method is used to showing add new DelinquencyPolicy
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		DelinquencyPolicyForm objOldForm = (DelinquencyPolicyForm) form;
		DelinquencyPolicyForm objForm = new DelinquencyPolicyForm();

		try {
			BeanUtils.copyProperties(objOldForm, objForm);

		} catch (Exception ex) {
			System.out
					.println("Error converting to form bean in CardBatchDispatchAction addNew : "
							+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in CardBatchDispatchAction addNew : "
							+ ex);
		}

		// Success
		return mapping.findForward("success");
	}

	/**
	 * this method is used for creating the DelinquencyPolicyDispatchAction to
	 * add
	 */
	@SuppressWarnings("deprecation")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		DelinquencyPolicyForm objForm = (DelinquencyPolicyForm) form;
		errors = objForm.validate(mapping, request);
		DelinquencyPolicyDto objDto = new DelinquencyPolicyDto();

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setIssuerId((String) request.getSession(false).getAttribute(
					"ISSUER"));
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in DelinquencyPolicyDispatchAction create method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in DelinquencyPolicyDispatchAction create method "
							+ ex);
		}

		// Action Execution
		DelinquencyPolicyManager objManager = new DelinquencyPolicyManager();
		String nextAttribute = "cancel";

		// Check record
		boolean recExistRes = objManager.validate(objDto);

		if (!recExistRes) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.delinquencyPolicyExist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		} else {
			objDto.setStatus("A");
			objDto.setLastUpdatedDate(new Date());
			objDto.setLastUpdatedBy(objForm.getUserId());
			boolean boolCreated = objManager.add(objDto);

			if (!boolCreated) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
				nextAttribute = "add";
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				nextAttribute = "cancel";
			}
		}

		// Success
		request.setAttribute("ACTION", nextAttribute);
		return mapping.findForward("success");
	}

	/**
	 * this method is used for showing the DelinquencyPolicyDispatchAction to
	 * update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		DelinquencyPolicyManager objManager = new DelinquencyPolicyManager();
		DelinquencyPolicyForm objForm = (DelinquencyPolicyForm) form;

		try {
			DelinquencyPolicyDto objDto = objManager.getPolicyDetail(request
					.getParameter("id"));
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in DelinquencyPolicyDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in DelinquencyPolicyDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for update the DelinquencyPolicyDispatchAction
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// Form Validations
		DelinquencyPolicyForm objForm = (DelinquencyPolicyForm) form;

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// Action Execution
		DelinquencyPolicyManager objManager = new DelinquencyPolicyManager();
		DelinquencyPolicyDto objDto = new DelinquencyPolicyDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedBy(objForm.getUserId());
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in DelinquencyPolicyDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in DelinquencyPolicyDispatchAction update method:"
							+ ex);
		}

		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for delete the CardBatchDispatchAction
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// Form Validations
		DelinquencyPolicyForm objForm = (DelinquencyPolicyForm) form;

		// Dto Creation
		DelinquencyPolicyDto objDto = new DelinquencyPolicyDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setStatus("D");
			objDto.setLastUpdatedBy(objForm.getUserId());
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in DelinquencyPolicyDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in DelinquencyPolicyDispatchAction in delete method: "
							+ e);
		}

		// Action Execution
		DelinquencyPolicyManager objManager = new DelinquencyPolicyManager();

		String nextaction = "delete";
		boolean boolDelete = objManager.update(objDto);

		if (!boolDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}
}
