package org.transinfo.cacis.action.customerservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.applicationforms.CardsRenewalManager;
import org.transinfo.cacis.controller.authorization.SystemParamManager;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.controller.customerservice.CardReplacementManager;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.dto.authorization.SystemParamDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.customerservice.CardCloseDto;
import org.transinfo.cacis.dto.customerservice.CardLimitAdjustmentDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementDto;
import org.transinfo.cacis.dto.customerservice.CardStatusRemarksDto;
import org.transinfo.cacis.dto.customerservice.CreditSplitDto;
import org.transinfo.cacis.dto.customerservice.PinResendDto;
import org.transinfo.cacis.formbean.customerservice.CardRenewalForm;
import org.transinfo.cacis.formbean.customerservice.CardReplacementForm;
import org.transinfo.cacis.formbean.customerservice.CreditSplitForm;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;
import org.transinfo.cacis.formbean.customerservice.LimitForm;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings( { "unchecked", "deprecation" })
public class CardRenewalDispatchAction extends BaseDispatchAction {

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
		String cardAction = request.getParameter("cardAction");
		request.setAttribute("CARDACTION", cardAction);

		CardRenewalForm objForm = (CardRenewalForm) form;
		CardRenewalForm newObjForm = new CardRenewalForm();
		
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

		CardReplacementForm objForm = (CardReplacementForm) form;

		// to check cardnumber Entered or not
		if (objForm.getCardNumber() != null
				&& objForm.getCardNumber().equals("")) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnorequired"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		// Dto creation
		CardReplacementDto objDto = new CardReplacementDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			CardReplacementManager objManager = new CardReplacementManager();

