//Package name
package org.transinfo.cacis.report.reportutil;

//Java specific imports
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.transinfo.cacis.ErrorCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.report.ReportUtil;
import org.transinfo.cacis.report.databean.TransDataBean;
import org.transinfo.cacis.report.exception.ReportException;
import org.transinfo.cacis.util.DBUtil;
import org.transinfo.cacis.util.StringUtil;

public class TransactionReport implements ReportUtil {

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
			DBUtil emvdbutil = new DBUtil();
			String strUserIssuerId = (String) objSession.getAttribute("ISSUER");
			String strUserType = (String) objSession.getAttribute("USERTYPE");
			ArrayList arlIssuerList = emvdbutil.getIssuerList(strUserType,
					strUserIssuerId);
			ArrayList arlTranxTypeList = emvdbutil.getCodeList("TRANXTYPE");
			objTransDataBean.setIssuerList(arlIssuerList);
			objTransDataBean.setTranxTypeList(arlTranxTypeList);

			// this.userName = objUserDataBean.getUserId();
			System.out.println("!!!!! mode = "+mode);
			if (mode.trim().equals("Search") || mode.trim().equals("Next") || mode.trim().equals("View")
					|| mode.trim().equals("Prev") || mode.trim().equals("Down")) {

				objTransDataBean.setIssuerId(objRequest
						.getParameter("ISSUER_ID"));
				objTransDataBean.setCardNoFrom(objRequest
						.getParameter("CARDNUMBER_FROM"));
				objTransDataBean.setCardNoTo(objRequest
						.getParameter("CARDNUMBER_TO"));
				objTransDataBean.setTransDateFrom(objRequest
						.getParameter("TRANS_DATE_FROM"));
				objTransDataBean.setTransTimeFrom(objRequest
						.getParameter("TRANS_TIME_FROM"));
				objTransDataBean.setTransDateTo(objRequest
						.getParameter("TRANS_DATE_TO"));
				objTransDataBean.setTransTimeTo(objRequest
						.getParameter("TRANS_TIME_TO"));
				objTransDataBean.setTransType(objRequest
						.getParameter("TRANS_TYPE"));
				objTransDataBean.setReferenceNo(objRequest
						.getParameter("REFERENCE_NO"));
				objTransDataBean.setResponseCode(objRequest
						.getParameter("RESPONSE_CODE"));
				objTransDataBean.setApprovalCode(objRequest
						.getParameter("APPROVAL_CODE"));
				objTransDataBean.setException(objRequest
						.getParameter("EXCEPTION"));
				objTransDataBean.setScriptReturn(StringUtil.checkBox(objRequest
						.getParameter("SCRIPT_RETURN")));
				String chipTrans = objRequest.getParameter("CHIP_TRANS");
				if (objRequest.getParameter("CHIP_TRANS") == null)
					chipTrans = "NOSEL";
				objTransDataBean.setChipTrans(chipTrans);
				objTransDataBean.setRowsPerPage(objRequest
						.getParameter("ROWSPERPAGE"));
				objTransDataBean.setChkIssuerResponse(StringUtil
						.checkBox(objRequest
								.getParameter("CHK_ISSUER_RESPONSE_CODE")));
				objTransDataBean.setChkEmvResponseCode(StringUtil
						.checkBox(objRequest
								.getParameter("CHK_EMV_RESPONSE_CODE")));
				objTransDataBean.setChkFinalResponse(StringUtil
						.checkBox(objRequest
								.getParameter("CHK_FINAL_RESPONSE_CODE")));
				objTransDataBean
						.setChkApprovalCode(StringUtil.checkBox(objRequest
								.getParameter("CHK_APPROVAL_CODE")));
				objTransDataBean.setChkTraceNo(StringUtil.checkBox(objRequest
						.getParameter("CHK_TRACE_NO")));
				objTransDataBean.setChkChipTrans(StringUtil.checkBox(objRequest
						.getParameter("CHK_CHIP_TRANS")));
				objTransDataBean
						.setChkArpcReturned(StringUtil.checkBox(objRequest
								.getParameter("CHK_ARPC_RETURNED")));
				objTransDataBean.setChkArqcValidation(StringUtil
						.checkBox(objRequest
								.getParameter("CHK_ARQC_VALIDATION")));
				objTransDataBean.setChkScriptGenerated(StringUtil
						.checkBox(objRequest
								.getParameter("CHK_SCRIPT_GENERATED")));
				objTransDataBean
						.setChkCvrTvrStatus(StringUtil.checkBox(objRequest
								.getParameter("CHK_CVR_TVR_STATUS")));
				objTransDataBean.setChkReasonException(StringUtil
						.checkBox(objRequest
								.getParameter("CHK_REASON_EXCEPTION")));

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
		int NUMBER_OF_SQL_FIELDS = 19;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be lessthan or equal 20.
		StringBuffer sbfList = new StringBuffer();
		sbfList
				.append(" SELECT TO_CHAR(DATETIME,'DD/MM/YYYY HH24:MI:SS') AS TRANSTIME, ");
		sbfList
				.append(" TRANXCODE, CURRCODE, AMOUNT, CARDNUMBER, TERMINALID, MERCHANTID, RESPONSECODE, F55_RESPONSECODE,");
		sbfList
				.append(" F55_FINALRESPONSECODE, APPROVALCODE, TRACENO, F55_EXIST, F55_ARPC_STATUS, F55_ARQC_STATUS, F55_SCRIPT_STATUS, F55_CVR_TVR_STATUS, F55_REASONOFEXCEPTION, ISSUER_ID ");

		return sbfList;
	}

