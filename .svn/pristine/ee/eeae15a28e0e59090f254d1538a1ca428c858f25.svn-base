package org.transinfo.cacis.action.riskmanagement;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.riskmanagement.CardActivityManager;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.formbean.riskmanagement.CardActivitySearch;

public class SearchCardActivityAction extends BaseDispatchAction {
	
	/*public ActionForward preList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CardActivitySearch objForm = (CardActivitySearch) form;
		} catch (Exception exp) {
			System.out.println("Error converting to form bean: " + exp);
		}
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}*/
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		CardActivitySearch objForm = (CardActivitySearch) form;
		
		// Form Validations
		errors = objForm.validate(mapping, request);
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		int pageNo = 0;
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}
	
	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		CardActivitySearch objForm = (CardActivitySearch) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) + 1;

		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward PREV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		CardActivitySearch objForm = (CardActivitySearch) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}
	
	public void commonSearch(int pageNo, CardActivitySearch objForm, HttpServletRequest request) throws TPlusException, Exception{
		CardActivityDto objDto = new CardActivityDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		CardActivityManager objManager = new CardActivityManager();
		Collection CardActivity = objManager
				.search(objDto, pageNo);

		request.setAttribute("SEARCHLIST", CardActivity);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		System.out
				.println("CardActivityAction: execute() successful");
	}
	
	public static void main(String s[]) throws Exception {
		CardActivitySearch objForm = new CardActivitySearch();
		CardActivityDto objDto = new CardActivityDto();
		objForm.setCardNumber("123456789012346000");
		//objForm.setActivity("qwerty");
		objForm.setCardActivityId("123");
		//objForm.setDateTime("12/12/2005");
		objForm.setStationIp("000.000.000.000");
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		System.out.println("objDto.getCardNumber = "+objDto.getCardNumber());
		System.out.println("objDto.getActivity = "+objDto.getActivity());
		System.out.println("objDto.getCardActivityId = "+objDto.getCardActivityId());
		System.out.println("objDto.getDateTime = "+objDto.getDateTime());
		System.out.println("objDto.getStationIp = "+objDto.getStationIp());
	}
	
}