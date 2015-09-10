/*
* Copyright (c) 2001-2002 transinfo Pte Ltd. Singapore. All Rights Reserved.
* This work contains trade secrets and confidential material of  transinfo Pte Ltd. Singapore.
* and its use of disclosure in whole or in part without express written permission of
* transinfo Pte Ltd. Singapore. is prohibited.
*
* File Name          : TPlusCodes.java
* Author             : I.T. Solutions (Singapore) Pte Ltd.
* Date of Creation   : Jan 11, 2002
* Description        : This class contains all the error codes used in TPlus.
* Version Number     : 1.0
*					Modification History:
* Date 		 Version No.			Modified By  	 	  Modification Details.
*
*
*17-09-2002	  1.0.2					Sock Hoon				Added version 1.0.2
*/


package org.transinfo.cacis;


import java.util.HashMap;


/**
* This class contains all the error codes & description used in TPlus
*/

public class TPlusCodes
{

// Satya for TPLUS
// Error Codes Related to the DataBase Access

	public static final String CLIENT_REQUEST_TYPE ="CLIENTREQUEST";
	public static final String ISSUER_RESPONSE_TYPE ="ISSUERRESPONSE";




	/******************** Related to TPlus Server *******************/
	public static final String LOG_FILE_ERR				= "251";
	public static final String SVR_SOCK_FAIL			= "252";
	public static final String SVR_START_FAIL			= "253";
	public static final String INI_FILE_ERR				= "254";
	public static final String KEY_NOT_FOUND			= "255";
	public static final String IO_ERR_INI				= "256";
	public static final String INVALID_PARAM			= "257";
	public static final String ENCR_DCR_FAIL			= "258";
	public static final String IO_ERR_INPUT				= "259";
	public static final String IO_ERR_OUTPUT			= "260";
	public static final String UNKNOWN_ERR				= "261";
	public static final String ERR_ADM_SOCK				= "263";
	public static final String ERR_SVR_SOCK				= "264";
	public static final String ERR_REQ					= "265";
	public static final String REQ_KILL					= "266";
	public static final String THREAD_EXP				= "267";
	public static final String IO_EXP_SVR				= "268";
	public static final String ERR_ADM_REQ				= "269";
	public static final String EN_DEC_FAIL				= "270";

	public static final String ACS_CONN_TIME_OUT		= "281";
	public static final String INVALID_ACS_URL			= "282";
	public static final String SOCK_ACS_FAIL			= "283";
	public static final String CONN_ACS_FAIL			= "284";


	public static final String CONN_TIME_OUT			= "291";
	public static final String INVALID_VD_URL			= "292";
	public static final String SOCK_VD_FAIL				= "293";
	public static final String CONN_VD_FAIL				= "294";

	//added for TPlusConnectionWrapper
	public static final String URL_CONN_TIME_OUT		= "301";
	public static final String URL_INVALID				= "302";
	public static final String URL_SOCK_FAIL			= "303";
	public static final String URL_CONN_FAIL			= "304";

	/*************************************************************/




