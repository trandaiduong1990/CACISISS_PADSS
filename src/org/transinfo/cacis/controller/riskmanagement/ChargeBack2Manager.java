package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.ChargeBack2DAO;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestSearchDto;

public class ChargeBack2Manager {

	private ChargeBack2DAO objCharBack2DAO;

	public ChargeBack2Manager() throws Exception {
		objCharBack2DAO = DAOFactory.getInstance()
				.getChargeBack2DAO();
	}

	public Collection search(OriginalRequestSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objCharBack2DAO.search(objSearchDto, pageNo);

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
			objOriReqDto = objCharBack2DAO
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
			success = objCharBack2DAO.update(objOriReqDto);
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
				&& objCharBack2DAO.checkExistrecord(objOriReqDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}
}
