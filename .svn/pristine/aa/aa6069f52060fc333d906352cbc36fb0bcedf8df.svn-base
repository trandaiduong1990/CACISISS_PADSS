package org.transinfo.cacis.action.log;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.log.UserActivitiesManager;
import org.transinfo.cacis.dto.log.UserActivitiesSearchDto;
import org.transinfo.cacis.formbean.log.UserActivitiesSearchForm;

public class SearchUserActivitiesAction extends BaseAction {

	@SuppressWarnings("unchecked")
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		if (request.getParameter("method") != null
				&& ((String) request.getParameter("method")).equals("List")) {

			try {
				UserActivitiesSearchForm objForm = (UserActivitiesSearchForm) form;
				objForm.setIssuerId((String) request.getSession().getAttribute(
						"ISSUER"));
				objForm.getPreListData();
				return mapping.findForward("success");

			} catch (Exception exp) {
				System.out.println("Error converting to form bean: " + exp);
				throw new TPlusException("Could not populate the form bean: "
						+ exp);
			}
		} else {

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

			UserActivitiesSearchForm objForm = (UserActivitiesSearchForm) form;
			UserActivitiesSearchDto objDto = new UserActivitiesSearchDto();
			objForm.getPreListData();

			try {

				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: "
						+ e);
			}
			UserActivitiesManager objManager = new UserActivitiesManager();
			Collection userActivitiesForm = objManager.search(objDto, pageNo);

			request.setAttribute("SEARCHLIST", userActivitiesForm);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

			return mapping.findForward("success");
		}
	}

}