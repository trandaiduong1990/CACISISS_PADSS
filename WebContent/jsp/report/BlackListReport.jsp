<%@page import="org.transinfo.cacis.report.databean.TransDataBean"%>
<%@page import="org.transinfo.cacis.AdminConfig"%>
<%@page import="org.transinfo.cacis.report.databean.ReportsDataBean"%>
<%@page import="org.transinfo.cacis.ErrorCodes"%>
<%@page import="java.util.*"%>
 <%
    String strReportCode = "";
      if(session.getAttribute("REPORTCODE") != null){
      strReportCode = (String)session.getAttribute("REPORTCODE");
      System.out.println("reportcode" + strReportCode );
      session.removeAttribute("REPORTCODE");
	  }
%>
<%
 ArrayList arlIssuer = new ArrayList(); 
  ArrayList arlReason = new ArrayList(); 

    String strErrorMessage    =   "";
    String strDateFrom = "";
    String strTimeFrom = "";
    String strDateTo = "";
    String strTimeTo = "";
    String strCardNumberFrom = "";
    String strCardNumberTo = "";
    String strRowsPerPage = "";
    String strReason = "";
    String IssuerId = "";
          
    int intRowsPerPage  =  AdminConfig.intPaginationConstant;
    int intErr  = -1;
    if(session.getAttribute("ERRORMSG") != null){
        intErr  = Integer.parseInt((String)session.getAttribute("ERRORMSG"));
        strErrorMessage  = ErrorCodes.getErrorMessage(intErr);
        session.removeAttribute("ERRORMSG");
    }

    TransDataBean objTransDataBean = new TransDataBean();
    if(session.getAttribute("REPORT_VIEW_DATA_OBJECT") != null){
        objTransDataBean    = (TransDataBean)session.getAttribute("REPORT_VIEW_DATA_OBJECT");
	    IssuerId = objTransDataBean.getIssuerId();  
        strDateFrom = objTransDataBean.getTransDateFrom();
        strTimeFrom = objTransDataBean.getTransTimeFrom();
        strDateTo = objTransDataBean.getTransDateTo();
        strTimeTo =objTransDataBean.getTransTimeTo();
        strCardNumberFrom =objTransDataBean.getCardNoFrom();
        strCardNumberTo = objTransDataBean.getCardNoTo();
        strReason = objTransDataBean.getReason();
        strRowsPerPage = objTransDataBean.getRowsPerPage();
	    System.out.println(".. 01 ..");
		arlIssuer = objTransDataBean.getIssuerList();
		System.out.println(".. 02 ..");
		arlReason = objTransDataBean.getReasonList();
		System.out.println(".. 03 ..");
        if(!strRowsPerPage.trim().equals("")) { 
        	intRowsPerPage = Integer.parseInt(strRowsPerPage);
        }
    }

    int intTotalNoOfRecords =   0;
    int intSize             =   0;
    int intPage             =   0;
    int intTotal            =   0;
    try{
        ArrayList arlListValues =   new ArrayList();
        ArrayList arlResult     =   new ArrayList();
    if(session.getAttribute("ADMINDATABEAN") != null){
    	System.out.println("......ADMINDATABEAN not null......");
	    if (intErr != ErrorCodes.NO_RECORDS_FOUND){
	    	System.out.println("......record found......");
        	arlListValues           =   (ArrayList) session.getAttribute("ADMINDATABEAN");
        	arlResult               =   (ArrayList) arlListValues.get (0);
        	intTotalNoOfRecords     =   Integer.parseInt(arlListValues.get (1).toString());
        	intSize                 =   arlResult.size();
        	intPage                 =   ((Integer)session.getAttribute("PAGINATIONCONSTANT")).intValue();
        	intTotal                =   intTotalNoOfRecords / intRowsPerPage + (intTotalNoOfRecords % intRowsPerPage > 0 ? 1 : 0);
    	}
    }else{
    	System.out.println("......ADMINDATABEAN null......");
        session.removeAttribute("ADMINDATABEAN");
    }

    String accessRight = "G";

    Object [] objArr 		= 	null;
    int intArlSize = 0;
    System.out.println(".. done ..");


%>
<html>
<head>
<title>Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="js/Checking.js"></script>
<script language="JavaScript" src="js/ReportView.js"></script>
<link rel="stylesheet" href="inc/css/emvadmin.css" type="text/css">
</head>

<body bgcolor="#C4D9E3" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="frmMain" action="../../GenerateReportServlet.do" method="post">
<input type="hidden" name="AC" value="<%=accessRight%>">
<input type="hidden" name="mode" >
<input type="hidden" name="hdOpCode" >
<input type="hidden" name="REPORT_CODE" value="<%=strReportCode%>">

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> </td>
    </tr>
    <tr> 
      <td width="95%" height="32" class="toolbar"><span class="texts">&nbsp;<strong>Reports 
        > Black List Report </strong></span></td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td> 
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td class="texts" height="27"> 
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr> 
                        <td class="texts"><b> Date From</b></td>
                        <td> 
                          <input type="text" name="TRANS_DATE_FROM" size="12" maxlength="10"  value="<%=strDateFrom%>" class="form_field">
                          <font size="1">(DD/MM/YYYY)</font> </td>
                        <td> 
                          <input type="text" name="TRANS_TIME_FROM" size="8" maxlength="8"  value="<%=strTimeFrom%>" class="form_field">
                          <font size="1">(HH24:MI:SS)</font> </td>
                      </tr>
                      <tr> 
                        <td class="texts" height="27"><b>Date To</b></td>
                        <td height="27"> 
                          <input type="text" name="TRANS_DATE_TO" size="12" maxlength="10"  value="<%=strDateTo%>" class="form_field">
                          <font size="1">(DD/MM/YYYY)</font> </td>
                        <td> 
                          <input type="text" name="TRANS_TIME_TO" size="8" maxlength="8"  value="<%=strTimeTo%>" class="form_field">
                          <font size="1">(HH24:MI:SS)</font> </td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr> 
                  <td class="texts" height="27"> 
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="texts"><b>Issuer Id</b></td>
                        <td> 
                          <select name="ISSUER_ID" size="1" class="form_field">
                            <option value="" >ALL</option>
                            <%intArlSize  = arlIssuer.size() ;
                                System.out.println("array list size : "+intArlSize);
                               String zszSelectedText = new String();
                               if(intArlSize != 0){
                                for (int intIndex = 0; intIndex < intArlSize; intIndex++){
                                    Hashtable AcqDetails = (Hashtable) arlIssuer.get(intIndex);
                                    String zszAcqId = (String) AcqDetails.get("ISSUER_ID");
                                    String zszAcqName = (String) AcqDetails.get("ISSUER_NAME");
                                    zszSelectedText = "";
                                    if(IssuerId != null && IssuerId.trim().equalsIgnoreCase(zszAcqId)) zszSelectedText ="selected";
                                    %>
                            <option value="<%=zszAcqId%>" <%=zszSelectedText%>> 
                            <%=zszAcqName%></option>
                            <%}
                              }%>
                          </select>
                        </td>
                        <td class="texts">&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr> 
                        <td class="texts"><b>Card Number From</b></td>
                        <td> 
                          <input type="text" name="CARD_NUMBER_FROM" class="form_field" size="19" maxlength="19" value="<%=strCardNumberFrom%>">
                        </td>
                        <td class="texts"><b>Card Number To</b></td>
                        <td> 
                          <input type="text" name="CARD_NUMBER_TO" class="form_field" size="19" maxlength="19" value="<%=strCardNumberTo%>">
                        </td>
                      </tr>
                      <tr> 
                        <td class="texts"><b>Reason</b></td>
                        <td> 
                          <select name="REASON" class="texts">
                            <option value="">All</option>
                            <% intArlSize  = arlReason.size() ;
                               if(intArlSize != 0){
                                    for (int intIndex = 0; intIndex < intArlSize; intIndex++){
                                         objArr = (Object []) arlReason.get (intIndex);%>
                            <option value="<%=objArr[0]%>" <%if(strReason.trim().equals(objArr[0])) out.print("selected");%>><%=objArr[1]%></option>
                            <%      }
                               }%>
                          </select>
                        </td>
                        <td class="texts"><b>No of rows per page</b></td>
                        <td> 
                          <select name="ROWSPERPAGE" class="form_field">
                            <option value=""> 
                            <option value="10" <%if(strRowsPerPage.trim().equals("10")) out.print("selected");%>>10</option>
                            <option value="20" <%if(strRowsPerPage.trim().equals("20")) out.print("selected");%>>20</option>
                            <option value="30" <%if(strRowsPerPage.trim().equals("30")) out.print("selected");%>>30</option>
                            <option value="50" <%if(strRowsPerPage.trim().equals("50")) out.print("selected");%>>50</option>
                            <option value="100" <%if(strRowsPerPage.trim().equals("100")) out.print("selected");%>>100</option>
                          </select>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
                			<tr>
								<td><input type="button"
									onClick="javascript:setmode(0,'Search','BLR','frmMain')"
									value="Search" name="Search"> <input type="button"
									onClick="javascript:setmode(4,'View','BLR')"
									value="PDF Download " name="PDF"> 
								</td>
							</tr>
        </table>
      </td>
    </tr>
    <tr> 
      <td class="textstylestext">&nbsp; </td>
    </tr>
    <tr> 
      <td class="texts"> 
        <div align="left"><b>Total No of Records : <%=intTotalNoOfRecords%> </b></div>
      </td>
    </tr>
    <%if (intTotalNoOfRecords > 0){ %>
    <tr> 
      <td>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td>
              <table border="0" cellspacing="1" cellpadding="4" width="63%">
                <tr> 
                  <td class="texts" nowrap bgcolor="#cccccc" width="29%"><b> Date 
                    Time </b></td>
                  <td class="texts" nowrap bgcolor="#cccccc" width="38%"><b> Issuer 
                    Id </b></td>
                  <td class="texts" nowrap bgcolor="#cccccc" width="38%"><b> Card 
                    Number</b></td>
                  <td class="texts" nowrap bgcolor="#cccccc" width="33%"><b> Reason</b></td>
                </tr>
                <%}%>
                <%
            String strXML1 = "";
            String strXML2 = "";
            String strContent = "";
            String strParsedXml = "";
            ReportsDataBean objReportsDataBean = new ReportsDataBean();
            for (int intIndex = 0 ; intIndex < intSize ; intIndex++){
                objReportsDataBean = (ReportsDataBean) arlResult.get(intIndex);
            %>
                <tr> 
                  <td class="texts" nowrap width="29%"> <%=objReportsDataBean.getColumn1()%> 
                  </td>
                  <td class="texts" nowrap width="38%"> <%=objReportsDataBean.getColumn4()%></td>
                  <td class="texts" nowrap width="38%"> <%=objReportsDataBean.getColumn2()%></td>
                  <td class="texts" nowrap width="33%"> <%=objReportsDataBean.getColumn3()%></td>
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
        <div align="left">
          <% System.out.println("intTotalNoOfRecords"+intTotalNoOfRecords);
                if (intTotalNoOfRecords > 0){ %>
          <b>Page </b><%=intPage%> <b>of </b><%=intTotal%> </div>
      </td>
      <%}%>
    </tr>
  </table>
</form>
<form name="frmXMLPost" action="PaXMLViewer.jsp" METHOD='POST' >
<input type="hidden" name="XMLString" value="">
</form>
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
</body>
</html>
