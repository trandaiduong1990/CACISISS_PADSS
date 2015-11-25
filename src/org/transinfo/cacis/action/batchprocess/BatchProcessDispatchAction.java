package org.transinfo.cacis.action.batchprocess;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.controller.batchprocess.CardBatchProcessManager;
import org.transinfo.cacis.controller.key.KeyIndexManager;
import org.transinfo.cacis.controller.useraccess.AdminLoginManager;
import org.transinfo.cacis.dto.batchprocess.CardApplLinkDto;
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.settings.CustomerGroupFeeDto;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.formbean.batchprocess.BatchProcess;
import org.transinfo.cacis.formbean.batchprocess.BatchProcessSuccess;
import org.transinfo.cacis.formbean.batchprocess.BatchProcessForm;

@SuppressWarnings( { "unchecked", "deprecation" })
public class BatchProcessDispatchAction extends BaseDispatchAction {
	
	private Logger logger = Logger.getLogger(BatchProcessDispatchAction.class);

	public ActionForward List(ActionMapping mapping, ActionForm form,
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

		BatchProcessForm objForm = (BatchProcessForm) form;
		objForm.getPreListData();
		objForm.setCardBatchStatus(CommonCodes.BATCH_NEW);
//		LinkedHashMap statusList = (LinkedHashMap)objForm.getCardBatchStatusList();
//		Set set = statusList.entrySet();
//	    Iterator i = set.iterator();
//	    while(i.hasNext()) {
//	         Map.Entry me = (Map.Entry)i.next();
//	         if(me.getKey().equals(CommonCodes.BATCH_NEW)) {
//	        	 objForm.setCardBatchStatus((String) me.getValue());
//	         }
//	         System.out.print(me.getKey() + ": ");
//	         System.out.println(me.getValue());
//	      }
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();

		try {
			//get CardBatch with status are 'N'
			ArrayList<BatchProcess> objBatchProcessList = new ArrayList<BatchProcess>();
			String batchId = request.getParameter("batchId");
			//directed from New Card menu link. after authorized
			if(batchId!=null && !batchId.equals("")) {
				CardBatchDto objCardBatchDto= objCardBatchProcessManager.getCardBatchDto(batchId);
				BatchProcess objBatchProcess = new BatchProcess();
				objBatchProcess.setBatchId(objCardBatchDto.getBatchId());
				objBatchProcess.setNoApplications(String.valueOf(objCardBatchDto.getNoApplications()));
				objBatchProcess.setAuthorizedBy(objCardBatchDto.getAuthorizedBy());
				objBatchProcess.setAuthorizedDate(String.valueOf(objCardBatchDto.getAuthorizedDate()));
				
				ArrayList<ApplicationFormDto> objApplicationFormDtoList = objCardBatchProcessManager.getApplicationFormByBatchId(objCardBatchDto.getBatchId());
				objBatchProcess.setApplList(objApplicationFormDtoList);
				String applicationType = objCardBatchProcessManager.getApplicationType(objApplicationFormDtoList.get(0).getApplicationType());
				objBatchProcess.setApplicationType(applicationType);
				objBatchProcessList.add(objBatchProcess);
				objForm.setBatchProcessList(objBatchProcessList);
				objForm.setApplicationType(objApplicationFormDtoList.get(0).getApplicationType()+"");
				String userId = objForm.getUserId();
				//String issuerId = objForm.getIssuerId();
				//if user type is user branch
				String userType = objCardBatchProcessManager.getUserType(userId);
				if(userType!=null && userType.equals("ISSUSER")) {
					request.setAttribute("ACTION", "report");
				} else {
					request.setAttribute("ACTION", "process");
				}
			} else {
				//access from Batch Process menu link.
				ArrayList<CardBatchDto> cardBatchList = objCardBatchProcessManager.getCardBatch();
				
				if(!cardBatchList.isEmpty()) {
					String applicationType = "";
					String typeID = "";
					Iterator<CardBatchDto> itr = cardBatchList.iterator();
					while (itr.hasNext()) {
						CardBatchDto objCardBatchDto = (CardBatchDto) itr.next();
						BatchProcess objBatchProcess = new BatchProcess();
						objBatchProcess.setBatchId(objCardBatchDto.getBatchId());
						objBatchProcess.setNoApplications(String.valueOf(objCardBatchDto.getNoApplications()));
						objBatchProcess.setAuthorizedBy(objCardBatchDto.getAuthorizedBy());
						objBatchProcess.setAuthorizedDate(String.valueOf(objCardBatchDto.getAuthorizedDate()));
						
						ArrayList<ApplicationFormDto> objApplicationFormDtoList = objCardBatchProcessManager.getApplicationFormByBatchId(objCardBatchDto.getBatchId());
						objBatchProcess.setApplList(objApplicationFormDtoList);
						applicationType = objCardBatchProcessManager.getApplicationType(objApplicationFormDtoList.get(0).getApplicationType());
						typeID = objApplicationFormDtoList.get(0).getApplicationType()+"";
						objBatchProcess.setApplicationType(applicationType);
						objBatchProcessList.add(objBatchProcess);
					}
					objForm.setBatchProcessList(objBatchProcessList);
					objForm.setApplicationType(typeID);
				}
				request.setAttribute("ACTION", "process");
			}
		} catch (Exception e) {
			logger.error(new Object(), e);
			System.out
					.println("Error converting to form bean in NewCardsDispatchAction : "
							+ e.getMessage());
			throw new TPlusException(
					"Error converting to form bean in NewCardsDispatchAction : "
							+ e);
		}
		return mapping.findForward("success");

	}

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		boolean isError = false;

		int pageNo = 0;

		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo"));
		}

		String issuerId = (String) request.getSession(false).getAttribute(
				"ISSUER");
		String userID = (String) request.getSession(false).getAttribute(
				"USERID");

		BatchProcessForm objForm = (BatchProcessForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();
		
		try {
			int x;
		} catch (Exception e) {
			logger.error(new Object(), e);
			System.out.println(e);

			errors.add("Error", new ActionError("error.applicationerror"));
			saveErrors(request, errors);

		}

		return mapping.findForward("success");
	}
}
