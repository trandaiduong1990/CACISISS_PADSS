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
import org.transinfo.cacis.controller.customerservice.TransactionEnquiryManager;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeManualReconDto;
import org.transinfo.cacis.formbean.customerservice.TranxEnquirySetupForm;
import org.transinfo.cacis.formbean.disputemanagement.TransactionLogForm;
import org.transinfo.cacis.util.DateUtil;
import org.transinfo.cacis.util.StringUtil;

@SuppressWarnings("deprecation")
public class TransactionEnquiryDispatchAction extends BaseDispatchAction {

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		TranxEnquirySetupForm objForm = (TranxEnquirySetupForm) form;

		// Action execution
		TransactionEnquiryManager objTransactionEnquiryManager = new TransactionEnquiryManager();

		// object of transaction form
		TransactionLogForm objTransactionLogForm = new TransactionLogForm();

		try {

			String tranxId = request.getParameter("tranxId");
			TransactionLogDto objTransactionLogDto = objTransactionEnquiryManager.getTransactionDto(tranxId);

			BeanUtils.copyProperties(objTransactionLogForm, objTransactionLogDto);
			
			try{
				
				String cardNo = String.valueOf(objTransactionLogDto.getCardNumber());
				if(!"470532".equals(cardNo.subSequence(0, 6))){
					objTransactionLogForm.setRevertEnable("Y");
				}
				
				String merchant = objTransactionLogDto.getMerchantName();
				if(merchant != null && !"".equals(merchant) && merchant.length() >= 40){
					objTransactionLogForm.setMerchantName(merchant.substring(0, 25));
					objTransactionLogForm.setMerchantloc(merchant.substring(25, 38));
					objTransactionLogForm.setMerchantcountry(merchant.substring(38, 40));
				}
			}catch (Exception e) {
				objTransactionLogForm.setMerchantName("");
				objTransactionLogForm.setMerchantloc("");
				objTransactionLogForm.setMerchantcountry("");
			}
			
			objForm.setTranxlogForm(objTransactionLogForm);
			
			// check the recon status and get the required details to display
			String recon = StringUtil.confirmString(objTransactionLogDto.getRecon());
			if(!"".equals(recon) && "M".equals(recon)){
				DisputeManualReconDto objDisputeManualReconDto = objTransactionEnquiryManager.getDisputeManualReconDto(tranxId);
				if(objDisputeManualReconDto != null){
					objForm.setRemarks(objDisputeManualReconDto.getRemarks());
					objForm.setUpdatedBy(objDisputeManualReconDto.getUpdatedBy());
					objForm.setUpdatedDate(DateUtil.convertTranxDateToDateString(objDisputeManualReconDto.getUpdatedDate()));
				}
			}

		} catch (Exception e) {
			throw new TPlusException(
					"In TransactionEnquiryDispatchAction view method: " + e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}
	
	public ActionForward manualRecon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		TranxEnquirySetupForm objForm = (TranxEnquirySetupForm) form;
		
		boolean isError = false;
		
		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			isError = true;
		}

		// Action execution
		TransactionEnquiryManager objTransactionEnquiryManager = new TransactionEnquiryManager();

		// object of transaction form
		TransactionLogForm objTransactionLogForm = new TransactionLogForm();

		try {

			String tranxId = request.getParameter("tranxId");
			TransactionLogDto objTransactionLogDto = objTransactionEnquiryManager
					.getTransactionDto(tranxId);

			BeanUtils.copyProperties(objTransactionLogForm,
					objTransactionLogDto);
			objForm.setTranxlogForm(objTransactionLogForm);
			
			String cardNo = String.valueOf(objTransactionLogDto.getCardNumber());
			if(!"470532".equals(cardNo.subSequence(0, 6))){
				objTransactionLogForm.setRevertEnable("Y");
			}
			
			String merchant = objTransactionLogDto.getMerchantName();
			if(merchant != null && !"".equals(merchant) && merchant.length() >= 40){
				objTransactionLogForm.setMerchantName(merchant.substring(0, 25));
				objTransactionLogForm.setMerchantloc(merchant.substring(25, 38));
				objTransactionLogForm.setMerchantcountry(merchant.substring(38, 40));
			}
			
			if(!isError){
				// do the manual recon

				String userID = (String) request.getSession(false).getAttribute("USERID");
				String remarks = objForm.getRemarks();
				
				DisputeManualReconDto objDisputeManualReconDto = new DisputeManualReconDto();
				objDisputeManualReconDto.setTranxlogId(Long.valueOf(tranxId).longValue());
				objDisputeManualReconDto.setRemarks(remarks);
				objDisputeManualReconDto.setUpdatedBy(userID);
				objDisputeManualReconDto.setUpdatedDate(new Date());
				

				boolean insertRes = objTransactionEnquiryManager.saveManualRecon(objDisputeManualReconDto);
				
				if (!insertRes) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addfailed"));
					saveErrors(request, errors);
				} else {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addSuccess"));
					saveErrors(request, errors);
					
					DisputeManualReconDto objManualReconDto = objTransactionEnquiryManager.getDisputeManualReconDto(tranxId);
					if(objDisputeManualReconDto != null){
						objForm.setRemarks(objManualReconDto.getRemarks());
						objForm.setUpdatedBy(objManualReconDto.getUpdatedBy());
						objForm.setUpdatedDate(DateUtil.convertTranxDateToDateString(objManualReconDto.getUpdatedDate()));
					}
					
					objTransactionLogForm.setRecon("M");
					objForm.setTranxlogForm(objTransactionLogForm);
				}
			}

		} catch (Exception e) {
			throw new TPlusException(
					"In TransactionEnquiryDispatchAction view method: " + e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}

	public ActionForward TranxRevert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		TranxEnquirySetupForm objForm = (TranxEnquirySetupForm) form;
		
		boolean isError = false;
		
		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			isError = true;
		}

		// Action execution
		TransactionEnquiryManager objTransactionEnquiryManager = new TransactionEnquiryManager();

		// object of transaction form
		TransactionLogForm objTransactionLogForm = new TransactionLogForm();

		try {

			String tranxId = request.getParameter("tranxId");
			TransactionLogDto objTransactionLogDto = objTransactionEnquiryManager.getTransactionDto(tranxId);

			BeanUtils.copyProperties(objTransactionLogForm,objTransactionLogDto);
			
			String cardNo = String.valueOf(objTransactionLogDto.getCardNumber());
			if(!"470532".equals(cardNo.subSequence(0, 6))){
				objTransactionLogForm.setRevertEnable("Y");
			}
			
			String merchant = objTransactionLogDto.getMerchantName();
			if(merchant != null && !"".equals(merchant) && merchant.length() >= 40){
				objTransactionLogForm.setMerchantName(merchant.substring(0, 25));
				objTransactionLogForm.setMerchantloc(merchant.substring(25, 38));
				objTransactionLogForm.setMerchantcountry(merchant.substring(38, 40));
			}
			
			if(!isError){
				// do the manual recon

				String userID = (String) request.getSession(false).getAttribute("USERID");
				String remarks = objForm.getRemarks();				

				boolean insertRes = objTransactionEnquiryManager.TranxRevert(tranxId, remarks, userID);
				
				if (!insertRes) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addfailed"));
					saveErrors(request, errors);
				} else {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.moneyrevert"));
					saveErrors(request, errors);
					objTransactionLogForm.setDeleted("G");
				}
			}

			objForm.setTranxlogForm(objTransactionLogForm);

		} catch (Exception e) {
			throw new TPlusException(
					"In TransactionEnquiryDispatchAction view method: " + e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}

}
