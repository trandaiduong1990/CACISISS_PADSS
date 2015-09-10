package org.transinfo.cacis.dataacess.dao.disputemanagement;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCaseEventDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCleaningMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeMasterDto;

public interface CTFProcessDAO extends BaseDAO {

	public TransactionLogDto getTransactionDto(String cardNo, String appCode,
			String[] tranxCode) throws TPlusException;

	public boolean insertCTFIncomingMaster(
			DisputeCleaningMasterDto objDisputeCleaningMasterDto,
			boolean isTranxLogUpdate) throws TPlusException;

	public DisputeMasterDto getDisputeMasterDto(String arn)
			throws TPlusException;

	public boolean updateDisputeResponse(
			DisputeCaseEventDto objDisputeCaseEventDto, String userId)
			throws TPlusException;

}
