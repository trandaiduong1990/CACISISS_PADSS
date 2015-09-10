package org.transinfo.cacis.action.authorization;

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
import org.transinfo.cacis.controller.authorization.SMSServiceManager;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.authorization.SMSServiceDto;
import org.transinfo.cacis.formbean.authorization.SMSServiceForm;

public class SMSServiceDispatchAction extends BaseDispatchAction {
	
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			SMSServiceForm objOldForm = (SMSServiceForm) form;
			SMSServiceForm objForm = new SMSServiceForm();
			objForm.setIssuerId(objOldForm.getIssuerId());
			try {
				BeanUtils.copyProperties(objOldForm, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: "
						+ e);
			}
			objOldForm.getPreListData();
		} catch (Exception exp) {
			System.out.println("Error converting to form bean: " + exp);
		}
		request.setAttribute("ACTION", "add");
		return mapping.findForward("success");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		SMSServiceForm objForm = (SMSServiceForm) form;
		objForm.getPreListData();
		SMSServiceDto objDto = new SMSServiceDto();
		SMSServiceManager objManager = new SMSServiceManager();
		System.out.println(" we are in DispatchAction: add method()");

		ActionErrors errors = null;
		boolean idExist = false;
		boolean idDuplicate = false;
		boolean boolAdd = false;

		// Form Validations
		System.out.println(" ...1... ");
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		System.out.println(" ...2... ");
		// DTO Creation
		
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		
		idExist = objManager.validateExist(objDto);
		// idExist is to check whether a card number is exixt in the cards table
		// if idExist then can add child record to the card
		// for example, 1234567890123460000 will become 1.23456789012346E18 in the DB
		idDuplicate = objManager.validateDuplicate(objDto, BaseDAO.ADD);
		System.out.println(" idExist= "+idExist);
		System.out.println(" idDuplicate= "+idDuplicate);
		if(idExist && !idDuplicate){
			// Form Validations
			errors = objForm.validate(mapping, request);
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.getInputForward();
			}

			// Action Execution
			boolAdd = objManager.add(objDto);	
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.idnotexist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		
		if (!boolAdd) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);

		}

		System.out.println("Record Added" + boolAdd);
		// Success
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		SMSServiceManager objManager = new SMSServiceManager();
		SMSServiceForm objForm = (SMSServiceForm) form;
		objForm.getPreListData();
		// DTO Creation
		SMSServiceDto objDto = new SMSServiceDto();
		objDto = objManager.get(Long.parseLong(request.getParameter("cardNumber")));
		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		System.out.println("we are in dipatchAction update method");
		SMSServiceForm objForm = (SMSServiceForm) form;
		objForm.getPreListData();
		SMSServiceDto objDto = new SMSServiceDto();
		SMSServiceManager objManager = new SMSServiceManager();
		ActionErrors errors = null;
		boolean idExist = false;
		boolean idDuplicate = false;
		boolean boolUpdate = false;
		
		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}
		
		// DTO Creation
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		
		idExist = objManager.validateExist(objDto);
		// idExist is to check whether a card number is exixt in the cards table
		// if idExist then can add child record to the card
		// for example, 1234567890123460000 will become 1.23456789012346E18 in the DB
		idDuplicate = objManager.validateDuplicate(objDto, BaseDAO.UPDATE);
		if(idExist && idDuplicate){
			// Form Validations
			errors = objForm.validate(mapping, request);
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.getInputForward();
			}

			// Action Execution
			boolUpdate = objManager.update(objDto);	
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.idnotexist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		
		if (!boolUpdate) {
			System.out
					.println("SMSServiceDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out
				.println("SMSServiceDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}
	
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SMSServiceForm objOldForm = (SMSServiceForm) form;
		SMSServiceForm objForm = new SMSServiceForm();
		try {
			BeanUtils.copyProperties(objOldForm, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
		}
		return mapping.findForward("listPage");
	}

}
