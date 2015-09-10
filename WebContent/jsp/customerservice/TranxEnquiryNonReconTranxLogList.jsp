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
<script language="JavaScript" src="inc/js/TransactionEnquirySearch.js"></script>

<script>

function view(tranxId){
	document.forms[0].method.value="view";
	document.forms[0].tranxId.value=tranxId;
	document.forms[0].action ="tranxEnquiryProcess.do";
	document.forms[0].submit();
	
}

function go(action) {

	document.forms[0].mode.value=action;
	searchlist();
	
}
</script>

</head>

<body bgcolor="ffffff" >
<html:form action ="tranxEnquiryListNonReconTranxlog.do">
<input type="hidden" name="method" value ="save">

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="tranxenquiry.titlenonrecont"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
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
		    <!-- to Show the List Data -->   
			<logic:present name="SEARCHLIST" scope="request"> 
		     	<bean:size id ="size" name ="SEARCHLIST"/>
		        	<logic:greaterThan  name ="size" value ="0"> 
			           <tr> 
			              <td>
			                <table border="0" cellspacing="0" cellpadding="0" width="60%">
			                <tr>
							     <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							     	<b><bean:write name="tranxEnquirySearchFormNonReconTranxlog" property="totalCount"/></b>
							     	<bean:message key ="tranxenquiry.totcountmsg"/>
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
										<td class="title" nowrap><bean:message key ="tranxenquiry.list.tranxid"/></td>
										<td class="title" nowrap><bean:message key ="tranxenquiry.list.tranxdate"/></td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.cardno"/></td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.tranxtype"/></td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.rescode"/> </td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.rescodedes"/> </td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.curr"/> </td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.merchant"/> </td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.authcode"/> </td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.refno"/> </td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.recon"/> </td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.amt"/> </td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.amtclear"/> </td>
	                                    <td class="title" nowrap><bean:message key ="tranxenquiry.list.tranxcurrcona"/> </td>
	                                 </tr>
		                               <logic:iterate id ="commObj" name ="SEARCHLIST">
										<bean:define id="sno" name="commObj" property="column1"/>
		                                  <tr>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column1" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column2" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column3" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column4" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column5" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column12" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column11" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column7" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column8" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column9" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column10" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column6" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column13" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column14" /></td>
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