	/***************** Related to Messaging *********************/
	public static final String XML_PARSING_FATAL 		= "401";
	public static final String XML_IOEXCEPTION 			= "402";
	public static final String UNKNOWN_SAX_ERR			= "403";
	public static final String XML_PARSING_WARNING 		= "404";
	public static final String XML_PARSING_ERROR 		= "405";
	public static final String UNRECOGN_CRITICAL 		= "406";
	public static final String UNSUP_VERSION 			= "407";
	public static final String INVALID_MSGID_ERR		= "408";
	public static final String ELE_MISSING 				= "409";
	public static final String ELE_MISSING_CRRES 		= "410";
	public static final String ELE_MISSING_VERES		= "411";
	public static final String ELE_MISSING_PARES		= "412";
	public static final String INVALID_DATA				= "413";
	public static final String INVALID_DATA_CRRES		= "414";
	public static final String INVALID_DATA_VERES		= "415";
	public static final String INVALID_DATA_PARES 		= "416";
	public static final String UNHANDLED_ERR 			= "417";
	public static final String IREQ_RESP				= "418";
	public static final String ELE_MISSING_GEN			= "419";
	public static final String DATA_MISSING_PAREQ		= "420";
	public static final String INVALID_ERR_CODE			= "421";
	public static final String SIGN_FAILED				= "422";
	public static final String ENCODING_FAILED			= "423";
	public static final String PARES_GEN_FAIL			= "430";
	public static final String SIGN_GEN_FAILED			= "431";
	public static final String ELE_MISSING_CPRS			= "432";
	public static final String CPRS_GEN_FAILED			= "433";
	public static final String SAXPARSER_ERR			= "434";
	public static final String CLASS_NOT_FOUND			= "435";
	public static final String SYSTEM_ERR				= "436";
	public static final String ACS_POST_ERR				= "438";
	public static final String ERR_EXTN_TAG				= "439";
	public static final String UNSUP_HIGHER_VERSION		= "440";
	public static final String INVALID_DATA_CPRS		= "442";

	/*************************************************************/


	/******************** Related to Session *********************/
	public static final String SESSION_CREATION_FAIL	= "500";
	public static final String SESSID_NOT_FOUND			= "501";
	public static final String SESSION_CLEANUP			= "502";

	/*************************************************************/




	/*********************** TPlus Return Codes **********************************/

	/******************** 3D Secure Error Codes *******************/
	public static final String ROOT_ELE_INVALID	 				= "001";
	public static final String MSG_ELE_UNDEFINED	 			= "002";
	public static final String REQ_ELE_MISSING 					= "003";
	public static final String CRITICAL_ELE_UNRECOGNIZED	 	= "004";
	public static final String FORMAT_ELE_INVALID 				= "005";
	public static final String VERSION_TOO_OLD 					= "006";
	public static final String ACS_PERMANENT_DOWN				= "007";

	/******************** 3D Secure IReq Codes ********************/
	public static final String ACQ_NOT_PART	 					= "050";
	public static final String MERCH_NOT_PART 					= "051";
	public static final String NO_PWD		 					= "052";
	public static final String INVALID_PWD		 				= "053";
	public static final String INVALID_ISO_CODE	 				= "054";
	public static final String TRAN_DATA_INVALID				= "055";
	public static final String PAREQ_ROUTE_ERR 					= "056";

	//Added by Sock Hoon
	//public static final String PAREQ_SERIAL_UNLOCATED 		= "057";
	public static final String PAREQ_TRANSIENT_SYSTEM_FAIL		= "098";
	public static final String PAREQ_PERMANENT_SYSTEM_FAIL		= "099";

	//Added by Satya for compliance
	public static final String SERIAL_ELE_MISSING 				= "057";

	//EOC

	public static final String AUTH_SUCCESS 					= "000";
	public static final String AUTH_ATTEMPT 					= "150";

	/******************* TPlus defined return codes ******************/

	public static final String PAN_NOT_PART 					= "100";
	public static final String AUTH_FAIL_VERES	 				= "101";
	public static final String AUTH_UNAVAIL_VERES 				= "102";
	public static final String PROTOCOL_NOT_3DS		 			= "103";
	public static final String INVALID_MSGID 					= "104";
	public static final String AUTH_FAIL_PARES 					= "105";
	public static final String AUTH_UNAVAIL_PARES 				= "106";
	public static final String INVALID_SESSID 					= "107";
	public static final String INVALID_PARES_PAN				= "108";
	public static final String INVALID_CAVV_VALUE				= "109";

	public static final String TPlus_SYS_ERR	 				= "200";
	public static final String INVALID_REQ	 					= "201";
	public static final String INVALID_INPUT	 				= "203";
	public static final String INVALID_EXT_SESSID				= "204";
	public static final String INVALID_TRAN_DET	 				= "205";
	public static final String CHECK_DIG_FAIL 					= "207";
	public static final String SMS_ACS_POST_ERR	 				= "208";
	public static final String INV_ACS_RES	 					= "209";
	public static final String INV_URL_RES	 					= "210";

