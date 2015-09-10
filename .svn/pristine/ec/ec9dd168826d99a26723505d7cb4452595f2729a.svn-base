package org.transinfo.cacis.action.cardproduction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.cardproduction.ApplValidationManager;
import org.transinfo.cacis.dto.cardproduction.ApplValidationDto;
import org.transinfo.cacis.formbean.cardproduction.ApplValidationSetupForm;

public class ApplValidationDispatchAction extends BaseDispatchAction {
	
	private Logger logger = Logger.getLogger(ApplValidationDispatchAction.class);

	/**
	* this method is used for showing the ApplValidationDispatchAction to add
	*/
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ApplValidationSetupForm objOldForm = (ApplValidationSetupForm) form;
		ApplValidationSetupForm objForm = new ApplValidationSetupForm();
		objForm.setIssuerId(objOldForm.getIssuerId());

		try {
			BeanUtils.copyProperties(objOldForm, objForm);
			objOldForm.getPreListData();
			
		} catch (Exception ex) {
			System.out
			.println("Error converting to form bean in ApplValidationDispatchAction addNew : "
					+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in ApplValidationDispatchAction addNew : "
							+ ex);
		}
		
		// Success
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for creating the ApplValidationDispatchAction to add
	*/
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		ApplValidationSetupForm objForm = (ApplValidationSetupForm) form;
		errors = objForm.validate(mapping, request);
		ApplValidationDto objDto = new ApplValidationDto();
		
		// Validation form
		ValidationConditionForm(objForm, errors);
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
			
		try {
			String userId = (String) request.getSession(false).getAttribute("USERID");
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setApplValId(IdsGenartion.GenerateApplValId());
			objDto.setLastUpdatedBy(userId);
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in ApplValidationDispatchAction add method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in ApplValidationDispatchAction add method "
							+ ex);
		}
		
		// Action Execution
		ApplValidationManager objManager = new ApplValidationManager();
		String nextAttribute = "cancel";
		
		// Check record
		boolean recExistRes = objManager.validate(objDto, 0);
				
		if(!recExistRes) {
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.applval.exists"));
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
	* this method is used for showing the ApplValidationDispatchAction to update
	*/
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ApplValidationManager objManager = new ApplValidationManager();
		ApplValidationSetupForm objForm = (ApplValidationSetupForm) form;
		
		try {
			ApplValidationDto objDto = objManager.getApplValidationDetail(request.getParameter("id"));
			
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in ApplValidationDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in ApplValidationDispatchAction change method: "
							+ ex);
		}
		
		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for update the ApplValidationDispatchAction
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
		ApplValidationSetupForm objForm = (ApplValidationSetupForm) form;

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		
		ValidationConditionForm(objForm, errors);
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		
		// Action Execution
		ApplValidationManager objManager = new ApplValidationManager();
		ApplValidationDto objDto = new ApplValidationDto();
		
		try {
			
			String userId = (String) request.getSession(false).getAttribute("USERID");
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedBy(userId);
			objDto.setLastUpdatedDate(new Date());
			
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in ApplValidationDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in ApplValidationDispatchAction update method:"
							+ ex);
		}
		
		// Check record
		boolean recExistRes = objManager.validate(objDto, 1);
		
		if(!recExistRes) {
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cgfexists"));
			saveErrors(request, errors);
			//nextAttribute = "add";
			
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
	* this method is used for delete the ApplValidationDispatchAction
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
		ApplValidationSetupForm objForm = (ApplValidationSetupForm) form;
		objForm.getPreListData();
		
		// Dto Creation
		ApplValidationDto objDto = new ApplValidationDto();
		
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean in ApplValidationDispatchAction in delete method "
					+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in ApplValidationDispatchAction in delete method: "
									+ e);
		}
		
		// Action Execution
		ApplValidationManager objManager = new ApplValidationManager();

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
	
	/**
	* this method is used for check condition of form.
	 * @param errors 
	 * @throws Exception 
	*/
	private void ValidationConditionForm(ApplValidationSetupForm objForm, ActionErrors errors) throws Exception {
		
		for (int i = 1;i <= 5; i++) {
			
			if (CommonCodes.AGE.equals(this.CallByName(objForm, ("getCriteria" + i)).toString())) {
				if (IsNumeric(this.CallByName(objForm, ("getValue1" + i)).toString()) 
						|| IsNumeric(this.CallByName(objForm, ("getValue2" + i)).toString())) {
					Integer value1 = Integer.valueOf(this.CallByName(objForm, ("getValue1" + i)).toString());
					Integer value2 = Integer.valueOf(this.CallByName(objForm, ("getValue2" + i)).toString());
						
					if (!(18 <= value1 && value1 <= 65) || !(18 <= value2 && value2 <= 65)) {
						errors.add("Error", new ActionError("error.applval.age"));
					}
				} else {
					errors.add("Error", new ActionError("error.applval.integer"));
				}
			} 

			if ("Y".equals(this.CallByName(objForm, ("getCondition" + i)).toString())) {
				if (StringUtils.isBlank(this.CallByName(objForm, ("getCondCriteria" + i)).toString()) 
						|| StringUtils.isBlank(this.CallByName(objForm, ("getCondOperator" + i)).toString()) 
						|| StringUtils.isBlank(this.CallByName(objForm, ("getValue" + i)).toString())) {
					errors.add("Error", new ActionError("error.applval.conditon"));
				}
			}
			
			if (StringUtils.isBlank(this.CallByName(objForm, ("getCriteria" + i)).toString()) == false) {
				if (StringUtils.isBlank(this.CallByName(objForm, ("getOperator" + i)).toString())
						|| StringUtils.isBlank(this.CallByName(objForm, ("getValue1" + i)).toString())
						|| StringUtils.isBlank(this.CallByName(objForm, ("getRefFail" + i)).toString())) {
					errors.add("Error", new ActionError("error.applval.criteria"));
				}
			}
			
			if (CommonCodes.AGE.equals(this.CallByName(objForm, ("getCondCriteria" + i)).toString())) {
				if (!IsNumeric(this.CallByName(objForm, ("getValue" + i)).toString())) {
					errors.add("Error", new ActionError("error.applval.integer"));
				}
			}
		}
	}
	
	public String CallByName(Object obj, String funcName) throws Exception {
		
	    return obj.getClass().getDeclaredMethod(funcName).invoke(obj).toString();
	}
	
	public boolean HaveValue(String strValue) {
		boolean result = false;
		// Set hard code
		List<String> valueOperators = new ArrayList<String>();
		valueOperators.add("EQ");
		valueOperators.add("BT");
		valueOperators.add("NB");
		valueOperators.add("LT");
		valueOperators.add("GT");
				
		for(String str : valueOperators) {
			if (str.equals(strValue)) 
				result = true;
		}
		
		return result;
	}

	/**
	* this method is used for check string is a numeric
	*/
	public static boolean IsNumeric(String str)  
	{  
		try {
			Integer d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;  
	}
}
