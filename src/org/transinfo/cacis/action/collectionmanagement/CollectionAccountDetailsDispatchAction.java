package org.transinfo.cacis.action.collectionmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAccountDetailsManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAccountDetailsManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAccountDetailsManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAccountDetailsManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupDto;
import org.transinfo.cacis.formbean.collectionmanagement.AgeingAction;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAccountDetailsSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAccountDetailsSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAccountDetailsSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAccountDetailsSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSetupForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class CollectionAccountDetailsDispatchAction extends
		BaseDispatchAction {
	private Logger logger = Logger
			.getLogger(CollectionAccountDetailsDispatchAction.class);

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CollectionAccountDetailsManager objManager = new CollectionAccountDetailsManager();
		CollectionAccountDetailsSetupForm objForm = (CollectionAccountDetailsSetupForm) form;

		try {
			CollectionDto objDto = objManager.getCollection(request.getParameter("colectId"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
			objForm.setCustomerName(request.getParameter("customerName"));
			objForm.setCreditLimit(request.getParameter("creditLimit"));
			objForm.setDueAmt(request.getParameter("dueAmt"));
			if(objDto.getColectRef().equals("B")) {
				objForm.setCurrentCollector2(objDto.getCurrentCollector());
			} else {
				objForm.setCurrentCollector1(objDto.getCurrentCollector());
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			objForm.setDateAssigned(dateFormat.format(objDto.getDateAssigned()));
			objForm.setWriteOffDate(dateFormat.format(objDto.getWriteOffDate()));
			objForm.setLastRecoveryDate(dateFormat.format(objDto.getLastRecoveryDate()));
			objForm.setRecovedFullDate(dateFormat.format(objDto.getRecovedFullDate()));
			objForm.getPreListData();
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAccountDetailsDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAccountDetailsDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for update the
	 * CollectionAgeingDispatchAction
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// Form Validations
		CollectionAccountDetailsSetupForm objForm = (CollectionAccountDetailsSetupForm) form;
		objForm.getPreListData();
		
		// Action Execution
		CollectionAccountDetailsManager objManager = new CollectionAccountDetailsManager();
		CollectionDto objDto = new CollectionDto();
		
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		
		
		try {
			objDto = objManager.getCollection(objForm.getColectId());
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
			objDto.setColectRef(objForm.getColectRef());
			if(objForm.getColectRef().equals("A")) {
				objDto.setCurrentCollector(objForm.getCurrentCollector1());
			} else {
				objDto.setCurrentCollector(objForm.getCurrentCollector2());
			}
			//validate
			if((null==objForm.getCurrentCollector1()|| objForm.getCurrentCollector1().equals("")) 
					&& (null==objForm.getCurrentCollector2() || objForm.getCurrentCollector2().equals(""))) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError(
						"codetail.errorinput"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.getInputForward();
			}
			
			objDto.setInstallmentPayment(objForm.getInstallmentPayment());
			if(objForm.getInstallmentPayment()!=null && objForm.getInstallmentPayment().equals("Y")) {
				if(null!=objForm.getNoOfInstallment() && !objForm.getNoOfInstallment().equals("")) {
					objDto.setNoOfInstallment(Integer.parseInt(objForm.getNoOfInstallment()));
				}
				if(null!=objForm.getMinPaymentAmt() && !objForm.getMinPaymentAmt().equals("")) {
					objDto.setMinPaymentAmt(Long.parseLong(objForm.getMinPaymentAmt()));
				}
				if(null!=objForm.getInterestRate() && !objForm.getInterestRate().equals("")) {
					objDto.setInterestRate(Double.parseDouble(objForm.getInterestRate()));
				}
				if(null!=objForm.getNote() && !objForm.getNote().equals("")) {
					objDto.setNote(objForm.getNote());
				}
			}
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAccountDetailsDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAccountDetailsDispatchAction update method:"
							+ ex);
		}

		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}
}
