package org.transinfo.cacis.action.cardproduction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.cardproduction.ApplicationProcessManager;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessSearchDto;
import org.transinfo.cacis.formbean.cardproduction.ApplicationProcessSearchForm;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
@SuppressWarnings("unchecked")
public class SearchApplicationProcessAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ApplicationProcessSearchForm objForm = (ApplicationProcessSearchForm) form;
		
		String issuer = (String)request.getSession().getAttribute("ISSUER");

		if (request.getParameter("method") != null
				&& ((String) request.getParameter("method")).equals("List")) {
			try {
				ApplicationProcessSearchForm newObjForm = new ApplicationProcessSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);
				//return mapping.findForward("success");

			} catch (Exception exp) {
				throw new TPlusException(
						"Error in Search Branch Action Calling PreList " + exp);
			}
		}

		HttpSession session = request.getSession(true);
		String branchId = (String)session.getAttribute("LOGEDUSERBRANCH");
		objForm.setBranchId(branchId);
		
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
			// dto creation
			ApplicationProcessSearchDto objDto = new ApplicationProcessSearchDto();
			try {
				objForm.setIssuerId(issuer);
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				System.out
						.println("Error converting to form bean in SearchApplicationProcessAction execute: "
								+ e);
				throw new TPlusException(
						"Could not populate the form bean in SearchApplicationProcessAction execute: "
								+ e);
			}
			// Action execution
			ApplicationProcessManager objManager = new ApplicationProcessManager();
			Collection appProcessList = objManager.search(objDto, pageNo);
			
			request.setAttribute("SEARCHLIST", appProcessList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			return mapping.findForward("success");
		}
	
}
