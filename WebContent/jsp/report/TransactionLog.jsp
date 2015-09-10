<%@page import="org.transinfo.cacis.report.databean.TransDataBean"%>
<%@page import="org.transinfo.cacis.AdminConfig"%>
<%@page import="org.transinfo.cacis.report.databean.ReportsDataBean"%>
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
    ArrayList arlIssuer = new ArrayList();
    ArrayList arlTranxType = new ArrayList();

    String strErrorMessage    =   "";
    String strCardNoFrom = "";
    String strCardNoTo = "";
    String strTransDateFrom = "";
    String strTransTimeFrom = "";
    String strTransDateTo = "";
    String strTransTimeTo = "";
    String strTransType = "";
    String strReferenceNo = "";
    String strResponseCode = "";
    String strApprovalCode = "";
    String strException = "";
    String strScriptReturn = "";
    String strChipTrans = "";
    String strRowsPerPage = "";
    String chkIssuerResponse	=	"";
    String chkEmvResponseCode	=	"";
    String chkFinalResponse		=	"";
    String chkApprovalCode		=	"";
    String chkTraceNo		=	"";
    String chkChipTrans		=	"";
    String chkArpcReturned		=	"";
    String chkArqcValidation	=	"";
    String chkScriptGenerated	=	"";
    String chkCvrTvrStatus		=	"";
    String chkReasonException	=	"";
    String IssuerId = "";
      
    int intRowsPerPage  =  AdminConfig.intPaginationConstant;
    int intErr  = -1;
    if(session.getAttribute("ERRORMSG") != null){
        intErr  = Integer.parseInt((String)session.getAttribute("ERRORMSG"));
        strErrorMessage  = ErrorCodes.getErrorMessage(intErr);
        session.removeAttribute("ERRORMSG");
    }

	System.out.println("......01......");
    TransDataBean objTransDataBean = new TransDataBean();
    if(session.getAttribute("REPORT_VIEW_DATA_OBJECT") != null){
    	System.out.println("......REPORT_VIEW_DATA_OBJECT not null......");
        objTransDataBean    = (TransDataBean)session.getAttribute("REPORT_VIEW_DATA_OBJECT");
        strCardNoFrom     = objTransDataBean.getCardNoFrom();
        strCardNoTo       = objTransDataBean.getCardNoTo();
        strTransDateFrom  = objTransDataBean.getTransDateFrom();
        strTransTimeFrom  = objTransDataBean.getTransTimeFrom();
        strTransDateTo    = objTransDataBean.getTransDateTo();
        strTransTimeTo    = objTransDataBean.getTransTimeTo();
        strTransType      = objTransDataBean.getTransType();
        strReferenceNo    = objTransDataBean.getReferenceNo();
        strResponseCode  = objTransDataBean.getResponseCode();
        strApprovalCode  = objTransDataBean.getApprovalCode();
        strException     = objTransDataBean.getException();
        strScriptReturn  = objTransDataBean.getScriptReturn();
        strChipTrans    = objTransDataBean.getChipTrans();
        strRowsPerPage  = objTransDataBean.getRowsPerPage();
        chkIssuerResponse	= objTransDataBean.getChkIssuerResponse();
        chkEmvResponseCode	= objTransDataBean.getChkEmvResponseCode();
        chkFinalResponse	= objTransDataBean.getChkFinalResponse();
        chkApprovalCode		= objTransDataBean.getChkApprovalCode();
        chkTraceNo		    = objTransDataBean.getChkTraceNo();
        chkChipTrans		= objTransDataBean.getChkChipTrans();
        chkArpcReturned		= objTransDataBean.getChkArpcReturned();
        chkArqcValidation	= objTransDataBean.getChkArqcValidation();
        chkScriptGenerated	= objTransDataBean.getChkScriptGenerated();
        chkCvrTvrStatus		= objTransDataBean.getChkCvrTvrStatus();
        chkReasonException	= objTransDataBean.getChkReasonException();
        IssuerId = objTransDataBean.getIssuerId(); 
       
		arlIssuer = objTransDataBean.getIssuerList();
        arlTranxType = objTransDataBean.getTranxTypeList();
		System.out.println("......02......");
        if(!strRowsPerPage.trim().equals("")) {
        	intRowsPerPage = Integer.parseInt(strRowsPerPage);
        }
    	System.out.println("......03......");
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

    int intArlSize  =   0;
    Object [] objArr = 	null;
%>
<html>
<head>
<title>Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="js/Checking.js"></script>
<script language="JavaScript" src="js/ReportView.js"></script>
<link rel="stylesheet" href="inc/css/emvadmin.css" type="text/css">
</head>

<body bgcolor="#DADCF1" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="frmMain" action="../../GenerateReportServlet.do" method="post">
  <p>
    <input type="hidden" name="AC" value="<%=accessRight%>">
    <input type="hidden" name="mode" >
    <input type="hidden" name="hdOpCode" >
   <input type="hidden" name="REPORT_CODE" value="<%=strReportCode%>">
  </p>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td width="95%" height="32" class="toolbar"><span class="texts">&nbsp;<strong><b>Reports 
        > Transaction Report </b></strong></span></td>
    </tr>
    <tr> 
      <td>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="171"> 
              <table border="0" cellspacing="0" cellpadding="0" width="100%">
                <tr> 
                  <td width="60%" valign="top"> 
                    <table border="0" cellspacing="0" cellpadding="2" width="575">
                      <tr> 
                        <td class="texts">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td class="texts">&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
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
                          <input type="text" name="CARDNUMBER_FROM" size="16" maxlength="16" value="<%=strCardNoFrom%>" class="form_field">
                        </td>
                        <td class="texts"><b>Card No To</b></td>
                        <td> 
                          <input type="text" name="CARDNUMBER_TO" size="16" maxlength="16" value="<%=strCardNoTo%>" class="form_field">
                        </td>
                      </tr>
                      <tr> 
                        <td class="texts"><b>Tranx Date From</b></td>
                        <td> 
                          <input type="text" name="TRANS_DATE_FROM" size="12" maxlength="10"  value="<%=strTransDateFrom%>" class="form_field">
                          <font size="1">(DD/MM/YYYY)</font> </td>
                        <td class="texts"> 
                          <input type="text" name="TRANS_TIME_FROM" size="8" maxlength="8"  value="<%=strTransTimeFrom%>" class="form_field">
                          <font size="1">(HH24:MI:SS)</font></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr> 
                        <td class="texts"><b>Tranx Date To</b></td>
                        <td> 
                          <input type="text" name="TRANS_DATE_TO" size="12" maxlength="10"  value="<%=strTransDateTo%>" class="form_field">
                          <font size="1">(DD/MM/YYYY)</font> </td>
                        <td class="texts"> 
                          <input type="text" name="TRANS_TIME_TO" size="8" maxlength="8"  value="<%=strTransTimeTo%>" class="form_field">
                          <font size="1">(HH24:MI:SS)</font></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr> 
                        <td class="texts"><b>Tranx Type</b></td>
                        <td> 
                          <select name="TRANS_TYPE" class="form_field">
                            <option value="">ALL</option>
                            <% intArlSize  = arlTranxType.size() ;
                   if(intArlSize != 0){
                        for (int intIndex = 0; intIndex < intArlSize; intIndex++){
                             objArr = (Object []) arlTranxType.get (intIndex);%>
                            <option value="<%=objArr[0]%>" <%if(strTransType.trim().equals(objArr[0])) out.print("selected");%>><%=objArr[1]%></option>
                            <%      }
                   }%>
                          </select>
                        </td>
                        <td class="texts"><b>Response Code</b></td>
                        <td> 
                          <input type="text" name="RESPONSE_CODE" size="3" maxlength="2"  value="<%=strResponseCode%>" class="form_field">
                        </td>
                      </tr>
                      <tr> 
                        <td class="texts"><b>Reference No</b></td>
                        <td> 
                          <input type="text" name="REFERENCE_NO" size="12" maxlength="12"  value="<%=strReferenceNo%>" class="form_field">
                        </td>
                        <td class="texts"><b>Approval Code</b></td>
                        <td> 
                          <input type="text" name="APPROVAL_CODE" size="6" maxlength="6"  value="<%=strApprovalCode%>" class="form_field">
                        </td>
                      </tr>
                      <tr> 
                        <td class="texts"><b>Exception</b></td>
                        <td> 
                          <select name="EXCEPTION" class="form_field">
                            <option value="" <%if(strException.trim().equals("")) out.print("selected");%>> 
                            </option>
                            <option value="ALL" <%if(strException.trim().equals("ALL")) out.print("selected");%>>EMV 
                            Fail</option>
                            <option value="ARQC" <%if(strException.trim().equals("ARQC")) out.print("selected");%>>Online 
                            Auth Fail (ARQC)</option>
                            <option value="ARPC" <%if(strException.trim().equals("ARPC")) out.print("selected");%>>Issuer 
                            Auth Fail (ARPC)</option>
                            <option value="SCRIPT" <%if(strException.trim().equals("SCRIPT")) out.print("selected");%>>Script 
                            Fail</option>
                            <option value="ISSUER" <%if(strException.trim().equals("ISSUER")) out.print("selected");%>>Issuer 
                            Fail </option>
                          </select>
                        </td>
                        <td class="texts"><b>Script Returned</b></td>
                        <td> 
                          <input type="checkbox" name="SCRIPT_RETURN" <%if(strScriptReturn.trim().equals("1")) out.println("checked");%>>
                        </td>
                      </tr>
                      <tr> 
                        <td class="texts"><b>Chip Transaction</b></td>
                        <td class="texts"> 
                          <input type="radio" name="CHIP_TRANS" value="YES" <%if(strChipTrans.trim().equals("YES")) out.println("checked");%>>
                          YES 
                          <input type="radio" name="CHIP_TRANS" value="NO" <%if(strChipTrans.trim().equals("NO")) out.println("checked");%>>
                          NO </td>
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
                      <tr> 
                        <td class="texts">&nbsp;</td>
                        <td>&nbsp;</td>
                        <td class="texts">&nbsp;</td>
                        <td class="texts">&nbsp;</td>
                      </tr>
                    </table>
                  </td>
                  <td valign="top" width="40%"> 
                    <table border="0" cellspacing="10" cellpadding="4" width="100%">
                      <tr> 
                        <td> 
                          <table border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td class="textm"><b>Optional fields.</b></td>
                            </tr>
                            <tr> 
                              <td class="texts"><b> Note : Select to view the 
                                values.</b></td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_ISSUER_RESPONSE_CODE"   <%if(chkIssuerResponse.trim().equals("1")) out.println("checked");%>>
                                Issure Response Code </td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_EMV_RESPONSE_CODE"  <%if(chkEmvResponseCode.trim().equals("1")) out.println("checked");%>>
                                EMV Response Code </td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_FINAL_RESPONSE_CODE"  <%if(chkFinalResponse.trim().equals("1")) out.println("checked");%>>
                                Final Response Code </td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_APPROVAL_CODE"  <%if(chkApprovalCode.trim().equals("1")) out.println("checked");%>>
                                Approval Code </td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_TRACE_NO"  <%if(chkTraceNo.trim().equals("1")) out.println("checked");%>>
                                Trace No </td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_CHIP_TRANS"  <%if(chkChipTrans.trim().equals("1")) out.println("checked");%>>
                                Chip Trans </td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_ARPC_RETURNED"  <%if(chkArpcReturned.trim().equals("1")) out.println("checked");%>>
                                ARPC Returned </td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_ARQC_VALIDATION"  <%if(chkArqcValidation.trim().equals("1")) out.println("checked");%>>
                                ARQC Validation</td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_SCRIPT_GENERATED"  <%if(chkScriptGenerated.trim().equals("1")) out.println("checked");%>>
                                Script Generated</td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_CVR_TVR_STATUS"  <%if(chkCvrTvrStatus.trim().equals("1")) out.println("checked");%>>
                                CVR TVR Status</td>
                            </tr>
                            <tr> 
                              <td class="texts"> 
                                <input type="checkbox" name="CHK_REASON_EXCEPTION"  <%if(chkReasonException.trim().equals("1")) out.println("checked");%>>
                                Reason of Exception</td>
                            </tr>
                          </table>
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
									onClick="javascript:setmode(0,'Search','TR','frmMain')"
									value="Search" name="Search"> <input type="button"
									onClick="javascript:setmode(4,'View','TR')"
									value="PDF Download " name="PDF"> 
								</td>
							</tr>
        </table>
      </td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
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
                  <td class="texts" nowrap><b> Date Time </b></td>
                  <td class="texts" nowrap><b> Issuer Id</b></td>
                  <td class="texts" nowrap><b> Trans Type </b></td>
                  <td class="texts" nowrap><b> Curr Code </b></td>
                  <td class="texts" nowrap><b> Amount </b></td>
                  <td class="texts" nowrap><b> Card Number </b></td>
                  <td class="texts" nowrap><b> Terminal Id </b></td>
                  <td class="texts" nowrap><b>Merchant Id</b></td>
                  <% if(chkIssuerResponse.trim().equals("1")){%>
                  <td class="texts" nowrap><b> Issure Response Code </b></td>
                  <%}%>
                  <% if(chkEmvResponseCode.trim().equals("1")){%>
                  <td class="texts" nowrap><b> EMV Response Code </b></td>
                  <%}%>
                  <% if(chkFinalResponse.trim().equals("1")){%>
                  <td class="texts" nowrap><b> Final Response Code </b></td>
                  <%}%>
                  <% if(chkApprovalCode.trim().equals("1")){%>
                  <td class="texts" nowrap><b> Approval Code </b></td>
                  <%}%>
                  <% if(chkTraceNo.trim().equals("1")){%>
                  <td class="texts" nowrap><b>Trace No</b></td>
                  <%}%>
                  <% if(chkChipTrans.trim().equals("1")){%>
                  <td class="texts" nowrap><b>Chip Trans</b></td>
                  <%}%>
                  <% if(chkArpcReturned.trim().equals("1")){%>
                  <td class="texts" nowrap><b>ARPC Returned</b></td>
                  <%}%>
                  <% if(chkArqcValidation.trim().equals("1")){%>
                  <td class="texts" nowrap><b>ARQC Validation</b></td>
                  <%}%>
                  <% if(chkScriptGenerated.trim().equals("1")){%>
                  <td class="texts" nowrap><b>Script Generated</b></td>
                  <%}%>
                  <% if(chkCvrTvrStatus.trim().equals("1")){%>
                  <td class="texts" nowrap><b>CVR TVR Status</b></td>
                  <%}%>
                  <% if(chkReasonException.trim().equals("1")){%>
                  <td class="texts" nowrap><b>Reason of Exception</b></td>
                  <%}%>
                </tr>
                <%}%>
                <%
                ReportsDataBean objReportsDataBean = new ReportsDataBean();
                for (int intIndex = 0 ; intIndex < intSize ; intIndex++){
                    objReportsDataBean = (ReportsDataBean) arlResult.get(intIndex);
            %>
                <tr valign="top"> 
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn1()%> 
                  </td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn19()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn2()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn3()%></td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn4()%> 
                  </td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn5()%> 
                  </td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn6()%> 
                  </td>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn7()%> 
                  </td>
                  <% if(chkIssuerResponse.trim().equals("1")){%>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn8()%> 
                  </td>
                  <%}%>
                  <% if(chkEmvResponseCode.trim().equals("1")){%>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn9()%> 
                  </td>
                  <%}%>
                  <% if(chkFinalResponse.trim().equals("1")){%>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn10()%> 
                  </td>
                  <%}%>
                  <% if(chkApprovalCode.trim().equals("1")){%>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn11()%> 
                  </td>
                  <%}%>
                  <% if(chkTraceNo.trim().equals("1")){%>
                  <td class="texts" nowrap> <b><%=objReportsDataBean.getColumn12()%> 
                    </b></td>
                  <%}%>
                  <% if(chkChipTrans.trim().equals("1")){%>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn13()%> 
                  </td>
                  <%}%>
                  <% if(chkArpcReturned.trim().equals("1")){%>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn14()%> 
                  </td>
                  <%}%>
                  <% if(chkArqcValidation.trim().equals("1")){%>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn15()%> 
                  </td>
                  <%}%>
                  <% if(chkScriptGenerated.trim().equals("1")){%>
                  <td class="texts" nowrap> <%=objReportsDataBean.getColumn16()%> 
                  </td>
                  <%}%>
                  <% if(chkCvrTvrStatus.trim().equals("1")){%>
                  <td class="texts" nowrap> 
                    <textarea name="cvr_tvr_status" class="textareabox"><%=objReportsDataBean.getColumn17()%></textarea>
                  </td>
                  <%}%>
                  <% if(chkReasonException.trim().equals("1")){%>
                  <td class="texts" nowrap><%=objReportsDataBean.getColumn18()%></td>
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
