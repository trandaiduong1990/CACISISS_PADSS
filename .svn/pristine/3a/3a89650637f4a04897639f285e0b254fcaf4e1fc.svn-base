package org.transinfo.cacis.action.customerservice;

import java.util.Date;
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
import org.transinfo.cacis.controller.customerservice.AccountAdjustmentManager;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.customerservice.AccountAdjustmentDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.formbean.customerservice.AccountAdjustmentForm;

@SuppressWarnings( { "deprecation", "unchecked","unused" })
public class AccountAdjustmentDispatchAction extends BaseDispatchAction {
	private Date effectiveDate;


	public ActionForward checkSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {
		//System.out.println("++++++++++++AccountAdjustmentDispatchAction+++++++++checkSession++++++++++++++++");
		processSession(request);
		//System.out.println("++++++++++++AccountAdjustmentDispatchAction+++++++++processSession++++++++++++++++");

		//CardChangeForm objForm = (CardChangeForm) form;

		AccountAdjustmentForm objForm = (AccountAdjustmentForm) form;
		//System.out.println("++++++++++++++++++++after form+++++++++++++++++++++++");
		AccountAdjustmentForm newObjForm = new AccountAdjustmentForm();
		//System.out.println("++++++++++++++++++++after obj form+++++++++++++++++++++++");
		try{
			BeanUtils.copyProperties(objForm, newObjForm);
		}
		catch(Exception e){
			System.out.println("++++++++++++++++++++Exception copyProperties+++++++"+e);
		}
		System.out.println("+++++++++++ return success+++checkSession++++++++++++++");
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
		System.out.println("+++++++AccountAdjustmentForm : Search +++++++");

		AccountAdjustmentForm objForm = (AccountAdjustmentForm) form;

		if (objForm.getCardNo() != null && objForm.getCardNo().equals("")) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnorequired"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		try {
			long cardNo = Long.valueOf(objForm.getCardNo());
		} catch (Exception e) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnoinvalid"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		AccountAdjustmentManager objManager =new AccountAdjustmentManager();

		request.setAttribute("ACTION","add");

		try {

			// get card detail
			CardsDto cardDto = objManager.getCardDto(objForm.getCardNo());
			if (cardDto != null) {
				CustomerAccountDto objCustomerAccount = objManager.getCustomerAccountDto(cardDto.getAccountId());
				if(objCustomerAccount != null){
					CardProductDto objCardProductDto= objManager.getCardProductDto(cardDto.getCardProductId());

					AccountAdjustmentForm  objNewForm=new AccountAdjustmentForm();

					try
					{

						objNewForm.setCardLimit(String.valueOf(objCustomerAccount.getCreditLimit()));
						objNewForm.setLimitUsed(String.valueOf(objCustomerAccount.getLimitUsed()));
						objNewForm.setPurchaseUsed(String.valueOf(objCustomerAccount.getSaleUsed()));
						objNewForm.setCashUsed(String.valueOf(objCustomerAccount.getCashUsed()));

						objNewForm.setCardNo(String.valueOf(cardDto.getCardNumber()));
						objNewForm.setCardStatus(cardDto.getCardStatus());

						objNewForm.setCardProductName(objCardProductDto.getCardProductName());

						BeanUtils.copyProperties(objForm, objNewForm);

					} catch (Exception e) {
						System.out.println("Error converting to AccountAdjustmentForm/CustomerAccountDto bean: " + e);
						throw new TPlusException("Could not populate the form bean: " + e);
					}

					saveToken(request);
					request.setAttribute("$ACCOUNTADJUSTMENT$", "search");
					//request.setAttribute("ACTION","search");
					saveErrors(request, errors);
					return mapping.findForward("success");
				}
				else{
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.CustomerAccountDtonotexit"));
					saveErrors(request, errors);
					return mapping.findForward("success");
				}

			}
			else{

				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				return mapping.findForward("success");
			}


		} catch (Exception e) {

			System.out
			.println("Error converting to form bean in AccountAdjustmentDispatchAction: "
					+ e);
			throw new TPlusException(
					"Could not populate the form bean in AccountAdjustmentDispatchAction: "
					+ e);
		}
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {
		System.out.println("+++++++AccountAdjustmentForm : Change +++++++");
		ActionErrors errors = null;

		AccountAdjustmentForm objForm = (AccountAdjustmentForm) form;

		errors = objForm.validate(mapping,request);

		if (errors != null && !errors.isEmpty()) {
			request.setAttribute("$ACCOUNTADJUSTMENT$", "search");
			request.setAttribute("ACTION","add");
			saveErrors(request, errors);
			return mapping.findForward("success");
		}
		
		AccountAdjustmentManager objManager = new AccountAdjustmentManager();
		
		// DTO Creation
		AccountAdjustmentDto objDto=new AccountAdjustmentDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out.println("Error converting to AccountAdjustmentForm bean: " + e);
			throw new TPlusException("Could not populate the AccountAdjustmentForm bean: " + e);
		}

		objManager =new AccountAdjustmentManager();
		boolean boolAdd = objManager.add(objDto);

		if(!boolAdd)
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.AccountAdjustmentfailed"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);

			CardsDto cardDto = objManager.getCardDto(objForm.getCardNo());
			CustomerAccountDto objCustomerAccount = objManager.getCustomerAccountDto(cardDto.getAccountId());

			objForm.setLimitUsed(String.valueOf(objCustomerAccount.getLimitUsed()));
			objForm.setPurchaseUsed(String.valueOf(objCustomerAccount.getSaleUsed()));
			objForm.setCashUsed(String.valueOf(objCustomerAccount.getCashUsed()));

		}

		System.out.println("Record Added" + boolAdd);
		// Success
		request.setAttribute("ACTION","cancel");
		//return mapping.findForward("change");

		request.setAttribute("$ACCOUNTADJUSTMENT$", "search");
		return mapping.findForward("success");
	}

}
