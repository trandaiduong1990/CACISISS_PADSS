<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>
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
	document.forms[0].action.value = action;	
	document.forms[0].submit();
}
</script>
<body>
<html:form action="/documentuploadprocess" method="post"
	enctype="multipart/form-data">
	<html:hidden property="issuerId"
		value="<%=(String)session.getAttribute("ISSUER")%>" />
	<html:hidden property="userId"
		value="<%=(String)session.getAttribute("USERID")%>" />
	<html:hidden property="reasonCode" />
	<input type=hidden name="action" />

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="titreSection" width="519"><bean:message
						key="documentuploadsetup.screentitle" /></td>
					<td class="titreSection" width="484">&nbsp;</td>
				</tr>
				<tr>
					<td background="images/ligne.gif" colspan="3"><img
						src="images/spacer.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td class="titreSection">
					<table border="0" cellspacing="2" cellpadding="2" align="left">
						<tr>
							<td class="texteMenuGauche">
							<p class="titreSection"></p>
							</td>
					</table>
					</td>
					<td class="titreSection">
					<table border="0" cellspacing="2" cellpadding="2" align="right">
						<tr>
							<td class="texteMenuGauche">Help</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td background="images/ligne.gif" colspan="3"><img
						src="images/spacer.gif" width="1" height="1"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
		</tr>
		<tr>
			<td valign="top"><br>
			<table>
				<tr>
					<td valign="top">
					<table border="0" cellpadding="0" cellspacing="0" align="left">
						<tr>
							<td><img src="images/form_tab_start_on.gif" border="0" alt=""
								width="5" height="22"></td>
							<td background="images/tbl_haut.gif" colspan="2">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="form_tab_on" background="images/form_tab_bg_on.gif"
										style="padding-left:15px"><bean:message
										key="documentuploadsetup.grouptitle" /></td>
									<td><img src="images/form_tab_end_on.gif" width="25"
										height="22"></td>
								</tr>
							</table>
							</td>
							<td><img src="images/tbl_haut_d.gif" border="0" alt="" width="5"
								height="22"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif"></td>
							<td class="form_bgcolor" colspan="2">
							<div align="right"><html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button></div>
							</td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif"></td>
							<td class="form_bgcolor" valign="top" colspan="2"
								style="padding: 20px 20px 10px 20px;">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="desc_cell" nowrap><font class="label"><bean:message
										key="documentuploadsetup.cardnumber" /></font></td>
									<td class="label"><html:text property="cardNumber" size="20"
										readonly="true" /></td>
									<td class="desc_cell" nowrap><font class="label"><bean:message
										key="documentuploadsetup.customername" /></font></td>
									<td class="label"><html:text property="customerName" size="20"
										readonly="true" /></td>
								</tr>
								<tr>
									<td class="desc_cell" nowrap><font class="label"><bean:message
										key="documentuploadsetup.claimnumber" /></font></td>
									<td class="label"><html:text property="claimNumber" size="20"
										readonly="true" /></td>
									<td class="desc_cell" nowrap><font class="label"><bean:message
										key="documentuploadsetup.claimdate" /></font></td>
									<td class="label"><html:text property="strClaimDate" size="20"
										readonly="true" /></td>
								</tr>
							</table>
							</td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif"></td>
							<td class="form_bgcolor" colspan="2"><%@ include
								file="/jsp/common/Buttons.jsp"%></td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						<tr><td background="images/tbl_g.gif"></td>
							<td class="form_bgcolor" colspan="2"><html:select property="selectedDocumentName">
								<html:option value=""></html:option>
								<html:optionsCollection property="documentNameList"
									value="key" label="value" />
								</html:select> &nbsp;&nbsp; <html:file property="uploadFile" />
								&nbsp;&nbsp; <html:submit onclick="go('upload')"
								value="Upload" /></td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						</html:form>
						<tr>
							<td background="images/tbl_g.gif" height="216"></td>
							<td class="form_bgcolor" valign="top"><logic:present
								name="SEARCHLIST" scope="request">
								
								<display:table id="appProcess" name="requestScope.SEARCHLIST"
									width="100%" pagesize="20" cellpadding="2" cellspacing="1"
									border="1" class="label">
									<display:column title="Document Id" property="column1"
										class="DataTD" nowrap="true" />
									<display:column title="Document For" class="DataTD"
										nowrap="true">Request</display:column>
									<display:column title="Document Name" align="center" class="DataTD"
										nowrap="true">
										<table cellpadding="0" cellspacing="0" border="0">
											<%if (((CommonDataBean) pageContext.getAttribute("appProcess"))
					.getColumn3().trim().equals("N")) {%>
											<tr>
												<td nowrap><font class="DataTD"><%=((CommonDataBean) pageContext
								.getAttribute("appProcess")).getColumn2()%></font></td>
											</tr>
											<%} else if (((CommonDataBean) pageContext.getAttribute("appProcess"))
					.getColumn3().trim().equals("Y")) {%>
											<tr>
												<td nowrap><html:form action="/documentuploadprocess">
					<html:hidden property="claimNumber"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn5()%>" />
					<html:hidden property="cardNumber"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn6()%>" />
					<html:hidden property="customerName"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn7()%>" />
					<html:hidden property="strClaimDate"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn8()%>" />
					<html:hidden property="reasonCode"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn9()%>" />
					<html:hidden property="issuerId"
						value="<%=(String)session.getAttribute("ISSUER")%>" />
					<html:hidden property="userId"
						value="<%=(String)session.getAttribute("USERID")%>" />
					<html:hidden property="documentId"
						value="<%=((CommonDataBean) pageContext.getAttribute("appProcess")).getColumn1()%>" />
					<html:hidden property="documentName"
						value="<%=((CommonDataBean) pageContext.getAttribute("appProcess")).getColumn2()%>" />
					<html:hidden property="strUploadedDate"
						value="<%=((CommonDataBean) pageContext.getAttribute("appProcess")).getColumn4()%>" />
					<html:hidden property="action" value="open" />
					<html:submit>
						<%=((CommonDataBean) pageContext.getAttribute("appProcess")).getColumn2()%>
												</html:submit></html:form></td>
											</tr>
											<%}%>
										</table>
									</display:column>
									<display:column title="Action" align="center" class="DataTD"
										nowrap="true">
										<table cellpadding="0" cellspacing="0" border="0">
											<%if (((CommonDataBean) pageContext.getAttribute("appProcess"))
					.getColumn3().trim().equals("N")) {%>
											<tr>
												<td nowrap><font class="DataTD">Not Uploaded</font></td>
											</tr>
											<%} else if (((CommonDataBean) pageContext.getAttribute("appProcess"))
					.getColumn3().trim().equals("Y")) {%>
											<tr>
												<td nowrap><html:form action="/documentuploadprocess">
					<html:hidden property="claimNumber"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn5()%>" />
					<html:hidden property="cardNumber"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn6()%>" />
					<html:hidden property="customerName"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn7()%>" />
					<html:hidden property="strClaimDate"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn8()%>" />
					<html:hidden property="reasonCode"
						value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn9()%>" />
					<html:hidden property="issuerId"
						value="<%=(String)session.getAttribute("ISSUER")%>" />
					<html:hidden property="userId"
						value="<%=(String)session.getAttribute("USERID")%>" />
					<html:hidden property="documentId"
						value="<%=((CommonDataBean) pageContext.getAttribute("appProcess")).getColumn1()%>" />
					<html:hidden property="documentName"
						value="<%=((CommonDataBean) pageContext.getAttribute("appProcess")).getColumn2()%>" />
					<html:hidden property="strUploadedDate"
						value="<%=((CommonDataBean) pageContext.getAttribute("appProcess")).getColumn4()%>" />
					<html:hidden property="action" value="remove" />
					<html:submit value="Remove" /></html:form></td>
											</tr>
											<%}%>
										</table>
									</display:column>
								</display:table>
							</logic:present></td>
							<td class="form_bgcolor">&nbsp;</td>
							<td background="images/tbl_d.gif" height="216"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif"></td>
							<td class="form_bgcolor" colspan="2">&nbsp;</td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif"></td>
							<td class="form_bgcolor" colspan="2">
							<div align="right"><html:submit onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:submit></div>
							</td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						<tr>
							<td><img src="images/tbl_bas_g.gif" border="0" alt="" width="5"
								height="5"></td>
							<td background="images/tbl_bas.gif"></td>
							<td background="images/tbl_bas.gif"></td>
							<td><img src="images/tbl_bas_d.gif" border="0" alt="" width="5"
								height="5"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</body>
</html:html>
