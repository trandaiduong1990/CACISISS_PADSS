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
import org.transinfo.cacis.controller.settings.BranchManager;
import org.transinfo.cacis.controller.useraccess.AdminLoginManager;
import org.transinfo.cacis.controller.useraccess.UserSetupManager;
import org.transinfo.cacis.dto.batchprocess.CardApplLinkDto;
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CustomerGroupFeeDto;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.batchprocess.BatchProcessSuccess;
import org.transinfo.cacis.formbean.batchprocess.NewCardBPForm;

@SuppressWarnings( { "unchecked", "deprecation" })
public class NewCardsDispatchAction extends BaseDispatchAction {
	
	private Logger logger = Logger.getLogger(NewCardsDispatchAction.class);

	public ActionForward changeBranch(ActionMapping mapping, ActionForm form,
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
		NewCardBPForm objForm = (NewCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();
		try {
			String issuerId = (String) request.getSession(false).getAttribute(
					"ISSUER");
			String userId = (String) request.getSession(false).getAttribute("USERID");
			//check branch 
			UserSetupManager objUserSetupManager = new UserSetupManager();
			BranchManager objBranchManager = new BranchManager();
				
				objForm.setBranchList(objCardBatchProcessManager.getBranch(null,"ALL"));
				Collection applicationsList;
				if(objForm.getBranchId().equals("")) {
					applicationsList = objCardBatchProcessManager.list(issuerId, pageNo, null, "ALL");
				} else {
					BranchDto objBranchDto = objBranchManager.getBranchDto(objForm.getBranchId());
					applicationsList = objCardBatchProcessManager.list(issuerId, pageNo, objBranchDto, "");
				}
				objForm.setAppList(applicationsList);
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
	public ActionForward List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		/*response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
		int cookieLenght = cookies.length;
		for (int i = 0; i < cookieLenght; i++) {
		Cookie cookie = cookies[i];
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setDomain(request.getHeader("host"));
		response.addCookie(cookie);
		}*/
		
		/*HttpSession session = request.getSession();
		Object userId = session.getAttribute("USERID");
		
		if(userId == null){
			return mapping.findForward("sessionExpired");
		}*/
		
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

		NewCardBPForm objForm = (NewCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();

		// generate the new batch id
		if (objForm.getBatchId() == null || "".equals(objForm.getBatchId())) {
			String batchId = IdsGenartion.GenerateBatchId();
			objForm.setBatchId(batchId);
		}

		try {
			String issuerId = (String) request.getSession(false).getAttribute(
					"ISSUER");
			String userId = (String) request.getSession(false).getAttribute("USERID");
			//check branch 
			UserSetupManager objUserSetupManager = new UserSetupManager();
			BranchManager objBranchManager = new BranchManager();
			UserMasterDto objUserMasterDto = objUserSetupManager.getUserMasterForm(issuerId, userId);
			if(null != objUserMasterDto) {
				String branchId = objUserMasterDto.getBranchId();
				objForm.setBranchId(branchId);
				if(branchId.equals("ALL")) {
					objForm.setBranchList(objCardBatchProcessManager.getBranch(null,"ALL"));
					Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo, null, "ALL");
					objForm.setAppList(applicationsList);
					request.setAttribute("SEARCHLIST", applicationsList);
					request.setAttribute("PAGENO", new Integer(pageNo).toString());
				} else {
					BranchDto objBranchDto = objBranchManager.getBranchDto(branchId);
					objForm.setBranchList(objCardBatchProcessManager.getBranch(objBranchDto,""));
					Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo, objBranchDto, "");
					objForm.setAppList(applicationsList);
					request.setAttribute("SEARCHLIST", applicationsList);
					request.setAttribute("PAGENO", new Integer(pageNo).toString());
				}
			// The case when usserId = ASPSUPERADMIN and issuerId = Issuer1
			} else {
				objForm.setBranchList(objCardBatchProcessManager.getBranch(null,"ALL"));
				Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo, null, "ALL");
				objForm.setAppList(applicationsList);
				request.setAttribute("SEARCHLIST", applicationsList);
				request.setAttribute("PAGENO", new Integer(pageNo).toString());
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

	/*public ActionForward process(ActionMapping mapping, ActionForm form,
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

		NewCardBPForm objForm = (NewCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();
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
					
					logger.info("Authentication result message :: " + rtnMsg);

					if (!"VALIDUSER".equals(rtnMsg)) {
						errors.add("Error", new ActionError(
								"error.batchprocessauthfailed"));
						saveErrors(request, errors);
						isError = true;
					}
				}

			}

			if (isError) {

				Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo);
				objForm.setAppList(applicationsList);
				
				int totApps = objCardBatchProcessManager.getTotalCardsApps(issuerId);
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
				ArrayList appSuppCardsList = new ArrayList();
        		StringBuilder genCardNos = new StringBuilder();

				// get all the applications
				ArrayList allApps = objCardBatchProcessManager.getAllApplication(issuerId);
				totalApps = allApps.size();

				int count = 0;
				int cardCount = 0;
				ArrayList successApps = new ArrayList();

				Iterator itr = allApps.iterator();
				while (itr.hasNext()) {
					ApplicationFormDto objApplicationFormDto = (ApplicationFormDto) itr.next();

					ApplicationProcessDto objApplicationProcessDto = objCardBatchProcessManager.getPrimaryCustomer(objApplicationFormDto.getApplicationId());
					ApplicationProcessDto objApplicationProcessDtoSuppl = new ApplicationProcessDto();
					
					if(objApplicationFormDto.getCheckedSupplCardRequired() == 'Y'){
						objApplicationProcessDtoSuppl = objCardBatchProcessManager.getSupplCustomer(objApplicationFormDto.getApplicationId());
					}
					
					try {
						if (objApplicationProcessDto != null) {
							
							CustomerGroupFeeDto objCustomerGroupFeeDto = objCardBatchProcessManager.getCustomerGF(objApplicationFormDto.getSelectedAppCardProducts(), objApplicationProcessDto.getCustomerTypeId());
							
							//CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(objApplicationProcessDto.getSelectedAppCardProducts());
							int embossNameLenght = ICacisiss.IEmboss.TRACK1_LENGTH;
							
							// assign required fields
							objApplicationProcessDto.setUserId(objForm.getUserId());
							objApplicationProcessDto.setCheckedSupplCardRequired(objApplicationFormDto.getCheckedSupplCardRequired());
							objApplicationProcessDto.setCheckedAtmLink(objApplicationFormDto.getCheckedAtmLink());
							objApplicationProcessDto.setSavingAccount(objApplicationFormDto.getSavingAccount());
							objApplicationProcessDto.setCheckingAccount(objApplicationFormDto.getCheckingAccount());
							objApplicationProcessDto.setSupplmenatryId(objApplicationProcessDtoSuppl.getCustomerId());
							objApplicationProcessDto.setDefaultAccount(objApplicationFormDto.getDefaultAccount());
							
							objApplicationProcessDto.setCollectoralAmt(objApplicationFormDto.getCollectoralAmt());
							objApplicationProcessDto.setCollectoralAccount(objApplicationFormDto.getCollectoralAccount());
							objApplicationProcessDto.setAutoPayAccount(objApplicationFormDto.getAutoPayAccount());
							objApplicationProcessDto.setCheckedautoPayAccountOn(objApplicationFormDto.getCheckedautoPayAccountOn());

							boolean appProcessRes = objCardBatchProcessManager.batchProcess(objApplicationProcessDto, objApplicationProcessDtoSuppl, objApplicationFormDto, indexesList, objForm.getBatchId(), userID, issuerId, embossNameLenght, objApplicationFormDto.getImmeidateProcess(), objCustomerGroupFeeDto);
							if (appProcessRes) {
								count++;
								successApps.add(objApplicationProcessDto);
								
								Set objCustomerAccounts = objApplicationProcessDto.getCustomerAccount();
								
								for (Iterator it = objCustomerAccounts.iterator(); it.hasNext();) {
									CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto) it.next();
									
									Set objCards = objCustomerAccountDto.getCustomerCards();
									for (Iterator iter = objCards.iterator(); iter.hasNext();) {

										CardsDto objCardsDto = (CardsDto) iter.next();
										cardCount++;
										
										genCardNos.append("'");
										genCardNos.append(objCardsDto.getCardNumber());
										genCardNos.append("',");
										
										objBatchProcessSuccess = new BatchProcessSuccess();
										objBatchProcessSuccess.setApplicationId(objApplicationProcessDto.getApplicationId());
										objBatchProcessSuccess.setMaskedCardno(objCardsDto.getMaskedCardNo());
										objBatchProcessSuccess.setCardExpDate(objCardsDto.getCardExpDate());
										
										if (objCardsDto.getCardHolderType() == CommonCodes.PRIMARYCARD_HOLDER) {
											objBatchProcessSuccess.setCustomerName(objApplicationFormDto.getCustomerName());
											objBatchProcessSuccess.setIdNumber(objApplicationFormDto.getIdNumber());
											appPrimaryCardsList.add(objBatchProcessSuccess);
										} else {
											objBatchProcessSuccess.setCustomerName(objApplicationFormDto.getSupplCustomerName());
											objBatchProcessSuccess.setIdNumber(objApplicationFormDto.getSupplIdNumber());
											appSuppCardsList.add(objBatchProcessSuccess);
										}
									}
									
								}
								
								if(objApplicationFormDto.getCheckedSupplCardRequired() == 'Y'){
									Set objCustomerAccountsSuppl = objApplicationProcessDtoSuppl.getCustomerAccount();
									
									for (Iterator it = objCustomerAccountsSuppl.iterator(); it.hasNext();) {
										CustomerAccountDto objCustomerAccountDto = (CustomerAccountDto) it.next();
										
										Set objCards = objCustomerAccountDto.getCustomerCards();
										for (Iterator iter = objCards.iterator(); iter.hasNext();) {

											CardsDto objCardsDto = (CardsDto) iter.next();
											cardCount++;
											
											genCardNos.append("'");
											genCardNos.append(objCardsDto.getCardNumber());
											genCardNos.append("',");
											
											objBatchProcessSuccess = new BatchProcessSuccess();
											objBatchProcessSuccess.setApplicationId(objApplicationProcessDto.getApplicationId());
											objBatchProcessSuccess.setMaskedCardno(objCardsDto.getMaskedCardNo());
											objBatchProcessSuccess.setCardExpDate(objCardsDto.getCardExpDate());
											
											if (objCardsDto.getCardHolderType() == CommonCodes.PRIMARYCARD_HOLDER) {
												objBatchProcessSuccess.setCustomerName(objApplicationFormDto.getCustomerName());
												objBatchProcessSuccess.setIdNumber(objApplicationFormDto.getIdNumber());
												appPrimaryCardsList.add(objBatchProcessSuccess);
											} else {
												objBatchProcessSuccess.setCustomerName(objApplicationFormDto.getSupplCustomerName());
												objBatchProcessSuccess.setIdNumber(objApplicationFormDto.getSupplIdNumber());
												appSuppCardsList.add(objBatchProcessSuccess);
											}
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
						objBatchDto
								.setStatus(CommonCodes.BATCH_COMPLETED_PARTIAl);
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
						
						objForm.setSuccessAppsPrimaryList(appPrimaryCardsList);
						objForm.setSuccessAppsSuppList(appSuppCardsList);
						objForm.setCardNos(genCardNos.toString().substring(0, (genCardNos.toString().length() - 1)));
						
						return mapping.findForward("reportpage");
					}

				} else {
					Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo);
					objForm.setAppList(applicationsList);
					
					int totApps = objCardBatchProcessManager.getTotalCardsApps(issuerId);
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

			Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo);
			objForm.setAppList(applicationsList);
			
			int totApps = objCardBatchProcessManager.getTotalCardsApps(issuerId);
			objForm.setTotalNoOfApps(String.valueOf(totApps));
			
			request.setAttribute("SEARCHLIST", applicationsList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

			errors.add("Error", new ActionError("error.applicationerror"));
			saveErrors(request, errors);

		}

		return mapping.findForward("success");
	}
	*/
	public ActionForward authorized(ActionMapping mapping, ActionForm form,
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

		NewCardBPForm objForm = (NewCardBPForm) form;
		CardBatchProcessManager objCardBatchProcessManager = new CardBatchProcessManager();
		
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			//check branch 
			UserSetupManager objUserSetupManager = new UserSetupManager();
			BranchManager objBranchManager = new BranchManager();
			UserMasterDto objUserMasterDto = objUserSetupManager.getUserMasterForm(issuerId, userID);
			if(null != objUserMasterDto) {
				String branchId = objUserMasterDto.getBranchId();
				objForm.setBranchId(branchId);
				if(branchId.equals("ALL")) {
					objForm.setBranchList(objCardBatchProcessManager.getBranch(null,"ALL"));
					Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo, null, "ALL");
					objForm.setAppList(applicationsList);
					request.setAttribute("SEARCHLIST", applicationsList);
					request.setAttribute("PAGENO", new Integer(pageNo).toString());
				} else {
					BranchDto objBranchDto = objBranchManager.getBranchDto(branchId);
					objForm.setBranchList(objCardBatchProcessManager.getBranch(objBranchDto,""));
					Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo, objBranchDto, "");
					objForm.setAppList(applicationsList);
					request.setAttribute("SEARCHLIST", applicationsList);
					request.setAttribute("PAGENO", new Integer(pageNo).toString());
				}
			} else {
				objForm.setBranchList(objCardBatchProcessManager.getBranch(null,"ALL"));
				Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo, null, "ALL");
				objForm.setAppList(applicationsList);
				request.setAttribute("SEARCHLIST", applicationsList);
				request.setAttribute("PAGENO", new Integer(pageNo).toString());
			}
			return mapping.findForward("success");
		}
		
