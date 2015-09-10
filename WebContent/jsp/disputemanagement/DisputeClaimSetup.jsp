<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="disputemanagement.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script>
function go(action) 
{
       document.forms[0].method.value=action;	
       document.forms[0].submit();
}
function showList()
{
      document.forms[0].action= "claimformlistpage.do";
      document.forms[0].submit();
  }
  function docList(action)
  {
    document.forms[0].method.value=action;
    document.forms[0].submit();
  }
</script>
<script type="text/javascript">
<!--
function switchMenu(obj) {
	pageLoad()
	var el = document.getElementById(obj);
	if ( el.style.display != "none" ) {
		el.style.display = 'none';
	}
	else {
		el.style.display = '';
	}
}

function $() {
	var elements = new Array();
	for (var i = 0; i < arguments.length; i++) {
		var element = arguments[i];
		if (typeof element == 'string')
			element = document.getElementById(element);
		if (arguments.length == 1)
			return element;
		elements.push(element);
	}
	return elements;
}

function collapseAll(objs) {
	var i;
	for (i=0;i<objs.length;i++ ) {
		objs[i].style.display = 'none';
	}
}
function pageLoad() {
	collapseAll($('tab1','tab2'));
}
function showInitTab(){
	<logic:present name ="DOCLIST" scope="request">
		switchMenu('tab2')
	</logic:present>
	<logic:notPresent name="DOCLIST" scope="request">  
		switchMenu('tab1')
	</logic:notPresent>
}
//-->
</script>
<body onLoad="showInitTab()">
  <html:form action="claimformprocess.do" >
