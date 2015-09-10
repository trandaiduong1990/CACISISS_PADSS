package org.transinfo.cacis.controller.customerservice;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO;
import org.transinfo.cacis.dto.customerservice.CardCloseDto;
import org.transinfo.cacis.dto.customerservice.CardLimitAdjustmentDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementDto;
import org.transinfo.cacis.dto.customerservice.CardStatusRemarksDto;
import org.transinfo.cacis.dto.customerservice.CreditSplitDto;
import org.transinfo.cacis.dto.customerservice.PinResendDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings( { "static-access", "unchecked" })
public class CardReplacementManager {

	private CardReplacementDAO objDAO;

	public CardReplacementManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardReplacementDAO();

	}

	public Collection search(CardReplacementDto objDto) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.search(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager search method:" + e);
		}
		return searchLst;

	}

	/*
	 * method for cardreplacementform
	 */
	public boolean add(CardReplacementDto objRepDto) throws TPlusException {

		boolean replace = false;
		try {
			/*
			 * this is to validate whether this type of application already
			 * existed or not in application_master if exists show message this
			 * type of record already opend.
			 * 
			 * ApplicationMasterDto objMstDto = new ApplicationMasterDto();
			 * objMstDto.setApplicationType(objRepDto.getApplicationType());
			 * objMstDto.setIdNumber(objRepDto.getIdNumber());
			 */

			if (!validate(objRepDto, objDAO.ADD)) {
				System.out.println("Record Already Exists");
			} else {
				replace = objDAO.add(objRepDto);
			}

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager repalceCard mehod:" + e);
		}
		return replace;
	}

	// methods for CardReplacementProcess

	public Collection processSearch(CardReplacementDto objDto, int pageNo)
	throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.processSearch(objDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementProcessManager processSearch method"
					+ e);
		}
		return searchLst;

	}

	public CardReplacementDto getCardReplacementDto(String applicationId)
	throws TPlusException {

		CardReplacementDto objCarRepDto = null;

		try {
			objCarRepDto = objDAO.getCardReplacementDto(applicationId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager getCardReplacementDto method"
					+ e);
		}
		return objCarRepDto;
	}

	public boolean accept(CardReplacementDto objDto) throws TPlusException {
		boolean repAccept = false;
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * 
			 * System.out.println("Record Not Exists"); }
			 */
			repAccept = objDAO.accept(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager accept method" + e);
		}
		return repAccept;
	}

	public boolean reject(CardReplacementDto objDto) throws TPlusException {
		boolean repReject = false;
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * 
			 * System.out.println("Record Not Exists"); }
			 */
			repReject = objDAO.reject(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager Reject method" + e);
		}
		return repReject;
	}

	/*
	 * for activating the card
	 */
	public boolean cardreceived(CardReplacementDto objDto)
	throws TPlusException {
		boolean received = false;
		try {
			received = objDAO.cardreceived(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardreceived method" + e);
		}
		return received;
	}

	/*
	 * for stoping the card
	 */
	public boolean cardstop(CardReplacementDto objDto) throws TPlusException {
		boolean stop = false;
		try {
			stop = objDAO.cardstop(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardstop method" + e);
		}
		return stop;
	}

	/*
	 * for activating the card
	 */
	public boolean cardactivate(CardReplacementDto objDto)
	throws TPlusException {
		boolean activate = false;
		try {
			activate = objDAO.cardactivate(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardactivate method" + e);
		}
		return activate;
	}

	public boolean eComEnableDisable(CardReplacementDto objDto)
	throws TPlusException {
		boolean activate = false;
		try {
			activate = objDAO.eComEnableDisable(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardactivate method" + e);
		}
		return activate;
	}

	/*
	 * for reseting the card pin count
	 */
	public boolean resetpincount(CardReplacementDto objDto)
	throws TPlusException {
		boolean resetpincount = false;
		try {
			resetpincount = objDAO.resetpincount(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager resetpincount method" + e);
		}
		return resetpincount;
	}

	/*
	 * for billinAddress Change
	 */
	public boolean billingaddchange(CardReplacementDto objDto)
	throws TPlusException {
		boolean billaddchange = false;
		try {
			billaddchange = objDAO.billingaddchange(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager billing address change method"
					+ e);
		}
		return billaddchange;
	}

	// methods for CardCloseForm
	/*
	 * for saving the cardclose forms data
	 */
	public boolean cardCloseAdd(CardCloseDto objClsoeDto) throws TPlusException {
		boolean cardClose = false;
		try {
			/*
			 * this is to validate whether this type of application already
			 * existed or not in application_master if exists show message this
			 * type of record already opend. ApplicationMasterDto objMstDto =
			 * new ApplicationMasterDto();
			 * objMstDto.setApplicationType(objClsoeDto.getApplicationType());
			 * objMstDto.setIdNumber(objClsoeDto.getIdNumber());
			 */
			if (!validate(objClsoeDto, objDAO.ADD)) {

				System.out.println("Record Already Exists");
			} else {
				cardClose = objDAO.cardCloseAdd(objClsoeDto);
			}
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardCloseAdd method" + e);
		}
		return cardClose;
	}

	// methods for CardCloseFormProcess

	public Collection cardCloseProcessSearch(CardCloseDto objDto, int pageNo)
	throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.cardCloseProcessSearch(objDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException("Error in CardReplacementProcessManager cardCloseProcessSearch method" + e);
		}
		return searchLst;

	}

	public CardCloseDto getCardCloseDto(String applicationId)
	throws TPlusException {

		CardCloseDto objCarRepDto = null;

		try {
			objCarRepDto = objDAO.getCardCloseDto(applicationId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager getCardCloseDto method"
					+ e);
		}
		return objCarRepDto;
	}

	public boolean cardCloseAccept(CardCloseDto objDto) throws TPlusException {
		boolean accept = false;
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * 
			 * System.out.println("Record Not Exists"); }
			 */
			accept = objDAO.cardCloseAccept(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardCloseAccept method"
					+ e);
		}
		return accept;
	}

	public boolean cardCloseAcceptNew(CardCloseDto objDto, String userId) throws TPlusException {
		boolean accept = false;
		try {
			accept = objDAO.cardCloseAcceptNew(objDto, userId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardCloseAcceptNew method"
					+ e);
		}
		return accept;
	}

	public boolean cardCloseReject(CardCloseDto objDto) throws TPlusException {
		boolean reject = false;
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * 
			 * System.out.println("Record Not Exists"); }
			 */
			reject = objDAO.cardCloseReject(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardCloseReject method"
					+ e);
		}
		return reject;
	}

	// methods for PinResendForm
	/*
	 * for saving the PinResendForm data
	 */
	public boolean pinResendAdd(PinResendDto objPinResendDto)
	throws TPlusException {
		boolean pinResend = false;
		try {

			/*
			 * this is to validate whether this type of application already
			 * existed or not in application_master if exists show message this
			 * type of record already opend. ApplicationMasterDto objMstDto =
			 * new ApplicationMasterDto();
			 * objMstDto.setApplicationType(objPinResendDto
			 * .getApplicationType());
			 * objMstDto.setIdNumber(objPinResendDto.getIdNumber());
			 */
			if (!validate(objPinResendDto, objDAO.ADD)) {

				System.out.println("Record Already Exists and opend ");
			} else {
				pinResend = objDAO.pinResendAdd(objPinResendDto);
			}

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager pinResendAdd method" + e);
		}
		return pinResend;
	}

	// methods for pinResendFormProcess

	public Collection pinResendProcessSearch(PinResendDto objDto, int pageNo)
	throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.pinResendProcessSearch(objDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementProcessManager pinResendProcessSearch method"
					+ e);
		}
		return searchLst;

	}

	public PinResendDto getPinResendDto(String applicationId)
	throws TPlusException {

		PinResendDto objCarRepDto = null;

		try {
			objCarRepDto = objDAO.getPinResendDto(applicationId);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in CardReplacementManager getPinResendDto method"
					+ e);
		}
		return objCarRepDto;
	}

	public boolean pinResendAccept(PinResendDto objDto) throws TPlusException {
		boolean accept = false;
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * 
			 * System.out.println("Record Not Exists"); }
			 */
			accept = objDAO.pinResendAccept(objDto);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in CardReplacementManager pinResendAccept method"
					+ e);
		}
		return accept;
	}

	public boolean pinResendReject(PinResendDto objDto) throws TPlusException {

		boolean reject = false;
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * 
			 * System.out.println("Record Not Exists"); }
			 */

			reject = objDAO.pinResendReject(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager pinResendReject method"
					+ e);
		}
		return reject;
	}

	// methods for cardLimitAdjsutmentForm
	/*
	 * for saving the cardLimitAdjsutmentforms data
	 */
	public boolean cardLimitAdd(CardLimitAdjustmentDto objCardLimitDto)
	throws TPlusException {

		boolean limitAdd = false;
		try {
			/*
			 * this is to validate whether this type of application already
			 * existed or not in application_master if exists show message this
			 * type of record already opend. ApplicationMasterDto objMstDto =
			 * new ApplicationMasterDto();
			 * objMstDto.setApplicationType(objCardLimitDto
			 * .getApplicationType());
			 * objMstDto.setIdNumber(objCardLimitDto.getIdNumber());
			 */
			if (!validate(objCardLimitDto, objDAO.ADD)) {
				System.out
				.println("Record Already Exists and this type of record already opend");
			} else {
				limitAdd = objDAO.cardLimitAdd(objCardLimitDto);
			}
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardLimitAdd method" + e);
		}
		return limitAdd;
	}

	// methods for cardLimitAdjsutmentFormProcess

	public Collection cardLimitProcessSearch(CardLimitAdjustmentDto objDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.cardLimitProcessSearch(objDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementProcessManager cardLimitProcessSearch method"
					+ e);
		}
		return searchLst;

	}

	public CardLimitAdjustmentDto getCardLimitAdjustmentDto(String applicationId)
	throws TPlusException {

		CardLimitAdjustmentDto objCarRepDto = null;

		try {
			objCarRepDto = objDAO.getCardLimitAdjustmentDto(applicationId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager getCardLimitAdjustmentDto method"
					+ e);
		}
		return objCarRepDto;
	}

	public boolean cardLimitAccept(CardLimitAdjustmentDto objDto, String userId)
	throws TPlusException {

		boolean accept = false;
		try {

			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * 
			 * System.out.println("Record Not Exists"); }
			 */
			accept = objDAO.cardLimitAccept(objDto, userId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardLimitAccept method"
					+ e);
		}
		return accept;
	}

	public boolean cardLimitReject(CardLimitAdjustmentDto objDto)
	throws TPlusException {

		boolean reject = false;
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * 
			 * System.out.println("Record Not Exists"); }
			 */
			reject = objDAO.cardLimitReject(objDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager cardLimitReject method"
					+ e);
		}
		return reject;
	}

	/*
	 * for credit Spliting updation
	 */
	public boolean creditSplitUpdate(CreditSplitDto objSplit)
	throws TPlusException {
		boolean split = false;
		try {
			split = objDAO.creditSplitUpdate(objSplit);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager creditSplitUpdate method"
					+ e);
		}
		return split;
	}

	/*
	 * for Customer History
	 */
	public Collection customerHistory(String custIdNumber)
	throws TPlusException {

		Collection historyList = null;

		try {
			historyList = objDAO.customerHistory(custIdNumber);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in CardRepalcementManager customerHistory method"
					+ e);
		}
		return historyList;

	}

	/*
	 * for Card Activites Details
	 */
	public Collection cardActivities(long cardNumber) throws TPlusException {

		Collection cardActivityList = null;

		try {
			cardActivityList = objDAO.cardActivities(cardNumber);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in CardRepalcementManager cardActivityList method"
					+ e);
		}
		return cardActivityList;

	}

	/*
	 * for validating the records existed or not
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;
		if (mode == 0 && objDAO.checkExistrecord(obj) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(obj) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public CardStatusRemarksDto getCardStatusRemarks(long cardStatusID,
			String cardNo) throws TPlusException {

		CardStatusRemarksDto searchRes = null;

		try {
			searchRes = objDAO.getCardStatusRemarks(cardStatusID, cardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager getCardStatusRemarks method:"
					+ e);
		}
		return searchRes;

	}

	public CardReplacementDto getOpenReplacementFormSubmission(String cardNo)
	throws TPlusException {

		CardReplacementDto searchRes = null;

		try {
			searchRes = objDAO.getOpenReplacementFormSubmission(cardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardReplacementManager getCardStatusRemarks method:"
					+ e);
		}
		return searchRes;

	}
}