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
import org.transinfo.cacis.controller.settings.MCCManager;
import org.transinfo.cacis.dto.settings.MCCDto;
import org.transinfo.cacis.formbean.settings.MCCSetupForm;

@SuppressWarnings("deprecation")
public class MCCDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(MCCDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		MCCSetupForm objOldForm = (MCCSetupForm) form;
		MCCSetupForm objForm = new MCCSetupForm();
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
	 * this method is used for adding the MccDetails
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
		MCCSetupForm objForm = (MCCSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			objForm.getPreListData();
			return mapping.findForward("success");
		}

		// DTO Creation
		MCCDto objDto = new MCCDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in MCCDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in MCCDispatchAction add method "
							+ e);
		}
		// Action Execution
		MCCManager objManager = new MCCManager();
		
		boolean recExistRes = objManager.validate(objDto, 0);
		String nextAttribute = "cancel";
		
		if(!recExistRes){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.mccexists"));
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
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	/*
	 * this method is used for updating the MccDetails
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
		MCCSetupForm objForm = (MCCSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.findForward("success");
		}

		// Dto Creation
		MCCDto objDto = new MCCDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in MCCDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in MCCDispatchAction update mthod:"
							+ e);
		}
		// Action Execution
		MCCManager objManager = new MCCManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
		}
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	/*
	 * this method is used for deleting the MccDetails
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
		MCCSetupForm objForm = (MCCSetupForm) form;

		MCCDto objDto = new MCCDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in MCCDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in MCCDispatchAction in delete method: "
							+ e);
		}
		// Action Execution
		MCCManager objManager = new MCCManager();
		
		// validation for branch has active customers
		boolean hasRules = objManager.hasWithdrawalLimitRules(objDto.getMerchantId());
		if(hasRules){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.mcchasrules"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.findForward("success");
		}else{
			boolean hasRiskTarnsConfig = objManager.hasRiskTranxConfig(objDto.getMerchantId());
			if(hasRiskTarnsConfig){
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.mcchasrisktranxconfig"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				objForm.getPreListData();
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
			
			objForm.setMerchantId("");
			
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		objForm.getPreListData();
		return mapping.findForward(nextaction);
	}

	/*
	 * this method is used for showing the MccDetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		MCCSetupForm objForm = (MCCSetupForm) form;
		// Action execution
		MCCManager objManager = new MCCManager();
		MCCDto objDto = objManager
				.getMCCDto(request.getParameter("merchantId"));
		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in MCCDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in MCCDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
}
