package org.transinfo.cacis.action.key;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.key.KeyIndexManager;
import org.transinfo.cacis.dto.key.KeyIndexSearchDto;
import org.transinfo.cacis.formbean.key.KeyIndexSearchForm;

@SuppressWarnings( { "unchecked" })
public class SearchKeyIndexAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		String issuer = (String)request.getSession().getAttribute("ISSUER");
		
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
		
		// form validation
		KeyIndexSearchForm objForm = (KeyIndexSearchForm) form;
		// Dto Creation
		KeyIndexSearchDto objDto = new KeyIndexSearchDto();

		try {
			objForm.setIssuerId(issuer);
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			System.out
					.println("Error converting to form bean in SearchCardDeliverAction execute: "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in SearchCardDeliverAction execute: "
							+ e);
		}
		
		// Action Execution
		KeyIndexManager objManager = new KeyIndexManager();
		Collection keyForm = objManager.getKeyIndexList(objDto, pageNo);

		request.setAttribute("SEARCHLIST", keyForm);
		request.setAttribute("PAGENO", new Integer(pageNo).toString());

		// System.out.println("LIST SIZE"+keyForm.size());
		System.out.println("KeyIndexDispatchAction: get() successful");

		objForm.getPreListData();
		return mapping.findForward("success");

	}
}
