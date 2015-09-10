<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="issuersetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">

<script language="JavaScript">
   function go(action) {
       	document.forms[0].method.value=action;
   		document.forms[0].submit();
    }
    
     function showList(){
          	//document.forms[0].action= "issuerlist.do?method=List";
          	document.forms[0].action= "issuerlist.do";
     		document.forms[0].submit();
       }
 
 //to help link      
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
</head>
<body bgcolor="ffffff">
<html:form action="issuerprocess.do">
<input type="hidden" name="method"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="issuersetup.screentitle"/></td>
            <td class="titreSection" width="455">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td colspan="2" class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
              <tr>
             <td><html:button property="submitbutton" onclick ="go('addNew')"><bean:message key ="common.addnew"/></html:button><br></td>
              </tr>
            </table>
            <td class="texteMenuGauche"><div align="right">&nbsp;<a href='javascript:helpLink("ISSUSETUP")'>Help</a></div>
            </td>
          </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
        <br>
        <tr>
          <td valign="top" class="ErrFONT"><table width="100%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
            </tr>
          </table></td>
        </tr>
    <tr> 
      <td valign="top"><br>
      <table>
        <tr> 
            <td width="601" height="419" valign="top"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td width="5"> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
                          <bean:message key ="issuersetup.group1"/>                         </td>
                        <td><img src="images/form_tab_end_on.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td width="186"> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                      <%
					    if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add"))
					    {%>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
       						<html:button property="submitbutton" onclick="go('delete')"><bean:message key ="common.delete"/></html:button>
       						<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				    <%}%>	
                    </div>                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td style="padding: 20px 20px 10px 20px;" valign="top"><table cellspacing=0 cellpadding=0 border=0 width="122">
                        <tbody>
                          <tr>
                            <td><table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif> <bean:message key ="issuersetup.group2"/></td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                          <tr>
                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td height="59"><table border="0" cellpadding="0" cellspacing="0">
                              <tr>
                                <td class="desc_cell" nowrap><b><bean:message key ="issuersetup.issuerid"/></b></td>
                                <td ><html:text property="issuerId" maxlength="32" size="20"/>                                </td>
                              </tr>
                              <tr>
                                <td class="desc_cell" nowrap><b><bean:message key ="issuersetup.issuername"/></b></td>
                                <td ><html:text property="issuerName" maxlength="120" size="30"/>                                </td>
                              </tr>
                              <tr>
                                <td class="desc_cell" nowrap><b><bean:message key ="issuersetup.status"/></b></td>
                                <td valign="top">
                                   <html:select property="status">
                                        <option value=""></option>
                                        <html:optionsCollection property="statusList" value="key" label="value" />
	                                </html:select> </td>
                              </tr>
                            </table></td>
                          </tr>
                        </tbody>
                      </table></td>
                      <td style="padding: 20px 20px 10px 20px;" valign="top"><table cellspacing=0 cellpadding=0 border=0>
                        <tbody>
                          <tr>
                            <td><table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif> <bean:message key ="issuersetup.group3"/></td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                          <tr>
                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td><table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.contactname"/></td>
                                    <td><html:text property="contactName" maxlength="120" size="30" /></td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.contactphone"/></td>
                                    <td><html:text property="contactPhone" maxlength="15"  size="15" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.contactmobile"/></td>
                                    <td><html:text property="contactMobile" maxlength="15"  size="15" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.contactfax"/></td>
                                    <td><html:text property="contactFax" size="15" maxlength="15" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td noWrap class=desc_cell><bean:message key ="issuersetup.contactemail"/></td>
                                    <td><html:text property="contactEmail" size="30" /></td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                        </tbody>
                      </table></td>
                    </tr>
                    <tr>
                      <td style="padding: 20px 20px 10px 20px;" valign="top"><table cellspacing=0 cellpadding=0 border=0>
                        <tbody>
                          <tr>
                            <td><table cellspacing=0 cellpadding=0 border=0>
                                <tbody>
                                  <tr>
                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                    <td class=group_title background=images/tab_fond.gif> <bean:message key ="issuersetup.group3"/></td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                          <tr>
                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td><table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0 width="100%">
                                <tbody>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.address1"/> </td>
                                    <td><html:text property="address1"  size="30"/>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.address2"/> </td>
                                    <td><html:text property="address2"  size="30"/></td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.city"/></td>
                                    <td><html:text property="city" size="30" maxlength="120" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.state"/></td>
                                    <td><html:text property="state" size="30" maxlength="120" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.country"/></td>
                                    <td><html:select property="country">
                                        <option value=""></option>
                                        <html:optionsCollection property="countryList" value="key" label="value" />
	                                </html:select>
                                   </td>
                                  </tr>
                                  <tr>
                                    <td class=desc_cell noWrap><bean:message key ="issuersetup.postalcode"/></td>
                                    <td><html:text property="postalCode" maxlength="6"  size="6" />
                                    </td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                        </tbody>
                      </table></td>
                      <td style="padding: 20px 20px 10px 20px;" valign="top">&nbsp;</td>
                    </tr>
                    
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                      <%
					    if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add"))
					    {%>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
       						<html:button property="submitbutton" onclick="go('delete')"><bean:message key ="common.delete"/></html:button>
       						<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				    <%}%>	
                    </div>                  </td>
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
