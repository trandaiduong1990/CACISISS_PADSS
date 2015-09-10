package org.transinfo.cacis.action.disputemanagement;

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
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.disputemanagement.DisputeDocumentsManager;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsSearchDto;
import org.transinfo.cacis.formbean.disputemanagement.DisputeDocumentsSearch;

public class SearchDisputeDocumentsAction extends BaseDispatchAction {

	public ActionForward preList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			DisputeDocumentsSearch objOldForm = (DisputeDocumentsSearch) form;
			DisputeDocumentsSearch objForm = new DisputeDocumentsSearch();
			try {
				BeanUtils.copyProperties(objOldForm, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
			}
			objOldForm.getPreListData();
		} catch (Exception exp) {
			System.out.println("Error converting to form bean: " + exp);
		}
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DisputeDocumentsSearch objForm = (DisputeDocumentsSearch) form;
		int pageNo = 0;
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DisputeDocumentsSearch objForm = (DisputeDocumentsSearch) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) + 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward PREV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DisputeDocumentsSearch objForm = (DisputeDocumentsSearch) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public void commonSearch(int pageNo, DisputeDocumentsSearch objForm,
			HttpServletRequest request) throws TPlusException, Exception {
		DisputeDocumentsSearchDto objDto = new DisputeDocumentsSearchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		DisputeDocumentsManager objManager = new DisputeDocumentsManager();
		Collection DisputeDocuments = objManager.search(objDto, pageNo);
		request.setAttribute("SEARCHLIST", DisputeDocuments);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		System.out.println("DisputeDocumentsSearchAction: execute() successful");
		objForm.getPreListData();
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DisputeDocumentsSearch objOldForm = (DisputeDocumentsSearch) form;
		ActionErrors errors = null;
		System.out.println("DisputeDocumentsSearchAction: change here");
		System.out.println("getReasonCode = "+objOldForm.getReasonCode());
	
		if (objOldForm.getReasonCode().trim().equals("")) {
			System.out.println("error");
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.idnotexist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "search");
			objOldForm.getPreListData();
			return mapping.getInputForward();
		}
		System.out.println("out side error");
		//DisputeDocumentsForm objForm = new DisputeDocumentsForm();
		//objForm.setReasonCode(objOldForm.getReasonCode());
		//objForm.setIssuerId(request.getParameter("issuerId"));
		//objForm.getPreListData();
		//request.setAttribute("ACTION", "change");
		return mapping.findForward("editPage");
	}

}