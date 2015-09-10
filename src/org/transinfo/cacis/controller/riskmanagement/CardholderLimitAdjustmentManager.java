package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.CardholderLimitAdjustmentDAO;
import org.transinfo.cacis.dto.riskmanagement.CardLimitsDto;
import org.transinfo.cacis.dto.riskmanagement.CardholderLimitAdjustmentDto;
import org.transinfo.cacis.dto.riskmanagement.CardholderLimitAdjustmentSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class CardholderLimitAdjustmentManager {

	private CardholderLimitAdjustmentDAO objChLimAdjDAO;

	public CardholderLimitAdjustmentManager() throws Exception {
		objChLimAdjDAO = DAOFactory.getInstance()
				.getCardholderLimitAdjustmentDAO();
	}

	public Collection search(CardholderLimitAdjustmentSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objChLimAdjDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in CardholderLimitAdjustment search method" + e);
		}
		return searchLst;

	}

	public CardholderLimitAdjustmentDto get(String temporaryLimitId)
			throws TPlusException {
		CardholderLimitAdjustmentDto objChLimAdjDto = null;
		try {
			objChLimAdjDto = objChLimAdjDAO
					.getCardholderLimitAdjustment(temporaryLimitId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardholderLimitAdjustment get method" + e);
		}
		return objChLimAdjDto;
	}

	public CardLimitsDto getCreate(String cardNumber) throws TPlusException {
		CardLimitsDto objCardLimitsDto = null;
		try {
			objCardLimitsDto = objChLimAdjDAO.getCardLimits(cardNumber);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardholderLimitAdjustment getCreate method" + e);
		}
		return objCardLimitsDto;
	}

	public boolean add(CardholderLimitAdjustmentDto objChLimAdjDto)
			throws TPlusException {
		boolean success = false;
		// Date today = new Date();
		// objChLimAdjDto.setLastUpdatedDate(today);
		try {
			success = objChLimAdjDAO.add(objChLimAdjDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardholderLimitAdjustment Add method" + e);
		}
		return success;
	}

	public boolean update(CardholderLimitAdjustmentDto objChLimAdjDto)
			throws TPlusException {
		boolean success = false;
		// Date today = new Date();
		// objChLimAdjDto.setLastUpdatedDate(today);
		try {
			success = objChLimAdjDAO.update(objChLimAdjDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardholderLimitAdjustment update method" + e);
		}
		return success;
	}

	public boolean delete(CardholderLimitAdjustmentDto objChLimAdjDto)
			throws TPlusException {
		boolean success = false;
		objChLimAdjDto.setStatus('I');
		try {
			success = objChLimAdjDAO.delete(objChLimAdjDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardholderLimitAdjustment delete method" + e);
		}
		return success;
	}

	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = false;
		CardholderLimitAdjustmentDto objChLimAdjDto = (CardholderLimitAdjustmentDto) obj;
		System.out.println(objChLimAdjDto.getCardNumber());
		if ((mode == 0 || mode == 1 || mode == 4)
				&& objChLimAdjDAO.checkExistrecord(objChLimAdjDto, mode) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}
}
