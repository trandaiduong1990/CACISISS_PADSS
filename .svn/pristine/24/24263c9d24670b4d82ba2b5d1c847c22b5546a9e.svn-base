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
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.controller.disputemanagement.DisputeDocumentsManager;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsDto.Id;
import org.transinfo.cacis.formbean.disputemanagement.DisputeDocumentsForm;

public class DisputeDocumentsDispatchAction extends BaseDispatchAction {

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DisputeDocumentsForm objForm = (DisputeDocumentsForm) form;
		objForm.getPreListData();
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	public ActionForward mandatoryAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		DisputeDocumentsManager objManager = new DisputeDocumentsManager();
		ActionErrors errors = null;
		DisputeDocumentsForm objForm = (DisputeDocumentsForm) form;
		if (objForm.getMandatoryDocumentName().trim().equals("")){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("errors.required", "Mandatory Document Name"));
			saveErrors(request, errors);
			objForm.getPreListData();
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}
			
		objForm.setDocumentType("M");
		objForm.setDocumentName(objForm.getMandatoryDocumentName());
		// DTO Creation
		DisputeDocumentsDto objDto = new DisputeDocumentsDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		Id MandatoryInfo = new Id();
		MandatoryInfo.setIssuerId(objForm.getIssuerId());
		MandatoryInfo.setReasonCode(Integer.parseInt(objForm.getReasonCode()));
		MandatoryInfo.setDocumentId(IdsGenartion.GenDocumentId());
		objDto.setId(MandatoryInfo);
		boolean boolAdd = false;
		
		boolAdd = objManager.add(objDto);
		if (!boolAdd) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}
		// Success
		saveToken(request);
		objForm.getPreListData();
		objForm.setMandatoryDocumentName("");
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	public ActionForward mandatoryRemove(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {
		System.out.println("we are in dipatchAction delete method");
		ActionErrors errors = null;
		int deleteCount = 0;
		boolean blnDelete = false;

		DisputeDocumentsForm objForm = (DisputeDocumentsForm) form;
		// DTO Creation
		DisputeDocumentsDto objDto = new DisputeDocumentsDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		DisputeDocumentsManager objManager = new DisputeDocumentsManager();
		if (objForm.getArlMandatoryDocuments() == null){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("errors.mask", "Mandatory Documents"));
			saveErrors(request, errors);
			objForm.getPreListData();
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}
		String[] arlMandatoryDocuments = objForm.getArlMandatoryDocuments();
		if (arlMandatoryDocuments != null) {
			for (int i = 0; i < arlMandatoryDocuments.length; i++) {
				String strMandatoryDoc = (String) arlMandatoryDocuments[i];
				Id MandatoryInfo = new Id();
				MandatoryInfo.setDocumentId(strMandatoryDoc);
				MandatoryInfo.setIssuerId(objForm.getIssuerId());
				MandatoryInfo.setReasonCode(Integer.parseInt(objForm.getReasonCode()));
				objDto.setId(MandatoryInfo);
				blnDelete = objManager.delete(objDto);
				if (blnDelete) {
					deleteCount = deleteCount + 1;
				}
			}
		}
		if (deleteCount == arlMandatoryDocuments.length)
			blnDelete = true;
		else
			blnDelete = false;

		if (!blnDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
		}
		objForm.getPreListData();
		return mapping.findForward("success");
	}

	public ActionForward nonMandatoryAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {
		ActionErrors errors = null;
		DisputeDocumentsManager objManager = new DisputeDocumentsManager();
		DisputeDocumentsForm objForm = (DisputeDocumentsForm) form;
		if (objForm.getNonMandatoryDocumentName().trim().equals("")){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("errors.required", "Non Mandatory Document Name"));
			saveErrors(request, errors);
			objForm.getPreListData();
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}
		objForm.setDocumentType("N");
		objForm.setDocumentName(objForm.getNonMandatoryDocumentName());
		// DTO Creation
		DisputeDocumentsDto objDto = new DisputeDocumentsDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		Id MandatoryInfo = new Id();
		MandatoryInfo.setIssuerId(objForm.getIssuerId());
		MandatoryInfo.setReasonCode(Integer.parseInt(objForm.getReasonCode()));
		MandatoryInfo.setDocumentId(IdsGenartion.GenDocumentId());
		objDto.setId(MandatoryInfo);
		boolean boolAdd = false;
		boolAdd = objManager.add(objDto);
		if (!boolAdd) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}
		// Success
		saveToken(request);
		objForm.getPreListData();
		objForm.setNonMandatoryDocumentName("");
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	public ActionForward nonMandatoryRemove(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {
		System.out.println("we are in dipatchAction delete method");
		ActionErrors errors = null;
		int deleteCount = 0;
		boolean blnDelete = false;

		DisputeDocumentsForm objForm = (DisputeDocumentsForm) form;
		// DTO Creation
		DisputeDocumentsDto objDto = new DisputeDocumentsDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		DisputeDocumentsManager objManager = new DisputeDocumentsManager();
		if (objForm.getArlNonMandatoryDocuments() == null){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("errors.mask", "Non Mandatory Documents"));
			saveErrors(request, errors);
			objForm.getPreListData();
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}
		String[] arlMandatoryDocuments = objForm.getArlNonMandatoryDocuments();
		if (arlMandatoryDocuments != null) {
			for (int i = 0; i < arlMandatoryDocuments.length; i++) {
				String strMandatoryDoc = (String) arlMandatoryDocuments[i];
				Id MandatoryInfo = new Id();
				MandatoryInfo.setDocumentId(strMandatoryDoc);
				MandatoryInfo.setIssuerId(objForm.getIssuerId());
				MandatoryInfo.setReasonCode(Integer.parseInt(objForm.getReasonCode()));
				objDto.setId(MandatoryInfo);
				blnDelete = objManager.delete(objDto);
				if (blnDelete) {
					deleteCount = deleteCount + 1;
				}
			}
		}
		if (deleteCount == arlMandatoryDocuments.length)
			blnDelete = true;
		else
			blnDelete = false;

		if (!blnDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
		}
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DisputeDocumentsForm objOldForm = (DisputeDocumentsForm) form;
		DisputeDocumentsForm objForm = new DisputeDocumentsForm();
		try {
			BeanUtils.copyProperties(objOldForm, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
		}
		return mapping.findForward("listPage");
	}
}
