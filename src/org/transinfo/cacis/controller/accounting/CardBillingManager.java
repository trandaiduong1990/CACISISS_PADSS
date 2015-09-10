package org.transinfo.cacis.controller.accounting;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.accounting.CardBillingDAO;
import org.transinfo.cacis.dto.accounting.CardBillingDto;

public class CardBillingManager {
	
	private  CardBillingDAO objDAO;

    public CardBillingManager()throws Exception {
    	
    	objDAO = DAOFactory.getInstance().getCardBillingDAO();
        
    }
    /*
	    * This method is used forchecking  the billing cycle date
	    */ 
	 public CardBillingDto checkBllingCycle(CardBillingDto objCardBillDto) throws TPlusException{
				
		 CardBillingDto objResDto= null;
               
		        try {
		        	objCardBillDto.setBillingCycleDone("BillingCycleDone");
		        	objResDto = objDAO.checkBllingCycle(objCardBillDto);

		        } catch (Exception e) {
		        	 throw new TPlusException("Error in CardBillingManager checkBllingCycle method" +e);
		        }
		   
		 return objResDto;
			
		}

}
