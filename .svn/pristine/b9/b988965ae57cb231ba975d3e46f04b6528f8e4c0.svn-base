<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="cardproductsetup.screentitile"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/decimalNo.js"></script>
<script language="JavaScript" src="inc/js/CardProcudtSetup.js"></script>

<script language="JavaScript">
   function go(action) {
       document.forms[0].method.value=action;
       if(action != 'addNew'){
           //if(onFormSub(document.forms[0])){
          		document.forms[0].submit();
          //}
       }else{
           document.forms[0].submit();
       }
    }
     
    function showList(){
          document.forms[0].action= "cardproductlist.do?method=List";
          document.forms[0].submit();
       }
</script>
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<body bgcolor="ffffff">
  <html:form action="cardproductprocess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="cardproductsetup.screentitile"/></td>
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
                         <bean:message key ="cardproductsetup.screentitile"/>                       </td>
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
       						<html:button property="submitbutton" onclick="deleteAction('delete')"><bean:message key ="common.delete"/></html:button>
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardproductsetup.group1"/> </td>
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
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.cardtype"/> * </td>
                                <td><html:select property="cardType.cardTypeId" >
					         		  <html:option value=""></html:option>	
                                      <html:optionsCollection property="cardTypeList" value="key" label="value" />
	                                </html:select>
	                             </td>
                              </tr>
                              <tr>
                                <td></td>
                                <td></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.cardproducttypeid"/> * </td>
                                <td><html:select property="cardProductType.cardProductTypeId" >
					         		    <html:option value=""></html:option>	
                                        <html:optionsCollection property="cardProductTypeList" value="key" label="value" />
	                               </html:select>      
	                              </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.cardproductid"/> * </td>
                                <td>
                                	<logic:present scope="request" name="ACTION">
				                    	<logic:equal name="ACTION" value="change">
				                    		<html:hidden property="cardProductId" name="cardProductSetupForm" />
						      	   			<bean:write name="cardProductSetupForm" property="cardProductId"/>
				                    	</logic:equal>
				                    	<logic:equal name="ACTION" value="update">
				                    		<html:hidden property="cardProductId" name="cardProductSetupForm" />
						      	   			<bean:write name="cardProductSetupForm" property="cardProductId"/>
				                    	</logic:equal>
				                    	<logic:equal name="ACTION" value="add">
                                			<html:text property="cardProductId" maxlength ="3" size="10" />
				                    	</logic:equal>
				                    </logic:present>
				                    <logic:present scope="request" name="ACTION">
				                    	<logic:equal name="ACTION" value="cancel">
				                    		<html:hidden property="cardProductId" name="cardProductSetupForm" />
						      	   			<bean:write name="cardProductSetupForm" property="cardProductId"/>
				                    	</logic:equal>
					                </logic:present>
					                <logic:notPresent scope="request" name="ACTION">
					                	<html:text property="cardProductId" maxlength ="3" size="10" />
				                    </logic:notPresent>
                                </td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.cardproductname"/> * </td>
                                <td><html:text property="cardProductName"  maxlength ="30" size="30" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.binlength"/> * </td>
                                <td><html:text property="binLength" maxlength ="1" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.bin"/> * </td>
                                <td><html:text property="bin" size="10" maxlength="6" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.servicecode"/> * </td>
                                <td><html:text property="serviceCode" maxlength ="3" size="10" /></td>
                              </tr>
                              <!-- 
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.expiretime"/></td>
                                <td><html:text property="expireTime" maxlength ="2" size="10" /></td>
                              </tr>
                               -->
                               <html:hidden property="floorLimit" value="0"/>
                              <!-- 
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.floorLimit"/></td>
                                <td><html:text property="floorLimit" maxlength ="16" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.duedays"/></td>
                                <td><html:text property="dueDays" maxlength ="2" size="10" /></td>
                              </tr>
							  -->
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardproductsetup.group2"/></td>
                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                  </tr>
                                </tbody>
                            </table></td>
                          </tr>
                          <tr>
                            <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td><table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.pinlength"/> * </td>
                                <td><html:text property="pinLength" maxlength ="2" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.cardNolength"/> * </td>
                                <td><html:text property="cardNoLength" maxlength ="2" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.embossNamelength"/> * </td>
                                <td><html:text property="embossNameLength" maxlength ="2" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.firstIssueValidity"/> * </td>
                                <td><html:text property="firstIssueValidity" maxlength ="2" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.renewalIssueValidity"/> * </td>
                                <td><html:text property="renewalIssueValidity" maxlength ="2" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.maxPintry"/> * </td>
                                <td><html:text property="maxPinRetry" maxlength ="1" size="10" /></td>
                              </tr>
                              <tr>
                                <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.cardNextValue"/> * </td>
                                <td>
                                <% if((request.getAttribute("ACTION") != null) && (((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))){ %>
                                	<html:text property="cardNoNextValue" readonly="true" maxlength ="3" size="10" />
                                <% }else{ %>
                                	<html:text property="cardNoNextValue" maxlength ="3" size="10" />
                                <% } %>
                                </td>
                              </tr>
                            </table></td>
                          </tr>
                        </tbody>
                      </table></td>
                    </tr>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
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
                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardproductsetup.group3"/> </td>
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
									<td>
										<logic:equal name="cardProductSetupForm" property="pinRequired" value="Y">
				                    		<input type="checkbox" value="Y" name ="pinRequired" checked="checked"/>
				                    	</logic:equal>
				                    	<logic:notEqual name="cardProductSetupForm" property="pinRequired" value="Y">
				                    		<input type="checkbox" value="Y" name ="pinRequired"/>
				                    	</logic:notEqual>
									</td>
									<td nowrap class="desc_cell"><bean:message key ="cardproductsetup.pinRequired"/></td>
								</tr>
							</table>
						</td>
                          </tr>
                          <tr>
                          	<td>
								<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
									<tr>
			                            <td>
			                            	<logic:equal name="cardProductSetupForm" property="renewalCardActive" value="Y">
					                    		<input type="checkbox" value="Y" name ="renewalCardActive" checked="checked"/>
					                    	</logic:equal>
					                    	<logic:notEqual name="cardProductSetupForm" property="renewalCardActive" value="Y">
					                    		<input type="checkbox" value="Y" name ="renewalCardActive"/>
					                    	</logic:notEqual>
			                            </td>
			                            <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.renewalCardActive"/></td>
									</tr>
								</table>
							</td>
                          </tr>
                          <tr>
                          	<td>
								<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
									<tr>
			                            <td>
			                            	<logic:equal name="cardProductSetupForm" property="accountCreation" value="Y">
					                    		<input type="checkbox" value="Y" name ="accountCreation" checked="checked"/>
					                    	</logic:equal>
					                    	<logic:notEqual name="cardProductSetupForm" property="accountCreation" value="Y">
					                    		<input type="checkbox" value="Y" name ="accountCreation"/>
					                    	</logic:notEqual>
			                            </td>
			                            <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.accountCreation"/></td>
									</tr>
								</table>
							</td>
                          </tr>
                          <tr>
                          	<td>
								<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
									<tr>
			                            <td>
			                            	<logic:equal name="cardProductSetupForm" property="pinMailerRequired" value="Y">
					                    		<input type="checkbox" value="Y" name ="pinMailerRequired" checked="checked"/>
					                    	</logic:equal>
					                    	<logic:notEqual name="cardProductSetupForm" property="pinMailerRequired" value="Y">
					                    		<input type="checkbox" value="Y" name ="pinMailerRequired"/>
					                    	</logic:notEqual>
			                            </td>
			                            <td nowrap class="desc_cell"><bean:message key ="cardproductsetup.pinMAilerRequired"/></td>
									</tr>
								</table>
							</td>
                          </tr>
                          <tr>
                          	<td>
							<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
								<tr>
                            <td nowrap class="desc_cell" style="width: 170px;"><bean:message key ="cardproductsetup.cardActivationOn"/></td>
                            <td style="font-family: verdana;font-size: 11px;">
                            	<div style="float: left;width: 120px;">
	                            	<html:radio property="cardActivationOn" value="D" /> 
									<bean:message key="cardproductsetup.cardActivationOnDeliver" />
								</div>
								<div style="float: left">
									<html:radio property="cardActivationOn" value="T" /> 
									<bean:message key="cardproductsetup.cardActivationOnTerminal" />
								</div>
							</td>
								</tr>
							</table>
							</td>
                          </tr>
                          <tr>
                          	<td>
							<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
								<tr>
                            <td nowrap class="desc_cell" style="width: 170px;"><bean:message key ="cardproductsetup.cardreplacementnumber"/></td>
                            <td style="font-family: verdana;font-size: 11px;">
                            	<div style="float: left;width: 120px;">
	                            	<html:radio property="newOrSameCardnumberForReplacement" value="N" /> 
									<bean:message key="cardproductsetup.cardreplacementnumbernew" />
								</div>
								<div style="float: left;">
									<html:radio property="newOrSameCardnumberForReplacement" value="S" /> 
									<bean:message key="cardproductsetup.cardreplacementnumbersame" />
								</div>
							</td>
								</tr>
							</table>
							</td>
                          </tr>
                          <tr>
                          	<td>
							<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
								<tr>
                            <td nowrap class="desc_cell" style="width: 170px;"><bean:message key ="cardproductsetup.branchidinclude"/></td>
                            <td style="font-family: verdana;font-size: 11px;">
                            	<div style="float: left;width: 120px;">
	                            	<html:radio property="branchIdInclude" value="Y" /> 
									<bean:message key="cardproductsetup.branchidincludeyes" />
								</div>
								<div style="float: left;">
									<html:radio property="branchIdInclude" value="N" /> 
									<bean:message key="cardproductsetup.branchidincludeno" />
								</div>
							</td>
								</tr>
							</table>
							</td>
                          </tr>
                          
                          <tr>
                          	<td>
							<table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0>
								<tr>
                            <td nowrap class="desc_cell" style="width: 170px;"><bean:message key ="cardproductsetup.ecomenable"/></td>
                            <td style="font-family: verdana;font-size: 11px;">
                            	<div style="float: left;width: 120px;">
	                            	<html:radio property="eComEnable" value="Y" /> 
									<bean:message key="cardproductsetup.branchidincludeyes" />
								</div>
								<div style="float: left;">
									<html:radio property="eComEnable" value="N" /> 
									<bean:message key="cardproductsetup.branchidincludeno" />
								</div>
							</td>
								</tr>
							</table>
							</td>
                          </tr>
                        </tbody>
                      </table>
                  </td>
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
       						<html:button property="submitbutton" onclick="deleteAction('delete')"><bean:message key ="common.delete"/></html:button>
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