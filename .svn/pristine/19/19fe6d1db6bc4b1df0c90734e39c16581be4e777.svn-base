package org.transinfo.cacis.action.riskmanagement;

import java.util.Collection;

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
import org.transinfo.cacis.controller.riskmanagement.PhotocopyRequestManager;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestSearchDto;
import org.transinfo.cacis.formbean.riskmanagement.OriginalRequest;
import org.transinfo.cacis.formbean.riskmanagement.OriginalRequestSearch;

public class SearchPhotocopyRequestAction extends BaseDispatchAction {

	public ActionForward preList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			OriginalRequestSearch objOldForm = (OriginalRequestSearch) form;
			OriginalRequestSearch objForm = new OriginalRequestSearch();
			try {
				BeanUtils.copyProperties(objOldForm, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
			}
		} catch (Exception exp) {
			System.out.println("Error converting to form bean: " + exp);
		}
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		OriginalRequestSearch objForm = (OriginalRequestSearch) form;
		// Form Validations
		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "search");
			return mapping.getInputForward();
		}
		int pageNo = 0;
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		OriginalRequestSearch objForm = (OriginalRequestSearch) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) + 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward PREV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		OriginalRequestSearch objForm = (OriginalRequestSearch) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public void commonSearch(int pageNo, OriginalRequestSearch objForm,
			HttpServletRequest request) throws TPlusException, Exception {
		OriginalRequest objORForm = new OriginalRequest();
		OriginalRequestSearchDto objDto = new OriginalRequestSearchDto();
		OriginalRequestDto objOriReq = new OriginalRequestDto();
		objORForm.setCardNumber(objForm.getCardNumber());
		try {
			BeanUtils.copyProperties(objDto, objForm);
			BeanUtils.copyProperties(objOriReq, objORForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		boolean exist = false;
		ActionErrors errors = null;
		PhotocopyRequestManager objManager = new PhotocopyRequestManager();
		exist = objManager.validate(objOriReq, BaseDAO.INQUIRY);

		if (!exist) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.idnotexist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "search");
		} else {
			Collection PhotocopyRequest = objManager.search(objDto, pageNo);
			request.setAttribute("SEARCHLIST", PhotocopyRequest);
		}
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		System.out.println("PhotocopyRequestAction: execute() successful");
	}
}