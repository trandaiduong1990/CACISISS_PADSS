package org.transinfo.cacis.action.settings;

import java.util.Collection;
import java.util.Date;

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
import org.transinfo.cacis.controller.settings.CurrencyRateManager;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.dto.settings.CurrencyRateDto;
import org.transinfo.cacis.formbean.settings.CurrencyRateForm;

@SuppressWarnings( { "unchecked", "deprecation" })
public class CurrencyRateDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger.getLogger(CurrencyRateDispatchAction.class);
	
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		CurrencyRateForm objForm = (CurrencyRateForm) form;
		CurrencyRateForm newObjForm = new CurrencyRateForm();
		
		try {
			BeanUtils.copyProperties(objForm, newObjForm);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}
		
		objForm.getPreListData();
		
		return mapping.findForward("listsuccess");

	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

			CurrencyRateForm objForm = (CurrencyRateForm) form;
			String currency = objForm.getCurrencyId();
			
			ActionErrors errors = null;
			
			if(currency == null || "".equals(currency)){
				errors = new ActionErrors();
				errors.add("Error", new ActionError("errors.selectcurrency"));
				saveErrors(request, errors);
				
				objForm.getPreListData();
				return mapping.findForward("listsuccess");
			}
			
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
	
			CurrencyRateDto objDto = new CurrencyRateDto();
	
			try {
	
				String issId = (String) request.getSession().getAttribute("ISSUER");
				CurrencyDto currDto = new CurrencyDto();
				currDto.setCurrencyCode(objForm.getCurrencyId());
	
				objDto.id.setIssuerId(issId);
				objDto.id.setCurrCode(currDto);
	
			} catch (Exception e) {
				logger.error(e);
				System.out.println("Error converting to form bean: " + e);
				throw new TPlusException("Could not populate the form bean: " + e);
			}
			CurrencyRateManager objManager = new CurrencyRateManager();
	
			Collection currencyRateList = objManager.getCurrencyRateList(objDto,
					pageNo);
			objForm.getPreListData();
	
			request.setAttribute("SEARCHLIST", currencyRateList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
	
			return mapping.findForward("listsuccess");
		
	}

	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CurrencyRateForm objForm = (CurrencyRateForm) form;
		objForm.getPreListData();

		request.setAttribute("ACTION", "add");
		return mapping.findForward("processsuccess");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Form Validations
		CurrencyRateForm objForm = (CurrencyRateForm) form;
		errors = objForm.validate(mapping, request);
		objForm.getPreListData();

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		CurrencyRateDto objCurrencyRateDto = new CurrencyRateDto();
		CurrencyDto objCurrencyDto = new CurrencyDto();
		try {

			objCurrencyRateDto.setRate(Float.valueOf(objForm.getRate()));
			objCurrencyRateDto.setUpdatedDate(new Date());
			objCurrencyRateDto.setUserId(objForm.getUserId());

			objCurrencyDto.setCurrencyCode(objForm.getCurrencyId());
			objCurrencyRateDto.id.setCurrCode(objCurrencyDto);
			objCurrencyRateDto.id.setIssuerId(objForm.getIssuerId());
			
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CurrencyRateDispatchAction add method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CurrencyRateDispatchAction add method "
							+ e);
		}
		// Action Execution
		CurrencyRateManager objManager = new CurrencyRateManager();
		
		boolean recExistRes = objManager.validate(objCurrencyRateDto, 0);
		String nextAttribute = "cancel";
		
		if(!recExistRes){
			
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.currencyexists"));
			saveErrors(request, errors);
			nextAttribute = "add";
			
		}else{
				
			boolean boolAdd = objManager.add(objCurrencyRateDto);
	
			//String userActivity = "";
	
			if (!boolAdd) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addfailed"));
				saveErrors(request, errors);
				nextAttribute = "add";
				//userActivity = UserActivityData.CURRENCY_RATE_ADD_FAIL;
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.addSuccess"));
				saveErrors(request, errors);
				nextAttribute = "cancel";
				//userActivity = UserActivityData.CURRENCY_RATE_ADD_SUCESS;
				
				objCurrencyDto = objManager.getCurrencyDto(objForm.getCurrencyId());
				objForm.setCurrency(objCurrencyDto);
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

		request.setAttribute("ACTION", nextAttribute);
		return mapping.findForward("processsuccess");
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		// Action execution
		CurrencyRateManager objManager = new CurrencyRateManager();
		CurrencyRateForm objForm = (CurrencyRateForm) form;

		CurrencyRateDto objDto = objManager.getCurrencyRateDto(objForm
				.getCurrencyId(), objForm.getIssuerId());

		try {
			
			CurrencyDto objCurrencyDto = objManager.getCurrencyDto(objForm.getCurrencyId());

			objForm.setRate(String.valueOf(objDto.getRate()));
			objForm.setCurrency(objCurrencyDto);
			objForm.setCurrencyId(objDto.id.getCurrCode().getCurrencyCode());

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CurrencyRateDispatchAction in change method: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CurrencyRateDispatchAction method: "
							+ e);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("processsuccess");
	}

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
		CurrencyRateForm objForm = (CurrencyRateForm) form;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		CurrencyRateManager objManager = new CurrencyRateManager();
		// Dto Creation
		CurrencyRateDto objDto = new CurrencyRateDto();
		CurrencyDto objCurrencyDto = new CurrencyDto();

		try {

			objDto = objManager.getCurrencyRateDto(objForm.getCurrencyId(),
					objForm.getIssuerId());
			objDto.setRate(Float.valueOf(objForm.getRate()));

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CurrencyRateDispatchAction in update mthod: "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CurrencyRateDispatchAction update mthod:"
							+ e);
		}
		// Action Execution
		boolean boolUpdate = objManager.update(objDto);

		//String userActivity = "";

		if (!boolUpdate) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
			//userActivity = UserActivityData.CURRENCY_RATE_UPDATE_FAIL;
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			//userActivity = UserActivityData.CURRENCY_RATE_UPDATE_SUCESS;
		}
		
		objCurrencyDto = objManager.getCurrencyDto(objForm.getCurrencyId());
		objForm.setCurrency(objCurrencyDto);
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
		return mapping.findForward("processsuccess");
	}

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
		CurrencyRateForm objForm = (CurrencyRateForm) form;
		
		CurrencyRateManager objManager = new CurrencyRateManager();
		// DTO Creation
		CurrencyRateDto objDto = new CurrencyRateDto();
		CurrencyDto objCurrencyDto =  new CurrencyDto();

		try {

			objCurrencyDto.setCurrencyCode(objForm.getCurrencyId());
			
			objDto.id.setIssuerId(objForm.getIssuerId());
			objDto.id.setCurrCode(objCurrencyDto);
			objDto.setUserId(objForm.getUserId());
			
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CurrencyRateDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CurrencyRateDispatchAction in delete method: "
							+ e);
		}
		// Action execution
		boolean boolDelete = objManager.delete(objDto);
		String nextaction = "delete";

		//String userActivity = "";

		if (!boolDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "processsuccess";
			request.setAttribute("ACTION", "update");
			//userActivity = UserActivityData.CURRENCY_RATE_DELETE_FAIL;
			
			objCurrencyDto = objManager.getCurrencyDto(objForm.getCurrencyId());
			objForm.setCurrency(objCurrencyDto);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
			//userActivity = UserActivityData.CURRENCY_RATE_DELETE_SUCESS;
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

}
