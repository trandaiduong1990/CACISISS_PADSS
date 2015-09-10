package org.transinfo.cacis.action.disputemanagement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.controller.disputemanagement.DisputeManagementManager;
import org.transinfo.cacis.dto.disputemanagement.DisputeCleaningMasterDto;
import org.transinfo.cacis.formbean.disputemanagement.DisputeCleaningMasterForm;
import org.transinfo.cacis.formbean.disputemanagement.DisputeNotReconsiledTranxSetupForm;
import org.transinfo.cacis.util.AdminParamsLoad;

@SuppressWarnings("unchecked")
public class DisputeNotReconsiledTransactionDispatchAction extends BaseDispatchAction {

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws TPlusException, Exception {

		DisputeNotReconsiledTranxSetupForm objForm = (DisputeNotReconsiledTranxSetupForm) form;

		// Action execution
		DisputeManagementManager objDisputeManagementManager = new DisputeManagementManager();

		// object of transaction form
		DisputeCleaningMasterForm objDisputeCleaningMasterForm = new DisputeCleaningMasterForm();

		try {

			String settlementId = request.getParameter("settlementId");
			DisputeCleaningMasterDto objDisputeCleaningMasterDto = objDisputeManagementManager.getDisputeCleaningMasterDto(settlementId);

			BeanUtils.copyProperties(objDisputeCleaningMasterForm, objDisputeCleaningMasterDto);
			objForm.setDisputeCleaningMasterForm(objDisputeCleaningMasterForm);

			List disList = objDisputeManagementManager.getDisputeMasterList(settlementId);

			if(disList != null && disList.size() > 0){
				request.setAttribute("RELATEDLIST", disList);
				objForm.setChargeBackButton("N");
			}

			List eventList = objDisputeManagementManager.getDisputeCaseEventsListByARD(objDisputeCleaningMasterDto.getArn());

			if(disList != null && disList.size() > 0){
				request.setAttribute("EVENTLIST", eventList);
				objForm.setChargeRevrseBackButton("Y");
			}

			objForm.setDisMngAltBuff(AdminParamsLoad.disMngExpAltBuff);

		} catch (Exception e) {
			throw new TPlusException("In DisputeNotReconsiledTransactionDispatchAction view method: "
					+ e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}

}
