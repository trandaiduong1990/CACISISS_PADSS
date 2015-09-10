package org.transinfo.cacis.action.settings;

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
import org.transinfo.cacis.controller.settings.MCCManager;
import org.transinfo.cacis.dto.settings.MCCMasterDto;
import org.transinfo.cacis.formbean.settings.MCCMasterSearch;

@SuppressWarnings( { "deprecation", "unchecked" })
public class SearchMCCMasterAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
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
		MCCMasterSearch objForm = (MCCMasterSearch) form;
		
		// Dto Creation
		MCCMasterDto objDto = new MCCMasterDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean in SearchMCCMasterAction execute: " + e);
			throw new TPlusException("Could not populate the form bean in SearchMCCMasterAction execute: " + e);
		}
		
		// Action Execution
		MCCManager objManager = new MCCManager();
		Collection mccMasterList = objManager.searchMccMaster(objDto, pageNo);

		if (mccMasterList.size() > 0) {

			request.setAttribute("SEARCHLIST", mccMasterList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

		} else {
			ActionErrors errors = new ActionErrors();
			errors.add("Error", new ActionError("error.nomccmasterfound"));
			saveErrors(request, errors);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}
}