	/*******************************************************************/

	//Hashmap to contain all the error codes and the description
	private static HashMap hmpErrorCodes = new HashMap();

	//Hashmap to contain all the return codes and the description
	private static HashMap hmpReturnCodes = new HashMap();

	static{


		/***************** Related to Messaging *********************/
		hmpErrorCodes.put(XML_PARSING_FATAL,"TPlus XML Parsing Fatal Exception.");
		hmpErrorCodes.put(XML_IOEXCEPTION,"TPlus XML Parsing IOException.");
		hmpErrorCodes.put(UNKNOWN_SAX_ERR,"TPlus XML Parsing Fatal Exception.");
		hmpErrorCodes.put(XML_PARSING_WARNING,"TPlus XML Parsing Warning.");
		hmpErrorCodes.put(XML_PARSING_ERROR,"TPlus XML Parsing Not Fatal Error.");
		hmpErrorCodes.put(UNRECOGN_CRITICAL,"Unrecognized Element With Critical Attribute as true.");
		hmpErrorCodes.put(UNSUP_VERSION,"Unsupported ThreeDSecure Version Number.");
		hmpErrorCodes.put(ACS_PERMANENT_DOWN,"ACS System Failure,No further transaction can taken place.");

		//Added by Sock Hoon
		hmpErrorCodes.put(UNSUP_HIGHER_VERSION,"Unsupported ThreeDSecure Higher Version Number.");
		//EOC

		hmpErrorCodes.put(INVALID_MSGID_ERR,"Invalid MessageID ");
		hmpErrorCodes.put(ELE_MISSING,"Mandatory Element Missing in Response XML.");
		hmpErrorCodes.put(ELE_MISSING_CRRES,"Mandatory Data Missing in the CERES ResponseXML.");
		hmpErrorCodes.put(ELE_MISSING_VERES,"Mandatory Data Missing in VERes XML.");
		hmpErrorCodes.put(ELE_MISSING_PARES,"Mandatory Data Missing in the PARes XML.");
		hmpErrorCodes.put(INVALID_DATA,"Invalid Data in ISO Request.");
		hmpErrorCodes.put(INVALID_DATA_CRRES,"Invalid Data in CRRes XML.");
		hmpErrorCodes.put(INVALID_DATA_VERES,"Invalid Data in VERes XML.");
		hmpErrorCodes.put(INVALID_DATA_PARES,"Invalid Data in PARes XML.");
		hmpErrorCodes.put(UNHANDLED_ERR,"Unhandled Error in CRRes/Cachehandler.");
		hmpErrorCodes.put(IREQ_RESP,"IReq Response for CRRes.");
		hmpErrorCodes.put(ELE_MISSING_GEN,"XML Generation Failed due to Mandatory element is missing.");
		hmpErrorCodes.put(DATA_MISSING_PAREQ,"PaReq XML Generation Failed.Required Data not availlable.");
		hmpErrorCodes.put(INVALID_ERR_CODE,"Invalid Error Code/IReqCode.");
		hmpErrorCodes.put(SIGN_FAILED,"Signature Validation Failed.");
		hmpErrorCodes.put(ENCODING_FAILED,"Encoding/Compression Failed.");
		hmpErrorCodes.put(PARES_GEN_FAIL,"PARes XML Generation from CPRS Failed.Required Data not available.");
		hmpErrorCodes.put(SIGN_GEN_FAILED,"PARes Signature Element Generation failed.");
		hmpErrorCodes.put(ELE_MISSING_CPRS,"Mandatory Element Missing in CPRS XML.");
		hmpErrorCodes.put(CPRS_GEN_FAILED,"Error While generating PARes XML from CPRS XML.");
		hmpErrorCodes.put(SAXPARSER_ERR,"TPlusSaxParser Error when parsing CRRes.");
		hmpErrorCodes.put(CLASS_NOT_FOUND,"Selected Version class not found.");
		hmpErrorCodes.put(SYSTEM_ERR,"System Error while trying to instantiate the class.");
		hmpErrorCodes.put(ACS_POST_ERR,"ACS POST to the merchant server failed.");
		hmpErrorCodes.put(ERR_EXTN_TAG,"Error while generating Extension tag.");

		//Added by Sock Hoon
		hmpErrorCodes.put(INVALID_DATA_CPRS,"Invalid Data in CPRS XML.");

		/*************************************************************/



		/******************** Related to Session *********************/
		hmpErrorCodes.put(SESSION_CREATION_FAIL,"Session could not be created.");
		hmpErrorCodes.put(SESSID_NOT_FOUND,"Session id not found in the session table.");
		hmpErrorCodes.put(SESSION_CLEANUP,"Session Cleanup.");
		/*************************************************************/


		/******************** Related to TPlus Server *******************/
		hmpErrorCodes.put(LOG_FILE_ERR,"Could not open the log files.");
		hmpErrorCodes.put(SVR_SOCK_FAIL,"Unable to create Server Sockets.");
		hmpErrorCodes.put(SVR_START_FAIL,"Unable to start the TPlusServerDaemon.");
		hmpErrorCodes.put(INI_FILE_ERR,"Unable to read the TPlusConfig.ini or TPlusConfigLimits.ini.");
		hmpErrorCodes.put(KEY_NOT_FOUND,"Could not find the master key file.");
		hmpErrorCodes.put(IO_ERR_INI,"IOError while reading the config file.");
		hmpErrorCodes.put(INVALID_PARAM,"Configuration parameter entry invalid.");
		hmpErrorCodes.put(ENCR_DCR_FAIL,"Could not encrypt/decrypt as the key is corrupted or not available.");
		hmpErrorCodes.put(IO_ERR_INPUT,"IOError occurred while trying to read the input.");
		hmpErrorCodes.put(UNKNOWN_ERR,"Unknown error occurred while starting the TPlusServerDaemon.");
		hmpErrorCodes.put(ERR_ADM_SOCK,"Error while closing the Admin Server.");
		hmpErrorCodes.put(ERR_SVR_SOCK,"Error while closing the TPlusServer.");
		hmpErrorCodes.put(ERR_REQ,"Unknown error occurred while servicing the request.");
		hmpErrorCodes.put(REQ_KILL,"Request killed, since the system is shutting down.");
		hmpErrorCodes.put(THREAD_EXP,"Threadpool exception occurred when adding request to the request queue.");
		hmpErrorCodes.put(IO_EXP_SVR,"IOException occurred when listening to the serverport.");
		hmpErrorCodes.put(ERR_ADM_REQ,"Error while servicing admin request.");
		hmpErrorCodes.put(EN_DEC_FAIL,"Encoding/Decoding failed.");

		hmpErrorCodes.put(ACS_CONN_TIME_OUT,"HTTPS Connection to ACS Timed Out.");
		hmpErrorCodes.put(INVALID_ACS_URL,"Invalid ACS URL.");
		hmpErrorCodes.put(SOCK_ACS_FAIL,"Could not establish socket connection to ACS.");
		hmpErrorCodes.put(CONN_ACS_FAIL,"Could not connect to ACS.");


		hmpErrorCodes.put(CONN_TIME_OUT,"HTTPS Connection to Visa Directory Timed Out.");
		hmpErrorCodes.put(INVALID_VD_URL,"Invalid Visa Directory URL.");
		hmpErrorCodes.put(SOCK_VD_FAIL,"Could not establish socket connection to Visa Directory.");
		hmpErrorCodes.put(CONN_VD_FAIL,"Could not connect to Visa Directory.");

		hmpErrorCodes.put(URL_CONN_TIME_OUT,"HTTPS Connection Timed Out.");
		hmpErrorCodes.put(URL_INVALID,"Invalid URL.");
		hmpErrorCodes.put(URL_SOCK_FAIL,"Could not establish socket connection to URL.");
		hmpErrorCodes.put(URL_CONN_FAIL,"Could not connect to URL.");

		/*************************************************************/

	}

