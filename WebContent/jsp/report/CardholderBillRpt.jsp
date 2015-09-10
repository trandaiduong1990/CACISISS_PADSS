
<%@page import="org.transinfo.cacis.report.databean.ReportViewBean"%>
<%@page import="org.transinfo.cacis.AdminConfig"%>
<%@page import="org.transinfo.cacis.report.databean.ReportsDataBean"%>
<%@page import="org.transinfo.cacis.report.ReportConfig"%>
<%@page import="org.transinfo.cacis.ErrorCodes"%>
<%@page import="java.util.*"%>
<%@page import="org.transinfo.cacis.util.DBUtil"%>

<%
    String strReportCode = "";
      if(session.getAttribute("REPORTCODE") != null){
      strReportCode = (String)session.getAttribute("REPORTCODE");
      System.out.println("reportcode" + strReportCode );
      session.removeAttribute("REPORTCODE");
	  }
%>
<%
    String strErrorMessage    =   "";
    String userType = "";
    int intErr  = -1;
    if(session.getAttribute("ERRORMSG") != null){
        intErr  = Integer.parseInt((String)session.getAttribute("ERRORMSG"));
        strErrorMessage  = ErrorCodes.getErrorMessage(intErr);
        session.removeAttribute("ERRORMSG");
    }

    boolean blnMod = false;
    boolean blnMod_Cal = false;

	 ArrayList arlIssuer  = new ArrayList();

    String strIssuerId= "";
    String strMode= "";
    String strOpCode="";
    String strCycleNo = "";
    String strDayOfMonth = "";
    String strLastBillingDate = "";
    String strCurrentDate = "";
    String strMessage = "";
    String strSubmitValue = "";
   
    DBUtil dbutil = new DBUtil();
    strCurrentDate = dbutil.getTodayDate();
    GregorianCalendar c = new GregorianCalendar();
    if (strDayOfMonth.equals("") || c.get(Calendar.DAY_OF_MONTH) != Integer.parseInt(strDayOfMonth))
    {
       blnMod_Cal = true;
    }

	 ReportViewBean obReportViewBean = new ReportViewBean();
    if(session.getAttribute("REPORT_VIEW_DATA_OBJECT") != null){
       obReportViewBean    = (ReportViewBean)session.getAttribute("REPORT_VIEW_DATA_OBJECT");
        strReportCode = obReportViewBean.getReportCode();
        strMode=obReportViewBean.getMode();  
        strOpCode=obReportViewBean.getOpCode();
        strIssuerId=obReportViewBean.getIssuerId();
        strMode = obReportViewBean.getMode();    
        strCycleNo = obReportViewBean.getCycleNo();
        strLastBillingDate = obReportViewBean.getFromDate();
        strMessage = obReportViewBean.getMessage();
        strDayOfMonth = obReportViewBean.getDayOfMonth();
         arlIssuer = obReportViewBean.getIssuerList();
        session.removeAttribute("REPORT_VIEW_DATA_OBJECT");

    }
        String accessRight = "E";
