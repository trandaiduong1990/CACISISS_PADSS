<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>

<html:html>
<head>
<title><bean:message key="helpsetup.screentitle" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<body bgcolor="ffffff">

<table border="0" cellspacing="0" cellpadding="0"
	style="border-collapse: collapse" bordercolor="#111111" width="100%">
	<tr>
		<td>
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td class="titreSection"><bean:message key="helpsetup.screentitle" /></td>
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
		<td valign="top"><br>
		<table width="100%">
			<tr>
				<td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
			</tr>
			<tr>
				<td valign="top"><br><logic:present name="SEARCHLIST" scope="request">
					<display:table id="appProcess" name="requestScope.SEARCHLIST"
						width="100%" pagesize="20" cellpadding="2" cellspacing="1"
						border="1" class="label">
						<display:column property="column1" title="S no"
							class="label" />
						<display:column property="column2" title="Field Name"
							class="label" />
						<display:column property="column3" title="Field Description"
							class="label" />
						<display:caption>
							<font class="label"><%=((CommonDataBean) pageContext.getAttribute("appProcess"))
							.getColumn4()%></font>
						</display:caption>
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
