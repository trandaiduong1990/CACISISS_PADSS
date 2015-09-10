package org.transinfo.cacis.action.riskmanagement;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.riskmanagement.RiskTranxSalesManager;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxSaleCashDto;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxSaleCashMccsDto;
import org.transinfo.cacis.formbean.riskmanagement.RiskTranxSaleCashForm;


public class RiskTranxSalesDispatchAction extends BaseDispatchAction {


       public ActionForward search(ActionMapping mapping,
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


    	RiskTranxSaleCashForm objForm = (RiskTranxSaleCashForm) form;
        
    	objForm.getPreListData();        
        objForm.setIssuerId((String)request.getSession().getAttribute("ISSUER"));   
        objForm.setTranxCode(request.getParameter("tranxCode"));
        System.out.println("Mcc Available List Size==>"+objForm.getMccList().size());
        
        RiskTranxSaleCashDto objDto = new RiskTranxSaleCashDto();
        try
        {
            BeanUtils.copyProperties(objDto, objForm);
            
        }
        catch (Exception e)
        {
           System.out.println("Error converting to form bean: " + e);
           throw new TPlusException("Could not populate the form bean: " + e);
        }
        RiskTranxSalesManager objManager = new RiskTranxSalesManager();
        Collection riskTranxForm = objManager.search(objDto,pageNo);

		request.setAttribute("SEARCHLIST",riskTranxForm);
		request.setAttribute("PAGENO",new Integer(pageNo).toString());

        System.out.println("RiskTranxFormAction: execute() successful");

        return mapping.findForward("success");
    }



    public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addmethod()");

		ActionErrors errors = null;

		// Token Validation
	      /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
                RiskTranxSaleCashForm objNewForm = new RiskTranxSaleCashForm();
		RiskTranxSaleCashForm objForm = (RiskTranxSaleCashForm) form;
                objNewForm.setIssuerId(objForm.getIssuerId());
                
                try
		{
			BeanUtils.copyProperties(objForm, objNewForm);
                        
                } catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
                
		objForm.getPreListData();

	 	System.out.println("Add New Successful");
		// Success

		//resetToken(request);
		return mapping.findForward("change");
                
    }



	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addmethod()");

		ActionErrors errors = null;

		// Token Validation
	      /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
		RiskTranxSaleCashForm objForm = (RiskTranxSaleCashForm) form;
		objForm.getPreListData();

		if(objForm ==null)
		System.out.println("objForm Null");

		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","add");
			return mapping.getInputForward();
		}

		// DTO Creation
		RiskTranxSaleCashDto objDto = new RiskTranxSaleCashDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

                RiskTranxSaleCashMccsDto riskTranxMccDto = new RiskTranxSaleCashMccsDto();
                riskTranxMccDto.setLastUpdatedDate(objForm.getUpdatedDate());
                riskTranxMccDto.setUserId(objForm.getUserId());

                String[] selList = objForm.getSelMccList();
                if(selList != null)
                {
                
                for(int intIndex=0; intIndex<selList.length; intIndex++){
                  System.out.println("Selected List:==>"+selList[intIndex]);
                  objDto.getRiskTranxMcc().put(selList[intIndex], riskTranxMccDto);
                }
                }else{
                         System.out.println("selList is Null");                        
                }

                
		// Action Execution
		RiskTranxSalesManager objManager = new RiskTranxSalesManager();
		boolean boolAdd = objManager.add(objDto);

		if(!boolAdd)
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
		}
		else
		{
                        System.out.println("addition operation success");
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
                        
		}

		System.out.println("Record Added" + boolAdd);
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("change");
	}



	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in DipatchAction update method");

		ActionErrors errors = null;

		// Token Validation
	     /*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
		RiskTranxSaleCashForm objForm = (RiskTranxSaleCashForm) form;
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","update");
			return mapping.getInputForward();
		}

		// DTO Creation
		RiskTranxSaleCashDto objDto = new RiskTranxSaleCashDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);                       
 			RiskTranxSaleCashMccsDto riskTranxMccDto = new RiskTranxSaleCashMccsDto();

                    String[] selList = objForm.getSelMccList();
                    if(selList != null)
                    {    
                   
                    for(int intIndex=0; intIndex<selList.length; intIndex++){
                        System.out.println("Selected List:==>"+selList[intIndex]);
                        objDto.getRiskTranxMcc().put(selList[intIndex], riskTranxMccDto);
                    }
                    
                    riskTranxMccDto.setId(new Integer(objForm.getId()).intValue());
                    riskTranxMccDto.setLastUpdatedDate(objForm.getUpdatedDate());
                    riskTranxMccDto.setUserId(objForm.getUserId());
     
                    }else{
                         System.out.println("selList is Null");                        
                    }



		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		RiskTranxSalesManager objManager = new RiskTranxSalesManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate)
		{
			System.out.println("RiskTranxSalesDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION","update");
			saveErrors(request, errors);
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("RiskTranxSalesDispatchAction:update() successful");
        objForm.getPreListData();
		objForm.getSelectedListData();
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("change");
	}


	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		RiskTranxSalesManager objManager = new RiskTranxSalesManager();
		RiskTranxSaleCashForm objForm = (RiskTranxSaleCashForm) form;
		System.out.println("%%%%%%%%  PRELIST");
		objForm.getPreListData();

		// DTO Creation
		RiskTranxSaleCashDto objDto = new RiskTranxSaleCashDto();        
		objDto = objManager.getRiskTranxForm(Integer.parseInt(request.getParameter("id")));
		try
		{
			BeanUtils.copyProperties(objForm, objDto);
                       
		}
		catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		//Success
		saveToken(request);
		request.setAttribute("ACTION","update");
		return mapping.findForward("change");
	}

}

