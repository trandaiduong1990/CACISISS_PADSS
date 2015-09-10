package com.transinfo.batch;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class TestEmboss {

	String jdbcURL = "";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String user = "";
	String passwd = "";
	String outDir = "C:\\Users\\CTTI-IT-96\\Desktop\\NeedToClean\\BCEL_LOG";
	LogicalTranx logicalTnx = null;

	public static void main(String s[]) throws Exception {
		
		System.out.println("test".subSequence(0, 1));
		
		/*TestEmboss tm = new TestEmboss();
		tm.createEmbossHeader();
		tm.createEmbossData();*/
	}

	public TestEmboss() {
		logicalTnx = new LogicalTranx();
	}
	
	public void createEmbossHeader() throws Exception {
		Header objHeader = new Header();
		
		objHeader.set("Header1", "901726");
        objHeader.set("Separator1", "|");
        objHeader.set("Header2", "29JUL13");
        objHeader.set("Separator2", "|");
        objHeader.set("Header3", "173146");
        objHeader.set("Separator3", "|");
        objHeader.set("Header4", "01");
        objHeader.set("Separator4", "|");
        objHeader.set("Header5", "Visa Debit          ");
        objHeader.set("Separator5", "|");
        //objHeader.set("End", 1676);
		
		TC tc = (TC)objHeader;
		logicalTnx.add(tc);
	}

	public void createEmbossData() throws Exception {
		try {

			DraftData tcr = new DraftData(0);
			
			tcr.set("SNO", "000001");
	        tcr.set("Separator1", "|");
	        tcr.set("CardNo", "4705 32XX XXXX 1647");
	        tcr.set("Separator2", "|");
	        tcr.set("ConstSpace", getSpace(3));
	        tcr.set("IssueDate", "07/13");
	        tcr.set("ConstSpace", getSpace(3));
	        tcr.set("ExpDate", "07/17");
	        tcr.set("ConstSpace", getSpace(14));
	        tcr.set("Separator3", "|");
	        tcr.set("Name", "XIENTHONG PHIMMAVONG          ");
	        tcr.set("Separator4", "|");
	        tcr.set("ConstSpace", getSpace(30));
	        tcr.set("Separator5", "|");
	        tcr.set("ConstSpace", getSpace(20));
	        tcr.set("CVV", "596");
	        tcr.set("ConstSpace", getSpace(7));
	        tcr.set("Separator6", "|");
	        tcr.set("Unknown1", "000000610927");
	        tcr.set("ConstSpace", getSpace(18));
	        tcr.set("Separator7", "|");
	        tcr.set("StartTrack1", "%B");
	        tcr.set("Track1", "470532XXXXXX1647^PHIMMAVONG/XIENTHONG^1707201119370000XXXXXX662000000");
	        tcr.set("EndTrack1", "?");
	        tcr.set("ConstSpace", getSpace(9));
	        tcr.set("Separator8", "|");
	        tcr.set("StartTrack2", ";");
	        tcr.set("Track2", "470532XXXXXX1647=17072011193700000662");
	        tcr.set("EndTrack2", "?");
	        tcr.set("ConstSpace", getSpace(3));
	        tcr.set("Separator9", "|");
	        tcr.set("Unknown2", "_;7?");
	        tcr.set("Separator10", "|");
	        tcr.set("Unknown3", "1");
	        tcr.set("Separator11", "|");
	        tcr.set("Image", "XIENTHONG PHIMMAVONG.JPG      ");
	        tcr.set("Separator12", "|");
	        tcr.set("Unknown4", "098001130701");
	        tcr.set("Separator13", "|");
	        tcr.set("Name2", "MRS X PHIMMAVONG                        ");
	        tcr.set("Separator14", "|");
	        tcr.set("Name3", "B. KHAMHAUNG M. SAYTHANY VTE            ");
	        tcr.set("Separator15", "|");
	        tcr.set("Country", "LAOS                                    ");
	        tcr.set("Separator16", "|");
	        tcr.set("ConstSpace", getSpace(40));
	        tcr.set("Separator17", "|");
	        tcr.set("ConstSpace", getSpace(40));
	        tcr.set("Separator18", "|");
	        tcr.set("ConstSpace", getSpace(40));
	        tcr.set("Separator19", "|");
	        tcr.set("Unknown5", "       123");
	        tcr.set("Separator20", "|");
	        tcr.set("Unknown6", "00000000");
	        tcr.set("Separator21", "|");
	        tcr.set("ConstSpace", getSpace(70));
	        tcr.set("Separator22", "|");

			TC tc = (TC) tcr;

			logicalTnx.add(tc);
			
			tcr = new DraftData(0);
			
			tcr.set("SNO", "000002");
	        tcr.set("Separator1", "|");
	        tcr.set("CardNo", "4705 32XX XXXX 1647");
	        tcr.set("Separator2", "|");
	        tcr.set("ConstSpace", getSpace(3));
	        tcr.set("IssueDate", "07/13");
	        tcr.set("ConstSpace", getSpace(3));
	        tcr.set("ExpDate", "07/17");
	        tcr.set("ConstSpace", getSpace(14));
	        tcr.set("Separator3", "|");
	        tcr.set("Name", "XIENTHONG PHIMMAVONG          ");
	        tcr.set("Separator4", "|");
	        tcr.set("ConstSpace", getSpace(30));
	        tcr.set("Separator5", "|");
	        tcr.set("ConstSpace", getSpace(20));
	        tcr.set("CVV", "596");
	        tcr.set("ConstSpace", getSpace(7));
	        tcr.set("Separator6", "|");
	        tcr.set("Unknown1", "000000610927");
	        tcr.set("ConstSpace", getSpace(18));
	        tcr.set("Separator7", "|");
	        tcr.set("StartTrack1", "%B");
	        tcr.set("Track1", "470532XXXXXX1647^PHIMMAVONG/XIENTHONG^1707201119370000XXXXXX662000000");
	        tcr.set("EndTrack1", "?");
	        tcr.set("ConstSpace", getSpace(9));
	        tcr.set("Separator8", "|");
	        tcr.set("StartTrack2", ";");
	        tcr.set("Track2", "470532XXXXXX1647=17072011193700000662");
	        tcr.set("EndTrack2", "?");
	        tcr.set("ConstSpace", getSpace(3));
	        tcr.set("Separator9", "|");
	        tcr.set("Unknown2", "_;7?");
	        tcr.set("Separator10", "|");
	        tcr.set("Unknown3", "1");
	        tcr.set("Separator11", "|");
	        tcr.set("Image", "XIENTHONG PHIMMAVONG.JPG      ");
	        tcr.set("Separator12", "|");
	        tcr.set("Unknown4", "098001130701");
	        tcr.set("Separator13", "|");
	        tcr.set("Name2", "MRS X PHIMMAVONG                        ");
	        tcr.set("Separator14", "|");
	        tcr.set("Name3", "B. KHAMHAUNG M. SAYTHANY VTE            ");
	        tcr.set("Separator15", "|");
	        tcr.set("Country", "LAOS                                    ");
	        tcr.set("Separator16", "|");
	        tcr.set("ConstSpace", getSpace(40));
	        tcr.set("Separator17", "|");
	        tcr.set("ConstSpace", getSpace(40));
	        tcr.set("Separator18", "|");
	        tcr.set("ConstSpace", getSpace(40));
	        tcr.set("Separator19", "|");
	        tcr.set("Unknown5", "       123");
	        tcr.set("Separator20", "|");
	        tcr.set("Unknown6", "00000000");
	        tcr.set("Separator21", "|");
	        tcr.set("ConstSpace", getSpace(70));
	        tcr.set("Separator22", "|");

			tc = (TC) tcr;

			logicalTnx.add(tc);

			if (!logicalTnx.isEmpty()) {
				// logger.writeToFile("Creating GL File....");

				creatGLOutFile(logicalTnx);
				// logger.writeToFile("GL File Created ....");
				System.out.println("GL File Created ....");

			} else {
				System.out.println("/*****************************************************************************/");
				System.out.println("/***************** NO RECORDS AVAILABLE FOR GL POSTING ***********************/");
				System.out.println("/*****************************************************************************/");

				// logger.writeToFile("*****************************************************************************");
				// logger.writeToFile("***************** NO RECORDS AVAILABLE FOR GL POSTING ***********************");
				// logger.writeToFile("*****************************************************************************");
			}
		} catch (Exception exp) {

			System.out.println(exp);
			throw exp;

		}

	}

	public String getSpace(int noOfSpaces) {

		String res = "";
		for (int i = 0; i < noOfSpaces; i++) {
			res += " ";
		}

		return res;
	}

	public String creatGLOutFile(LogicalTranx logicalTnx) throws Exception {
		try {

			CTFMsg amsg = new CTFMsg();
			// add the last logical transaction
			amsg.add(logicalTnx);

			// Store file to CTF directory
			java.util.Date now = new java.util.Date(System.currentTimeMillis());
			SimpleDateFormat df;
			df = new SimpleDateFormat("MMddHHmmss");
			String dateTime = df.format(now);

			// Make sure the file name is unique by assigning the time of
			// creation to the file name
			String fileName = "GL_" + dateTime + ".txt";
			System.out.println(outDir + "\\" + fileName);
			File file = new File(outDir + "\\" + fileName);
			FileOutputStream fos = new FileOutputStream(file);

			// Write the whole message to an output stream
			amsg.write2Stream(fos);
			fos.flush();
			fos.close();
			// Commit transaction and close everything
			System.out.println(" NUMBER OF RECORDS AVAILABLE IN GL FILE  "
					+ logicalTnx.getBatchCount());
			// logger.writeToFile(" NUMBER OF RECORDS AVAILABLE IN GL FILE  "+
			// logicalTnx.getBatchCount());

			return fileName;

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			throw e;

		}

	}

	public static String isNull(String strValue) throws Exception {

		if (strValue == null || strValue.trim().equals("")) {
			// throw new NullPointerException();
		}
		return strValue;
	}

}