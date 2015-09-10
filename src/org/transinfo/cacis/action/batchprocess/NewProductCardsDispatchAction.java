package org.transinfo.cacis.action.batchprocess;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.settings.CustomerGroupFeeDto;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.formbean.batchprocess.BatchProcessSuccess;
import org.transinfo.cacis.formbean.batchprocess.NewProductCardBPForm;

@SuppressWarnings( { "unchecked", "deprecation" })
public class NewProductCardsDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(NewProductCardsDispatchAction.class);

	public ActionForward List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		Cookie nameCookie = new Cookie("USERNAME", ""); 
		nameCookie.setPath("/"); 
		nameCookie.setMaxAge(0);// delete this cookie 

		Cookie passwordCookie = new Cookie("PASSWORD", ""); 
		passwordCookie.setPath("/"); 
		passwordCookie.setMaxAge(0);// delete this cookie 

		response.addCookie(nameCookie); 
		response.addCookie(passwordCookie);

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

		NewProductCardBPForm objForm = (NewProductCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();

		// generate the new batch id
		if (objForm.getBatchId() == null || "".equals(objForm.getBatchId())) {
			String batchId = IdsGenartion.GenerateBatchId();
			objForm.setBatchId(batchId);
		}

		try {

			String issuerId = (String) request.getSession(false).getAttribute("ISSUER");
			Collection applicationsList = objCardBatchProcessManager.listNewProduct(issuerId, pageNo);
			objForm.setAppList(applicationsList);

			int totApps = objCardBatchProcessManager.getTotalCardsAppsNewProduct(issuerId);
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

		String issuerId = (String) request.getSession(false).getAttribute("ISSUER");
		String userID = (String) request.getSession(false).getAttribute("USERID");

		NewProductCardBPForm objForm = (NewProductCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();

		try {

			errors = objForm.validate(mapping, request);

			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				isError = true;
			} else {
				errors = new ActionErrors();
				if (objForm.getUserId().equals(objForm.getAuthUserId())) {
					errors.add("Error", new ActionError("error.batchprocesssameusercannot"));
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
					String rtnMsg = objManager.validateBatchProcessAuthUser(objDto);

					logger.info("Authentication result message :: " + rtnMsg);

					if (!"VALIDUSER".equals(rtnMsg)) {
						errors.add("Error", new ActionError("error.batchprocessauthfailed"));
						saveErrors(request, errors);
						isError = true;
					}
				}

			}

			if (isError) {

				Collection applicationsList = objCardBatchProcessManager.listNewProduct(issuerId, pageNo);
				objForm.setAppList(applicationsList);

				int totApps = objCardBatchProcessManager.getTotalCardsAppsNewProduct(issuerId);
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

						Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo);
						objForm.setAppList(applicationsList);

						int totApps = objCardBatchProcessManager.getTotalCardsApps(issuerId);
						objForm.setTotalNoOfApps(String.valueOf(totApps));

						request.setAttribute("SEARCHLIST", applicationsList);
						request.setAttribute("PAGENO", new Integer(pageNo).toString());

						return mapping.findForward("success");
					}
				}catch (Exception e) {
					logger.error(new Object(), e);
					System.out.println("Error on checking PPK for card products " + e);
					throw new TPlusException("Error on checking PPK for card products "+ e);
				}

				int totalApps = 0;
				BatchProcessSuccess objBatchProcessSuccess = null;
				ArrayList appPrimaryCardsList = new ArrayList();
				StringBuilder genCardNos = new StringBuilder();

				// get all the applications
				ArrayList allApps = objCardBatchProcessManager.getAllApplicationNewProduct(issuerId);
				totalApps = allApps.size();

				int count = 0;
				int cardCount = 0;
				ArrayList successApps = new ArrayList();

				Iterator itr = allApps.iterator();
				while (itr.hasNext()) {
					AddProductDto objAddProductDto = (AddProductDto) itr.next();

					ApplicationProcessDto objApplicationProcessDto = objCardBatchProcessManager.getCustomerById(objAddProductDto.getCustomerId());

					try {
						if (objApplicationProcessDto != null) {

							CustomerGroupFeeDto objCustomerGroupFeeDto = objCardBatchProcessManager.getCustomerGF(objAddProductDto.getNewCardProduct(), objApplicationProcessDto.getCustomerTypeId());

							int embossNameLenght = ICacisiss.IEmboss.TRACK1_LENGTH;

							// assign required fields
							objApplicationProcessDto.setUserId(objForm.getUserId());

							objApplicationProcessDto.setCollectoralAmt(objAddProductDto.getCollectoralAmt());
							objApplicationProcessDto.setCollectoralAccount(objAddProductDto.getCollectoralAccount());
							objApplicationProcessDto.setAutoPayAccount(objAddProductDto.getAutoPayAccount());
							objApplicationProcessDto.setCheckedautoPayAccountOn(objAddProductDto.getCheckedautoPayAccountOn());

							boolean appProcessRes = objCardBatchProcessManager.batchProcessNewProduct(objApplicationProcessDto, objAddProductDto, indexesList, objForm.getBatchId(), userID, issuerId, embossNameLenght , objCustomerGroupFeeDto);

							if (appProcessRes) {
								count++;
								successApps.add(objApplicationProcessDto);

								Set objCustomerAccounts = objApplicationProcessDto.getCustomerAccount();
								for (Iterator it = objCustomerAccounts.iterator(); it.hasNext();) {
									CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto) it.next();

									Set objCards = objCustomerAccountDto.getCustomerCards();
									for (Iterator iter = objCards.iterator(); iter.hasNext();) {

										CardsDto objCardsNewDto = (CardsDto) iter.next();

										if(objCardsNewDto.getTrack1() != null){

											cardCount++;

											genCardNos.append("'");
											genCardNos.append(objCardsNewDto.getCardNumber());
											genCardNos.append("',");

											objBatchProcessSuccess = new BatchProcessSuccess();
											objBatchProcessSuccess.setApplicationId(String.valueOf(objAddProductDto.getSno()));
											objBatchProcessSuccess.setCardNumber(objCardsNewDto.getCardNumber());
											objBatchProcessSuccess.setCardExpDate(objCardsNewDto.getCardExpDate());

											objBatchProcessSuccess.setCustomerName(objAddProductDto.getCustomerName());
											objBatchProcessSuccess.setIdNumber(objAddProductDto.getNric());
											appPrimaryCardsList.add(objBatchProcessSuccess);
											
											break;

										}
									}
								}

							}

						}

					} catch (Exception e) {
						logger.error(new Object(), e);
						System.out.println("Exception on card number generation " + e);
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
						objForm.setCardNos(genCardNos.toString().substring(0, (genCardNos.toString().length() - 1)));

						return mapping.findForward("reportpage");
					}

				} else {

					Collection applicationsList = objCardBatchProcessManager.listNewProduct(issuerId, pageNo);
					objForm.setAppList(applicationsList);

					int totApps = objCardBatchProcessManager.getTotalCardsAppsNewProduct(issuerId);
					objForm.setTotalNoOfApps(String.valueOf(totApps));

					request.setAttribute("SEARCHLIST", applicationsList);
					request.setAttribute("PAGENO", new Integer(pageNo).toString());

					errors.add("Error", new ActionError("error.batchprocessfailed"));
					saveErrors(request, errors);
				}

			}
		} catch (Exception e) {
			logger.error(new Object(), e);
			System.out.println(e);

			Collection applicationsList = objCardBatchProcessManager.listNewProduct(issuerId, pageNo);
			objForm.setAppList(applicationsList);

			int totApps = objCardBatchProcessManager.getTotalCardsAppsNewProduct(issuerId);
			objForm.setTotalNoOfApps(String.valueOf(totApps));

			request.setAttribute("SEARCHLIST", applicationsList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

			errors.add("Error", new ActionError("error.applicationerror"));
			saveErrors(request, errors);

		}

		return mapping.findForward("success");
	}

}