	static{

		hmpReturnCodes.put(ROOT_ELE_INVALID,"Root element invalid not recognized.");
		hmpReturnCodes.put(MSG_ELE_UNDEFINED,"Message element not a defined message.");
		hmpReturnCodes.put(REQ_ELE_MISSING,"Required element missing.");
		hmpReturnCodes.put(CRITICAL_ELE_UNRECOGNIZED,"Critical element not recognized.");
		hmpReturnCodes.put(FORMAT_ELE_INVALID,"Format of one or more elements is invalid according to the specification.");
		hmpReturnCodes.put(VERSION_TOO_OLD,"Protocol version too old.");
		hmpReturnCodes.put(ACQ_NOT_PART,"Acquirer not participating in 3-D Secure.");
		hmpReturnCodes.put(MERCH_NOT_PART,"Merchant not participating in 3-D Secure (based on Acquirer Bin & Merchant ID).");
		hmpReturnCodes.put(NO_PWD,"Password required but not password supplied.");
		hmpReturnCodes.put(INVALID_PWD,"Supplied password is not valid for combination of Acquirer BIN and Merchant ID.");
		hmpReturnCodes.put(INVALID_ISO_CODE,"ISO code not valid per ISO tables (for either country or currency).");
		hmpReturnCodes.put(TRAN_DATA_INVALID,"Transaction data not valid. For example: purchase amount <> display amount, PAReq.acctid <> VEReq.acctid.");
		hmpReturnCodes.put(PAREQ_ROUTE_ERR,"PAReq was incorrectly routed; either: The PAReq was received by the wrong ACS, The PAReq should never have been sent, based on the values in the VERes.");

		//Added by Sock Hoon
		hmpReturnCodes.put(PAREQ_TRANSIENT_SYSTEM_FAIL,"Transient System Failure.");
		hmpReturnCodes.put(PAREQ_PERMANENT_SYSTEM_FAIL,"Permanent System Failure.");
		//EOC

		hmpReturnCodes.put(AUTH_SUCCESS,"Authentication succeeded.");
		hmpReturnCodes.put(PAN_NOT_PART,"PAN not participating based on card range (cache).");
		hmpReturnCodes.put(AUTH_FAIL_VERES,"Authentication Failed.Participated card is not Enrolled.");
		hmpReturnCodes.put(AUTH_UNAVAIL_VERES,"Enrolled cardholder, but authentication is currently unavailable.  The enrolled has the value U.");
		hmpReturnCodes.put(PROTOCOL_NOT_3DS,"Protocol not 3-D secure. (In the VERes).");
		hmpReturnCodes.put(INVALID_MSGID,"Message ID different in VEReq.");
		hmpReturnCodes.put(AUTH_FAIL_PARES,"Authentication Failed (PARes) Transaction Status is ‘N’ in the PARes. No IReq is available.");
		hmpReturnCodes.put(AUTH_UNAVAIL_PARES,"Authentication Not Available (PARes) Transaction Status is U in the PARes.");
		hmpReturnCodes.put(INVALID_SESSID,"Invalid Session ID (PARes).");
		hmpReturnCodes.put(INVALID_PARES_PAN,"PAN available in PARes is not same as the PAN submited to VisaDirectory.");
		hmpReturnCodes.put(INVALID_CAVV_VALUE,"Invalid CAVV value.");

		hmpReturnCodes.put(TPlus_SYS_ERR,"TPlus System Error.");
		hmpReturnCodes.put(INVALID_REQ,"Invalid Request (Request type not identifiable).");
		hmpReturnCodes.put(INVALID_INPUT,"Invalid input from the script.");
		hmpReturnCodes.put(INVALID_EXT_SESSID,"Invalid External Session ID (PARes).");
		hmpReturnCodes.put(INVALID_TRAN_DET,"Transaction detail missing or invalid.");
		hmpReturnCodes.put(CHECK_DIG_FAIL,"Invalid PAN, check digit validation failed.");
		hmpReturnCodes.put(SMS_ACS_POST_ERR,"ACS POST failed at TPlus for SMS request.");
		hmpReturnCodes.put(INV_ACS_RES,"The Response status returned by ACS while posting PaReq is not 200 OK.");
		hmpReturnCodes.put(INV_URL_RES,"The Response status returned by URL while posting PaReq is not 200 OK.");
	}



