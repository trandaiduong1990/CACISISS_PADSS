package org.transinfo.cacis.dataacess.dao.customerservice;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.CardCloseDto;
import org.transinfo.cacis.dto.customerservice.CardLimitAdjustmentDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementLogDto;
import org.transinfo.cacis.dto.customerservice.CardStatusRemarksDto;
import org.transinfo.cacis.dto.customerservice.CreditSplitDto;
import org.transinfo.cacis.dto.customerservice.PinResendDto;

@SuppressWarnings("unchecked")
public interface CardReplacementDAO extends BaseDAO {

	// common for all the screens
	public Collection search(CardReplacementDto objDto) throws TPlusException;

	// methods for CardReplacementForm
	public boolean add(CardReplacementDto objDto) throws TPlusException;

	// methods for CardReplacementFormProcess
	public Collection processSearch(CardReplacementDto objDto, int pageNo)
			throws TPlusException;

	public CardReplacementDto getCardReplacementDto(String applicationId)
			throws TPlusException;

	public boolean accept(CardReplacementDto objDto) throws TPlusException;

	public boolean reject(CardReplacementDto objDto) throws TPlusException;

	// method for cardreceived
	public boolean cardreceived(CardReplacementDto objDto)
			throws TPlusException;

	// method for CardStop
	public boolean cardstop(CardReplacementDto objDto) throws TPlusException;

	// method for cardactivate
	public boolean cardactivate(CardReplacementDto objDto)
			throws TPlusException;
	
	public boolean eComEnableDisable(CardReplacementDto objDto)
	throws TPlusException;

	// method for resetpincount
	public boolean resetpincount(CardReplacementDto objDto)
			throws TPlusException;

	// methods for BillingAddressChange
	public boolean billingaddchange(CardReplacementDto objDto)
			throws TPlusException;

	// methods for cardcloseform
	public boolean cardCloseAdd(CardCloseDto objDto) throws TPlusException;

	// methods for cardcloseform process
	public Collection cardCloseProcessSearch(CardCloseDto objDto, int pageNo)
			throws TPlusException;

	public CardCloseDto getCardCloseDto(String applicationId)
			throws TPlusException;

	public boolean cardCloseAccept(CardCloseDto objDto) throws TPlusException;

	public boolean cardCloseAcceptNew(CardCloseDto objDto, String userId) throws TPlusException;

	public boolean cardCloseReject(CardCloseDto objDto) throws TPlusException;

	// mehtods for PinResendForm
	public boolean pinResendAdd(PinResendDto objDto) throws TPlusException;

	// methods for PinResendFormprocess
	public Collection pinResendProcessSearch(PinResendDto objDto, int pageNo)
			throws TPlusException;

	public PinResendDto getPinResendDto(String applicationId)
			throws TPlusException;

	public boolean pinResendAccept(PinResendDto objDto) throws TPlusException;

	public boolean pinResendReject(PinResendDto objDto) throws TPlusException;

	// methods for CardLimitAdjustmentForm
	public boolean cardLimitAdd(CardLimitAdjustmentDto objDto)
			throws TPlusException;

	// methods for CardLimitAdjustmentFormprocess
	public Collection cardLimitProcessSearch(CardLimitAdjustmentDto objDto,
			int pageNo) throws TPlusException;

	public CardLimitAdjustmentDto getCardLimitAdjustmentDto(String applicationId)
			throws TPlusException;

	public boolean cardLimitAccept(CardLimitAdjustmentDto objDto, String userId)
			throws TPlusException;

	public boolean cardLimitReject(CardLimitAdjustmentDto objDto)
			throws TPlusException;

	// for the CreditSplit
	public boolean creditSplitUpdate(CreditSplitDto objDto)
			throws TPlusException;

	// customer history
	public Collection customerHistory(String custIdNumber)
			throws TPlusException;

	// card activity
	public Collection cardActivities(long cardNumber) throws TPlusException;

	// for checking Existing records
	public int checkExistrecord(Object commobj) throws TPlusException;

	public CardStatusRemarksDto getCardStatusRemarks(long cardStatusId,
			String cardNo) throws TPlusException;

	public CardReplacementDto getOpenReplacementFormSubmission(String cardNo)
			throws TPlusException;

	public boolean updateReplacementCard(CardsDto objCardsDto) throws TPlusException;

	public boolean insertReplacementLog(
			CardReplacementLogDto objCardReplacementLogDto) throws TPlusException;

	public boolean updateCardReplacementForm(CardReplacementDto objCardRepDto) throws TPlusException;

}
