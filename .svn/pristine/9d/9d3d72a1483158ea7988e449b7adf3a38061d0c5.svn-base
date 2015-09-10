package org.transinfo.cacis.action.customerservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
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
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.controller.accounting.CardHolderStatementManager;
import org.transinfo.cacis.controller.authorization.SystemParamManager;
import org.transinfo.cacis.controller.cardproduction.ApplicationFormManager;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.controller.customerservice.AccountAdjustmentManager;
import org.transinfo.cacis.controller.customerservice.CardReplacementManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
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
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.formbean.customerservice.CardReplacementForm;
import org.transinfo.cacis.formbean.customerservice.CreditSplitForm;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;
import org.transinfo.cacis.formbean.customerservice.LimitForm;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings( { "unchecked", "deprecation" })
public class CardReplacementDispatchAction extends BaseDispatchAction {

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

		CardReplacementForm objForm = (CardReplacementForm) form;
		CardReplacementForm newObjForm = new CardReplacementForm();

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

		try {
			@SuppressWarnings("unused")
			long cardNo = Long.valueOf(objForm.getCardNumber());
		} catch (Exception e) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnoinvalid"));
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
			cardDto.setEncryptedCardNo(CardEncryption.encrypt(String.valueOf(objDto.getCardNumber())));
			
			objDto.setEncryptedCardNo(CardEncryption.encrypt(String.valueOf(objDto.getCardNumber())));
			
