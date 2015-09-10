<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<title><bean:message key="writeoffsetup.screentitle" /></title>
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

function helpLink(screenId) {
	aBase = '<html:rewrite forward="callHelpWin"/>'; 
	HC_doOpenRemote(aBase + '?screenId=' + screenId,'Help'); 
}
// Open window 
function HC_openWin(newURL, newName, newFeatures, orgName) { 
    var newWin = open(newURL, newName, newFeatures); 
    if (newWin.opener == null) newWin.opener = window; 
    newWin.opener.name = orgName; 
    return newWin 
} 
// Open centered remote 
function HC_doOpenRemote(aURL, newName){ 
   var newWin = HC_openWin(aURL, newName); newWin.focus();
    return newWin 
}
</script>
<body>
<html:form action="writeoffprocess.do">
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
						key="writeoffsetup.screentitle" /></td>
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
							<td class="texteMenuGauche">&nbsp;<a href='javascript:helpLink("WRITEOFF")'>Help</a></td>
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
										key="writeoffsetup.screentitle" /></td>
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
							<div align="right"><%System.out.println("ACTION => "+request.getAttribute("ACTION"));
							if (request.getAttribute("ACTION") == null
					|| ((String) request.getAttribute("ACTION")).equals("add")) {%> <html:button property="submitbutton"
								onclick="go('add')">
								<bean:message key="common.save" />
							</html:button>  <%} else if (((String) request.getAttribute("ACTION"))
					.equals("change")
					|| ((String) request.getAttribute("ACTION"))
							.equals("update")) {

			%> <html:button property="submitbutton" onclick="go('update')">
								<bean:message key="common.save" />
							</html:button>  <%} %>
							</div>
							</td>
							<td background="images/tbl_d.gif"></td>
						</tr>
						<tr>
							<td background="images/tbl_g.gif"></td>
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
														key="writeoffsetup.grouptitle1" /></td>
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
													key="writeoffsetup.issuerid" /> </b></td>
												<td valign="top" colspan="3"><html:text
													property="issuerId" size="20" readonly="true" /></td>
											</tr>
											<tr>
												<td nowrap class="desc_cell"></td>
												<td class="desc_cell" nowrap ><b> <bean:message
													key="writeoffsetup.noaction" /> &nbsp;&nbsp;&nbsp;</b></td>
												<td class="desc_cell" nowrap><b> <bean:message
													key="writeoffsetup.sendreminder" />&nbsp;&nbsp;&nbsp; </b></td>
												<td class="desc_cell" nowrap><b> <bean:message
													key="writeoffsetup.cardtemporaryblock" />&nbsp;&nbsp;&nbsp; </b></td>
												<td class="desc_cell" nowrap><b> <bean:message
													key="writeoffsetup.writeoff" /> &nbsp;&nbsp;&nbsp;</b></td>
											</tr>
												
											<tr align="center">
												<td class="desc_cell" nowrap><b> <bean:message
													key="writeoffsetup.dpd0" /> </b></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd0" value="-1">
												</html:radio></td>	
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd0" value="0">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd0" value="1">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd0" value="2">
												</html:radio></td>
											</tr>
											<tr align="center">
												<td class="desc_cell" nowrap><b> <bean:message
													key="writeoffsetup.dpd30" /> </b></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd30" value="-1">
												</html:radio></td>	
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd30" value="0">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd30" value="1">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd30" value="2">
												</html:radio></td>
											</tr>
											<tr align="center">
												<td class="desc_cell" nowrap><b> <bean:message
													key="writeoffsetup.dpd60" /> </b></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd60" value="-1">
												</html:radio></td>	
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd60" value="0">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd60" value="1">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd60" value="2">
												</html:radio></td>
											</tr>
											<tr align="center">
												<td class="desc_cell" nowrap><b> <bean:message
													key="writeoffsetup.dpd90" /> </b></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd90" value="-1">
												</html:radio></td>	
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd90" value="0">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd90" value="1">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd90" value="2">
												</html:radio></td>
											</tr>
											<tr align="center">
												<td class="desc_cell" nowrap><b> <bean:message
													key="writeoffsetup.dpd120" /> </b></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd120" value="-1">
												</html:radio></td>	
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd120" value="0">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd120" value="1">
												</html:radio></td>
												<td valign="top" class="form_tab_on" nowrap><html:radio
													property="dpd120" value="2">
												</html:radio></td>
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
							<div align="right">
							<%if (request.getAttribute("ACTION") == null
					|| ((String) request.getAttribute("ACTION")).equals("add")) {%> <html:button property="submitbutton"
								onclick="go('add')">
								<bean:message key="common.save" />
							</html:button>  <%} else if (((String) request.getAttribute("ACTION"))
					.equals("change")
					|| ((String) request.getAttribute("ACTION"))
							.equals("update")) {

			%> <html:button property="submitbutton" onclick="go('update')">
								<bean:message key="common.save" />
							</html:button> <%} %></div>
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
