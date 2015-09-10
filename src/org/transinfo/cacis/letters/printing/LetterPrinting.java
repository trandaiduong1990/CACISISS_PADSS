package org.transinfo.cacis.letters.printing;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.controller.letters.LetterApplMapManager;
import org.transinfo.cacis.controller.letters.LetterDispatchManager;
import org.transinfo.cacis.controller.letters.LetterTemplateManager;
import org.transinfo.cacis.dto.letters.DispatchLetterSearchDto;
import org.transinfo.cacis.dto.letters.LetterTemplateDto;
import org.transinfo.cacis.util.AdminParamsLoad;

/**
 * LetterPrinting
 * 
 * @author hoang-vu
 * 
 */
public class LetterPrinting {
	
	private Logger logger = Logger.getLogger(LetterApplMapManager.class);
	
	public void letterPrinting() throws Exception {
		
		//Declate Letter Template and Letter Dispath
		LetterTemplateManager objLetterManager = new LetterTemplateManager();
		LetterDispatchManager objLetterDispManager = new LetterDispatchManager();
		
		//Get all Letter Template with status = "A"
		Collection objLetterTempCollection = objLetterManager.getAllLetterTemplate();
		
		//Config printing
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
	    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
		PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
		printServiceAttributeSet.add(new PrinterName(AdminParamsLoad.printerName, null));
		JRPrintServiceExporter exporter = new JRPrintServiceExporter();
		SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
		configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
		configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
		configuration.setDisplayPageDialog(false);
		//configuration.setDisplayPrintDialog(true);
		exporter.setConfiguration(configuration);
		
		for (Iterator<LetterTemplateDto> i = objLetterTempCollection.iterator(); i.hasNext();) {
			
			LetterTemplateDto objDto = i.next();
			try {
				Collection objLetterDispCollection = objLetterDispManager.getLetterDispByLetterId(objDto.getLetterId());
				
				for (Iterator<DispatchLetterSearchDto> j = objLetterDispCollection.iterator(); j.hasNext();) {
					DispatchLetterSearchDto objDispLetterDto = j.next();
					String jasperDesign = AdminParamsLoad.letterPath + objDto.getApplicationSource();
					JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
					Map listParamLetter = objLetterManager.executeQuery(objDto.getSqlQuery());
					//listParamLetter.put("CARD_NUMBER", String.valueOf(objDispLetterDto.getCardNumber()));
					//listParamLetter.put("LAST_UPDATE_DATE", String.valueOf(objDispLetterDto.getLastUpdateDate()));
					JasperPrint jasperPrint = 
				             JasperFillManager.fillReport(jasperReport, listParamLetter, new JREmptyDataSource()); 
					
					exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
					exporter.exportReport();
				}
				
			} catch (Exception e) {
				logger.error(e);
				throw new TPlusException(
						"Error in LetterApplMapManager letterPrinting method" + e);
			}
		}
	}
}
