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
public class ApplicationStatus implements ReportUtil {

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
			
			ArrayList arlProductTypeList = emvdbutil.getCardProductTypeList();
			objReportViewBean.setArlCardProductTypeList(arlProductTypeList);
			
			this.userName = (String) objSession.getAttribute("USERID");

			if (mode.trim().equals("Search") || mode.trim().equals("Next") || mode.trim().equals("View") || mode.trim().equals("Prev") || mode.trim().equals("Down")) {

				if (objRequest.getParameter("CARD_PRODUCT") != null) {
					objReportViewBean.setCardProduct(objRequest.getParameter("CARD_PRODUCT"));
				}

				if (objRequest.getParameter("CARD_TYPE") != null) {
					objReportViewBean.setCardProductType(objRequest.getParameter("CARD_TYPE"));
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
		sbfStr.append("JTT.PRODUCT_TYPE_DESC, JTT.CARD_TYPE_DESC, JTT.CARD_PRODUCT_ID, JTT.CARDPRODUCTNAME, ");
		sbfStr.append("SUM(NEW) AS NEW , SUM(APPROVED) AS APPROVED , SUM(REJECTED) AS REJECTED , SUM(PROCESSED) AS PROCESSED , SUM(PENDING) AS PENDING ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		String cardProduct = objReportViewBean.getCardProduct();
		String cardProductType = objReportViewBean.getCardProductType();
		
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("FROM ( ");
		
		sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC, CT.CARD_TYPE_DESC, CP.CARD_PRODUCT_ID, CP.CARDPRODUCTNAME, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 0 THEN AF.APPLICATION_ID ELSE NULL  END)  AS NEW, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 1 THEN AF.APPLICATION_ID ELSE NULL  END)  AS APPROVED, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 2 THEN AF.APPLICATION_ID ELSE NULL  END)  AS REJECTED, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 4 THEN AF.APPLICATION_ID ELSE NULL  END)  AS PROCESSED, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 5 THEN AF.APPLICATION_ID ELSE NULL  END)  AS PENDING ");
		sbfStr.append("FROM APPLICATIONFORMS AF, APPLICATION_CARD_PRODUCTS ACP, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT, CARD_TYPE CT ");
		sbfStr.append("WHERE AF.APPLICATION_ID = ACP.APPLICATION_ID ");
		sbfStr.append("AND ACP.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		sbfStr.append("AND CPT.PRODUCT_TYPE_ID = CP.CARD_PRODUCT_TYPE_ID ");
		sbfStr.append("AND CT.CARD_TYPE_ID = CP.CARD_TYPE_ID ");
		sbfStr.append("AND (AF.LAST_UPDATED_DATE >= TO_DATE('" + objReportViewBean.getFromDate() + "', '" + OUTPUT_DATE_FORMAT + "')) ");
		sbfStr.append("AND (AF.LAST_UPDATED_DATE < TO_DATE('" + objReportViewBean.getToDate() + "', '" + OUTPUT_DATE_FORMAT + "') + 1) ");
		
		if(cardProduct != null && !"".equals(cardProduct)){
			sbfStr.append("AND ACP.CARD_PRODUCT_ID = '"+ cardProduct +"' ");
		}
		if(cardProductType != null && !"".equals(cardProductType)){
			sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+ cardProductType +"' ");
		}
		
		sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC, CT.CARD_TYPE_DESC, CP.CARD_PRODUCT_ID, CP.CARDPRODUCTNAME ");
		
		sbfStr.append("UNION ALL ");
		
		sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC, CT.CARD_TYPE_DESC, CP.CARD_PRODUCT_ID, CP.CARDPRODUCTNAME, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 0 THEN AF.APPLICATION_ID ELSE NULL  END)  AS NEW, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 1 THEN AF.APPLICATION_ID ELSE NULL  END)  AS APPROVED, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 2 THEN AF.APPLICATION_ID ELSE NULL  END)  AS REJECTED, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 4 THEN AF.APPLICATION_ID ELSE NULL  END)  AS PROCESSED, ");
		sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 5 THEN AF.APPLICATION_ID ELSE NULL  END)  AS PENDING ");
		sbfStr.append("FROM SUPPLEMENTARY_FORMS AF, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT, CARD_TYPE CT ");
		sbfStr.append("WHERE AF.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		sbfStr.append("AND CPT.PRODUCT_TYPE_ID = CP.CARD_PRODUCT_TYPE_ID ");
		sbfStr.append("AND CT.CARD_TYPE_ID = CP.CARD_TYPE_ID ");
		sbfStr.append("AND (AF.LAST_UPDATED_DATE >= TO_DATE('" + objReportViewBean.getFromDate() + "', '" + OUTPUT_DATE_FORMAT + "')) ");
		sbfStr.append("AND (AF.LAST_UPDATED_DATE < TO_DATE('" + objReportViewBean.getToDate() + "', '" + OUTPUT_DATE_FORMAT + "') + 1) ");
		
		if(cardProduct != null && !"".equals(cardProduct)){
			sbfStr.append("AND AF.CARD_PRODUCT_ID = '"+ cardProduct +"' ");
		}
		if(cardProductType != null && !"".equals(cardProductType)){
			sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+ cardProductType +"' ");
		}
		
		sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC, CT.CARD_TYPE_DESC, CP.CARD_PRODUCT_ID, CP.CARDPRODUCTNAME ");
		
		sbfStr.append(") JTT ");
		sbfStr.append("GROUP BY JTT.PRODUCT_TYPE_DESC, JTT.CARD_TYPE_DESC, JTT.CARD_PRODUCT_ID, JTT.CARDPRODUCTNAME ");
		sbfStr.append("ORDER BY JTT.PRODUCT_TYPE_DESC, JTT.CARD_TYPE_DESC, JTT.CARDPRODUCTNAME ");

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
