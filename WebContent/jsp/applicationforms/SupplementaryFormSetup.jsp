<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title><bean:message key ="supplementaryformsetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/DWRUtils.js'></script>
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/decimalNo.js"></script>
<script language="JavaScript" src="inc/js/SupplementaryFormSetup.js"></script>
</head>
<script>

function accRej(method) 
{
  document.forms[0].method.value=method;
  if(onFormSub(document.forms[0])){
	  var r=confirm("<bean:message key ='areyousure'/>");
	  if (r==true)
	  {
		document.forms[0].submit();
	  }
  }
}

function go(action) 
{
  document.forms[0].method.value=action;
  if(action != 'addNew'){
      if(onFormSub(document.forms[0])){
     		document.forms[0].submit();
     }
  }else{
      document.forms[0].submit();
  }	
}
 function showList(){
          document.forms[0].action= "supplementaryformlistpage.do";
          document.forms[0].submit();
       }

 function update() {
		
	  var name = dwr.util.getValue("principleChCardNo");
	  //alert(name);
	  if(name != ''){
		//  alert(name);
		DWRUtils.validateSuppl(name, function(data) {
		//alert(data);
		var mySplitResult = data.split("#");
		var length = '('+mySplitResult[1]+')';
		//alert(length);
		//alert(mySplitResult[0]);
		    if(mySplitResult[0] == '1'){
		        dwr.util.setValue("demoReply", "<bean:message key ='alert.invalidcard'/>");
		    }else if(mySplitResult[0] == '2'){
		        dwr.util.setValue("demoReply", "<bean:message key ='alert.nonotavai'/>");
		    }else if(mySplitResult[0] == '3'){
		        dwr.util.setValue("demoReply", "<bean:message key ='alert.nonprimary'/>");
		    }else if(mySplitResult[0] == '4'){
		        //dwr.util.setValue("demoReply", "<bean:message key ='alert.noncreditcard'/>");
		    }else{
		    	dwr.util.setValue("demoReply", "");
		        dwr.util.setValue("demoReply1", length);
		    }
		  });
	  }
	}
   
