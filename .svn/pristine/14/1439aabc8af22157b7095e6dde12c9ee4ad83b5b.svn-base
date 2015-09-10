package org.transinfo.cacis.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.ForwardAction;

public class BaseForwardAction extends ForwardAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("USERID");
		
		if(userId == null){
			return mapping.findForward("sessionExpired");
		}
		
		return super.execute(mapping, form, request, response);
	}

}
