<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
<head>
<title><bean:message key ="cardcardembossingsearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/NonReconTransactionEnquirySearch.js"></script>

<script>
function go(action) {

	document.forms[0].mode.value=action;
	searchlist();
	
}
</script>

</head>

<body bgcolor="ffffff" >
<html:form action ="nonReconTranxEnquiryList.do">
<input type="hidden" name="method" value ="save">
<input type="hidden" name="tranxNonReconId" value ="">

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="nonRecontranxenquiry.title"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td>
              	<html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button>
              </td>
              <td>&nbsp;</td>
            </tr>
          </table> <td class="texteMenuGauche"><div align="right"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
      <table width="100%">

        <tr>
          <td valign="top" class="ErrFONT"><table width="100%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td class="ErrFONT"><font color="#FF0000"><html:errors/></font></td>
            </tr>
          </table></td>
        </tr>
       <tr> 
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="4">
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="nonRecontranxenquiry.cardno"/> </td>
                        <td><html:text property="cardNo" size="20"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT"><bean:message key ="nonRecontranxenquiry.authcode"/> </td>
                        <td><html:text property="authCode" size="20"/></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="nonRecontranxenquiry.startdate"/><bean:message key ="common.dateformat"/></td>
                        <td><html:text property="startDate" size="20" onblur="javascript:isValidDate(document.nonReconTranxEnquirySearchForm.startDate)"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT"><bean:message key ="nonRecontranxenquiry.enddate"/><bean:message key ="common.dateformat"/></td>
                        <td><html:text property="endDate" size="20" onblur="javascript:isValidDate(document.nonReconTranxEnquirySearchForm.endDate)"/></td>
                      </tr>
                    </table>
                 </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td valign="top">
		     <!-- to Show the List Data -->   
			<logic:present name="SEARCHLIST" scope="request"> 
		     	<bean:size id ="size" name ="SEARCHLIST"/>
		        	<logic:greaterThan  name ="size" value ="0"> 
			           <tr> 
			              <td>
			                <table border="0" cellspacing="0" cellpadding="0" width="60%">
			                <tr>
							     <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							     	<b><bean:write name="nonReconTranxEnquirySearchForm" property="totalCount"/></b>
							     	<bean:message key ="nonRecontranxenquiry.totcountmsg"/>
							     </td>
							     <td></td>	
			                 </tr>
			                 <tr>
							     <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							     	<%@ include file="/jsp/common/TranxButtons.jsp"%>
							     </td>
							     <td></td>	
			                 </tr>
			                 <tr>
			                      <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
			                      <table class="wrapper" border="0" cellspacing="1" cellpadding="2">
	                                <tr>
										<td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.cardNo"/></td>
										<td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.purchaseDate"/></td>
										<td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.tranxCode"/></td>
	                                    <td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.srcAmt"/></td>
	                                    <td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.srcCurr"/></td>
	                                    <td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.destAmt"/> </td>
	                                    <td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.destCurr"/> </td>
	                                    <td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.merchantName"/> </td>
	                                    <td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.merchantCountry"/> </td>
	                                    <td class="title" nowrap><bean:message key ="nonRecontranxenquiry.list.authCode"/> </td>
	                                 </tr>
		                               <logic:iterate id ="commObj" name ="SEARCHLIST">
		                                  <tr>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column1" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column2" /></td>
		                                    <% String tranxCode = ((CommonDataBean)pageContext.getAttribute("commObj")).getColumn3();
		                                    	if(tranxCode.equals("5") || tranxCode.equals("00")) { 
		                                    %>
		                                    <td class="DataTD" nowrap>Sale</td>
		                                    <% } else if(tranxCode.equals("6") || tranxCode.equals("20")) { %>
		                                    <td class="DataTD" nowrap>Refund</td>
		                                    <% } else if(tranxCode.equals("7") || tranxCode.equals("12") || tranxCode.equals("01")) { %>
		                                    <td class="DataTD" nowrap>Cash</td>
		                                    <% } else if(tranxCode.equals("25")) { %>
		                                    <td class="DataTD" nowrap>Sale Reversal</td>
		                                    <% } else if(tranxCode.equals("26")) { %>
		                                    <td class="DataTD" nowrap>Refund Reversal</td>
		                                    <% } else if(tranxCode.equals("27")) { %>
		                                    <td class="DataTD" nowrap>Cash Reversal</td>
		                                    <% } %>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column4" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column5" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column6" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column7" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column8" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column9" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column10" /></td>
		                                  </tr>
		                               </logic:iterate>                        
			                        </table>
			                       </td>
			                       </tr>
		                  	</table>
		                  </td>
			              <td background=images/tbl_d.gif></td>
					</tr>
				</logic:greaterThan>
			</logic:present> 
         </td>
        </tr>
        <tr> 
          <td valign="top">&nbsp; </td>
        </tr>
      </table>
    </td>
  </tr>
</table>               
</html:form>
</body>
</html:html>