<input type="hidden" name="method"/>
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
 <html:hidden  property="settlementId"  />
 <html:hidden  property="cardNumber"  />
 <html:hidden  property="customerName" />
 <html:hidden  property="dateOfBirth" />
 <html:hidden  property="nricNo" />
 <html:hidden  property="nricExpire" />
 <html:hidden  property="merchantName" />
  <html:hidden  property="tranxDate" />
 <html:hidden  property="referenceNo" />
 <html:hidden  property="tranxAmt" />
 <html:hidden  property="tranxCurr" />
 <html:hidden  property="settlementAmt" />
 <html:hidden  property="settlementCurr" />
 <html:hidden  property="nubmerOfDocs" />
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="disputemanagement.screentitle"/></td>
            <td class="titreSection" width="484">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td class="titreSection" width="519">&nbsp;</td>
           <td class="texteMenuGauche"><div align="right"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div></td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr><td valign="top" class="ErrFONT"><font color="#FF0000"> <html:errors /> </font></td></tr>
  <tr> 
      <td valign="top"><br>
      <table>
        <tr> 
          <td width="482" valign="top"> 
              <table width="482" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr> 
                  <td> <img src="images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td background="images/form_tab_bg_off.gif" class="form_tab_off" style="padding-left:15px"><a href="javascript:switchMenu('tab1')"><bean:message key ="disputemanagement.tab1"/></a>  </td>
                        <td><img src="images/form_tab_btw_off_off.gif" width="25" height="22"></td>
                        <td background="images/form_tab_bg_off.gif" class="form_tab_off"><a href="javascript:switchMenu('tab2')"><bean:message key ="disputemanagement.tab2"/></a> </td>
                        <td><img src="images/form_tab_end_off.gif" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right"> 
                           <html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
                    </div>			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr>
                  <td height="66" background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;">
           
                  <table border="0" cellpadding="0" cellspacing="0" width="264" height="54">
                    <tr>
                      <td height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.cardnumber"/> </td>
                      <td class="label"><bean:write name ="disputeClaimSetupForm"  property="cardNumber"  /></td>
                      <td height="18" nowrap class="desc_cell">&nbsp;</td>
                      <td class="label">&nbsp;</td>
                    </tr>
                    <tr>
                      <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.customername"/></td>
                      <td class="label"><bean:write name ="disputeClaimSetupForm"  property="customerName" /> </td>
                      <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.nircorpassport"/></td>
                      <td class="label"><bean:write name ="disputeClaimSetupForm"  property="nricNo"  scope ="request"/></td>
                    </tr>
                    <tr>
                      <td height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.dob"/></td>
                      <td class="label"><bean:write name ="disputeClaimSetupForm"  property="dateOfBirth" /></td>
                      <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.nircexpire"/></td>
                      <td class="label"><bean:write name ="disputeClaimSetupForm"  property="nricExpire" /></td>
                    </tr>

                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <!-- Tranx Adress Details -->
                <tr>
                  <td height="153" background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor">
				  <!-- tab 1 -->
				  <div id="tab1">
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                          <tbody>
                            <tr>
                              <td><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.group1"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="144">
                                  <tr>
                                    <td height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.merchantname"/> </td>
                                    <td class="label"><bean:write name ="disputeClaimSetupForm"  property="merchantName" /></td>
                                  </tr>
                                  <tr>
                                    <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.cardnumber"/> </td>
                                    <td class="label"><bean:write name ="disputeClaimSetupForm"  property="cardNumber" /></td>
                                  </tr>
                                  <tr>
                                    <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.tranxdate"/> </td>
                                    <td class="label"><bean:write name ="disputeClaimSetupForm"  property="tranxDate" /></td>
                                  </tr>
                                  <tr>
                                    <td height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.referenceno"/> </td>
                                    <td class="label"><bean:write name ="disputeClaimSetupForm"  property="referenceNo" /></td>
                                  </tr>
                                  <tr>
                                    <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.tranxamount"/> </td>
                                    <td class="label"><bean:write name ="disputeClaimSetupForm"  property="tranxAmt" /></td>
                                  </tr>
                                  <tr>
                                    <td height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.tranxcurrency"/></td>
                                    <td class="label"><bean:write name ="disputeClaimSetupForm"  property="tranxCurr" /></td>
                                  </tr>
                                  <tr>
                                    <td height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.settlementamount"/> </td>
                                    <td class="label"><bean:write name ="disputeClaimSetupForm"  property="settlementAmt" /></td>
                                  </tr>
                                  <tr>
                                    <td height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.settlementcurrency"/> </td>
                                    <td class="label"><bean:write name ="disputeClaimSetupForm"  property="settlementCurr" /> </td>
                                  </tr>
                                                              
                              </table></td>
                            </tr>
                          </tbody>
                      </table></td>
                       <!-- Contact Adress Details -->
                       
                      <td style="padding: 20px 20px 10px 20px;"><table   border=0 cellpadding=0 cellspacing=0>
                          <tbody>
                            <tr>
                              <td><table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                    <tr>
                                      <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                      <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.group2"/></td>
                                      <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                            <tr>
                              <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                            </tr>
                            <tr>
                              <td height="159"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                                   <tr>
                                    <td class=desc_cell ><bean:message key ="disputemanagement.address1"/> </td>
                                    <td><html:text property="address1"  size="30"/>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell nowrap><bean:message key ="disputemanagement.address2"/> </td>
                                    <td><html:text property="address2"  size="30"/></td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell nowrap><bean:message key ="disputemanagement.city"/></td>
                                    <td><html:text property="city" size="30" maxlength="120" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell nowrap><bean:message key ="disputemanagement.state"/></td>
                                    <td><html:text property="state" size="30" maxlength="120" />                  </td>
                                  </tr>
                                    <tr>
                                    <td class=desc_cell nowrap><bean:message key ="disputemanagement.country"/></td>
                                    <td><html:select property="country">
                                        <option value=""></option>
                                        <html:optionsCollection property="countryList" value="key" label="value"/>
	                                </html:select> </td>
                                  </tr>
                                 <tr>
                                    <td class=desc_cell nowrap><bean:message key ="disputemanagement.postalcode"/></td>
                                    <td><html:text property="postalCode" maxlength="10"  size="15" /></td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell nowrap><bean:message key ="disputemanagement.phone"/></td>
                                    <td><html:text property="phoneNumber" maxlength="12"  size="15" /></td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell nowrap><bean:message key ="disputemanagement.fax"/></td>
                                    <td><html:text property="faxNumber" maxlength="12"  size="15" /> </td>
                                  </tr>
                                                                                                                                     
                              </table>      </td>
                            </tr>
                           </tbody>
                      </table></td>
                    </tr>
                    </table>
					</div>
			<!-- tab 2 -->
			<div id="tab2">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="disputemanagement.gropu3"/></td>
                                <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                      </tr>
                      <tr>
                        <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="54">
                            <tr>
                              <td height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.tranxdonebyuser"/>
                              </td>
                              <td class="label"><html:radio property="transactionDone"  value="Y" />
                                  <bean:message key ="disputemanagement.yes"/>
                                  <html:radio  property="transactionDone" value="N" />
                                  <bean:message key ="disputemanagement.no"/>
                              </td>
                            </tr>
                            <tr>
                              <td height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.calimtype"/>
                              </td>
                              <td class="label"><html:select property ="claimTypeId">
                                  <html:option value=" "> </html:option>
                                  <html:optionsCollection property="claimTypeList" value="key" label="value" />
                                </html:select>
                              </td>
                            </tr>
                            <tr>
                              <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.reasonforclaim"/>
                              </td>
                              <td class="label"><html:select  property ="claimReasonCode" onchange="docList('DocPreList')">
                                  <html:option value=""> </html:option>
                                  <html:optionsCollection property="claimReasonList" value="key" label="value" />
                                </html:select>
                              </td>
                            </tr>
                        </table></td>
                      </tr>
                    </tbody>
                </table></td>
              </tr>
              <logic:present name ="DOCLIST">
                <tr>
                  <td style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                      <tbody>
                        <tr>
                          <td><table cellspacing=0 cellpadding=0 border=0>
                              <tbody>
                                <tr>
                                  <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                  <td class=group_title background=images/tab_fond.gif><b>
                                    <bean:message key ="disputemanagement.gropu4"/>
                                  </b></td>
                                  <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                </tr>
                              </tbody>
                          </table></td>
                        </tr>
                        <tr>
                          <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                        </tr>
                        <tr>
                          <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="54">
                              <tr>
                                <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputemanagement.numberofdocument"/>
                                </td>
                                <td class="label"><bean:write name ="disputeClaimSetupForm"  property="nubmerOfDocs" /></td>
                              </tr>
                              <tr>
                                <td height="18" valign="top" nowrap class="desc_cell"><bean:message key ="disputemanagement.documentssubmitted"/>
                                </td>
                                <td class="label"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td class="desc_cell"><strong>
                                        <bean:message key ="disputemanagement.doucumentsmandatory"/>
                                      </strong></td>
                                    </tr>
                                    <logic:iterate id="objMandocs" name ="disputeClaimSetupForm" property="docsMandatoryList">
                                      <tr>
                                        <td class="desc_cell" nowrap width="132"><html:multibox property="selectedMandatoryDocs">
                                            <bean:write name="objMandocs" property ="key"/>
                                          </html:multibox>
                                            <bean:write name="objMandocs" property ="value"/>
                                        </td>
                                      </tr>
                                    </logic:iterate>
                                    <tr>
                                      <td class="desc_cell"><strong>
                                        <bean:message key ="disputemanagement.doucumentsnonmandatory"/>
                                      </strong></td>
                                    </tr>
                                    <logic:iterate id="objNondocs" name ="disputeClaimSetupForm" property="docsNonMandatoryList">
                                      <tr>
                                        <td class="desc_cell" nowrap width="132"><html:multibox property="selectedNonMandatoryDocs">
                                            <bean:write name="objNondocs" property ="key"/>
                                          </html:multibox>
                                            <bean:write name="objNondocs" property ="value"/>
                                        </td>
                                      </tr>
                                    </logic:iterate>
                                </table></td>
                              </tr>
                              <tr>
                                <td width="132" height="18" valign="top" nowrap class="desc_cell"><bean:message key ="disputemanagement.remarks"/></td>
                                <td class="label"><html:textarea property="remarks"  cols="50" rows="5"/></td>
                              </tr>
                          </table></td>
                        </tr>
                      </tbody>
                  </table></td>
                </tr>
              </logic:present>
            </table>
			</div>
			<!-- tab end-->
			</td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                                             
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                       <html:button property="submitbutton" onclick="go('add')"><bean:message key ="common.save"/></html:button>
        			   <html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
                </div>				  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td> <img src="images/tbl_bas_g.gif" border="0" alt="" width="5" height="5"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td background="images/tbl_bas.gif"></td>
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