<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>

<html:html>
<head>
<title><bean:message key="printsetup.screentitle" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
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
				<td class="titreSection"><bean:message key="printsetup.screentitle" /></td>
			</tr>
			<tr>
				<td background="images/ligne.gif" colspan="2"><img
					src="images/spacer.gif" width="1" height="1"></td>
			</tr>
			<tr>
				<td class="titreSection">
				<table border="0" cellspacing="2" cellpadding="2" width="157">
					<tr>
						<td class="texteMenuGauche">
						<p class="titreSection">&nbsp;&nbsp;</p>
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
	<tr>
		<td valign="top">
		<table width="100%">
			<tr>
				<td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
			</tr>
			<tr>
				<td valign="top"><html:form action="printlist">
					<html:hidden property="issuerId"
						value="<%=(String)session.getAttribute("ISSUER")%>" />
					<input type=hidden name="action" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table cellpadding="0" cellspacing="0">
								<tr>
									<td><font class="label"><bean:message key="printsetup.message" /></font></td>
								</tr>
								<tr>
									<td>
									<div align="right"><html:button property="submitbutton" onclick="go('retrieve')">
										<bean:message key="printsetup.retrieve" />
									</html:button> &nbsp;&nbsp;<html:button property="submitbutton" onclick="go('print')">
										<bean:message key="printsetup.print" />
									</html:button> &nbsp;&nbsp;<html:button property="submitbutton" onclick="go('cancel')">
										<bean:message key="common.cancel" />
									</html:button></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table></html:form></td>
			</tr>
			<tr>
				<td valign="top">&nbsp;</td>
			</tr>
			<tr>
				<td><br><logic:present name="SEARCHLIST" scope="request">
					<%String filePath = (String) request.getAttribute("filePath");

			%>
					<display:table id="appProcess" name="requestScope.SEARCHLIST"
						width="100%" pagesize="20" cellpadding="2" cellspacing="1"
						border="1" class="label" align="center">
						<display:column property="column1" titleKey="printsetup.column1"
							class="label" align="center" />
						<display:column property="column2" titleKey="printsetup.column2"
							class="label" align="center" />
						<display:column property="column3" titleKey="printsetup.column3"
							class="label" align="center" />
						<display:column property="column4" titleKey="printsetup.column4"
							class="label" align="center" />
						<display:caption class="label">
							<%=filePath%>
						</display:caption>
					</display:table>
				</logic:present>&nbsp;</td>
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
