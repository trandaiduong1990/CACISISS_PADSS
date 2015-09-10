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
import org.transinfo.cacis.util.DBUtil;

@SuppressWarnings({"unchecked","unused"})
public class CardsWriteOff implements ReportUtil {

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

			this.userName = (String) objSession.getAttribute("USERID");

			if (mode.trim().equals("Search") || mode.trim().equals("Next") || mode.trim().equals("View") || mode.trim().equals("Prev") || mode.trim().equals("Down")) {
				/*
				if (objRequest.getParameter("TRANS_DATE_FROM") != null) {
					objReportViewBean.setFromDate(objRequest.getParameter("TRANS_DATE_FROM"));
				}
				 */
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
		int NUMBER_OF_SQL_FIELDS = 4;
		return NUMBER_OF_SQL_FIELDS;
	}

	public StringBuffer getReportFields() {
		// number of fields should be less than or equal 20.

		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("SELECT ");
		sbfStr.append("cu.customer_name, c.cardnumber, wp.writeoff_count, al.autopay_account, ac.credit_limit, ac.limit_used, ");
		sbfStr.append("ct.cust_type_desc, cs.stat_amt, cs.stat_min_amt, ca.tel, cs.PREV_STAT_AMT-cs.STAT_PAY_AMT as pre_unpaid_amt ");
		sbfStr.append(", b.BRANCH_NAME ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "MM/YYYY";
		String startDate = objReportViewBean.getFromDate();
		String endDate = objReportViewBean.getToDate();

		StringBuffer sbfStr = new StringBuffer();
		//sbfStr.append("FROM ( ");
		sbfStr.append("FROM customer_statement cs , customer_fee cf , statement_feedetails sf , cards c , customer_account ac , card_atm_link al , customer cu , customer_address ca, writeoff_process wp, cust_type ct ");
		sbfStr.append(", branch b ");
		//sbfStr.append(" ,  WRITEOFF_HISTORY wh ");
		//sbfStr.append("WHERE (CA.LAST_UPDATED_DATE >= TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"') and  ");
		sbfStr.append( "WHERE cu.customer_id = c.customer_id and cs.stat_id=c.last_statement_id and ca.customer_id = cu.customer_id and c.cardnumber = wp.cardnumber " );
		//sbfStr.append( "WHERE cu.customer_id = c.customer_id and cs.CARD_NUMBER=c.CARDNUMBER and ca.customer_id = cu.customer_id and c.cardnumber = wp.cardnumber and " );
		sbfStr.append( "AND cu.BRANCH_ID = b.BRANCH_ID(+) ");
		sbfStr.append( " and c.account_id = ac.account_id and c.cardnumber = al.cardnumber and cs.stat_id = sf.statement_id and cf.id = sf.fee_id and ct.CUST_TYPE_ID = cu.CUST_TYPE_ID AND ca.ADDR_TYPE='H' and cf.ref_id='LPF' and cs.stat_gen='Y' ");
		//sbfStr.append( "and wp.CARDNUMBER=wh.CARDNUMBER ");
		sbfStr.append( "and to_char(generated_date,'MM/YYYY') ='"+endDate+"' ");
		//sbfStr.append( "and to_char(wh.LAST_UPDATED,'MM/YYYY') ='"+endDate+"' ");
		//sbfStr.append( "and to_char(generated_date,'MM/YYYY') ='"+endDate+"')");
		//sbfStr.append("AND (CA.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
		sbfStr.append( "order by wp.writeoff_count, c.cardnumber ");


		return sbfStr;
	}

	public ResultSet getReportData(Object obj) throws ReportException {
		try {

			StringBuffer sbfStr = new StringBuffer();
			sbfStr.append(getReportFields());
			sbfStr.append(getFilterFields(obj));
			System.out.println("CardsWriteOffQuery: " + sbfStr.toString() );
			DBManager objDBManager = new DBManager();

			ResultSet rs = objDBManager.executeSQL(sbfStr.toString(), true);

			this.resultset = rs;

		} catch (Exception exp) {
			System.out.println("CardsWriteOff.java: Exception " + exp);
			if (exp instanceof ReportException){
				System.out.println("CardsWriteOff.java: ReportException ");
				throw (ReportException) exp;
			}
			else {
				System.out.println("CardsWriteOff.java: Exception in Executing the Query ");
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
			//parameters.put("FROM_DATE", objReportViewBean.getFromDate());
			parameters.put("TO_DATE", objReportViewBean.getToDate());
			parameters.put("USERNAME", this.userName);

		} catch (Exception e) {
			throw new ReportException("" + ErrorCodes.PARAMETER_ERR, "Exception in getting parameters for the report");
		}
		return parameters;
	}

}
