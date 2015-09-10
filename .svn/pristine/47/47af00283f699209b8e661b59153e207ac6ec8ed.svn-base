<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="cardcardembossingrenewalsearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script language="JavaScript">
  
    function report() {
     	 document.forms[0].action="GenerateReportServlet.do?hdOpCode=4&mode=Down&REPORT_CODE=CESREC";
     	document.forms[0].submit();
      }

    function downloadFile() {
    	document.forms[0].action="cardembossingrenewalprocess.do";
    	document.forms[0].method.value="embossFileDownload";

    	var ele = document.getElementById("embossDownloadFile");
    	ele.style.display = "none";

    	document.forms[0].submit();
     }

</script>

<body bgcolor="ffffff">
<html:form action ="cardembossingrenewalprocess.do">
 <input type="hidden" name="method" value ="">

<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>

<bean:define id="cardNums" name="renewalCardEmbossingSearch" property="cardNos"/>
<bean:define id="eFile" name="renewalCardEmbossingSearch" property="emossFile"/>

<input type="hidden" name="CARD_NO" value="<%=(String)cardNums%>" />
<input type="hidden" name="emossFile" value="<%=(String)eFile%>" />

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="cardcardembossingrenewalsearch.screentitle"/></td>
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
                <td class="texteMenuGauche">  
                	<div id="embossDownloadFile" align="left" style="display: block;">
                		<html:button property="submitbutton" onclick ="downloadFile()"><bean:message key ="common.download"/></html:button>
                	</div>
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
			<logic:present name="renewalCardEmbossingSearch" property="successCardsList"> 
			<bean:size id="size" name="renewalCardEmbossingSearch" property="successCardsList"/>
			<logic:greaterThan name="size" value="0" >
				<display:table id="cardEmboss" name="renewalCardEmbossingSearch" property="successCardsList" class="simple" width="80%">
				   <display:column property="cardNumber" title="Card No" class="label" />
				   <display:column property="cardExpDate" title="Card Exp Date" class="label" />
				   <display:column property="cardProduct" title="Card Product" class="label" />
				   <display:column property="custName" title="Customer Name" class="label" />
				   <display:column property="embossName" title="Emboss Name" class="label" />
				   <display:caption><font class="titreSection"><bean:message key ="cardemboss.successnos"/></font></display:caption>
			   	</display:table>
			</logic:greaterThan>
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