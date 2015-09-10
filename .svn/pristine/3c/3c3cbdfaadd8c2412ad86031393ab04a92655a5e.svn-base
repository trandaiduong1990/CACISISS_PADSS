package org.transinfo.cacis.action.applicationforms;

import java.util.Date;

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
import org.transinfo.cacis.controller.applicationforms.AddCardProcessManager;
import org.transinfo.cacis.controller.customerservice.AddProductManager;
import org.transinfo.cacis.controller.customerservice.TransactionEnquiryManager;
import org.transinfo.cacis.dto.applicationforms.AddCardProcessSetupDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.customerservice.AddProductSearchDto;
import org.transinfo.cacis.dto.customerservice.AddProductSetupDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;
import org.transinfo.cacis.formbean.applicationforms.AddCardProcessSetupForm;
import org.transinfo.cacis.formbean.customerservice.AddProductSearchForm;
import org.transinfo.cacis.formbean.customerservice.AddProductSetupForm;
import org.transinfo.cacis.formbean.customerservice.TranxEnquirySetupForm;
import org.transinfo.cacis.formbean.disputemanagement.TransactionLogForm;
import org.transinfo.cacis.report.reportutil.ApplicationStatus;
import org.transinfo.cacis.util.DateUtil;
import org.transinfo.cacis.util.StringUtil;

@SuppressWarnings("deprecation")
public class AddCardProcessDispatchAction extends BaseDispatchAction {

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		AddCardProcessSetupForm objForm = (AddCardProcessSetupForm) form;

		// Dto Creation
		AddCardProcessSetupDto objDto = new AddCardProcessSetupDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			throw new TPlusException(
					"In AddProductAddCardAction executeAction: " + e);
		}

		// Action execution
		AddCardProcessManager objAddProductManager = new AddCardProcessManager();
		objDto = objAddProductManager.getAddProductForm(objDto);
		objForm.setCustomerName(objDto.getCustomerName());
		objForm.setNric(objDto.getNric());
		objForm.setCardProductId(objDto.getCardProductId());
		objForm.setCreditLimit(objDto.getCreditLimit());
		String issuer = (String) request.getSession().getAttribute("ISSUER");
		objForm.setIssuerId(issuer);
		objForm.setCustomerId(objDto.getCustomerId());
		// Success
		saveToken(request);
		objForm.getPreListData();
		request.setAttribute("cycleList", objDto.getCycleList());
		return mapping.findForward("success");
	}

	public ActionForward reject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		AddCardProcessSetupForm objForm = (AddCardProcessSetupForm) form;
		boolean isError = false;

		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);

		// Dto Creation
		AddCardProcessSetupDto objDto = new AddCardProcessSetupDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			// Action execution
			AddCardProcessManager objAddProductManager = new AddCardProcessManager();
			boolean rejectBool = objAddProductManager.reject(objDto);
			if (!rejectBool) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			throw new TPlusException(
					"In AddCardProcessDispatchAction executeAction: " + e);
		}
		saveToken(request);
		objForm.getPreListData();
		request.setAttribute("cycleList", objDto.getCycleList());
		return mapping.findForward("success");
	}

	public ActionForward accept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		AddCardProcessSetupForm objForm = (AddCardProcessSetupForm) form;
		boolean isError = false;
		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);

		// Dto Creation
		AddCardProcessSetupDto objDto = new AddCardProcessSetupDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			// Action execution
			AddCardProcessManager objAddProductManager = new AddCardProcessManager();
			boolean acceptBool = objAddProductManager.accept(objDto);
			if (!acceptBool) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			throw new TPlusException(
					"In AddCardProcessDispatchAction executeAction: " + e);
		}
		saveToken(request);
		objForm.getPreListData();
		request.setAttribute("cycleList", objDto.getCycleList());
		return mapping.findForward("success");
	}
}
