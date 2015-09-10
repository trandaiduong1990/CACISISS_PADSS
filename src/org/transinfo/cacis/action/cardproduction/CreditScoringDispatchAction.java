package org.transinfo.cacis.action.cardproduction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.cardproduction.CreditScoringManager;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;
import org.transinfo.cacis.formbean.cardproduction.CreditScoringSetupForm;

public class CreditScoringDispatchAction extends BaseDispatchAction {

	public static List creditScorings = new ArrayList<CreditScoringSetupForm>();
	private Logger logger = Logger.getLogger(CreditScoringDispatchAction.class);

	/**
	* this method is used for showing the CreditScoringDispatchAction to add
	*/
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		CreditScoringSetupForm objForm = (CreditScoringSetupForm) form;
		objForm.setIssuerId(objForm.getIssuerId());
		
		try {
			BeanUtils.copyProperties(objForm, objForm);
			objForm.getPreListData();
			
		} catch (Exception ex) {
			System.out
			.println("Error converting to form bean in CreditScoringDispatchAction addNew : "
					+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in CreditScoringDispatchAction addNew : "
							+ ex);
		}
		
		// Action Execution
		CreditScoringManager objManager = new CreditScoringManager();
	
		creditScorings = objManager.getAllCreditScoring();
		
		objForm.setCreditScoringList(creditScorings);
					
		// Success
		saveToken(request);
		request.setAttribute("SEARCHLIST", objForm.getCreditScoringList());
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for creating the CreditScoringDispatchAction to update
	*/
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		CreditScoringSetupForm objForm = (CreditScoringSetupForm) form;
		
		errors = objForm.validate(mapping, request);
		
		// Action Execution
		CreditScoringManager objManager = new CreditScoringManager();
		String nextAttribute = "cancel";
		
		for(Iterator<CreditScoringSetupForm> i = objForm.getCreditScoringList().iterator(); i.hasNext();) {
			CreditScoringSetupForm creditScoringForm = i.next();
			
			CreditScoringDto objDto = new CreditScoringDto();
			if(CommonCodes.STATUS_BLOCKED.equals(creditScoringForm.getStatus())) {
				
				// Validation form
				ValidationConditionForm(creditScoringForm , errors);
				
				creditScoringForm.setStatus(CommonCodes.STATUS_ACTIVE);
			} else {
				creditScoringForm.setStatus(CommonCodes.STATUS_INACTIVE);
			}
		}
		
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "change");
			objForm.getPreListData();
			return mapping.getInputForward();
		}	
		
		for(Iterator<CreditScoringSetupForm> i = objForm.getCreditScoringList().iterator(); i.hasNext();) {
			CreditScoringSetupForm creditScoringForm = i.next();
			
			CreditScoringDto objDto = new CreditScoringDto();
			if(CommonCodes.STATUS_ACTIVE.equals(creditScoringForm.getStatus())) {
				
				try {
					String userId = (String) request.getSession(false).getAttribute("USERID");
					creditScoringForm.setLastUpdatedDate(null);
					BeanUtils.copyProperties(objDto, creditScoringForm);
					objDto.setLastUpdatedBy(userId);
					objDto.setLastUpdatedDate(new Date());
				} catch (Exception ex) {
					logger.error(ex);
					System.out
							.println("Error converting to form bean in CreditScoringDispatchAction add method: "
									+ ex.getMessage());
					throw new TPlusException(
							"Could not populate the form bean in CreditScoringDispatchAction add method "
									+ ex);
				}
				
				// Check record
				boolean recExistRes = objManager.validate(objDto, 1);
					
				if(!recExistRes) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.cgfexists"));
				}else{
					boolean boolCreated = objManager.update(objDto);
					if (!boolCreated) {
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.updatefailed"));
						nextAttribute = "update";
					} else {
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.updateSuccess"));
						nextAttribute = "cancel";
					}
					saveErrors(request, errors);
				}
			} 
		}
		
		// Success
		objForm.getPreListData();
		request.setAttribute("ACTION", nextAttribute);		
		return mapping.findForward("success");
	}

	private void ValidationConditionForm(
			CreditScoringSetupForm objForm, ActionErrors errors) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Boolean errorInteger = false;
		Boolean errorMinMax = false;
		String min = objForm.getMinScore();
		String max = objForm.getMaxScore();
		
		for (int i = 1; i <= 10; i++) {
			Class tClass = objForm.getClass();
			Method gs1Method = tClass.getMethod("getC" + i + "s" + i, new Class[] {});
			String csResult = (String) gs1Method.invoke(objForm, new Object[] {});
			
			if (!StringUtils.isBlank(csResult)) {
				if (!IsNumeric(csResult)) {
					errorInteger = true;
				} else {
					if (!MinMax(csResult, min, max) && !StringUtils.isBlank(csResult)) {
						errorMinMax = true;
					}
				}
			}
		}

		if (errorInteger) {
			errors.add("Error", new ActionError("error.creditscoring.integer"));
		}
		
		if (errorMinMax) {
			errors.add("Error", new ActionError("error.creditscoring.minmax"));
		}
	}
	
	/**
	* Check Min Max of Value
	*/
	public boolean MinMax(String value, String min, String max) {
		Integer csResult = Integer.valueOf(value);
		
		if (csResult != 0 && (csResult  < Integer.valueOf(min) || csResult > Integer.valueOf(max))) {
			return false;
		}
		return true;
	}
	
	/**
	* Check string is a numeric
	*/
	public boolean IsNumeric(String str)  
	{  
		if (!StringUtils.isBlank(str)) {
			try {
				Integer d = Integer.parseInt(str);
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
		return true;    
	}
}
