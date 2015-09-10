<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<head>
<title><bean:message key="disputedocumentssetup.screentitle" /></title>
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
<html:form action="disputedocumentsprocess.do">
	<html:hidden property="issuerId"
		value="<%=(String)session.getAttribute("ISSUER")%>" />
	<html:hidden property="lastUpdatedBy"
		value="<%=(String)session.getAttribute("USERID")%>" />
	<input type=hidden name="action" />

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="titreSection" width="519"><bean:message
						key="disputedocumentssetup.screentitle" /></td>
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
										key="disputedocumentssetup.grouptitle" /></td>
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
							<td class="form_bgcolor" valign="top"
								style="padding: 20px 20px 10px 20px;">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="desc_cell" nowrap><font class="label"><bean:message
									key="disputedocumentssetup.reasoncode" /></font></td>
									<td class="label"><html:text property="reasonCode" size="5" readonly="true" /></td>
								</tr>
							</table>
							</td>
							<td class="form_bgcolor">&nbsp;</td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif" height="216"></td>
							<td class="form_bgcolor" valign="top"
								style="padding: 20px 20px 10px 20px;" height="216">
							<table cellspacing=0 cellpadding=0 border=0 width="271">
								<tbody>
									<tr>
										<td>
										<table cellspacing=0 cellpadding=0 border=0>
											<tbody>
												<tr>
													<td><img height=19 alt="" src="images/tab_g.gif" width=8
														border=0></td>
													<td class=group_title background=images/tab_fond.gif><bean:message
														key="disputedocumentssetup.grouptitle1" /></td>
													<td><img height=19 alt="" src="images/tab_d.gif" width=8
														border=0></td>
												</tr>
											</tbody>
										</table>
										</td>
									</tr>
									<tr>
										<td bgcolor=#dce5ea>
										<div style="FONT-SIZE: 1px">&nbsp;</div>
										</td>
									</tr>
									<tr>
										<td height="21">
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td nowrap><font class="label"><bean:message
													key="disputedocumentssetup.documentname" /></font></td>
												<td nowrap>&nbsp;</td>
												<td nowrap><font class="label"><bean:message
													key="disputedocumentssetup.documententered" /></font></td>
											</tr>
											<tr>
												<td valign="top" nowrap><html:text
													property="mandatoryDocumentName" size="20" /></td>
												<td valign="top" nowrap>
												<table border="0" cellspacing="4" cellpadding="0"
													align="center">
													<tr>
														<td align="center" nowrap class="label"><html:button property="submitbutton"
															onclick="go('mandatoryAdd')">
															<bean:message key="disputedocumentssetup.toright" />
														</html:button></td>
													</tr>
													<tr>
														<td align="center" nowrap class="label"><html:button property="submitbutton"
															onclick="go('mandatoryRemove')">
															<bean:message key="disputedocumentssetup.toleft" />
														</html:button></td>
													</tr>
												</table>
												</td>
												<td class="label">
												<div align="center"><html:select
													property="arlMandatoryDocuments" style='width:250px'
													multiple="true" size="5">
													<html:optionsCollection property="mandatoryDocumentsList"
														value="key" label="value" />
												</html:select></div>
												</td>
											</tr>
										</table>
										</td>
									</tr>
								</tbody>
							</table>
							</td>
							<td class="form_bgcolor">&nbsp;</td>
							<td background="images/tbl_d.gif" height="216"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif" height="216"></td>
							<td class="form_bgcolor" style="padding: 20px 20px 10px 20px;"
								valign="top" height="216">
							<table cellspacing=0 cellpadding=0 border=0 width="318">
								<tbody>
									<tr>
										<td>
										<table cellspacing=0 cellpadding=0 border=0>
											<tbody>
												<tr>
													<td><img height=19 alt="" src="images/tab_g.gif" width=8
														border=0></td>
													<td class=group_title background=images/tab_fond.gif><bean:message
														key="disputedocumentssetup.grouptitle2" /></td>
													<td><img height=19 alt="" src="images/tab_d.gif" width=8
														border=0></td>
												</tr>
											</tbody>
										</table>
										</td>
									</tr>
									<tr>
										<td bgcolor=#dce5ea>
										<div style="FONT-SIZE: 1px">&nbsp;</div>
										</td>
									</tr>
									<tr>
										<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td nowrap><font class="label"><bean:message
													key="disputedocumentssetup.documentname" /></font></td>
												<td nowrap>&nbsp;</td>
												<td nowrap><font class="label"><bean:message
													key="disputedocumentssetup.documententered" /></font></td>
											</tr>
											<tr>
												<td valign="top" nowrap><html:text
													property="nonMandatoryDocumentName" size="20" /></td>
												<td valign="top" nowrap>
												<table border="0" cellspacing="4" cellpadding="0"
													align="center">
													<tr>
														<td align="center" nowrap class="label"><html:button property="submitbutton"
															onclick="go('nonMandatoryAdd')">
															<bean:message key="disputedocumentssetup.toright" />
														</html:button></td>
													</tr>
													<tr>
														<td align="center" nowrap class="label"><html:button property="submitbutton"
															onclick="go('nonMandatoryRemove')">
															<bean:message key="disputedocumentssetup.toleft" />
														</html:button></td>
													</tr>
												</table>
												</td>
												<td class="label">
												<div align="center"><html:select
													property="arlNonMandatoryDocuments" style='width:250px'
													multiple="true" size="5">
													<html:optionsCollection
														property="nonMandatoryDocumentsList" value="key"
														label="value" />
												</html:select></div>
												</td>
											</tr>
										</table>
										</td>
									</tr>
								</tbody>
							</table>
							</td>
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
							<div align="right"><html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button></div>
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
</html:form>
</body>
</html:html>
