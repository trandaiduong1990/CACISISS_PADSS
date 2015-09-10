package org.transinfo.cacis.action.riskmanagement;

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
import org.transinfo.cacis.controller.riskmanagement.WriteOffManager;
import org.transinfo.cacis.dto.riskmanagement.WriteOffMasterDto;
import org.transinfo.cacis.formbean.riskmanagement.WriteOffForm;

public class WriteOffDispatchAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addmethod()");
		WriteOffManager objManager = new WriteOffManager();
		WriteOffMasterDto objDto = new WriteOffMasterDto();
		WriteOffForm objForm = (WriteOffForm) form;
		boolean boolAdd = true;
		boolean idExist = false;
		ActionErrors errors = null;

		// Form Validations
		errors = objForm.validate(mapping, request);
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		idExist = objManager.validateExist(objDto);

		if (idExist) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.idduplicate"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		} else {
			boolAdd = objManager.add(objDto);
		}

		if (!boolAdd) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}
		System.out.println("Record Added" + boolAdd);
		request.setAttribute("ACTION", "change");
		return mapping.findForward("success");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		System.out.println("we are in dipatchAction update method");
		WriteOffManager objManager = new WriteOffManager();
		WriteOffMasterDto objDto = new WriteOffMasterDto();
		WriteOffForm objForm = (WriteOffForm) form;
		ActionErrors errors = null;
		boolean idExist = false;
		boolean boolUpdate = false;

		// DTO Creation
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		idExist = objManager.validateExist(objDto);
		if (idExist) {
			boolUpdate = objManager.update(objDto);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.idnotexist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		if (!boolUpdate) {
			System.out.println("WriteOffDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("WriteOffDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "change");
		return mapping.findForward("success");
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		WriteOffManager objManager = new WriteOffManager();
		WriteOffMasterDto objDto = new WriteOffMasterDto();
		WriteOffForm objForm = (WriteOffForm) form;
		HttpSession objSession = null;
		objSession = request.getSession(false);
		String strIssuerId = (String) objSession.getAttribute("ISSUER");
		System.out.println("strIssuerId => " + strIssuerId);
		objForm.setIssuerId(strIssuerId);
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		boolean idExist = objManager.validateExist(objDto);
		System.out.println("idExist => " + idExist);
		if (idExist) {
			objDto = objManager.get(strIssuerId);
			try {
				BeanUtils.copyProperties(objForm, objDto);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: "
						+ e);
			}
			request.setAttribute("ACTION", "update");
			System.out.println("idExist UPDATE");
		} else {
			request.setAttribute("ACTION", "add");
			System.out.println("idNotExist ADD");
		}

		// Success
		// saveToken(request);
		return mapping.findForward("success");
	}

}
