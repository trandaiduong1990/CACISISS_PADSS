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
<script language="JavaScript" src="inc/js/TranxEnquiryManualRecon.js"></script>

<script language="JavaScript">

function showList(){
	document.forms[0].action= "tranxEnquiryList.do";
	document.forms[0].submit();
}

function go(action) {
	document.forms[0].method.value=action;
	var r=confirm('Are you sure to do the Manual Recon?');
    if (r==true){
    	document.forms[0].submit();
    }
}
function revert(action) {
	document.forms[0].method.value=action;
	var r=confirm('Are you sure to revert the Transaction Amount?');
    if (r==true){
    	document.forms[0].submit();
    }
}

</script>

</head>
<body bgcolor="ffffff">
  <html:form action="tranxEnquiryProcess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  
<bean:define id="cardNo" name="tranxEnquirySetupForm" property="cardNo"/>
<html:hidden property="cardNo" value="<%=(String)cardNo%>"/>  

<bean:define id="terminalId" name="tranxEnquirySetupForm" property="terminalId"/>
<html:hidden property="terminalId" value="<%=(String)terminalId%>"/>  

<bean:define id="authCode" name="tranxEnquirySetupForm" property="authCode"/>
<html:hidden property="authCode" value="<%=(String)authCode%>"/>  

<bean:define id="refNo" name="tranxEnquirySetupForm" property="refNo"/>
<html:hidden property="refNo" value="<%=(String)refNo%>"/>  

<bean:define id="responseCode" name="tranxEnquirySetupForm" property="responseCode"/>
<html:hidden property="responseCode" value="<%=(String)responseCode%>"/> 

<bean:define id="startDate" name="tranxEnquirySetupForm" property="startDate"/>
<html:hidden property="startDate" value="<%=(String)startDate%>"/>  

<bean:define id="endDate" name="tranxEnquirySetupForm" property="endDate"/>
<html:hidden property="endDate" value="<%=(String)endDate%>"/>    

<bean:define id="recon" name="tranxEnquirySetupForm" property="recon"/>
<html:hidden property="recon" value="<%=(String)recon%>"/>  

<bean:define id="pageNo" name="tranxEnquirySetupForm" property="pageNo"/>
<html:hidden property="pageNo" value="<%=(String)pageNo%>"/>

