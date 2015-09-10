
package org.transinfo.cacis.action.applicationforms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import org.transinfo.cacis.controller.applicationforms.CardGradeFormManager;
import org.transinfo.cacis.dto.applicationforms.CardGradeFormDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.common.AddressDto;
import org.transinfo.cacis.formbean.applicationforms.CardGradeForm;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;

public class CardGradeFormDispatchAction extends BaseDispatchAction {
		
   
	/*
	 * this method is for Get all the  Data using  model's(CustomerSevice class getCustomerServiceData() method) 
	 */             
	public ActionForward search(ActionMapping mapping,
             ActionForm form,
             HttpServletRequest request,
             HttpServletResponse response) throws TPlusException,Exception {
		 
		     ActionErrors errors = null;
		     CustomerServiceDataBean objCustService =null;
		     CardGradeForm objForm = (CardGradeForm) form;
		     CardGradeFormDto objDto  = new CardGradeFormDto();
    
      try{   
    	     
             BeanUtils.copyProperties(objDto, objForm);
             
             CardGradeFormManager objManager = new CardGradeFormManager();
           
             Collection carddataList = objManager.search(objDto);
             objCustService =(CustomerServiceDataBean)((ArrayList)carddataList).get(0);;
               
                           
            //After the Search Result copying all card holder data to form
             BeanUtils.copyProperties(objForm,objCustService);
          
            //To show the Account Details(saving(ATM link Account1) and checking(AtmLinkAccount2)   
 		     Set objAccount =objCustService.getCustomerAccount();
 		    for(Iterator it =objAccount.iterator();it.hasNext();){
 	    	   CustomerAccountDto objCustAcc =(CustomerAccountDto)it.next();
 	    	  objForm.setSavingAccount(objCustAcc.getSavingAccount());
 	    	  objForm.setCheckingAccount(objCustAcc.getCheckingAccount());
 	        }
 		  
         }catch (Exception e){
              System.out.println("Error converting to form bean in CardGradeFormDispatchAction: " + e.getMessage());
              throw new TPlusException("Could not populate the form bean in CardGradeFormDispatchAction: " + e);
           }
            
              //calling preListData
              objForm.getPreListData();
             
             // for checking wether the enter card primary card or not(for primary cards only Grading possible) 
     		if(!(objCustService.getCardHolderType()!=null && objCustService.getCardHolderType().equals("1"))){
     			errors = new ActionErrors();
     			errors.add("Error", new ActionError("error.cardchecking"));
     			saveErrors(request,errors);
     			 request.setAttribute("ACTION","cancel");
     			return mapping.findForward("success");
     		  } 
     		
             saveToken(request);
             request.setAttribute("ACTION","search");
             return mapping.findForward("success");
     }

//This method for saving the CardGrade applicationformdata	
 public ActionForward add(ActionMapping mapping,
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
		CardGradeForm objForm = (CardGradeForm) form;
		//errors = objForm.validate(mapping,request);
	
		//For checking Selected Cards To Cancel or Split 
		if(objForm.getCardsCancelOrSplit()!=null && objForm.getCardsCancelOrSplit().length>1){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardscacelorsplit"));
		  }
		
		//for checking Selected CardProducts 
	
		if(objForm.getSelectedAppCardProducts()!=null && objForm.getSelectedAppCardProducts().length>1){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.onecardselect"));
		  }
		
    
		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","search");
		    return mapping.findForward("success");
			
		}
			
         // DTO Creation
		    CardGradeFormDto objCardGradeDto  = new CardGradeFormDto();
                     
     try{
    	     BeanUtils.copyProperties(objCardGradeDto, objForm);
                      
        }catch (Exception e){
          System.out.println("Error converting to form bean CardGradeFormDispatchAction save method : " + e.getMessage());
          throw new TPlusException("Could not populate the form bean CardGradeFormDispatchAction save method: " + e);
          }
        
          //Action Execution
           CardGradeFormManager objManager = new CardGradeFormManager();
            boolean boolAdd = objManager.add(objCardGradeDto);
          
