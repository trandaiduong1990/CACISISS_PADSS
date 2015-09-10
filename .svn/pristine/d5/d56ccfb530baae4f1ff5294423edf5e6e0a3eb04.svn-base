package org.transinfo.cacis.action.customerservice;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.controller.customerservice.CustomerScreenProcessManager;
import org.transinfo.cacis.dto.batchprocess.CardATMLinkDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.formbean.customerservice.AccountChangeForm;

@SuppressWarnings( { "deprecation", "unchecked", "unused" })
public class AccountChangeDispatchAction extends BaseDispatchAction {

	public ActionForward checkSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		processSession(request);
		
		AccountChangeForm objForm = (AccountChangeForm) form;
		AccountChangeForm newObjForm = new AccountChangeForm();

		BeanUtils.copyProperties(objForm, newObjForm);
		
		return mapping.findForward("success");
	}

	public void processSession(HttpServletRequest request) {

		if (request.getSession(false) != null) {
			HttpSession session = request.getSession(false);
			Enumeration listCustServ = session.getAttributeNames();
			while (listCustServ.hasMoreElements()) {
				String custObj = (String) listCustServ.nextElement();
				if (custObj.startsWith("$") && custObj.endsWith("$")) {
					session.removeAttribute(custObj);
				}
			}
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		AccountChangeForm objForm = (AccountChangeForm) form;
		//errors = objForm.validate(mapping, request);

		/*if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}*/

		try {
			long cardNo = Long.valueOf(objForm.getCardNumber());
		} catch (Exception e) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnoinvalid"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}
		
		CustomerScreenProcessManager objManager = new CustomerScreenProcessManager();
		
		String forward = "success";

		try {

			// get card detail
			CardATMLinkDto objCardATMLinkDto = objManager.getCardATMLinkDto(objForm.getCardNumber());
			
			//AccountChangeForm objFormNew = new AccountChangeForm();
			//BeanUtils.copyProperties(objForm, objFormNew);
			
			if (objCardATMLinkDto != null) {

				request.getSession(false).setAttribute("$CARDREPLACELIST$", "TEST");
				
				objForm.setButEnable("Y");
				objForm.setCardNumber(String.valueOf(objCardATMLinkDto.getCardNumber()));
				objForm.setSavingAccount(objCardATMLinkDto.getSavingAccount());
				objForm.setCurrentAccount(objCardATMLinkDto.getCurrentAccount());
				objForm.setDefaultAccount(Character.toString(objCardATMLinkDto.getDefaultAccount()));
				
				objForm.setCollectoralAccount(objCardATMLinkDto.getCollectoralAccount());
				objForm.setCollectoralAmt(String.valueOf(objCardATMLinkDto.getCollectoralAmt()));
				objForm.setAutoPayAccount(objCardATMLinkDto.getAutoPayAccount());
				//objForm.setAutoPayAccounton(Character.toString(objCardATMLinkDto.getAutoPayAccounton()));
				
				if(objCardATMLinkDto.getAutoPayAccounton() != null && "Y".equals(Character.toString(objCardATMLinkDto.getAutoPayAccounton()))){
					objForm.setAutoPayAccounton(true);
				}else{
					objForm.setAutoPayAccounton(false);
				}
				

				if(objCardATMLinkDto.getAutoPayDisable() != null && "Y".equals(Character.toString(objCardATMLinkDto.getAutoPayDisable()))){
					objForm.setAutoPayDisable(true);
				}else{
					objForm.setAutoPayDisable(false);
				}
				
				if("470532".equals(String.valueOf(objCardATMLinkDto.getCardNumber()).substring(0, 6))){
					objForm.setAtmLink(true);
				}
								
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				return mapping.findForward(forward);
			}

		} catch (Exception e) {

			System.out.println("Error converting to form bean in CardChangeDispatchAction: " + e);
			throw new TPlusException("Could not populate the form bean in CardChangeDispatchAction: " + e);
		}
		
		saveToken(request);
		request.setAttribute("SEARCHLIST", "search");
		return mapping.findForward(forward);
	}

	public ActionForward accountChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {


		ActionErrors errors = null;
		
		AccountChangeForm objForm = (AccountChangeForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		// validate and save card changes
		String forward = "success";
		
		try {

			CustomerScreenProcessManager objManager = new CustomerScreenProcessManager();
			CardManager objCardManager = new CardManager();
			
			CardATMLinkDto objCardATMLinkDto = new CardATMLinkDto();
			
			try {
				
				BeanUtils.copyProperties(objCardATMLinkDto, objForm);
				
				//if(objForm.getAutoPayAccounton() != null && "Y".equals(objForm.getAutoPayAccounton())){
				if(objForm.isAutoPayAccounton()){
					objCardATMLinkDto.setAutoPayAccounton('Y');
				}else{
					objCardATMLinkDto.setAutoPayAccounton('N');
				}
				
				if(objForm.isAutoPayDisable()){
					objCardATMLinkDto.setAutoPayDisable('Y');
				}else{
					objCardATMLinkDto.setAutoPayDisable('N');
				}
				
				String cardNo = objForm.getCardNumber();
				CardsDto objCardsDto = objCardManager.getCard(cardNo);
				
				objCardATMLinkDto.setCard(objCardsDto);
				
				boolean boolUpdate = objManager.update(objCardATMLinkDto);

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
				
			} catch (Exception e) {
				System.out.println("Error converting to form bean in AccountChangeDispatchAction accountChange method: " + e.getMessage());
				throw new TPlusException("Could not populate the form bean in AccountChangeDispatchAction accountChange method " + e);
			}
			
			return mapping.findForward("success");
			
		} catch (Exception e) {
			System.out.println("Error converting to form bean in CardChangeDispatchAction: " + e);
			throw new TPlusException("Could not populate the form bean in CardChangeDispatchAction: " + e);
		}
		
	}

}
