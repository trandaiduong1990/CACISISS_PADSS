<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="cardproductsetup.screentitile"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/chargeBackResend.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DWRUtils.js'></script>

</head>
<body bgcolor="ffffff" onload="makeReadOnly();">
<html:form action="chargeBackResendProcess.do" >

<input type="hidden" name="method"/>

<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  
<%--
<bean:define id="cardNo" name="chargeBackSetupForm" property="cardNo"/>
<html:hidden property="cardNo" value="<%=(String)cardNo%>"/>  

<bean:define id="terminalId" name="chargeBackSetupForm" property="terminalId"/>
<html:hidden property="terminalId" value="<%=(String)terminalId%>"/>

<bean:define id="refNo" name="chargeBackSetupForm" property="refNo"/>
<html:hidden property="refNo" value="<%=(String)refNo%>"/>  

<bean:define id="mcc" name="chargeBackSetupForm" property="mcc"/>
<html:hidden property="mcc" value="<%=(String)mcc%>"/>  

<bean:define id="authCode" name="chargeBackResendSetupForm" property="authCode"/>
<html:hidden property="authCode" value="<%=(String)authCode%>"/>  
 --%>  

<bean:define id="arn" name="chargeBackResendSetupForm" property="arn"/>
<html:hidden property="arn" value="<%=(String)arn%>"/>  

<bean:define id="amtFrom" name="chargeBackResendSetupForm" property="amtFrom"/>
<html:hidden property="amtFrom" value="<%=(String)amtFrom%>"/>  

<bean:define id="amtTo" name="chargeBackResendSetupForm" property="amtTo"/>
<html:hidden property="amtTo" value="<%=(String)amtTo%>"/>  

<bean:define id="startDate" name="chargeBackResendSetupForm" property="startDate"/>
<html:hidden property="startDate" value="<%=(String)startDate%>"/>  

<bean:define id="endDate" name="chargeBackResendSetupForm" property="endDate"/>
<html:hidden property="endDate" value="<%=(String)endDate%>"/>  

<bean:define id="pageNo" name="chargeBackResendSetupForm" property="pageNo"/>
<html:hidden property="pageNo" value="<%=(String)pageNo%>"/>

