package org.transinfo.cacis.controller.settings;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardPromotionDAO;
import org.transinfo.cacis.dto.settings.CardPromotionDto;
import org.transinfo.cacis.dto.settings.CardPromotionSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class CardPromotionManager {

	private Logger logger = Logger.getLogger(CardPromotionManager.class);

	private CardPromotionDAO objDAO;

	public CardPromotionManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardPromotionDAO();

	}

	/*
	 * This method is used for getting the CardPromotionList
	 */
	public Collection search(CardPromotionSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;
		try {
			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardPromotionManager search method" + e);
		}
		return searchList;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public CardPromotionDto getCardPromotionDto(String cardPromotionId)
			throws TPlusException {
		CardPromotionDto objDto = null;

		try {
			objDto = objDAO.getCardPromotionDto(cardPromotionId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardPromotionManager getCardPromotionDto method"
							+ e);
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular Record to Card_Promotion
	 * table
	 */
	public boolean add(CardPromotionDto objDto) throws TPlusException {

		boolean boolAdd = false;
		objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {

			if (!validate(objDto, objDAO.ADD)) {
				System.out.println("\n\n Record Already Exists");
			} else {

				boolAdd = objDAO.add(objDto);
			}

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CardPromotionManager add method"
					+ e);
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular Record in Card_Promotion
	 * table
	 */
	public boolean update(CardPromotionDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		// setting the status value
		objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */
			boolUpdate = objDAO.update(objDto);
			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardPromotionManager update method" + e);
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in Card_Promotion
	 * table
	 */
	public boolean delete(CardPromotionDto objDto) throws TPlusException {

		boolean boolDelete = false;
		// setting the status value
		objDto.setStatus(CommonCodes.STATUS_INACTIVE);
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */

			boolDelete = objDAO.delete(objDto);

			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardPromotionManager delete method" + e);
		}
		return boolDelete;
	}

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		CardPromotionDto objDto = (CardPromotionDto) obj;

		if (mode == 0 && objDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(objDto) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public static void main(String s[]) throws Exception {
		CardPromotionManager objManager = new CardPromotionManager();
		/*
		 * CardPromotionDto objDto = new CardPromotionDto();
		 * objDto.setPromotionId("2"); objDto.setPromotionType("yyyyy");
		 * objDto.setUpdatedDate(new Date()); // objManager.add(objDto); //
		 * objManager.update(objDto); // objManager.delete(objDto);
		 */

		// Getting CardPromotion
		// objDto.setPromotionId("3"));
		// objDto= objManager.getPromotionId("3");

		// for search
		/*
		 * CardPromotionSearchDto objSearchDto = new CardPromotionSearchDto();
		 * objSearchDto.setPromotionId("2"); Collection listcol =
		 * objManager.search(objSearchDto);
		 * System.out.println("the list size"+listcol.size());
		 */
		// for listDatatest
		// CardPromotionDAO objDAO
		// =DAOFactory.getInstance().getCardPromotionDAO();
		// HashMap hp =objDAO.cardPromotionListData();
		// System.out.println("the list size"+hp.size());

	}

}