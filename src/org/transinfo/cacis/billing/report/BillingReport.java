package org.transinfo.cacis.billing.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.transinfo.cacis.billing.utils.BillingParams;
import org.transinfo.cacis.billing.utils.BillingUtil;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.report.db.DBManager;

@SuppressWarnings({"deprecation", "unchecked"})
public class BillingReport {

	private static final Logger billLog = Logger.getLogger("BillLog");
	private ResultSet resultset = null;
	
	private static InputStream inputStream = BillingReport.class.getResourceAsStream(ICacisiss.IBilling.BILL_REPORT_DEFAULT_FILE);
	
	public ResultSet getReportData(Date billingDate, String cardNo) {
		try {
			
			StringBuffer sbfStr = new StringBuffer();
			sbfStr.append(getReportFields());
			sbfStr.append(getFilterFields(billingDate, cardNo));

			DBManager objDBManager = new DBManager();
			ResultSet rs = objDBManager.executeSQL(sbfStr.toString(), true);
			
			this.resultset = rs;
			
		} catch (Exception exp) {
			billLog.error(exp);
		}
		return resultset;
	}
	
	public StringBuffer getReportFields() {
		
		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("SELECT ");
		sbfStr.append("JTT.TRANSACTION_DATE, ");
		sbfStr.append("JTT.TRANX_TYPE, JTT.GL_TYPE, JTT.AMOUNT ");

		return sbfStr;
	}
	
	public StringBuffer getFilterFields(Date billingDate, String cardNo) {

		StringBuffer sbfStr = new StringBuffer();
		sbfStr.append("FROM ( ");
		
		sbfStr.append("SELECT ");
		sbfStr.append("DATETIME AS TRANSACTION_DATE, ");
		sbfStr.append("TRANX_TYPE, GL_TYPE, AMOUNT ");
		sbfStr.append("FROM TRANX_CREDIT_GL TCG ");
		sbfStr.append("WHERE TCG.BILLED = 'Y' ");
		sbfStr.append("AND TCG.CARD_NO = '" + cardNo + "' ");
		sbfStr.append("AND TCG.DATETIME >= TO_DATE('" + BillingUtil.getStrStartDate(billingDate) + "', '"+ BillingUtil.sqlDateFormat +"') ");
		sbfStr.append("AND TCG.DATETIME <= TO_DATE('" + BillingUtil.getStrEndDate(billingDate) + "', '"+ BillingUtil.sqlDateFormat +"') ");

		sbfStr.append("UNION ALL ");
		
		sbfStr.append("SELECT ");
		sbfStr.append("DATETIME AS TRANSACTION_DATE, ");
		sbfStr.append("TRANX_TYPE, GL_TYPE, AMOUNT ");
		sbfStr.append("FROM FEE_CREDIT_GL FCG ");
		sbfStr.append("WHERE FCG.BILLED = 'Y' ");
		sbfStr.append("AND FCG.CARD_NO = '" + cardNo + "' ");
		sbfStr.append("AND FCG.DATETIME >= TO_DATE('" + BillingUtil.getStrStartDate(billingDate) + "', '"+ BillingUtil.sqlDateFormat +"') ");
		sbfStr.append("AND FCG.DATETIME <= TO_DATE('" + BillingUtil.getStrEndDate(billingDate) + "', '"+ BillingUtil.sqlDateFormat +"') ");
		
		sbfStr.append(") JTT ");
		sbfStr.append("ORDER BY JTT.TRANSACTION_DATE ");

		return sbfStr;
	}
	
	public static void createInputStream(){
		
		try{
			//String inRptXml = "D:\\Projects\\Cacisiss\\report\\xmls\\BillingReport.xml";
			String inRptXml = BillingParams.billReportXML;
			if(inRptXml == null){
				billLog.warn("Bill Report XML file is NOT configured on properties file");
				inputStream = BillingReport.class.getResourceAsStream(ICacisiss.IBilling.BILL_REPORT_DEFAULT_FILE);
			}else{
				File reportFile = new File(inRptXml);
				if(reportFile.exists()){
					if(reportFile.isFile()){
						inputStream = new FileInputStream(inRptXml);
					}else{
						billLog.warn("'" + inRptXml + "' is NOT file");
						inputStream = BillingReport.class.getResourceAsStream(ICacisiss.IBilling.BILL_REPORT_DEFAULT_FILE);
					}
				}else{
					billLog.warn("Bill Report XML file '" + inRptXml + "' is NOT exists");
					inputStream = BillingReport.class.getResourceAsStream(ICacisiss.IBilling.BILL_REPORT_DEFAULT_FILE);
				}
			}
		}catch (Exception e) {
			billLog.error(e);
		}
	}
	
	public void exportReport(ResultSet objResultSet, String path, String cardNo, Map params){
		
		try{
			
			String fileName = cardNo + ".pdf";
			String pdfFile = path + File.separator + fileName;
			
			// load JasperDesign from XML and compile it into JasperReport
			//JasperDesign jasperDesign = JasperManager.loadXmlDesign(inputStream);
	
			// Compile the JasperDesign
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
	
			// Get a result set
			JRResultSetDataSource dataSource = new JRResultSetDataSource(objResultSet);
	
			// Create JasperPrint using fillReport() method
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			
			// 1- export to PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFile);
			
			billLog.info("Report " + fileName + " created on " + path + " location");
		
		}catch (Exception e) {
			billLog.error(e);
		}
		
	}
	
	// for testing
	public static void main(String[] args) {
		BillingReport objBillingReport = new BillingReport();
		objBillingReport.getReportData(new Date(), "6221590000000018");
	}

}
