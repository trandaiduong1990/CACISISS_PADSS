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
import org.transinfo.cacis.controller.riskmanagement.CardholderLimitAdjustmentManager;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.CardLimitsDto;
import org.transinfo.cacis.dto.riskmanagement.CardholderLimitAdjustmentDto;
import org.transinfo.cacis.dto.riskmanagement.CardholderLimitAdjustmentSearchDto;
import org.transinfo.cacis.formbean.riskmanagement.CardholderLimitAdjustment;

public class SearchCardholderLimitAdjustmentAction extends BaseDispatchAction {
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		CardholderLimitAdjustment objForm = (CardholderLimitAdjustment) form;
		int pageNo = 0;
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}

	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		CardholderLimitAdjustment objForm = (CardholderLimitAdjustment) form;
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
		CardholderLimitAdjustment objForm = (CardholderLimitAdjustment) form;
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request);
		return mapping.findForward("success");
	}
	
	public void commonSearch(int pageNo, CardholderLimitAdjustment objForm, HttpServletRequest request) throws TPlusException, Exception{
		CardholderLimitAdjustmentSearchDto objDto = new CardholderLimitAdjustmentSearchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		CardholderLimitAdjustmentManager objManager = new CardholderLimitAdjustmentManager();
		Collection CardholderLimitAdjustment = objManager
				.search(objDto, pageNo);

		request.setAttribute("SEARCHLIST", CardholderLimitAdjustment);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		System.out
				.println("CardholderLimitAdjustmentAction: execute() successful");
		objForm.getPreListData();
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in SearchAction: create()");

		CardholderLimitAdjustmentManager objManager = new CardholderLimitAdjustmentManager();
		CardholderLimitAdjustment objForm = (CardholderLimitAdjustment) form;
		CardholderLimitAdjustmentDto objDto = new CardholderLimitAdjustmentDto();

		boolean exist = false;
		ActionErrors errors = null;

		// DTO Creation
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		exist = objManager.validate(objDto, BaseDAO.CREATE);

		if (!exist)
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.idnotexist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "search");
			return mapping.getInputForward();
		} else
		{
			CardLimitsDto objCardLimitsDto = objManager.getCreate(request.getParameter("cardNumber"));
			try
			{
				//CardholderLimitAdjustment cardLimitAdj =new CardholderLimitAdjustment();
				BeanUtils.copyProperties(objForm, objCardLimitsDto);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: " + e);
			}
		}
		System.out.println(form.toString());
		request.setAttribute("ACTION", "add");
		System.out.println(objForm.getCardNumber());
		System.out.println(objForm.getAmountPerTranx());
		System.out.println(objForm.getDailyLimitAmount());
		System.out.println(objForm.getDailyLimitCount());
		System.out.println(objForm.getCurrCode());
		objForm.getPreListData();
		return mapping.findForward("create");
	}
}