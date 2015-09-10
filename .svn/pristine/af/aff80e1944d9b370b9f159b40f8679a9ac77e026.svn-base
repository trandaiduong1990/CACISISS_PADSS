package org.transinfo.cacis.action.disputemanagement;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.disputemanagement.DisputeClaimFormManager;
import org.transinfo.cacis.dto.disputemanagement.SearchDisputeClaimFormDto;
import org.transinfo.cacis.formbean.disputemanagement.SearchDisputeClaimForm;


public class SearchDisputeClaimFormAction extends BaseAction {

    public ActionForward executeAction(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                         throws TPlusException,Exception {
  
    	  
    	 int pageNo=0;

 		if(request.getParameter("mode")!=null && ((String)request.getParameter("mode")).equals("NEXT"))
		{
 			
			if(request.getParameter("pageNo") != null)
			{
				pageNo = Integer.parseInt((String)request.getParameter("pageNo"))+1;
				
			}
	 	}
	 	if(request.getParameter("mode")!=null && ((String)request.getParameter("mode")).equals("PREV"))
		{
	 		
			if(request.getParameter("pageNo") != null)
			{
				pageNo = Integer.parseInt((String)request.getParameter("pageNo"))-1;
				
			}
	 	}
	    
	 	SearchDisputeClaimForm objForm = (SearchDisputeClaimForm) form;
	 	System.out.println("A1" +objForm.getAmount());
        ActionErrors  errors = objForm.validate(mapping,request);
       
        if(errors!=null && !errors.isEmpty())
 		{
        	 saveErrors(request,errors);
        	 return mapping.findForward("success");
 		}
        //Dto Creation
        SearchDisputeClaimFormDto objDto = new SearchDisputeClaimFormDto();
        try
        {
            BeanUtils.copyProperties(objDto, objForm);
        }
        catch (Exception e)
        {
        	System.out.println("Error converting to form bean in SearchDisputeClaimFormAction execute method: " + e.getMessage());
            throw new TPlusException("Error converting to form bean in SearchDisputeClaimFormAction execute method: " + e);
        }
       //Action Execution
        DisputeClaimFormManager objManager = new DisputeClaimFormManager();
        Collection claimRecords = objManager.search(objDto,pageNo);
        
	     request.setAttribute("SEARCHLIST",claimRecords);
		 request.setAttribute("PAGENO",new Integer(pageNo).toString());
	     return mapping.findForward("success");
    }
  }
