package org.transinfo.cacis.action.cardproduction;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.cardproduction.ApplValidationManager;
import org.transinfo.cacis.dto.cardproduction.ApplValidationSearchDto;
import org.transinfo.cacis.formbean.cardproduction.ApplValidationSearchForm;

public class SearchApplValidationAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchApplValidationAction.class);

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {
		
		if ((request.getParameter("method") != null)
				&& (((String) request.getParameter("method")).equals("List") || ((String) request
						.getParameter("method")).equals("delete"))) {
			try {
				ApplValidationSearchForm objForm = (ApplValidationSearchForm) form;
				ApplValidationSearchForm newObjForm = new ApplValidationSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);
				objForm.setIssuerId((String) request.getSession(false)
						.getAttribute("ISSUER"));

				objForm.getPreListData();
				return mapping.findForward("success");
			} catch (Exception ex) {
				logger.error(ex);
				System.out
						.println("Error in Search Card Risk Profile Action Calling PreList: "
								+ ex);
				throw new TPlusException(
						"Error in Search Card Risk Profile Action Calling PreList " + ex);
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
			
			ApplValidationSearchForm objForm = (ApplValidationSearchForm) form;
			ActionErrors errors = objForm.validate(mapping, request);
			
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			
			// Dto Creation
			ApplValidationSearchDto objDto = new ApplValidationSearchDto();
			
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchApplValidationAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchApplValidationAction execute method: "
								+ e);
			}
			
			// Action Execution
			ApplValidationManager objManager = new ApplValidationManager();
			Collection applValidations = objManager.search(objDto, pageNo);
			
			// Success
			request.setAttribute("SEARCHLIST", applValidations);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			objForm.getPreListData();
			return mapping.findForward("success");
		}
	}
}
