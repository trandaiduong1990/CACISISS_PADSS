<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
<head>
<title><bean:message key ="cardprintingsearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>

<body bgcolor="ffffff">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="cardprintingsearch.screentitle"/></td>
            <td class="titreSection" width="484">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1">
            </td>
        </tr>
        <tr> 
            <td class="titreSection" width="519">&nbsp;</td>
            <td class="titreSection" width="484"> 
              <table border="0" cellspacing="2" cellpadding="2" align="right">
              <tr> 
                  <td width="8">&nbsp;</td>
                  <td class="texteMenuGauche"></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
      <td valign="top" height="584"><br>
      <table>
        <tr> 
          <td valign="top"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td width="5"> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
                          <bean:message key ="cardpinmailer.pinprinting"/>
                        </td>
                        <td> <img src="images/form_tab_end_on.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>
                  </td>
                  <td width="5"> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif" width="5"></td>
                  <td class="form_bgcolor"  colspan="2">
                  </td>
                  <td background="images/tbl_d.gif" width="5"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif" width="5"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr> 
                        <td rowspan="2" valign="top"> 
                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td  style="padding: 20px 20px 10px 20px;" valign="top">
                             <table cellspacing=0 cellpadding=0 border=0 width="122">
                                  <tbody> 
                                  <tr> 
                                    <td> 
                                      <table cellspacing=0 cellpadding=0 border=0>
                                        <tbody> 
                                        <tr> 
                                          <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                          <td class=group_title background=images/tab_fond.gif><bean:message key ="cardpinmailer.types"/></td>
                                          <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                        </tr>
                                        </tbody> 
                                      </table>
                                    </td>
                                  </tr>
                                  <tr> 
                                    <td> 
                                      <table border="0" cellpadding="0" cellspacing="0">
                                      <tr> 
                                          <td class="desc_cell" nowrap>
                                          <b>*</b>&nbsp;&nbsp;&nbsp;&nbsp;
                                          <html:link action="pinprintinglistpage.do"><bean:message key ="cardpinmailer.pinprinting"/></html:link>
                                          </td>
                                        </tr>
                                        <tr> 
                                          <td nowrap width="132"></td>
                                        </tr>
                                        <tr> 
                                          <td class="desc_cell" nowrap>
                                          <b>*</b>&nbsp;&nbsp;&nbsp;&nbsp;
                                          <html:link action="resendpinprintinglistpage.do"><bean:message key ="cardpinmailer.resendpin"/></html:link>
                                          </td>
                                        </tr>
                                        <tr> 
                                          <td nowrap width="132"></td>
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
                      </tr>
                    </table>
                  </td>
                  <td background="images/tbl_d.gif" width="5"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif" width="5" height="10"></td>
                  <td class="form_bgcolor" colspan="2" height="10">&nbsp; </td>
                  <td background="images/tbl_d.gif" width="5" height="10"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif" width="5"></td>
                  <td class="form_bgcolor"  colspan="2">
                  </td>
                  <td background="images/tbl_d.gif" width="5"></td>
                </tr>
                <tr> 
                  <td width="5"> <img src="images/tbl_bas_g.gif" border="0" alt="" width="5" height="5"></td>
                  <td width="383" background="images/tbl_bas.gif"></td>
                  <td width="295" background="images/tbl_bas.gif"></td>
                  <td width="5"> <img src="images/tbl_bas_d.gif" border="0" alt="" width="5" height="5"></td>
                </tr>
              </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html:html>