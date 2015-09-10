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
String strReportCode = "";

	if (session.getAttribute("REPORTCODE") != null) {
		strReportCode = (String) session.getAttribute("REPORTCODE");
		session.removeAttribute("REPORTCODE");
	}

	String strErrorMessage = "";
	String strCardNo = "";
	String strFromDate = "";
	String strToDate = "";
	String strMode = "";
	String strOpCode = "";
	String tranxStatus = "";
	String tranxType = "";
	String strResponseCode = "";
	String strcardProduct = "";
	ArrayList arlcardProductList = new ArrayList();
	ArrayList arlresponseCodeList = new ArrayList();

	int intArlSize = 0;
	Object[] objArr = null;

	int intErr = -1;

	ReportViewBean objReportViewBean = new ReportViewBean();
	
	if (session.getAttribute("REPORT_VIEW_DATA_OBJECT") != null) {
		objReportViewBean = (ReportViewBean) session.getAttribute("REPORT_VIEW_DATA_OBJECT");
		strMode = objReportViewBean.getMode();
		strOpCode = objReportViewBean.getOpCode();
		strFromDate = objReportViewBean.getFromDate();
		strToDate = objReportViewBean.getToDate();
		strCardNo = objReportViewBean.getCardNo();
		strcardProduct = objReportViewBean.getCardProduct();
		strResponseCode = objReportViewBean.getResponseCode();
		tranxStatus = objReportViewBean.getTranxStatus();
		tranxType = objReportViewBean.getTranxType();
		arlcardProductList = objReportViewBean.getArlCardProductList();
		arlresponseCodeList = objReportViewBean.getArlResponseCodeList();
		
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
			<% String reportName = "Synthesis Report Card Transactions"; %>
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
								<td class="desc_cell">Card Number:</td>
								<td>
									<input type="text" name="CARD_NO" size="16" maxlength="16" value="<%=strCardNo%>" class="form_field">
								</td>
							</tr>
							<tr>
								<td class="desc_cell">From Date:</td>
								<td>
									<input type="text" name="TRANS_DATE_FROM" size="16" maxlength="16" value="<%=strFromDate%>" class="form_field">
									<a href="#" onClick="show_calendar('forms[0].TRANS_DATE_FROM', null, null, 'DD/MM/YYYY', null, null)"> 
                                	(DD/MM/YYYY)
                                	</a>
								</td>
							</tr>
							<tr>
								<td class="desc_cell">To Date:</td>
								<td>
									<input type="text" name="TRANS_DATE_TO" size="16" maxlength="16" value="<%=strToDate%>" class="form_field">
									<a href="#" onClick="show_calendar('forms[0].TRANS_DATE_TO', null, null, 'DD/MM/YYYY', null, null)"> 
                                	(DD/MM/YYYY)
                                	</a>
								</td>
							</tr>
							<tr>
								<td class="desc_cell" style="width: 60px;">Transaction Type:</td>
								<td>
									<select name="TRANX_TYPE" size="1" class="form_field">
										<option value=""></option>
										<option value="BALANCE" <% if(tranxType != null && !"".equals(tranxType) && "BALANCE".equals(tranxType)){ %> selected="selected" <% } %> >Balance</option>
										<option value="CASH" <% if(tranxType != null && !"".equals(tranxType) && "CASH".equals(tranxType)){ %> selected="selected" <% } %> >Cash</option>
										<option value="REFUND" <% if(tranxType != null && !"".equals(tranxType) && "REFUND".equals(tranxType)){ %> selected="selected" <% } %> >Refund</option>
										<option value="REVERSAL" <% if(tranxType != null && !"".equals(tranxType) && "REVERSAL".equals(tranxType)){ %> selected="selected" <% } %> >Reversal</option>
										<option value="SALE" <% if(tranxType != null && !"".equals(tranxType) && "SALE".equals(tranxType)){ %> selected="selected" <% } %> >Sale</option>
										<option value="WITHDRAWAL" <% if(tranxType != null && !"".equals(tranxType) && "WITHDRAWAL".equals(tranxType)){ %> selected="selected" <% } %> >Withdrawal</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="desc_cell">Transaction Status:</td>
								<td>
									<select name="TRANX_STATUS" id="TRANX_STATUS" size="1" class="form_field" onchange="disableOrEnable();">
										<option value=""></option>
										<option value="A" <% if(tranxStatus != null && !"".equals(tranxStatus) && "A".equals(tranxStatus)){ %> selected="selected" <% } %> >Approved</option>
										<option value="D" <% if(tranxStatus != null && !"".equals(tranxStatus) && "D".equals(tranxStatus)){ %> selected="selected" <% } %> >Declined</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="desc_cell">Response Code:</td>
								<td>
									<select name="RESPONSE_CODE" id="RESPONSE_CODE" size="1" class="form_field">
									<option value=""></option>
									<%
									intArlSize = arlresponseCodeList.size();
									String selectedResponseCode = new String();
									if (intArlSize != 0) {
										for (int intIndex = 0; intIndex < intArlSize; intIndex++) {
											Hashtable AcqDetails = (Hashtable) arlresponseCodeList.get(intIndex);
											String zszAcqId = (String) AcqDetails.get("RESPONSE_CODE");
											String zszAcqName = (String) AcqDetails.get("DETAIL");
											selectedResponseCode = "";
											if (strResponseCode != null && strResponseCode.trim().equalsIgnoreCase(zszAcqId))
												selectedResponseCode = "selected";
									%>
									<option value="<%=zszAcqId%>" <%=selectedResponseCode%>><%=zszAcqName%></option>
											<%}
										}%>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="2">
								<div align="left">
									<input type="button" onClick="javascript:setmode(0,'Search','SYNREPCARDTRANX','frmMain')" value="Search" name="Search">
									<input type="button" onClick="javascript:setmode(4,'Down','SYNREPCARDTRANX', 0)" value="PDF Download " name="PDF">
									<input type="button" onClick="javascript:setmode(4,'Down','SYNREPCARDTRANX', 2)" value="Excel Download " name="PDF">
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
		              <input type="button" onClick="javascript:setmode(1,'Search','SYNREPCARDTRANX','frmMain')" value="Previous" name="Search">
		            <%}%>
		            <%if (intPage != intTotal){%>
		              <input type="button" onClick="javascript:setmode(2,'Search','SYNREPCARDTRANX','frmMain')" value="Next" name="Search">
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
						<td class="texts" nowrap><b> Tranx Time </b></td>
						<td class="texts" nowrap><b> Tranx Date </b></td>
						<td class="texts" nowrap><b> Terminal Id </b></td>
						<td class="texts" nowrap><b> Merchant ID </b></td>
						<td class="texts" nowrap><b> Merchant Name </b></td>
						<td class="texts" nowrap><b> Card Number </b></td>
						<td class="texts" nowrap><b> Ref No </b></td>
						<td class="texts" nowrap><b> Trace No </b></td>
						<td class="texts" nowrap><b> Tranx Type </b></td>
						<td class="texts" nowrap><b> Amount </b></td>
						<td class="texts" nowrap><b> Res Code </b></td>
						<td class="texts" nowrap><b> App Code </b></td>
						<td class="texts" nowrap><b> Customer name </b></td>
					</tr>
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
						<td class="texts" nowrap><%=objReportsDataBean.getColumn5()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn6()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn7()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn8()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn9()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn10()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn11()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn12()%></td>
						<td class="texts" nowrap><%=objReportsDataBean.getColumn13()%></td>
					</tr>
					<%}%>
				</table>
				</td>
			</tr>
			<tr>
				<td style="padding-top: 10px;">
					<%if (intPage!= 1){%>
		              <input type="button" onClick="javascript:setmode(1,'Search','SYNREPCARDTRANX','frmMain')" value="Previous" name="Search">
		            <%}%>
		            <%if (intPage != intTotal){%>
		              <input type="button" onClick="javascript:setmode(2,'Search','SYNREPCARDTRANX','frmMain')" value="Next" name="Search">
		            <%}%>
				</td>
				<td class="texts" style="padding-top: 10px;">
				<% if (intTotalNoOfRecords > 0) { %>
					<div align="right"><b>Page </b><%=intPage%> <b>of </b><%=intTotal%></div>
				<% } %>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<%}%> <%} catch (Exception expGeneral) { %> 
<SCRIPT Language="JavaScript1.2">
    alert ("The Exception in JSP is :<%=expGeneral.toString ()%>");
</SCRIPT>
<%} %>
</form>
</body>
</html>