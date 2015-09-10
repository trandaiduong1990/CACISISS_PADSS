package org.transinfo.cacis.report;

import org.transinfo.cacis.report.exception.ReportException;
/**
 *
 *  This class will generate and return the Implementing class.
 *
 */

public class ReportFactory {

    private static final String packageName = "org.transinfo.cacis.report.reportutil";

    /**
     * This method returns a Implementation instance
     * @param packageName - packageName for choosing the implementation instance
     * @param intIndex - Index of the Class to be returned
     * @return Object
     * @throws ReportException
     */
    public static Object getInstance(String ReportName) throws ReportException {
        try{
            return Class.forName(packageName + "." + ReportName).newInstance();
        }
        catch (Exception exp) {
            throw new ReportException(exp.getMessage());
        }
    }

}