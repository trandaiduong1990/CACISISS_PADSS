package org.transinfo.cacis.report;

//Java imports
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.transinfo.cacis.report.exception.ReportException;


/**
 * Interface for Report Util
 */
@SuppressWarnings("unchecked")
public interface ReportUtil
{
    public Object setRequest(HttpServletRequest request, HttpSession objSession) throws ReportException;
    public Object validateRequest(Object obj) throws ReportException;
    public ResultSet getReportData(Object obj) throws ReportException;
	public Map getParamData(Object obj) throws ReportException;
    public ResultSet getResultSet() throws ReportException;
	public StringBuffer getReportFields();
    public StringBuffer getFilterFields(Object obj);
    public int getNumberOfSQLFields();
}


