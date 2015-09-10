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
<script language="JavaScript" src="inc/js/DisputeNotReconsiledTransactionSearch.js"></script>

<script>

function view(settlementId){
	document.forms[0].method.value="view";
	document.forms[0].settlementId.value=settlementId;
	document.forms[0].action ="disNotReconsiledTranxProcess.do";
	document.forms[0].submit();
	
}

function go(action) {
    if(action =="save"){

        if(document.forms[0].serialNosToEmboss.value.length == 0){
        	alert('Please select card to Emboss');
			return false;
        }else{
        	var r=confirm("<bean:message key ='areyousure'/>");
			if (r==true)
			{
				document.forms[0].method.value=action;
				document.forms[0].action ="cardembossingprocess.do";
				//document.forms[0].submit();
			}
        }
     }else
     {
    	document.forms[0].mode.value=action;
     	document.forms[0].action ="disNotReconsiledTranxList.do";
 	 	document.forms[0].submit();
     }
}

</script>

</head>

<body bgcolor="ffffff" >
<html:form action ="disNotReconsiledTranxList.do">
<input type="hidden" name="method" value ="save">
<input type="hidden" name="settlementId" value ="">

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="disputemanagement.nr.transactionsearch"/></td>
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
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.nr.cardno"/> </td>
                        <td><html:text property="cardNo" size="20"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.nr.arn"/> </td>
                        <td><html:text property="arn" size="25"/></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.nr.authcode"/> </td>
                        <td><html:text property="authCode" size="20"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT"></td>
                        <td></td>
                      </tr>
                      <tr>
                      	<td style="padding-top: 10px;" colspan="5" nowrap class="ColumnFONT"><bean:message key ="disputemanagement.nr.ttar"/>:</td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT" align="right"><bean:message key ="disputemanagement.nr.ttarfrom"/> </td>
                        <td><html:text property="amtFrom" size="20"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT" align="right"><bean:message key ="disputemanagement.nr.ttarto"/> </td>
                        <td><html:text property="amtTo" size="25"/></td>
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
							     	<b><bean:write name="disNotReconsiledTranxSearchForm" property="totalCount"/></b>
							     	<bean:message key ="disputemanagement.nr.totcountmsg"/>
							     </td>
							     <td></td>	
			                 </tr>
			                 <tr>
							     <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							     	<%@ include file="/jsp/common/Buttons.jsp"%>
							     </td>
							     <td></td>	
			                 </tr>
			                 <tr>
			                      <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
			                      <table class="wrapper" border="0" cellspacing="1" cellpadding="2">
	                                <tr>
										<td class="title" nowrap><bean:message key ="disputemanagement.nr.list.cardno"/></td>
										<td class="title" nowrap><bean:message key ="disputemanagement.nr.list.tranxcode"/></td>
	                                    <td class="title" nowrap><bean:message key ="disputemanagement.nr.list.amt"/></td>
	                                    <td class="title" nowrap><bean:message key ="disputemanagement.nr.list.arn"/></td>
										<td class="title" nowrap><bean:message key ="disputemanagement.nr.list.processcode"/></td>
	                                    <td class="title" nowrap></td>
	                                 </tr>
		                               <logic:iterate id ="commObj" name ="SEARCHLIST">
										<bean:define id="sno" name="commObj" property="column1"/>
		                                  <tr>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column2" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column3" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column4" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column5" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column6" /></td>
	                                    	<td class="title" nowrap>
	                                    		<input type="button" value="View" onclick="<%= "javascript:view('"+sno+"');"%>">
	                                    	</td>
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