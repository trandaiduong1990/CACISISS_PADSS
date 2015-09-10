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
import org.transinfo.cacis.controller.settings.CardProductLimitManager;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductLimitDto;
import org.transinfo.cacis.formbean.settings.CardProductLimitSetupForm;

@SuppressWarnings("deprecation")
public class CardProductLimitDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger
			.getLogger(CardProductLimitDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardProductLimitSetupForm objOldForm = (CardProductLimitSetupForm) form;
		CardProductLimitSetupForm objForm = new CardProductLimitSetupForm();
		objForm.setIssuerId(objOldForm.getIssuerId());

		try {
			BeanUtils.copyProperties(objOldForm, objForm);
			objOldForm.getPreListData();
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		return mapping.findForward("success");

	}

	/*
	 * this method is used for adding the Cardproductsdetails details
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
		CardProductLimitSetupForm objForm = (CardProductLimitSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		CardProductDto cardProductDto = new CardProductDto();
		cardProductDto.setCardProductId(objForm.getCardProductId());

		// DTO Creation
		CardProductLimitDto objDto = new CardProductLimitDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setCardProduct(cardProductDto);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductLimitDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductLimitDispatchAction add method "
							+ e);
		}
		// Action Execution
		CardProductLimitManager objManager = new CardProductLimitManager();
		
		boolean recExistRes = objManager.validate(objDto, 0);
		String nextAttribute = "cancel";
		
		if(!recExistRes){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardproductlimitexists"));
			saveErrors(request, errors);
			nextAttribute = "add";
			
		}else{
				
			boolean boolAdd = objManager.add(objDto);
	
			if (!boolAdd) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
				nextAttribute = "add";
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				nextAttribute = "cancel";
	
				objDto = objManager.getCardProductLimitDto(objForm
						.getCardProductId());
	
				try {
					BeanUtils.copyProperties(objForm, objDto);
				} catch (Exception e) {
					logger.error(e);
					System.out
							.println("Error converting to form bean in CardProductLimitDispatchAction in change method: "
									+ e.getMessage());
					throw new TPlusException(
							"Could not populate the form bean in CardProductLimitDispatchAction method: "
									+ e);
				}
	
			}
		
		}
		// Success

		// saveToken(request);
		request.setAttribute("ACTION", nextAttribute);
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	/*
	 * this method is used for updating the Cardproductsdetails
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
		CardProductLimitSetupForm objForm = (CardProductLimitSetupForm) form;

		errors = objForm.validate(mapping, request);

		// Dto Creation
		CardProductLimitDto objDto = new CardProductLimitDto();
		// Action Execution
		CardProductLimitManager objManager = new CardProductLimitManager();

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();

			objDto = objManager.getCardProductLimitDto(objForm
					.getCardProductId());

			try {
				BeanUtils.copyProperties(objForm, objDto);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in CardProductLimitDispatchAction in change method: "
								+ e.getMessage());
				throw new TPlusException(
						"Could not populate the form bean in CardProductLimitDispatchAction method: "
								+ e);
			}

			return mapping.getInputForward();
		}

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductLimitDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductLimitDispatchAction update mthod:"
							+ e);
		}

		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
		} else {

			objDto = objManager.getCardProductLimitDto(objForm
					.getCardProductId());

			try {
				BeanUtils.copyProperties(objForm, objDto);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in CardProductLimitDispatchAction in change method: "
								+ e.getMessage());
				throw new TPlusException(
						"Could not populate the form bean in CardProductLimitDispatchAction method: "
								+ e);
			}

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

	/*
	 * this method is used for deleting the Cardproductsdetails
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
		CardProductLimitSetupForm objForm = (CardProductLimitSetupForm) form;

		// Dto Creation
		CardProductLimitDto objDto = new CardProductLimitDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductLimitDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CardProductLimitDispatchAction in delete method: "
							+ e);
		}
		// Action Execution
		CardProductLimitManager objManager = new CardProductLimitManager();
		boolean boolDelete = objManager.delete(objDto);
		objForm.getPreListData();
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
	 * this method is used for showing the Cardproductsdetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardProductLimitManager objManager = new CardProductLimitManager();
		CardProductLimitSetupForm objForm = (CardProductLimitSetupForm) form;

		CardProductLimitDto objDto = objManager.getCardProductLimitDto(request
				.getParameter("cardProductId"));

		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
}
