package org.transinfo.cacis.controller.cardproduction;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDeliverDAO;
import org.transinfo.cacis.dto.cardproduction.CardDeliverSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class CardDeliverManager {

	private CardDeliverDAO objDAO;

	public CardDeliverManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardDeliverDAO();

	}

	public Collection search(CardDeliverSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in CardDeliverManager saearch method" + e);
		}
		return searchLst;

	}

	public boolean save(CardDeliverSearchDto objDto) throws TPlusException {

		boolean success;
		try {
			success = objDAO.save(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in CardDeliverManager save mehod"
					+ e);
		}
		return success;
	}

	// this for CardReceived Link in this cardstatus is set to activate (bcz
	// cards were received)
	public boolean received(CardDeliverSearchDto objDto) throws TPlusException {

		boolean success;
		try {
			success = objDAO.received(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardDeliverManager received mehod" + e);
		}
		return success;
	}

	public CardsDto getCard(String cardDeliverSerialNo) throws TPlusException {

		CardsDto objCardsDto = null;
		try {

			objCardsDto = objDAO.getCard(cardDeliverSerialNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardEmbossingManager getCard method" + e);
		}
		return objCardsDto;
	}

	public boolean updateObjects(String cardDeliverSerialNo, String userId)
			throws TPlusException {

		boolean success;
		try {
			success = objDAO.updateObjects(cardDeliverSerialNo, userId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in  CardEmbossingManager save mehod" + e);
		}
		return success;
	}

}