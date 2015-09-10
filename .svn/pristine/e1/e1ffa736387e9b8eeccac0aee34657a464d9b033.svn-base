package org.transinfo.cacis;

public class ErrorCodes
{

    public static final int MIN_INDEX               =   0;
    public static final int APPLICATION_ERROR       =   MIN_INDEX ;
    public static final int RECORD_ALREADY_EXIST    =   MIN_INDEX + 1;
    public static final int RECORD_NOT_EXIST        =   MIN_INDEX + 2;
    public static final int NO_RECORDS_FOUND        =   MIN_INDEX + 3;
    public static final int ADD_SUCCESS             =   MIN_INDEX + 4;
    public static final int CHANGE_SUCCESS          =   MIN_INDEX + 5;
    public static final int DELETE_SUCCESS          =   MIN_INDEX + 6;
    public static final int ADD_FAIL                =   MIN_INDEX + 7;
    public static final int CHANGE_FAIL             =   MIN_INDEX + 8;
    public static final int DELETE_FAIL             =   MIN_INDEX + 9;
    public static final int BAD_PASSWORD            =   MIN_INDEX + 10;
    public static final int USER_ALREADY_ACTIVATED  =   MIN_INDEX + 11;
    public static final int INVALID_OLD_PASSWORD    =   MIN_INDEX + 12;
    public static final int PASSWORD_EXPIRY_NOT_DEFINED =   MIN_INDEX + 13;
    public static final int INVALID_LOGIN_ATTEMPT       =   MIN_INDEX + 14;
    public static final int USER_INACTIVE               =   MIN_INDEX + 15;
    public static final int USER_LOCKED                 =   MIN_INDEX + 16;
    public static final int SERVER_STOPED               =   MIN_INDEX + 17;
    public static final int SERVER_STARTED              =   MIN_INDEX + 18;
    public static final int CARD_TYPE_EXIST             =   MIN_INDEX + 19;
    public static final int TAG_FIELD_EXIST             =   MIN_INDEX + 20;
    public static final int ROLE_EXIST                  =   MIN_INDEX + 21;
    public static final int DUPLICATE_CARD_FOUND        =   MIN_INDEX + 22;
    public static final int CARD_RECORD_EXIST           =   MIN_INDEX + 23;
    public static final int INVALID_CARD_NUMBER         =   MIN_INDEX + 24;
    public static final int UNKNOWN_DATA_TYPE       =   MIN_INDEX + 25;
    public static final int XML_FAIL               =   MIN_INDEX + 26;
    public static final int REQUEST_FAIL              =  MIN_INDEX + 27;
    public static final int PACKAGE_FAIL             =  MIN_INDEX + 28;
    public static final int SQL_QUERY_ERR           =   MIN_INDEX + 29;
     public static final int QUERY_ERR           =   MIN_INDEX + 30;
    public static final int PARAMETER_ERR           =   MIN_INDEX + 31;
     public static final int NO_DATA           =   MIN_INDEX + 32;
    public static final int PASSWORD_EXPIRED=   MIN_INDEX + 33;
    public static final int FIRSTTIMELOGIN_EXPIRED=   MIN_INDEX + 34;
     public static final int UNAUTHORIZED_USER=   MIN_INDEX + 35;
     public static final int PAYMENT_NOTPROCESSD=   MIN_INDEX + 36;
     public static final int PAYMENT_CONFIRMED=   MIN_INDEX + 37;
     public static final int CUSTOMER_RECORD_EXIST  =   MIN_INDEX + 38;
     public static final int CONFIRM_SUCCESS        =   MIN_INDEX + 39;
     public static final int CONFIRM_FAIL           =   MIN_INDEX + 40;
     public static final int REJECT_SUCCESS         =   MIN_INDEX + 41;
     public static final int REJECT_FAIL            =   MIN_INDEX + 42;
     public static final int ACTIVE_SUCCESS           =   MIN_INDEX + 43;
     public static final int ACTIVE_FAIL           =   MIN_INDEX + 44;
	 public static final int CARD_PROCESS_VALIDATION_FAIL =  MIN_INDEX + 45;
	 public static final int ACCEPTION_SUCCESS  =  MIN_INDEX + 46;
     public static final int ACCEPTION_FAIL      =  MIN_INDEX + 47;
     public static final int CLOSE_SUCCESS  =  MIN_INDEX + 48;
     public static final int CLOSE_FAIL      =  MIN_INDEX + 49;
     public static final int CARDPERSONALIZATION_SUCCESS  =  MIN_INDEX + 50;
     public static final int CARDPERSONALIZATION_FAIL      =  MIN_INDEX + 51;

     public static final int INTERESTFEECAl_ERR      =  MIN_INDEX + 52;
	 public static final int INTERESTFEECAl_NO      =  MIN_INDEX + 53;
	 public static final int INTERESTFEECAl_SUCCESS      =  MIN_INDEX + 54;
	 public static final int   ONLINEFILE_SUCCESS      =  MIN_INDEX + 55;
	 public static final int ONLINEFILE_FAIL      =  MIN_INDEX + 56;
	 public static final int CHARGEBACK_SUCCESS      =  MIN_INDEX + 57;
	 public static final int CHARGEBACK_FAIL      =  MIN_INDEX + 58;


     public static final int MAX_INDEX               =   MIN_INDEX + 59; //spr001