%>
 <%
 
    int intTotalNoOfRecords =   0;
    int intSize             =   0;
    int intPage             =   0;
    int intTotal            =   0;
    try{
    
        ArrayList arlListValues =   new ArrayList();
        ArrayList arlResult     =   new ArrayList();
     if(session.getAttribute("ADMINDATABEAN") != null){
              if (intErr != ErrorCodes.NO_RECORDS_FOUND){
        arlListValues           =   (ArrayList) session.getAttribute("ADMINDATABEAN");
        arlResult               =   (ArrayList) arlListValues.get (0);
        intTotalNoOfRecords     =   Integer.parseInt(arlListValues.get (1).toString());
        intSize                 =   arlResult.size();
        intPage                 =   ((Integer)session.getAttribute("PAGINATIONCONSTANT")).intValue();
        intTotal                =   intTotalNoOfRecords/AdminConfig.intPaginationConstant + (intTotalNoOfRecords%AdminConfig.intPaginationConstant > 0 ? 1 : 0);
    }
	  }else{
        session.removeAttribute("ADMINDATABEAN");
    }
    
   %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="inc/css/emvadmin.css" type="text/css">
<script language="JavaScript" src="js/Checking.js"></script>
<script language="JavaScript" src="js/ReportView.js"></script>
</head>

<body bgcolor="#C4D9E3" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="frmMain" method="post" action="../../GenerateReportServlet.do">
<input type="hidden" name="AC" value="<%=accessRight%>">
<input type=hidden name="hdOpCode" value="">
<input type="hidden" name="mode" value="<%= strMode%>" >
<input type="hidden" name="REPORT_CODE" value="<%=strReportCode%>">
<input type="hidden" name="REPORT_FORMAT">
  <table width="100%" border="0" cellspacing="0" rules=all cellpadding="0">
    <tr> 
      <td width="95%" height="32" class="toolbar"><span class="texts">&nbsp;<strong>Report > CardHolder Billing Statement </strong></span></td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td>
        <table width="40%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
              <td class="textm"><font color='#0000FF'><b><%=strMessage%></b></font></td>
          </tr>        
        </table>
      </td>
    </tr>
	<tr> 
      <td>&nbsp; </td>
    </tr>  
	<tr>
	  <td>        
        <table width="40%" border="0" cellspacing="0" cellpadding="0" align="center">
                      <tr> 
                        <td class="texts"><b> Issuer: </b></td>
                        <td> 
                          <select name="ISSUER_ID" size="1" class="form_field" onChange="callSubmit('CHANGEISS');">
                            <option value=""></option>
                            <%
                   int intArlSize  = arlIssuer.size() ;
                   String zszSelectedText = new String();
                   if(intArlSize != 0){
                        for (int intIndex = 0; intIndex < intArlSize; intIndex++){
                            Hashtable IssDetails = (Hashtable) arlIssuer.get(intIndex);
                            String zszIssId = (String) IssDetails.get("ISSUER_ID");
                            String zszIssName = (String) IssDetails.get("ISSUER_NAME");
                            zszSelectedText = "";
                           if(strIssuerId != null && strIssuerId.equalsIgnoreCase(zszIssId))
                                zszSelectedText = " selected";%>
                            <option value="<%=zszIssId%>" <%=zszSelectedText%>><%=zszIssId%> 
                            - <%=zszIssName%></option>
                            <%}
                    }%>
                          </select>
                        </td>
                      </tr>
                     <tr> 
                        <td class="texts"><b>Billing Cycle No.</b></td>
                        <td> 
                          <input type="text" name="CYCLE_NO" class="textbox1" maxlength="3" size="3" value="<%=strCycleNo%>" onFocus=javascript:blur()>
                        </td>
                      </tr>
                     <tr> 
                        <td class="texts"><b>Last Billing Date.</b></td>
                        <td> 
                          <input type="text" name="LAST_BILLING_DATE" class="textbox1" maxlength="12" size="12" value="<%=strLastBillingDate%>" <%if(blnMod)out.print("onFocus=javascript:blur()");%>>
                        </td>
                      </tr>
                       <tr> 
                        <td class="texts"><b>Current Date.</b></td>
                        <td> 
                          <input type="text" name="CURRENT_DATE" class="textbox1" maxlength="12" size="12" value="<%=strCurrentDate%>" onFocus=javascript:blur()>
                        </td>
                      </tr>
                			<tr>
								<td><input type="button"
									onClick="javascript:setmode(0,'Search','CBS','frmMain')"
									value="Search" name="Search"> <input type="button"
									onClick="javascript:setmode(4,'View','CBS')"
									value="PDF Download " name="PDF"> 
								</td>
							</tr>
            </table>
</td>
</tr>
 </table>
 <table border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> </td>
    </tr>
    <tr> 
      <td class="texts"> 
        <div align="left"><b>Total No of Records : <%=intTotalNoOfRecords%> </b></div>
      </td>
    </tr>
    <%if (intTotalNoOfRecords > 0){ %>
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0" height="18">
          <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>
              <table border="0" cellspacing="1" cellpadding="4" align="center" width="100%">
                <tr bgcolor="#CCCCCC">
                  <td class="texts" nowrap><b>Card HolderName </b></td>
				  <td class="texts" nowrap><b>Address</b></td>
                  <td class="texts" nowrap><b>Credit Limit </b></td>
                  <td class="texts" nowrap><b>Payment Due Date </b></td>
                  <td class="texts" nowrap><b>Amount Credited </b></td>
                  <td class="texts" nowrap><b>Amount Debited </b></td>
                  <td class="texts" nowrap><b>Billing Amount</b></td>
                  <td class="texts" nowrap><b>Currency</b></td>
				  <td class="texts" nowrap><b>Ref No </b></td>
                  <td class="texts" nowrap><b>Transx Date </b></td>
                  <td class="texts" nowrap><b>DateTime</b></td>
                  <td class="texts" nowrap><b>PrimaryCardNumber</b></td>
				  <td class="texts" nowrap><b>CardNumber</b></td>
				  <td class="texts" nowrap><b>Merchant Name</b></td>
                  <td class="texts" nowrap><b>Merchant City</b></td>
                  <td class="texts" nowrap><b>Merchant Country</b></td>
				  <td class="texts" nowrap><b>AvailableCreditLimit</b></td>
				  <td class="texts" nowrap><b> BeginingBalance</b></td>
                  <td class="texts" nowrap><b>EndingBalance</b></td>
                </tr>
                <%
                ReportsDataBean objReportsDataBean = new ReportsDataBean();
                for (int intIndex = 0 ; intIndex < intSize ; intIndex++){
                    objReportsDataBean = (ReportsDataBean) arlResult.get(intIndex);
            %>
                <tr valign="top"> 
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn1()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn2()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn3()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn4()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn5()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn6()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn7()%></td>
				  <td class="texts" nowrap> <%=objReportsDataBean.getColumn8()%></td>
				   <td class="texts" nowrap> <%=objReportsDataBean.getColumn9()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn10()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn11()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn12()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn13()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn14()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn15()%></td>
				  <td class="texts" nowrap> <%=objReportsDataBean.getColumn16()%></td>
				  <td class="texts" nowrap> <%=objReportsDataBean.getColumn17()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn18()%></td>
				  <td class="texts" nowrap> <%=objReportsDataBean.getColumn19()%></td>
				</tr>
                <%}%>
				
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr> 
      <td class="texts"> 
        <% System.out.println("intTotalNoOfRecords"+intTotalNoOfRecords);
                if (intTotalNoOfRecords > 0){ %>
        <div align="left"><b>Page </b><%=intPage%> <b>of </b><%=intTotal%> </div>
      </td>
      <%}%>
    </tr>
  </table>
  <%}%>
  
<% }
catch(Exception expGeneral)
{
%>
    <SCRIPT Language="JavaScript1.2">
        alert ("The Exception in JSP is :<%=expGeneral.toString ()%>");
    </SCRIPT>
<%
}
%>

</form>
</body>
</html>
