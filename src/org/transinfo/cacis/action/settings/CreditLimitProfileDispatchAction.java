package org.transinfo.cacis.action.settings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

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
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.cardproduction.CreditScoringManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.controller.settings.CreditLimitProfileManager;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CreditLimitProfileDto;
import org.transinfo.cacis.formbean.settings.CreditLimitProfileSetupForm;

public class CreditLimitProfileDispatchAction extends BaseDispatchAction {
	
private Logger logger = Logger.getLogger(CreditLimitProfileDispatchAction.class);
	
	/**
	* this method is used for showing the CreditLimitProfileDispatchAction to add
	*/
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		CreditLimitProfileSetupForm objOldForm = (CreditLimitProfileSetupForm) form;
		CreditLimitProfileSetupForm objForm = new CreditLimitProfileSetupForm();
		objForm.setIssuerId(objOldForm.getIssuerId());
		
		try {
			if (CommonCodes.TYPE_SCREEN_CHANGE.equals(objOldForm.getScreen()) || 
					CommonCodes.TYPE_SCREEN_SEARCH.equals(objOldForm.getScreen())) {
				BeanUtils.copyProperties(objOldForm, objForm);
			}
			objOldForm.getPreListData();
			
		} catch (Exception ex) {
			System.out
			.println("Error converting to form bean in CreditLimitProfileDispatchAction addNew : "
					+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in CreditLimitProfileDispatchAction addNew : "
							+ ex);
		}
		
		// Action Execution
		CreditLimitProfileManager objManager = new CreditLimitProfileManager();
		
		if (!StringUtils.isBlank(objOldForm.getCardProductId())) {
			CardProductDto objCardProductDto = objManager.getLowerUpperLimit(objOldForm.getCardProductId());
			objOldForm.setLowerLimit(String.valueOf(objCardProductDto.getCreditLowerLimit()));
			objOldForm.setUpperLimit(String.valueOf(objCardProductDto.getCreditUpperLimit()));
		} else {
			objOldForm.setLowerLimit("0");
			objOldForm.setUpperLimit("0");
		}
		
		if (!StringUtils.isBlank(objOldForm.getScoreId())) {
			Integer totalScore = objManager.getSumCreditScoring(objOldForm.getScoreId());
			objOldForm.setTotalScoringPoint(totalScore.toString());
		} else {
			objOldForm.setTotalScoringPoint("0");
		}

		//objOldForm.setTotalScoringPoint(objManager.getTotalScoringPoint());
		
		// Success
		objOldForm.setScreen(CommonCodes.TYPE_SCREEN_ADDNEW);
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for creating the CreditLimitProfileDispatchAction to add
	*/
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		CreditLimitProfileSetupForm objForm = (CreditLimitProfileSetupForm) form;
		errors = objForm.validate(mapping, request);
		CreditLimitProfileDto objDto = new CreditLimitProfileDto();
		CreditLimitProfileManager objManager = new CreditLimitProfileManager();
		String totalScore = null;

