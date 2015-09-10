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
import org.transinfo.cacis.controller.settings.CardTypeManager;
import org.transinfo.cacis.dto.settings.CardTypeSearchDto;
import org.transinfo.cacis.formbean.settings.CardTypeSearchForm;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
public class SearchCardTypeAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchCardTypeAction.class);

	@SuppressWarnings( { "unchecked", "deprecation" })
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		if ((request.getParameter("method") != null)
				&& (((String) request.getParameter("method")).equals("List") || ((String) request
						.getParameter("method")).equals("delete"))) {
			try {

				CardTypeSearchForm objForm = (CardTypeSearchForm) form;
				CardTypeSearchForm newObjForm = new CardTypeSearchForm();

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

			CardTypeSearchForm objForm = (CardTypeSearchForm) form;

			ActionErrors errors = objForm.validate(mapping, request);

			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			// Dto Creation
			CardTypeSearchDto objDto = new CardTypeSearchDto();
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchCardTypeAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchCardTypeAction execute method: "
								+ e);
			}

			/*// user activities record into database
			UserActivitiesDto objUserActivitiesDto = new UserActivitiesDto();

			objUserActivitiesDto.setDateTime(new Date());
			objUserActivitiesDto.setIssuerId((String) request.getSession()
					.getAttribute("ISSUER"));
			objUserActivitiesDto.setUserId((String) request.getSession()
					.getAttribute("USERID"));
			objUserActivitiesDto.setActivity(UserActivityData.CARD_TYPE_SEARCH);
			objUserActivitiesDto.setStationIp(InetAddress.getLocalHost()
					.getHostAddress());

			UserActivitiesManager activityManager = new UserActivitiesManager();
			activityManager.add(objUserActivitiesDto);*/

			// end

			// Action Execution
			CardTypeManager objManager = new CardTypeManager();
			Collection issuers = objManager.search(objDto, pageNo);

			request.setAttribute("SEARCHLIST", issuers);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			objForm.getPreListData();
			return mapping.findForward("success");
		}
	}
}