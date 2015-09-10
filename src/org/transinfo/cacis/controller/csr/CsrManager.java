package org.transinfo.cacis.controller.csr;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.csr.CsrDAO;
import org.transinfo.cacis.dto.csr.CallRecordLogDto;
import org.transinfo.cacis.dto.csr.CsrDto;
import org.transinfo.cacis.dto.csr.CsrSearchDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CreditSplitDto;

@SuppressWarnings("unchecked")
public class CsrManager {

	private CsrDAO objDAO;

	public CsrManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCsrDAO();

	}

	/*
	 * This method is used for getting the callCenterList
	 */
	public Collection callCenterSearch(CsrSearchDto objSearchDto)
			throws TPlusException {

		Collection searchResult = null;
		try {
			searchResult = objDAO.callCenterSearch(objSearchDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CsrManager callCenterSearch method" + e);
		}
		return searchResult;
	}

	/*
	 * This method is used for insert the data into the call_Record_Log table
	 */
	public void callRecordLog(CallRecordLogDto objCallLogDto)
			throws TPlusException {

		try {
			// setting the values to
			Date callStartTime = new Date();
			// CallRecordLogDto objCallLogDto = new CallRecordLogDto();
			// objCallLogDto.setCardNumber(objCsrDto.getCardNumber());
			// objCallLogDto.setIssuerId(objCsrDto.getIssuerId());
			//objCallLogDto.setCallTypeId(" ");
			objCallLogDto.setCallStartTime(callStartTime);
			objCallLogDto.setCallStatus(CommonCodes.CALLSTATUS_OPEN);
			// objCallLogDto.setUserId(objCsrDto.getUserId());
			objDAO.callRecordLog(objCallLogDto);

		} catch (Exception e) {
			throw new TPlusException("Error in callRecordLog  method" + e);
		}

	}

	/*
	 * this for checking the previousCalls exists for this refNo before
	 * inserting
	 */
	public CommonDataBean previousCallsCheck(String refNo)
			throws TPlusException {
		CommonDataBean objCommBean = null;
		try {
			objCommBean = objDAO.previousCallsCheck(refNo);

		} catch (Exception e) {
			throw new TPlusException("Error in previousCallsCheck  method" + e);
		}
		return objCommBean;
	}

	/*
	 * This method is used for getting the previousCallDataList
	 */
	public Collection previousCallData(CallRecordLogDto objCallRecDto)
			throws TPlusException {

		Collection searchResult = null;
		try {
			searchResult = objDAO.previousCallData(objCallRecDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CsrManager previousCallData method" + e);
		}
		return searchResult;
	}

	/*
	 * This method is used for updating the call_Record_Log table
	 */
	public boolean callRecordUpdate(CallRecordLogDto objCallRecDto)
			throws TPlusException {

		boolean callUpdate = false;
		try {
			// setting the values to
			Date callEndTime = new Date();
			objCallRecDto.setCallEndTime(callEndTime);
			callUpdate = objDAO.callRecordUpdate(objCallRecDto);
		} catch (Exception e) {
			System.out.println("Error in CsrManager callRecordUpdate method"
					+ e.getMessage());
			throw new TPlusException(
					"Error in CsrManager callRecordUpdate method" + e);
		}
		return callUpdate;
	}

	/*
	 * This method is used for getting the openCallsList
	 */
	public Collection openCalls() throws TPlusException {

		Collection openCallsResult = null;
		try {
			openCallsResult = objDAO.openCalls();

		} catch (Exception e) {
			throw new TPlusException("Error in CsrManager openCalls method" + e);
		}
		return openCallsResult;
	}

	/*
	 * This method is used for getting the Customer Summary List
	 */
	public Collection csrSummary(CsrDto objSearchDto) throws TPlusException {

		Collection searchResult = null;
		try {
			searchResult = objDAO.csrSummary(objSearchDto);

		} catch (Exception e) {
			throw new TPlusException("Error in CsrManager csrSummary method"
					+ e);
		}
		return searchResult;
	}

	/*
	 * This method is used for getting the CardActivities List
	 */
	public Collection cardActivities(CsrDto objSearchDto) throws TPlusException {

		Collection searchResult = null;
		try {
			searchResult = objDAO.cardActivities(objSearchDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CsrManager cardActivities method" + e);
		}
		return searchResult;
	}

	/*
	 * This method is used for getting the TranxHistory List
	 */
	public Collection tranxHistory(CsrDto objSearchDto) throws TPlusException {

		Collection searchResult = null;
		try {
			searchResult = objDAO.tranxHistory(objSearchDto);

		} catch (Exception e) {
			throw new TPlusException("Error in CsrManager TranxHistory method"
					+ e);
		}
		return searchResult;
	}

	/*
	 * This method is used for getting the Account Details List
	 */
	public Collection accountInfo(CsrDto objSearchDto) throws TPlusException {

		Collection searchResult = null;
		try {
			searchResult = objDAO.accountInfo(objSearchDto);

		} catch (Exception e) {
			throw new TPlusException("Error in CsrManager accountInfo method"
					+ e);
		}
		return searchResult;
	}

	/*
	 * This method is used for getting the Account Details List
	 */
	public CustomerInfoDto customerInfo(CsrDto objSearchDto)
			throws TPlusException {

		CustomerInfoDto objDto = null;
		try {
			objDto = objDAO.customerInfo(objSearchDto);

		} catch (Exception e) {
			throw new TPlusException("Error in CsrManager customerInfo method"
					+ e);
		}
		return objDto;
	}

	/*
	 * This method is used for getting the Account Details List
	 */
	public Map cardDetails(CsrDto objSearchDto) throws TPlusException {

		Map searchResult = null;
		try {
			searchResult = objDAO.cardDetails(objSearchDto);

		} catch (Exception e) {
			throw new TPlusException("Error in CsrManager cardDetails method"
					+ e);
		}
		return searchResult;
	}

	/*
	 * This method is used for getting the CurrentPinCount to csrResetPinCount
	 * screen
	 */
	public Collection csrResetPinCount(CsrDto objSearchDto)
			throws TPlusException {

		Collection currentPinCount = null;
		try {
			currentPinCount = objDAO.csrResetPinCount(objSearchDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CsrManager csrResetPinCount method" + e);
		}
		return currentPinCount;
	}

	/*
	 * This method is used for getting creditSplitData
	 */
	public CreditSplitDto creditSplittData(CreditSplitDto objDto)
			throws TPlusException {

		CreditSplitDto objSplittDto = null;
		try {
			objSplittDto = objDAO.creditSplittData(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CsrManager creditSplittData method" + e);
		}
		return objSplittDto;
	}

	/*
	 * for validating the records existed or not
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;
		if (mode == 0 && objDAO.checkExistrecord(obj) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(obj) == 0) {
			rtnMessage = false;
		}
		// this for cardHolderType check for Csr CreditSplit Screen
		if (mode == 3
				&& objDAO.checkExistrecord(obj) == CommonCodes.SUPPLEMENTARYCARD_HOLDER) {
			rtnMessage = false;
		}
		return rtnMessage;
	}
}
