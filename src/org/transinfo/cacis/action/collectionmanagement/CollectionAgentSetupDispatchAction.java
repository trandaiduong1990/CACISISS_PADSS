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
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgentManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSetupForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class CollectionAgentSetupDispatchAction extends
		BaseDispatchAction {
	private Logger logger = Logger
			.getLogger(CollectionAgentSetupDispatchAction.class);

	/**
	 * this method is used to showing add new DelinquencyPolicy
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CollectionAgentSetupForm objOldForm = (CollectionAgentSetupForm) form;
		CollectionAgentSetupForm objForm = new CollectionAgentSetupForm();

		try {
			BeanUtils.copyProperties(objOldForm, objForm);
			objOldForm.getPreListData();
		} catch (Exception ex) {
			System.out
					.println("Error converting to form bean in CollectionAgentSetupDispatchAction addNew : "
							+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in CollectionAgentSetupDispatchAction addNew : "
							+ ex);
		}
		objOldForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));
		// Success
		return mapping.findForward("success");
	}

	/**
	 * this method is used for creating the
	 * CollectionAgentSetupDispatchAction to add
	 */
	@SuppressWarnings("deprecation")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		CollectionAgentSetupForm objForm = (CollectionAgentSetupForm) form;
		errors = objForm.validate(mapping, request);
		CollectionAgentDto objDto = new CollectionAgentDto();

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		CollectionAgentManager objManager = new CollectionAgentManager();

		// manually validation
		boolean checkExist = objManager.checkExist(objForm);
		if (!checkExist) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("collectionageing.exist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setStatus("A");
			objDto.setLastUpdatedDate(new Date());
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgentSetupDispatchAction create method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgentSetupDispatchAction create method "
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
		request.setAttribute("ACTION", nextAttribute);
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	/**
	 * this method is used for showing the DelinquencyPolicyDispatchAction to
	 * update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CollectionAgentManager objManager = new CollectionAgentManager();
		CollectionAgentSetupForm objForm = (CollectionAgentSetupForm) form;

		try {
			CollectionAgentDto objDto = objManager
					.getCollectionAgent(request.getParameter("id"));
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgentSetupDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgentSetupDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	/**
	 * this method is used for update the
	 * CollectionAgentSetupDispatchAction
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
		CollectionAgentSetupForm objForm = (CollectionAgentSetupForm) form;

		// Action Execution
		CollectionAgentManager objManager = new CollectionAgentManager();
		CollectionAgentDto objDto = new CollectionAgentDto();
		
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setStatus("A");
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgentSetupDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgentSetupDispatchAction update method:"
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
		objForm.getPreListData();
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
		CollectionAgentSetupForm objForm = (CollectionAgentSetupForm) form;

		// Dto Creation
		CollectionAgentDto objDto = new CollectionAgentDto();

		// Action Execution
		CollectionAgentManager objManager = new CollectionAgentManager();
		try {
			objDto = objManager.getCollectionAgent(objForm.getAgentId());
			objDto.setStatus("D");
			objDto.setIssuerId(objForm.getIssuerId());
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgentSetupDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgentSetupDispatchAction method: "
							+ ex);
		}

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
		objForm.getPreListData();
		return mapping.findForward(nextaction);
	}
}
