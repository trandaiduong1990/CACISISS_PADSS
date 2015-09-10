package org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.CardGradeFormDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.applicationforms.CardGradeFormDto;
import org.transinfo.cacis.dto.applicationforms.CardGradeFormSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.common.ApplicationMasterDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.formbean.customerservice.CustomerServiceDataBean;
import org.transinfo.cacis.model.CardGeneration;
import org.transinfo.cacis.model.CustomerService;

public class CardGradeFormDAOImpl extends BaseDAOImpl implements  CardGradeFormDAO {
	
	/*
	 *  (non-Javadoc)
	 * @see org.transinfo.cacis.dataacess.dao.applicationforms.CardGradeFormDAO
	 * #search(org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms.CardGradeFormDto)
	 * this method is for Get all the  Data(for all customer service screens) using 
	 *  model's(CustomerSevice class getCustomerServiceData() method)
	 */ 
	public Collection search(CardGradeFormDto objDto)  throws TPlusException {

		   Collection objSearchCollection =null;
		
		try	{
		   
		if(objDto.getCardNumber()>0){
		   CustomerService objCustServ = new CustomerService();
		   CustomerServiceDataBean objService = objCustServ.getCustomerServiceData(objDto.getCardNumber());
		   objSearchCollection = new ArrayList();
		   objSearchCollection.add(objService);
		   }
		
		}catch (Exception e)
		{
			System.out.println("Error in CardGradeFormDAOImpl search method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in CardGradeFormDAOImpl search  method :"+e);
		}
		finally
		{
	 	}
	 return  objSearchCollection ;
 }
	/*
	 *  (non-Javadoc)
	 * @see org.transinfo.cacis.dataacess.dao.applicationforms.CardGradeFormDAO
	 * #add(org.transinfo.cacis.dto.applicationforms.CardGradeFormDto)
	 * this mehod is for updating the cards table and inserting data into
	 *  cardgrade_forms ,Application_Master and cardActvites tables 
	 */
public boolean add(CardGradeFormDto objDto) throws TPlusException{
	
	boolean bolExecute=false;
	Transaction tx = null;
	CardActivityDto objCardActivity =null;
    ApplicationMasterDto objAppMaster =null;
	try
	{
		Session session =HibernetFactory.currentSession();
		tx =session.beginTransaction();
									
	  //inserting into Application_master Table
	   objAppMaster = new ApplicationMasterDto();
	   objAppMaster.setApplicationId(objDto.getApplicationId());
	   objAppMaster.setIssuerId(objDto.getIssuerId());
	   objAppMaster.setApplicationStatus(objDto.getApplicationStatus());
	   objAppMaster.setApplicationType(objDto.getApplicationType());
	   objAppMaster.setIdNumber(objDto.getIdNumber());
	   objAppMaster.setOpenDate(objDto.getOpenDate());
	   objAppMaster.setUserId(objDto.getUserId());
	   objAppMaster.setUpdatedDate(objDto.getUpdatedDate());
		
	    session.save(objAppMaster);
	  
      // saving to CardGrade_forms table
		session.save(objDto); 
	    
       //inserting into CardActivites Table
		objCardActivity = new CardActivityDto();
		objCardActivity.setDateTime(new Date());
		objCardActivity.setCardNumber(objDto.getCardNumber());
		objCardActivity.setActivity("CardGrade Application Created");
		objCardActivity.setStationIp(InetAddress.getLocalHost().getHostAddress());
		objCardActivity.setUserId(objDto.getUserId());
		objCardActivity.setReason(objDto.getRemarks());
		objCardActivity.setLastUpdatedBy(objDto.getUserId());
		objCardActivity.setUpdatedDate(objDto.getUpdatedDate());
		
		session.save(objCardActivity);
					
		tx.commit();
		bolExecute=true;
	}

	catch (Exception e)
	{
		System.out.println("Error:while add method in CardGradeFormDAOIMPL" +e.getMessage());

		if(tx!=null)
		{
			tx.rollback();
		}
	throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while add method in CardGradeFormDAOIMPL"+e);
	}
	finally
	{
		HibernetFactory.closeSession();
 	}
    return 	bolExecute;
	
	}
 
