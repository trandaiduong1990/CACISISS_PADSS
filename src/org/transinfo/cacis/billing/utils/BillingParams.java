package org.transinfo.cacis.billing.utils;

import org.apache.log4j.Logger;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.util.PropUtil;

@SuppressWarnings("static-access")
public class BillingParams {

	private static final Logger billLog = Logger.getLogger("BillLog");

	public static String billReportLocation = "";
	public static String billReportXML = "";

	public static void loadProperties() throws Exception {
		try {
			PropUtil putil = new PropUtil(ICacisiss.IProperty.PROPERTY_FILE);

			billReportLocation = putil.getProperty("BillReportLocation");
			if (billReportLocation == null) {
				throw new Exception("BillReportLocation not defined");
			}

			billReportXML = putil.getProperty("BillReportXML");
			if (billReportXML == null) {
				throw new Exception("BillReportXML not defined");
			}

			billLog.info("BillReportLocation	::	" + billReportLocation);
			billLog.info("BillReportXML			::	" + billReportXML);

		} catch (Exception exp) {
			throw exp;
		}
	}

}
