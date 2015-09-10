<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="customerrelation.carddetails"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
  <script>
	 function go(action) {
	  document.forms[0].method.value=action;
	  document.forms[0].submit();
	 }
 </script>

<body bgcolor="ffffff">
<html:form action ="csrprocess">
 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
      <td valign="top" height="244">
        <table width="100%" height="230">
          <tr>
            <td valign="top" height="323">
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                 <tr> 
                  <td> <img src="images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
                  <td colspan="2" background="images/tbl_haut.gif"><table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td class="form_tab_off" background="images/form_tab_bg_off.gif" style="padding-left:15px"><html:link action="csrprocess.do?method=csrSummary"><bean:message key ="customerrelation.summary" /></html:link></td>
                      <td><img height=22 alt="" src="images/form_tab_btw_off_on.gif" width=25 border=0></td>
                       <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=customerInfo"><bean:message key ="customerrelation.customerinfo"/></html:link> </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_on_off.gif" width=25 border=0></td>
                     <td background="images/form_tab_bg_on.gif" nowrap class="form_tab_on"><bean:message key ="customerrelation.carddetails"/>  </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_off_off.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=accountInfo"><bean:message key ="customerrelation.accountdetails"/></html:link> </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_off_off.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=cardActivities"><bean:message key ="customerrelation.cardactivity"/></html:link> </td>
                      <td><img height=22 alt="" src="images/form_tab_btw_off_off.gif" width=25 border=0></td>
                      <td background="images/form_tab_bg_off.gif" class="form_tab_off"><html:link action="csrprocess.do?method=tranxHistory"><bean:message key ="customerrelation.cardtranxhistory"/></html:link> </td>
                      <td><img src="images/form_tab_end_off.gif" width="25" height="22"></td>
                      
                    </tr>
                  </table></td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
        
               <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" colspan="4"><div align="right"></div></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" colspan="4"><font color="#FF0000"><html:errors /></font></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
      <!-- for other primary cards to display -->   
         <logic:present name="PRIMARYCRADINFO" scope="request">    
                <tr>
                  <td height="66" background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 border=0>
                    <tbody>
                      <tr>
                        <td><img height=22 alt=""src="images/form_tab_start_on.gif" width=5 border=0></td>
                        <td background=images/tbl_haut.gif><table cellspacing=0 cellpadding=0 border=0>
                             
                            <tbody>
                                <tr>
                                <td class=form_tab_off style="PADDING-LEFT: 15px" noWrap background=images/form_tab_bg_on.gif><bean:message key ="customerrelation.primarycards"/></td>
                                <td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
                              </tr>
                            </tbody>
                             
                        </table></td>
                        <td><img height=22 alt="" src="images/tbl_haut_d.gif" width=5 border=0></td>
                      </tr>
                      <tr>
                        <td background=images/tbl_g.gif></td>
                        <td><table border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table class="wrapper" border="0" cellspacing="1" cellpadding="2">
                     
                                  <tr>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardnumber"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardproductname"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardexpire"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.creditlimit"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardstatus"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.avabalance"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.osbalance"/></td>
                                    </tr>
                             <logic:iterate id ="commonObj" name ="PRIMARYCRADINFO">
                                  <tr>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column1" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column2" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column3" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column4" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column5" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column6" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column7" /></td>
                                 </tr>
                            </logic:iterate>
 	                 
                                           
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
                        <td><img height=5 alt=""
                        src="images/tbl_bas_g.gif" width=5 border=0></td>
                        <td background=images/tbl_bas.gif></td>
                        <td><img height=5 alt=""
                        src="images/tbl_bas_d.gif" width=5      border=0></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
             
              
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" >
                    <div align="right"></div>                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
 
     </logic:present> 
    
     <!-- for other cards to display -->
     
      <logic:present name="OTHERCARDSINFO" scope="request">   
                <tr>
                  <td height="66" background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 border=0>
                    <tbody>
                      <tr>
                        <td><img height=22 alt=""src="images/form_tab_start_on.gif" width=5 border=0></td>
                        <td background=images/tbl_haut.gif><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                       
                              <tr>
                                <td class=form_tab_off style="PADDING-LEFT: 15px" noWrap background=images/form_tab_bg_on.gif><bean:message key ="customerrelation.othercards"/></td>
                                <td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
                              </tr>
                            </tbody>
                        </table></td>
                        <td><img height=22 alt="" src="images/tbl_haut_d.gif" width=5 border=0></td>
                      </tr>
                      <tr>
                        <td background=images/tbl_g.gif></td>
                        <td><table border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table class="wrapper" border="0" cellspacing="1" cellpadding="2">
                                            
                                  <tr>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardnumber"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardproductname"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardexpire"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.creditlimit"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardstatus"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.avabalance"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.osbalance"/></td>
                                    </tr>
                             <logic:iterate id ="commonObj" name ="OTHERCARDSINFO">
                                  <tr>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column1" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column2" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column3" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column4" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column5" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column6" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column7" /></td>
                                 </tr>
                            </logic:iterate>
 	                                                          
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
                        <td><img height=5 alt=""
                        src="images/tbl_bas_g.gif" width=5 border=0></td>
                        <td background=images/tbl_bas.gif></td>
                        <td><img height=5 alt=""
                        src="images/tbl_bas_d.gif" width=5      border=0></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
    </logic:present>  
  
   <!-- for suplementary cards to display -->
   
     <logic:present name="SUPPLYCARDSINFO" scope="request">         
                <tr>
                  <td background="images/tbl_g.gif" height="136"></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;" height="136">
                    <table cellspacing=0 cellpadding=0 border=0>
                      <tbody>
                       <tr>
                        <td><img height=22 alt=""src="images/form_tab_start_on.gif" width=5 border=0></td>
                        <td background=images/tbl_haut.gif>
                          <table cellspacing=0 cellpadding=0 border=0>
                            <tbody> 
                            <tr> 
                              <td class=form_tab_off style="PADDING-LEFT: 15px" noWrap background=images/form_tab_bg_on.gif> 
                                <bean:message key ="customerrelation.supplycards"/> </td>
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
                              <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table class="wrapper" border="0" cellspacing="1" cellpadding="2">
                     
                             <tr>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardnumber"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardproductname"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardexpire"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.creditlimit"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.cardstatus"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.avabalance"/></td>
                                    <td class="title" nowrap><bean:message key ="customerrelation.osbalance"/></td>
                                    </tr>
                          <logic:iterate id ="commonObj" name ="SUPPLYCARDSINFO">
                                  <tr>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column1" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column2" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column3" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column4" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column5" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column6" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="commonObj"  property="column7" /></td>
                                 </tr>
                            </logic:iterate>
 	                                  
                              </table></td>
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
                        src="images/tbl_bas_d.gif" width=5                border=0></td>
                      </tr>
                      </tbody>
                    </table>                  </td>
                  <td background="images/tbl_d.gif" height="136"></td>
                </tr>
        </logic:present> 
                <tr>
                  <td> <img width="5" height="5" border="0" src="images/tbl_bas_g.gif" alt=""></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td background="images/tbl_bas.gif"></td>
                  <td> <img width="5" height="5" border="0" src="images/tbl_bas_d.gif" alt=""></td>
                </tr>	
              </table>            </td>
          </tr>
        </table>      </td>
  </tr>
</table>
</html:form>
</body>
</html:html>