  //methods implementation for CardGradeFormProcess
  /* 
   * this method is used to get the data for dipalying the replacementcards list from  CardGrade_forms table 
   * to aceept or reject process
   * 
   */

public Collection processSearch(CardGradeFormSearchDto objSearchDto,int pageNo)  throws TPlusException {
    
    
    Collection objSearchCollection = new ArrayList();
	StringBuffer sbf = new StringBuffer();
	
 try {
          sbf.append("select crg.applicationId,crg.customerName,crg.idNumber,");
          sbf.append("to_char(crg.updatedDate,'dd-MM-yyyy') FROM  CardGradeFormDto crg where crg.applicationStatus = "+CommonCodes.APPLICATIONSTATUS_NEW+" ");
          
         if(objSearchDto.getCardNumber()>0){
		    sbf.append("and crg.cardNumber = "+objSearchDto.getCardNumber()+" ");
	     }
         if(objSearchDto.getIssuerId()!=null && !objSearchDto.getIssuerId().equals("")){
        	 sbf.append("and crg.issuerId = '"+objSearchDto.getIssuerId()+"' ");
         }
         
        objSearchCollection = getSearchList(sbf.toString(),pageNo);
        
      }catch (Exception e){
	     System.out.println("Error in   CardGradeDAOImpl processSearch Method"+e.getMessage());
	 throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in   CardGradeDAOImpl processSearch  Method"+e);
   }
    
    return objSearchCollection;
  }
/*
 *  (non-Javadoc)
 * @see org.transinfo.cacis.dataacess.dao.applicationforms. CardGradeFormDAO
 * #getCardGradeDto(java.lang.String)
 * this for getting the  CardGradeDto Data
 */

public CardGradeFormDto getCardGradeFormDto(String applicationId)throws TPlusException
{

	  CardGradeFormDto objDto =null;
	  Transaction tx =null;
	 
		try
		{

	    Session session =HibernetFactory.currentSession();
	    tx = session.beginTransaction();
	    objDto = (CardGradeFormDto)session.get(CardGradeFormDto.class,applicationId);
	   tx.commit();
		}
		
		catch (Exception e)
		{
			if(tx!=null)
			{
				tx.rollback();
			}
			System.out.println("Error while getting  CardGradeFormDto data in getCardGradeFormDto method" +e.getMessage());
			 throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while getting  CardGradeFormDto data in getCardGradeFormDto method"+e);
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}
  
		return 	objDto;

	}
public boolean update(CardGradeFormDto objDto)throws TPlusException
{
	boolean bolExecute = false;
	Transaction tx = null;
	try
	{
		Session session = HibernetFactory.currentSession();
		tx = session.beginTransaction();
		session.update(objDto);
		session.flush();
		tx.commit();
		bolExecute = true;
	}

	catch (Exception e) {
		if (tx != null) {
			tx.rollback();
		}
		System.out.println("Error while updating CardGradeForm data "+ e);
		throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
								  "Error: while updating CardGradeForm data" + e);

	} finally
	{
		HibernetFactory.closeSession();
	}

	return bolExecute;
}
/*
 *  (non-Javadoc)
 * @see org.transinfo.cacis.dataacess.dao.applicationforms.CardGradeFormDAO
 * #accept(org.transinfo.cacis.dto.applicationforms.CardGradeFormDto)
 * this method is used for aceepting the cardgradeform
 */
