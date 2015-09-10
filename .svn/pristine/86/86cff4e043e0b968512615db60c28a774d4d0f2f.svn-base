package org.transinfo.cacis.action.customerservice;

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
import org.transinfo.cacis.controller.customerservice.AddProductManager;
import org.transinfo.cacis.controller.customerservice.TransactionEnquiryManager;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.customerservice.AddProductSearchDto;
import org.transinfo.cacis.dto.customerservice.AddProductSetupDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;
import org.transinfo.cacis.formbean.customerservice.AddProductSearchForm;
import org.transinfo.cacis.formbean.customerservice.AddProductSetupForm;
import org.transinfo.cacis.formbean.customerservice.TranxEnquirySetupForm;
import org.transinfo.cacis.formbean.disputemanagement.TransactionLogForm;
import org.transinfo.cacis.report.reportutil.ApplicationStatus;
import org.transinfo.cacis.util.DateUtil;
import org.transinfo.cacis.util.StringUtil;

@SuppressWarnings("deprecation")
public class AddProductDispatchAction extends BaseDispatchAction {

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		AddProductSetupForm objForm = (AddProductSetupForm) form;

		// Dto Creation
		AddProductSetupDto objDto = new AddProductSetupDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			throw new TPlusException(
					"In AddProductAddCardAction executeAction: " + e);
		}

		// Action execution
		String customerId = request.getParameter("customerId");
		AddProductManager objAddProductManager = new AddProductManager();
		objDto = objAddProductManager.get(objDto, customerId);
		request.setAttribute("SEARCHLIST", objDto.getSearchList());
		objForm.setCustomerName(objDto.getCustomerName());
		objForm.setNric(objDto.getNric());
		objForm.setCustomerId(customerId);
		objForm.setCreditLimit(objDto.getCreditLimit());
		objForm.setCardProductList(objDto.getCardProductList());
		objForm.setAccountIdAndCardProductIdList(objDto.getAccountIdAndCardProductIdList());
		// Success
		saveToken(request);
		return mapping.findForward("success");
	}

	public ActionForward addProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		AddProductSetupForm objForm = (AddProductSetupForm) form;
		boolean isError = false;

		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);

		// Dto Creation
		AddProductSetupDto objDto = new AddProductSetupDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
			// Action execution
				AddProductManager objAddProductManager = new AddProductManager();
				String userID = (String) request.getSession(false).getAttribute(
						"USERID");
				boolean checkInsert = objAddProductManager.addProduct(objDto,
						userID);
				if (!checkInsert) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addfailed"));
					saveErrors(request, errors);
				} else {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addSuccess"));
					saveErrors(request, errors);
					objDto = objAddProductManager.get(objDto, objDto.getCustomerId());
					objForm.setCardProductList(objDto.getCardProductList());
					objForm.setAccountIdAndCardProductIdList(objDto.getAccountIdAndCardProductIdList());
					request.setAttribute("SEARCHLIST", objDto.getSearchList());
				}
		} catch (Exception e) {
			throw new TPlusException(
					"In AddProductDispatchAction addProduct method: " + e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}
}
