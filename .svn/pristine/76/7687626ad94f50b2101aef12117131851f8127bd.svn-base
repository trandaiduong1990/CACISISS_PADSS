package org.transinfo.cacis.action.accounting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

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
import org.transinfo.cacis.controller.accounting.CardHolderPaymentManager;
import org.transinfo.cacis.controller.cardproduction.CardManager;
import org.transinfo.cacis.dto.accounting.CardHolderPaymentDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.formbean.accounting.CardHolderPaymentForm;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;

@SuppressWarnings({"deprecation","unchecked"})
public class CardHolderPaymentDispatchAction  extends BaseDispatchAction {


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
		CardHolderPaymentForm objForm = (CardHolderPaymentForm) form;

		//to check cardnumber Entered or not
		if(objForm.getCardNumber()!=null  && objForm.getCardNumber().equals(""))
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardnorequired"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}
		CardHolderPaymentDto objDto  = new CardHolderPaymentDto();

		try{
			BeanUtils.copyProperties(objDto, objForm);
			CardHolderPaymentManager objManager = new CardHolderPaymentManager();

			//to check cardnumber existed or not
			CardsDto cardDto = new CardsDto();
			cardDto.setCardNumber(Long.parseLong(objDto.getCardNumber()));
			boolean cardNoCheck = objManager.validate(cardDto,0);
			if(cardNoCheck)
			{
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.cardnumbernotexit"));
				saveErrors(request, errors);
				return mapping.findForward("success");
			}else{

				CardManager objCardManager = new CardManager();

				String cardType = objCardManager.getCardType(String.valueOf(objDto.getCardNumber()));

				if(!ICacisiss.IBilling.CREDIT_CARD.equals(cardType)){
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.creditcardonly"));
					saveErrors(request, errors);
					return mapping.findForward("success");
				}


				Collection cardDataList = objManager.search(objDto);
				request.getSession(false).setAttribute("$CARDDATALIST$",cardDataList);
				CustomerServiceDataBean objCustService =(CustomerServiceDataBean)((ArrayList)cardDataList).get(0);;
				//After the Search Result
				BeanUtils.copyProperties(objForm,objCustService);

				CardsDto objCardsDto = objCardManager.getCard(String.valueOf(objDto.getCardNumber()));
				objForm.setCreditLimit(String.valueOf(objCardsDto.getCustAccountDto().getCreditLimit()));
				objForm.setCurrencyCode(String.valueOf(objCardsDto.getCustAccountDto().getCurrencyCode()));

				//this for setting  the billing address
				for(Iterator it = objCustService.getApplicationAddress().iterator();it.hasNext();) {
					CustomerAddressDto addressDto = (CustomerAddressDto) it
					.next();
					if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("H")
							&& objCustService.getBillingTo().equals("H")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					} else if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("S")
							&& objCustService.getBillingTo().equals("S")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					} else {
						// addressDto.getAddressType()!=null &&
						// addressDto.getAddressType().equals("C") &&
						// objCustService.getBillingTo().equals("C")
						// this to show company Address as defalut
						// BilllingAddress
						if (addressDto.getAddressType() != null
								&& addressDto.getAddressType().equals("C")
								&& objCustService.getBillingTo().equals("C")) {
							BeanUtils.copyProperties(objForm.getRequestAddress(),
									addressDto);
							break;
						}
					}
				}

				//calling preListData
				objForm.getPreListData();
				
				objForm.setButaction("SAVE");
				
			}//cardnumber check else close 	      
		}catch (Exception e){
			System.out.println("Error converting to form bean in CardHolderPaymentDispatchAction: " + e);
			throw new TPlusException("Could not populate the form bean in CardHolderPaymentDispatchAction: " + e);
		}


		saveToken(request);
		return mapping.findForward("success");
	}


	//	This method for saving the CardGrade applicationformdata	
	public ActionForward cardHolderPayment(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws TPlusException,Exception {

		ActionErrors errors = null;

		// Token Validation
		if(!isTokenValid(request))
		{	
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		}

		// Form Validations
		CardHolderPaymentForm objForm = (CardHolderPaymentForm) form;
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{

			saveErrors(request,errors);
			return mapping.findForward("success");

		}
		System.out.println("4");		
		// DTO Creation
		CardHolderPaymentDto objDto  = new CardHolderPaymentDto();

		try{
			BeanUtils.copyProperties(objDto, objForm);

		}catch (Exception e){
			System.out.println("Error converting to form bean CardHolderPaymentDispatchAction add method : " + e.getMessage());
			throw new TPlusException("Could not populate the form bean CardHolderPaymentDispatchAction add method: " + e);
		}

		String userId = (String)request.getSession().getAttribute("USERID");

		//Action Execution
		CardHolderPaymentManager objManager = new  CardHolderPaymentManager();
		CardManager objCardManager = new CardManager();
		boolean boolPayment= objManager.cardHolderPayment(objDto,userId);

		if(!boolPayment)
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addfailed"));
			saveErrors(request, errors);
			
			objForm.setButaction("SAVE");
			
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
			//request.getSession(false).removeAttribute("$CARDDATALIST$");
			
			Collection cardDataList = objManager.search(objDto);
			request.getSession(false).setAttribute("$CARDDATALIST$",cardDataList);
			CustomerServiceDataBean objCustService =(CustomerServiceDataBean)((ArrayList)cardDataList).get(0);;
			//After the Search Result
			BeanUtils.copyProperties(objForm,objCustService);

			CardsDto objCardsDto = objCardManager.getCard(String.valueOf(objDto.getCardNumber()));
			objForm.setCreditLimit(String.valueOf(objCardsDto.getCustAccountDto().getCreditLimit()));
			objForm.setCurrencyCode(String.valueOf(objCardsDto.getCustAccountDto().getCurrencyCode()));

			//this for setting  the billing address
			for(Iterator it = objCustService.getApplicationAddress().iterator();it.hasNext();) {
				CustomerAddressDto addressDto = (CustomerAddressDto) it
				.next();
				if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("H")
						&& objCustService.getBillingTo().equals("H")) {
					BeanUtils.copyProperties(objForm.getRequestAddress(),
							addressDto);
					break;
				} else if (addressDto.getAddressType() != null
						&& addressDto.getAddressType().equals("S")
						&& objCustService.getBillingTo().equals("S")) {
					BeanUtils.copyProperties(objForm.getRequestAddress(),
							addressDto);
					break;
				} else {
					// addressDto.getAddressType()!=null &&
					// addressDto.getAddressType().equals("C") &&
					// objCustService.getBillingTo().equals("C")
					// this to show company Address as defalut
					// BilllingAddress
					if (addressDto.getAddressType() != null
							&& addressDto.getAddressType().equals("C")
							&& objCustService.getBillingTo().equals("C")) {
						BeanUtils.copyProperties(objForm.getRequestAddress(),
								addressDto);
						break;
					}
				}
			}

			//calling preListData
			objForm.getPreListData();
			
			objForm.setButaction("NOSAVE");
		}

		resetToken(request);
		return mapping.findForward("success");
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
