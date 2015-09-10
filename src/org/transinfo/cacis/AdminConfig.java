package org.transinfo.cacis;

import java.io.File;

import org.transinfo.cacis.log.DebugToFile;
import org.transinfo.cacis.util.PropUtil;

/**
 * BatchConfig is the configuration class for Batch process. It loads the
 * properties from the config file to the static variables.
 */

@SuppressWarnings("static-access")
public class AdminConfig {
	// constants
	public static int intPaginationConstant = 10;

	public static int intPaginationConstantForReport = 100;

	public static final String poolName = "org.transinfo.cacis.properties.ticisplus_config";

	public static boolean loadConfig = false;

	// system parameters
	public static String strPwdExpiryPeriod = "PASSWORD_EXPIRY_PERIOD";

	public static String strMaxFailedLogins = "MAX_NO_OF_LOGIN_FAILED_ATTEMPTS";

	public static String strPwdRemainderDays = "PASSWORD_EXPIRY_REMIANDER_DAYS";

	public static String strFirsttimeloginValiddays = "FTL_VALIDITY_DAYS";

	public static String strLockUser = "LOCK_USER";

	// server parameters
	public static String strMailServerIP = "MAIL_SERVER_ADDRESS";

	public static String strMailServerPort = "MAIL_SERVER_PORT";

	public static String strFromName = "FROM_NAME";

	public static String strFromMail = "FROM_MAIL";

	public static String strEmvPort = "EMV_PORT";

	public static String strNumberOfWorkerThreads = "NO_WORKER_THREAD";

	public static String strSystemMonitorAddress = "SYSTEM_MONITOR_ADDRESS";

	public static String strSystemMonitorPort = "SYSTEM_MONITOR_PORT";

	public static String strTransMonitorAddress = "TRANS_MONITOR_ADDRESS";

	public static String strTransMonitorPort = "TRANS_MONITOR_PORT";

	public static String strDebugMonitorAddress = "DEBUG_MONITOR_ADDRESS";

	public static String strDebugMonitorPort = "DEBUG_MONITOR_PORT";

	public static String strDebugLevel = "DEBUG_LEVEL";

	public static String strHSMName = "HSM_NAME";

	public static String strHSMPort = "HSM_PORT";

	public static String strHSMConnPollSize = "HSM_CONN_POLL_SIZE";

	public static String strAdminPort = "ADMIN_PORT";

	public static String strApiPort = "API_PORT";

	public static String strPassThough = "PASS_THROUGH";

	public static String strDataTimeOut = "DATA_TIMEOUT";

	public static String strLoopTimeOut = "LOOP_TIMEOUT";

	public static String strScriptEnable = "SCRIPT_ENABLE";

	/** Log directory name */
	public static String strLogDirectory = "";

	/** Debug File Flag */
	public static String strDebugFlag = "";

	/** Debug File Name */
	public static String strDebugFileName = "";

	/** Log File Flag */
	public static String strLogFlag = "";

	/** Log File Name */
	public static String strLogFileName = "";

	/** Show on Console */
	public static String strShowOnConsole = "";

	// for debug and Server log
	/** DebugToFile */
	public static DebugToFile serverLog;

	/** DebugToFile */
	public static DebugToFile debugLog;

	/** Upload files */
	public static String uploadDirName = "C:\\EMVAdminUploadFile\\";

	/** Report config xml file path */
	public static String strReportXml = "";

	public static String TransactionServerIP = "";

	// public static String TransactionServerPort = "";
	public static String CMDStopServer = "";

	public static String RTNStopServer = "";

	public static String CMDPollServer = "";

	public static String RTNPollServer = "";

	public static String CMDStartServer = "";

	public static String ResTimeout = "";
	
    public static int reportTimeInterval = 0;

	public static void loadConfig() throws Exception {
		try {
			if (!loadConfig) {
				loadProperties();
				initiateLogging();
				loadConfig = true;
			}
		} catch (Exception e) {
			loadConfig = false;
			System.out.println("Exception while loading the config param");
		}
	}

	/**
	 * This method load the properties from the Config File to the config static
	 * variables.
	 * 
	 * @throws Exception
	 */