public boolean accept(CardGradeFormDto objCardGradeDto) throws TPlusException{
	
	  boolean accept =false;
	  Transaction tx =null;
	  int count;
	  CardActivityDto objCardActivity =null;
	  CardsDto objCardsDto =null;
	  CardEmbossingDto objCardEb =null;  
	  //DispatchLetterSearchDto objDispLetters =null;
	 try{
	      Session session =HibernetFactory.currentSession();
          tx= session.beginTransaction();
          
  	    //this for canceling this card its all supply cards
          if(objCardGradeDto.getSelectedCancelOrSplit()!=null && objCardGradeDto.getSelectedCancelOrSplit().equals("Cancel")){ 
  		       if(objCardGradeDto.getCardsToCancel().length>0){
  		
  			    for(int i=0;i<objCardGradeDto.getCardsToCancel().length;i++){
  			    String cardsSql = "UPDATE CardsDto SET cardStatusId =:cardstatus WHERE cardNumber in(:cardnumbers)";
  				count= session.createQuery(cardsSql)
  				            .setParameterList("cardnumbers",objCardGradeDto.getCardsToCancel())
  						    .setLong("cardstatus",CommonCodes.CARD_CLOSE)
  						    .executeUpdate();
  			     }
  		       }
          }
          //updating CardGrade_forms table                      
            String sql  = "UPDATE CardGradeFormDto SET applicationStatus =:appacceptstatus  WHERE applicationId=:applicationid";
 		    count   = session.createQuery(sql)
 					   .setString("applicationid",objCardGradeDto.getApplicationId())
 					   .setInteger("appacceptstatus",objCardGradeDto.getApplicationStatus())
 					   .executeUpdate();
          
 		  //updating in Application_master table
		    String   appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
		     count       =  session.createQuery(appMtsql)
						       .setString("applicationid",objCardGradeDto.getApplicationId())
						       .setInteger("appacceptstatus",objCardGradeDto.getApplicationStatus())
						       .setDate("closedate",objCardGradeDto.getCloseDate())
						      .executeUpdate();	  
 	   
		
		   //caraNumber Generation
		    if(count>0){
		        CardGeneration  objCardGen = CardGeneration.getInstance();
	 		    ArrayList  sendList = new ArrayList();  
	  	        sendList.add("0");
	  	        sendList.add(objCardGradeDto.getSelectedCardProductId());
	  	        sendList.add(objCardGradeDto.getBranchId());
	  	        sendList.add(objCardGradeDto.getIssuerId());
	  	 
	  	        ArrayList recvList =objCardGen.cardGeneration(sendList);
	  	    //Inserting Data into Cards Table
	 		    objCardsDto = new CardsDto();
	 		   
	 		//setting the account object to cards
	 		    System.out.println("the Account id in DAOIMPL" +objCardGradeDto.getCustomerAccountDto().getAccountId());
	 		    objCardsDto.setCustAccountDto(objCardGradeDto.getCustomerAccountDto());
	 		   
			    objCardsDto.setCardNumber(Long.parseLong((String)recvList.get(0)));
			    objCardsDto.setCardExpDate((String)recvList.get(1));
			    objCardsDto.setCvki(((Integer)recvList.get(2)).intValue());
			    objCardsDto.setPvki(((Integer)recvList.get(3)).intValue());
			    
			    objCardsDto.setIssuerId(objCardGradeDto.getIssuerId());
			    objCardsDto.setCardProductId(objCardGradeDto.getSelectedCardProductId());
			    objCardsDto.setCardHolderType(objCardGradeDto.getCardHolderType());
			    objCardsDto.setCardStatusId(objCardGradeDto.getCardStatusId());
			    objCardsDto.setCustomerId(objCardGradeDto.getCustomerId());
			    objCardsDto.setEffectiveDate(objCardGradeDto.getEffectiveDate());
			    objCardsDto.setPinDisabled('N');
			    objCardsDto.setStatus("A");
			    objCardsDto.setLastUpdatedBy(objCardGradeDto.getUserId());
			    objCardsDto.setUpdatedDate(objCardGradeDto.getUpdatedDate());
			//Setting remaining card data by generaing random numbers
			    objCardGen.getCardData(objCardsDto);
			
			    session.save(objCardsDto);
			    
			    if(objCardGradeDto.getSelectedCancelOrSplit()!=null&&objCardGradeDto.getSelectedCancelOrSplit().equals("Split")){
			  
			     String currCardSet = "UPDATE CardsDto SET creditLimitPercent =:creditlimitpercentage WHERE cardNumber =:cardnumber";
		  		 count              = session.createQuery(currCardSet)
		  				              .setLong("cardnumber",objCardGradeDto.getCardNumber())
		  				              .setInteger("creditlimitpercentage",objCardGradeDto.getCurrCardCreditLimitPer())
		  						      .executeUpdate();	
			    
			    String newCardSet = "UPDATE CardsDto SET creditLimitPercent =:creditlimitpercentage WHERE cardNumber =:cardnumber";
  				count= session.createQuery(newCardSet)
  				        .setLong("cardnumber",objCardsDto.getCardNumber())
  				        .setInteger("creditlimitpercentage",objCardGradeDto.getNewCardCreditLimitPer())
  						.executeUpdate();
			    
			    }
			    
			    
			 	    
			 //Inserting Data into CardEmbossing Table 
	 			objCardEb = new CardEmbossingDto();
	 			//objCardEb.setCardNumber(objCardsDto.getCardNumber());
	 			//objCardEb.setCustomerId(objCardsDto.getCustomerId());
	 			objCardEb.setIssuerId(objCardsDto.getIssuerId());
	 			//objCardEb.setMagStripeData("Embossing Data");
	 			objCardEb.setStatus(objCardGradeDto.getCardProcessStatus());
	 			objCardEb.setLastUpdatedBy(objCardGradeDto.getUserId());
	 			objCardEb.setUpdatedDate(objCardGradeDto.getUpdatedDate());
	 			 			
	 			session.save(objCardEb);
	 			
	 			 //	inserting into LettersDispatch table  
			    /*objDispLetters = new DispatchLetterSearchDto();
			    objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
			    objDispLetters.setLetterId(CommonCodes.CARDGRADE_APPLICATION);
			    objDispLetters.setApplicationId(objCardGradeDto.getApplicationId());
			    objDispLetters.setCardNumber(objCardGradeDto.getCardNumber());
			    objDispLetters.setIssuerId(objCardGradeDto.getIssuerId());
			    objDispLetters.setStatus(CommonCodes.CARD_PROCESS_PROCESS);
			    objDispLetters.setLastUpdateDate(objCardGradeDto.getUpdatedDate());
			    objDispLetters.setLastUpdatedBy(objCardGradeDto.getUserId());
				  
				  session.save(objDispLetters);	*/		
		     
		   //Inserting Data into CardActivity Table 
	 			objCardActivity = new CardActivityDto();
	 		    objCardActivity.setDateTime(new Date());
	 		    objCardActivity.setCardNumber(objCardGradeDto.getCardNumber());
	 			objCardActivity.setActivity("CardGradeForm Application Acccepted");
	 			objCardActivity.setStationIp("127.0.0");
	 			objCardActivity.setUserId(objCardGradeDto.getUserId());
				objCardActivity.setReason(objCardGradeDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCardGradeDto.getUserId());
				objCardActivity.setUpdatedDate(objCardGradeDto.getUpdatedDate());
	 		
 			    session.save(objCardActivity); 		 
		     }
 	       session.flush();
 	   	   tx.commit();
 	      // tx.rollback();
 	   	   if(count>0)accept=true;
	   
	       }catch (Exception e){
	    	  if(tx!=null){
	    		 tx.rollback();
	    	 }
			  System.out.println("Error while accepting the  data in CardGradeFormDAOImpl AcceptMethod" +e.getMessage());
			 throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while accepting the CardGradeForm in CardGradeFormDAOImpl"+e);	
		}
	    finally
		{
	    	
			HibernetFactory.closeSession();
	 	}
	return accept;
}
/*
 *  (non-Javadoc)
 * @see org.transinfo.cacis.dataacess.dao.applicationforms.CardGradeFormDAO
 * #reject(org.transinfo.cacis.dto.applicationforms.CardGradeFormDto)
 * this method is used for rejecting the CardGradeForm
 */
