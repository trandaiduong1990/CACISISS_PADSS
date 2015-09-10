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
public class CardHolderStatistics implements ReportUtil {

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
			
			ArrayList arlProductTypeList = emvdbutil.getCardProductTypeList();
			objReportViewBean.setArlCardProductTypeList(arlProductTypeList);
			
			this.userName = (String) objSession.getAttribute("USERID");

			if (mode.trim().equals("Search") || mode.trim().equals("Next") || mode.trim().equals("View") || mode.trim().equals("Prev") || mode.trim().equals("Down")) {

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
		sbfStr.append("FJTT.PRODUCT_TYPE_DESC, SUM(BCOUNT) AS BCOUNT, SUM(NCARDS) AS NCARDS, ");
		sbfStr.append("SUM(RECARDS) AS RECARDS, SUM(CCCARDS) AS CCCARDS, SUM(ECOUNT) AS ECOUNT, ");
		sbfStr.append("SUM(ACTAPPS) AS ACTAPPS, SUM(PENDAPPS) AS PENDAPPS, SUM(REJAPPS) AS REJAPPS ");

		return sbfStr;
	}

	public StringBuffer getFilterFields(Object obj) {
		ReportViewBean objReportViewBean = new ReportViewBean();
		objReportViewBean = (ReportViewBean) obj;

		String OUTPUT_DATE_FORMAT = "DD/MM/YYYY";
		String cardProductType = objReportViewBean.getCardProductType();
		String startDate = objReportViewBean.getFromDate();
		String endDate = objReportViewBean.getToDate();
		
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("FROM ( ");
		sbfStr.append("SELECT JTT1.PRODUCT_TYPE_DESC, SUM(CNO) AS BCOUNT, 0 AS NCARDS, 0 AS RECARDS, 0 AS CCCARDS, 0 AS ECOUNT, 0 AS ACTAPPS, 0 AS PENDAPPS, 0 AS REJAPPS ");
		sbfStr.append("FROM ( ");
		      sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC, COUNT(CA.CARDNUMBER) AS CNO  ");
		      sbfStr.append("FROM CARDS CA, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT ");
		      sbfStr.append("WHERE CA.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		            sbfStr.append("AND CP.CARD_PRODUCT_TYPE_ID = CPT.PRODUCT_TYPE_ID ");
		            sbfStr.append("AND CA.STATUS <> 'C' ");
		            
		            if(cardProductType != null && !"".equals(cardProductType)){
			            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+cardProductType+"' ");
		            }
		            
		            sbfStr.append("AND (CA.LAST_UPDATED_DATE < TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
		      sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC ");
		      sbfStr.append(") JTT1  ");
		sbfStr.append("GROUP BY JTT1.PRODUCT_TYPE_DESC ");

		sbfStr.append("UNION ALL ");

		sbfStr.append("SELECT JTT1.PRODUCT_TYPE_DESC, 0 AS BCOUNT, SUM(CNO) AS NCARDS, 0 AS RECARDS, 0 AS CCCARDS, 0 AS ECOUNT, 0 AS ACTAPPS, 0 AS PENDAPPS, 0 AS REJAPPS ");
		sbfStr.append("FROM ( ");
		      sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC, COUNT(CA.CARDNUMBER) AS CNO  ");
		      sbfStr.append("FROM APPLICATIONFORMS AF, CUSTOMER CU, CARDS CA, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT ");
		      sbfStr.append("WHERE AF.APPLICATION_ID = CU.APPLICATION_ID ");
		            sbfStr.append("AND CU.CUSTOMER_ID = CA.CUSTOMER_ID ");
		            sbfStr.append("AND CA.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		            sbfStr.append("AND CP.CARD_PRODUCT_TYPE_ID = CPT.PRODUCT_TYPE_ID ");
		            sbfStr.append("AND AF.APPLICATIONSTATUS = 4 ");
		            
		            if(cardProductType != null && !"".equals(cardProductType)){
			            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+cardProductType+"' ");
		            }
		            
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE >= TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"')) ");
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
		      sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC ");
		      
		      sbfStr.append("UNION ALL ");
		      
		      sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC, COUNT(CA.CARDNUMBER) AS CNO ");
		      sbfStr.append("FROM SUPPLEMENTARY_FORMS AF, CUSTOMER CU, CARDS CA, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT ");
		      sbfStr.append("WHERE AF.APPLICATION_ID = CU.APPLICATION_ID ");
		            sbfStr.append("AND CU.CUSTOMER_ID = CA.CUSTOMER_ID ");
		            sbfStr.append("AND CA.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		            sbfStr.append("AND CP.CARD_PRODUCT_TYPE_ID = CPT.PRODUCT_TYPE_ID ");
		            sbfStr.append("AND AF.APPLICATIONSTATUS = 4 ");
		            
		            if(cardProductType != null && !"".equals(cardProductType)){
			            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+cardProductType+"' ");
		            }
		            
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE >= TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"')) ");
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
		      sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC ");
		      sbfStr.append(") JTT1  ");
		sbfStr.append("GROUP BY JTT1.PRODUCT_TYPE_DESC ");

