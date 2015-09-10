<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<html:html>
<head>
<title><bean:message key ="customerservice.ccprocess"/></title>
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
///to call csrDispatchAction to show all the data as popup window
function viewAllInfo(urlToCall){
  	var left = (screen.width/2)-(400);
  	var top = (screen.height/2)-(250);
  	window.open(urlToCall, "CustServiceAllData",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=500,width=800,location=no, top='+top+', left='+left);
  }
 ///to call different actions when click on cancel button
  function showList(url){
    document.forms[0].action = url;
    document.forms[0].submit();
   }

  function accRej(method) 
  {
    document.forms[0].method.value=method;
    var r=confirm("<bean:message key ='areyousure'/>");
    if (r==true)
    {
  	document.forms[0].submit();
    }
  }  
</script>
<body bgcolor="ffffff">
<html:form  action ="cardchangeprocess" >
<input type ="hidden" name ="method" />

<logic:present name="$CARDCHANGEPROCESS$">
  <!--  hiddeen fields to insert the data in varioues tables-->
<html:hidden property="cardNo"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
       
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="customerservice.ccprocess"/></td>
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
  <tr><td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td></tr>
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
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
						<bean:message key ="customerservice.cardinfo"/>                    </td>
                        <td><img src="images/form_tab_btw_on_off.gif" width="25" height="22"></td>
                        <td background="images/form_tab_bg_off.gif" class="form_tab_off"><a href="javascript:viewAllInfo('csrprocess.do?method=csrSummary&CustServCardNo=<bean:write name ="cardChangeProcessForm"  property="cardNo" />')"><bean:message key ="customerservice.viewtotalinformation"/></a></td>
                        <td><img src="images/form_tab_end_off.gif" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right"> 
                    <% if(request.getAttribute("ACTION") != null && ((String)request.getAttribute("ACTION")).equals("YES")) {%>
                         <html:button property="accept" onclick="accRej('accept')" > <bean:message key ="common.accept"/></html:button>
                         <html:button property="accept" onclick="accRej('reject')" > <bean:message key ="common.reject"/></html:button>
                      <%} %>
                         <html:button property="submitbutton" onclick="showList('cardchangeprocess.do?method=processSearch')"><bean:message key ="common.cancel"/></html:button>
                    </div>
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.personalinfo"/></td>
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
                        <td height="21">
                        	<table border="0" cellpadding="0" cellspacing="0" width="264">
                         	<tr> 
                              <td class="desc_cell" nowrap  height="18" ><bean:message key ="customerservice.customername"/></td>
                              <td class="label"><bean:write name ="cardChangeProcessForm"  property="customer.customerName" /></td>
                            </tr>
                         	<tr> 
                              <td class="desc_cell" nowrap  height="18" ><bean:message key ="customerservice.nricnumber"/></td>
                              <td class="label"><bean:write name ="cardChangeProcessForm"  property="customer.idNumber" /></td>
                            </tr>
                         	<tr> 
                              <td class="desc_cell" nowrap  height="18" ><bean:message key ="customerservice.nricexpiredate"/></td>
                              <td class="label"><bean:write name ="cardChangeProcessForm"  property="customer.expDate" /></td>
                            </tr>
                         	<tr> 
                              <td class="desc_cell" nowrap  height="18" ><bean:message key ="customerservice.dateofbirth"/></td>
                              <td class="label"><bean:write name ="cardChangeProcessForm"  property="customer.dob" /></td>
                            </tr>             
                        	</table>
                        </td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table width="264" 
                              border=0 cellpadding=0 cellspacing=0>
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.cardinfo"/></td>
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
                        <td><table border="0" cellpadding="0" cellspacing="0" width="264">
                            <tr> 
                              <td class="desc_cell" nowrap  height="18" ><bean:message key ="customerservice.cardnumber"/></td>
                              <td class="label"><bean:write name ="cardChangeProcessForm"  property="card.cardNumber" /></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap height="18" ><bean:message key ="customerservice.cardtype"/></td>
                              <td class="label"><bean:write name ="cardChangeProcessForm"  property="card.cardProductsDto.cardType.cardType" /></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap><bean:message key ="customerservice.cardproductname"/></td>
                              <td class="label"><bean:write name ="cardChangeProcessForm"  property="card.cardProductsDto.cardProductName" /></td>
                            </tr>
							<tr> 
                              <td class="desc_cell" nowrap ><bean:message key ="customerservice.cardstatus"/></td>
                              <td class="label"><bean:write name ="cardChangeProcessForm"  property="card.cardStatus" /></td>
                            </tr>
                           
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>   
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table width="335" 
                              border=0 cellpadding=0 cellspacing=0>
                    <tbody>
                      <tr>
                        <td width="335"><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="customerservice.ccproducts"/></td>
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
                        <td height="21">
                        <table border="0" cellpadding="0" cellspacing="0" width="264">
					       <tr> 
                               <td class="desc_cell" nowrap ><bean:message key ="customerservice.ccproduct"/></td>
                               <td class="label">
                               		<html:select  property = "changeProductID">
							   	       <html:optionsCollection property="productList" value="key" label="value" /> 	
							       </html:select>
                               </td>
                            </tr>
                            <tr> 
                               <td class="desc_cell" nowrap>
                               		<html:checkbox property="existCardStatus" value="C"></html:checkbox>
                               </td>
                               <td class="label">
                               		<bean:message key ="customerservice.ccexistcardstatus"/>
                               </td>
                            </tr>
                        </table>
                        </td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;">&nbsp;</td>
                  <td background="images/tbl_d.gif"></td>                                   
                </tr>                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right"> 
                   <% if(request.getAttribute("ACTION") != null && ((String)request.getAttribute("ACTION")).equals("YES")) {%>
                         <html:button property="accept" onclick="accRej('accept')" > <bean:message key ="common.accept"/></html:button>
                         <html:button property="accept" onclick="accRej('reject')" > <bean:message key ="common.reject"/></html:button>
                      <%} %>
                         <html:button property="submitbutton" onclick="showList('cardchangeprocess.do?method=processSearch')"><bean:message key ="common.cancel"/></html:button>
                    </div>
                  </td>
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
 </logic:present>   
</html:form>     
</body>
</html:html>        