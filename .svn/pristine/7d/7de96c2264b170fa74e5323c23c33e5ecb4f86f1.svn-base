package org.transinfo.cacis.action.settings;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.transinfo.cacis.controller.settings.CardProductRateManager;
import org.transinfo.cacis.dto.settings.CardProductRateDto;
import org.transinfo.cacis.formbean.settings.CardProductRateSetupForm;

@SuppressWarnings( { "deprecation", "unchecked" })
public class CardProductRateDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger
			.getLogger(CardProductRateDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardProductRateSetupForm objOldForm = (CardProductRateSetupForm) form;
		CardProductRateSetupForm objForm = new CardProductRateSetupForm();
		objForm.setIssuerId(objOldForm.getIssuerId());
		// objForm.setIssuerId((String)request.getSession(false).getAttribute("ISSUER"));

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
	 * this method is used for adding the CardProductRateDetails
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
		CardProductRateSetupForm objForm = (CardProductRateSetupForm) form;

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.findForward("success");
		}

		CardProductRateManager objManager = new CardProductRateManager();

		Date startDate = objForm.getStartDt();
		Date endDate = objForm.getEndDt();

		// start date and end date overlapping checking
		// get all existing card product rate for this card product ID
		boolean isExistOverlap = false;
		List existsCardProductRates = objManager.getAllRates(objForm
				.getCardProductId());
		if (existsCardProductRates.size() == 0) {
			isExistOverlap = false;
		} else {
			Iterator itr = existsCardProductRates.iterator();
			while (itr.hasNext()) {
				CardProductRateDto objCardProductRateDto = (CardProductRateDto) itr
						.next();

				Date objStartDate = objCardProductRateDto.getStartDt();
				Date objEndDate = objCardProductRateDto.getEndDt();

				if (((startDate.getTime() >= objStartDate.getTime()) && (startDate
						.getTime() <= objEndDate.getTime()))
						|| (endDate.getTime() >= objStartDate.getTime())
						&& (endDate.getTime() <= objEndDate.getTime())) {
					isExistOverlap = true;
					break;
				}
			}
		}

		if (isExistOverlap) {
			errors.add("Error", new ActionError("errors.timesoverlap"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.findForward("success");
		}

		// DTO Creation
		CardProductRateDto objDto = new CardProductRateDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductRateDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductRateDispatchAction add method "
							+ e);
		}
		// Action Execution
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
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	/*
	 * this method is used for updating theCardProductRateDetails
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

		CardProductRateSetupForm objForm = (CardProductRateSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.findForward("success");
		}
		
		CardProductRateManager objManager = new CardProductRateManager();
		
		Date startDate = objForm.getStartDt();
		Date endDate = objForm.getEndDt();

		// start date and end date overlapping checking
		// get all existing card product rate for this card product ID
		boolean isExistOverlap = false;
		List existsCardProductRates = objManager.getAllRatesExceptOne(objForm
				.getCardProductId(), objForm.getId());
		if (existsCardProductRates.size() == 0) {
			isExistOverlap = false;
		} else {
			Iterator itr = existsCardProductRates.iterator();
			while (itr.hasNext()) {
				CardProductRateDto objCardProductRateDto = (CardProductRateDto) itr
						.next();

				Date objStartDate = objCardProductRateDto.getStartDt();
				Date objEndDate = objCardProductRateDto.getEndDt();

				if (((startDate.getTime() >= objStartDate.getTime()) && (startDate
						.getTime() <= objEndDate.getTime()))
						|| (endDate.getTime() >= objStartDate.getTime())
						&& (endDate.getTime() <= objEndDate.getTime())) {
					isExistOverlap = true;
					break;
				}
			}
		}

		if (isExistOverlap) {
			errors.add("Error", new ActionError("errors.timesoverlap"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.findForward("success");
		}

		// Dto Creation
		CardProductRateDto objDto = new CardProductRateDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductRateDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductRateDispatchAction update mthod:"
							+ e);
		}
		// Action Execution
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
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	/*
	 * this method is used for deleting the CardProductRateDetails
	 */

	/*
	 * public ActionForward delete(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * TPlusException,Exception { ActionErrors errors = null;
	 * 
	 * // Token Validation if(!isTokenValid(request)) { errors = new
	 * ActionErrors(); errors.add("Error", new ActionError("error.duplicate"));
	 * saveErrors(request,errors); return mapping.findForward("token"); }
	 * 
	 * //Form Validations CardProductRateSetupForm objForm =
	 * (CardProductRateSetupForm)form; objForm.getPreListData(); //Dto creation
	 * CardProductRateDto objDto = new CardProductRateDto(); try {
	 * BeanUtils.copyProperties(objDto, objForm); } catch (Exception e) {
	 * System.out.println(
	 * "Error converting to form bean in CardProductRateDispatchAction in delete method "
	 * + e.getMessage()); throw newTPlusException(
	 * "Could not populate the form bean  in CardProductRateDispatchAction in delete method: "
	 * + e); } // Action Execution CardProductRateManager objManager = new
	 * CardProductRateManager(); boolean boolDelete = objManager.delete(objDto);
	 * 
	 * String nextaction = "delete";
	 * 
	 * if(!boolDelete) { errors = new ActionErrors(); errors.add("Error", new
	 * ActionError("error.deletefailed")); saveErrors(request, errors);
	 * nextaction = "success"; request.setAttribute("ACTION", "update"); } else
	 * { errors = new ActionErrors(); errors.add("Error", new
	 * ActionError("error.deleteSuccess")); saveErrors(request, errors); }
	 * 
	 * // Success resetToken(request); request.setAttribute("ACTION","cancel");
	 * return mapping.findForward(nextaction); }
	 */
	/*
	 * this method is used for showing the CardProductRateDetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardProductRateSetupForm objForm = (CardProductRateSetupForm) form;

		// Action Execution
		CardProductRateManager objManager = new CardProductRateManager();
		CardProductRateDto objDto = objManager.getCardProductRateDto(Integer
				.parseInt(request.getParameter("id")));

		try {
			BeanUtils.copyProperties(objForm, objDto);
			objForm
					.setCardProductId(objDto.getCardProduct()
							.getCardProductId());
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductRateDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductRateDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData();
		return mapping.findForward("success");
	}

}
