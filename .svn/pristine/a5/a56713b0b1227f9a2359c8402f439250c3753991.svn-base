package org.transinfo.cacis.action;

/**
 * The perform() method is almost a exact copy of the same method in original DispatchAction
 * only added the handling of our ApplicationException and SystemException
 * Creation date: (8/29/02 11:42:26 AM)
 * @author: Hu Ji Rong
 */
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.controller.log.ErrorlogManager;
import org.transinfo.cacis.controller.log.UserActivitiesManager;
import org.transinfo.cacis.dto.log.ErrorlogDto;
import org.transinfo.cacis.dto.log.UserActivitiesDto;

public class BaseDispatchAction extends DispatchAction {

	private Logger logger = Logger.getLogger(BaseDispatchAction.class);

	/**
	 * Retrieve an object from the application scope by its name. This is a
	 * convience method.
	 */
	protected Object getApplicationObject(String attrName) {
		return servlet.getServletContext().getAttribute(attrName);
	}

	/**
	 * Retrieve a session object based on the request and the attribute name.
	 */
	protected Object getSessionObject(HttpServletRequest req, String attrName) {
		Object sessionObj = null;
		// Don't create a session if one isn't already present
		HttpSession session = req.getSession(false);
		sessionObj = session.getAttribute(attrName);
		return sessionObj;
	}

	/**
	 * This method is a copy from DispatchAction source only Creation date:
	 * (8/29/02 3:23:20 PM)
	 * 
	 * @param:
	 * @return:
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("USERID");
		
		if(userId == null){
			return mapping.findForward("sessionExpired");
		}

		boolean boolError = false;
		String errorMessage = "";

		// Identify the request parameter containing the method name
		String parameter = mapping.getParameter();
		System.out.println("^^^^^^^^^^PARAMETER=" + parameter);
		if (parameter == null) {
			String message = messages.getMessage("dispatch.handler", mapping
					.getPath());
			servlet.log(message);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					message);
			return (null);
		}
		// Identify the method name to be dispatched to
		String name = request.getParameter(parameter);
		System.out.println("Name=" + name);
		/*
		 * String ss[]=name.split("_"); System.out.println(ss[0]); name=ss[1];
		 */
		if (name == null) {
			String message = messages.getMessage("dispatch.parameter", mapping
					.getPath(), parameter);
			servlet.log(message);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
			return (null);
		}
		System.out.println("1");
		// Identify the method object to be dispatched to
		Method method = null;
		try {
			method = getMethod(name);
		} catch (NoSuchMethodException e) {
			System.out.println("1" + e);
			String message = messages.getMessage("dispatch.method", mapping
					.getPath(), name);
			servlet.log(message);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					message);
			return (null);
		}
		System.out.println("2");
		// Dispatch to the specified method
		ActionForward forward = null;
		try {
			System.out.println("////////////////////////////////");
			Object args[] = { mapping, form, request, response };
			forward = (ActionForward) method.invoke(this, args);
		} catch (ClassCastException e) {
			String message = messages.getMessage("dispatch.return", mapping
					.getPath(), name);
			servlet.log(message);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					message);
			return (null);
		} catch (IllegalAccessException e) {
			String message = messages.getMessage("dispatch.error", mapping
					.getPath(), name);
			servlet.log(message, e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					message);
			return (null);
		} catch (InvocationTargetException e) {

			boolError = true;

			// / ------------- no changes above this line -------------------

			// changes by Hu Ji Rong to handle our ApplicationException and
			// SystemException

			if (e.getTargetException() instanceof TPlusException) {
				TPlusException exp = (TPlusException) e.getTargetException();

				// log the application error
				logger.error(new Object(), exp);
				
				errorMessage = exp.getMessage();
				forward = processExceptions(request, mapping, exp);
			} else {
				// ------------- end of change -------------------
				// retain the orginal implementation

				String message = messages.getMessage("dispatch.error", mapping
						.getPath(), name);
				servlet.log(message, e);
				response.sendError(
						HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
				errorMessage = message;
				return (null);
			}
		} finally {
			try {

				Date date = new Date();

				String issuerID = (String) request.getSession().getAttribute(
						"ISSUER");
				String userID = (String) request.getSession().getAttribute(
						"USERID");

				String actionClass = mapping.getType();

				String errorActivity = actionClass + " - " + name;

				String userActivity = actionClass.substring(actionClass
						.lastIndexOf(".") + 1, actionClass.length())
						+ " - " + name;

				if (boolError) {
					System.out.println("$$$Add To Error Log$$$$$");

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

				System.out.println("$$$$$$$$$Add To Audit Log$$$$$$$$$$");

				String actionStatus = "";
				ActionErrors errors = (ActionErrors) request
						.getAttribute(Globals.ERROR_KEY);
				if (errors != null) {
					Iterator it = errors.get("Error");
					while (it.hasNext()) {
						ActionError error = (ActionError) it.next();
						String key = error.getKey();

						if (key.contains("success") || key.contains("Success")) {
							actionStatus = " - Success";
						} else {
							actionStatus = " - Failed";
						}

					}
				}

				userActivity = userActivity + actionStatus;

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

		// Return the returned ActionForward instance
		return (forward);
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

		if (mapping.findForward("errorpage") != null) {
			System.out.println("mapping findForward");
			forward = mapping.findForward("errorpage");
		}
		saveErrors(request, errors);
		return forward;
	}
}