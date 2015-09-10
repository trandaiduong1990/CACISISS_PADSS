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
import org.transinfo.cacis.report.databean.TransDataBean;
import org.transinfo.cacis.report.db.DBManager;
import org.transinfo.cacis.report.exception.ReportException;
import org.transinfo.cacis.util.DBUtil;

public class BlackListReport implements ReportUtil {

	private ResultSet resultset = null;

	private String userName = "";

	public Object setRequest(HttpServletRequest objRequest,
			HttpSession objSession) throws ReportException {
		TransDataBean objTransDataBean = new TransDataBean();
		try {
			System.out.println("Inside setRequest method");

			String mode = objRequest.getParameter("mode");
			objTransDataBean.setMode(mode);
			objTransDataBean.setOpCode(objRequest.getParameter("hdOpCode"));

			// UserDataBean objUserDataBean =
			// (UserDataBean)objSession.getAttribute("USERDETAILS");
			// String strUserType=objUserDataBean.getUserType();
			// String strUserIssuerId=objUserDataBean.getIssuerId();
			String strUserType = (String) objSession.getAttribute("USERTYPE");
			String strUserIssuerId = (String) objSession.getAttribute("ISSUER");
			// DBUtil emvdbutil = new DBUtil(AdminConfig.poolName);
			DBUtil emvdbutil = new DBUtil();
			ArrayList arlIssuerList = emvdbutil.getIssuerList(strUserType,
					strUserIssuerId);
			ArrayList arlReasonList = emvdbutil.getCodeList("CARDSTATUSID");
			objTransDataBean.setIssuerList(arlIssuerList);
			objTransDataBean.setReasonList(arlReasonList);

			// this.userName = objUserDataBean.getUserId();

			if (objRequest.getParameter("ISSUER_ID") != null) {
				objTransDataBean.setIssuerId(objRequest
						.getParameter("ISSUER_ID"));
			}

			if (mode.trim().equals("Search") || mode.trim().equals("Next")
					|| mode.trim().equals("View") || mode.trim().equals("Prev")
					|| mode.trim().equals("Down")) {
				objTransDataBean.setIssuerId(objRequest
						.getParameter("ISSUER_ID"));
				objTransDataBean.setTransDateFrom(objRequest
						.getParameter("TRANS_DATE_FROM"));
				objTransDataBean.setTransTimeFrom(objRequest
						.getParameter("TRANS_TIME_FROM"));
				objTransDataBean.setTransDateTo(objRequest
						.getParameter("TRANS_DATE_TO"));
				objTransDataBean.setTransTimeTo(objRequest
						.getParameter("TRANS_TIME_TO"));
				objTransDataBean.setCardNoFrom(objRequest
						.getParameter("CARD_NUMBER_FROM"));
				objTransDataBean.setCardNoTo(objRequest
						.getParameter("CARD_NUMBER_TO"));
				objTransDataBean.setRowsPerPage(objRequest
						.getParameter("ROWSPERPAGE"));
				objTransDataBean.setReason(objRequest.getParameter("REASON"));
			} else {
				objTransDataBean
						.setTransDateFrom(emvdbutil
								.getFieldValue(
										"SELECT TO_CHAR(SYSDATE-1,'DD/MM/YYYY') AS TODAYDATE FROM DUAL",
										"TODAYDATE"));
				objTransDataBean.setTransTimeFrom("00:00:00");
				objTransDataBean
						.setTransDateTo(emvdbutil
								.getFieldValue(
										"SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') AS TODAYDATE FROM DUAL",
										"TODAYDATE"));
				objTransDataBean.setTransTimeTo("23:59:59");
			}

			if (strUserType.trim().equals("ISSUSER")
					|| strUserType.trim().equals("ISSADMIN")) {
				objTransDataBean.setIssuerId(strUserIssuerId);
			}

		} catch (Exception e) {
			throw new ReportException("" + ErrorCodes.REQUEST_FAIL,
					"Exception in getting the input parameters");
		}
		return objTransDataBean;
	}

	public Object validateRequest(Object obj) {
		TransDataBean objTransDataBean = new TransDataBean();
		objTransDataBean = (TransDataBean) obj;
		System.out.println("Inside validateRequest method");
		System.out.println("From Date" + objTransDataBean.getTransDateFrom());
		System.out.println("To Date" + objTransDataBean.getTransDateTo());
		return objTransDataBean;
	}

