package org.transinfo.cacis.action.letters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.letters.LetterApplMapManager;
import org.transinfo.cacis.controller.letters.LetterTemplateManager;
import org.transinfo.cacis.controller.settings.EMVProfileManager;
import org.transinfo.cacis.dto.letters.LetterCategoryDto;
import org.transinfo.cacis.dto.letters.LetterTemplateDto;
import org.transinfo.cacis.dto.settings.EMVProfileDto;
import org.transinfo.cacis.formbean.letters.LetterApplMapSetupForm;
import org.transinfo.cacis.formbean.letters.LetterTemplateSetupForm;
import org.transinfo.cacis.formbean.settings.EMVProfileSetupForm;
import org.transinfo.cacis.letters.printing.LetterPrinting;

public class LetterTemplateDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(LetterTemplateDispatchAction.class);

	/**
	* this method is used for showing the LetterTemplateDispatchAction to add
	*/
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		LetterTemplateSetupForm objOldForm = (LetterTemplateSetupForm) form;
		LetterTemplateSetupForm objForm = new LetterTemplateSetupForm();
		
		try {
			BeanUtils.copyProperties(objOldForm, objForm);
			objOldForm.getPreListData((String) request.getSession().getAttribute(
					"ISSUER"));
			
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in LetterTemplateDispatchAction in add method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in LetterTemplateDispatchAction method: "
							+ ex);
		}
		
		// Success
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for creating the EMVProfileProcessDispatchAction to add
	*/
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		LetterTemplateSetupForm objForm = (LetterTemplateSetupForm) form;
		errors = objForm.validate(mapping, request);
		LetterTemplateDto objDto = new LetterTemplateDto();
		
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData((String) request.getSession().getAttribute("ISSUER"));
			return mapping.getInputForward();
		}
			
		try {
			String userId = (String) request.getSession(false).getAttribute("USERID");
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedDate(new Date());
			objDto.setLastUpdatedBy(userId);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in LetterTemplateDispatchAction add method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in LetterTemplateDispatchAction add method "
							+ ex);
		}
		
		// Action Execution
		LetterTemplateManager objManager = new LetterTemplateManager();
		String nextAttribute = "cancel";
		objDto.setCategory(objManager.getLetterCategory(objForm.getLetterCategory()));

		// Check record
		boolean recExistRes = objManager.validate(objDto, 0);
				
		if(!recExistRes) {
			
			errors = new ActionErrors();
			
			if (!recExistRes) {
				errors.add("Error", new ActionError("error.lettertemplate.exitst"));
			} 

			saveErrors(request, errors);
			nextAttribute = "add";
		}else{
			boolean boolCreated = objManager.add(objDto);
			
			if (!boolCreated) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
				nextAttribute = "add";
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				nextAttribute = "cancel";
				LetterTemplateDto objAfter = new LetterTemplateDto();
				objAfter = objManager.getLetterTemplateDetail(objForm.getLetterId());
				objForm.setApplicationSource(objAfter.getApplicationSource());
			}
		}
		
		// Success
		objForm.getPreListData((String) request.getSession().getAttribute("ISSUER"));
		request.setAttribute("ACTION", nextAttribute);		
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for showing the LetterTemplateDispatchAction to change
	*/
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		LetterTemplateManager objManager = new LetterTemplateManager();
		LetterTemplateSetupForm objForm = (LetterTemplateSetupForm) form;
		
		try {
			LetterTemplateDto objDto = objManager.getLetterTemplateDetail(request.getParameter("id"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setLetterCategory(objDto.getCategory().getId());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in LetterTemplateDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in LetterTemplateDispatchAction method: "
							+ ex);
		}
		
		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData((String) request.getSession().getAttribute("ISSUER"));
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for update the EMVProfileProcessDispatchAction
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
		LetterTemplateSetupForm objForm = (LetterTemplateSetupForm) form;

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "change");
			objForm.getPreListData((String) request.getSession().getAttribute("ISSUER"));
			return mapping.getInputForward();
		}
		
		// Action Execution
		LetterTemplateManager objManager = new LetterTemplateManager();
		LetterTemplateDto objDto = new LetterTemplateDto();
		
		try {
			String userId = (String) request.getSession(false).getAttribute("USERID");
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedDate(new Date());
			objDto.setLastUpdatedBy(userId);
			
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in LetterTemplateDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in LetterTemplateDispatchAction update method:"
							+ ex);
		}
		
		// Check record
		boolean recExistRes = objManager.validate(objDto, 1);
		objDto.setCategory(objManager.getLetterCategory(objForm.getLetterCategory()));
		
		if(!recExistRes) {
			
			errors = new ActionErrors();
			if (!recExistRes) {
				errors.add("Error", new ActionError("error.lettertemplate.exitst"));
			} 
			request.setAttribute("ACTION", "change");
			saveErrors(request, errors);
			objForm.getPreListData((String) request.getSession().getAttribute("ISSUER"));
			return mapping.getInputForward();
			
		} else {
			boolean boolUpdate = objManager.update(objDto);
			
			if (!boolUpdate) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updatefailed"));
				saveErrors(request, errors);
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updateSuccess"));
				saveErrors(request, errors);
			}
		}
		
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		objForm.getPreListData((String) request.getSession().getAttribute("ISSUER"));
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for delete the LetterTemplateDispatchAction
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
		LetterTemplateSetupForm objForm = (LetterTemplateSetupForm) form;
		objForm.getPreListData((String) request.getSession().getAttribute("ISSUER"));
		
		// Dto Creation
		LetterTemplateDto objDto = new LetterTemplateDto();
		
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean in LetterTemplateDispatchAction in delete method "
					+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in LetterTemplateDispatchAction in delete method: "
									+ e);
		}
		
		// Action Execution
		LetterTemplateManager objManager = new LetterTemplateManager();

		boolean emvProfileExits = objManager.validate(objDto, 0);
		
		if (emvProfileExits) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.producthascards"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData((String) request.getSession().getAttribute("ISSUER"));
			return mapping.findForward("success");
		} 
		
		String nextaction = "delete";
		boolean boolDelete = objManager.delete(objDto);

		if (!boolDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "success";
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}
	
	/**
	* this method is used for creating the LetterTemplateDispatchAction to printLetter
	*/
	public void printLetter(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		LetterPrinting letterPrint = new LetterPrinting();
		letterPrint.letterPrinting();
	}
}
