package org.transinfo.cacis.controller.cardproduction;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardEmbossingDAO;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class CardEmbossingManager {

	private CardEmbossingDAO objDAO;

	public CardEmbossingManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardEmbossingDAO();

	}

	public Collection search(CardEmbossingSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in  CardEmbossingManager search method" + e);
		}
		return searchLst;

	}

	public boolean save(CardEmbossingSearchDto objDto) throws TPlusException {

		boolean success;
		try {
			success = objDAO.save(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in  CardEmbossingManager save mehod" + e);
		}
		return success;
	}

	public CardsDto getCard(String embossSerialNo) throws TPlusException {

		CardsDto objCardsDto = null;
		try {

			objCardsDto = objDAO.getCard(embossSerialNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardEmbossingManager getCard method" + e);
		}
		return objCardsDto;
	}

	public boolean updateObjects(String embossSerialNo, String userId,
			Date embossDate) throws TPlusException {

		boolean success;
		try {
			success = objDAO.updateObjects(embossSerialNo, userId, embossDate);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in  CardEmbossingManager save mehod" + e);
		}
		return success;
	}

	public ApplicationProcessDto getCustomerByCusId(String custId)
			throws TPlusException {

		ApplicationProcessDto objApplicationProcessDto = null;
		try {

			objApplicationProcessDto = objDAO.getCustomerByCusId(custId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardEmbossingManager getCustomerByCusId method"
							+ e);
		}
		return objApplicationProcessDto;
	}

	public CardEmbossingDto getCardEmboss(String embossSerialNo)
			throws TPlusException {

		CardEmbossingDto objCardEmbossingDto = null;
		try {

			objCardEmbossingDto = objDAO.getCardEmboss(embossSerialNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardEmbossingManager getCardEmboss method" + e);
		}
		return objCardEmbossingDto;
	}

	public Collection searchReplacement(CardEmbossingSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.searchReplacement(objSearchDto, pageNo);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in  CardEmbossingManager searchReplacement method"
							+ e);
		}
		return searchLst;

	}

	public boolean updateObjectsReplacement(String embossSerialNo,
			String userId, Date embossDate) throws TPlusException {

		boolean success;
		try {
			success = objDAO.updateObjectsReplacement(embossSerialNo, userId,
					embossDate);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in  CardEmbossingManager save mehod" + e);
		}
		return success;
	}

	public Collection searchRenewal(CardEmbossingSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.searchRenewal(objSearchDto, pageNo);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in  CardEmbossingManager searchRenewal method"
							+ e);
		}
		return searchLst;

	}
	
	public boolean updateObjectsRenewal(String embossSerialNo,
			String userId, Date embossDate) throws TPlusException {

		boolean success;
		try {
			success = objDAO.updateObjectsRenewal(embossSerialNo, userId,
					embossDate);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in  CardEmbossingManager updateObjectsRenewal mehod" + e);
		}
		return success;
	}

}