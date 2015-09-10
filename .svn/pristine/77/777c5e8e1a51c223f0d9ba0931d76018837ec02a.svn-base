<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="cardlevellimit.title"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/cacis.js"></script>
<script src="inc/js/setup.js"></script>
<script language="JavaScript" src="inc/js/decimalNo.js"></script>
<script language="JavaScript" src="inc/js/CardLevelLimitSetup.js"></script>

<script language="JavaScript">
   function go(action) {
       document.forms[0].method.value=action;
       document.forms[0].submit();
    }
     
    function showList(){
          document.forms[0].action= "cardlevellimitlist.do?method=List";
          document.forms[0].submit();
       }
</script>
</head>
<body bgcolor="ffffff">
  <html:form action="cardlevellimitprocess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <html:hidden property="cardHolderType"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="cardlevellimit.title"/></td>
            <td class="titreSection" width="455">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td colspan="2" class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
              <tr>
                <td><br></td>
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
                          <bean:message key ="cardlevellimit.group1"/> </td>
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
        					<html:button property="submitbutton" onclick="go('update')" ><bean:message key ="common.save"/></html:button>
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
                          <td width="200"></td>
                          <td width="100"></td>
                          <td colspan="2"  align="right" nowrap class="desc_cell" style="padding-left: 10px;""><bean:message key ="cardlevellimit.productlimitheader"/></td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardlevellimit.cardno"/></td>
                          <td style="font-size: 11px;font-family: verdana;">
                    		<html:hidden property="cardNo" name="cardLevelLimitSetupForm" />
		      	   			<bean:write name="cardLevelLimitSetupForm" property="cardNo"/>                    
                           </td>
                           <td></td>
                        </tr>
                        <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell"><bean:message key ="cardlevellimit.status"/></td>
                          <td style="font-size: 11px;font-family: verdana;">
                    		<html:select property="status" >
                        	 <html:option value=""></html:option>
                             <html:optionsCollection property="statusList" value="key" label="value" />
                            </html:select>                    
                           </td>
                           <td></td>
                        </tr>
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.cashlimit"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="cashLimit" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<logic:equal value="1" name="cardLevelLimitSetupForm" property="cardHolderType">
                           		<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCashLimiPerCrl"/>
                           	</logic:equal>
                    		<logic:equal value="2" name="cardLevelLimitSetupForm" property="cardHolderType">
                           		<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxSuppCashLimit"/>
                           	</logic:equal>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="cashLimitInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<logic:equal value="1" name="cardLevelLimitSetupForm" property="cardHolderType">
                           		<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCashLimiPerCrlInt"/>
                           	</logic:equal>
                    		<logic:equal value="2" name="cardLevelLimitSetupForm" property="cardHolderType">
                           		<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxSuppCashLimitInt"/>
                           	</logic:equal>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.purchaselimit"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="purchaseLimit" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<logic:equal value="1" name="cardLevelLimitSetupForm" property="cardHolderType">
                           		<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCardLimitPerSalary"/>
                           	</logic:equal>
                    		<logic:equal value="2" name="cardLevelLimitSetupForm" property="cardHolderType">
                           		<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxSuppCreditLimit"/>
                           	</logic:equal>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="purchaseLimitInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<logic:equal value="1" name="cardLevelLimitSetupForm" property="cardHolderType">
                           		<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCardLimitPerSalaryInt"/>
                           	</logic:equal>
                    		<logic:equal value="2" name="cardLevelLimitSetupForm" property="cardHolderType">
                           		<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxSuppCreditLimitInt"/>
                           	</logic:equal>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.amtpertranx"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="amtPerTranx" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCreditAmtPerTrnx"/>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="amtPerTranxInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCreditAmtPerTrnxInt"/>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.cashpertranx"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="cashPerTranx" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCashLimitPerTrnx"/>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="cashPerTranxInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCashLimitPerTrnxInt"/>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.dailylimitcount"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="dailyLimitCount" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCreditTrnxPerDay"/>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="dailyLimitCountInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCreditTrnxPerDayInt"/>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.dailylimitamt"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="dailyLimitAmt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCreditLimitPerDay"/>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="dailyLimitAmtInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCreditLimitPerDayInt"/>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.dailycashcount"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="dailyCashCount" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCashTrnxPerDay"/>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="dailyCashCountInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCashTrnxPerDayInt"/>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.dailycashamt"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="dailyCashAmt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCashLimitPerDay"/>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="dailyCashAmtInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.maxCashLimitPerDayInt"/>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.ecomamt"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="eComAmt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.eComAmt"/>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="eComAmtInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.eComAmtInt"/>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.ecomamtperday"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="eComAmtPerDay" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.eComAmtPerDay"/>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="eComAmtPerDayInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.eComAmtPerDayInt"/>
                           </td>
                        </tr>
                        
                        
                       <tr>
                       	<td style="height: 5px;"></td>
                       </tr>
                        <tr>
                          <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardlevellimit.ecomtranxperday"/> * </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                          <td><html:text property="eComTranxPerDay" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.eComTranxPerDay"/>
                           </td>
                        </tr>
                        <tr>
                          <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                          <td><html:text property="eComTranxPerDayInt" maxlength ="10" size="10" /></td>
                          <td style="padding-left: 10px;font-size: 11px;font-family: verdana;">
                           	<bean:write name="cardLevelLimitSetupForm" property="objCardProductLimitSetupForm.eComTranxPerDayInt"/>
                           </td>
                        </tr>
                        
                      </table>
                      </td>
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
        					<html:button property="submitbutton" onclick="go('update')" ><bean:message key ="common.save"/></html:button>
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