		if (!StringUtils.isBlank(objForm.getScoreId())) {
			totalScore = objManager.getSumCreditScoring(objForm.getScoreId()).toString();
		} 
		
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			objForm.setTotalScoringPoint(totalScore);
			return mapping.getInputForward();
		}
		
		CardProductManager objCardProductManager = new CardProductManager();
		CardProductDto objCardProductDto = new CardProductDto();
		CreditScoringDto objCreditScoringDto = new CreditScoringDto();
		CreditScoringManager objCreditScoringManager = new CreditScoringManager();
		
		if (!StringUtils.isBlank(objForm.getCardProductId())) {
			objCardProductDto = objCardProductManager.getCardProductDto(objForm.getCardProductId());
		}
		
		if (!StringUtils.isBlank(objForm.getScoreId())) {
			objCreditScoringDto = objCreditScoringManager.getCreditScoringDetail(objForm.getScoreId());
		}
		
		// Validation form
		ValidationConditionForm(objForm, totalScore, errors);
		
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			objForm.setTotalScoringPoint(totalScore);
			return mapping.getInputForward();
		}
			
		try {
			String userId = (String) request.getSession(false).getAttribute("USERID");
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setsNo(IdsGenartion.GenerateCreditLimitProfileSNo());
			objDto.setLastUpdatedBy(userId);
			objDto.setLastUpdatedDate(new Date());
			objDto.setCardProduct(objCardProductDto);
			objDto.setCreditScore(objCreditScoringDto);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CreditLimitProfileDispatchAction add method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CreditLimitProfileDispatchAction add method "
							+ ex);
		}
		
		// Action Execution
		String nextAttribute = "cancel";
		
		// Check record
		boolean recExistRes = objManager.validate(objDto, 0);
		boolean recUniqueRes = objManager.validateScoreAndProduct(objDto, 0);
				
		if(!recExistRes || !recUniqueRes) {
			
			errors = new ActionErrors();
			if (!recExistRes) {
				errors.add("Error", new ActionError("error.creditlimitprofileexist"));
			} 
			
			if (!recUniqueRes){
				errors.add("Error", new ActionError("error.creditlimitprofileunique"));
			}
			saveErrors(request, errors);
			nextAttribute = "add";
		}else{
			boolean boolCreated = objManager.add(objDto);
			
			if (!boolCreated) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
				nextAttribute = "change";
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				nextAttribute = "cancel";
			}
		}
		
		// Success
		objForm.getPreListData();
		objForm.setTotalScoringPoint(totalScore);
		objForm.setScreen(CommonCodes.TYPE_SCREEN_ADDNEW);
		request.setAttribute("ACTION", nextAttribute);		
		return mapping.findForward("success");
	}
	
	

	/**
	* this method is used for showing the CreditLimitProfileDispatchAction to update
	*/
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		CreditLimitProfileManager objManager = new CreditLimitProfileManager();
		CreditLimitProfileSetupForm objForm = (CreditLimitProfileSetupForm) form;
		
		try {
			if (!CommonCodes.TYPE_SCREEN_CHANGE.equals(objForm.getScreen())) {
				CreditLimitProfileDto objDto = objManager.getCreditLimitProfileDetail(objForm.getsNo());
				
				BeanUtils.copyProperties(objForm, objDto);
				objForm.setCardProductId(objDto.getCardProduct().getCardProductId());
			}
			objForm.setCardProductId(objForm.getCardProductId());
			objForm.setTotalScoringPoint(objManager.getSumCreditScoring(objForm.getScoreId()).toString());
			
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CreditLimitProfileDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CreditLimitProfileDispatchAction change method: "
							+ ex);
		}
		
		//Set value form
		if (!StringUtils.isBlank(objForm.getCardProductId())) {
			CardProductDto objCardProductDto = objManager.getLowerUpperLimit(objForm.getCardProductId());
			objForm.setLowerLimit(String.valueOf(objCardProductDto.getCreditLowerLimit()));
			objForm.setUpperLimit(String.valueOf(objCardProductDto.getCreditUpperLimit()));
		} else {
			objForm.setLowerLimit("0");
			objForm.setUpperLimit("0");
		}
		
		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		objForm.getPreListData();
		objForm.setScreen(CommonCodes.TYPE_SCREEN_CHANGE);
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for update the CreditLimitProfileDispatchAction
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
		CreditLimitProfileSetupForm objForm = (CreditLimitProfileSetupForm) form;

		errors = objForm.validate(mapping, request);
		
		// Action Execution
		CreditLimitProfileManager objManager = new CreditLimitProfileManager();
		CreditLimitProfileDto objDto = new CreditLimitProfileDto();
		
		CardProductManager objCardProductManager = new CardProductManager();
		CardProductDto objCardProductDto = new CardProductDto();
		CreditScoringDto objCreditScoringDto = new CreditScoringDto();
		CreditScoringManager objCreditScoringManager = new CreditScoringManager();
		
		if (!StringUtils.isBlank(objForm.getCardProductId())) {
			objCardProductDto = objCardProductManager.getCardProductDto(objForm.getCardProductId());
		}
		
		if (!StringUtils.isBlank(objForm.getScoreId())) {
			objCreditScoringDto = objCreditScoringManager.getCreditScoringDetail(objForm.getScoreId());
		}
		
		String totalScore = null;

		if (!StringUtils.isBlank(objForm.getScoreId())) {
			totalScore = objManager.getSumCreditScoring(objForm.getScoreId()).toString();
		} 
		
		// Validation form
		ValidationConditionForm(objForm, totalScore, errors);
				
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "change");
			objForm.getPreListData();
			objForm.setTotalScoringPoint(totalScore);
			return mapping.getInputForward();
		}
		
		try {
			
			String userId = (String) request.getSession(false).getAttribute("USERID");
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedBy(userId);
			objDto.setLastUpdatedDate(new Date());
			objDto.setCardProduct(objCardProductDto);
			objDto.setCreditScore(objCreditScoringDto);
			
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CreditLimitProfileDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CreditLimitProfileDispatchAction update method:"
							+ ex);
		}
		
		// Check record
		boolean recExistRes = objManager.validate(objDto, 1);
		boolean recUniqueRes = objManager.validateScoreAndProduct(objDto, 1);
		
		if(!recExistRes || recUniqueRes) {
			
			errors = new ActionErrors();
			
			if (!recExistRes) {
				errors.add("Error", new ActionError("error.creditlimitprofileexist"));
			} 
			
			if (recUniqueRes){
				errors.add("Error", new ActionError("error.creditlimitprofileunique"));
			}
			saveErrors(request, errors);
			request.setAttribute("ACTION", "change");
			objForm.getPreListData();
			objForm.setTotalScoringPoint(totalScore);
			return mapping.getInputForward();
			
		} else {
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
		}
		
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		objForm.getPreListData();
		objForm.setTotalScoringPoint(totalScore);
		objForm.setScreen(CommonCodes.TYPE_SCREEN_CHANGE);
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for delete the CreditLimitProfileDispatchAction
	*/
	public ActionForward delete(ActionMapping mapping, ActionForm form,
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
		CreditLimitProfileSetupForm objForm = (CreditLimitProfileSetupForm) form;
		objForm.getPreListData();
		
		// Dto Creation
		CreditLimitProfileDto objDto = new CreditLimitProfileDto();
		
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean in CreditLimitProfileDispatchAction in delete method "
					+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CreditLimitProfileDispatchAction in delete method: "
									+ e);
		}
		
		// Action Execution
		CreditLimitProfileManager objManager = new CreditLimitProfileManager();

		boolean emvProfileExits = objManager.validate(objDto, 0);
		
		if (emvProfileExits) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.producthascards"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		} 
		
		String nextaction = "delete";
		boolean boolDelete = objManager.delete(objDto);

		if (!boolDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "success";
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}
	
	private void ValidationConditionForm(CreditLimitProfileSetupForm objForm, String totalscore, ActionErrors errors) throws SecurityException, 
	NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		Boolean errorInteger = false;
		Boolean errorMinMax = false;
		Boolean errorFrTo = false;
		Boolean errorTotalCore = false;
		int totalScore = Integer.valueOf(totalscore);
		
		for (int i = 1; i <= 8; i++) {
			Class tClass = objForm.getClass();
			Method spfMethod = tClass.getMethod("getSpf" + i, new Class[] {});
			Method sptMethod = tClass.getMethod("getSpt" + i, new Class[] {});
			Method creditLimitMethod = tClass.getMethod("getCreditLimit" + i, new Class[] {});
			String spfString = String.valueOf(spfMethod.invoke(objForm, new Object[] {}));
			String sptString = String.valueOf(sptMethod.invoke(objForm, new Object[] {}));
			String creditLimitString = String.valueOf(creditLimitMethod.invoke(objForm, new Object[] {}));
			
			if (!IsNumeric(spfString) || !IsNumeric(sptString) || !IsNumeric(creditLimitString)) {
				errorInteger = true;
			} else {
				//Integer spfInteger = Integer.valueOf(spfString);
				//Integer sptInteger = Integer.valueOf(sptString);
				//Integer creditLimitInteger = Integer.valueOf(creditLimitString);
				
				if ((!StringUtils.isBlank(spfString) && (Integer.valueOf(spfString) > totalScore)) ||
						(!StringUtils.isBlank(sptString) && Integer.valueOf(sptString) > totalScore)) {
					errorTotalCore = true;
				}
				
				if (!StringUtils.isBlank(creditLimitString) && (Integer.valueOf(creditLimitString) > Integer.valueOf(objForm.getUpperLimit())
						|| Integer.valueOf(creditLimitString) < Integer.valueOf(objForm.getLowerLimit()))) {
					errorMinMax = true;
				}
				
				if(!StringUtils.isBlank(spfString) && i > 1) {
					Method spfMethodBf = tClass.getMethod("getSpt" + (i-1), new Class[] {});
					String valueBefore = (String) spfMethodBf.invoke(objForm, new Object[] {});
					
					if ((Integer.valueOf(valueBefore) > Integer.valueOf(spfString)) && Integer.valueOf(spfString) != 0) {
						errorTotalCore = true;
					}
				}
				
				if (!StringUtils.isBlank(spfString) && !StringUtils.isBlank(sptString)){
					if (Integer.valueOf(spfString) > Integer.valueOf(sptString)) {
						errorFrTo = true;
					}
				}
			}
		}
		
		if (errorInteger) {
			errors.add("Error", new ActionError("error.creditscoring.integer"));
		}
		
		if (errorTotalCore) {
			errors.add("Error", new ActionError("error.creditscoring.score"));
		}
		
		if (errorMinMax) {
			errors.add("Error", new ActionError("error.creditscoring.minmax"));
		}
		
		if (errorFrTo) {
			errors.add("Error", new ActionError("error.creditlimit.fromto"));
		}
	}
	
	/**
	* this method is used for check string is a numeric
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
