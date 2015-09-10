package org.transinfo.cacis.report.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRParameter;

import org.transinfo.cacis.AdminConfig;
import org.transinfo.cacis.ErrorCodes;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.report.ReportFactory;
import org.transinfo.cacis.report.ReportUtil;
import org.transinfo.cacis.report.databean.ExportReportBean;
import org.transinfo.cacis.report.databean.ReportTypeBean;
import org.transinfo.cacis.report.db.DBManager;
import org.transinfo.cacis.report.exception.ReportException;
import org.transinfo.cacis.report.reporttype.ReportTypeFactory;
import org.transinfo.cacis.report.reporttype.ReportTypeUtil;
import org.transinfo.cacis.util.AdminParamsLoad;
import org.transinfo.cacis.util.XmlUtil;

@SuppressWarnings( { "static-access", "serial", "unchecked", "unused" })
public class GenerateReportServlet extends HttpServlet {
	DBManager objDBManager = null;

	HttpSession objSession = null;

	boolean test;

	public int intCurrentPage;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=====01=====");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=====02=====");
		ServletContext context = getServletContext();
		response.setContentType("text/html");
		RequestDispatcher dispatcher = null;
		System.out.println("=====03=====");
		objSession = request.getSession(false);
		if (objSession == null) {
			System.out.println("=====04=====");
			dispatchJSP(response, "session.jsp");
			return;
		}
		System.out.println("=====05=====");
		String userId = (String) objSession.getAttribute("USERID");
		if (userId == null) {
			System.out.println("=====06=====");
			// AdminConfig.writeDebug("Null User Id");
			dispatchJSP(response, "session.jsp");
			return;
		}

		try {
			System.out.println("=====07=====");
			// objDBManager = new DBManager(AdminConfig.poolName);
			//AdminConfig.loadProperties();
			// AdminConfig.loadConfig();
		} catch (Exception e) {
			System.out.println("=====08=====");
			// AdminConfig.writeDebug("Null Connection");
			dispatchJSP(response, "session.jsp");
		}
		System.out.println("=====09=====");
		ReportFactory objReportFactory = new ReportFactory();
		ReportTypeFactory objReportTypeFactory = new ReportTypeFactory();
		ReportTypeBean objReportTypeBean = new ReportTypeBean();
		ExportReportBean objExportReportBean = new ExportReportBean();
		String mode = request.getParameter("mode");
		System.out.println("&&&&&& mode " + mode + "&&&&&&");
		System.out.println("=====10=====");
		String reportFormat = "0";// request.getParameter("REPORT_FORMAT");
		
		if(request.getParameter("REPORT_FORMAT") != null){
            reportFormat = request.getParameter("REPORT_FORMAT");
        }
		
		String redirectjsp = "";
		objSession.setAttribute("REPORTCODE", request
				.getParameter("REPORT_CODE"));
		System.out.println("&&&&&& mode "
				+ (String) request.getParameter("REPORT_CODE") + "&&&&&&");
		System.out.println("=====11=====");
		try {
			// getting the report details into bean
			objReportTypeBean = getReportTypeDetails(request, objSession);
			redirectjsp = objReportTypeBean.getReportJsp();// dispatch jsp
			System.out.println("report jsp" + redirectjsp);
			// //AdminConfig.writeDebug("Report util implementation starts");
			System.out.println("=====12=====");
			// Report util implementation
			ReportUtil reportUtil = (ReportUtil) objReportFactory
					.getInstance(objReportTypeBean.getReportName());
			System.out.println("reportUtiltoString() = "
					+ reportUtil.toString());
			// set the requested values
			// ////AdminConfig.writeDebug("set the requested values");
			Object objTemp = reportUtil.setRequest(request, objSession);
			System.out.println("=====13=====");
			// validate the requested values
			// //AdminConfig.writeDebug("validate the request values");
			objTemp = reportUtil.validateRequest(objTemp);
			System.out.println("=====14=====");
			System.out.println("objTemp = " + objTemp.toString());
			System.out.println("objTemp = " + objTemp.getClass().getName());
			objSession.setAttribute("REPORT_VIEW_DATA_OBJECT", objTemp);
			int intOpCode = Integer.parseInt(request.getParameter("hdOpCode"));
			System.out.println("&&&&&& hdOpCode " + intOpCode + " &&&&&&");
			if (intOpCode <= 2) {
				ArrayList arlResultList = getAll(intOpCode, reportUtil
						.getReportFields(),
						reportUtil.getFilterFields(objTemp), reportUtil
								.getNumberOfSQLFields());
				System.out.println("arlResultList.size = "
						+ arlResultList.size());
				System.out.println("=====15=====");
				objSession.setAttribute("ADMINDATABEAN", arlResultList);
				
				int intTotalNoOfRecords = Integer.parseInt(arlResultList.get(1).toString());
				if(intTotalNoOfRecords == 0){
					objSession.setAttribute("NODATAFOUND", ICacisiss.IErrorMessages.NODATEFOUND);
				}else{
					objSession.removeAttribute("NODATAFOUND");
				}
				
				dispatchJSP(response, redirectjsp);
			} else if (intOpCode == 3) {
				System.out.println("=====16=====");
				if (objSession.getAttribute("ADMINDATABEAN") != null)
					objSession.removeAttribute("ADMINDATABEAN");
				if (objSession.getAttribute("NODATAFOUND") != null)
					objSession.removeAttribute("NODATAFOUND");
				dispatchJSP(response, redirectjsp);
			} else {
				System.out.println("=====17=====");
				// Getting the result set of the given query
				ResultSet objResultSet = reportUtil.getReportData(objTemp);
				if (!objResultSet.isBeforeFirst()) {
					objSession.setAttribute("NODATAFOUND", ICacisiss.IErrorMessages.NODATEFOUND);
					throw new ReportException("" + ErrorCodes.NO_DATA, "No Data For this report");
				}else{
					objSession.removeAttribute("NODATAFOUND");
				}
				//objResultSet.first();
				/*System.out.println("objResultSet.getRow = "
						+ objResultSet.getRow());*/
				System.out.println("=====18=====");
				// create a map of parameters to pass to the report.
				Map parameters = new HashMap();
				parameters = reportUtil.getParamData(objTemp);
				
				if(!reportFormat.equals("0")){
                    parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
                }
				
				parameters.put("LOGO_PATH", AdminParamsLoad.reportPath);

				// Getting the instance for report type
				// AdminConfig.writeDebug("Report Type implementation
				// starts");
				ReportTypeUtil reportTypeUtil = (ReportTypeUtil) objReportTypeFactory
						.getInstance(Integer.parseInt(reportFormat));
				System.out.println("=====19=====");
				// exporting the report data
				// //AdminConfig.writeDebug("AF getting instance");
				objExportReportBean.setInputReportXml(objReportTypeBean
						.getInputXmlFileName());
				objExportReportBean.setInputParams(parameters);
				/*objExportReportBean.setInputResultSet(reportUtil
						.getReportData(objTemp));*/
				objExportReportBean.setInputResultSet(objResultSet);
				objExportReportBean.setOutputFileName(objReportTypeBean
						.getOutputFileName());
				objExportReportBean.setResponse(response);
				objExportReportBean.setExportType(mode);
				System.out.println("=====20=====");
				reportTypeUtil.exportReport(objExportReportBean);
			}
		} catch (ReportException rexp) {
			// //AdminConfig.writeDebug(rexp.toString());
			objSession.setAttribute("ERRORMSG", rexp.getErrorCode());
			dispatchJSP(response, redirectjsp);
		} catch (Exception expGeneral) {
			// //AdminConfig.writeDebug(expGeneral.toString());
			objSession.setAttribute("ERRORMSG", ""
					+ ErrorCodes.APPLICATION_ERROR);
			dispatchJSP(response, redirectjsp);
		}
	}

	private ReportTypeBean getReportTypeDetails(HttpServletRequest request,
			HttpSession objSession) throws ReportException {
		// AdminConfig.writeDebug("inside report type details method");
		System.out.println("=====1101=====");
		//String strReportTypeXml = AdminConfig.strReportXml;
		String strReportTypeXml = AdminParamsLoad.reportXML;
		ReportTypeBean objReportTypeBean = new ReportTypeBean();
		System.out.println("=====1102=====");
		try {
			Hashtable ht_ReportTypeXml = readReportTypeXml(strReportTypeXml);
			System.out.println("=====1103=====");
			System.out.println("ht_ReportTypeXml.isEmpty()= "
					+ ht_ReportTypeXml.isEmpty());
			System.out.println("ht_ReportTypeXml.toString()= "
					+ ht_ReportTypeXml.toString());
			int i_ReportTypeCount = ht_ReportTypeXml.size();
			System.out.println("=====1103.1=====");
			boolean foundReportCode = false;

			String tmp_ReportCode = "";
			String tmp_ReportName = "";
			String tmp_InputXmlFile = "";
			String tmp_OutputFileName = "";
			String tmp_ReportJsp = "";
			System.out.println("=====1103.2=====");
			for (int ik = 0; ik < i_ReportTypeCount; ik++) {
				System.out.println("=====1103.3=====");
				String str_temp = "" + ik;
				Hashtable hmpChildTags = (Hashtable) ht_ReportTypeXml
						.get(str_temp);
				System.out.println("1");
				tmp_ReportCode = (String) hmpChildTags.get("REPORT_CODE");
				System.out.println("2 reportcode" + tmp_ReportCode);
				tmp_ReportName = (String) hmpChildTags.get("REPORT_NAME");
				System.out.println("3 reportName" + tmp_ReportName);
				tmp_InputXmlFile = (String) hmpChildTags.get("INPUT_XML_FILE");
				System.out.println("4 InputXml file" + tmp_InputXmlFile);
				tmp_OutputFileName = (String) hmpChildTags
						.get("OUTPUT_FILE_NAME");
				System.out.println("5 out xl file" + tmp_OutputFileName);
				tmp_ReportJsp = (String) hmpChildTags.get("REPORT_JSP");
				System.out.println("6 tmp_ReportJsp" + tmp_ReportJsp);
				if (tmp_ReportCode.trim().equals(
						(String) request.getParameter("REPORT_CODE"))) {
					foundReportCode = true;// ifour record there
					break;
				}
			}
			System.out.println("=====1104=====");
			if (foundReportCode) {
				System.out.println("=====1105=====");
				// AdminConfig.writeDebug("Valid Report Type Found :
				// "+tmp_ReportName);
				objReportTypeBean.setReportCode(tmp_ReportCode);
				objReportTypeBean.setReportName(tmp_ReportName);
				objReportTypeBean.setInputXmlFileName(AdminParamsLoad.reportPath+tmp_InputXmlFile);
				objReportTypeBean.setOutputFileName(tmp_OutputFileName);
				objReportTypeBean.setReportJsp(tmp_ReportJsp);
			} else {
				System.out.println("=====1106=====");
				throw new ReportException("" + ErrorCodes.XML_FAIL,
						"Exception in getting the Xml Details");
			}
			System.out.println("=====1107=====");
		} catch (Exception bep) {

			if (bep instanceof ReportException) {
				System.out.println("=====1108=====");
				throw (ReportException) bep;
			} else {
				System.out.println("=====1109=====");
				throw new ReportException("" + ErrorCodes.APPLICATION_ERROR,
						"Exception in getting the Xml details "
								+ bep.getMessage());
			}
		}
		return objReportTypeBean;
	}

	private Hashtable readReportTypeXml(String strReportTypeXml)
			throws ReportException {
		Hashtable ht_ReportTypeXml = null;
		try {
			System.out.println("=====1102.1=====");
			ArrayList arlChildTags = new ArrayList();
			arlChildTags.add("REPORT_CODE");
			arlChildTags.add("REPORT_NAME");
			arlChildTags.add("INPUT_XML_FILE");
			arlChildTags.add("OUTPUT_FILE_NAME");
			arlChildTags.add("REPORT_JSP");
			System.out.println("=====1102.2=====");
			ht_ReportTypeXml = XmlUtil.processXml(new File(strReportTypeXml),
					"REPORTTYPE", arlChildTags);
			System.out.println("=====1102.3=====");
		}

		catch (Exception bep) {
			if (bep instanceof ReportException)
				throw (ReportException) bep;
			else
				throw new ReportException("" + ErrorCodes.XML_FAIL,
						"Exception in getting the Xml Details");

		}
		return ht_ReportTypeXml;
	}

	private void dispatchJSP(HttpServletResponse response, String jspName) {
		try {
			response.sendRedirect(jspName);
			return;
		} catch (Exception e) {
			// //AdminConfig.writeDebug("Exception :: "+e.toString());
		}
	}

	private ArrayList getAll(int intOpCode, StringBuffer sbfList,
			StringBuffer sbfFrom, int NoOfFields) throws Exception {
		// AdminConfig.writeDebug("\n\nwe are in getAll record size");

		ArrayList arlResultList = new ArrayList();
		boolean bolTrack = false;
		int intPage = 0;

		try {
			switch (intOpCode) {
			// Previous Button Action
			case 1: {
				System.out.println("\n\n in case 1");
				bolTrack = true;
				int intPrevPage = ((Integer) objSession
						.getAttribute("PAGINATIONCONSTANT")).intValue();
				if (intPrevPage > 0) {
					intPage = ((Integer) objSession
							.getAttribute("PAGINATIONCONSTANT")).intValue() - 1;
				} else {
					intPage = 1;
				}
			}
				// Next Button Action
			case 2: {
				if (!bolTrack) {
					bolTrack = true;
					intPage = ((Integer) objSession
							.getAttribute("PAGINATIONCONSTANT")).intValue() + 1;
				}
			}
				// Method to Display the List Screen
			case 0: {
				if (!bolTrack) {
					// The User has clicked the List for the First time
					intPage = 1;
				}
				System.out.println("\n\n 2");
				int intMin = ((intPage - 1) * AdminConfig.intPaginationConstantForReport) + 1;
				int intMax = ((intPage - 1) * AdminConfig.intPaginationConstantForReport) + AdminConfig.intPaginationConstantForReport;
				System.out.println("intMin = " + intMin);
				System.out.println("intMax = " + intMax);
				ArrayList arlNoRecord = new ArrayList();
				System.out.println("*************************************");
				System.out.println("In Get All : " + sbfList.toString()
						+ sbfFrom.toString());

				System.out.println("\n\n 3");

				DBManager objDBMan = new DBManager();
				System.out.println("\n\n 4");
				arlResultList = objDBMan.executeStoredProc(sbfList, sbfFrom,
						intMin, intMax, NoOfFields, arlResultList);
				/*
				 * problem after execute objDBMan.executeStoredProc
				 */
				System.out
						.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println("arlResultList.get(0)"
						+ arlResultList.get(0).toString());
				arlNoRecord = (ArrayList) arlResultList.get(0);
				System.out.println("arlResultList.size() = "
						+ arlResultList.size());
				System.out.println("\n\n 5");
				if (arlNoRecord.size() == 0) {
					objSession.setAttribute("ERRORMSG", ""
							+ ErrorCodes.NO_RECORDS_FOUND);
				}
				objSession.setAttribute("PAGINATIONCONSTANT", new Integer(
						intPage));
				System.out
						.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			}
				break;
			}
		} catch (Exception expGeneral) {
			System.out.println("************* Exception " + expGeneral
					+ "**************");
			throw expGeneral;
		}
		System.out.println("\n\n 6");
		return arlResultList;
	}

}// END CLASS