			// to check cardnumber existed or not
			CardsDto cardDto = new CardsDto();
			cardDto.setCardNumber(objDto.getCardNumber());
			boolean cardNoCheck = objManager.validate(cardDto, 0);
			if (cardNoCheck) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				return mapping.findForward("success");
			} else {
				// Action Execution
				Collection cardReplaceList = objManager.search(objDto);
				request.getSession(false).setAttribute("$CARDREPLACELIST$",
						cardReplaceList);
				CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList)
						.get(0);
				;

				// After the Search Result
				BeanUtils.copyProperties(objForm, objCustService);

				// this is setting remarks
				CardStatusRemarksDto objCardStatusRemarksDto = objManager
						.getCardStatusRemarks(Long.valueOf(
								objCustService.getCardStatusId()).longValue(),
								objCustService.getCardNumber());
				if (objCardStatusRemarksDto != null) {
					objForm.setRemarks(objCardStatusRemarksDto.getRemarks());
				}

				// this for setting the billing address
				for (Iterator it = objCustService.getApplicationAddress()
						.iterator(); it.hasNext();) {
					CustomerAddressDto addressDto = (CustomerAddressDto) it
							.next();
					if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("H")
							&& objCustService.getBillingTo().equals("H")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					} else if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("S")
							&& objCustService.getBillingTo().equals("S")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					} else {
						// addressDto.getAddressType()!=null &&
						// addressDto.getAddressType().equals("C") &&
						// objCustService.getBillingTo().equals("C")
						// this to show company Address as defalut
						// BilllingAddress
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					}
				}
			}// cardnumber check else close
		} catch (Exception e) {

			System.out
					.println("Error converting to form bean in CardRenewalDispatchAction: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardRenewalDispatchAction: "
							+ e);
		}

		objForm.getPreListData();

		saveToken(request);
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}

	// mehtods for CardReplacementForm

	// this method for saving the cardreplacement applicationformdata
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");
		String userId = (String) request.getSession(false).getAttribute(
				"USERID");

		// Form Validations
		CardRenewalForm objForm = (CardRenewalForm) form;

		CardsRenewalManager objManager = new CardsRenewalManager();
		CardManager objCardManager = new CardManager();

		// DTO Creation
		CardsRenewalDto objCardsRenewalDto = new CardsRenewalDto();
		boolean boolAdd = false;

		try {

			CardsDto objCardsDto = objCardManager.getCard(objForm
					.getCardNumber());

			objCardsRenewalDto.setCardNumber(Long.valueOf(objForm
					.getCardNumber()));
			objCardsRenewalDto.setIssuerId(issuerId);
			objCardsRenewalDto.setCustomerId(objCardsDto.getCustomerId());
			objCardsRenewalDto.setCardExpireDate(objCardsDto.getCardExpDate());
			objCardsRenewalDto.setUserId(userId);
			objCardsRenewalDto.setUpdatedDate(new Date());
			objCardsRenewalDto.setStatus(CommonCodes.APPLICATIONSTATUS_NEW);

			// Action Execution
			boolAdd = objManager.add(objCardsRenewalDto);

		} catch (Exception e) {
			System.out.println("Error CardRenewalDispatchAction save method : "
					+ e);
			throw new TPlusException(
					"Error CardRenewalDispatchAction save method : " + e);
		}

		if (!boolAdd) {
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

	// Mehtods For CardReplacementProcess
	/*
	 * this method for showing the card repalcement processlist
	 */
	public ActionForward processSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		String issuer = (String)request.getSession().getAttribute("ISSUER");

		int pageNo = 0;

		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("NEXT")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) + 1;
			}
		}
		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("PREV")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) - 1;
			}
		}

		CardsRenewalManager objCardsRenewalManager = new CardsRenewalManager();

		CardRenewalForm objForm = (CardRenewalForm) form;
		
		HttpSession session = request.getSession(true);
		String branchId = (String)session.getAttribute("LOGEDUSERBRANCH");
		objForm.setBranchId(branchId);
		
		CardsRenewalDto objDto = new CardsRenewalDto();
		try {
			objForm.setIssuerId(issuer);
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardReplacementDispatchAction ProcessSearch method: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction : "
							+ e);
		}

		Collection cardRepProcessList = objCardsRenewalManager.processSearch(
				objDto, pageNo);

		request.setAttribute("SEARCHLIST", cardRepProcessList);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		return mapping.findForward("success");
	}

	/*
	 * this method is for showing the particular record to accept or reject the
	 * cardreplacement form
	 */
	public ActionForward renew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardRenewalForm objForm = (CardRenewalForm) form;
		CardsRenewalDto objCardRepDto = new CardsRenewalDto();

		objForm.setCardNumber((String) request.getParameter("cardNumber"));
		objForm.setApplicationId((String) request.getParameter("serialNo"));

		try {
			BeanUtils.copyProperties(objCardRepDto, objForm);

			CardsRenewalManager objManager = new CardsRenewalManager();

			// this for calling search method in DAOImpl to get the
			// CustomerServiceDataBean
			Collection cardReplaceList = objManager.search(objCardRepDto);
			request.getSession(false).setAttribute("$CARDREPLACELIST$",
					cardReplaceList);
			CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList)
					.get(0);

			// this for setting the billing address
			for (Iterator it = objCustService.getApplicationAddress()
					.iterator(); it.hasNext();) {
				CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("H")
						&& objCustService.getBillingTo().equals("H")) {
					BeanUtils.copyProperties(objForm.getRequestAddress(),
							addressDto);
					break;
				} else if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("S")
						&& objCustService.getBillingTo().equals("S")) {
					BeanUtils.copyProperties(objForm.getRequestAddress(),
							addressDto);
					break;
				} else {
					BeanUtils.copyProperties(objForm.getRequestAddress(),
							addressDto);
				}
			}

			// this for setting the values to from hidden fileds
			objForm.setCardNumber(objCustService.getCardNumber());
			objForm.setCustomerId(objCustService.getCustomerId());
			objForm.setBranchId(objCustService.getBranchId());
			objForm.setCardProductId(objCustService.getCardProductId());
			objForm.setCardHolderType(objCustService.getCardHolderType());

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardRenewalDispatchAction: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardRenewalDispatchAction: "
							+ e);
		}

		// this for getting ReasonPreList
		objForm.getPreListData();

		saveToken(request);
		request.setAttribute("ACTION", "replace");
		return mapping.findForward("success");
	}

	/*
	 * this method is used for accepting the cardreplacement form
	 */

	public ActionForward accept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");
		String userId = (String) request.getSession(false).getAttribute(
				"USERID");

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		CardRenewalForm objForm = (CardRenewalForm) form;
		CardsRenewalDto objCardRepDto = new CardsRenewalDto();
		boolean boolAccept = false;

		CardsRenewalManager objCardsRenewalManager = new CardsRenewalManager();

		try {
			objCardRepDto.setCardRenewalSerial(objForm.getApplicationId());
			objCardRepDto.setCardNumber(Long.valueOf(objForm.getCardNumber()));
			objCardRepDto.setIssuerId(issuerId);
			objCardRepDto.setUserId(userId);

			boolAccept = objCardsRenewalManager.accept(objCardRepDto);
		} catch (Exception e) {
			System.out.println("Error in CardReRenewalDispatchAction accept: "
					+ e);
			throw new TPlusException(
					"Error in CardReRenewalDispatchAction accept: " + e);
		}

		if (!boolAccept) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.acceptfailed"));
			request.setAttribute("ACTION", "replace");
			saveErrors(request, errors);
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
	public ActionForward reject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");
		String userId = (String) request.getSession(false).getAttribute(
				"USERID");

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		CardRenewalForm objForm = (CardRenewalForm) form;
		CardsRenewalDto objCardRepDto = new CardsRenewalDto();
		boolean boolReject = false;

		CardsRenewalManager objCardsRenewalManager = new CardsRenewalManager();

		try {
			objCardRepDto.setCardRenewalSerial(objForm.getApplicationId());
			objCardRepDto.setCardNumber(Long.valueOf(objForm.getCardNumber()));
			objCardRepDto.setIssuerId(issuerId);
			objCardRepDto.setUserId(userId);

			boolReject = objCardsRenewalManager.reject(objCardRepDto);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: "
					+ e.getMessage());
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		if (!boolReject) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.rejectfailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "replace");
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
	 * this method is used for cardreceived confirmation
	 */
	public ActionForward cardreceived(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		CardReplacementForm objForm = (CardReplacementForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		// DTO Creation
		CardReplacementDto objCardRepDto = new CardReplacementDto();

		try {
			BeanUtils.copyProperties(objCardRepDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in cardreceived: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in cardreceived: " + e);
		}
		// Action Execution

		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolReceived = objManager.cardreceived(objCardRepDto);

		if (!boolReceived) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.receivedfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.receivedSuccess"));
			saveErrors(request, errors);
		}
		// Success
		// resetToken(request);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for cardstoping
	 */
	public ActionForward cardstop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// form validation
		CardReplacementForm objForm = (CardReplacementForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		// DTO Creation
		CardReplacementDto objCardRepDto = new CardReplacementDto();

		try {

			BeanUtils.copyProperties(objCardRepDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: "
					+ e.getMessage());
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolStop = objManager.cardstop(objCardRepDto);

		if (!boolStop) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.stopfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.stopSuccess"));
			saveErrors(request, errors);
		}
		// Success
		// resetToken(request);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for cardactivating
	 */
	public ActionForward cardactivate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		CardReplacementForm objForm = (CardReplacementForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}
		// DTO Creation
		CardReplacementDto objCardRepDto = new CardReplacementDto();

		try {
			BeanUtils.copyProperties(objCardRepDto, objForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: "
					+ e.getMessage());
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolActivate = objManager.cardstop(objCardRepDto);

		if (!boolActivate) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.activatefailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.activateSuccess"));
			saveErrors(request, errors);
		}
		// Success
		// resetToken(request);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for setting card pin count
	 */
	public ActionForward resetpincount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		CardReplacementForm objForm = (CardReplacementForm) form;

		// DTO Creation
		CardReplacementDto objCardRepDto = new CardReplacementDto();

		try {
			BeanUtils.copyProperties(objCardRepDto, objForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: "
					+ e.getMessage());
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolResetpin = objManager.resetpincount(objCardRepDto);

		if (!boolResetpin) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.resetpincountfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.resetpincountSuccess"));
			saveErrors(request, errors);

			// Action Execution
			Collection cardReplaceList = objManager.search(objCardRepDto);
			request.getSession(false).setAttribute("$CARDREPLACELIST$",
					cardReplaceList);
			CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList)
					.get(0);
			;

			// After the Search Result
			BeanUtils.copyProperties(objForm, objCustService);

			// this is setting remarks
			CardStatusRemarksDto objCardStatusRemarksDto = objManager
					.getCardStatusRemarks(Long.valueOf(
							objCustService.getCardStatusId()).longValue(),
							objCustService.getCardNumber());
			if (objCardStatusRemarksDto != null) {
				objForm.setRemarks(objCardStatusRemarksDto.getRemarks());
			}

			// this for setting the billing address
			for (Iterator it = objCustService.getApplicationAddress()
					.iterator(); it.hasNext();) {
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
					// addressDto.getAddressType()!=null &&
					// addressDto.getAddressType().equals("C") &&
					// objCustService.getBillingTo().equals("C")
					// this to show company Address as defalut
					// BilllingAddress
					BeanUtils.copyProperties(objForm.getRequestAddress(),
							addressDto);
				}
			}

		}
		// Success
		// resetToken(request);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for setting new Billing Address
	 */
	public ActionForward billingaddchange(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		CardReplacementForm objForm = (CardReplacementForm) form;
		// DTO Creation
		CardReplacementDto objCardRepDto = new CardReplacementDto();
		// this to set the customer to CustomeAdddressDto
		ApplicationProcessDto objcustomerDto = new ApplicationProcessDto();

		try {
			BeanUtils.copyProperties(objCardRepDto.getCustomerAddDto(), objForm
					.getRequestAddress());
			// this for setting the customer Id to
			// Customer(ApplicationProcessDto)
			objcustomerDto.setCustomerId(objForm.getCustomerId());
			// this for setting the Customer(ApplicationProcessDto) to
			// CustomerAdreessDto
			objCardRepDto.getCustomerAddDto().setAppFormProcssDto(
					objcustomerDto);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: "
					+ e.getMessage());
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolbilladd = objManager.billingaddchange(objCardRepDto);

		if (!boolbilladd) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.billingaddfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.billingaddSuccess"));
			saveErrors(request, errors);
		}
		// Success
		// resetToken(request);
		return mapping.findForward("success");
	}

	// Mehtods for CardCloseForm
	/*
	 * this method for saving the cardclose applicationformdata
	 */
	public ActionForward cardCloseAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		CardReplacementForm objForm = (CardReplacementForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {

			saveErrors(request, errors);
			request.setAttribute("ACTION", "search");
			return mapping.findForward("success");
		}

		// DTO Creation
		CardCloseDto objCardCloseDto = new CardCloseDto();
		CustomerAddressDto objAddressDto = new CustomerAddressDto();

		try {

			// this to copy the form data to CardClosedto
			BeanUtils.copyProperties(objCardCloseDto, objForm);

			// this copying AddressForm object from CardReplacementForm to
			// CustomerAddressDto
			BeanUtils
					.copyProperties(objAddressDto, objForm.getRequestAddress());

			// this to setting the CustomerAddressDto to CardCloseDto
			objCardCloseDto.setCustomerAddDto(objAddressDto);

			// settiing the application details from constants
			objCardCloseDto.setApplicationId(IdsGenartion
					.GenerateApplicationId());
			objCardCloseDto
					.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_NEW);
			objCardCloseDto
					.setApplicationType(CommonCodes.APPLICATIONTYPE_CARDCLOSE);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean CardReplacementDispatchAction cardCloseAdd method : "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction cardCloseAdd method: "
							+ e);
		}
		// Action Execution

		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolClose = objManager.cardCloseAdd(objCardCloseDto);

		if (!boolClose) {
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

		// resetToken(request);
		return mapping.findForward("success");
	}

	// Mehtods for CardCloseForm Process

	/*
	 * this method for showing the cardClose processlist
	 */
	public ActionForward cardCloseProcessSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		int pageNo = 0;

		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("NEXT")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) + 1;
			}
		}
		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("PREV")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) - 1;
			}
		}

		CardReplacementForm objForm = (CardReplacementForm) form;
		CardCloseDto objDto = new CardCloseDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardReplacementDispatchAction cardCloseProcessSearch method: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction cardCloseProcessSearch : "
							+ e);
		}

		CardReplacementManager objManager = new CardReplacementManager();
		Collection cardCloseProcessList = objManager.cardCloseProcessSearch(
				objDto, pageNo);

		request.setAttribute("SEARCHLIST", cardCloseProcessList);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		return mapping.findForward("success");
	}

	/*
	 * this method is for showing the particular record to accept or reject the
	 * cardClose form
	 */
	public ActionForward cardClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardReplacementForm objForm = (CardReplacementForm) form;
		// this dto for search condition
		CardReplacementDto objCardRepDto = new CardReplacementDto();
		// this is for loading data from cardclose_forms table
		// / CardCloseDto objCardCloseDto = new CardCloseDto();

		objForm.setCardNumber((String) request.getParameter("cardNumber"));

		try {
			BeanUtils.copyProperties(objCardRepDto, objForm);

			CardReplacementManager objManager = new CardReplacementManager();
			// this for calling search method in DAOImpl to get the
			// CustomerServiceDataBean
			Collection cardReplaceList = objManager.search(objCardRepDto);
			request.getSession(false).setAttribute("$CARDREPLACELIST$",
					cardReplaceList);
			CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList)
					.get(0);
			;

			// get the CardCloseDto
			CardCloseDto objCardCloseDto = objManager
					.getCardCloseDto((String) request
							.getParameter("applicationId"));
			// copying address from this CardCloseDto to CardReplacementForm
			// Requestaddress object
			BeanUtils.copyProperties(objForm.getRequestAddress(),
					objCardCloseDto.getCustomerAddDto());
			// setting the values from CardCloseDto to CardReplacementForm
			objForm.setRemarks(objCardCloseDto.getRemarks());
			// this value to pass reject method to update cardcloseForms table
			// when clicks rejection
			objForm.setApplicationId(objCardCloseDto.getApplicationId());
			// this for setting the values to from hidden fileds
			objForm.setCardNumber(objCustService.getCardNumber());

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardReplacementDispatchAction cardClose: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardReplacementProcessDispatchAction cardClose:  "
							+ e);
		}

		// this for getting ReasonPreList
		objForm.getPreListData();

		saveToken(request);
		request.setAttribute("ACTION", "close");
		return mapping.findForward("success");
	}

	/*
	 * this method is used for accepting the cardreplacement form
	 */

	public ActionForward cardCloseAccept(ActionMapping mapping,
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

		CardReplacementForm objForm = (CardReplacementForm) form;
		// DTO Creation
		CardCloseDto objCardCloseDto = new CardCloseDto();

		try {
			BeanUtils.copyProperties(objCardCloseDto, objForm);

			// this to checking the cardtype if primarycard we have to close all
			// the suppcards for this card and suppcard only that card has to be
			// close
			if (request.getSession(false).getAttribute("$CARDREPLACELIST$") != null) {
				Collection dataList = (Collection) request.getSession(false)
						.getAttribute("$CARDREPLACELIST$");
				CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) dataList)
						.get(0);
				Set cards = objCustService.getCustomerCards();
				String cardsToStop[] = new String[cards.size()];
				int i = 0;
				for (Iterator it = cards.iterator(); it.hasNext();) {
					CardsDto objCardsDto = new CardsDto();
					objCardsDto = (CardsDto) it.next();
					// checking the card holder type if primcardholder close all
					// his supply cards
					if (objCustService.getCardHolderType() != null
							&& objCustService
									.getCardHolderType()
									.equals(
											String
													.valueOf(CommonCodes.PRIMARYCARD_HOLDER))) {
						cardsToStop[i] = String.valueOf(objCardsDto
								.getCardNumber());
						i++;
					}
				}
				// setting the cardstostop to the CardCloseDto
				objCardCloseDto.setCardsToStop(cardsToStop);
			}

		}

		catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolAccept = objManager.cardCloseAccept(objCardCloseDto);
		if (!boolAccept) {
			System.out
					.println("CardReplacementFormDispatchAction: cardCloseAccept record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.acceptfailed"));
			request.setAttribute("ACTION", "close");
			saveErrors(request, errors);
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
	public ActionForward cardCloseReject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		CardReplacementForm objForm = (CardReplacementForm) form;

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// DTO Creation
		CardCloseDto objCardCloseDto = new CardCloseDto();
		try {

			// objCardCloseDto.setApplicationId((String)request.getParameter("applicationId"));
			BeanUtils.copyProperties(objCardCloseDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: "
					+ e.getMessage());
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolReject = objManager.cardCloseReject(objCardCloseDto);

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

	// Mehtods for PinResendForm
	/*
	 * this method for saving the PinResendForm data
	 */
	public ActionForward pinResendAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		// Form Validations
		CardReplacementForm objForm = (CardReplacementForm) form;
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
					.setApplicationType(CommonCodes.APPLICATIONTYPE_CARDCLOSE);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean CardReplacementDispatchAction pinResendAddAdd method : "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction pinResendAddAdd method: "
							+ e);
		}

		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
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

		int pageNo = 0;

		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("NEXT")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) + 1;
			}
		}
		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("PREV")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) - 1;
			}
		}

		CardReplacementForm objForm = (CardReplacementForm) form;
		PinResendDto objDto = new PinResendDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardReplacementDispatchAction pinResendProcessSearch method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction pinResendProcessSearch : "
							+ e);
		}

		CardReplacementManager objManager = new CardReplacementManager();
		Collection cardCloseProcessList = objManager.pinResendProcessSearch(
				objDto, pageNo);

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

		CardReplacementForm objForm = (CardReplacementForm) form;
		// this dto for search condition
		CardReplacementDto objCardRepDto = new CardReplacementDto();
		objForm.setCardNumber((String) request.getParameter("cardNumber"));

		try {
			BeanUtils.copyProperties(objCardRepDto, objForm);

			CardReplacementManager objManager = new CardReplacementManager();

			// this for calling search method in DAOImpl to get the
			// CustomerServiceDataBean
			Collection cardReplaceList = objManager.search(objCardRepDto);
			request.getSession(false).setAttribute("$CARDREPLACELIST$",
					cardReplaceList);
			CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList)
					.get(0);
			;

			// this is for loading data from pinResend_forms table
			PinResendDto objPinResendDto = objManager
					.getPinResendDto((String) request
							.getParameter("applicationId"));

			// copying address from this PinResendDto to CardReplacementForm
			// Requestaddress object
			BeanUtils.copyProperties(objForm.getRequestAddress(),
					objPinResendDto.getCustomerAddDto());

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

		CardReplacementForm objForm = (CardReplacementForm) form;
		// DTO Creation
		PinResendDto objPinResendDto = new PinResendDto();

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
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolAccept = objManager.pinResendAccept(objPinResendDto);
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

		CardReplacementForm objForm = (CardReplacementForm) form;

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
		CardReplacementManager objManager = new CardReplacementManager();
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

	// Mehtods for CardLimitAdjusmentForm
	/*
	 * this method for saving the CardLimitAdjusment applicationformdata
	 */
	public ActionForward cardLimitAdd(ActionMapping mapping, ActionForm form,
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
		CardReplacementForm objForm = (CardReplacementForm) form;

		// DTO Creation
		CardLimitAdjustmentDto objCardLimitDto = new CardLimitAdjustmentDto();
		CustomerAddressDto objAddressDto = new CustomerAddressDto();

		try {

			// this to copy the form data to CardLimitAdjustmentDto
			BeanUtils.copyProperties(objCardLimitDto, objForm);

			// this copying AddressForm object from CardReplacementForm to
			// CustomerAddressDto
			BeanUtils
					.copyProperties(objAddressDto, objForm.getRequestAddress());

			// this to setting the CustomerAddressDto to CardLimitDto
			objCardLimitDto.setCustomerAddDto(objAddressDto);

			// settiing the application details from constants
			objCardLimitDto.setApplicationId(IdsGenartion
					.GenerateApplicationId());
			objCardLimitDto
					.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_NEW);
			objCardLimitDto
					.setApplicationType(CommonCodes.APPLICATIONTYPE_CARDLIMITADJUST);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean CardReplacementDispatchAction cardLimitAdd method : "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction cardLimitAdd method: "
							+ e);
		}
		// Action Execution

		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolLimit = objManager.cardLimitAdd(objCardLimitDto);

		if (!boolLimit) {
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
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}

	// Mehtods for cardLimitAdjusmentForm Process

	/*
	 * this method for showing the cardLimitAdjusment Cards list
	 */

	public ActionForward cardLimitProcessSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		int pageNo = 0;

		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("NEXT")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) + 1;
			}
		}
		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("PREV")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) - 1;
			}
		}

		CardReplacementForm objForm = (CardReplacementForm) form;
		CardLimitAdjustmentDto objDto = new CardLimitAdjustmentDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardReplacementDispatchAction cardLimitProcessSearch method: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction cardLimitProcessSearch : "
							+ e);
		}

		CardReplacementManager objManager = new CardReplacementManager();
		Collection cardCloseProcessList = objManager.cardLimitProcessSearch(
				objDto, pageNo);

		request.setAttribute("SEARCHLIST", cardCloseProcessList);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		return mapping.findForward("success");
	}

	/*
	 * this method is for showing the particular record to accept or reject the
	 * cardLimitAdjusment form
	 */
	public ActionForward cardLimitAdjust(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		CardReplacementForm objForm = (CardReplacementForm) form;
		// this dto for search condition
		CardReplacementDto objCardRepDto = new CardReplacementDto();

		objForm.setCardNumber((String) request.getParameter("cardNumber"));

		try {
			BeanUtils.copyProperties(objCardRepDto, objForm);

			CardReplacementManager objManager = new CardReplacementManager();

			// this for calling search method in DAOImpl to get the
			// CustomerServiceDataBean
			Collection cardReplaceList = objManager.search(objCardRepDto);
			request.getSession(false).setAttribute("$CARDREPLACELIST$",
					cardReplaceList);
			CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList)
					.get(0);
			;

			// this is for loading data from CardLimitAdjsut_forms
			CardLimitAdjustmentDto objLimitDto = objManager
					.getCardLimitAdjustmentDto((String) request
							.getParameter("applicationId"));

			// copying address from this CardLimitAdjsutmentDto to
			// CardReplacementForm Requestaddress object
			BeanUtils.copyProperties(objForm.getRequestAddress(), objLimitDto
					.getCustomerAddDto());

			// setting the values from CardLimitAdjsutmentDto to
			// CardReplacementForm
			objForm.setRemarks(objLimitDto.getRemarks());
			// this value to pass reject method to update CardLimitAdjsut_Forms
			// table when clicks rejection
			objForm.setApplicationId(objLimitDto.getApplicationId());
			// this for setting the values to from hidden fileds
			objForm.setCardNumber(objCustService.getCardNumber());

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardReplacementDispatchAction CardLimitAdjsut method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardReplacementProcessDispatchAction CardLimitAdjsut method:  "
							+ e);
		}

		// this for getting ReasonPreList
		objForm.getPreListData();

		saveToken(request);
		request.setAttribute("ACTION", "adjust");
		return mapping.findForward("success");
	}

	/*
	 * this method is used for accepting the cardreplacement form
	 */

	public ActionForward cardLimitAccept(ActionMapping mapping,
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

		CardReplacementForm objForm = (CardReplacementForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "adjust");
			;
			return mapping.findForward("success");
		}
		// DTO Creation
		CardLimitAdjustmentDto objLimitDto = new CardLimitAdjustmentDto();

		String userID = (String) request.getSession(false).getAttribute("USERID");

		try {
			BeanUtils.copyProperties(objLimitDto, objForm);
			// to set the Customer Account to update the Customer_Account
			// Details
			CustomerAccountDto objCustAccount = null;
			Set objAccount = ((CustomerServiceDataBean) ((ArrayList) request
					.getSession(false).getAttribute("$CARDREPLACELIST$"))
					.get(0)).getCustomerAccount();
			for (Iterator it = objAccount.iterator(); it.hasNext();) {
				objCustAccount = (CustomerAccountDto) it.next();
			}

			// setting the form values to account object to update
			objCustAccount.setCreditLimit(Float.parseFloat(objForm
					.getCreditLimit()));
			objCustAccount.setCashLimit(Float
					.parseFloat(objForm.getCashLimit()));

			// set the account object to CardLimitAdjustmentDto to get in
			// Daoimple class
			objLimitDto.setCustomerAccountDto(objCustAccount);

		}

		catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolAccept = objManager.cardLimitAccept(objLimitDto, userID);
		if (!boolAccept) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.acceptfailed"));
			request.setAttribute("ACTION", "adjust");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.acceptSuccess"));
			request.setAttribute("ACTION", "cancel");
			saveErrors(request, errors);
		}
		// Success
		resetToken(request);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for rejecting the cardreplacement form
	 */
	public ActionForward cardLimitReject(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		CardReplacementForm objForm = (CardReplacementForm) form;

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// DTO Creation
		CardLimitAdjustmentDto objLimitDto = new CardLimitAdjustmentDto();
		try {

			// objLimitDto.setApplicationId((String)request.getParameter("applicationId"));
			BeanUtils.copyProperties(objLimitDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: "
					+ e.getMessage());
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolReject = objManager.cardLimitReject(objLimitDto);

		if (!boolReject) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.rejectfailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "adjust");
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
	 * this method is used for Credit Limit Splitting Data Dispaly
	 */
	public ActionForward creditSplitData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		ActionErrors errors = null;
		CreditSplitForm objSplitForm = (CreditSplitForm) form;
		HttpSession session = request.getSession(false);
		CustomerServiceDataBean objCustService = null;
		CustomerAccountDto objCustAcc = null;
		int limitIndex = 0;
		LimitForm objLimitform = null;
		try {

			if (session.getAttribute("$CARDREPLACELIST$") != null) {
				Collection dataList = (Collection) session
						.getAttribute("$CARDREPLACELIST$");
				objCustService = (CustomerServiceDataBean) ((ArrayList) dataList)
						.get(0);
				if (!objCustService.getCardHolderType().equals(
						String.valueOf(CommonCodes.PRIMARYCARD_HOLDER))) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.cardchecking"));
					saveErrors(request, errors);
					return mapping.findForward("noprimrycard");
				}

				// this is main creditlimit and cashlimit from account table for
				// this customer(to set this to hidden fields to calculations)
				Set objAccount = objCustService.getCustomerAccount();
				for (Iterator it = objAccount.iterator(); it.hasNext();) {
					objCustAcc = (CustomerAccountDto) it.next();
					objSplitForm.setCreditLimit(String.valueOf(objCustAcc
							.getCreditLimit()));
					objSplitForm.setCashLimit(String.valueOf(objCustAcc
							.getCashLimit()));

				}
				// getting supply cards for this primary card and setting values
				Set cards = objCustService.getCustomerCards();
				for (Iterator it = cards.iterator(); it.hasNext();) {

					CardsDto objCardsDto = new CardsDto();
					objCardsDto = (CardsDto) it.next();

					if (limitIndex == 0)
						objLimitform = objSplitForm.getLimitForm0();
					else if (limitIndex == 1)
						objLimitform = objSplitForm.getLimitForm1();
					else if (limitIndex == 2)
						objLimitform = objSplitForm.getLimitForm2();
					else if (limitIndex == 3)
						objLimitform = objSplitForm.getLimitForm3();
					else if (limitIndex == 4)
						objLimitform = objSplitForm.getLimitForm4();

					objLimitform.setCardNumber(String.valueOf(objCardsDto
							.getCardNumber()));

					if (objCardsDto.getCardHolderType() == CommonCodes.PRIMARYCARD_HOLDER) {
						objLimitform.setCardHolderType("PRIMARY");
						// calculating currentCashLimit value using
						// customerCashLimit and currentCashLimit%(for
						// primarycard only allow to change cashlimit)
						double currentCashLimit = ((objCustAcc.getCashLimit()) * (objCardsDto
								.getCashLimitPercent())) / 100;
						objLimitform.setCurrentCashLimit(String
								.valueOf(currentCashLimit));
						objLimitform.setCurrentCashLimitRatio(String
								.valueOf(objCardsDto.getCashLimitPercent()));
					} else {
						objLimitform.setCardHolderType("SUPPLEMENTARY");
					}
					objLimitform.setCardStatus(objCardsDto.getCardStatus());
					objLimitform.setCustomerName(objCustService
							.getCustomerName());
					// calculating currentCreditLimit value using
					// customerCrediLimit and currentCrediLimit%
					double currentLimit = ((objCustAcc.getCreditLimit()) * (objCardsDto
							.getCreditLimitPercent())) / 100;
					objLimitform.setCurrentLimit(String.valueOf(currentLimit));
					objLimitform.setCurrentRatio(String.valueOf(objCardsDto
							.getCreditLimitPercent()));

					limitIndex++;

				}
				// setting the cards size
				objSplitForm.setCardsSize(String.valueOf(cards.size()));

			}
		} catch (Exception e) {
			System.out
					.println("Error in CardReplacementDispatchAction mehtod creditSplitData: "
							+ e.getMessage());
			throw new TPlusException(
					"Error in CardReplacementDispatchAction mehtod creditSplitData: "
							+ e);
		}
		// to display the cacel button for Creditsplit
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	/*
	 * this method is used for CreditLimitSplitting updation
	 */
	public ActionForward creditSplitUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		boolean boolSplit = false;
		ActionErrors errors = null;
		CreditSplitForm objForm = (CreditSplitForm) form;
		// Dto Creation
		CreditSplitDto objSplit = new CreditSplitDto(objForm);
		try {
			// Action Execution
			BeanUtils.copyProperties(objSplit, objForm);
			CardReplacementManager objManager = new CardReplacementManager();
			boolSplit = objManager.creditSplitUpdate(objSplit);

			// removing all from limitFormsList Set
			objSplit.getLimitFormsList().removeAll(objForm.getLimitFormsList());

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean CardReplacementDispatchAction creditSplitUpdate method : "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction creditSplitUpdate method: "
							+ e);
		}

		if (!boolSplit) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.creditsplitfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.creditsplitSuccess"));
			saveErrors(request, errors);
		}
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
		CardReplacementManager objManager = new CardReplacementManager();
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
		CardReplacementManager objManager = new CardReplacementManager();
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
				// System.out.println("Customer Object"
				// +custObj+"  "+session.getAttribute(custObj).getClass().getName());
				if (custObj.startsWith("$") && custObj.endsWith("$"))
				// if(session.getAttribute(custObj) instanceof
				// CustomerServiceDataBean)
				{
					session.removeAttribute(custObj);

				}

			}

		}
	}

	public ActionForward searchRenewal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		CardRenewalForm objForm = (CardRenewalForm) form;

		// to check cardnumber Entered or not
		if (objForm.getCardNumber() != null
				&& objForm.getCardNumber().equals("")) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnorequired"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		} else {
			String strCardNo = objForm.getCardNumber();
			try {
				@SuppressWarnings("unused")
				long longCardNo = Long.valueOf(strCardNo);
			} catch (Exception e) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnoinvalid"));
				saveErrors(request, errors);
				return mapping.findForward("success");
			}
		}

		boolean isError = false;
		String errormsg = "";

		// Dto creation
		CardsRenewalDto objCardsRenewalDto = new CardsRenewalDto();
		CardsRenewalDto objCardsRenewalDtoForSearch = new CardsRenewalDto();

		try {
			BeanUtils.copyProperties(objCardsRenewalDtoForSearch, objForm);

			CardManager objCardManager = new CardManager();
			CardsRenewalManager objCardsRenewalManager = new CardsRenewalManager();
			SystemParamManager objSystemParamManager = new SystemParamManager();

			CardsDto objCardsDto = objCardManager.getCard(objForm
					.getCardNumber());

			if (objCardsDto == null) {
				isError = true;
				errormsg = "error.cardnumbernotexit";
			} else {
				objCardsRenewalDto = objCardsRenewalManager
						.getOpenRenewalSubmission(objForm.getCardNumber());
				if (objCardsRenewalDto == null) {
					boolean isEmbossed = objCardManager.isCardEmbossed(objForm
							.getCardNumber());
					if (!isEmbossed) {
						isError = true;
						errormsg = "error.cardnotembossed";
					}else{
						Date now = new Date();
						String strExpDate = objCardsDto.getCardExpDate();
	
						SimpleDateFormat sdf = new SimpleDateFormat("MMyy");
						Date eDate = sdf.parse(strExpDate);
	
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(eDate);
						calendar.set(calendar.get(1), calendar.get(2), calendar
								.get(5));
	
						int maxDay = calendar
								.getActualMaximum(Calendar.DAY_OF_MONTH);
	
						calendar.set(calendar.get(1), calendar.get(2), maxDay);
						Date dateExpDate = calendar.getTime();
	
						if (now.getTime() < dateExpDate.getTime()) {

							SystemParamDto objSystemParamDto = objSystemParamManager
									.get("ASP");
							int renewalTimeInterval = objSystemParamDto
									.getRenewalTimeInterval();
	
							Date renewTimePeriod = DateUtil.addMonths(dateExpDate,
									-renewalTimeInterval);
							if (now.getTime() < renewTimePeriod.getTime()) {
								isError = true;
								errormsg = "errors.cardcannotrenew";
							}
						}
					}
				} else {

					int status = objCardsRenewalDto.getStatus();

					switch (status) {
					case 0:
						isError = true;
						errormsg = "error.cardchnagerequested";
						break;
					case 1:
						isError = true;
						errormsg = "error.cardchangerequestaccepted";
						break;
					case 2:
						isError = true;
						errormsg = "error.cardchangerequestrejected";
						break;
					default:
						break;
					}
				}
			}

			if (isError) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError(errormsg));
				saveErrors(request, errors);
				return mapping.findForward("success");
			} else {

				// Action Execution
				Collection cardReplaceList = objCardsRenewalManager
						.search(objCardsRenewalDtoForSearch);
				request.getSession(false).setAttribute("$CARDREPLACELIST$",
						cardReplaceList);
				CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList)
						.get(0);

				// After the Search Result
				BeanUtils.copyProperties(objForm, objCustService);

				// this is setting remarks
				CardStatusRemarksDto objCardStatusRemarksDto = objCardManager
						.getCardStatusRemarks(Long.valueOf(
								objCustService.getCardStatusId()).longValue(),
								objCustService.getCardNumber());
				if (objCardStatusRemarksDto != null) {
					objForm.setRemarks(objCardStatusRemarksDto.getRemarks());
				}

				// this for setting the billing address
				for (Iterator it = objCustService.getApplicationAddress()
						.iterator(); it.hasNext();) {
					CustomerAddressDto addressDto = (CustomerAddressDto) it
							.next();
					if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("H")
							&& objCustService.getBillingTo().equals("H")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					} else if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("S")
							&& objCustService.getBillingTo().equals("S")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					} else {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
					}
				}
			}
		} catch (Exception e) {

			System.out
					.println("Error converting to form bean in CardReplacementDispatchAction: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardReplacementDispatchAction: "
							+ e);
		}

		objForm.getPreListData();

		saveToken(request);
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}

}
