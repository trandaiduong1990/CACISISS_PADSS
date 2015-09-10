package org.transinfo.cacis.action.collectionmanagement;

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
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyFeeSetupManager;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;
import org.transinfo.cacis.util.DateUtil;

public class DelinquencyFeeSetupDispatchAction extends BaseDispatchAction {

	private Logger logger = Logger
			.getLogger(DelinquencyFeeSetupDispatchAction.class);

	/**
	 * this method is used to showing add new DelinquencyPolicy
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		DelinquencyFeeSetupForm objOldForm = (DelinquencyFeeSetupForm) form;
		DelinquencyFeeSetupForm objForm = new DelinquencyFeeSetupForm();

		try {
			BeanUtils.copyProperties(objOldForm, objForm);

		} catch (Exception ex) {
			System.out
					.println("Error converting to form bean in DelinquencyFeeSetupDispatchAction addNew : "
							+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in DelinquencyFeeSetupDispatchAction addNew : "
							+ ex);
		}
		objOldForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));
		// Success
		objOldForm.getPreListData();
		return mapping.findForward("success");
	}

	/**
	 * this method is used for creating the DelinquencyFeeSetupDispatchAction to
	 * add
	 */
	@SuppressWarnings("deprecation")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		DelinquencyFeeSetupForm objForm = (DelinquencyFeeSetupForm) form;
		errors = objForm.validate(mapping, request);
		DelinquencyFeeSetupDto objDto = new DelinquencyFeeSetupDto();

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		DelinquencyFeeSetupManager objManager = new DelinquencyFeeSetupManager();

		// manually validation
		boolean aging = Integer.parseInt(objForm.getAgingBeginDays()) <= Integer
				.parseInt(objForm.getAgingEndDays());
		boolean overlap = objManager.checkOverlap(objForm, 0);
		boolean checkEffectDate = DateUtil.convertDateStringToDate(
				objForm.getStartEffectDate()).compareTo(
				DateUtil.convertDateStringToDate(objForm.getEndEffectDate())) < 1;
		String agingBeginEndRange = objManager.agingBeginEndRange(objForm);
		if (!aging || !overlap || !checkEffectDate) {
			errors = new ActionErrors();
			if (!aging)
				errors.add("Error", new ActionError("co.agingError"));
			else if (!overlap)
				errors.add("Error", new ActionError("co.overlapError",
						agingBeginEndRange));
			else
				errors.add("Error", new ActionError("co.dateError"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		try {
			String startEffect = objForm.getStartEffectDate();
			String endEffect = objForm.getEndEffectDate();
			objForm.setStartEffectDate(null);
			objForm.setEndEffectDate(null);
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setStatus("N");
			objDto.setStartEffectDate(DateUtil
					.convertDateStringToDate(startEffect));
			objDto.setEndEffectDate(DateUtil.convertDateStringToDate(endEffect));
			objDto.setLastUpdatedDate(new Date());
			objDto.setLastUpdatedBy(objForm.getUserId());
			objDto.setIssuerId((String) request.getSession(false).getAttribute(
					"ISSUER"));
			if (objDto.getFeeId() == null || "".equals(objDto.getFeeId())) {
				objDto.setFeeId(IdsGenartion.GenerateDelinquencyFeeId());
			}
			objForm.setStartEffectDate(startEffect);
			objForm.setEndEffectDate(endEffect);
			objForm.setPolicyName(objManager.getPolicyName(objForm));
			
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in DelinquencyFeeSetupDispatchAction create method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in DelinquencyFeeSetupDispatchAction create method "
							+ ex);
		}

		// Action Execution
		String nextAttribute = "cancel";

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
			nextAttribute = "cancel";
		}

		// Success
		objForm.getPreListData();
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

		DelinquencyFeeSetupManager objManager = new DelinquencyFeeSetupManager();
		DelinquencyFeeSetupForm objForm = (DelinquencyFeeSetupForm) form;

		try {
			DelinquencyFeeSetupDto objDto = objManager.getFeeDetail(request
					.getParameter("id"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setStartEffectDate(DateUtil.convertDateToDateString(objDto
					.getStartEffectDate()));
			objForm.setEndEffectDate(DateUtil.convertDateToDateString(objDto
					.getEndEffectDate()));
			objForm.setPolicyName(objManager.getPolicyName(objForm));
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in DelinquencyFeeSetupDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in DelinquencyFeeSetupDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		objForm.getPreListData();
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for update the DelinquencyFeeSetupDispatchAction
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
		DelinquencyFeeSetupForm objForm = (DelinquencyFeeSetupForm) form;

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}

		// Action Execution
		DelinquencyFeeSetupManager objManager = new DelinquencyFeeSetupManager();
		DelinquencyFeeSetupDto objDto = new DelinquencyFeeSetupDto();

		// manually validation
		boolean aging = Integer.parseInt(objForm.getAgingBeginDays()) <= Integer
				.parseInt(objForm.getAgingEndDays());
		boolean overlap = objManager.checkOverlap(objForm, 1);
		boolean checkEffectDate = DateUtil.convertDateStringToDate(
				objForm.getStartEffectDate()).compareTo(
				DateUtil.convertDateStringToDate(objForm.getEndEffectDate())) < 1;
		objForm.setPolicyName(objManager.getPolicyName(objForm));
		if (!aging || !overlap || !checkEffectDate) {
			errors = new ActionErrors();
			if (!aging)
				errors.add("Error", new ActionError("co.agingError"));
			else if (!overlap) {
				String agingBeginEndRange = objManager
						.agingBeginEndRange(objForm);
				errors.add("Error", new ActionError("co.overlapError",
						agingBeginEndRange));
			} else
				errors.add("Error", new ActionError("co.dateError"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		try {
			String startEffect = objForm.getStartEffectDate();
			String endEffect = objForm.getEndEffectDate();
			objForm.setStartEffectDate(null);
			objForm.setEndEffectDate(null);
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setStartEffectDate(DateUtil
					.convertDateStringToDate(startEffect));
			objDto.setEndEffectDate(DateUtil.convertDateStringToDate(endEffect));
			objDto.setLastUpdatedDate(new Date());
			objDto.setLastUpdatedBy(objForm.getUserId());
			objForm.setStartEffectDate(startEffect);
			objForm.setEndEffectDate(endEffect);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in DelinquencyFeeSetupDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in DelinquencyFeeSetupDispatchAction update method:"
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
		request.setAttribute("ACTION", "cancel");
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
		DelinquencyFeeSetupForm objForm = (DelinquencyFeeSetupForm) form;

		// Dto Creation
		DelinquencyFeeSetupDto objDto = new DelinquencyFeeSetupDto();

		try {
			String startEffect = objForm.getStartEffectDate();
			String endEffect = objForm.getEndEffectDate();
			objForm.setStartEffectDate(null);
			objForm.setEndEffectDate(null);
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setStatus("D");
			objDto.setStartEffectDate(DateUtil
					.convertDateStringToDate(startEffect));
			objDto.setEndEffectDate(DateUtil.convertDateStringToDate(endEffect));
			objDto.setLastUpdatedBy(objForm.getUserId());
			objDto.setLastUpdatedDate(new Date());
			objForm.setStartEffectDate(startEffect);
			objForm.setEndEffectDate(endEffect);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in DelinquencyFeeSetupDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in DelinquencyFeeSetupDispatchAction in delete method: "
							+ e);
		}

		// Action Execution
		DelinquencyFeeSetupManager objManager = new DelinquencyFeeSetupManager();

		String nextaction = "delete";
		boolean boolDelete = objManager.update(objDto);

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
}
