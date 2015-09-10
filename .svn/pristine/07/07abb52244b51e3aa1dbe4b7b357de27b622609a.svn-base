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
import org.transinfo.cacis.controller.customerservice.AddProductManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.controller.settings.CardTypeManager;
import org.transinfo.cacis.controller.settings.ProductTranxManager;
import org.transinfo.cacis.dto.customerservice.AddProductSetupDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductTranxDto;
import org.transinfo.cacis.dto.settings.CardTypeDto;
import org.transinfo.cacis.dto.settings.TranxTypeDto;
import org.transinfo.cacis.formbean.customerservice.AddProductSetupForm;
import org.transinfo.cacis.formbean.settings.CardProductSetupForm;
import org.transinfo.cacis.formbean.settings.CardTypeSetupForm;
import org.transinfo.cacis.formbean.settings.ProductTranxSetupForm;

@SuppressWarnings("deprecation")
public class ProductTranxDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(ProductTranxDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ProductTranxSetupForm objOldForm = (ProductTranxSetupForm) form;
		ProductTranxSetupForm objForm = new ProductTranxSetupForm();

		try {
			BeanUtils.copyProperties(objForm, objOldForm);
			objOldForm.getPreListData();
			request.setAttribute("tranxTypeList", objOldForm.getTranxTypeList());
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
		ProductTranxSetupForm objForm = (ProductTranxSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			request.setAttribute("tranxTypeList", objForm.getTranxTypeList());
			return mapping.getInputForward();
		}

		// DTO Creation
		CardProductTranxDto objDto = new CardProductTranxDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.id.setProductId(objForm.getCardProductId());
			objDto.id.setChannel(objForm.getChannelId());
			objDto.setUserId((String) request.getSession().getAttribute("USERID"));
			
			ProductTranxManager objProductTranxManager = new ProductTranxManager();
			boolean exist = objProductTranxManager.checkExistRecord(objDto);
			
			if(exist){
				
				request.setAttribute("ACTION", "add");
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardproducttranxexists"));
				saveErrors(request, errors);
				
			}else{
				
				TranxTypeDto objTranxTypeDto = new TranxTypeDto();
				//get TranxTypeDto
				objTranxTypeDto = objProductTranxManager.getTranxTypeDto(objForm.getTranxType());
				
				objDto.setTranxId(objTranxTypeDto);
				boolean boolAdd = objProductTranxManager.addProductTranx(objDto);
			
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
					"In ProductTranxDispatchAction add method: " + e);
		}
		
		objForm.getPreListData();
		request.setAttribute("tranxTypeList", objForm.getTranxTypeList());
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
		ProductTranxSetupForm objForm = (ProductTranxSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// Dto Creation
		CardProductTranxDto objDto = new CardProductTranxDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.id.setProductId(objForm.getCardProductId());
			objDto.id.setChannel(objForm.getChannelId());
			objDto.setUserId((String) request.getSession().getAttribute("USERID"));
			
			ProductTranxManager objProductTranxManager = new ProductTranxManager();
			
			TranxTypeDto objTranxTypeDto = new TranxTypeDto();
			//get TranxTypeDto
			objTranxTypeDto = objProductTranxManager.getTranxTypeDto(objForm.getTranxType());
			
			objDto.setTranxId(objTranxTypeDto);
			boolean boolUpdate = objProductTranxManager.update(objDto);

			//String userActivity = "";

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
					"In ProductTranxDispatchAction update method: " + e);
		}
		
		objForm.getPreListData();
		request.setAttribute("tranxTypeList", objForm.getTranxTypeList());
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		// Action execution
		ProductTranxSetupForm objOldForm = (ProductTranxSetupForm) form;
		ProductTranxSetupForm objForm = new ProductTranxSetupForm();

		try {
			BeanUtils.copyProperties(objForm, objOldForm);
			objOldForm.setIssuerId((String) request.getSession(false)
					.getAttribute("ISSUER"));
			objOldForm.getPreListData();
			request.setAttribute("tranxTypeList", objOldForm.getTranxTypeList());
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

}
