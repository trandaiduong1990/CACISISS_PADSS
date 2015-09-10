package org.transinfo.cacis.test;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.customerservice.CardReplacementManager;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dto.accounting.CardBillingDto;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalHistoryDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.csr.CsrSearchDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementDto;
import org.transinfo.cacis.dto.customerservice.CreditSplitDto;
import org.transinfo.cacis.formbean.csr.CsrForm;
import org.transinfo.cacis.formbean.customerservice.CreditSplitForm;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;
import org.transinfo.cacis.formbean.customerservice.LimitForm;
import org.transinfo.cacis.model.CustomerService;
import org.transinfo.cacis.util.DateUtil;

public class CacisDataTestProgram {
	
	public CacisDataTestProgram(){}
public void getCardData() throws TPlusException{
		Transaction tx = null;

		try
		{
			Session session =HibernetFactory.currentSession();
			tx =session.beginTransaction();
		   Query qy = session.createQuery("from CardsDto ca ,CardProductDto cpd  where ca.cardProductId=cpd.cardProductId and ca.cardNumber = 1724570000000022 ");
		  // List list = qy.list();
		   //System.out.println("size="+list.size()+"  "+list.get(0).getClass().getName());
			//CardsDto objCardsDto = (CardsDto)list.get(0);
			CardsDto objCardsDto = (CardsDto)session.get(CardsDto.class,new Long("1724570000000022"));
			System.out.println("the CardStatus is" +objCardsDto.getCardStatus());
			System.out.println("the CreditLimit is" +objCardsDto.getCreditLimit());
			System.out.println("the cardProductName from CardProductDto is" +objCardsDto.getCardProductsDto().getCardType());
			
			tx.commit();
				
		}

		catch (Exception e)
		{
			System.out.println("Exception" +e);

			if(tx!=null)
			{
				tx.rollback();
			}
		throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while received method in CardDeliverDAOImpl"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}
	}
		   
	public void getCardStop() throws TPlusException{
		

		try
		{
			Session session =HibernetFactory.currentSession();
			
			 CustomerService objCustServ = new CustomerService();
			 CardsDto objCardsDto =null;
			   CustomerServiceDataBean objService = objCustServ.getCustomerServiceData(new Long("1724570000000022").longValue());
			 
			   
			   if(objService.getCardHolderType().equals("1") || objService.getCardHolderType().equals("2")){
				   System.out.println("######PERSONALINFO######");
				   System.out.println("CustomerName:" +objService.getCustomerName());
				   System.out.println("######CARDINFO######");
				   System.out.println("CardNumber" +objService.getCardNumber());
						  
			    Set cards =	objService.getCustomerCards();
			    for(Iterator it =cards.iterator();it.hasNext();){
				   objCardsDto = new CardsDto();
			       objCardsDto = (CardsDto)it.next();
			   	   if(objCardsDto.getCardHolderType()==2){
						  System.out.println("######SUPPLEMENTARYCARDS######");
					      System.out.println("SupplementaryCardNumber" +objCardsDto.getCardNumber());
					   }else if(objCardsDto.getCardHolderType()==1){
							  System.out.println("######MAINCARDS######");
						      System.out.println("MainCardCardNumber" +objCardsDto.getCardNumber());
						   }
				   }  
				   
			   }	   
			    
			 /*    if(objService.getCardHolderType().equals("2")){
			    	   System.out.println("######SuppCard PERSONALINFO######");
					   System.out.println("CustomerName:" +objService.getCustomerName());
					   System.out.println("###### SuppCard CARDINFO######");
					   System.out.println("CardNumber" +objService.getCardNumber());
			    	
					   Set suppcards =	objService.getCustomerCards();
					   for(Iterator it =suppcards.iterator();it.hasNext();){
						   objCardsDto = new CardsDto();
					      objCardsDto = (CardsDto)it.next();
					      if(objCardsDto.getCardHolderType()==1){
							  System.out.println("######MAINCARDS######");
						      System.out.println("MainCardCardNumber" +objCardsDto.getCardNumber());
						   }
					   }*/
			 
			    	
			
						
		}

		catch (Exception e)
		{
			System.out.println("Exception" +e);

		throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while received method in CardDeliverDAOImpl"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}
	}
	
	
	public void accountInfoTest() throws TPlusException{
		Transaction tx = null;
		  Collection accountDetails = new ArrayList();
		CommonDataBean objCommBean =null;
		try
		{
			StringBuffer sbf = new StringBuffer();
			Session session =HibernetFactory.currentSession();
			tx =session.beginTransaction();
			 sbf.append("select acc.creditLimit,acc.cashLimit,acc.previousBalance, acc.limitUsed,acc.amountCredited, ");
			 sbf.append("acc.amountDebited, acc.authorizationAmt,acc.lastStatementDate,acc.lastStatementDueDate,acc.currentMinPaymentDue ");
			 sbf.append("from CardsDto ca left  join  fetch ca.custAccountDto acc   where ca.cardNumber = 1724570000000022");
			 Query qy = session.createQuery(sbf.toString());
			 // Query qy = session.createQuery("from ApplicationFormDto appl left join fetch appl.appDocProofs left join fetch appl.appCardProducts where appl.applicationId ='3'");
	        // Query qy = session.createQuery("from CardsDto ca left  join  fetch ca.custAccountDto   where ca.cardNumber = 1724570000000022 ");
			 //can use for CSR AccountDetails
		//Query qy = session.createQuery("from CardsDto ca left  join  fetch ca.custAccountDto acc  where ca.cardNumber = 1724570000000022 ");
	         
		   List resultList = qy.list();
		   System.out.println("the list isze is" +resultList.size());
		   
		   for(Iterator it = resultList.iterator();it.hasNext();){
			    Object obj[]= (Object[])it.next();
			    objCommBean = new CommonDataBean();
			  
			    System.out.println("#######Account Details####:");
			  
			    objCommBean.setColumn1(String.valueOf(obj[0]));
			    objCommBean.setColumn2(String.valueOf(obj[1]));
			    objCommBean.setColumn3(String.valueOf(obj[2]));
			    objCommBean.setColumn4(String.valueOf(obj[3]));
			    objCommBean.setColumn5(String.valueOf(obj[4]));
			    objCommBean.setColumn6(String.valueOf(obj[5]));
			    objCommBean.setColumn7(String.valueOf(obj[6]));
			    objCommBean.setColumn8(DateUtil.convertDateToDateString((Date)obj[7]));
			    objCommBean.setColumn9(DateUtil.convertDateToDateString((Date)obj[8]));
			    objCommBean.setColumn10(String.valueOf(obj[9]));
			    accountDetails.add(objCommBean);
		   } 
		   System.out.println("AccountList" +accountDetails.size()); 
		}
		catch (Exception e)
		{
			System.out.println("Exception" +e);

		throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while received method in CardDeliverDAOImpl"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}
	}
	
	public void customerSummaryTest() throws TPlusException{
		  Transaction tx = null;
		  CommonDataBean objCommBean =null;
		try
		{   
			StringBuffer sbf = new StringBuffer();
			Session session =HibernetFactory.currentSession();
			tx =session.beginTransaction();
		   
			 sbf.append("select app.customerName,app.embossingName, app.idNumber, app.expDate, app.dob,app.referenceCompany,");
		     sbf.append("cr.cardNumber,cp.cardProductName, cr.creditLimit,cr.cardExpDate, cr.cardStatus,cht.description,  ");
		     sbf.append("add.address1 ,add.address2,add.city,add.state,add.country,add.postalCode,add.phone,add.fax,app.customerId ");
		   	 sbf.append("From ApplicationProcessDto app, CardsDto cr, CardProductDto cp,CustomerAddressDto add,CardHolderTypeDto cht ");
		   	 sbf.append("where ");
		   	 sbf.append("cr.customerId = app.customerId and ");
		   	 sbf.append("cr.cardProductId =cp.cardProductId and ");
		     sbf.append("cr.cardHolderType = cht.cardHolderTypeId and ");
			 sbf.append("app.customerId = add.appFormProcssDto.customerId and ");
			 sbf.append("app.billingTo = add.addressType and ");
			 sbf.append("cr.cardNumber = 1724570000000022 ");
		   
			 Query qry = session.createQuery(sbf.toString());
		   	 List resultList = qry.list();
		  
		     for(Iterator it = resultList.iterator();it.hasNext();){
			    Object obj[]= (Object[])it.next();
			    objCommBean = new CommonDataBean();
			  
			    System.out.println("#######CustomerDetails####:");
			  
			    objCommBean.setColumn1((String)obj[0]);
			    objCommBean.setColumn2((String)obj[1]);
			    objCommBean.setColumn3((String)obj[2]);
			    objCommBean.setColumn4(DateUtil.convertDateToDateString((Date)obj[3]));
			    objCommBean.setColumn5(DateUtil.convertDateToDateString((Date)obj[4]));
			    objCommBean.setColumn6((String)obj[5]);
			   			   
			    System.out.println("Name :  "+objCommBean.getColumn1());
			    System.out.println("Name on Card :  "+objCommBean.getColumn2());
			    System.out.println("Nric/Pass No :  "+objCommBean.getColumn3());
			    System.out.println("Nric Expire :  "+objCommBean.getColumn4());
			    System.out.println("Date Of Birtth:  "+objCommBean.getColumn5());
			    System.out.println("company Name:  "+objCommBean.getColumn6());
			    
			   System.out.println("#######CardDetails#### :");
			  
			   objCommBean.setColumn7(String.valueOf(obj[6]));
			   objCommBean.setColumn8((String)obj[7]);
			   objCommBean.setColumn9(String.valueOf(obj[8]));
			   objCommBean.setColumn10((String)obj[9]);
			   objCommBean.setColumn11((String)obj[10]);
			   objCommBean.setColumn12((String)obj[11]);
			  
			    System.out.println("Card Number :  "+objCommBean.getColumn7());
			    System.out.println("CardProductName:  "+objCommBean.getColumn8());
			    System.out.println("CreditLimit :  "+objCommBean.getColumn9());
			    System.out.println("Expire :  "+objCommBean.getColumn10());
			    System.out.println("Card Status :  "+objCommBean.getColumn11());
			    System.out.println("CardHolderType:  "+objCommBean.getColumn12());
			   
			    System.out.println("#######Billing Address#### :"); 
			   
			   objCommBean.setColumn13((String)obj[12]);
			   objCommBean.setColumn14((String)obj[13]);
			   objCommBean.setColumn15((String)obj[14]);
			   objCommBean.setColumn16((String)obj[15]);
			   objCommBean.setColumn17((String)obj[16]);
			   objCommBean.setColumn18(String.valueOf(obj[17]));
			   objCommBean.setColumn19((String)obj[18]);
			   objCommBean.setColumn20((String)obj[19]);
			   
			   
			    System.out.println("Address1 :  "+objCommBean.getColumn13());
			    System.out.println("Address12:  "+objCommBean.getColumn14());
			    System.out.println("City :  "+objCommBean.getColumn15());
			    System.out.println("State :  "+objCommBean.getColumn16());
			    System.out.println("Country :  "+objCommBean.getColumn17());
			    System.out.println("Psotal Code:  "+objCommBean.getColumn18());
			    System.out.println("Phone:  "+objCommBean.getColumn19());
			    System.out.println("fax:  "+objCommBean.getColumn20());
			    
			    objCommBean.setColumn21((String)obj[20]);
			    System.out.println("CustomerId:  "+objCommBean.getColumn21());
			 //  historyCollection.add(objCommBean);

		      }
		   	 
	       
		  
		}
		catch (Exception e)
		{
			System.out.println("Exception" +e);

		throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while received method in CardDeliverDAOImpl"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}
	}
	//csr 
	public void customerInfoTest() throws TPlusException{
		Transaction tx = null;

		try
		{
			StringBuffer sbf = new StringBuffer();
			Session session =HibernetFactory.currentSession();
			tx =session.beginTransaction();
		//	 sbf.append("select cf.customerName from CustomerInfoDto cf left join fetch cf.applicationAddress,CardsDto ca ");
		//	 sbf.append("where cf.customerId =ca.customerId ");
		//	 sbf.append("and ca.cardNumber = 1724570000000022");
			//session.get(CustomerInfoDto.class,"C0605133984");
		//	 sbf.append("from CustomerInfoDto cf where cf.customerId = 'C0605133984' ");
			
			sbf.append("from CustomerInfoDto cf where cf.customerId =(select cr.customerId from CardsDto cr where cr.cardNumber=1724570000000022) ");
			// CustomerInfoDto cfDto = (CustomerInfoDto)session.get(CustomerInfoDto.class,"C0605133984");
			// System.out.println("the list isze is" +cfDto.getApplicationAddress().size());
			 
	       Query qy = session.createQuery(sbf.toString());
		   List ls = qy.list();
	       CustomerInfoDto cfDto =(CustomerInfoDto)ls.get(0);
	       
		  System.out.println("the list isze is" +cfDto.getCustomerId());
		  
		  for(Iterator it = cfDto.getApplicationAddress().iterator();it.hasNext();) {
				 CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
				 System.out.println("the list isze is" +addressDto.getAddress1());
			 }
		}
		catch (Exception e)
		{
			System.out.println("Exception" +e);

		throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while received method in CardDeliverDAOImpl"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}
	}
	public Map cardDetailsTest(CsrForm objSearchDto ) throws TPlusException{
		
		  Map CardDetailsList = new TreeMap();
		    ArrayList allCards = new ArrayList();
		    ArrayList primaryCards = new ArrayList();
		    ArrayList otherCards = new ArrayList();
		    ArrayList supplyCards = new ArrayList();
		    Transaction tx = null;
		    CommonDataBean objCommBean = null;
			try
			{
				StringBuffer sbf = new StringBuffer();
				Session session =HibernetFactory.currentSession();
				tx =session.beginTransaction();
		      
			sbf.append("select cr.customerId,cr.cardHolderType from CardsDto cr where cr.cardNumber= "+objSearchDto.getCardNumber()+" ");
		        Query qy = session.createQuery(sbf.toString());
			    List lst = qy.list();
			    Object custIdObj[] =(Object[])lst.get(0);
					      
			    if((String.valueOf(custIdObj[1]).equals(String.valueOf(CommonCodes.PRIMARYCARD_HOLDER)))){//if enter card is primary card 
					
				//getting all other cardstype for this customer execpt this card	
				 sbf = new StringBuffer();
				 sbf.append("select cr.cardHolderType,cr.cardNumber from CardsDto cr where cr.customerId = '"+ custIdObj[0]+"' and cr.cardNumber != "+objSearchDto.getCardNumber()+" ");
			     Query qy1 = session.createQuery(sbf.toString());
		 		 List ls1 = qy1.list();
		 		
		 		 System.out.println("list size"+ls1.size());
		         //with out this card adding all cards to array
		 		 for(int i=0;i<ls1.size();i++){ 
		 			 System.out.println("1");
		 			   Object allObj[] =(Object[])ls1.get(i);
		 			  System.out.println("2");
		 			   allCards.add(allObj[1]);
		 			  System.out.println("3");
		 			   }
		 		    	  
		 		     if(allCards.size()>0){//selecting data for all cards
				     
				    	sbf = new StringBuffer();
				    	sbf.append("select cr.cardNumber,cp.cardProductName,cr.cardExpDate,cr.creditLimit,cr.cardStatus,cr.cardHolderType ");
						sbf.append("from CardsDto cr,CardProductDto cp ");
					    sbf.append("where ");
						sbf.append("cr.cardProductId = cp.cardProductId and ");
						sbf.append("cr.cardNumber in(:cardsno) ");
						
						 Query cardsQy = session.createQuery(sbf.toString()).setParameterList("cardsno",allCards);
					     List resList = cardsQy.list();
					     System.out.println("All CardsList list size"+resList.size());
					     
					   for(Iterator it = resList.iterator();it.hasNext();){
						   System.out.println("4");
							    Object obj[]= (Object[])it.next();
							    System.out.println("5");
							    objCommBean = new CommonDataBean();
							    objCommBean.setColumn1(String.valueOf(obj[0]));
								objCommBean.setColumn2((String)obj[1]);
								objCommBean.setColumn3(String.valueOf(obj[2]));
								objCommBean.setColumn4((String)obj[3]);
							    objCommBean.setColumn5((String)obj[4]);
							    System.out.println("6");
							    //this for other cards records
							    if((String.valueOf(obj[5]).equals(String.valueOf(CommonCodes.PRIMARYCARD_HOLDER)))){
							    	 System.out.println("7");
							      otherCards.add(objCommBean);
					          } else{ //this for  supply cards records
					        	  System.out.println("8");
					        	   	  supplyCards.add(objCommBean);
					             }
					     }
					   System.out.println("9");
					     //this for other cards records adding to map  
						    CardDetailsList.put("OtherCards",otherCards);
						    System.out.println("OtherCards list size"+otherCards.size());
	                       //this for supply cards records adding to map  
						    CardDetailsList.put("SupplyCards",supplyCards); 
						    System.out.println("SupplyCards list size"+supplyCards.size());
						    System.out.println("10");
		 		     }    
			}else
				 {//if enter card is supply card for showing primary card details 
				   
				    sbf = new StringBuffer();
			      	sbf.append("select cr.cardNumber,cp.cardProductName,cr.cardExpDate,cr.creditLimit,cr.cardStatus ");
					sbf.append("from CardsDto cr,CardProductDto cp ");
				    sbf.append("where ");
					sbf.append("cr.cardProductId = cp.cardProductId and ");
					sbf.append("cr.customerId =(select sc.appProcessDto.customerId from CardsDto c,SupplementaryCardHolderDto sc ");	
					sbf.append("where c.customerId = sc.supplementaryId  and c.cardNumber=4563270000000033)");
					 //sbf.append("cr.cardNumber ="+objSearchDto.getCardNumber()+" ");
					
					Query primaryCardQy = session.createQuery(sbf.toString());
				    List resPrimaryList = primaryCardQy.list();
				    System.out.println("primaryCardList list size"+resPrimaryList.size()); 
				    
				    for(Iterator it = resPrimaryList.iterator();it.hasNext();){
					    Object obj[]= (Object[])it.next();
					    objCommBean = new CommonDataBean();
					    objCommBean.setColumn1(String.valueOf(obj[0]));
						objCommBean.setColumn2((String)obj[1]);
						objCommBean.setColumn3(String.valueOf(obj[2]));
						objCommBean.setColumn4((String)obj[3]);
					    objCommBean.setColumn5((String)obj[4]);
					    //adding all the bean objects to arraylist
					    primaryCards.add(objCommBean);
			      }    
			            //adding the primary cards records  to map  
					     CardDetailsList.put("PrimaryCards",primaryCards); 
			  }
		           tx.commit();
			 
			}		   
		catch (Exception e)
		{
			System.out.println("Exception" +e);

		throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while received method in CardDeliverDAOImpl"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}
		return CardDetailsList;

}
	
	 
    public void callRecordLogTest()throws TPlusException { 
    	CommonDataBean objCommBean =null;
    	Transaction tx = null;
    	 Collection searchList = new ArrayList();
	try	{
    /* CallRecordLogDto objCallLogDto = new CallRecordLogDto();
		   Date callStartTime = new Date();
		   objCallLogDto.setReferenceNo("RF0606167578");
		   objCallLogDto.setCardNumber(3000022);
		   objCallLogDto.setIssuerId("Issuer1");
		 //  objCallLogDto.setCallStartTime(callStartTime);
		   objCallLogDto.setCallStatus(CommonCodes.CALLSTATUS_OPEN);
		   objCallLogDto.setUserId("AspSuperAdmin");
		   
		   objCallLogDto.setCallTypeId("ca");
		  // objCallLogDto.setPreReferenceNo(" ");   
		   objCallLogDto.setCallEndTime(callStartTime);
		   
		 System.out.println("D1");*/
		  StringBuffer sbf = new StringBuffer();
		  Session	session = HibernetFactory.currentSession();
		  				tx  = session.beginTransaction();
	    // session.save(objCallLogDto);
	    // session.update(objCallLogDto);
		// sbf.append("select crl.cardNumber from CallRecordLogDto crl where crl.referenceNo= '1'");
		// CallRecordLogDto objDto = (CallRecordLogDto)session.get(CallRecordLogDto.class,"1");
		 sbf = new StringBuffer();
		 sbf.append("select cr.cardNumber,app.customerName, app.idNumber, app.dob ");
		 sbf.append("from ApplicationProcessDto app left join fetch app.customerCards cr ");
		 //sbf.append("from ApplicationProcessDto app join Cards cr  ");
		// sbf.append("from ApplicationProcessDto app,CardsDto cr  ");
	    // sbf.append(" and app.customerId = cr.customerId ");
	   // sbf.append("where cr.cardNumber like '%1724570000000022%' ");
	     sbf.append("where cr.cardNumber =(select crl.cardNumber from CallRecordLogDto crl where crl.referenceNo= '1') " );
	    // sbf.append("OR app.idNumber like '%1114232%' ");
		// sbf.append("OR app.customerName like '%12212%'" );
	 
	   Query qry = session.createQuery(sbf.toString());
	   	 List resultList = qry.list();
	   	 System.out.println("the list size()" +resultList.size());
	   for(Iterator it = resultList.iterator();it.hasNext();){
		   Object obj[]= (Object[])it.next();
		    objCommBean = new CommonDataBean();
		    objCommBean.setColumn1(String.valueOf(obj[0]));
		    System.out.println( objCommBean.getColumn1());
		    objCommBean.setColumn2((String)(obj[1]));
		    objCommBean.setColumn3((String)(obj[2]));
		    objCommBean.setColumn4(DateUtil.convertDateToDateString((Date)obj[3]));
		   searchList.add(objCommBean);
	  }  	 
	
	    System.out.println("list size is" +searchList.size());		
	      tx.commit();
	      
		}
		
		catch (Exception e)
		{
			 if(tx!=null)
				{
					tx.rollback();
				}
		   System.out.println("Error in CsrDAOImpl callRecordLog method : "+e.getMessage());
		   throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CsrDAOImpl callRecordLog  method :"+e);
		}
		 finally
		    {
			HibernetFactory.closeSession();
		    }
	}
             
    public CommonDataBean callNewRecord(String val)throws TPlusException { 
    	CommonDataBean objCommBean = new  CommonDataBean();
    	Transaction tx = null;
    	ArrayList reflist = new ArrayList();
	try	{
    
		  StringBuffer sbf = new StringBuffer();
		  Session	session = HibernetFactory.currentSession();
		  				tx  = session.beginTransaction();
	  		 /*
		  *  select REFERENCE_NO, PREV_REFERENCE_NO,ORIGINAL_REFERENCE_NO  from CALL_RECORD_LOG 
          select  PREV_REFERENCE_NO,ORIGINAL_REFERENCE_NO  from CALL_RECORD_LOG 
          where REFERENCE_NO = 'RF0608227656' and STATUS = 'O' 
          select REFERENCE_NO   from CALL_RECORD_LOG 
          where  PREV_REFERENCE_NO ='RF0608227656'  and ORIGINAL_REFERENCE_NO ='RF0608227656'
          insert into Call_record_log(new ref number .this reNo as prevNo, above orinal number as orinalRefno)
		  */
		 sbf.append("select prevReferenceNo,originalRefNo ");
		 sbf.append("from CallRecordLogDto where referenceNo = '"+val+"' and callStatus = 'O'");
			 
	   Query qry = session.createQuery(sbf.toString());
	   	 List resultList = qry.list();
	   	 System.out.println("the list size()" +resultList.size());
	   	
	   for(Iterator it = resultList.iterator();it.hasNext();){
		   Object obj[]= (Object[])it.next();
		   objCommBean.setColumn1((String)obj[0]);
		   objCommBean.setColumn2((String)obj[1]);
			 System.out.println("prevReferenceNo" +obj[0]);
			 System.out.println("originalRefNo" +obj[1]);
	  }  
	  
	   //to set the previous number
	 if(objCommBean.getColumn1()!=null){
	 String refNo = (String)session.createQuery("select referenceNo from CallRecordLogDto where prevReferenceNo = '"+ objCommBean.getColumn1()+"' and originalRefNo = '"+ objCommBean.getColumn2()+"' and callStatus = 'O'").list().get(0);	
	  objCommBean.setColumn3(refNo);  
	    System.out.println("refNo" +refNo);	
	  }  
	    tx.commit();
	      
		}
		
		catch (Exception e)
		{
			 if(tx!=null)
				{
					tx.rollback();
				}
		   System.out.println("Error in CsrDAOImpl callRecordLog method : "+e.getMessage());
		   throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CsrDAOImpl callRecordLog  method :"+e);
		}
		 finally
		    {
			HibernetFactory.closeSession();
		    }
		 return objCommBean;
	}
    
    
    /*
	 * This method is used for getting the Card tranx details from TranxLog table
	 */
	public void tranxHistory() throws TPlusException {
		 
		   Collection tranxHistoryList = new ArrayList();
		    Transaction tx = null;
		   CommonDataBean objCommBean = null;
	    try	{	
	    	     StringBuffer sbf = new StringBuffer();
	    	    Session	session = HibernetFactory.currentSession();
				tx  = session.beginTransaction();
	    	   sbf.append("select tl.dateTime,tl.merchantName,tl.originalAmount,tl.originalCurrency, ");
		       sbf.append("tl.amount,tl.currency,tl.referenceNo From TransactionLogDto tl where tl.cardNumber = 1724570000000022 " );
		       
	    	   /* if(objSearchDto.getTransDateFrom()==null && objSearchDto.getTransDateTo()==null){
		           sbf.append("and tl.dateTime between trunc(add_months(sysdate ,-1)) and sysdate   ");
		         }else{
		        	 sbf.append("and tl.dateTime  between to_date('"+objSearchDto.getTransDateFrom()+"','dd/mm/yyyy') and to_date('"+ objSearchDto.getTransDateTo()+"','dd/mm/yyyy')");
		           }*/
	    	   

		      // List resultList = session.createQuery(sbf.toString()).setLong("cardnumber", 1724570000000022).list();
		        
		         Query qy = session.createQuery(sbf.toString());
			     List resultList = qy.list();
				  for(Iterator it = resultList.iterator();it.hasNext();){
					   Object obj[]= (Object[])it.next();
					
					   objCommBean = new CommonDataBean();
					   objCommBean.setColumn1(DateUtil.convertDateToDateString((Date)obj[0]));
					   objCommBean.setColumn2((String)obj[1]);
					   objCommBean.setColumn3(String.valueOf(obj[2]));
					   objCommBean.setColumn4((String)obj[3]);
					   objCommBean.setColumn5(String.valueOf(obj[4]));
					   objCommBean.setColumn6((String)obj[5]);
					   objCommBean.setColumn7((String)obj[6]);
					   tranxHistoryList.add(objCommBean);
					  // cardActities = getSearchList(sbf.toString(),0);
				      }
			    System.out.println("tranxHistory list size"+tranxHistoryList.size()); 
			    tx.commit();
		     }catch (Exception e){
			
			System.out.println("Error in CsrDAOImpl in  tranxHistory method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CsrDAOImpl tranxHistory  method :"+e);
			
		}
	}       
	/*
	 * This is for getting thee Current Pin Count form cards Table
	 */

	public void  csrResetPinCount()  throws TPlusException {
	    
	    Collection currentPinCount = new ArrayList();
	    CommonDataBean objCommBean =new CommonDataBean();
	    Transaction tx = null;
	  try	
		  {
		     StringBuffer sbf = new StringBuffer();
			 Session session =HibernetFactory.currentSession();
			 tx =session.beginTransaction();
		 	
			  sbf.append("select cr.wrongPinCount,cr.cardStatusId From CardsDto cr ");
			  sbf.append("where cr.cardNumber =1724570000000022 ");
			  List resultList = session.createQuery(sbf.toString()).list();
			  
			  for(Iterator it = resultList.iterator();it.hasNext();){
				   Object obj[]= (Object[])it.next();
				
				    objCommBean = new CommonDataBean();
				    objCommBean.setColumn1(String.valueOf(obj[0]));
				    objCommBean.setColumn2(String.valueOf(obj[1]));
				  
				      System.out.println("pin Count is" +objCommBean.getColumn1());
				  	  System.out.println("status Id is" +objCommBean.getColumn2());
			  }	
			  currentPinCount.add(objCommBean);
			  /*System.out.println("class is" +resultList.get(0).getClass().getName());
		  	   //Object obj[]= (Object[])resultList.get(0);
		  	      Integer currentPin = (Integer)resultList.get(0);
			  // objCommBean.setColumn1(currentPin.toString());
		  	    objCommBean.setColumn1(String.valueOf(currentPin));
		  	   Long statusId = (Long)resultList.get(1);
			  // objCommBean.setColumn1(currentPin.toString());
		  	    objCommBean.setColumn2(String.valueOf(statusId));
		  	  */
			  
		
	     tx.commit();
		
		  }catch (Exception e){
			 if(tx!=null){
	    		 tx.rollback();
	    	 }
				System.out.println("Error while getting csrResetPinCount   in CsrDAOImpl"+e.getMessage());
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving csrResetPinCount in CsrDAOImpl"+e);
			}
	  finally{
			  
			  HibernetFactory.closeSession();
		     }
	
	} 
	
	/*
	 * cardSplitData calculations
	 */
	public void  cardSplitData()  throws TPlusException {
		
		CreditSplitForm objSplitForm = new CreditSplitForm();
		  LimitForm objform =null;
		  int i=0;
		 // CommonDataBean objCommonBean = null;
	       ArrayList arlSplitList = new ArrayList();
	       CardReplacementDto objDto  = new CardReplacementDto();
	       objDto.setCardNumber(Long.valueOf("1724570000000022").longValue());
	  try{
	    
		  CardReplacementManager objManager = new CardReplacementManager();
          Collection dataList = objManager.search(objDto);
          CustomerServiceDataBean objCustService =(CustomerServiceDataBean)((ArrayList)dataList).get(0);
	         Set cards =	objCustService.getCustomerCards();
	         System.out.println("cards size"+cards.size());
	      for(Iterator it =cards.iterator();it.hasNext();){
	    	 
	    	  System.out.println("1"+i);
		    CardsDto objCardsDto = new CardsDto();
		    objCardsDto = (CardsDto)it.next();
	      // 	objCommonBean = new CommonDataBean();
	       	if(i==0)objform =objSplitForm.getLimitForm0();
	       	else if(i==1)objform =objSplitForm.getLimitForm1();
	       	else if(i==2)objform =objSplitForm.getLimitForm2();
	      	else if(i==3)objform =objSplitForm.getLimitForm2();
	      	else if(i==4)objform =objSplitForm.getLimitForm2();
	      
	       	objform.setCardNumber(String.valueOf(objCardsDto.getCardNumber()));
	    
	       	if(objCardsDto.getCardHolderType()==CommonCodes.PRIMARYCARD_HOLDER){
	    		objform.setCardHolderType("PRIMARYCARDHOLDER");
	    	}else{
	    		objform.setCardHolderType("SUPPLEMENTARYCARDHOLDER");	
	    	}
	    	objform.setCustomerName(objCustService.getCustomerName());
	    	  
	        	double currentLimit = (((Float.valueOf(objCardsDto.getCreditLimit()).floatValue()))*(objCardsDto.getCreditLimitPercent()) )/100;
	        
	        	objform.setCurrentLimit(String.valueOf(currentLimit));
	        	objform.setCurrentRatio(String.valueOf(objCardsDto.getCreditLimitPercent()));
	    	i++;
	    	  //  arlSplitList.add(objCommonBean);
	      }
	  
	 System.out.println("the card number"+objSplitForm.getLimitForm0().getCardNumber());
	 System.out.println("the card number"+objSplitForm.getLimitForm1().getCardNumber());     
	 System.out.println("the card number"+objSplitForm.getLimitForm2().getCardNumber());     
	   
	 /*    for(int i=0;i<arlSplitList.size();i++){
		   CommonDataBean objCommonBean1 = (CommonDataBean)arlSplitList.get(i);
		   System.out.println("Record No :" +i);
		   System.out.println("Carnumber :" +objCommonBean1.getColumn1());
		   System.out.println("CardHolderType :" +objCommonBean1.getColumn2());
		   System.out.println("CustomerName :" +objCommonBean1.getColumn3());
		   System.out.println("CurrentLimit :" +objCommonBean1.getColumn4());
		   System.out.println("CurrentRation :" +objCommonBean1.getColumn5());
	   }*/
	
	   
	     }catch (Exception e){
	          System.out.println("Error converting to form bean in CardReplacementDispatchAction: " + e);
	          throw new TPlusException("Could not populate the form bean in CardReplacementDispatchAction: " + e);
	       }
	     
	}

	/*
	 * cardSplitData calculations
	 */
	public void  cardSplitDataWithForm()  throws TPlusException {
	 
		   Class[] arrClass = new Class[1];
		  arrClass[0] = new String("").getClass();
	      arrClass[0] = new LimitForm().getClass();
		  CreditSplitForm objSplitForm = new CreditSplitForm();
		   Method method =null;
			Object[] temp = new Object[3] ;
			 LimitForm objform =null;
			   
	  try{
		 
		/*    Class objSplitFormClass = objSplitForm.getClass();
		  	method = objSplitFormClass.getMethod("setLimitForm0",arrClass);
		    System.out.println("then method values is" +  method.getName());
	       // method.invoke(objSplitForm,temp);*/
		 for(int i=0;i<3;i++){
			 if(i==0)objform =objSplitForm.getLimitForm0();
			 objform.setCardNumber("0");
			 if(i==1)objform =objSplitForm.getLimitForm1();
			 objform.setCardNumber("1");
			 if(i==2)objform =objSplitForm.getLimitForm2();
			 objform.setCardNumber("2");
		 }
		 
		   System.out.println("then method values is" +objSplitForm.getLimitForm0().getCardNumber());
		   System.out.println("then method values is" +objSplitForm.getLimitForm1().getCardNumber());
		   System.out.println("then method values is" +objSplitForm.getLimitForm2().getCardNumber());
	   
	     }catch (Exception e){
	          System.out.println("Error converting to form bean in CardReplacementDispatchAction: " + e);
	          throw new TPlusException("Could not populate the form bean in CardReplacementDispatchAction: " + e);
	       }
	     
	}

	
	public void csrCrdeitSplit() throws TPlusException{
		
		     Transaction tx = null;
		     LimitForm   objLimitform = null;
		     CreditSplitDto objSplit = new CreditSplitDto();
			try
			{
				StringBuffer sbf = new StringBuffer();
				Session session =HibernetFactory.currentSession();
				tx =session.beginTransaction();
				sbf.append("select cr.cardNumber,cr.cardStatus,app.customerName,cr.creditLimitPercent,ca.creditLimit,cr.cardHolderType,cr.cashLimitPercent,ca.cashLimit ");
		     	sbf.append("from ApplicationProcessDto app left join fetch app.customerCards cr left join fetch app.customerAccount ca ");
				sbf.append("where ");
				sbf.append("app.customerId = (select crd.customerId  from CardsDto crd where  crd.cardNumber = 1724570000000022)");
						
				Query cardsQy = session.createQuery(sbf.toString());
				List resList = cardsQy.list();
				System.out.println("All CardsList list size"+resList.size());
					     
			    for(Iterator it = resList.iterator();it.hasNext();){
				    Object obj[]= (Object[])it.next();
				    System.out.println("1");
		   if(String.valueOf(obj[0]).equals("1724570000000022")||String.valueOf(obj[5]).equals(String.valueOf(CommonCodes.SUPPLEMENTARYCARD_HOLDER))){ 
			   System.out.println("2");
			   objLimitform = new LimitForm();
		       objLimitform.setCardNumber(String.valueOf(obj[0]));
		       System.out.println("3");
		       if(String.valueOf(obj[0]).equals("1724570000000022")){
		       		 objLimitform.setCardHolderType("PRIMARY");
		       		                           /*         cashlimitpercent from cards               *            cashlimit from account  */
		       		double currentCashLimit = (((Float.valueOf(String.valueOf(obj[6])).floatValue()))*((Float.valueOf(String.valueOf(obj[7])).floatValue())))/100;
		       	    System.out.println("calculated CashLimit" +currentCashLimit);
		       	    objLimitform.setCurrentCashLimit(String.valueOf(currentCashLimit));
		       	   objLimitform.setCurrentCashLimitRatio(String.valueOf(obj[6]));
		       }else{
		    		objLimitform.setCardHolderType("SUPPLEMENTARY");	
		    	}
		       System.out.println("4");
		        objLimitform.setCardStatus((String)obj[1]);
	       	    objLimitform.setCustomerName((String)obj[2]);
	       	    
	       	 System.out.println("5");
	       	 System.out.println("current credit percent from cards:"+((Float.valueOf(String.valueOf(obj[3])).floatValue())) );
	       	 System.out.println("actual creditlimit from account"+((Float.valueOf(String.valueOf(obj[4])).floatValue())));
	    	     
	       	//calculating currentLimit value
	        	double currentCreditLimit = (((Float.valueOf(String.valueOf(obj[3])).floatValue()))*((Float.valueOf(String.valueOf(obj[4])).floatValue())))/100;
	        	 System.out.println("5 calculated currentCreditLimit val= :" +currentCreditLimit);
	        	objLimitform.setCurrentLimit(String.valueOf(currentCreditLimit));
	        	 System.out.println("6");
	        	objLimitform.setCurrentRatio(String.valueOf(obj[3]));
	        	 System.out.println("7");
	        	objSplit.getLimitFormsList().add(objLimitform);
	        	 System.out.println("8");
	        	objSplit.setCreditLimit(String.valueOf(obj[4]));
	        	 System.out.println("9");
			   }
		 
			}	
			    System.out.println("the CreditLimit"+objSplit.getCreditLimit());
			    System.out.println("the currentLimit"+objLimitform.getCurrentLimit());
		           tx.commit();
			 
		}	   
		catch (Exception e)
		{
			System.out.println("Exception" +e);

		throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while received method in CardDeliverDAOImpl"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}
		
}
	
	public int checkCardHolderType(Object commonObj)throws TPlusException{
		
		int res=0;
		try
			{ 
			       StringBuffer sbf = new StringBuffer();
			       Session session =HibernetFactory.currentSession();
			 	 
			   if(commonObj instanceof CsrSearchDto){
				   CsrSearchDto objDto =(CsrSearchDto)commonObj;
				   if(objDto.getCallRefNo()!=null && !objDto.getCallRefNo().equals("")){
					   sbf.append("select count(*) from CallRecordLogDto crl where crl.referenceNo= '"+objDto.getCallRefNo()+"' ");   
				   }else{
				     sbf.append("select count(*) from CardsDto cr where cr.cardNumber= "+objDto.getQuickCardNo()+" ");
				   }
			   }
			   if(commonObj instanceof CreditSplitDto){
				   CreditSplitDto objDto =(CreditSplitDto)commonObj; 
				   sbf.append("select cr.cardHolderType from CardsDto cr where cr.cardNumber= "+objDto.getCardNumber()+" ");   
			   }
			   Query qry = session.createQuery(sbf.toString());
			   List list = qry.list();
			   res = ((Integer)list.get(0)).intValue();
		   System.out.println("After CardReplacmentDAOImpl checkExistrecord()" +res);
			   }

			catch (Exception e)
			{
				System.out.println("while checking Existing Record in CsrDAOImpl :" +e.getMessage());
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error:  while checking Existing Record in CsrDAOImpl:"+e);
			}

			finally
			{
				HibernetFactory.closeSession();
		 	}

		return 	res;
	}
	
	public void renewalProcess() throws TPlusException{
		
		  boolean renewal =false;
		  Transaction tx =null;
		  int count=0;
		  CardActivityDto objCardActivity =null;
		  CardsRenewalHistoryDto objCardRenHist=null;
		   StringBuffer sbf =null;
		 try{
			  
		      Session session =HibernetFactory.currentSession();
	          tx= session.beginTransaction();
	          sbf = new StringBuffer();
	        //  List custIdls = session.createQuery("Select customerId from CardsDto where  cardNumber =1724570000000022 ").list();
        	// String custId = (String)custIdls.get(0);
        	  
	       // Query namedQuery = session.getNamedQuery("testCallableQuery");
	        PreparedStatement st = session.connection().prepareStatement("{call	CARDS_TO_RENEWAL}");
	        boolean success = st.execute();
	          System.out.println("value to prepare statement"+success);
	        //getting the cards to renewal from the cards_renewal table 
	      /*    sbf.append("select crd.cardNumber,crd.cardExpireDate from CardsRenewalDto crd left join fetch crd.cardsDto cr ");
	          sbf.append("where crd.cardNumber = cr.cardNumber and cr.status ='A' ");
	  	      Query qry = session.createQuery(sbf.toString());
			   List resList = qry.list();
			 System.out.println("list from cards_renawal size"+resList.size());
			 
         	for(Iterator it = resList.iterator();it.hasNext();){
					Object obj[]= (Object[])it.next();
					 System.out.println("1"+Long.valueOf(String.valueOf(obj[0])).longValue());
			
			//generating the newExpire date using old expire date
			// SQLQuery newExpDateList =session.createSQLQuery("SELECT LTRIM(TO_CHAR(TO_NUMBER('0607')+100*2,'0000'))AS EXPDATE FROM DUAL").addScalar("EXPDATE", Hibernate.STRING);
			  String expdate = (String)((SQLQuery)session.createSQLQuery("SELECT LTRIM(TO_CHAR(TO_NUMBER('"+(String)obj[1]+"')+100*2,'0000'))AS EXPDATE FROM DUAL").addScalar("EXPDATE", Hibernate.STRING)).list().get(0);
		      System.out.println("new expire date"+ expdate);
			 
		      //updating cards table to set new exoire date
		        String cardsql = "UPDATE CardsDto SET cardExpDate =:cardexpdate WHERE cardnumber=:cardnumber";
				 count         = session.createQuery(cardsql)
					        		  .setString("cardexpdate",expdate)
									  .setLong("cardnumber",Long.parseLong(String.valueOf(obj[0])))
									  .executeUpdate();
			
				 if(count>0){
			   	//for cards_Renewal_History table insertion
			    	objCardRenHist = new CardsRenewalHistoryDto();
			    	objCardRenHist.setCardNumber(Long.parseLong(String.valueOf(obj[0])));
			    	objCardRenHist.setCardExpireDate(expdate);
			    	objCardRenHist.setUpdatedDate(new Date());
			    	objCardRenHist.setUserId("satyam");
			    	System.out.println("1");
			    	session.save(objCardRenHist);
			  
               // 	Inserting Data into CardActivity Table 
		 			objCardActivity = new CardActivityDto();
		 		   
		 			objCardActivity.setDateTime(new Date());
		 		    objCardActivity.setCardNumber(Long.parseLong(String.valueOf(obj[0])));
		 			objCardActivity.setActivity("Card Renewled");
		 			objCardActivity.setStationIp("127.0.0");
		 			objCardActivity.setUserId("satyam");
		 			objCardActivity.setReason("");
		 			objCardActivity.setLastUpdatedBy("satyam");
		 			objCardActivity.setUpdatedDate(new Date());
		 			
		 			session.save(objCardActivity); 		 	
		 			
			    }
         	}*/
			   
	 	   	 tx.commit();
	 	     		   
		       }catch (Exception e){
		    	  if(tx!=null){
		    		 tx.rollback();
		    	 }
				  System.out.println("Error  in CardsRenewalDAOImpl renewal Process Method" +e.getMessage());
				 throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error:  in CardsRenewalDAOImpl renewal Process Method"+e);	
			}
		    finally
			{
		    	
				HibernetFactory.closeSession();
		 	}
		
	}
	

	
	//	Base DAo methods newly added by satyam reddy
	  
	
	 public Map cardTypeListData(String issuerId)  throws TPlusException {

					Map cardTypeList = new TreeMap();
				    Transaction tx =null;
					try
					  {
					   Session	session = HibernetFactory.currentSession();
					                tx  = session.beginTransaction();
					   Query qry = session.createQuery("select cardTypeId,cardType From CardTypeDto where issuerId ='"+issuerId+"' ");
					   List listCardTypes = qry.list();
					   for(Iterator it = listCardTypes.iterator();it.hasNext();){
						 Object objCardTypes[] = (Object[])it.next(); 
						 cardTypeList.put((String)objCardTypes[0],(String)objCardTypes[1]);
					   }
					    tx.commit();
					  }catch(Exception e){
						  if(tx!=null){
							  tx.rollback();
						  }
							System.out.println("while retrieving cardTypeListData in BaseDAOIMpl"+e.getMessage());
							throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving cardTypeListData in BaseDAOIMpl"+e);
						}
						 finally{

							  HibernetFactory.closeSession();
						  }
				  return cardTypeList;
				}

	   public Map cardProductListData(String issuerId)  throws TPlusException {
					
				  Map cardProductList = new TreeMap();
			      Transaction tx =null;
				  
				try
				  {

				   Session	session = HibernetFactory.currentSession();
				   				tx  = session.beginTransaction();
				   Query qry = session.createQuery("select cardProductId,cardProductName From CardProductDto where issuerId ='"+issuerId+"' ");
				   List listCardProducts = qry.list();
				   for(Iterator it = listCardProducts.iterator();it.hasNext();){
					 Object objTypes[] = (Object[])it.next(); 
					 cardProductList.put((String)objTypes[0],(String)objTypes[1]);
				   }
				    tx.commit();

				  }catch(Exception e){
					  if(tx!=null){
						  tx.rollback();
					  }
						System.out.println("while retrieving cardProductListData in BaseDAOIMpl"+e.getMessage());
						throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving cardProductListData in BaseDAOIMpl"+e);
					}
					 finally{

						  HibernetFactory.closeSession();
					  }
			 return cardProductList;
			}

   public void disputeClaimTest() throws TPlusException{
			  Transaction tx = null;
			  CommonDataBean objCommBean =null;
			try
			{   
				StringBuffer sbf = new StringBuffer();
				Session session =HibernetFactory.currentSession();
				tx =session.beginTransaction();
				System.out.println("1");
				 sbf.append("select  cr.cardNumber,app.customerName,app.dob, app.idNumber, app.expDate, ");
				 sbf.append("st.merchantName,to_char(st.tranxDate,'DD-MM-YYYY HH24:MI:SS'),st.refNumber,st.originalAmount,st.originalCurrName,st.amountCurr, st.currName, ");
			     sbf.append("add.address1 ,add.address2,add.city,add.state,add.country,add.postalCode,add.phone,add.fax ");
			   	 sbf.append("From ApplicationProcessDto app, CardsDto cr, CustomerAddressDto add,OriginalRequestDto st ");
			   	 sbf.append("where ");
			   	 sbf.append("cr.customerId = app.customerId and ");
			     sbf.append("app.customerId = add.appFormProcssDto.customerId and ");
			    sbf.append("app.billingTo = add.addressType and ");
				 sbf.append("cr.cardNumber = 1724570000000022 and  ");
				 sbf.append("st.settlementId = 800 ");
				// sbf.append("to_date(to_char(st.tranxDate,'DD/MM/YYYY'),'DD/MM/YYYY') = TO_DATE('26-05-2006', 'DD/MM/YYYY HH24:MI')) ");
				
				 //sbf.append("select cardsdto1_.CARDNUMBER as col_0_0_, applicatio0_.CUSTOMER_NAME as col_1_0_, applicatio0_.DOB as col_2_0_, applicatio0_.ID_NUMBER as col_3_0_, applicatio0_.ID_EXPIRE as col_4_0_, originalre3_.MERCHANTNAME as col_5_0_, to_char(originalre3_.TRANXDATE, 'DD-MM-YYYY HH24:MI:SS') as col_6_0_, originalre3_.REFNO as col_7_0_, originalre3_.AMOUNT_CR   as col_8_0_, originalre3_.CURRCODE as col_9_0_, originalre3_.ORIGINALAMOUNT as col_10_0_, originalre3_.ORIGINALCURRCODE as col_11_0_, customerad2_.ADDRESS1 as col_12_0_, customerad2_.ADDRESS2 as col_13_0_, customerad2_.CITY as col_14_0_, customerad2_.STATE as col_15_0_, customerad2_.COUNTRY as col_16_0_, customerad2_.POSTAL_CODE as col_17_0_, customerad2_.TEL as col_18_0_, customerad2_.FAX as col_19_0_ from CUSTOMER applicatio0_, CARDS cardsdto1_, CUSTOMER_ADDRESS customerad2_, SETTLEMENTS originalre3_ where cardsdto1_.CUSTOMER_ID=applicatio0_.CUSTOMER_ID and applicatio0_.CUSTOMER_ID=customerad2_.customer_id and applicatio0_.BILLING_TO=customerad2_.ADDR_TYPE and cardsdto1_.CARDNUMBER=1724570000000022 and originalre3_.CARDNUMBER=1724570000000022 and to_date(to_char(originalre3_.TRANXDATE, 'DD/MM/YYYY HH24:MI:SS'), 'DD/MM/YYYY HH24:MI:SS')=to_date('26-05-2006 02:37:25', 'DD/MM/YYYY HH24:MI:SS')");

				 Query qry = session.createQuery(sbf.toString());
				 System.out.println("2");
			   	 List resultList = qry.list();
			   	System.out.println("3");
			     for(Iterator it = resultList.iterator();it.hasNext();){
				    Object obj[]= (Object[])it.next();
				    objCommBean = new CommonDataBean();
				  
				    System.out.println("#######CustomerDetails####:");
				    objCommBean.setColumn1(String.valueOf(obj[0]));
				    objCommBean.setColumn2((String)obj[1]);
				    objCommBean.setColumn3(DateUtil.convertDateToDateString((Date)obj[2]));
				    objCommBean.setColumn4((String)obj[3]);
				    objCommBean.setColumn5(DateUtil.convertDateToDateString((Date)obj[4]));
				   
				    System.out.println("Card Number :  "+objCommBean.getColumn1());			   
				    System.out.println("Name :  "+objCommBean.getColumn2());
				    System.out.println("Date Of Birtth:  "+objCommBean.getColumn3());
				     System.out.println("Nric/Pass No :  "+objCommBean.getColumn4());
				    System.out.println("Nric Expire :  "+objCommBean.getColumn5());
				    
				    
				   System.out.println("#######TranxDetails#### :");
				  
				   objCommBean.setColumn6((String)obj[5]);
				   objCommBean.setColumn7((String)obj[6]);
				   objCommBean.setColumn8((String)obj[7]);
				   objCommBean.setColumn9(String.valueOf(obj[8]));
				   objCommBean.setColumn10((String)obj[9]);
				   objCommBean.setColumn11(String.valueOf(obj[10]));
				   objCommBean.setColumn12((String)obj[11]);
				  
				    
				    System.out.println("merchantName:  "+objCommBean.getColumn6());
				    System.out.println("tranxdate :  "+objCommBean.getColumn7());
				    System.out.println("refno :  "+objCommBean.getColumn8());
				    System.out.println("transamount:  "+objCommBean.getColumn9());
				    System.out.println("transcurrency:  "+objCommBean.getColumn10());
				    System.out.println("settleamt:  "+objCommBean.getColumn11());
				    System.out.println("settlecurrency:  "+objCommBean.getColumn12());
				  
				    System.out.println("#######Contact Address#### :"); 
				   
				   objCommBean.setColumn13((String)obj[12]);
				   objCommBean.setColumn14((String)obj[13]);
				   objCommBean.setColumn15((String)obj[14]);
				   objCommBean.setColumn16((String)obj[15]);
				   objCommBean.setColumn17((String)obj[16]);
				   objCommBean.setColumn18(String.valueOf(obj[17]));
				   objCommBean.setColumn19((String)obj[18]);
				   objCommBean.setColumn20((String)obj[19]);
				   
				   
				    System.out.println("Address1 :  "+objCommBean.getColumn13());
				    System.out.println("Address12:  "+objCommBean.getColumn14());
				    System.out.println("City :  "+objCommBean.getColumn15());
				    System.out.println("State :  "+objCommBean.getColumn16());
				    System.out.println("Country :  "+objCommBean.getColumn17());
				    System.out.println("Psotal Code:  "+objCommBean.getColumn18());
				    System.out.println("Phone:  "+objCommBean.getColumn19());
				    System.out.println("fax:  "+objCommBean.getColumn20());
				    
				    System.out.println("4");
				 //  historyCollection.add(objCommBean);

			      }
			        
			  
			}
			catch (Exception e)
			{
				System.out.println("Exception" +e);

			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while received method in CardDeliverDAOImpl"+e.getMessage());
			}
			finally
			{
				HibernetFactory.closeSession();
		 	}
		}
		
	   
	   public void disputeClaimListTest() throws TPlusException{
			  Transaction tx = null;
			  CommonDataBean objCommBean =null;
			try
			{   
				StringBuffer sbf = new StringBuffer();
				Session session =HibernetFactory.currentSession();
				tx =session.beginTransaction();
				System.out.println("1");
				 sbf.append("select st.settlementId,st.cardNumber,st.merchantName,to_char(st.tranxDate,'DD-MM-YYYY HH24:MI'), ");
				 sbf.append("st.originalAmount,st.originalCurrName,st.amountCurr, st.currName, st.refNumber ");
				 sbf.append("from OriginalRequestDto st where ");
			     sbf.append("st.cardNumber = 1724570000000022 and ");
				 sbf.append("to_date(to_char(st.tranxDate,'DD/MM/YYYY'),'DD/MM/YYYY') = TO_DATE('26-05-2006', 'DD/MM/YYYY HH24:MI')) ");
				 sbf.append(" OR st.amountCurr =5000 ");
				 //sbf.append("select cardsdto1_.CARDNUMBER as col_0_0_, applicatio0_.CUSTOMER_NAME as col_1_0_, applicatio0_.DOB as col_2_0_, applicatio0_.ID_NUMBER as col_3_0_, applicatio0_.ID_EXPIRE as col_4_0_, originalre3_.MERCHANTNAME as col_5_0_, to_char(originalre3_.TRANXDATE, 'DD-MM-YYYY HH24:MI:SS') as col_6_0_, originalre3_.REFNO as col_7_0_, originalre3_.AMOUNT_CR   as col_8_0_, originalre3_.CURRCODE as col_9_0_, originalre3_.ORIGINALAMOUNT as col_10_0_, originalre3_.ORIGINALCURRCODE as col_11_0_, customerad2_.ADDRESS1 as col_12_0_, customerad2_.ADDRESS2 as col_13_0_, customerad2_.CITY as col_14_0_, customerad2_.STATE as col_15_0_, customerad2_.COUNTRY as col_16_0_, customerad2_.POSTAL_CODE as col_17_0_, customerad2_.TEL as col_18_0_, customerad2_.FAX as col_19_0_ from CUSTOMER applicatio0_, CARDS cardsdto1_, CUSTOMER_ADDRESS customerad2_, SETTLEMENTS originalre3_ where cardsdto1_.CUSTOMER_ID=applicatio0_.CUSTOMER_ID and applicatio0_.CUSTOMER_ID=customerad2_.customer_id and applicatio0_.BILLING_TO=customerad2_.ADDR_TYPE and cardsdto1_.CARDNUMBER=1724570000000022 and originalre3_.CARDNUMBER=1724570000000022 and to_date(to_char(originalre3_.TRANXDATE, 'DD/MM/YYYY HH24:MI:SS'), 'DD/MM/YYYY HH24:MI:SS')=to_date('26-05-2006 02:37:25', 'DD/MM/YYYY HH24:MI:SS')");

				 Query qry = session.createQuery(sbf.toString());
			 	 List resultList = qry.list();
			     for(Iterator it = resultList.iterator();it.hasNext();){
				    Object obj[]= (Object[])it.next();
				    objCommBean = new CommonDataBean();
				    
				    
				   System.out.println("#######TranxDetails#### :");
				   objCommBean.setColumn1(String.valueOf(obj[0]));
				   objCommBean.setColumn2((String)obj[1]);
				   objCommBean.setColumn3((String)obj[2]);
				   objCommBean.setColumn4((String)obj[3]);
				   objCommBean.setColumn5(String.valueOf(obj[4]));
				   objCommBean.setColumn6((String)obj[5]);
				   objCommBean.setColumn7(String.valueOf(obj[6]));
				   objCommBean.setColumn8((String)obj[7]);
				   objCommBean.setColumn9((String)obj[8]);
				   
				    System.out.println("settlemetId"+objCommBean.getColumn1());
				    System.out.println("cardnumber :  "+objCommBean.getColumn2());
				    System.out.println("merchantName:  "+objCommBean.getColumn3());
				    System.out.println("tranxdate :  "+objCommBean.getColumn4());
				    System.out.println("transxamount:  "+objCommBean.getColumn5());
				    System.out.println("transxcurrency:  "+objCommBean.getColumn6());
				    System.out.println("settleamt:  "+objCommBean.getColumn7());
				    System.out.println("settlecurrency:  "+objCommBean.getColumn8());
				    System.out.println("refno :  "+objCommBean.getColumn9());
				  
				 
				 //  historyCollection.add(objCommBean);

			      }
			        
			  
			}
			catch (Exception e)
			{
				System.out.println("Exception" +e);

			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while received method in CardDeliverDAOImpl"+e.getMessage());
			}
			finally
			{
				HibernetFactory.closeSession();
		 	}
		}
	   
	   
//	 to get the disputeClaimFormValidation
	    public void disputeClaimFormValidation() throws TPlusException {
			
	   	 ArrayList documentsList = new ArrayList();
	   	 Transaction tx =null;
	   	try	
	   	  {
	   	   
	   	   Session	session = HibernetFactory.currentSession();
	   	   				 tx = session.beginTransaction();
	   	   Query qry = session.createQuery("select dcm.transactionValidationPeriod from DisputeConfigMasterDto dcm where (sysdate - to_date('05-05-2006 14:37:25','dd/mm/yyyy'))<dcm.transactionValidationPeriod ");
	   	    List listDocs = qry.list();
	   	    int res = ((Integer)listDocs.get(0)).intValue();
	   	  System.out.println("the list size" +res);
	   	    tx.commit();
	   	   
	   	  }catch (Exception e){
	   		  if(tx!=null)
	   			{
	   				tx.rollback();
	   			}
	   		  System.out.println("while retrieving disputeDocsListData in DisputeClaimFormDAOImpl"+e.getMessage());
	   		 throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving disputeDocsListData in DisputeClaimFormDAOImpl"+e);
	   		}
	   	  finally
	   		{
	   			HibernetFactory.closeSession();
	   	 	}
	   // return documentsList;
	   }    

	    //		 to get the disputeWorkItem
	    public void disputeWorkItem() throws TPlusException {
			
	   	 ArrayList documentsList = new ArrayList();
	   	 Transaction tx =null;
	   	 CommonDataBean objCommBean =null;
	   	try	
	   	  {
	   	   StringBuffer sbf = new StringBuffer();
	   	   Session	session = HibernetFactory.currentSession();
	   	   				 tx = session.beginTransaction();
	   	   		 sbf.append("select  dcf.cardNumber,dcf.claimNumber,dct.claimType ,drd.description,dcf.customerName,to_char(dcf.claimDate,'DD-MM-YYYY'), dsd.description ");
	   	 		 sbf.append("from DisputeClaimFormDto dcf,DisputeClaimTypesDto dct,DisputeReasonDto drd,DisputeStatusDto dsd ");
	   	 		 sbf.append("where ");
	   	 		 sbf.append("dcf.claimTypeId = dct.claimTypeId and ");
	   	 	     sbf.append("dcf.claimReasonCode = drd.code and ");
	   	 		 sbf.append("dcf.status = dsd.id and ");
	   	 		 sbf.append("dcf.claimNumber = 'DC060821462' ");
	   	 		
	   	 	 Query qry = session.createQuery(sbf.toString());
		 	 List resultList = qry.list();
		 	 System.out.println("the list size is"+resultList.size());
		 	 int i=0;
		     for(Iterator it = resultList.iterator();it.hasNext();){
			    Object obj[]= (Object[])it.next();
			    objCommBean = new CommonDataBean();
			    System.out.println("the cand number:" +obj[0]);
			    System.out.println("the claim number:" +obj[1]);
			    System.out.println("the calim type :" +obj[2]);
			    System.out.println("the reason type :" +obj[3]);
			    System.out.println("the customer name:" +obj[4]);
			    System.out.println("the claim date :" +obj[5]);
			    System.out.println("the calim status :" +obj[6]);
			    
		     }
	   	    tx.commit();
	   	   
	   	  }catch (Exception e){
	   		  if(tx!=null)
	   			{
	   				tx.rollback();
	   			}
	   		  System.out.println("while retrieving disputeDocsListData in DisputeClaimFormDAOImpl"+e.getMessage());
	   		 throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving disputeDocsListData in DisputeClaimFormDAOImpl"+e);
	   		}
	   	  finally
	   		{
	   			HibernetFactory.closeSession();
	   	 	}
	   // return documentsList;
	   	  
	 /*  	FUNCTION CheckCycle (cycledone in varchar2)   RETURN varchar2 IS

	   	tvalue    PARAMETERS.VALUE%type;
	   	dofmonth  CYCLES.DAYOFMONTH%type;
	   	cycleno   CYCLES.CYCLENO%type;
	   	issuerid   CYCLES.ISSUER_ID%type;
	   	tmpvalue  varchar2(20);

	   	BEGIN
	   	SELECT  VALUE INTO tvalue FROM PARAMETERS WHERE NAME = cycledone;

	   	--IF tvalue  THEN
	   	SELECT DAYOFMONTH, CYCLENO,ISSUER_ID INTO dofmonth, cycleno,issuerid FROM CYCLES
	   	WHERE CYCLENO = MOD(NVL(TO_NUMBER(tvalue),0), 9)+1;
	   	dbms_output.put_line('dofmonth= '|| dofmonth);
	   	--END IF;

	   	   tmpvalue := dofmonth||':'||cycleno ||':'||issuerid;
	   	   RETURN tmpvalue;

	   	   EXCEPTION
	   	     WHEN NO_DATA_FOUND THEN
	   	       dbms_output.put_line('No data Found...');
	   		   dofmonth := 0;
	   		   RETURN dofmonth;
	   	     WHEN OTHERS THEN
	   	       dbms_output.put_line('Exception Occured...');

	   	END CheckCycle;*/

	   }     
	
	    //to get the CardBilling Cycle
    public CardBillingDto checkBllingCycle(CardBillingDto objCardBillDto) throws TPlusException {
			
	  	 Transaction tx =null;
	   	 CommonDataBean objCommBean =null;
	   	 StringBuffer sbf = new StringBuffer();
	   	try	
	   	  {
	   	   Session	session = HibernetFactory.currentSession();
	   	   				 tx = session.beginTransaction();
	   	 //to previous Cycle  				 
	   	   String prevCycle = (String)session.createQuery("select pr.value from ParametersDto pr where pr.name = '"+objCardBillDto.getBillingCycleDone()+"' ").list().get(0);
	       objCardBillDto.setPrevCycleNo(Integer.valueOf(prevCycle).intValue());
	       int prevCycleDate = ((Integer)(session.createQuery("select cs.dayOfMonth from CycleDto cs where cs.cycleNo = "+prevCycle+"  ").iterate()).next()).intValue();
	      objCardBillDto.setPrevCycleDate(prevCycleDate);
	       System.out.println("previous Cycle Done" +prevCycle);
	       System.out.println("previous Cycle Done Date" +prevCycleDate);
	   	   
	   	   //to calculate next cycle 
	       int numberOfCyles = ((Integer)session.createQuery("select count(*) from CycleDto cs where cs.issuerId= '"+objCardBillDto.getIssuerId()+"' "). list().get(0)).intValue();;
	       System.out.println("number of cycles for this issuer" +numberOfCyles);
	       
	         sbf.append("select  cs.cycleNo,cs.dayOfMonth from CycleDto cs ");
 	 		 sbf.append("where ");
 	 		 sbf.append("cs.cycleNo = MOD(NVL(TO_NUMBER("+prevCycle+"),0), "+numberOfCyles+" )+1 ");
 	 		 Query qry = session.createQuery(sbf.toString());
		 	 List resultList = qry.list();
		 	 System.out.println("the next Cycle list size is"+resultList.size());
		 	
		     for(Iterator it = resultList.iterator();it.hasNext();){
			    Object obj[]= (Object[])it.next();
			   
			    objCardBillDto.setNextCycleNo(Integer.parseInt(String.valueOf(obj[0])));
			    objCardBillDto.setNextCycleDate(Integer.parseInt(String.valueOf(obj[1])));
			   
			    /*objCommBean = new CommonDataBean();
			    objCommBean.setColumn1(String.valueOf(obj[0]));
			    objCommBean.setColumn2(String.valueOf(obj[1]));*/
			    
			  System.out.println("the Next Cycle Number:" +objCardBillDto.getNextCycleNo());
			  System.out.println(" Next Cycle  Date :" +objCardBillDto.getNextCycleDate());
		 }
 	 		
		    //to show accounts to process for this cycle 
		     int numberOfAccounts = ((Integer)session.createQuery("select count(*) from CustomerAccountDto ca where ca.cycleNo= "+objCardBillDto.getNextCycleNo()+" "). list().get(0)).intValue();
		     objCardBillDto.setNumberOfAccounts(numberOfAccounts);
 	 		 System.out.println("Number Of Accounts to Process For This Cycle."+numberOfAccounts);	
	   	  
 	 		 tx.commit();
	   	   
 	 	//to checking Day of Month
 	 		int dayOfMonth = objCardBillDto.getNextCycleDate();
 	 	    if( dayOfMonth==0)   System.out.println("Billing error");
 	 	 	GregorianCalendar c = new GregorianCalendar();
 	 	  	System.out.println("today date" + c.get(Calendar.DAY_OF_MONTH));
  	 	   
 	 	 	if (c.get(Calendar.DAY_OF_MONTH) != dayOfMonth){
 	 	    	System.out.println("today is not the Card Billing Date");
 	 		    }

 	 	 	/*to get the value in single statement
 	 	 	 *  double amount_cr = ((Double)(session.createQuery("select sum(sdto.amountCredited) from SettlementDto sdto where sdto.cardNumber='"+cardnumber+"' and sdto.dateTime>=to_date('06/01/2006','MM/DD/YYYY') and sdto.dateTime <=to_date('"+ dueDate+"','YYYY/MM/DD') order by sdto.tranxdate").iterate()).next()).doubleValue();
                String expdate = (String)((SQLQuery)session.createSQLQuery("SELECT LTRIM(TO_CHAR(TO_NUMBER('"+(String)obj[1]+"')+100*2,'0000'))AS EXPDATE FROM DUAL").addScalar("EXPDATE", Hibernate.STRING)).list().get(0);
 	 	 	 */
 	 		 
	   	  }catch (Exception e){
	   		  if(tx!=null)
	   			{
	   				tx.rollback();
	   			}
	   		  System.out.println("while retrieving disputeDocsListData in DisputeClaimFormDAOImpl"+e.getMessage());
	   		 throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving disputeDocsListData in DisputeClaimFormDAOImpl"+e);
	   		}
	   	  finally
	   		{
	   			HibernetFactory.closeSession();
	   	 	}
	  return objCardBillDto;
	   }     
	    
	    
	public static void main(String args[]) throws Exception{
		CacisDataTestProgram objTest = new CacisDataTestProgram();
		CsrForm objDto = new CsrForm();
		CreditSplitDto objSplitDto = new CreditSplitDto();
		objSplitDto.setCardNumber("1724570000000031");
		  // objDto.setCardNumber("1724570000000031");
		//objDto.setCardNumber("1724570000000022");
		//objTest.getCardData();
		 //objTest.getCardStop();
		// objTest.accountInfoTest();
	//objTest.customerSummaryTest();
	 // objTest.customerInfoTest();
	//objTest.callRecordLogTest();
		CommonDataBean obj =	objTest.callNewRecord("RF0608227468");
    // objTest.tranxHistory();	
		
   //  objTest.csrResetPinCount();
	//objTest.cardSplitData();
   //objTest.csrCrdeitSplit();
  // objTest.renewalProcess();
//	objTest.disputeClaimTest();
		
	//	objTest.disputeClaimListTest();
	//int res =objTest.checkCardHolderType(objSplitDto);	
	//	objTest.disputeClaimFormValidation();
	//	objTest.disputeWorkItem();
  
		//to check CarBilling Cycle
		/*CardBillingDto objCardBillDto = new CardBillingDto();
		objCardBillDto.setIssuerId("Issuer1");
		objCardBillDto.setBillingCycleDone("BillingCycleDone");
		objCardBillDto =objTest.checkBllingCycle(objCardBillDto);	*/	
		
	//	objTest.cardSplitDataWithForm();
	  /*	 Map cardsDetailList = objTest.cardDetailsTest(objDto);
		 if(cardsDetailList!=null && cardsDetailList.size()>0){
      	
      	   if(cardsDetailList.containsKey("OtherCards")){
      		   System.out.println("sat2");
      	       ArrayList OtherCardsList = (ArrayList)cardsDetailList.get("OtherCards");
      	      // CommonDataBean commObj =(CommonDataBean)OtherCardsList.get(0);
      	    // System.out.println("value" +commObj.getColumn1());
      	       System.out.println("3");
      	   } 
      	  if(cardsDetailList.containsKey("SupplyCards")){
      		  System.out.println("4");
      		  ArrayList supplyCardsList = (ArrayList)cardsDetailList.get("SupplyCards");
      		  System.out.println("5");
      		  
             }
      	  if(cardsDetailList.containsKey("PrimaryCards")){
      		  System.out.println("6");
      		  ArrayList primaryCardsList = (ArrayList)cardsDetailList.get("PrimaryCards");
      		  System.out.println("7");
      		 
             }
		 }   */ 
	}
	

}
