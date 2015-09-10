//Package name
package org.transinfo.cacis.report.reportutil;

//Java specific imports
import java.sql.ResultSet;
import java.util.ArrayList;
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

public class CardVolume implements ReportUtil {

	private ResultSet resultset = null;

	private String userName = "";

	public Object setRequest(HttpServletRequest objRequest,
			HttpSession objSession) throws ReportException {
		ReportViewBean objReportViewBean = new ReportViewBean();
		try {
			System.out.println("Inside setRequest method");

			String mode = objRequest.getParameter("mode");
			objReportViewBean.setMode(mode);
			objReportViewBean.setOpCode(objRequest.getParameter("hdOpCode"));

			//UserDataBean objUserDataBean = (UserDataBean) objSession.getAttribute("USERDETAILS");
			//String strUserType = objUserDataBean.getUserType();
			//String strUserIssuerId = objUserDataBean.getIssuerId();
			DBUtil emvdbutil = new DBUtil();
			String strUserIssuerId = (String)objSession.getAttribute("ISSUER");
			String strUserType = (String)objSession.getAttribute("USERTYPE");
			ArrayList arlIssuerList = emvdbutil.getIssuerList(strUserType,
					strUserIssuerId);
			objReportViewBean.setIssuerList(arlIssuerList);
			//this.userName = objUserDataBean.getUserId();

			if (mode.trim().equals("Search") || mode.trim().equals("Next") || mode.trim().equals("View")
					|| mode.trim().equals("Prev") || mode.trim().equals("Down")) {
				if (objRequest.getParameter("ISSUER_ID") != null) {
					objReportViewBean.setIssuerId(objRequest
							.getParameter("ISSUER_ID"));
				}

				if (objRequest.getParameter("CARD_NO") != null) {
					objReportViewBean.setCardNo(objRequest
							.getParameter("CARD_NO"));
				}

				if (objRequest.getParameter("TRANS_DATE_FROM") != null) {
					objReportViewBean.setFromDate(objRequest
							.getParameter("TRANS_DATE_FROM"));
				}

				if (objRequest.getParameter("TRANS_DATE_TO") != null) {
					objReportViewBean.setToDate(objRequest
							.getParameter("TRANS_DATE_TO"));
				}
			} else {
				objReportViewBean
						.setFromDate(emvdbutil
								.getFieldValue(
										"SELECT TO_CHAR(SYSDATE-1,'DD/MM/YYYY') AS TODAYDATE FROM DUAL",
										"TODAYDATE"));
				objReportViewBean
						.setToDate(emvdbutil
								.getFieldValue(
										"SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') AS TODAYDATE FROM DUAL",
										"TODAYDATE"));

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
		System.out.println("Inside validateRequest method");
		System.out.println("From Date" + objReportViewBean.getFromDate());
		System.out.println("To Date" + objReportViewBean.getToDate());
		return objReportViewBean;
	}

	public int getNumberOfSQLFields() {
		// get the count from number of fields in the getReportFields() method.
		int NUMBER_OF_SQL_FIELDS = 11;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be lessthan or equal 20.
		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		StringBuffer sbfStr = new StringBuffer();
		sbfStr
				.append("SELECT TL.CardNumber, SUM(DECODE(TRANXCODE, 'CASH', AMOUNT, 0)) AS CASHAMOUNT,");
		sbfStr
				.append("COUNT(DECODE(TRANXCODE, 'CASH', AMOUNT, NULL)) AS CASHCOUNT, ");
		sbfStr
				.append("SUM(DECODE(TRANXCODE, 'SALE', AMOUNT, 0)) AS SALEAMOUNT, ");
		sbfStr
				.append("COUNT(DECODE(TRANXCODE, 'SALE', AMOUNT, NULL)) AS SALECOUNT,");
		sbfStr
				.append("SUM(DECODE(TRANXCODE, 'REFUND', AMOUNT, 0)) AS REFUNDAMOUNT, ");
		sbfStr
				.append("COUNT(DECODE(TRANXCODE, 'REFUND', AMOUNT, NULL)) AS REFUNDCOUNT, ");
		sbfStr
				.append("SUM(DECODE(TRANXCODE, 'TRANSFER', AMOUNT, 0)) AS TRANSFERAMOUNT, ");
		sbfStr
				.append("COUNT(DECODE(TRANXCODE, 'TRANSFER', AMOUNT, NULL)) AS TRANSFERCOUNT,");
		sbfStr
				.append("SUM(AMOUNT) AS TOTALAMOUNT,COUNT(AMOUNT) AS TOTALCOUNT ");
		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		String CardNo = objReportViewBean.getCardNo();
		String IssuerId = objReportViewBean.getIssuerId();

		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append(" FROM TRANXLOG TL, CARDS CA");
		sbfStr.append(" WHERE (DATETIME >= TO_DATE('"
				+ objReportViewBean.getFromDate() + "', '" + OUTPUT_DATE_FORMAT
				+ "')) ");
		sbfStr.append(" AND (DATETIME <TO_DATE('"
				+ objReportViewBean.getToDate() + "', '" + OUTPUT_DATE_FORMAT
				+ "') + 1) ");
		sbfStr.append(" AND RESPONSECODE='00' ");
		sbfStr
				.append(" AND TRANXCODE IN ('CASH', 'SALE', 'REFUND', 'TRANSFER') ");
		sbfStr.append("AND CA.CARDNUMBER = TL.CARDNUMBER ");

		if (!IssuerId.trim().equals("")) {
			sbfStr.append(" AND CA.ISSUER_ID = '" + IssuerId + "'");
		}
		if (!CardNo.trim().equals("")) {
			sbfStr.append(" AND TL.CardNumber = '" + CardNo + "'");
		}

		sbfStr.append(" GROUP BY TL.CardNumber ORDER BY TL.CardNumber");

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
			String strAcqName = "";
			objReportViewBean = (ReportViewBean) obj;
			String strIssuerId = objReportViewBean.getIssuerId();
			parameters.put("BANKNAME", "Sample Bank");
			parameters.put("FROM_DATE", objReportViewBean.getFromDate());
			parameters.put("TO_DATE", objReportViewBean.getToDate());
			parameters.put("USERNAME", this.userName);
		} catch (Exception e) {
			throw new ReportException("" + ErrorCodes.PARAMETER_ERR,
					"Exception in getting parameters for the report");
		}
		return parameters;
	}

}
