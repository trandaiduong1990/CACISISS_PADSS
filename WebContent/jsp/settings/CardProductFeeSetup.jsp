<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="cardproductfeesetup.screentitile"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/cacis.js"></script>
<script src="inc/js/setup.js"></script>
<script language="JavaScript" src="inc/js/decimalNo.js"></script>
<script language="JavaScript" src="inc/js/CardProcudtFeeSetup.js"></script>

<script language="JavaScript">
   function go(action) {
	   document.forms[0].method.value=action;
	   //alert('1');
       if(action != 'addNew'){
	       if(onFormSub(document.forms[0])){
			   if(dateValidationOnFormSubmit(document.cardProductFeeSetupForm.startDate, document.cardProductFeeSetupForm.endDate)){
			    //alert('2');
			   	document.forms[0].submit();
		   		}
		   }
		}else{
		    document.forms[0].submit();
		}
       }
     
    function showList(){
          document.forms[0].action= "cardproductfeelist.do?method=List";
          document.forms[0].submit();
       }
</script>
</head>
<body bgcolor="ffffff">
  <html:form action="cardproductfeeprocess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <html:hidden property="id"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="cardproductfeesetup.screentitile"/></td>
            <td class="titreSection" width="455">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td colspan="2" class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
              <tr>
                <td><html:button property="submitbutton" onclick="go('addNew')"><bean:message key ="common.addnew"/></html:button><br></td>
                </tr>
            </table>
            <td class="texteMenuGauche"><div align="right">&nbsp;<a href='javascript:helpLink("WRITEOFF")'>Help</a></div>
            </td>
          </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
     
        <tr>
          <td valign="top" class="ErrFONT"><table width="100%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
            </tr>
          </table></td>
        </tr>
  <tr> 
      <td height="369" valign="top"><br>
      <table>
        <tr> 
            <td height="166" valign="top"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
                          <bean:message key ="cardproductfee.group1"/> </td>
                        <td><img src="images/form_tab_end_on.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
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
                      <td style="padding: 20px 20px 10px 20px;" valign="top"><table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111">
                        
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.cardproductname"/> * </td>
                          <td>
                          <% if((request.getAttribute("ACTION") != null) && (((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))) {%>
                          <html:select property="cardProductId" disabled="true">
                             <html:option value=" "> </html:option>	
                             <html:optionsCollection property="cardProductList" value="key" label="value" />
                          </html:select>
  						  <html:hidden property="cardProductId"/>
                          <% }else{ %>
                          <html:select property="cardProductId">
                             <html:option value=" "> </html:option>	
                             <html:optionsCollection property="cardProductList" value="key" label="value" />
                          </html:select>   
                          <% } %>                  
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.annualfeeforprimarycard"/> * </td>
                          <td><html:text property="annualFeePrimary" size="10" maxlength ="6" onblur="javascript:isValidDecimal(this, 3, 2)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.annualfeeforsecondarycard"/> * </td>
                          <td><html:text property="annualFeeSecondary" size="10" maxlength ="6" onblur="javascript:isValidDecimal(this, 3, 2)" /></td>
                        </tr>
                         <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.issuefee"/> * </td>
                          <td><html:text property="issuerFee" size="10" maxlength ="6" onblur="javascript:isValidDecimal(this, 3, 2)" /></td>
                        </tr>
                           <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.cardreplacementfee"/> * </td>
                          <td><html:text property="replacementFee" size="10" maxlength ="6" onblur="javascript:isValidDecimal(this, 3, 2)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.cardcancellationfee"/> * </td>
                          <td><html:text property="cancelationFee" size="10" maxlength ="6" onblur="javascript:isValidDecimal(this, 3, 2)" /></td>
                        </tr>
                        
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.currencyconversionfee"/> * </td>
                          <td><html:text property="currConversionFee" size="10" maxlength ="6" onblur="javascript:isValidDecimal(this, 3, 2)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.administrativefee"/> * </td>
                          <td><html:text property="adminstrationFee" size="10" maxlength ="6" onblur="javascript:isValidDecimal(this, 3, 2)" /></td>
                        </tr>
                        
                        
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.joinfeeprimary"/> * </td>
                          <td><html:text property="joinFeePrimary" size="10" maxlength ="10" onblur="javascript:validInteger(this)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.joinfeesecondary"/> * </td>
                          <td><html:text property="joinFeeSecondary" size="10" maxlength ="10" onblur="javascript:validInteger(this)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.creditadjfeeprimary"/> * </td>
                          <td><html:text property="creditAdjFeePrimary" size="10" maxlength ="10" onblur="javascript:validInteger(this)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.creditadjfeesecondery"/> * </td>
                          <td><html:text property="creditAdjFeeSecondary" size="10" maxlength ="10" onblur="javascript:validInteger(this)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.urgentfeeprimary"/> * </td>
                          <td><html:text property="urgentFeePrimary" size="10" maxlength ="10" onblur="javascript:validInteger(this)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.urgentfeesecondary"/> * </td>
                          <td><html:text property="urgentFeeSecondary" size="10" maxlength ="10" onblur="javascript:validInteger(this)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.repinfeeprimary"/> * </td>
                          <td><html:text property="repinFeePrimary" size="10" maxlength ="10" onblur="javascript:validInteger(this)" /></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductfeesetup.repinfeesecondary"/> * </td>
                          <td><html:text property="repinFeeSecondary" size="10" maxlength ="10" onblur="javascript:validInteger(this)" /></td>
                        </tr>
                        
                        
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductratesetup.ratestartdate"/> * </td>
                          <td><html:text property="startDate" size="10" onblur="javascript:isValidDate(document.cardProductFeeSetupForm.startDate)"/></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardproductratesetup.rateenddate"/> * </td>
                          <td><html:text property="endDate" size="10" onblur="javascript:isValidDate(document.cardProductFeeSetupForm.endDate)"/></td>
                        </tr>
                      </table></td>
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