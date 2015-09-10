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
import org.transinfo.cacis.controller.riskmanagement.UserActivityManager;
import org.transinfo.cacis.dto.riskmanagement.UserActivitySearchDto;
import org.transinfo.cacis.formbean.riskmanagement.UserActivitySearch;

public class SearchUserActivityAction extends BaseDispatchAction {

	/*public ActionForward preList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			UserActivitySearch objForm = (UserActivitySearch) form;
		} catch (Exception exp) {
			System.out.println("Error converting to form bean: " + exp);
		}
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}*/

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		ActionErrors errors = null;
		UserActivitySearch objForm = (UserActivitySearch) form;
		// Form Validations
		errors = objForm.validate(mapping, request);
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		int pageNo = 0;
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		UserActivitySearch objForm = (UserActivitySearch) form;
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
		UserActivitySearch objForm = (UserActivitySearch) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public void commonSearch(int pageNo, UserActivitySearch objForm,
			HttpServletRequest request) throws TPlusException, Exception {
		UserActivitySearchDto objDto = new UserActivitySearchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		UserActivityManager objManager = new UserActivityManager();
		Collection UserActivity = objManager.search(objDto, pageNo);

		request.setAttribute("SEARCHLIST", UserActivity);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		System.out.println("UserActivityAction: execute() successful");
	}
}