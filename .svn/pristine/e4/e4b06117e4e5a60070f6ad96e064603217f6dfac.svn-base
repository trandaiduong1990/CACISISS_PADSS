package org.transinfo.cacis.action.applicationforms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.applicationforms.CardGradeFormManager;
import org.transinfo.cacis.dto.applicationforms.CardGradeFormSearchDto;
import org.transinfo.cacis.formbean.applicationforms.CardGradeSearchForm;


public class SearchCardGradeFormAction extends BaseAction {



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


	 	CardGradeSearchForm objForm = (CardGradeSearchForm) form;
	 	CardGradeFormSearchDto objDto = new CardGradeFormSearchDto();
        try
        {
            BeanUtils.copyProperties(objDto, objForm);
        }
        catch (Exception e)
        {
           System.out.println("Error converting to form bean: " + e);
           throw new TPlusException("Could not populate the form bean: " + e);
        }
        CardGradeFormManager objManager = new CardGradeFormManager();
        Collection cadrGradeList = objManager.processSearch(objDto,pageNo);

		request.setAttribute("SEARCHLIST",cadrGradeList);
		request.setAttribute("PAGENO",new Integer(pageNo).toString());

		//System.out.println("LIST SIZE"+supplementaryForm.size());
        System.out.println("SearchSupplementaryFormAction: execute() successful");

        return mapping.findForward("success");
    }

 }