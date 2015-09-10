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
public class BatchProcessProductChangeCards implements ReportUtil {

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

				if (objRequest.getParameter("AUTH_PERSON") != null) {
					objReportViewBean.setAuthPerson(objRequest
							.getParameter("AUTH_PERSON"));
				}
				
				if (objRequest.getParameter("CARD_NO") != null) {
					objReportViewBean.setCardNo(objRequest
							.getParameter("CARD_NO"));
				}
				
				if (objRequest.getParameter("OLD_CARD_NO") != null) {
					objReportViewBean.setOldCardNo(objRequest
							.getParameter("OLD_CARD_NO"));
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
		sbfStr.append("SELECT CCL.OLD_CARD_NO AS OCARDNO, CU.CUSTOMER_NAME AS CUSTNAME, ");
		sbfStr.append("CU.ID_NUMBER IDNO, CCL.NEW_CARD_NO AS NCARDNO, substr(C.EXPDATE,1,2)||'/'||substr(C.EXPDATE,3,4) AS EXPDATE ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String cardNos = objReportViewBean.getCardNo();
		String oldCardNos = objReportViewBean.getOldCardNo();

		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("FROM CARDS C, CUSTOMER CU, CARDCHANGE_LOG CCL ");
		sbfStr.append("WHERE C.CUSTOMER_ID = CU.CUSTOMER_ID ");
		sbfStr.append("AND C.CARDNUMBER = CCL.NEW_CARD_NO ");
		sbfStr.append("AND C.CARDNUMBER IN (" + cardNos + ") ");
		sbfStr.append("AND CCL.OLD_CARD_NO IN (" + oldCardNos + ") ");

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
