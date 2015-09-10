<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>
<%@page import="java.util.ArrayList"%>

<html:html>
<head>
<title><bean:message key="withdrawallimitrulessetup.screentitle" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script type="text/javascript">
function searchlist(){
	document.forms[0].submit();
}
</script>
</head>
<script src="inc/js/cacis.js"></script>
<script>
function doSetup()
{
	document.forms[0].action="withdrawallimitrulesprocess.do?action=addNew";
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
					key="withdrawallimitrulessetup.screentitle" /></td>
			</tr>
			<tr>
				<td background="images/ligne.gif" colspan="2"><img
					src="images/spacer.gif" width="1" height="1"></td>
			</tr>
			<tr>
				<td class="titreSection">
				<table border="0" cellspacing="2" cellpadding="2" width="157">
					<tr>
						<td class="texteMenuGauche"><html:form
							action="withdrawallimitruleslist">
							<html:hidden property="issuerId"
								value="<%=(String)session.getAttribute("ISSUER")%>" />
							<p class="titreSection"><html:button property="submitbutton" onclick="doSetup()">
								<bean:message key="common.addnew" />
							</html:button>&nbsp;&nbsp;<html:button property="submitbutton" onclick="searchlist()">
								<bean:message key="common.search" />
							</html:button></p></td>
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
				<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td><font class="label"><bean:message
									key="withdrawallimitrulessetup.ruleid" /></font></td>
								<td>&nbsp;&nbsp;</td>
								<td><html:text property="ruleId" size="20" /></td>
							</tr>
							<tr>
								<td><font class="label"><bean:message
									key="withdrawallimitrulessetup.tranxType" /></font></td>
								<td>&nbsp;&nbsp;</td>
								<td><html:text property="tranxType" size="20" /></td>
							</tr>
							<%@ include file="/jsp/common/Buttons.jsp"%>
							</html:form>
						</table>
						</td>
					</tr>
				</table>
				<br><logic:present name="SEARCHLIST" scope="request">
					<display:table id="appProcess" name="requestScope.SEARCHLIST"
						width="100%" pagesize="20" cellpadding="2" cellspacing="1"
						border="1" class="label">
						<display:column property="column1"
							titleKey="withdrawallimitrulessetup.column1" class="label" />
						<display:column property="column2"
							titleKey="withdrawallimitrulessetup.column2" class="label" />
						<display:column property="column3"
							titleKey="withdrawallimitrulessetup.column3" class="label" />
						<display:column property="column4"
							titleKey="withdrawallimitrulessetup.column4" class="label" />
						<display:column titleKey="withdrawallimitrulessetup.details"
							align="center" width="80">

							<form name="listForm" action="withdrawallimitrulesprocess.do">
						<html:hidden property="ruleId"
										value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn1()%>" />
									<html:hidden property="issuerId"
										value="<%=(String)session.getAttribute("ISSUER")%>" /> <html:hidden
										property="action" value="change" /> <html:submit
										value="Update" />
							</form>
						</display:column>
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
