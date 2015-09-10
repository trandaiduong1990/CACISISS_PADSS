<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>
<%@page import="java.util.ArrayList"%>

<html:html>
<head>
<title><bean:message key="statisticreportssetup.screentitle" /></title>
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
				<td class="titreSection"><bean:message
					key="statisticreportssetup.screentitle" /></td>
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
				<td valign="top"><html:form action="statisticreportslist">
					<html:hidden property="issuerId"
						value="<%=(String)session.getAttribute("ISSUER")%>" />
					<input type=hidden name="action" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table cellpadding="0" cellspacing="0">
								<tr>
									<td><font class="label"><bean:message
										key="statisticreportssetup.startdate" /></font></td>
									<td>&nbsp;&nbsp;</td>
									<td><html:text
									property="strStartDate" size="12" maxlength="10" /></td>
								</tr>
								<tr>
									<td><font class="label"><bean:message
										key="statisticreportssetup.enddate" /></font></td>
									<td>&nbsp;&nbsp;</td>
									<td><html:text
									property="strEndDate" size="12" maxlength="10" /></td>
								</tr>
								<tr>
									<td><font class="label"><bean:message
										key="statisticreportssetup.agency" /></font></td>
									<td>&nbsp;&nbsp;</td>
									<td><html:text property="agency" size="20" /></td>
								</tr>
								<tr>
									<td>&nbsp;&nbsp;</td>
									<td colspan="2">
									<div align="right"><html:button property="submitbutton" onclick="go('daily')">
										<bean:message key="statisticreportssetup.daily" />
									</html:button> <html:button property="submitbutton" onclick="go('weekly')">
										<bean:message key="statisticreportssetup.weekly" />
									</html:button> <html:button property="submitbutton" onclick="go('monthly')">
										<bean:message key="statisticreportssetup.monthly" />
									</html:button> <html:button property="submitbutton" onclick="go('yearly')">
										<bean:message key="statisticreportssetup.yearly" />
									</html:button> <html:button property="submitbutton" onclick="go('cancel')">
										<bean:message key="common.cancel" />
									</html:button></div>
									</td>
								</tr>

								<%@ include file="/jsp/common/Buttons.jsp"%>
								</html:form>

							</table>
							</td>
						</tr>
					</table>

					<br><logic:present name="SEARCHLIST" scope="request">
						<%String reportType = (String) request.getAttribute("reportType");

			%>
						<display:table id="appProcess" name="requestScope.SEARCHLIST"
							class="simple" width="80%">
							<display:column property="column1" title="<%=reportType%>"
								class="label" align="center" />
							<display:column property="column2" title="Claims Started"
								class="label" align="center" />
							<display:column property="column3" title="Claims Active"
								class="label" align="center" />
							<display:column property="column4" title="Claims Closed"
								class="label" align="center" />
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
