package org.transinfo.cacis.util;

import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.formbean.settings.HSMDataBean;

import vn.com.tivn.hsm.phw.EracomPHW;

/**
 * BatchConfig is the configuration class for Batch process.
 * It loads the properties from the config file to the static variables.
 */

public class AdminParamsLoad {

    public static String embossFilePath = "";
    public static String embossFileNameDateFormat = "";
    public static String embossImagespath = "";
    public static String embossImageFormat = "";
    
    public static String reportXML = "";
    public static String reportPath = "";
    public static String billReportLocation = "";
    public static String billReportXML = "";
    public static String disMngExpAltBuff = "";
    public static String letterPath = "";
    public static String printerName = "";
    public static int reportTimeInterval = 0;

    /**
     * This method load the properties from the Config File to the config static variables.
     * @throws Exception
     */

    @SuppressWarnings("static-access")
	public static void loadProperties() throws Exception {
        try {
            //PropUtil putil = new PropUtil("com.titpl.giftpac.admin.properties.gpac_config");
            PropUtil putil = new PropUtil(ICacisiss.IProperty.PROPERTY_FILE);

            // LogDirectory validations
            embossFilePath = putil.getProperty("EmbossFilePath");
            if (embossFilePath == null) {
                System.out.println("embossFilePath not defined in properties file");
            }
            // LogFlag validations
            embossFileNameDateFormat = putil.getProperty("EmbossFileNameDateFormat");
            if (embossFileNameDateFormat == null) {
                System.out.println("embossFileNameDateFormat not defined in properties file");
            }
            
            embossImagespath = putil.getProperty("EmbossImagesPath");
            if (embossImagespath == null) {
                System.out.println("EmbossImagesPath not defined in properties file");
            }
            
            embossImageFormat = putil.getProperty("EmbossImageFormat");
            if (embossImageFormat == null) {
                System.out.println("EmbossImageFormat not defined in properties file");
            }
            
            reportXML = putil.getProperty("ReportXML");
            if (reportXML == null) {
                System.out.println("ReportXML not defined in properties file");
            }
            
            reportPath = putil.getProperty("ReportPath");
            if (reportPath == null) {
                System.out.println("ReportPath not defined in properties file");
            }
            
            billReportLocation = putil.getProperty("BillReportLocation");
            if (billReportLocation == null) {
                System.out.println("BillReportLocation not defined in properties file");
            }
            
            billReportXML = putil.getProperty("BillReportXML");
            if (billReportXML == null) {
                System.out.println("BillReportXML not defined in properties file");
            }
            
            disMngExpAltBuff = putil.getProperty("DisMngExpAltBuff");
            if (disMngExpAltBuff == null) {
                System.out.println("DisMngExpAltBuff not defined in properties file");
            }
            
            letterPath = putil.getProperty("LetterPath");
            if (letterPath == null) {
                System.out.println("Letter Path not defined in properties file");
            }
            
            printerName = putil.getProperty("PrinterName");
            if (printerName == null) {
                System.out.println("Printer Name not defined in properties file");
            }

            try{
            	reportTimeInterval = Integer.parseInt(putil.getProperty("ReportTimeInterval"));
            }catch (Exception e) {
            	System.out.println("ReportTimeInterval is not properly defined in properties file");
            	System.out.println("Default value will be 3");
            	reportTimeInterval = 3;
			}

            System.out.println("EmbossFilePath					:: "+  embossFilePath);
            System.out.println("EmbossFileNameDateFormat        :: "+  embossFileNameDateFormat);
            System.out.println("EmbossImagesPath        		:: "+  embossImagespath);
            System.out.println("EmbossImageFormat        		:: "+  embossImageFormat);
            System.out.println("ReportXML        				:: "+  reportXML);
            System.out.println("ReportPath        				:: "+  reportPath);
            System.out.println("BillReportLocation        		:: "+  billReportLocation);
            System.out.println("BillReportXML        			:: "+  billReportXML);
            System.out.println("DisMngExpAltBuff        		:: "+  disMngExpAltBuff);
            System.out.println("Report Time Interval        	:: "+  reportTimeInterval);

        }catch (Exception exp){
            System.out.println(exp.toString());
            throw exp;
        }
    }
    
    public static void initHSM() throws Exception{
    	DBUtil dbutil = new DBUtil();
        HSMDataBean hsm = dbutil.getHSM();
        EracomPHW.init(hsm.getIp(), new Integer(hsm.getPort()).intValue(), new Integer(hsm.getMaxConn()).intValue(), new Integer(hsm.getWeight()).intValue());
        EracomPHW.INITIALIZED=true;
      }
}