public boolean reject(CardGradeFormDto objCardGradeDto) throws TPlusException{
	 
	  boolean reject =false;
	  Transaction tx =null;
	  int count;
	  CardActivityDto objCardActivity =null;
	  //DispatchLetterSearchDto objDispLetters =null;
	   try{
		          
		  Session	session =HibernetFactory.currentSession();
	      tx =session.beginTransaction();
		
	     //updating cardReplacements_forms
	      String sql   = "UPDATE CardGradeFormDto SET applicationStatus =:apprejectstatus WHERE applicationId=:applicationid";
       	  count    = session.createQuery(sql)
					   .setString("applicationid",objCardGradeDto.getApplicationId())
					   .setInteger("apprejectstatus",objCardGradeDto.getApplicationStatus())
					   .executeUpdate();
        
        //updating Application_master table	  
		  String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:apprejectstatus,closeDate =:closedate WHERE applicationId=:applicationid";
	      count           = session.createQuery(appMtsql)
					       .setString("applicationid",objCardGradeDto.getApplicationId())
					       .setInteger("apprejectstatus",objCardGradeDto.getApplicationStatus())
					       .setDate("closedate",objCardGradeDto.getUpdatedDate())
					       .executeUpdate();	
       
	    
		    if(count>0){
		    	
		    	 //	inserting into LettersDispatch table  
			    /*objDispLetters = new DispatchLetterSearchDto();
			    objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
			    objDispLetters.setLetterId(CommonCodes.CARDGRADE_REJECT_APPLICATION);
			    objDispLetters.setApplicationId(objCardGradeDto.getApplicationId());
			    objDispLetters.setCardNumber(objCardGradeDto.getCardNumber());
			    objDispLetters.setIssuerId(objCardGradeDto.getIssuerId());
			    objDispLetters.setStatus(CommonCodes.CARD_PROCESS_NEW);
			    objDispLetters.setLastUpdateDate(objCardGradeDto.getUpdatedDate());
			    objDispLetters.setLastUpdatedBy(objCardGradeDto.getUserId());
				  
				  session.save(objDispLetters);	*/
				
				 //Inserting Data into CardActivity Table 
				objCardActivity = new CardActivityDto();
			    objCardActivity.setDateTime(new Date());
			    objCardActivity.setCardNumber(objCardGradeDto.getCardNumber());
				objCardActivity.setActivity("CardGradeForm Application Rejected");
				objCardActivity.setStationIp(InetAddress.getLocalHost().getHostAddress());
				objCardActivity.setUserId(objCardGradeDto.getUserId());
				objCardActivity.setReason(objCardGradeDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objCardGradeDto.getUserId());
				objCardActivity.setUpdatedDate(objCardGradeDto.getUpdatedDate());
				
				session.save(objCardActivity);
	    }
		  
		 
		   tx.commit();
		  
		   System.out.println("reject Count="+count);
		   if(count>0) reject =true;
	   }

			catch (Exception e)
			{
				 if(tx!=null){
		    		 tx.rollback();
		    	 }
				System.out.println("Error while rejecting the CardGradeForm in CardGradeFormDAOImpl" +e.getMessage());
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error:  while rejecting the CardGradeForm in CardGradeFormDAOImpl"+e);
			}
			finally{
				  HibernetFactory.closeSession();
			}
	return reject;
}

