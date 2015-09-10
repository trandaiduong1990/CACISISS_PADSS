<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>
<%@page import="java.util.ArrayList"%>

<html:html>
<head>
<title><bean:message key="smsservicesetup.screentitle" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/cacis.js"></script>
</head>
<script language="JavaScript">
function doSetup()
{
	document.forms[0].action="smsserviceprocess.do?action=addNew";
	document.forms[0].submit();
}

function searchlist(){
	document.forms[0].submit();
}
</script>

<body bgcolor="ffffff">
<html:form action="smsservicelist">
	<table border="0" cellspacing="0" cellpadding="0"
		style="border-collapse: collapse" bordercolor="#111111" width="100%">
		<tr>
			<td>
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td class="titreSection"><bean:message
						key="smsservicesetup.screentitle" /></td>
				</tr>
				<tr>
					<td background="images/ligne.gif" colspan="2"><img
						src="images/spacer.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td class="titreSection">
					<table border="0" cellspacing="2" cellpadding="2" width="157">
						<tr>
							<td class="texteMenuGauche"><html:hidden property="issuerId"
								value="<%=(String)session.getAttribute("ISSUER")%>" />
							<p class="titreSection"><html:button property="submitbutton" onclick="doSetup()">
								<bean:message key="common.addnew" /></html:button>
							&nbsp;&nbsp;<html:button property="submitbutton" onclick="searchlist()">
								<bean:message key="common.search" /></html:button></p>
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
					<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table cellpadding="0" cellspacing="0">
								<tr>
									<td><font class="label"><bean:message
										key="smsservicesetup.cardnumber" /></font> <html:text
										property="cardNumber" size="20" onkeypress="return disableEnterKey(event)" /></td>
								</tr>
								<%@ include file="/jsp/common/Buttons.jsp"%>
								</html:form>
							</table>
							</td>
						</tr>
					</table>
					<br><logic:present name="SEARCHLIST" scope="request">
						<display:table id="appProcess" name="requestScope.SEARCHLIST"
							class="simple" width="80%">
							<display:column property="column1"
								title="Card Number" class="label" />
							<display:column property="column2"
								title="Account Inquiry" class="label" />
							<display:column property="column3"
								title="Payment Alert" class="label" />
							<display:column property="column4"
								title="General Message" class="label" />
							<display:column property="column5"
								title="Status" class="label" />
							<display:column property="column6"
								title="Last Updated Date" class="label" />
							<font class="label"><display:column
								title="Update" align="center" width="80"></font>
							<form name="listForm" action="smsserviceprocess.do">
					<html:hidden property="cardNumber"
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