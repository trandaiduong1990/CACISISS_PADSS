<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head><title></title>
<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
<META http-equiv=Expire CONFILTERED="now">
<META http-equiv=Pragma CONFILTERED="no-cache">
<META http-equiv=Cache-Control CONFILTERED="no-cache">
<LINK href="./inc/css/udm-style.css" type=text/css rel=StyleSheet>
<link  href="http://localhost:8080/cacis/inc/css/style.css" rel="stylesheet" type="text/css">
<script src="http://localhost:8080/cacis/inc/js/timer.js"></script>

<!-- <link  href="D:/eclipse/workspace/StrutsCacis/.deployables/StrutsCacis/inc/css/style.css" rel="stylesheet" type="text/css">
<script src="D:/eclipse/workspace/StrutsCacis/.deployables/StrutsCacis/inc/js/timer.js"></script>  
-->

</head>

<body bottomMargin=0 bgColor=#1c5b94 leftMargin=0 topMargin=0 rightMargin=0 marginwidth="0" marginheight="0" onload="window.setTimeout('getSecs()',1)" >
<form>
<table width="100%" border="0">
  <tr>
    <td colspan="2"> </td>
  </tr>
  <tr>

    <td><table border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="desc_cell"><bean:message key ="customerrelation.cardnumber"/> </td>
        <td class="label"><%=(String)session.getAttribute("CARDNUMBER")%></td>
        <td class="label">&nbsp;</td>
        <td class="desc_cell"><bean:message key ="customerrelation.customername"/></td>
        <td class="label"><%=(String)session.getAttribute("CUSTOMERNAME")%></td>
        <td class="label">&nbsp;</td>
        <td class="desc_cell"><bean:message key ="customerrelation.nircorpassport"/></td>
        <td class="label"><%=(String)session.getAttribute("NRICNO")%></td>
        <td class="label"><bean:message key ="customerrelation.timespent"/><input type="text" size="5" name="timespent" /></td>
        <td class="label">&nbsp;</td>
      </tr>

    </table></td>
  </tr>
</table>
</form>
</body>
</html:html>
