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
import org.transinfo.cacis.controller.settings.IssuerManager;
import org.transinfo.cacis.dto.settings.IssuerSearchDto;
import org.transinfo.cacis.formbean.settings.IssuerSearchForm;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
@SuppressWarnings("unchecked")
public class SearchIssuerAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchIssuerAction.class);

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		if (request.getParameter("method") != null
				&& ((String) request.getParameter("method")).equals("List")) {

			try {
				IssuerSearchForm objForm = (IssuerSearchForm) form;
				//objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
				objForm.getPreListData();
				return mapping.findForward("success");

			} catch (Exception exp) {
				logger.error(exp);
				System.out
						.println("Error in SearchIssuerAction Calling PreList: "
								+ exp);
				throw new TPlusException(
						"Error in SearchIssuerAction Calling PreList " + exp);
			}
		}

		else {

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

			IssuerSearchForm objForm = (IssuerSearchForm) form;
			// Dto Creation
			IssuerSearchDto objDto = new IssuerSearchDto();
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				System.out
						.println("Error converting to form bean in SearchIssuerAction execute method: "
								+ e.getMessage());
				logger.error(e);
				throw new TPlusException(
						"Error converting to form bean in SearchIssuerAction execute method: "
								+ e);
			}
			// Action execution
			IssuerManager objManager = new IssuerManager();
			Collection issuers = objManager.search(objDto, pageNo);

			request.setAttribute("SEARCHLIST", issuers);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			objForm.getPreListData();
			return mapping.findForward("success");
		}
	}
}