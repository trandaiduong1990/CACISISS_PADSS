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
<script language="JavaScript" src="inc/js/disputeChargeBack.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DWRUtils.js'></script>

</head>

<body bgcolor="ffffff">
  <html:form action="disChargeBackProcess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  
<bean:define id="disTypeCode" name="disChargeBackSetupForm" property="disTypeCode"/>
<html:hidden property="disTypeCode" value="<%=(String)disTypeCode%>"/>  
  
<bean:define id="cardNo" name="disChargeBackSetupForm" property="cardNo"/>
<html:hidden property="cardNo" value="<%=(String)cardNo%>"/>

<bean:define id="terminalId" name="disChargeBackSetupForm" property="terminalId"/>
<html:hidden property="terminalId" value="<%=(String)terminalId%>"/>

<bean:define id="authCode" name="disChargeBackSetupForm" property="authCode"/>
<html:hidden property="authCode" value="<%=(String)authCode%>"/>

<bean:define id="refNo" name="disChargeBackSetupForm" property="refNo"/>
<html:hidden property="refNo" value="<%=(String)refNo%>"/>

<bean:define id="mcc" name="disChargeBackSetupForm" property="mcc"/>
<html:hidden property="mcc" value="<%=(String)mcc%>"/>

<bean:define id="arn" name="disChargeBackSetupForm" property="arn"/>
<html:hidden property="arn" value="<%=(String)arn%>"/>

<bean:define id="amtFrom" name="disChargeBackSetupForm" property="amtFrom"/>
<html:hidden property="amtFrom" value="<%=(String)amtFrom%>"/>

<bean:define id="amtTo" name="disChargeBackSetupForm" property="amtTo"/>
<html:hidden property="amtTo" value="<%=(String)amtTo%>"/>

<bean:define id="startDate" name="disChargeBackSetupForm" property="startDate"/>
<html:hidden property="startDate" value="<%=(String)startDate%>"/>

<bean:define id="endDate" name="disChargeBackSetupForm" property="endDate"/>
<html:hidden property="endDate" value="<%=(String)endDate%>"/>

<bean:define id="pageNo" name="disChargeBackSetupForm" property="pageNo"/>
<html:hidden property="pageNo" value="<%=(String)pageNo%>"/>

<bean:define id="settlementId" name="disChargeBackSetupForm" property="disputeCleaningMasterForm.settlementID"/>
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
	        <td class="ErrFONT">
	        	<font color="#FF0000">
	        		<html:errors />
	        		<html:messages id="msg" message="true" header="errors.header" footer="errors.footer">
						<bean:write name="msg"/><br>
					</html:messages>
	        	</font>
	        </td>
	      </tr>
    	</table>
    </td>
  </tr>
  <tr> 
      <td valign="top"><br>
      <table style="width: 600px;">
        <tr> 
            <td height="419" valign="top"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td width="5"> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
                         <bean:message key ="disputemanagement.cbadd.title"/>
                        </td>
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
                    <logic:equal value="Y" property="addButton" name="disChargeBackSetupForm">
      					<html:button property="submitbutton" onclick="go('insertChargeBack')" ><bean:message key ="common.save"/></html:button>
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
                            <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse;width: 280px;" bordercolor="#111111">
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.cardno"/></td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.cardNumber" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.indexid"/></td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="disputeCleaningMasterForm.intlFeeIndex" /> </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.tranxid"/></td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.tranxLogId" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.refno"/></td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.referenceNo" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.tranxtype"/></td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.tranxCode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.tranxstatus"/></td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.remarks" /> </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.tranxdate"/></td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.strDateTime" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.recon"/></td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.recon" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.recondate"/></td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.strreconDate" /></td>
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
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.orgamt"/> </td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.amount" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.orgcurr"/> </td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.currencyCode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.settamt"/> </td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.cardHolderTranxAmt" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.settcurr"/> </td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.cardHolderTranxCurr" /></td>
                              </tr>
							  <tr>
								<td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.merchantname"/></td>
								<td class="label"><bean:write name="disChargeBackSetupForm" property="disputeCleaningMasterForm.merchantName" /></td>
							  </tr>
							  <tr>
								<td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.merchantloc"/></td>
								<td class="label"><bean:write name="disChargeBackSetupForm" property="disputeCleaningMasterForm.merchantCity" /></td>
							  </tr>
							  <tr>
								<td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.merchantcnt"/></td>
								<td class="label"><bean:write name="disChargeBackSetupForm" property="disputeCleaningMasterForm.merchantCountry" /></td>
							  </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.acqid"/> </td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.acqId" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.currconvamt"/> </td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.tranxCurrCovAmt" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="disputemanagement.view.clearamt"/> </td>
                                <td class="label"><bean:write name="disChargeBackSetupForm" property="tranxlogForm.clearAmount" /></td>
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
                <logic:notEqual name="disChargeBackSetupForm" property="disputeCleaningMasterForm.remarks" value="">
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
                                    <td nowrap class="desc_cell">
                  						<bean:message key ="disputemanagement.cbadd.remarks"/>
                  					</td>
                                    <td class="label">
                  						<bean:write name="disChargeBackSetupForm" property="disputeCleaningMasterForm.remarks" />
                  					</td>
                                  </tr>
                                </tbody>
                            	</table>
                            </td>
                          </tr>
                        </tbody>
                    </table>
                  </td>
                  <td background="images/tbl_d.gif"></td>	
                </tr>
                </logic:notEqual>
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
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.distype"/></td>
										<td class="label"><bean:write name="disChargeBackSetupForm" property="disType" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.disgroup"/></td>
										<td class="label">
											<html:select styleId="disGroup" property="disGroup" onchange="groupReasonUpdate(this);" >
							         		  <html:option value=""></html:option>	
		                                      <html:optionsCollection property="disGroupList" value="key" label="value" />
			                                </html:select>
										</td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.disreason"/></td>
										<td class="label">
											<html:select styleId="disReason" property="disReason" >
							         		  <html:option value=""></html:option>	
		                                      <html:optionsCollection property="disReasonList" value="key" label="value" />
			                                </html:select>
										</td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.disamt"/></td>
										<td class="label"><html:text property="disAmt" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.discurr"/></td>
										<td class="label">
											<html:select property="disCurr" >
							         		  <html:option value=""></html:option>	
		                                      <html:optionsCollection property="currList" value="key" label="value" />
			                                </html:select>
										</td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.moto"/></td>
										<td class="label">
											<html:select property="disMoto" >
							         		  <html:option value=""></html:option>	
		                                      <html:optionsCollection property="motoList" value="key" label="value" />
			                                </html:select>
										</td>
									</tr>
									<tr>
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
										<td nowrap class="desc_cell"><bean:message key ="disputemanagement.cbadd.docattached"/></td>
										<td class="label">
											<html:checkbox property="docUpload" value="Y" />
										</td>
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
                    <logic:equal value="Y" property="addButton" name="disChargeBackSetupForm">
      					<html:button property="submitbutton" onclick="go('insertChargeBack')" ><bean:message key ="common.save"/></html:button>
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