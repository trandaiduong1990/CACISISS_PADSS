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
import org.transinfo.cacis.util.DateUtil;

@SuppressWarnings("unchecked")
public class BatchProcessReplacementCards implements ReportUtil {

	private ResultSet resultset = null;

	private String userName = "";

	public Object setRequest(HttpServletRequest objRequest,
			HttpSession objSession) throws ReportException {
		ReportViewBean objReportViewBean = new ReportViewBean();
		try {

			String mode = objRequest.getParameter("mode");
			objReportViewBean.setMode(mode);
			objReportViewBean.setOpCode(objRequest.getParameter("hdOpCode"));
			
			this.userName = (String) objSession.getAttribute("USERID");

			if (mode.trim().equals("Down")) {

				if (objRequest.getParameter("APP_NO") != null) {
					objReportViewBean.setAppNos(objRequest
							.getParameter("APP_NO"));
				}

				if (objRequest.getParameter("AUTH_PERSON") != null) {
					objReportViewBean.setAuthPerson(objRequest
							.getParameter("AUTH_PERSON"));
				}
				
				if (objRequest.getParameter("NEW_NOS") != null) {
					objReportViewBean.setNewNos(objRequest
							.getParameter("NEW_NOS"));
				}

			}

		} catch (Exception e) {
			throw new ReportException("" + ErrorCodes.REQUEST_FAIL,
					"Exception in getting the input parameters");
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
		int NUMBER_OF_SQL_FIELDS = 14;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be lessthan or equal 20.
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("SELECT CRF.APPLICATION_ID AS APPID, CU.CUSTOMER_NAME AS CUSTNAME, ");
		sbfStr.append("CRF.CARDNUMBER AS OCARDNO, RL.NEW_CARD_NO AS NCARDNO, CRF.REMARKS AS REMARKS, ");
		sbfStr.append("TO_CHAR(CRF.LAST_UPDATED_DATE, 'YYYY-MM-DD') AS APPDATE ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String appNos = objReportViewBean.getAppNos();
		String newNos = objReportViewBean.getNewNos();

		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("FROM CARDREPLACEMENT_FORMS CRF, CUSTOMER CU,CARDS C, REPLACEMENT_LOG RL ");
		sbfStr.append("WHERE CRF.CARDNUMBER = RL.OLD_CARD_NO ");
		sbfStr.append("AND C.CUSTOMER_ID = CU.CUSTOMER_ID ");
		sbfStr.append("AND RL.NEW_CARD_NO = C.CARDNUMBER ");
		sbfStr.append("AND RL.FLAG = '1' ");
		sbfStr.append("AND CRF.APPLICATION_ID IN (" + appNos + ") ");
		sbfStr.append("AND RL.NEW_CARD_NO IN (" + newNos + ") ");

		return sbfStr;
	}

	public ResultSet getReportData(Object obj) throws ReportException {
		try {
			StringBuffer sbfStr = new StringBuffer();
			sbfStr.append(getReportFields());
			sbfStr.append(getFilterFields(obj));
			System.out.println("Report SQL : " + sbfStr.toString());
			DBManager objDBManager = new DBManager();
			ResultSet rs = objDBManager.executeSQL(sbfStr.toString(), true);
			this.resultset = rs;
		} catch (Exception exp) {
			if (exp instanceof ReportException)
				throw (ReportException) exp;
			else {
				throw new ReportException("" + ErrorCodes.QUERY_ERR,
						"Exception in Executing the Query");
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
			parameters.put("REPORT_DATE", DateUtil.getCurrentDate());
			parameters.put("AUTH_PERSON", objReportViewBean.getAuthPerson());
			parameters.put("USERNAME", this.userName);

		} catch (Exception e) {
			throw new ReportException("" + ErrorCodes.PARAMETER_ERR,
					"Exception in getting parameters for the report");
		}
		return parameters;
	}

}
