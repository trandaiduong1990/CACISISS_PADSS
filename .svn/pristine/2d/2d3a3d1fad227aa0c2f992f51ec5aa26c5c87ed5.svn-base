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
import org.transinfo.cacis.controller.settings.CardProductFeeManager;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.formbean.settings.CardProductFeeSetupForm;

@SuppressWarnings( { "deprecation", "unchecked" })
public class CardProductFeeDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger
			.getLogger(CardProductFeeDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardProductFeeSetupForm objOldForm = (CardProductFeeSetupForm) form;
		CardProductFeeSetupForm objForm = new CardProductFeeSetupForm();
		objForm.setIssuerId(objOldForm.getIssuerId());

		try {
			BeanUtils.copyProperties(objOldForm, objForm);
			objOldForm.getPreListData();
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		objOldForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));

		return mapping.findForward("success");

	}

	/*
	 * this method is used for adding the CardProductfeeDetails
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
		CardProductFeeSetupForm objForm = (CardProductFeeSetupForm) form;

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.findForward("success");
		}

		CardProductFeeManager objManager = new CardProductFeeManager();

		Date startDate = objForm.getStartDt();
		Date endDate = objForm.getEndDt();

		// start date and end date overlapping checking
		// get all existing card product rate for this card product ID
		boolean isExistOverlap = false;
		List existsCardProductFees = objManager.getAllFees(objForm
				.getCardProductId());
		if (existsCardProductFees.size() == 0) {
			isExistOverlap = false;
		} else {
			Iterator itr = existsCardProductFees.iterator();
			while (itr.hasNext()) {
				CardProductFeeDto objCardProductFeeDto = (CardProductFeeDto) itr
						.next();

				Date objStartDate = objCardProductFeeDto.getStartDt();
				Date objEndDate = objCardProductFeeDto.getEndDt();

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
		CardProductFeeDto objDto = new CardProductFeeDto();
		try {

			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductFeeDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductFeeDispatchAction add method "
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
	 * this method is used for updating the CardProductfeeDetails
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

		CardProductFeeSetupForm objForm = (CardProductFeeSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.findForward("success");
		}

		CardProductFeeManager objManager = new CardProductFeeManager();

		Date startDate = objForm.getStartDt();
		Date endDate = objForm.getEndDt();

		// start date and end date overlapping checking
		// get all existing card product rate for this card product ID
		boolean isExistOverlap = false;
		List existsCardProductFees = objManager.getAllFeesExceptOne(objForm
				.getCardProductId(), objForm.getId());
		if (existsCardProductFees.size() == 0) {
			isExistOverlap = false;
		} else {
			Iterator itr = existsCardProductFees.iterator();
			while (itr.hasNext()) {
				CardProductFeeDto objCardProductFeeDto = (CardProductFeeDto) itr
						.next();

				Date objStartDate = objCardProductFeeDto.getStartDt();
				Date objEndDate = objCardProductFeeDto.getEndDt();

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
		CardProductFeeDto objDto = new CardProductFeeDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductFeeDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductFeeDispatchAction update mthod:"
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
	 * this method is used for deleting the CardProductfeeDetails
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
	 * //Form Validations CardProductFeeSetupForm objForm =
	 * (CardProductFeeSetupForm)form; objForm.getPreListData(); //Dto creation
	 * CardProductFeeDto objDto = new CardProductFeeDto(); try {
	 * BeanUtils.copyProperties(objDto, objForm); } catch (Exception e) {
	 * System.out.println(
	 * "Error converting to form bean in CardProductFeeDispatchAction in delete method "
	 * + e.getMessage()); throw newTPlusException(
	 * "Could not populate the form bean  in CardProductFeeDispatchAction in delete method: "
	 * + e); } // Action Execution CardProductFeeManager objManager = new
	 * CardProductFeeManager(); boolean boolDelete = objManager.delete(objDto);
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
	 * this method is used for showing the CardProductfeeDetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardProductFeeSetupForm objForm = (CardProductFeeSetupForm) form;

		// Action execution
		CardProductFeeManager objManager = new CardProductFeeManager();
		CardProductFeeDto objDto = objManager.getCardProductFeeDto(Integer
				.parseInt(request.getParameter("id")));

		try {
			BeanUtils.copyProperties(objForm, objDto);
			objForm
					.setCardProductId(objDto.getCardProduct()
							.getCardProductId());
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductFeeDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductFeeDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
}
