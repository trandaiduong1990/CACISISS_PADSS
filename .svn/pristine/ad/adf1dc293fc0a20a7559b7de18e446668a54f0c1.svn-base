package org.transinfo.cacis.action.settings;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.settings.MCCManager;
import org.transinfo.cacis.dto.settings.MCCSearchDto;
import org.transinfo.cacis.formbean.settings.MCCSearchForm;

@SuppressWarnings( { "deprecation", "unchecked" })
public class SearchMCCAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchMCCAction.class);

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		MCCSearchForm objForm = (MCCSearchForm) form;
		String mccId = objForm.getMerchantId();

		ActionErrors errors = null;

		if (mccId == null || "".equals(mccId)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("errors.selectmcc"));
			saveErrors(request, errors);

			return mapping.findForward("success");
		}

		if (request.getParameter("method") != null
				&& ((String) request.getParameter("method")).equals("List")) {
			try {

				MCCSearchForm newObjForm = new MCCSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);
				return mapping.findForward("success");

			} catch (Exception exp) {
				throw new TPlusException(
						"Error in Search Branch MCC Calling PreList " + exp);
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

			objForm.setIssuerId((String) request.getSession(false)
					.getAttribute("ISSUER"));
			// Dto Creation
			MCCSearchDto objDto = new MCCSearchDto();
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchMCCAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchMCCAction execute method: "
								+ e);
			}
			// Action Execution
			MCCManager objManager = new MCCManager();
			Collection clycles = objManager.search(objDto, pageNo);

			request.setAttribute("SEARCHLIST", clycles);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			return mapping.findForward("success");
		}
	}

}