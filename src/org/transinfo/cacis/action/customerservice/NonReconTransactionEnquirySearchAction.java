package org.transinfo.cacis.action.customerservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.customerservice.NonReconTransactionEnquiryManager;
import org.transinfo.cacis.dto.customerservice.NonReconTranxEnquirySearchDto;
import org.transinfo.cacis.formbean.customerservice.NonReconTranxEnquirySearchForm;
import org.transinfo.cacis.util.StringUtil;

/**
 * WORKSHOP W06_CONTROLLER Extend from the
 * org.apache.struts.actions.DispatchAction class
 *********/
@SuppressWarnings( { "deprecation" })
public class NonReconTransactionEnquirySearchAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		if ((request.getParameter("method") != null)
				&& ((String) request.getParameter("method")).equals("List")) {
			
			try {

				NonReconTranxEnquirySearchForm objForm = (NonReconTranxEnquirySearchForm) form;
				NonReconTranxEnquirySearchForm newObjForm = new NonReconTranxEnquirySearchForm();

				BeanUtils.copyProperties(objForm, newObjForm);

				objForm.getPreListData();
				return mapping.findForward("success");

			} catch (Exception exp) {
				throw new TPlusException(
						"Error in TransactionEnquirySearchAction " + exp);
			}
			
		}else {
		
			ActionErrors errors = null;
			
			NonReconTranxEnquirySearchForm objForm = (NonReconTranxEnquirySearchForm) form;
			
			errors = objForm.validate(mapping, request);
	
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				return mapping.getInputForward();
			}
			
			int pageNo = 0;
			
			String pageNumber = objForm.getPageNo();
			try{
				pageNo = Integer.valueOf(pageNumber);
			}catch (Exception e) {
				pageNo = 0;
			}
	
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
			
			int totCount = 0;
			
			// Dto Creation
			NonReconTranxEnquirySearchDto objDto = new NonReconTranxEnquirySearchDto();
	
			try {
				
				// start date & end date validations
				String startDate = objForm.getStartDate();
				String endDate = objForm.getEndDate();
				
				if(!"".equals(StringUtil.confirmString(startDate)) && "".equals(StringUtil.confirmString(endDate))){
					objForm.setEndDate(startDate);
				}
				
				if("".equals(StringUtil.confirmString(startDate)) && !"".equals(StringUtil.confirmString(endDate))){
					objForm.setStartDate(endDate);
				}
				
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				throw new TPlusException(
						"In NonReconTransactionEnquirySearchAction executeAction: " + e);
			}
	
			// Action execution
			NonReconTransactionEnquiryManager objTransactionEnquiryManager = new NonReconTransactionEnquiryManager();
			objDto = objTransactionEnquiryManager.search(objDto, pageNo);
			
			totCount = objDto.getTotalCount();
			objForm.setTotalCount(String.valueOf(totCount));
	
			if (totCount > 0) {
				request.setAttribute("SEARCHLIST", objDto.getSearchList());
				request.setAttribute("PAGENO", new Integer(pageNo).toString());
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.nodatafound"));
				saveErrors(request, errors);
	
			}
			
			// Success
			objForm.getPreListData();
			saveToken(request);
			return mapping.findForward("success");
		}
		
	}
}
