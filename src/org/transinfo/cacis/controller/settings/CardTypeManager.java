package org.transinfo.cacis.controller.settings;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardTypeDAO;
import org.transinfo.cacis.dto.settings.CardTypeDto;
import org.transinfo.cacis.dto.settings.CardTypeSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings( { "unchecked" })
public class CardTypeManager {

	private Logger logger = Logger.getLogger(CardTypeManager.class);

	private CardTypeDAO objDAO;

	public CardTypeManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardTypeDAO();
	}

	/*
	 * This method is used for getting the cardtypelist
	 */
	public Collection search(CardTypeSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;
		try {
			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CardTypeManager search method"
					+ e);
		}
		return searchList;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public CardTypeDto getCardTypeDto(String branchId) throws TPlusException {
		CardTypeDto objDto = null;

		try {
			objDto = objDAO.getCardTypeDto(branchId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardTypeManager getCardTypeDto method" + e);
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular Record to card_type table
	 */
	/*public boolean add(CardTypeDto objDto) throws TPlusException {

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
			throw new TPlusException("Error in CardTypeManager add method" + e);
		}
		return boolAdd;
	}*/
	
	public boolean add(CardTypeDto objDto) throws TPlusException {

		boolean boolAdd = false;
		objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {

			boolAdd = objDAO.add(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CardTypeManager add method" + e);
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular Record in card_type table
	 */
	public boolean update(CardTypeDto objDto) throws TPlusException {

		boolean boolUpdate = false;
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
			throw new TPlusException("Error in CardTypeManager update method"
					+ e);
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in card_type table
	 */
	public boolean delete(CardTypeDto objDto) throws TPlusException {

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
			throw new TPlusException("Error in CardTypeManager delete method"
					+ e);
		}
		return boolDelete;
	}

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		CardTypeDto objDto = (CardTypeDto) obj;

		// ActionErrors errors = new ActionErrors();

		if (mode == 0 && objDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(objDto) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public boolean hasCardProducts(String cardTypeId) throws TPlusException {
		boolean hasCus = false;

		try {

			hasCus = objDAO.hasCardProducts(cardTypeId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardTypeManager hasCardProducts method" + e);
		}

		return hasCus;
	}

	public static void main(String s[]) throws Exception {
		/*
		 * 
		 * // CardType Test CardTypeManager objManager = new CardTypeManager();
		 * 
		 * 
		 * CardTypeDto objDto = new CardTypeDto(); objDto .setCardTypeId("1");
		 * objDto .setIssuerId("Issuer1" ); objDto.setCardType( "MasterCard");
		 * objDto. setMasterKey("327498" ); objDto.setPek("54355" );
		 * objDto.setKek("843673" ); objDto.setLastUpdatedDate (new Date());
		 * objDto.setLastUpdatedBy ("IssuerAdmin"); //boolean b = objManager
		 * .add(objDto); boolean b = objManager.update( objDto); // boolean b =
		 * objManager.delete(objDto );
		 * 
		 * 
		 * // Card Type Search CardTypeSearchDto objSearchDto = new
		 * CardTypeSearchDto(); objSearchDto .setCardTypeId ("MasterCard");
		 * objManager .search(objSearchDto, 1);
		 * 
		 * // for getting cardtypeDto // CardTypeDto cardDto = objManager
		 * .getCardTypeDto("1"); // System.out.println( "CardtypeID:" +cardDto
		 * .getCardTypeId()); // System .out.println("Cardtype:" +
		 * cardDto.getCardType() );
		 */
	}

}