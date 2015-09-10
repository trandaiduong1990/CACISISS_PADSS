package org.transinfo.cacis.action.settings;

import java.io.IOException;

import javax.servlet.ServletException;
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
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.settings.CreditLimitProfileManager;
import org.transinfo.cacis.dto.settings.CreditLimitProfileSearchDto;
import org.transinfo.cacis.formbean.settings.CreditLimitProfileSearchForm;
import org.transinfo.cacis.formbean.settings.EMVProfileSearchForm;

public class SearchCreditLimitProfileAction extends BaseAction {
	
private Logger logger = Logger.getLogger(SearchCreditLimitProfileAction.class);
	
	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {

		if ((request.getParameter("method") != null)
				&& (((String) request.getParameter("method")).equals("List"))) {
			try {

				CreditLimitProfileSearchForm objForm = (CreditLimitProfileSearchForm) form;
				CreditLimitProfileSearchForm newObjForm = new CreditLimitProfileSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);

				//objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
				objForm.getPreListData();
				objForm.setScreen(CommonCodes.TYPE_SCREEN_SEARCH);
				return mapping.findForward("success");

			} catch (Exception exp) {
				logger.error(exp);
				System.out
						.println("Error in SearchCreditLimitProfileAction Calling PreList: "
								+ exp);
				throw new TPlusException(
						"Error in SearchCreditLimitProfileAction Calling PreList " + exp);
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
			
			int totCount = 0;
			
			CreditLimitProfileSearchForm objForm = (CreditLimitProfileSearchForm) form;
			ActionErrors errors = objForm.validate(mapping, request);
			
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			
			// Dto Creation
			CreditLimitProfileSearchDto objDto = new CreditLimitProfileSearchDto();
			
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchCreditLimitProfileAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchCreditLimitProfileAction execute method: "
								+ e);
			}
			
			// Action Execution
			CreditLimitProfileManager objManager = new CreditLimitProfileManager();
			CreditLimitProfileSearchDto creditLimitProfile = objManager.search(objDto, pageNo);
			
			// Success
			totCount = objDto.getTotalCount();
			objForm.setTotalCount(String.valueOf(totCount));
			
			if (totCount > 0) {
				request.setAttribute("SEARCHLIST", objDto.getSearchList());
				request.setAttribute("PAGENO", new Integer(pageNo).toString());
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.nodatafound"));
				saveErrors(request, errors);
			}
			
			objForm.getPreListData();
			objForm.setScreen(CommonCodes.TYPE_SCREEN_SEARCH);
			return mapping.findForward("success");
		}
	}
}
