package org.transinfo.cacis.common;

import org.transinfo.cacis.util.DBUtil;
import org.transinfo.cacis.util.DateUtil;

public class IdsGenartion {

	public static synchronized String GenerateCustomerId() {
		//return "C" + DateUtil.getDateFormat();
		return "C" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenerateSupplementaryId() {
		//return "S" + DateUtil.getDateFormat();
		return "S" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenerateApplicationId() {
		//return "A" + DateUtil.getDateFormat();
		return "A" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenerateAccountId() {
		//return "AC" + DateUtil.getDateFormat();
		return "AC" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenerateCallRefNo() {
		//return "RF" + DateUtil.getDateFormat();
		return "RF" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenerateAddessId() {
		String addressId = DateUtil.getDateFormat();
		//return addressId.substring(4);
		return addressId.substring(4) + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenLetterDispId() {
		String dispatchId = DateUtil.getDateFormat();
		//return dispatchId.substring(4);
		//return dispatchId;
		return dispatchId.substring(4) + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenerateClaimId() {
		//return "DC" + DateUtil.getDateFormat();
		return "DC" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenerateApplValId() {
		//return "AV" + DateUtil.getDateFormat();
		return "AV" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}
	
	public static synchronized String GenerateCreditLimitProfileSNo() {
		//return "CL" + DateUtil.getDateFormat();
		return "CL" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}
	
	public static synchronized String GenDocumentId() {
		//return DateUtil.getDocumentIdFormat();
		return DateUtil.getDocumentIdFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenRemarksId() {
		//return DateUtil.getDocumentIdFormat();
		return DateUtil.getDocumentIdFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenerateBatchId() {
		//return "B" + DateUtil.getDateFormat();
		return "B" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static synchronized String GenerateDisputeMasterId() {
		return "CB_" + DateUtil.getDisMngDateFormat() + "_";
	}
	
	public static synchronized String GenerateCardId() {
		return DateUtil.getCardIdDateFormat() + DBUtil.getCardPKSeriesNextValue();
	}
	
	public static synchronized String GenerateCardBatchId() {
		return "IC" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}
	//generate delinquency fee ID
	public static synchronized String GenerateDelinquencyFeeId() {
		return "DF" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}
	//generate delinquency notification ID
	public static synchronized String GenerateDelinquencyNotificationId() {
		return "DN" + DateUtil.getDateFormat() + DBUtil.getPKSeriesNextValue();
	}

	public static void main(String args[]) {
		// IdsGenartion objId = new IdsGenartion();

		// /System.out.println("Customer id : " + objId.GenerateCustomerId());
		// System.out.println("Customer supplemenatry id : " +
		// objId.GenerateSupplementaryId());
		// System.out.println("Application id :" +
		// objId.GenerateApplicationId());
		// System.out.println("Address id :" + objId.GenerateAddessId());
		// System.out.println("Call Record RefNO :" +
		// objId.GenerateCallRefNo());
		// System.out.println("letter diospatch id:" + objId.GenLetterDispId());
		// System.out.println("Dispute Management claim id:" +
		// objId.GenerateClaimId());
		// System.out.println("Document id:" + objId.GenDocumentId());
		// System.out.println("Rmarks id:" + objId.GenDocumentId());
	}
}