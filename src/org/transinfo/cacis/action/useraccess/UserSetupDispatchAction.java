package org.transinfo.cacis.action.useraccess;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import org.transinfo.cacis.controller.useraccess.UserSetupManager;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.useraccess.UserSetupForm;
import org.transinfo.cacis.formbean.useraccess.UserSetupSearchForm;
import org.transinfo.cacis.util.EncryptUtility;

@SuppressWarnings("deprecation")
public class UserSetupDispatchAction extends BaseDispatchAction {

	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerid = (String) request.getSession().getAttribute("ISSUER");
		String userid = (String) request.getSession().getAttribute("USERID");

		System.out.println(" we are in DispatchAction: addNew method()");
		UserSetupManager objManager = new UserSetupManager();
		//ActionErrors errors = null;
		
//		String userType = (String)request.getParameter("userType");

		System.out.println("BaseDispatchAction");
		UserSetupForm objNewForm = new UserSetupForm();
		UserSetupForm objForm = (UserSetupForm) form;
		
		BeanUtils.copyProperties(objForm, objNewForm);
		
		objForm.setIssuerId(issuerid);
//		objForm.setUserType(userType);
		//objNewForm.setUserId(objForm.getUserId());
		System.out.println("1");
		// objNewForm.setAdminUserName((String)objManager.getUserName(objForm.getIssuerId(),
		// objForm.getUserId()));
		// objNewForm.setIssuerName((String)objManager.getIssuerName(objForm.getIssuerId()));
		//objForm.setAdminUserName(objManager.getUserName(issuerid, userid));
		System.out.println("2");
		objForm.setIssuerName(objManager.getIssuerName(issuerid));
		System.out.println("3");
		//objForm.setAdminUserId(userid);
		System.out.println("4");
		objForm.getPreListData();
		System.out.println("5");
		request.setAttribute("ACTION", "addNew");
		return mapping.findForward("change");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println(" we are in DispatchAction: add method()");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		UserSetupForm objForm = (UserSetupForm) form;
		objForm.getPreListData();
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}

		// DTO Creation
		UserMasterDto objDto = new UserMasterDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.getId().setUserId(objForm.getFrmUserId());
			objDto.getId().setIssuerId(objForm.getIssuerId());
			objDto.setLastUpdatedBy(objForm.getUserId());
			objDto.setPassword(" ");
			objDto.setStatus("C");

			if (objForm.getUserStationIp().equals("")) {
				objDto.setUserStationIp("000.000.000.000");
			}

			if (String.valueOf(objForm.getFirstTimeLogin()).equals(" ")) {
				System.out.println("Setting first time login default value.");
				objDto.setFirstTimeLogin('Y');
			}

			if (objForm.getFtlExpDate() != null
					&& !objForm.getFtlExpDate().equals("")) {
				// converting string ftlExpiryDate to Date type
				Date ftlExpiry = new SimpleDateFormat("yyyy-MM-dd")
						.parse(objForm.getFtlExpDate());
				objDto.setFtlExpiryDate(ftlExpiry);
			} else {
				GregorianCalendar gc = new GregorianCalendar();
				gc.roll(Calendar.DAY_OF_YEAR, 15);
				Date ftlExpiryDate = gc.getTime();
				objDto.setFtlExpiryDate(ftlExpiryDate);
			}

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		UserSetupManager objManager = new UserSetupManager();
		boolean boolAdd = objManager.add(objDto);

		if (!boolAdd) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.createfailed"));
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.addSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("Record Added" + boolAdd);
		// Success

		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("change");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction update method");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		UserSetupForm objForm = (UserSetupForm) form;
		objForm.getPreListData();
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// DTO Creation
		UserMasterDto objDto = new UserMasterDto();
		try {

			BeanUtils.copyProperties(objDto, objForm);
			objDto.getId().setUserId(objForm.getFrmUserId());
			objDto.getId().setIssuerId(objForm.getIssuerId());
			objDto.setPassword(objForm.getFrmPassword());
			objDto.setLastUpdatedBy(objForm.getUserId());

			// System.out.println("ftlDate from Action Update==>"+objForm.getFtlExpDate());
			// converting string ftlExpiryDate to Date type

			Date ftlExpiry = new SimpleDateFormat("yyyy-MM-dd").parse(objForm
					.getFtlExpDate());
			objDto.setFtlExpiryDate(ftlExpiry);

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		UserSetupManager objManager = new UserSetupManager();
		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			System.out.println("UserSetupDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("UserSetupDispatchAction:update() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("change");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction delete method");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		UserSetupForm objForm = (UserSetupForm) form;
		objForm.getPreListData();
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// DTO Creation
		UserMasterDto objDto = new UserMasterDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.getId().setUserId(objForm.getFrmUserId());
			objDto.getId().setIssuerId(objForm.getIssuerId());
			objDto.setPassword(objForm.getFrmPassword());
			objDto.setLastUpdatedBy(objForm.getUserId());

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution
		UserSetupManager objManager = new UserSetupManager();

		boolean boolDlete = objManager.delete(objDto);
		String nextaction = "delete";

		if (!boolDlete) {

			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
			nextaction = "success";
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deleteSuccess"));
			saveErrors(request, errors);
			return mapping.findForward(nextaction);
		}

		System.out.println("Record Delete" + boolDlete);
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward(nextaction);
	}

	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		String issuerid = (String) request.getSession().getAttribute("ISSUER");
		//String userid = (String) request.getParameter("userId");
		String userid = (String) request.getParameter("frmUserId");
		
		UserSetupManager objManager = new UserSetupManager();
		UserSetupForm objForm = (UserSetupForm) form;
		objForm.getPreListData();
		// DTO Creation
		UserMasterDto objDto = new UserMasterDto();
		// System.out.println("UserId from Change===>"+request.getParameter("userId"));
		// System.out.println("UserId from dto in Change===>"+objDto.getUserId());
		// objDto =
		// objManager.getUserMasterForm(request.getParameter("issuerId"),
		// request.getParameter("userId"));
		objDto = objManager.getUserMasterForm(issuerid, userid);
		try {
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setFrmUserId(objDto.getId().getUserId());
			objForm.setIssuerId(objDto.getId().getIssuerId());
			objForm.setFrmPassword(objDto.getPassword());
			objForm.setFtlExpDate(objDto.getFtlExpiryDate().toString());
			objForm.setAdminUserId(userid);
			objForm.setAdminUserName(objManager.getUserName(issuerid, userid));
			objForm.setIssuerName(objManager.getIssuerName(issuerid));

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Success
		//saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("change");
	}

	public ActionForward pwdUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		System.out.println("we are in dipatchAction pwdUpdate method");

		ActionErrors errors = null;

		// Token Validation
		/*
		 * if(!isTokenValid(request)) { errors = new ActionErrors();
		 * errors.add("Error", new ActionError("error.duplicate"));
		 * saveErrors(request,errors); return mapping.findForward("token"); }
		 */

		// Form Validations
		UserSetupForm objForm = (UserSetupForm) form;
		objForm.getPreListData();
		// errors = objForm.validate(mapping,request);
		
		// validations
		boolean isError = false;
		String newPW = objForm.getFrmPassword();
		String confirmPW = objForm.getConfirmPassword();
		
		errors = new ActionErrors();
		
		if(newPW == null || "".equals(newPW)){
			isError = true;
			errors.add("Error", new ActionError("error.newpwrequired"));
		}
		
		if(confirmPW == null || "".equals(confirmPW)){
			isError = true;
			errors.add("Error", new ActionError("error.confirmpwrequired"));
		}
		
		if(newPW != null && !"".equals(newPW) && confirmPW != null && !"".equals(confirmPW)){
			if(!newPW.equals(confirmPW)){
				isError = true;
				errors.add("Error", new ActionError("error.mismatch"));
			}
		}
		
		if(isError){
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("failure");
		}
		

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// DTO Creation
		UserMasterDto objDto = new UserMasterDto();
		UserSetupManager objManager = new UserSetupManager();

		objDto = objManager.getUserMasterForm(objForm.getIssuerId(), objForm
				.getFrmUserId());

		try {

			// System.out.println("SearchUserId in Activation==>"+objForm.getFrmUserId());

			if (objForm.getFrmPassword() != null
					&& objForm.getFrmPassword().equals(
							objForm.getConfirmPassword())) {
				objDto.getId().setUserId(objForm.getFrmUserId());
				objDto.getId().setIssuerId(objForm.getIssuerId());
				objDto.setPassword(EncryptUtility.encrPassword(objForm
						.getFrmPassword()));
				objDto.setStatus("A");
				objDto.setPwdModifiedOn(new Date());
				objDto.setLoginFailCount(0);
				objDto.setFirstTimeLogin('Y');
				objDto.setLastUpdatedBy(objForm.getUserId());

				// Date calculation
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(new Date());
				gc.roll(Calendar.DAY_OF_YEAR, 15);
				Date dt = gc.getTime();

				objDto.setFtlExpiryDate(dt);

			} else {
				System.out.println("Password mismatch with confirm password");
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.mismatch"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.findForward("failure");
			}

		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw new TPlusException("Could not populate the form bean: " + e);
		}

		// Action Execution

		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			System.out.println("UserSetupDispatchAction: update record fail");
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			request.setAttribute("ACTION", "update");
			saveErrors(request, errors);
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		System.out.println("UserSetupDispatchAction:pwdUpdate() successful");
		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");
		return mapping.findForward("activated");
	}

	public ActionForward setRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserSetupForm ajaxForm = (UserSetupForm)form;
	    response.setContentType("text/text;charset=utf-8");
	    response.setHeader("cache-control", "no-cache");
	    PrintWriter out = response.getWriter();
	    ajaxForm.getPreListData();
	    StringBuffer sb = new StringBuffer();
	    sb.append("<select name=\"roleId\">");
	    sb.append("<option value=\"\">");
	    HashMap roleList = (HashMap) ajaxForm.getRoleList();
	    Set set = roleList.entrySet();
	    Iterator iterator = set.iterator();
	    while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         sb.append("<option value=\""+mentry.getKey() + "\">" + mentry.getValue() + "</option>");
	    }
	    sb.append("</select>");
	    out.println(sb.toString());
	    out.flush();
	    return null;
	}

}
