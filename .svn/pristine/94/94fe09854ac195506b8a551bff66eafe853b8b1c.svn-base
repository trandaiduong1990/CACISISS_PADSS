package org.transinfo.cacis.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.log4j.Logger;

import com.transinfo.batch.CTFMsg;
import com.transinfo.batch.DraftData;
import com.transinfo.batch.Header;
import com.transinfo.batch.LogicalTranx;
import com.transinfo.batch.TC;

public class CardEmboss {

	private Logger logger = Logger.getLogger(CardEmboss.class);

	String outDir = "";
	String fileName = "";
	LogicalTranx logicalTnx = null;
	int counter = 1;

	public CardEmboss(String strOutDir) {

		//System.out.println("Working directory----------------"+new File(".").getAbsolutePath());
		//ReadProperties.readProperties();

		logicalTnx = new LogicalTranx();
		outDir = strOutDir;
		System.out.println("outDir=" + outDir);

	}
	
	public void setFileName(String strFileName){
		fileName = strFileName;
	}
	
	public boolean embossHeader(EmbossFileHeaderContent objEmbossFileHeaderContent) throws Exception {
		boolean returnStatus = false;
		
		try{
			
			Header objHeader = new Header();
			
			objHeader.set("Header1", objEmbossFileHeaderContent.getHeader1());
	        objHeader.set("Separator1", "|");
	        objHeader.set("Header2", objEmbossFileHeaderContent.getHeader2());
	        objHeader.set("Separator2", "|");
	        objHeader.set("Header3", objEmbossFileHeaderContent.getHeader3());
	        objHeader.set("Separator3", "|");
	        objHeader.set("Header4", objEmbossFileHeaderContent.getHeader4());
	        objHeader.set("Separator4", "|");
	        objHeader.set("Header5", objEmbossFileHeaderContent.getHeader5());
	        objHeader.set("Separator5", "|");
			
			TC tc = (TC)objHeader;
			logicalTnx.add(tc);
			
			returnStatus = true;
			
		}catch (Exception exp) {
			System.out.println(exp);
			throw exp;
		}
		
		return returnStatus;
		
	}

	public boolean cardEmboss(EmbossFileContent objEmbossFileContent) throws Exception {
		boolean returnStatus = false;

		try {
			logger.error("Embossing Started..");
			System.out.println("Embossing Started..");
			DraftData tcr = new DraftData(0);
			
			tcr.set("SNO", StringUtil.LPAD(new Integer(counter++).toString(), 6, "0"));
	        tcr.set("Separator1", "|");
	        tcr.set("CardNo", objEmbossFileContent.getCardNo());
	        tcr.set("Separator2", "|");
	        tcr.set("ConstSpace1", StringUtil.getSpace(3));
	        tcr.set("IssueDate", objEmbossFileContent.getIssuingDate());
	        tcr.set("ConstSpace2", StringUtil.getSpace(3));
	        tcr.set("ExpDate", objEmbossFileContent.getExpiryDate());
	        tcr.set("ConstSpace3", StringUtil.getSpace(14));
	        tcr.set("Separator3", "|");
	        tcr.set("Name", StringUtil.RPAD(objEmbossFileContent.getEmbossName(), 30, " "));
	        tcr.set("Separator4", "|");
	        tcr.set("ConstSpace4", StringUtil.getSpace(30));
	        tcr.set("Separator5", "|");
	        tcr.set("ConstSpace5", StringUtil.getSpace(25));
	        tcr.set("CVV", objEmbossFileContent.getCvv2());
	        tcr.set("ConstSpace6", StringUtil.getSpace(2));
	        tcr.set("Separator6", "|");
	        tcr.set("Unknown1", "000000610927");
	        tcr.set("ConstSpace7", StringUtil.getSpace(18));
	        tcr.set("Separator7", "|");
	        tcr.set("StartTrack1", "%B");
	        tcr.set("Track1", objEmbossFileContent.getTrack1data());
	        //tcr.set("EndTrack1", "?");
	        //tcr.set("ConstSpace8", StringUtil.getSpace(9));
	        tcr.set("Separator8", "|");
	        tcr.set("StartTrack2", ";");
	        tcr.set("Track2", objEmbossFileContent.getTrack2data());
	        //tcr.set("EndTrack2", "?");
	        //tcr.set("ConstSpace9", StringUtil.getSpace(3));
	        tcr.set("Separator9", "|");
	        tcr.set("Unknown2", "_;7?");
	        tcr.set("Separator10", "|");
	        tcr.set("Unknown3", "1");
	        tcr.set("Separator11", "|");
	        tcr.set("Image", objEmbossFileContent.getImagePath());
	        tcr.set("Separator12", "|");
	        tcr.set("iCVVData", objEmbossFileContent.getIcvvData());
	        tcr.set("Separator13", "|");
	        tcr.set("Address1", objEmbossFileContent.getAddress1());
	        tcr.set("Separator14", "|");
	        tcr.set("Address2", objEmbossFileContent.getAddress2());
	        tcr.set("Separator15", "|");
	        tcr.set("City", objEmbossFileContent.getCity());
	        tcr.set("Separator16", "|");
	        tcr.set("StateCountry", objEmbossFileContent.getStateCountry());
	        tcr.set("Separator17", "|");
	        tcr.set("ConstSpace11", StringUtil.getSpace(40));
	        tcr.set("Separator18", "|");
	        tcr.set("ConstSpace12", StringUtil.getSpace(40));
	        tcr.set("Separator19", "|");
	        tcr.set("Unknown5", StringUtil.LPAD("123", 10, " "));
	        tcr.set("Separator20", "|");
	        //tcr.set("Unknown6", 8);
	        tcr.set("Separator21", "|");
	        tcr.set("ConstSpace13", StringUtil.getSpace(70));
	        tcr.set("Separator22", "|");

			TC tc = (TC) tcr;
			logicalTnx.add(tc);
			
			returnStatus = true;

		} catch (Exception exp) {

			logger.error(new Object(), exp);
			System.out.println(exp);
			throw exp;

		}

		return returnStatus;
	}

