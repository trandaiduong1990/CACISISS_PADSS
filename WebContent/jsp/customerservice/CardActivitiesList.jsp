<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html >
<head>
<title><bean:message key ="cardactivities.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
  
<body bgcolor="ffffff">

<html:form action="csrprocess">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>&nbsp;</td>
      <td valign="top">
        <table width="100%" height="230">
          <tr>
            <td valign="top" height="323">
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td> <img src="images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
                  <td colspan="2" background="images/tbl_haut.gif"><table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                     <td><b><font color ="red"><bean:message key ="cardactivities.screentitle"/></font></b></td>
                    </tr>
                  </table></td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" >
                    <div align="right"></div>                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
             
                <tr>
                  <td background="images/tbl_g.gif" height="136"></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;" height="136">
                    <table cellspacing=0 cellpadding=0 border=0>
                      <tbody>
          
                <logic:present name="SEARCHLIST" scope="request">     
                      <tr>
                        <td><img height=22 alt=""src="images/form_tab_start_on.gif" width=5 border=0></td>
                        <td background=images/tbl_haut.gif>
                          <table cellspacing=0 cellpadding=0 border=0>
                            <tbody> 
                            <tr> 
                              <td class=form_tab_off style="PADDING-LEFT: 15px" noWrap background=images/form_tab_bg_on.gif> 
                                <bean:message key ="customerrelation.cardactivitylog"/> </td>
                              <td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
                            </tr>
                            </tbody> 
                          </table>                        </td>
                        <td><img height=22 alt="" src="images/tbl_haut_d.gif" width=5 border=0></td>
                      </tr>
                      <tr>
                        <td background=images/tbl_g.gif></td>
                        <td>
                          <table border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px">
                                <table class="wrapper" border="0" cellspacing="1" cellpadding="2">
                        
                                  <tr>
                                    <td class="title" nowrap><bean:message key ="customerrelation.datetime"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.activity"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.stationip"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.reason"/></td>
                                    </tr>
                            <logic:iterate id ="commonObj" name ="SEARCHLIST">
                                  <tr>
                                    <td class="DataTD" id="firstcolumn" nowrap><bean:write name ="commonObj"  property="column1" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column2" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column3" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column4" /></td>
                                  </tr>
                            </logic:iterate>
 	                  </logic:present>
					       </table>   </td>
                            </tr>
                            <tr>
                              <td>
                                <div align="right"></div>  </td>
                            </tr>
                          </table>                        </td>
                        <td background=images/tbl_d.gif></td>
                      </tr>
                      <tr>
                        <td background=images/tbl_g.gif></td>
                        <td background=images/tbl_d.gif></td>
                      </tr>
                      <tr>
                        <td><img height=5 alt=""
                        src="images/tbl_bas_g.gif" width=5 border=0></td>
                        <td background=images/tbl_bas.gif></td>
                        <td><img height=5 alt=""
                        src="images/tbl_bas_d.gif" width=5
                    border=0></td>
                      </tr>
                      </tbody>
                    </table>                  </td>
                  <td background="images/tbl_d.gif" height="136"></td>
                </tr>
                <tr>
                  <td> <img src="images/tbl_bas_g.gif" border="0" alt="" width="5" height="5"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td> <img src="images/tbl_bas_d.gif" border="0" alt="" width="5" height="5"></td>
                </tr>
              </table>            </td>
          </tr>
        </table>      </td>
  </tr>
</table>
</html:form>
</body>
</html:html>