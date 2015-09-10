<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<html:html >
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>

<script src="inc/js/creditsplit.js"></script>
   
<body bgcolor="ffffff">
  <html:errors/>
<html:form action="creditsplitprocess.do">
<input type="hidden" name="method"/>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>&nbsp;</td>
      <td valign="top">
        <table width="100%" height="230">
    
      <!-- this is only for Csr(CreditSplit Screen) to chek for cardHolderType, if not a primarycardholder to disply error only -->
        <logic:notPresent name ="CARDHODERTYPECHECK">
        
          <tr>
            <td valign="top" height="323">
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td> <img src="images/form_tab_start_off.gif" border="0" alt="" width="5" height="22"></td>
                  <td colspan="2" background="images/tbl_haut.gif"><table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                    <td><b><bean:message key ="customerservice.creditsplit"/></b></td>
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
                       <tr>
                        <td><img height=22 alt=""src="images/form_tab_start_on.gif" width=5 border=0></td>
                        <td background=images/tbl_haut.gif>
                          <table cellspacing=0 cellpadding=0 border=0>
                            <tbody> 
                            <tr> 
                              <td class=form_tab_off style="PADDING-LEFT: 15px" noWrap background=images/form_tab_bg_on.gif> 
                                <bean:message key ="customerservice.creditsplit"/> </td>
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
                                    <td class="title" nowrap><bean:message key ="customerservice.cardnumber"/></td>
                                    <td class="title" nowrap><bean:message key ="customerservice.cardtype"/></td>
                                    <td class="title" nowrap><bean:message key ="customerservice.cardstatus"/></td>
                                    <td class="title" nowrap><bean:message key ="customerservice.customername"/></td>
                                   
                                    <td class="title" nowrap><bean:message key ="customerservice.currentcreditlimit"/></td>
                                    <td class="title" nowrap><bean:message key ="customerservice.currentlimitratio"/></td>
                                     <td class="title" nowrap><bean:message key ="customerservice.newlimit"/></td>
                                     <td class="title" nowrap><bean:message key ="customerservice.newlimitratio"/></td>
                                     
                                    <td class="title" nowrap><bean:message key ="customerservice.currentcashlimit"/></td>
                                    <td class="title" nowrap><bean:message key ="customerservice.currentcashlimitpercent"/></td>
                                    <td class="title" nowrap><bean:message key ="customerservice.newcashlimit"/></td>
                                    <td class="title" nowrap><bean:message key ="customerservice.newcashlimitpercent"/></td>
                                   
                                    </tr>
                                   <!-- total number of cards size stroing here to display all cards -->
                                  <bean:define id="splitSize" name="creditSplitForm" property="cardsSize"/>
                                   <%
                                      int intSize =Integer.valueOf((String)splitSize).intValue();
                                       for (int intIndex = 0 ; intIndex <intSize; intIndex++){
                                  %>
                                  <tr>
                                    <td class="DataTD" id="firstcolumn" nowrap><bean:write name ="creditSplitForm"  property="<%="limitForm" + intIndex + ".cardNumber" %>" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="creditSplitForm"  property="<%="limitForm" + intIndex + ".cardHolderType" %>" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="creditSplitForm"  property="<%="limitForm" + intIndex + ".cardStatus" %>" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="creditSplitForm"  property="<%="limitForm" + intIndex + ".customerName" %>" /></td>
                                 
                                    <td class="DataTD" nowrap><bean:write name ="creditSplitForm"  property="<%="limitForm" + intIndex + ".currentLimit" %>" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="creditSplitForm"  property="<%="limitForm" + intIndex + ".currentRatio" %>" /></td>
                                    <td class="DataTD" nowrap><html:text  property="<%="limitForm" + intIndex + ".newLimit" %>"  size="10"  onblur="<%="limitToRatio('" + "limitForm" + intIndex + ".newLimit" + "','" + "limitForm" + intIndex + ".newLimitRatio" + "')" %>"/></td>
                                    <td class="DataTD" nowrap><html:text  property="<%="limitForm" + intIndex + ".newLimitRatio" %>"  size="10"  onblur="<%="ratioToLimit('" + "limitForm" + intIndex + ".newLimitRatio" + "','" + "limitForm" + intIndex + ".newLimit" + "')" %>"/></td>
                                 <!-- checking cardholder type if primry cardholder then only show cashlimit things -->  
                                <logic:match name ="creditSplitForm" property ="<%="limitForm" + intIndex + ".cardHolderType" %>" value = "PRIMARY">
                                    <td class="DataTD" nowrap><bean:write name ="creditSplitForm"  property="<%="limitForm" + intIndex + ".currentCashLimit" %>" /></td>
                                    <td class="DataTD" nowrap><bean:write name ="creditSplitForm"  property="<%="limitForm" + intIndex + ".currentCashLimitRatio" %>" /></td>
                                    <td class="DataTD" nowrap><html:text  property="<%="limitForm" + intIndex + ".newCashLimit" %>"  size="10"   onblur="<%="cashLimitToRatio('" + "limitForm" + intIndex + ".newCashLimit" + "','" + "limitForm" + intIndex + ".newCashLimitRatio" + "')" %>"/></td>
                                    <td class="DataTD" nowrap><html:text  property="<%="limitForm" + intIndex + ".newCashLimitRatio" %>"  size="10"  onblur="<%="cashRatioToLimit('" + "limitForm" + intIndex + ".newCashLimitRatio" + "','" + "limitForm" + intIndex + ".newCashLimit" + "')" %>"/></td>
                              </logic:match>
                                    <html:hidden  property="<%="limitForm" + intIndex + ".cardNumber" %>" />
                                </tr>
                              <% } %>
                              <tr><td>             </td></tr>
                              <tr><td> 
                                <html:hidden  property="creditLimit" />
                                <html:hidden  property="cashLimit" />
                               <html:button property ="submit1" onclick= "checkTotalRatio('creditSplitUpdate')"><bean:message key ="common.save"/></html:button>
                           
                            <!-- to display the cacel button for customerservice Creditsplit not for csrCreditsplit -->
                           <logic:present name ="ACTION">
                               <html:link action="creditsplitpage?method=checkSession"><bean:message key ="common.cancel"/></html:link>                         
                           </logic:present>
 	                       
 	                       </td></tr> 
 	                  <tr><td>
 	                   </td></tr>
 	     </logic:notPresent>  
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