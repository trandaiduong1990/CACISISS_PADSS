package org.transinfo.cacis.action.customerservice;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.customerservice.CardStatusManager;
import org.transinfo.cacis.formbean.customerservice.CardStatusForm;

@SuppressWarnings("unchecked")
public class CardStatusAction extends BaseAction {

	private Logger logger = Logger.getLogger(CardStatusAction.class);

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		try {
			/*HttpSession session = request.getSession();
			Object userId = session.getAttribute("USERID");
			
			if(userId == null){
				return mapping.findForward("sessionExpired");
			}*/

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

			String sCardNo = (String) request.getParameter("cardNo");
			String mCardNo = (String) request.getParameter("mcardNo");

			CardStatusForm objForm = (CardStatusForm) form;

			CardStatusManager objCardStatusManager = new CardStatusManager();

			// Action Execution
			Collection remarksList = objCardStatusManager.list(sCardNo, pageNo);
			objForm.setStatusList(remarksList);
			objForm.setCardNo(sCardNo);
			objForm.setMcardNo(mCardNo);

			request.setAttribute("SEARCHLIST", remarksList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());

		} catch (Exception e) {
			logger.error(e);
		}
		
		return mapping.findForward("success");

	}
}
