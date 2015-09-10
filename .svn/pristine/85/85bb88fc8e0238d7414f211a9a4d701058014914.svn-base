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
import org.transinfo.cacis.controller.settings.CardTypeManager;
import org.transinfo.cacis.dto.settings.CardTypeDto;
import org.transinfo.cacis.formbean.settings.CardTypeSetupForm;

@SuppressWarnings("deprecation")
public class CardTypeDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(CardTypeDispatchAction.class);

	/*
	 * this method is used for gettting the PreList according to issuer
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardTypeSetupForm objOldForm = (CardTypeSetupForm) form;
		CardTypeSetupForm objForm = new CardTypeSetupForm();
		try {
			BeanUtils.copyProperties(objOldForm, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		return mapping.findForward("success");

	}

	/*
	 * this method is used for adding the CardTypeDetails
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
		CardTypeSetupForm objForm = (CardTypeSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		CardTypeDto objDto = new CardTypeDto();
		try {

			BeanUtils.copyProperties(objDto, objForm);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardTypeDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardTypeDispatchAction add method "
							+ e);
		}
		// Action Execution
		CardTypeManager objManager = new CardTypeManager();
		
		boolean recExistRes = objManager.validate(objDto, 0);
		
		if(!recExistRes){
			
			request.setAttribute("ACTION", "add");
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardtypeexists"));
			saveErrors(request, errors);
			
		}else{
		
			boolean boolAdd = objManager.add(objDto);
	
			//String userActivity = "";
	
			if (!boolAdd) {
				request.setAttribute("ACTION", "add");
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
				//userActivity = UserActivityData.CARD_TYPE_ADD_FAIL;
			} else {
				request.setAttribute("ACTION", "cancel");
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				//userActivity = UserActivityData.CARD_TYPE_ADD_SUCESS;
			}
		
		}

		/*// user activities record into database
		UserActivitiesDto objUserActivitiesDto = new UserActivitiesDto();

		objUserActivitiesDto.setDateTime(new Date());
		objUserActivitiesDto.setIssuerId((String) request.getSession()
				.getAttribute("ISSUER"));
		objUserActivitiesDto.setUserId((String) request.getSession()
				.getAttribute("USERID"));
		objUserActivitiesDto.setActivity(userActivity);
		objUserActivitiesDto.setStationIp(InetAddress.getLocalHost()
				.getHostAddress());

		UserActivitiesManager activityManager = new UserActivitiesManager();
		activityManager.add(objUserActivitiesDto);*/

		// end
		// Success
		// saveToken(request);
		return mapping.findForward("success");
	}

	/*
	 * this method is used for updating the CardTypeDetails
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
		CardTypeSetupForm objForm = (CardTypeSetupForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// Dto Creation
		CardTypeDto objDto = new CardTypeDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardTypeDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardTypeDispatchAction update mthod:"
							+ e);
		}
		// Action Execution
		CardTypeManager objManager = new CardTypeManager();
		boolean boolUpdate = objManager.update(objDto);

		//String userActivity = "";

		if (!boolUpdate) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
			//userActivity = UserActivityData.CARD_TYPE_UPDATE_FAIL;
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			//userActivity = UserActivityData.CARD_TYPE_UPDATE_SUCESS;
		}
		// Success

		/*// user activities record into database
		UserActivitiesDto objUserActivitiesDto = new UserActivitiesDto();

		objUserActivitiesDto.setDateTime(new Date());
		objUserActivitiesDto.setIssuerId((String) request.getSession()
				.getAttribute("ISSUER"));
		objUserActivitiesDto.setUserId((String) request.getSession()
				.getAttribute("USERID"));
		objUserActivitiesDto.setActivity(userActivity);
		objUserActivitiesDto.setStationIp(InetAddress.getLocalHost()
				.getHostAddress());

		UserActivitiesManager activityManager = new UserActivitiesManager();
		activityManager.add(objUserActivitiesDto);*/

		// end

		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("success");
	}

	/*
	 * this method is used for deleting the CardTypeDetails
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

		// Form Validations
		CardTypeSetupForm objForm = (CardTypeSetupForm) form;
		// DTO Creation
		CardTypeDto objDto = new CardTypeDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardTypeDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CardTypeDispatchAction in delete method: "
							+ e);
		}
		// Action execution
		CardTypeManager objManager = new CardTypeManager();
		
		// validation for branch has active customers
		boolean hasCardProducts = objManager.hasCardProducts(objDto.getCardTypeId());
		if(hasCardProducts){
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardtypehasproducts"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
		}
		
		boolean boolDelete = objManager.delete(objDto);
		String nextaction = "delete";

		//String userActivity = "";

		if (!boolDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "success";
			request.setAttribute("ACTION", "update");
			//userActivity = UserActivityData.CARD_TYPE_DELETE_FAIL;
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
			//userActivity = UserActivityData.CARD_TYPE_DELETE_SUCESS;
		}

		// Success

		/*// user activities record into database
		UserActivitiesDto objUserActivitiesDto = new UserActivitiesDto();

		objUserActivitiesDto.setDateTime(new Date());
		objUserActivitiesDto.setIssuerId((String) request.getSession()
				.getAttribute("ISSUER"));
		objUserActivitiesDto.setUserId((String) request.getSession()
				.getAttribute("USERID"));
		objUserActivitiesDto.setActivity(userActivity);
		objUserActivitiesDto.setStationIp(InetAddress.getLocalHost()
				.getHostAddress());

		UserActivitiesManager activityManager = new UserActivitiesManager();
		activityManager.add(objUserActivitiesDto);*/

		// end

		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}

	/*
	 * this method is used for showing the CardTypeDetails to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		// Action execution
		CardTypeManager objManager = new CardTypeManager();
		CardTypeSetupForm objForm = (CardTypeSetupForm) form;
		CardTypeDto objDto = objManager.getCardTypeDto(request
				.getParameter("cardTypeId"));

		try {
			BeanUtils.copyProperties(objForm, objDto);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardTypeDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardTypeDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}
}
