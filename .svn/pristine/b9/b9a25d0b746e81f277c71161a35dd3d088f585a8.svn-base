//Package name
package org.transinfo.cacis.report.reportutil;

//Java specific imports
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.transinfo.cacis.AdminConfig;
import org.transinfo.cacis.ErrorCodes;
import org.transinfo.cacis.report.ReportUtil;
import org.transinfo.cacis.report.databean.ReportViewBean;
import org.transinfo.cacis.report.db.DBManager;
import org.transinfo.cacis.report.exception.ReportException;
import org.transinfo.cacis.util.DBUtil;

public class CardholderBillingStatement implements ReportUtil {

	private ResultSet resultset = null;

	public Object setRequest(HttpServletRequest objRequest,
			HttpSession objSession) throws ReportException {
		ReportViewBean objReportViewBean = new ReportViewBean();
		DBUtil dbutil = new DBUtil();
		String IssuerId = "";
		String LAST_BILLING_DATE = "";
		String CURRENT_DATE = "";
		String Card_No = "";

		String mode = objRequest.getParameter("mode");
		String reportCode = objRequest.getParameter("REPORT_CODE");
		//UserDataBean objUserDataBean = (UserDataBean) objSession.getAttribute("USERDETAILS");
		//String strUserType = objUserDataBean.getUserType();
		//String strUserIssuerId = objUserDataBean.getIssuerId();
		//String userId = (String) objSession.getAttribute("USERID");
		String strUserIssuerId = (String)objSession.getAttribute("ISSUER");
		String strUserType = (String)objSession.getAttribute("USERTYPE");
		DBUtil emvdbutil = new DBUtil();
		ArrayList arlIssuerList = emvdbutil.getIssuerList(strUserType,
				strUserIssuerId);
		objReportViewBean.setIssuerList(arlIssuerList);

		objReportViewBean.setMode(mode);
		objReportViewBean.setReportCode(reportCode);
		objReportViewBean.setOpCode(objRequest.getParameter("hdOpCode"));
		objReportViewBean.setUserType(strUserType);

		if (mode.trim().equals("Search") || mode.trim().equals("Next") || mode.trim().equals("View")
				|| mode.trim().equals("Prev") || mode.trim().equals("Down")) {

			if (objRequest.getParameter("LAST_BILLING_DATE") != null) {
				LAST_BILLING_DATE = objRequest
						.getParameter("LAST_BILLING_DATE");
				objReportViewBean.setFromDate(LAST_BILLING_DATE);
			}
			if (objRequest.getParameter("CURRENT_DATE") != null) {
				CURRENT_DATE = objRequest.getParameter("CURRENT_DATE");
				objReportViewBean.setToDate(CURRENT_DATE);
			}
			if (objRequest.getParameter("ISSUER_ID") != null) {
				System.out.println("I hv reached here !!!");
				IssuerId = objRequest.getParameter("ISSUER_ID");
				objReportViewBean.setIssuerId(IssuerId);
				String nextCycleNo = Integer
						.toString(evaluateNextCycleNo(IssuerId));
				objReportViewBean.setCycleNo(nextCycleNo);
				String LastBillingDate = evaluateLastBillingDate(IssuerId,
						objReportViewBean.getCycleNo());
				objReportViewBean.setFromDate(LastBillingDate);

				String strDayOfMonth = "";
				strDayOfMonth = evaluateDayOfMonth(IssuerId, objReportViewBean
						.getCycleNo());
				objReportViewBean.setDayOfMonth(strDayOfMonth);
				GregorianCalendar c = new GregorianCalendar();
				if (strDayOfMonth.equals("")
						|| c.get(Calendar.DAY_OF_MONTH) != Integer
								.parseInt(strDayOfMonth)) {
					objReportViewBean
							.setMessage("Today is not a billing day for this issuer");
				}
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
			objReportViewBean.setIssuerId(objRequest.getParameter("ISSUER_ID"));
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
		int NUMBER_OF_SQL_FIELDS = 16;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be lessthan or equal 20.
		String OUTPUT_DATE_FORMAT = "dd/MM/yyyy";
		StringBuffer sbfStr = new StringBuffer();
		sbfStr
				.append("SELECT C2.NAME AS CARDHOLDERNAME, C2.ADDRESS, Cust.CREDITLIMIT, TO_CHAR(SYSDATE + NVL(C3.DUEDAYS, 0), '"
						+ OUTPUT_DATE_FORMAT + "') AS PAYMENTDUEDATE,");
		sbfStr
				.append("S.AMOUNT_CR, S.AMOUNT_DR, (S.AMOUNT_DR - S.AMOUNT_CR) AS BILLINGAMOUNT, C4.CURR_SYMBOL, S.REFNO, S.TRANXDATE, S.DATETIME, ");
		sbfStr
				.append("S.PRIMARYCARDNUMBER, S.CARDNUMBER, S.MERCHANTNAME, S.MERCHANTCITY, S.MERCHANTCOUNTRY ");
		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		String cardNum = objReportViewBean.getCardNo();
		String sCycleNo = objReportViewBean.getCycleNo();
		String fromDate = objReportViewBean.getFromDate();
		String toDate = objReportViewBean.getToDate();

		StringBuffer sbfStr = new StringBuffer();
		// sbfStr.append(" (cust.CREDITLIMIT - BALANCE(S.PRIMARYCARDNUMBER,
		// TO_DATE('" + toDate + "','" + OUTPUT_DATE_FORMAT + "')+1)) AS
		// AVAILABLE_CREDITLIMIT,");
		// sbfStr.append("BALANCE(S.PRIMARYCARDNUMBER, TO_DATE('" + fromDate +
		// "','" + OUTPUT_DATE_FORMAT + "')) AS BeginningBalance,");
		// sbfStr.append(" BALANCE(S.PRIMARYCARDNUMBER, TO_DATE('" + toDate +
		// "','" + OUTPUT_DATE_FORMAT + "')+1) AS EndingBalance ");
		sbfStr
				.append(" FROM TICISPLUS.APPLICATIONFORMS A, TICISPLUS.CUSTOMER C1 , TICISPLUS.CARDINFOS  C2, TICISPLUS.CARDPRODUCTS C3, TICISPLUS.CURRENCIES C4, TICISPLUS.SETTLEMENTS S,TICISPLUS.CUSTACCOUNT cust,TICISPLUS.Cycles c5 ");
		sbfStr
				.append(" WHERE  C2.CARDNUMBER = S.PRIMARYCARDNUMBER AND A.CUSTOMERID = C1.CUSTOMER_ID AND C2.APPLICATIONID = A.APPLICATIONID AND S.CURRCODE = C4.CURR_CODE AND A.CARDPRODUCTID = C3.CARDPRODUCTID ");
		sbfStr.append(" AND c5.CYCLENO IN (" + sCycleNo + ") ");
		sbfStr.append(" AND (S.TRANXDATE BETWEEN TO_DATE('" + fromDate + "', '"
				+ OUTPUT_DATE_FORMAT + "') ");
		sbfStr.append(" AND TO_DATE('" + toDate + "', '" + OUTPUT_DATE_FORMAT
				+ "')+1) ");

		if (cardNum.length() > 0) {
			sbfStr.append("AND (PRIMARYCARDNUMBER = '" + cardNum + "') ");
		}

		sbfStr
				.append(" ORDER BY C4.CURR_CODE, S.PRIMARYCARDNUMBER, S.CARDNUMBER, S.TRANXDATE, S.DATETIME ");

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
			String strIssName = "";
			objReportViewBean = (ReportViewBean) obj;
			String strIssuerId = objReportViewBean.getIssuerId();
			if (!strIssuerId.trim().equals("") && strIssuerId != null)
				// strIssName=objReportViewBean.getIssName(strIssuerId);
				// parameters.put("BANKNAME", strAcqName);
				parameters.put("FROM_DATE", objReportViewBean.getFromDate());
			parameters.put("TO_DATE", objReportViewBean.getToDate());
		} catch (Exception e) {
			throw new ReportException("" + ErrorCodes.PARAMETER_ERR,
					"Exception in getting parameters for the report");
		}
		return parameters;
	}

	public String evaluateLastBillingDate(String strIssId, String strCycleNo) {
		String strLastBillingDate = "";

		try {
			DBManager objDBManager = new DBManager(AdminConfig.poolName);

			int prevCycleNo = Integer.parseInt(strCycleNo) - 1;

			if (prevCycleNo < 1) {
				int totalCycles = 0;

				String sSQL = "SELECT COUNT(*) AS TOTALCYCLES "
						+ "FROM CYCLES " + "WHERE ISSUER_ID = '" + strIssId
						+ "'";
				System.out.println("GetTotalcycle sql is " + sSQL);
				ResultSet rs = objDBManager.executeSQL(sSQL.toString());

				System.out.println("select count sql is " + sSQL);
				if (rs.next()) {
					totalCycles = rs.getInt("TOTALCYCLES");
				}
				prevCycleNo = totalCycles;
			}
			String sSQL = "SELECT TO_CHAR(LASTBILLINGDATE,'DD/MM/YYYY') AS LASTBILLINGDATE FROM CYCLES "
					+ "WHERE ISSUER_ID = '"
					+ strIssId
					+ "' AND CYCLENO = '"
					+ prevCycleNo + "'";
			System.out.println("GetLastBillingDate sql is " + sSQL);
			ResultSet RS = objDBManager.executeSQL(sSQL.toString());

			if (RS.next()) {
				strLastBillingDate = RS.getString("LASTBILLINGDATE");
				DBUtil dbutil = new DBUtil();
			} else {
				strLastBillingDate = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strLastBillingDate;
	}

	public String evaluateDayOfMonth(String strIssId, String cycleno) {

		String strDayOfMonth = "";
		try {
			String sSQL = "SELECT DAYOFMONTH " + "FROM CYCLES "
					+ "WHERE ISSUER_ID = '" + strIssId + "' AND CYCLENO='"
					+ cycleno + "'";

			System.out.println("Getdayofmonth sql is " + sSQL);

			DBManager objDBManager = new DBManager(AdminConfig.poolName);
			ResultSet rs = objDBManager.executeSQL(sSQL.toString());

			if (rs.next()) {
				strDayOfMonth = rs.getString("DAYOFMONTH");
			}

			return strDayOfMonth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public int evaluateNextCycleNo(String strIssId) {
		int nextcycleno = 0;
		int totalCycles = 0;
		try {
			DBManager objDBManager = new DBManager(AdminConfig.poolName);

			String sSQL = "SELECT COUNT(*) AS TOTALCYCLES FROM CYCLES WHERE ISSUER_ID = '"
					+ strIssId + "'";
			System.out.println("GetTotalcycle sql is " + sSQL);
			ResultSet rs = objDBManager.executeSQL(sSQL.toString());
			System.out.println("select count sql is " + sSQL);
			if (rs.next()) {
				totalCycles = rs.getInt("TOTALCYCLES") + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nextcycleno = 1;

	}

}
