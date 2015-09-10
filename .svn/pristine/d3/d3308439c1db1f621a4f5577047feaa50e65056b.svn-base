package org.transinfo.cacis.dataacess.daoimpl.oracle.excell;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.controller.authorization.BlackListCardManager;
import org.transinfo.cacis.controller.cardproduction.ApplicationFormManager;
import org.transinfo.cacis.controller.cardproduction.CardDeliverManager;
import org.transinfo.cacis.controller.cardproduction.CardEmbossingManager;
import org.transinfo.cacis.controller.cardproduction.PinPrintingManager;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.excell.UploadDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.CardDeliverSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingSearchDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingSearchDto;
import org.transinfo.cacis.dto.excell.UploadDto;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.model.excell.ExcellDataWriter;

public class UploadDAOImpl extends BaseDAOImpl implements UploadDAO {
	 /*
     * This method is used for insert the particular  Record to respect tables and write to excell
     */
    public boolean add(UploadDto objUploadDto)throws TPlusException { 
    	
	    	boolean boolAdd=false;
 			Transaction tx = null;
			  //writeToExcell after reading success or fail
	        ExcellDataWriter objDataWriter = new ExcellDataWriter();
	        List resultRow = new ArrayList();
	    	ArrayList colDataArl = null;
	        String errorMsg=" ";  
	      //  String fileName = "D:/eclipse/workspace/StrutsCacis/.deployables/StrutsCacis/WEB-INF/classes/org/transinfo/cacis/model/excell/xls/response";
	       
	        String fileName = objUploadDto.getUploadResPath();
	      
	        String sheetName=" ";
	        String processCode="Fail";
	        //cols names
	       ArrayList colNamesArl = new ArrayList();
	    	colNamesArl.add("REFERENCE ID");
	    	colNamesArl.add("PROCESS CODE");
	    	colNamesArl.add("DESCRIPTION");
	    	colNamesArl.add("PROCESS DATE");
	       //adding cols names as first row
	    	resultRow.add(colNamesArl);
	   	
    	
	try	{
		
	        for(int i=0; i<objUploadDto.getDtoList().size();i++){
	            Object obj = (Object)((ArrayList)objUploadDto.getDtoList()).get(i);
	        
	           if(obj instanceof ApplicationFormDto){ 
	       
		  	        ApplicationFormDto appDto  =(ApplicationFormDto)obj;
		            appDto.setIssuerId(objUploadDto.getIssuerId());
		        	appDto.setUserId(objUploadDto.getUserId());
		  	        appDto.setUpdatedDate(objUploadDto.getUpdatedDate());
		  	     ApplicationFormManager objManager = new ApplicationFormManager();
			     //to excell write
	                if(i==0){
			         fileName   = fileName+"/APPLICATIONFORM_RESPONSE";
			         sheetName = "APPRESPONSE";
	                 }
   		  		 //to insert in applicationforms table
	                try
			  		 {
		  	            boolAdd = objManager.add(appDto);
		         	 }catch(Exception exp) {
						   boolAdd=false;
						   processCode = "Fail";
						   errorMsg = exp.getMessage();
					  }
    	  		  if(boolAdd){
		        	     processCode = "Success";
		        	 }
    	    //to excell write	  
		  	    colDataArl =  new ArrayList();
	  		    colDataArl.add(appDto.getReferenceId());
	  		    colDataArl.add(processCode);
	  		    colDataArl.add(errorMsg);
	  		    colDataArl.add(new Date());
		        //adding cols data to write
		  	    resultRow.add(colDataArl); 
		  		
		  	 } 
		 	 
		  	 
		  	 if(obj instanceof CurrencyDto){
		  		 Session	session = HibernetFactory.currentSession();
		  	     CurrencyDto currDto = (CurrencyDto)obj;
		  	     StringBuffer sbf = new StringBuffer();
		  	     Connection con =  session.connection();
		  	     Statement st = con.createStatement();
		  	  //to get the rate list
		  	    ArrayList codeList =(ArrayList)currDto.getCurrCodeList();
		  	     sbf.append("INSERT INTO CURR_RATE (ISSUER_ID, ALLL,");
	             for(int j=0;j<codeList.size();j++){
			   	  	 sbf.append(" "+codeList.get(j)+" ");
			   	      sbf.append(",");
			  	   } 
	            sbf.append("START_DATE,END_DATE,LAST_UPDATED_DATE,LAST_UPDATED_BY )");
	           //to get the currency code list
		  	    ArrayList rateList =(ArrayList)currDto.getCurrRateList();  
		  	    sbf.append("VALUES ('"+objUploadDto.getIssuerId()+"','', ");
		  	     	    
		  	    for(int k=0;k<rateList.size();k++){
		  	    	//String currCode = (String)rateList.get(j);
		  	    	 sbf.append(" "+rateList.get(k)+" ");
		  			 sbf.append(",");
		  	    }
		  	  	   sbf.append("SYSDATE, SYSDATE, SYSDATE, ");
		  	  	   sbf.append(" '"+objUploadDto.getUserId()+"') ");	
		  	  	 
		  	      System.out.println("Currency Rate Created Sql inUploadDAOImpl add method : :" +sbf.toString()); 
		  	        boolAdd =  st.execute(sbf.toString()) ;
		  	        
		  	      //to excell write
	             /*   if(i==0){
			         fileName   = fileName+"/CURRREATE_RESPONSE";
			         sheetName = "CURRRATERESPONSE";
	                 }
   		  		 //to insert in applicationforms table
	                try
			  		 {
	                	 boolAdd =  st.execute(sbf.toString()) ;
		         	 }catch(Exception exp) {
						   boolAdd=false;
						   processCode = "Fail";
						   errorMsg = exp.getMessage();
					  }
    	  		  if(boolAdd){
		        	     processCode = "Success";
		        	 }
    	    //to excell write	  
		  	    colDataArl =  new ArrayList();
	  		    colDataArl.add(currDto.getReferenceId());
	  		    colDataArl.add(processCode);
	  		    colDataArl.add(errorMsg);
	  		    colDataArl.add(new Date());
		        //adding cols data to write
		  	    resultRow.add(colDataArl); */
		  		
		  	      	 }
		  
		  	 if(obj instanceof BlackListCardDto){ 
		    	   BlackListCardDto blackListDto = (BlackListCardDto)obj;
		  	 		 blackListDto.setIssuerId(objUploadDto.getIssuerId());
		  	 		 blackListDto.setUpdatedDate(objUploadDto.getUpdatedDate());
		  	 		 blackListDto.setUserId(objUploadDto.getUserId());
		  		BlackListCardManager objManager = new BlackListCardManager();
		  	 	  //to excell write
		                if(i==0){
				         fileName   = fileName+"/BLACKLISTCARDS_RESPONSE";
				         sheetName = "BLKCARDSRESPONSE";
		                 }
	   		  	    try
				  		 {
	   		  	          boolAdd = 	  objManager.add(blackListDto);
		                	  
			         	 }catch(Exception exp) {
							   boolAdd=false;
							   processCode = "Fail";
							   errorMsg = exp.getMessage();
						  }
	    	  		  if(boolAdd){
			        	     processCode = "Success";
			        	   }
	    	    //to excell write	  
			  	    colDataArl =  new ArrayList();
		  		    colDataArl.add(blackListDto.getReferenceId());
		  		    colDataArl.add(processCode);
		  		    colDataArl.add(errorMsg);
		  		    colDataArl.add(new Date());
			        //adding cols data to write
			  	    resultRow.add(colDataArl);
		  	 	    
		  	 	 }
        
		    if(obj instanceof CardEmbossingSearchDto){ 
            		
                  CardEmbossingSearchDto cardEmboSerchDto =(CardEmbossingSearchDto)obj;
            	    cardEmboSerchDto.setLastUpdatedBy(objUploadDto.getUserId());
            	    cardEmboSerchDto.setUpdatedDate(objUploadDto.getUpdatedDate());
            	    CardEmbossingManager objManager = new CardEmbossingManager();
            	    //to excell write
	                if(i==0){
			         fileName   = fileName+"/CARDEMBOSSING_RESPONSE";
			         sheetName = "CARDEMBOSSRESPONSE";
	                 }
   		  		 //to insert in Card_embosiingtable table
	                try
			  		 {
	                	  boolAdd = objManager.save(cardEmboSerchDto);
		         	 }catch(Exception exp) {
						   boolAdd=false;
						   processCode = "Fail";
						   errorMsg = exp.getMessage();
					  }
    	  		  if(boolAdd){
		        	     processCode = "Success";
		        	 }
    	    //to excell write	  
		  	    colDataArl =  new ArrayList();
	  		    colDataArl.add(cardEmboSerchDto.getReferenceId());
	  		    colDataArl.add(processCode);
	  		    colDataArl.add(errorMsg);
	  		    colDataArl.add(new Date());
		        //adding cols data to write
		  	    resultRow.add(colDataArl); 
		  		 
            	
		  	  	  }
          
             
             if(obj instanceof PinPrintingSearchDto){ 
            	
            	  PinPrintingSearchDto pinPrintSearchDto  =(PinPrintingSearchDto)obj;
            	  //pinPrintSearchDto.setIssuerId(objUploadDto.getIssuerId());
            	  pinPrintSearchDto.setLastUpdatedBy(objUploadDto.getUserId());
            	  pinPrintSearchDto.setUpdatedDate(objUploadDto.getUpdatedDate());
           	      PinPrintingManager objManager = new PinPrintingManager();
           	   //to excell write
	                if(i==0){
			         fileName   = fileName+"/PINPRINTING_RESPONSE";
			         sheetName = "PINPRINTRESPONSE";
	                 }
 		  		 //to insert in Pin_Printing table table
	                try
			  		 {
	                	 boolAdd = objManager.save(pinPrintSearchDto);
		         	 }catch(Exception exp) {
						   boolAdd=false;
						   processCode = "Fail";
						   errorMsg = exp.getMessage();
					  }
  	  		  if(boolAdd){
		        	     processCode = "Success";
		        	 }
  	    //to excell write	  
		  	    colDataArl =  new ArrayList();
	  		    colDataArl.add(pinPrintSearchDto.getReferenceId());
	  		    colDataArl.add(processCode);
	  		    colDataArl.add(errorMsg);
	  		    colDataArl.add(new Date());
		        //adding cols data to write
		  	    resultRow.add(colDataArl); 
           	      
		  	  	  }
        
            if(obj instanceof CardDeliverSearchDto){ 
            	
            	  CardDeliverSearchDto cardDeliverSearchDto  =(CardDeliverSearchDto)obj;
            	  cardDeliverSearchDto.setLastUpdatedBy(objUploadDto.getUserId());
            	  cardDeliverSearchDto.setUpdatedDate(objUploadDto.getUpdatedDate());
           	      CardDeliverManager objManager = new CardDeliverManager();
           	   //to excell write
	                if(i==0){
			         fileName   = fileName+"/CARDDELIVER_RESPONSE";
			         sheetName = "CARDDELIVERRESPONSE";
	                 }
		  		 //to insert in Pin_Printing table table
	                try
			  		 {
	                	 boolAdd = objManager.save(cardDeliverSearchDto);
		         	 }catch(Exception exp) {
						   boolAdd=false;
						   processCode = "Fail";
						   errorMsg = exp.getMessage();
					  }
	  		  if(boolAdd){
		        	     processCode = "Success";
		        	  }
	    //to excell write	  
		  	    colDataArl =  new ArrayList();
	  		    colDataArl.add(cardDeliverSearchDto.getReferenceId());
	  		    colDataArl.add(processCode);
	  		    colDataArl.add(errorMsg);
	  		    colDataArl.add(new Date());
		        //adding cols data to write
		  	    resultRow.add(colDataArl); 
           	      
		  	  	  }     	   
		 }
		 	
	        //to insert into the update log table
	        Session	session = HibernetFactory.currentSession();
		  	tx  = session.beginTransaction();
		    session.save(objUploadDto);
	      
		 
				
	      tx.commit();
		  boolAdd=true;
		  
		}
		
		catch (Exception e)
		{
			 if(tx!=null)
				{
					tx.rollback();
				}
		  
		   // errorMsg = e.getMessage();
		  //  processCode = "Fail";
		    System.out.println("Error in UploadDAOImpl add method : "+e.getMessage());
		    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in UploadDAOImpl add  method :"+e);
		}
	 finally
		    {
			 
			HibernetFactory.closeSession();
			//calling excell writer
	    	 objDataWriter.writeToExcel(fileName,sheetName,resultRow);
		  }
	return 	boolAdd;

}
    
  public int checkExistrecord(UploadDto  objDto)throws TPlusException {

    	int res=0;
	try
		{
	     	Session session =HibernetFactory.currentSession();
	     	StringBuffer sbf = new StringBuffer();
	     	sbf.append("select count(*) from  UploadDto up where up.uploadType =:uploadtype ");
	     	sbf.append("and up.uploadFileName =:filename and up.status=:uploadstatus");
	     	Query qry = session.createQuery(sbf.toString());
		    qry.setString("uploadtype",objDto.getUploadType());
		    qry.setString("filename",objDto.getUploadFileName());
		    qry.setString("uploadstatus",objDto.getStatus());
		    
			List list = qry.list();
	    	res = ((Integer)list.get(0)).intValue();
			System.out.println("After UploadDAOIMPL checkExistrecord() count" +res);

		}

		catch (Exception e)
		{
			
			System.out.println("Error in UploadDAOIMPL checkExist record method:" +e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error:  Error in UploadDAOIMPL checkExistrecord method:"+e);
		}

		finally
		{
			HibernetFactory.closeSession();
	 	}

	return 	res;

}  

}
