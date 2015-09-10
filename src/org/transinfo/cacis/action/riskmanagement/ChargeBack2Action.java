package org.transinfo.cacis.action.riskmanagement;

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
import org.transinfo.cacis.controller.riskmanagement.ChargeBack2Manager;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;
import org.transinfo.cacis.formbean.riskmanagement.OriginalRequest;

public class ChargeBack2Action extends BaseDispatchAction {

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		System.out.println("we are in dipatchAction update method");
		OriginalRequest objForm = (OriginalRequest) form;
		OriginalRequestDto objDto = new OriginalRequestDto();
		ChargeBack2Manager objManager = new ChargeBack2Manager();
		ActionErrors errors = null;
		boolean idExist = false;
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
			System.out.println("Error converting to dto: " + e);
			throw new TPlusException("Could not populate to the dto: " + e);
		}

		idExist = objManager.validate(objDto, BaseDAO.UPDATE);
		if (idExist) {
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
					.println("OriginalRequestDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
			try {
				BeanUtils.copyProperties(objForm, objDto);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: "
						+ e);
			}
		}

		System.out.println("OriginalRequestDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		ChargeBack2Manager objManager = new ChargeBack2Manager();
		OriginalRequest objForm = (OriginalRequest) form;
		// DTO Creation
		OriginalRequestDto objDto = new OriginalRequestDto();
		String strSettlementId = request.getParameter("settlementId");
		long settlementId = Long.parseLong(strSettlementId);
		objDto = objManager.get(settlementId);
		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Success
		saveToken(request);
		String errorMsg = "";
		int status = objDto.getStatus();
		if (status != 3) {
			switch (status) {
			case 0:
				errorMsg = "originalrequestsetup.useoriginalfunc";
				break;
			case 1:
				errorMsg = "originalrequestsetup.usephotocopyfunc";
				break;
			case 2:
				errorMsg = "originalrequestsetup.usechargebackfunc";
				break;
			case 4:
				errorMsg = "originalrequestsetup.chargeback";
				break;
			}
			ActionErrors errors = null;
			errors = new ActionErrors();
			errors.add("Error", new ActionError(errorMsg));
			saveErrors(request, errors);
		}
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}
	
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OriginalRequest objOldForm = (OriginalRequest) form;
		OriginalRequest objForm = new OriginalRequest();
		try {
			BeanUtils.copyProperties(objOldForm, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
		}
		return mapping.findForward("listPage");
	}
}