		try {
			//update ApplicationForms
			String applIdArray[] = objForm.getApplIdArray().split(",");
			/*if(applIdArray.length == 1 && applIdArray[0].equals("")) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError(
						"error.authorizedfailed"));
				saveErrors(request, errors);
				Collection applicationsList = objCardBatchProcessManager.list(issuerId, pageNo);
				objForm.setAppList(applicationsList);
				
				request.setAttribute("SEARCHLIST", applicationsList);
				request.setAttribute("PAGENO", new Integer(pageNo).toString());
				return mapping.findForward("success");
			}*/
			
			for(int i=0;i<applIdArray.length;i++) {
				ApplicationFormDto objApplicationFormDto = objCardBatchProcessManager.getApplicationForm(applIdArray[i]);
				objApplicationFormDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_AUTHORIZED);
				objApplicationFormDto.setUserId(objForm.getUserId());
				objApplicationFormDto.setUpdatedDate(new Date());
				
				objCardBatchProcessManager.updateApplication(objApplicationFormDto);
			}
			//insert CardBatch
			CardBatchDto objBatchDto = new CardBatchDto();

			objBatchDto.setStatus(CommonCodes.BATCH_NEW);
			objBatchDto.setBatchId(objForm.getBatchId());
			objBatchDto.setAuthorizedBy(objForm.getAuthUserId());
			objBatchDto.setAuthorizedDate(new Date());
			objBatchDto.setUpdateddBy(objForm.getUserId());
			objBatchDto.setUpdatedDate(new Date());
			objBatchDto.setNoApplications(applIdArray.length);

			boolean overallSuccess = objCardBatchProcessManager
					.addBatch(objBatchDto);
			
			//insert CardApplLink
			for(int i=0;i<applIdArray.length;i++) {
				CardApplLinkDto objCardApplLinkDto = new CardApplLinkDto();
				objCardApplLinkDto.setBatchId(objForm.getBatchId());
				objCardApplLinkDto.setApplicationId(applIdArray[i]);
				objCardApplLinkDto.setApplicationType(CommonCodes.APPLICATIONTYPE_NEWCARD);
				objCardApplLinkDto.setUpdatedDate(new Date());
				objCardApplLinkDto.setUpdateddBy(objForm.getUserId());
				objCardBatchProcessManager.addCardApplLink(objCardApplLinkDto);
			}
			
			return mapping.findForward("batchprocess");
		} catch (Exception e) {
			logger.error(new Object(), e);
			System.out
					.println("Error converting to form bean in NewCardsDispatchAction : "
							+ e.getMessage());
			throw new TPlusException(
					"Error converting to form bean in NewCardsDispatchAction : "
							+ e);
		}

	}
}
