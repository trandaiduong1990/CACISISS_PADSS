package org.transinfo.cacis.action.collectionmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingActionManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingActionManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingActionManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupDto;
import org.transinfo.cacis.formbean.collectionmanagement.AgeingAction;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSetupForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class CollectionAgeingActionDispatchAction extends
		BaseDispatchAction {
	private Logger logger = Logger
			.getLogger(CollectionAgeingActionDispatchAction.class);

	/**
	 * this method is used to showing add new DelinquencyPolicy
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CollectionAgeingActionSetupForm objOldForm = (CollectionAgeingActionSetupForm) form;
		CollectionAgeingActionSetupForm objForm = new CollectionAgeingActionSetupForm();
		
		try {
			BeanUtils.copyProperties(objOldForm, objForm);
		} catch (Exception ex) {
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction addNew : "
							+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in CollectionAgeingDispatchAction addNew : "
							+ ex);
		}
		objOldForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));
		objOldForm.getPreListData();
		// Success
		return mapping.findForward("success");
	}

	/**
	 * this method is used for creating the
	 * CollectionAgeingDispatchAction to add
	 */
	@SuppressWarnings("deprecation")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		CollectionAgeingActionSetupForm objForm = (CollectionAgeingActionSetupForm) form;
		
		objForm.setStartDay(request.getParameter("startDay"));
		objForm.setEndDay(request.getParameter("endDay"));
		objForm.getPreListData();
		
		errors = objForm.validate(mapping, request);
		CollectionAgeingActionDto objDto = new CollectionAgeingActionDto();

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			return mapping.getInputForward();
		}
		
		CollectionAgeingActionManager objManager = new CollectionAgeingActionManager();
		// validation
		List<AgeingAction> ageingActionList = objForm.getAgeingActionList();
		boolean rowInputed = false;
		for (int i = 0; i < ageingActionList.size(); i++) {
			AgeingAction objAgeingAction = ageingActionList.get(i);
			//input day is not null
			if (objAgeingAction.getDays() != null && !objAgeingAction.getDays().equals("")) {
				rowInputed = true;
				int day = Integer.parseInt(objAgeingAction.getDays());
				int startDay = Integer.parseInt(objForm.getStartDay());
				int endDay = Integer.parseInt(objForm.getEndDay());
				//out of range
				if (day < startDay || day > endDay) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError(
							"collectionageingaction.inputDayError2"));
					saveErrors(request, errors);
					request.setAttribute("ACTION", "add");
					return mapping.getInputForward();
				}
				//override day
				for(int j=ageingActionList.size()-1;j>i;j--) {
					AgeingAction objAgeingActionCheck = ageingActionList.get(j);
					if(objAgeingActionCheck.getDays()!=null && !objAgeingActionCheck.getDays().equals("")) {
						int dayCheck = Integer.parseInt(objAgeingActionCheck.getDays());
						if(day == dayCheck) {
							errors = new ActionErrors();
							errors.add("Error", new ActionError(
									"collectionageingaction.inputDayError1"));
							saveErrors(request, errors);
							request.setAttribute("ACTION", "add");
							return mapping.getInputForward();
						}
					}
				}
				//overlap in db
				boolean checkOverlap = objManager.checkOverlap(day, objForm.getIssuerId());
				if (!checkOverlap) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError(
							"collectionageingaction.inputDayError3"));
					saveErrors(request, errors);
					request.setAttribute("ACTION", "add");
					return mapping.getInputForward();
				}
				
			} else {
				//input day is null, but others field not null
				if(rowInputed==false) {
					if((objAgeingAction.getPhone()!=null && !objAgeingAction.getPhone().equals(""))
							|| (objAgeingAction.getRemainder()!=null && !objAgeingAction.getRemainder().equals(""))
							|| (objAgeingAction.getRemainderType()!=null && !objAgeingAction.getRemainderType().equals(""))
							|| (objAgeingAction.getTempCardOff()!=null && !objAgeingAction.getTempCardOff().equals(""))
							|| (objAgeingAction.getWriteOff()!=null && !objAgeingAction.getWriteOff().equals(""))) {
						errors = new ActionErrors();
						errors.add("Error", new ActionError("collectionageingaction.inputDayError4"));
						saveErrors(request, errors);
						request.setAttribute("ACTION", "add");
						return mapping.getInputForward();
					} else {
						//all fields are null
						errors = new ActionErrors();
						errors.add("Error", new ActionError("collectionageingaction.inputDayError5"));
						saveErrors(request, errors);
						request.setAttribute("ACTION", "add");
						return mapping.getInputForward();
					}
				}
			}
		}
		
		// Action Execution
		String nextAttribute = "cancelA";
		String userId = (String) request.getSession(false).getAttribute("USERID");
		for(int i=0;i<ageingActionList.size();i++) {
			AgeingAction objAgeingAction = ageingActionList.get(i);
			if(objAgeingAction.getDays() != null && !objAgeingAction.getDays().equals("")) {
				try {
					BeanUtils.copyProperties(objDto, objForm);
					objDto.setDays(Integer.parseInt(objAgeingAction.getDays()));
					objDto.setCollectionAgeingDto(new CollectionAgeingDto(objForm.getAgeingPolicy()));
					objDto.setRemainder(objAgeingAction.getRemainder());
					objDto.setRemainderType(objAgeingAction.getRemainderType());
					objDto.setTempCardOff(objAgeingAction.getTempCardOff());
					objDto.setWriteOff(objAgeingAction.getWriteOff());
					objDto.setLastUpdatedBy(userId);
					objDto.setLastUpdatedDate(new Date());
				} catch (Exception ex) {
					logger.error(ex);
					System.out
							.println("Error converting to form bean in CollectionAgeingDispatchAction in change method: "
									+ ex.getMessage());
					throw new TPlusException(
							"Could not populate the form bean in CollectionAgeingDispatchAction method: "
									+ ex);
				}
				
				boolean boolCreated = objManager.add(objDto);
				if (!boolCreated) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addfailed"));
					saveErrors(request, errors);
					nextAttribute = "add";
				} else {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("error.addSuccess"));
					saveErrors(request, errors);
					nextAttribute = "cancelA";
				}
			}
		}
		// Success
		request.setAttribute("ACTION", nextAttribute);
		return mapping.findForward("success");
	}

	/**
	 * this method is used for showing the DelinquencyPolicyDispatchAction to
	 * update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CollectionAgeingActionManager objManager = new CollectionAgeingActionManager();
		CollectionAgeingManager objAgeingManager = new CollectionAgeingManager();
		CollectionAgeingActionSetupForm objForm = (CollectionAgeingActionSetupForm) form;

		try {
			CollectionAgeingActionDto objDto = objManager
					.getCollectionAgeingAction(request.getParameter("sno"));
			CollectionAgeingDto objCollectionAgeingDto = objAgeingManager.getCollectionAgeing(request.getParameter("ageingPolicy"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setAgeingPolicy(objCollectionAgeingDto.getAgeingPolicy());
			objForm.setStartDay(String.valueOf(objCollectionAgeingDto.getStartDays()));
			objForm.setEndDay(String.valueOf(objCollectionAgeingDto.getEndDays()));
			
			
//			List<AgeingAction> list = new ArrayList<AgeingAction>();
//			list.add(new AgeingAction(String.valueOf(objDto.getDays()),objDto.getPhone(), objDto.getRemainder(), objDto.getRemainderType(), objDto.getTempCardOff(), objDto.getWriteOff()));
//			objForm.setAgeingAction(list);
			objForm.setOriginalDay(String.valueOf(objDto.getDays()));
			objForm.getPreListData();
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgeingDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for update the
	 * CollectionAgeingDispatchAction
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
		CollectionAgeingActionSetupForm objForm = (CollectionAgeingActionSetupForm) form;
		objForm.getPreListData();
		
		// Action Execution
		CollectionAgeingActionManager objManager = new CollectionAgeingActionManager();
		CollectionAgeingActionDto objDto = new CollectionAgeingActionDto();
		
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		
		// check day input is not between start day and end day
		int day = Integer.parseInt(objForm.getDays());
		int startDay = Integer.parseInt(objForm.getStartDay());
		int endDay = Integer.parseInt(objForm.getEndDay());
		if (day < startDay || day > endDay) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("collectionageingaction.inputDayError2"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		// check day overlap
		if (!objForm.getDays().equals(objForm.getOriginalDay())) {
			boolean checkOverlap = objManager.checkOverlap(Integer.parseInt(objForm.getDays()), objForm.getIssuerId());
			if (!checkOverlap) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("collectionageingaction.inputDayError3"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "update");
				return mapping.getInputForward();
			}
		}
		
		try {
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
			objDto.setCollectionAgeingDto(new CollectionAgeingDto(objForm.getAgeingPolicy()));
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgeingDispatchAction update method:"
							+ ex);
		}

		boolean boolUpdate = objManager.update(objDto);

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
		resetToken(request);
		request.setAttribute("ACTION", "cancelU");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for delete the CardBatchDispatchAction
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
		CollectionAgeingActionSetupForm objForm = (CollectionAgeingActionSetupForm) form;

		// Dto Creation
		CollectionAgeingActionDto objDto = new CollectionAgeingActionDto();


		// Action Execution
		CollectionAgeingActionManager objManager = new CollectionAgeingActionManager();

		try {
			objDto = objManager.getCollectionAgeingAction(objForm.getSno());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgentSetupDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgentSetupDispatchAction method: "
							+ ex);
		}
		
		String nextaction = "delete";
		boolean boolDelete = objManager.delete(objDto);

		if (!boolDelete) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.deletefailed"));
			saveErrors(request, errors);
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
	
	public ActionForward getAgeingPolicy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		CollectionAgeingActionSetupForm objForm = (CollectionAgeingActionSetupForm) form;
		PrintWriter out = response.getWriter();
		String ageingPolicy = objForm.getAgeingPolicy();
		if(ageingPolicy != null && !ageingPolicy.equals("")) {
			CollectionAgeingManager objCollectionAgeingManager = new CollectionAgeingManager();
			CollectionAgeingDto objCollectionAgeingDto = objCollectionAgeingManager
					.getCollectionAgeing(ageingPolicy);
			out.println(objCollectionAgeingDto.getStartDays() +"|"+ objCollectionAgeingDto.getEndDays());
		} 
		out.flush();
		return null;
	}
	
	public ActionForward getDropdown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		CollectionAgeingActionSetupForm objForm = (CollectionAgeingActionSetupForm) form;
		objForm.getPreListData();
		
		PrintWriter out = response.getWriter();
		LinkedHashMap remainderTypeList = (LinkedHashMap) objForm.getRemainderTypeList();
		Set set = remainderTypeList.entrySet();
	    Iterator i = set.iterator();
	      // Display elements
	    String responseStr = "<option></option>";
	    while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	        responseStr += "<option value=\"" + me.getKey() + "\">" + me.getValue() + "</option>";
	    }
		out.println(responseStr);
		out.flush();
		return null;
	}
	
}
