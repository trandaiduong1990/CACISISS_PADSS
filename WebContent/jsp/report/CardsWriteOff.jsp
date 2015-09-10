<%@page import="org.transinfo.cacis.report.databean.ReportViewBean"%>
<%@page import="org.transinfo.cacis.AdminConfig"%>
<%@page import="org.transinfo.cacis.report.databean.ReportsDataBean"%>
<%@page import="org.transinfo.cacis.ErrorCodes"%>
<%@page import="java.util.*"%>
<%@page import="org.transinfo.cacis.util.DBUtil"%>

<%if (session.getAttribute("USERID") == null) { %>
<SCRIPT>
	location.href = "session.jsp";
</SCRIPT>
<%}%>

<%

    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};


String strReportCode = "";

	if (session.getAttribute("REPORTCODE") != null) {
		strReportCode = (String) session.getAttribute("REPORTCODE");
		session.removeAttribute("REPORTCODE");
	}

	String strErrorMessage = "";
	
	String strToDate = "";
	String strMode = "";
	String strOpCode = "";

	int intArlSize = 0;
	Object[] objArr = null;

	int intErr = -1;
	int month =0;

	ReportViewBean objReportViewBean = new ReportViewBean();
	
	if (session.getAttribute("REPORT_VIEW_DATA_OBJECT") != null) {
		objReportViewBean = (ReportViewBean) session.getAttribute("REPORT_VIEW_DATA_OBJECT");
		strMode = objReportViewBean.getMode();
		strOpCode = objReportViewBean.getOpCode();
		
		strToDate = objReportViewBean.getToDate();
		
		session.removeAttribute("REPORT_VIEW_DATA_OBJECT");
	}

%>

