package org.transinfo.cacis.action.letters;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.letters.DispatchLetterManager;
import org.transinfo.cacis.dto.letters.DispatchLetterSearchDto;
import org.transinfo.cacis.formbean.letters.DispatchLetterSearch;

public class SearchDispatchLetterAction extends BaseDispatchAction {
	
	public ActionForward preList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			DispatchLetterSearch objForm = (DispatchLetterSearch) form;
			objForm.getPreListData();
		} catch (Exception exp) {
			System.out.println("Error converting to form bean: " + exp);
		}
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DispatchLetterSearch objForm = (DispatchLetterSearch) form;
		int pageNo = 0;
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}
	
	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DispatchLetterSearch objForm = (DispatchLetterSearch) form;
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
		DispatchLetterSearch objForm = (DispatchLetterSearch) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}
	
	public void commonSearch(int pageNo, DispatchLetterSearch objForm, HttpServletRequest request) throws TPlusException, Exception{
		DispatchLetterSearchDto objDto = new DispatchLetterSearchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		DispatchLetterManager objManager = new DispatchLetterManager();
		Collection dispatchLetter = objManager.search(objDto, pageNo);
		request.setAttribute("SEARCHLIST", dispatchLetter);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		System.out.println("LIST SIZE" + dispatchLetter.size());
		System.out
				.println("SearchDispatchLetterAction: execute() successful");
		objForm.getPreListData();
	}
	
}