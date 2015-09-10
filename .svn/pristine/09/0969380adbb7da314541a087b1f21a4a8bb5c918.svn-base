package org.transinfo.cacis.controller.disputemanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeManagementDAO;
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

public class DisputeManagementManager {

	private DisputeManagementDAO objManagementDAO;

	public DisputeManagementManager() throws Exception {
		objManagementDAO = DAOFactory.getInstance().getDisputeManagementDAO();
	}

	public DisputeTranxSearchDto search(
			DisputeTranxSearchDto objDisputeTranxSearchDto, int pageNo)
			throws TPlusException {

		try {
			objDisputeTranxSearchDto = objManagementDAO.search(
					objDisputeTranxSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in DisputeManagementManager search method" + e);
		}

		return objDisputeTranxSearchDto;

	}

	public DisputeTranxNRSearchDto searchNR(
			DisputeTranxNRSearchDto objDisputeTranxSearchDto, int pageNo)
			throws TPlusException {

		try {
			objDisputeTranxSearchDto = objManagementDAO.searchNR(objDisputeTranxSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in DisputeManagementManager search method" + e);
		}

		return objDisputeTranxSearchDto;

	}

	public DisputeGroupDetailsDto getDisputeGroupDetailsDto(String reasonId)
			throws TPlusException {
		DisputeGroupDetailsDto objDisputeGroupDetailsDto = null;

		try {
			objDisputeGroupDetailsDto = objManagementDAO
					.getDisputeGroupDetailsDto(reasonId);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeGroupDetailsDto method"
							+ e);
		}

		return objDisputeGroupDetailsDto;
	}

	public DisputeGroupDetailsDto getDisputeGroupDetailsDto(String reasonId, String groupId)
			throws TPlusException {
		DisputeGroupDetailsDto objDisputeGroupDetailsDto = null;

		try {
			objDisputeGroupDetailsDto = objManagementDAO
					.getDisputeGroupDetailsDto(reasonId, groupId);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeGroupDetailsDto method"
							+ e);
		}

		return objDisputeGroupDetailsDto;
	}

	public TransactionLogDto getTransactionDto(String tranxId)
			throws TPlusException {
		TransactionLogDto objTransactionLogDto = null;

		try {
			objTransactionLogDto = objManagementDAO.getTransactionDto(tranxId);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getTransactionDto method" + e);
		}

		return objTransactionLogDto;
	}

	public boolean insertChargeBack(DisputeMasterDto objDisputeMasterDto,
			DisputeCaseEventDto objDisputeCaseEventDto) throws TPlusException {

		boolean boolInsert = false;

		try {

			boolInsert = objManagementDAO.insertChargeBack(objDisputeMasterDto,
					objDisputeCaseEventDto);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager insertChargeBack method" + e);
		}
		return boolInsert;
	}

	public Map getDisputeGroups() throws TPlusException {

		Map disGroupList = new HashMap();

		try {

			disGroupList = objManagementDAO.disputeGroupListData();

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeGroups method" + e);
		}

		return disGroupList;
	}

	public Map getDisputeGroups(String scheme) throws TPlusException {

		Map disGroupList = new HashMap();

		try {

			disGroupList = objManagementDAO.disputeGroupListData(scheme);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeGroups method" + e);
		}

		return disGroupList;
	}

	public Map getDisputeGroupDetails(String groupId) throws TPlusException {

		Map disGroupDetailsList = new HashMap();

		try {

			disGroupDetailsList = objManagementDAO
					.disputeGroupReasonListData(groupId);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeGroupDetails method"
							+ e);
		}

