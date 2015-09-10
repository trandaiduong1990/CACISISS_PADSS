package org.transinfo.cacis.action.batchprocess;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import org.transinfo.cacis.controller.authorization.SystemParamManager;
import org.transinfo.cacis.controller.batchprocess.CardBatchProcessManager;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.controller.key.KeyIndexManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.controller.useraccess.AdminLoginManager;
import org.transinfo.cacis.dto.authorization.SystemParamDto;
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.formbean.batchprocess.ReplacementBatchProcessSuccess;
import org.transinfo.cacis.formbean.batchprocess.ReplacementCardBPForm;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings( { "unchecked", "deprecation" })
public class ReplacementCardsDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(RenewalCardsDispatchAction.class);

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

		ReplacementCardBPForm objForm = (ReplacementCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();

		// generate the new batch id
		if (objForm.getBatchId() == null || "".equals(objForm.getBatchId())) {
			String batchId = IdsGenartion.GenerateBatchId();
			objForm.setBatchId(batchId);
		}

		try {
			String issuerId = (String) request.getSession(false).getAttribute(
			"ISSUER");
			Collection applicationsList = objCardBatchProcessManager.listReplacementCardApps(issuerId, pageNo);
			objForm.setAppList(applicationsList);

			int totApps = objCardBatchProcessManager.getTotalReplacementCardsApps(issuerId);
			objForm.setTotalNoOfApps(String.valueOf(totApps));

			request.setAttribute("SEARCHLIST", applicationsList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

		} catch (Exception e) {
			logger.error(new Object(), e);
			System.out
			.println("Error converting to form bean in ReplacementCardsDispatchAction : "
					+ e.getMessage());
			throw new TPlusException(
					"Error converting to form bean in ReplacementCardsDispatchAction : "
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
		String userId = (String) request.getSession(false).getAttribute(
		"USERID");

		ReplacementCardBPForm objForm = (ReplacementCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();
		CardManager objCardManager = new CardManager();
		CardProductManager objCardProductManager = new CardProductManager();
		SystemParamManager objSystemParamManager = new SystemParamManager();

		try {

			errors = objForm.validate(mapping, request);

			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				isError = true;
			} else {
				errors = new ActionErrors();
				if (objForm.getUserId().equals(objForm.getAuthUserId())) {
					errors.add("Error", new ActionError(
					"error.batchprocesssameusercannot"));
					saveErrors(request, errors);
					isError = true;
				} else {
					AdminLoginDto objDto = new AdminLoginDto();
					objDto.setIssuerId(objForm.getIssuerId());
					objDto.setUserId(objForm.getAuthUserId());
					objDto.setPassword(objForm.getAuthPassword());

					String remoteIp = InetAddress.getLocalHost().getHostAddress();
					objDto.setRemoteIp(remoteIp);

					AdminLoginManager objManager = new AdminLoginManager();
					String rtnMsg = objManager
					.validateBatchProcessAuthUser(objDto);

					if (!"VALIDUSER".equals(rtnMsg)) {
						errors.add("Error", new ActionError(
						"error.batchprocessauthfailed"));
						saveErrors(request, errors);
						isError = true;
					}
				}

			}

			if (isError) {

				Collection applicationsList = objCardBatchProcessManager.listReplacementCardApps(issuerId, pageNo);
				objForm.setAppList(applicationsList);

				int totApps = objCardBatchProcessManager.getTotalReplacementCardsApps(issuerId);
				objForm.setTotalNoOfApps(String.valueOf(totApps));

				request.setAttribute("SEARCHLIST", applicationsList);
				request.setAttribute("PAGENO", new Integer(pageNo).toString());

				return mapping.findForward("success");
			} else {

				Map indexesList = new HashMap();
				KeyIndexManager objKeyIndexManager = new KeyIndexManager();

				// check all the card products have PPK values
				try{
					indexesList  = objKeyIndexManager.getIndexesList(issuerId);
					if(indexesList.isEmpty()){
						errors = new ActionErrors();
						errors.add("Error", new ActionError("error.cardproductsnoindexes"));
						saveErrors(request, errors);

						Collection applicationsList = objCardBatchProcessManager.listReplacementCardApps(issuerId, pageNo);

						objForm.setAppList(applicationsList);
						request.setAttribute("SEARCHLIST", applicationsList);
						request.setAttribute("PAGENO", new Integer(pageNo).toString());

						return mapping.findForward("success");
					}
				}catch (Exception e) {
					logger.error(new Object(), e);
					System.out.println("Error on checking PPK for card products " + e);
					throw new TPlusException("Error on checking PPK for card products "+ e);
				}

				SystemParamDto objSystemParamDto = objSystemParamManager.get("ASP");
				if(objSystemParamDto == null){
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.nosystemparam"));
					saveErrors(request, errors);

					Collection applicationsList = objCardBatchProcessManager.listReplacementCardApps(issuerId, pageNo);

					objForm.setAppList(applicationsList);
					request.setAttribute("SEARCHLIST", applicationsList);
					request.setAttribute("PAGENO", new Integer(pageNo).toString());

					return mapping.findForward("success");
				}

				int totalApps = 0;
				ReplacementBatchProcessSuccess objReplacementBatchProcessSuccess = null;
				ArrayList appCardsList = new ArrayList();
				StringBuilder genAppsIds = new StringBuilder();
				StringBuilder newCardsNos = new StringBuilder();

				// get all the applications
				ArrayList allApps = objCardBatchProcessManager.getAllReplacementApplication(issuerId);
				totalApps = allApps.size();

				int count = 0;
				int cardCount = 0;
				ArrayList successApps = new ArrayList();

				Iterator itr = allApps.iterator();
				while (itr.hasNext()) {
					CardReplacementDto objCardReplacementDto = (CardReplacementDto) itr.next();
					
					CardsDto objCardsDto = objCardManager.getCard(String.valueOf(objCardReplacementDto.getCardNumber()));
					  
					CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(objCardsDto.getCardProductId());

					int cardHolderType = objCardsDto.getCardHolderType();

					//String oldExpDate = objCardsDto.getCardExpDate();
					String oldExpDate = DateUtil.convertIssueDateToDateString(new Date());

					String extendInterval = String.valueOf(objSystemParamDto.getExpireDateExtend());

					ApplicationProcessDto objApplicationProcessDtoOriginal = objCardBatchProcessManager.getCustomerById(objCardsDto.getCustomerId());

					ApplicationProcessDto objApplicationProcessDtoPrimary = new ApplicationProcessDto();
					if(objCardsDto.getCardHolderType() == 2){
						objApplicationProcessDtoPrimary = objCardBatchProcessManager.getCustomerById(objApplicationProcessDtoOriginal.getParenetCustomerId());
					}else{
						objApplicationProcessDtoPrimary = objApplicationProcessDtoOriginal;
					}

					try {
						if (objApplicationProcessDtoOriginal != null) {

							// assign required fields
							objApplicationProcessDtoOriginal.setUserId(objForm.getUserId());

							if("S".equals(objCardProductDto.getNewOrSameCardnumberForReplacement())){
								boolean appProcessRes = objCardBatchProcessManager.replacementBatchProcessForSameCardNo(objApplicationProcessDtoOriginal, objCardsDto, indexesList, objCardProductDto, objCardReplacementDto, objForm.getBatchId(), userId, extendInterval, oldExpDate);
								if (appProcessRes) {
									count++;
									successApps.add(objCardReplacementDto);
									cardCount++;

									genAppsIds.append("'");
									genAppsIds.append(objCardReplacementDto.getApplicationId());
									genAppsIds.append("',");

									newCardsNos.append("'");
									newCardsNos.append(String.valueOf(objCardReplacementDto.getCardNumber()));
									newCardsNos.append("',");

									objReplacementBatchProcessSuccess = new ReplacementBatchProcessSuccess();
									objReplacementBatchProcessSuccess.setApplicationId(objCardReplacementDto.getApplicationId());
									objReplacementBatchProcessSuccess.setCustomerName(objApplicationProcessDtoOriginal.getCustomerName());
									objReplacementBatchProcessSuccess.setOldCardNumber(String.valueOf(objCardReplacementDto.getCardNumber()));
									objReplacementBatchProcessSuccess.setNewCardNumber(String.valueOf(objCardReplacementDto.getCardNumber()));
									objReplacementBatchProcessSuccess.setReason(objCardReplacementDto.getRemarks());
									objReplacementBatchProcessSuccess.setAppliedDate(DateUtil.convertDateToDateString(objCardReplacementDto.getUpdatedDate()));
									appCardsList.add(objReplacementBatchProcessSuccess);
								}
							}else{
								boolean appProcessRes = objCardBatchProcessManager.replacementBatchProcessForNewCardNo(objApplicationProcessDtoPrimary, objCardReplacementDto, indexesList, objForm.getBatchId(), cardHolderType, objCardsDto, userId, extendInterval, oldExpDate, objApplicationProcessDtoOriginal, issuerId, objCardProductDto);
								if (appProcessRes) {
									count++;
									successApps.add(objApplicationProcessDtoOriginal);

									Set objCustomerAccounts = objApplicationProcessDtoPrimary.getCustomerAccount();

									if(objCustomerAccounts != null && objCustomerAccounts.size() > 0){


										for (Iterator it = objCustomerAccounts.iterator(); it.hasNext();) {
											CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto) it.next();

											Set objCards = objCustomerAccountDto.getCustomerCards();
											for (Iterator iter = objCards.iterator(); iter.hasNext();) {

												CardsDto objCardsNewDto = (CardsDto) iter.next();

												if(objCardsNewDto.getTrack1() != null){
													cardCount++;

													genAppsIds.append("'");
													genAppsIds.append(objCardReplacementDto.getApplicationId());
													genAppsIds.append("',");

													newCardsNos.append("'");
													newCardsNos.append(String.valueOf(objCardsNewDto.getCardNumber()));
													newCardsNos.append("',");

													objReplacementBatchProcessSuccess = new ReplacementBatchProcessSuccess();
													objReplacementBatchProcessSuccess.setApplicationId(objCardReplacementDto.getApplicationId());
													objReplacementBatchProcessSuccess.setCustomerName(objApplicationProcessDtoOriginal.getCustomerName());
													objReplacementBatchProcessSuccess.setOldCardNumber(String.valueOf(objCardsDto.getMaskedCardNo()));
													objReplacementBatchProcessSuccess.setNewCardNumber(String.valueOf(objCardsNewDto.getMaskedCardNo()));
													objReplacementBatchProcessSuccess.setReason(objCardReplacementDto.getRemarks());
													objReplacementBatchProcessSuccess.setAppliedDate(DateUtil.convertDateToDateString(objCardReplacementDto.getUpdatedDate()));
													appCardsList.add(objReplacementBatchProcessSuccess);
												}
											}

										}

									}else{

										Set objCards = objCardsDto.getCustAccountDto().getCustomerCards();
										for (Iterator iter = objCards.iterator(); iter.hasNext();) {

											CardsDto objCardsNewDto = (CardsDto) iter.next();

											if(objCardsNewDto.getTrack1() != null){
												cardCount++;

												genAppsIds.append("'");
												genAppsIds.append(objCardReplacementDto.getApplicationId());
												genAppsIds.append("',");

												newCardsNos.append("'");
												newCardsNos.append(String.valueOf(objCardsNewDto.getCardNumber()));
												newCardsNos.append("',");

												objReplacementBatchProcessSuccess = new ReplacementBatchProcessSuccess();
												objReplacementBatchProcessSuccess.setApplicationId(objCardReplacementDto.getApplicationId());
												objReplacementBatchProcessSuccess.setCustomerName(objApplicationProcessDtoOriginal.getCustomerName());
												objReplacementBatchProcessSuccess.setOldCardNumber(String.valueOf(objCardsDto.getMaskedCardNo()));
												objReplacementBatchProcessSuccess.setNewCardNumber(String.valueOf(objCardsNewDto.getMaskedCardNo()));
												objReplacementBatchProcessSuccess.setReason(objCardReplacementDto.getRemarks());
												objReplacementBatchProcessSuccess.setAppliedDate(DateUtil.convertDateToDateString(objCardReplacementDto.getUpdatedDate()));
												appCardsList.add(objReplacementBatchProcessSuccess);
											}
										}

									}
								}
							}
						}
					} catch (Exception e) {
						logger.error(new Object(), e);
						System.out
						.println("Exception on card number generation "
								+ e);
					}
				}

				// insert into batch
				if (successApps.size() > 0) {
					CardBatchDto objBatchDto = new CardBatchDto();

					if (totalApps == count) {
						objBatchDto.setStatus(CommonCodes.BATCH_COMPLETED);
					} else {
						objBatchDto.setStatus(CommonCodes.BATCH_COMPLETED_PARTIAl);
					}

					objBatchDto.setBatchId(objForm.getBatchId());
					objBatchDto.setNoOfCardsGenerated(cardCount);
					objBatchDto.setErrorMsg("");
					objBatchDto.setAuthorizedBy(objForm.getAuthUserId());
					objBatchDto.setAuthorizedDate(new Date());
					objBatchDto.setUpdateddBy(objForm.getUserId());
					objBatchDto.setUpdatedDate(new Date());

					boolean overallSuccess = objCardBatchProcessManager
					.addBatch(objBatchDto);
					if (overallSuccess) {

						objForm.setSuccessAppsPrimaryList(appCardsList);
						objForm.setAppIds(genAppsIds.toString().substring(0, (genAppsIds.toString().length() - 1)));
						objForm.setNewCardNos(newCardsNos.toString().substring(0, (newCardsNos.toString().length() - 1)));

						return mapping.findForward("reportpage");
					}

				} else {

					Collection applicationsList = objCardBatchProcessManager.listReplacementCardApps(issuerId, pageNo);
					objForm.setAppList(applicationsList);

					int totApps = objCardBatchProcessManager.getTotalReplacementCardsApps(issuerId);
					objForm.setTotalNoOfApps(String.valueOf(totApps));

					request.setAttribute("SEARCHLIST", applicationsList);
					request.setAttribute("PAGENO", new Integer(pageNo)
					.toString());

					errors.add("Error", new ActionError(
					"error.batchprocessfailed"));
					saveErrors(request, errors);
				}

			}
		} catch (Exception e) {
			logger.error(new Object(), e);
			System.out.println(e);

			Collection applicationsList = objCardBatchProcessManager.listReplacementCardApps(issuerId, pageNo);
			objForm.setAppList(applicationsList);

			int totApps = objCardBatchProcessManager.getTotalReplacementCardsApps(issuerId);
			objForm.setTotalNoOfApps(String.valueOf(totApps));

			request.setAttribute("SEARCHLIST", applicationsList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

			errors.add("Error", new ActionError("error.applicationerror"));
			saveErrors(request, errors);

		}

		return mapping.findForward("success");
	}

}