<%
int intTotalNoOfRecords = 0;
int intSize = 0;
int intPage = 0;
int intTotal = 0;
try {

	ArrayList arlListValues = new ArrayList();
	ArrayList arlResult = new ArrayList();
	if (session.getAttribute("ADMINDATABEAN") != null) {
		if (intErr != ErrorCodes.NO_RECORDS_FOUND) {
			arlListValues = (ArrayList) session.getAttribute("ADMINDATABEAN");
			arlResult = (ArrayList) arlListValues.get(0);
			intTotalNoOfRecords = Integer.parseInt(arlListValues.get(1).toString());
			intSize = arlResult.size();
			intPage = ((Integer) session.getAttribute("PAGINATIONCONSTANT")).intValue();
			intTotal = intTotalNoOfRecords / AdminConfig.intPaginationConstantForReport + (intTotalNoOfRecords % AdminConfig.intPaginationConstantForReport > 0 ? 1 : 0);
		}		
	} else {
		session.removeAttribute("ADMINDATABEAN");
	}

	String accessRight = "G";

	if (session.getAttribute("NODATAFOUND") != null) {
		strErrorMessage = (String)session.getAttribute("NODATAFOUND");
	} else {
		session.removeAttribute("NODATAFOUND");
	}

%>

<html>
<head>
<title>Report View</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="../../inc/css/emvadmin.css" type="text/css">
<link rel="stylesheet" href="../../inc/css/style.css" type="text/css">
<script language="JavaScript" src="js/Checking.js"></script>
<script language="JavaScript" src="js/ReportView.js"></script>
<script language="JavaScript" src="js/Transaction.js"></script>
<script language="JavaScript" src="../../inc/js/Calendar.js"></script>
</head>

<body bgcolor="ffffff">
<form name="frmMain" method="post" action="../../GenerateReportServlet.do">
<input type="hidden" name="AC" value="<%=accessRight%>">
<input type="hidden" name="mode" value="<%=strMode%>">
<input type="hidden" name="hdOpCode" value="<%=strOpCode%>">
<input type="hidden" name="REPORT_CODE" value="<%=strReportCode%>">
<input type="hidden" name="REPORT_FORMAT">
<table width="102%" cellspacing="0" cellpadding="0">
	<tr>
		<td></td>
	</tr>
	<tr>
		<td>
		<table border="0" cellspacing="0" cellpadding="2" width="100%">
			<% String reportName = "Cards Write Off"; %>
			<tr class="desc_cell">
				<td height="32" class="toolbar"><span class="texts">&nbsp;<strong>Reports > <%=reportName%></strong></span></td>
			</tr>
			<tr>
				<td valign="top">
				<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td>
						<table>
							<tr>
								<td colspan="2">
									<div id="reportErrorMessage" style="height: 20px;color: #FF0000;font-size: 16;display: block;">
										<%=strErrorMessage %>
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="desc_cell">To Duration:</td>
								<td>
									<!--  <input type="text" name="TRANS_DATE_TO" size="16" maxlength="16" class="form_field">
									 <a href="#" onClick="show_calendar('forms[0].TRANS_DATE_TO', null, null, 'MM/YYYY', null, null)">  
                                	(MM/YYYY)
                                	</a> -->
                                	<select id="TRANS_DATE_TO" name="TRANS_DATE_TO">
                                	<%Calendar cal = Calendar.getInstance();
                                	Date date=new Date();
                                	cal.setTime(date);
                                	month = cal.get(Calendar.MONTH)+1;
                                	
                                	//String strMonth = ""+month;
                                	/* if(month<10){
                                		strMonth="0"+month;
                                	} */
                                	int year=	cal.get(Calendar.YEAR);
                                	for(int x=0;x< month;x++){
                                		//String key=strMonth+"/"+year;
                                		//String key=month+"/"+year;
                                		String monthName=  monthNames[x];
                                		String strMonth = months[x];
                                		String key=strMonth+"/"+year;
                                	%>
  											<option value="<%=key%>"><%=monthName%> -<%=year%></option>
  											
  											  
  											<%} %>
  											<!-- <option value="11/2013">November - 2013</option>  -->
  									</select>
								</td>
							</tr>
							<tr>
								<td colspan="2">
								<div align="left">
									<!-- <input type="button" onClick="javascript:setmode(0,'Search','CARDSWRITEOFF','frmMain')" value="Search" name="Search">  -->
									<input type="button" onClick="javascript:setmode(4,'Down','CARDSWRITEOFF', 0)" value="PDF Download " name="PDF">
									<input type="button" onClick="javascript:setmode(4,'Down','CARDSWRITEOFF', 2)" value="Excel Download " name="PDF">
								</div>
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
<!-- 
<table border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="texts" style="padding-bottom: 10px;">
			<div align="left"><b>Total No of Records : <%=intTotalNoOfRecords%> </b></div>
		</td>
	</tr>
	<% if (intTotalNoOfRecords > 0) { %>
	<tr>
		<td>
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="padding-bottom: 10px;">
					<%if (intPage!= 1){%>
		              <input type="button" onClick="javascript:setmode(1,'Search','CANCELCARDSSTATISTICS','frmMain')" value="Previous" name="Search">
		            <%}%>
		            <%if (intPage != intTotal){%>
		              <input type="button" onClick="javascript:setmode(2,'Search','CANCELCARDSSTATISTICS','frmMain')" value="Next" name="Search">
		            <%}%>
				</td>
				<td class="texts" style="padding-bottom: 10px;">
				<% if (intTotalNoOfRecords > 0) { %>
					<div align="right"><b>Page </b><%=intPage%> <b>of </b><%=intTotal%></div>
				<% } %>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<table border="0" cellspacing="1" cellpadding="4" align="center" width="100%">
					<tr bgcolor="#CCCCCC">
						<td class="texts" nowrap><b> Manual Suspensions </b></td>
						<td class="texts" nowrap><b> Closed By Bank </b></td>
						<td class="texts" nowrap><b> Closed By Customer Request </b></td>
						<td class="texts" nowrap><b> Write Off </b></td>
					</tr>
					<%-- 
					<%
					ReportsDataBean objReportsDataBean = new ReportsDataBean();
					for (int intIndex = 0; intIndex < intSize; intIndex++) {
						objReportsDataBean = (ReportsDataBean) arlResult.get(intIndex);
					%>
					<tr valign="top" class="ReportDataTD">
						<td class="texts" nowrap><%=objReportsDataBean.getColumn1()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn2()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn3()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn4()%></td>
					</tr>
					<%}%>
					--%>
				</table>
				</td>
			</tr>
			<tr>
				<td style="padding-top: 10px;">
				<%--
					<%if (intPage!= 1){%>
		              <input type="button" onClick="javascript:setmode(1,'Search','CANCELCARDSSTATISTICS','frmMain')" value="Previous" name="Search">
		            <%}%>
		            <%if (intPage != intTotal){%>
		              <input type="button" onClick="javascript:setmode(2,'Search','CANCELCARDSSTATISTICS','frmMain')" value="Next" name="Search">
		            <%}%>
				</td>
				<td class="texts" style="padding-top: 10px;">
				<% if (intTotalNoOfRecords > 0) { %>
					<div align="right"><b>Page </b><%=intPage%> <b>of </b><%=intTotal%></div>
				<% } %>
				--%>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
 -->
<%}%> <%} catch (Exception expGeneral) { %> 
<SCRIPT Language="JavaScript1.2">
    alert ("The Exception in JSP is :<%=expGeneral.toString ()%>");
</SCRIPT>
<%} %>
</form>
</body>
</html>