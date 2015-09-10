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
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.formbean.disputemanagement.Base2TranxCodeForm;
import org.transinfo.cacis.formbean.disputemanagement.DisputeTranxSetupForm;
import org.transinfo.cacis.formbean.disputemanagement.TransactionLogForm;
import org.transinfo.cacis.util.AdminParamsLoad;

@SuppressWarnings("unchecked")
public class DisputeTransactionDispatchAction_bak extends BaseDispatchAction {

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws TPlusException, Exception {

		DisputeTranxSetupForm objForm = (DisputeTranxSetupForm) form;

		// Action execution
		DisputeManagementManager objDisputeManagementManager = new DisputeManagementManager();

		// object of transaction form
		TransactionLogForm objTransactionLogForm = new TransactionLogForm();
		Base2TranxCodeForm objBase2TranxCodeForm = new Base2TranxCodeForm();
		//DisputeCleaningMasterForm objDisputeCleaningMasterForm = new DisputeCleaningMasterForm();

		try {

			String settlementId = request.getParameter("settlementId");
			//DisputeCleaningMasterDto objDisputeCleaningMasterDto = objDisputeManagementManager.getDisputeCleaningMasterDto(settlementId);
			TransactionLogDto objTransactionLogDto = objDisputeManagementManager.getTransactionDto(settlementId);

			//BeanUtils.copyProperties(objDisputeCleaningMasterForm, objDisputeCleaningMasterDto);
			//objForm.setDisputeCleaningMasterForm(objDisputeCleaningMasterForm);

			BeanUtils.copyProperties(objTransactionLogForm, objTransactionLogDto);

			try{
				String merchant = objTransactionLogDto.getMerchantName();
				if(merchant != null && !"".equals(merchant) && merchant.length() >= 40){
					objTransactionLogForm.setMerchantName(merchant.substring(0, 25));
					objTransactionLogForm.setMerchantloc(merchant.substring(25, 38));
					objTransactionLogForm.setMerchantcountry(merchant.substring(38, 40));
				}
			}catch (Exception e) {
				objTransactionLogForm.setMerchantName("");
				objTransactionLogForm.setMerchantloc("");
				objTransactionLogForm.setMerchantcountry("");
			}

			objForm.setTranxlogForm(objTransactionLogForm);

			BeanUtils.copyProperties(objBase2TranxCodeForm, objTransactionLogDto.getTranxCode());
			objForm.setTranxCodeForm(objBase2TranxCodeForm);

			List disList = objDisputeManagementManager.getDisputeMasterList(settlementId);

			if(disList != null && disList.size() > 0){
				request.setAttribute("RELATEDLIST", disList);
				objForm.setChargeBackButton("N");
			}

			objForm.setDisMngAltBuff(AdminParamsLoad.disMngExpAltBuff);

		} catch (Exception e) {
			throw new TPlusException("In DisputeTransactionDispatchAction view method: "
					+ e);
		}

		// Success
		saveToken(request);
		return mapping.findForward("success");
	}

}
