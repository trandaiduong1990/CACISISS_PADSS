package org.transinfo.cacis.controller.cardproduction;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.PinPrintingDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingSearchDto;
import org.transinfo.cacis.dto.cardproduction.ResendPinPrintingSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class PinPrintingManager {

	private PinPrintingDAO objDAO;

	public PinPrintingManager() throws Exception {
		objDAO = DAOFactory.getInstance().getPinPrintingDAO();

	}

	public Collection search(PinPrintingSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in PinPrintingManager saearch method" + e);
		}
		return searchLst;

	}

	public boolean save(PinPrintingSearchDto objDto) throws TPlusException {

		boolean success;
		try {
			success = objDAO.save(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in PinPrintingManager save mehod"
					+ e);
		}
		return success;
	}

	public boolean updateObjects(String pinPrinSerialNo, String userId)
			throws TPlusException {

		boolean success;
		try {
			success = objDAO.updateObjects(pinPrinSerialNo, userId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in  PinPrintingManager updateObjects mehod" + e);
		}
		return success;
	}

	public PinPrintingDto getPinPrint(String cardNo) throws TPlusException {

		PinPrintingDto objPinPrintingDto = null;

		try {
			objPinPrintingDto = objDAO.getPinPrint(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in PinPrintingManager getPinPrint mehod" + e);
		}

		return objPinPrintingDto;
	}

	public CardsDto getCard(String pinPrintSerialNo) throws TPlusException {

		CardsDto objCardsDto = null;
		try {

			objCardsDto = objDAO.getCard(pinPrintSerialNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in PinPrintingManager getCard method" + e);
		}
		return objCardsDto;
	}

	public boolean isEmbossed(String cardNo) throws TPlusException {

		boolean res = false;

		try {
			res = objDAO.isEmbossed(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in PinPrintingManager isEmbossed mehod" + e);
		}

		return res;
	}

	public PinPrintingDto getPinPrintNotProcessed(String cardNo)
			throws TPlusException {

		PinPrintingDto objPinPrintingDto = null;

		try {
			objPinPrintingDto = objDAO.getPinPrintNotProcessed(cardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in PinPrintingManager getPinPrint mehod" + e);
		}

		return objPinPrintingDto;
	}

	public Collection searchResendList(ResendPinPrintingSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.searchResendList(objSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in PinPrintingManager updateResendObjects method" + e);
		}
		return searchLst;

	}

	public boolean updateResendObjects(String pinPrinSerialNo, String userId)
			throws TPlusException {

		boolean success;
		try {
			success = objDAO.updateResendObjects(pinPrinSerialNo, userId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in  PinPrintingManager updateResendObjects mehod" + e);
		}
		return success;
	}

	public boolean updateCARDSandCARDDATA(CardsDto objCardsDto)
			throws TPlusException {

		boolean success;
		try {
			success = objDAO.updateCARDSandCARDDATA(objCardsDto);
		} catch (Exception e) {
			throw new TPlusException("Error in  PinPrintingManager updateCARDSandCARDDATA mehod" + e);
		}
		return success;
	}

}