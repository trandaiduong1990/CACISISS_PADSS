package org.transinfo.cacis.action.cardproduction;

import java.io.File;
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
import org.transinfo.cacis.controller.cardproduction.CardEmbossingManager;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingSearchDto;
import org.transinfo.cacis.formbean.cardproduction.RenewalCardEmbossingSearch;
import org.transinfo.cacis.util.AdminParamsLoad;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
@SuppressWarnings({ "deprecation", "unchecked" })
public class SearchRenewalCardEmbossingAction extends BaseAction {

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
		RenewalCardEmbossingSearch objForm = (RenewalCardEmbossingSearch) form;
		
		HttpSession session = request.getSession(true);
		String branchId = (String)session.getAttribute("LOGEDUSERBRANCH");
		objForm.setBranchId(branchId);
		
		// Dto Creation
		CardEmbossingSearchDto objDto = new CardEmbossingSearchDto();

		try {
			objForm.setIssuerId(issuer);
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in SearchCardEmbossingAction execute: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in SearchCardEmbossingAction execute : "
							+ e);
		}
		// get all the pre list data
		objForm.getPreListData();
		// check for emboss file location exists
		String embossFileLoc = AdminParamsLoad.embossFilePath;
		boolean isEmbossFolderExists = true;

		ActionErrors errors = new ActionErrors();
		
		try {
			
			File file = new File(embossFileLoc);
			boolean exists = file.exists();
			if (!exists) {
				errors.add("Error", new ActionError("error.embosssfilelocationnot"));
				saveErrors(request, errors);
				isEmbossFolderExists = false;
			} else {
				if(file.isDirectory()){
					isEmbossFolderExists = true;
				}else{
					errors.add("Error", new ActionError("error.embosssfilelocationisnotdirectory"));
					saveErrors(request, errors);
					isEmbossFolderExists = false;
				}
			}
		} catch (Exception e) {
			errors.add("Error", new ActionError("error.embosssfilelocationnot"));
			saveErrors(request, errors);
			isEmbossFolderExists = false;
		}
		
		if(!isEmbossFolderExists){
			return mapping.findForward("pathfailed");
		}
		
		// Action execution
		CardEmbossingManager objManager = new CardEmbossingManager();
		Collection cardEmbossingList = objManager.searchRenewal(objDto, pageNo);

		if (cardEmbossingList.size() > 0) {
			request.setAttribute("SEARCHLIST", cardEmbossingList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

		} else {
			errors.add("Error", new ActionError("error.nodatafound"));
			saveErrors(request, errors);

		}
		// Success
		saveToken(request);
		return mapping.findForward("success");
	}
}
