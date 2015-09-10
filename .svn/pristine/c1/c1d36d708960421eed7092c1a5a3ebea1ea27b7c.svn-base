package org.transinfo.cacis.action.cardproduction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.transinfo.cacis.controller.cardproduction.ApplicationFormManager;
import org.transinfo.cacis.controller.cardproduction.ApplicationProcessManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.controller.settings.SalaryProfileManager;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.cardproduction.SupplementaryCardHolderDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.SalaryProfileDto;
import org.transinfo.cacis.formbean.cardproduction.ApplicationFormSuccess;
import org.transinfo.cacis.formbean.cardproduction.ApplicationProcessSetup;

@SuppressWarnings( { "unchecked", "deprecation", "unused" })
public class ApplicationProcessDispatchAction extends BaseDispatchAction {

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ApplicationProcessSetup objForm = (ApplicationProcessSetup) form;
		objForm.getPreListData();
		return mapping.findForward("success");

	}

	/* to update the ApplicationForms table during the application process */
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
		ApplicationProcessSetup objForm = (ApplicationProcessSetup) form;
		/*
		 * errors = objForm.validate(mapping,request);
		 * 
		 * if(errors!=null && !errors.isEmpty()) { saveErrors(request,errors);
		 * request.setAttribute("ACTION","update"); objForm.getPreListData();
		 * return mapping.getInputForward(); }
		 */
		// Dto Creation
		ApplicationFormDto objAppFormDto = new ApplicationFormDto();

		/*
		 * ApplicationProcessDto objAppProcessDto = new ApplicationProcessDto();
		 * SupplementaryCardHolderDto objSuppDto = new
		 * SupplementaryCardHolderDto(); CustomerAccountDto objCutAccountDto =
		 * new CustomerAccountDto(); CardsDto objCardsDto = new CardsDto();
		 */

		try {
			BeanUtils.copyProperties(objAppFormDto, objForm);
			/*
			 * BeanUtils.copyProperties(objAppProcessDto,objForm);
			 * BeanUtils.copyProperties(objSuppDto,objForm);
			 * BeanUtils.copyProperties
			 * (objSuppDto,objForm.getSupplementaryAddress());
			 * BeanUtils.copyProperties(objCutAccountDto,objForm );
			 * //BeanUtils.copyProperties(objCardsDto,objForm ); // Setting DTO
			 * based on Mapping
			 * 
			 * // Customer
			 * objAppProcessDto.getCustomerSuppAddress().add(objSuppDto);
			 * objAppProcessDto.getCustomerAccount().add(objCutAccountDto);
			 * 
			 * // Supplementary objSuppDto.setAppProcessDto(objAppProcessDto);
			 * 
			 * // Customer Account
			 * objCutAccountDto.getCustomerCards().add(objCardsDto);
			 * objCutAccountDto.setAppProcessDto(objAppProcessDto);
			 * 
			 * // Cards objCardsDto.setCustAccountDto(objCutAccountDto);
			 */

		}

		catch (Exception e) {
			System.out
					.println("Error converting to form bean in ApplicationProcessDispatchAction save method: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in ApplicationProcessDispatchAction in save: "
							+ e);
		}
		// Action Execution
		ApplicationProcessManager objManager = new ApplicationProcessManager();
		boolean boolUpdate = objManager.update(objAppFormDto);

		if (boolUpdate) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "cancel");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
		}

		// Success
		resetToken(request);
		objForm.getPreListData();
		return mapping.findForward("success");
	}

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

		// Form Validation
		ApplicationProcessSetup objForm = (ApplicationProcessSetup) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		// Dto Creation
		ApplicationProcessDto objAppProcessDto = new ApplicationProcessDto();

		try {
			BeanUtils.copyProperties(objAppProcessDto, objForm);
		} catch (Exception e) {
			throw new TPlusException(
					"Could not populate the form bean in ApplicationProcessDispatchAction reject method: "
							+ e);
		}

		// Action Execution
		ApplicationProcessManager objManager = new ApplicationProcessManager();
		boolean boolReject = objManager.reject(objAppProcessDto);

		if (boolReject) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.rejectSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "cancel");

		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.rejectfailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");

		}

		// Success
		resetToken(request);
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	public ActionForward pending(ActionMapping mapping, ActionForm form,
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

		// Form Validation
		ApplicationProcessSetup objForm = (ApplicationProcessSetup) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		// Dto Creation
		ApplicationProcessDto objAppProcessDto = new ApplicationProcessDto();

		try {
			BeanUtils.copyProperties(objAppProcessDto, objForm);
		} catch (Exception e) {
			throw new TPlusException(
					"Could not populate the form bean in ApplicationProcessDispatchAction pending method: "
							+ e);
		}

		// Action Execution
		ApplicationProcessManager objManager = new ApplicationProcessManager();
		boolean boolReject = objManager.pending(objAppProcessDto);

		if (boolReject) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.pendingSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "cancel");

		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.pendingfailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");

		}

		// Success
		resetToken(request);
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	public ActionForward accept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		System.out
				.println("****************************************************************************");
		System.out
				.println("************************** DISPATCH ACTION *************************************");
		System.out
				.println("****************************************************************************");

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}
		
		// Form Validations
		ApplicationProcessSetup objForm = (ApplicationProcessSetup) form;
		
		ApplicationFormManager objManagerApplicationFormManager = new ApplicationFormManager();
				
		String hcountry = objForm.getHomeAddress().getCountry();
		if("MM".equals(hcountry)){
			objForm.setStateList(objManagerApplicationFormManager.getStateList());
			objForm.setCityList(objManagerApplicationFormManager.getCityList(objForm.getHomeAddress().getState()));
			objForm.setTownshipList(objManagerApplicationFormManager.getTownshipList(objForm.getHomeAddress().getCity()));
		}

		String ccountry = objForm.getCompanyAddress().getCountry();
		if("MM".equals(ccountry)){
			objForm.setCstateList(objManagerApplicationFormManager.getStateList());
			objForm.setCcityList(objManagerApplicationFormManager.getCityList(objForm.getCompanyAddress().getState()));
			objForm.setCtownshipList(objManagerApplicationFormManager.getTownshipList(objForm.getCompanyAddress().getCity()));
		}

		String scountry = objForm.getSupplementaryAddress().getCountry();
		if("MM".equals(scountry)){
			objForm.setSstateList(objManagerApplicationFormManager.getStateList());
			objForm.setScityList(objManagerApplicationFormManager.getCityList(objForm.getSupplementaryAddress().getState()));
			objForm.setStownshipList(objManagerApplicationFormManager.getTownshipList(objForm.getSupplementaryAddress().getCity()));
		}
		
		errors = objForm.validate(mapping, request);
		System.out.println("Valiation Ended...");

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		/*
		 * errors = objForm.validate(mapping,request);
		 * 
		 * if(errors!=null && !errors.isEmpty()) { saveErrors(request,errors);
		 * objForm.getPreListData(); request.setAttribute("ACTION","update");
		 * return mapping.getInputForward(); }
		 */
		// Dto Creations
		ApplicationProcessDto objAppProcessDto = new ApplicationProcessDto();
		SupplementaryCardHolderDto objSuppDto = new SupplementaryCardHolderDto();
		CustomerAccountDto objCutAccountDto = new CustomerAccountDto();
		CustomerAddressDto supplAddressDto = new CustomerAddressDto();
		
		ApplicationProcessManager objManager = new ApplicationProcessManager();
		
		String nextAttribute = "cancel";
		
		// validate NRC format
		String nrc = objForm.getIdNumber();
		String suppnrc = objForm.getSupplIdNumber();
		
		boolean validationSuccess = true;
		
		/*if(!RegExUtil.isValidNRC(nrc)){

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nrcfromat"));
			saveErrors(request, errors);
			nextAttribute = "update";
			validationSuccess = false;

			// ID need to be saved in UPPER case
			objForm.setIdNumber(objForm.getIdNumber().toUpperCase());
			
		}
		
		if(suppnrc != null && !"".equals(suppnrc) && !RegExUtil.isValidNRC(suppnrc)){

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nrcfromat"));
			saveErrors(request, errors);
			nextAttribute = "update";
			validationSuccess = false;
			
			objForm.setSupplIdNumber(objForm.getSupplIdNumber().toUpperCase());
			
		}
		
		// validate NRC against DB
		boolean isDuplicateNRC = objManager.isDuplicateNRC(objForm.getIdNumber(), objForm.getSupplIdNumber(), objForm.getApplicationId());
		*/
		boolean isDuplicateNRC = false;;
		
		if(!isDuplicateNRC){
			validationSuccess = true;
		}else{

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nrcduplicate"));
			saveErrors(request, errors);
			nextAttribute = "update";
			validationSuccess = false;
			
		}
		
		if(validationSuccess){

			try {
	
				BeanUtils.copyProperties(objAppProcessDto, objForm);
				// Generating CustomerId and Supplymenatary Id
				objAppProcessDto.setCustomerId(IdsGenartion.GenerateCustomerId());
				objAppProcessDto.setCustomerStatus(1);
				// to insert the supplemetary Id in cards and supplycardHolder Table
				objAppProcessDto.setSupplmenatryId(IdsGenartion.GenerateSupplementaryId());
	
				BeanUtils.copyProperties(objSuppDto, objForm);
				BeanUtils.copyProperties(objSuppDto, objForm.getSupplementaryAddress());
				BeanUtils.copyProperties(objCutAccountDto, objForm);
				objCutAccountDto.setAccountId(IdsGenartion.GenerateAccountId());
				// for testing
				//objCutAccountDto.setAccountId("AC0908216281");
				objCutAccountDto.setAccountName(objAppProcessDto.getCustomerName());
	
				/* Setting DTO based on Mapping */
	
				// Customer
				if(objAppProcessDto.getCheckedSupplCardRequired() == 'Y'){
					objAppProcessDto.getCustomerSuppAddress().add(objSuppDto);
				}
				objAppProcessDto.getCustomerAccount().add(objCutAccountDto);
	
				// Supplementary
				objSuppDto.setSupplementaryId(objAppProcessDto.getSupplmenatryId());
				objSuppDto.setAppProcessDto(objAppProcessDto);
	
				// Customer Account
	
				objCutAccountDto.setAppProcessDto(objAppProcessDto);
	
				// Copying the Address to DTO
				CustomerAddressDto homeAddressDto = new CustomerAddressDto();
				CustomerAddressDto companyAddressDto = new CustomerAddressDto();
				BeanUtils.copyProperties(supplAddressDto, objForm.getSupplementaryAddress());
	
				BeanUtils.copyProperties(homeAddressDto, objForm.getHomeAddress());
				// homeAddressDto.setAddressId(Long.valueOf(IdsGenartion.GenerateAddessId()).longValue());
				BeanUtils.copyProperties(companyAddressDto, objForm.getCompanyAddress());
				// companyAddressDto.setAddressId(Long.valueOf(IdsGenartion.GenerateAddessId()).longValue());
	
				objAppProcessDto.getApplicationAddress().add(homeAddressDto);
				objAppProcessDto.getApplicationAddress().add(companyAddressDto);
	
			}
	
			catch (Exception e) {
				System.out
						.println("Error converting to form bean in ApplicationProcessDispatchAction accept method : "
								+ e);
				throw new TPlusException(
						"Could not populate the form bean in ApplicationProcessDispatchAction accept method: "
								+ e);
			}
			
			boolean boolAccept = objManager.accept(objAppProcessDto, supplAddressDto);
	
			// this for Application Accept Success jsp to show the Generated Cards
			// list(primary and supply cards)
			if (boolAccept) {
				// preList calling to show the cardproduct name
				objForm.getPreListData();
				ApplicationFormSuccess objAppSuccess = null;
				ArrayList appPrimaryCardsList = new ArrayList();
				ArrayList appSuppCardsList = new ArrayList();
				Set objCards = objCutAccountDto.getCustomerCards();
	
				for (Iterator it = objCards.iterator(); it.hasNext();) {
	
					CardsDto objCardsDto = (CardsDto) it.next();
					objAppSuccess = new ApplicationFormSuccess();
					objAppSuccess.setCustomerName(objForm.getCustomerName());
					objAppSuccess.setIdNumber(objForm.getIdNumber());
					objAppSuccess.setCardNumber(objCardsDto.getCardNumber());
					objAppSuccess.setCardExpDate(objCardsDto.getCardExpDate());
	
					// newly added due to AppCardProductList changed ArrayList to
					// Map
					Map dataMap = objForm.getAppCardProductList();
					Set dataSet = dataMap.entrySet();
					Iterator cardit = dataSet.iterator();
					while (cardit.hasNext()) {
						Map.Entry me = (Map.Entry) cardit.next();
						String CardProductId = (String) me.getKey();
	
						if (CardProductId.equals(objCardsDto.getCardProductId())) {
							objAppSuccess.setCardProduct((String) me.getValue());
						}
					}
	
					objAppSuccess.setCreditLimit(objForm.getCreditLimit());
					objAppSuccess.setCurrency((String) ((TreeMap) objForm
							.getCurrencyList()).get(objForm.getCurrencyCode()));
	
					if (objCardsDto.getCardHolderType() == CommonCodes.PRIMARYCARD_HOLDER) {
						appPrimaryCardsList.add(objAppSuccess);
					} else {
						appSuppCardsList.add(objAppSuccess);
	
					}
				}
				if (appPrimaryCardsList.size() > 0) {
					request.setAttribute("APPSUCCESSPRIMCARDLIST",
							appPrimaryCardsList);
				}
				if (appSuppCardsList.size() > 0) {
	
					request
							.setAttribute("APPSUCCESSSUPPCARDLIST",
									appSuppCardsList);
				}
	
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.acceptSuccess"));
				saveErrors(request, errors);
	
			} else {
	
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.acceptfailed"));
				saveErrors(request, errors);
				objForm.getPreListData();
				request.setAttribute("ACTION", "update");
				return mapping.findForward("success");
	
			}
		
		}else{

			objForm.getPreListData();
			request.setAttribute("ACTION", nextAttribute);
			return mapping.findForward("success");
			
		}

		return mapping.findForward("acceptSuccess");
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ApplicationProcessSetup objForm = (ApplicationProcessSetup) form;
		
		ApplicationProcessManager objManager = new ApplicationProcessManager();
		SalaryProfileManager objSalaryProfileManager = new SalaryProfileManager();
		CardProductManager objCardProductManager = new CardProductManager();
		
		List salaryprofileList = new ArrayList();
		SalaryProfileDto objSalaryProfileDto = null;
		
		ApplicationProcessDto objDto = objManager.getApplicationProcessDto(request.getParameter("applicationId"));

		try {
			String cardProductId = objDto.getSelectedAppCardProducts();
			
			BeanUtils.copyProperties(objForm, objDto);
			
			CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(cardProductId);
			String maintainAcc = objCardProductDto.getAccountCreation();
			
			if(maintainAcc != null && "Y".equals(maintainAcc)){
				
				//String carProductType = objCardProductManager.getCardProductType(cardProductId);
				String carProductType = objCardProductDto.getCardProductType().getCardProductType();
				if(carProductType.equals("CreditCard")){
					salaryprofileList = objSalaryProfileManager.getSalaryProfileListAppProcess(cardProductId, String.valueOf(objDto.getIncome()));
				}
				
				if(salaryprofileList.size() > 0){
					objSalaryProfileDto = (SalaryProfileDto)salaryprofileList.get(0);
					objForm.setCashLimit(String.valueOf(objSalaryProfileDto.id.getCashAdvanceLimit()));
					objForm.setCreditLimit(String.valueOf(objSalaryProfileDto.id.getCreditLimit()));
				}
				
				request.setAttribute("ACCDETTAB", "ON");
				
			}else{
				request.setAttribute("ACCDETTAB", "OFF");
			}

		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in ApplicationProcessDispatchAction  change method: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in ApplicationProcessDispatchAction change method: "
							+ e);
		}

		ApplicationFormManager objManagerApplicationFormManager = new ApplicationFormManager();
		
		String hcountry = objForm.getHomeAddress().getCountry();
		if("MM".equals(hcountry)){
			objForm.setStateList(objManagerApplicationFormManager.getStateList());
			objForm.setCityList(objManagerApplicationFormManager.getCityList(objForm.getHomeAddress().getState()));
			objForm.setTownshipList(objManagerApplicationFormManager.getTownshipList(objForm.getHomeAddress().getCity()));
		}

		String ccountry = objForm.getCompanyAddress().getCountry();
		if("MM".equals(ccountry)){
			objForm.setCstateList(objManagerApplicationFormManager.getStateList());
			objForm.setCcityList(objManagerApplicationFormManager.getCityList(objForm.getCompanyAddress().getState()));
			objForm.setCtownshipList(objManagerApplicationFormManager.getTownshipList(objForm.getCompanyAddress().getCity()));
		}

		String scountry = objForm.getSupplementaryAddress().getCountry();
		if("MM".equals(scountry)){
			objForm.setSstateList(objManagerApplicationFormManager.getStateList());
			objForm.setScityList(objManagerApplicationFormManager.getCityList(objForm.getSupplementaryAddress().getState()));
			objForm.setStownshipList(objManagerApplicationFormManager.getTownshipList(objForm.getSupplementaryAddress().getCity()));
		}
		
		objForm.getPreListData();
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	public ActionForward customerHistory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {
		ApplicationProcessSetup objForm = (ApplicationProcessSetup) form;
		ApplicationProcessManager objManager = new ApplicationProcessManager();
		// Collection historyList =
		// objManager.customerHistory(objForm.getIdNumber());
		Collection historyList = objManager.customerHistory((String) request
				.getParameter("nricNo"), (String) request
				.getParameter("applicationId"));

		if (historyList != null && !historyList.isEmpty()) {
			request.setAttribute("CUSTOMERHISTORYLIST", historyList);
		}
		
		request.setAttribute("CHISTORYTAB", "CHISTORYTAB");
		
		request.setAttribute("ACCDETTAB", request.getParameter("ACCDETTAB"));
		
		// objForm.setIssuerId((String)request.getSession(false).getAttribute("ISSUER"));
		objForm.getPreListData();
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");

	}

}
