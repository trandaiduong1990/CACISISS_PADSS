package org.transinfo.cacis.action.cardproduction;

import java.util.Date;

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
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.cardproduction.ApplicationFormManager;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.common.ApplicationMasterDto;
import org.transinfo.cacis.formbean.cardproduction.ApplicationSetupForm;

@SuppressWarnings( { "deprecation", "unused" })
public class ApplicationFormDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(ApplicationFormDispatchAction.class);

	/*
	 * this method is used for getting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ApplicationSetupForm objOldForm = (ApplicationSetupForm) form;
		ApplicationSetupForm objForm = new ApplicationSetupForm();
		objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));

		ApplicationFormManager objManager = new ApplicationFormManager();

		try {
			BeanUtils.copyProperties(objOldForm, objForm);
			objOldForm.getPreListData();
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in ApplicationFormDispatchAction addNew : "
							+ e.getMessage());
			throw new TPlusException(
					"Error converting to form bean in ApplicationFormDispatchAction addNew : "
							+ e);
		}
		request.setAttribute("PRODUCTLIST", objManager.getProductList(objForm.getIssuerId()));
		
		return mapping.findForward("success");
	}

	/*
	 * this method is used for creating new applicationform
	 */

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
		ApplicationSetupForm objForm = (ApplicationSetupForm) form;

		// Action Execution
		ApplicationFormManager objManager = new ApplicationFormManager();
				
		String hcountry = objForm.getHomeAddress().getCountry();
		if("MM".equals(hcountry)){
			objForm.setStateList(objManager.getStateList());
			objForm.setCityList(objManager.getCityList(objForm.getHomeAddress().getState()));
			objForm.setTownshipList(objManager.getTownshipList(objForm.getHomeAddress().getCity()));
		}

		String ccountry = objForm.getCompanyAddress().getCountry();
		if("MM".equals(ccountry)){
			objForm.setCstateList(objManager.getStateList());
			objForm.setCcityList(objManager.getCityList(objForm.getCompanyAddress().getState()));
			objForm.setCtownshipList(objManager.getTownshipList(objForm.getCompanyAddress().getCity()));
		}

		String scountry = objForm.getSupplementaryAddress().getCountry();
		if("MM".equals(scountry)){
			objForm.setSstateList(objManager.getStateList());
			objForm.setScityList(objManager.getCityList(objForm.getSupplementaryAddress().getState()));
			objForm.setStownshipList(objManager.getTownshipList(objForm.getSupplementaryAddress().getCity()));
		}
		
		System.out.println("Valiation Starting...");
		System.out
				.println("*********************************************************************");
		errors = objForm.validate(mapping, request);
		System.out.println("Valiation Ended...");

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		// DTO Creation
		ApplicationFormDto objDto = new ApplicationFormDto();
		try {

			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in ApplicationFormDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in ApplicationFormDispatchAction add method: "
							+ e);
		}		
		
		Date date = new Date();
		objDto.setApplicationId(IdsGenartion.GenerateApplicationId());
		System.out.println("Appl Id=" + objDto.getApplicationId());
		objDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_NEW);
		objDto.setApplicationType(CommonCodes.APPLICATIONTYPE_NEWCARD);
		objDto.setOpenDate(date);

		// to check existing record
		ApplicationMasterDto objAppMasterDto = new ApplicationMasterDto();
		objAppMasterDto.setApplicationType(objDto.getApplicationType());
		objAppMasterDto.setIdNumber(objDto.getIdNumber());

		String nextAttribute = "cancel";
		
		// validate NRC format
		String nrc = objForm.getIdNumber();
		String suppnrc = objForm.getSupplIdNumber();
		
		boolean validationSuccess = true;
		
		/*if(!RegExUtil.isValidNRC(nrc)){

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nrcfromat"));
			saveErrors(request, errors);
			nextAttribute = "add";
			validationSuccess = false;

			// ID need to be saved in UPPER case
			objDto.setIdNumber(objDto.getIdNumber().toUpperCase());
			
		}
		
		if(suppnrc != null && !"".equals(suppnrc) && !RegExUtil.isValidNRC(suppnrc)){

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nrcfromat"));
			saveErrors(request, errors);
			nextAttribute = "add";
			validationSuccess = false;
			
			objDto.setSupplIdNumber(objDto.getSupplIdNumber().toUpperCase());
			
		}*/
		
		if(validationSuccess){
			
			boolean recExistRes = objManager.validate(objAppMasterDto, 0);
			
			if(!recExistRes){
				
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.applicationcreateerror"));
				saveErrors(request, errors);
				nextAttribute = "add";
				
			}else{
				
				// validate NRC against DB
				boolean isDuplicateNRC = false;//objManager.isDuplicateNRC(objDto);
			
				if(!isDuplicateNRC){
					
					boolean boolAdd = objManager.add(objDto);
			
					if (!boolAdd) {
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.addfailed"));
						saveErrors(request, errors);
						nextAttribute = "add";
					} else {
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.addSuccess"));
						saveErrors(request, errors);
						nextAttribute = "cancel";
					}
				
				}else{
					
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.nrcduplicate"));
					saveErrors(request, errors);
					nextAttribute = "add";
					
				}
			
			}
		
		}
		
		resetToken(request);
		request.setAttribute("ACTION", nextAttribute);
		objForm.getPreListData();
		
		return mapping.findForward("success");

	}

	/*
	 * this method is used for updating the applicationform
	 */
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
		ApplicationSetupForm objForm = (ApplicationSetupForm) form;

		// Action Execution
		ApplicationFormManager objManager = new ApplicationFormManager();
		
		String hcountry = objForm.getHomeAddress().getCountry();
		if("MM".equals(hcountry)){
			objForm.setStateList(objManager.getStateList());
			objForm.setCityList(objManager.getCityList(objForm.getHomeAddress().getState()));
			objForm.setTownshipList(objManager.getTownshipList(objForm.getHomeAddress().getCity()));
		}

		String ccountry = objForm.getCompanyAddress().getCountry();
		if("MM".equals(ccountry)){
			objForm.setCstateList(objManager.getStateList());
			objForm.setCcityList(objManager.getCityList(objForm.getCompanyAddress().getState()));
			objForm.setCtownshipList(objManager.getTownshipList(objForm.getCompanyAddress().getCity()));
		}

		String scountry = objForm.getSupplementaryAddress().getCountry();
		if("MM".equals(scountry)){
			objForm.setSstateList(objManager.getStateList());
			objForm.setScityList(objManager.getCityList(objForm.getSupplementaryAddress().getState()));
			objForm.setStownshipList(objManager.getTownshipList(objForm.getSupplementaryAddress().getCity()));
		}
		
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		// DTO Creation
		ApplicationFormDto objDto = new ApplicationFormDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in ApplicationFormDispatchAction update method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in ApplicationFormDispatchAction update method: "
							+ e);
		}

		// validate NRC format
		String nrc = objForm.getIdNumber();
		String suppnrc = objForm.getSupplIdNumber();

		String nextAttribute = "cancel";
		boolean validationSuccess = true;
		
		/*if(!RegExUtil.isValidNRC(nrc)){

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nrcfromat"));
			saveErrors(request, errors);
			nextAttribute = "add";
			validationSuccess = false;

			// ID need to be saved in UPPER case
			objDto.setIdNumber(objDto.getIdNumber().toUpperCase());
			
		}
		
		if(suppnrc != null && !"".equals(suppnrc) && !RegExUtil.isValidNRC(suppnrc)){

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nrcfromat"));
			saveErrors(request, errors);
			nextAttribute = "add";
			validationSuccess = false;
			
			objDto.setSupplIdNumber(objDto.getSupplIdNumber().toUpperCase());
			
		}*/
		
		if(validationSuccess){
		
			boolean recExistRes = objManager.validateUpdate(objDto);
			
			if(!recExistRes){
				
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.applicationcreateerror"));
				saveErrors(request, errors);
				nextAttribute = "update";
				
			}else{
				
				// validate NRC against DB
				boolean isDuplicateNRC = false;//objManager.isDuplicateNRC(objDto);
			
				if(!isDuplicateNRC){
				
					boolean boolUpdate = objManager.update(objDto);
			
					if (!boolUpdate) {
			
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.updatefailed"));
						saveErrors(request, errors);
						nextAttribute = "update";
					} else {
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.updateSuccess"));
						saveErrors(request, errors);
						nextAttribute = "cancel";
					}
				
				}else{
					
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.nrcduplicate"));
					saveErrors(request, errors);
					nextAttribute = "add";
					
				}
				
			}
			
		}
		// Success
		resetToken(request);
		request.setAttribute("ACTION", nextAttribute);
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	/*
	 * this method is used for deleting the applicationform
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
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
		ApplicationSetupForm objForm = (ApplicationSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		// DTO Creation
		ApplicationFormDto objDto = new ApplicationFormDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in ApplicationFormDispatchAction delete method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in ApplicationFormDispatchAction delete method:: "
							+ e);
		}
		// Action Execution
		ApplicationFormManager objManager = new ApplicationFormManager();
		String nextaction = "delete";
		
		boolean recExistRes = objManager.validate(objDto, 1);
		String nextAttribute = "cancel";
		
		if(!recExistRes){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.recordnotexists"));
			saveErrors(request, errors);
			nextaction = "success";
			nextAttribute = "update";
			
		}else{

			boolean boolDelete = objManager.delete(objDto);
	
			if (!boolDelete) {
	
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.deletefailed"));
				saveErrors(request, errors);
				nextaction = "success";
				nextAttribute = "update";
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.deleteSuccess"));
				saveErrors(request, errors);
				nextAttribute = "cancel";
			}
		
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", nextAttribute);
		objForm.getPreListData();
		return mapping.findForward(nextaction);
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ApplicationFormManager objManager = new ApplicationFormManager();
		ApplicationSetupForm objForm = (ApplicationSetupForm) form;
		// DTO Creation
		ApplicationFormDto objDto = new ApplicationFormDto();

		objDto = objManager.getApplicationForm(request
				.getParameter("applicationId"));
		System.out.println("++++Action+++++appID: ++"+objDto.getApplicationId());


		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in ApplicationFormDispatchAction change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in ApplicationFormDispatchAction change method: "
							+ e);
		}
		
		String hcountry = objForm.getHomeAddress().getCountry();
		if("MM".equals(hcountry)){
			objForm.setStateList(objManager.getStateList());
			objForm.setCityList(objManager.getCityList(objForm.getHomeAddress().getState()));
			objForm.setTownshipList(objManager.getTownshipList(objForm.getHomeAddress().getCity()));
		}

		String ccountry = objForm.getCompanyAddress().getCountry();
		if("MM".equals(ccountry)){
			objForm.setCstateList(objManager.getStateList());
			objForm.setCcityList(objManager.getCityList(objForm.getCompanyAddress().getState()));
			objForm.setCtownshipList(objManager.getTownshipList(objForm.getCompanyAddress().getCity()));
		}

		String scountry = objForm.getSupplementaryAddress().getCountry();
		if("MM".equals(scountry)){
			objForm.setSstateList(objManager.getStateList());
			objForm.setScityList(objManager.getCityList(objForm.getSupplementaryAddress().getState()));
			objForm.setStownshipList(objManager.getTownshipList(objForm.getSupplementaryAddress().getCity()));
		}

		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
}
