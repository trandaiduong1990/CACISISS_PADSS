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

@SuppressWarnings("unchecked")
public class TransactionHistory implements ReportUtil {

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
			
			String strUserIssuerId = (String)objSession.getAttribute("ISSUER");
			ArrayList arlProductList = emvdbutil.getCardProductList(strUserIssuerId);
			objReportViewBean.setArlCardProductList(arlProductList);
			
			ArrayList arlResponseCodeList = emvdbutil.getResponseCodeList();
			objReportViewBean.setArlResponseCodeList(arlResponseCodeList);
			
			this.userName = (String) objSession.getAttribute("USERID");

			if (mode.trim().equals("Search") || mode.trim().equals("Next") || mode.trim().equals("View") || mode.trim().equals("Prev") || mode.trim().equals("Down")) {

				if (objRequest.getParameter("CARD_PRODUCT") != null) {
					objReportViewBean.setCardProduct(objRequest.getParameter("CARD_PRODUCT"));
				}

				if (objRequest.getParameter("CARD_NO") != null) {
					objReportViewBean.setCardNo(objRequest.getParameter("CARD_NO"));
				}

				if (objRequest.getParameter("TRANS_DATE_FROM") != null) {
					objReportViewBean.setFromDate(objRequest.getParameter("TRANS_DATE_FROM"));
				}

				if (objRequest.getParameter("TRANS_DATE_TO") != null) {
					objReportViewBean.setToDate(objRequest.getParameter("TRANS_DATE_TO"));
				}
				
				if (objRequest.getParameter("TRANX_STATUS") != null) {
					objReportViewBean.setTranxStatus(objRequest.getParameter("TRANX_STATUS"));
				}

				if (objRequest.getParameter("RESPONSE_CODE") != null) {
					objReportViewBean.setResponseCode(objRequest.getParameter("RESPONSE_CODE"));
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
		int NUMBER_OF_SQL_FIELDS = 12;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be less than or equal 20.
		
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("SELECT ");
		sbfStr.append("TO_CHAR(DATETIME,'DD/MM/YYYY HH24:MI:SS') AS TRANSACTION_DATE, ");
		sbfStr.append("TL.CARDNUMBER, TRANXCODE, MERCHANTID, TERMINALID, TRANX_CARDHOLDER_AMT AS AMOUNT, ");
		sbfStr.append("CURR_SYMBOL, REFNO, TRACENO, RESPONSECODE, APPROVALCODE, RECON ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		String cardNo = objReportViewBean.getCardNo();
		String cardProduct = objReportViewBean.getCardProduct();
		
		String tranxStatus = objReportViewBean.getTranxStatus();
		String responseCode = objReportViewBean.getResponseCode();
		
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("FROM TRANXLOG TL, CURRENCIES CU, CARDS CA ");
		sbfStr.append("WHERE (DATETIME >= TO_DATE('" + objReportViewBean.getFromDate() + "', '" + OUTPUT_DATE_FORMAT + "')) ");
		sbfStr.append("AND (DATETIME < TO_DATE('" + objReportViewBean.getToDate() + "', '" + OUTPUT_DATE_FORMAT + "') + 1) ");
		sbfStr.append("AND TL.tranx_settlement_curr  = CU.CURR_CODE ");
		sbfStr.append("AND TL.CARDNUMBER = CA.CARDNUMBER ");
		
		if(tranxStatus != null && !"".equals(tranxStatus) && "A".equals(tranxStatus)){
			sbfStr.append("AND TL.RESPONSECODE = '00' ");
		}else if(tranxStatus != null && !"".equals(tranxStatus) && "D".equals(tranxStatus)){
			if(responseCode == null || "".equals(responseCode)){
				sbfStr.append("AND TL.RESPONSECODE <> '00' ");
			}else{
				if (responseCode != null && !responseCode.equals("") && "00".equals(responseCode)) {
					sbfStr.append("AND TL.RESPONSECODE = 'DUMMY' ");
				}else{
					sbfStr.append("AND TL.RESPONSECODE = '" + responseCode + "' ");
				}
			}
		}else{
			if ((responseCode != null) && (!responseCode.equals(""))) {
				sbfStr.append("AND TL.RESPONSECODE = '" + responseCode + "' ");
			}
		}

		/*if ((responseCode != null) && (!responseCode.equals(""))) {
			sbfStr.append("AND TL.RESPONSECODE = '" + responseCode + "' ");
		}*/
		if ((cardNo != null) && (!cardNo.trim().equals(""))) {
			sbfStr.append("AND TL.CARDNUMBER = '" + cardNo + "' ");
		}
		if ((cardProduct != null) && (!cardProduct.trim().equals(""))) {
			sbfStr.append("AND CA.CARD_PRODUCT_ID = '" + cardProduct + "' ");
		}

		sbfStr.append("ORDER BY TL.TRANXLOGID DESC ");

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
