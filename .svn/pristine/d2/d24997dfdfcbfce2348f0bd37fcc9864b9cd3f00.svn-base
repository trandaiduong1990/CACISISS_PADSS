package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.PhotocopyRequestDAO;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestSearchDto;

public class PhotocopyRequestManager {

	private PhotocopyRequestDAO objPhotoReqDAO;

	public PhotocopyRequestManager() throws Exception {
		objPhotoReqDAO = DAOFactory.getInstance()
				.getPhotocopyRequestDAO();
	}

	public Collection search(OriginalRequestSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objPhotoReqDAO.search(objSearchDto, pageNo);

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
			objOriReqDto = objPhotoReqDAO
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
			success = objPhotoReqDAO.update(objOriReqDto);
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
				&& objPhotoReqDAO.checkExistrecord(objOriReqDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}
}
