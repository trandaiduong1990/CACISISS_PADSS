package org.transinfo.cacis.action.disputemanagement;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.controller.disputemanagement.DisputeManagementManager;
import org.transinfo.cacis.dto.disputemanagement.Base2DocIndicatorDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCaseEventDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCleaningMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeEventsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeGroupDetailsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeTypesDto;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.formbean.disputemanagement.ChargeBackResendSetupForm;
import org.transinfo.cacis.util.DateUtil;
import org.transinfo.cacis.util.StringUtil;

@SuppressWarnings({ "unchecked", "deprecation" })
public class DisputeChargeBackResendDispatchAction extends BaseDispatchAction {
	
	public ActionForward viewChargeBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ChargeBackResendSetupForm objForm = (ChargeBackResendSetupForm) form;
		
		// Action execution
		DisputeManagementManager objDisputeManagementManager = new DisputeManagementManager();
		
		try {

			String disCaseNo = request.getParameter("disCaseNo");
			DisputeMasterDto objDisputeMasterDto = objDisputeManagementManager.getDisputeMasterDto(disCaseNo);
			
			objForm.setDisAmt(String.valueOf(objDisputeMasterDto.getDisputeAmt()));
			objForm.setDisCurr(objDisputeMasterDto.getDisputeCurrency().getCurrencySymbol());
			objForm.setDisReason(objDisputeMasterDto.getDisputeReason().getChargeBackReason());
			objForm.setStatus(objDisputeMasterDto.getStatus().getDescription());
			objForm.setUser(objDisputeMasterDto.getDisputeCreatedBy());
			objForm.setMemMsgText(objDisputeMasterDto.getMemberMessage());
			objForm.setCardHolder(objDisputeMasterDto.getCardHolder());
			
			objForm.setStatusSymbol(objDisputeMasterDto.getStatus().getId());
			objForm.setDisArn(objDisputeMasterDto.getArn());
			
			// what to do attaching doc, card holder and remarks
			
			// here pass the event details some thing like below
			//request.setAttribute("EVENTLIST", objTransactionLogDto.getDisputeMasters());
			List disCaseEvensList = objDisputeManagementManager.getDisputeCaseEventsList(disCaseNo);
			
			if(disCaseEvensList != null && disCaseEvensList.size() > 0){
				request.setAttribute("EVENTLIST", disCaseEvensList);
			}
			
			// pass object into session to get back original data
			request.getSession().setAttribute("DISPUTEMASTERDTO", objDisputeMasterDto);

		} catch (Exception e) {
			throw new TPlusException("In DisputeChargeBackDispatchAction viewChargeBack method: "
					+ e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}
	
	public ActionForward chargeBackCorrection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ChargeBackResendSetupForm objForm = (ChargeBackResendSetupForm) form;
		
		// Action execution
		DisputeManagementManager objDisputeManagementManager = new DisputeManagementManager();
		
		try {

			String disCaseNo = request.getParameter("disCaseNo");
			DisputeMasterDto objDisputeMasterDto = objDisputeManagementManager.getDisputeMasterDto(disCaseNo);
			
			String groupId = objDisputeMasterDto.getDisputeReason().getDisputeGroup().getGroupId();
			
			objForm.setDisAmt(String.valueOf(objDisputeMasterDto.getDisputeAmt()));
			objForm.setDisCurr(objDisputeMasterDto.getDisputeCurrency().getCurrencyCode());
			objForm.setDisReason(objDisputeMasterDto.getDisputeReason().getReasonCode());
			objForm.setDisGroup(groupId);
			objForm.setStatus(objDisputeMasterDto.getStatus().getDescription());
			objForm.setUser(objDisputeMasterDto.getDisputeCreatedBy());
			objForm.setMemMsgText(objDisputeMasterDto.getMemberMessage());
			objForm.setCardHolder(objDisputeMasterDto.getCardHolder());
			objForm.setDisCreatedDate(DateUtil.convertTranxDateToDateString(objDisputeMasterDto.getDisputeCreatedDate()));
			
			objForm.setStatusSymbol(objDisputeMasterDto.getStatus().getId());
			objForm.setDisArn(objDisputeMasterDto.getArn());
			
			objForm.setDisType(ICacisiss.IDisputeManagement.DIS_TYPE_CB_RE);
			objForm.setDisGroupList(objDisputeManagementManager.getDisputeGroups());
			objForm.setDisReasonList(objDisputeManagementManager.getDisputeGroupDetails(groupId));
			objForm.setCurrList(objDisputeManagementManager.getCurrencies());
			
			List disCaseEvensList = objDisputeManagementManager.getDisputeCaseEventsList(disCaseNo);
			
			if(disCaseEvensList != null && disCaseEvensList.size() > 0){
				request.setAttribute("EVENTLIST", disCaseEvensList);
			}

		} catch (Exception e) {
			throw new TPlusException("In DisputeChargeBackDispatchAction addChargeBack method: "
					+ e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("correction");
	}
	
	public ActionForward chargeBackClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ChargeBackResendSetupForm objForm = (ChargeBackResendSetupForm) form;
		
		// Action execution
		DisputeManagementManager objDisputeManagementManager = new DisputeManagementManager();
		ActionErrors errors = null;
		String forward = "closeSucess";
		
		try {

			String disCaseNo = request.getParameter("disCaseNo");
			
			boolean updateRes = objDisputeManagementManager.updateChargeBack(disCaseNo);
			
			if (!updateRes) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.closefailed"));
				saveErrors(request, errors);
				forward = "success";
				
				DisputeMasterDto objDisputeMasterDto = objDisputeManagementManager.getDisputeMasterDto(disCaseNo);
				
				objForm.setDisAmt(String.valueOf(objDisputeMasterDto.getDisputeAmt()));
				objForm.setDisCurr(objDisputeMasterDto.getDisputeCurrency().getCurrencySymbol());
				objForm.setDisReason(objDisputeMasterDto.getDisputeReason().getChargeBackReason());
				objForm.setStatus(objDisputeMasterDto.getStatus().getDescription());
				objForm.setUser(objDisputeMasterDto.getDisputeCreatedBy());
				objForm.setMemMsgText(objDisputeMasterDto.getMemberMessage());
				objForm.setCardHolder(objDisputeMasterDto.getCardHolder());
				
				objForm.setStatusSymbol(objDisputeMasterDto.getStatus().getId());
				
				// what to do attaching doc, card holder and remarks
				
				// here pass the event details some thing like below
				//request.setAttribute("EVENTLIST", objTransactionLogDto.getDisputeMasters());
				List disCaseEvensList = objDisputeManagementManager.getDisputeCaseEventsList(disCaseNo);
				
				if(disCaseEvensList != null && disCaseEvensList.size() > 0){
					request.setAttribute("EVENTLIST", disCaseEvensList);
				}
				
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.closeSuccess"));
				saveErrors(request, errors);
			}

		} catch (Exception e) {
			throw new TPlusException("In DisputeChargeBackDispatchAction chargeBackClose method: "
					+ e);
		}

		// Success
		saveToken(request);
		return mapping.findForward(forward);
	}
	
