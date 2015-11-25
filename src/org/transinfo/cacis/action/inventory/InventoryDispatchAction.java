package org.transinfo.cacis.action.inventory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
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
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.controller.batchprocess.CardBatchManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAccountDetailsManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingActionManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgentManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyFeeSetupManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.controller.inventory.InventoryManagementManager;
import org.transinfo.cacis.controller.inventory.InventoryMasterManager;
import org.transinfo.cacis.controller.settings.BranchManager;
import org.transinfo.cacis.controller.useraccess.UserSetupManager;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryManagementDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupSearchDto;
import org.transinfo.cacis.dto.inventory.InventoryManagementDto;
import org.transinfo.cacis.dto.inventory.InventoryMasterDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAccountDetailsSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupSearchForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSearchForm;
import org.transinfo.cacis.formbean.inventory.InventorySearchForm;
import org.transinfo.cacis.formbean.inventory.InventorySetupForm;
import org.transinfo.cacis.formbean.inventory.StockSetupForm;

public class InventoryDispatchAction extends BaseDispatchAction {
	private Logger logger = Logger
			.getLogger(InventoryDispatchAction.class);

	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {

		InventorySetupForm objForm = (InventorySetupForm) form;
		InventorySetupForm objNewForm = new InventorySetupForm();
		
		try {
			BeanUtils.copyProperties(objForm, objNewForm);
			objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error converting to form bean in SearchDelinquencyNotificationSetupAction execute method: "
							+ e.getMessage());
			throw new TPlusException(
					"Error converting to form bean in SearchDelinquencyNotificationSetupAction execute method: "
							+ e);
		}
		