		return disGroupDetailsList;
	}

	public Map getCurrencies() throws TPlusException {

		Map currList = new HashMap();

		try {

			//currList = objManagementDAO.currencyListData();
			currList = objManagementDAO.issuerCurrencyListData();

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getCurrencies method" + e);
		}

		return currList;
	}

	public Map getMotos() throws TPlusException {

		Map motoList = new HashMap();

		try {

			motoList = objManagementDAO.disputeMotoListData();

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getMotos method" + e);
		}

		return motoList;
	}

	public ChargeBackSearchDto CBsearch(
			ChargeBackSearchDto objChargeBackSearchDto, int pageNo)
			throws TPlusException {

		try {
			objChargeBackSearchDto = objManagementDAO.CBsearch(
					objChargeBackSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in DisputeManagementManager CBsearch method" + e);
		}

		return objChargeBackSearchDto;

	}

	public DisputeMasterDto getDisputeMasterDto(String disCaseNo)
			throws TPlusException {
		DisputeMasterDto objDisputeMasterDto = null;

		try {
			objDisputeMasterDto = objManagementDAO
					.getDisputeMasterDto(disCaseNo);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeMasterDto method" + e);
		}

		return objDisputeMasterDto;
	}

	public DisputeCleaningMasterDto getDisputeCleaningMasterDto(
			String settlementId) throws TPlusException {
		DisputeCleaningMasterDto objDisputeCleaningMasterDto = null;

		try {
			objDisputeCleaningMasterDto = objManagementDAO
					.getDisputeCleaningMasterDto(settlementId);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeCleaningMasterDto method"
							+ e);
		}

		return objDisputeCleaningMasterDto;
	}

	public List getDisputeMasterList(String settlementId) throws TPlusException {
		List disList = new ArrayList();

		try {
			disList = objManagementDAO.getDisputeMasterList(settlementId);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeMasterList method" + e);
		}

		return disList;
	}

	public List getDisputeMasterListByARN(String arn) throws TPlusException {
		List disList = new ArrayList();

		try {
			disList = objManagementDAO.getDisputeMasterListByARN(arn);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeMasterListByARN method" + e);
		}

		return disList;
	}

	public int getDisputeEventsCountByEvent(String eventId, String arn) throws TPlusException {
		int res = 0;

		try {
			
			res = objManagementDAO.getDisputeEventsCountByEvent(eventId, arn);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeEventsCountByEvent method" + e);
		}

		return res;
	}

	public List getDisputeCaseEventsList(String disCaseNo)
			throws TPlusException {
		List disList = new ArrayList();

		try {
			disList = objManagementDAO.getDisputeCaseEventsList(disCaseNo);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeCaseEventsList method"
							+ e);
		}

		return disList;
	}

	public List getDisputeCaseEventsListByARD(String ard)
			throws TPlusException {
		List disList = new ArrayList();

		try {
			disList = objManagementDAO.getDisputeCaseEventsListByARD(ard);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager getDisputeCaseEventsList method"
							+ e);
		}

		return disList;
	}

	public DisputeNotReconsiledTranxSearchDto searchNotReconsiledTranx(
			DisputeNotReconsiledTranxSearchDto objDisputeNotReconsiledTranxSearchDto,
			int pageNo) throws TPlusException {

		try {
			objDisputeNotReconsiledTranxSearchDto = objManagementDAO
					.searchNotReconsiledTranx(
							objDisputeNotReconsiledTranxSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in DisputeManagementManager search method" + e);
		}

		return objDisputeNotReconsiledTranxSearchDto;

	}

	public boolean insertNotReconciledChargeBack(
			DisputeMasterDto objDisputeMasterDto,
			DisputeCaseEventDto objDisputeCaseEventDto, String settlementId)
			throws TPlusException {

		boolean boolInsert = false;

		try {

			boolInsert = objManagementDAO.insertNotReconciledChargeBack(
					objDisputeMasterDto, objDisputeCaseEventDto, settlementId);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager insertChargeBack method" + e);
		}
		return boolInsert;
	}

	public ChargeBackResendSearchDto CBResendsearch(
			ChargeBackResendSearchDto objBackResendSearchDto, int pageNo)
			throws TPlusException {

		try {
			objBackResendSearchDto = objManagementDAO.CBResendsearch(
					objBackResendSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in DisputeManagementManager CBsearch method" + e);
		}

		return objBackResendSearchDto;

	}

	public boolean updateChargeBack(String disCaseNo) throws TPlusException {

		boolean boolUpdate = false;

		try {

			boolUpdate = objManagementDAO.updateChargeBack(disCaseNo);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager updateChargeBack method" + e);
		}
		return boolUpdate;
	}

	public boolean updateResendChargeBack(DisputeMasterDto objDisputeMasterDto,
			DisputeCaseEventDto objDisputeCaseEventDto) throws TPlusException {

		boolean boolInsert = false;

		try {

			boolInsert = objManagementDAO.updateResendChargeBack(
					objDisputeMasterDto, objDisputeCaseEventDto);

		} catch (Exception e) {
			throw new TPlusException(
					"DisputeManagementManager updateResendChargeBack method"
							+ e);
		}
		return boolInsert;
	}

}