<bean:define id="tranxId" name="tranxEnquirySetupForm" property="tranxlogForm.tranxLogId"/>
<html:hidden property="tranxId" value="<%=(String)tranxId%>"/>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="tranxenquiry.transactionview"/></td>
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
                         <bean:message key ="tranxenquiry.transactiondetail"/>                       </td>
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
                    	<logic:equal value="00" property="tranxlogForm.responseCode" name="tranxEnquirySetupForm">
	                    	<logic:equal value="Y" property="tranxlogForm.revertEnable" name="tranxEnquirySetupForm">
	                    		<logic:equal value="N" property="tranxlogForm.deleted" name="tranxEnquirySetupForm">
	                    			<logic:equal value="N" property="tranxlogForm.recon" name="tranxEnquirySetupForm">
	                    				<logic:equal value="SALE" property="tranxlogForm.tranxCode" name="tranxEnquirySetupForm">
	                    					<html:button property="submitbutton" onclick ="revert('TranxRevert')"><bean:message key ="common.revertTranx"/></html:button>
	                    				</logic:equal>
	                    			</logic:equal>
	                    		</logic:equal>
	                    	</logic:equal>
                    	</logic:equal>
                    	<logic:equal value="00" property="tranxlogForm.responseCode" name="tranxEnquirySetupForm">
	                    	<logic:equal value="Y" property="tranxlogForm.revertEnable" name="tranxEnquirySetupForm">
	                    		<logic:equal value="N" property="tranxlogForm.deleted" name="tranxEnquirySetupForm">
	                    			<logic:equal value="N" property="tranxlogForm.recon" name="tranxEnquirySetupForm">
		                    			<logic:equal value="CASH" property="tranxlogForm.tranxCode" name="tranxEnquirySetupForm">
		                    				<html:button property="submitbutton" onclick ="revert('TranxRevert')"><bean:message key ="common.revertTranx"/></html:button>
		                    			</logic:equal>
	                    			</logic:equal>
	                    		</logic:equal>
	                    	</logic:equal>
                    	</logic:equal>
                    	<logic:equal value="00" property="tranxlogForm.responseCode" name="tranxEnquirySetupForm">
	                    	<logic:equal value="N" property="tranxlogForm.deleted" name="tranxEnquirySetupForm">
                    			<logic:equal value="N" property="tranxlogForm.recon" name="tranxEnquirySetupForm">
	                    			<logic:equal value="SALE" property="tranxlogForm.tranxCode" name="tranxEnquirySetupForm">
                    					<html:button property="submitbutton" onclick ="go('manualRecon')"><bean:message key ="common.manalrecon"/></html:button>
                    				</logic:equal>
                    			</logic:equal>
                    		</logic:equal>
                    	</logic:equal>
                    	<logic:equal value="00" property="tranxlogForm.responseCode" name="tranxEnquirySetupForm">
	                    	<logic:equal value="N" property="tranxlogForm.deleted" name="tranxEnquirySetupForm">
                    			<logic:equal value="N" property="tranxlogForm.recon" name="tranxEnquirySetupForm">
	                    			<logic:equal value="CASH" property="tranxlogForm.tranxCode" name="tranxEnquirySetupForm">
                    					<html:button property="submitbutton" onclick ="go('manualRecon')"><bean:message key ="common.manalrecon"/></html:button>
                    				</logic:equal>
                    			</logic:equal>
                    		</logic:equal>
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="tranxenquiry.tranxdetail1"/> </td>
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
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.tranxid"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.tranxLogId" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.cardno"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.cardNumber" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.tranxtype"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.tranxCode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.tranxstatus"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.remarks" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.tranxdate"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.strDateTime" /></td>
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="tranxenquiry.tranxdetail2"/></td>
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
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.motoecom"/></td>
                                <td class="label"> </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.entrymode"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.posentryMode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.refno"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.referenceNo" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.traceauditno"/> </td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.traceNo" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.authcode"/> </td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.approvalCode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.indexid"/></td>
                                <td class="label"> </td>
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="tranxenquiry.view.moredet"/> </td>
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
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.list.amtori"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.amount" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.list.currori"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.currencyCode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.list.amtset"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.cardHolderTranxAmt" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.list.tranxcurrcona"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.tranxCurrCovAmt" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.list.currset"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.cardHolderTranxCurr" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.list.rescode"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.responseCode" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.list.amtclear"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.clearAmount" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.list.currconvfee"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.currConvFee" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="tranxenquiry.list.billedamt"/></td>
                                <td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.billedAmt" /></td>
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
                  <td class="form_bgcolor" colspan="2" style="padding: 20px 20px 10px 20px;">
                  		<table cellspacing=0 cellpadding=0 border=0>
                        <tbody>
                          <tr>
                            <td><table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="tranxenquiry.view.tranxpart"/> </td>
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
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.acqid"/></td>
										<td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.acqId" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.acqcntrycode"/></td>
										<td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.acQcCountryCode" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.merchantid"/></td>
										<td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.merchantId" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.merchantname"/></td>
										<td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.merchantName" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.merchantloc"/></td>
										<td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.merchantloc" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.merchantcnt"/></td>
										<td class="label"><bean:write name="tranxEnquirySetupForm" property="tranxlogForm.merchantcountry" /></td>
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
                  <td class="form_bgcolor" colspan="2" style="padding: 20px 20px 10px 20px;">
                  		<table cellspacing=0 cellpadding=0 border=0>
                        <tbody>
                          <tr>
                            <td><table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="tranxenquiry.view.manualrecon"/> </td>
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
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.mr.remarks"/></td>
										<td class="label">
											<div style="float: left;">
												<html:textarea styleId="remarks" style="resize:none;" property="remarks" cols="30" rows="3" onkeyup="javascript:countCharacters('demoReply3','remarks');" />
											</div>
                                    		<div style="color: green;width: 70px;float: left;">
                                    			<bean:message key ="tranxenquiry.view.mr.remarks.help"/>
                                    			<div id="demoReply3" style="color: green;width: 70px;padding-top: 20px;"></div>
                                    		</div>
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
                <logic:equal value="M" property="tranxlogForm.recon" name="tranxEnquirySetupForm">
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="tranxenquiry.view.manualrecon"/> </td>
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
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.mr.remarks"/></td>
										<td class="label" style="width: 450px;"><bean:write name="tranxEnquirySetupForm" property="remarks" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.mr.updatedby"/></td>
										<td class="label"><bean:write name="tranxEnquirySetupForm" property="updatedBy" /></td>
									</tr>
									<tr>
										<td nowrap class="desc_cell"><bean:message key ="tranxenquiry.view.mr.updatedate"/></td>
										<td class="label"><bean:write name="tranxEnquirySetupForm" property="updatedDate" /></td>
									</tr>
								</table>
							</td>
                          </tr>
                        </tbody>
                      </table>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                </logic:equal>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2">
                    <div align="right">
                    	<logic:equal value="00" property="tranxlogForm.responseCode" name="tranxEnquirySetupForm">
	                    	<logic:equal value="Y" property="tranxlogForm.revertEnable" name="tranxEnquirySetupForm">
	                    		<logic:equal value="N" property="tranxlogForm.deleted" name="tranxEnquirySetupForm">
	                    			<logic:equal value="N" property="tranxlogForm.recon" name="tranxEnquirySetupForm">
	                    				<logic:equal value="SALE" property="tranxlogForm.tranxCode" name="tranxEnquirySetupForm">
	                    					<html:button property="submitbutton" onclick ="revert('TranxRevert')"><bean:message key ="common.revertTranx"/></html:button>
	                    				</logic:equal>
	                    			</logic:equal>
	                    		</logic:equal>
	                    	</logic:equal>
                    	</logic:equal>
                    	<logic:equal value="00" property="tranxlogForm.responseCode" name="tranxEnquirySetupForm">
	                    	<logic:equal value="Y" property="tranxlogForm.revertEnable" name="tranxEnquirySetupForm">
	                    		<logic:equal value="N" property="tranxlogForm.deleted" name="tranxEnquirySetupForm">
	                    			<logic:equal value="N" property="tranxlogForm.recon" name="tranxEnquirySetupForm">
		                    			<logic:equal value="CASH" property="tranxlogForm.tranxCode" name="tranxEnquirySetupForm">
		                    				<html:button property="submitbutton" onclick ="revert('TranxRevert')"><bean:message key ="common.revertTranx"/></html:button>
		                    			</logic:equal>
		                    		</logic:equal>
	                    		</logic:equal>
	                    	</logic:equal>
                    	</logic:equal>
                    	<logic:equal value="00" property="tranxlogForm.responseCode" name="tranxEnquirySetupForm">
	                    	<logic:equal value="N" property="tranxlogForm.deleted" name="tranxEnquirySetupForm">
                    			<logic:equal value="N" property="tranxlogForm.recon" name="tranxEnquirySetupForm">
	                    			<logic:equal value="SALE" property="tranxlogForm.tranxCode" name="tranxEnquirySetupForm">
                    					<html:button property="submitbutton" onclick ="go('manualRecon')"><bean:message key ="common.manalrecon"/></html:button>
                    				</logic:equal>
                    			</logic:equal>
                    		</logic:equal>
                    	</logic:equal>
                    	<logic:equal value="00" property="tranxlogForm.responseCode" name="tranxEnquirySetupForm">
	                    	<logic:equal value="N" property="tranxlogForm.deleted" name="tranxEnquirySetupForm">
                    			<logic:equal value="N" property="tranxlogForm.recon" name="tranxEnquirySetupForm">
	                    			<logic:equal value="CASH" property="tranxlogForm.tranxCode" name="tranxEnquirySetupForm">
                    					<html:button property="submitbutton" onclick ="go('manualRecon')"><bean:message key ="common.manalrecon"/></html:button>
                    				</logic:equal>
                    			</logic:equal>
                    		</logic:equal>
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