public int checkExistrecord(Object commonObj)throws TPlusException{
	
	int res=0;
	try
		{ 
		       StringBuffer sbf = new StringBuffer();
		       Session session =HibernetFactory.currentSession();
		 
		 if(commonObj instanceof CardGradeFormDto){
			 CardGradeFormDto objDto =(CardGradeFormDto)commonObj; 
			     sbf.append("select count(*) from CardGradeFormDto cgd where cgd.applicationId='"+objDto.getApplicationId()+"' ");
	          }else if(commonObj instanceof ApplicationMasterDto){
			     ApplicationMasterDto objDto =(ApplicationMasterDto)commonObj;
				 sbf.append("select count(*) from ApplicationMasterDto mst where mst.idNumber ='"+objDto.getIdNumber()+"' ");
				 sbf.append("and mst.applicationType ="+objDto.getApplicationType()+" ");
				 sbf.append("and mst.closeDate is  null ");
			   }
		  
		   Query qry = session.createQuery(sbf.toString());
		   List list = qry.list();
		   res = ((Integer)list.get(0)).intValue();
	   System.out.println("After CardReplacmentDAOImpl checkExistrecord()" +res);
		}

		catch (Exception e)
		{
			System.out.println("Error Because Record Already Existed:" +e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error:  Because Record Already Existed:"+e);
		}

		finally
		{
			HibernetFactory.closeSession();
	 	}

	return 	res;
}

}
