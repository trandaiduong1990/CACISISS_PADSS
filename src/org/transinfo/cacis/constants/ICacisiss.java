package org.transinfo.cacis.constants;

public interface ICacisiss {
	
	public interface IEmboss{
		
		public final String CVV2_SERVICE_CODE =  "000";
		public final String ICVV_SERVICE_CODE =  "999";
		public final String BET_SERVICECODE_AND_CVV =  "00";
		public final String AFTER_CVV =  "000000";
		public final int TRACK1_LENGTH = 25;
		public final String EXP_DATE_SEPARATOR = "/";
		public final String CARD_NUMBER_SEPARATOR = " ";
		public final String TRACK1_DATA_APPENDER = "B";
		public final int CARD_NUMBER_SPLIT_COUNT = 4;
		public final String EMBOSS_FILE_CONTENT_TYPE_XLS = "application/vnd.ms-excel";
		public final String EMBOSS_FILE_CONTENT_TYPE_TXT = "APPLICATION/DOWNLOAD";
		
	}
	
	public interface IErrorMessages{

		public final String NODATEFOUND = "No Data Found";
	}
	
	public interface ICardGeneration{

		public final Character PIN_DISABLE_VALUE = 'Y';
		public final int CVKI = 1;
		public final int PVKI = 1;
	}
	
	public interface IBilling{
		
		public final String BILL_DEFAULT_LOC = "C:\\Cacisiss\\bill_reports\\";
		public final String BILL_REPORT_DEFAULT_FILE = "/BillingReport.xml";
		
		public final String CREDIT_CARD = "CreditCard";
		public final String DEBIT_CARD = "DebitCard";
		
		// transaction types
		public final String TRNX_SALE = "SALE";
		public final String TRNX_REFUND = "REFUND";
		public final String TRNX_CASH = "CASH";
		
		public final String DEBIT_GL = "D";
		public final String CREDIT_GL = "C";
		
		public final String MIN_CAl_METHOD_PER = "PER";
		public final String MIN_CAl_METHOD_OCC = "OCC";
		
		public interface IParams{
			public final String ACCOUNT_ID = "ACCOUNT_ID";
			
			public final String PRE_BILLING_DATE = "PRE_BILLING_DATE";
			public final String DUE_DATE = "DUE_DATE";
			public final String GRACE_PERIOD = "GRACE_PERIOD";
			
			public final String PRE_PURCHASE_BALANCE = "prePurchseBalance";
			public final String PRE_CASH_BALANCE = "preCashBalance";
			public final String PAYMENTS = "payments";
			public final String PURCHASES = "purchases";
			public final String CASH_ADVANCES = "cashAdvances";
			public final String CASH_ADVANCE_FEE = "cashAdvanceFee";
			public final String LATE_FEE = "lateFee";
			public final String OVER_LIMIT_FEE = "overLimitFee";
			public final String FINANCE_CHANGRE = "financeCharge";
			public final String NEW_PURCHASE_BALANCE = "newPurchseBalance";
			public final String NEW_CASH_BALANCE = "newCashBalance";
			public final String LAST_STATEMENT_DATE = "lastStatementDate";
			public final String LAST_STATEMENT_DUE_DATE = "lastStatementDueDate";
			public final String MINPAY_DUE_LASTSTATEMENT_DATE = "minpayDueLaststatementDate";
			public final String PAYMENT_FROM_LAST_STATEMENT = "paymentFromLastStatement";
			public final String CURRENT_MINPAYMENT_DUE = "currentMinPaymenetDue";
			public final String CREDIT_RETURNED_PURCHASES = "creditReturnedPurchases";
			public final String MINIMUM_PAYMENT = "minPayment";
			
			public final String PRE_BALANCE = "preBalance";
			public final String NEW_BALANCE = "newBalance";
		}
		
		public interface IReprtParams{
			public final String BANKNAME = "BANKNAME";
			public final String LOGO_PATH = "LOGO_PATH";
			
			public final String PRE_BILLING_DATE = "PRE_BILLING_DATE";
			public final String DUE_DATE = "DUE_DATE";
			public final String GRACE_PERIOD = "GRACE_PERIOD";

			public final String PRE_BALANCE = "PRE_BALANCE";
			public final String PAYMENTS = "PAYMENTS";
			public final String CREDITS = "CREDITS";
			public final String PURCHASE_OTHER_CHARGES = "PURCHASE_OTHER_CHARGES";
			public final String CASH_ADVANCE = "CASH_ADVANCE";
			public final String CASH_ADVANCE_FEE = "CASH_ADVANCE_FEE";
			public final String LATE_FEE = "LATE_FEE";
			public final String OVER_LIMIT_FEE = "OVER_LIMIT_FEE";
			public final String FINANCE_CHARGES = "FINANCE_CHARGES";
			public final String NEW_BALANCE = "NEW_BALANCE";
			public final String MINIMUM_PAYMENT = "MINIMUM_PAYMENT";
		}
	}
	
	public interface IDisputeManagement{
		public final String DIS_TYPE_CB = "Charge Back";
		public final String DIS_TYPE_RR = "Retrieval Request";
		public final String DIS_TYPE_CB_RE = "Charge Back Resend";

		public final String DIS_VALIDATE_RESCODE = "00";
		
		public final String DIS_INCOME_MASTER_NO_TRANX = "No Transaction found in the TRANXLOG table";
		public final String DIS_INCOME_MASTER_WRONG_RESCODE = "Both Response codes are NOT matching";
		public final String DIS_INCOME_MASTER_WRONG_AMT = "Both Amounts are NOT matching";
		public final String DIS_INCOME_MASTER_WRONG_CURRCODE = "Both Currency codes are NOT matching";
		public final String DIS_INCOME_MASTER_DELETED = "Transaction already Reversed";
		public final String DIS_INCOME_MASTER_RESPONSE_RECEIVED = "Response received from acquirer to raised dispute";
		public final String DIS_INCOME_MASTER_RESPONSE_NO_DISPUTE = "No dispute record found.";
		
		public final String DIS_INCOME_MASTER_TRANX_UPDATE_QUERY = "UPDATE TransactionLogDto SET recon=:reconStatus, reconDate=:rDate WHERE tranxLogId=:transactionId ";
		public final String DIS_INCOME_MASTER_DISPUTE_MASTER_UPDATE = "UPDATE DisputeMasterDto SET status=:disStatus, updatedDate=:uDate, updatedBy=:uBy WHERE disputeCaseNo=:disCaseNo ";
	}
	
	public interface IDateStuff{
		public final String START_TIME_PART = "00:00:00";
		public final String END_TIME_PART = "23:59:59";
		public final String DATETIME_FORMAT_DISPLAY = "dd/MM/yyyy HH:mi:ss AM";
		public final String DATETIME_FORMAT_VALIDATION = "dd/MM/yyyy HH24:mi:ss";
	}
	
	public interface IProperty{
		public final String PROPERTY_FILE = "cacisiss";
		
		public final String BG_TIME_TEST = "TestBatchProcess";
		public final String BG_TIME_BILLING = "BillingBatchProcess";
		public final String BG_TIME_CARD_CLOSE = "CardCloseBatchProcess";
		public final String BG_TIME_CARD_RENEWAL = "CardRenewalBatchProcess";
		public final String BG_TIME_CARD_CHANGE_CLOSE = "CardChangeCloseBatchProcess";
	}
	
	public interface IFee{
		
		public final String FEE_SRC = "Admin";
		
	}

}
