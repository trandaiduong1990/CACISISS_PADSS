<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<title><bean:message key="cardholderlimitadjustmentsetup.screentitle" /></title>
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
<html:form action="cardholderlimitadjustmentprocess.do">
	<html:hidden property="issuerId"
		value="<%=(String)session.getAttribute("ISSUER")%>" />
	<html:hidden property="lastUpdatedBy"
		value="<%=(String)session.getAttribute("USERID")%>" />
	<html:hidden property="temporaryLimitId" />
	<input type=hidden name="action" />

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="titreSection" width="519"><bean:message
						key="cardholderlimitadjustmentsetup.screentitle" /></td>
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
							<td class="texteMenuGauche">&nbsp;<a href="#" target="_blank">Help</a></td>
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
										key="cardholderlimitadjustmentsetup.grouptitle1" /></td>
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
							<div align="right"><%if (request.getAttribute("ACTION") == null
					|| ((String) request.getAttribute("ACTION")).equals("add")) {%> <html:button property="submitbutton"
								onclick="go('add')">
								<bean:message key="common.save" />
							</html:button> <html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button> <%} else if (((String) request.getAttribute("ACTION"))
					.equals("change")
					|| ((String) request.getAttribute("ACTION"))
							.equals("update")) {

			%> <html:button property="submitbutton" onclick="go('update')">
								<bean:message key="common.save" />
							</html:button> <html:button property="submitbutton" onclick="go('delete')">
								<bean:message key="common.delete" />
							</html:button> <html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button> <%} else if (((String) request.getAttribute("ACTION"))
					.equals("cancel")) {

			%> <html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button> <%}%></div>
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
														key="cardholderlimitadjustmentsetup.grouptitle1" /></td>
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
										<table border="0" cellpadding="0" cellspacing="0" height="109">
											<tr>
												<td class="desc_cell" nowrap><b><bean:message
													key="cardholderlimitadjustmentsetup.cardnumber" /></b></td>
												<td valign="top" width="242" nowrap><html:text
													property="cardNumber" size="22" readonly="true" /></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b><bean:message
													key="cardholderlimitadjustmentsetup.curr" /></b></td>
												<td valign="top" width="242" nowrap><html:select
													property="currCode">
													<html:option value=""></html:option>
													<html:optionsCollection property="currList" value="key"
														label="value" />
												</html:select></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b> <bean:message
													key="cardholderlimitadjustmentsetup.startdate" /> </b></td>
												<td valign="top" nowrap><html:select
													property="fromDateForm.day">
													<html:option value=""></html:option>
													<html:optionsCollection property="dayList" value="key"
														label="value" />
												</html:select> <html:select property="fromDateForm.month">
													<html:option value=""></html:option>
													<html:optionsCollection property="monthList" value="key"
														label="value" />
												</html:select> <html:select property="fromDateForm.year">
													<html:option value=""></html:option>
													<html:optionsCollection property="yearList" value="key"
														label="value" />
												</html:select></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b> <bean:message
													key="cardholderlimitadjustmentsetup.enddate" /> </b></td>
												<td valign="top"><html:select property="toDateForm.day">
													<html:option value=""></html:option>
													<html:optionsCollection property="dayList" value="key"
														label="value" />
												</html:select> <html:select property="toDateForm.month">
													<html:option value=""></html:option>
													<html:optionsCollection property="monthList" value="key"
														label="value" />
												</html:select> <html:select property="toDateForm.year">
													<html:option value=""></html:option>
													<html:optionsCollection property="yearList" value="key"
														label="value" />
												</html:select></td>
											</tr>
										</table>
										</td>
									</tr>
								</tbody>
							</table>
							</td>
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
														key="cardholderlimitadjustmentsetup.grouptitle2" /></td>
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
										<table border="0" cellpadding="0" cellspacing="0" width="322">
											<tr>
												<td class="desc_cell" nowrap><b> <bean:message
													key="cardholderlimitadjustmentsetup.creditlimit" /> </b></td>
												<td valign="top"><html:text property="creditLimit" size="20" /></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b> <bean:message
													key="cardholderlimitadjustmentsetup.cashlimit" /> </b></td>
												<td valign="top"><html:text property="cashLimit" size="20" /></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b><bean:message
													key="cardholderlimitadjustmentsetup.amountpertranx" /></b></td>
												<td valign="top" width="242"><html:text
													property="amountPerTranx" size="20" /></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b><bean:message
													key="cardholderlimitadjustmentsetup.dailylimitcount" /></b></td>
												<td valign="top" width="242"><html:text
													property="dailyLimitCount" size="20" /></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b><bean:message
													key="cardholderlimitadjustmentsetup.dailylimitamount" /></b></td>
												<td valign="top" width="242"><html:text
													property="dailyLimitAmount" size="20" /></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b><bean:message
													key="cardholderlimitadjustmentsetup.monthlylimitcount" /></b></td>
												<td valign="top" width="242"><html:text
													property="monthlyLimitCount" size="20" /></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap><b><bean:message
													key="cardholderlimitadjustmentsetup.monthlylimitamount" /></b></td>
												<td valign="top" width="242"><html:text
													property="monthlyLimitAmount" size="20" /></td>
											</tr>
										</table>
										</td>
									</tr>
								</tbody>
							</table>
							</td>
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
							<div align="right"><%if (request.getAttribute("ACTION") == null
					|| ((String) request.getAttribute("ACTION")).equals("add")) {%> <html:button property="submitbutton"
								onclick="go('add')">
								<bean:message key="common.save" />
							</html:button> <html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button> <%} else if (((String) request.getAttribute("ACTION"))
					.equals("change")
					|| ((String) request.getAttribute("ACTION"))
							.equals("update")) {

			%> <html:button property="submitbutton" onclick="go('update')">
								<bean:message key="common.save" />
							</html:button> <html:button property="submitbutton" onclick="go('delete')">
								<bean:message key="common.delete" />
							</html:button> <html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button> <%} else if (((String) request.getAttribute("ACTION"))
					.equals("cancel")) {

			%> <html:button property="submitbutton" onclick="go('cancel')">
								<bean:message key="common.cancel" />
							</html:button> <%}%></div>
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
