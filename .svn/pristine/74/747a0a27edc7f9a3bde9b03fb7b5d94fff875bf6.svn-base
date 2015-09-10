package org.transinfo.cacis.controller.disputemanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeClaimFormDAO;
import org.transinfo.cacis.dto.disputemanagement.DisputeClaimFormDto;
import org.transinfo.cacis.dto.disputemanagement.SearchDisputeClaimFormDto;

public class DisputeClaimFormManager {
	 
	private DisputeClaimFormDAO objDAO;
	   
	    public DisputeClaimFormManager()throws Exception {
	    	objDAO = DAOFactory.getInstance().getDisputeClaimFormDAO();
	      
	      }
	  /*
	    * This method is used for getting the CalimFormList
	    */
	   public Collection search(SearchDisputeClaimFormDto objSearchDto,int pageNo) throws TPlusException {
	        
	           Collection claimList =null;
	   try {
		     claimList = objDAO.search(objSearchDto, pageNo);
	             
	       } catch (Exception e) {
	    	   throw new TPlusException("Error in DisputeClaimFormManager search method" +e);
	    }
	    return claimList;
	}
	   /*
	    * This method is used for getting the CalimFormList
	    */
	   public DisputeClaimFormDto createClaim(DisputeClaimFormDto objDto) throws TPlusException {
	        
		   DisputeClaimFormDto claimFormDt0 =null;
	   try {
		   claimFormDt0 = objDAO.createClaim(objDto);
	             
	       } catch (Exception e) {
	    	   throw new TPlusException("Error in DisputeClaimFormManager createClaim method" +e);
	    }
	    return claimFormDt0;
	}
	   /*
	    * This method is used for insert the particular  Record to Baranch table
	    */
	    public boolean add(DisputeClaimFormDto objDto)  throws TPlusException {

	         boolean boolAdd = false;
	         objDto.setClaimNumber(IdsGenartion.GenerateClaimId());
	         objDto.setStatus(CommonCodes.CLAIM_NEW);
	         objDto.setDocsUploaded(CommonCodes.DOCSUPLOADED_NO);
	        //for remraks id
	         objDto.setRemarksId(IdsGenartion.GenRemarksId());
	         
	         try {
 		 /*  if(!validate(objDto,objDAO.ADD)){
			     System.out.println("\n\n Record Already Exists");
				}else{*/
				
					boolAdd = objDAO.add(objDto);
			//	 }
				
	            } catch (Exception e) {
	            	 throw new TPlusException("Error in DisputeClaimFormManager add method" +e);
	        }
	       return boolAdd;
	    }
	    
	    /*
	     * this method is used for calling the checkexistrecord method in Impl class
	     */
	    public boolean validate(Object obj,int mode )throws TPlusException
	    {
	        boolean rtnMessage = true;

	        DisputeClaimFormDto objDto = (DisputeClaimFormDto)obj;
	     

	        if(mode==0 && objDAO.checkExistrecord(objDto)>0)
	        {
	            rtnMessage = false;
	        }
	        if(mode==1 && objDAO.checkExistrecord(objDto)==0)
	        {
	   			rtnMessage = false;
	        }

	        return rtnMessage;
	    }  

	    public static void main(String args[]){
	    	try{
	  DisputeClaimFormManager objManager = new DisputeClaimFormManager();
	   	DisputeClaimFormDto objDto = new DisputeClaimFormDto();
	   	objDto.setCardNumber(Long.valueOf("1724570000000022").longValue());
	    objDto.setSettlementId(800) ;
	    objDto.setTranxDate("5/5/2006 2:37:25");
	    objDto.setIssuerId("Issuer1");
	    objDto.setTranxAmt("14.0");
	   //objDto  =objManager.createClaim(objDto);
	    
	  boolean res =  objManager.validate(objDto,0);
	    //System.out.println("size" +arl.size());
	    	
	    	}catch(Exception ex){
	    		 System.out.println("exception" +ex.getMessage());
	    	}
	    }
}
