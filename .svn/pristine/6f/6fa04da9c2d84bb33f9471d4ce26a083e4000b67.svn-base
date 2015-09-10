//Package name
package org.transinfo.cacis.report.reportutil;

//Java specific imports
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.transinfo.cacis.ErrorCodes;
import org.transinfo.cacis.report.ReportUtil;
import org.transinfo.cacis.report.databean.ReportViewBean;
import org.transinfo.cacis.report.db.DBManager;
import org.transinfo.cacis.report.exception.ReportException;
import org.transinfo.cacis.util.DBUtil;

@SuppressWarnings("unchecked")
public class CancelCardsStatistics implements ReportUtil {

	private ResultSet resultset = null;

	private String userName = "";

	public Object setRequest(HttpServletRequest objRequest,
			HttpSession objSession) throws ReportException {
		ReportViewBean objReportViewBean = new ReportViewBean();
		try {

			String mode = objRequest.getParameter("mode");
			objReportViewBean.setMode(mode);
			objReportViewBean.setOpCode(objRequest.getParameter("hdOpCode"));
			
			DBUtil emvdbutil = new DBUtil();
			
			this.userName = (String) objSession.getAttribute("USERID");

			if (mode.trim().equals("Search") || mode.trim().equals("Next") || mode.trim().equals("View") || mode.trim().equals("Prev") || mode.trim().equals("Down")) {

				if (objRequest.getParameter("TRANS_DATE_FROM") != null) {
					objReportViewBean.setFromDate(objRequest.getParameter("TRANS_DATE_FROM"));
				}

				if (objRequest.getParameter("TRANS_DATE_TO") != null) {
					objReportViewBean.setToDate(objRequest.getParameter("TRANS_DATE_TO"));
				}
				
			} else {
				objReportViewBean.setFromDate(emvdbutil.getFieldValue("SELECT TO_CHAR(SYSDATE-1,'DD/MM/YYYY') AS TODAYDATE FROM DUAL", "TODAYDATE"));
				objReportViewBean.setToDate(emvdbutil.getFieldValue("SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') AS TODAYDATE FROM DUAL", "TODAYDATE"));
			}

		} catch (Exception e) {
			throw new ReportException("" + ErrorCodes.REQUEST_FAIL, "Exception in getting the input parameters");
		}
		return objReportViewBean;
	}

	public Object validateRequest(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;
		return objReportViewBean;
	}

	public int getNumberOfSQLFields() {
		// get the count from number of fields in the getReportFields() method.
		int NUMBER_OF_SQL_FIELDS = 4;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be less than or equal 20.
		
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("SELECT ");
		sbfStr.append("SUM(JTT.MANUSUS) AS MANUSUS, SUM(JTT.CLSBYBANK) AS CLSBYBANK, ");
		sbfStr.append("SUM(JTT.CLSBYCUS) AS CLSBYCUS, SUM(JTT.WRITEOFF) AS WRITEOFF ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		String startDate = objReportViewBean.getFromDate();
		String endDate = objReportViewBean.getToDate();
		
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("FROM ( ");
	      sbfStr.append("SELECT COUNT (CASE WHEN CA.CARD_STATUS_ID = 44 THEN CA.CARD_STATUS_ID ELSE NULL  END)  AS MANUSUS, ");
	             sbfStr.append("COUNT (CASE WHEN CA.CARD_STATUS_ID = 45 THEN CA.CARD_STATUS_ID ELSE NULL  END)  AS CLSBYBANK, ");
	             sbfStr.append("COUNT (CASE WHEN CA.CARD_STATUS_ID = 46 THEN CA.CARD_STATUS_ID ELSE NULL  END)  AS CLSBYCUS, ");
	             sbfStr.append("0 AS WRITEOFF ");
	      sbfStr.append("FROM CARDS CA ");
	      sbfStr.append("WHERE (CA.LAST_UPDATED_DATE >= TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"')) ");
	            sbfStr.append("AND (CA.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
	            
	      sbfStr.append("UNION ALL ");
	      
	      sbfStr.append("SELECT 0 AS MANUSUS, 0 AS CLSBYBANK, 0 AS CLSBYCUS, COUNT(WOC.CARDNUMBER) AS WRITEOFF ");
	      sbfStr.append("FROM WRITEOFF_CARDS WOC ");
	      sbfStr.append("WHERE (WOC.LAST_UPDATED_DATE >= TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"')) ");
	            sbfStr.append("AND (WOC.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
	      sbfStr.append(") JTT  ");

		return sbfStr;
	}

	public ResultSet getReportData(Object obj) throws ReportException {
		try {
			
			StringBuffer sbfStr = new StringBuffer();
			sbfStr.append(getReportFields());
			sbfStr.append(getFilterFields(obj));

			DBManager objDBManager = new DBManager();
			ResultSet rs = objDBManager.executeSQL(sbfStr.toString(), true);
			
			this.resultset = rs;
			
		} catch (Exception exp) {
			if (exp instanceof ReportException)
				throw (ReportException) exp;
			else {
				throw new ReportException("" + ErrorCodes.QUERY_ERR, "Exception in Executing the Query");
			}
		}
		return resultset;
	}

	public ResultSet getResultSet() {
		return this.resultset;
	}

	public Map getParamData(Object obj) throws ReportException {
		
		Map parameters = new HashMap();
		ReportViewBean objReportViewBean = new ReportViewBean();
		
		try {

			objReportViewBean = (ReportViewBean) obj;

			parameters.put("BANKNAME", "Sample Bank");
			parameters.put("FROM_DATE", objReportViewBean.getFromDate());
			parameters.put("TO_DATE", objReportViewBean.getToDate());
			parameters.put("USERNAME", this.userName);
			
		} catch (Exception e) {
			throw new ReportException("" + ErrorCodes.PARAMETER_ERR, "Exception in getting parameters for the report");
		}
		return parameters;
	}

}
