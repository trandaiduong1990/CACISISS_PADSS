package org.transinfo.cacis.common.constants;

public class CommonCodes {
	// Application Type Codes
	public static final int APPLICATIONTYPE_NEWCARD = 0;
	public static final int APPLICATIONTYPE_RENEWAL = 1;
	public static final int APPLICATIONTYPE_REPLACEMENT = 2;
	public static final int APPLICATIONTYPE_RENEWALNOTIFICATION = 3;

	public static final int APPLICATIONTYPE_NEWSUPPLEMENTARYCARD = 4;
	public static final int APPLICATIONTYPE_CARDCLOSE = 5;
	public static final int APPLICATIONTYPE_CARDLIMITADJUST = 6;
	public static final int APPLICATIONTYPE_CARDGRADE = 7;
	public static final int APPLICATIONTYPE_PINRESEND = 8;

	// Application status Codes
	public static final int APPLICATIONSTATUS_NEW = 0;
	public static final int APPLICATIONSTATUS_ACCEPTED = 1;
	public static final int APPLICATIONSTATUS_REJECTED = 2;
	public static final int APPLICATIONSTATUS_CLOSED = 3;
	public static final int APPLICATIONSTATUS_ACCEPTED_PROCESSED = 4;
	public static final int APPLICATIONSTATUS_PENDING = 5;
	public static final int APPLICATIONSTATUS_AUTHORIZED = 6;
	
	// CardStatus Codes
	public static final int CARD_ACTIVE = 0;
	public static final int CARD_NEW = 1;
	public static final int CARD_DATAEXPORT = 2;
	public static final int CARD_MADE = 3;
	public static final int PIN_MADE = 4;
	public static final int CARD_DELIVER = 5;
	public static final int CARD_REPLACEMENT = 6;
	public static final int CARD_CLOSE = 7;
	public static final int CARD_GREATER = 10;
	public static final int CARD_PIN_COUNT = 0;
	public static final char CARD_PIN_RESET = 'Y';
	public static final int CARD_MANUAL_SUSPENSION = 44;
	public static final int CARD_CLOSED_BY_BANK = 45;
	public static final int CARD_CLOSED_BY_CUST_REQUEST = 46;
	public static final char CARD_PIN_DISABLE = 'Y';

	// Card Holder Types
	public static final int PRIMARYCARD_HOLDER = 1;
	public static final int SUPPLEMENTARYCARD_HOLDER = 2;

	// Card Process Status Data
	public static final int CARD_PROCESS_NEW = 1;
	public static final int CARD_PROCESS_PROCESS = 2;
	public static final int CARD_PROCESS_CREATED = 3;
	public static final int CARD_PROCESS_REJECT = 4;
	public static final int CARD_PROCESS_RECEIVED = 5;
	public static final int CARD_PROCESS_NOT_EMBOSSED = 7;
	public static final int CARD_PROCESS_EMBOSSED = 6;
	public static final int CARD_PROCESS_NOT_PINMAILED = 9;
	public static final int CARD_PROCESS_PINMAILED = 8;
	public static final int CARD_PROCESS_DELIVERED = 10;
	public static final int CARD_PROCESS_NOT_DELIVERED = 11;
	
	// char values
	public static final Character CARD_PROCESS_RESEND_NOT_PINMAILED = 'N';
	public static final Character CARD_PROCESS_RESEND_PINMAILED = 'Y';

	// Call RecodLog For CallCenter(csr)
	public static final String CALLSTATUS_OPEN = "O";
	public static final String CALLSTATUS_CLOSE = "C";

	// For Address Type
	public static final String HOME_ADDRESS = "H";
	public static final String COMPANY_ADDRESS = "C";
	public static final String SUPPLEMENTARY_ADDRESS = "S";

	// For Letter Template Codes

	public static final String NEWCARD_APPLICATION = "CRDAPP";
	public static final String NEWCARD_REJECT_APPLICATION = "REJAPP";
	public static final String NEWSUPPCARD_APPLICATION = "SUPAPP";
	public static final String NEWSUPPCARD_REJECT_APPLICATION = "REJSUP";
	public static final String BILLINGADDRESS_CHANGE = "CUSADD";
	public static final String CARDREPLACE_APPLICATION = "CRDREP";
	public static final String CARDREPLACE_REJECT_APPLICATION = "REJREP";
	public static final String PINRESEND_APPLICATION = "PINRES";
	public static final String PINRESEND_REJECT_APPLICATION = "REJPIN";
	public static final String CARDCLOSE_APPLICATION = "CRDCLS";
	public static final String CARDCLOSE_REJECT_APPLICATION = "REJCLS";
	public static final String CARDRENEWAL_APPLICATION = "CRDREN";
	public static final String CARD_WRITEOFF = "WRIOFF";
	public static final String BILLPAYMENT_REMINDER = "BILLPA";
	public static final String CARDGRADE_APPLICATION = "CRDGRD";
	public static final String CARDGRADE_REJECT_APPLICATION = "REJGRD";
	public static final String CARDLIMITADJUST_APPLICATION = "LMTADJ";
	public static final String CARDLIMITADJUST_REJECT_APPLICATION = "REJLMT";
	public static final String NEWCARD_PENDING_APPLICATION = "PENAPP";

