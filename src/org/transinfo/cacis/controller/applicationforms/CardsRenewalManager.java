package org.transinfo.cacis.controller.applicationforms;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.CardsRenewalDAO;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;

@SuppressWarnings("unchecked")
public class CardsRenewalManager {

	private CardsRenewalDAO objDAO;

	public CardsRenewalManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardsRenewalDAO();

	}

	/*
	 * This method is used for getting renewalCardsList
	 */
	public Collection renewalList(int pageNo) throws TPlusException {

		Collection renewlList = null;
		try {
			renewlList = objDAO.renewalList(pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardsRenewalManager renewalList method" + e);
		}
		return renewlList;
	}

	/* This method is used for renewal all the cards and updating cards table */
	public boolean renewalProcess(CardsRenewalDto objDto) throws TPlusException {

		boolean boolRenewal = false;
		// Date date = new Date();
		try {
			// setting the data to insert log tables
			/*
			 * objDto.setDateTime(date); objDto.setActivity("Card Renewled");
			 * objDto.setStationIp(java.net.InetAddress.getLocalHost()
			 * .getHostAddress()); objDto.setUpdatedDate(date);
			 */

			boolRenewal = objDAO.renewalProcess(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardsRenewalManager renewalProcess method" + e);
		}
		return boolRenewal;
	}

	public CardsRenewalDto getOpenRenewalSubmission(String cardNo)
			throws TPlusException {

		CardsRenewalDto searchRes = null;

		try {
			searchRes = objDAO.getOpenRenewalSubmission(cardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardsRenewalManager getOpenRenewalSubmission method:"
							+ e);
		}
		return searchRes;

	}

	public Collection search(CardsRenewalDto objDto) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.search(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardsRenewalManager search method:" + e);
		}
		return searchLst;

	}

	public boolean add(CardsRenewalDto objCardsRenewalDto)
			throws TPlusException {

		boolean replace = false;
		try {
			replace = objDAO.add(objCardsRenewalDto);

		} catch (Exception e) {
			throw new TPlusException("Error in CardsRenewalManager add mehod:"
					+ e);
		}
		return replace;
	}

	public Collection processSearch(CardsRenewalDto objDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.processSearch(objDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardsRenewalManager processSearch method" + e);
		}
		return searchLst;

	}

	public boolean accept(CardsRenewalDto objDto) throws TPlusException {
		boolean repAccept = false;
		try {
			repAccept = objDAO.accept(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager accept method" + e);
		}
		return repAccept;
	}

	public boolean reject(CardsRenewalDto objDto) throws TPlusException {
		boolean repReject = false;
		try {
			repReject = objDAO.reject(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager Reject method" + e);
		}
		return repReject;
	}

}
