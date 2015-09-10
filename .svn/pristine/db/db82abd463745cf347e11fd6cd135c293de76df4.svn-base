package org.transinfo.cacis.action.disputemanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
import org.transinfo.cacis.controller.disputemanagement.WorkItemManager;
import org.transinfo.cacis.dto.disputemanagement.RequestWorkItemFormDto;
import org.transinfo.cacis.dto.disputemanagement.WorkItemDto;
import org.transinfo.cacis.formbean.disputemanagement.RequestWorkItemForm;
import org.transinfo.cacis.formbean.disputemanagement.WorkItemForm;

public class WorkItemDispatchAction extends BaseDispatchAction {

	/*
	   * this method is used for showing   the ClaimDetails History
	   */
	 public ActionForward workItemInfo(ActionMapping mapping,
	          ActionForm form,
	          HttpServletRequest request,
	          HttpServletResponse response)
	      throws TPlusException,Exception {


		 RequestWorkItemForm objForm = (RequestWorkItemForm)form;
		// DTO Creation
		 RequestWorkItemFormDto objDto = new  RequestWorkItemFormDto();

	         try {

	        	BeanUtils.copyProperties(objDto,objForm);

	        // Action execution
	        WorkItemManager objManager = new WorkItemManager();
	         ArrayList  workItemList = objManager.workItemInfo(objDto);

	          if(workItemList.size()>0){
	        	//Basic Claim Details
	        	  objDto  = (RequestWorkItemFormDto)((ArrayList)workItemList.get(0)).get(0);
	        	  BeanUtils.copyProperties(objForm,objDto);
	           //claim History Details
	            ArrayList historyList = (ArrayList)workItemList.get(1);
	            if(historyList!=null)
	            request.setAttribute("CLAIMHISTORY",historyList);
	          }

	         } catch (Exception e){
	           	  System.out.println("Error converting to form bean in WorkItemDispatchAction in workItemInfo method: " + e.getMessage());
	              throw new TPlusException("Could not populate the form bean in WorkItemDispatchAction  workItemInfo method: " + e);
	            }

	         return mapping.findForward("success");
	    }


	 //Hee Leng
//	 In WorkItemDispatchAction, it do not used Form Validations, because
		// 1.. for add, the document name field can be empty, but it is a mandotory field for documentAdd
		// 2.. for documentAdd, the remarks field can be empty, but it is a mandotory field for add

	public ActionForward add(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws TPlusException, Exception {
			System.out.println("we are in dipatchAction update method");
			WorkItemManager objManager = new WorkItemManager();
			WorkItemDto objDto = new WorkItemDto();
			WorkItemForm objForm = (WorkItemForm) form;
			ActionErrors errors = null;
			boolean boolAdd = false;
			if (objForm.getDocumentNameList().size() == 0
					|| objForm.getRemarks().trim().equals("")) {
				errors = new ActionErrors();
				errors.add("Error",
						new ActionError("errors.required", "Documents Info"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "add");
				return mapping.findForward("success");
			}
			// DTO Creation
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: " + e);
			}

			boolAdd = objManager.add(objDto);

			if (!boolAdd) {
				System.out.println("WorkItemDispatchAction: add record fail");
				errors = new ActionErrors();
				errors.add("Error", new ActionError(" error.addfailed"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "add");
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "cancel");
			}

			System.out.println("WorkItemDispatchAction:add() successful");
			// Success
			// resetToken(request);
			return mapping.findForward("success");
		}

		public ActionForward change(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws TPlusException, Exception {
			System.out.println("in Change");
			WorkItemForm objForm = (WorkItemForm) form;
			System.out.println("Form in Change");
			objForm.setDocumentNameList(new HashMap());
			request.setAttribute("ACTION", "add");
			return mapping.findForward("success");
		}

		public ActionForward documentAdd(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws TPlusException, Exception {
			ActionErrors errors = null;
			boolean blnExist = false;
			String errMsg = "";
			WorkItemDto objDto = new WorkItemDto();
			WorkItemManager objManager = new WorkItemManager();
			WorkItemForm objForm = (WorkItemForm) form;

			if (objForm.getDocumentName().trim().equals("") || objForm.getDocumentName().length() > 12) {
				errors = new ActionErrors();
				if (objForm.getDocumentName().trim().equals(""))
					errMsg = "errors.required";
				if (objForm.getDocumentName().length() > 12)
					errMsg = "errors.maxlength";
				errors.add("Error", new ActionError(errMsg,
						"Document Name", "", "12"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "add");
				return mapping.findForward("success");
			}

			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to dto: " + e);
				throw new TPlusException("Could not populate to the dto: " + e);
			}

			blnExist = objManager.validate(objDto);

			Map DocumentsNameList = new HashMap();
			if (!objForm.getDocumentNameList().isEmpty()) {
				DocumentsNameList = objForm.getDocumentNameList();
				Iterator it = DocumentsNameList.keySet().iterator();
				while (it.hasNext()) {
					Object Key = it.next();
					if (Key.toString().trim().equals(objForm.getDocumentName()))
						blnExist = true;
				}
			}

			if(!blnExist){
				Object[] arrObj = new Object[2];
				arrObj[0] = objForm.getDocumentName();
				arrObj[1] = objForm.getDocumentName();
				DocumentsNameList.put(arrObj[0], arrObj[1]);
				objForm.setDocumentNameList(DocumentsNameList);
				objForm.setDocumentName("");
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("errors.duplicate",
						"Document Name"));
				saveErrors(request, errors);
			}

			request.setAttribute("ACTION", "add");
			return mapping.findForward("success");
		}

		public ActionForward documentRemove(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws TPlusException, Exception {
			System.out.println("we are in dipatchAction delete method");
			ActionErrors errors = null;
			WorkItemForm objForm = (WorkItemForm) form;

			if (objForm.getArlDocuments() == null) {
				errors = new ActionErrors();
				errors.add("Error",
						new ActionError("errors.mask", "Documents List"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "add");
				return mapping.findForward("success");
			}
			Map DocumentsNameList = new HashMap();
			DocumentsNameList = objForm.getDocumentNameList();
			String[] arlDocuments = objForm.getArlDocuments();
			if (arlDocuments != null) {
				for (int i = 0; i < arlDocuments.length; i++) {
					String strMandatoryDoc = (String) arlDocuments[i];
					DocumentsNameList.remove(strMandatoryDoc);
				}
			}
			request.setAttribute("ACTION", "add");
			return mapping.findForward("success");
		}

		public ActionForward cancel(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			WorkItemForm objOldForm = (WorkItemForm) form;
			WorkItemForm objForm = new WorkItemForm();
			try {
				BeanUtils.copyProperties(objOldForm, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
			}
			return mapping.findForward("menuPage");
		}


		public ActionForward documentList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
		{
			try
			{
			 String claimNo = request.getParameter("claimNumber");
			 System.out.println("CLAIMNO="+claimNo);
			 WorkItemManager objManager = new WorkItemManager();
			 Collection workItemRecords = objManager.getDocumentList(claimNo);
			 request.setAttribute("SEARCHLIST",workItemRecords);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
			}

			 return mapping.findForward("success");
		}


}
