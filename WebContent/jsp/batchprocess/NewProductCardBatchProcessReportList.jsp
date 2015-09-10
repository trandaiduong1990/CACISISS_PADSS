<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="cardbatcprocess.newcards.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script language="JavaScript">
  
    function report() {
       	 //document.forms[0].action="newcardbatchprocess.do?method=report";
     	 document.forms[0].action="GenerateReportServlet.do?hdOpCode=4&mode=Down&REPORT_CODE=BPNPC";
     	 document.forms[0].submit();
      }

</script>

<body bgcolor="ffffff">
<html:form action ="newcardproductbatchprocess">

<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>

<bean:define id="batId" name="newProductCardBatchprocessFrom" property="batchId"/>
<html:hidden property="batchId" value="<%=(String)batId%>"/>

<bean:define id="cardNums" name="newProductCardBatchprocessFrom" property="cardNos"/>
<bean:define id="authPerson" name="newProductCardBatchprocessFrom" property="authUserId"/>

<input type="hidden" name="CARD_NO" value="<%=(String)cardNums%>" />
<input type="hidden" name="AUTH_PERSON" value="<%=(String)authPerson%>" />

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="cardbatcprocess.newcards.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
          	<table border="0" cellspacing="2" cellpadding="2" width="157">
              <tr> 
                <td class="texteMenuGauche"> 
                	<html:button property="submitbutton" onclick ="report()"><bean:message key ="common.report"/></html:button>
                </td>
              </tr>
          	</table>
          </td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
     <br>
      <table width="100%">
        <tr>
          <td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
        </tr>
        <tr>
          <td valign="top">
          	<table>
          		<tr>
          			<td>
          				<bean:message key ="cardbatcprocess.batchId"/>
          			</td>
          			<td>
          				&nbsp;&nbsp;:
          			</td>
          			<td>
          				<bean:write name ="newProductCardBatchprocessFrom" property="batchId" />
          			</td>
          		</tr>
          		<tr>
          			<td>
          				<bean:message key ="cardbatcprocess.noofsuccessapplicarion"/>
          			</td>
          			<td>
          				&nbsp;&nbsp;:
          			</td>
          			<td>
						<bean:size id="size" name="newProductCardBatchprocessFrom" property="successAppsPrimaryList"/>
          				<bean:write name ="size" />
          			</td>
          		</tr>
          	</table>
          </td>
        </tr>
        <tr> 
          <td valign="top">
			<logic:present name="newProductCardBatchprocessFrom" property="successAppsPrimaryList"> 
			<bean:size id="size" name="newProductCardBatchprocessFrom" property="successAppsPrimaryList"/>
			<logic:greaterThan name="size" value="0" >
				<display:table id="appProcess" name="newProductCardBatchprocessFrom" property="successAppsPrimaryList" class="simple" width="80%">
				   <display:column property="applicationId" title="Application Id" class="label" />
				   <display:column property="customerName" title="Customer Name" class="label" />
				   <display:column property="idNumber" title="NRIC" class="label" />
				   <display:column property="cardNumber" title="Card No" class="label" />
				   <display:column property="cardExpDate" title="Card Exp Date" class="label" />
				   <display:caption><font class="titreSection"><bean:message key ="cardbatcprocess.primary.exists"/></font></display:caption>
			   	</display:table>
			</logic:greaterThan>
			<logic:lessEqual name="size" value="0">
				<bean:message key ="cardbatcprocess.primary.nonexists"/>
			</logic:lessEqual>
			</logic:present>
     	  </td>
        </tr>
  		<tr> 
     		<td valign="top">&nbsp;</td>
  		</tr>
        <tr> 
          <td valign="top">
			<logic:present name="newProductCardBatchprocessFrom" property="successAppsSuppList"> 
			<bean:size id="size1" name="newProductCardBatchprocessFrom" property="successAppsSuppList"/>
			<logic:greaterThan name="size" value="0" >
				<display:table id="appProcess" name="newProductCardBatchprocessFrom" property="successAppsSuppList" class="simple" width="80%">
				   <display:column property="applicationId" title="Application Id" class="label" />
				   <display:column property="customerName" title="Customer Name" class="label" />
				   <display:column property="idNumber" title="NRIC" class="label" />
				   <display:column property="cardNumber" title="Card No" class="label" />
				   <display:column property="cardExpDate" title="Card Exp Date" class="label" />
				   <display:caption><font class="titreSection"><bean:message key ="cardbatcprocess.supp.exists"/></font></display:caption>
			   	</display:table>
			</logic:greaterThan>
			<logic:lessEqual name="size" value="0">
				<bean:message key ="cardbatcprocess.supp.nonexists"/>
			</logic:lessEqual>
			</logic:present>
     	  </td>
        </tr>
  		<tr> 
     		<td valign="top">&nbsp;</td>
  		</tr>
	</table>
   </td>
  </tr>
</table>

</html:form> 
</body>
</html:html>