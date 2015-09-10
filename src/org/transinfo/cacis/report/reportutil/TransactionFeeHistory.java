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
public class TransactionFeeHistory implements ReportUtil {

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
		int NUMBER_OF_SQL_FIELDS = 7;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be less than or equal 20.
		
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("SELECT tdate, cardno, tranxcode, des, amt, sign, refId ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		String cardNo = objReportViewBean.getCardNo();
		String cardProduct = objReportViewBean.getCardProduct();
		
		StringBuffer sbfStr = new StringBuffer();

		sbfStr.append(" FROM ( ");
		sbfStr.append(" SELECT to_char(tl.datetime, 'DD/MM/YYYY HH:MI:SS AM') as tdate, tl.cardnumber as cardno, tl.tranxcode, tl.merchantname as des, to_number(tl.tranx_cardholder_amt) as amt, '' as sign, tl.tranxlogid as refId ");
		sbfStr.append(" FROM TRANXLOG TL, CARDS CA ");
		sbfStr.append(" WHERE tl.cardnumber = ca.cardnumber ");
		sbfStr.append(" and tl.deleted='N'  ");
		sbfStr.append(" and tl.responsecode='00'  ");
		sbfStr.append(" and (tl.datetime >= to_date('" + objReportViewBean.getFromDate() + "', '" + OUTPUT_DATE_FORMAT + "')) ");
		sbfStr.append(" and (tl.datetime < to_date('" + objReportViewBean.getToDate() + "', '" + OUTPUT_DATE_FORMAT + "') + 1) ");
		
		if(cardNo != null && !"".equals(cardNo)){
			sbfStr.append(" and TL.CARDNUMBER='"+cardNo+"' ");
		}
		
		if(cardProduct != null && !"".equals(cardProduct)){
			sbfStr.append(" and ca.card_product_id='"+cardProduct+"' ");
		}

		sbfStr.append(" UNION ALL ");

		sbfStr.append(" SELECT to_char(cf.fee_date, 'DD/MM/YYYY HH:MI:SS AM') as tdate, cf.cardno as cardno, 'FEE' as tranxcode, f.fdesc as des, cf.fee_amt as amt, cf.amount_sign as sign, cf.tranxlogid as refId ");
		sbfStr.append(" FROM CUSTOMER_FEE CF, FEE F, CARDS CA ");
		sbfStr.append(" WHERE cf.cardno = ca.cardnumber ");
		sbfStr.append(" and CF.REF_ID=F.FEE_ID  ");
		sbfStr.append(" and (cf.fee_date >= to_date('" + objReportViewBean.getFromDate() + "', '" + OUTPUT_DATE_FORMAT + "')) ");
		sbfStr.append(" and (cf.fee_date < to_date('" + objReportViewBean.getToDate() + "', '" + OUTPUT_DATE_FORMAT + "') + 1 ) ");

		if(cardNo != null && !"".equals(cardNo)){
			sbfStr.append(" and CF.CARDNO='"+cardNo+"' ");
		}
		
		if(cardProduct != null && !"".equals(cardProduct)){
			sbfStr.append(" and ca.card_product_id='"+cardProduct+"' ");
		}
		
		sbfStr.append(" ) ");
		sbfStr.append(" order by refId desc ");

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
