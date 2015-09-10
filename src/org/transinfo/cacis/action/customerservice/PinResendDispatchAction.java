package org.transinfo.cacis.action.customerservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

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
import org.transinfo.cacis.common.CardEncryption;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.controller.cardproduction.PinPrintingManager;
import org.transinfo.cacis.controller.customerservice.PinResendManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingDto;
import org.transinfo.cacis.dto.customerservice.CardStatusRemarksDto;
import org.transinfo.cacis.dto.customerservice.PinResendDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;
import org.transinfo.cacis.formbean.customerservice.PinResendForm;

@SuppressWarnings( { "unchecked", "deprecation" })
public class PinResendDispatchAction extends BaseDispatchAction {

	/*
	 * this method is for checking CustomerServiceDataBean has been Existed in
	 * the session
	 */
	public ActionForward checkSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {
		// in this method removing the attribute from session that starts and
		// ends with dollor symbol($)
		// bcz we are setting CustomerServiceDataBean in session if he clicks
		// the link remove old one and set new one

		processSession(request);

		PinResendForm objForm = (PinResendForm) form;
		PinResendForm newObjForm = new PinResendForm();

		BeanUtils.copyProperties(objForm, newObjForm);
		return mapping.findForward("success");
	}

	/*
	 * this method is for Get all the Data(for all customer service screens)
	 * using model's(CustomerSevice class getCustomerServiceData() method)
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		ActionErrors errors = null;
		PinResendForm objForm = (PinResendForm) form;

		// to check cardnumber Entered or not
		if (objForm.getCardNumber() != null
				&& objForm.getCardNumber().equals("")) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnorequired"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		boolean isError = false;
		String errormsg = "";

		// Dto creation
		PinResendDto objPinResendDto = new PinResendDto();

		try {

			PinResendManager objPinResendManager = new PinResendManager();
			CardProductManager objCardProductManager = new CardProductManager();
			PinPrintingManager objPinPrintingManager = new PinPrintingManager();
			CardManager objCardManager = new CardManager();

			// to check cardnumber existed or not
			//CardsDto objCardsDto = objPinResendManager.getCard(objForm.getCardNumber());
			CardsDto objCardsDto = objCardManager.getCardByEncryptedData(CardEncryption.encrypt(objForm.getCardNumber()));

			if(objCardsDto == null){
				isError = true;
				errormsg = "error.cardnumbernotexit";
			}else{
				CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(objCardsDto.getCardProductId());
				String pinRequired = objCardProductDto.getPinRequired();
				if(pinRequired != null && "Y".equals(pinRequired)){
					String pinMailerRequired = objCardProductDto.getPinMailerRequired();
					if(pinMailerRequired != null && "Y".equals(pinMailerRequired)){
						boolean isEmbossed = true; //objPinPrintingManager.isEmbossed(objForm.getCardNumber());
						if(isEmbossed){
							if((objCardsDto.getCardStatusId() == CommonCodes.CARD_ACTIVE) && "A".equals(objCardsDto.getStatus())){
								objPinResendDto = objPinResendManager.getPinResendApp(objForm.getCardNumber());
								if(objPinResendDto == null){
									PinPrintingDto objPinPrintingDto = objPinPrintingManager.getPinPrintNotProcessed(objForm.getCardNumber());
									if(objPinPrintingDto != null){
										isError = true;
										errormsg = "error.pinresendappnotprint";
									}
								}else{
									isError = true;
									errormsg = "error.pinresendappopen";
								}
							}else{
								isError = true;
								errormsg = "error.pinresendcardcheck";
							}
						}else{
							isError = true;
							errormsg = "error.pinresendcardnotemboss";
						}
					}else{
						isError = true;
						errormsg = "error.cardnumbernotpinmailerrequired";
					}
				}else{
					isError = true;
					errormsg = "error.cardnumbernotpinrequired";
				}
			}

			if (isError) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError(errormsg));
				saveErrors(request, errors);
				return mapping.findForward("success");
			} else {
				// Action Execution
				Collection cardReplaceList = objPinResendManager.search(CardEncryption.encrypt(objForm.getCardNumber()));
				request.getSession(false).setAttribute("$CARDREPLACELIST$",cardReplaceList);
				CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList).get(0);

				// After the Search Result
				BeanUtils.copyProperties(objForm, objCustService);

				// this is setting remarks
				CardStatusRemarksDto objCardStatusRemarksDto = objPinResendManager.getCardStatusRemarks(Long.valueOf(objCustService.getCardStatusId()).longValue(), objCustService.getCardNumber());
				if (objCardStatusRemarksDto != null) {
					objForm.setRemarks(objCardStatusRemarksDto.getRemarks());
				}

				// this for setting the billing address
				for (Iterator it = objCustService.getApplicationAddress().iterator(); it.hasNext();) {
					CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
					if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("H")
							&& objCustService.getBillingTo().equals("H")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
					} else if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("S")
							&& objCustService.getBillingTo().equals("S")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
					} else {
						if (addressDto.getAddressType() != null
								&& addressDto.getAddressType().equals("C")
								&& objCustService.getBillingTo().equals("C")) {
							BeanUtils.copyProperties(objForm.getRequestAddress(),
									addressDto);
							break;
						}
					}
				}
			}// cardnumber check else close
		} catch (Exception e) {

			System.out
			.println("Error converting to form bean in PinResendDispatchAction: "
					+ e);
			throw new TPlusException(
					"Could not populate the form bean in PinResendDispatchAction: "
							+ e);
		}

		objForm.getPreListData();

		saveToken(request);
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}

	// Mehtods for PinResendForm
	/*
	 * this method for saving the PinResendForm data
	 */
	public ActionForward pinResendAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		ActionErrors errors = null;
		// Form Validations
		PinResendForm objForm = (PinResendForm) form;
		errors = objForm.validate(mapping, request);
		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "search");
			return mapping.findForward("success");
		}

		// checking cardstatus is active or not if active only allow to resend
		if (!objForm.getCardStatusId().equals(
				String.valueOf(CommonCodes.CARD_ACTIVE))) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.pinresendcardcheck"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		// DTO Creation
		PinResendDto objPinResendDto = new PinResendDto();
		CustomerAddressDto objAddressDto = new CustomerAddressDto();

		CardManager objCardManager = new CardManager();

		try {

			// this to copy the form data to CardClosedto
			BeanUtils.copyProperties(objPinResendDto, objForm);

			// this copying AddressForm object from CardReplacementForm to
			// CustomerAddressDto
			BeanUtils
			.copyProperties(objAddressDto, objForm.getRequestAddress());

			// this to setting the CustomerAddressDto to PinResendDto
			objPinResendDto.setCustomerAddDto(objAddressDto);

			// settiing the application details from constants
			objPinResendDto.setApplicationId(IdsGenartion
					.GenerateApplicationId());
			objPinResendDto
			.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_NEW);
			objPinResendDto
			.setApplicationType(CommonCodes.APPLICATIONTYPE_PINRESEND);

			CardsDto objCardsDto = objCardManager.getCard(objForm.getCardNumber());
			objPinResendDto.setCustomerId(objCardsDto.getCustomerId());

		} catch (Exception e) {
			System.out
			.println("Error converting to form bean PinResendDispatchAction pinResendAddAdd method : "
					+ e);
			throw new TPlusException(
					"Could not populate the form bean PinResendDispatchAction pinResendAddAdd method: "
							+ e);
		}

		// Action Execution
		PinResendManager objManager = new PinResendManager();
		boolean boolResend = objManager.pinResendAdd(objPinResendDto);

		if (!boolResend) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addfailed"));
			errors.add("Error",
					new ActionError("error.applicationCreationfail"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}

		resetToken(request);
		return mapping.findForward("success");
	}

	// Mehtods for PinResendForm Process

	/*
	 * this method for showing the pinResendprocesslist
	 */

	public ActionForward pinResendProcessSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		String issuer = (String)request.getSession().getAttribute("ISSUER");

		int pageNo = 0;

		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("NEXT")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request.getParameter("pageNo")) + 1;
			}
		}
		
		if (request.getParameter("mode") != null && ((String) request.getParameter("mode")).equals("PREV")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
			}
		}

		PinResendForm objForm = (PinResendForm) form;

		HttpSession session = request.getSession(true);
		String branchId = (String)session.getAttribute("LOGEDUSERBRANCH");
		objForm.setBranchId(branchId);

		PinResendDto objDto = new PinResendDto();
		try {
			objForm.setIssuerId(issuer);
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out
			.println("Error converting to form bean in PinResendDispatchAction pinResendProcessSearch method: "
					+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean PinResendDispatchAction pinResendProcessSearch : "
							+ e);
		}

		if(objForm.getCardNumber() != null && !"".equals(objForm.getCardNumber())){
			objDto.setEncryptedCardno(CardEncryption.encrypt(objForm.getCardNumber()));
		}

		PinResendManager objManager = new PinResendManager();
		Collection cardCloseProcessList = objManager.pinResendProcessSearch(objDto, pageNo);

		request.setAttribute("SEARCHLIST", cardCloseProcessList);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		return mapping.findForward("success");
	}

	/*
	 * this method is for showing the particular record to accept or reject the
	 * pinResend form
	 */
	public ActionForward pinResend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		PinResendForm objForm = (PinResendForm) form;
		// this dto for search condition
		PinResendDto objPinResendDto = new PinResendDto();
		objForm.setCardNumber((String) request.getParameter("cardNumber"));

		try {
			
			BeanUtils.copyProperties(objPinResendDto, objForm);

			PinResendManager objManager = new PinResendManager();

			// this for calling search method in DAOImpl to get the
			// CustomerServiceDataBean
			Collection cardReplaceList = objManager.search(objForm.getCardNumber());
			request.getSession(false).setAttribute("$CARDREPLACELIST$", cardReplaceList);
			CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList).get(0);

			// this is for loading data from pinResend_forms table
			objPinResendDto = objManager.getPinResendDto((String) request.getParameter("applicationId"));

			// copying address from this PinResendDto to CardReplacementForm
			// Request address object
			BeanUtils.copyProperties(objForm.getRequestAddress(), objPinResendDto.getCustomerAddDto());

			// setting the values from PinResendDto to CardReplacementForm
			objForm.setRemarks(objPinResendDto.getRemarks());

			// this value to pass reject method to update cardcloseForms table
			// when clicks rejection
			objForm.setApplicationId(objPinResendDto.getApplicationId());

			// this for setting the values to from hidden fileds
			objForm.setCardNumber(objCustService.getCardNumber());
			objForm.setCustomerId(objCustService.getCustomerId());
			objForm.setBranchId(objCustService.getBranchId());
			objForm.setCardProductId(objCustService.getCardProductId());
			objForm.setCardHolderType(objCustService.getCardHolderType());

		} catch (Exception e) {
			System.out
			.println("Error converting to form bean in CardReplacementDispatchAction pinResend: "
					+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardReplacementProcessDispatchAction pinResend:  "
							+ e);
		}

		// this for getting ReasonPreList
		objForm.getPreListData();

		saveToken(request);
		request.setAttribute("ACTION", "pinresend");
		return mapping.findForward("success");
	}

	/*
	 * this method is used for accepting the pinResend form
	 */

	public ActionForward pinResendAccept(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		PinResendForm objForm = (PinResendForm) form;
		// DTO Creation
		PinResendDto objPinResendDto = new PinResendDto();

		String userID = (String) request.getSession(false).getAttribute("USERID");

		try {
			BeanUtils.copyProperties(objPinResendDto, objForm);
			// to set the Customer Account to newly created cards object
			Set objAccount = ((CustomerServiceDataBean) ((ArrayList) request
					.getSession(false).getAttribute("$CARDREPLACELIST$"))
					.get(0)).getCustomerAccount();
			for (Iterator it = objAccount.iterator(); it.hasNext();) {
				CustomerAccountDto objCustAcc = (CustomerAccountDto) it.next();
				objPinResendDto.setCustomerAccountDto(objCustAcc);
			}

		}

		catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		PinResendManager objManager = new PinResendManager();
		boolean boolAccept = objManager.pinResendAccept(objPinResendDto, userID);
		if (!boolAccept) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.acceptfailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "close");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.acceptSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "cancel");
		}
		// Success
		resetToken(request);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for rejecting the cardreplacement form
	 */
	public ActionForward pinResendReject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		PinResendForm objForm = (PinResendForm) form;

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// DTO Creation
		PinResendDto objPinResendDto = new PinResendDto();
		try {

			// objPinResendDto.setApplicationId((String)request.getParameter("applicationId"));
			BeanUtils.copyProperties(objPinResendDto, objForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: "
					+ e.getMessage());
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		PinResendManager objManager = new PinResendManager();
		boolean boolReject = objManager.pinResendReject(objPinResendDto);

		if (!boolReject) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.rejectfailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "close");

		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.rejectSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "cancel");
		}
		// Success
		resetToken(request);
		return mapping.findForward("success");
	}

	/*
	 * for Customer History Details
	 */
	public ActionForward customerHistory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		// CardReplacementForm objForm = (CardReplacementForm)form;
		// Action Execution
		PinResendManager objManager = new PinResendManager();
		Collection historyList = objManager.customerHistory((String) request
				.getParameter("NricNo"));
		if (historyList != null) {
			request.setAttribute("CUSTOMERHISTORYLIST", historyList);
		}
		return mapping.findForward("history");
	}

	/*
	 * for Card ACtivity Details
	 */
	public ActionForward cardActivities(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		// CardReplacementForm objForm = (CardReplacementForm)form;

		// Action Execution
		PinResendManager objManager = new PinResendManager();
		Collection cardActivitesList = objManager.cardActivities(Long
				.parseLong((String) request.getParameter("CustServCardNo")));
		if (cardActivitesList != null) {
			request.setAttribute("SEARCHLIST", cardActivitesList);
		}
		return mapping.findForward("cardactivities");
	}

	/*
	 * this method checks in session wether setted Attributes like $anyname$
	 * existed in session if exists it removes that attribute
	 */
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

}
