package org.transinfo.cacis.action.disputemanagement;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.controller.disputemanagement.CTFProcessManager;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.disputemanagement.Base2TranxCodeDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCaseEventDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCleaningMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeClearanceCTFDetails;
import org.transinfo.cacis.dto.disputemanagement.DisputeEventsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeMasterDto;
import org.transinfo.cacis.formbean.disputemanagement.IncomingCTFProcessForm;
import org.transinfo.cacis.util.StringUtil;

import vn.com.tivn.ctf.CTFMsg;
import vn.com.tivn.ctf.TC;

@SuppressWarnings( { "deprecation", "unchecked" })
public class IncomingCTFProcessDispatchAction extends BaseDispatchAction {

	InputStream in = null;
	String CTFInFileName = "";

	public ActionForward incomingCTFFileProcess(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws TPlusException, Exception {

		IncomingCTFProcessForm objForm = (IncomingCTFProcessForm) form;

		ActionErrors errors = null;
		errors = objForm.validate(mapping, request);

		if (errors != null && !errors.isEmpty()) {
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		FormFile file = objForm.getFile();

		boolean isWrongType = false;
		String fileName = file.getFileName();
		String fileType[] = fileName.split("\\.");

		if (fileType == null) {
			isWrongType = true;
		} else {
			if (fileType.length != 2) {
				isWrongType = true;
			} else {
				String fileExtension = fileType[1];
				if (!"CTF".equalsIgnoreCase(fileExtension)) {
					isWrongType = true;
				}
			}
		}

		if (isWrongType) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("errors.wrongctffileformat"));
			saveErrors(request, errors);
			return mapping.findForward("success");
		}
		
		CTFProcessManager objCtfProcessManager = new CTFProcessManager();

		byte[] fileData = file.getFileData();
		
		in = new ByteArrayInputStream(fileData);
		this.CTFInFileName = fileName;

		try {

			String usageCode = "";
			String cardNo = "";
			String tranxCode = "";
			String arn = "";
			String remarks = "";
			String acquirerID = "";
			String purchaseDate = "";
			String athorizationCode = "";
			String motoECIID = "";
			String isDispute = "Y";
			String status = "1";
			String merchantName = "";
			String merchantCity = "";
			String merchantCountry = "";
			String posTerminalCap = "";
			String intlFeeIndex = "";
			Date updatedDate = new Date();
			String updatedBy = (String) request.getSession(false).getAttribute("USERID");

			//String desAmt = "";
			//String desCurrency = "";

			String sourceAmt = "";
			String sourceCurrency = "";

			CTFMsg amsg = new CTFMsg();

			Enumeration drafts = amsg.getDraftData0(in);
			//Enumeration doc = amsg.getDocumentRequests(in);
			//Enumeration fee = amsg.getFeeCollectionRequest(in);
			//Enumeration misc = amsg.getMisc(in);

			//ArrayList DraftData = new ArrayList();
			
			DisputeCleaningMasterDto objDisputeCleaningMasterDto = null;
			DisputeClearanceCTFDetails objDisputeClearanceCTFDetails = null;

			while (drafts.hasMoreElements()) {

				TC aTC = (TC) drafts.nextElement();
				
				boolean isTranxLogUpdate = false;

				cardNo = aTC.get("AccNo");
				usageCode = aTC.get("UsageCode");
				tranxCode = aTC.get("TranxCode");
				arn = aTC.get("ARN");
				acquirerID = aTC.get("AcqBusinessID");
				purchaseDate = aTC.get("PurchaseDate");
				athorizationCode = aTC.get("AuthCode");
				motoECIID = aTC.get("POSEntryMode");
				merchantName = aTC.get("MerchantName");
				merchantCity = aTC.get("MerchantCity");
				merchantCountry = aTC.get("MerchantCountryCode");
				posTerminalCap = aTC.get("POSTerminalCapability");
				intlFeeIndex = aTC.get("InternationalFeeIndicator");
				updatedDate = new Date();

				//desAmt = aTC.get("DestinationAmt");
				//desCurrency = aTC.get("DestinationCurrCode");

				sourceAmt = aTC.get("SourceAmt");
				sourceCurrency = aTC.get("SourceCurrCode");
				
				// values assign to DisputeCleaningMasterDto
				objDisputeCleaningMasterDto = new DisputeCleaningMasterDto();
				objDisputeCleaningMasterDto.setArn(arn);
				
				Base2TranxCodeDto objBase2TranxCodeDto = new Base2TranxCodeDto();
				objBase2TranxCodeDto.setTranxCode(tranxCode);
				objDisputeCleaningMasterDto.setTranxCode(objBase2TranxCodeDto);
				
				objDisputeCleaningMasterDto.setAcquirerID(acquirerID);
				objDisputeCleaningMasterDto.setPurchaseDate(purchaseDate);
				objDisputeCleaningMasterDto.setCtfAmt(sourceAmt);
				objDisputeCleaningMasterDto.setCtfCurrency(sourceCurrency);
				objDisputeCleaningMasterDto.setAthorizationCode(athorizationCode);
				objDisputeCleaningMasterDto.setMotoECIID(motoECIID);
				objDisputeCleaningMasterDto.setUpdatedBy(updatedBy);
				objDisputeCleaningMasterDto.setMerchantName(merchantName);
				objDisputeCleaningMasterDto.setMerchantCity(merchantCity);
				objDisputeCleaningMasterDto.setMerchantCountry(merchantCountry);
				objDisputeCleaningMasterDto.setPosTerminalCap(posTerminalCap);
				objDisputeCleaningMasterDto.setIntlFeeIndex(intlFeeIndex);
				objDisputeCleaningMasterDto.setUsageCode(usageCode);
				
				// values assign to objDisputeClearanceCTFDetails
				objDisputeClearanceCTFDetails = new DisputeClearanceCTFDetails();
				
				// assign the DisputeClearanceCTFDetails object to DisputeCleaningMasterDto
				objDisputeCleaningMasterDto.setDisputeCTFDetails(objDisputeClearanceCTFDetails);

				// assign the objDisputeCleaningMasterDto object to DisputeClearanceCTFDetails
				objDisputeClearanceCTFDetails.setIncomingMaster(objDisputeCleaningMasterDto);

				if("05".equals(tranxCode) || "07".equals(tranxCode)){
					if ("0".equals(usageCode)) {
						// validation against TRANXLOG table
						String[] traxnCodes = {"VOID", "REVERSAL"};
						
						TransactionLogDto objTransactionLogDto = objCtfProcessManager.getTransactionDto(cardNo, athorizationCode, traxnCodes);
						if(objTransactionLogDto == null){
							remarks = ICacisiss.IDisputeManagement.DIS_INCOME_MASTER_NO_TRANX;
						}else{
							objDisputeCleaningMasterDto.setTranxLog(objTransactionLogDto);
							
							double tranxAmt = Double.valueOf(objTransactionLogDto.getAmount());
							String tranxCurrCode = objTransactionLogDto.getCurrencyCode();
							String resCode = objTransactionLogDto.getResponseCode();
							String deleted = objTransactionLogDto.getDeleted();
							
							// validate for response code
							if(ICacisiss.IDisputeManagement.DIS_VALIDATE_RESCODE.equals(resCode)){
								// validate for amount
								if(tranxAmt == StringUtil.getDoubleAmt(sourceAmt)){
									// validate for currency code
									if(tranxCurrCode.equalsIgnoreCase(sourceCurrency)){
										// validate for DELETED
										if("N".equalsIgnoreCase(deleted)){
											// update the TranxLog table
											isTranxLogUpdate = true;
											isDispute = "N";
											status = "0";
										}else{
											remarks = ICacisiss.IDisputeManagement.DIS_INCOME_MASTER_DELETED;
										}
										
									}else{
										remarks = ICacisiss.IDisputeManagement.DIS_INCOME_MASTER_WRONG_CURRCODE;
									}
									
								}else{
									remarks = ICacisiss.IDisputeManagement.DIS_INCOME_MASTER_WRONG_AMT;
								}
								
							}else{
								remarks = ICacisiss.IDisputeManagement.DIS_INCOME_MASTER_WRONG_RESCODE;
							}
							
						}
	
					} else if ("1".equals(usageCode)) {
						// validation against DISPUTE_MASTER table
						DisputeMasterDto objDisputeMasterDto = objCtfProcessManager.getDisputeMasterDto(arn);
						
						if(objDisputeMasterDto != null){
							remarks = ICacisiss.IDisputeManagement.DIS_INCOME_MASTER_RESPONSE_RECEIVED;
							// update the DISPUTE_MASTER and insert into DISPUTE_CASE_EVENT
							
							DisputeEventsDto objDisputeEventsDto = new DisputeEventsDto();
							objDisputeEventsDto.setEventId("3");
							
							DisputeCaseEventDto objDisputeCaseEventDto = new DisputeCaseEventDto();
							objDisputeCaseEventDto.setDisputEevent(objDisputeEventsDto);
							objDisputeCaseEventDto.setDisputeMaster(objDisputeMasterDto);
							objDisputeCaseEventDto.setDateTime(new Date());
							objDisputeCaseEventDto.setRemarks(remarks);
							
							boolean upRes = objCtfProcessManager.updateDisputeResponse(objDisputeCaseEventDto, updatedBy);
							if (!upRes) {
								throw new TPlusException("Exception on saving object " + cardNo + " " + athorizationCode + " " + objDisputeMasterDto.getDisputeCaseNo());
							}
						}else{
							remarks = ICacisiss.IDisputeManagement.DIS_INCOME_MASTER_RESPONSE_NO_DISPUTE;
						}
	
					}
				}
				
				objDisputeCleaningMasterDto.setIsDispute(isDispute);
				objDisputeCleaningMasterDto.setUpdatedDate(updatedDate);
				objDisputeCleaningMasterDto.setStatus(status);
				objDisputeCleaningMasterDto.setRemarks(remarks);
				
				boolean insertRes = objCtfProcessManager.insertCTFIncomingMaster(objDisputeCleaningMasterDto, isTranxLogUpdate);
				if (!insertRes) {
					throw new TPlusException("Exception on saving object " + cardNo + " " + athorizationCode);
				}

			}

		} catch (Exception exp) {
			throw new TPlusException("Exception on extractContents method "
					+ exp);
		}

		// Success
		errors = new ActionErrors();
		errors.add("Error", new ActionError("error.ctfimportsuccess"));
		saveErrors(request, errors);
		
		saveToken(request);
		return mapping.findForward("success");
	}

}
