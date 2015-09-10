package org.transinfo.cacis.action.customerservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.accounting.CardHolderStatementManager;
import org.transinfo.cacis.controller.cardproduction.CardEmbossingManager;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.controller.customerservice.CustomerScreenProcessManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.dto.batchprocess.CardATMLinkDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.customerservice.CustomerScreenProcessDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.formbean.customerservice.CustomerScreenProcessForm;

@SuppressWarnings({"unchecked","unused", "deprecation"})
public class CustomerScreenDispatchAction extends BaseDispatchAction {

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		CustomerScreenProcessForm objForm = (CustomerScreenProcessForm) form;
		String customerId = objForm.getCustomerId();
		String cardNo = objForm.getCardNo();

		CustomerScreenProcessManager objCustomerScreenProcessManager = new CustomerScreenProcessManager();
		CardProductManager objCardProductManager = new CardProductManager();
		//SalaryProfileManager objSalaryProfileManager = new SalaryProfileManager();
		CardManager objCardManager = new CardManager();

		CardsDto objCardsDto = null;
		CardProductDto objCardProductDto = null;
		CardATMLinkDto objAtmLinkDto = null;
		//SalaryProfileDto objSalaryProfileDto = null;
		//CustomerLimitsDto objCustomerLimitsDto = null;
		CustomerAccountDto objCustomerAccountDto = null;

		String cardNumber = "";
		int cardHolderType = 0;
		String accountCreation = "";
		String cardProductId = "";
		List suppCards = new ArrayList();
		//List salaryprofileList = new ArrayList();
		String income = "";

		String avaBal = "";
		Double nonReconTranxAmt = 0.0;
		String totFeeAmt = "0";
		String totBal = "0";
		String accountId = "";

