package org.transinfo.cacis.util;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.transinfo.cacis.common.constants.EmbossFileConstants;

public class CardEmbossFile {
	private WritableCellFormat times;
	private String inputFile;
	private WritableWorkbook workbook;
	private WritableSheet excelSheet;
	private WorkbookSettings wbSettings;
	private CellView cv;
	private File file;

	public void setOutputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void fileCreate() throws IOException, WriteException {
		//File file = new File(inputFile);
		file = new File(inputFile);

		wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("en", "EN"));

		workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet("CardEmboss", 0);
		excelSheet = workbook.getSheet(0);
		excelSheet.setColumnView(0, 25);
		excelSheet.setColumnView(1, 30);
		excelSheet.setColumnView(2, 35);

		createLabel(excelSheet);
	}

	private void createLabel(WritableSheet sheet) throws WriteException {
		// Lets create a times font
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 11);
		// Define the cell format
		times = new WritableCellFormat(times10pt);

		cv = new CellView();
		cv.setFormat(times);

	}

	public boolean createRow(EmbossFileContent objEmbossFileContent)
			throws WriteException, RowsExceededException, IOException {

		boolean res = false;
		int rowNumber = objEmbossFileContent.getRowNumber();

		// First column
		addLabel(0, rowNumber, objEmbossFileContent.getCardNo());
		// Second column
		addLabel(1, rowNumber, objEmbossFileContent.getCardHolderName());
		// Third column
		addLabel(2, rowNumber, objEmbossFileContent.getEmbossName());
		// forth column
		addLabel(3, rowNumber, objEmbossFileContent.getExpiryDate());
		// fifth column
		addLabel(4, rowNumber, objEmbossFileContent.getIssuingDate());
		// sixth column
		addLabel(5, rowNumber, objEmbossFileContent.getCvv2());
		// seventh column
		addLabel(6, rowNumber, objEmbossFileContent.getTrack1data());
		// eighth column
		addLabel(7, rowNumber, objEmbossFileContent.getTrack2data());
		// ninth column
		addLabel(8, rowNumber, objEmbossFileContent.getImagePath());
		res = true;
		
		return res;

	}
	
	public void headerCreate() throws IOException, WriteException {
		// First column
		addCaption(0, 0, EmbossFileConstants.COLUMN_1);
		// Second column
		addCaption(1, 0, EmbossFileConstants.COLUMN_2);
		// Third column
		addCaption(2, 0, EmbossFileConstants.COLUMN_3);
		// forth column
		addCaption(3, 0, EmbossFileConstants.COLUMN_4);
		// fifth column
		addCaption(4, 0, EmbossFileConstants.COLUMN_5);
		// sixth column
		addCaption(5, 0, EmbossFileConstants.COLUMN_6);
		// seventh column
		addCaption(6, 0, EmbossFileConstants.COLUMN_7);
		// eighth column
		addCaption(7, 0, EmbossFileConstants.COLUMN_8);
		// ninth column
		addCaption(8, 0, EmbossFileConstants.COLUMN_9);
	}

	private void addCaption(int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s);
		excelSheet.addCell(label);
	}

	public void writeAndClose() throws WriteException, RowsExceededException,
			IOException {
		workbook.write();
		workbook.close();
	}

	public void makeReadOnly(){
		file.setReadOnly();
	}

	private void addLabel(int column, int row, String s) throws WriteException,
			RowsExceededException, IOException {
		Label label;
		label = new Label(column, row, s);
		excelSheet.addCell(label);
	}
}
