<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="disputeworkitem.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script>
function go(method)
{
  document.forms[0].method.value=method;
  document.forms[0].submit();

}
function showList()
{
      document.forms[0].action= "workitemlistpage.do";
      document.forms[0].submit();
  }
</script>
<body>
  <html:form action="requestworkitem.do" >
<input type="hidden" name="method"/>
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
        <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="titreSection" width="519"><bean:message key ="disputeworkitem.screentitle"/> </td>
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
  <tr><td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td></tr>
  <tr>
      <td valign="top"><br>
      <table>
        <tr>
          <td width="550" valign="top">
              <table width="550" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                  <td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2">
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td background="images/form_tab_bg_on.gif" class="form_tab_on" style="padding-left:15px"><bean:message key ="disputeworkitem.group1"/> </td>
                        <td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2">
                    <div align="right">
                     <html:cancel onclick ="showList()"><bean:message key ="common.cancel"/></html:cancel>
                    </div>			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr>
                  <td height="82" background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table border="0" cellpadding="0" cellspacing="0" width="264" height="72">
                    <tr>
                      <td height="18" nowrap class="desc_cell"><bean:message key ="disputeworkitem.cardnumber"/></td>
                      <td class="label"><bean:write name ="requestWorkItemForm"  property="cardNumber"  /></td>
                      <td height="18" nowrap class="desc_cell"><bean:message key ="disputeworkitem.customername"/></td>
                      <td class="label"><bean:write name ="requestWorkItemForm"  property="customerName"  /> </td>
                    </tr>
                    <tr>
                      <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputeworkitem.claimnumber"/></td>
                      <td class="label"><bean:write name ="requestWorkItemForm"  property="claimNumber"  /></td>
                      <td width="132" height="18" nowrap class="desc_cell"><bean:message key ="disputeworkitem.claimdate"/> </td>
                      <td class="label" width="132"><bean:write name ="requestWorkItemForm"  property="claimDate"  /></td>
                    </tr>
                    <tr>
                      <td height="18" nowrap class="desc_cell"><bean:message key ="disputeworkitem.claimtype"/> </td>
                      <td class="label"><bean:write name ="requestWorkItemForm"  property="claimType"  /></td>
                      <td height="18" nowrap class="desc_cell"><bean:message key ="disputeworkitem.claimsttus"/> </td>
                      <td class="label"><bean:write name ="requestWorkItemForm"  property="claimStatus"  /></td>
                    </tr>
                    <tr>
                      <td height="18" nowrap class="desc_cell"><bean:message key ="disputeworkitem.claimreason"/> </td>
                      <td height="18" colspan="3" class="label"><bean:write name ="requestWorkItemForm"  property="claimReason"  /></td>
                      </tr>

                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td height="66" background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table width="100%" border=0 cellpadding=0 cellspacing=0>
                    <tbody>
                      <tr>
                        <td><img height=22 alt="" src="images/form_tab_start_off.gif" width=5 border=0></td>
                        <td background=images/tbl_haut.gif><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>

                              <tr>
                               
                                <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"><a href ="#"><bean:message key ="disputeworkitem.tab1"/></td>
                                <td><img height=22 alt="" src="images/form_tab_btw_off_on.gif" width=25 border=0></td>
                                <td class="form_tab_off" background="images/form_tab_bg_off.gif"><a href="workitemdocprocess.do?action=documentList&claimNumber=<bean:write name ="requestWorkItemForm"  property="claimNumber"  />"> <bean:message key ="disputeworkitem.tab2"/></a>  </td>
                                <td><img height=22 alt="" src="images/form_tab_btw_on_off.gif" width=25 border=0></td>
                                <td nowrap background="images/form_tab_bg_off.gif" class="form_tab_off"><a href="workitemsetup.do?action=change&claimNumber=<bean:write name ="requestWorkItemForm"  property="claimNumber"  />"><bean:message key ="disputeworkitem.tab3"/></a></td>
                                <td><img src="images/form_tab_end_off.gif" width="25" height="22"></td>
                                </tr>
                       
                     
                            </tbody>
                        </table></td>
                        <td><img height=22 alt="" src="images/tbl_haut_d.gif" width=5 border=0></td>
                      </tr>
                      <tr>
                        <td background=images/tbl_g.gif></td>
                        <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td><div id="tab1">
                                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                          <td style="padding: 20px 20px 10px 20px;"><table class="wrapper" border="0" cellspacing="1" cellpadding="2">
                                            <tr>
                                              <td class="title" nowrap><bean:message key ="disputeworkitem.sequencenumber"/></td>
                                              <td class="title" nowrap><bean:message key ="disputeworkitem.communicationtype"/></td>
                                              <td class="title" nowrap><bean:message key ="disputeworkitem.claimdetails"/></td>
                                              <td class="title" nowrap><bean:message key ="disputeworkitem.action"/></td>
                                            </tr>
                                         
                                      <logic:iterate id="objCommDataBean" indexId ="seqno" name ="CLAIMHISTORY" scope="request">
                                            <tr>
                                              <td valign="top" nowrap class="DataTD" id="firstcolumn"><%=seqno.intValue()+1%></td>
                                              <td valign="top" nowrap class="DataTD"><bean:write name ="objCommDataBean"  property="column1"  /></td>
                                              <td class="DataTD" nowrap><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                  <td class="desc_cell"><bean:message key ="disputeworkitem.date"/></td>
                                                  <td class="label"><bean:write name ="objCommDataBean"  property="column2"  /></td>
                                                </tr>
                                                <tr>
                                                  <td class="desc_cell"><bean:message key ="disputeworkitem.details"/></td>
                                                  <td class="label"><bean:write name ="objCommDataBean"  property="column3"  /></td>
                                                </tr>
                                              </table></td>
                                              <td class="DataTD" nowrap>&nbsp;<bean:write name ="objCommDataBean"  property="column4"  /></td>
                                            </tr>
                                  </logic:iterate> 
                                             <tr>
                                              <td class="DataTD" id="firstcolumn" nowrap>&nbsp;</td>
                                              <td class="DataTD" nowrap>&nbsp;</td>
                                              <td class="DataTD" nowrap>&nbsp;</td>
                                              <td class="DataTD" nowrap>&nbsp;</td>
                                            </tr>
                                          
                                          </table></td>
                                          </tr>
                                      </table>
                                      
                                      
                                    </div>
                                        </td>
                                  </tr>
                              </table></td>
                            </tr>
                        </table></td>
                        <td background=images/tbl_d.gif></td>
                      </tr>
                      <tr>
                        <td background=images/tbl_g.gif></td>
                        <td background=images/tbl_d.gif></td>
                      </tr>
                      <tr>
                        <td><img height=5 alt="" src="images/tbl_bas_g.gif" width=5 border=0></td>
                        <td background=images/tbl_bas.gif></td>
                        <td><img height=5 alt="" src="images/tbl_bas_d.gif" width=5 border=0></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td height="19" background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor">&nbsp;</td>
                  <td background="images/tbl_d.gif"></td>
                </tr>


                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2">
                    <div align="right">
                     <html:cancel onclick ="showList()"><bean:message key ="common.cancel"/></html:cancel>
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