	/**************** Error codes defined in the Spec *******************/
	// This will be used to generate the error XML when the response from
	// Visa Directory or the ACS is invalid

	public static final String ThreeDS_ROOT_ELE_INVALID	 		= "1";
	public static final String ThreeDS_MSG_ELE_UNDEFINED	 	= "2";
	public static final String ThreeDS_REQ_ELE_MISSING 			= "3";
	public static final String ThreeDS_CRITICAL_ELE_UNRECOGN 	= "4";
	public static final String ThreeDS_FORMAT_ELE_INVALID 		= "5";
	public static final String ThreeDS_VER_TOO_OLD	 			= "6";

	//Added by Sock Hoon
	public static final String ThreeDS_TRANSIENT_SYSTEM_FAIL	= "98";
	public static final String ThreeDS_PERMANENT_SYSTEM_FAIL	= "99";
	//EOC

	//Added by Satya
	public static String ACS_PRESENT_ERROR_CODE = "0";
	public static final String ACS_PERMENT_ERROR_CODE = "99";

	/****************************** End *********************************/


	/****************** Map Visa Spec Error Codes to TPlus error codes *****************/

	// This will be used to generate the error XML when the response from
	// Visa Directory or the ACS is invalid
	public static HashMap hmpMapCodes = new HashMap();
	public static HashMap hmpThreeDSErrorCodes = new HashMap();


