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
<script language="JavaScript" src="inc/js/chargeBack.js"></script>

</head>
<body bgcolor="ffffff">
<html:form action="chargeBackProcess.do">

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

<bean:define id="authCode" name="chargeBackSetupForm" property="authCode"/>
<html:hidden property="authCode" value="<%=(String)authCode%>"/>  

 --%>  
<bean:define id="arn" name="chargeBackSetupForm" property="arn"/>
<html:hidden property="arn" value="<%=(String)arn%>"/>  

<bean:define id="amtFrom" name="chargeBackSetupForm" property="amtFrom"/>
<html:hidden property="amtFrom" value="<%=(String)amtFrom%>"/>  

<bean:define id="amtTo" name="chargeBackSetupForm" property="amtTo"/>
<html:hidden property="amtTo" value="<%=(String)amtTo%>"/>  

<bean:define id="startDate" name="chargeBackSetupForm" property="startDate"/>
<html:hidden property="startDate" value="<%=(String)startDate%>"/>  

<bean:define id="endDate" name="chargeBackSetupForm" property="endDate"/>
<html:hidden property="endDate" value="<%=(String)endDate%>"/>  

<bean:define id="pageNo" name="chargeBackSetupForm" property="pageNo"/>
<html:hidden property="pageNo" value="<%=(String)pageNo%>"/>

<bean:define id="disCaseNo" name="chargeBackSetupForm" property="disCaseNo"/>
<html:hidden property="disCaseNo" value="<%=(String)disCaseNo%>"/>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="disputemanagement.chargebackview"/></td>
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
                         <bean:message key ="disputemanagement.cb.view.title"/>                       </td>
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
                    <logic:equal value="N" property="statusSymbol" name="chargeBackSetupForm">
      					<!-- <html:button property="submitbutton" onclick="go('chargeBackReversal')" ><bean:message key ="common.chargebackreversal"/></html:button> -->
      				</logic:equal>
                    <logic:equal value="P" property="statusSymbol" name="chargeBackSetupForm">
      					<!-- <html:button property="submitbutton" onclick="go('chargeBackReversal')" ><bean:message key ="common.chargebackreversal"/></html:button> -->
      				</logic:equal>
                    <logic:equal value="R" property="statusSymbol" name="chargeBackSetupForm">
      					<html:button property="submitbutton" onclick="go('chargeBackClose')" ><bean:message key ="common.close"/></html:button>
      					<!-- <html:button property="submitbutton" onclick="go('chargeBackArbitration')" ><bean:message key ="common.arbitration"/></html:button> -->
      				</logic:equal>
                    <logic:equal value="E" property="statusSymbol" name="chargeBackSetupForm">
      					<!-- <html:button property="submitbutton" onclick="go('chargeBackArbitration')" ><bean:message key ="common.arbitration"/></html:button> -->
      				</logic:equal>
      					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
                    </div>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2">
                  <table border="0" cellspacing="0" cellpadding="0">
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.cb.view.title"/> </td>
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
                            <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;width: 480px;" bordercolor="#111111">
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.disamt"/></td>
                                <td class="label"><bean:write name="chargeBackSetupForm" property="disAmt" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.curr"/></td>
                                <td class="label"><bean:write name="chargeBackSetupForm" property="disCurr" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.cardholder"/></td>
                                <td class="label"><bean:write name="chargeBackSetupForm" property="cardHolder" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.reason"/></td>
                                <td class="label"><bean:write name="chargeBackSetupForm" property="disReason" /> </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.status"/></td>
                                <td class="label"><bean:write name="chargeBackSetupForm" property="status" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.user"/></td>
                                <td class="label"><bean:write name="chargeBackSetupForm" property="user" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.mmsg"/></td>
                                <td class="label"><bean:write name="chargeBackSetupForm" property="memMsgText" /></td>
                              </tr>
                              <!-- 
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.attdoc"/></td>
                                <td class="label"></td>
                              </tr>
                               -->
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.cb.view.remarks"/></td>
                                <td class="label"><html:text property="remarks" /></td>
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
		                            <td>
		                            	<table cellspacing=0 cellpadding=0 border=0>
			                                <tbody>
			                                  <tr>
			                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
			                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.cb.view.eventdetail"/> </td>
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
		                          	<td style="padding-top: 10px;">
										<table class="wrapper" style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
											<tr>
												<td nowrap class="title"><bean:message key ="disputemanagement.cb.view.edate"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.cb.view.event"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.cb.view.details"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.cb.view.docuploaded"/></td>
											</tr>
											<logic:iterate id ="commObj" name ="EVENTLIST">
											<tr>
												<td nowrap class="DataTD"><bean:write name="commObj" property="dateTime"/> </td>
												<td nowrap class="DataTD"><bean:write name="commObj" property="disputEevent.description"/> </td>
												<td nowrap class="DataTD"><bean:write name="commObj" property="remarks"/> </td>
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
                  <td class="form_bgcolor"  colspan="2">
                    <div align="right">
                    <logic:equal value="N" property="statusSymbol" name="chargeBackSetupForm">
      					<!-- <html:button property="submitbutton" onclick="go('chargeBackReversal')" ><bean:message key ="common.chargebackreversal"/></html:button> -->
      				</logic:equal>
                    <logic:equal value="P" property="statusSymbol" name="chargeBackSetupForm">
      					<!-- <html:button property="submitbutton" onclick="go('chargeBackReversal')" ><bean:message key ="common.chargebackreversal"/></html:button> -->
      				</logic:equal>
                    <logic:equal value="R" property="statusSymbol" name="chargeBackSetupForm">
      					<html:button property="submitbutton" onclick="go('chargeBackClose')" ><bean:message key ="common.close"/></html:button>
      					<!-- <html:button property="submitbutton" onclick="go('chargeBackArbitration')" ><bean:message key ="common.arbitration"/></html:button> -->
      				</logic:equal>
                    <logic:equal value="E" property="statusSymbol" name="chargeBackSetupForm">
      					<!-- <html:button property="submitbutton" onclick="go('chargeBackArbitration')" ><bean:message key ="common.arbitration"/></html:button> -->
      				</logic:equal>
      					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
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