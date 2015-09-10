<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>
<%@page import="java.util.ArrayList"%>

<html:html>
<head>
<title><bean:message key="documentuploadsetup.screentitle" /></title>
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
					key="documentuploadsetup.screentitle" /></td>
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
							action="documentuploadlist">
							<html:hidden property="issuerId"
								value="<%=(String)session.getAttribute("ISSUER")%>" />
							<input type=hidden name="action" />
							<p class="titreSection"><html:button property="submitbutton" onclick="go('search')">
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
						<table cellpadding="0" cellspacing="4">
							<tr>
								<td nowrap class="ColumnFONT"><bean:message
									key="documentuploadsetup.cardnumber" /> &nbsp;&nbsp;<html:text
									property="cardNumber" size="20" /> &nbsp;&nbsp; </td>
								</tr><tr>	
								<td nowrap class="ColumnFONT"><bean:message
									key="documentuploadsetup.claimnumber" />
								<html:text property="claimNumber" size="20" /> &nbsp;&nbsp; </td>
								</tr><tr>	
								<td nowrap class="ColumnFONT"><bean:message
									key="documentuploadsetup.claimdate" /> <html:select
										property="claimDateForm.day">
										<html:option value=""></html:option>
										<html:optionsCollection property="dayList" value="key"
											label="value" />
									</html:select> <html:select property="claimDateForm.month">
										<html:option value=""></html:option>
										<html:optionsCollection property="monthList" value="key"
											label="value" />
									</html:select> <html:select property="claimDateForm.year">
										<html:option value=""></html:option>
										<html:optionsCollection property="yearList" value="key"
											label="value" />
									</html:select></td>
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
						<display:column property="column1" title="Claim No" class="label"
							nowrap="true" />
						<display:column property="column2" title="Claim Date"
							class="label" nowrap="true" />
						<display:column property="column3" title="Card Number"
							class="label" nowrap="true" />
						<display:column property="column4" title="Customer Name"
							class="label" nowrap="true" />
						<display:column title="Details" class="label" align="center" width="80">
							<form name="listForm" action="documentuploadprocess.do">
							<html:hidden property="claimNumber"
										value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn1()%>" />
										<html:hidden property="cardNumber"
										value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn3()%>" />
										<html:hidden property="customerName"
										value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn4()%>" />
										<html:hidden property="strClaimDate"
										value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn2()%>" />
										<html:hidden property="reasonCode"
										value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn5()%>" />
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
