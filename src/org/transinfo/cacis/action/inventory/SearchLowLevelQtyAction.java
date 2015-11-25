package org.transinfo.cacis.action.inventory;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseAction;
import org.transinfo.cacis.controller.collectionmanagement.CollectionAgentManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyFeeSetupManager;
import org.transinfo.cacis.controller.collectionmanagement.DelinquencyNotificationSetupManager;
import org.transinfo.cacis.controller.inventory.InventoryManagementManager;
import org.transinfo.cacis.controller.inventory.InventoryMasterManager;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupSearchDto;
import org.transinfo.cacis.dto.inventory.InventoryManagementDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupSearchForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSearchForm;
import org.transinfo.cacis.formbean.inventory.OrderListSearchForm;

public class SearchLowLevelQtyAction extends BaseAction {
	private Logger logger = Logger
			.getLogger(SearchLowLevelQtyAction.class);

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, TPlusException, Exception {

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


			// Action Execution
			InventoryMasterManager objManager = new InventoryMasterManager();
			Collection orderList = objManager.searchLowLevelQty(pageNo);

			// Success
			request.setAttribute("SEARCHLIST", orderList);
			request.setAttribute("PAGENO", new Integer(pageNo).toString());
			return mapping.findForward("success");
		}

}
