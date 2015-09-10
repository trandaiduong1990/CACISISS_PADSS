package org.transinfo.cacis.action.settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.settings.BranchManager;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.formbean.settings.BranchSetupForm;

@SuppressWarnings("deprecation")
public class BranchDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(BranchDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		BranchSetupForm objOldForm = (BranchSetupForm) form;
		BranchSetupForm objForm = new BranchSetupForm();
		// objForm.setIssuerId((String)request.getSession(false).getAttribute("ISSUER"));

		try {
			BeanUtils.copyProperties(objOldForm, objForm);
			objOldForm.getPreListData();			
		} catch (Exception e) {
			//logger.error(e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		return mapping.findForward("success");

	}

	/*
	 * this method is used for adding the BranchDetails
	 */

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		/*
		 * Token Validation if(!isTokenValid(request)) { errors = new
		 * ActionErrors(); errors.add("Error", new
		 * ActionError("error.duplicate")); saveErrors(request,errors); return
		 * mapping.findForward("token"); }
		 */

		// Form Validations
		BranchSetupForm objForm = (BranchSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		BranchDto objDto = new BranchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in BranchDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in BranchDispatchAction add method "
							+ e);
		}
		// Action Execution

		BranchManager objManager = new BranchManager();
		
		boolean recExistRes = objManager.validate(objDto, 0);
		String nextAttribute = "cancel";
		
		if(!recExistRes){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.branchexists"));
			saveErrors(request, errors);
			nextAttribute = "add";
			
		}else{
		
			boolean boolAdd = objManager.add(objDto);
	
			if (!boolAdd) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
				nextAttribute = "add";
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				nextAttribute = "cancel";
			}
		
		}
		
		// Success
		// saveToken(request);
		request.setAttribute("ACTION", nextAttribute);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for updating the BranchDetails
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// Form Validations
		BranchSetupForm objForm = (BranchSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// Dto Creation
		BranchDto objDto = new BranchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in BranchDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in BranchDispatchAction update mthod:"
							+ e);
		}
		// Action Execution
		BranchManager objManager = new BranchManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	/*
	 * this method is used for deleting the BranchDetails
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}
		
		BranchManager objManager = new BranchManager();

		// Form Validations
		BranchSetupForm objForm = (BranchSetupForm) form;
		// DTO Creation
		BranchDto objDto = new BranchDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in BranchDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in BranchDispatchAction in delete method: "
							+ e);
		}
		
		// validation for branch has active customers
		boolean hasCus = objManager.hasActiveCustomers(objDto.getBranchId());
		if(hasCus){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.branchhascustomers"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}
		
		// Action execution
		boolean boolDelete = objManager.delete(objDto);
		String nextaction = "delete";
		if (!boolDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "success";
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}

	/*
	 * this method is used for showing the BranchDetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		BranchSetupForm objForm = (BranchSetupForm) form;

		// Action execution
		BranchManager objManager = new BranchManager();
		BranchDto objDto = objManager.getBranchDto(request
				.getParameter("branchId"));

		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in BranchDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in BranchDispatchActionchange method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

}
