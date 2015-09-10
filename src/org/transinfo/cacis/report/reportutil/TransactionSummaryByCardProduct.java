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
public class TransactionSummaryByCardProduct implements ReportUtil {

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
			
			this.userName = (String) objSession.getAttribute("USERID");

			if (mode.trim().equals("Search") || mode.trim().equals("Next") || mode.trim().equals("View") || mode.trim().equals("Prev") || mode.trim().equals("Down")) {

				if (objRequest.getParameter("CARD_PRODUCT") != null) {
					objReportViewBean.setCardProduct(objRequest.getParameter("CARD_PRODUCT"));
				}

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
		int NUMBER_OF_SQL_FIELDS = 9;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be less than or equal 20.
		
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("SELECT ");
		sbfStr.append("CP.CARD_TYPE_ID, CP.CARDPRODUCTNAME, CT.CARD_TYPE_DESC, ");
		sbfStr.append("SUM (CASE WHEN UPPER(TL.TRANXCODE) = 'WITHDRAWAL' THEN AMOUNT ELSE 0 END)  AS TOTWITHDRAWAL, ");
		sbfStr.append("SUM (CASE WHEN UPPER(TL.TRANXCODE) = 'SALE' THEN AMOUNT ELSE 0 END)  AS TOTSALE, ");
		sbfStr.append("SUM (CASE WHEN UPPER(TL.TRANXCODE) = 'CASH' THEN AMOUNT ELSE 0 END)  AS TOTCASH, ");
		sbfStr.append("SUM (CASE WHEN UPPER(TL.TRANXCODE) = 'REFUND' THEN AMOUNT ELSE 0 END)  AS TOTREFUND, ");
		sbfStr.append("SUM (CASE WHEN UPPER(TL.TRANXCODE) = 'REVERSAL' THEN AMOUNT ELSE 0 END)  AS TOTREVERSAL, ");
		sbfStr.append("SUM (CASE WHEN UPPER(TL.TRANXCODE) = 'BALANCE' THEN AMOUNT ELSE 0 END)  AS TOTBALANCE ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		String cardProduct = objReportViewBean.getCardProduct();

		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("FROM TRANXLOG TL, CARDS CA, CARD_PRODUCTS CP, CARD_TYPE CT ");
		sbfStr.append("WHERE (DATETIME >= TO_DATE('" + objReportViewBean.getFromDate() + "', '" + OUTPUT_DATE_FORMAT + "')) ");
		sbfStr.append("AND (DATETIME < TO_DATE('" + objReportViewBean.getToDate() + "', '" + OUTPUT_DATE_FORMAT + "') + 1) ");
		sbfStr.append("AND TL.CARDNUMBER = CA.CARDNUMBER ");
		sbfStr.append("AND CA.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		sbfStr.append("AND CP.CARD_TYPE_ID = CT.CARD_TYPE_ID ");

		if ((cardProduct != null) && (!cardProduct.trim().equals(""))) {
			sbfStr.append("AND CA.CARD_PRODUCT_ID = '" + cardProduct + "' ");
		}

		sbfStr.append("GROUP BY CP.CARD_TYPE_ID, CP.CARDPRODUCTNAME, CT.CARD_TYPE_DESC ");
		sbfStr.append("ORDER BY CP.CARDPRODUCTNAME ");

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
