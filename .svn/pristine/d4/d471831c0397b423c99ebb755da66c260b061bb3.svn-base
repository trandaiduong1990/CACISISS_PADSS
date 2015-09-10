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
import org.transinfo.cacis.controller.settings.CycleManager;
import org.transinfo.cacis.dto.settings.CycleDto;
import org.transinfo.cacis.formbean.settings.CycleSetupForm;

@SuppressWarnings("deprecation")
public class CycleDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(CycleDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CycleSetupForm objOldForm = (CycleSetupForm) form;
		CycleSetupForm objForm = new CycleSetupForm();
		objForm.setIssuerId(objOldForm.getIssuerId());
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
	 * this method is used for adding the CycleDetails
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
		CycleSetupForm objForm = (CycleSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		CycleDto objDto = new CycleDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CycleDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CycleDispatchAction add method "
							+ e);
		}
		// Action Execution
		CycleManager objManager = new CycleManager();
		
		// validate against database for cycle no and day of month
		// validate for cycle no
		boolean validation = true;
		if(objManager.validateCycleNo(objForm.getCycleNo())){
			if(objManager.validateDayOfMonth(objForm.getDayOfMonth())){
				validation = true;
			}else{
				validation = false;
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.dayofmonthalreadygiven"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "add");
			}
		}else{
			validation = false;
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cyclenoalreadygiven"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
		}

		if(validation){
			boolean boolAdd = objManager.add(objDto);
	
			if (!boolAdd) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "add");
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "cancel");
			}
		}
		// Success
		// saveToken(request);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for updating the CycleDetails
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

		CycleSetupForm objForm = (CycleSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// Dto Creation
		CycleDto objDto = new CycleDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CycleDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CycleDispatchAction update mthod:"
							+ e);
		}
		// Action Execution
		CycleManager objManager = new CycleManager();
		
		// validate against database for cycle no and day of month
		// validate for cycle no
		boolean validation = true;
		if(objManager.validateDayOfMonth(objForm.getDayOfMonth())){
			validation = true;
		}else{
			validation = false;
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.dayofmonthalreadygiven"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
		}

		if(validation){
		
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
			
		}
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	/*
	 * this method is used for deleting the CycleDetails
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
		CycleSetupForm objForm = (CycleSetupForm) form;

		CycleDto objDto = new CycleDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CycleDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CycleDispatchAction in delete method: "
							+ e);
		}
		// Action Execution
		CycleManager objManager = new CycleManager();
		
		// validation for branch has active customers
		boolean hasCus = objManager.hasCustomerAccounts(objForm.getCycleNo());
		if(hasCus){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cyclehascustaccounts"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}
		
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
	 * this method is used for showing the CycleDetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CycleSetupForm objForm = (CycleSetupForm) form;

		// Action Executon
		CycleManager objManager = new CycleManager();
		CycleDto objDto = objManager.getCycleDto(Integer.parseInt(request
				.getParameter("cycleNo")));

		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CycleDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CycleDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}
}