         if(!boolAdd)
   		{
   			errors = new ActionErrors();
   			errors.add("Error", new ActionError("error.addfailed"));
   			saveErrors(request, errors);
   		}
   		else
   		{
   			errors = new ActionErrors();
   			errors.add("Error", new ActionError("error.addSuccess"));
   			saveErrors(request, errors);
   		}
          
     resetToken(request);
     return mapping.findForward("actionsuccess");
    }
	
	
//Mehtods For CardGradeApplicationProcess
	
 public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
    		ActionErrors errors = null;
		
      // Token Validation
		if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		}
		
		// Form Validations
	    CardGradeForm objForm = (CardGradeForm) form;
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","update");
			return mapping.getInputForward();
		}

		// DTO Creation
		 CardGradeFormDto objDto = new  CardGradeFormDto();
		
		 try
		{
    		BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

	   // Action Execution
	    CardGradeFormManager objManager = new  CardGradeFormManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate)
		{
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			request.setAttribute("ACTION","update");
			saveErrors(request, errors);
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}
    	  // Success
		  resetToken(request);
		//request.setAttribute("ACTION","cancel");
		 return mapping.findForward("success");
	}

 public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

	 CardGradeFormManager objManager = new  CardGradeFormManager();
	 CardGradeForm objForm = (CardGradeForm) form;

		// DTO Creation
	    CardGradeFormDto objDto = new CardGradeFormDto();
		objDto = objManager.getCardGradeFormDto (request.getParameter("applicationId"));
		try
		{
			BeanUtils.copyProperties(objForm, objDto);
		//this for copying this address data for this application holder	
		for(Iterator it = objDto.getApplicationAddress().iterator();it.hasNext();) {
			 AddressDto addressDto = (AddressDto) it.next();
			
		  if(addressDto.getAddressType()!=null && addressDto.getAddressType().equals("H")){
			 BeanUtils.copyProperties(objForm.getHomeAddress(), addressDto);
			  }
		  if(addressDto.getAddressType()!=null && addressDto.getAddressType().equals("C")){
				 BeanUtils.copyProperties(objForm.getCompanyAddress(), addressDto);
			  } 
				  
		 }
		}
		catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		 //calling preListData
          objForm.getPreListData();
		//Success
		saveToken(request);
		request.setAttribute("ACTION","update");
		return mapping.findForward("success");
	}


	/*
	 * this method is used for accepting the CardGradeForm
	 *  */ 
	
	 public ActionForward accept(ActionMapping mapping,
	            ActionForm form,
	            HttpServletRequest request,
	            HttpServletResponse response)
	        throws TPlusException,Exception {
		 
		 	
		    ActionErrors errors = null;
		   CardGradeForm objForm = (CardGradeForm)form;
      		 
			// Token Validation
			if(!isTokenValid(request))
			{	errors = new ActionErrors();
				errors.add("Error", new ActionError("error.duplicate"));
				saveErrors(request,errors);
				return mapping.findForward("token");
			}
		  
                  
			// DTO Creation
			CardGradeFormDto objDto = new CardGradeFormDto();
		    
		  try
	       {
			
		   BeanUtils.copyProperties(objDto,objForm);
          
		   CardGradeFormManager objManager = new CardGradeFormManager();
		
		   //this for calling search method in DAOImpl to get the  CustomerServiceDataBean
	        Collection dataList = objManager.search(objDto);
           CustomerServiceDataBean objCustService =(CustomerServiceDataBean)((ArrayList)dataList).get(0);
         
           // this to checking  whether to close the cards or splits the cards according to user selection
            String cardsCancelOrSplit[] =objForm.getCardsCancelOrSplit();
            if(cardsCancelOrSplit!=null && cardsCancelOrSplit[0].equals("Cancel")){
        	      Set cards =	objCustService.getCustomerCards();
                  String cardsToCancel[] =new String[cards.size()];
              int i=0;
              for(Iterator it =cards.iterator();it.hasNext();){
		    	 CardsDto objCardsDto = new CardsDto();
		       objCardsDto = (CardsDto)it.next();
		       //checking the card holder type if primcardholder close all his supply cards
		       if(objCustService.getCardHolderType()!=null && objCustService.getCardHolderType().equals("1")){
		    	   cardsToCancel[i] = String.valueOf(objCardsDto.getCardNumber());
		    	   System.out.println("\n\nCards To Close in DispatchAction:" +cardsToCancel[i]);
		    	   i++;
		        }
              }
              //setting the cardsToCancel to the CardGradeFormDto
                objDto.setCardsToCancel(cardsToCancel);
            }
            
            //to set the Customer Account to newly created cards object
             if(objCustService.getCustomerAccount()!=null){
          		Set objAccount =objCustService.getCustomerAccount();
         	     for(Iterator it =objAccount.iterator();it.hasNext();){
         		   CustomerAccountDto objCustAcc =(CustomerAccountDto)it.next();
         	       objDto.setCustomerAccountDto(objCustAcc);
       	         }
              } 
         
            //Setting values to the CardGradeFormDto to insert into Cards Table 
             objDto.setCardHolderType(Integer.parseInt(objCustService.getCardHolderType()));
             objDto.setCustomerId(objCustService.getCustomerId());
                        
           }catch (Exception e) {
	          System.out.println("Error converting to form bean: " + e);
	          throw new TPlusException("Could not populate the form bean: " + e);
	       }
	     	// Action Execution
	       CardGradeFormManager objManager = new CardGradeFormManager();
	        boolean boolAccept = objManager.accept(objDto);
	    
	       if (!boolAccept)
	 		{
	 			
	 			errors = new ActionErrors();
	 			errors.add("Error", new ActionError("error.acceptfailed"));
	 			saveErrors(request, errors);
	 		}
	 		else
	 		{
	 			errors = new ActionErrors();
	 			errors.add("Error", new ActionError("error.acceptSuccess"));
	 			saveErrors(request, errors);
	 		}
      //Success
	  resetToken(request);
	  return mapping.findForward("actionsuccess");
	      }  
	
	 /*
     * this method is used for rejecting the CardGradeForm
     *
     */ 
	 public ActionForward reject(ActionMapping mapping,
	            ActionForm form,
	            HttpServletRequest request,
	            HttpServletResponse response)
	        throws TPlusException,Exception {
		  
			ActionErrors errors = null;

			// Token Validation
			if(!isTokenValid(request))
			{	errors = new ActionErrors();
				errors.add("Error", new ActionError("error.duplicate"));
				saveErrors(request,errors);
				return mapping.findForward("token");
			}
		
			if(errors!=null && !errors.isEmpty())
			{
				saveErrors(request,errors);
		        request.setAttribute("ACTION","replace");
				return mapping.getInputForward();
			}
			CardGradeForm objForm = (CardGradeForm)form;
         // DTO Creation
			CardGradeFormDto objCardRepDto = new CardGradeFormDto();
	          try {
	        	   BeanUtils.copyProperties(objCardRepDto,objForm);
	            }catch (Exception e){
	                System.out.println("Error converting to form bean: " + e.getMessage());
	                throw new TPlusException("Could not populate the form bean: " + e);
	          }
	        	// Action Execution
	            CardGradeFormManager objManager = new  CardGradeFormManager();
	              boolean boolReject  = objManager.reject(objCardRepDto);
	                
	          	if (!boolReject)
	    		{

	    			errors = new ActionErrors();
	    			errors.add("Error", new ActionError("error.rejectfailed"));
	    			saveErrors(request, errors);
	    		
	    		}
	    		else
	    		{
	    			errors = new ActionErrors();
	    			errors.add("Error", new ActionError("error.rejectSuccess"));
	    			saveErrors(request, errors);
	    		}
             // Success
	    	resetToken(request);
	        return mapping.findForward("actionsuccess");
	      }   
	  
 }
