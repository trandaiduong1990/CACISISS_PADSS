package org.transinfo.cacis.action.disputemanagement;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.disputemanagement.DocumentUploadManager;
import org.transinfo.cacis.dto.disputemanagement.DocumentUploadSearchDto;
import org.transinfo.cacis.formbean.disputemanagement.DocumentUploadSearchForm;

public class SearchDocumentUploadAction extends BaseDispatchAction {

	public ActionForward preList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			DocumentUploadSearchForm objOldForm = (DocumentUploadSearchForm) form;
			DocumentUploadSearchForm objForm = new DocumentUploadSearchForm();
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
		DocumentUploadSearchForm objForm = (DocumentUploadSearchForm) form;
		int pageNo = 0;
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DocumentUploadSearchForm objForm = (DocumentUploadSearchForm) form;
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
		DocumentUploadSearchForm objForm = (DocumentUploadSearchForm) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public void commonSearch(int pageNo, DocumentUploadSearchForm objForm,
			HttpServletRequest request) throws TPlusException, Exception {
		DocumentUploadSearchDto objDto = new DocumentUploadSearchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		DocumentUploadManager objManager = new DocumentUploadManager();
		Collection DocumentUpload = objManager.search(objDto, pageNo);
		request.setAttribute("SEARCHLIST", DocumentUpload);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		System.out.println("DocumentUploadSearchAction: execute() successful");
		objForm.getPreListData();
	}
}