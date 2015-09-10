package org.transinfo.cacis.controller.settings;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductLimitDAO;
import org.transinfo.cacis.dto.settings.CardProductLimitDto;
import org.transinfo.cacis.dto.settings.CardProductLimitSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings( "unchecked" )
public class CardProductLimitManager {

	private Logger logger = Logger.getLogger(CardProductLimitManager.class);

	private CardProductLimitDAO objDAO;

	public CardProductLimitManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardProductLimitDAO();

	}

	public Collection search(CardProductLimitSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;

		try {
			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductLimitManager search method" + e);
		}
		return searchList;

	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public CardProductLimitDto getCardProductLimitDto(String cardProductId)
			throws TPlusException {
		CardProductLimitDto objDto = null;

		try {
			objDto = objDAO.getCardProductLimitDto(cardProductId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductManager CardProductDto method" + e);
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular Record to Cust_type table
	 */
	/*public boolean add(CardProductLimitDto objDto) throws TPlusException {

		boolean boolAdd = false;
		// objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {

			if (!validate(objDto, objDAO.ADD)) {
				System.out.println("\n\n Record Already Exists");
			} else {

				boolAdd = objDAO.add(objDto);
			}

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CardProductManager add method"
					+ e);
		}
		return boolAdd;
	}*/
	
	public boolean add(CardProductLimitDto objDto) throws TPlusException {

		boolean boolAdd = false;
		// objDto.setStatus(CommonCodes.STATUS_ACTIVE);
		try {

			boolAdd = objDAO.add(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CardProductManager add method"
					+ e);
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular Record in cust_type table
	 */
	public boolean update(CardProductLimitDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		// objDto.setStatus(CommonCodes.STATUS_ACTIVE);
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
					"Error in CardProductLimitManager update method" + e);
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in cust_type table
	 */
	public boolean delete(CardProductLimitDto objDto) throws TPlusException {

		boolean boolDelete = false;
		// objDto.setStatus(CommonCodes.STATUS_INACTIVE);
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
					"Error in CardProductManager delete method" + e);
		}
		return boolDelete;
	}

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		CardProductLimitDto objCardProductLimit = (CardProductLimitDto) obj;

		System.out.println(objCardProductLimit.getCardProductId());

		if (mode == 0 && objDAO.checkExistrecord(objCardProductLimit) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(objCardProductLimit) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public static void main(String s[]) throws Exception {/*
														 * CardProductLimitManager
														 * objManager = new
														 * CardProductLimitManager
														 * (); CardProductDto
														 * objDto = new
														 * CardProductDto();
														 * //adding
														 * 
														 * 
														 * 
														 * objDto.setCardProductId
														 * ("3");
														 * objDto.setCardProductName
														 * ("MasterGold");
														 * objDto
														 * .setCardProductTypeId
														 * (1);
														 * objDto.setCardTypeId
														 * ("1");
														 * objDto.setBin("123");
														 * objDto
														 * .setDueDays(12);
														 * objDto
														 * .setExpireTime(02);
														 * objDto
														 * .setFloorLimit(211);
														 * objDto
														 * .setIssuerId("Issuer1"
														 * );objDto.
														 * setMaxCardLimitPerSal
														 * (22);
														 * objDto.setMaxCashLimit
														 * (12);objDto.
														 * setMaxCashLimitPerCrl
														 * (12);
														 * objDto.setMaxSuppLimit
														 * (21);
														 * objDto.setMinimumSalary
														 * (100);objDto.
														 * setRiskLastUpdatedBy
														 * ("stayam");
														 * objDto.setServiceCode
														 * ("432");
														 * objDto.setStatus
														 * ("A");objDto.
														 * setTempCashLimitRaise
														 * (12);objDto.
														 * setTempCreditLimitRaise
														 * (21);
														 * objDto.setUpdatedDate
														 * (new Date());
														 * objDto.setLastUpdatedBy
														 * ("satyam");
														 * 
														 * String promotions[]=
														 * {"1"};
														 * 
														 * for(int i=0;
														 * i<promotions
														 * .length;i++) {
														 * objDto.
														 * getCardPromotions
														 * ().put
														 * (promotions[i],new
														 * CardProductPromotionDto
														 * (new Date(),new
														 * Date()
														 * ,objDto.getLastUpdatedBy
														 * ())); }
														 * 
														 * //
														 * objManager.add(objDto
														 * );
														 * //objManager.update
														 * (objDto);
														 * 
														 * //Getting cardProduct
														 * objDto
														 * .setCardProductId
														 * ("3"); // objDto=
														 * objManager
														 * .getCardProduct("3");
														 * //System.out.println(
														 * "card product size="
														 * +objDto
														 * .getSelectedPromotions
														 * ().length);
														 * 
														 * 
														 * //for search
														 * CardProductSearchDto
														 * objSearchDto = new
														 * CardProductSearchDto
														 * ();objSearchDto.
														 * setCardProductId
														 * ("3"); Collection
														 * listcol =
														 * objManager.search
														 * (objSearchDto);
														 * System
														 * .out.println("size="
														 * +cpd
														 * .getSelectedPromotions
														 * ().length);
														 * System.out
														 * .println("the list size"
														 * +listcol.size());
														 */
	}

}