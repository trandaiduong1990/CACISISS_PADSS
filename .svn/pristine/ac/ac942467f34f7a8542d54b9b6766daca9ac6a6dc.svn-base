package org.transinfo.cacis.action.applicationforms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.applicationforms.CardsRenewalManager;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.formbean.applicationforms.CardsRenewalForm;

@SuppressWarnings( { "deprecation", "unchecked" })
public class SearchCardsRenewalAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		CardsRenewalForm objForm = (CardsRenewalForm) form;

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

		try {

			// Dto Creation
			CardsRenewalDto objDto = new CardsRenewalDto();
			// Action Execution
			CardsRenewalManager objManager = new CardsRenewalManager();
			// this is for cardsRenewal process
			if (request.getParameter("method") != null
					&& ((String) request.getParameter("method"))
							.equals("renewalProcess")) {

				BeanUtils.copyProperties(objDto, objForm);

				boolean cardsRenewal = objManager.renewalProcess(objDto);

				if (!cardsRenewal) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.renewalfail"));
					saveErrors(request, errors);
				} else {
					errors = new ActionErrors();
					errors
							.add("Error", new ActionError(
									"error.renewalSuccess"));
					saveErrors(request, errors);
				}
				return mapping.findForward("success");
			} else {
				// this for showing the cards to renewal list
				Collection renewalList = objManager.renewalList(pageNo);
				if (renewalList.size() > 0) {
					request.setAttribute("SEARCHLIST", renewalList);
					request.setAttribute("PAGENO", new Integer(pageNo)
							.toString());
				} else {
					// if there is no cards to renewal to show the message
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.nocardfound"));
					saveErrors(request, errors);
				}
			}
		} catch (Exception e) {
			System.out
					.println("Error in SearchCardRenewalAction renewalList method: "
							+ e.getMessage());
			throw new TPlusException(
					"Error  in SearchCardRenewalAction renewalList method: "
							+ e);
		}

		return mapping.findForward("success");
	}

}