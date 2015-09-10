package org.transinfo.cacis.action.cardproduction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.cardproduction.PinPrintingManager;
import org.transinfo.cacis.dto.cardproduction.PinPrintingSearchDto;
import org.transinfo.cacis.formbean.cardproduction.PinPrintingSearch;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
@SuppressWarnings( { "unchecked", "deprecation" })
public class SearchPinPrintingAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		String issuer = (String)request.getSession().getAttribute("ISSUER");
		
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
		// form validation
		PinPrintingSearch objForm = (PinPrintingSearch) form;

		HttpSession session = request.getSession(true);
		String branchId = (String)session.getAttribute("LOGEDUSERBRANCH");
		objForm.setBranchId(branchId);

		// Dto Creation
		PinPrintingSearchDto objDto = new PinPrintingSearchDto();
		try {
			objForm.setIssuerId(issuer);
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in SearchPinPrintingAction execute: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in SearchPinPrintingAction execute: "
							+ e);
		}
		// Action execution
		PinPrintingManager objManager = new PinPrintingManager();
		Collection cardPrintingList = objManager.search(objDto, pageNo);

		if (cardPrintingList.size() > 0) {
			request.setAttribute("SEARCHLIST", cardPrintingList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nodatafound"));
			saveErrors(request, errors);

		}
		// Success
		saveToken(request);
		objForm.getPreListData();
		return mapping.findForward("success");
	}
}
