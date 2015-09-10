package org.transinfo.cacis.report.reporttype;

import org.transinfo.cacis.report.exception.ReportException;
/**
 *
 *  This class will generate and return the Implementing class.
 *
 */

public class ReportTypeFactory {

    private static final String packageName = "org.transinfo.cacis.report.reporttype";
    // min index
    public static final int MIN_INDEX			=   0;
    // PDF report type
    public static final int PDF_REPORT_INDEX		=   MIN_INDEX;
    // HTML report type
    public static final int HTML_REPORT_INDEX		=   MIN_INDEX + 1;
    // EXCEL report type
    public static final int XLS_REPORT_INDEX		=   MIN_INDEX + 2;

    // max index
    public static final int MAX_INDEX			=   MIN_INDEX + 3;

    private static String [] sArrReportTypes;

    static {
        sArrReportTypes                      	= new String [MAX_INDEX];
        sArrReportTypes[PDF_REPORT_INDEX]	= "pdf";
        sArrReportTypes[HTML_REPORT_INDEX]	= "html";
        sArrReportTypes[XLS_REPORT_INDEX]	= "xls";
    }

    /**
     * This method returns a Implementation instance
     * @param packageName - packageName for choosing the implementation instance
     * @param intIndex - Index of the Class to be returned
     * @return Object
     * @throws ReportException
     */
    public static Object getInstance(int intIndex) throws ReportException {
        try{
            return Class.forName(packageName + "." + sArrReportTypes[intIndex]+"."+"ExportReport").newInstance();
        }
        catch (Exception exp) {
            throw new ReportException(exp.getMessage());
        }
    }

}