		try {

			CustomerScreenProcessDto objCustomerScreenProcessDto = objCustomerScreenProcessManager.getApplicationProcessDto(customerId);

			income = String.valueOf(objCustomerScreenProcessDto.getIncome());

			// get card
			/*Set cards = objCustomerScreenProcessDto.getCustomerCards();
			for(Iterator it = cards.iterator(); it.hasNext();){
				objCardsDto = (CardsDto) it.next();
			}*/

			objCardsDto = objCardManager.getCard(cardNo);

			if(objCardsDto != null){
				
				objForm.setStatus(objCardsDto.getStatus());
				
				cardNumber = String.valueOf(objCardsDto.getCardNumber());
				objCustomerScreenProcessDto.setCardNumber(cardNumber);

				// to get all cards
				accountId = objCardsDto.getAccountId();

				objCustomerScreenProcessDto.setCycleNo(Integer.valueOf(objCardsDto.getCycleNo()).intValue());

				cardHolderType = objCardsDto.getCardHolderType();
				cardProductId = objCardsDto.getCardProductId();
				
				// assign card product id
				objCustomerScreenProcessDto.setSelectedAppCardProducts(cardProductId);

				// get card product
				objCardProductDto = objCardProductManager.getCardProductDto(cardProductId);
				accountCreation = objCardProductDto.getAccountCreation();

				String carProductType = objCardProductDto.getCardProductType().getCardProductType();

				if((accountCreation != null && "Y".equals(accountCreation)) || (carProductType != null && "CreditCard".equals(carProductType))){
					accountCreation = "Y";

					/*if(carProductType.equals("CreditCard")){
						salaryprofileList = objSalaryProfileManager.getSalaryProfileListAppProcess(cardProductId, income);
					}

					if(salaryprofileList.size() > 0){
						objSalaryProfileDto = (SalaryProfileDto)salaryprofileList.get(0);
						objCustomerScreenProcessDto.setCashLimit(String.valueOf(objSalaryProfileDto.id.getCashAdvanceLimit()));
						objCustomerScreenProcessDto.setCreditLimit(String.valueOf(objSalaryProfileDto.id.getCreditLimit()));
					}*/

					// get customer limits
					/*objCustomerLimitsDto = objCustomerScreenProcessManager.getCustomerLimitsDto(cardNumber);
					if(objCustomerLimitsDto != null){
						objCustomerScreenProcessDto.setAmtPerTranx(objCustomerLimitsDto.getAmtPerTranx());
						objCustomerScreenProcessDto.setDailyLimitAmt(objCustomerLimitsDto.getDailyLimitAmt());
						objCustomerScreenProcessDto.setDailyLimitCount(objCustomerLimitsDto.getDailyLimitCount());
						objCustomerScreenProcessDto.setMonthlyLimitAmt(objCustomerLimitsDto.getMonthlyLimitAmt());
						objCustomerScreenProcessDto.setMonthlyLimitCount(objCustomerLimitsDto.getMonthlyLimitCount());
					}*/

					// get customer account
					/*Set accounts = objCustomerScreenProcessDto.getCustomerAccount();
					for(Iterator itr = accounts.iterator(); itr.hasNext();){
						objCustomerAccountDto = (CustomerAccountDto) itr.next();
					}*/
					double limitUsed = 0;
					
					objCustomerAccountDto = objCardsDto.getCustAccountDto();
					if(objCustomerAccountDto != null){
						objCustomerScreenProcessDto.setCurrencyCode(objCustomerAccountDto.getCurrencyCode());
						objCustomerScreenProcessDto.setCreditLimit(String.valueOf(objCustomerAccountDto.getCreditLimit()));
						objCustomerScreenProcessDto.setLimitUsed(String.valueOf(objCustomerAccountDto.getLimitUsed()));
						objCustomerScreenProcessDto.setPurchasedUsed(String.valueOf(objCustomerAccountDto.getSaleUsed()));
						objCustomerScreenProcessDto.setCashUsed(String.valueOf(objCustomerAccountDto.getCashUsed()));
						//objCustomerScreenProcessDto.setCycleNo(objCustomerAccountDto.getCycleNo());
						//avaBal = String.valueOf(objCustomerAccountDto.getCreditLimit()-objCustomerAccountDto.getLimitUsed());
						
						BigDecimal bigavabal = new BigDecimal(String.valueOf(objCustomerAccountDto.getCreditLimit()-objCustomerAccountDto.getLimitUsed()));
						bigavabal = bigavabal.setScale(2, BigDecimal.ROUND_HALF_UP);
						
						avaBal = bigavabal.toString();
						
						limitUsed = objCustomerAccountDto.getLimitUsed();
						
					}

					// get all cards for this card's account ID
					Collection cardLists = objCustomerScreenProcessManager.getAllCardsByAccountID(accountId);
					request.setAttribute("SEARCHLIST", cardLists);
					
					// get total amount of non RECON transactions
					nonReconTranxAmt = objCustomerScreenProcessManager.getTotNonReconAmt(accountId);
					
					// get un billed fee
					CardHolderStatementManager objManagerCardHolderStatementManager = new CardHolderStatementManager();
					double totFeeDouble = objManagerCardHolderStatementManager.getTotFeeAmt(objForm.getCardNumber());

					BigDecimal bigTotFee = BigDecimal.valueOf(totFeeDouble);
					bigTotFee = bigTotFee.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					totFeeAmt = bigTotFee.toString();
					
					BigDecimal biglimitUsed = BigDecimal.valueOf(limitUsed);
					biglimitUsed = biglimitUsed.setScale(2, BigDecimal.ROUND_HALF_EVEN);

					totBal = biglimitUsed.add(bigTotFee).toString();

				}else{
					accountCreation = "N";
				}
				objCustomerScreenProcessDto.setAccountOnSystem(accountCreation);

				// get Card ATM link
				objAtmLinkDto = objCustomerScreenProcessManager.getCardATMLinkDto(cardNumber);
				if(objAtmLinkDto != null){
					if(!"".equals(objAtmLinkDto.getDefaultAccount())){
						objCustomerScreenProcessDto.setCheckedAtmLink('Y');						
					}else{
						objCustomerScreenProcessDto.setCheckedAtmLink('N');	
					}
					objCustomerScreenProcessDto.setDefaultAccount(objAtmLinkDto.getDefaultAccount());
					objCustomerScreenProcessDto.setSavingAccount(objAtmLinkDto.getSavingAccount());
					objCustomerScreenProcessDto.setCheckingAccount(objAtmLinkDto.getCurrentAccount());

					objCustomerScreenProcessDto.setCollectoralAccount(objAtmLinkDto.getCollectoralAccount());
					objCustomerScreenProcessDto.setCollectoralAmt(String.valueOf(objAtmLinkDto.getCollectoralAmt()));
					objCustomerScreenProcessDto.setAutoPayAccount(objAtmLinkDto.getAutoPayAccount());

					if((objAtmLinkDto.getAutoPayAccounton() != null) && ('Y' == objAtmLinkDto.getAutoPayAccounton())){
						objCustomerScreenProcessDto.setCheckedautoPayAccountOn('Y');
					}

					if((objAtmLinkDto.getAutoPayDisable() != null) && ('Y' == objAtmLinkDto.getAutoPayDisable())){
						objCustomerScreenProcessDto.setCheckedautoPayDisable('Y');
					}
					
				}

				// check primary or supplementary
				/*if(cardHolderType == 1){
					// get supplementary cards
					//String accountId = objCardsDto.getAccountId();
					suppCards = objCustomerScreenProcessManager.getSuppCards(cardNumber, accountId);
					objCustomerScreenProcessDto.setSuppCards(suppCards);

				}*/
				
				// get other cards
				suppCards = objCustomerScreenProcessManager.getOtherCards(cardNo, accountId);
				objCustomerScreenProcessDto.setSuppCards(suppCards);

				if(suppCards.size() > 0){
					objCustomerScreenProcessDto.setSubCardsCount("1");
				}else{
					objCustomerScreenProcessDto.setSubCardsCount("2");
				}

			}

			BeanUtils.copyProperties(objForm, objCustomerScreenProcessDto);
			objForm.setAvailableBalance(avaBal);
			objForm.setNonReconTranxAmt(String.valueOf(nonReconTranxAmt));
			objForm.setAccountId(accountId);

			objForm.setTotFeeAmt(totFeeAmt);
			objForm.setTotBal(totBal);

		} catch (Exception e) {
			throw new TPlusException(
					"Could not populate the form bean in CustomerScreenDispatchAction change method: "
					+ e);
		}

