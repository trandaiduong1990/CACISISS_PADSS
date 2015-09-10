package org.transinfo.cacis.action.disputemanagement;

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
import org.transinfo.cacis.controller.disputemanagement.DisputeManagementManager;
import org.transinfo.cacis.dto.disputemanagement.DisputeNotReconsiledTranxSearchDto;
import org.transinfo.cacis.formbean.disputemanagement.DisputeNotReconsiledTranxSearchForm;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
@SuppressWarnings( { "deprecation" })
public class DisputeNotReconsiledTransactionSearchAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		
		DisputeNotReconsiledTranxSearchForm objForm = (DisputeNotReconsiledTranxSearchForm) form;
		
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		
		int pageNo = 0;
		
		String pageNumber = objForm.getPageNo();
		try{
			pageNo = Integer.valueOf(pageNumber);
		}catch (Exception e) {
			pageNo = 0;
		}

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
		
		// Dto Creation
		DisputeNotReconsiledTranxSearchDto objDto = new DisputeNotReconsiledTranxSearchDto();

		try {
			
			BeanUtils.copyProperties(objDto, objForm);
			
		} catch (Exception e) {
			throw new TPlusException(
					"In DisputeNotReconsiledTransactionSearchAction executeAction: " + e);
		}

		// Action execution
		DisputeManagementManager objManager = new DisputeManagementManager();
		objDto = objManager.searchNotReconsiledTranx(objDto, pageNo);
		
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
		
		// Success
		saveToken(request);
		return mapping.findForward("success");
	}
}