			boolean cardNoCheck = objManager.validate(cardDto, 0);
			if (cardNoCheck) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				return mapping.findForward("success");
			} else {
				// Action Execution
				Collection cardReplaceList = objManager.search(objDto);
				request.getSession(false).setAttribute("$CARDREPLACELIST$", cardReplaceList);
				CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList) .get(0);

				// After the Search Result
				BeanUtils.copyProperties(objForm, objCustService);

				// make reason and remarks blank since we have new link to list those
				objForm.setReasonCode("");
				objForm.setRemarks("");

				// this is setting remarks
				/*CardStatusRemarksDto objCardStatusRemarksDto = objManager
						.getCardStatusRemarks(Long.valueOf(
								objCustService.getCardStatusId()).longValue(),
								objCustService.getCardNumber());
				if (objCardStatusRemarksDto != null) {
					objForm.setRemarks(objCardStatusRemarksDto.getRemarks());
				}*/

				// this for setting the billing address
				for (Iterator it = objCustService.getApplicationAddress() .iterator(); it.hasNext();) {
					CustomerAddressDto addressDto = (CustomerAddressDto) it.next();

					// Action Execution
					ApplicationFormManager objApplicationFormManager = new ApplicationFormManager();

					String hcountry = addressDto.getCountry();
					if("MM".equals(hcountry)){
						objForm.setStateList(objApplicationFormManager.getStateList());
						objForm.setCityList(objApplicationFormManager.getCityList(addressDto.getState()));
						objForm.setTownshipList(objApplicationFormManager.getTownshipList(addressDto.getCity()));
					}

					if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("H") && objCustService.getBillingTo().equals("H")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
						break;
					} else if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("S") && objCustService.getBillingTo().equals("S")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
						break;
					} else {
						// addressDto.getAddressType()!=null &&
						// addressDto.getAddressType().equals("C") &&
						// objCustService.getBillingTo().equals("C")
						// this to show company Address as defalut
						// BilllingAddress
						if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("C") && objCustService.getBillingTo().equals("C")) {
							BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
							break;
						}
					}
				}

				List feeTranx = new ArrayList();
				String totFeeAmt = "0";

				String limitUsed = "0";
				String totBal = "0";

				// get fee details to credit cards
				if(!"470532".equals(objForm.getCardNumber().substring(0, 6))){

					CardHolderStatementManager objManagerCardHolderStatementManager = new CardHolderStatementManager();
					feeTranx = objManagerCardHolderStatementManager.getFeeTranx(objForm.getCardNumber());

					double totFeeDouble = objManagerCardHolderStatementManager.getTotFeeAmt(objForm.getCardNumber());

					BigDecimal bigTotFee = BigDecimal.valueOf(totFeeDouble);
					bigTotFee = bigTotFee.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					totFeeAmt = bigTotFee.toString();

					String accountId = objCustService.getAccountId();

					AccountAdjustmentManager objAccountAdjustmentManager = new AccountAdjustmentManager();
					CustomerAccountDto objCustomerAccountDto = objAccountAdjustmentManager.getCustomerAccountDto(accountId);

					float limitUsedFloat = objCustomerAccountDto.getLimitUsed();

					BigDecimal biglimitUsed = BigDecimal.valueOf(limitUsedFloat);
					biglimitUsed = biglimitUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					limitUsed = biglimitUsed.toString();

					totBal = biglimitUsed.add(bigTotFee).toString();

				}

				objForm.setFeeTranx(feeTranx);
				objForm.setTotFeeAmt(totFeeAmt);

				objForm.setLimitUsed(limitUsed);
				objForm.setTotBal(totBal);

			}// cardnumber check else close
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

	public ActionForward searchECom(ActionMapping mapping, ActionForm form,
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

		try {
			@SuppressWarnings("unused")
			long cardNo = Long.valueOf(objForm.getCardNumber());
		} catch (Exception e) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnoinvalid"));
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
				request.getSession(false).setAttribute("$CARDREPLACELIST$", cardReplaceList);
				CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList).get(0);

				// After the Search Result
				BeanUtils.copyProperties(objForm, objCustService);

				// make reason and remarks blank since we have new link to list those
				objForm.setReasonCode("");
				objForm.setRemarks("");

				// this is setting remarks
				/*CardStatusRemarksDto objCardStatusRemarksDto = objManager
						.getCardStatusRemarks(Long.valueOf(
								objCustService.getCardStatusId()).longValue(),
								objCustService.getCardNumber());
				if (objCardStatusRemarksDto != null) {
					objForm.setRemarks(objCardStatusRemarksDto.getRemarks());
				}*/

				CardManager objCardManager = new CardManager();
				CardsDto objCardsDto = objCardManager.getCard(String.valueOf(objDto.getCardNumber()));
				objForm.seteComEnable(objCardsDto.geteComEnable());

				// this for setting the billing address
				for (Iterator it = objCustService.getApplicationAddress()
						.iterator(); it.hasNext();) {
					CustomerAddressDto addressDto = (CustomerAddressDto) it
							.next();


					// Action Execution
					ApplicationFormManager objApplicationFormManager = new ApplicationFormManager();

					String hcountry = addressDto.getCountry();
					if("MM".equals(hcountry)){
						objForm.setStateList(objApplicationFormManager.getStateList());
						objForm.setCityList(objApplicationFormManager.getCityList(addressDto.getState()));
						objForm.setTownshipList(objApplicationFormManager.getTownshipList(addressDto.getCity()));
					}

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


	public ActionForward searchLimitAdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		ActionErrors errors = null;

		CardReplacementForm objForm = (CardReplacementForm) form;

		// to check cardnumber Entered or not
		if (objForm.getCardNumber() != null && objForm.getCardNumber().equals("")) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnorequired"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		try {
			@SuppressWarnings("unused")
			long cardNo = Long.valueOf(objForm.getCardNumber());
		} catch (Exception e) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnoinvalid"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		// Dto creation
		CardReplacementDto objDto = new CardReplacementDto();

		try {

			BeanUtils.copyProperties(objDto, objForm);

			CardReplacementManager objManager = new CardReplacementManager();
			CardManager objCardManager = new CardManager();

			CardsDto cardDto = objCardManager.getCard(String.valueOf(objDto.getCardNumber()));

			if(cardDto == null){
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				return mapping.findForward("success");
			}else{

				String cardType = objCardManager.getCardType(String.valueOf(objDto.getCardNumber()));

				if(!"".equals(cardType)){
					
					if(!ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.cardlimitadjcreditcard"));
						saveErrors(request, errors);
						return mapping.findForward("success");
					}
					
					int cardHolderType = cardDto.getCardHolderType();
					
					if(cardHolderType != 1){
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.cardlimitadjcreditcardpri"));
						saveErrors(request, errors);
						return mapping.findForward("success");
					}

					// Action Execution
					Collection cardReplaceList = objManager.search(objDto);
					request.getSession(false).setAttribute("$CARDREPLACELIST$", cardReplaceList);
					CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList).get(0);

					// After the Search Result
					BeanUtils.copyProperties(objForm, objCustService);

					// make reason and remarks blank since we have new link to list those
					objForm.setReasonCode("");
					objForm.setRemarks("");
					
					CardsDto objCardsDto = objCardManager.getCard(String.valueOf(objDto.getCardNumber()));
					objForm.setCreditLimit(String.valueOf(objCardsDto.getCustAccountDto().getCreditLimit()));
					objForm.setAccountId(objCardsDto.getCustAccountDto().getAccountId());

					// this for setting the billing address
					for (Iterator it = objCustService.getApplicationAddress().iterator(); it.hasNext();) {
						CustomerAddressDto addressDto = (CustomerAddressDto) it.next();

						// Action Execution
						ApplicationFormManager objApplicationFormManager = new ApplicationFormManager();

						String hcountry = addressDto.getCountry();
						if("MM".equals(hcountry)){
							objForm.setStateList(objApplicationFormManager.getStateList());
							objForm.setCityList(objApplicationFormManager.getCityList(addressDto.getState()));
							objForm.setTownshipList(objApplicationFormManager.getTownshipList(addressDto.getCity()));
						}

						if (addressDto.getAddressType() != null
								&& addressDto.getAddressType().equals("H")
								&& objCustService.getBillingTo().equals("H")) {
							BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
							break;
						} else if (addressDto.getAddressType() != null
								&& addressDto.getAddressType().equals("S")
								&& objCustService.getBillingTo().equals("S")) {
							BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
							break;
						} else {
							// BilllingAddress
							if (addressDto.getAddressType() != null
									&& addressDto.getAddressType().equals("C")
									&& objCustService.getBillingTo().equals("C")) {
								BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
								break;
							}
						}
					}

				}else{
					
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.cardnoinvalid"));
					saveErrors(request, errors);
					return mapping.findForward("success");
					
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

	// mehtods for CardReplacementForm

	// this method for saving the cardreplacement applicationformdata
	public ActionForward add(ActionMapping mapping, ActionForm form,
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
		CardReplacementDto objCardRepDto = new CardReplacementDto();
		CustomerAddressDto objAddressDto = new CustomerAddressDto();

		CardManager objCardManager = new CardManager();

		try {
			// this to copy the form data to dto
			BeanUtils.copyProperties(objCardRepDto, objForm);
			// this copying AddressForm object from CardReplacementForm to
			// CustomerAddressDto
			BeanUtils
			.copyProperties(objAddressDto, objForm.getRequestAddress());
			// this to setting the CustomerAddressDto to CardRepalcementDto
			objCardRepDto.setCustomerAddDto(objAddressDto);
			// seetiing the application details from constants
			objCardRepDto
			.setApplicationId(IdsGenartion.GenerateApplicationId());
			objCardRepDto
			.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_NEW);
			objCardRepDto
			.setApplicationType(CommonCodes.APPLICATIONTYPE_REPLACEMENT);

			CardsDto objCardsDto = objCardManager.getCard(objForm.getCardNumber());
			objCardRepDto.setCustomerId(objCardsDto.getCustomerId());

		} catch (Exception e) {
			System.out
			.println("Error converting to form bean CardReplacementDispatchAction save method : "
					+ e);
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction save method: "
							+ e);
		}
		// Action Execution

		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolAdd = objManager.add(objCardRepDto);

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

		CardReplacementForm objForm = (CardReplacementForm) form;

		HttpSession session = request.getSession(true);
		String branchId = (String)session.getAttribute("LOGEDUSERBRANCH");
		objForm.setBranchId(branchId);

		CardReplacementDto objDto = new CardReplacementDto();
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
		
		if(objForm.getCardNumber() != null && !"".equals(objForm.getCardNumber())){
			objDto.setEncryptedCardNo(CardEncryption.encrypt(objForm.getCardNumber()));
		}

		CardReplacementManager objManager = new CardReplacementManager();
		Collection cardRepProcessList = objManager.processSearch(objDto, pageNo);

		request.setAttribute("SEARCHLIST", cardRepProcessList);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		return mapping.findForward("success");
	}

	/*
	 * this method is for showing the particular record to accept or reject the
	 * cardreplacement form
	 */
	public ActionForward replace(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		ActionErrors errors = null;

		CardReplacementForm objForm = (CardReplacementForm) form;
		CardReplacementDto objCardRepDto = new CardReplacementDto();
		objForm.setCardNumber((String) request.getParameter("cardNumber"));

		try {
			BeanUtils.copyProperties(objCardRepDto, objForm);

			CardReplacementManager objManager = new CardReplacementManager();
			CardManager objCardManager = new CardManager();
			CardProductManager objCardProductManager = new CardProductManager();

			// get card product replacement fees
			CardsDto objCardsDto = objCardManager.getCard(objForm.getCardNumber());
			CardProductFeeDto objProductFeeDto = objCardProductManager.getCardProductFeeDto(objCardsDto.getCardProductId());

			if(objProductFeeDto != null){
				String displayStatus = objProductFeeDto.getDisplayStatus();
				if(displayStatus.equalsIgnoreCase("Active")){
					// this for calling search method in DAOImpl to get the
					// CustomerServiceDataBean
					Collection cardReplaceList = objManager.search(objCardRepDto);
					request.getSession(false).setAttribute("$CARDREPLACELIST$",
							cardReplaceList);
					CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList)
							.get(0);

					// get the carreplacementDto
					objCardRepDto = objManager.getCardReplacementDto((String) request
							.getParameter("applicationId"));
					// copying address from this CardReplacementDto to
					// CardReplacementForm Requestaddress object
					BeanUtils.copyProperties(objForm.getRequestAddress(), objCardRepDto
							.getCustomerAddDto());
					// setting the values from CardReplacementDto to CardReplacementForm
					objForm
					.setReasonCode(String
							.valueOf(objCardRepDto.getReasonCode()));
					objForm.setRemarks(objCardRepDto.getRemarks());
					// this value to pass reject method to update carreplacementForms
					// table when clicks rejection
					// request.setAttribute("ApplicationId",objCardRepDto.getApplicationId());
					objForm.setApplicationId(objCardRepDto.getApplicationId());
					// this for setting the values to from hidden fileds
					objForm.setCardNumber(objCustService.getCardNumber());
					objForm.setCustomerId(objCustService.getCustomerId());
					objForm.setBranchId(objCustService.getBranchId());
					objForm.setCardProductId(objCustService.getCardProductId());
					objForm.setCardHolderType(objCustService.getCardHolderType());

					objForm.setReplacementFees(String.valueOf(objProductFeeDto.getReplacementFee()));
				}else{
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.cardproductfeeconfigurena"));
					saveErrors(request, errors);

					return mapping.findForward("fail");
				}
			}else{
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardproductfeenotconfigure"));
				saveErrors(request, errors);

				return mapping.findForward("fail");
			}

		} catch (Exception e) {
			System.out
			.println("Error converting to form bean in CardReplacementDispatchAction: "
					+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardReplacementProcessDispatchAction: "
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
		CardReplacementDto objCardRepDto = new CardReplacementDto();

		try {
			BeanUtils.copyProperties(objCardRepDto, objForm);
			// to set the Customer Account to newly created cards object
			Set objAccount = ((CustomerServiceDataBean) ((ArrayList) request
					.getSession(false).getAttribute("$CARDREPLACELIST$"))
					.get(0)).getCustomerAccount();
			for (Iterator it = objAccount.iterator(); it.hasNext();) {
				CustomerAccountDto objCustAcc = (CustomerAccountDto) it.next();
				objCardRepDto.setCustomerAccountDto(objCustAcc);
			}

		}

		catch (Exception e) {
			System.out
			.println("Error converting to form bean in CardReplacementFormDispatchAction accept: "
					+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardReplacementFormDispatchAction accept: "
							+ e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolAccept = objManager.accept(objCardRepDto);
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
		CardReplacementDto objCardRepDto = new CardReplacementDto();
		try {

			// objCardRepDto.setApplicationId((String)request.getParameter("applicationId"));
			BeanUtils.copyProperties(objCardRepDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: "
					+ e.getMessage());
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolReject = objManager.reject(objCardRepDto);

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
					if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("C")
							&& objCustService.getBillingTo().equals("C")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					}
				}
			}


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
		boolean boolActivate = objManager.cardactivate(objCardRepDto);

		if (!boolActivate) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.activatefailed"));
			saveErrors(request, errors);
		} else {

			// Action Execution
			Collection cardReplaceList = objManager.search(objCardRepDto);
			request.getSession(false).setAttribute("$CARDREPLACELIST$", cardReplaceList);
			CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList) .get(0);

			// After the Search Result
			BeanUtils.copyProperties(objForm, objCustService);

			// this is setting remarks
			CardStatusRemarksDto objCardStatusRemarksDto = objManager .getCardStatusRemarks(Long.valueOf( objCustService.getCardStatusId()).longValue(), objCustService.getCardNumber());
			if (objCardStatusRemarksDto != null) {
				objForm.setRemarks(objCardStatusRemarksDto.getRemarks());
			}

			// this for setting the billing address
			for (Iterator it = objCustService.getApplicationAddress().iterator(); it.hasNext();) {
				CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
				if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("H") && objCustService.getBillingTo().equals("H")) {
					BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
					break;
				} else if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("S") && objCustService.getBillingTo().equals("S")) {
					BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
					break;
				} else {
					// addressDto.getAddressType()!=null &&
					// addressDto.getAddressType().equals("C") &&
					// objCustService.getBillingTo().equals("C")
					// this to show company Address as defalut
					// BilllingAddress
					if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("C") && objCustService.getBillingTo().equals("C")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
						break;
					}
				}
			}

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.activateSuccess"));
			saveErrors(request, errors);
		}
		// Success
		// resetToken(request);
		return mapping.findForward("success");
	}

	public ActionForward eComEnableDisable(ActionMapping mapping, ActionForm form,
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
		boolean boolActivate = objManager.eComEnableDisable(objCardRepDto);

		if (!boolActivate) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.ecomactivatefailed"));
			saveErrors(request, errors);
		} else {

			// Action Execution
			Collection cardReplaceList = objManager.search(objCardRepDto);
			request.getSession(false).setAttribute("$CARDREPLACELIST$", cardReplaceList);
			CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList)
					.get(0);
			;

			// After the Search Result
			BeanUtils.copyProperties(objForm, objCustService);
			objForm.setRemarks(objCardRepDto.getRemarks());

			// this is setting remarks
			/*CardStatusRemarksDto objCardStatusRemarksDto = objManager
					.getCardStatusRemarks(Long.valueOf(
							objCustService.getCardStatusId()).longValue(),
							objCustService.getCardNumber());
			if (objCardStatusRemarksDto != null) {
				objForm.setRemarks(objCardStatusRemarksDto.getRemarks());
			}*/

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
					if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("C")
							&& objCustService.getBillingTo().equals("C")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					}
				}
			}

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.ecomactivatesucc"));
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

		// Action Execution
		ApplicationFormManager objApplicationFormManager = new ApplicationFormManager();

		String hcountry = objForm.getRequestAddress().getCountry();
		if("MM".equals(hcountry)){
			objForm.setStateList(objApplicationFormManager.getStateList());
			objForm.setCityList(objApplicationFormManager.getCityList(objForm.getRequestAddress().getState()));
			objForm.setTownshipList(objApplicationFormManager.getTownshipList(objForm.getRequestAddress().getCity()));
		}

		// form validation
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		// DTO Creation
		CardReplacementDto objCardRepDto = new CardReplacementDto();
		// this to set the customer to CustomeAdddressDto
		ApplicationProcessDto objcustomerDto = new ApplicationProcessDto();

		try {
			BeanUtils.copyProperties(objCardRepDto.getCustomerAddDto(), objForm.getRequestAddress());
			//BeanUtils.copyProperties(objCardRepDto.getCustomerAddDto(), objForm.getChangeAddress());
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

		CardManager objCardManager = new CardManager();

		try {

			// this to copy the form data to CardClosedto
			BeanUtils.copyProperties(objCardCloseDto, objForm);

			// this copying AddressForm object from CardReplacementForm to
			// CustomerAddressDto
			BeanUtils.copyProperties(objAddressDto, objForm.getRequestAddress());

			// this to setting the CustomerAddressDto to CardCloseDto
			objCardCloseDto.setCustomerAddDto(objAddressDto);

			// setting the application details from constants
			objCardCloseDto.setApplicationId(IdsGenartion.GenerateApplicationId());
			objCardCloseDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_NEW);
			objCardCloseDto.setApplicationType(CommonCodes.APPLICATIONTYPE_CARDCLOSE);

			CardsDto objCardsDto = objCardManager.getCard(objForm.getCardNumber());
			objCardCloseDto.setCustomerId(objCardsDto.getCustomerId());

		} catch (Exception e) {
			System.out.println("Error converting to form bean CardReplacementDispatchAction cardCloseAdd method : " + e);
			throw new TPlusException("Could not populate the form bean CardReplacementDispatchAction cardCloseAdd method: " + e);
		}
		// Action Execution

		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolClose = objManager.cardCloseAdd(objCardCloseDto);

		if (!boolClose) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addfailed"));
			errors.add("Error", new ActionError("error.applicationCreationfail"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			errors.add("Error", new ActionError("alert.cardclose"));
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

		CardReplacementForm objForm = (CardReplacementForm) form;

		HttpSession session = request.getSession(true);
		String branchId = (String)session.getAttribute("LOGEDUSERBRANCH");
		objForm.setBranchId(branchId);

		CardCloseDto objDto = new CardCloseDto();
		
		try {
			objForm.setIssuerId(issuer);
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean in CardReplacementDispatchAction cardCloseProcessSearch method: " + e);
			throw new TPlusException("Could not populate the form bean CardReplacementDispatchAction cardCloseProcessSearch : " + e);
		}

		CardReplacementManager objManager = new CardReplacementManager();
		Collection cardCloseProcessList = objManager.cardCloseProcessSearch(objDto, pageNo);

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

	public ActionForward cardCloseAcceptNew(ActionMapping mapping,
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

		String userId = (String)request.getSession().getAttribute("USERID");

		try {
			BeanUtils.copyProperties(objCardCloseDto, objForm);
		}catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		CardReplacementManager objManager = new CardReplacementManager();
		boolean boolAccept = objManager.cardCloseAcceptNew(objCardCloseDto, userId);

		if (!boolAccept) {
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
			BeanUtils.copyProperties(objAddressDto, objForm.getRequestAddress());

			// this to setting the CustomerAddressDto to CardLimitDto
			objCardLimitDto.setCustomerAddDto(objAddressDto);

			// settiing the application details from constants
			objCardLimitDto.setApplicationId(IdsGenartion.GenerateApplicationId());
			objCardLimitDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_NEW);
			objCardLimitDto.setApplicationType(CommonCodes.APPLICATIONTYPE_CARDLIMITADJUST);

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

			CardManager objCardManager = new CardManager();
			CardsDto objCardsDto = objCardManager.getCard(objForm.getCardNumber());
			objForm.setCreditLimit(String.valueOf(objCardsDto.getCustAccountDto().getCreditLimit()));

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

			//float orgLimit = objCustAccount.getCreditLimit();

			CardManager objCardManager = new CardManager();
			CardsDto objCardsDto = objCardManager.getCard(objForm.getCardNumber());
			objCustAccount = objCardsDto.getCustAccountDto();
			float orgLimit = objCustAccount.getCreditLimit();

			// setting the form values to account object to update
			objCustAccount.setCreditLimit(Float.parseFloat(objForm.getCreditLimit()));
			objCustAccount.setOrgLimit(String.valueOf(orgLimit));
			objCustAccount.setOrgBackDate(objForm.getOrgBackDate());

			//objCustAccount.setCashLimit(Float.parseFloat(objForm.getCashLimit()));

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

	public ActionForward searchReplace(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		ActionErrors errors = null;
		CardReplacementForm objForm = (CardReplacementForm) form;

		// to check cardnumber Entered or not
		if (objForm.getCardNumber() != null && objForm.getCardNumber().equals("")) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnorequired"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}else{
			String strCardNo = objForm.getCardNumber();
			try{
				@SuppressWarnings("unused")
				long longCardNo = Long.valueOf(strCardNo);
			}catch (Exception e) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnoinvalid"));
				saveErrors(request, errors);
				return mapping.findForward("success");
			}
		}

		boolean isError = false;
		String errormsg = "";

		// Dto creation
		CardReplacementDto objCardReplacementDto = new CardReplacementDto();
		CardReplacementDto objCardReplacementDtoForSearch = new CardReplacementDto();

		try {
			
			BeanUtils.copyProperties(objCardReplacementDtoForSearch, objForm);

			CardManager objCardManager = new CardManager();
			SystemParamManager objSystemParamManager = new SystemParamManager();
			CardReplacementManager objCardReplacementManager = new CardReplacementManager();

			CardsDto objCardsDto = objCardManager.getCardByEncryptedData(CardEncryption.encrypt(objForm.getCardNumber()));
			
			// encrypted card no assign
			objCardReplacementDtoForSearch.setEncryptedCardNo(CardEncryption.encrypt(objForm.getCardNumber()));

			if (objCardsDto == null) {
				isError = true;
				errormsg = "error.cardnumbernotexit";
			} else {

				String cardStatus = objCardsDto.getStatus();

				if(!"A".equals(cardStatus)){
					isError = true;
					errormsg = "error.cardclosed";
				}else{

					objCardReplacementDto = objCardReplacementManager.getOpenReplacementFormSubmission(String.valueOf(objCardsDto.getCardNumber()));
					if(objCardReplacementDto == null){
						boolean isEmbossed = objCardManager.isCardEmbossed(String.valueOf(objCardsDto.getCardNumber()));
						if (isEmbossed) {
							Date now = new Date();
							String strExpDate = objCardsDto.getCardExpDate();

							SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
							Date eDate = sdf.parse(strExpDate);

							Calendar calendar = Calendar.getInstance();
							calendar.setTime(eDate);
							calendar.set(calendar.get(1), calendar.get(2), calendar.get(5));

							int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

							calendar.set(calendar.get(1), calendar.get(2), maxDay);
							Date dateExpDate = calendar.getTime();

							if (now.getTime() > dateExpDate.getTime()) {
								//isError = true;
								isError = false; // for some wrong expire date formats. will enable it later
								errormsg = "error.cardcannotreplaceonlyrenew";
							} else {
								SystemParamDto objSystemParamDto = objSystemParamManager.get("ASP");
								int renewalTimeInterval = objSystemParamDto.getRenewalTimeInterval();

								Date renewTimePeriod = DateUtil.addMonths(dateExpDate,-renewalTimeInterval);
								if (now.getTime() > renewTimePeriod.getTime()) {
									//isError = true;
									isError = false; // for some wrong expire date formats. will enable it later
									errormsg = "error.cardgoingtoexpirerenew";
								}
							}
						} else {
							isError = true;
							errormsg = "error.cardnotembossed";
						}
					}else{
						isError = true;
						errormsg = "error.replacementappopen";
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
				Collection cardReplaceList = objCardReplacementManager.search(objCardReplacementDtoForSearch);
				request.getSession(false).setAttribute("$CARDREPLACELIST$", cardReplaceList);
				CustomerServiceDataBean objCustService = (CustomerServiceDataBean) ((ArrayList) cardReplaceList).get(0);

				// After the Search Result
				BeanUtils.copyProperties(objForm, objCustService);

				// this is setting remarks
				/*CardStatusRemarksDto objCardStatusRemarksDto = objCardReplacementManager
						.getCardStatusRemarks(Long.valueOf(
								objCustService.getCardStatusId()).longValue(),
								objCustService.getCardNumber());
				if (objCardStatusRemarksDto != null) {
					objForm.setRemarks(objCardStatusRemarksDto.getRemarks());
				}*/

				// this for setting the billing address
				for (Iterator it = objCustService.getApplicationAddress().iterator(); it.hasNext();) {
					CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
					if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("H") && objCustService.getBillingTo().equals("H")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
						break;
					} else if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("S") && objCustService.getBillingTo().equals("S")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
						break;
					} else {
						if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("C") && objCustService.getBillingTo().equals("C")) {
							BeanUtils.copyProperties(objForm.getRequestAddress(), addressDto);
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error converting to form bean in CardReplacementDispatchAction: " + e);
			throw new TPlusException("Could not populate the form bean in CardReplacementDispatchAction: " + e);
		}

		objForm.getPreListData();

		saveToken(request);
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}

}
