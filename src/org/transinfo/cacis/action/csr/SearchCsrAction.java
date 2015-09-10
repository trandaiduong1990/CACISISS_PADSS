package org.transinfo.cacis.action.csr;

import java.util.Collection;

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
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.controller.csr.CsrManager;
import org.transinfo.cacis.dto.csr.CallRecordLogDto;
import org.transinfo.cacis.dto.csr.CsrSearchDto;
import org.transinfo.cacis.formbean.csr.CsrSearchForm;

@SuppressWarnings({"deprecation","unchecked"})
public class SearchCsrAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                         throws TPlusException,Exception {
    	  
    	  ActionErrors errors = null;
    	  HttpSession  session =null;
    	  
    	      
    	  //Form Validation
    	  CsrSearchForm objForm = (CsrSearchForm) form;
    	//  errors = objForm.validate(mapping,request);
  		
  		if(errors!=null && !errors.isEmpty())
  		{
  			saveErrors(request,errors);
  			return mapping.findForward("callcentersearch");
  		}
  		
       		
  		
  	  //  this excutes when click on openCallList Reference No
	   if(request.getParameter("RefNo")!=null){
		   System.out.println("\n\n 1");
		   objForm.setCallRefNo(request.getParameter("RefNo"));
		 }
	    //Dto Creation
        CsrSearchDto objDto  = new CsrSearchDto();
 
   try{
          BeanUtils.copyProperties(objDto, objForm);
     
      }catch (Exception e){
           System.out.println("Error converting to form bean in SearchCsrAction  " + e);
           throw new TPlusException("Could not populate the form bean in SearchCsrAction " + e);
        }
           
            CsrManager objManager = new CsrManager();
       //This is for QuickOpen and After search result when clicks on particular card number 
       	   if(request.getParameter("cardNo")!=null ||(objForm.getQuickCardNo()!=null && !objForm.getQuickCardNo().equals("")) ){
       		       session =request.getSession(false);
       		  
         //  this for setting the cardnumber in sesion to get the cardnumber for other screens After search result clicks on cardNo
           if(request.getParameter("cardNo")!=null){
       			  System.out.println("\n\n 2" );
       		      session.setAttribute("CARDNUMBER",request.getParameter("cardNo"));
       		     }
       	 //this for quick open
           if(objForm.getQuickCardNo()!=null && !objForm.getQuickCardNo().equals("")){
       			   System.out.println("\n\n 3");
       			 //checking for card number exist or not
       		     boolean checkCardNo = objManager.validate(objDto,0);
       		     if(checkCardNo) {
 		                errors = new ActionErrors();
 	                    errors.add("Error", new ActionError("error.nocardfound"));
 		                saveErrors(request, errors);
 		                return mapping.findForward("callcentersearch");
 	               }else{
 	            	  System.out.println("\n\n  5");
       		        session.setAttribute("CARDNUMBER",objForm.getQuickCardNo());
 	                }
       		   }  
       	      	
       		  System.out.println("\n\n  6");
           //Generating Call Record Reference No and setting in Session  
       	      session.setAttribute("NEWCALLREFNO",IdsGenartion.GenerateCallRefNo()); 
       	   //Setting the data to insert the record into call_record_table
       		 CallRecordLogDto objCallLogDto = new CallRecordLogDto();
       		 objCallLogDto.setReferenceNo((String)session.getAttribute("NEWCALLREFNO"));
       
       		 //if clicks on open calls then click on refno to insert this call preve and original refno if call is open	 
    	 if(request.getSession(false).getAttribute("CALLRECREFNO")!=null){
    	//to get the preve and original refno using this refno	 
    	 CommonDataBean commonDataBean = objManager.previousCallsCheck((String)session.getAttribute("CALLRECREFNO"));
        //if prevrefno ==null insert in new record's prevrefno and original refno fields with orignalrefNo		
    		 if(commonDataBean.getColumn1()==null){
    		        objCallLogDto.setPrevReferenceNo(commonDataBean.getColumn2());
    		        objCallLogDto.setOriginalRefNo(commonDataBean.getColumn2());
    		   }else{
    	           //if prevreno not null insert above orinalreno as refno		
    			   objCallLogDto.setOriginalRefNo(commonDataBean.getColumn2());
    			  //get the refNo using above prevrefno and originalrefno insert in preverefno filed in new record 
    			   objCallLogDto.setPrevReferenceNo(commonDataBean.getColumn3());
    			
    		 }
    	//if there in records for this call then insert originalref no as generated refno and prevrefno =null	 
    	   }else{
       			 objCallLogDto.setOriginalRefNo(objCallLogDto.getReferenceNo());
           }
       		 
       	     objCallLogDto.setCardNumber(Long.valueOf((String)session.getAttribute("CARDNUMBER")).longValue());
       	  	 objCallLogDto.setIssuerId((String)session.getAttribute("ISSUER"));
       		 objCallLogDto.setUserId((String)session.getAttribute("USERID"));
       	        
       		 objManager.callRecordLog(objCallLogDto);
             
            //this is for forwarding to the csrindex page  
            return  mapping.findForward("csrindex");
         }
      //this excutes when Enters ReferenceNo in CsrSearch and clicks on OpenCalls RefNo
    	   if(objForm.getCallRefNo()!=null && !objForm.getCallRefNo().equals("")){
    		       boolean checkRefNo = objManager.validate(objDto,0);
    		       System.out.println("\n\n 7"+checkRefNo);
	                if(checkRefNo) {
	                	errors = new ActionErrors();
	                    errors.add("Error", new ActionError("error.nocallrefno"));
		                saveErrors(request, errors);
		                return mapping.findForward("callcentersearch");
	               }else{
	            	   //this setting the Refno in seeion to get previous calls list data
	                session =request.getSession(false);  
    		        session.setAttribute("CALLRECREFNO",objForm.getCallRefNo());
	                }     
    		   }
		     //Action Execution
            Collection searchResult = objManager.callCenterSearch(objDto);  
           
            System.out.println("\n\n 8");  
            if(searchResult!=null && searchResult.size()>0){
            	request.setAttribute("SEARCHLIST",searchResult);
            }
     
       //this is for forwarding to the csrsearchpage with search result   
       return  mapping.findForward("callcentersearch");
    }
 }