    private static String[] sArrError;

    static{
        sArrError   =   new String[MAX_INDEX];
        sArrError[APPLICATION_ERROR]        =   "Application Error. Please Contact Adminstrator,";
        sArrError[RECORD_ALREADY_EXIST]     =   "Record Already Exist";
        sArrError[RECORD_NOT_EXIST]     =   "Record Not Exist";
        sArrError[NO_RECORDS_FOUND]     =   "No Records Found";
        sArrError[ADD_SUCCESS]     =   "Add Successful";
        sArrError[CHANGE_SUCCESS]     =   "Change Successful:";
        sArrError[DELETE_SUCCESS]     =   "Deletion Successful";
        sArrError[ADD_FAIL]    		  =   "Add Fail";
        sArrError[CHANGE_FAIL]        =   "Change Fail:";
        sArrError[DELETE_FAIL]        =   "Delete Fail";
        sArrError[BAD_PASSWORD]       =   "The Password matches with Previous password, Please enter different one.";
        sArrError[USER_ALREADY_ACTIVATED]     =   "User already activated.";
        sArrError[INVALID_OLD_PASSWORD] = "Invalid Old Password.";
        sArrError[PASSWORD_EXPIRY_NOT_DEFINED] =   "Password Expiry Period Not Defined";
        sArrError[INVALID_LOGIN_ATTEMPT]       =   "Invalid User/Password";
        sArrError[USER_INACTIVE ]              =   "User Inactive";
        sArrError[USER_LOCKED ]                =   "User Locked";
        sArrError[CARD_TYPE_EXIST]             =  "Deletion Fail. Card Type(s) are reffered by AC Data List or Key Index. ";
        sArrError[TAG_FIELD_EXIST]             =  "Deletion Fail. Tag(s) are reffered by AC Data List. ";
        sArrError[ROLE_EXIST]                   =   "Deletion Fail. Role(s) are reffered by Users. ";
        sArrError[DUPLICATE_CARD_FOUND]         =   "Duplicate card(s) found.";
        sArrError[CARD_RECORD_EXIST]     =   "Card record(s) already exist";
        sArrError[INVALID_CARD_NUMBER] = "Invalid card(s) exist";
        sArrError[UNKNOWN_DATA_TYPE ]          =   "Unknown Datatype";
        sArrError[REQUEST_FAIL ]                ="Failed to get Input Parameters";
        sArrError[PACKAGE_FAIL ]                ="Failed to find the Package Name";
        sArrError[QUERY_ERR ]                   =  "Database error while retrieving data";
        sArrError[PARAMETER_ERR ]                ="Failed to get parameters for the Report";
        sArrError[NO_DATA]                     ="No data for this report Details.";
        sArrError[PASSWORD_EXPIRED]  ="Password Expired";
        sArrError[FIRSTTIMELOGIN_EXPIRED]  ="First time login activation expired, please contact administrator.";
        sArrError[PAYMENT_NOTPROCESSD]  ="Payment Not Processed";
         sArrError[PAYMENT_CONFIRMED]  ="Payment Confirmed";
        sArrError[UNAUTHORIZED_USER]  ="UnAuthorized User,please contact Administrator.";
        sArrError[CUSTOMER_RECORD_EXIST]     =   "Customer record(s) already exist";
        sArrError[CONFIRM_SUCCESS]     =   "Conformation Successful";
        sArrError[CONFIRM_FAIL]     =   "Conformation Fail";
        sArrError[REJECT_SUCCESS]     =   "Reject Successful";
        sArrError[REJECT_FAIL]     =   "Reject Fail";
        sArrError[ACTIVE_SUCCESS]     =   "Activation Successful";
        sArrError[ACTIVE_FAIL]     =   "Activation Fail";
        sArrError[CARD_PROCESS_VALIDATION_FAIL]     =   "Card Processing Validation Failed\n";
		sArrError[ACCEPTION_SUCCESS]     =   "ApplicationForm Acception Success: ";
		sArrError[ACCEPTION_FAIL]     =   "ApplicationForm Acception Fail: ";
		sArrError[CLOSE_SUCCESS]     =   " Close Success: ";
		sArrError[CLOSE_FAIL]     =   "Close Fail: ";
		sArrError[CARDPERSONALIZATION_SUCCESS]     = "Operation Succeed ";//for all card personalization operations
		sArrError[CARDPERSONALIZATION_FAIL]        = "Operation Failed ";

		sArrError[INTERESTFEECAl_ERR]         = "There is no billing cycle data." ;
		sArrError[INTERESTFEECAl_NO]          = "Today is not for Interest Calculation. ";
		sArrError[INTERESTFEECAl_SUCCESS]      = "Processing the record Successful" ;

		sArrError[ONLINEFILE_SUCCESS]      ="Operation Succeed.";
		sArrError[ONLINEFILE_FAIL]      ="Operation Fail.";

		sArrError[CHARGEBACK_SUCCESS]      ="ChargeBack Succeed.";
		sArrError[CHARGEBACK_FAIL]      ="ChargeBack Fail.";
    }

    public static String getErrorMessage(String errorIndex){
        int errIndex    =   Integer.parseInt(errorIndex);
        return sArrError[errIndex];
    }

    public static String getErrorMessage(int errorIndex){
        return sArrError[errorIndex];
    }
}