	// for Status List
	public static final String STATUS_ACTIVE = "A";
	public static final String STATUS_INACTIVE = "I";
	public static final String STATUS_BLOCKED = "B";
	public static final String STATUS_STOLEN = "S";
	public static final String STATUS_DEACTIVE = "D";

	// for uploadfile
	public static final String UPLOAD_SUCCESS = "S";
	// for Dispute Calim Form Status
	public static final char CLAIM_NEW = 'N';
	public static final char CLAIM_PROCESS = 'P';
	public static final char CLAIM_CLOSE = 'C';
	// for Dispute Calim Form DocumentUpload Status
	public static final char DOCSUPLOADED_YES = 'Y';
	public static final char DOCSUPLOADED_NO = 'N';
	
	//card change status
	public static final int CARD_CHANGE_REQUESTED = 0;
	public static final int CARD_CHANGE_ACCEPTED = 1;
	public static final int CARD_CHANGE_REJECTED = 2;
	public static final int CARD_CHANGE_PROCESSED = 3;
	
	// Card Batch Status
	public static final String BATCH_COMPLETED = "C";
	public static final String BATCH_COMPLETED_PARTIAl = "P";
	public static final String BATCH_NEW = "N";
	
	// Replacement log flag
	public static final char LOG_OLD_NO_INSERTED = '0';
	public static final char LOG_NEW_NO_UPDATED = '1';
	
	// card embossing new card for
	public static final char NEW_CARD_FOR_REPLACEMENT = 'R';
	public static final char NEW_CARD_FOR_RENEWAL = 'W';
	
	// Card product Types
	public static final String CPT_CREDIT = "CreditCard";
	public static final String CPT_DEBIT = "DebitCard";
	public static final String CPT_ATM = "ATMCard";
	
	// GL Types
	public static final String GL_DEBIT = "D";
	public static final String GL_CREDIT = "C";
	
	// Transaction Types
	public static final String TRANX_WITHDRAWAL			=	"WLD";
	public static final String TRANX_BALANCE_ENQUIRY	=	"BAL";
	public static final String TRANX_TRANX_REVERSAL		=	"REVERSAL";
	public static final String TRANX_TRANX_VOID			=	"VOID";
	public static final String TRANX_PURCHASE			=	"PUR";
	public static final String TRANX_CASH_ADVANCE		=	"CSH";
	public static final String TRANX_REPLACEMENT_FEE	=	"RF";
	public static final String TRANX_RENEWAL_FEE		=	"RNF";
	public static final String TRANX_PREAUTH			=	"PA";
	public static final String TRANX_PREAUTH_COMPLETE	=	"PAC";
	public static final String TRANX_REFUND				=	"RFD";
	
	// GL Records Billed
	public static final String GL_RECORD_BILLED			=	"Y";
	public static final String GL_RECORD_NOT_BILLED		=	"N";
	
	// Processing Hosts
	public static final String PH_CORE_BANKING			=	"C";
	
	// Debit processing status
	public static final String DEBIT_PROCESS_STATUS_NO	=	"N";
	public static final String DEBIT_PROCESS_STATUS_YES	=	"Y";
	
	//card renewal status
	public static final int CARD_RENEWAl_REQUESTED = 0;
	public static final int CARD_RENEWAl_ACCEPTED = 1;
	public static final int CARD_RENEWAl_REJECTED = 2;
	public static final int CARD_RENEWAl_PROCESSED = 3;
	
	//card status
	public static final String CARD_STATUS_NEW = "0";
	public static final String CARD_STATUS_CLOSED = "1";
	
	// card STATUS column
	public static final String CARD_SACTIVE = "A";
	public static final String CARD_SCLOSED = "C";
	
	// RECON status
	public static final String RECON_MANUAL = "M";

	public static final String TRANX_REVERT = "G";
	
	// Fee Source
	public static final String FEE_SRC_ADMIN = "Admin Module";
	public static final String FEE_SRC_CLEARENCE = "Clearence File";
	
	// Type Screen
	public static final String TYPE_SCREEN_ADDNEW = "addNew";
	public static final String TYPE_SCREEN_ADD = "add";
	public static final String TYPE_SCREEN_CHANGE = "change";
	public static final String TYPE_SCREEN_UPDATE = "update";
	public static final String TYPE_SCREEN_SEARCH = "search";
	
	// Appl Validation
	public static final String AGE = "AGE";
		
}