	static{

		//Changed by Sock Hoon
		//hmpMapCodes.put(XML_PARSING_FATAL,ThreeDS_FORMAT_ELE_INVALID);

		hmpMapCodes.put(XML_PARSING_FATAL,ThreeDS_REQ_ELE_MISSING);
		hmpMapCodes.put(ROOT_ELE_INVALID,ThreeDS_ROOT_ELE_INVALID);
		hmpMapCodes.put(MSG_ELE_UNDEFINED,ThreeDS_MSG_ELE_UNDEFINED);


		//EOC

		hmpMapCodes.put(XML_IOEXCEPTION,ThreeDS_REQ_ELE_MISSING);
		hmpMapCodes.put(UNKNOWN_SAX_ERR,ThreeDS_REQ_ELE_MISSING);
		hmpMapCodes.put(UNRECOGN_CRITICAL,ThreeDS_CRITICAL_ELE_UNRECOGN);
		hmpMapCodes.put(UNSUP_VERSION,ThreeDS_VER_TOO_OLD);

		//Added by Sock Hoon
		hmpMapCodes.put(UNSUP_HIGHER_VERSION,ThreeDS_FORMAT_ELE_INVALID);
		//EOC

		hmpMapCodes.put(ELE_MISSING,ThreeDS_REQ_ELE_MISSING);
		hmpMapCodes.put(ELE_MISSING_CRRES,ThreeDS_REQ_ELE_MISSING);
		hmpMapCodes.put(ELE_MISSING_VERES,ThreeDS_REQ_ELE_MISSING);
		hmpMapCodes.put(ELE_MISSING_PARES,ThreeDS_REQ_ELE_MISSING);
		hmpMapCodes.put(INVALID_DATA,ThreeDS_FORMAT_ELE_INVALID);
		hmpMapCodes.put(INVALID_DATA_CRRES,ThreeDS_FORMAT_ELE_INVALID);
		hmpMapCodes.put(INVALID_DATA_VERES,ThreeDS_FORMAT_ELE_INVALID);
		hmpMapCodes.put(INVALID_DATA_PARES,ThreeDS_FORMAT_ELE_INVALID);
		hmpMapCodes.put(UNHANDLED_ERR,ThreeDS_FORMAT_ELE_INVALID);
		//hmpMapCodes.put(ELE_MISSING_GEN,ThreeDS_REQ_ELE_MISSING);//Not necessary
		hmpMapCodes.put(INVALID_ERR_CODE,ThreeDS_FORMAT_ELE_INVALID);
		//hmpMapCodes.put(DATA_MISSING_PAREQ,ThreeDS_REQ_ELE_MISSING);//Not Necessary
		hmpMapCodes.put(ELE_MISSING_CPRS,ThreeDS_REQ_ELE_MISSING);
		hmpMapCodes.put(ACS_POST_ERR,ThreeDS_FORMAT_ELE_INVALID);
		hmpMapCodes.put(INVALID_TRAN_DET,ThreeDS_FORMAT_ELE_INVALID);

		//Added by Sock Hoon
		hmpMapCodes.put(INVALID_DATA_CPRS,ThreeDS_FORMAT_ELE_INVALID);




		hmpThreeDSErrorCodes.put("1", "Root element invalid.");
		hmpThreeDSErrorCodes.put("2", "Message element not a defined message.");
		hmpThreeDSErrorCodes.put("3", "Required element missing.");
		hmpThreeDSErrorCodes.put("4", "Critical element not recognized.");
		hmpThreeDSErrorCodes.put("5", "Format of one or more elements is invalid according to the specification.");
		hmpThreeDSErrorCodes.put("6", "Protocol version too old.");
		//Added by Sock Hoon
		hmpThreeDSErrorCodes.put("98", "Transient system failure.");
		hmpThreeDSErrorCodes.put("99", "Permanent system failure.");

	}


