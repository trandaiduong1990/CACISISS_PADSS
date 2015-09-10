package org.transinfo.cacis.action.authorization;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.authorization.BlackListCardManager;
import org.transinfo.cacis.controller.authorization.BlockMerchantManager;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;
import org.transinfo.cacis.dto.authorization.BlockMerchantDto;
import org.transinfo.cacis.formbean.authorization.BlackListCardSearchForm;
import org.transinfo.cacis.formbean.authorization.BlockMerchantSearchForm;

public class SearchBlockMerchantFormAction extends BaseAction {



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


    	//BlackListCardSearchForm objForm = (BlackListCardSearchForm) form;
	 	BlockMerchantSearchForm objForm =(BlockMerchantSearchForm) form;
        BlockMerchantDto objDto = new BlockMerchantDto();
        try
        {
            BeanUtils.copyProperties(objDto, objForm);
            objDto.setIssuerId((String)request.getSession().getAttribute("ISSUER"));
            if(objForm.getSearchMerchantId()!=null && !objForm.getSearchMerchantId().equals("")){
              objDto.setSearchMerchantId(Long.parseLong(objForm.getSearchMerchantId()));
            }
        }
        catch (Exception e)
        {
           System.out.println("Error converting to BlockMerchantSearchForm bean: " + e);
           throw new TPlusException("Could not populate the form bean: " + e);
        }
        //BlackListCardManager objManager = new BlackListCardManager();
        BlockMerchantManager objMananger = new BlockMerchantManager(); 
        //Collection blackListForm = objManager.search(objDto,pageNo);
        Collection blockMerchantForm = objMananger.search(objDto,pageNo);


		request.setAttribute("SEARCHLIST",blockMerchantForm);
		request.setAttribute("PAGENO",new Integer(pageNo).toString());

		System.out.println("LIST SIZE"+blockMerchantForm.size());
        System.out.println("SearchBlockMerchantRFormAction: execute() successful");

        return mapping.findForward("success");
    }

 }