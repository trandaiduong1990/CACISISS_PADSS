package org.transinfo.cacis.action.accounting;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.controller.accounting.CardHolderStatementManager;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.dto.accounting.CardHoderStatementDto;
import org.transinfo.cacis.dto.accounting.CustomerStatement;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.formbean.accounting.CardHolderStatementForm;
import org.transinfo.cacis.formbean.accounting.CustomerStatementForm;

@SuppressWarnings({"deprecation","unchecked"})
public class CardHolderStatementDispatchAction  extends BaseDispatchAction {


	/*
	 * this method is for checking CustomerServiceDataBean has been Existed in the session 
	 */
	public ActionForward checkSession(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws TPlusException,Exception {
		//in this method removing the attribute from session that starts and ends with dollor symbol($)	
		processSession(request); 

		CardHolderStatementForm objForm = (CardHolderStatementForm) form;

		CardHolderStatementForm newObjForm = new CardHolderStatementForm();

		BeanUtils.copyProperties(objForm, newObjForm);

		return mapping.findForward("success");
	}

	/*
	 * this method is for Get all the  Data(for all customer service screens) using  model's(CustomerSevice class getCustomerServiceData() method) 
	 */             
	public ActionForward search(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws TPlusException,Exception {

		ActionErrors errors = null;
		CardHolderStatementForm objForm = (CardHolderStatementForm) form;

		//to check card number Entered or not
		if(objForm.getCardNumber()!=null  && objForm.getCardNumber().equals(""))
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnorequired"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		CardHoderStatementDto objDto  = new CardHoderStatementDto();

		String forward = "success";

		try{

			BeanUtils.copyProperties(objDto, objForm);

			CardHolderStatementManager objManager = new CardHolderStatementManager();
			CardManager objCardManager = new CardManager();

			CardsDto objCardsDto = objCardManager.getCard(objDto.getCardNumber());

			if(objCardsDto == null)
			{
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				return mapping.findForward("success");

			}else{

				String cardType = objCardManager.getCardType(String.valueOf(objDto.getCardNumber()));

				if(!ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.creditcardonly"));
					saveErrors(request, errors);
					return mapping.findForward("success");
				}

				String cardNo = objForm.getCardNumber();

				int noOfrec = objManager.search(objDto);

				if(noOfrec == 0){
					// this card donesn't have any statement and need to give un billed link only
					
					objForm.setUnbilled("Y");
					objForm.setEnableLink("UN");

					forward = "unbilled";

					List postTranx = objManager.getPostTranx(cardNo);
					objForm.setPostTranx(postTranx);

					List paymentTranx = objManager.getPaymentTranx(cardNo);
					objForm.setPaymentTranx(paymentTranx);

					List feeTranx = objManager.getFeeTranx(cardNo);
					objForm.setFeeTranx(feeTranx);

				}else if(noOfrec == 1){
					// this card has only current statement and need to show un billed and current statement
					
					objForm.setUnbilled("Y");
					objForm.setCurrent("Y");
					objForm.setEnableLink("CU");

					forward = "current";
					
					// get current statement
					CustomerStatement objCustomerStatement = objManager.getCurrentStatement(cardNo);
					
					CustomerStatementForm customerStatement = new CustomerStatementForm();
					BeanUtils.copyProperties(customerStatement, objCustomerStatement);
					
					objForm.setCustomerStatement(customerStatement);
					
					String statId = objCustomerStatement.getStatId();
					
					double minPay = objCustomerStatement.getStatMinAmt().doubleValue();
					double statAmt = objCustomerStatement.getStatAmt().doubleValue();
					
					// get un billed total payments
					//CurrPaySumDto objCurrPaySumDto = objManager.getCurrPaySum(cardNo);
					
					double totPay = 0;
					//if(objCurrPaySumDto != null){
						totPay = objCustomerStatement.getPrevPayAmt().doubleValue();
					//}
					
					objForm.setOutAmtDue(String.valueOf(statAmt-totPay));
					objForm.setOutMinPayDue(String.valueOf(minPay-totPay));
					
					List statTranx = objManager.getStatementTranx(statId);
					objForm.setStatTranx(statTranx);
					
					List statPay = objManager.getStatPayment(statId);
					objForm.setStatPay(statPay);
					
					List statFee = objManager.getStatFee(statId);
					objForm.setStatFee(statFee);
					
					List statInt = objManager.getStatInterest(statId);
					objForm.setStatInt(statInt);
					
				}else if(noOfrec >= 2){
					// this card has more statements. need to show current, previous and un billed
					
					objForm.setUnbilled("Y");
					objForm.setCurrent("Y");
					objForm.setPrevious("Y");
					objForm.setEnableLink("CU");

					forward = "current";
					
					// get current statement
					CustomerStatement objCustomerStatement = objManager.getCurrentStatement(cardNo);
					
					CustomerStatementForm customerStatement = new CustomerStatementForm();
					BeanUtils.copyProperties(customerStatement, objCustomerStatement);
					
					objForm.setCustomerStatement(customerStatement);
					
					String statId = objCustomerStatement.getStatId();
					
					double minPay = objCustomerStatement.getStatMinAmt().doubleValue();
					double statAmt = objCustomerStatement.getStatAmt().doubleValue();
					
					// get un billed total payments
					//CurrPaySumDto objCurrPaySumDto = objManager.getCurrPaySum(cardNo);
					
					double totPay = 0;
					//if(objCurrPaySumDto != null){
						totPay = objCustomerStatement.getPrevPayAmt().doubleValue();
					//}
					
					objForm.setOutAmtDue(String.valueOf(statAmt-totPay));
					objForm.setOutMinPayDue(String.valueOf(minPay-totPay));
					
					List statTranx = objManager.getStatementTranx(statId);
					objForm.setStatTranx(statTranx);
					
					List statPay = objManager.getStatPayment(statId);
					objForm.setStatPay(statPay);
					
					List statFee = objManager.getStatFee(statId);
					objForm.setStatFee(statFee);
					
					List statInt = objManager.getStatInterest(statId);
					objForm.setStatInt(statInt);

				}

				request.getSession(false).setAttribute("$CARDDATALIST$","TRUE");

			}
		}catch (Exception e){
			System.out.println("Error converting to form bean in CardHolderPaymentDispatchAction: " + e);
			throw new TPlusException("Could not populate the form bean in CardHolderPaymentDispatchAction: " + e);
		}


		saveToken(request);
		return mapping.findForward(forward);
	}

	public ActionForward unbilled(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws TPlusException,Exception {

		CardHolderStatementForm objForm = (CardHolderStatementForm) form;

		String forward = "success";

		try{

			CardHolderStatementManager objManager = new CardHolderStatementManager();

			String cardNo = objForm.getCardNumber();
			
			CardHoderStatementDto objDto  = new CardHoderStatementDto();
			BeanUtils.copyProperties(objDto, objForm);
			
			int noOfrec = objManager.search(objDto);

			if(noOfrec == 0){
				// this card donesn't have any statement and need to give un billed link only
				
				objForm.setUnbilled("Y");
				objForm.setEnableLink("UN");

			}else if(noOfrec == 1){
				// this card has only current statement and need to show un billed and current statement
				
				objForm.setUnbilled("Y");
				objForm.setCurrent("Y");
				objForm.setEnableLink("UN");
				
			}else if(noOfrec >= 2){
				// this card has more statements. need to show current, previous and un billed
				
				objForm.setUnbilled("Y");
				objForm.setCurrent("Y");
				objForm.setPrevious("Y");
				objForm.setEnableLink("UN");

			}

			// this card donesn't have any statement and need to give un billed link only

			forward = "unbilled";

			List postTranx = objManager.getPostTranx(cardNo);
			objForm.setPostTranx(postTranx);

			List paymentTranx = objManager.getPaymentTranx(cardNo);
			objForm.setPaymentTranx(paymentTranx);

			List feeTranx = objManager.getFeeTranx(cardNo);
			objForm.setFeeTranx(feeTranx);

			request.getSession(false).setAttribute("$CARDDATALIST$","TRUE");

		}catch (Exception e){
			System.out.println("Error converting to form bean in CardHolderPaymentDispatchAction: " + e);
			throw new TPlusException("Could not populate the form bean in CardHolderPaymentDispatchAction: " + e);
		}


		saveToken(request);
		return mapping.findForward(forward);
	}

	public ActionForward current(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws TPlusException,Exception {

		CardHolderStatementForm objForm = (CardHolderStatementForm) form;

		String forward = "success";

		try{

			CardHolderStatementManager objManager = new CardHolderStatementManager();

			String cardNo = objForm.getCardNumber();
			
			CardHoderStatementDto objDto  = new CardHoderStatementDto();
			BeanUtils.copyProperties(objDto, objForm);
			
			int noOfrec = objManager.search(objDto);

			if(noOfrec == 0){
				// this card donesn't have any statement and need to give un billed link only
				
				objForm.setUnbilled("Y");
				objForm.setEnableLink("CU");

			}else if(noOfrec == 1){
				// this card has only current statement and need to show un billed and current statement
				
				objForm.setUnbilled("Y");
				objForm.setCurrent("Y");
				objForm.setEnableLink("CU");
				
			}else if(noOfrec >= 2){
				// this card has more statements. need to show current, previous and un billed
				
				objForm.setUnbilled("Y");
				objForm.setCurrent("Y");
				objForm.setPrevious("Y");
				objForm.setEnableLink("CU");

			}

			// this card donesn't have any statement and need to give un billed link only

			forward = "current";

			// get current statement
			CustomerStatement objCustomerStatement = objManager.getCurrentStatement(cardNo);
			
			CustomerStatementForm customerStatement = new CustomerStatementForm();
			BeanUtils.copyProperties(customerStatement, objCustomerStatement);
			
			objForm.setCustomerStatement(customerStatement);
			
			String statId = objCustomerStatement.getStatId();
			
			double minPay = objCustomerStatement.getStatMinAmt().doubleValue();
			double statAmt = objCustomerStatement.getStatAmt().doubleValue();
			
			// get un billed total payments
			//CurrPaySumDto objCurrPaySumDto = objManager.getCurrPaySum(cardNo);
			
			double totPay = 0;
			//if(objCurrPaySumDto != null){
				totPay = objCustomerStatement.getPrevPayAmt().doubleValue();
			//}
			
			objForm.setOutAmtDue(String.valueOf(statAmt-totPay));
			objForm.setOutMinPayDue(String.valueOf(minPay-totPay));
			
			List statTranx = objManager.getStatementTranx(statId);
			objForm.setStatTranx(statTranx);
			
			List statPay = objManager.getStatPayment(statId);
			objForm.setStatPay(statPay);
			
			List statFee = objManager.getStatFee(statId);
			objForm.setStatFee(statFee);
			
			List statInt = objManager.getStatInterest(statId);
			objForm.setStatInt(statInt);

			request.getSession(false).setAttribute("$CARDDATALIST$","TRUE");

		}catch (Exception e){
			System.out.println("Error converting to form bean in CardHolderPaymentDispatchAction: " + e);
			throw new TPlusException("Could not populate the form bean in CardHolderPaymentDispatchAction: " + e);
		}


		saveToken(request);
		return mapping.findForward(forward);
	}

	public ActionForward previous(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws TPlusException,Exception {

		CardHolderStatementForm objForm = (CardHolderStatementForm) form;

		String forward = "success";

		try{

			CardHolderStatementManager objManager = new CardHolderStatementManager();

			String cardNo = objForm.getCardNumber();
			
			CardHoderStatementDto objDto  = new CardHoderStatementDto();
			BeanUtils.copyProperties(objDto, objForm);
			
			int noOfrec = objManager.search(objDto);

			if(noOfrec == 0){
				// this card donesn't have any statement and need to give un billed link only
				
				objForm.setUnbilled("Y");
				objForm.setEnableLink("PR");

			}else if(noOfrec == 1){
				// this card has only current statement and need to show un billed and current statement
				
				objForm.setUnbilled("Y");
				objForm.setCurrent("Y");
				objForm.setEnableLink("PR");
				
			}else if(noOfrec >= 2){
				// this card has more statements. need to show current, previous and un billed
				
				objForm.setUnbilled("Y");
				objForm.setCurrent("Y");
				objForm.setPrevious("Y");
				objForm.setEnableLink("PR");

			}

			// this card donesn't have any statement and need to give un billed link only

			forward = "previous";

			// get current statement
			CustomerStatement objCustomerPrevStatement = objManager.getPreviousStatement(cardNo);
			
			CustomerStatementForm customerStatement = new CustomerStatementForm();
			BeanUtils.copyProperties(customerStatement, objCustomerPrevStatement);
			
			objForm.setCustomerStatement(customerStatement);
			
			String statId = objCustomerPrevStatement.getStatId();
			
			double minPay = objCustomerPrevStatement.getStatMinAmt().doubleValue();
			double statAmt = objCustomerPrevStatement.getStatAmt().doubleValue();
			
			// get un billed total payments
			//CurrPaySumDto objCurrPaySumDto = objManager.getCurrPaySum(cardNo);
			
			double totPay = 0;
			//if(objCurrPaySumDto != null){
				totPay = customerStatement.getPrevPayAmt().doubleValue();
			//}
			
			objForm.setOutAmtDue(String.valueOf(statAmt-totPay));
			objForm.setOutMinPayDue(String.valueOf(minPay-totPay));
			
			List statTranx = objManager.getStatementTranx(statId);
			objForm.setStatTranx(statTranx);
			
			List statPay = objManager.getStatPayment(statId);
			objForm.setStatPay(statPay);
			
			List statFee = objManager.getStatFee(statId);
			objForm.setStatFee(statFee);
			
			List statInt = objManager.getStatInterest(statId);
			objForm.setStatInt(statInt);

			request.getSession(false).setAttribute("$CARDDATALIST$","TRUE");

		}catch (Exception e){
			System.out.println("Error converting to form bean in CardHolderPaymentDispatchAction: " + e);
			throw new TPlusException("Could not populate the form bean in CardHolderPaymentDispatchAction: " + e);
		}


		saveToken(request);
		return mapping.findForward(forward);
	}

	/*
	 * this method checks in session wether setted Attributes like $anyname$  existed in session
	 * if exists it removes that attribute
	 */	
	public void processSession(HttpServletRequest request){

		if(request.getSession(false)!=null)
		{		
			HttpSession  session =request.getSession(false);	
			Enumeration listCustServ = session.getAttributeNames();
			while(listCustServ.hasMoreElements())
			{
				String custObj= (String)listCustServ.nextElement();
				//  System.out.println("Customer Object" +custObj+"  "+session.getAttribute(custObj).getClass().getName());
				if(custObj.startsWith("$") && custObj.endsWith("$"))
					// if(session.getAttribute(custObj) instanceof CustomerServiceDataBean)
				{		    	
					session.removeAttribute(custObj);

				}

			}

		}
	}
}
