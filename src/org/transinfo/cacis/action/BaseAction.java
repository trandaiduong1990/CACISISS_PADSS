package org.transinfo.cacis.action;

/**
 * Insert the type's description here.
 * Creation date: (8/29/02 12:02:18 PM)
 * @author: Hu Ji Rong
 */
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.controller.log.ErrorlogManager;
import org.transinfo.cacis.controller.log.UserActivitiesManager;
import org.transinfo.cacis.dto.log.ErrorlogDto;
import org.transinfo.cacis.dto.log.UserActivitiesDto;

//import framework.logging.*;

/**
 * An abstract Action class that all store front action classes should extend.
 */

abstract public class BaseAction extends Action {

	private Logger logger = Logger.getLogger(BaseAction.class);

	/**
	 * The actual do work method that must be overridden by the subclasses.
	 */
	abstract public ActionForward executeAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			TPlusException, Exception;

	/**
	 * Retrieve an object from the application scope by its name. This is a
	 * convience method.
	 */
	protected Object getApplicationObject(String attrName) {
		return servlet.getServletContext().getAttribute(attrName);
	}

	protected String getLoginToken(HttpSession session) {
		return (String) session.getAttribute("LOGIN_TOKEN_KEY");
	}

	/**
	 * Retrieve a session object based on the request and the attribute name.
	 */
	protected Object getSessionObject(HttpServletRequest req, String attrName) {
		Object sessionObj = null;

		// Don't create a session if one isn't already present
		HttpSession session = req.getSession(true);
		sessionObj = session.getAttribute(attrName);
		return sessionObj;
	}

	public boolean isLoggedIn(HttpServletRequest request) {

		return true;
	}

	/**
	 * The default execute() method that all actions must implement.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("USERID");
		
		if(userId == null){
			return mapping.findForward("sessionExpired");
		}
		
		System.out.println(" DFSGdffffffffffffffffffffffffffffffffffffff");
		ActionForward forwardPage = null;
		boolean boolError = false;
		String errorMessage = "";

		try {
			// It just calls a worker method that contains the real execute
			// logic
			// Pattern: Template Method
			forwardPage = executeAction(mapping, form, request, response);
		} catch (TPlusException exp) {
			// log the application error
			logger.error(new Object(), exp);
			
			boolError = true;
			System.out.println("TPlusException" + exp.getMessage());
			errorMessage = exp.getMessage();
			forwardPage = processExceptions(request, mapping, exp);

		} catch (Throwable e) {
			// log the system error
			// LogService.logError("System Error", e);
			// Make the exception avaliable to system error page
			request.setAttribute("EXCEPTION_KEY", e);
			// "system_error" must be defined in mapping
			forwardPage = mapping.findForward("errorpage");
		} finally {
			try {

				Date date = new Date();

				String issuerID = (String) request.getSession().getAttribute(
						"ISSUER");
				String userID = (String) request.getSession().getAttribute(
						"USERID");

				String actionClass = mapping.getType();
				String errorActivity = actionClass + " - " + "Search";

				String userActivity = actionClass.substring(actionClass
						.lastIndexOf(".") + 1, actionClass.length())
						+ " - " + "Search";

				if (boolError)

				{
					System.out.println("$$$$$$$$Add To Error Log$$$$$$$$");

					// insert to ErrorLog tables
					ErrorlogDto errorDto = new ErrorlogDto();
					ErrorlogManager errorManager = new ErrorlogManager();

					errorDto.setDateTime(date);
					errorDto.setIssuerId(issuerID);
					errorDto.setUserId(userID);
					errorDto.setErrorMsg(errorMessage);
					errorDto.setActivity(errorActivity);
					errorDto.setStationIp(InetAddress.getLocalHost()
							.getHostAddress());

					errorManager.add(errorDto);
				}

				System.out.println("$$$$$$$$$$Add To Audit Log$$$$$$$$$$");
				// insert to UserActivitiesLog tables
				UserActivitiesManager activityManager = new UserActivitiesManager();
				UserActivitiesDto activityDto = new UserActivitiesDto();

				activityDto.setDateTime(date);
				activityDto.setIssuerId(issuerID);
				activityDto.setUserId(userID);
				activityDto.setActivity(userActivity);
				activityDto.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());

				activityManager.add(activityDto);

			} catch (Exception e) {
				System.out.println("Exception in the finally block");
			}
		}

		return forwardPage;
	}

	protected void processBaseException(HttpServletRequest request,
			ActionErrors errors, Throwable throwable) {
		// handle I18N and error code
		System.out.println("processBaseException" + throwable.getMessage());
		// errors.add("Error", new
		// ActionError("error.exception",throwable.getMessage()));
		@SuppressWarnings("unused")
		HttpSession session = request.getSession();
		request.setAttribute("APPLICATIONEXCEPTION", throwable.getMessage());

	}

	@SuppressWarnings("deprecation")
	protected ActionForward processExceptions(HttpServletRequest request,
			ActionMapping mapping, Throwable throwable) {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = null;

		processBaseException(request, errors, throwable);

		// Either return to the input resource or a configured failure forward
		System.out.println("Input Path=" + mapping.getInput());
		if (mapping.findForward("errorpage") != null) {
			forward = mapping.findForward("errorpage");
		}

		// Tell the Struts framework to save the errors into the request
		saveErrors(request, errors);

		// Return the ActionForward
		return forward;
	}

	protected void removeLoginToken(HttpSession session) {
		session.removeAttribute("LOGIN_TOKEN_KEY");
	}

	protected void setLoginToken(HttpSession session, String path) {
		session.setAttribute("LOGIN_TOKEN_KEY", path);
	}
}