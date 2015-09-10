package org.transinfo.cacis.action.disputemanagement;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.disputemanagement.DocumentUploadManager;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.disputemanagement.DocumentUploadDto;
import org.transinfo.cacis.dto.disputemanagement.DocumentUploadSearchDto;
import org.transinfo.cacis.formbean.common.DateForm;
import org.transinfo.cacis.formbean.disputemanagement.DocumentUploadForm;
import org.transinfo.cacis.formbean.disputemanagement.DocumentUploadSearchForm;
import org.transinfo.cacis.util.DateUtil;

public class DocumentUploadDispatchAction extends BaseDispatchAction {
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DocumentUploadForm objForm = (DocumentUploadForm) form;
		DocumentUploadDto objDto = new DocumentUploadDto();
		int pageNo = 0;
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		DocumentUploadManager objManager = new DocumentUploadManager();
		Collection DocumentUploadGet = objManager.get(objDto, pageNo);
		request.setAttribute("SEARCHLIST", DocumentUploadGet);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		System.out
				.println("DocumentUploadDispatchAction: execute() successful");
		objForm.getPreListData();
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DocumentUploadForm objOldForm = (DocumentUploadForm) form;
		DocumentUploadForm objForm = new DocumentUploadForm();
		try {
			BeanUtils.copyProperties(objOldForm, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
		}
		return mapping.findForward("listPage");
	}

	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DocumentUploadForm objForm = (DocumentUploadForm) form;
		objForm.setLastUpdatedDateForm(DateForm.todayDateForm()); 
		DocumentUploadDto objDto = new DocumentUploadDto();
		ActionErrors errors = null;
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		objDto.setDocsUploadedDate(DateUtil.convertDateStringToDate(objForm.getStrUploadedDate()));
		DocumentUploadManager objManager = new DocumentUploadManager();

		boolean success = objManager.remove(objDto);
		if (!success) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
		}
		//objForm.getPreListData();
		request.setAttribute("ACTION", "update");
		return mapping.findForward("successAction");
	}

	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DocumentUploadForm objForm = (DocumentUploadForm) form;
		objForm.setDocsUploadedDateForm(DateForm.todayDateForm());
		objForm.setLastUpdatedDateForm(DateForm.todayDateForm());
		ActionErrors errors = null;
		if (objForm.getUploadFile() == null
				|| objForm.getSelectedDocumentName().trim().equals("")) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("errors.required",
					"Upload Document Information"));
			saveErrors(request, errors);
			objForm.getPreListData();
			request.setAttribute("ACTION", "update");
			return mapping.findForward("successAction");
		}
		objForm.setDocumentId(objForm.getSelectedDocumentName());
		DocumentUploadDto objDto = new DocumentUploadDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		DocumentUploadManager objManager = new DocumentUploadManager();
		// Process the FormFile
		FormFile uploadFile = objForm.getUploadFile();
		String contentType = uploadFile.getContentType();
		String fileName = uploadFile.getFileName();
		int fileSize = uploadFile.getFileSize();
		System.out.println("contentType: " + contentType);
		System.out.println("File Name: " + fileName);
		System.out.println("File Size: " + fileSize);

		boolean success = objManager.upload(objDto);
		if (!success) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}
		//objForm.getPreListData();
		request.setAttribute("ACTION", "update");
		return mapping.findForward("successAction");
	}
	
	public ActionForward open(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DocumentUploadForm objForm = (DocumentUploadForm) form;
		objForm.getStrUploadedDate();
		try {
			Runtime runTime = Runtime.getRuntime();
			System.out.println(objForm.getClaimNumber());
			System.out.println(objForm.getStrUploadedDate());
			System.out.println(objForm.getDocumentName());
			System.out.println("==================================");
			System.out.println(request.getParameter("strUploadedDate"));
			System.out.println(request.getParameter("documentName"));
			
			Process process = null;
			// Date uploadDate = DateUtil.convertDateStringToDate(request.getParameter("strUploadedDate"));
			Date uploadDate = DateUtil.convertDateStringToDate(objForm.getStrUploadedDate());
			String strUploadDate = new SimpleDateFormat("ddMMyy").format(uploadDate);
			String command = BaseDAO.UPLOAD_DOCUMENTS_PATH + objForm.getClaimNumber() + "\\" + strUploadDate + "\\request\\" + objForm.getDocumentName();
			process = runTime.exec("cmd /C "+command);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("ACTION", "update");
		return mapping.findForward("successAction");
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
