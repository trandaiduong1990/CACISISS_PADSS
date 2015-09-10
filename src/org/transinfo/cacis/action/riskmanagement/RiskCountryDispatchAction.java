package org.transinfo.cacis.action.riskmanagement;

import java.util.Collection;
import java.util.Map;

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
import org.transinfo.cacis.controller.riskmanagement.RiskCountryManager;
import org.transinfo.cacis.dto.riskmanagement.RiskCountryDto;
import org.transinfo.cacis.formbean.riskmanagement.RiskCountryForm;
import org.transinfo.cacis.formbean.riskmanagement.SearchRiskCountryForm;

@SuppressWarnings( { "unchecked", "deprecation" })
public class RiskCountryDispatchAction extends BaseDispatchAction {


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


		SearchRiskCountryForm objForm = (SearchRiskCountryForm) form;

		//objForm.getPreListData();        

		RiskCountryDto objDto = new RiskCountryDto();
		try
		{
			// BeanUtils.copyProperties(objDto, objForm);
			if(objForm.getSearchCardNo()!=null && !objForm.getSearchCardNo().equals("")){
				objDto.id.setCardNo(Long.parseLong(objForm.getSearchCardNo()));
			}


		}
		catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		RiskCountryManager objManager = new RiskCountryManager();
		Collection riskCntryForm = objManager.search(objDto,pageNo);

		request.setAttribute("SEARCHLIST",riskCntryForm);
		request.setAttribute("PAGENO",new Integer(pageNo).toString());

		System.out.println("RiskCountryFormAction: execute() successful");

		return mapping.findForward("success");
	}



	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: addnew method()");

		//ActionErrors errors = null;

		// Token Validation
		/*	if(!isTokenValid(request))
		{	errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request,errors);
			return mapping.findForward("token");
		} */


		// Form Validations
		RiskCountryForm objNewForm = new RiskCountryForm();
		RiskCountryForm objForm = (RiskCountryForm) form;
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
		RiskCountryForm objForm = (RiskCountryForm) form;
		objForm.getPreListData();

		if(objForm==null)
			System.out.println("objForm Null");

		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","add");
			return mapping.getInputForward();
		}

		// DTO Creation
		RiskCountryDto objDto = new RiskCountryDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);
			objDto.id.setCardNo(Long.parseLong(objForm.getCardNo()));
			objDto.id.setCountryCode(objForm.getCountryCode());

			//RiskCitiesDto riskCitiesDto = new RiskCitiesDto();

			//String[] selList = objForm.getSelCities();
			//System.out.println("selList.length==>"+selList.length);

			/*if(selList.length > 0)
			{

				for(int intIndex=0; intIndex<selList.length; intIndex++){
					System.out.println("Selected List:==>"+selList[intIndex]);
					objDto.getRiskCities().put(selList[intIndex], riskCitiesDto);
				}

				riskCitiesDto.id.setCardNo(Long.parseLong(objForm.getCardNo()));
				riskCitiesDto.id.setCountryCode(objForm.getCountryCode());
				riskCitiesDto.setStatus(objForm.getStatus());
				riskCitiesDto.setUpdatedDate(objForm.getUpdatedDate());
				riskCitiesDto.setUserId(objForm.getUserId());
			}else{
				System.out.println("selList is Null");
			}*/

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		// Action Execution
		RiskCountryManager objManager = new RiskCountryManager();
		boolean boolAdd = objManager.add(objDto);

		if(!boolAdd)
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
		}
		else
		{
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
		RiskCountryForm objForm = (RiskCountryForm) form;
		objForm.getPreListData();
		errors = objForm.validate(mapping,request);

		if(errors!=null && !errors.isEmpty())
		{
			saveErrors(request,errors);
			request.setAttribute("ACTION","update");
			return mapping.getInputForward();
		}

		// DTO Creation
		RiskCountryDto objDto = new RiskCountryDto();
		try
		{
			BeanUtils.copyProperties(objDto, objForm);  
			objDto.id.setCardNo(Long.parseLong(objForm.getCardNo()));
			objDto.id.setCountryCode(objForm.getCountryCode());

			/*RiskCitiesDto riskCitiesDto = new RiskCitiesDto();

			String[] selList = objForm.getSelCities();
			if(selList != null)
			{                        
				for(int intIndex=0; intIndex<selList.length; intIndex++){
					System.out.println("Selected List:==>"+selList[intIndex]);
					objDto.getRiskCities().put(selList[intIndex], riskCitiesDto);
				}

				riskCitiesDto.id.setCardNo(Long.parseLong(objForm.getCardNo()));
				riskCitiesDto.id.setCountryCode(objForm.getCountryCode());
				riskCitiesDto.setStatus(objForm.getStatus());
				riskCitiesDto.setUpdatedDate(objForm.getUpdatedDate());
				riskCitiesDto.setUserId(objForm.getUserId());
			}else{
				System.out.println("selList is Null");
			}
*/
		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		RiskCountryManager objManager = new RiskCountryManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate)
		{
			System.out.println("RiskCountryFormAction: update record fail");
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

		System.out.println("RiskCountryFormAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION","cancel");
		return mapping.findForward("change");
	}


	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		Map citiesList = null;
		RiskCountryManager objManager = new RiskCountryManager();
		RiskCountryForm objForm = (RiskCountryForm) form;
		System.out.println("%%%%%%%%  PRELIST");
		objForm.getPreListData();

		// DTO Creation
		RiskCountryDto objDto = new RiskCountryDto();        
		System.out.println("CardNo in Change==>"+request.getParameter("searchCardNo"));
		objDto = objManager.getRiskCountryForm(Long.parseLong(request.getParameter("searchCardNo")), request.getParameter("countryCode"));
		try
		{
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setCardNo(String.valueOf(objDto.id.getCardNo()));
			citiesList = objManager.getCities(objDto);
			objForm.setCitiesList(citiesList);

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

	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {
		System.out.println("We are in get() method."); 
		Map citiesList = null;            
		RiskCountryManager objManager = new RiskCountryManager();
		RiskCountryForm objForm = (RiskCountryForm) form;
		RiskCountryDto objDto = new RiskCountryDto();

		System.out.println("%%%%%%%%  PRELIST");
		objForm.getPreListData();

		System.out.println("Country Code==>"+request.getParameter("countryCode"));
		System.out.println("Action==>"+request.getParameter("frmAction"));
		System.out.println("CardNo in get==>"+request.getParameter("cardNo"));
		try{     
			if(objForm.getCardNo()!=null && !objForm.getCardNo().equals("")){    
				objDto.id.setCardNo(Long.parseLong(objForm.getCardNo()));
			}  
			objDto.id.setCountryCode(objForm.getCountryCode());
			citiesList = objManager.getCities(objDto);                
			System.out.println("citiesList==>"+citiesList.size());
			objForm.setCitiesList(citiesList);

		}catch (Exception e)
		{

			throw new TPlusException("Could not populate the form bean: " + e);
		}

		//Success
		saveToken(request);
		if(request.getParameter("frmAction").equals("update")){
			request.setAttribute("ACTION","update");
		}else{
			request.setAttribute("ACTION","add");
		}
		//request.setAttribute("ACTION","add");
		return mapping.findForward("change");
	}

}

