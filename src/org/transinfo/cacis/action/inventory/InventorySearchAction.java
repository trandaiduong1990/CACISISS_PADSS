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
import org.transinfo.cacis.controller.collectionmanagement.CollectionAccountDetailsManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgeingActionManager;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgentManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyFeeSetupManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.controller.inventory.InventoryMasterManager;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupSearchDto;
import org.transinfo.cacis.dto.inventory.InventoryMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAccountDetailsSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupSearchForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSearchForm;
import org.transinfo.cacis.formbean.inventory.InventorySearchForm;

public class InventorySearchAction extends BaseAction {
	private Logger logger = Logger
			.getLogger(InventorySearchAction.class);
	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {
		
			InventorySearchForm objForm = (InventorySearchForm) form;
			InventorySearchForm objNewForm = new InventorySearchForm();
			ActionErrors errors = null;
			
			if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("order"))) {
				objForm.setStatus("O");
				request.setAttribute("ACTION", "order");
			} else if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("authorized"))) {
				objForm.setStatus("O");
				request.setAttribute("ACTION", "authorized");
			} else if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("dispatch"))) {
				objForm.setStatus("A");
				request.setAttribute("ACTION", "dispatch");
			} else if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("received"))) {
				objForm.setStatus("D");
				request.setAttribute("ACTION", "received");
			} else if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("return"))) {
				objForm.setStatus("R");
				request.setAttribute("ACTION", "return");
			} else if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("searchOrder"))) {
				if(null==objForm.getOrderNo() ||objForm.getOrderNo().equals("")) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("inventory.ordernorequired"));
					saveErrors(request, errors);
					objForm.setStatus("O");
					request.setAttribute("ACTION", "order");
				} else {
					return mapping.findForward("searchOrder");
				}
			} else if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("searchAuthorized"))) {
				if(null==objForm.getOrderNo() ||objForm.getOrderNo().equals("")) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("inventory.ordernorequired"));
					saveErrors(request, errors);
					objForm.setStatus("O");
					request.setAttribute("ACTION", "authorized");
				} else {
					return mapping.findForward("searchAuthorized");
				}
			} else if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("searchDispatch"))) {
				if(null==objForm.getOrderNo() ||objForm.getOrderNo().equals("")) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("inventory.ordernorequired"));
					saveErrors(request, errors);
					objForm.setStatus("A");
					request.setAttribute("ACTION", "dispatch");
				} else {
					return mapping.findForward("searchDispatch");
				}
			} else if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("searchReceived"))) {
				if(null==objForm.getOrderNo() ||objForm.getOrderNo().equals("")) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("inventory.ordernorequired"));
					saveErrors(request, errors);
					objForm.setStatus("D");
					request.setAttribute("ACTION", "received");
				} else {
					return mapping.findForward("searchReceived");
				}
			} else if ((request.getParameter("method") != null)
					&& (((String) request.getParameter("method")).equals("searchReturn"))) {
				if(null==objForm.getOrderNo() ||objForm.getOrderNo().equals("")) {
					errors = new ActionErrors();
					errors.add("Error", new ActionError("inventory.ordernorequired"));
					saveErrors(request, errors);
					objForm.setStatus("D");
					request.setAttribute("ACTION", "return");
				} else {
					return mapping.findForward("searchReturn");
				}
			}   

			objForm.getPreListData();
			return mapping.findForward("success");
		}
}
