package org.transinfo.cacis.action.customerservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.controller.customerservice.CardLevelLimitManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerLimitsDto;
import org.transinfo.cacis.dto.settings.CardProductLimitDto;
import org.transinfo.cacis.formbean.customerservice.CardLevelLimitSetupForm;
import org.transinfo.cacis.formbean.settings.CardProductLimitSetupForm;

@SuppressWarnings("deprecation")
public class CardLevelLimitDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(CardLevelLimitDispatchAction.class);
	
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardLevelLimitSetupForm objOldForm = (CardLevelLimitSetupForm) form;
		CardLevelLimitSetupForm objForm = new CardLevelLimitSetupForm();
		objForm.setIssuerId(objOldForm.getIssuerId());
		objForm.setCardNo(objOldForm.getCardNo());
		
		String cardHolderType = objOldForm.getCardHolderType();
		objForm.setCardHolderType(cardHolderType);
		
		CardLevelLimitManager objCardLevelLimitManager = new CardLevelLimitManager();
		CardProductLimitDto objCardProductLimitDto =  objCardLevelLimitManager.getCardProductLimitDto(objOldForm.getCardNo());
		
		CardProductManager objCardProductManager = new CardProductManager();
		
		ActionErrors errors = null;
		
		if(objCardProductLimitDto != null){
			
			objForm.getPreListData();
			
			try {

				String eComEnable = objCardProductManager.getCardProductDto(objCardProductLimitDto.getCardProductId()).geteComEnable();
				
				CardProductLimitSetupForm objCardProductLimitSetupForm = new CardProductLimitSetupForm();
				BeanUtils.copyProperties(objCardProductLimitSetupForm, objCardProductLimitDto);
				BeanUtils.copyProperties(objOldForm, objForm);
				objOldForm.setObjCardProductLimitSetupForm(objCardProductLimitSetupForm);
				objOldForm.setCardProducteComEnable(eComEnable);
			} catch (Exception e) {
				logger.error(e);
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: " + e);
			}
			
		}else{
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nocardproductlimit"));
			saveErrors(request, errors);
			
			request.setAttribute("ACTION", "cancel");
			
			return mapping.findForward("noproductlimit");
		}

		return mapping.findForward("success");

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		// Token Validation
		/*if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}*/

		// Form Validations
		CardLevelLimitSetupForm objForm = (CardLevelLimitSetupForm) form;
		
		CardLevelLimitManager objCardLevelLimitManager = new CardLevelLimitManager();
		CardProductLimitDto objCardProductLimitDto =  objCardLevelLimitManager.getCardProductLimitDto(objForm.getCardNo());
		
		CardProductManager objCardProductManager = new CardProductManager();
		String eComEnable = objCardProductManager.getCardProductDto(objCardProductLimitDto.getCardProductId()).geteComEnable();
		
		objForm.getPreListData();
		
		try {
			CardProductLimitSetupForm objCardProductLimitSetupForm = new CardProductLimitSetupForm();
			BeanUtils.copyProperties(objCardProductLimitSetupForm, objCardProductLimitDto);
			objForm.setObjCardProductLimitSetupForm(objCardProductLimitSetupForm);
			objForm.setCardProducteComEnable(eComEnable);
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		
		boolean productLimitValidation = true;
		// validation against product limit
		/*if((objCardProductLimitDto.getMaxCreditAmtPerTrnx() < Float.valueOf(objForm.getAmtPerTranx()).floatValue()) || 
			(objCardProductLimitDto.getMaxCashLimitPerTrnx() < Float.valueOf(objForm.getCashPerTranx()).floatValue()) || 
			(objCardProductLimitDto.getMaxCreditTrnxPerDay() < Integer.valueOf(objForm.getDailyLimitCount()).intValue()) || 
			(objCardProductLimitDto.getMaxCreditLimitPerDay() < Float.valueOf(objForm.getDailyLimitAmt()).floatValue()) || 
			(objCardProductLimitDto.getMaxCashLimitPerDay() < Float.valueOf(objForm.getDailyCashAmt()).floatValue()) || 
			(objCardProductLimitDto.getMaxCashTrnxPerDay() < Float.valueOf(objForm.getDailyCashCount()).floatValue()) || 
			((objCardProductLimitDto.geteComAmt() < Float.valueOf(objForm.geteComAmt()).floatValue()))
		){
			productLimitValidation = false;
		}
		
		if(Integer.valueOf(objForm.getCardHolderType()) == 1){
			if((objCardProductLimitDto.getMaxCardLimitPerSalary() < Float.valueOf(objForm.getPurchaseLimit()).floatValue()) || 
					(objCardProductLimitDto.getMaxCashLimiPerCrl() < Float.valueOf(objForm.getCashLimit()).intValue())
				){
					productLimitValidation = false;
				}
		}else if(Integer.valueOf(objForm.getCardHolderType()) == 2){
			if((objCardProductLimitDto.getMaxSuppCreditLimit() < Float.valueOf(objForm.getPurchaseLimit()).floatValue()) || 
					(objCardProductLimitDto.getMaxSuppCashLimit() < Float.valueOf(objForm.getCashLimit()).intValue())
				){
					productLimitValidation = false;
				}
		}*/
		
		/*if("N".equals(eComEnable) && "Y".equals(objForm.geteComEnable())){
			productLimitValidation = false;
		}*/
		
		if(!productLimitValidation){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.productlimitfailed"));
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("USERID");

		// Dto Creation
		CustomerLimitsDto objDto = new CustomerLimitsDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedBy(userId);
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
		CardLevelLimitManager objManager = new CardLevelLimitManager();
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

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardLevelLimitManager objManager = new CardLevelLimitManager();
		CardLevelLimitSetupForm objForm = (CardLevelLimitSetupForm) form;

		CustomerLimitsDto objDto = objManager.getCustomerLimitDto(request.getParameter("cardNo"));
		
		CardLevelLimitManager objCardLevelLimitManager = new CardLevelLimitManager();
		CardProductLimitDto objCardProductLimitDto =  objCardLevelLimitManager.getCardProductLimitDto(objForm.getCardNo());
		
		CardProductManager objCardProductManager = new CardProductManager();
		
		CardManager objCardManager = new CardManager();
	
		objForm.getPreListData();
		
		ActionErrors errors = null;
		
		if(objCardProductLimitDto != null){

			try {

				String eComEnable = objCardProductManager.getCardProductDto(objCardProductLimitDto.getCardProductId()).geteComEnable();
				CardsDto objCardsDto = objCardManager.getCard(objForm.getCardNo());

				BeanUtils.copyProperties(objForm, objDto);

				CardProductLimitSetupForm objCardProductLimitSetupForm = new CardProductLimitSetupForm();
				BeanUtils.copyProperties(objCardProductLimitSetupForm, objCardProductLimitDto);
				objForm.setObjCardProductLimitSetupForm(objCardProductLimitSetupForm);
				objForm.setCardProducteComEnable(eComEnable);
				objForm.setCardHolderType(String.valueOf(objCardsDto.getCardHolderType()));

			} catch (Exception e) {
				logger.error(e);
				System.out
				.println("Error converting to form bean in CardProductDispatchAction in change method: "
						+ e.getMessage());
				throw new TPlusException(
						"Could not populate the form bean in CardProductDispatchAction method: "
						+ e);
			}
			
		}else{
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nocardproductlimit"));
			saveErrors(request, errors);
			
			request.setAttribute("ACTION", "cancel");
			
			return mapping.findForward("noproductlimit");
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		
		return mapping.findForward("success");
	}
}
