package org.transinfo.cacis.dataacess.daoimpl.oracle.printing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.printing.PrintDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;

public class PrintDAOImpl extends BaseDAOImpl implements PrintDAO {

	public Collection retrieve() throws TPlusException {
		Collection objSearchCollection = null;
		CommonDataBean objReport = new CommonDataBean();
		ArrayList arlResultData = new ArrayList();
		try {
			File dir = new File(PRINT_DOCUMENTS_FOLDER);

			String[] children = dir.list();
			if (children == null) {
				// Either dir does not exist or is not a directory
			} else {
				for (int i = 0; i < children.length; i++) {
					// Get filename of file or directory
					String filename = children[i];
					System.out.println("file " + i + " = " + filename);
				}
			}
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.indexOf(".pdf") > 0;
				}
			};
			children = dir.list(filter);
			System.out.println("children = " + children.length);

			for (int j = 0; j < children.length; j++) {
				String printfilename = dir.getAbsolutePath() + "\\"
						+ children[j];
				System.out.println("printfilename = " + printfilename);
				File theFile = new File(printfilename);
				long lastModified = theFile.lastModified();
				long length = theFile.length();
				objReport.setColumn1(Integer.toString(j + 1));
				objReport.setColumn2(children[j]);
				objReport.setColumn3(Long.toString(lastModified));
				objReport.setColumn4(Long.toString(length));
				arlResultData.add(objReport);
				objReport = new CommonDataBean();
			}
			System.out.println("=======================");
			objSearchCollection = arlResultData;
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the Print Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Print Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean print() throws TPlusException {
		boolean success = false;
		try {
			File dir = new File(PRINT_DOCUMENTS_FOLDER);

			String[] children = dir.list();
			if (children == null) {
				// Either dir does not exist or is not a directory
			} else {
				for (int i = 0; i < children.length; i++) {
					// Get filename of file or directory
					String filename = children[i];
					System.out.println("file " + i + " = " + filename);
				}
			}
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.indexOf(".pdf") > 0;
					//return name.contains(".pdf");
				}
			};
			children = dir.list(filter);
			System.out.println("children = " + children.length);

			for (int j = 0; j < children.length; j++) {
				String inputFile = dir.getAbsolutePath() + "\\" + children[j];
				System.out.println("inputFile = " + inputFile);
				String outputFile = generateOuputFileName(inputFile);
				convertPdfToPostScript(inputFile, outputFile);
				printDocument(outputFile);
			}
			cleanUpThread();
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the Print Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Print Search Info"
							+ e);
		} finally {

		}
		return success;
	}

	public boolean printerDetect(String ip) throws InterruptedException,
			IOException {
		Process p = Runtime.getRuntime().exec("ping -n 1 " + ip);
		int status = p.waitFor();
		System.out.println(ip + " is " + (status == 0 ? "alive" : "dead"));
		if (status == 0)
			return true;
		else
			return false;
	}

	public void printDocument(String outputFile) {
		try {
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

			PrintService printService[] = PrintServiceLookup
					.lookupPrintServices(flavor, pras);

			if (printService == null || printService.length == 0) {
				System.out.println("No printer detected");
			} else {
				PrintService service = null;

				PrintService defaultService = PrintServiceLookup
						.lookupDefaultPrintService();
				if (defaultService == null) {
					System.out.println("defaultService == null");
					System.out.println("No default printer detected");
				} else {
					System.out.println("defaultService != null");
					if (!defaultService.isDocFlavorSupported(flavor)) {
						System.out
								.println("The printer does not support the DocFlavor");
					} else {
						System.out.println("printer support");
						// this is to prompt the user with print dialog box
						service = ServiceUI.printDialog(null, 200, 200,
								printService, defaultService, flavor, pras);
					}
				}

				// this print without asking the user.
				// however, if allow this, then must use thread...
				// PrintService service = PrintServiceLookup
				// .lookupDefaultPrintService();

				if (service != null) {
					DocPrintJob job = service.createPrintJob();
					FileInputStream fis = new FileInputStream(outputFile);
					String printerName = service.getName();
					boolean printerStatus = false;
					int printerIpStarter = printerName.indexOf("\\\\");
					if (printerIpStarter > -1) {
						int printerIpEnd = printerName.lastIndexOf("\\");
						String printerIP = printerName.substring(
								printerIpStarter + 2, printerIpEnd);
						System.out.println("printerName = " + printerName);
						System.out.println("printerIP = " + printerIP);
						printerStatus = printerDetect(printerIP);
					} else {
						printerStatus = true;
					}

					if (printerStatus) {
						DocAttributeSet das = new HashDocAttributeSet();
						Doc doc = new SimpleDoc(fis, flavor, das);
						PrintJobWatcher pjDone = new PrintJobWatcher(job);
						try {
							job.print(doc, pras);
							pjDone.waitForDone();
						} catch (PrintException pe) {
							System.out.println(pe.getMessage());
						}
					} else {
						System.out
								.println("network printer is down. Printer Name: "
										+ printerName);
					}
				} else {
					System.out
							.println("No printer available, check printer driver");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method generates the post script output file name. The method
	 * obtains the path and PDF fileName and generates the post script file name
	 * with the same name as PDF file under the same path.
	 */
	protected String generateOuputFileName(String inputFile) {
		String filename = "";
		try {
			// Create a File Object
			File file = new File(inputFile);
			// from the give PDF file.
			filename = file.getName();
			System.out.println("filename = " + filename);
			String parent = file.getParent();
			System.out.println("parent = " + parent);
			StringTokenizer fileNameTokenizer = new StringTokenizer(filename,
					".");
			System.out
					.println("fileNameTokenizer = " + fileNameTokenizer == null);
			// Extract the file name
			filename = fileNameTokenizer.nextToken();
			System.out.println("filename = " + filename);

			filename = parent + "\\" + filename + ((new Date()).getTime())
					+ ".ps";
			File outputFile = new File(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

	/**
	 * This method convets the given PDF file to PS file This method uses the
	 * pdftopdf.exe tool to pass the input pdf file name and output ps file name
	 * to generate the Postscript version of the input PDF file.
	 */
	protected void convertPdfToPostScript(String inputFile, String outputFile) {
		try {
			Runtime runTime = Runtime.getRuntime();
			Process process = null;
			String command = PDF_TO_PS_TOOL_PATH + " " + inputFile + " "
					+ outputFile;
			process = runTime.exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method cleans up the generated post script document so that all the
	 * generated PS documents do not clog the hard drive space.
	 */
	protected void cleanUpPSDocument() {
		try {
			System.out.println("=============== cleanUpPSDocument ==============================");
			File file = new File(PRINT_DOCUMENTS_FOLDER);
			File[] fileList = file.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					// clear the output file, can used to clear the pdf also
					if (name.endsWith(".ps"))
						return true;
					else
						return false;
				}
			});
			if (fileList != null) {
				for (int i = 0; i < fileList.length; i++) {
					long lastModified = fileList[i].lastModified();
					System.out.println("lastModified = " + lastModified);

					// can decide whether clear disk every time before print or
					// after print
					// can also delete based on modified day...

					// If the file is more than ONE hour old then delete it.
					// if ((lastModified / (60 * 60 * 1000)) > (60 * 60 * 1000))

					// If the file is more than ONE minit old then delete it.
					// if ((lastModified / (60 * 1000)) > (60 * 1000))
					fileList[i].delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanUpThread() {
		cleanUpPSDocument();
	}

}
