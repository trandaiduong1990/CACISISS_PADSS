
<%@page import="org.transinfo.cacis.report.databean.ReportViewBean"%>
<%@page import="org.transinfo.cacis.AdminConfig"%>
<%@page import="org.transinfo.cacis.report.databean.ReportsDataBean"%>
<%@page import="org.transinfo.cacis.report.ReportConfig"%>
<%@page import="org.transinfo.cacis.ErrorCodes"%>
<%@page import="java.util.*"%>
<%@page import="org.transinfo.cacis.util.DBUtil"%>
<%if (session.getAttribute("USERID") == null) {
%>
<SCRIPT>
        location.href = "session.jsp";
</SCRIPT>
<%}%>

<%
	String strReportCode = "";
	if (session.getAttribute("REPORTCODE") != null) {
		strReportCode = (String) session.getAttribute("REPORTCODE");
		System.out.println("reportcode" + strReportCode);
		session.removeAttribute("REPORTCODE");
	}

	System.out.println("...jsp_01...");
	String strErrorMessage = "";
	String strIssuerId = "";
	String strCardNo = "";
	String strFromDate = "";
	String strToDate = "";
	String strMode = "";
	String strOpCode = "";
	String strRefNo = "";
	String strResponseCode = "";
	String strApprovalCode = "";
	String strTranxCode = "";
	ArrayList arlIssuer = new ArrayList();
	ArrayList arlTranxCodes = new ArrayList();
			
	//  DBUtil dbutil = new DBUtil(AdminConfig.poolName);
	int intArlSize = 0;
	Object[] objArr = null;
	System.out.println("...jsp_02...");
	int intErr = -1;
	System.out.println("...jsp_03...");
	ReportViewBean objReportViewBean = new ReportViewBean();
	if (session.getAttribute("REPORT_VIEW_DATA_OBJECT") != null) {
		objReportViewBean = (ReportViewBean) session
				.getAttribute("REPORT_VIEW_DATA_OBJECT");
		strMode = objReportViewBean.getMode();
		strOpCode = objReportViewBean.getOpCode();
		strIssuerId = objReportViewBean.getIssuerId();
		strFromDate = objReportViewBean.getFromDate();
		strToDate = objReportViewBean.getToDate();
		strCardNo = objReportViewBean.getCardNo();
		strRefNo = objReportViewBean.getRefNo();
		strResponseCode = objReportViewBean.getResponseCode();
		strApprovalCode = objReportViewBean.getApprovalCode();
		strTranxCode = objReportViewBean.getTranxCode();
		arlIssuer = objReportViewBean.getIssuerList();
		arlTranxCodes = objReportViewBean.getTranxCodeList();
		session.removeAttribute("REPORT_VIEW_DATA_OBJECT");
	}
	System.out.println("...jsp_04...");
