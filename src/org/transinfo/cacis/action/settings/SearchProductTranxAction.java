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
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.controller.settings.ProductTranxManager;
import org.transinfo.cacis.dto.settings.CardProductSearchDto;
import org.transinfo.cacis.dto.settings.ProductTranxSearchDto;
import org.transinfo.cacis.formbean.settings.CardProductSearchForm;
import org.transinfo.cacis.formbean.settings.ProductTranxSearchForm;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
@SuppressWarnings({"deprecation","unchecked"})
public class SearchProductTranxAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchProductTranxAction.class);

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		if ((request.getParameter("method") != null)
				&& (((String) request.getParameter("method")).equals("List") || ((String) request
						.getParameter("method")).equals("delete"))) {
			try {

				ProductTranxSearchForm objForm = (ProductTranxSearchForm) form;
				ProductTranxSearchForm newObjForm = new ProductTranxSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);


				objForm.setIssuerId((String) request.getSession(false)
						.getAttribute("ISSUER"));
				objForm.getPreListData();
				return mapping.findForward("success");

			} catch (Exception exp) {
				logger.error(exp);
				System.out
				.println("Error in Search Product Tranx PreList: "
						+ exp);
				throw new TPlusException(
						"Error in Search Product Tranx PreList " + exp);
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
			ProductTranxSearchForm objForm = (ProductTranxSearchForm) form;

			ActionErrors errors = objForm.validate(mapping, request);

			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			// Dto Creation
			ProductTranxSearchDto objDto = new ProductTranxSearchDto();
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
				.println("Error converting to form bean in SearchProductTranxAction execute method: "
						+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchProductTranxAction execute method: "
								+ e);
			}
			// Action Execution
			ProductTranxManager objManager = new ProductTranxManager();
			Collection productTranx = objManager.search(objDto, pageNo);
			int totCount = 0;
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
			saveToken(request);
			return mapping.findForward("success");
		}
	}
}