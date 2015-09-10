<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>
<%@page import="java.util.ArrayList"%>

<html:html>
<head>
<title><bean:message key="writeoffcardssetup.screentitle" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<%
System.out.println("In Cards List");
%>

<script>
function go(action) 
{
  document.forms[0].action.value=action;	
  document.forms[0].submit();
}
</script>

<body bgcolor="ffffff">


<table border="0" cellspacing="0" cellpadding="0"
	style="border-collapse: collapse" bordercolor="#111111" width="100%">
	<tr>
	
		<td>
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td class="titreSection"><bean:message
					key="writeoffcardssetup.screentitle" /></td>
			</tr>
			<tr>
				<td background="images/ligne.gif" colspan="2"><img
					src="images/spacer.gif" width="1" height="1"></td>
			</tr>
			<tr>
				<td class="titreSection">
				<table border="0" cellspacing="2" cellpadding="2" width="157">
					<html:form action="writeoffcardslist">
						<input type=hidden name="action" />
						<tr>
							<td class="texteMenuGauche">
							<p class="titreSection"><html:button property="submitbutton" onclick="go('search')">
								<bean:message key="common.search" />
							</html:button>&nbsp;&nbsp;</p>
							</td>
						</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td background="images/ligne.gif" colspan="2"><img
					src="images/spacer.gif" width="1" height="1"></td>
			</tr>
		</table>
		</td>
	</tr>

<%
System.out.println("In Cards List1");
%>
	
	<tr>
		<td valign="top"><br>
		<table width="100%">
			<tr>
				<td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
			</tr>
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td><font class="label"><bean:message
									key="writeoffcardssetup.cardnumber" /></font> </td><td>&nbsp;&nbsp;</td><td><html:text
									property="cardNumber" size="20" maxlength="19" /></td>
							</tr>
							<tr>
								<td><font class="label"><bean:message
									key="writeoffcardssetup.fromdate" /></font> </td><td>&nbsp;&nbsp;</td><td><html:text
									property="strFromDate" size="12" maxlength="10" /></td>
							</tr>
							<tr>
								<td><font class="label"><bean:message
									key="writeoffcardssetup.todate" /></font></td><td>&nbsp;&nbsp;</td><td><html:text
									property="strToDate" size="12" maxlength="10" /></td>
							</tr>
							<%@ include file="/jsp/common/Buttons.jsp"%>
							</html:form>
						</table>
						</td>
					</tr>
				</table>
<%
System.out.println("In Cards List2");
%>				
		<br><logic:present name="SEARCHLIST" scope="request">
				
			   <display:table id="appProcess" name="requestScope.SEARCHLIST"
				class="simple" width="80%">
				<display:column property="column1"
					title="Card Number" class="label" />
				<display:column property="column2"
					title="Write-Off Date" class="label" />
				<display:column property="column3"
					title="Credit Limit" class="label" />
				<display:column property="column4"
					title="Outstanding Balance" class="label" />
				</display:table>
				
		 </logic:present></td>

				
			</tr>
			<tr>
				<td valign="top">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</body>
</html:html>
