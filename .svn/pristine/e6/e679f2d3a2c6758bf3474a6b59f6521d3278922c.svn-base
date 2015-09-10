package org.transinfo.cacis.action.disputemanagement;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import org.transinfo.cacis.dto.disputemanagement.DisputeStatusDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeTypesDto;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.formbean.disputemanagement.Base2TranxCodeForm;
import org.transinfo.cacis.formbean.disputemanagement.ChargeBackSetupForm;
import org.transinfo.cacis.formbean.disputemanagement.DisputeChargeBackSetupForm;
import org.transinfo.cacis.formbean.disputemanagement.DisputeCleaningMasterForm;
import org.transinfo.cacis.formbean.disputemanagement.TransactionLogForm;

@SuppressWarnings({"deprecation", "unchecked"})
public class DisputeChargeBackDispatchAction extends BaseDispatchAction {

	public ActionForward addChargeBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		DisputeChargeBackSetupForm objForm = (DisputeChargeBackSetupForm) form;

		// Action execution
		DisputeManagementManager objDisputeManagementManager = new DisputeManagementManager();

		// object of transaction form
		TransactionLogForm objTransactionLogForm = new TransactionLogForm();
		Base2TranxCodeForm objBase2TranxCodeForm = new Base2TranxCodeForm();
		DisputeCleaningMasterForm objDisputeCleaningMasterForm = new DisputeCleaningMasterForm();

		try {

			String settlementId = request.getParameter("settlementId");
			DisputeCleaningMasterDto objDisputeCleaningMasterDto = objDisputeManagementManager.getDisputeCleaningMasterDto(settlementId);

			BeanUtils.copyProperties(objDisputeCleaningMasterForm, objDisputeCleaningMasterDto);
			objForm.setDisputeCleaningMasterForm(objDisputeCleaningMasterForm);

			BeanUtils.copyProperties(objTransactionLogForm, objDisputeCleaningMasterDto.getTranxLog());
			objForm.setTranxlogForm(objTransactionLogForm);

			BeanUtils.copyProperties(objBase2TranxCodeForm, objDisputeCleaningMasterDto.getTranxCode());
			objForm.setTranxCodeForm(objBase2TranxCodeForm);
			
			String disTypeCode = objForm.getDisTypeCode();
			
			if("RR".equals(disTypeCode)){
				objForm.setDisType(ICacisiss.IDisputeManagement.DIS_TYPE_RR);
			}else{
				objForm.setDisType(ICacisiss.IDisputeManagement.DIS_TYPE_CB);
			}
			
			objForm.setDisGroupList(objDisputeManagementManager.getDisputeGroups());
			objForm.setDisReasonList(objDisputeManagementManager.getDisputeGroupDetails(""));
			objForm.setCurrList(objDisputeManagementManager.getCurrencies());
			objForm.setMotoList(objDisputeManagementManager.getMotos());
			
			// assign the MOTO ECI
			objForm.setDisMoto(objDisputeCleaningMasterDto.getMotoEci());
			
			objForm.setDisAmt(objDisputeCleaningMasterDto.getTranxLog().getCardHolderTranxAmt());
			objForm.setDisCurr(objDisputeCleaningMasterDto.getTranxLog().getCardHolderTranxCurr());
			
			objForm.setDisTypeCode(disTypeCode);

		} catch (Exception e) {
			throw new TPlusException("In DisputeChargeBackDispatchAction addChargeBack method: "
					+ e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}

	public ActionForward insertChargeBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		DisputeChargeBackSetupForm objForm = (DisputeChargeBackSetupForm) form;

		boolean isError = false;

		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			isError = true;
		}

		// Action execution
		DisputeManagementManager objDisputeManagementManager = new DisputeManagementManager();

		// object of transaction form
		TransactionLogForm objTransactionLogForm = new TransactionLogForm();
		Base2TranxCodeForm objBase2TranxCodeForm = new Base2TranxCodeForm();
		DisputeCleaningMasterForm objDisputeCleaningMasterForm = new DisputeCleaningMasterForm();

