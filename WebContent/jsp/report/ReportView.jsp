<%@ page import="java.util.*"%>

<%  String IssuerId = "";
    ArrayList arlIssuer = new ArrayList();
   ArrayList arlTranxCodes = new ArrayList();
	%>
<%
if(session.getAttribute("USERID") == null){
%>
    <SCRIPT>
        location.href = "session.jsp";
    </SCRIPT>
<%}%>

<%
    String strReportCode = "";
      if(session.getAttribute("REPORTCODE") != null){
      strReportCode = (String)session.getAttribute("REPORTCODE");
      System.out.println("reportcode" + strReportCode );
      session.removeAttribute("REPORTCODE");
   }

    String strErrorMessage    =   "";
    String strIssuerId= "";
    String strCardNo = "";
    String strFromDate = "";
    String strToDate= "";
    String strMode = "";
    String strOpCode = "";
    String strRefNo        = "";
    String strResponseCode = "";
    String strApprovalCode = "";
    String strTranxCode    = "";
  //  DBUtil dbutil = new DBUtil(AdminConfig.poolName);
    int intArlSize  =   0;
    Object [] objArr = 	null;

	int intErr  = -1;
    if(session.getAttribute("ERRORMSG") != null){
        intErr  = Integer.parseInt((String)session.getAttribute("ERRORMSG"));
        strErrorMessage  = ErrorCodes.getErrorMessage(intErr);
        session.removeAttribute("ERRORMSG");
    }
    
    ReportViewBean objReportViewBean = new  ReportViewBean();
	if(session.getAttribute("REPORT_VIEW_DATA_OBJECT") != null){
        objReportViewBean  = (ReportViewBean)session.getAttribute("REPORT_VIEW_DATA_OBJECT");
        strMode         = objReportViewBean.getMode();  
        strOpCode       = objReportViewBean.getOpCode();
        strIssuerId     = objReportViewBean.getIssuerId();
        strFromDate     = objReportViewBean.getFromDate();
        strToDate       = objReportViewBean.getToDate();
        strCardNo       = objReportViewBean.getCardNo();
        strRefNo        = objReportViewBean.getRefNo();
        strResponseCode = objReportViewBean.getResponseCode();
        strApprovalCode = objReportViewBean.getApprovalCode();
        strTranxCode    = objReportViewBean.getTranxCode();
       
		arlIssuer      = objReportViewBean.getIssuerList();
        arlTranxCodes  = objReportViewBean.getTranxCodeList();
		session.removeAttribute("REPORT_VIEW_DATA_OBJECT");
    }else{
       //  strFromDate   = dbutil.getFieldValue("SELECT TO_CHAR(SYSDATE-1,'DD/MM/YYYY') AS TODAYDATE FROM DUAL","TODAYDATE");
         //strToDate     = dbutil.getFieldValue("SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') AS TODAYDATE FROM DUAL","TODAYDATE");
    }

       
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
    
//String accessRight = "GNPE";
    String accessRight = "G";
    String strPrivilege = (String) session.getAttribute("ADMINFUNCTIONS");
    if(strPrivilege.indexOf("FN401") > -1) accessRight += "E";
    if (intPage!= 1) accessRight += "P";
    if (intPage != intTotal) accessRight += "N";
 %>       


<%@page import="org.transinfo.cacis.report.databean.ReportsDataBean"%>
<%@page import="org.transinfo.cacis.report.databean.ReportViewBean"%>
<%@page import="org.transinfo.cacis.ErrorCodes"%>
<%@page import="org.transinfo.cacis.AdminConfig"%><html>
<head>
    <title>Report View</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script language="JavaScript" src="inc/js/Checking.js"></script>
    <script language="JavaScript" src="inc/js/ReportView.js"></script>

    <link rel="stylesheet" href="inc/css/emvadmin.css" type="text/css">


    <script language="JavaScript">
        <!--
        function callTop(){
            parent.BFrame.location.href = "buttons.jsp";
            <%if(!strErrorMessage.trim().equals("")){%>
            alert('<%=strErrorMessage%>');
            <%}%>
        }
        function setmode(mode){
            doSubmit(mode , document.frmMain);
        }
        function setRptMode(mode,rpttype){
            doSubmit(mode, rpttype, document.frmMain);
        }

         function setRptMode1(mode){
            doSubmit(mode, '0', document.frmMain);
        }
        function dosubmit(){
            document.frmMain.target = "_self"
            document.frmMain.action="ReportView.jsp";
            document.frmMain.submit(); 
        }
        //-->

    </script>
</head>