	public ActionForward chargeBackResend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ChargeBackResendSetupForm objForm = (ChargeBackResendSetupForm) form;
		
		boolean isError = false;
		String forward = "correction";
		
		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			isError = true;
		}
		
		// Action execution
		DisputeManagementManager objDisputeManagementManager = new DisputeManagementManager();
		
		try {
			
			DisputeMasterDto objDisputeMasterDto = (DisputeMasterDto) request.getSession().getAttribute("DISPUTEMASTERDTO");
			
			if(objDisputeMasterDto != null){
				
				String settlementId = String.valueOf(objDisputeMasterDto.getSettlementId().longValue());
				DisputeCleaningMasterDto objDisputeCleaningMasterDto = objDisputeManagementManager.getDisputeCleaningMasterDto(settlementId);
				
				// amount validations
				if(!isError){
					Double disAmt = Double.valueOf(objDisputeCleaningMasterDto.getTranxLog().getAmount());
					Double userEnteredAmt = Double.valueOf(objForm.getDisAmt());
					
					if(disAmt.doubleValue() < userEnteredAmt.doubleValue()){
						String reasonId =  objForm.getDisReason();
						DisputeGroupDetailsDto objDisputeGroupDetailsDto = objDisputeManagementManager.getDisputeGroupDetailsDto(reasonId);
						
						if(objDisputeGroupDetailsDto.getMinCBAmt().doubleValue() > userEnteredAmt.doubleValue()){
							ActionMessages messages = new ActionMessages();
							ActionMessage msg = new ActionMessage("message.amtgreaterreasonparam", objDisputeGroupDetailsDto.getMinCBAmt());
							messages.add("message1", msg);
							saveMessages(request, messages);
							isError = true;
						}
					}else{
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.amtequalorgreater"));
						saveErrors(request, errors);
						isError = true;
					}
				}
				
				String disCaseNo = request.getParameter("disCaseNo");
				
				objForm.setStatus(objDisputeMasterDto.getStatus().getDescription());
				objForm.setUser(objDisputeMasterDto.getDisputeCreatedBy());
				objForm.setDisCreatedDate(DateUtil.convertTranxDateToDateString(objDisputeMasterDto.getDisputeCreatedDate()));
				
				objForm.setStatusSymbol(objDisputeMasterDto.getStatus().getId());
				
				objForm.setDisType(ICacisiss.IDisputeManagement.DIS_TYPE_CB_RE);
				objForm.setDisGroupList(objDisputeManagementManager.getDisputeGroups());
				objForm.setDisReasonList(objDisputeManagementManager.getDisputeGroupDetails(""));
				objForm.setCurrList(objDisputeManagementManager.getCurrencies());
				
				List disCaseEvensList = objDisputeManagementManager.getDisputeCaseEventsList(disCaseNo);
				
				if(disCaseEvensList != null && disCaseEvensList.size() > 0){
					request.setAttribute("EVENTLIST", disCaseEvensList);
				}
				
				// pass object into session to get back original data
				request.getSession().setAttribute("DISPUTEMASTERDTO", objDisputeMasterDto);
				
				if(!isError){
					// update and insert
					StringBuffer remarks = new StringBuffer();
					String disReason = "";
					double disAmt = 0;
					String disCurr = "";
					String disCardHolder = "";
					String memText = "";
					String disArn = "";
					
					// check dispute reason
					if("Y".equals(StringUtil.confirmString(objForm.getEdisGroup()))){
						disReason = objForm.getDisReason();
						remarks.append(" Dispute Reason : " + objDisputeMasterDto.getDisputeReason().getReasonCode() + " ");
					}else{
						disReason = objDisputeMasterDto.getDisputeReason().getReasonCode();
					}
					
					// check dispute amount
					if("Y".equals(StringUtil.confirmString(objForm.getEdisAmt()))){
						disAmt = Long.valueOf(objForm.getDisAmt());
						remarks.append(" Dispute Amt : " + objDisputeMasterDto.getDisputeAmt() + " ");
					}else{
						disAmt = objDisputeMasterDto.getDisputeAmt();
					}
					
					// check dispute currency
					if("Y".equals(StringUtil.confirmString(objForm.getEdisCurr()))){
						disCurr = objForm.getDisCurr();
						remarks.append(" Dispute Currency : " + objDisputeMasterDto.getDisputeCurrency().getCurrencySymbol() + " ");
					}else{
						disCurr = objDisputeMasterDto.getDisputeCurrency().getCurrencyCode();
					}
					
					// check dispute card holder
					if(!objDisputeMasterDto.getCardHolder().equals(objForm.getCardHolder())){
						disCardHolder = objForm.getCardHolder();
						remarks.append(" Dispute Cardholder : " + objDisputeMasterDto.getCardHolder() + " ");
					}else{
						disCardHolder = objDisputeMasterDto.getCardHolder();
					}
					
					// check member text
					if("Y".equals(StringUtil.confirmString(objForm.getEmemMsgText()))){
						memText = objForm.getMemMsgText();
						remarks.append(" Dispute Mem Text : " + objDisputeMasterDto.getMemberMessage() + " ");
					}else{
						memText = objDisputeMasterDto.getMemberMessage();
					}
					
					// check ARN
					if("Y".equals(StringUtil.confirmString(objForm.getEdisArn()))){
						disArn = objForm.getDisArn();
						remarks.append(" Dispute ARN : " + objDisputeMasterDto.getArn() + " ");
					}else{
						disArn = objDisputeMasterDto.getArn();
					}
					
					String documentIncluded = "";
					String docUploadUserSelect = objForm.getAttachingDoc();
					if("Y".equals(StringUtil.confirmString(docUploadUserSelect))){
						documentIncluded = "1";
					}
					
					DisputeMasterDto objDisputeMasterDto2 = objDisputeMasterDto;
					
					DisputeGroupDetailsDto objDisputeGroupDetailsDto = new DisputeGroupDetailsDto();
					objDisputeGroupDetailsDto.setReasonCode(disReason);
					objDisputeMasterDto2.setDisputeReason(objDisputeGroupDetailsDto);
					
					objDisputeMasterDto2.setDisputeAmt(disAmt);
					
					CurrencyDto objCurrencyDto = new CurrencyDto();
					objCurrencyDto.setCurrencyCode(disCurr);
					objDisputeMasterDto2.setDisputeCurrency(objCurrencyDto);
					
					objDisputeMasterDto2.setCardHolder(disCardHolder);
					objDisputeMasterDto2.setMemberMessage(memText);
					objDisputeMasterDto2.setArn(disArn);
					objDisputeMasterDto2.setResend("N");

					DisputeTypesDto objDisputeTypesDto = new DisputeTypesDto();
					objDisputeTypesDto.setSno("6");
					objDisputeMasterDto2.setDisputeType(objDisputeTypesDto);
					
					DisputeEventsDto objDisputeEventsDto = new DisputeEventsDto();
					objDisputeEventsDto.setEventId("4");
					
					DisputeCaseEventDto objDisputeCaseEventDto = new DisputeCaseEventDto();
					objDisputeCaseEventDto.setDisputEevent(objDisputeEventsDto);
					objDisputeCaseEventDto.setDateTime(new Date());
					objDisputeCaseEventDto.setRemarks(remarks.toString());
					
					Base2DocIndicatorDto objBase2DocIndicatorDto = new Base2DocIndicatorDto();
					objBase2DocIndicatorDto.setDocIndicator(documentIncluded);
					objDisputeCaseEventDto.setDocumentIncluded(objBase2DocIndicatorDto);
					
					objDisputeCaseEventDto.setDisputeMaster(objDisputeMasterDto2);
					
					boolean insertRes = objDisputeManagementManager.updateResendChargeBack(objDisputeMasterDto2, objDisputeCaseEventDto);
					
					objForm.setEdisGroup(null);
					objForm.setEdisAmt(null);
					objForm.setEdisCurr(null);
					objForm.setEmemMsgText(null);
					objForm.setEdisArn(null);
					
					if (!insertRes) {
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.updatefailed"));
						saveErrors(request, errors);
						
						objForm.setAddButton("Y");
						
					} else {
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.updateSuccess"));
						saveErrors(request, errors);
						
						forward = "corrAddSucess";
						
						request.getSession().removeAttribute("DISPUTEMASTERDTO");
						objForm.setAddButton("N");
					}
					
				}else{

					objForm.setEdisGroup(null);
					objForm.setEdisAmt(null);
					objForm.setEdisCurr(null);
					objForm.setEmemMsgText(null);
					objForm.setEdisArn(null);
				}
				
			}

		} catch (Exception e) {
			throw new TPlusException("In DisputeChargeBackDispatchAction chargeBackClose method: "
					+ e);
		}

		// Success
		saveToken(request);
		return mapping.findForward(forward);
	}

}
