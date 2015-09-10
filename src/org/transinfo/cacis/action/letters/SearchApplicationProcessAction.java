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
import org.transinfo.cacis.controller.letters.ApplicationProcessSearchManager;
import org.transinfo.cacis.dto.letters.ApplicationProcessSearchDto;
import org.transinfo.cacis.formbean.letters.ApplicationProcessSearch;

public class SearchApplicationProcessAction extends BaseDispatchAction {

	public ActionForward preList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			ApplicationProcessSearch objForm = (ApplicationProcessSearch) form;
			objForm.setLetterCategory((String) request.getParameter("letterCategory"));
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
		ApplicationProcessSearch objForm = (ApplicationProcessSearch) form;
		System.out.println("objForm.getLetterCategory() => "
				+ objForm.getLetterCategory());
		int pageNo = 0;
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		ApplicationProcessSearch objForm = (ApplicationProcessSearch) form;
		System.out.println("objForm.getLetterCategory() => "
				+ objForm.getLetterCategory());
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
		ApplicationProcessSearch objForm = (ApplicationProcessSearch) form;
		System.out.println("objForm.getLetterCategory() => "
				+ objForm.getLetterCategory());
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}
	
	public void commonSearch(int pageNo, ApplicationProcessSearch objForm, HttpServletRequest request) throws TPlusException, Exception{
		ApplicationProcessSearchDto objDto = new ApplicationProcessSearchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		ApplicationProcessSearchManager objManager = new ApplicationProcessSearchManager();
		Collection ApplicationProcess = objManager.search(objDto, pageNo);

		request.setAttribute("SEARCHLIST", ApplicationProcess);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		System.out.println("ApplicationProcessAction: execute() successful");
		objForm.getPreListData();
	}
}