		try {

			String settlementId = request.getParameter("settlementId");
			DisputeCleaningMasterDto objDisputeCleaningMasterDto = objDisputeManagementManager.getDisputeCleaningMasterDto(settlementId);

			// amount validations
			if(!isError){
				//Double disAmt = objDisputeCleaningMasterDto.getTranxLog().getAmount();
				Double userEnteredAmt = Double.valueOf(objForm.getDisAmt());

				//if(disAmt.doubleValue() < userEnteredAmt.doubleValue()){
				String reasonId =  objForm.getDisReason();
				String groupId = objForm.getDisGroup();
				//DisputeGroupDetailsDto objDisputeGroupDetailsDto = objDisputeManagementManager.getDisputeGroupDetailsDto(reasonId,groupId);
				DisputeGroupDetailsDto objDisputeGroupDetailsDto = objDisputeManagementManager.getDisputeGroupDetailsDto(reasonId);

				if(objDisputeGroupDetailsDto.getMinCBAmt().doubleValue() > userEnteredAmt.doubleValue()){
					ActionMessages messages = new ActionMessages();
					ActionMessage msg = new ActionMessage("message.amtgreaterreasonparam", objDisputeGroupDetailsDto.getMinCBAmt());
					messages.add("message1", msg);
					saveMessages(request, messages);
					isError = true;
				}
				/*}else{
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.amtequalorgreater"));
					saveErrors(request, errors);
					isError = true;
				}*/
			}

			BeanUtils.copyProperties(objDisputeCleaningMasterForm, objDisputeCleaningMasterDto);
			objForm.setDisputeCleaningMasterForm(objDisputeCleaningMasterForm);

			BeanUtils.copyProperties(objTransactionLogForm, objDisputeCleaningMasterDto.getTranxLog());
			objForm.setTranxlogForm(objTransactionLogForm);

			BeanUtils.copyProperties(objBase2TranxCodeForm, objDisputeCleaningMasterDto.getTranxCode());
			objForm.setTranxCodeForm(objBase2TranxCodeForm);
			
			String disTypeCode = objForm.getDisTypeCode();
			
			if("RR".equals(disTypeCode)){
				objForm.setDisType(ICacisiss.IDisputeManagement.DIS_TYPE_RR);
			}else{
				objForm.setDisType(ICacisiss.IDisputeManagement.DIS_TYPE_CB);
			}

			//objForm.setDisType(ICacisiss.IDisputeManagement.DIS_TYPE_CB);
			objForm.setDisGroupList(objDisputeManagementManager.getDisputeGroups());

			String groupId = objForm.getDisGroup();
			objForm.setDisReasonList(objDisputeManagementManager.getDisputeGroupDetails(groupId));

			objForm.setCurrList(objDisputeManagementManager.getCurrencies());
			objForm.setMotoList(objDisputeManagementManager.getMotos());

			if(!isError){

				String userID = (String) request.getSession(false).getAttribute("USERID");

				DisputeTypesDto objDisputeTypesDto = new DisputeTypesDto();
				
				DisputeEventsDto objDisputeEventsDto = new DisputeEventsDto();
				
				if("RR".equals(disTypeCode)){
					objDisputeTypesDto.setSno("1");
					objDisputeEventsDto.setEventId("5");
				}else{
					objDisputeTypesDto.setSno("2");
					objDisputeEventsDto.setEventId("1");
				}

				String reasonCode = objForm.getDisReason();
				DisputeGroupDetailsDto objDisputeGroupDetailsDto = new DisputeGroupDetailsDto();
				objDisputeGroupDetailsDto.setReasonCode(reasonCode);

				DisputeStatusDto objDisputeStatusDto = new DisputeStatusDto();
				objDisputeStatusDto.setId("N");

				String currencyCode = objForm.getDisCurr();
				CurrencyDto objCurrencyDto = new CurrencyDto();
				objCurrencyDto.setCurrencyCode(currencyCode);

				String documentIncluded = "";
				String docUploadUserSelect = objForm.getDocUpload();
				if(docUploadUserSelect != null && !"".equals(docUploadUserSelect) && "Y".equals(docUploadUserSelect)){
					documentIncluded = "1";
				}else{
					documentIncluded = "0";
				}

				// insert data into DISPUTE_MASTER table
				DisputeMasterDto objDisputeMasterDto = new DisputeMasterDto();
				objDisputeMasterDto.setDisputeType(objDisputeTypesDto);
				objDisputeMasterDto.setDisputeReason(objDisputeGroupDetailsDto);
				//objDisputeMasterDto.setDisputeAmt(Long.valueOf(objForm.getDisAmt()));
				objDisputeMasterDto.setDisputeAmt(Double.valueOf(objForm.getDisAmt()));
				objDisputeMasterDto.setDisputeCurrency(objCurrencyDto);
				objDisputeMasterDto.setMemberMessage(objForm.getMemMsgText());
				objDisputeMasterDto.setStatus(objDisputeStatusDto);
				objDisputeMasterDto.setUpdatedDate(new Date());
				objDisputeMasterDto.setUpdatedBy(userID);
				objDisputeMasterDto.setDisputeCreatedDate(new Date());
				objDisputeMasterDto.setDisputeCreatedBy(userID);
				objDisputeMasterDto.setArn(objDisputeCleaningMasterDto.getArn());
				objDisputeMasterDto.setSettlementId(Long.valueOf(settlementId));
				objDisputeMasterDto.setDisputeSource("M");
				objDisputeMasterDto.setCardHolder(objForm.getCardHolder());
				objDisputeMasterDto.setDisMoto(objForm.getDisMoto());

				// insert data into DISPUTE_CASE_EVENT table
				DisputeCaseEventDto objDisputeCaseEventDto = new DisputeCaseEventDto();
				objDisputeCaseEventDto.setDisputEevent(objDisputeEventsDto);
				objDisputeCaseEventDto.setDateTime(new Date());
				objDisputeCaseEventDto.setRemarks(objForm.getMemMsgText());
				objDisputeCaseEventDto.setArn(objDisputeCleaningMasterDto.getArn());

				Base2DocIndicatorDto objBase2DocIndicatorDto = new Base2DocIndicatorDto();
				objBase2DocIndicatorDto.setDocIndicator(documentIncluded);
				objDisputeCaseEventDto.setDocumentIncluded(objBase2DocIndicatorDto);

				boolean insertRes = objDisputeManagementManager.insertChargeBack(objDisputeMasterDto, objDisputeCaseEventDto);

				if (!insertRes) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addfailed"));
					saveErrors(request, errors);
				} else {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addSuccess"));
					saveErrors(request, errors);

					objForm.setAddButton("N");
				}
			}

		} catch (Exception e) {
			throw new TPlusException("In DisputeChargeBackDispatchAction insertChargeBack method: "
					+ e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}

	public ActionForward viewChargeBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		ChargeBackSetupForm objForm = (ChargeBackSetupForm) form;

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

			// what to do attaching doc, card holder and remarks

			// here pass the event details some thing like below
			//request.setAttribute("EVENTLIST", objTransactionLogDto.getDisputeMasters());
			List disCaseEvensList = objDisputeManagementManager.getDisputeCaseEventsList(disCaseNo);

			if(disCaseEvensList != null && disCaseEvensList.size() > 0){
				request.setAttribute("EVENTLIST", disCaseEvensList);
			}

		} catch (Exception e) {
			throw new TPlusException("In DisputeChargeBackDispatchAction viewChargeBack method: "
					+ e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}

	public ActionForward chargeBackClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		ChargeBackSetupForm objForm = (ChargeBackSetupForm) form;

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

}
