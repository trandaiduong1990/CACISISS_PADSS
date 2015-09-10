package org.transinfo.cacis.action.settings;

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
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.settings.CardPromotionManager;
import org.transinfo.cacis.dto.settings.CardPromotionDto;
import org.transinfo.cacis.formbean.settings.CardPromotionSetupForm;

@SuppressWarnings("deprecation")
public class CardPromotionDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(CardPromotionDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardPromotionSetupForm objOldForm = (CardPromotionSetupForm) form;
		CardPromotionSetupForm objForm = new CardPromotionSetupForm();
		// objForm.setIssuerId((String)request.getSession(false).getAttribute("ISSUER"));

		try {
			BeanUtils.copyProperties(objOldForm, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		return mapping.findForward("success");

	}

	/*
	 * this method is used for adding the CardPromotionDetails
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		/*
		 * Token Validation if(!isTokenValid(request)) { errors = new
		 * ActionErrors(); errors.add("Error", new
		 * ActionError("error.duplicate")); saveErrors(request,errors); return
		 * mapping.findForward("token"); }
		 */

		// Form Validations
		CardPromotionSetupForm objForm = (CardPromotionSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.findForward("success");
		}

		// DTO Creation
		CardPromotionDto objDto = new CardPromotionDto();
		try {

			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardPromotionDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardPromotionDispatchAction add method "
							+ e);
		}
		// Action Execution
		CardPromotionManager objManager = new CardPromotionManager();
		boolean boolAdd = objManager.add(objDto);

		if (!boolAdd) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}
		// Success
		// saveToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	/*
	 * this method is used for updating the CardPromotionDetails
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
		CardPromotionSetupForm objForm = (CardPromotionSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}

		// Dto Creation
		CardPromotionDto objDto = new CardPromotionDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardPromotionDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardPromotionDispatchAction update mthod:"
							+ e);
		}
		// Action Execution
		CardPromotionManager objManager = new CardPromotionManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
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

	/*
	 * this method is used for deleting the CardPromotionDetails
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

		CardPromotionSetupForm objForm = (CardPromotionSetupForm) form;
		// Dto creation
		CardPromotionDto objDto = new CardPromotionDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardPromotionDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CardPromotionDispatchAction in delete method: "
							+ e);
		}
		// Action Execution
		CardPromotionManager objManager = new CardPromotionManager();
		boolean boolDelete = objManager.delete(objDto);

		String nextaction = "delete";

		if (!boolDelete) {
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

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}

	/*
	 * this method is used for showing the CardPromotionDetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardPromotionSetupForm objForm = (CardPromotionSetupForm) form;
		// Action execution
		CardPromotionManager objManager = new CardPromotionManager();
		CardPromotionDto objDto = objManager.getCardPromotionDto(request
				.getParameter("promotionId"));

		try {
			BeanUtils.copyProperties(objForm, objDto);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardPromotionDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardPromotionDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}
}
