package org.transinfo.cacis.action.customerservice;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.customerservice.CustomerScreenProcessManager;
import org.transinfo.cacis.dto.customerservice.CustomerScreenSearchDto;
import org.transinfo.cacis.formbean.customerservice.CustomerScreenForm;

@SuppressWarnings("unchecked")
public class SearchCustomerScreenAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		int pageNo = 0;

		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("NEXT")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) + 1;

			}
		}
		if (request.getParameter("mode") != null
				&& ((String) request.getParameter("mode")).equals("PREV")) {

			if (request.getParameter("pageNo") != null) {
				pageNo = Integer.parseInt((String) request
						.getParameter("pageNo")) - 1;

			}
		}
		CustomerScreenForm objForm = (CustomerScreenForm) form;
		// DTO creation
		CustomerScreenSearchDto objDto = new CustomerScreenSearchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			throw new TPlusException("SearchCustomerScreenAction execute: " + e);
		}

		// Action execution
		CustomerScreenProcessManager objManager = new CustomerScreenProcessManager();
		Collection cusScreenProcessList = objManager.search(objDto, pageNo);

		request.setAttribute("SEARCHLIST", cusScreenProcessList);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		return mapping.findForward("success");
	}
}
