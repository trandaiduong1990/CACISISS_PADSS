package org.transinfo.cacis.controller.bgprocess;

import java.util.Date;
import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.bgprocess.BgProcessDAO;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;

@SuppressWarnings("unchecked")
public class BgProcessManger {

	private BgProcessDAO objBgProcessDAO;

	public BgProcessManger() throws TPlusException {
		objBgProcessDAO = DAOFactory.getInstance().getBgProcessDAO();
	}

	public List getClosingCardsList(Date cutOffDate) throws TPlusException {
		List closingCards = null;

		try {

			closingCards = objBgProcessDAO.getClosingCardsList(cutOffDate);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in BgProcessManger getClosingCardsList method" + e);
		}

		return closingCards;
	}

	public boolean updateCardClosing(String cardCloseSNo) throws TPlusException {
		boolean resOfUpdate = false;

		try {

			resOfUpdate = objBgProcessDAO.updateCardClosing(cardCloseSNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in BgProcessManger updateCardClosing method" + e);
		}

		return resOfUpdate;
	}

	public List getRenewalCardsList(Date startDate, Date endDate)
			throws TPlusException {
		List renewalCards = null;

		try {

			renewalCards = objBgProcessDAO.getRenewalCardsList(startDate,
					endDate);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in BgProcessManger getRenewalCardsList method" + e);
		}

		return renewalCards;
	}

	public int renewalProcessStatus(String cardNo) throws TPlusException {
		int renewalStatus = -1;

		try {

			renewalStatus = objBgProcessDAO.renewalProcessStatus(cardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in BgProcessManger renewalProcessStatus method" + e);
		}

		return renewalStatus;

	}

	public boolean insertRenewalAndActivity(CardsRenewalDto objCardsRenewalDto,
			CardActivityDto objActivityDto) throws TPlusException {
		boolean insertRes = false;

		try {

			insertRes = objBgProcessDAO.insertRenewalAndActivity(
					objCardsRenewalDto, objActivityDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in BgProcessManger insertRenewal method" + e);
		}

		return insertRes;
	}

	public List getChangeClosingCardsList(Date cutOffDate) throws TPlusException {
		List changeClosingCards = null;

		try {

			changeClosingCards = objBgProcessDAO.getChangeClosingCardsList(cutOffDate);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in BgProcessManger getChangeClosingCardsList method" + e);
		}

		return changeClosingCards;
	}

	public boolean updateChangeCardClosing(String cardChangeCloseSNo, String cardNo) throws TPlusException {
		boolean resOfUpdate = false;

		try {

			resOfUpdate = objBgProcessDAO.updateChangeCardClosing(cardChangeCloseSNo, cardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in BgProcessManger updateChangeCardClosing method" + e);
		}

		return resOfUpdate;
	}

}