	public static void loadProperties() throws Exception {
		try {
			PropUtil putil = new PropUtil(
					"org.transinfo.cacis.properties.ticisplus_config");

			// LogDirectory validations
			strLogDirectory = putil.getProperty("LogDirectory");
			if (strLogDirectory == null) {
				System.out
						.println("LogDirectory not defined in properties file");
			}
			// LogFlag validations
			strLogFlag = putil.getProperty("LogFlag");
			if (strLogFlag == null) {
				System.out.println("LogFlag not defined in properties file");
			}
			// LogFileName validations
			strLogFileName = putil.getProperty("LogFileName");
			if (strLogFileName == null) {
				System.out
						.println("LogFileName not defined in properties file");
			}
			// DebugFlag validations
			strDebugFlag = putil.getProperty("DebugFlag");
			if (strDebugFlag == null) {
				System.out.println("DebugFlag not defined in properties file");
			}
			// DebugFileName validations
			strDebugFileName = putil.getProperty("DebugFileName");
			if (strDebugFileName == null) {
				System.out
						.println("DebugFileName not defined in properties file");
			}
			// ShowOnConsole validations
			strShowOnConsole = putil.getProperty("ShowOnConsole");
			if (strShowOnConsole == null) {
				System.out
						.println("ShowOnConsole not defined in properties file");
			}

			// reportXml validations
			strReportXml = putil.getProperty("REPORT_XML");
			if (strReportXml == null) {
				System.out.println("reportXml not defined in properties file");
			}
			File flTempDir = null;
			flTempDir = new File(strReportXml);
			if (flTempDir == null || !(flTempDir.isDirectory())) {
				System.out.println("Check the reportXml file/path");
			}

            try{
            	reportTimeInterval = Integer.parseInt(putil.getProperty("ReportTimeInterval"));
            }catch (Exception e) {
            	System.out.println("ReportTimeInterval is not properly defined in properties file");
            	System.out.println("Default value will be 3");
            	reportTimeInterval = 3;
			}

			// TransactionServerIP = putil.getProperty("TransactionServerIP");
			// TransactionServerPort =
			// putil.getProperty("TransactionServerPort");
			// CMDStopServer = putil.getProperty("CMDStopServer");
			// RTNStopServer = putil.getProperty("RTNStopServer");
			// CMDPollServer = putil.getProperty("CMDPollServer");
			// RTNPollServer = putil.getProperty("RTNPollServer");
			// CMDStartServer = putil.getProperty("CMDStartServer");
			// ResTimeout = putil.getProperty("ResTimeout");

			System.out.println("strLogDirectory     :: " + strLogDirectory);
			System.out.println("strDebugFlag        :: " + strDebugFlag);
			System.out.println("strDebugFileName    :: " + strDebugFileName);
			System.out.println("strLogFlag          :: " + strLogFlag);
			System.out.println("strLogFileName      :: " + strLogFileName);
			System.out.println("strShowOnConsole    :: " + strShowOnConsole);

			// System.out.println("TransactionServerIP :: "+
			// TransactionServerIP);
			// System.out.println("TransactionServerPort :: "+
			// TransactionServerPort);
			// System.out.println("CMDStopServer :: "+ CMDStopServer);
			// System.out.println("RTNStopServer :: "+ RTNStopServer);
			// System.out.println("CMDPollServer :: "+ CMDPollServer);
			// System.out.println("RTNPollServer :: "+ RTNPollServer);
			// System.out.println("CMDStartServer :: "+ CMDStartServer);
			// System.out.println("ResTimeout :: "+ ResTimeout);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			throw exp;
		}
	}

	/**
	 * This method inits the logging process.
	 */
	public static void initiateLogging() {
		serverLog = new DebugToFile(strLogDirectory, strLogFileName,
				new Boolean(strLogFlag).booleanValue());
		debugLog = new DebugToFile(strLogDirectory, strDebugFileName,
				new Boolean(strDebugFlag).booleanValue());
	}

	/**
	 * This method logs the data into log file.
	 * 
	 * @param actionCode
	 *            Action code indicates the UICC success/error conditions.
	 * @param status
	 *            Status indicates the fail (01) or success (00).
	 * @param data
	 *            Data indicates the error or success description.
	 */
	public static void writeLog(String actionCode, String status, String data) {
		String modileId = "ADMIN";
		serverLog.log(modileId, actionCode, status, data);
	}

	/**
	 * This method shows the debug on console and logs the data into debug log.
	 * 
	 * @param data
	 *            Success or error data.
	 */
	public static void writeDebug(String data) {
		boolean blnConsole = new Boolean(strShowOnConsole).booleanValue();
		if (blnConsole)
			System.out.println(data);
		debugLog.log(data);
	}

