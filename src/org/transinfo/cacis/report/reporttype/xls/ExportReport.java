//Package name
package org.transinfo.cacis.report.reporttype.xls;

//Java specific imports
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.transinfo.cacis.report.databean.ExportReportBean;
import org.transinfo.cacis.report.exception.ReportException;
import org.transinfo.cacis.report.reporttype.ReportTypeUtil;


@SuppressWarnings( { "unchecked", "deprecation" })
public class ExportReport implements ReportTypeUtil {

	public void exportReport(ExportReportBean objExportReportBean)
			throws ReportException {
		String inRptXml = objExportReportBean.getInputReportXml();
		Map inParams = objExportReportBean.getInputParams();
		ResultSet inRs = objExportReportBean.getInputResultSet();
		String outRptFile = objExportReportBean.getOutputFileName();
		HttpServletResponse resp = objExportReportBean.getResponse();
		// String exportType = objExportReportBean.getExportType();
		// DBManager dbManager = objExportReportBean.getM_InputDBManager();

		JasperPrint jasperPrint;
		try {
			System.out.println("Exporting Report...");
			try {
				// JRProperties.setProperty(JRProperties.COMPILER_CLASSPATH,
				// "C:\\TIPAC_SERVER\\lib\\jasperreports-1.2.7.jar");
				JRResultSetDataSource dataSource = new JRResultSetDataSource(
						inRs);
				JasperReport jasperReport = getCompiledReport(inRptXml);
				// Create JasperPrint object using the fillReport() method in
				// JasperManager class
				jasperPrint = JasperFillManager.fillReport(jasperReport, inParams,
						dataSource);
				resp.setHeader("content-disposition", "attachment;filename="
						+ outRptFile + ".xls");
				resp.setContentType("application/vnd.ms-excel");
				generateXlsOutput(jasperPrint, resp);

			} catch (IOException e) {
				System.out.println("Error writing report output : " + e);
				throw new ReportException(e.getMessage());
			}
		} catch (Exception bep) {
			System.out.println("Exception :" + bep.toString());
			if (bep instanceof ReportException)
				throw (ReportException) bep;
			else
				throw new ReportException(
						"Exception while generating the report : "
								+ bep.toString());
		} finally {
			/*
			 * if(dbManager!=null){ try{ dbManager.closeConnection();
			 * }catch(Exception e){ e.printStackTrace(); } }
			 */
		}
	}

	private void generateXlsOutput(JasperPrint jasperPrint,
			HttpServletResponse resp) throws IOException, JRException {
		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();

		// JRXlsExporter exporterXLS = new JRXlsExporter();
		// changed this class to display the logo on excel report
		JExcelApiExporter exporterXLS = new JExcelApiExporter();
		exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		// exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE,
		// Boolean.FALSE);
		// exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
		// Boolean.TRUE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporterXLS.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporterXLS.exportReport();

		System.out.println("Size of byte array:" + xlsReport.size());
		byte[] bytes = xlsReport.toByteArray();
		resp.setContentLength(bytes.length);
		ServletOutputStream ouputStream = resp.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		ouputStream.flush();
		ouputStream.close();
	}

	private JasperReport getCompiledReport(String fileName) throws JRException {
		//JasperDesign jasperDesign = JasperManager.loadXmlDesign(fileName);
		// Compile the JasperDesign
		JasperReport jasperReport = JasperCompileManager.compileReport(fileName);
		return jasperReport;
	}

}