%>
<%
	int intTotalNoOfRecords = 0;
	int intSize = 0;
	int intPage = 0;
	int intTotal = 0;
	try {
		System.out.println("...jsp_05...");
		ArrayList arlListValues = new ArrayList();
		ArrayList arlResult = new ArrayList();
		if (session.getAttribute("ADMINDATABEAN") != null) {
			if (intErr != ErrorCodes.NO_RECORDS_FOUND) {
				System.out.println("...records found...");
				arlListValues = (ArrayList) session
						.getAttribute("ADMINDATABEAN");
				arlResult = (ArrayList) arlListValues.get(0);
				intTotalNoOfRecords = Integer.parseInt(arlListValues
						.get(1).toString());
				intSize = arlResult.size();
				intPage = ((Integer) session
						.getAttribute("PAGINATIONCONSTANT")).intValue();
				intTotal = intTotalNoOfRecords / AdminConfig.intPaginationConstant+ (intTotalNoOfRecords%AdminConfig.intPaginationConstant > 0 ? 1:0);
			}
		} else {
			System.out.println("...no records found...");
			session.removeAttribute("ADMINDATABEAN");
		}
		System.out.println("...jsp_06...");
		String accessRight = "G";
%>

<html>
<head>
<title>Report View</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<link rel="stylesheet" href="inc/css/emvadmin.css" type="text/css">
<script language="JavaScript" src="js/Checking.js"></script>
<script language="JavaScript" src="js/ReportView.js"></script>

</head>

<body bgcolor="#C4D9E3" text="#000000" leftmargin="0" topmargin="0"
	marginwidth="0" marginheight="0">
<form name="frmMain" method="post"
	action="../../GenerateReportServlet.do"><input type="hidden" name="AC"
	value="<%=accessRight%>"> <input type="hidden" name="mode"
	value="<%=strMode%>"> <input type="hidden" name="hdOpCode"
	value="<%=strOpCode%>"> <input type="hidden" name="REPORT_CODE"
	value="<%=strReportCode%>"> <input type="hidden" name="REPORT_FORMAT">
<table width="102%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td></td>
	</tr>
	<tr>
		<td>
		<table border="0" cellspacing="0" cellpadding="2" width="100%">
			<tr>
				<%
				String reportName = "Settlement Report";
				
				%>
				<td height="32" class="toolbar"><span class="texts">&nbsp;<strong>Reports
				> <%=reportName%></strong></span></td>
			</tr>
			<tr>
				<td valign="top">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td>
						<table rules=all border=0>
							<tr>
								<td class="texts">Issuer Id:</td>
								<td><select name="ISSUER_ID" size="1" class="form_field">
									<option value=""></option>
                            <% intArlSize  = arlIssuer.size() ;
                               System.out.println("array list size : "+intArlSize);
                               String zszSelectedText = new String();
                               if(intArlSize != 0){
                                for (int intIndex = 0; intIndex < intArlSize; intIndex++){
                                    Hashtable AcqDetails = (Hashtable) arlIssuer.get(intIndex);
                                    String zszAcqId = (String) AcqDetails.get("ISSUER_ID");
                                    String zszAcqName = (String) AcqDetails.get("ISSUER_NAME");
                                    zszSelectedText = "";
                                    if(strIssuerId != null && strIssuerId.trim().equalsIgnoreCase(zszAcqId)) zszSelectedText ="selected";
                                    %>
                            <option value="<%=zszAcqId%>" <%=zszSelectedText%>> 
                            <%=zszAcqName%></option>
                            <%}
                                      }%>

								</select></td>
							</tr>
							<tr>
								<td class="texts">Card Number:</td>
								<td><input type="text" name="CARD_NO" size="16" maxlength="16"
									value="<%=strCardNo%>" class="form_field"></td>
							</tr>
							<tr>
								<td class="texts">From Date:</td>
								<td><input type="text" name="TRANS_DATE_FROM" size="16"
									maxlength="16" value="<%=strFromDate%>" class="form_field"> <font
									size="1">(DD/MM/YYYY)</font></td>
							</tr>
							<tr>
								<td class="texts">To Date:</td>
								<td><input type="text" name="TRANS_DATE_TO" size="16"
									maxlength="16" value="<%=strToDate%>" class="form_field"> <font
									size="1">(DD/MM/YYYY)</font></td>
							</tr>
							<tr>
								<td><input type="button"
									onClick="javascript:setmode(0,'Search','STL','frmMain')"
									value="Search" name="Search"> <input type="button"
									onClick="javascript:setmode(4,'View','STL')"
									value="PDF Download " name="PDF"> 
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td valign="top">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td></td>
	</tr>
	<tr>
		<td class="texts">
		<div align="left"><b>Total No of Records : <%=intTotalNoOfRecords%> </b></div>
		</td>
	</tr>
	<%System.out.println("...jsp_08...");
				if (intTotalNoOfRecords > 0) {

					%>
	<tr>
		<td>
		<table border="0" cellspacing="0" cellpadding="0" height="18">
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>
				<table border="0" cellspacing="1" cellpadding="4" align="center"
					width="100%">
					<tr bgcolor="#CCCCCC">
						<td class="texts" nowrap><b>Date Time </b></td>
						<td class="texts" nowrap><b>Card Number </b></td>
						<td class="texts" nowrap><b> Trans Type </b></td>
						<td class="texts" nowrap><b> Tranx Date </b></td>
						<td class="texts" nowrap><b> MerchantName </b></td>
						<td class="texts" nowrap><b> MerchnatCity </b></td>
						<td class="texts" nowrap><b> Merchant Country</b></td>
						<td class="texts" nowrap><b> ApprovalCode </b></td>
						<td class="texts" nowrap><b> Amount Credited </b></td>
						<td class="texts" nowrap><b> Amount Debited</b></td>
					</tr>
					<%System.out.println("...jsp_08...");
					ReportsDataBean objReportsDataBean = new ReportsDataBean();
					for (int intIndex = 0; intIndex < intSize; intIndex++) {
						objReportsDataBean = (ReportsDataBean) arlResult
								.get(intIndex);
						%>
					<tr valign="top">
						<td class="texts" nowrap><%=objReportsDataBean.getColumn1()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn2()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn3()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn4()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn5()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn6()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn7()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn8()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn9()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn10()%></td>
						<%}%>
					</tr>
					<%}%>

				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td class="texts"><%System.out.println("intTotalNoOfRecords"
							+ intTotalNoOfRecords);
					if (intTotalNoOfRecords > 0) {

						%>
		<div align="left"><b>Page </b><%=intPage%> <b>of </b><%=intTotal%></div>
		</td>
		<%}%>
	</tr>
</table>
<%} catch (Exception expGeneral) {
%> <SCRIPT Language="JavaScript1.2">
        alert ("The Exception in JSP is :<%=expGeneral.toString ()%>");
    </SCRIPT> <%}

		%></form>
</body>
</html>
