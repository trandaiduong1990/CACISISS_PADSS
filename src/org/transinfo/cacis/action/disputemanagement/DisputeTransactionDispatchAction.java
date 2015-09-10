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
import org.transinfo.cacis.formbean.disputemanagement.Base2TranxCodeForm;
import org.transinfo.cacis.formbean.disputemanagement.DisputeCleaningMasterForm;
import org.transinfo.cacis.formbean.disputemanagement.DisputeTranxSetupForm;
import org.transinfo.cacis.formbean.disputemanagement.TransactionLogForm;
import org.transinfo.cacis.util.AdminParamsLoad;

@SuppressWarnings("unchecked")
public class DisputeTransactionDispatchAction extends BaseDispatchAction {

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		DisputeTranxSetupForm objForm = (DisputeTranxSetupForm) form;
		
		// Action execution
		DisputeManagementManager objDisputeManagementManager = new DisputeManagementManager();
		
		// object of transaction form
		TransactionLogForm objTransactionLogForm = new TransactionLogForm();
		Base2TranxCodeForm objBase2TranxCodeForm = new Base2TranxCodeForm();
		DisputeCleaningMasterForm objDisputeCleaningMasterForm = new DisputeCleaningMasterForm();
		
		try {

			String settlementId = request.getParameter("settlementId");
			DisputeCleaningMasterDto objDisputeCleaningMasterDto = objDisputeManagementManager.getDisputeCleaningMasterDto(settlementId);
			//TransactionLogDto objTransactionLogDto = objDisputeManagementManager.getTransactionDto(tranxId);
			
			BeanUtils.copyProperties(objDisputeCleaningMasterForm, objDisputeCleaningMasterDto);
			objForm.setDisputeCleaningMasterForm(objDisputeCleaningMasterForm);
			
			BeanUtils.copyProperties(objTransactionLogForm, objDisputeCleaningMasterDto.getTranxLog());
			objForm.setTranxlogForm(objTransactionLogForm);
			
			BeanUtils.copyProperties(objBase2TranxCodeForm, objDisputeCleaningMasterDto.getTranxCode());
			objForm.setTranxCodeForm(objBase2TranxCodeForm);
			
			String arn = objDisputeCleaningMasterDto.getArn();
			
			List disList = objDisputeManagementManager.getDisputeMasterListByARN(arn);
			
			if(disList != null && disList.size() > 0){
				request.setAttribute("RELATEDLIST", disList);
				
				int RRCount = objDisputeManagementManager.getDisputeEventsCountByEvent("5", arn);
				if(RRCount > 0){
					objForm.setRRButton("N");
				}
				
				int CBCount = objDisputeManagementManager.getDisputeEventsCountByEvent("1", arn);
				if(CBCount > 0){
					objForm.setChargeBackButton("N");
					// once CB created then can NOT raise RR
					//objForm.setRRButton("N");
				}
				
				/*DisputeMasterDto objDisputeMasterDto = null;
				for (Iterator iterator = disList.iterator(); iterator.hasNext();) {
					objDisputeMasterDto = (DisputeMasterDto) iterator.next();
					
					if("1".equals(objDisputeMasterDto.getDisputeType().getSno())){
						objForm.setRRButton("N");
					}else if("2".equals(objDisputeMasterDto.getDisputeType().getSno())){
						objForm.setChargeBackButton("N");
					}
					
				}*/
				
			}
			
			// check MCC, if 6011 no RR
			String mcc = objDisputeCleaningMasterDto.getTranxLog().getMcc();
			if("6011".equals(mcc)){
				objForm.setRRButton("N");
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