	@SuppressWarnings("unused")
	public static String parseXMLString(String strInput) {
		String strTestInp = replaceString(strInput, "\"", "\\\"");
		String strTagsFoundThusFar = "";
		StringBuffer sbfOut = new StringBuffer();
		String strOutString = "";
		String strTag = strInput, strToken = "";
		int intStartIndex = -1;
		int intEndIndex = 0;

		int intTempIndex1 = 0, intTempIndex2 = 0;
		String strTempTag = "";

		while (true) {

			while (true) {

				intStartIndex = strInput.indexOf("<", intStartIndex);

				if ((strInput.indexOf("<?")) == intStartIndex) {
					intStartIndex = intStartIndex + 2;
					continue;
				}

				if (intStartIndex < 0)
					break;

				intEndIndex = strInput.indexOf(">", intStartIndex);

				strTag = strInput.substring(intStartIndex + 1, intEndIndex);
				while (true) {

					if (strTag.startsWith("/")) {
						strTempTag = strTag.substring(1);

						intTempIndex1 = strTagsFoundThusFar.indexOf("."
								+ strTempTag + ".");

						if (intTempIndex1 < 0)
							break;

						strTagsFoundThusFar = strTagsFoundThusFar.substring(0,
								intTempIndex1);

						if (strTagsFoundThusFar.trim().equals("")) {
							strOutString = replaceString(sbfOut.toString(),
									"#^", "<BR>");
							return strOutString;
						}

						intStartIndex = strInput
								.indexOf("<", intStartIndex + 1);
						if (intStartIndex < 0)
							break;

						intEndIndex = strInput.indexOf(">", intStartIndex);

						strTag = strInput.substring(intStartIndex + 1,
								intEndIndex);
						strTagsFoundThusFar += ".";
					}

					else {
						break;
					}
				}

				if (!strTagsFoundThusFar.endsWith("."))
					strTagsFoundThusFar += ".";

				strTagsFoundThusFar += strTag;

				if (strInput.charAt(intEndIndex + 1) != '<') {

					strTagsFoundThusFar += ".";
					intStartIndex = intEndIndex;
					break;
				}

				intStartIndex = intEndIndex;

			}
			intEndIndex = strInput.indexOf("<", intStartIndex);

			strToken = strInput.substring(intStartIndex + 1, intEndIndex);
			sbfOut.append(strTagsFoundThusFar.substring(1, strTagsFoundThusFar
					.length() - 1));
			sbfOut.append("=");
			sbfOut.append(strToken + "#^");

			intStartIndex = intEndIndex;
			if (intStartIndex < 0)
				break;
			intEndIndex = strInput.indexOf(">", intStartIndex);
			if (intStartIndex < 0)
				break;
		}

		strOutString = replaceString(sbfOut.toString(), "#^", "<BR>");
		return strOutString;
	}

	public static String parseXML(String strXMLAuditLog, String strBreak) {
		StringBuffer sbfParsedXML = new StringBuffer();

		int intIndex1 = -1, intIndex2 = -1;
		String strTag = "", strToken = "";

		while (true) {
			intIndex1 = strXMLAuditLog.indexOf("<", intIndex1 + 1);

			if (intIndex1 == -1)
				break;

			intIndex2 = strXMLAuditLog.indexOf(">", intIndex1);
			strTag = strXMLAuditLog.substring(intIndex1 + 1, intIndex2);

			intIndex1 = strXMLAuditLog.indexOf("</", intIndex1 + 1);
			strToken = strXMLAuditLog.substring(intIndex2 + 1, intIndex1);

			sbfParsedXML.append(strTag + " = " + strToken + "," + strBreak);
		}
		return sbfParsedXML.toString();
	}

	public static String parseXML(String strXMLAuditLog) {
		StringBuffer sbfParsedXML = new StringBuffer();

		int intIndex1 = -1, intIndex2 = -1;
		String strTag = "", strToken = "";

		while (true) {
			intIndex1 = strXMLAuditLog.indexOf("<", intIndex1 + 1);

			if (intIndex1 == -1)
				break;

			intIndex2 = strXMLAuditLog.indexOf(">", intIndex1);
			strTag = strXMLAuditLog.substring(intIndex1 + 1, intIndex2);

			intIndex1 = strXMLAuditLog.indexOf("</", intIndex1 + 1);
			strToken = strXMLAuditLog.substring(intIndex2 + 1, intIndex1);

			sbfParsedXML.append(strTag + " = " + strToken + ",<br>");
		}
		return sbfParsedXML.toString();
	}

	public static String replaceString(String strString, String strSrchString,
			String strRplString) {

		if (strString == null)
			return "";

		String strOutString = "";
		int intIndex = 0;
		int intPrevIndex = 0;
		int intSrcStrLength = strSrchString.length();

		do {
			intIndex = strString.indexOf(strSrchString, intPrevIndex);

			if (intIndex == -1) {
				strOutString += strString.substring(intPrevIndex);
				break;
			}

			strOutString += strString.substring(intPrevIndex, intIndex)
					+ strRplString;
			intPrevIndex = intIndex + intSrcStrLength;

		} while (true);

		return strOutString;
	}

	public static String formatDate(String inDate) { // expected date format
		// = DD/MM/YYYY HH:MI:SS
		String DD = inDate.substring(0, 2);
		String MM = inDate.substring(3, 5);
		String YYYY = inDate.substring(6, 10);
		String HH = inDate.substring(11, 13);
		String MI = inDate.substring(14, 16);
		String SS = inDate.substring(17, 19);
		return YYYY + MM + DD + HH + MI + SS;
	}
}
