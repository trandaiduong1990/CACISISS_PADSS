package org.transinfo.cacis.action.settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.settings.EMVProfileManager;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CardProductDAOImpl;
import org.transinfo.cacis.dto.settings.EMVProfileDto;
import org.transinfo.cacis.formbean.settings.EMVProfileSetupForm;

/**
 * EMVProfileProcessDispatchAction
 * 
 * @author hoang-vu
 * 
 */
public class EMVProfileDispatchAction extends BaseDispatchAction {
	
	private Logger logger = Logger.getLogger(CardProductDAOImpl.class);

	/**
	* this method is used for showing the EMVProfileProcessDispatchAction to add
	*/
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		EMVProfileSetupForm objOldForm = (EMVProfileSetupForm) form;
		EMVProfileSetupForm objForm = new EMVProfileSetupForm();
		
		try {
			if ("change".equals(objOldForm.getScreen()) || "list".equals(objOldForm.getScreen())) {
				BeanUtils.copyProperties(objOldForm, objForm);
			}
			objOldForm.getPreListData();
			objOldForm.setScreen("addNew");
		} catch (Exception ex) {
			System.out
			.println("Error converting to form bean in EMVProfileDispathAction addNew : "
					+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in EMVProfileDispathAction addNew : "
							+ ex);
		}
		
		// Success
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for creating the EMVProfileProcessDispatchAction to add
	*/
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		EMVProfileSetupForm objForm = (EMVProfileSetupForm) form;
		errors = objForm.validate(mapping, request);
		EMVProfileDto objDto = new EMVProfileDto();
		
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
			
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in EMVProfileProcessDispatchAction create method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in EMVProfileProcessDispatchAction create method "
							+ ex);
		}
		
		// Action Execution
		EMVProfileManager objManager = new EMVProfileManager();
		String nextAttribute = "cancel";
		
		// Check record
		boolean recExistRes = objManager.validate(objDto, 0);
		boolean emvApplTypeCrypto = objManager.checkEmvApplTypeCryto(objDto, 0);
				
		if(!recExistRes || !emvApplTypeCrypto) {
			
			errors = new ActionErrors();
			
			if (!recExistRes) {
				errors.add("Error", new ActionError("error.emvexists"));
			} 
			
			if (!emvApplTypeCrypto){
				errors.add("Error", new ActionError("error.emvapplcrypto"));
			}
			saveErrors(request, errors);
			nextAttribute = "add";
		}else{
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
		}
		
		// Success
		objForm.getPreListData();
		request.setAttribute("ACTION", nextAttribute);		
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for showing the EMVProfileProcessDispatchAction to update
	*/
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		EMVProfileManager objManager = new EMVProfileManager();
		EMVProfileSetupForm objForm = (EMVProfileSetupForm) form;
		
		try {
			if (StringUtils.isBlank(objForm.getApplType())) {
				EMVProfileDto objDto = objManager.getEMVProfileDetail(request.getParameter("id"));
				BeanUtils.copyProperties(objForm, objDto);
			}
			
			objForm.setScreen("change");
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in EMVProfileProcessAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in EMVProfileProcessAction method: "
							+ ex);
		}
		
		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for update the EMVProfileProcessDispatchAction
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
		EMVProfileSetupForm objForm = (EMVProfileSetupForm) form;

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "change");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		
		// Action Execution
		EMVProfileManager objManager = new EMVProfileManager();
		EMVProfileDto objDto = new EMVProfileDto();
		
		try {
			
			BeanUtils.copyProperties(objDto, objForm);
			
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in EMVProfileProcessAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in EMVProfileProcessAction update method:"
							+ ex);
		}
		
		// Check record
		boolean recExistRes = objManager.validate(objDto, 1);
		boolean emvApplTypeCrypto = objManager.checkEmvApplTypeCryto(objDto, 1);
		
		if(!recExistRes || emvApplTypeCrypto) {
			
			errors = new ActionErrors();
			if (!recExistRes) {
				errors.add("Error", new ActionError("error.emvexists"));
			} 
			
			if (emvApplTypeCrypto){
				errors.add("Error", new ActionError("error.emvapplcrypto"));
			}
			request.setAttribute("ACTION", "change");
			saveErrors(request, errors);
			objForm.getPreListData();
			return mapping.getInputForward();
			
		} else {
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
		}
		
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for delete the EMVProfileProcessDispatchAction
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
		EMVProfileSetupForm objForm = (EMVProfileSetupForm) form;
		objForm.getPreListData();
		
		// Dto Creation
		EMVProfileDto objDto = new EMVProfileDto();
		
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean in EMVProfileProcessAction in delete method "
					+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in EMVProfileProcessAction in delete method: "
									+ e);
		}
		
		// Action Execution
		EMVProfileManager objManager = new EMVProfileManager();

		boolean emvProfileExits = objManager.validate(objDto, 0);
		
		if (emvProfileExits) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.producthascards"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		} 
		
		String nextaction = "delete";
		boolean boolDelete = objManager.delete(objDto);

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
}