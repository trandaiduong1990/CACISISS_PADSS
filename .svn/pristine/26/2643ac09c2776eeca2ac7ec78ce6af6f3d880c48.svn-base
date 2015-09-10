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

<script language="JavaScript">
function go(action) {
    document.forms[0].disTypeCode.value="CB";
    document.forms[0].method.value=action;
    document.forms[0].action= "disChargeBackProcess.do";
    document.forms[0].submit();
 }

function rr(action) {
	//alert('test');
    document.forms[0].disTypeCode.value="RR";
    document.forms[0].method.value="addChargeBack";
    document.forms[0].action= "disChargeBackProcess.do";
    document.forms[0].submit();
 }
     
function showList(){
      document.forms[0].action= "disTranxList.do";
      document.forms[0].submit();
   }
</script>

</head>
<body bgcolor="ffffff">
<html:form action="disTranxProcess.do">
<input type="hidden" name="method"/>
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>

<html:hidden property="disTypeCode" value=""/>  
  
<bean:define id="cardNo" name="disTranxSetupForm" property="cardNo"/>
<html:hidden property="cardNo" value="<%=(String)cardNo%>"/>  

<bean:define id="terminalId" name="disTranxSetupForm" property="terminalId"/>
<html:hidden property="terminalId" value="<%=(String)terminalId%>"/>  

<bean:define id="authCode" name="disTranxSetupForm" property="authCode"/>
<html:hidden property="authCode" value="<%=(String)authCode%>"/>  

<bean:define id="refNo" name="disTranxSetupForm" property="refNo"/>
<html:hidden property="refNo" value="<%=(String)refNo%>"/>  

<bean:define id="mcc" name="disTranxSetupForm" property="mcc"/>
<html:hidden property="mcc" value="<%=(String)mcc%>"/>  

<bean:define id="arn" name="disTranxSetupForm" property="arn"/>
<html:hidden property="arn" value="<%=(String)arn%>"/>  

<bean:define id="amtFrom" name="disTranxSetupForm" property="amtFrom"/>
<html:hidden property="amtFrom" value="<%=(String)amtFrom%>"/>  

<bean:define id="amtTo" name="disTranxSetupForm" property="amtTo"/>
<html:hidden property="amtTo" value="<%=(String)amtTo%>"/>  

<bean:define id="startDate" name="disTranxSetupForm" property="startDate"/>
<html:hidden property="startDate" value="<%=(String)startDate%>"/>  

<bean:define id="endDate" name="disTranxSetupForm" property="endDate"/>
<html:hidden property="endDate" value="<%=(String)endDate%>"/>  

<bean:define id="pageNo" name="disTranxSetupForm" property="pageNo"/>
<html:hidden property="pageNo" value="<%=(String)pageNo%>"/>

