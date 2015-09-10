package org.transinfo.cacis.action.letters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.letters.DispatchLetterManager;
import org.transinfo.cacis.dto.letters.DispatchLetterSearchDto;
import org.transinfo.cacis.formbean.letters.DispatchLetterSearch;

public class DispatchLetterDispatchAction extends BaseDispatchAction {

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction update method");

		ActionErrors errors = null;
		DispatchLetterSearch objForm = (DispatchLetterSearch) form;
		DispatchLetterManager objManager = new DispatchLetterManager();
		// DTO Creation
		DispatchLetterSearchDto objDto = new DispatchLetterSearchDto();
		System.out.println("");
		
		try
		{
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e)
		{
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		boolean boolUpdate = objManager.update(objDto);
		
		if (!boolUpdate)
		{
			System.out.println("DispatchLetter DispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError(" error.updatefailed"));
			request.setAttribute("ACTION","update");
			saveErrors(request, errors);
		}
		else
		{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}
		System.out.println("DispatchLetter DispatchAction:update() successful");
		request.setAttribute("ACTION","cancel");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
}