		objForm.getPreListData();
		saveToken(request);
		request.setAttribute("ACTION", "update");

		return mapping.findForward("success");
	}

	public ActionForward csupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		CustomerScreenProcessForm objForm = (CustomerScreenProcessForm) form;

		String custId =  objForm.getCustomerId();

		ActionErrors errors = null;
		
		CardProductManager objCardProductManager = new CardProductManager();
		CardManager objCardManager = new CardManager();

		CardsDto objCardsDto = null;
		CardProductDto objCardProductDto = null;
		CardATMLinkDto objAtmLinkDto = null;
		CustomerAccountDto objCustomerAccountDto = null;

		String cardNumber = "";
		int cardHolderType = 0;
		String accountCreation = "";
		String cardProductId = "";
		List suppCards = new ArrayList();
		String avaBal = "";

		Double nonReconTranxAmt = 0.0;
		String accountId = "";

		try {

			ApplicationProcessDto objAppProcessDto = new ApplicationProcessDto();

			CardEmbossingManager objCardEmbossingManager = new CardEmbossingManager();
			objAppProcessDto =  objCardEmbossingManager.getCustomerByCusId(custId);
			
			Set addressess = objAppProcessDto.getApplicationAddress();
			//Map appCardProducts = objAppProcessDto.getAppCardProducts();

			BeanUtils.copyProperties(objAppProcessDto, objForm);
			
			//objAppProcessDto.setAppCardProducts(null);
			//objAppProcessDto.setAppCardProducts(appCardProducts);

			String cAddress = objForm.getCompanyAddress().getAddressId();

			for (Iterator it = addressess.iterator(); it.hasNext();) {
				CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
				if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("H")) {
					BeanUtils.copyProperties(addressDto, objForm.getHomeAddress());
					objAppProcessDto.getApplicationAddress().add(addressDto);
				} else if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("C")) {
					if(cAddress != null && !"".equals(cAddress)){
						BeanUtils.copyProperties(addressDto, objForm.getCompanyAddress());
						objAppProcessDto.getApplicationAddress().add(addressDto);
					}
				}
			}

			CustomerScreenProcessManager objCustomerScreenProcessManager = new CustomerScreenProcessManager();
			boolean boolUpdate = objCustomerScreenProcessManager.updateCU(objAppProcessDto);

			if (boolUpdate) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updateSuccess"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updatefailed"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
			}
			
			CustomerScreenProcessDto objCustomerScreenProcessDto = objCustomerScreenProcessManager.getApplicationProcessDto(custId);

			String cardNo = objForm.getCardNumber();
			objCardsDto = objCardManager.getCard(cardNo);

			if(objCardsDto != null){
				
				objForm.setStatus(objCardsDto.getStatus());
				
				cardNumber = String.valueOf(objCardsDto.getCardNumber());
				objCustomerScreenProcessDto.setCardNumber(cardNumber);

				// to get all cards
				accountId = objCardsDto.getAccountId();

				objCustomerScreenProcessDto.setCycleNo(Integer.valueOf(objCardsDto.getCycleNo()).intValue());

				cardHolderType = objCardsDto.getCardHolderType();
				cardProductId = objCardsDto.getCardProductId();

				// get card product
				objCardProductDto = objCardProductManager.getCardProductDto(cardProductId);
				accountCreation = objCardProductDto.getAccountCreation();

				String carProductType = objCardProductDto.getCardProductType().getCardProductType();

				if((accountCreation != null && "Y".equals(accountCreation)) || (carProductType != null && "CreditCard".equals(carProductType))){
					accountCreation = "Y";

					objCustomerAccountDto = objCardsDto.getCustAccountDto();
					if(objCustomerAccountDto != null){
						objCustomerScreenProcessDto.setCurrencyCode(objCustomerAccountDto.getCurrencyCode());
						objCustomerScreenProcessDto.setCreditLimit(String.valueOf(objCustomerAccountDto.getCreditLimit()));
						objCustomerScreenProcessDto.setLimitUsed(String.valueOf(objCustomerAccountDto.getLimitUsed()));
						objCustomerScreenProcessDto.setPurchasedUsed(String.valueOf(objCustomerAccountDto.getSaleUsed()));
						objCustomerScreenProcessDto.setCashUsed(String.valueOf(objCustomerAccountDto.getCashUsed()));
						//objCustomerScreenProcessDto.setCycleNo(objCustomerAccountDto.getCycleNo());
						avaBal = String.valueOf(objCustomerAccountDto.getCreditLimit()-objCustomerAccountDto.getLimitUsed());
					}

					// get all cards for this card's account ID
					Collection cardLists = objCustomerScreenProcessManager.getAllCardsByAccountID(accountId);
					request.setAttribute("SEARCHLIST", cardLists);
					
					// get total amount of non RECON transactions
					nonReconTranxAmt = objCustomerScreenProcessManager.getTotNonReconAmt(accountId);

				}else{
					accountCreation = "N";
				}
				objCustomerScreenProcessDto.setAccountOnSystem(accountCreation);

				// get Card ATM link
				objAtmLinkDto = objCustomerScreenProcessManager.getCardATMLinkDto(cardNumber);
				if(objAtmLinkDto != null){
					if(!"".equals(objAtmLinkDto.getDefaultAccount())){
						objCustomerScreenProcessDto.setCheckedAtmLink('Y');						
					}else{
						objCustomerScreenProcessDto.setCheckedAtmLink('N');	
					}
					objCustomerScreenProcessDto.setDefaultAccount(objAtmLinkDto.getDefaultAccount());
					objCustomerScreenProcessDto.setSavingAccount(objAtmLinkDto.getSavingAccount());
					objCustomerScreenProcessDto.setCheckingAccount(objAtmLinkDto.getCurrentAccount());

					objCustomerScreenProcessDto.setCollectoralAccount(objAtmLinkDto.getCollectoralAccount());
					objCustomerScreenProcessDto.setCollectoralAmt(String.valueOf(objAtmLinkDto.getCollectoralAmt()));
					objCustomerScreenProcessDto.setAutoPayAccount(objAtmLinkDto.getAutoPayAccount());

					if((objAtmLinkDto.getAutoPayAccounton() != null) && ('Y' == objAtmLinkDto.getAutoPayAccounton())){
						objCustomerScreenProcessDto.setCheckedautoPayAccountOn('Y');
					}
				}

				// check primary or supplementary
				if(cardHolderType == 1){
					// get supplementary cards
					//String accountId = objCardsDto.getAccountId();
					suppCards = objCustomerScreenProcessManager.getSuppCards(cardNumber, accountId);
					objCustomerScreenProcessDto.setSuppCards(suppCards);

				}

				if(suppCards.size() > 0){
					objCustomerScreenProcessDto.setSubCardsCount("1");
				}else{
					objCustomerScreenProcessDto.setSubCardsCount("2");
				}

			}

			BeanUtils.copyProperties(objForm, objCustomerScreenProcessDto);
			objForm.setAvailableBalance(avaBal);

			objForm.setNonReconTranxAmt(String.valueOf(nonReconTranxAmt));
			objForm.setAccountId(accountId);
			
			objForm.getPreListData();
			saveToken(request);
			request.setAttribute("ACTION", "update");

		}catch (Exception e) {
			System.out.println("Error converting to form bean in ApplicationProcessDispatchAction accept method : " + e);
			throw new TPlusException("Could not populate the form bean in ApplicationProcessDispatchAction accept method: " + e);
		}

		return mapping.findForward("success");

	}

	public ActionForward customerHistory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {
		CustomerScreenProcessForm objForm = (CustomerScreenProcessForm) form;
		CustomerScreenProcessManager objManager = new CustomerScreenProcessManager();

		Collection historyList = objManager.customerHistory((String) request.getParameter("nricNo"));

		if (historyList != null) {
			request.setAttribute("CUSTOMERHISTORYLIST", historyList);
		}

		CustomerScreenProcessManager objCustomerScreenProcessManager = new CustomerScreenProcessManager();

		List suppCards = new ArrayList();
		String cardNo = objForm.getCardNumber();
		CardManager objCardManager = new CardManager();
		CardsDto objCardsDto =  objCardManager.getCard(cardNo);
		String accountId = "";
		if(objCardsDto != null){

			int cardHolderType = objCardsDto.getCardHolderType();

			// check primary or supplementary
			if(cardHolderType == 1){
				// get supplementary cards
				accountId = objCardsDto.getAccountId();
				suppCards = objCustomerScreenProcessManager.getSuppCards(cardNo, accountId);
				objForm.setSuppCards(suppCards);

				if(suppCards.size() > 0){
					objForm.setSubCardsCount("1");
				}else{
					objForm.setSubCardsCount("2");
				}
			}

			// get all cards for this card's account ID
			Collection cardLists = objCustomerScreenProcessManager.getAllCardsByAccountID(objCardsDto.getAccountId());
			request.setAttribute("SEARCHLIST", cardLists);
			
			// get total amount of non RECON transactions
			Double nonReconTranxAmt = objCustomerScreenProcessManager.getTotNonReconAmt(accountId);

			objForm.setNonReconTranxAmt(String.valueOf(nonReconTranxAmt));
			objForm.setAccountId(accountId);

		}

		/*
		Collection cardList = objManager.getCardDetails((String) request
				.getParameter("applicationId"));
		if (cardList != null && cardList.size() > 0) {
			request.setAttribute("CARDLIST", cardList);
		}
		 */

		objForm.getPreListData();
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");

	}

}
