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
import org.transinfo.cacis.controller.settings.CardProductRulesManager;
import org.transinfo.cacis.dto.settings.CardProductRulesDto;
import org.transinfo.cacis.formbean.settings.CardProductRulesSetupForm;

@SuppressWarnings("deprecation")
public class CardProductRulesDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(CardProductRulesDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardProductRulesSetupForm objOldForm = (CardProductRulesSetupForm) form;
		CardProductRulesSetupForm objForm = new CardProductRulesSetupForm();
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

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		// Form Validations
		CardProductRulesSetupForm objForm = (CardProductRulesSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		// DTO Creation
		CardProductRulesDto objDto = new CardProductRulesDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			
			CardProductRulesManager objCardProductRulesManager = new CardProductRulesManager();
			boolean exist = objCardProductRulesManager.checkExistRecord(objDto);
			
			if(exist){
				
				request.setAttribute("ACTION", "add");
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardproductrulesexists"));
				saveErrors(request, errors);
				
			}else{
				objDto.setUserId((String) request.getSession().getAttribute("USERID"));
				
				//set OL_MCC_LIST for CardProductRulesDto
				String mccList = "";
				if(objForm.getSelMccList() != null) {
					for(int i=0;i<objForm.getSelMccList().length;i++) {
						if(i < (objForm.getSelMccList().length - 1)) {
							mccList += objForm.getSelMccList()[i] + ", ";
						} else {
							mccList += objForm.getSelMccList()[i];
						}
					}
				}
				objDto.setOlMccList(mccList);
				
				boolean boolAdd = objCardProductRulesManager.add(objDto);
			
				//set Selected List
				objDto = objCardProductRulesManager.getCardProductRulesDto(objDto.getCardProductId());
				objForm.setSelectedListSet(objDto.getSelectedListSet());
				
				if (!boolAdd) {
					request.setAttribute("ACTION", "add");
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addfailed"));
					saveErrors(request, errors);
				} else {
					request.setAttribute("ACTION", "cancel");
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addSuccess"));
					saveErrors(request, errors);
				}
			}
		} catch (Exception e) {
			throw new TPlusException(
					"In CardProductRulesDispatchAction add method: " + e);
		}
		
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
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
		CardProductRulesSetupForm objForm = (CardProductRulesSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// Dto Creation
		CardProductRulesDto objDto = new CardProductRulesDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setUserId((String) request.getSession().getAttribute("USERID"));
			
			//set OL_MCC_LIST for CardProductRulesDto
			String mccList = "";
			if(objForm.getSelMccList() != null) {
				for(int i=0;i<objForm.getSelMccList().length;i++) {
					if(i < (objForm.getSelMccList().length - 1)) {
						mccList += objForm.getSelMccList()[i] + ", ";
					} else {
						mccList += objForm.getSelMccList()[i];
					}
				}
			}
			objDto.setOlMccList(mccList);
			
			CardProductRulesManager objCardProductRulesManager = new CardProductRulesManager();
			
			boolean boolUpdate = objCardProductRulesManager.update(objDto);
			//set Selected List
			objDto = objCardProductRulesManager.getCardProductRulesDto(objDto.getCardProductId());
			objForm.setSelectedListSet(objDto.getSelectedListSet());
			
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
		} catch (Exception e) {
			throw new TPlusException(
					"In CardProductRulesDispatchAction update method: " + e);
		}
		
		objForm.getPreListData();
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		// Action execution
		CardProductRulesManager objManager = new CardProductRulesManager();
		CardProductRulesSetupForm objForm = (CardProductRulesSetupForm) form;
		objForm.setIssuerId((String) request.getSession(false)
				.getAttribute("ISSUER"));

		CardProductRulesDto objDto = objManager.getCardProductRulesDto(request
				.getParameter("cardProductId"));
		try {
			BeanUtils.copyProperties(objForm, objDto);
			objForm.getPreListData();
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardProductRulesDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardProductRulesDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

}
