package org.transinfo.cacis.action.accounting;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
import org.transinfo.cacis.controller.accounting.CardBillingManager;
import org.transinfo.cacis.dto.accounting.CardBillingDto;
import org.transinfo.cacis.formbean.accounting.CardBillingForm;

@SuppressWarnings("deprecation")
public class CardBillingDispatchAction extends BaseDispatchAction {
	
	
	 /*
     * this method is used for Checking   cardBilling Cycle
     */     
public ActionForward checkBllingCycle(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
        throws TPlusException,Exception {
	 
	   ActionErrors errors = null;
	   //form
	   CardBillingForm objForm = (CardBillingForm)form;
	   objForm.setIssuerId((String)request.getSession(false).getAttribute("ISSUER"));
	   //DtoCreation
	   CardBillingDto objDto  =  new CardBillingDto();
        
           try {
                BeanUtils.copyProperties(objDto,objForm);
              //Action execution
           	    CardBillingManager objManager = new CardBillingManager();
               	 objDto = objManager.checkBllingCycle(objDto);
               	 BeanUtils.copyProperties(objForm,objDto);
               }
              catch (Exception e){
            	  System.out.println("Error converting to form bean in CardBillingDispatchAction  checkBllingCycle method: " + e.getMessage());
                  throw new TPlusException("Could not populate the form bean in CardBillingDispatchAction checkBllingCycle method: " + e);
          }
              
          	//to checking Day of Month
   	 		int dayOfMonth = objDto.getNextCycleDate();
   	 	  // if( dayOfMonth==0)   System.out.println("Billing error");
   	 	   	GregorianCalendar c = new GregorianCalendar();
   	 	   if (c.get(Calendar.DAY_OF_MONTH) != dayOfMonth){
		       	  errors = new ActionErrors();
				  errors.add("Error", new ActionError("error.todayisnotbillingdate"));
				  saveErrors(request, errors);
			}else
				{
			  // errors = new ActionErrors();
		       // errors.add("Error", new ActionError("error.todayisbillingdate"));
		      // saveErrors(request, errors);
		        request.setAttribute("TODAYISBILLINGDATE", "");
		        }
	  return mapping.findForward("success");
}  
    
}
