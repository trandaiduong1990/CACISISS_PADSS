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
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyFeeSetupManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupSearchDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupSearchForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSearchForm;

public class SearchDelinquencyNotificationSetupAction extends BaseAction {
	private Logger logger = Logger
			.getLogger(SearchDelinquencyNotificationSetupAction.class);

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {
		if ((request.getParameter("method") != null)
				&& (((String) request.getParameter("method")).equals("List") || ((String) request
						.getParameter("method")).equals("delete"))) {
			try {
				DelinquencyNotificationSearchForm objForm = (DelinquencyNotificationSearchForm) form;
				DelinquencyNotificationSearchForm newObjForm = new DelinquencyNotificationSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);
				objForm.setIssuerId((String) request.getSession(false)
						.getAttribute("ISSUER"));
				objForm.getPreListData();
				return mapping.findForward("success");
			} catch (Exception ex) {
				logger.error(ex);
				System.out
						.println("Error in Search Branch Action Calling PreList: "
								+ ex);
				throw new TPlusException(
						"Error in Search Branch Action Calling PreList " + ex);
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

			DelinquencyNotificationSearchForm objForm = (DelinquencyNotificationSearchForm) form;
			ActionErrors errors = objForm.validate(mapping, request);
			objForm.setIssuerId((String) request.getSession(false)
					.getAttribute("ISSUER"));

			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}

			// Dto Creation
			DelinquencyNotificationSetupSearchDto objDto = new DelinquencyNotificationSetupSearchDto();

			try {
				BeanUtils.copyProperties(objDto, objForm);
				objDto.setIssuerId((String) request.getSession(false)
						.getAttribute("ISSUER"));
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchDelinquencyNotificationSetupAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchDelinquencyNotificationSetupAction execute method: "
								+ e);
			}

			// Action Execution
			DelinquencyNotificationSetupManager objManager = new DelinquencyNotificationSetupManager();
			Collection notificationList = objManager.search(objDto, pageNo);

			// Success
			request.setAttribute("SEARCHLIST", notificationList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			objForm.getPreListData();
			return mapping.findForward("success");
		}
	}

}