	public int getNumberOfSQLFields() {
		// get the count from number of fields in the getReportFields() method.
		int NUMBER_OF_SQL_FIELDS = 4;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be lessthan or equal 20.
		StringBuffer sbfList = new StringBuffer();
		sbfList
				.append(" SELECT TO_CHAR(BLC.LAST_UPDATED_DATE,'DD-MM-YYYY HH24:MI:SS') AS DATETIME, ");
		sbfList
				.append(" BLC.CARDNUMBER, CM1.CODE_DESCRIPTION AS CARDSTATUS, BLC.ISSUER_ID ");
		return sbfList;
	}

	public StringBuffer getFilterFields(Object obj) {
		TransDataBean objTransDataBean = new TransDataBean();
		objTransDataBean = (TransDataBean) obj;
		StringBuffer sbfFrom = new StringBuffer();
		StringBuffer sbfWhere = new StringBuffer();
		String strMode = objTransDataBean.getMode();
		sbfFrom.append(" FROM BLACKLIST_CARDS BLC , CODE_MASTER CM1 ");
		sbfWhere
				.append(" AND BLC.CARDSTATUSID = CM1.CODE_ID AND CM1.GROUP_ID='CARDSTATUSID' ");
		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY HH24:MI:SS";
		String strDateFrom = objTransDataBean.getTransDateFrom();
		String strTimeFrom = objTransDataBean.getTransTimeFrom();
		String strDateTo = objTransDataBean.getTransDateTo();
		String strTimeTo = objTransDataBean.getTransTimeTo();

		if (!strDateFrom.trim().equals("")) {
			String fromDateTime = strDateFrom + " " + strTimeFrom;
			sbfWhere.append(" AND BLC.LAST_UPDATED_DATE >= TO_DATE('"
					+ fromDateTime + "', '" + OUTPUT_DATE_FORMAT + "') ");
		}
		if (!strDateTo.trim().equals("")) {
			String toDateTime = strDateTo + " " + strTimeTo;
			sbfWhere.append("  AND BLC.LAST_UPDATED_DATE < (TO_DATE('"
					+ toDateTime + "', '" + OUTPUT_DATE_FORMAT + "') + 1) ");
		}

		String strIssuerId = objTransDataBean.getIssuerId();
		if (!strIssuerId.trim().equals(""))
			sbfWhere.append("  AND BLC.ISSUER_ID= '" + strIssuerId + "' ");

		if (!strMode.trim().equals("List")) {
			String strCardNumberFrom = objTransDataBean.getCardNoFrom();
			String strCardNumberTo = objTransDataBean.getCardNoTo();
			String strReason = objTransDataBean.getReason();

			if (!strCardNumberFrom.trim().equals("")
					|| !strCardNumberTo.trim().equals("")) {
				if (strCardNumberFrom.trim().equals(strCardNumberTo))
					sbfWhere.append("  AND (BLC.CARDNUMBER = '"
							+ strCardNumberFrom + "' OR BLC.CARDNUMBER = '"
							+ strCardNumberTo + "') ");
				else
					sbfWhere.append("  AND BLC.CARDNUMBER >= '"
							+ strCardNumberFrom + "' AND BLC.CARDNUMBER <= '"
							+ strCardNumberTo + "' ");
			}

			if (!strReason.trim().equals(""))
				sbfWhere.append("  AND BLC.CARDSTATUSID= '" + strReason + "' ");
		} else {
			// if(objSession.getAttribute("BLACKLISTRPTDATABEAN") != null){
			// objSession.removeAttribute("BLACKLISTRPTDATABEAN");
			// }
		}

		String whereSql = sbfWhere.toString();
		if (!whereSql.trim().equals(""))
			sbfFrom.append(" WHERE "
					+ whereSql.substring(whereSql.indexOf("AND") + 3, whereSql
							.length()));
		sbfFrom.append(" ORDER BY DATETIME DESC");

		return sbfFrom;

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
		TransDataBean objTransDataBean = new TransDataBean();
		try {
			String strAcqName = "";
			objTransDataBean = (TransDataBean) obj;
			String strIssuerId = objTransDataBean.getIssuerId();
			parameters.put("BANKNAME", "Sample Bank");
			parameters.put("FROM_DATE", objTransDataBean.getTransDateFrom());
			parameters.put("TO_DATE", objTransDataBean.getTransDateTo());
			parameters.put("USERNAME", this.userName);
		} catch (Exception e) {
			throw new ReportException("" + ErrorCodes.PARAMETER_ERR,
					"Exception in getting parameters for the report");
		}
		return parameters;
	}

}
