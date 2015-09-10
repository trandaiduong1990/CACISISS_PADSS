<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<title><bean:message key="workitemsetup.screentitle" /></title>
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
<body>
<html:form action="workitemprocess.do">
	<html:hidden property="issuerId"
		value="<%=(String)session.getAttribute("ISSUER")%>" />
	<html:hidden property="userId"
		value="<%=(String)session.getAttribute("USERID")%>" />
	<input type=hidden name="action" />

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="titreSection" width="519"><bean:message
						key="workitemsetup.screentitle" /></td>
					<td class="titreSection" width="484">&nbsp;</td>
				</tr>
				<tr>
					<td background="images/ligne.gif" colspan="3"><img
						src="images/spacer.gif" width="1" height="1"></td>
				</tr>
				<tr>
					<td class="titreSection" width="519">&nbsp;</td>
					<td class="titreSection" width="484">
					<table border="0" cellspacing="2" cellpadding="2" align="right">
						<tr>
							<td class="texteMenuGauche">
							<p class="titreSection">&nbsp;&nbsp;</p>
							</td>
							<td>&nbsp;</td>
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
							<td><img src="images/form_tab_start_off.gif" border="0" alt=""
								width="5" height="22"></td>
							<td background="images/tbl_haut.gif" colspan="2">
							<table border="0" cellpadding="0" cellspacing="0">
							     <tr>
								<td class=form_tab_off style="PADDING-LEFT: 15px" noWrap background=images/form_tab_bg_off.gif><a href= "requestworkitem.do?method=workItemInfo&claimNumber=<bean:write name ="workItem"  property="claimNumber"  />"><bean:message key ="disputeworkitem.tab1"/></a>  </td>
								<td><img height=22 alt="" src="images/form_tab_btw_off_on.gif" width=25 border=0></td>
								<td class="form_tab_off" background="images/form_tab_bg_off.gif"><a href="workitemdocprocess.do?action=documentList&claimNumber=<bean:write name ="workItem"  property="claimNumber"  />"> <bean:message key ="disputeworkitem.tab2"/></a>  </td>
								<td><img height=22 alt="" src="images/form_tab_btw_on_off.gif" width=25 border=0></td>
								<td nowrap background="images/form_tab_bg_on.gif" class="form_tab_off"><a href="#"><bean:message key ="disputeworkitem.tab3"/></a></td>
								<td><img src="images/form_tab_end_off.gif" width="25" height="22"></td>
								</tr>
							</table>
							</td>
							<td><img src="images/tbl_haut_d.gif" border="0" alt="" width="5"
								height="22"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif"></td>
							<td class="form_bgcolor" colspan="2">
							<div align="right"><%if (((String) request.getAttribute("ACTION")).equals("add")) {%>
							<html:button property="submitbutton" onclick="go('add')">
								<bean:message key="common.save" />
							</html:button><%} else {%><html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button><%}

			%></div>
							</td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif"></td>
							<td class="form_bgcolor" colspan="2"></td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif" height="216"></td>
							<td class="form_bgcolor" valign="top"
								style="padding: 20px 20px 10px 20px;" height="216">
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
														key="workitemsetup.grouptitle1" /></td>
													<td><img height=19 alt="" src="images/tab_d.gif" width=8
														border=0></td>
												</tr>
											</tbody>
										</table>
										</td>
									</tr>
									<tr>
										<td>
										<table border="0" cellpadding="0" cellspacing="0" width="322">
											<tr>
												<td nowrap class="desc_cell"><b> <bean:message
													key="workitemsetup.claimno" /> </b></td>
												<td valign="top"><html:text property="claimNumber" size="20"
													readonly="true" /></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b><bean:message
													key="workitemsetup.remarks" /></b></td>
												<td valign="top"><html:textarea property="remarks" rows="3"
													cols="50"></html:textarea></td>
											</tr>
											<tr>
												<td nowrap colspan="2">
												<table border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td class="desc_cell" nowrap><font class="label"><bean:message
															key="workitemsetup.documentname" /></font></td>
														<td class="desc_cell" nowrap>&nbsp;</td>
														<td class="desc_cell" nowrap><font class="label"><bean:message
															key="workitemsetup.documententered" /></font></td>
													</tr>
													<tr>
														<td valign="top" nowrap><html:text property="documentName"
															size="20" /></td>
														<td valign="top" nowrap>
														<table border="0" cellspacing="4" cellpadding="0"
															align="center">
															<tr>
																<td align="center" nowrap class="label"><%if (((String) request.getAttribute("ACTION")).equals("add")) {%>
																<html:button property="submitbutton" onclick="go('documentAdd')">
																	<bean:message key="workitemsetup.toright" />
																</html:button><%} else {%><html:button property="submitbutton"
																	onclick="go('documentAdd')" disabled="true">
																	<bean:message key="workitemsetup.toright" />
																</html:button><%}%></td>
															</tr>
															<tr>
																<td align="center" nowrap class="label"><%if (((String) request.getAttribute("ACTION")).equals("add")) {%>
																<html:button property="submitbutton" onclick="go('documentRemove')">
																	<bean:message key="workitemsetup.toleft" />
																</html:button><%} else {%><html:button property="submitbutton"
																	onclick="go('documentRemove')" disabled="true">
																	<bean:message key="workitemsetup.toleft" />
																</html:button><%}%></td>
															</tr>
														</table>
														</td>
														<td class="label">
														<div align="center"><html:select property="arlDocuments"
															style='width:250px' multiple="true" size="5">
															<html:optionsCollection property="documentNameList"
																value="key" label="value" />
														</html:select></div>
														</td>
													</tr>
												</table>
												</td>
											</tr>
										</table>
										</td>
									</tr>
								</tbody>
							</table>
							</td>
							<td class="form_bgcolor" style="padding: 20px 20px 10px 20px;"
								valign="top" height="216">&nbsp;</td>
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
							<div align="right"><%if (((String) request.getAttribute("ACTION")).equals("add")) {%>
							<html:button property="submitbutton" onclick="go('add')">
								<bean:message key="common.save" />
							</html:button><%} else {%><html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button><%}

		%></div>
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
