package org.transinfo.cacis.action.settings;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.settings.CycleManager;
import org.transinfo.cacis.dto.settings.CycleSearchDto;
import org.transinfo.cacis.formbean.settings.CycleSearchForm;

public class SearchCycleAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchCycleAction.class);

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
		CycleSearchForm objForm = (CycleSearchForm) form;
		objForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));

		// Dto Creation
		CycleSearchDto objDto = new CycleSearchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in SearchCycleAction execute method: "
							+ e.getMessage());
			throw new TPlusException(
					"Error converting to form bean in SearchCycleAction execute method: "
							+ e);
		}
		// Action Execution
		CycleManager objManager = new CycleManager();
		Collection clycles = objManager.search(objDto, pageNo);

		request.setAttribute("SEARCHLIST", clycles);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		return mapping.findForward("success");
	}
}
