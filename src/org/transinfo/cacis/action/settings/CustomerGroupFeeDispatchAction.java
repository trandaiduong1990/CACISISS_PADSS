package org.transinfo.cacis.action.settings;

import java.util.Date;

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
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.controller.settings.CustomerGroupFeeManager;
import org.transinfo.cacis.controller.settings.CustomerTypeManager;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CustomerGroupFeeDto;
import org.transinfo.cacis.dto.settings.CustomerTypeDto;
import org.transinfo.cacis.formbean.settings.CardProductSetupForm;
import org.transinfo.cacis.formbean.settings.CustomerGroupFeeSetupForm;

@SuppressWarnings("deprecation")
public class CustomerGroupFeeDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(CustomerGroupFeeDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CustomerGroupFeeSetupForm objOldForm = (CustomerGroupFeeSetupForm) form;
		CustomerGroupFeeSetupForm objForm = new CustomerGroupFeeSetupForm();
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
		CustomerGroupFeeSetupForm objForm = (CustomerGroupFeeSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		
		CardProductManager objCardProductManager = new CardProductManager();
		CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(objForm.getCardProduct());
		
		CustomerTypeManager objCustomerTypeManager = new CustomerTypeManager();
		CustomerTypeDto objCustomerTypeDto = objCustomerTypeManager.getCustomerTypeDto(objForm.getCustomerType());

		// DTO Creation
		CustomerGroupFeeDto objDto = new CustomerGroupFeeDto();
		
		try {
			
			String userId = (String) request.getSession(false).getAttribute("USERID");
						
			objDto.setCardProduct(objCardProductDto);
			objDto.setCustomerType(objCustomerTypeDto);
			objDto.setAnnualFee(Float.valueOf(objForm.getAnnualFee()));
			objDto.setJoinFee(Float.valueOf(objForm.getJoinFee()));
			objDto.setUserId(userId);
			objDto.setUpdatedDate(new Date());
			objDto.setAnnualFeeSup(Float.valueOf(objForm.getAnnualFeeSup()));
			objDto.setJoinFeeSup(Float.valueOf(objForm.getJoinFeeSup()));
			
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductDispatchAction add method "
							+ e);
		}
		// Action Execution
		CustomerGroupFeeManager objManager = new CustomerGroupFeeManager();
		
		boolean recExistRes = objManager.validate(objDto, 0);
		String nextAttribute = "cancel";
		
		if(!recExistRes){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cgfexists"));
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
		CustomerGroupFeeSetupForm objForm = (CustomerGroupFeeSetupForm) form;

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		
		CardProductManager objCardProductManager = new CardProductManager();
		CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(objForm.getCardProduct());
		
		CustomerTypeManager objCustomerTypeManager = new CustomerTypeManager();
		CustomerTypeDto objCustomerTypeDto = objCustomerTypeManager.getCustomerTypeDto(objForm.getCustomerType());

		// DTO Creation
		CustomerGroupFeeDto objDto = new CustomerGroupFeeDto();
		
		try {			
			
			String userId = (String) request.getSession(false).getAttribute("USERID");
			objDto.setId(objForm.getId());
			objDto.setCardProduct(objCardProductDto);
			objDto.setCustomerType(objCustomerTypeDto);
			objDto.setAnnualFee(Float.valueOf(objForm.getAnnualFee()));
			objDto.setJoinFee(Float.valueOf(objForm.getJoinFee()));
			objDto.setUserId(userId);
			objDto.setUpdatedDate(new Date());
			objDto.setAnnualFeeSup(Float.valueOf(objForm.getAnnualFeeSup()));
			objDto.setJoinFeeSup(Float.valueOf(objForm.getJoinFeeSup()));
			
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductDispatchAction update mthod:"
							+ e);
		}

		// Action Execution
		CustomerGroupFeeManager objManager = new CustomerGroupFeeManager();
		
		boolean recExistRes = objManager.validate(objDto, 1);
		//String nextAttribute = "cancel";
		
		if(!recExistRes){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cgfexists"));
			saveErrors(request, errors);
			//nextAttribute = "add";
			
		}else{
				
			boolean boolAdd = objManager.update(objDto);
	
			if (!boolAdd) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updatefailed"));
				saveErrors(request, errors);
				//nextAttribute = "add";
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updateSuccess"));
				saveErrors(request, errors);
				//nextAttribute = "cancel";
			}
		
		}
		
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
		CardProductSetupForm objForm = (CardProductSetupForm) form;
		objForm.getPreListData();

		// Dto Creation
		CardProductDto objDto = new CardProductDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CardProductDispatchAction in delete method: "
							+ e);
		}
		// Action Execution
		CardProductManager objManager = new CardProductManager();

		boolean hasProductCards = objManager.hasCards(objForm.getCardProductId());

		if (hasProductCards) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.producthascards"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}else{
			boolean hasWithdrawalLimitrules = objManager.hasWithdrawalLimitRules(objDto.getCardProductId());
			if(hasWithdrawalLimitrules){
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardproducthasrules"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.findForward("success");
			}
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

	/*
	 * this method is used for showing the Cardproductsdetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CustomerGroupFeeManager objManager = new CustomerGroupFeeManager();
		CustomerGroupFeeSetupForm objForm = (CustomerGroupFeeSetupForm) form;

		CustomerGroupFeeDto objDto = objManager.getCustomeGroupFeeDto(request.getParameter("id"));

		try {

			String issuerId = (String) request.getSession(false).getAttribute("ISSUER");
			//BeanUtils.copyProperties(objForm, objDto);
			
			objForm.setIssuerId(issuerId);
			objForm.setId(objDto.getId());
			objForm.setCardProduct(objDto.getCardProduct().getCardProductId());
			objForm.setCustomerType(objDto.getCustomerType().getCustomerTypeId());
			objForm.setAnnualFee(String.valueOf(objDto.getAnnualFee()));
			objForm.setJoinFee(String.valueOf(objDto.getJoinFee()));
			objForm.setAnnualFeeSup(String.valueOf(objDto.getAnnualFeeSup()));
			objForm.setJoinFeeSup(String.valueOf(objDto.getJoinFeeSup()));

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
