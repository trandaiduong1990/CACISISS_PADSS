//Package name
package org.transinfo.cacis.report.reporttype.html;

//Java specific imports
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;

import org.transinfo.cacis.AdminConfig;
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
		String exportType = objExportReportBean.getExportType();

		JasperPrint jasperPrint;
		try {
			AdminConfig.writeDebug("Exporting Report...");
			try {
				JRResultSetDataSource dataSource = new JRResultSetDataSource(
						inRs);

				JasperReport jasperReport = getCompiledReport(inRptXml);
				// Create JasperPrint object using the fillReport() method in
				// JasperManager class
				jasperPrint = JasperFillManager.fillReport(jasperReport, inParams,
						dataSource);

				if (exportType.trim().equals("Down"))
					resp.setHeader("content-disposition",
							"attachment;filename=" + outRptFile + ".html");
				else
					resp.setHeader("content-disposition", "inline;filename="
							+ outRptFile + ".html");

				generateHtmlOutput(jasperPrint, resp);

			} catch (IOException e) {
				AdminConfig.writeDebug("Error writing report output : " + e);
				throw new ReportException(e.getMessage());
			}
		} catch (Exception bep) {
			AdminConfig.writeDebug("Exception :" + bep.toString());
			if (bep instanceof ReportException)
				throw (ReportException) bep;
			else
				throw new ReportException(
						"Exception while generating the report : "
								+ bep.toString());
		}
	}

	private void generateHtmlOutput(JasperPrint jasperPrint,
			HttpServletResponse resp) throws IOException, JRException {
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, resp
				.getWriter());
		exporter.exportReport();
	}

	private JasperReport getCompiledReport(String fileName) throws JRException {
		//JasperDesign jasperDesign = JasperManager.loadXmlDesign(fileName);
		// Compile the JasperDesign
		JasperReport jasperReport = JasperCompileManager.compileReport(fileName);
		return jasperReport;
	}

}
