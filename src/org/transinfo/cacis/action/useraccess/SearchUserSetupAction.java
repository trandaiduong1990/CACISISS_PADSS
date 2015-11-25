package org.transinfo.cacis.action.useraccess;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.useraccess.UserSetupManager;
import org.transinfo.cacis.dto.useraccess.UserSetupSearchDto;
import org.transinfo.cacis.formbean.useraccess.UserSetupSearchForm;

//import org.transinfo.cacis.exception.*;

public class SearchUserSetupAction extends BaseAction {

	@SuppressWarnings("unchecked")
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {
		System.out.println("***" + request.getParameter("method") + "  "
				+ (String) request.getSession().getAttribute("ISSUER") + "  "
				+ (String) request.getParameter("userType"));

		if (request.getParameter("method") != null
				&& ((String) request.getParameter("method")).equals("setRole")) {
			
			UserSetupSearchForm ajaxForm = (UserSetupSearchForm)form;
		    response.setContentType("text/text;charset=utf-8");
		    response.setHeader("cache-control", "no-cache");
		    PrintWriter out = response.getWriter();
		    ajaxForm.getPreListData();
		    StringBuffer sb = new StringBuffer();
		    sb.append("<select name=\"searchRoleId\">");
		    sb.append("<option value=\"\">");
		    HashMap roleList = (HashMap) ajaxForm.getRoleList();
		    Set set = roleList.entrySet();
		    Iterator iterator = set.iterator();
		    while(iterator.hasNext()) {
		         Map.Entry mentry = (Map.Entry)iterator.next();
		         sb.append("<option value=\""+mentry.getKey() + "\">" + mentry.getValue() + "</option>");
		    }
		    sb.append("</select>");
		    out.println(sb.toString());
		    out.flush();
		    return null;
		    
		} else if (request.getParameter("method") != null
					&& ((String) request.getParameter("method")).equals("List")) {

			try {
				UserSetupSearchForm objForm = (UserSetupSearchForm) form;
				objForm.setIssuerId((String) request.getSession().getAttribute(
						"ISSUER"));
				//objForm.setUserType((String) request.getParameter("userType"));
				objForm.setUserType("");
				System.out.println("IssuerId from Search Action==>"
						+ (String) request.getSession().getAttribute("ISSUER"));
				objForm.getPreListData();

				return mapping.findForward("success");

			} catch (Exception exp) {
				System.out.println("Error converting to form bean: " + exp);
				throw new TPlusException("Could not populate the form bean: "
						+ exp);
			}
		} else {

			int pageNo = 0;

			if (request.getParameter("mode") != null
					&& ((String) request.getParameter("mode")).equals("NEXT")) {
				if (request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt((String) request
							.getParameter("pageNo")) + 1;

				}
			}
			if (request.getParameter("mode") != null
					&& ((String) request.getParameter("mode")).equals("PREV")) {

				if (request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt((String) request
							.getParameter("pageNo")) - 1;
				}
			}

			UserSetupSearchForm objForm = (UserSetupSearchForm) form;
			UserSetupSearchDto objDto = new UserSetupSearchDto();

			try {
				System.out.println("userType from search form..."
						+ objForm.getUserType());
				System.out.println("Issuerid from form..."
						+ objForm.getIssuerId());
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: "
						+ e);
			}
			UserSetupManager objManager = new UserSetupManager();
			Collection userForm = objManager.search(objDto, pageNo);

			request.setAttribute("SEARCHLIST", userForm);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			objForm.getPreListData();

			// System.out.println("LIST SIZE"+userForm.size());
			System.out.println("SearchUserSetupAction: execute() successful");

			return mapping.findForward("success");
		}

	}
	
}