<bean:define id="settlementId" name="disTranxSetupForm" property="disputeCleaningMasterForm.settlementID"/>
<html:hidden property="settlementId" value="<%=(String)settlementId%>"/>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="disputemanagement.transactionview"/></td>
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
                         <bean:message key ="disputemanagement.transactiondetail"/>                       </td>
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
                    <logic:equal value="N" property="RRButton" name="disTranxSetupForm">
      					<html:button property="submitbutton" onclick="rr('addRR')" disabled="true" ><bean:message key ="common.rr"/></html:button>
      				</logic:equal>
                    <logic:equal value="Y" property="RRButton" name="disTranxSetupForm">
      					<html:button property="submitbutton" onclick="rr('addRR')" ><bean:message key ="common.rr"/></html:button>
      				</logic:equal>
                    <logic:equal value="Y" property="chargeBackButton" name="disTranxSetupForm">
      					<html:button property="submitbutton" onclick="go('addChargeBack')" ><bean:message key ="common.chargeback"/></html:button>
      				</logic:equal>
                    <logic:equal value="N" property="chargeBackButton" name="disTranxSetupForm">
      					<html:button property="submitbutton" onclick="go('addChargeBack')"  disabled="true"><bean:message key ="common.chargeback"/></html:button>
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
                      <table cellspacing=0 cellpadding=0 border=0 width="122">
                        <tbody>
                          <tr>
                            <td>
                            <table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.tranxdetail1"/> </td>
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
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.tranxid"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.tranxLogId" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.cardno"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.cardNumber" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.tranxtype"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.tranxCode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.tranxstatus"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.remarks" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.tranxdate"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.strDateTime" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.recon"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.recon" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.recondate"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.strreconDate" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.motoecom"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="disputeCleaningMasterForm.motoECIID" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.entrymode"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="disputeCleaningMasterForm.posTerminalCap" /></td>
                              </tr>
                            </table>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                      </td>
                      <td style="padding: 20px 20px 10px 20px;" valign="top">
                      <table cellspacing=0 cellpadding=0 border=0>
                        <tbody>
                          <tr>
                            <td><table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.tranxdetail2"/></td>
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
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.refno"/></td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.referenceNo" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.traceauditno"/> </td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.traceNo" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.authcode"/> </td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.approvalCode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.orgamt"/> </td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.amount" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.orgcurr"/> </td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.currencyCode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.settamt"/> </td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.cardHolderTranxAmt" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.settcurr"/> </td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.cardHolderTranxCurr" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.currconvamt"/> </td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.tranxCurrCovAmt" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.clearamt"/> </td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.clearAmount" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.acqid"/> </td>
                                <td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.acqId" /></td>
                              </tr>
                              <tr>
                                <td nowrap style="color: red;" colspan="2"><bean:message key ="disputemanagement.view.note"/>*</td>
                              </tr>
                              <tr>
                                <td colspan="2"><bean:message key ="disputemanagement.view.notedetdebit"/></td>
                              </tr>
                              <tr>
                                <td nowrap colspan="2"><bean:message key ="disputemanagement.view.notedetcredit"/></td>
                              </tr>
                            </table>
                            </td>
                          </tr>
                        </tbody>
                      </table></td>
                    </tr>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.view.tranxpart"/> </td>
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
									<!--
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.acqid"/></td>
										<td class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.acqId" /></td>
									</tr> 
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.acqmastid"/></td>
										<td class="label"></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.merchantid"/></td>
										<td class="label"></td>
									</tr>
									 -->
									 <tr>
		                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.terminalid"/></td>
		                                <td  nowrap class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.terminalId" /></td>
		                              </tr>
		                              <tr>
		                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.merchantid"/></td>
		                                <td  nowrap class="label"><bean:write name="disTranxSetupForm" property="tranxlogForm.merchantId" /></td>
		                              </tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.merchantname"/></td>
										<td class="label"><bean:write name="disTranxSetupForm" property="disputeCleaningMasterForm.merchantName" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.merchantloc"/></td>
										<td class="label"><bean:write name="disTranxSetupForm" property="disputeCleaningMasterForm.merchantCity" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.merchantcnt"/></td>
										<td class="label"><bean:write name="disTranxSetupForm" property="disputeCleaningMasterForm.merchantCountry" /></td>
									</tr>
								</table>
							</td>
                          </tr>
                        </tbody>
                      </table>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <logic:present name="RELATEDLIST" scope="request"> 
                	<bean:size id ="size" name ="RELATEDLIST"/>
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
		                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.relatedcases"/> </td>
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
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.cno"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.follow"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.sta"/></td>
												<!-- <td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.rcode"/></td>  -->
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.rdes"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.daystoact"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.amt"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.arn"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.user"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.dissource"/></td>
												<td nowrap class="title"><bean:message key ="disputemanagement.relatedcases.distype"/></td>
											</tr>
											<bean:define id="disMngAlrtBuff" name="disTranxSetupForm" property="disMngAltBuff" />
											<% int disMngAlt = Integer.valueOf((String)disMngAlrtBuff).intValue(); %>
											<logic:iterate id ="commObj" name ="RELATEDLIST">
												<bean:define id="cDays" name="commObj" property="createdDays" />
												<bean:define id="rtLimit" name="commObj" property="disputeReason.timeLimit" />
												<% 
												String css = "DataTD";
												int daysToAct = Integer.valueOf((String)rtLimit).intValue() - Integer.valueOf((String)cDays).intValue();
												if(daysToAct <= disMngAlt) css = "expiresoon";
												%>
											<tr>
												<td nowrap class="<%=css %>"><bean:write name="commObj" property="disputeCaseNo"/> </td>
												<td nowrap class="<%=css %>"><bean:write name="commObj" property="updatedDate"/> </td>
												<td nowrap class="<%=css %>"><bean:write name="commObj" property="status.description"/> </td>
												<!-- <td nowrap class="<%=css %>"><bean:write name="commObj" property="disputeReason.reasonCode"/> </td>  -->
												<td nowrap class="<%=css %>"><bean:write name="commObj" property="disputeReason.chargeBackReason"/> </td>
												<td nowrap class="<%=css %>"><%= daysToAct %></td>
												<td nowrap class="<%=css %>"><bean:write name="commObj" property="disputeAmt"/> </td>
												<td nowrap class="<%=css %>"><bean:write name="commObj" property="arn"/> </td>
												<td nowrap class="<%=css %>"><bean:write name="commObj" property="disputeCreatedBy"/> </td>
												<td nowrap class="<%=css %>"><bean:write name="commObj" property="disputeSource"/> </td>
												<td nowrap class="<%=css %>"><bean:write name="commObj" property="disputeType.description"/> </td>
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
                    <logic:equal value="N" property="RRButton" name="disTranxSetupForm">
      					<html:button property="submitbutton" onclick="rr('addRR')" disabled="true" ><bean:message key ="common.rr"/></html:button>
      				</logic:equal>
                    <logic:equal value="Y" property="RRButton" name="disTranxSetupForm">
      					<html:button property="submitbutton" onclick="rr('addRR')" ><bean:message key ="common.rr"/></html:button>
      				</logic:equal>
                    <logic:equal value="Y" property="chargeBackButton" name="disTranxSetupForm">
      					<html:button property="submitbutton" onclick="go('addChargeBack')" ><bean:message key ="common.chargeback"/></html:button>
      				</logic:equal>
                    <logic:equal value="N" property="chargeBackButton" name="disTranxSetupForm">
      					<html:button property="submitbutton" onclick="go('addChargeBack')"  disabled="true"><bean:message key ="common.chargeback"/></html:button>
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