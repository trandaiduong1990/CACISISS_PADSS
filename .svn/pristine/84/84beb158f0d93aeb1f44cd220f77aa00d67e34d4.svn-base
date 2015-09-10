package org.transinfo.cacis.action.csr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

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
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.csr.CsrManager;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.csr.CallRecordLogDto;
import org.transinfo.cacis.dto.csr.CsrDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CreditSplitDto;
import org.transinfo.cacis.formbean.cardproduction.ApplicationProcessSetup;
import org.transinfo.cacis.formbean.csr.CallRecordForm;
import org.transinfo.cacis.formbean.csr.CsrForm;
import org.transinfo.cacis.formbean.customerservice.CardReplacementForm;
import org.transinfo.cacis.formbean.customerservice.CreditSplitForm;

@SuppressWarnings( { "unchecked", "deprecation" })
public class CSRDispatchAction extends BaseDispatchAction {

	/*
	 * mehtod for showing the Previous Call Records Details
	 */
	public ActionForward previousCallData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		ActionErrors errors = null;
		HttpSession session = request.getSession(false);
		CallRecordForm objForm = (CallRecordForm) form;

		// setting the CallReference Number from session to get the
		// PreviousCallList(onclicking NewCall or CallRecord)
		if (session.getAttribute("CALLRECREFNO") != null
				|| session.getAttribute("NEWCALLREFNO") != null) {
			objForm.setOriginalRefNo((String) session
					.getAttribute("CALLRECREFNO"));
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.csrcallrecordcheck"));
			saveErrors(request, errors);
		} else {

			// after upding the record clicks on new call it forward to search
			// page
			return mapping.findForward("csrsearch");
		}

		// DTO Creation
		CallRecordLogDto objCallRecDto = new CallRecordLogDto();

		try {

			BeanUtils.copyProperties(objCallRecDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean CSRDispatchAction previousCallData method : "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction previousCallData method: "
							+ e);
		}

		CsrManager objManager = new CsrManager();
		Collection searchResult = objManager.previousCallData(objCallRecDto);

		if (searchResult != null && searchResult.size() > 0) {
			request.setAttribute("SEARCHLIST", searchResult);
		}

