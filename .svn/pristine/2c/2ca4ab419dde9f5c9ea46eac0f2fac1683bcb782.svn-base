package org.transinfo.cacis.controller.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRateDAO;
import org.transinfo.cacis.dto.settings.CardProductRateDto;
import org.transinfo.cacis.dto.settings.CardProductRateSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class CardProductRateManager {

	private Logger logger = Logger.getLogger(CardProductRateManager.class);

	private CardProductRateDAO objDAO;

	public CardProductRateManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardProductRateDAO();
	}

	/*
	 * This method is used for getting the CardProductRatelist
	 */
	public Collection search(CardProductRateSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchList = null;
		try {
			searchList = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRateManager search method" + e);
		}
		return searchList;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public CardProductRateDto getCardProductRateDto(int rateId)
			throws TPlusException {
		CardProductRateDto objDto = null;

		try {
			objDto = objDAO.getCardProductRateDto(rateId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRateManager getIssuerDto method" + e);
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular Record to Card_Product_Rate
	 * table
	 */
	public boolean add(CardProductRateDto objDto) throws TPlusException {

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
					"Error in CardProductRateManager add method" + e);
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular Record
	 * inCard_Product_Rate table
	 */
	public boolean update(CardProductRateDto objDto) throws TPlusException {

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
					"Error in CardProductRateManager update method" + e);
		}
		return boolUpdate;
	}

	public List getAllRates(String cardProductId) throws TPlusException {

		List list = new ArrayList();

		try {
			list = objDAO.getAllRates(cardProductId);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRateManager getAllRates method" + e);
		}

		return list;
	}

	public List getAllRatesExceptOne(String cardProductId, String serialNo) throws TPlusException {

		List list = new ArrayList();

		try {
			list = objDAO.getAllRatesExceptOne(cardProductId, serialNo);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardProductRateManager getAllRates method" + e);
		}

		return list;
	}

	/*
	 * This method is used for deleting the particular Record in
	 * Card_Product_Rate table
	 */
	/*
	 * public boolean delete(CardProductRateDto objDto) throws TPlusException {
	 * 
	 * boolean boolDelete =false; try { if(!validate(objDto,objDAO.UPDATE)) {
	 * System.out.println("\n\n Record  not Existed"); }else{
	 * 
	 * boolDelete = objDAO.delete(objDto);
	 * 
	 * // }
	 * 
	 * } catch (Exception e) { logger.error(e); throw new
	 * TPlusException("Error in CardProductRateManager delete method" +e); }
	 * return boolDelete; }
	 */

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	/*
	 * public boolean validate(Object obj,int mode )throws TPlusException {
	 * boolean rtnMessage = true;
	 * 
	 * CardProductRateDto objDto = (CardProductRateDto)obj;
	 * 
	 * 
	 * if(mode==0 && objDAO.checkExistrecord(objDto)>0) { rtnMessage = false; }
	 * if(mode==1 && objDAO.checkExistrecord(objDto)==0) { rtnMessage = false; }
	 * 
	 * return rtnMessage; }
	 */

	public static void main(String s[]) throws Exception {

		// CardProductRateSetup Test
		// CardProductRateManager objManager = new CardProductRateManager();
		// CardProductRateDto objDto = new CardProductRateDto();
		// CardProductDto objDto1 = new CardProductDto();
		// objDto.setCardProduct(objDto1);

		/*
		 * objDto.setCardProductId("2"); objDto.setCashAdvanceCharge(22);
		 * objDto.setCashChargeAmt(232); objDto.setCashFinaceCharge(212);
		 * objDto.setCreditFinanceCharge(43); objDto.setLatePaymentFee(32);
		 * objDto.setMaxCashDueTranx(321); objDto.setMaxCashTranx(34);
		 * objDto.setMinRepaymentAmt(243); objDto.setMinRepaymentPercent(34);
		 * objDto.setStartDt(new Date()); objDto.setEndDt(new Date());
		 */

		// objDto.setLastUpdatedBy("IssuerAdmin1");
		// boolean b = objManager.add(objDto);
		// boolean b = objManager.update(objDto);
		// boolean b = objManager.delete(objDto); */

		// for CardProductRateSetup seaching
		/*
		 * CardProductRateSearchDto searchbchdto = new
		 * CardProductRateSearchDto(); searchbchdto.setCardProductId("1");
		 * Collection issuers= objManager.search(searchbchdto);
		 * System.out.println("LIST SIZE"+issuers.size());
		 */
		// for getting Branch dto

		// CardProductRateDto cprdto = objManager.getCardProductRateDto(124);
		// System.out.println("RateId:" + cprdto.getId());
		// System.out.println("cardproduct id is:"
		// + cprdto.getCardProduct().getCardProductId());

	}

}