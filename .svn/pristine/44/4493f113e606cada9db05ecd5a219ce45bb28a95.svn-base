<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>

<html:html>
<head>
<title><bean:message key="disputedocumentssetup.screentitle" /></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script>
//to show the calimhistory list when click cancel button
function showList(url){
        document.forms[0].action = url;
        document.forms[0].submit();
         }
        
</script>
<body>
  <html:form action="requestworkitem.do">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
        <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="titreSection" width="519"><bean:message key="disputeworkitemdocument.screentitle" /> </td>
            <td class="titreSection" width="484">&nbsp; </td>
        </tr>
        <tr>
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
            <td class="titreSection" width="519">&nbsp;</td>
            <td class="titreSection" width="484">&nbsp;</td>
        </tr>
        <tr>
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr><td valign="top" class="ErrFONT"><font color="#FF0000"></font></td></tr>
  <tr>
      <td valign="top"><br>
      <table>
        <tr>
          <td width="482" valign="top">
              <table width="482" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr>
                  <td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2">
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td background="images/form_tab_bg_on.gif" class="form_tab_on" style="padding-left:15px"><bean:message key ="disputeworkitem.group1"/>  </td>
                        <td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2">
                    <div align="right">
                      <input name="b1" value="cancel" type="button" onclick="showList('requestworkitem.do?method=workItemInfo&claimNumber=<bean:write name ="workItem"  property="claimNumber"  />')" >
                      </div>			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
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
                                <td class=form_tab_off style="PADDING-LEFT: 15px" noWrap background=images/form_tab_bg_off.gif><a href= "requestworkitem.do?method=workItemInfo&claimNumber=<bean:write name ="workItem"  property="claimNumber"  />"><bean:message key ="disputeworkitem.tab1"/></a>  </td>
                                <td><img height=22 alt="" src="images/form_tab_btw_off_on.gif" width=25 border=0></td>
                                <td class="form_tab_off" background="images/form_tab_bg_on.gif"><a href="workitemdocprocess.do?action=documentList&claimNumber=<bean:write name ="workItem"  property="claimNumber"  />"> <bean:message key ="disputeworkitem.tab2"/></a>  </td>
                                <td><img height=22 alt="" src="images/form_tab_btw_on_off.gif" width=25 border=0></td>
                                <td nowrap background="images/form_tab_bg_off.gif" class="form_tab_off"><a href="workitemsetup.do?action=change&claimNumber=<bean:write name ="workItem"  property="claimNumber"  />"><bean:message key ="disputeworkitem.tab3"/></a></td>
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
                                          <td style="padding: 20px 20px 10px 20px;">
                                                                        
                   <br><logic:present name="SEARCHLIST" scope="request">
					   	<display:table id="appProcess" name="requestScope.SEARCHLIST"
					  		class="simple" width="80%">
					  		<display:column property="column1" title="Remarks Id"class="label" group="1" />
					  		<display:column property="column2" title="Claim Type"
					  			class="label" group="2" />
					  		<display:column property="column3" title="Document Uploaded"
					  			class="label" group="3" />
					  		<display:column property="column4" title="Claim Date"
					  			class="label" group="4" />
					  		<display:column property="column5" title="Document Name"
					  			class="label" />
					  	<display:caption><font class="titreSection">Document List</font></display:caption>	
					  	</display:table>
						</logic:present>
                                          
                                          </td>
                                          </tr>
                                      </table>
                                      </div>                                        </td>
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
                     <input name="b2" value="cancel" type="button" onclick="showList('requestworkitem.do?method=workItemInfo&claimNumber=<bean:write name ="workItem"  property="claimNumber"  />')" >
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