   /*
	* Returns the error description for the given error code
	*/


	//Added by Satya
	/****************** Map Visa Spec Error Codes to TPlus error codes *****************/

	// This will be used to generate the IReq XML when the response from
	// Visa Directory or the ACS is invalid
	public static HashMap hTPlusReqCodes = new HashMap();
	public static HashMap hmpThreeDSIReqCodes = new HashMap();


	static
	{
				hmpThreeDSIReqCodes.put(SERIAL_ELE_MISSING, "Serail Number cannot be located");
	}
	//EOC


	public static String getErrorDesc(String strErrCode){
		Object obj = hmpErrorCodes.get(strErrCode);
		if(obj != null)
			return (String)hmpErrorCodes.get(strErrCode);
		else
			return (String)hmpReturnCodes.get(strErrCode);
	}


   /*
	* Returns the return code description for the given return code
	*/

	public static String getReturnCodeDesc(String strRetCode){
		return (String)hmpReturnCodes.get(strRetCode);
	}

   /*
	* Returns the ThreeDS error description for the given ThreeDS error code
	*/

	public static String getThreeDSErrorDesc(String strThreeDSErrCode){
		return (String)hmpThreeDSErrorCodes.get(strThreeDSErrCode);
	}

   /*
	* Returns the ThreeDS Error Code that maps the TPlus Error Code
	*/

	public static String getThreeDSErrorCode(String strTPlusErrCode){
		return (String)hmpMapCodes.get(strTPlusErrCode);
	}

	/*
	 * Setting ACS Error Code to the TPlus System
	 */

		public static void setACSErrorCode(String strACSErrCode)
		{
			ACS_PRESENT_ERROR_CODE=strACSErrCode;
		}

	/*
	 * Returns the  ACS Error Code to the TPlus System
	 */

		public static String getACSErrorCode()
		{
			return ACS_PRESENT_ERROR_CODE;
		}


	//Changed by Satya
	/*
	 * Returns the ThreeDS IReq error description for the given ThreeDS IReq code
	 */

		public static String getThreeDSIReqDesc(String strThreeDSIReqCode)
		{
			return (String)hmpThreeDSIReqCodes.get(strThreeDSIReqCode);
		}

	/*
	 * Returns the ThreeDS IReq Code that maps the TPlus IReq Code
	 */

	 	public static String getThreeDSIReqCode(String strTPlusErrCode)
	 	{
	 		return (String)hTPlusReqCodes.get(strTPlusErrCode);
		}
	//EOC

// ISO Message codes
//Error code

