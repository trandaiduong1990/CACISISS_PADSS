package org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeClaimFormDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.disputemanagement.DisputeClaimFormDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeFormRemarksDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeRequestDocumentsDto;
import org.transinfo.cacis.dto.disputemanagement.SearchDisputeClaimFormDto;
import org.transinfo.cacis.util.DateUtil;

public class DisputeClaimFormDAOImpl extends BaseDAOImpl implements DisputeClaimFormDAO {
	
	/*
	 * This method is used for getting the ClaimFomList page Data
	 */
 public Collection search(SearchDisputeClaimFormDto objSearchDto,int pageNo)  throws TPlusException {
			
		   Collection objSearchCollection = new ArrayList();
		   StringBuffer sbf = new StringBuffer();
	try	{	 
	
		 sbf.append("select st.settlementId,st.cardNumber,st.merchantName,to_char(st.tranxDate,'DD-MM-YYYY HH24:MI:SS'), ");
		 sbf.append("st.originalAmount,st.originalCurrName,st.amountCurr, st.currName, st.refNumber ");
		 sbf.append("from OriginalRequestDto st where ");
	     sbf.append("st.cardNumber = "+objSearchDto.getCardNumber()+" and ");
	     if(objSearchDto.getAmount()>0){
	    	  sbf.append("st.amountCurr = "+objSearchDto.getAmount()+" and ");
	     }
		 sbf.append("to_date(to_char(st.tranxDate,'DD/MM/YYYY'),'DD/MM/YYYY') = TO_DATE('"+objSearchDto.getTranxDate()+"', 'DD/MM/YYYY')) ");
					    
	     objSearchCollection = getSearchList(sbf.toString(),pageNo);
		//HH24:MI:SS
		}catch (Exception e){
			
			System.out.println("Error in DisputeClaimFormDAOImpl search method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in DisputeClaimFormDAOImpl search  method :"+e);
			
		}
 return objSearchCollection;
   
 }
 /*used to show  the claim form Edit Page Data*/
 public DisputeClaimFormDto createClaim(DisputeClaimFormDto objDto) throws TPlusException{
	
	 Transaction tx = null;
	 StringBuffer sbf = new StringBuffer();
	 long settlementId =objDto.getSettlementId();
	try
	{   
		
		Session session =HibernetFactory.currentSession();
		tx =session.beginTransaction();
		 sbf.append("select  cr.cardNumber,app.customerName,app.dob, app.idNumber, app.expDate, ");
		 sbf.append("st.merchantName,to_char(st.tranxDate,'DD/MM/YYYY'),st.refNumber,st.originalAmount,st.originalCurrName,st.amountCurr, st.currName, ");
	     sbf.append("add.address1 ,add.address2,add.city,add.state,add.country,add.postalCode,add.phone,add.fax ");
	   	 sbf.append("From ApplicationProcessDto app, CardsDto cr, CustomerAddressDto add,OriginalRequestDto st ");
	   	 sbf.append("where ");
	   	 sbf.append("cr.customerId = app.customerId and ");
	   	 sbf.append("app.customerId = add.appFormProcssDto.customerId and ");
		 sbf.append("app.billingTo = add.addressType and ");
		 sbf.append("cr.cardNumber = "+objDto.getCardNumber()+" and  ");
		 sbf.append("st.settlementId = "+objDto.getSettlementId()+" ");
	   	 Query qry = session.createQuery(sbf.toString());
		 List resultList = qry.list();
	     for(Iterator it = resultList.iterator();it.hasNext();){
		    Object obj[]= (Object[])it.next();
       
		     objDto =new DisputeClaimFormDto();
		     //setting Customer Details
			 objDto.setCardNumber(Long.valueOf(String.valueOf(obj[0])).longValue());
			 objDto.setCustomerName((String)obj[1]);
			 objDto.setDateOfBirth(DateUtil.convertDateToDateString((Date)obj[2]));
			 objDto.setNricNo((String)obj[3]);
			 objDto.setNricExpire(DateUtil.convertDateToDateString((Date)obj[4]));
          	 //Setting the Tranx Details 
			 objDto.setMerchantName((String)obj[5]);
			 objDto.setTranxDate((String)obj[6]);
			 objDto.setReferenceNo((String)obj[7]);
			 objDto.setTranxAmt(String.valueOf(obj[8]));
			 objDto.setTranxCurr((String)obj[9]);
			 objDto.setSettlementAmt(String.valueOf(obj[10]));
			 objDto.setSettlementCurr((String)obj[11]);
			 //Setting the ContactAddress Details 
		   objDto.setAddress1((String)obj[12]);
		   objDto.setAddress2((String)obj[13]);
		   objDto.setCity((String)obj[14]);
		   objDto.setState((String)obj[15]);
		   objDto.setCountry((String)obj[16]);
		   objDto.setPostalCode(Long.valueOf(String.valueOf(obj[17])).longValue());
		   objDto.setPhoneNumber(Long.valueOf(String.valueOf(obj[18]).trim()).longValue());
		   objDto.setFaxNumber(Long.valueOf(String.valueOf(obj[18]).trim()).longValue());
		   	
		   //setting the settlementId
		     objDto.setSettlementId(settlementId);
		
		     //  claimData.add(objDto);
      
	      }
	   tx.commit();     
	  
	}
	catch (Exception e)
	{
		if(tx!=null)
		{
			tx.rollback();
		}
	throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while createClaim method in DisputeClaimFormDAOImpl"+e);
	}
	finally
	{
		HibernetFactory.closeSession();
	}
return objDto;	
}
 
