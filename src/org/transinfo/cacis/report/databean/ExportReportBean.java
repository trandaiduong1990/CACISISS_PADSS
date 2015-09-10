package org.transinfo.cacis.report.databean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


public class ExportReportBean implements Serializable {

    private String              m_InputReportXml = "";
    private Map                 m_InputParams = null;
    private ResultSet           m_InputResultSet      = null;
    private String              m_OutputFileName    = "";
    private HttpServletResponse m_Response = null;
    private String              m_ExportType  = null; // View or Download

    /**
     * Gets the ExportType
     * @param None
     * @return String
     * @exception None
     */
    public String getExportType() {
        return this.m_ExportType;
    }

    /**
     * Sets the ExportType
     * @param String
     * @return None
     * @exception None
     */
    public void setExportType(String ExportType) {
        this.m_ExportType = ExportType ;
    }

    /**
     * Gets the Response
     * @param None
     * @return HttpServletResponseonse
     * @exception None
     */
    public HttpServletResponse getResponse() {
        return this.m_Response;
    }

    /**
     * Sets the Response
     * @param HttpServletResponse
     * @return None
     * @exception None
     */
    public void setResponse(HttpServletResponse Response) {
        this.m_Response = Response ;
    }


    /**
     * Gets the Output File Name
     * @param None
     * @return String
     * @exception None
     */
    public String getOutputFileName() {
        return this.m_OutputFileName;
    }

    /**
     * Sets the Output File Name
     * @param String
     * @return None
     * @exception None
     */
    public void setOutputFileName(String OutputFileName) {
        this.m_OutputFileName = OutputFileName ;
    }

    /**
     * Gets the InputReportXml
     * @param None
     * @return String
     * @exception None
     */
    public String getInputReportXml() {
        return this.m_InputReportXml ;
    }

    /**
     * Sets the InputReportXml
     * @param String
     * @return None
     * @exception None
     */
    public void setInputReportXml(String InputReportXml) {
        this.m_InputReportXml = InputReportXml ;
    }

    /**
     * Gets the Input Param
     * @param None
     * @return Map
     * @exception None
     */
    public Map getInputParams() {
        return this.m_InputParams ;
    }

    /**
     * Sets the Input Param
     * @param Map
     * @return None
     * @exception None
     */
    public void setInputParams(Map InputParams) {
        this.m_InputParams = InputParams ;
    }

    /**
     * Gets the input ResultSet
     * @param None
     * @return ResultSet
     * @exception None
     */
    public ResultSet getInputResultSet() {
        return this.m_InputResultSet ;
    }

    /**
     * Sets Input ResultSet
     * @param ResultSet
     * @return None
     * @exception None
     */
    public void setInputResultSet(ResultSet InputResultSet) {
        this.m_InputResultSet = InputResultSet;
    }


}