		return mapping.findForward("success");
	}

	/*
	 * mehtod for showing the Call Record Update
	 */
	public ActionForward callRecordUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		ActionErrors errors = null;
		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		CallRecordForm objForm = (CallRecordForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		// setting the OriginalCallReference Number from session to update
		// record when there is no previous calls
		if (request.getSession(false).getAttribute("CALLRECREFNO") == null) {
			objForm.setOriginalRefNo((String) request.getSession(false)
					.getAttribute("NEWCALLREFNO"));

		} else {
			objForm.setOriginalRefNo((String) request.getSession(false)
					.getAttribute("CALLRECREFNO"));
		}
		// DTO Creation
		CallRecordLogDto objCallRecDto = new CallRecordLogDto();

		try {

			BeanUtils.copyProperties(objCallRecDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean CSRDispatchAction callRecordUpdate method : "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean CardReplacementDispatchAction callRecordUpdate method: "
							+ e);
		}

		// Action Execution
		CsrManager objManager = new CsrManager();
		boolean boolUpdate = objManager.callRecordUpdate(objCallRecDto);

		if (!boolUpdate) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
		} else {
			// After Call Recorded deleting the Cardnumber,nric,customername and
			// callrefno from session

			if (request.getSession(false) != null) {
				HttpSession session = request.getSession(false);
				Enumeration listObjects = session.getAttributeNames();
				while (listObjects.hasMoreElements()) {
					String currObj = (String) listObjects.nextElement();
					if (currObj.equals("CARDNUMBER")
							|| currObj.equals("CALLRECREFNO")
							|| currObj.equals("CUSTOMERNAME")
							|| currObj.equals("NRICNO")
							|| currObj.equals("NEWCALLREFNO"))

					{

						session.removeAttribute(currObj);

					}

				}

			}

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		// resetToken(request);
		return mapping.findForward("success");

	}

	/*
	 * mehtod for showing the Open Calls Details
	 */
	public ActionForward openCalls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		try {
			// BeanUtils.copyProperties(objDto, objForm);

			CsrManager objManager = new CsrManager();
			Collection searchResult = objManager.openCalls();

			if (searchResult != null && searchResult.size() > 0) {
				request.setAttribute("SEARCHLIST", searchResult);
			} else {

				ActionErrors errors = new ActionErrors();
				errors.add("Error", new ActionError("error.noopencallsmsg"));
				saveErrors(request, errors);

			}

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CSRDispatchAction in openCalls: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CSRDispatchAction in openCalls: "
							+ e);
		}

		return mapping.findForward("opencalls");
	}

	/*
	 * mehtod for showing the Basic Customer and Card information Data
	 */
	public ActionForward csrSummary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		HttpSession session = request.getSession(false);
		/*
		 * this only for customerservices screens(AllDataView popup link) for
		 * that setting cadrnumber in seeeion to call othermehtods
		 */
		if (request.getParameter("CustServCardNo") != null) {
			session.setAttribute("CARDNUMBER", request
					.getParameter("CustServCardNo"));
		}

		CsrForm objForm = (CsrForm) form;
		objForm.setCardNumber((String) session.getAttribute("CARDNUMBER"));
		// Dto Creation
		CsrDto objDto = new CsrDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CSRDispatchAction in csrSummary method: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CSRDispatchAction in  csrSummary method: "
							+ e);
		}

		CsrManager objManager = new CsrManager();
		Collection searchResult = objManager.csrSummary(objDto);

		if (searchResult != null && searchResult.size() > 0) {

			request.setAttribute("SEARCHLIST", searchResult);

			// this for setting the values in sesion to display on top menu
			// screen
			CommonDataBean objCommon = (CommonDataBean) ((ArrayList) searchResult).get(0);
			// HttpSession session =request.getSession(false);
			session.setAttribute("CUSTOMERNAME", objCommon.getColumn1());
			session.setAttribute("NRICNO", objCommon.getColumn3());

		}
		return mapping.findForward("customersummary");
	}

	/*
	 * mehtod for showing the customerInfo Details
	 */
	public ActionForward customerInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CsrForm objForm = (CsrForm) form;
		objForm.setCardNumber((String) request.getSession(false).getAttribute(
				"CARDNUMBER"));
		// Dto Creation
		CsrDto objDto = new CsrDto();
		ApplicationProcessSetup appForm = null;
		CustomerInfoDto objCustInfoDto = new CustomerInfoDto();
		try {

			BeanUtils.copyProperties(objDto, objForm);

			CsrManager objManager = new CsrManager();
			objCustInfoDto = objManager.customerInfo(objDto);

			appForm = new ApplicationProcessSetup();
			// to copy the customer info details
			BeanUtils.copyProperties(appForm, objCustInfoDto);
			// to copy the address details (bcz application process dto contains
			// AddressDto this returns CustomerAddressDto)
			for (Iterator it = objCustInfoDto.getApplicationAddress()
					.iterator(); it.hasNext();) {
				CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("H")) {
					BeanUtils.copyProperties(appForm.getHomeAddress(),
							addressDto);
				}
				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("C")) {
					BeanUtils.copyProperties(appForm.getCompanyAddress(),
							addressDto);
				}
				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("S")) {
					BeanUtils.copyProperties(appForm.getSupplementaryAddress(),
							addressDto);
				}
			}
		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CSRDispatchAction in customerInfo: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CSRDispatchAction in customerInfo: "
							+ e);
		}

		request.setAttribute("custInfoObj", appForm);
		return mapping.findForward("customerinfo");
	}

	/*
	 * mehtod for showing the cardDetails Details
	 */
	public ActionForward cardDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;

		CsrForm objForm = (CsrForm) form;
		objForm.setCardNumber((String) request.getSession(false).getAttribute(
				"CARDNUMBER"));
		// Dto Creation
		CsrDto objDto = new CsrDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CSRDispatchAction in cardDetails: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CSRDispatchAction in cardDetails: "
							+ e);
		}

		CsrManager objManager = new CsrManager();
		Map cardsDetailList = objManager.cardDetails(objDto);

		if (cardsDetailList != null && cardsDetailList.size() > 0) {

			if (cardsDetailList.containsKey("OtherCards")) {
				ArrayList OtherCardsList = (ArrayList) cardsDetailList
						.get("OtherCards");
				if (OtherCardsList.size() > 0)
					request.setAttribute("OTHERCARDSINFO", OtherCardsList);

			}
			if (cardsDetailList.containsKey("SupplyCards")) {

				ArrayList supplyCardsList = (ArrayList) cardsDetailList
						.get("SupplyCards");
				if (supplyCardsList.size() > 0)
					request.setAttribute("SUPPLYCARDSINFO", supplyCardsList);
			}
			if (cardsDetailList.containsKey("PrimaryCards")) {

				ArrayList primaryCardsList = (ArrayList) cardsDetailList
						.get("PrimaryCards");
				if (primaryCardsList.size() > 0)
					request.setAttribute("PRIMARYCRADINFO", primaryCardsList);
			}

		}else{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nomorecardstoperson"));
			saveErrors(request, errors);
		}
		return mapping.findForward("cardsdetails");
	}

	/*
	 * mehtod for showing the Card AccountInfo Details
	 */
	public ActionForward accountInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CsrForm objForm = (CsrForm) form;
		objForm.setCardNumber((String) request.getSession(false).getAttribute(
				"CARDNUMBER"));
		// Dto Creation
		CsrDto objDto = new CsrDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CSRDispatchAction in accountDetails: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CSRDispatchAction in accountDetails: "
							+ e);
		}

		CsrManager objManager = new CsrManager();
		Collection searchResult = objManager.accountInfo(objDto);

		if (searchResult != null && searchResult.size() > 0) {
			request.setAttribute("SEARCHLIST", searchResult);
		}
		return mapping.findForward("accountinfo");
	}

	/*
	 * mehtod for showing the Card Activity Details
	 */
	public ActionForward cardActivities(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CsrForm objForm = (CsrForm) form;
		objForm.setCardNumber((String) request.getSession(false).getAttribute(
				"CARDNUMBER"));
		// Dto Creation
		CsrDto objDto = new CsrDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CSRDispatchAction in cardActiviteis: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CSRDispatchAction in cardActiviteis: "
							+ e);
		}

		CsrManager objManager = new CsrManager();
		Collection searchResult = objManager.cardActivities(objDto);

		if (searchResult != null && searchResult.size() > 0) {

			request.setAttribute("SEARCHLIST", searchResult);

		} else {
			ActionErrors errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardactivitymsg"));
			saveErrors(request, errors);
		}

		return mapping.findForward("cardactivities");
	}

	/*
	 * mehtod for showing the tranxHistory Details
	 */
	public ActionForward tranxHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CsrForm objForm = (CsrForm) form;
		objForm.setCardNumber((String) request.getSession(false).getAttribute(
				"CARDNUMBER"));
		// Dto Creation
		CsrDto objDto = new CsrDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CSRDispatchAction in tranxHistory: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CSRDispatchAction in tranxHistory: "
							+ e);
		}

		CsrManager objManager = new CsrManager();
		Collection searchResult = objManager.tranxHistory(objDto);

		if (searchResult != null && searchResult.size() > 0) {
			request.setAttribute("SEARCHLIST", searchResult);
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add("Error", new ActionError("error.tranxhistorymsg"));
			saveErrors(request, errors);
		}
		return mapping.findForward("tranxhistory");
	}

	/*
	 * mehtod for showing the tranxHistory Details
	 */
	public ActionForward tranxHistorySearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		ActionErrors errors = null;

		// Form Validations
		CsrForm objForm = (CsrForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("tranxhistory");
		}

		objForm.setCardNumber((String) request.getSession(false).getAttribute(
				"CARDNUMBER"));
		// Dto Creation
		CsrDto objDto = new CsrDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in tranxHistorySearch in tranxHistory: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in tranxHistorySearch in tranxHistory: "
							+ e);
		}

		CsrManager objManager = new CsrManager();
		Collection searchResult = objManager.tranxHistory(objDto);

		if (searchResult != null && searchResult.size() > 0) {
			request.setAttribute("SEARCHLIST", searchResult);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.tranxhistorymsg"));
			saveErrors(request, errors);
		}
		return mapping.findForward("tranxhistory");
	}

	/*
	 * mehtod is for showing to the
	 * CsrActionsScreens(cardreplace,cardclose,BillingAddressChange etc)
	 */
	public ActionForward csrActionsData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardReplacementForm cardRepForm = (CardReplacementForm) form;

		// Dto Creation
		CsrDto objDto = new CsrDto();
		if (request.getSession(false).getAttribute("CARDNUMBER") != null) {
			objDto.setCardNumber(Long.parseLong((String) request.getSession(
					false).getAttribute("CARDNUMBER")));
		}

		CustomerInfoDto objCustInfoDto = new CustomerInfoDto();
		try {
			// Action execution
			CsrManager objManager = new CsrManager();

			// Getting the CustomerInfo dto
			objCustInfoDto = objManager.customerInfo(objDto);

			// this for setting the billing address
			for (Iterator it = objCustInfoDto.getApplicationAddress()
					.iterator(); it.hasNext();) {
				CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals(
								CommonCodes.HOME_ADDRESS)
						&& objCustInfoDto.getBillingTo().equals(
								CommonCodes.HOME_ADDRESS)) {
					BeanUtils.copyProperties(cardRepForm.getRequestAddress(),
							addressDto);
				} else {
					// addressDto.getAddressType()!=null &&
					// addressDto.getAddressType().equals("C") &&
					// objCustService.getBillingTo().equals("C")
					// this to show company Address as defalut BilllingAddress
					BeanUtils.copyProperties(cardRepForm.getRequestAddress(),
							addressDto);
				}
			}

			// this for setting the customerId for Billing AddressChange
			cardRepForm.setCustomerId(objCustInfoDto.getCustomerId());

		} catch (Exception e) {
			System.out
					.println("Error in CSRDispatchAction  csrActionsData method: "
							+ e);
			throw new TPlusException(
					"Error in CSRDispatchAction   csrActionsData method: " + e);
		}

		return mapping.findForward("success");
	}

	/*
	 * mehtod is for getting the current pin Count from cards table for
	 * csrResetPinCount screen
	 */
	public ActionForward csrResetPinCount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		CsrForm objForm = (CsrForm) form;
		if (request.getSession(false).getAttribute("CARDNUMBER") != null) {
			objForm.setCardNumber((String) request.getSession(false)
					.getAttribute("CARDNUMBER"));
		}
		// Dto Creation
		CsrDto objDto = new CsrDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in CSRDispatchAction in csrResetPinCount: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CSRDispatchAction in csrResetPinCount: "
							+ e);
		}

		CsrManager objManager = new CsrManager();
		Collection currentPinCount = objManager.csrResetPinCount(objDto);

		if (currentPinCount != null && currentPinCount.size() > 0) {
			request.setAttribute("PINCOUNT", currentPinCount);
		}
		return mapping.findForward("success");
	}

	/*
	 * mehtod is for getting the cardstatus and addressdata for csrPinResend
	 * screen
	 */
	public ActionForward csrPinResend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardReplacementForm cardRepForm = (CardReplacementForm) form;

		// Dto Creation
		CsrDto objDto = new CsrDto();
		if (request.getSession(false).getAttribute("CARDNUMBER") != null) {
			objDto.setCardNumber(Long.parseLong((String) request.getSession(
					false).getAttribute("CARDNUMBER")));
		}

		CustomerInfoDto objCustInfoDto = new CustomerInfoDto();
		try {
			// Action execution
			CsrManager objManager = new CsrManager();
			// for Customer info and Address
			objCustInfoDto = objManager.customerInfo(objDto);

			// this for getting the CardStatusId(csrResetPinCount() from this
			// method we will get cardstatus)
			Collection cardStatus = objManager.csrResetPinCount(objDto);

			// this for setting the billing address
			for (Iterator it = objCustInfoDto.getApplicationAddress()
					.iterator(); it.hasNext();) {
				CustomerAddressDto addressDto = (CustomerAddressDto) it.next();

				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals(
								CommonCodes.HOME_ADDRESS)
						&& objCustInfoDto.getBillingTo().equals(
								CommonCodes.HOME_ADDRESS)) {
					BeanUtils.copyProperties(cardRepForm.getRequestAddress(),
							addressDto);

				} else {
					// addressDto.getAddressType()!=null &&
					// addressDto.getAddressType().equals("C") &&
					// objCustService.getBillingTo().equals("C")
					// this to show company Address as defalut BilllingAddress
					BeanUtils.copyProperties(cardRepForm.getRequestAddress(),
							addressDto);
				}
			}

			// this for setting the cardstatusId for checking Card is Active or
			// not
			if (cardStatus != null && cardStatus.size() > 0) {
				CommonDataBean objCommon = (CommonDataBean) ((ArrayList) cardStatus)
						.get(0);
				cardRepForm.setCardStatusId(objCommon.getColumn2());
			}

		} catch (Exception e) {
			System.out
					.println("Error in CSRDispatchAction  csrPinResend method: "
							+ e);
			throw new TPlusException(
					"Error in CSRDispatchAction   csrPinResend method: " + e);
		}

		return mapping.findForward("success");
	}

	/*
	 * this method is used for getting the CreditLimitSplitting Data for
	 * CsrScreen
	 */
	public ActionForward creditSplittData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		ActionErrors errors = null;

		CreditSplitForm objForm = (CreditSplitForm) form;
		objForm.setCardNumber((String) request.getSession(false).getAttribute(
				"CARDNUMBER"));

		// Dto Creation
		CreditSplitDto objSplittDto = new CreditSplitDto();

		try {
			// copying data from form to dto to get the data
			BeanUtils.copyProperties(objSplittDto, objForm);

			// Action execution
			CsrManager objManager = new CsrManager();

			// checking the cardholderType(if primary only allow to Creditsplit)
			boolean cardTypeCheck = objManager.validate(objSplittDto, 3);

			if (!cardTypeCheck) {

				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardchecking"));
				saveErrors(request, errors);
				request.setAttribute("CARDHODERTYPECHECK",
						"CheckingforPrimaryCard");

			} else {

				objSplittDto = objManager.creditSplittData(objSplittDto);
				BeanUtils.copyProperties(objForm, objSplittDto);

				// removing all from the Set
				objSplittDto.getLimitFormsList().removeAll(
						objForm.getLimitFormsList());
			}
		} catch (Exception e) {
			System.out
					.println("Error converting to form bean CsrDispatchAction creditSplittData method : "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean CsrDispatchAction creditSplitData method: "
							+ e);
		}

		return mapping.findForward("success");
	}

}