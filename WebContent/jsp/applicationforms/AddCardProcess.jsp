<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
<title><bean:message key="cardproductsetup.screentitile" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/TranxEnquiryManualRecon.js"></script>

<script language="JavaScript">
	function showList() {
		document.forms[0].action = "addcardprocesslist.do?method=List";
		document.forms[0].submit();
	}

	function submitAction(method) {
		if(method == "accept") {
			document.forms[0].method.value = 'accept';
		} else {
			document.forms[0].method.value = 'reject';
		}
		document.forms[0].submit();
	}
	
	function showListBox(action) {
		if(action == true) {
			document.getElementById("listBoxId").style.display = 'block';
		} else {
			document.getElementById("listBoxId").style.display = 'none';
		}
	}
	function showCreditLimitField() {
		if(document.getElementById("accountTypeID").value == "E") {
			document.getElementById("creditLimitDisplay").style.display = 'block';
		} else {
			document.getElementById("creditLimitTextbox").style.display = 'block';
		}
	}
</script>

</head>
<body bgcolor="ffffff" onload="javascript:showCreditLimitField()">
	<html:form action="addCardProcess.do">
		<input type="hidden" name="method" />
		<html:hidden property="issuerId"
			value="<%=(String) session.getAttribute("ISSUER")%>" />
		<html:hidden property="userId"
			value="<%=(String) session.getAttribute("USERID")%>" />
		<bean:define id="accountType" name="addCardProcessSetupForm" property="accountType"/>
		<input type="hidden" value="<%=(String)accountType%>" id="accountTypeID" />	
		<html:hidden property="accountType" value="<%=(String)accountType%>"/>
		<bean:define id="sno" name="addCardProcessSetupForm" property="sno"/>
		<html:hidden property="sno" value="<%=(String)sno%>"/>
		<bean:define id="customerId" name="addCardProcessSetupForm" property="customerId"/>
		<html:hidden property="customerId" value="<%=(String)customerId%>"/>
		<bean:define id="customerName" name="addCardProcessSetupForm" property="customerName"/>
		<html:hidden property="customerName" value="<%=(String)customerName%>"/>
		<bean:define id="nric" name="addCardProcessSetupForm" property="nric"/>
		<html:hidden property="nric" value="<%=(String)nric%>"/>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="addCardProcess.addProductForm"/></td>
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
                         <bean:message key ="addCardProcess.addProductDetail"/>                       </td>
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
                    		<html:button property="submitbutton" onclick ="submitAction('accept')"><bean:message key ="common.accept"/></html:button>
                    		<html:button property="submitbutton" onclick ="submitAction('reject')"><bean:message key ="common.reject"/></html:button>
      						<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
                    </div>
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
                                <td nowrap class="desc_cell"><bean:message key ="addCardProcess.customerName"/></td>
                                <td class="label"><bean:write name="addCardProcessSetupForm" property="customerName"/></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="addCardProcess.nric"/></td>
                                <td class="label"><bean:write name="addCardProcessSetupForm" property="nric"/></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="addCardProcess.cardProductChoose"/></td>
                                <td class="text_as_label">
                                	<html:select property="cardProductId" styleClass="text_as_label" disabled="true">
                                		<html:optionsCollection property="cardProductList" value="key" label="value" />
                                	</html:select>
                                </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="addCardProcess.accountType"/></td>
                                <td>
                                	<table class="label">
                                		<tbody>
                                			<tr>
                                				<td><html:radio property="accountType" value="N" disabled="true"></html:radio><bean:message key ="addCardProcess.new"/></td>
                                				<td><html:radio property="accountType" value="E" disabled="true"></html:radio><bean:message key ="addCardProcess.existing"/></td>
                                			</tr>
                                		</tbody>
                                	</table>
                                </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="addCardProcess.creditLimit"/></td>
                                <td>
                                	<div id="creditLimitTextbox" style="display: none;">
                                		<html:text property="creditLimit" maxlength="30" size="20"/>
                                	</div>
                                	<div id="creditLimitDisplay" style="display: none;" class="label">
                                		<bean:write name="addCardProcessSetupForm" property="creditLimit"/>
                                	</div>
                                </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="addCardProcess.currency"/></td>
                                <td class="label">USD</td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="addCardProcess.cycle"/></td>
                                <td class="label">
                                	<html:select property="cycle">
                                		<logic:iterate name="cycleList" id="element">
                                			<html:option value="${element}">
											<bean:write name="element"/>
											</html:option>
										</logic:iterate>
                                	</html:select>
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
                    		<html:button property="submitbutton" onclick ="submitAction('accept')"><bean:message key ="common.accept"/></html:button>
                    		<html:button property="submitbutton" onclick ="submitAction('reject')"><bean:message key ="common.reject"/></html:button>
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