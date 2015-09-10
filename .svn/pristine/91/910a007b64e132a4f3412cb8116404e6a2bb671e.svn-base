package org.transinfo.cacis.action.printing;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.printing.PrintManager;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;

public class SearchPrintAction extends BaseDispatchAction {

	public ActionForward retrieve(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		PrintManager objManager = new PrintManager();
		Collection Print = null;
		Print = objManager.retrieve();
		request.setAttribute("filePath", BaseDAOImpl.PRINT_DOCUMENTS_FOLDER);
		request.setAttribute("SEARCHLIST", Print);
		System.out.println("PrintSearchAction: execute() successful");
		return mapping.findForward("success");
	}

	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		PrintManager objManager = new PrintManager();
		boolean Print = objManager.print();
		return mapping.findForward("success");
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("menuPage");
	}

	public static void main(String s[]) throws Exception {
	}

}