package org.transinfo.cacis.action.collectionmanagement;

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
import org.transinfo.cacis.action.settings.SearchEMVProfileAction;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyPolicyManager;
import org.transinfo.cacis.controller.settings.EMVProfileManager;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicySearchDto;
import org.transinfo.cacis.dto.settings.EMVProfileSearchDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyPolicyForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyPolicySearchForm;
import org.transinfo.cacis.formbean.settings.EMVProfileSearchForm;

public class SearchDelinquencyPolicyAction extends BaseAction {
	private Logger logger = Logger.getLogger(SearchEMVProfileAction.class);

	@SuppressWarnings("deprecation")
	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {
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

		DelinquencyPolicySearchForm objForm = (DelinquencyPolicySearchForm) form;
		ActionErrors errors = objForm.validate(mapping, request);
		objForm.setIssuerId((String) request.getSession(false)
				.getAttribute("ISSUER"));

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			objForm.getPreListData(objForm.getIssuerId());
			return mapping.getInputForward();
		}

		// Dto Creation
		DelinquencyPolicySearchDto objDto = new DelinquencyPolicySearchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setIssuerId((String) request.getSession(false).getAttribute(
					"ISSUER"));
			if ((request.getParameter("back") != null && request.getParameter(
					"back").equals("true"))
					|| request.getParameter("showList") != null) {
				objDto.setPolicyId(null);
				objForm.setPolicyId(null);
			}
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in SearchDelinquencyPolicyAction execute method: "
							+ e.getMessage());
			throw new TPlusException(
					"Error converting to form bean in SearchDelinquencyPolicyAction execute method: "
							+ e);
		}

		// Action Execution
		DelinquencyPolicyManager objManager = new DelinquencyPolicyManager();
		Collection policyList = objManager.search(objDto, pageNo);

		// Success
		request.setAttribute("SEARCHLIST", policyList);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		objForm.getPreListData(objDto.getIssuerId());
		return mapping.findForward("success");
	}

}
