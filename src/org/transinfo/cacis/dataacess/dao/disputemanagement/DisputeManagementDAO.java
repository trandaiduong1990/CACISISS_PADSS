package org.transinfo.cacis.dataacess.dao.disputemanagement;

import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.disputemanagement.ChargeBackResendSearchDto;
import org.transinfo.cacis.dto.disputemanagement.ChargeBackSearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCaseEventDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCleaningMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeGroupDetailsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeNotReconsiledTranxSearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeTranxNRSearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeTranxSearchDto;

public interface DisputeManagementDAO extends BaseDAO {

	public DisputeTranxSearchDto search(
			DisputeTranxSearchDto objDisputeTranxSearchDto, int pageNo)
			throws TPlusException;
	
	public DisputeTranxNRSearchDto searchNR(
			DisputeTranxNRSearchDto objDisputeTranxSearchDto, int pageNo)
			throws TPlusException;

	public DisputeGroupDetailsDto getDisputeGroupDetailsDto(String reasonId)
			throws TPlusException;
	
	public DisputeGroupDetailsDto getDisputeGroupDetailsDto(String reasonId, String groupId)
			throws TPlusException;

	public TransactionLogDto getTransactionDto(String tranxId)
			throws TPlusException;

	public boolean insertChargeBack(DisputeMasterDto objDisputeMasterDto,
			DisputeCaseEventDto objDisputeCaseEventDto) throws TPlusException;

	public ChargeBackSearchDto CBsearch(
			ChargeBackSearchDto objChargeBackSearchDto, int pageNo)
			throws TPlusException;

	public DisputeMasterDto getDisputeMasterDto(String disCaseNo)
			throws TPlusException;

	public DisputeCleaningMasterDto getDisputeCleaningMasterDto(
			String settlementId) throws TPlusException;

	public List getDisputeMasterList(String settlementId) throws TPlusException;
	
	public List getDisputeMasterListByARN(String arn) throws TPlusException;
	
	public int getDisputeEventsCountByEvent(String eventId, String arn) throws TPlusException;

	public List getDisputeCaseEventsList(String disCaseNo)
			throws TPlusException;
	
	public List getDisputeCaseEventsListByARD(String ard)
			throws TPlusException;

	public DisputeNotReconsiledTranxSearchDto searchNotReconsiledTranx(
			DisputeNotReconsiledTranxSearchDto objNotReconsiledTranxSearchDto,
			int pageNo) throws TPlusException;

	public boolean insertNotReconciledChargeBack(
			DisputeMasterDto objDisputeMasterDto,
			DisputeCaseEventDto objDisputeCaseEventDto, String settlementId)
			throws TPlusException;

	public ChargeBackResendSearchDto CBResendsearch(
			ChargeBackResendSearchDto objChargeBackResendSearchDto, int pageNo)
			throws TPlusException;

	public boolean updateChargeBack(String disCaseNo) throws TPlusException;

	public boolean updateResendChargeBack(DisputeMasterDto objDisputeMasterDto,
			DisputeCaseEventDto objDisputeCaseEventDto) throws TPlusException;

}
