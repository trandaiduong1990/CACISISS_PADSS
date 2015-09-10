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

public class TransactionList implements ReportUtil {

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
			ArrayList arlTranxCodeList = emvdbutil.getTranxCodes();

			objReportViewBean.setIssuerList(arlIssuerList);
			objReportViewBean.setTranxCodeList(arlTranxCodeList);

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

				if (objRequest.getParameter("TRANX_CODE") != null) {
					objReportViewBean.setTranxCode(objRequest
							.getParameter("TRANX_CODE"));
				}

				if (objRequest.getParameter("REF_NO") != null) {
					objReportViewBean.setRefNo(objRequest
							.getParameter("REF_NO"));
				}

				if (objRequest.getParameter("RESPONSE_CODE") != null) {
					objReportViewBean.setResponseCode(objRequest
							.getParameter("RESPONSE_CODE"));
				}

				if (objRequest.getParameter("APPROVAL_CODE") != null) {
					objReportViewBean.setApprovalCode(objRequest
							.getParameter("APPROVAL_CODE"));
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
		int NUMBER_OF_SQL_FIELDS = 14;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be lessthan or equal 20.
		StringBuffer sbfStr = new StringBuffer();
		sbfStr
				.append("SELECT DATETIME,TL.ISSUER_ID, TL.CARDNUMBER,TRANXCODE, MERCHANTID, TERMINALID, AMOUNT, TL.CURRCODE,");
		sbfStr
				.append("CURR_SYMBOL, REFNO, TRACENO, INVOICENO, RESPONSECODE, APPROVALCODE ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		String CardNo = objReportViewBean.getCardNo();
		String IssuerId = objReportViewBean.getIssuerId();
		String RefNo = objReportViewBean.getRefNo();
		String TranxCode = objReportViewBean.getTranxCode();
		String ResponseCode = objReportViewBean.getResponseCode();
		String ApprovalCode = objReportViewBean.getApprovalCode();

		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append(" FROM TRANXLOG TL, CURRENCIES CU, CARDS CA");
		sbfStr.append(" WHERE (DATETIME >= TO_DATE('"
				+ objReportViewBean.getFromDate() + "', '" + OUTPUT_DATE_FORMAT
				+ "')) ");
		sbfStr.append(" AND (DATETIME <TO_DATE('"
				+ objReportViewBean.getToDate() + "', '" + OUTPUT_DATE_FORMAT
				+ "') + 1) ");
		sbfStr.append("AND TL.CURRCODE  = CU.CURR_CODE ");
		sbfStr.append("AND TL.CARDNUMBER = CA.CARDNUMBER ");

		if (!TranxCode.trim().equals("ALL")) {
			sbfStr.append("AND TL.TranxCode = '" + TranxCode + "'");
		}
		if ((RefNo != null) && (!RefNo.trim().equals(""))) {
			sbfStr.append("AND TL.RefNo = '" + RefNo + "'");
		}
		if ((ResponseCode != null) && (!ResponseCode.equals(""))) {
			sbfStr.append("AND TL.ResponseCode = '" + ResponseCode + "'");
		}
		if ((ApprovalCode != null) && (!ApprovalCode.equals(""))) {
			sbfStr.append("AND TL.ApprovalCode = '" + ApprovalCode + "'");
		}
		if (!IssuerId.trim().equals("")) {
			sbfStr.append(" AND CA.ISSUER_ID = '" + IssuerId + "'");
		}
		if (!CardNo.trim().equals("")) {
			sbfStr.append(" AND TL.CardNumber = '" + CardNo + "'");
		}

		sbfStr.append(" ORDER BY TL.TRANXLOGID DESC ");

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
