package org.transinfo.cacis.action.settings;

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
import org.transinfo.cacis.controller.settings.EMVProfileManager;
import org.transinfo.cacis.dto.settings.EMVProfileSearchDto;
import org.transinfo.cacis.formbean.settings.EMVProfileSearchForm;

/**
 * SearchEMVProfileAction
 * 
 * @author hoang-vu
 * 
 */
public class SearchEMVProfileAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchEMVProfileAction.class);
	
	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {
		
		if ((request.getParameter("method") != null)
				&& (((String) request.getParameter("method")).equals("List") || ((String) request
						.getParameter("method")).equals("delete"))) {
			try {
				EMVProfileSearchForm objForm = (EMVProfileSearchForm) form;
				EMVProfileSearchForm newObjForm = new EMVProfileSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);
				objForm.getPreListData();
				objForm.setScreen("list");
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
			
			EMVProfileSearchForm objForm = (EMVProfileSearchForm) form;
			ActionErrors errors = objForm.validate(mapping, request);
			
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			
			// Dto Creation
			EMVProfileSearchDto objDto = new EMVProfileSearchDto();
			
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchEMVProfileAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchEMVProfileAction execute method: "
								+ e);
			}
			
			// Action Execution
			EMVProfileManager objManager = new EMVProfileManager();
			Collection emvProfiles = objManager.search(objDto, pageNo);
			
			// Success
			request.setAttribute("SEARCHLIST", emvProfiles);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			objForm.getPreListData();
			return mapping.findForward("success");
		}
	}

}
