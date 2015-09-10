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
import org.transinfo.cacis.controller.settings.CustomerTypeManager;
import org.transinfo.cacis.dto.settings.CustomerTypeDto;
import org.transinfo.cacis.formbean.settings.CustomerTypeSetupForm;

@SuppressWarnings("deprecation")
public class CustomerTypeDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(CustomerTypeDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CustomerTypeSetupForm objOldForm = (CustomerTypeSetupForm) form;
		CustomerTypeSetupForm objForm = new CustomerTypeSetupForm();
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
	 * this method is used for adding the CustomerTypeDetails
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
		CustomerTypeSetupForm objForm = (CustomerTypeSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		CustomerTypeDto objDto = new CustomerTypeDto();
		try {

			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CustomerTypeDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CustomerTypeDispatchAction add method "
							+ e);
		}
		// Action Execution
		CustomerTypeManager objManager = new CustomerTypeManager();
		
		boolean recExistRes = objManager.validate(objDto, 0);
		String nextAttribute = "cancel";
		
		if(!recExistRes){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.custtypeexists"));
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
			}
		
		}
		// Success
		// saveToken(request);
		request.setAttribute("ACTION", nextAttribute);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for updating the CustomerTypeDetails
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
		CustomerTypeSetupForm objForm = (CustomerTypeSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// Dto Creation
		CustomerTypeDto objDto = new CustomerTypeDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CustomerTypeDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CustomerTypeDispatchAction update mthod:"
							+ e);
		}
		// Action Execution
		CustomerTypeManager objManager = new CustomerTypeManager();
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
	 * this method is used for deleting the CustomerTypeDetails
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
		CustomerTypeSetupForm objForm = (CustomerTypeSetupForm) form;
		CustomerTypeDto objDto = new CustomerTypeDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CustomerTypeDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CustomerTypeDispatchAction in delete method: "
							+ e);
		}
		// Action Execution
		CustomerTypeManager objManager = new CustomerTypeManager();
		
		// validation for branch has active customers
		boolean hasCus = objManager.hasActiveCustomers(objDto.getCustomerTypeId());
		if(hasCus){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.customertypehascustomers"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}else{
			boolean hasWithdrawalLimitrules = objManager.hasWithdrawalLimitRules(objDto.getCustomerTypeId());
			if(hasWithdrawalLimitrules){
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.customertypehasrules"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.findForward("success");
			}
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
	 * this method is used for showing the CardTypeDetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		// Action execution
		CustomerTypeManager objManager = new CustomerTypeManager();
		CustomerTypeSetupForm objForm = (CustomerTypeSetupForm) form;
		CustomerTypeDto objDto = objManager.getCustomerTypeDto(request
				.getParameter("customerTypeId"));
		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CustomerTypDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CustomerTypDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}
}
