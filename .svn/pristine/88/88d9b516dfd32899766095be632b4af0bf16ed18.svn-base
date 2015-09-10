package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.ChargeBack1DAO;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestSearchDto;

public class ChargeBack1Manager {

	private ChargeBack1DAO objCharBack1DAO;

	public ChargeBack1Manager() throws Exception {
		objCharBack1DAO = DAOFactory.getInstance()
				.getChargeBack1DAO();
	}

	public Collection search(OriginalRequestSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objCharBack1DAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in OriginalRequest search method" + e);
		}
		return searchLst;

	}

	public OriginalRequestDto get(long settlementId) throws TPlusException {
		OriginalRequestDto objOriReqDto = null;
		try {
			objOriReqDto = objCharBack1DAO
					.getOriginalRequest(settlementId);
		} catch (Exception e) {
			throw new TPlusException("Error in OriginalRequest get method"
					+ e);
		}
		return objOriReqDto;
	}

	public boolean update(OriginalRequestDto objOriReqDto)
			throws TPlusException {
		boolean success = false;
		try {
			success = objCharBack1DAO.update(objOriReqDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in OriginalRequest update method" + e);
		}
		return success;
	}

	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = false;
		OriginalRequestDto objOriReqDto = (OriginalRequestDto) obj;
		System.out.println("objOriReqDto.getSettlementId()="+objOriReqDto.getSettlementId());
		if ((mode == 1 || mode == 3)
				&& objCharBack1DAO.checkExistrecord(objOriReqDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}
}
