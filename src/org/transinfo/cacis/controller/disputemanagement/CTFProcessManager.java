package org.transinfo.cacis.controller.disputemanagement;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.CTFProcessDAO;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCaseEventDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCleaningMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeMasterDto;

public class CTFProcessManager {

	private CTFProcessDAO objCtfProcessDAO;

	public CTFProcessManager() throws Exception {
		objCtfProcessDAO = DAOFactory.getInstance().getCTFProcessDAO();
	}

	public TransactionLogDto getTransactionDto(String cardNo, String appCode,
			String[] tranxCode) throws TPlusException {

		TransactionLogDto objTransactionLogDto = null;

		try {
			objTransactionLogDto = objCtfProcessDAO.getTransactionDto(cardNo,
					appCode, tranxCode);

		} catch (Exception e) {
			throw new TPlusException(
					"CTFProcessManager getTransactionDto method" + e);
		}

		return objTransactionLogDto;
	}

	public boolean insertCTFIncomingMaster(
			DisputeCleaningMasterDto objDisputeCleaningMasterDto,
			boolean isTranxLogUpdate) throws TPlusException {

		boolean boolInsert = false;

		try {

			boolInsert = objCtfProcessDAO.insertCTFIncomingMaster(
					objDisputeCleaningMasterDto, isTranxLogUpdate);

		} catch (Exception e) {
			throw new TPlusException(
					"CTFProcessManager insertCTFIncomingMaster method" + e);
		}
		return boolInsert;
	}

	public DisputeMasterDto getDisputeMasterDto(String arn)
			throws TPlusException {

		DisputeMasterDto objDisputeMasterDto = null;

		try {
			objDisputeMasterDto = objCtfProcessDAO.getDisputeMasterDto(arn);

		} catch (Exception e) {
			throw new TPlusException(
					"CTFProcessManager getDisputeMasterDto method" + e);
		}

		return objDisputeMasterDto;
	}

	public boolean updateDisputeResponse(
			DisputeCaseEventDto objDisputeCaseEventDto, String userId)
			throws TPlusException {

		boolean boolRes = false;

		try {

			boolRes = objCtfProcessDAO.updateDisputeResponse(
					objDisputeCaseEventDto, userId);

		} catch (Exception e) {
			throw new TPlusException(
					"CTFProcessManager updateDisputeResponse method" + e);
		}
		return boolRes;
	}

}