		public static String DATABASE_CONN_ERR			= "601";
		public static String SQL_QUERY_ERR				= "602";
		public static String LOG_ERROR					= "603";
		public static String ALR_UNAUTH_ACS				= "604";
        public static String UNKNOWN_DATA_TYPE			= "916";
        public static String NO_DATA_AVAILABLE			= "917";


        public static String INVALID_REQUEST			= "1001";
        public static String INVALID_REQUEST_TYPE		= "1019";
        public static String INVALID_MSG_LEN			= "1002";
        public static String RISK_CONTROL_FAILED		= "1003";
        public static String ERROR_READ_REQUEST			= "1004";
		public static String INVALID_REQUEST_MSG		= "1005";
		public static String APPLICATION_ERROR			= "1010";
		public static String SMTP_MAIL_ERR				= "1011";
		public static String INVALID_AMT_CONVERSION		= "1012";
		public static String SWITCH_MGR_NOT_STARTED		= "1013";
		public static String SWITCH_NOT_FOUND			= "1014";
		public static String TRANX_NOT_FOUND			= "1015";
		public static String ISO_PARSING_FATAL			= "1016";
		public static String ISO_IOEXCEPTION			= "1017";
		public static String RES_GEN_ERR				= "1018";

// Response Code

		public static String INVALID_TRANSACTION		= "12";
        public static String INVALID_CARDNUMBER			= "14";
        public static String INVALID_MERCHANT			= "03";
        public static String DO_NOT_HONOUR				= "05";
        public static String INVALID_PIN				= "55";

        static
        {
			hmpErrorCodes.put(DATABASE_CONN_ERR,"DataBase connection Error.");
			hmpErrorCodes.put(LOG_ERROR,"Error while logging data to DataBase.");
			hmpErrorCodes.put(ALR_UNAUTH_ACS,"Invalid Terminal/Merchant ID");

			hmpErrorCodes.put(INVALID_REQUEST,"Invalid Request Message.");
			hmpErrorCodes.put(INVALID_REQUEST_TYPE,"Not Supported Request Type.");
    	    hmpErrorCodes.put(INVALID_MSG_LEN,"Invalid Message Length.");
			hmpErrorCodes.put(RISK_CONTROL_FAILED,"Risk Control Validation Failed.");
			hmpErrorCodes.put(ERROR_READ_REQUEST,"Unable to parse the request.");
			hmpErrorCodes.put(INVALID_REQUEST_MSG,"Invalid message length.");
			hmpErrorCodes.put(APPLICATION_ERROR,"Application Error.");
			hmpErrorCodes.put(SMTP_MAIL_ERR,"Error while sending Alert mail.");
			hmpErrorCodes.put(INVALID_AMT_CONVERSION, "Conversion Amount is less than 0 ");
			hmpErrorCodes.put(SWITCH_MGR_NOT_STARTED, "Switch Manager not started");
			hmpErrorCodes.put(SWITCH_NOT_FOUND, "Switch not Found");
			hmpErrorCodes.put(TRANX_NOT_FOUND, "Transaction not Found");
			hmpErrorCodes.put(ISO_PARSING_FATAL, "TPlus ISO Parsing Fatal Exception.");
			hmpErrorCodes.put(ISO_IOEXCEPTION, "TPlus XML Parsing IOException.");
			hmpErrorCodes.put(RES_GEN_ERR, "Error while generating the response.");

			hmpErrorCodes.put(INVALID_TRANSACTION,"Invalid Transaction.");
			hmpErrorCodes.put(INVALID_CARDNUMBER,"Invalid CardNumber.");
    	    hmpErrorCodes.put(INVALID_CARDNUMBER,"Invalid CardNumber.");
    	    hmpErrorCodes.put(INVALID_MERCHANT,"Invalid Merchant.");
    	    hmpErrorCodes.put(DO_NOT_HONOUR," DO Not Honour.");
    	    hmpErrorCodes.put(INVALID_PIN," Invalid PIN.");
		}

}