<body bgcolor="#C4D9E3"  onLoad="callTop()" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <form name="frmMain" method="post" action = "GenerateReportServlet">
    <input type="hidden" name="AC" value="<%=accessRight%>">
    <input type="hidden" name="mode" value="<%=strMode%>" >
    <input type="hidden" name="hdOpCode" value="<%=strOpCode%>">
    <input type="hidden" name="REPORT_CODE" value="<%=strReportCode%>">
    <input type="hidden" name="REPORT_FORMAT">
    <table width="102%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td> </td>
    </tr>
    <tr> 
    <td> 
        <table border="0" cellspacing="0" cellpadding="2" width="100%">
          <tr> 
            <%String reportName = "";
            if(strReportCode.trim().equals("STL")) reportName="Settlement Report";
            if(strReportCode.trim().equals("CRV")) reportName="Card Volume Report";
            if(strReportCode.trim().equals("TND")) reportName="Transaction Detail Report";
            if(strReportCode.trim().equals("TNL")) reportName="Transaction Listing Report";
            %>          
            <td height="32" class="toolbar"><span class="texts">&nbsp;<strong>Reports > <%=reportName%></strong></span></td>
          </tr>
          <tr>
            <td valign="top">
              <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr> 
                  <td>
                    <table rules=all border=0 >
                      <tr> 
                        <td class="texts"> Issuer Id: </td>
                        <td> 
                          <select name="ISSUER_ID" size="1" class="form_field">
                            <option value="" ></option>
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
                          </select>
                        </td>
                      </tr>
                      <tr> 
                        <td class="texts">Card Number:</td>
                        <td> 
                          <input type="text" name="CARD_NO" size="16" maxlength="16" value="<%=strCardNo%>" class="form_field">
                        </td>
                      </tr>
                      <tr> 
                        <td class="texts"> From Date: </td>
                        <td> 
                          <input type="text" name="TRANS_DATE_FROM" size="16" maxlength="16" value="<%=strFromDate%>" class="form_field" >
                          <font size="1">(DD/MM/YYYY)</font> </td>
                      </tr>
                      <tr> 
                        <td class="texts"> To Date: </td>
                        <td> 
                          <input type="text" name="TRANS_DATE_TO" size="16" maxlength="16" value="<%=strToDate%>" class="form_field">
                          <font size="1">(DD/MM/YYYY)</font> </td>
                      </tr>
                   <%if(strReportCode.trim().equals("TND") || strReportCode.trim().equals("TNL")){
                          //  DBUtil Xdbutil = new DBUtil(AdminConfig.poolName);
                           // ArrayList arlTranxCodes = Xdbutil.getTranxCodes();
                   %>
                      <tr>
                        <td class="texts">Tranx Code:</td>
                        <td>
                          <select name="TRANX_CODE">
                          <option value="ALL">All</option>
                          <%  intArlSize  = arlTranxCodes.size() ;
                               if(intArlSize != 0){
                                    for (int intIndex = 0; intIndex < intArlSize; intIndex++){
                                         objArr = (Object []) arlTranxCodes.get (intIndex);
                             %>
                                  <option value="<%=objArr[0]%>" <%if(strTranxCode.trim().equals(objArr[0])) out.print("selected");%>><%=objArr[1]%></option>
                                  <%      }
                                }
                            %>                           
                          </select>
                        </td>
                      </tr>
                      <tr>
                        <td class="texts">Ref No:</td>
                        <td>
                          <input type="text" name="REF_NO" size="12" maxlength="12" value="<%=strRefNo%>">
                        </td>
                      </tr>
                      <tr>
                        <td class="texts">Response Code:</td>
                        <td>
                          <input type="text" name="RESPONSE_CODE" size="6" maxlength="6" value="<%=strResponseCode%>">
                        </td>
                      </tr>
                      <tr>
                        <td class="texts">Approval Code:</td>
                        <td>
                          <input type="text" name="APPROVAL_CODE" size="6" maxlength="6" value="<%=strApprovalCode%>">
                        </td>
                      </tr>
                      <%}%>
			                    
					 </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr> 
            <td valign="top">&nbsp; </td>
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
               <%if(strReportCode.trim().equals("TND") || strReportCode.trim().equals("TNL")){ %>
			      <td class="texts" nowrap><b> Date Time </b></td>
				  <td class="texts" nowrap><b> Issuer Id</b></td>
                  <td class="texts" nowrap><b> Card Number </b></td>
                  <td class="texts" nowrap><b> Trans Type </b></td>
                  <td class="texts" nowrap><b> Curr Code </b></td>
                  <td class="texts" nowrap><b> Amount </b></td>
                  <td class="texts" nowrap><b> Terminal Id </b></td>
                  <td class="texts" nowrap><b>Merchant Id</b></td>
				   </tr>
				    <%}else if(strReportCode.trim().equals("STL")){%>
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
   
                <%}else if(strReportCode.trim().equals("CRV")){%>
                  <td class="texts" nowrap><b> Cash Amount </b></td>
                  <td class="texts" nowrap><b> Cash Count</b></td>
				  <td class="texts" nowrap><b> Sale Amount </b></td>
                  <td class="texts" nowrap><b> Sale Count </b></td>
                  <td class="texts" nowrap><b> Refund Amount </b></td>
                  <td class="texts" nowrap><b> Refound Count </b></td>
                  <td class="texts" nowrap><b> Transfer Amount </b></td>
                  <td class="texts" nowrap><b> Transfer Count</b></td>
				  <td class="texts" nowrap><b> Total Amount</b></td>
				  <td class="texts" nowrap><b>Total Count</b></td>
				   </tr>
                       <%}%>
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
				  <%if(strReportCode.trim().equals("STL") || strReportCode.trim().equals("CRV")){ %>
                   <td class="texts" nowrap> <%=objReportsDataBean.getColumn9()%></td>
				   <td class="texts" nowrap> <%=objReportsDataBean.getColumn10()%></td>
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