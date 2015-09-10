package org.transinfo.cacis.dataacess.daoimpl.oracle.accounting;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.accounting.CardBillingDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.accounting.CardBillingDto;

public class CardBillingDAOImpl extends BaseDAOImpl implements CardBillingDAO {
	
	
    /*to check  the CardBilling Cycle date and calculating  the next cycle*/
    public CardBillingDto checkBllingCycle(CardBillingDto objCardBillDto) throws TPlusException {
			
	  	 Transaction tx =null;
	   	 StringBuffer sbf = new StringBuffer();
	   	try	
	   	  {
	   	   Session	session = HibernetFactory.currentSession();
	   	   				 tx = session.beginTransaction();
	   	  //to previous Cycle  				 
	   	   String prevCycle = (String)session.createQuery("select pr.value from ParametersDto pr where pr.name = '"+objCardBillDto.getBillingCycleDone()+"' ").list().get(0);
	       objCardBillDto.setPrevCycleNo(Integer.valueOf(prevCycle).intValue());
	       // System.out.println("previous Cycle Done" +prevCycle);
	       
	       //to show prevcycle date
	       int prevCycleDate = ((Integer)(session.createQuery("select cs.dayOfMonth from CycleDto cs where cs.cycleNo = "+prevCycle+"  ").iterate()).next()).intValue();
	       objCardBillDto.setPrevCycleDate(prevCycleDate);
	    
	      //  System.out.println("previous Cycle Done Date" +prevCycleDate);
	   	   
	   	   //to calculate next cycle 
	       int numberOfCyles = ((Integer)session.createQuery("select count(*) from CycleDto cs where cs.issuerId= '"+objCardBillDto.getIssuerId()+"' "). list().get(0)).intValue();;
	      // System.out.println("number of cycles for this issuer" +numberOfCyles);
	         sbf.append("select  cs.cycleNo,cs.dayOfMonth from CycleDto cs ");
 	 		 sbf.append("where ");
 	 		 sbf.append("cs.cycleNo = MOD("+prevCycle+" , "+numberOfCyles+" )+1 ");
 	 		 Query qry = session.createQuery(sbf.toString());
		 	 List resultList = qry.list();
		    for(Iterator it = resultList.iterator();it.hasNext();){
			    Object obj[]= (Object[])it.next();
			    objCardBillDto.setNextCycleNo(Integer.parseInt(String.valueOf(obj[0])));
			    objCardBillDto.setNextCycleDate(Integer.parseInt(String.valueOf(obj[1])));
			 // System.out.println("the Next Cycle Number:" +objCardBillDto.getNextCycleNo());
			//  System.out.println(" Next Cycle  Date :" +objCardBillDto.getNextCycleDate());
		 }
 	 		
		    //to show accounts to process for this cycle 
		     int numberOfAccounts = ((Integer)session.createQuery("select count(*) from CustomerAccountDto ca where ca.cycleNo= "+objCardBillDto.getNextCycleNo()+" "). list().get(0)).intValue();
		     objCardBillDto.setNumberOfAccounts(numberOfAccounts);
 	 	
 	 		 tx.commit();
	   	  
 	 	 	 		 
	   	  }catch (Exception e){
	   		  if(tx!=null)
	   			{
	   				tx.rollback();
	   			}
	   		  System.out.println("Error in CardBillingDAOImpl checkBllingCycle  method "+e.getMessage());
	   		 throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardBillingDAOImpl checkBllingCycle method"+e);
	   		}
	   	  finally
	   		{
	   			HibernetFactory.closeSession();
	   	 	}
	
	   	  return objCardBillDto;
	   }  
}
