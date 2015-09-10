package org.transinfo.cacis.util;

import org.apache.log4j.Logger;
import org.jpos.iso.ISOUtil;

import vn.com.tivn.hsm.phw.EracomPHW;
import vn.com.tivn.hsm.phw.PrintDataSet;

public class PINMailer {
	
	private Logger logger = Logger.getLogger(PINMailer.class);

	//PrintDataSet[] ds = new PrintDataSet[0];
	PrintDataSet[] ds = new PrintDataSet[2];

	public boolean printPINMailer(String strCardNo, String strCustName,
			String strPinBlock, String strPPK) throws Exception {

		try {
			/*
			 * ds[0] = new PrintDataSet();
			 * ds[0].setLineNo(ISOUtil.hex2byte("03"));
			 * ds[0].setColumnNo(ISOUtil.hex2byte("15"));
			 * ds[0].setData(ISOUtil.hex2byte("6213546210000016")); ds[1] = new
			 * PrintDataSet(); ds[1].setLineNo(ISOUtil.hex2byte("05"));
			 * ds[1].setColumnNo(ISOUtil.hex2byte("15"));
			 * ds[1].setData(ISOUtil.hex2byte("Nathalath1"));
			 */
			
			ds[0] = new PrintDataSet();
			ds[0].setLineNo(ISOUtil.hex2byte("0" + Integer.toHexString(5)));
			System.out.println("1");
			ds[0].setColumnNo(ISOUtil.hex2byte("0" + Integer.toHexString(15)));
			System.out.println("2");
			ds[0].setData(ISOUtil.hex2byte(stringToHex(strCardNo)));
			ds[1] = new PrintDataSet();
			ds[1].setLineNo(ISOUtil.hex2byte("0" + Integer.toHexString(8)));
			System.out.println("1");
			ds[1].setColumnNo(ISOUtil.hex2byte("0" + Integer.toHexString(15)));
			System.out.println("2");
			ds[1].setData(ISOUtil.hex2byte(stringToHex(strCustName)));
			System.out.println("3");

			int res = EracomPHW.printPINfromPINBlock(strPPK, strPinBlock,
					EracomPHW.PIN_FORMAT_ANSI, strCardNo, ds);
			System.out.println("res from HSM :: " + res);
			logger.error("res from HSM :: " + res);
			if (res == 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception npe) {
			logger.error(new Object(),npe);
			throw npe;
		}

	}

	static String stringToHex(String str) {

		char[] chars = str.toCharArray();
		StringBuffer strBuffer = new StringBuffer();

		for (int i = 0; i < chars.length; i++) {
			strBuffer.append(Integer.toHexString((int) chars[i]));
		}

		return strBuffer.toString();

	}

	public static void main(String str[]) throws Exception {
		PINMailer pinMailer = new PINMailer();
		EracomPHW.init("10.0.19.53", 1000, 5, 5000);
		System.out.println(pinMailer.printPINMailer("4705320000000036", "test", "68FCD5A8C664144A", "1"));
	}
}