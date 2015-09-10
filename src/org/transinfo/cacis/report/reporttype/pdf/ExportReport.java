//Package name
package org.transinfo.cacis.report.reporttype.pdf;

//Java specific imports
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.transinfo.cacis.report.databean.ExportReportBean;
import org.transinfo.cacis.report.exception.ReportException;
import org.transinfo.cacis.report.reporttype.ReportTypeUtil;

@SuppressWarnings("unchecked")
public class ExportReport implements ReportTypeUtil {

	@SuppressWarnings("deprecation")
	public void exportReport(ExportReportBean objExportReportBean)
			throws ReportException {
		String inRptXml = objExportReportBean.getInputReportXml();
		Map inParams = objExportReportBean.getInputParams();
		ResultSet inRs = objExportReportBean.getInputResultSet();
		String outRptFile = objExportReportBean.getOutputFileName();
		HttpServletResponse resp = objExportReportBean.getResponse();
		String exportType = objExportReportBean.getExportType();

		try {
			// AdminConfig.writeDebug("Exporting Report...");
			// JRProperties.setProperty(JRProperties.COMPILER_CLASSPATH,
			// "C:\\TIPAC_SERVER\\lib\\jasperreports-1.2.7.jar");
			System.out.println("***1***" + inRptXml);
			// load JasperDesign from XML and compile it into JasperReport
			//JasperDesign jasperDesign = JasperManager.loadXmlDesign(inRptXml);
			System.out.println("***1***");
			// Compile the JasperDesign
			JasperReport jasperReport = JasperCompileManager
					.compileReport(inRptXml);
			System.out.println("***2***");
			// Get a result set
			JRResultSetDataSource dataSource = new JRResultSetDataSource(inRs);
			System.out.println("***3***");
			// Create JasperPrint using fillReport() method
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
					inParams, dataSource);
			System.out.println("***4***");
			// Export and return PDF file
			byte buf[] = JasperExportManager.exportReportToPdf(jasperPrint);
			System.out.println("***5***");
			resp.setContentType("application/pdf");
			resp.setContentLength(buf.length);
			System.out.println("***6***" + exportType);
			if (exportType.trim().equals("Down"))
				resp.setHeader("content-disposition", "attachment;filename="
						+ outRptFile + ".pdf");
			else
				resp.setHeader("content-disposition", "inline;filename="
						+ outRptFile + ".pdf");
			System.out.println("***7***");
			OutputStream out = resp.getOutputStream();
			out.write(buf, 0, buf.length);
			out.close();
			System.out.println("***8*** PDF DISPATHED");
		} catch (Exception bep) {
			System.out.println("Report Exception=" + bep);
			if (bep instanceof ReportException)
				throw (ReportException) bep;
			else
				throw new ReportException(
						"Exception while generating the report : "
								+ bep.toString());
		}
	}

}
