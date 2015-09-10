package org.transinfo.cacis.action.key;

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
import org.transinfo.cacis.controller.key.KeyIndexManager;
import org.transinfo.cacis.dto.key.KeyIndexDto;
import org.transinfo.cacis.dto.key.KeyIndexSearchDto;
import org.transinfo.cacis.formbean.key.KeyIndexForm;
import org.transinfo.cacis.formbean.key.KeyIndexSearchForm;

@SuppressWarnings( { "deprecation", "unchecked" })
public class KeyIndexDispatchAction extends BaseDispatchAction {

	public ActionForward search(ActionMapping mapping, ActionForm form,
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

		KeyIndexSearchForm objForm = (KeyIndexSearchForm) form;
		KeyIndexSearchDto objDto = new KeyIndexSearchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		KeyIndexManager objManager = new KeyIndexManager();

		Collection keyForm = objManager.getKeyIndexList(objDto, pageNo);

		request.setAttribute("SEARCHLIST", keyForm);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		// System.out.println("LIST SIZE"+keyForm.size());
		System.out.println("KeyIndexDispatchAction: get() successful");

		return mapping.findForward("success");
	}

	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addnew method()");

		//ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		KeyIndexForm objNewForm = new KeyIndexForm();
		KeyIndexForm objForm = (KeyIndexForm) form;
		try {
			objNewForm.setIssuerId(objForm.getIssuerId());
			BeanUtils.copyProperties(objForm, objNewForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		objForm.getPreListData();

		System.out.println("Add New Successful");
		// Success

		// resetToken(request);
		return mapping.findForward("change");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: add method()");

		boolean boolAdd = false;
		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		KeyIndexForm objForm = (KeyIndexForm) form;
		objForm.getPreListData();
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		KeyIndexDto objDto = new KeyIndexDto();
		
		String tranxChannel = objForm.getTransactionChannel();
		if(tranxChannel == null){
			tranxChannel = "ONUS";
		}
		
		try {
			BeanUtils.copyProperties(objDto, objForm);

			objDto.id.setIssuerId(objForm.getIssuerId());
			objDto.id.setCardProductId(objForm.getCardProductId());
			objDto.id.setKeyType(objForm.getKeyType());
			objDto.id.setTransactionChannel(tranxChannel);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		KeyIndexManager objManager = new KeyIndexManager();

		// Checking whether the record already exist
		if (!objManager.isRecordExist(objDto)) {
			boolAdd = objManager.add(objDto);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.alreadyexist"));
			saveErrors(request, errors);
			return mapping.getInputForward();
		}

		if (!boolAdd) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("Record Added" + boolAdd);
		// Success

		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dispatchAction update method");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		KeyIndexForm objForm = (KeyIndexForm) form;
		objForm.getPreListData();

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// DTO Creation
		KeyIndexDto objDto = new KeyIndexDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.id.setIssuerId(objForm.getIssuerId());
			objDto.id.setCardProductId(objForm.getCardProductId());
			objDto.id.setKeyType(objForm.getKeyType());
			objDto.id.setTransactionChannel(objForm.getTransactionChannel());
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		KeyIndexManager objManager = new KeyIndexManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			System.out.println("KeyIndexDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("KeyIndexDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("change");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction delete method");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		KeyIndexForm objForm = (KeyIndexForm) form;
		objForm.getPreListData();

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// DTO Creation
		KeyIndexDto objDto = new KeyIndexDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.id.setIssuerId(objForm.getIssuerId());
			objDto.id.setCardProductId(objForm.getCardProductId());
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		KeyIndexManager objManager = new KeyIndexManager();

		boolean boolDlete = objManager.delete(objDto);
		String nextaction = "delete";

		if (!boolDlete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "success";
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
			return mapping.findForward(nextaction);
		}

		System.out.println("Record Delete" + boolDlete);
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		KeyIndexManager objManager = new KeyIndexManager();

		KeyIndexForm objForm = (KeyIndexForm) form;
		objForm.getPreListData();

		// DTO Creation
		KeyIndexDto objDto = new KeyIndexDto();

		System.out.println("Request parameter:===>"
				+ request.getParameter("cardProductId"));
		objDto = objManager
				.get(request.getParameter("issuerId"), request
						.getParameter("cardProductId"), request
						.getParameter("keyType"), request
						.getParameter("transactionChannel"));
		try {
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setIssuerId(objDto.id.getIssuerId());
			objForm.setCardProductId(objDto.id.getCardProductId());
			objForm.setKeyType(objDto.id.getKeyType());
			objForm.setTransactionChannel(objDto.id.getTransactionChannel());
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("change");
	}

}
