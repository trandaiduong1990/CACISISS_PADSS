package org.transinfo.cacis.action.letters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.letters.LetterApplMapManager;
import org.transinfo.cacis.formbean.letters.LetterApplMapSetupForm;

public class LetterApplMapDispatchAction extends BaseDispatchAction {
	
	private Logger logger = Logger.getLogger(LetterApplMapDispatchAction.class);

	/**
	* this method is used for showing the LetterMappingDispatchAction to change
	*/
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		LetterApplMapManager objManager = new LetterApplMapManager();
		LetterApplMapSetupForm objForm = (LetterApplMapSetupForm) form;
		List letterApplMapList = new ArrayList<LetterApplMapSetupForm>();
		
		try {
			BeanUtils.copyProperties(objForm, objForm);
			objForm.getPreListData();
			
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in LetterMappingDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in LetterMappingDispatchAction method: "
							+ ex);
		}
		
		// Action Execution	
		letterApplMapList = objManager.getAllLetterApplMap();
				
		objForm.setLetterApplMapList(letterApplMapList);
		
		// Success
		saveToken(request);
		request.setAttribute("ACTION", "change");
		return mapping.findForward("success");
	}
	
	/**
	* this method is used for update the EMVProfileProcessDispatchAction 
	*/
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		LetterApplMapSetupForm objForm = (LetterApplMapSetupForm) form;
		List letterApplMapList = new ArrayList<LetterApplMapSetupForm>();
		
		// Action Execution
		LetterApplMapManager objManager = new LetterApplMapManager();
		String nextAttribute = "cancel";
		
		for(Iterator<LetterApplMapSetupForm> i = objForm.getLetterApplMapList().iterator(); i.hasNext();) {
			
			LetterApplMapSetupForm letterApplMapSetupForm = i.next();
			errors = new ActionErrors();
			
			if (StringUtils.isBlank(letterApplMapSetupForm.getLetterTemplate())) {
				errors.add("Error", new ActionError("error.letterapplmap.lettercode"));
			}
			
			if (StringUtils.isBlank(letterApplMapSetupForm.getStatus())) {
				errors.add("Error", new ActionError("error.letterapplmap.status"));
			}
			
			if (errors != null && !errors.isEmpty()) {
				saveErrors(request, errors);
				request.setAttribute("ACTION", "change");
				objForm.getPreListData();
				return mapping.getInputForward();
			}
			
		}
		
		letterApplMapList = objForm.getLetterApplMapList();
		boolean boolUpdate = objManager.update(letterApplMapList);
		
		if (!boolUpdate) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}
		
		// Success
		saveToken(request);
		request.setAttribute("ACTION", "change");
		return mapping.findForward("success");
	}
}
