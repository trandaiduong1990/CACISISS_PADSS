package org.transinfo.cacis.action.cardproduction;

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
import org.transinfo.cacis.controller.cardproduction.ApplicationFormManager;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormSearchDto;
import org.transinfo.cacis.formbean.cardproduction.ApplicationSearchForm;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
@SuppressWarnings("unchecked")
public class SearchApplicationFormAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchApplicationFormAction.class);

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		String issuer = (String)request.getSession().getAttribute("ISSUER");

		if ((request.getParameter("method") != null)
				&& (((String) request.getParameter("method")).equals("List"))) {
			try {

				ApplicationSearchForm objForm = (ApplicationSearchForm) form;
				ApplicationSearchForm newObjForm = new ApplicationSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);

				return mapping.findForward("success");

			} catch (Exception exp) {
				logger.error(exp);
				System.out
						.println("Error in Search Branch Action Calling PreList: "
								+ exp);
				throw new TPlusException(
						"Error in Search Branch Action Calling PreList " + exp);
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

			ApplicationSearchForm objForm = (ApplicationSearchForm) form;
			// Dto Creation
			ApplicationFormSearchDto objDto = new ApplicationFormSearchDto();
			try {
				objForm.setIssuerId(issuer);
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchApplicationFormAction: execute() : "
								+ e);
				throw new TPlusException(
						"Could not populate the form bean in SearchApplicationFormAction: execute() : "
								+ e);
			}
			// Action Execution
			ApplicationFormManager objManager = new ApplicationFormManager();
			Collection applist = objManager.search(objDto, pageNo);

			request.setAttribute("SEARCHLIST", applist);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			return mapping.findForward("success");
		}
	}

}