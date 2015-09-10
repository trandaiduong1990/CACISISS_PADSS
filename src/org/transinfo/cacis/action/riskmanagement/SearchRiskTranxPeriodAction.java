package org.transinfo.cacis.action.riskmanagement;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.riskmanagement.RiskTranxPeriodManager;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxPeriodDto;
import org.transinfo.cacis.formbean.riskmanagement.RiskTranxPeriodForm;


public class SearchRiskTranxPeriodAction extends BaseAction {





public ActionForward executeAction(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                         throws TPlusException,Exception {
System.out.println("***"+request.getParameter("method")+"  "+(String)request.getSession().getAttribute("ISSUER")+"  "+(String)request.getParameter("userType"));



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

        RiskTranxPeriodForm objForm = (RiskTranxPeriodForm) form;
        RiskTranxPeriodDto objDto = new RiskTranxPeriodDto();

        try
        {
        
            BeanUtils.copyProperties(objDto, objForm);
            objDto.id.setIssuerId((String)request.getSession().getAttribute("ISSUER"));
        }
        catch (Exception e)
        {
           System.out.println("Error converting to form bean: " + e);
           throw new TPlusException("Could not populate the form bean: " + e);
        }
        RiskTranxPeriodManager objManager = new RiskTranxPeriodManager();
        Collection riskTranxPeriodForm = objManager.search(objDto,pageNo);

		request.setAttribute("SEARCHLIST", riskTranxPeriodForm);
		request.setAttribute("PAGENO",new Integer(pageNo).toString());
		

		//System.out.println("LIST SIZE"+userForm.size());
        System.out.println("SearchRiskTranxPeriodAction: execute() successful");

        return mapping.findForward("success");
 }



 
}