	 /*
     * This method is used for insert the particular  Record to  tables
     */
  public boolean add(DisputeClaimFormDto objDto)throws TPlusException { 
    	
    	boolean boolAdd=false;
		Transaction tx = null;
		DisputeRequestDocumentsDto objDocReqDto =null;
		 DisputeFormRemarksDto  objRemarksDto =null;
	try	{
		  Session	session = HibernetFactory.currentSession();
		  				tx  = session.beginTransaction();
		 // to insert Dispute_Claim_Form 
		  session.save(objDto);
		
		  //for Mandatory Docs	 to insert Dispute_Request_Documents
		  String manDocs[] = objDto.getSelectedMandatoryDocs();
		  for(int i=0;i<manDocs.length;i++){
			  objDocReqDto = new DisputeRequestDocumentsDto();
			  objDocReqDto.getId().setClaimNumber(objDto.getClaimNumber());
			  objDocReqDto.getId().setDocumentId(manDocs[i]);
			  objDocReqDto.setDocsUploaded(objDto.getDocsUploaded());
			  objDocReqDto.setRemarksId(objDto.getRemarksId());
			  objDocReqDto.setUpdatedDate(objDto.getUpdatedDate());
			  objDocReqDto.setUserId(objDto.getUserId());
			  session.save(objDocReqDto);
		  }
		  //For NonMnadatory Docs   to insert Dispute_Request_Documents
		  String nonManDocs[] = objDto.getSelectedNonMandatoryDocs();
		  for(int i=0;i<nonManDocs.length;i++){
			  objDocReqDto = new DisputeRequestDocumentsDto();
			   objDocReqDto.getId().setClaimNumber(objDto.getClaimNumber());
			  objDocReqDto.getId().setDocumentId(nonManDocs[i]);
			  objDocReqDto.setDocsUploaded(objDto.getDocsUploaded());
			  objDocReqDto.setRemarksId(objDto.getRemarksId());
			  objDocReqDto.setUpdatedDate(objDto.getUpdatedDate());
			  objDocReqDto.setUserId(objDto.getUserId());
			  session.save(objDocReqDto);
		  }
		 //to insert into Dispute_Form_Remrks
		  objRemarksDto =new DisputeFormRemarksDto();
		  objRemarksDto.setRemarksId(objDto.getRemarksId());
		  objRemarksDto.setClaimNumber(objDto.getClaimNumber());
		  objRemarksDto.setClaimDate(objDto.getClaimDate());
		  objRemarksDto.setCommunicationType("Request");
		  objRemarksDto.setRemarks("NEW CLAIM CREATED:"+objDto.getRemarks());
		  objRemarksDto.setAction(objDto.getStatus());
		  session.save(objRemarksDto);
		 	  
	      tx.commit();
		  boolAdd=true;
		  
		}
		
		catch (Exception e)
		{
			 if(tx!=null)
				{
					tx.rollback();
				}
		   System.out.println("Error in DisputeClaimFormDAOImpl add method : "+e.getMessage());
		   throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in DisputeClaimFormDAOImpl add  method :"+e);
		}
		 finally
		    {
			HibernetFactory.closeSession();
		    }
	return 	boolAdd;

}
 /*used to validate the Tranx Amount and Tranx Dates*/     
   public int checkExistrecord(DisputeClaimFormDto objDto)throws TPlusException {

        	int res=0;
    	try
    		{
    	     	Session session =HibernetFactory.currentSession();
    	     	//to validate TranxAmount
    	     	Query qryAmt = session.createQuery("select dcm.minDisputeAmt from DisputeConfigMasterDto dcm where dcm.issuerId=:issuerid ");
    	     	qryAmt.setString("issuerid",objDto.getIssuerId());
    			List listAmt = qryAmt.list();
    			double minDisputeAmt = ((Double)listAmt.get(0)).doubleValue();
    		
    		if(Double.valueOf(objDto.getTranxAmt()).doubleValue()>minDisputeAmt){
               //to validate TranxDate
    			Query qryDate = session.createQuery("select dcm.transactionValidationPeriod from DisputeConfigMasterDto dcm where dcm.issuerId='"+objDto.getIssuerId()+"' and (sysdate - to_date('"+objDto.getTranxDate()+"','dd/mm/yyyy'))>dcm.transactionValidationPeriod ");
    				List listData = qryDate.list();
    				if(listData.size()>0)
    				res = 1;
    			    }
    			System.out.println("After DisputeClaimFormDAOImpl checkExistrecord() count" +res);

    		}

    		catch (Exception e)
    		{
    			System.out.println("Error in DisputeClaimFormDAOImpl checkExist record method:" +e.getMessage());
    			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error:  Error in BranchDAOImpl checkExistrecord method:"+e);
    		}

    		finally
    		{
    			HibernetFactory.closeSession();
    	 	}

    	return 	res;

    }  

}