	public String creatBatchOutFile() throws Exception {
		try {

			if (!logicalTnx.isEmpty()) {

				System.out.println("GL File Creating ....");

				CTFMsg amsg = new CTFMsg();
				// add the last logical transaction
				amsg.add(logicalTnx);

				// Store file to CTF directory
				/*java.util.Date now = new java.util.Date(System.currentTimeMillis());
				SimpleDateFormat df;
				df = new SimpleDateFormat("ddMMyyyy_HHmm");
				String dateTime = df.format(now);*/

				// Make sure the file name is unique by assigning the time of
				// creation to the file name
				//String fileName = "PREPAID_EMB_" + dateTime + ".txt";
				System.out.println(outDir + fileName);
				File file = new File(outDir + fileName);
				FileOutputStream fos = new FileOutputStream(file);

				// Write the whole message to an output stream
				amsg.write2Stream(fos);
				fos.flush();
				fos.close();

				System.out
						.println(" NUMBER OF RECORDS AVAILABLE IN BATCH FILE  "
								+ logicalTnx.getBatchCount());

				return outDir + fileName;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			throw e;

		}

	}

	private String pad(String strMode, String orgString, String appendString,
			int len) {
		if (strMode.equals("L")) {
			for (int i = orgString.length(); i < len; i++) {
				orgString = appendString + orgString;
			}

			return orgString;

		} else {

			for (int i = orgString.length(); i < len; i++) {
				orgString = orgString + appendString;
			}
			return orgString;
		}

	}

	@SuppressWarnings("unused")
	private String getCardEmbossData(String strName, String strCardNo,
			String strStartDate, String strEndDate) {

		String strEmbName = pad("R", strName, " ", 24);
		String strEmbCardno = strCardNo.substring(0, 4) + " "
				+ strCardNo.substring(4, 8) + " " + strCardNo.substring(8, 12)
				+ " " + strCardNo.substring(12, 16);
		String strEmbExpDate = strEndDate.substring(0, 2) + "/"
				+ strEndDate.substring(2, 4);
		return strEmbName + "~" + strEmbCardno + "~" + strEmbExpDate;

	}

	public static void main(String s[]) throws Exception {

		CardEmboss objCardEmboss = new CardEmboss("C:\\Users\\CTTI-IT-96\\Desktop\\NeedToClean\\BCEL_LOG\\27-09-2013\\");
		
		String pPrefix = "VCC";
		Date embossDate = new Date();
		String pName = "VISA DEBIT BCEL";
		
		String strFileName = "001_EM03"+pPrefix+"_"+DateUtil.getStrDate("yyMMdd", embossDate)+"_"+DateUtil.getStrDate("HHmmss", embossDate)+".txt";
		objCardEmboss.setFileName(strFileName);

		EmbossFileHeaderContent objEmbossFileHeaderContent = new EmbossFileHeaderContent();
		objEmbossFileHeaderContent.setHeader1("901726");
		objEmbossFileHeaderContent.setHeader2(DateUtil.getStrDate("ddMMMyy", embossDate));
		objEmbossFileHeaderContent.setHeader3(DateUtil.getStrDate("HHmmss", embossDate));
		objEmbossFileHeaderContent.setHeader4("01");
		objEmbossFileHeaderContent.setHeader5(StringUtil.RPAD(StringUtil.getRequireString(pName,20), 20, " "));
		
		objCardEmboss.embossHeader(objEmbossFileHeaderContent);
		

		EmbossFileContent objEmbossFileContent = new EmbossFileContent();
		
		objEmbossFileContent.setAddress1(StringUtil.RPAD("qqq", 40, " "));
		objEmbossFileContent.setAddress2(StringUtil.RPAD("qqq", 40, " "));
		objEmbossFileContent.setCity(StringUtil.RPAD("qqq", 40, " "));
		String stateCountry = "qqq"+","+"qqq";
		objEmbossFileContent.setStateCountry(StringUtil.RPAD(stateCountry, 40, " "));
		
		objEmbossFileContent.setCardNo("4705 3200 0000 0036");
		objEmbossFileContent.setCardHolderName("Debit Test BCEL");
		objEmbossFileContent.setEmbossName("Debit Test BCEL");
		objEmbossFileContent.setExpiryDate("09/16");
		objEmbossFileContent.setIssuingDate("09/13");
		objEmbossFileContent.setCvv2(StringUtil.LPAD("337", 3, "0"));
		objEmbossFileContent.setTrack1data("4705320000000036^DEBIT TEST BCEL/^1609120100000000000000186000000?");
		objEmbossFileContent.setTrack2data("4705320000000036=16091201000000000186?");
		objEmbossFileContent.setImagePath("");
		
		String icvvDate = "1309";
		String icvvData = "682"+"001"+icvvDate+"01";
		objEmbossFileContent.setIcvvData(icvvData);
		objCardEmboss.cardEmboss(objEmbossFileContent);
		
		
		System.out.println(objCardEmboss.creatBatchOutFile());

	}
}