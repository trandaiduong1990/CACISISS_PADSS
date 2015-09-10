package org.transinfo.cacis.action.settings;

import java.util.Collection;

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
import org.transinfo.cacis.controller.settings.CardProductRateManager;
import org.transinfo.cacis.dto.settings.CardProductRateSearchDto;
import org.transinfo.cacis.formbean.settings.CardProductRateSearchForm;

public class SearchCardProductRateAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchCardProductRateAction.class);

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		if (request.getParameter("method") != null
				&& ((String) request.getParameter("method")).equals("List")) {
			try {

				CardProductRateSearchForm objForm = (CardProductRateSearchForm) form;
				CardProductRateSearchForm newObjForm = new CardProductRateSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);

				
				objForm.setIssuerId((String) request.getSession(false)
						.getAttribute("ISSUER"));
				objForm.getPreListData();
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

			CardProductRateSearchForm objForm = (CardProductRateSearchForm) form;
			// CardProductRateSetupForm objForm = (CardProductRateSetupForm)
			// form;

			ActionErrors errors = objForm.validate(mapping, request);

			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			// Dto creation
			CardProductRateSearchDto objDto = new CardProductRateSearchDto();
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchCardProductRateAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchCardProductRateAction execute method: "
								+ e);
			}
			// Action Execution
			CardProductRateManager objManager = new CardProductRateManager();
			Collection cardPrRatels = objManager.search(objDto, pageNo);

			request.setAttribute("SEARCHLIST", cardPrRatels);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			objForm.getPreListData();
			return mapping.findForward("success");
		}
	}
}