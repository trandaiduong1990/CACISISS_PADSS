package org.transinfo.cacis.action.letters;

import java.io.IOException;
import java.util.Collection;

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
import org.transinfo.cacis.controller.letters.LetterTemplateManager;
import org.transinfo.cacis.dto.letters.LetterTemplateSearchDto;
import org.transinfo.cacis.formbean.letters.LetterTemplateSearchForm;

public class SearchLetterTemplateAction extends BaseAction {

	private Logger logger = Logger.getLogger(SearchLetterTemplateAction.class);
	
	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {
		
		if ((request.getParameter("method") != null)
				&& (((String) request.getParameter("method")).equals("List") || ((String) request
						.getParameter("method")).equals("delete"))) {
			try {
				LetterTemplateSearchForm objForm = (LetterTemplateSearchForm) form;
				LetterTemplateSearchForm newObjForm = new LetterTemplateSearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);
				objForm.getPreListData();
				return mapping.findForward("success");
			} catch (Exception ex) {
				logger.error(ex);
				System.out
						.println("Error in Search Letter Template Calling PreList: "
								+ ex);
				throw new TPlusException(
						"Error in Search Letter Template Calling PreList " + ex);
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
			
			LetterTemplateSearchForm objForm = (LetterTemplateSearchForm) form;
			ActionErrors errors = objForm.validate(mapping, request);
			
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			
			// Dto Creation
			LetterTemplateSearchDto objDto = new LetterTemplateSearchDto();
			int totalCount = 0;
			
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				logger.error(e);
				System.out
						.println("Error converting to form bean in SearchLetterTemplateAction execute method: "
								+ e.getMessage());
				throw new TPlusException(
						"Error converting to form bean in SearchLetterTemplateAction execute method: "
								+ e);
			}
			
			// Action Execution
			LetterTemplateManager objManager = new LetterTemplateManager();
			LetterTemplateSearchDto letterTemplateList = objManager.search(objDto, pageNo);
			
			totalCount = objDto.getTotalCount();
			objForm.setTotalCount(String.valueOf(totalCount));
			
			// Success
			if (totalCount > 0) {
				request.setAttribute("SEARCHLIST", objDto.getSearchList());
				request.setAttribute("PAGENO", new Integer(pageNo).toString());
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.nodatafound"));
				saveErrors(request, errors);
	
			}
			objForm.getPreListData();
			return mapping.findForward("success");
		}
	}
}
