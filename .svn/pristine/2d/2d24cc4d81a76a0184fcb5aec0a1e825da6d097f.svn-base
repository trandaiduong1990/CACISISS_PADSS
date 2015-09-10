package org.transinfo.cacis.action.useraccess;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.controller.useraccess.AdminLoginManager;
import org.transinfo.cacis.dto.useraccess.AdminLoginDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.useraccess.AdminLoginForm;

@SuppressWarnings( { "deprecation", "unchecked" })
public class AdminLoginAction extends Action {

	private Logger logger = Logger.getLogger(AdminLoginAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		System.out.println("***" + request.getParameter("method") + "  "
				+ (String) request.getSession().getAttribute("ISSUER") + "  "
				+ (String) request.getParameter("userType"));

		AdminLoginForm objForm = (AdminLoginForm) form;
		AdminLoginDto objDto = new AdminLoginDto();
		ActionErrors errors = null;
		String zszJspRedirected = new String("login");
		HttpSession objSession = request.getSession(true);

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "login");
			return mapping.getInputForward();
		}

		try {
			BeanUtils.copyProperties(objDto, objForm);
			
			String remoteIp = InetAddress.getLocalHost().getHostAddress();
			objDto.setRemoteIp(remoteIp);
		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		AdminLoginManager objManager = new AdminLoginManager();
		String rtnMsg = objManager.validateUser(objDto);
		if (!"NOTVALIDUSER".equals(rtnMsg)) {
			UserMasterDto objUserMasterDto = objManager.getUserData();
			objSession.setAttribute("USERID", objUserMasterDto.getId()
					.getUserId());
			objSession.setAttribute("LOGON_ISSUERID", objUserMasterDto.getId()
					.getIssuerId());
			objSession.setAttribute("ISSUER", objUserMasterDto.getId()
					.getIssuerId());
			objSession.setAttribute(objUserMasterDto.getId().getUserId(),
					objUserMasterDto);
			objSession.setAttribute("LOGEDUSERBRANCH", objUserMasterDto.getBranchId());

			if (rtnMsg.equals("VALIDUSER")) {
				if (objUserMasterDto.getUserType().trim().equalsIgnoreCase(
						"ASPADMIN")
						|| objUserMasterDto.getUserType().trim()
								.equalsIgnoreCase("ASPUSER"))
					zszJspRedirected = new String("admin");
				else
					zszJspRedirected = new String("issuer");

				objUserMasterDto.setUserLogin(true);

				java.util.Map objMap = objUserMasterDto.getScreenFunctionList();
				if (objMap != null)
					System.out.println("Access Right" + objMap.size());
				else
					System.out.println("OBJMAP NULL");
				objSession.setAttribute(objUserMasterDto.getId().getUserId(),
						objUserMasterDto);
				// objForm.getPreListData();

			} else if (rtnMsg.equals("FIRSTTIMELOGIN")
					|| rtnMsg.equals("PASSWORD_EXPIRED")) {
				zszJspRedirected = new String("changepassword");
				// objSession.setAttribute("ERRORMSG", "" + loginResult);
				/*
				 * errors = new ActionErrors(); errors.add("Error", new
				 * ActionError("FIRSTTIMELOGIN")); saveErrors(request, errors);
				 */

			} else {
				System.out.println("rtnMsg=" + rtnMsg);
				errors = new ActionErrors();
				errors.add("Error", new ActionError(rtnMsg));
				saveErrors(request, errors);

			}
		} else {
			System.out.println("rtnMsg=" + rtnMsg);
			errors = new ActionErrors();
			errors.add("Error", new ActionError(rtnMsg));
			saveErrors(request, errors);
		}

		System.out.println("AdminLoginAction: execute() successful");
		System.out.println("jspRedirect=" + zszJspRedirected);

		/*
		 * errors = new ActionErrors(); errors.add("Error", new
		 * ActionError(rtnMsg)); saveErrors(request, errors);
		 */
		request.setAttribute("ACTION", "login");
		return mapping.findForward(zszJspRedirected);
	}

}