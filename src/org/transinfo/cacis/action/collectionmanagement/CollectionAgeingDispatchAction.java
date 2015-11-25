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
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSetupForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class CollectionAgeingDispatchAction extends
		BaseDispatchAction {
	private Logger logger = Logger
			.getLogger(CollectionAgeingDispatchAction.class);

	/**
	 * this method is used to showing add new DelinquencyPolicy
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CollectionAgeingSetupForm objOldForm = (CollectionAgeingSetupForm) form;
		CollectionAgeingSetupForm objForm = new CollectionAgeingSetupForm();

		try {
			BeanUtils.copyProperties(objOldForm, objForm);

		} catch (Exception ex) {
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction addNew : "
							+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in CollectionAgeingDispatchAction addNew : "
							+ ex);
		}
		objOldForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));
		// Success
		return mapping.findForward("success");
	}

	/**
	 * this method is used for creating the
	 * CollectionAgeingDispatchAction to add
	 */
	@SuppressWarnings("deprecation")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		CollectionAgeingSetupForm objForm = (CollectionAgeingSetupForm) form;
		errors = objForm.validate(mapping, request);
		CollectionAgeingDto objDto = new CollectionAgeingDto();

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		
		// validation
		int startDay = Integer.parseInt(objForm.getStartDays());
		int endDay = Integer.parseInt(objForm.getEndDays());
		if(startDay >= endDay) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("collectionageing.ageingSevError"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		
		CollectionAgeingManager objManager = new CollectionAgeingManager();

		// manually validation
		boolean checkExist = objManager.checkExist(objForm);
		if (!checkExist) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("co.agingSevError"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		
		// check overlap
		boolean checkOverlap = objManager.checkOverlap(objForm);
		if (!checkOverlap) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("collectionageing.overlapError"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedDate(new Date());
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction create method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgeingDispatchAction create method "
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
		return mapping.findForward("success");
	}

	/**
	 * this method is used for showing the DelinquencyPolicyDispatchAction to
	 * update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CollectionAgeingManager objManager = new CollectionAgeingManager();
		CollectionAgeingSetupForm objForm = (CollectionAgeingSetupForm) form;

		try {
			CollectionAgeingDto objDto = objManager
					.getCollectionAgeing(request.getParameter("ageingPolicy"));
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgeingDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for update the
	 * CollectionAgeingDispatchAction
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
		CollectionAgeingSetupForm objForm = (CollectionAgeingSetupForm) form;

		// Action Execution
		CollectionAgeingManager objManager = new CollectionAgeingManager();
		CollectionAgeingDto objDto = new CollectionAgeingDto();
		
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		
		// check day overlap
		boolean checkOverlap = objManager.checkOverlap(objForm);
		if (!checkOverlap) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("collectionageing.overlapError"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgeingDispatchAction update method:"
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
		CollectionAgeingSetupForm objForm = (CollectionAgeingSetupForm) form;

		// Dto Creation
		CollectionAgeingDto objDto = new CollectionAgeingDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CollectionAgeingDispatchAction in delete method: "
							+ e);
		}

		// Action Execution
		CollectionAgeingManager objManager = new CollectionAgeingManager();

		String nextaction = "delete";
		boolean boolDelete = objManager.delete(objDto);

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