		sbfStr.append("UNION ALL ");

		sbfStr.append("SELECT JTT1.PRODUCT_TYPE_DESC, 0 AS BCOUNT, 0 AS NCARDS, SUM(CNO) AS RECARDS, 0 AS CCCARDS, 0 AS ECOUNT, 0 AS ACTAPPS, 0 AS PENDAPPS, 0 AS REJAPPS ");
		sbfStr.append("FROM ( ");
		      sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC, COUNT(CA.CARDNUMBER) AS CNO  ");
		      sbfStr.append("FROM CARDS_RENEWAL AF, CARDS CA, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT ");
		      sbfStr.append("WHERE AF.CARDNUMBER = CA.CARDNUMBER ");
		            sbfStr.append("AND CA.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		            sbfStr.append("AND CP.CARD_PRODUCT_TYPE_ID = CPT.PRODUCT_TYPE_ID ");
		            sbfStr.append("AND AF.STATUS = 4 ");
		            
		            if(cardProductType != null && !"".equals(cardProductType)){
			            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+cardProductType+"' ");
		            }
		            
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE >= TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"')) ");
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
		      sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC ");
		      sbfStr.append(") JTT1  ");
		sbfStr.append("GROUP BY JTT1.PRODUCT_TYPE_DESC ");

		sbfStr.append("UNION ALL ");

		sbfStr.append("SELECT JTT1.PRODUCT_TYPE_DESC, 0 AS BCOUNT, 0 AS NCARDS, 0 AS RECARDS, SUM(CNO) AS CCCARDS, 0 AS ECOUNT, 0 AS ACTAPPS, 0 AS PENDAPPS, 0 AS REJAPPS ");
		sbfStr.append("FROM ( ");
		      sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC, COUNT(CA.CARDNUMBER) AS CNO  ");
		      sbfStr.append("FROM CARDCLOSE_FORMS AF, CARDS CA, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT ");
		      sbfStr.append("WHERE AF.CARDNUMBER = CA.CARDNUMBER ");
		            sbfStr.append("AND CA.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		            sbfStr.append("AND CP.CARD_PRODUCT_TYPE_ID = CPT.PRODUCT_TYPE_ID ");
		            sbfStr.append("AND AF.APPLICATIONSTATUS = 4 ");
		            
		            if(cardProductType != null && !"".equals(cardProductType)){
			            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+cardProductType+"' ");
		            }
		            
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE >= TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"')) ");
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
		      sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC ");
		      sbfStr.append(") JTT1  ");
		sbfStr.append("GROUP BY JTT1.PRODUCT_TYPE_DESC ");

		sbfStr.append("UNION ALL ");

		sbfStr.append("SELECT JTT1.PRODUCT_TYPE_DESC, 0 AS BCOUNT, 0 AS NCARDS, 0 AS RECARDS, 0 AS CCCARDS, SUM(CNO) AS ECOUNT, 0 AS ACTAPPS, 0 AS PENDAPPS, 0 AS REJAPPS ");
		sbfStr.append("FROM ( ");
		      sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC, COUNT(CA.CARDNUMBER) AS CNO  ");
		      sbfStr.append("FROM CARDS CA, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT ");
		      sbfStr.append("WHERE CA.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		            sbfStr.append("AND CP.CARD_PRODUCT_TYPE_ID = CPT.PRODUCT_TYPE_ID ");
		            sbfStr.append("AND CA.STATUS <> 'C' ");
		            
		            if(cardProductType != null && !"".equals(cardProductType)){
			            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+cardProductType+"' ");
		            }
		            
		            sbfStr.append("AND (CA.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
		      sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC ");
		      sbfStr.append(") JTT1  ");
		sbfStr.append("GROUP BY JTT1.PRODUCT_TYPE_DESC ");

		sbfStr.append("UNION ALL ");

		sbfStr.append("SELECT JTT1.PRODUCT_TYPE_DESC, 0 AS BCOUNT, 0 AS NCARDS, 0 AS RECARDS, 0 AS CCCARDS, 0 AS ECOUNT, SUM(APPROVED) AS ACTAPPS, SUM(PENDING) AS PENDAPPS, SUM(REJECTED) AS REJAPPS ");
		sbfStr.append("FROM ( ");
		      sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC,  ");
		       sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 4 THEN AF.APPLICATION_ID ELSE NULL  END)  AS APPROVED, ");
		       sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 5 THEN AF.APPLICATION_ID ELSE NULL  END)  AS PENDING, ");
		       sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 2 THEN AF.APPLICATION_ID ELSE NULL  END)  AS REJECTED ");
		      sbfStr.append("FROM APPLICATIONFORMS AF, APPLICATION_CARD_PRODUCTS ACP, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT ");
		      sbfStr.append("WHERE AF.APPLICATION_ID = ACP.APPLICATION_ID ");
		            sbfStr.append("AND ACP.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = CP.CARD_PRODUCT_TYPE_ID ");
		            
		            if(cardProductType != null && !"".equals(cardProductType)){
			            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+cardProductType+"' ");
		            }
		            
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE >= TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"')) ");
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
		      sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC ");

		      sbfStr.append("UNION ALL ");

		      sbfStr.append("SELECT CPT.PRODUCT_TYPE_DESC,  ");
		             sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 4 THEN AF.APPLICATION_ID ELSE NULL  END)  AS APPROVED, ");
		             sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 5 THEN AF.APPLICATION_ID ELSE NULL  END)  AS PENDING, ");
		             sbfStr.append("COUNT (CASE WHEN AF.APPLICATIONSTATUS = 2 THEN AF.APPLICATION_ID ELSE NULL  END)  AS REJECTED ");
		      sbfStr.append("FROM SUPPLEMENTARY_FORMS AF, CARD_PRODUCTS CP, CARD_PRODUCT_TYPE CPT ");
		      sbfStr.append("WHERE AF.CARD_PRODUCT_ID = CP.CARD_PRODUCT_ID ");
		            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = CP.CARD_PRODUCT_TYPE_ID ");
		            
		            if(cardProductType != null && !"".equals(cardProductType)){
			            sbfStr.append("AND CPT.PRODUCT_TYPE_ID = '"+cardProductType+"' ");
		            }
		            
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE >= TO_DATE('"+startDate+"', '"+OUTPUT_DATE_FORMAT+"')) ");
		            sbfStr.append("AND (AF.LAST_UPDATED_DATE < TO_DATE('"+endDate+"', '"+OUTPUT_DATE_FORMAT+"') + 1) ");
		      sbfStr.append("GROUP BY CPT.PRODUCT_TYPE_DESC ");
		      sbfStr.append(") JTT1  ");
		sbfStr.append("GROUP BY JTT1.PRODUCT_TYPE_DESC ");
		sbfStr.append(") FJTT ");
		sbfStr.append("GROUP BY FJTT.PRODUCT_TYPE_DESC ");
		sbfStr.append("ORDER BY FJTT.PRODUCT_TYPE_DESC ");

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
