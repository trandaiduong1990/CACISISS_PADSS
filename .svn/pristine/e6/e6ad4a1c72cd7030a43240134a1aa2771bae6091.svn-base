package org.transinfo.cacis.controller.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductFeeDAO;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CardProductFeeSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class CardProductFeeManager {

	private Logger logger = Logger.getLogger(CardProductFeeManager.class);

	private CardProductFeeDAO objDAO;

	public CardProductFeeManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardProductFeeDAO();
		// DBManager.initDBPool();
	}

	/*
	 * This method is used for getting the CardProductFeelist
	 */
	public Collection search(CardProductFeeSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;
		try {
			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductFeeManager search method" + e);
		}
		return searchList;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public CardProductFeeDto getCardProductFeeDto(int feeId)
			throws TPlusException {
		CardProductFeeDto objDto = null;

		try {
			objDto = objDAO.getCardProductFeeDto(feeId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductFeeManager getIssuerDto method" + e);
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular Record to Card_Product_fee
	 * table
	 */
	public boolean add(CardProductFeeDto objDto) throws TPlusException {

		boolean boolAdd = false;

		try {

			/*
			 * if(!validate(objDto,objDAO.ADD)){
			 * System.out.println("\n\n Record Already Exists"); }else{
			 */

			boolAdd = objDAO.add(objDto);
			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductFeeManager add method" + e);
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular Record inCard_Product_fee
	 * table
	 */
	public boolean update(CardProductFeeDto objDto) throws TPlusException {

		boolean boolUpdate = false;
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
					"Error in CardProductFeeManager update method" + e);
		}
		return boolUpdate;
	}
	public List getAllFees(String cardProductId) throws TPlusException {

		List list = new ArrayList();

		try {
			list = objDAO.getAllFees(cardProductId);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRateManager getAllRates method" + e);
		}

		return list;
	}

	public List getAllFeesExceptOne(String cardProductId, String serialNo) throws TPlusException {

		List list = new ArrayList();

		try {
			list = objDAO.getAllFeesExceptOne(cardProductId, serialNo);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRateManager getAllRates method" + e);
		}

		return list;
	}

	/*
	 * This method is used for deleting the particular Record in
	 * Card_Product_fee table
	 */
	/*
	 * public boolean delete(CardProductFeeDto objDto) throws TPlusException {
	 * 
	 * boolean boolDelete =false; try { if(!validate(objDto,objDAO.UPDATE)) {
	 * System.out.println("\n\n Record  not Existed"); }else{
	 * 
	 * boolDelete = objDAO.delete(objDto);
	 * 
	 * // }
	 * 
	 * } catch (Exception e) { throw new
	 * TPlusException("Error in CardProductFeeManager delete method" +e); }
	 * return boolDelete; }
	 */

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	/*
	 * public boolean validate(Object obj,int mode )throws TPlusException {
	 * boolean rtnMessage = true;
	 * 
	 * CardProductFeeDto objDto = (CardProductFeeDto)obj;
	 * 
	 * 
	 * if(mode==0 && objDAO.checkExistrecord(objDto)>0) { rtnMessage = false; }
	 * if(mode==1 && objDAO.checkExistrecord(objDto)==0) { rtnMessage = false; }
	 * 
	 * return rtnMessage; }
	 */

	public static void main(String s[]) throws Exception {

		// CardProductFeeSetup Test
		@SuppressWarnings("unused")
		CardProductFeeManager objManager = new CardProductFeeManager();
		CardProductFeeDto objDto = new CardProductFeeDto();
		objDto.setCardProductId("1");
		objDto.setAnnualFeePrimary(23);
		objDto.setAnnualFeeSecondary(45);
		objDto.setAdminstrationFee(12);
		objDto.setCancelationFee(87);
		objDto.setCurrConversionFee(33);
		objDto.setIssuerFee(78);
		objDto.setReplacementFee(89);
		// boolean b = objManager.add(objDto);
		// boolean b = objManager.update(objDto);
		// boolean b = objManager.delete(objDto);

		// for Branch seaching
		/*
		 * CardProductRateSearchDto searchbchdto = new
		 * CardProductRateSearchDto();
		 * searchbchdto.setCardProductName("VisaSilverCard");
		 * objManager.search(searchbchdto);
		 * 
		 * //for getting Branch dto
		 * 
		 * CardProductRateDto bchdto = objManager.getCardProductRateDto("2"); //
		 * System.out.println("BranchId:"+bchdto.getBranchId()); //
		 * System.out.println("BranchName:"+bchdto.getBranchName());
		 */

	}

	public CardProductFeeDto getCardProductFeeByProductId(String cardProductId)
			throws TPlusException {
		CardProductFeeDto objDto = null;

		try {
			objDto = objDAO.getCardProductFeeByProductId(cardProductId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductFeeManager getIssuerDto method" + e);
		}
		return objDto;
	}

}