<bean:define id="disCaseNo" name="chargeBackResendSetupForm" property="disCaseNo"/>
<html:hidden property="disCaseNo" value="<%=(String)disCaseNo%>"/>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="disputemanagement.re.chargebackview"/></td>
            <td class="titreSection" width="455">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="top" class="ErrFONT">
    	<table width="100%" border="0" cellspacing="0" cellpadding="4">
	      <tr>
	        <td class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
	      </tr>
    	</table>
    </td>
  </tr>
  <tr> 
      <td valign="top"><br>
      <table>
        <tr> 
            <td height="419" valign="top"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td width="5"> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
                         <bean:message key ="disputemanagement.re.cb.view.title"/>                       </td>
                        <td><img src="images/form_tab_end_on.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>
                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
	                    <logic:equal value="Y" property="addButton" name="chargeBackResendSetupForm">
      						<html:button property="submitbutton" onclick ="go('chargeBackResend')"><bean:message key ="common.resend"/></html:button>
	      				</logic:equal>
      					<html:button property="submitbutton" onclick ="showView()"><bean:message key ="common.cancel"/></html:button>
                    </div>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2">
                  <table border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                	<td style="padding-left: 25px;color: green;">
	                		<bean:message key ="alert.checkcheckboxtoedit"/>
	                	</td>
	                </tr>
                    <tr>
                      <td style="padding: 20px 20px 10px 20px;" valign="top">
                      <table cellspacing=0 cellpadding=0 border=0>
                        <tbody>
                          <tr>
                            <td>
                            <table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.re.cb.view.title"/> </td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                </tbody>
                            </table>
                            </td>
                          </tr>
                          <tr>
                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td height="59">
                            <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;width: 350px;" bordercolor="#111111">
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.re.cb.view.status"/></td>
                                <td class="label"><bean:write name="chargeBackResendSetupForm" property="status" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.re.cb.view.user"/></td>
                                <td class="label"><bean:write name="chargeBackResendSetupForm" property="user" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.re.cb.view.createddate"/></td>
                                <td class="label"><bean:write name="chargeBackResendSetupForm" property="disCreatedDate" /></td>
                              </tr>
                            </table>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                      </td>
                    </tr>
                  </table>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <logic:present name="EVENTLIST" scope="request"> 
                	<bean:size id ="size" name ="EVENTLIST"/>
		        	<logic:greaterThan  name ="size" value ="0"> 
		                <tr> 
		                  <td background="images/tbl_g.gif"></td>
		                  <td class="form_bgcolor" colspan="2" style="padding: 20px 20px 10px 20px;">
		                  		<table cellspacing=0 cellpadding=0 border=0>
		                        <tbody>
		                          <tr>
		                            <td><table cellspacing=0 cellpadding=0 border=0>
		                                <tbody>
		                                  <tr>
		                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
		                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.re.cb.view.eventdetail"/> </td>
		                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
		                                  </tr>
		                                </tbody>
		                            </table></td>
		                          </tr>
		                          <tr>
		                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
		                          </tr>
		                          <tr>
		                          	<td style="padding-top: 10px;">
										<table class="wrapper" style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
											<tr>
												<td nowrap class="title"><bean:message key ="disputemanagement.re.cb.view.edate"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.re.cb.view.event"/></td>
												<td nowrap class="title" style="width: 150px;"><bean:message key ="disputemanagement.re.cb.view.details"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.re.cb.view.docuploaded"/></td>
											</tr>
											<logic:iterate id ="commObj" name ="EVENTLIST">
											<tr>
												<td nowrap class="DataTD"><bean:write name="commObj" property="dateTime"/> </td>
												<td nowrap class="DataTD"><bean:write name="commObj" property="disputEevent.description"/> </td>
												<td nowrap class="DataTD" style="width: 150px;"><bean:write name="commObj" property="remarks"/> </td>
												<td nowrap class="DataTD"><bean:write name="commObj" property="documentIncluded.description"/> </td>
											</tr>
											</logic:iterate>
										</table>
									</td>
		                          </tr>
		                        </tbody>
		                      </table>
		                  </td>
		                  <td background="images/tbl_d.gif"></td>
		                </tr>
		        	</logic:greaterThan>
                </logic:present>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" colspan="2" style="padding: 20px 20px 10px 20px;">
                  		<table cellspacing=0 cellpadding=0 border=0>
                        <tbody>
                          <tr>
                            <td><table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.cbadd.disinfo"/> </td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                          <tr>
                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                          </tr>
                          <tr>
                          	<td>
								<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
									<tr>
										<td class="label"></td>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.distype"/></td>
										<td class="label"><bean:write name="chargeBackResendSetupForm" property="disType" /></td>
									</tr>
									<tr>
										<td class="label"><html:checkbox property="edisGroup" value="Y" onclick="enableDisableGroup(this);" /></td>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.disgroup"/></td>
										<td class="label">
											<html:select styleId="disGroup" property="disGroup" onchange="groupReasonUpdate(this);" >
							         		  <html:option value=""></html:option>	
		                                      <html:optionsCollection property="disGroupList" value="key" label="value" />
			                                </html:select>
										</td>
									</tr>
									<tr>
										<td class="label"></td>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.disreason"/></td>
										<td class="label">
											<html:select styleId="disReason" property="disReason" >
							         		  <html:option value=""></html:option>	
		                                      <html:optionsCollection property="disReasonList" value="key" label="value" />
			                                </html:select>
										</td>
									</tr>
									<tr>
										<td class="label"><html:checkbox property="edisAmt" value="Y" onclick="enableDisableAmt(this);" /></td>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.disamt"/></td>
										<td class="label"><html:text styleId="disAmt" property="disAmt" /></td>
									</tr>
									<tr>
										<td class="label"><html:checkbox property="edisCurr" value="Y" onclick="enableDisableCurrency(this);" /></td>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.discurr"/></td>
										<td class="label">
											<html:select styleId="disCurr" property="disCurr" >
							         		  <html:option value=""></html:option>	
		                                      <html:optionsCollection property="currList" value="key" label="value" />
			                                </html:select>
										</td>
									</tr>
									<tr>
										<td class="label"></td>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.cardholder"/></td>
										<td class="label">
											<div style="float: left;width: 80px;">
				                            	<html:radio property="cardHolder" value="C" /> 
												<bean:message key="disputemanagement.cbadd.cardholder.c" />
											</div>
											<div style="float: left">
												<html:radio property="cardHolder" value="D" /> 
												<bean:message key="disputemanagement.cbadd.cardholder.d" />
											</div>
										</td>
									</tr>
									<tr>
										<td class="label"><html:checkbox property="ememMsgText" value="Y" onclick="enableDisableMemText(this);" /></td>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.mmsgtext"/></td>
										<td class="label">
											<div style="float: left;">
												<html:textarea styleId="memMsgText" style="resize:none;" property="memMsgText" cols="40" rows="4" onkeyup="javascript:countCharacters('demoReply3','memMsgText');" />
                                    		</div>
                                    		<div style="color: green;width: 70px;float: left;">
                                    			<bean:message key ="disputemanagement.cbadd.mmsgtxt.help"/>
                                    			<div id="demoReply3" style="color: green;width: 70px;padding-top: 30px;"></div>
                                    		</div>
										</td>
									</tr>
									<tr>
										<td class="label"></td>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.docattached"/></td>
										<td class="label"><html:checkbox property="attachingDoc" value="Y" /></td>
									</tr>
									<tr>
										<td class="label"><html:checkbox property="edisArn" value="Y" onclick="enableDisableArn(this);" /></td>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.arn"/></td>
										<td class="label"><html:text styleId="disArn" property="disArn" size="25" /></td>
									</tr>
								</table>
							</td>
                          </tr>
                        </tbody>
                      </table>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2">
                    <div align="right">
	                    <logic:equal value="Y" property="addButton" name="chargeBackResendSetupForm">
      						<html:button property="submitbutton" onclick ="go('chargeBackResend')"><bean:message key ="common.resend"/></html:button>
	      				</logic:equal>
      					<html:button property="submitbutton" onclick ="showView()"><bean:message key ="common.cancel"/></html:button>
                    </div>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td> <img src="images/tbl_bas_g.gif" border="0" alt="" width="5" height="5"></td>
                  <td width="170" background="images/tbl_bas.gif"></td>
                  <td width="356" background="images/tbl_bas.gif"></td>
                  <td> <img src="images/tbl_bas_d.gif" border="0" alt="" width="5" height="5"></td>
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