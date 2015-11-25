package org.transinfo.cacis.action.useraccess;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.useraccess.AssignUserManager;
import org.transinfo.cacis.controller.useraccess.UserSetupManager;
import org.transinfo.cacis.dto.useraccess.AssignUserDto;
import org.transinfo.cacis.dto.useraccess.UserSetupSearchDto;
import org.transinfo.cacis.formbean.useraccess.AssignUserForm;
import org.transinfo.cacis.formbean.useraccess.UserSetupSearchForm;

//import org.transinfo.cacis.exception.*;

public class SearchAssignUserAction extends BaseAction {

	@SuppressWarnings("unchecked")
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {
		System.out.println("***" + request.getParameter("method") + "  "
				+ (String) request.getSession().getAttribute("ISSUER")); 

		if (request.getParameter("method") != null
				&& (((String) request.getParameter("method")).equals("List") || ((String) request
						.getParameter("method")).equals("delete"))) {

			try {
				AssignUserForm objNewForm = new AssignUserForm();
				AssignUserForm objForm = (AssignUserForm) form;
				
				BeanUtils.copyProperties(objForm, objNewForm);
				objForm.setIssuerId((String) request.getSession().getAttribute(
						"ISSUER"));
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

			AssignUserForm objForm = (AssignUserForm) form;
			
			ActionErrors errors = objForm.validate(mapping, request);
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			if(objForm.getRoleId().equals("") && objForm.getUserId().equals("")) {
				errors = new ActionErrors();
				errors.add("required", new ActionMessage("assignuser.required") );
				saveErrors(request, errors);
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			if(!objForm.getLower().equals("") || !objForm.getUpper().equals("")) {
				int lower = !objForm.getLower().equals("") ?Integer.parseInt(objForm.getLower()):0;
				int upper = !objForm.getUpper().equals("") ?Integer.parseInt(objForm.getUpper()):0;
				if((!objForm.getUpper().equals("") && objForm.getLower().equals("")) || (lower >= upper)) {
					errors = new ActionErrors();
					errors.add("Compare", new ActionMessage("assignuser.compare") );
					saveErrors(request, errors);
					objForm.getPreListData();
					return mapping.getInputForward();
				}
			}
			
			AssignUserDto objDto = new AssignUserDto();
			try {
				BeanUtils.copyProperties(objDto, objForm);
			} catch (Exception e) {
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: "
						+ e);
			}
			AssignUserManager objManager = new AssignUserManager();
			Collection userForm = objManager.search(objDto, pageNo);

			request.setAttribute("SEARCHLIST", userForm);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			objForm.getPreListData();

			// System.out.println("LIST SIZE"+userForm.size());
			System.out.println("SearchAssignUserAction: execute() successful");

			return mapping.findForward("success");
		}

	}

}