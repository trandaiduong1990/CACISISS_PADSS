<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="customerrelation.statusremarks.title"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script type="text/javascript">

    function go(param) {
        document.forms[0].action="cardstatusremarkslist.do";
        document.getElementById("mode").value = param; 
   		document.forms[0].submit();
     }
    
</script>

<body bgcolor="ffffff">
<html:form action ="cardstatusremarkslist.do">

<bean:define id="cardNo" name="cardStatusForm" property="cardNo"/>
<html:hidden property="cardNo" value="<%=(String)cardNo%>"/>

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="customerrelation.statusremarks.title"/></td>
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
<logic:present name="cardStatusForm" property="statusList"> 
<bean:size id="size" name="cardStatusForm" property="statusList"/>
<logic:greaterThan name="size" value="0" >
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
          				<bean:message key ="customerrelation.statusremarks.cardno"/>
          			</td>
          			<td>
          				&nbsp;&nbsp;:
          			</td>
          			<td>
          				<bean:write name ="cardStatusForm" property="mcardNo" />
          			</td>
          		</tr>
          	</table>
          </td>
        </tr>
        <tr>
          <td valign="top">
		  	<%@ include file="/jsp/common/Buttons.jsp" %>
		  </td>
        </tr>
        <tr> 
          <td valign="top">
			
			   <display:table id="appProcess" name="cardStatusForm" property="statusList" class="simple" width="80%">
				   <display:column property="column1" title="Status No" class="label" />
				   <display:column property="column5" title="Description" class="label" />
				   <display:column property="column2" title="Remarks" class="label" />
				   <display:column property="column3" title="Updated By" class="label" />
				   <display:column property="column4" title="Updated Date" class="label" />
				   <display:caption><font class="titreSection">Card Status Remarks</font></display:caption>
			   </display:table>
     	  </td>
        </tr>
  		<tr> 
     		<td valign="top">&nbsp;</td>
  		</tr>
	</table>
   </td>
  </tr>
</logic:greaterThan>
<logic:lessEqual name="size" value="0">
	<tr>
		<td valign="top">
      		<table width="100%">
		        <tr>
		        	<td valign="top">
						<bean:message key ="customerrelation.statusremarks.norem"/>
					</td>
		        </tr>
			</table>
		</td>
	</tr>
</logic:lessEqual>
</logic:present>
</table>

</html:form> 
</body>
</html:html>