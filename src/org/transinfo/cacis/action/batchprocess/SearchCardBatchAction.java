package org.transinfo.cacis.action.batchprocess;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

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
import org.transinfo.cacis.controller.batchprocess.CardBatchManager;
import org.transinfo.cacis.controller.settings.EMVProfileManager;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.batchprocess.SearchInstantCardDto;
import org.transinfo.cacis.dto.settings.EMVProfileSearchDto;
import org.transinfo.cacis.formbean.batchprocess.CardBatchSearchForm;
import org.transinfo.cacis.formbean.settings.EMVProfileSearchForm;
import org.transinfo.cacis.util.DateUtil;

public class SearchCardBatchAction extends BaseAction {
	private Logger logger = Logger.getLogger(SearchCardBatchAction.class);

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

		CardBatchSearchForm objForm = (CardBatchSearchForm) form;
		ActionErrors errors = objForm.validate(mapping, request);

		if (request.getParameter("back") != null
				&& request.getParameter("back").equals("true"))
			objForm.setBatchName(null);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.getInputForward();
		}

		boolean hasFromDate = objForm.getFromDate() != null
				&& objForm.getFromDate() != "";
		boolean hasToDate = objForm.getToDate() != null
				&& objForm.getToDate() != "";
		if (hasFromDate || hasToDate) {
			errors.add("Error", new ActionError("cardBatch.invalidFromDate"));
			Date from = DateUtil.convertDateStringToDate(objForm.getFromDate());
			Date to = DateUtil.convertDateStringToDate(objForm.getToDate());

			if ((hasFromDate && from == null) || (hasToDate && to == null)
					|| (from != null && to != null && from.compareTo(to) == 1)) {
				errors = new ActionErrors();
				if (hasFromDate && from == null)
					errors.add("Error", new ActionError(
							"cardBatch.invalidFromDate"));
				if (hasToDate && to == null)
					errors.add("Error", new ActionError(
							"cardBatch.invalidToDate"));
				if (from != null && to != null && from.compareTo(to) == 1)
					errors.add("Error", new ActionError(
							"cardBatch.fromGreaterThanTo"));
				saveErrors(request, errors);
				return mapping.getInputForward();
			}
		}
		// Dto Creation
		SearchInstantCardDto objDto = new SearchInstantCardDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setScreenType(mapping.getParameter().equals("add") ? "='N' "
					: mapping.getParameter().equals("auth") ? "in ('N','A') "
							: "in ('P','A')");
			objDto.setIssuerId((String) request.getSession(false).getAttribute(
					"ISSUER"));
			if(request.getParameter("showList")!=null){
				objDto.setBatchName(null);
				objForm.setBatchName(null);
			}
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in SearchCardBatchAction execute method: "
							+ e.getMessage());
			throw new TPlusException(
					"Error converting to form bean in SearchCardBatchAction execute method: "
							+ e);
		}

		// Action Execution
		CardBatchManager objManager = new CardBatchManager();
		Collection cardBatch = objManager.search(objDto, pageNo);
		objForm.setTotal(objDto.getTotal());
		boolean chkShowTotalRecs = (objForm.getBatchName() == "" || objForm
				.getBatchName() == null)
				&& (objForm.getFromDate() == "" || objForm.getFromDate() == null)
				&& (objForm.getToDate() == "" || objForm.getToDate() == null);

		// Success
		request.setAttribute("SEARCHLIST", cardBatch);
		if (!chkShowTotalRecs)
			request.setAttribute("CHKTOTALRECS", "1");
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		return mapping.findForward("success");
	}
}
