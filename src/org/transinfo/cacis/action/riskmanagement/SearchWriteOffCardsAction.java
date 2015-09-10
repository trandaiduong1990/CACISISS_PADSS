package org.transinfo.cacis.action.riskmanagement;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.riskmanagement.WriteOffCardsManager;
import org.transinfo.cacis.dto.riskmanagement.WriteOffCardsDto;
import org.transinfo.cacis.formbean.riskmanagement.WriteOffCardsSearch;

public class SearchWriteOffCardsAction extends BaseDispatchAction {
	
	/*public ActionForward preList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			WriteOffCardsSearch objForm = (WriteOffCardsSearch) form;
		} catch (Exception exp) {
			System.out.println("Error converting to form bean: " + exp);
		}
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}*/
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		WriteOffCardsSearch objForm = (WriteOffCardsSearch) form;
		int pageNo = 0;

		// Form Validations
		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "search");
			return mapping.getInputForward();
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		WriteOffCardsSearch objForm = (WriteOffCardsSearch) form;
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
		WriteOffCardsSearch objForm = (WriteOffCardsSearch) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public void commonSearch(int pageNo, WriteOffCardsSearch objForm,
			HttpServletRequest request) throws TPlusException, Exception {
		WriteOffCardsDto objDto = new WriteOffCardsDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		System.out.println("objForm.getFromDate() => "+objForm.getStrFromDate());
		System.out.println("objForm.getToDate() => "+objForm.getStrToDate());
		WriteOffCardsManager objManager = new WriteOffCardsManager();
		Collection WriteOffCards = objManager
				.search(objDto, pageNo);
		System.out.println("objDto.getFromDate() => "+objDto.getStrFromDate());
		System.out.println("objDto.getToDate() => "+objDto.getStrToDate());
		request.setAttribute("SEARCHLIST", WriteOffCards);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		System.out
				.println("WriteOffCardsAction: execute() successful");
	}
}