</script>
<body onload="update()">
<html:form action="supplementaryformprocess.do">
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
<html:hidden property="applicationId"/>
<html:hidden property="cardProductId"/>
<input type="hidden" name="method"/>
 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0" width="100%" >
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="supplementaryformsetup.screentitle"/></td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td colspan="2" class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
              <tr>
                <td class="titreSection" width="600"><html:button property="submitbutton"  onclick ="go('addNew')"><bean:message key ="common.addnew"/></html:button></td>
                </tr>
            </table>
            <td class="texteMenuGauche"><div align="left"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div></td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
      </td>
      </tr>
   
  <tr><td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td></tr>
  <tr> 
      <td valign="top"><br>
      <table>
        <tr> 
          <td valign="top"> 
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr> 
                  <td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
						<bean:message key ="supplementaryformsetup.grouptitle1"/>                        </td>
                        <td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
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
        				    <logic:equal value="0" name="supplementaryForm" property="applStatus">
	        					<html:button property="update" onclick="accRej('update')"><bean:message key ="common.save"/></html:button>
	        					<html:button property="accept" onclick="accRej('accept')"><bean:message key ="common.accept"/></html:button>
	       						<html:button property="reject" onclick="accRej('reject')"><bean:message key ="common.reject"/></html:button>
       						</logic:equal>
       						<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				    <%}%>		
  					  
                    </div>                  
			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="3"><div id="demoReply" style="color: green;padding-left: 20px;"></div></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0 width="122">
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="supplementaryformsetup.grouptitle1"/></td>
                                <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                      </tr>
                      <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.principlesurname"/></b></td>
                              <td valign="top" width="242"><html:text property="principleChSurname" size="20"/></td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.principlename"/></b></td>
                              <td valign="top" width="242"><html:text property="principleChName" size="20"/></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.principlenric"/></b></td>
                              <td valign="top" width="242"><html:text property="principleNRIC" size="20"/></td>
                              <td nowrap height="22">&nbsp;&nbsp;</td>
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.principlecardnumber"/></b></td>
                              <td valign="top" width="242"><html:text property="principleChCardNo" onblur="update();" size="20"/></td>
                            </tr>
                        </table></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif" height="216"></td>
                  <td class="form_bgcolor" valign="top" style="padding: 20px 20px 10px 20px;" height="216"> 
                    <table cellspacing=0 cellpadding=0 
                              border=0 width="271">
                      <tbody> 
                      <tr> 
                        <td> 
                          <table cellspacing=0 cellpadding=0 border=0>
                            <tbody> 
                            <tr> 
                              <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                              <td class=group_title background=images/tab_fond.gif><bean:message key ="supplementaryformsetup.grouptitle2"/></td>
                              <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                            </tr>
                            </tbody> 
                          </table>
                        </td>
                      </tr>
                      <tr> 
                        <td bgcolor=#dce5ea> 
                          <div style="FONT-SIZE: 1px">&nbsp;</div>                        </td>
                      </tr>
                      <tr> 
                        <td height="21"> 
                          <table border="0" cellpadding="0" cellspacing="0" height="109">
                            <tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarysurname"/></b></td>
                              <td valign="top"><html:text property="surnameName" size="20"/></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementaryname"/></b></td>
                              <td valign="top"><html:text property="customerName" size="20"/></td>
                            </tr>
                             <tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarybranchid"/></b></td>
                              <td valign="top">
                              <html:select property="branchId" >
                                <html:option value=""></html:option>
                                <html:optionsCollection property="branchList" value="key" label="value" />
							  </html:select>
							  </td>
                             </tr>
                             <%--
                             <tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarycardproductid"/></b></td>
                              <td valign="top">
                              <html:select property="cardProductId" >
                                <html:option value=""></html:option>
                                <html:optionsCollection property="cardProductList" value="key" label="value" />
							  </html:select>
							  </td>
                             </tr>
                             --%>
                            <tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementaryembossingname"/></b></td>
                              <td valign="top">
                              	<DIV style="float: left;"><html:text property="embossingName" size="20"/></DIV>
                              	<div id="demoReply1" style="color: green;width: 20px;float: left;"></div>
                              </td>
                             </tr>
                            <tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarydob"/></b></td>
                              <td valign="top">
							    <html:select property="dobForm.day" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="dayList" value="key" label="value" />
								</html:select>
								<html:select property="dobForm.month" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="monthList" value="key" label="value" />
								</html:select>
								<html:select property="dobForm.year" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="yearList" value="key" label="value" />
								</html:select>
							  </td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap><b><bean:message key ="supplementaryformsetup.supplementaryplaceofbirth"/></b></td>
                              <td height="2"> <html:text property="pob" size="20" maxlength="100"/>
							   </td>
                            </tr>
                            <tr> 
							<tr> 
                              <td class="desc_cell" nowrap><b><bean:message key ="supplementaryformsetup.supplementarygender"/></b></td>
                              <td height="2"> 
							    <html:select property="gender" >
                                  <html:option value=""></html:option>
								  <html:optionsCollection property="genderList" value="key" label="value" />
								</html:select>
							   </td>
                            </tr>
							<tr>
						      <td class="desc_cell" nowrap><bean:message key ="supplementaryformsetup.nationality"/></td>
						      <td>
							    <html:select property="nationality" >
                                  <html:option value=""></html:option>
								  <html:optionsCollection property="countryList" value="key" label="value" />
								</html:select>
	   						  </td>
  							</tr>
							<tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarynric"/></b></td>
                              <td valign="top"><html:text property="idNumber" size="20"/></td>
                            </tr>
							<tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarynricissuedate"/></b></td>
                              <td valign="top">
							    <html:select property="idDateForm.day" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="dayList" value="key" label="value" />
								</html:select>
								<html:select property="idDateForm.month" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="monthList" value="key" label="value" />
								</html:select>
								<html:select property="idDateForm.year" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="yearList" value="key" label="value" />
								</html:select>
							  </td>
                            </tr>
							<tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarynricexpirydate"/></b></td>
                              <td valign="top">
							  	<html:select property="idExpireForm.day" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="dayList" value="key" label="value" />
								</html:select>
								<html:select property="idExpireForm.month" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="monthList" value="key" label="value" />
								</html:select>
								<html:select property="idExpireForm.year" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="expiryYearList" value="key" label="value" />
								</html:select>
							   </td>
                            </tr>
							<tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarynricissueplace"/></b></td>
                              <td valign="top"><html:text property="idPlace" size="20"/></td>
                            </tr>
							<tr> 
                              <td class="desc_cell" nowrap><bean:message key ="supplementaryformsetup.maritalstatus"/></td>
                              <td>
							  	<html:select property="maritalStatus" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="maritalStatusList" value="key" label="value" />
								</html:select>
							  </td>
                            </tr>

							<tr> 
                              <td class="desc_cell" nowrap><bean:message key ="supplementaryformsetup.relationship"/></td>
                              <td>
							  	<html:select property="relationship" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="relationshipList" value="key" label="value" />
								</html:select>
							  </td>
                            </tr>


							<tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementaryincome"/></b></td>
                              <td valign="top"><html:text property="income" size="20" maxlength="11" onblur="javascript:isValidDecimal(this, 8, 2)" /></td>
                            </tr> 
                          </table>                        
						</td>
                      </tr>
                      </tbody> 
                    </table>                  </td>
                  <td class="form_bgcolor" style="padding: 20px 20px 10px 20px;" valign="top" height="216"> 
                    <table cellspacing=0 cellpadding=0 
                              border=0 width="318">
                      <tbody> 
                      <tr> 
                        <td> 
                          <table cellspacing=0 cellpadding=0 border=0>
                            <tbody> 
                            <tr> 
                              <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                              <td class=group_title background=images/tab_fond.gif><bean:message key ="supplementaryformsetup.grouptitle3"/></td>
                              <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                            </tr>
                            </tbody> 
                          </table>
                        </td>
                      </tr>
                      <tr> 
                        <td bgcolor=#dce5ea> 
                          <div style="FONT-SIZE: 1px">&nbsp;</div>
                        </td>
                      </tr>
                      <tr> 
                        <td> 
                          <table border="0" cellpadding="0" cellspacing="0" width="322">
                            <tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementaryaddress1"/></b></td>
                              <td valign="top" width="242"><html:text property="addressForm.address1" size="20" maxlength="100"/></td>
                            </tr>
							<tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementaryaddress2"/></b></td>
                              <td valign="top" width="242"><html:text property="addressForm.address2" size="20" maxlength="100"/></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarypostalcode"/></b></td>
                              <td valign="top" width="242"><html:text property="addressForm.postalCode" size="20" maxlength="5"/></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarycity"/></b></td>
                              <td valign="top" width="242"><html:text property="addressForm.city" size="20" maxlength="20"/></td>
                            </tr>
							<tr> 
                              <td class="desc_cell" nowrap width="132"><b><bean:message key ="supplementaryformsetup.supplementarystate"/></b></td>
                              <td valign="top" width="242"><html:text property="addressForm.state" size="20" maxlength="20"/></td>
                            </tr>
                            <tr> 
                              <td class="desc_cell" nowrap width="72"><b><bean:message key ="supplementaryformsetup.supplementaryaddresscountry"/></b></td>
                              <td valign="top" width="250"> 
								<html:select property="addressForm.country" >
                                  <html:option value=""></html:option>
								  <html:optionsCollection property="countryList" value="key" label="value" />
								</html:select>
							   </td>
                            </tr>
							<tr>
							   <td class="desc_cell"><bean:message key ="supplementaryformsetup.supplementarytel"/></td>
							   <td><html:text property="addressForm.phone" size="20" maxlength="12"/></td>
                            </tr>
                            <tr> 
                                <td class="desc_cell"><bean:message key ="supplementaryformsetup.supplementaryfax"/></td>
                                <td><html:text property="addressForm.fax" size="20" maxlength="12"/></td>
							</tr>
							<tr>
								<td class="desc_cell" nowrap><bean:message key ="supplementaryformsetup.supplementaryemail"/></td>
								<td><html:text property="email" size="20"/></td>
							</tr>
							<tr>
								<td class="desc_cell" nowrap><bean:message key ="supplementaryformsetup.supplementaryremark"/></td>
                                <td><html:textarea property="remarks" rows="3"></html:textarea></td>
							</tr>
						  </table>
					   </td>
					 </tr>
					</tbody>
					</table>                  
				  </td>
                  <td background="images/tbl_d.gif" height="216"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor" colspan="2">&nbsp; </td>
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
        				    <logic:equal value="0" name="supplementaryForm" property="applStatus">
	        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
	        					<html:button property="submitbutton" onclick="go('accept')"><bean:message key ="common.accept"/></html:button>
	       						<html:button property="submitbutton" onclick="go('reject')"><bean:message key ="common.reject"/></html:button>
       						</logic:equal>
       						<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				    <%}%>		
  					  
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
</html:form>
</body>
</html:html>