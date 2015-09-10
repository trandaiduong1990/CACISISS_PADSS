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
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.controller.authorization.SystemParamManager;
import org.transinfo.cacis.controller.batchprocess.CardBatchProcessManager;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.controller.key.KeyIndexManager;
import org.transinfo.cacis.controller.useraccess.AdminLoginManager;
import org.transinfo.cacis.dto.authorization.SystemParamDto;
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.formbean.batchprocess.ProductChangeBatchProcessSuccess;
import org.transinfo.cacis.formbean.batchprocess.ProductChangeCardBPForm;
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings( { "unchecked", "deprecation" })
public class ProductChangeCardsDispatchAction extends BaseDispatchAction {
	
	private Logger logger = Logger.getLogger(ProductChangeCardsDispatchAction.class);

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

		ProductChangeCardBPForm objForm = (ProductChangeCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();

		// generate the new batch id
		if (objForm.getBatchId() == null || "".equals(objForm.getBatchId())) {
			String batchId = IdsGenartion.GenerateBatchId();
			objForm.setBatchId(batchId);
		}

		try {
			String issuerId = (String) request.getSession(false).getAttribute(
					"ISSUER");
			Collection applicationsList = objCardBatchProcessManager
					.listProductChange(issuerId, pageNo);
			objForm.setAppList(applicationsList);

			int totApps = objCardBatchProcessManager
					.getTotalProductChangeCardsApps(issuerId);
			objForm.setTotalNoOfApps(String.valueOf(totApps));

			request.setAttribute("SEARCHLIST", applicationsList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

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
		String userId = (String) request.getSession(false).getAttribute(
				"USERID");

		ProductChangeCardBPForm objForm = (ProductChangeCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();
		SystemParamManager objSystemParamManager = new SystemParamManager();
		CardManager objCardManager = new CardManager();
		//CardProductManager objCardProductManager = new CardProductManager();

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

				Collection applicationsList = objCardBatchProcessManager
						.listProductChange(issuerId, pageNo);
				objForm.setAppList(applicationsList);

				int totApps = objCardBatchProcessManager
						.getTotalProductChangeCardsApps(issuerId);
				objForm.setTotalNoOfApps(String.valueOf(totApps));

				request.setAttribute("SEARCHLIST", applicationsList);
				request.setAttribute("PAGENO", new Integer(pageNo).toString());

				return mapping.findForward("success");
			} else {

				Map indexesList = new HashMap();
				KeyIndexManager objKeyIndexManager = new KeyIndexManager();

				// check all the card products have PPK values
				try {
					indexesList = objKeyIndexManager.getIndexesList(issuerId);
					if (indexesList.isEmpty()) {
						errors = new ActionErrors();
						errors.add("Error", new ActionError(
								"error.cardproductsnoindexes"));
						saveErrors(request, errors);

						Collection applicationsList = objCardBatchProcessManager
								.listProductChange(issuerId, pageNo);
						objForm.setAppList(applicationsList);

						int totApps = objCardBatchProcessManager
								.getTotalProductChangeCardsApps(issuerId);
						objForm.setTotalNoOfApps(String.valueOf(totApps));

						request.setAttribute("SEARCHLIST", applicationsList);
						request.setAttribute("PAGENO", new Integer(pageNo)
								.toString());

						return mapping.findForward("success");
					}
				} catch (Exception e) {
					logger.error(new Object(), e);
					System.out
							.println("Error on checking PPK for card products "
									+ e);
					throw new TPlusException(
							"Error on checking PPK for card products " + e);
				}
				

				SystemParamDto objSystemParamDto = objSystemParamManager.get("ASP");
				if(objSystemParamDto == null){
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.nosystemparam"));
					saveErrors(request, errors);
					
					Collection applicationsList = objCardBatchProcessManager.listProductChange(
							issuerId, pageNo);

					objForm.setAppList(applicationsList);
					request.setAttribute("SEARCHLIST", applicationsList);
					request.setAttribute("PAGENO", new Integer(pageNo).toString());
					
					return mapping.findForward("success");
				}

				int totalApps = 0;
				ProductChangeBatchProcessSuccess objProductChangeBatchProcessSuccess = null;
				ArrayList appPrimaryCardsList = new ArrayList();
				StringBuilder genCardNos = new StringBuilder();
				StringBuilder genOldCardNos = new StringBuilder();

				// get all the applications
				ArrayList allApps = objCardBatchProcessManager.getAllProductChangeApplication(issuerId);
				totalApps = allApps.size();

				int count = 0;
				int cardCount = 0;
				ArrayList successApps = new ArrayList();

				Iterator itr = allApps.iterator();
				while (itr.hasNext()) {
					CardChangeDto objChangeDto = (CardChangeDto) itr.next();
					CardsDto objCardsDto = objCardManager.getCard(String.valueOf(objChangeDto.getCardNo()));

					//String oldExpDate = objCardsDto.getCardExpDate();
					String oldExpDate = DateUtil.convertIssueDateToDateString(new Date());
					
					String extendInterval = String.valueOf(objSystemParamDto.getExpireDateExtend());
					
					//CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(objCardsDto.getCardProductId());
					int embossNameLength = ICacisiss.IEmboss.TRACK1_LENGTH;

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

							boolean appProcessRes = objCardBatchProcessManager.productChangeBatchProcess(objApplicationProcessDtoPrimary, objChangeDto, indexesList, objForm.getBatchId(), userId, extendInterval, oldExpDate, objApplicationProcessDtoOriginal, objCardsDto, issuerId, embossNameLength);
							
							if (appProcessRes) {
								count++;
								successApps.add(objApplicationProcessDtoOriginal);

								Set objCustomerAccounts = objApplicationProcessDtoPrimary.getCustomerAccount();

								for (Iterator it = objCustomerAccounts.iterator(); it.hasNext();) {
									CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto) it.next();

									Set objCards = objCustomerAccountDto.getCustomerCards();
									
									for (Iterator iter = objCards.iterator(); iter.hasNext();) {

										CardsDto objCardsNewDto = (CardsDto) iter.next();

										if (objCardsNewDto.getTrack1() != null) {
											cardCount++;

											// new card numbers
											genCardNos.append("'");
											genCardNos.append(String.valueOf(objCardsNewDto.getCardNumber()));
											genCardNos.append("',");
											
											// old card numbers
											genOldCardNos.append("'");
											genOldCardNos.append(String.valueOf(objCardsNewDto.getOldCradNo()));
											genOldCardNos.append("',");

											objProductChangeBatchProcessSuccess = new ProductChangeBatchProcessSuccess();
											objProductChangeBatchProcessSuccess.setOldCardNo(objCardsNewDto.getOldCradNo());
											objProductChangeBatchProcessSuccess.setCustomerName(objCardsNewDto.getCustName());
											objProductChangeBatchProcessSuccess.setIdNumber(objCardsNewDto.getNricId());
											objProductChangeBatchProcessSuccess.setNewCardNumber(String.valueOf(objCardsNewDto.getCardNumber()));
											objProductChangeBatchProcessSuccess.setCardExpDate(objCardsNewDto.getCardExpDate());
											appPrimaryCardsList.add(objProductChangeBatchProcessSuccess);
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

					boolean overallSuccess = objCardBatchProcessManager.addBatch(objBatchDto);
					if (overallSuccess) {

						objForm.setSuccessAppsPrimaryList(appPrimaryCardsList);
						objForm.setNoOfSuccessApps(String.valueOf(successApps.size()));
						objForm.setCardNos(genCardNos.toString().substring(0, (genCardNos.toString().length() - 1)));
						objForm.setOldCardNos(genOldCardNos.toString().substring(0, (genOldCardNos.toString().length() - 1)));

						return mapping.findForward("reportpage");
					}

				} else {
					Collection applicationsList = objCardBatchProcessManager
							.listProductChange(issuerId, pageNo);
					objForm.setAppList(applicationsList);

					int totApps = objCardBatchProcessManager
							.getTotalProductChangeCardsApps(issuerId);
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

			Collection applicationsList = objCardBatchProcessManager
					.listProductChange(issuerId, pageNo);
			objForm.setAppList(applicationsList);

			int totApps = objCardBatchProcessManager
					.getTotalProductChangeCardsApps(issuerId);
			objForm.setTotalNoOfApps(String.valueOf(totApps));

			request.setAttribute("SEARCHLIST", applicationsList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

			errors.add("Error", new ActionError("error.applicationerror"));
			saveErrors(request, errors);

		}

		return mapping.findForward("success");
	}

}
