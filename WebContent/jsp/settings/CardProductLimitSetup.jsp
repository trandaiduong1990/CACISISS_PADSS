<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="cardproductlimitsetup.screentitile"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/decimalNo.js"></script>
<script language="JavaScript" src="inc/js/CardProcudtLimitSetup.js"></script>

<script language="JavaScript">
   function go(action) {
       document.forms[0].method.value=action;
       if(action != 'addNew'){
           if(productLimitFormSub(document.forms[0])){
          		document.forms[0].submit();
          }
       }else{
           document.forms[0].submit();
       }
    }
     
    function showList(){
          document.forms[0].action= "cardproductlimitlist.do?method=List";
          document.forms[0].submit();
       }
</script>
</head>
<body bgcolor="ffffff">
  <html:form action="cardproductlimitprocess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="cardproductlimitsetup.screentitile"/></td>
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
                         <bean:message key ="cardproductlimitsetup.screentitile"/>                       </td>
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
       						<!--<html:button property="submitbutton" onclick="go('delete')"><bean:message key ="common.delete"/></html:button> -->
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardproductlimitsetup.group1"/> </td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                          <tr>
                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td height="59"><table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111">

                              <tr>
                                <td nowrap class="desc_cell" width="200"><bean:message key ="cardproductlimitsetup.cardproduct"/> * </td>
                                <td nowrap class="desc_cell">
	                                <logic:present scope="request" name="ACTION">
				                    	<logic:equal name="ACTION" value="change">
				                    		<html:hidden property="cardProductId" name="cardProductLimitSetupForm" />
						      	   			<bean:write name="cardProductLimitSetupForm" property="cardProduct.cardProductName"/>
				                    	</logic:equal>
				                    	<logic:equal name="ACTION" value="update">
				                    		<html:hidden property="cardProductId" name="cardProductLimitSetupForm" />
						      	   			<bean:write name="cardProductLimitSetupForm" property="cardProduct.cardProductName"/>
				                    	</logic:equal>
				                    	<logic:equal name="ACTION" value="add">
		                                	<html:select property="cardProductId" >
								         	  <html:option value=""> </html:option>	
		                                      <html:optionsCollection property="cardProductList" value="key" label="value" />
			                                </html:select>
				                    	</logic:equal>
				                    </logic:present>
				                    <logic:present scope="request" name="ACTION">
				                    	<logic:equal name="ACTION" value="cancel">
				                    		<html:hidden property="cardProductId" name="cardProductLimitSetupForm" />
						      	   			<bean:write name="cardProductLimitSetupForm" property="cardProduct.cardProductName"/>
				                    	</logic:equal>
					                </logic:present>
					                <logic:notPresent scope="request" name="ACTION">
	                                	<html:select property="cardProductId" >
							         	  <html:option value=""> </html:option>	
	                                      <html:optionsCollection property="cardProductList" value="key" label="value" />
		                                </html:select>
				                    </logic:notPresent>
	                             </td>
                              </tr>
                              <tr>
                                <td></td>
                                <td></td>
                              </tr>
                              <%--
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductlimitsetup.minSalary"/> * </td>
                                <td><html:text property="minSalary"  maxlength ="10" size="10" /></td>
                              </tr>
                               --%>
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxCardLimitPerSalary"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxCardLimitPerSalary" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxCardLimitPerSalaryInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxCashLimiPerCrl"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxCashLimiPerCrl" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxCashLimiPerCrlInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxSuppCreditLimit"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxSuppCreditLimit" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxSuppCreditLimitInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxSuppCashLimit"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxSuppCashLimit" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxSuppCashLimitInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.tempCreditLimitRaise"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="tempCreditLimitRaise" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="tempCreditLimitRaiseInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.tempCashLimitRaise"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="tempCashLimitRaise" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="tempCashLimitRaiseInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxecomamt"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="eComAmt" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="eComAmtInt" maxlength ="10" size="10" /></td>
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardproductlimitsetup.group2"/></td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                          <tr>
                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td>
                            <table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
                            
                              <tr>
                                <td nowrap class="desc_cell" colspan="2" width="300"><bean:message key ="cardproductlimitsetup.maxCreditAmtPerTrnx"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxCreditAmtPerTrnx" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxCreditAmtPerTrnxInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxCashLimitPerTrnx"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxCashLimitPerTrnx" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxCashLimitPerTrnxInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxCreditLimitPerDay"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxCreditLimitPerDay" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxCreditLimitPerDayInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxCreditTrnxPerDay"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxCreditTrnxPerDay" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxCreditTrnxPerDayInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxCashLimitPerDay"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxCashLimitPerDay" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxCashLimitPerDayInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxCashTrnxPerDay"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="maxCashTrnxPerDay" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="maxCashTrnxPerDayInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxecomamtperday"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="eComAmtPerDay" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="eComAmtPerDayInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                               <tr>
                               	<td style="height: 5px;"></td>
                               </tr>
                               
                              <tr>
                                <td nowrap class="desc_cell" colspan="2"><bean:message key ="cardproductlimitsetup.maxecomtranxperday"/> * </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.domestic"/></td>
                                <td><html:text property="eComTranxPerDay" maxlength ="10" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell" align="right" style="padding-right: 10px;"><bean:message key ="common.int"/></td>
                                <td><html:text property="eComTranxPerDayInt" maxlength ="10" size="10" /></td>
                              </tr>
                              
                            </table></td>
                          </tr>
                        </tbody>
                      </table></td>
                    </tr>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <logic:present name="cardProductLimitSetupForm" property="promotionsList" >
		          <bean:size id="size" name="cardProductLimitSetupForm" property="promotionsList"/>
		          	<logic:greaterThan name="size" value="0" >
		                <tr> 
		                  <td background="images/tbl_g.gif"></td>
		                  <td class="form_bgcolor" colspan="2" style="padding: 20px 20px 10px 20px;">
		                  		<table cellspacing=0 cellpadding=0 border=0>
		                        <tbody>
		                          <tr>
		                            <td><table cellspacing=0 cellpadding=0 border=0>
		                                <tbody>
		                                  <tr>
		                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
		                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardproductlimitsetup.group3"/> </td>
		                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
		                                  </tr>
		                                </tbody>
		                            </table></td>
		                          </tr>
		                          <tr>
		                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
		                          </tr>
		                          <tr>
		                          	<td>
										<table style="BORDER-COLLAPSE: collapse;width: 250px;" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
						                              <logic:iterate id="curpromotion" name ="cardProductLimitSetupForm" property="promotionsList">
						                                <tr>
						                                <td nowrap class="desc_cell">
						                                <html:multibox property="selectedPromotions">
						                                        <bean:write name="curpromotion" property ="promotionId"/>
						                                  </html:multibox>
						                                       <bean:write name="curpromotion" property ="promotionType"/>
						                                  </td>
						                                </tr>
						                              </logic:iterate>
										</table>
									</td>
		                          </tr>
		                        </tbody>
		                      </table>
		                  </td>
		                  <td background="images/tbl_d.gif"></td>
		                </tr>
                	</logic:greaterThan>
                </logic:present> 
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
       						<!--<html:button property="submitbutton" onclick="go('delete')"><bean:message key ="common.delete"/></html:button>-->
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