package org.transinfo.cacis.action.disputemanagement;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.disputemanagement.StatisticReportsManager;
import org.transinfo.cacis.dto.disputemanagement.StatisticReportsSearchDto;
import org.transinfo.cacis.formbean.disputemanagement.StatisticReportsSearchForm;

public class SearchStatisticReportsAction extends BaseDispatchAction {
	
	/*public ActionForward preList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			StatisticReportsSearchForm objForm = (StatisticReportsSearchForm) form;
			objForm.getPreListData();
		} catch (Exception exp) {
			System.out.println("Error converting to form bean: " + exp);
		}
		request.setAttribute("ACTION", "search");
		return mapping.findForward("success");
	}*/
	
	public ActionForward daily(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		StatisticReportsSearchForm objForm = (StatisticReportsSearchForm) form;
		int pageNo = 0;
		commonSearch(pageNo, objForm, request, "daily");
		request.setAttribute("reportType", "statisticreportssetup.daily");
		return mapping.findForward("success");
	}
	
	public ActionForward weekly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		StatisticReportsSearchForm objForm = (StatisticReportsSearchForm) form;
		int pageNo = 0;
		commonSearch(pageNo, objForm, request, "weekly");
		request.setAttribute("reportType", "statisticreportssetup.weekly");
		return mapping.findForward("success");
	}
	
	public ActionForward monthly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		StatisticReportsSearchForm objForm = (StatisticReportsSearchForm) form;
		int pageNo = 0;
		commonSearch(pageNo, objForm, request, "monthly");
		request.setAttribute("reportType", "statisticreportssetup.monthly");
		return mapping.findForward("success");
	}
	
	public ActionForward yearly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		StatisticReportsSearchForm objForm = (StatisticReportsSearchForm) form;
		int pageNo = 0;
		commonSearch(pageNo, objForm, request, "yearly");
		request.setAttribute("reportType", "statisticreportssetup.yearly");
		return mapping.findForward("success");
	}

	public ActionForward NEXT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		StatisticReportsSearchForm objForm = (StatisticReportsSearchForm) form;
		String reportType = (String) request.getAttribute("reportType");
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) + 1;
		}
		commonSearch(pageNo, objForm, request, reportType);
		return mapping.findForward("success");
	}

	public ActionForward PREV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		StatisticReportsSearchForm objForm = (StatisticReportsSearchForm) form;
		String reportType = (String) request.getAttribute("reportType");
		int pageNo = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt((String) request.getParameter("pageNo")) - 1;
		}
		commonSearch(pageNo, objForm, request, reportType);
		return mapping.findForward("success");
	}

	public void commonSearch(int pageNo, StatisticReportsSearchForm objForm,
			HttpServletRequest request, String reportType) throws TPlusException, Exception {
		StatisticReportsSearchDto objDto = new StatisticReportsSearchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		StatisticReportsManager objManager = new StatisticReportsManager();
		Collection StatisticReports = null;
		if (reportType.trim().equals("daily")) {
			StatisticReports = objManager.daily(objDto, pageNo);
		} else if (reportType.trim().equals("weekly")){
			StatisticReports = objManager.weekly(objDto, pageNo);
		} else if (reportType.trim().equals("monthly")){
			StatisticReports = objManager.monthly(objDto, pageNo);
		} else if (reportType.trim().equals("yearly")){
			StatisticReports = objManager.yearly(objDto, pageNo);
		} else {
			System.out.println(" ! ! ! StatisticReportsSearchAction: execute() fail ! ! ! ");
		}
		request.setAttribute("SEARCHLIST", StatisticReports);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());
		System.out.println("StatisticReportsSearchAction: execute() successful");
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		StatisticReportsSearchForm objOldForm = (StatisticReportsSearchForm) form;
		StatisticReportsSearchForm objForm = new StatisticReportsSearchForm();
		try {
			BeanUtils.copyProperties(objOldForm, objForm);
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
		}
		return mapping.findForward("menuPage");
	}
	
	public static void main(String s[]) throws Exception {
	}
	
}