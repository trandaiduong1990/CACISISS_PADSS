<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>
<%@page import="java.util.ArrayList"%>

<html:html>
<head>
<title><bean:message key="applicationprocesssetup.screentitle" /></title>
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
<html:form action="applicationprocesssearchlist">
<table border="0" cellspacing="0" cellpadding="0"
	style="border-collapse: collapse" bordercolor="#111111" width="100%">
	<tr>
		<td>
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td class="titreSection">
				<logic:present parameter="letterCategory"> 
				<logic:equal parameter="letterCategory" property="letterCategory" value="AP">
					<bean:message
					key="applicationprocesssetup.grouptitle1" />
				</logic:equal>
				<logic:equal parameter="letterCategory" property="letterCategory" value="CI">
					<bean:message
					key="applicationprocesssetup.grouptitle2" />
				</logic:equal>
				<logic:equal parameter="letterCategory" property="letterCategory" value="PY">
					<bean:message
					key="applicationprocesssetup.grouptitle3" />
				</logic:equal>
				</logic:present>
				</td>
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
						<p class="titreSection"><html:button property="submitbutton" onclick="go('search')"><bean:message key ="common.search"/></html:button></p>
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
					<html:hidden property="letterCategory"/>
					<input type=hidden name="action" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table cellpadding="2" cellspacing="2">
								<tr>
									<td nowrap class="ColumnFONT"><font class="label"><bean:message
										key="applicationprocesssetup.letterTypes" /> </font>
									</td>	
									<td>
										<html:select
										property="letterId">
										<html:option value=""></html:option>
										<html:optionsCollection property="letterTypesList" value="key"
											label="value" />
									</html:select></td>
									
								</tr>
								<tr>
									<td nowrap class="ColumnFONT"><font class="label"><bean:message
										key="applicationprocesssetup.cardnumber" /></font> 
									</td>
									<td>
										<html:text
										property="cardNumber" size="21" maxlength="19" /> </td>
								</tr>
								<tr>
										<td nowrap class="ColumnFONT"><font class="label"><bean:message
										key="applicationprocesssetup.fromdate" /> </font> 
										</td>
										<td>
										<html:text property="transDateFrom" size="21" maxlength="19" /></td>	
								<tr>	
									<td nowrap class="ColumnFONT"><font class="label"><bean:message
										key="applicationprocesssetup.todate" /> </font>
									</td>	
									<td>
									<html:text property="transDateTo" size="21" maxlength="19" /> </td>
								</tr>
								<%@ include file="/jsp/common/Buttons.jsp"%>
								</html:form>

							</table>
							</td>
						</tr>
					</table>
					<br>

					<logic:present name="SEARCHLIST" scope="request">
						<display:table id="appProcess" name="requestScope.SEARCHLIST"
							class="simple" width="80%">
							<display:column property="column1" title="Dispatch Id"
								class="label" />
							<display:column property="column2" title="Letter Type"
								class="label" />
							<display:column property="column3" title="Card Number" class="label" />
							<display:column property="column4" title="Last Updated Date"
								class="label" />
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