		objForm.getPreListData();
		request.setAttribute("ACTION", "add");
		return mapping.findForward("success");
		}

	public ActionForward order(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		InventoryManagementManager objManager = new InventoryManagementManager();
		InventorySetupForm objForm = (InventorySetupForm) form;
		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);
		
		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "add");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		
		InventoryManagementDto objDto = new InventoryManagementDto();
		try {
			BeanUtils.copyProperties(objDto, objForm);
			Date now = new Date();
			String dateNow = new SimpleDateFormat("dd/MM/yyyy").format(now);
			objForm.setOrderdate(dateNow);
			objDto.setOrderDate(now);
			Date orderExperted = new SimpleDateFormat("dd/MM/yyyy").parse(objForm.getOrderExpectedDate());
			objDto.setOrderExpected(orderExperted);
			objDto.setOrderBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setStatus("O");
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(now);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAccountDetailsDispatchAction in change method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAccountDetailsDispatchAction method: "
							+ ex);
		}

		// Action Execution
		String nextAttribute = "cancelFromOrder";
		
		// Check InventoryManagement exist
		boolean recExistRes = objManager.validate(objDto);

		if (recExistRes) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.orderNoExist"));
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
				nextAttribute = "cancelFromOrder";
			}
		}
		// Success
		request.setAttribute("ACTION", nextAttribute);
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	public ActionForward searchOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		InventoryManagementManager objManager = new InventoryManagementManager();
		InventorySetupForm objForm = (InventorySetupForm) form;
		try {
			InventoryManagementDto objDto = objManager
					.getInventoryManagement(request.getParameter("orderNo"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
			String orderDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderDate());
			String orderExpectedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderExpected());
			objForm.setOrderdate(orderDate);
			objForm.setOrderExpectedDate(orderExpectedDate);
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
		objForm.getPreListData();
		request.setAttribute("ACTION", "update");
		return mapping.findForward("success");
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
			request.setAttribute("ACTION", "order");
			return mapping.findForward("token");
		}

		// Form Validations
		InventorySetupForm objForm = (InventorySetupForm) form;

		// Action Execution
		InventoryManagementManager objManager = new InventoryManagementManager();
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
			return mapping.getInputForward();
		}
		
		InventoryManagementDto objDto;
		try {
			objDto = objManager.getInventoryManagement(objForm.getOrderNo());
			Date orderExperted = new SimpleDateFormat("dd/MM/yyyy").parse(objForm.getOrderExpectedDate());
			objDto.setOrderExpected(orderExperted);
			objDto.setBranchId(objForm.getBranchId());
			objDto.setCardproductId(objForm.getCardproductId());
			objDto.setOrderNote(objForm.getOrderNote());
			objDto.setOrderQty(Integer.parseInt(objForm.getOrderQty()));
			objDto.setOrderBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgentSetupDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgentSetupDispatchAction update method:"
							+ ex);
		}

		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "update");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "cancelFromOrder");
		}

		// Success
		resetToken(request);
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	public ActionForward dispatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "dispatch");
			return mapping.findForward("token");
		}

		// Form Validations
		InventorySetupForm objForm = (InventorySetupForm) form;

		// Action Execution
		InventoryManagementManager objManager = new InventoryManagementManager();
		errors = objForm.validate(mapping, request);
		boolean isError=false;
		String errorStr = null;
		// Validation
		if(null==objForm.getDelieverydate() || objForm.getDelieverydate().equals("")) {
			isError = true;
			errorStr = "inventory.inputdeliverydate";
		}
		if(null==objForm.getShipBy() || objForm.getShipBy().equals("")) {
			isError = true;
			errorStr = "inventory.inputshipby";
		}
		if(null==objForm.getTrackingNo() || objForm.getTrackingNo().equals("")) {
			isError = true;
			errorStr = "inventory.inputtrackingno";
		}
		if(null!=objForm.getShipBy() && objForm.getShipBy().equals("O")) {
			if(null==objForm.getOtherShip() || objForm.getOtherShip().equals("")) {
				isError = true;
				errorStr = "inventory.inputothership";
			}
		}
		
		if (isError) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError(errorStr));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "searchDispatch");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		
		InventoryManagementDto objDto;
		try {
			objDto = objManager.getInventoryManagement(objForm.getOrderNo());
			Date deliveryDate = new SimpleDateFormat("dd/MM/yyyy").parse(objForm.getDelieverydate());
			objDto.setDeliveryDate(deliveryDate);
			objDto.setDispatchBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setDispatchDate(new Date());
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
			objDto.setStatus("D");
			objDto.setShipBy(objForm.getShipBy());
			objDto.setOtherShip(objForm.getOtherShip());
			objDto.setTrackingNo(objForm.getTrackingNo());
			objDto.setDispatchNote(objForm.getDispatchNote());
			
			//update form
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setOrderdate(new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderDate()));
			objForm.setOrderExpectedDate(new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderExpected()));
			objForm.setBatchId(objDto.getBatchId());
			objForm.setAuthorizedBy(objDto.getAuthorizedBy());
			String authorizedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getAuthorizedDate());
			objForm.setAuthorizedDate(authorizedDate);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgentSetupDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgentSetupDispatchAction update method:"
							+ ex);
		}

		boolean boolUpdate = objManager.update(objDto);

		if (!boolUpdate) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "searchDispatch");
			objForm.getPreListData();
			return mapping.findForward("success");
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancelFromDispatch");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	public ActionForward received(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "received");
			return mapping.findForward("token");
		}

		InventorySetupForm objForm = (InventorySetupForm) form;
		// Form Validations
		boolean isError=false;
		String errorStr = null;
		// Validation
		try {
			if(null==objForm.getReceivedQty() || objForm.getReceivedQty().equals("")) {
				isError = true;
				errorStr = "inventory.inputreceiveqty";
			} else {
				int receivedQty = Integer.parseInt(objForm.getReceivedQty());
				if(receivedQty<0) {
					isError = true;
					errorStr = "inventory.receiveqtyerror";
				}
			}
		} catch (Exception e) {
			isError = true;
			errorStr = "inventory.receiveqtyerror";
		}
		
		if (isError) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError(errorStr));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "searchReceived");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		
		// Action Execution
		InventoryManagementManager objManager = new InventoryManagementManager();
		InventoryManagementDto objDto;
		try {
			objDto = objManager.getInventoryManagement(objForm.getOrderNo());
			objDto.setReceivedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setReceivedDate(new Date());
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
			objDto.setStatus("R");
			objDto.setReceivedQty(Integer.parseInt(objForm.getReceivedQty()));
			objDto.setReceivedNote(objForm.getReceivedNote());
			
			//get card list with batchId
			List<CardsDto> cardsList = objManager.getCardsAvailable(objDto.getBranchId(), objDto.getBatchId());
			for(int i=0;i<cardsList.size();i++) {
				cardsList.get(i).setStatus("A");
			}
			//update cards
			objManager.updateCardList(cardsList);
			
			boolean boolUpdate = objManager.update(objDto);

			if (!boolUpdate) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updatefailed"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "searchReceived");
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updateSuccess"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "cancelFromReceived");
				objForm.setReceiveddate(new SimpleDateFormat("dd/MM/yyyy").format(objDto.getReceivedDate()));
				objForm.setReceivedBy(objDto.getReceivedBy());
			}
			
			//update form
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setOrderdate(new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderDate()));
			objForm.setOrderExpectedDate(new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderExpected()));
			objForm.setBatchId(objDto.getBatchId());
			objForm.setAuthorizedBy(objDto.getAuthorizedBy());
			String authorizedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getAuthorizedDate());
			objForm.setAuthorizedDate(authorizedDate);
			objForm.setDelieverydate(new SimpleDateFormat("dd/MM/yyyy").format(objDto.getDeliveryDate()));
			
		} catch (Exception ex) {
			logger.error(ex);
			errors = new ActionErrors();
			errors.add("Error", new ActionError("inventory.errorauthorize2"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "searchReceived");
			return mapping.findForward("success");
		}

		// Success
		resetToken(request);

		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	public ActionForward returnOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "return");
			return mapping.findForward("token");
		}

		// Form Validations
		InventorySetupForm objForm = (InventorySetupForm) form;

		// Validation
		boolean isError=false;
		String errorStr = null;
		try {
			if (null == objForm.getReturnQty() || objForm.getReturnQty().equals("")) {
				isError = true;
				errorStr = "inventory.inputreturnqty";
			} else {
				int returnQty = Integer.parseInt(objForm.getReturnQty());
				if (returnQty < 0) {
					isError = true;
					errorStr = "inventory.returnQtyError";
				}
			}
		} catch (Exception e) {
			isError = true;
			errorStr = "inventory.returnQtyError";
		}
		
		if (isError) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError(errorStr));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "searchReturn");
			objForm.getPreListData();
			return mapping.getInputForward();
		}

		// Action Execution
		InventoryManagementManager objManager = new InventoryManagementManager();
		
		
		InventoryManagementDto objDto;
		try {
			objDto = objManager.getInventoryManagement(objForm.getOrderNo());
			objDto.setReturnDate(new Date());
			objDto.setReturnNote(objForm.getReturnNote());
			objDto.setReturnBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setReturnQty(Integer.parseInt(objForm.getReturnQty()));
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
			objDto.setStatus("B");
			
			// Update Form
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
			String orderDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderDate());
			String orderExpectedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderExpected());
			String authorizedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getAuthorizedDate());
			String deliverydate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getDispatchDate());
			String receivedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getReceivedDate());
			objForm.setOrderdate(orderDate);
			objForm.setOrderExpectedDate(orderExpectedDate);
			objForm.setAuthorizedDate(authorizedDate);
			objForm.setDelieverydate(deliverydate);
			objForm.setReceiveddate(receivedDate);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgentSetupDispatchAction in update method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgentSetupDispatchAction update method:"
							+ ex);
		}

		boolean boolUpdate = objManager.update(objDto);
		String returnAction = "cancelFromReturn";
		if (!boolUpdate) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updatefailed"));
			saveErrors(request, errors);
			returnAction = "searchReturn";
		} else {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.updateSuccess"));
			saveErrors(request, errors);
			
			objForm.setReturndate(new SimpleDateFormat("dd/MM/yyyy").format(objDto.getReturnDate()));
			objForm.setReturnBy(objDto.getReturnBy());
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", returnAction);
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
	public ActionForward searchAuthorized(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		InventoryManagementManager objManager = new InventoryManagementManager();
		InventorySetupForm objForm = (InventorySetupForm) form;
		try {
			InventoryManagementDto objDto = objManager
					.getInventoryManagement(request.getParameter("orderNo"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
			String orderDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderDate());
			String orderExpectedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderExpected());
			objForm.setOrderdate(orderDate);
			objForm.setOrderExpectedDate(orderExpectedDate);
			
	//check user have authorized permission show authorized button or not
			String userId = (String) request.getSession(false).getAttribute("USERID");
			String issuerId = (String) request.getSession(false).getAttribute("ISSUER");
			UserSetupManager objUserSetupManager = new UserSetupManager();
			BranchManager objBranchManager = new BranchManager();
			UserMasterDto objUserMasterDto = objUserSetupManager.getUserMasterForm(issuerId, userId);
			
			if(null != objUserMasterDto) {
				String branchId = objUserMasterDto.getBranchId();
				objForm.setBranchId(branchId);
				if(!branchId.equals("ALL")) {
					BranchDto objBranchDto = objBranchManager.getBranchDto(branchId);
					String accessAllBranch = objBranchDto.getAccessAllBranch();
					//user has not authorized permission show authorized
					if(!branchId.equals(objDto.getBatchId()) || accessAllBranch.equals("N")) {
						objForm.getPreListData();
						request.setAttribute("ACTION", "searchReceipt");
						return mapping.findForward("success");
					}
				}
			// The case when usserId = ASPSUPERADMIN and issuerId = Issuer1
			} else {
			}
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
		objForm.getPreListData();
		request.setAttribute("ACTION", "searchAuthorized");
		return mapping.findForward("success");
	}
	
	public ActionForward searchDispatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		InventoryManagementManager objManager = new InventoryManagementManager();
		InventorySetupForm objForm = (InventorySetupForm) form;
		try {
			InventoryManagementDto objDto = objManager
					.getInventoryManagement(request.getParameter("orderNo"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
			String orderDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderDate());
			String orderExpectedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderExpected());
			String authorizedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getAuthorizedDate());
			objForm.setOrderdate(orderDate);
			objForm.setOrderExpectedDate(orderExpectedDate);
			objForm.setAuthorizedDate(authorizedDate);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction in searchDispatchs method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgeingDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		objForm.getPreListData();
		request.setAttribute("ACTION", "searchDispatch");
		return mapping.findForward("success");
	}
	
	public ActionForward searchReceived(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		InventoryManagementManager objManager = new InventoryManagementManager();
		InventorySetupForm objForm = (InventorySetupForm) form;
		try {
			InventoryManagementDto objDto = objManager
					.getInventoryManagement(request.getParameter("orderNo"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
			String orderDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderDate());
			String orderExpectedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderExpected());
			String authorizedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getAuthorizedDate());
			String deliverydate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getDispatchDate());
			objForm.setOrderdate(orderDate);
			objForm.setOrderExpectedDate(orderExpectedDate);
			objForm.setAuthorizedDate(authorizedDate);
			objForm.setDelieverydate(deliverydate);
			objForm.setReceivedQty("");
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error converting to form bean in CollectionAgeingDispatchAction in searchDispatchs method: "
							+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgeingDispatchAction method: "
							+ ex);
		}

		// Success
		saveToken(request);
		objForm.getPreListData();
		request.setAttribute("ACTION", "searchReceived");
		return mapping.findForward("success");
	}
	
	public ActionForward searchReturn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {
		
		InventoryManagementManager objManager = new InventoryManagementManager();
		InventorySetupForm objForm = (InventorySetupForm) form;
		try {
			InventoryManagementDto objDto = objManager
					.getInventoryManagement(request.getParameter("orderNo"));
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
			String orderDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderDate());
			String orderExpectedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderExpected());
			String authorizedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getAuthorizedDate());
			String deliverydate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getDispatchDate());
			String receivedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getReceivedDate());
			objForm.setOrderdate(orderDate);
			objForm.setOrderExpectedDate(orderExpectedDate);
			objForm.setAuthorizedDate(authorizedDate);
			objForm.setDelieverydate(deliverydate);
			objForm.setReceiveddate(receivedDate);
			objForm.setReceivedQty("");
			objForm.setReturnQty("");
		} catch (Exception ex) {
			logger.error(ex);
			System.out
			.println("Error converting to form bean in CollectionAgeingDispatchAction in searchDispatchs method: "
					+ ex.getMessage());
			throw new TPlusException(
					"Could not populate the form bean in CollectionAgeingDispatchAction method: "
							+ ex);
		}
		
		// Success
		saveToken(request);
		objForm.getPreListData();
		request.setAttribute("ACTION", "searchReturn");
		return mapping.findForward("success");
	}
	
	public ActionForward cancelOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {
		
		ActionErrors errors = null;
		
		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "authorized");
			return mapping.findForward("token");
		}

		InventoryManagementManager objManager = new InventoryManagementManager();
		InventorySetupForm objForm = (InventorySetupForm) form;
		InventoryManagementDto objDto;
		try {
			objDto = objManager.getInventoryManagement(objForm.getOrderNo());
			objDto.setStatus("C");
			objDto.setCanceledBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setCanceledDate(new Date());
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(new Date());
			objForm.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
			
			boolean boolUpdate = objManager.update(objDto);

			if (!boolUpdate) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updatefailed"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "searchAuthorized");
				objForm.getPreListData();
				return mapping.findForward("success");
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("error.updateSuccess"));
				saveErrors(request, errors);
			}
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
		objForm.getPreListData();
		request.setAttribute("ACTION", "cancelOrder");
		return mapping.findForward("success");
	}
	
	public ActionForward authorized(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "authorized");
			return mapping.findForward("token");
		}

		// Form Validations
		InventorySetupForm objForm = (InventorySetupForm) form;

		// Action Execution
		InventoryManagementManager objManager = new InventoryManagementManager();
		InventoryMasterManager objMasterManager = new InventoryMasterManager();
		CardBatchManager objCardBatchManager = new CardBatchManager();
		InstantCardDto objInstantCardDto = new InstantCardDto();
		InventoryManagementDto objDto;
		InventoryMasterDto objMasterDto;
		boolean execute = false;
		
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			request.setAttribute("ACTION", "searchAuthorized");
			objForm.getPreListData();
			return mapping.getInputForward();
		}
		
		
		try {
			objDto = objManager.getInventoryManagement(objForm.getOrderNo());
			//get InventoryMaster 
			String cardProductId = objDto.getCardproductId();
			objMasterDto = objMasterManager.getInventoryMaster(cardProductId);
			//Check StockAvailable 
			int stockAvailable = objMasterDto.getStockAvailable();
			int orderQTy = objDto.getOrderQty();
			int currentStock = stockAvailable - orderQTy;
			Date now = new Date();
			if(currentStock < 0) {
				//reject authorize
				errors = new ActionErrors();
				errors.add("Error", new ActionError("inventory.errorauthorize"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "searchAuthorized");
				return mapping.findForward("success");
			}
			
			//create Anonymous batch 
			objInstantCardDto.setCreatedBy((String) request.getSession(false).getAttribute("USERID"));
			objInstantCardDto.setCreatedDate(now);
			objInstantCardDto.setUpdatedDate(now);
			objInstantCardDto.setUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objInstantCardDto.setIssuerId((String) request.getSession(false).getAttribute("ISSUER"));
			String batchId = IdsGenartion.GenerateCardBatchId();
			objInstantCardDto.setBatchId(batchId);
			objInstantCardDto.setBatchName(objDto.getOrderNo());
			objInstantCardDto.setStatus("N");
			objInstantCardDto.setCardProductId(objDto.getCardproductId());
			objInstantCardDto.setBranchId(objDto.getBranchId());
			objInstantCardDto.setNoOfCard(objDto.getOrderQty());
			
			execute = objCardBatchManager.add(objInstantCardDto);
			
			//GET Card range and update Cards Table
			List<CardsDto> cardsList = objManager.getCardsAvailable(objDto.getBranchId(),null);
			List<CardsDto> cardsListUpdate = new ArrayList<CardsDto>();
			// if cardsList in Cards Table is empty
			if(cardsList.isEmpty() || cardsList.size() < orderQTy) {
				execute = false;
			}
			
			if(execute) {
				for(int i=0;i<orderQTy;i++) {
					CardsDto objCardsDto = cardsList.get(i);
					objCardsDto.setBatchId(batchId);
					objCardsDto.setStatus("C");
					cardsListUpdate.add(objCardsDto);
				}
				
				execute = objManager.updateCardList(cardsListUpdate);
			}
			
			
			//Update Authorized
			objDto.setAuthorizedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setAuthorizedDate(now);
			objDto.setStatus("A");
			objDto.setBatchId(batchId);
			objDto.setLastUpdatedBy((String) request.getSession(false).getAttribute("USERID"));
			objDto.setLastUpdatedDate(now);
			objDto.setCardrangeFrom(cardsListUpdate.get(0).getMaskedCardNo());
			objDto.setCardrangeTo(cardsListUpdate.get(orderQTy-1).getMaskedCardNo());
			
			if(execute) {
				execute = objManager.update(objDto);
			}
			//update form
			BeanUtils.copyProperties(objForm, objDto);
			objForm.setOrderdate(new SimpleDateFormat("dd/MM/yyyy").format(objDto.getOrderDate()));
			objForm.setBatchId(batchId);
			objForm.setAuthorizedBy(objDto.getAuthorizedBy());
			String authorizedDate = new SimpleDateFormat("dd/MM/yyyy").format(objDto.getAuthorizedDate());
			objForm.setAuthorizedDate(authorizedDate);
			
			//Update current stock in InventoryMaster
			objMasterDto.setStockAvailable(currentStock);
			if(execute) {
				execute = objMasterManager.update(objMasterDto);
			}
			
			if(execute) {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("inventory.successauthorize"));
				saveErrors(request, errors);
			} else {
				errors = new ActionErrors();
				errors.add("Error", new ActionError("inventory.errorauthorize2"));
				saveErrors(request, errors);
				request.setAttribute("ACTION", "searchAuthorized");
				objForm.getPreListData();
				return mapping.findForward("success");
			}
		} catch (Exception ex) {
			logger.error(ex);
			errors = new ActionErrors();
			errors.add("Error", new ActionError("inventory.errorauthorize2"));
			saveErrors(request, errors);
			request.setAttribute("ACTION", "searchAuthorized");
			objForm.getPreListData();
			return mapping.findForward("success");
		}

		// Success
		resetToken(request);
		request.setAttribute("ACTION", "cancelFromAuthorized");
		objForm.getPreListData();
		return mapping.findForward("success");
	}
	
}
