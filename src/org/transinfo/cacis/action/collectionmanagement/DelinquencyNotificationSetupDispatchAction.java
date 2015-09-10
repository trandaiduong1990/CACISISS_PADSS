package org.transinfo.cacis.action.collectionmanagement;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSetupForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class DelinquencyNotificationSetupDispatchAction extends
		BaseDispatchAction {
	private Logger logger = Logger
			.getLogger(DelinquencyNotificationSetupDispatchAction.class);

	/**
	 * this method is used to showing add new DelinquencyPolicy
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		DelinquencyNotificationSetupForm objOldForm = (DelinquencyNotificationSetupForm) form;
		DelinquencyNotificationSetupForm objForm = new DelinquencyNotificationSetupForm();

		try {
			BeanUtils.copyProperties(objOldForm, objForm);

		} catch (Exception ex) {
			System.out
					.println("Error converting to form bean in DelinquencyNotificationSetupDispatchAction addNew : "
							+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in DelinquencyNotificationSetupDispatchAction addNew : "
							+ ex);
		}
		objOldForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));
		// Success
		objOldForm.getPreListData();
		return mapping.findForward("success");
	}

	/**
	 * this method is used for creating the
	 * DelinquencyNotificationSetupDispatchAction to add
	 */
	@SuppressWarnings("deprecation")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		DelinquencyNotificationSetupForm objForm = (DelinquencyNotificationSetupForm) form;
		errors = objForm.validate(mapping, request);
		DelinquencyNotificationSetupDto objDto = new DelinquencyNotificationSetupDto();

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		DelinquencyNotificationSetupManager objManager = new DelinquencyNotificationSetupManager();

		// manually validation
		boolean checkExist = objManager.checkExist(objForm, 0);
		if (!checkExist) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("co.agingSevError"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setStatus("N");
			objDto.setLastUpdatedDate(new Date());
			objDto.setLastUpdatedBy(objForm.getUserId());
			if (objDto.getNotificationId() == null
					|| "".equals(objDto.getNotificationId())) {
				objDto.setNotificationId(IdsGenartion
						.GenerateDelinquencyNotificationId());
			}
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in DelinquencyNotificationSetupDispatchAction create method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in DelinquencyNotificationSetupDispatchAction create method "
							+ ex);
		}

		// Action Execution
		String nextAttribute = "cancel";

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

		// Success
		objForm.getPreListData();
		objForm.setPolicyName(objManager.getPolicyName(objForm.getPolicyId()));
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

		DelinquencyNotificationSetupManager objManager = new DelinquencyNotificationSetupManager();
		DelinquencyNotificationSetupForm objForm = (DelinquencyNotificationSetupForm) form;

		try {
			DelinquencyNotificationSetupDto objDto = objManager
					.getNotificationDetail(request.getParameter("id"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setPolicyName(objManager.getPolicyName(objForm
					.getPolicyId()));
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in DelinquencyNotificationSetupDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in DelinquencyNotificationSetupDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		objForm.getPreListData();
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for update the
	 * DelinquencyNotificationSetupDispatchAction
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
		DelinquencyNotificationSetupForm objForm = (DelinquencyNotificationSetupForm) form;

		// Action Execution
		DelinquencyNotificationSetupManager objManager = new DelinquencyNotificationSetupManager();
		DelinquencyNotificationSetupDto objDto = new DelinquencyNotificationSetupDto();
		
		objForm.setPolicyName(objManager.getPolicyName(objForm.getPolicyId()));

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedBy(objForm.getUserId());
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in DelinquencyNotificationSetupDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in DelinquencyNotificationSetupDispatchAction update method:"
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
		DelinquencyNotificationSetupForm objForm = (DelinquencyNotificationSetupForm) form;

		// Dto Creation
		DelinquencyNotificationSetupDto objDto = new DelinquencyNotificationSetupDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setStatus("D");
			objDto.setLastUpdatedBy(objForm.getUserId());
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in DelinquencyNotificationSetupDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in DelinquencyNotificationSetupDispatchAction in delete method: "
							+ e);
		}

		// Action Execution
		DelinquencyNotificationSetupManager objManager = new DelinquencyNotificationSetupManager();

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