	public StringBuffer getFilterFields(Object obj) {
		TransDataBean objTransDataBean = new TransDataBean();
		objTransDataBean = (TransDataBean) obj;
		StringBuffer sbfFrom = new StringBuffer();
		StringBuffer sbfWhere = new StringBuffer();
		String strMode = objTransDataBean.getMode();
		sbfFrom.append(" FROM TRANXLOG ");
		System.out.print("Str Mode --> " + strMode);

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY HH24:MI:SS";
		String strTransDateFrom = objTransDataBean.getTransDateFrom();
		String strTransDateTo = objTransDataBean.getTransDateTo();
		String strTransTimeFrom = objTransDataBean.getTransTimeFrom();
		String strTransTimeTo = objTransDataBean.getTransTimeTo();

		if (!strTransDateFrom.trim().equals("")) {
			String fromDateTime = strTransDateFrom + " " + strTransTimeFrom;
			sbfWhere.append(" AND DATETIME >= TO_DATE('"+ fromDateTime + "', '" + OUTPUT_DATE_FORMAT+ "') ");

		}
		if (!strTransDateTo.trim().equals("")) {
			String toDateTime = strTransDateTo + " " + strTransTimeTo;
			sbfWhere.append("  AND DATETIME < (TO_DATE('"+ toDateTime + "', '" + OUTPUT_DATE_FORMAT+ "') + 1) ");
		}

		String strIssuerId = objTransDataBean.getIssuerId();
		if (!strIssuerId.trim().equals(""))
			sbfWhere.append(" AND ISSUER_ID = '" + strIssuerId + "' ");

		if (!strMode.trim().equals("List")) {

			String strCardNoFrom = objTransDataBean.getCardNoFrom();
			String strCardNoTo = objTransDataBean.getCardNoTo();
			String strTransType = objTransDataBean.getTransType();
			String strReferenceNo = objTransDataBean.getReferenceNo();
			String strResponseCode = objTransDataBean.getResponseCode();
			String strApprovalCode = objTransDataBean.getApprovalCode();
			String strException = objTransDataBean.getException();
			String strScriptReturn = objTransDataBean.getScriptReturn();
			String strChipTrans = objTransDataBean.getChipTrans();

			if (!strCardNoFrom.trim().equals(""))
				sbfWhere.append(" AND CARDNUMBER >= '" + strCardNoFrom + "' ");
			if (!strCardNoTo.trim().equals(""))
				sbfWhere.append(" AND CARDNUMBER <= '" + strCardNoTo + "' ");

			if (!strTransType.trim().equals(""))
				sbfWhere.append("  AND TRANXCODE = '" + strTransType + "' ");
			if (!strReferenceNo.trim().equals(""))
				sbfWhere.append("  AND REFNO = '" + strReferenceNo + "' ");
			if (!strResponseCode.trim().equals(""))
				sbfWhere.append("  AND F55_FINALRESPONSECODE = '"
						+ strResponseCode + "' ");
			if (!strApprovalCode.trim().equals(""))
				sbfWhere.append("  AND APPROVALCODE = '" + strApprovalCode
						+ "' ");
			if (!strException.trim().equals("")) {
				/*
				 * 11-05-2004 EMV Fail F55_RESPONSECODE <> ‘00’ OR ARQC_OK = F
				 * OR ARPC_OK = F OR SCRIPT_OK = F Online Auth Fail (ARQC)
				 * ARQC_OK = F Issuer Auth Fail (ARPC) ARPC_OK = F Script Fail
				 * SCRIPT_OK = F Issuer Fail RESPONSECODE <> ‘00’
				 */
				if (strException.trim().equals("ALL"))
					sbfWhere.append("  AND (F55_FINALRESPONSECODE <> '00') ");
				if (strException.trim().equals("ARQC"))
					sbfWhere.append("  AND F55_ARQC_STATUS ='F' ");
				if (strException.trim().equals("ARPC"))
					sbfWhere.append("  AND F55_ARPC_STATUS ='F' ");
				if (strException.trim().equals("SCRIPT"))
					sbfWhere.append("  AND F55_SCRIPT_STATUS ='F' ");
				if (strException.trim().equals("ISSUER"))
					sbfWhere.append("  AND RESPONSECODE <> '00' ");
			}
			if (strScriptReturn.trim().equals("1"))
				sbfWhere.append("  AND F55_SCRIPT_STATUS <> ' ' ");
			if (strChipTrans.trim().equals("YES"))
				sbfWhere.append("  AND F55_EXIST = 'T' ");
			if (strChipTrans.trim().equals("NO"))
				sbfWhere.append("  AND F55_EXIST = 'F' ");
		} else {
			// if(objSession.getAttribute("TRANSDATABEAN") != null){
			// objSession.removeAttribute("TRANSDATABEAN");
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
			TransDataBean objTransDataBean = new TransDataBean();
			objTransDataBean = (TransDataBean) obj;
			System.out.println("getTransDateFrom = "+objTransDataBean.getTransDateFrom());
			System.out.println("getTransDateTo = "+objTransDataBean.getTransDateTo());
			System.out.println("getTransTimeFrom = "+objTransDataBean.getTransTimeFrom());
			System.out.println("getTransTimeTo = "+objTransDataBean.getTransTimeTo());

			StringBuffer sbfStr = new StringBuffer();
			sbfStr.append(getReportFields());
			sbfStr.append(getFilterFields(obj));
			System.out.println("\nReport SQL : " + sbfStr.toString());
			//DBManager objDBManager = new DBManager();
			//ResultSet rs = objDBManager.executeSQL(sbfStr.toString(), true);
			Session session = HibernetFactory.currentSession();
			Connection con = session.connection();
			Statement stmt = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sbfStr.toString());
			System.out.println("rs.toString = "+rs.toString());
			this.resultset = rs;
			System.out.println("rs.toString = "+resultset.toString());
		} catch (Exception exp) {
			System.out.println("======= Exception in getReportData =========");
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
	
    public static void main(String s[]) throws Exception {
    	ResultSet rs = null;
    	TransactionReport instance = new TransactionReport();
    	TransDataBean objReportViewBean = new TransDataBean();
    	objReportViewBean.setTransDateFrom("10/10/1000");
    	objReportViewBean.setTransDateTo("10/10/2006");
    	objReportViewBean.setTransTimeFrom("00:00:00");
    	objReportViewBean.setTransTimeTo("00:00:00");
    	rs = instance.getReportData(objReportViewBean);
    	System.out.println("rs.next = "+rs.next());
    	int i = 0;
    	do{
    		System.out.print(rs.getString("TRANSTIME"));
    		System.out.print(rs.getString("TRANXCODE"));
    		System.out.print(rs.getString("CURRCODE"));
    		System.out.print(rs.getString("AMOUNT"));
    		System.out.print(rs.getString("CARDNUMBER"));
    		System.out.print(rs.getString("AMOUNT"));
    		System.out.print(rs.getString("TERMINALID"));
    		System.out.print(rs.getString("MERCHANTID"));
    		System.out.print("\n");
    		i++;
    	}while (rs.next());
    }

}
