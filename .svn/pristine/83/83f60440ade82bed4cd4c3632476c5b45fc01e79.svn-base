package org.transinfo.cacis.report.exception;

/**
 * ReportException class is to handle the exception.
 */
public class ReportException extends Exception
{

    private String strErrorCode;
    private String strErrorDesc;

    /**
     * Constructor      ReportException.
     * @param           strError Error code.
     */
    public ReportException(String strError)
    {
        super(strError);

        this.strErrorCode = strError;
    }

    /**
     * Constructor      ReportException.
     * @param           strError Error code.
     * @param           strErrorDesc Error description.
     */
    public ReportException(String strError, String strErrorDesc)
    {
        super(strError + strErrorDesc);

        this.strErrorCode = strError;
        this.strErrorDesc = strErrorDesc;
    }

    /**
     * This method to get the error code.
     * @return  Error Code.
     */
    public String getErrorCode()
    {
        return strErrorCode;
    }

    /**
     *  This method returns the error description.
     * @return  Error Description.
     */
    public String getErrorDesc()
    {
        return strErrorDesc;
    }
}