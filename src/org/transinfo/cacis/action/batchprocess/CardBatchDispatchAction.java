package org.transinfo.cacis.action.batchprocess;

import java.text.SimpleDateFormat;
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
import org.transinfo.cacis.controller.batchprocess.CardBatchManager;
import org.transinfo.cacis.dataacess.daoimpl.oracle.batchprocess.CardBatchDAOImpl;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.formbean.batchprocess.CardBatchForm;
import org.transinfo.cacis.util.DateUtil;

import antlr.StringUtils;

import com.opensymphony.oscache.util.StringUtil;

public class CardBatchDispatchAction extends BaseDispatchAction {
	private Logger logger = Logger.getLogger(CardBatchDAOImpl.class);

	/**
	 * this method is used to showing add new card batch
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardBatchForm objOldForm = (CardBatchForm) form;
		CardBatchForm objForm = new CardBatchForm();

		try {
			BeanUtils.copyProperties(objOldForm, objForm);
			objOldForm.setIssuerId((String) request.getSession(false)
					.getAttribute("ISSUER"));
			objOldForm.getPreListData();

		} catch (Exception ex) {
			System.out
					.println("Error converting to form bean in CardBatchDispatchAction addNew : "
							+ ex.getMessage());
			throw new TPlusException(
					"Error converting to form bean in CardBatchDispatchAction addNew : "
							+ ex);
		}

		// Success
		return mapping.findForward("success");
	}

	/**
	 * this method is used for creating the EMVProfileProcessDispatchAction to
	 * add
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		CardBatchForm objForm = (CardBatchForm) form;
		errors = objForm.validate(mapping, request);
		InstantCardDto objDto = new InstantCardDto();
		objForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		try {
			if (objForm.getCreatedDate().equals(""))
				objForm.setCreatedDate(null);
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setCreatedBy(objForm.getUserId());
			objDto.setCreatedDate(new Date());
			objDto.setUpdatedDate(new Date());
			objDto.setUpdatedBy(objForm.getUserId());
			objDto.setIssuerId((String) request.getSession(false).getAttribute(
					"ISSUER"));
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CardBatchDispatchAction create method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardBatchDispatchAction create method "
							+ ex);
		}

		// Action Execution
		CardBatchManager objManager = new CardBatchManager();
		String nextAttribute = "cancel";

		// generate the new batch id
		if (objDto.getBatchId() == null || "".equals(objDto.getBatchId())) {
			String cardBatchId = IdsGenartion.GenerateCardBatchId();
			objDto.setBatchId(cardBatchId);
		}

		// Check record
		boolean recExistRes = objManager.validate(objDto, 0);

		if (!recExistRes) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardBatchExist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		} else {
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
		}

		// Success
		objForm.getPreListData();
		request.setAttribute("ACTION", nextAttribute);
		request.setAttribute("CHECK", "ADD NEW");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for showing the CardBatchDispatchAction to update
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		CardBatchManager objManager = new CardBatchManager();
		CardBatchForm objForm = (CardBatchForm) form;
		objForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));

		try {
			InstantCardDto objDto = objManager.getCardBatchDetail(request
					.getParameter("id"));
			CodeMasterDto objCodeMaster = objManager.getStatusDesc(objDto
					.getStatus());
			CardProductDto objCardProduct = objManager
					.getCardProductName(objDto.getCardProductId());
			BranchDto objBranch = objManager
					.getBranchName(objDto.getBranchId());
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setStatusDesc(objCodeMaster.getCodeDesc());
			objForm.setBranchName(objBranch.getBranchName());
			objForm.setCardProductName(objCardProduct.getCardProductName());
			if (objDto.getUpdatedDate() != null)
				objForm.setCreatedDate(DateUtil.convertDateToDateString(objDto
						.getCreatedDate()));
			if (objDto.getUpdatedDate() != null)
				objForm.setUpdatedDate(DateUtil
						.convertDateToDateStringWithHyphen(objDto
								.getUpdatedDate()));
			if (objDto.getAuthorizedDate() != null)
				objForm.setAuthorizedDate(DateUtil
						.convertDateToDateStringWithHyphen(objDto
								.getAuthorizedDate()));
			objForm.getPreListData();
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CardBatchDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardBatchDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		request.setAttribute(
				"ACTION",
				(objForm.getAuthForm() != null && objForm.getAuthForm().equals(
						"auth")) ? "authorize" : "update");
		return mapping.findForward("success");
	}

	/**
	 * this method is used for update the CardBatchDispatchAction
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
		CardBatchForm objForm = (CardBatchForm) form;
		objForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		// Action Execution
		CardBatchManager objManager = new CardBatchManager();
		InstantCardDto objDto = new InstantCardDto();

		try {
			Date createdDate = DateUtil.convertDateStringToDate(objForm
					.getCreatedDate());
			objForm.setUpdatedDate(null);
			objForm.setCreatedDate(null);
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setUpdatedBy(objForm.getUserId());
			objDto.setUpdatedDate(new Date());
			objDto.setCreatedDate(createdDate);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CardBatchDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardBatchDispatchAction update method:"
							+ ex);
		}

		CodeMasterDto objCodeMaster = objManager.getStatusDesc(objDto
				.getStatus());

		// Check record
		boolean recExistRes = true;
		recExistRes = objManager.validate(objDto, 1);
		if (!recExistRes) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardBatchExist"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.setStatusDesc(objCodeMaster.getCodeDesc());
			objForm.setUpdatedBy(objDto.getUpdatedBy());
			objForm.setUpdatedDate(DateUtil
					.convertDateToDateStringWithHyphen(objDto.getUpdatedDate()));
			objForm.getPreListData();
			return mapping.getInputForward();

		} else {
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
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancel");

		objForm.setStatusDesc(objCodeMaster.getCodeDesc());
		objForm.setUpdatedBy(objDto.getUpdatedBy());
		objForm.setUpdatedDate(DateUtil
				.convertDateToDateStringWithHyphen(objDto.getUpdatedDate()));
		objForm.getPreListData();
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
		CardBatchForm objForm = (CardBatchForm) form;
		objForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));
		objForm.getPreListData();

		// Dto Creation
		InstantCardDto objDto = new InstantCardDto();

		try {
			Date createdDate = DateUtil.convertDateStringToDate(objForm
					.getCreatedDate());
			objForm.setUpdatedDate(null);
			objForm.setCreatedDate(null);
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setUpdatedBy(objForm.getUserId());
			objDto.setUpdatedDate(new Date());
			objDto.setCreatedDate(createdDate);
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in CardBatchDispatchAction in delete method "
							+ e.getMessage());
			throw new TPlusException(
					"Could not populate the form bean  in CardBatchDispatchAction in delete method: "
							+ e);
		}

		// Action Execution
		CardBatchManager objManager = new CardBatchManager();

		boolean emvProfileExits = objManager.validate(objDto, 0);

		if (emvProfileExits) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.producthascards"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.findForward("success");
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

	/**
	 * this method is used for process the CardBatchDispatchAction
	 */
	public ActionForward process(ActionMapping mapping, ActionForm form,
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
		CardBatchForm objForm = (CardBatchForm) form;
		objForm.setIssuerId((String) request.getSession(false).getAttribute(
				"ISSUER"));

		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		// Action Execution
		CardBatchManager objManager = new CardBatchManager();
		InstantCardDto objDto = new InstantCardDto();
		String authDate = objForm.getAuthorizedDate();

		try {
			Date createdDate = DateUtil.convertDateStringToDate(objForm
					.getCreatedDate());
			objForm.setUpdatedDate(null);
			objForm.setCreatedDate(null);
			objForm.setAuthorizedDate(null);
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setUpdatedBy(objForm.getUserId());
			objDto.setUpdatedDate(new Date());
			objDto.setCreatedDate(createdDate);
			objDto.setAuthorizedDate(DateUtil.convertDateStringToDateWithHyphen(authDate));
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CardBatchDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardBatchDispatchAction update method:"
							+ ex);
		}
		boolean boolUpdate = objManager.process(objDto);

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
		CodeMasterDto objCodeMaster = objManager.getStatusDesc(objDto
				.getStatus());
		CardProductDto objCardProduct = objManager.getCardProductName(objDto
				.getCardProductId());
		BranchDto objBranch = objManager.getBranchName(objDto.getBranchId());
		objForm.setBranchName(objBranch.getBranchName());
		objForm.setCardProductName(objCardProduct.getCardProductName());
		objForm.setStatusDesc(objCodeMaster.getCodeDesc());
		objForm.setUpdatedBy(objDto.getUpdatedBy());
		objForm.setUpdatedDate(DateUtil
				.convertDateToDateStringWithHyphen(objDto.getUpdatedDate()));
		objForm.setAuthorizedDate(authDate);
		;
		return mapping.findForward("success");
	}

	/**
	 * this method is used for authorize the CardBatchDispatchAction
	 */
	@SuppressWarnings("deprecation")
	public ActionForward authorize(ActionMapping mapping, ActionForm form,
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
		CardBatchForm objForm = (CardBatchForm) form;

		// Action Execution
		CardBatchManager objManager = new CardBatchManager();
		InstantCardDto objDto = new InstantCardDto();

		CodeMasterDto objCodeMaster = objManager.getStatusDesc(objForm
				.getStatus());
		CardProductDto objCardProduct = objManager.getCardProductName(objForm
				.getCardProductId());
		BranchDto objBranch = objManager.getBranchName(objForm.getBranchId());

		// check username and password
		boolean hasUName = objForm.getUserName() != null
				&& !objForm.getUserName().equals("");
		boolean hasPwd = objForm.getPwd() != null
				&& !objForm.getPwd().equals("");
		boolean userExist = objManager.validateUser(objForm);
		boolean chkAuthUser = objForm.getUserId().equals("ASPSUPERADMIN") ? true
				: !objForm.getCreatedBy().equals(objForm.getUserName());

		errors = objForm.validate(mapping, request);
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "change");
			return mapping.findForward("success");
		}
		if (!hasUName || !hasPwd || !userExist || !chkAuthUser) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError(
					!hasUName ? "cardbatcprocess.uNameRequire"
							: !hasPwd ? "cardbatcprocess.pwd"
									: !userExist ? "cardBatch.invalidUser"
											: "cardBatch.notSameUser"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "change");
			objForm.setBranchName(objBranch.getBranchName());
			objForm.setCardProductName(objCardProduct.getCardProductName());
			objForm.setStatusDesc(objCodeMaster.getCodeDesc());
			return mapping.findForward("success");
		}

		try {
			Date createdDate = DateUtil.convertDateStringToDate(objForm
					.getCreatedDate());
			objForm.setUpdatedDate(null);
			objForm.setCreatedDate(null);
			BeanUtils.copyProperties(objDto, objForm);
			objDto.setUpdatedBy(objForm.getUserId());
			objDto.setUpdatedDate(new Date());
			objDto.setCreatedDate(createdDate);
			objDto.setAuthorizedDate(new Date());
			objDto.setAuthorizedBy(objForm.getUserName());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CardBatchDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CardBatchDispatchAction update method:"
							+ ex);
		}
		boolean boolUpdate = objManager.authorize(objDto);

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

		objForm.setBranchName(objBranch.getBranchName());
		objForm.setCardProductName(objCardProduct.getCardProductName());
		objForm.setStatusDesc(objCodeMaster.getCodeDesc());
		objForm.setUpdatedBy(objDto.getUpdatedBy());
		objForm.setUpdatedDate(DateUtil
				.convertDateToDateStringWithHyphen(objDto.getUpdatedDate()));
		objForm.setAuthorizedDate(DateUtil
				.convertDateToDateStringWithHyphen(objDto.getAuthorizedDate()));
		objForm.setAuthorizedBy(objDto.getAuthorizedBy());
		return mapping.findForward("success");
	}
}
