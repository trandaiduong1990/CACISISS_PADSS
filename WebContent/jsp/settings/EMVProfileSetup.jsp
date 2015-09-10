<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="cgf.screentitile.setup"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/decimalNo.js"></script>
<script language="JavaScript" src="inc/js/CustomerGroupFeeSetup.js"></script>

<script language="JavaScript">
   function go(action) {
       document.forms[0].method.value=action;
       document.forms[0].submit();
   }
     
   function showList(){
		document.forms[0].action= "emvprofilelist.do?method=List";
		document.forms[0].submit();
   }
   
   function dropboxChange() {
	   var e = document.getElementById("applType");
	   var strBox = e.options[e.selectedIndex].value;
	   var screen = document.forms[0].screen.value;
	   go(screen);
   }
   
   function enableField() {
	   var offAllowed = document.emvProfileSetupForm.offlineAllowed;
	   var offMaxPin = document.emvProfileSetupForm.offlineMaxPin;
	   var offFloor = document.emvProfileSetupForm.offlineFloorLimit;
	if (offAllowed.checked == true) {
		offMaxPin.readOnly = false;
		offFloor.readOnly = false;
	} else {
		offMaxPin.value = "";
		offFloor.value="";
		offMaxPin.readOnly = true;
		offFloor.readOnly = true;
	}
   }
</script>
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<body bgcolor="ffffff" onLoad="enableField()">
  <html:form action="emvprofileprocess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <html:hidden property="status"/>
  <html:hidden property="screen"/>
  
<%-- <bean:define id="pkId" name="customerGroupFeeSetupForm" property="id" />
<html:hidden property="id" value="<%=(String)pkId%>"/> --%>

  <table style="width:805px;" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> <td class="titreSection" width="504"><bean:message key ="emvprofile.titlesetup"/></td>
          <td class="titreSection" width="455">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td colspan="2" class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
              <tr>
               <td><html:button property="submitbutton"  onclick ="go('addNew')"><bean:message key ="common.addnew"/></html:button><br></td>
                </tr>
            </table>
            <td class="texteMenuGauche">
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
				<td valign="top"> 
					<table border="0" cellpadding="0" cellspacing="0" align="left">
					<tr> 
						<td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
						<td background="images/tbl_haut.gif" colspan="2"> 
							<table border="0" cellpadding="0" cellspacing="0">
								<tr> 
									<td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
									<bean:message key ="emvprofile.title"/>                        </td>
									<td><img src="images/form_tab_end_on.gif" width="25" height="22"></td>
								</tr>
							</table>                  
						</td>
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
       						<html:button property="submitbutton" onclick="deleteAction('delete')"><bean:message key ="common.delete"/></html:button>
       						<html:button property="submitbutton" onclick="go('print')"><bean:message key ="common.export"/></html:button>
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
						<td background="images/tbl_d.gif">
						</td>
					</tr>
					<tr> 
						<td background="images/tbl_g.gif"></td>
						<td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;">
							<table cellspacing=0 cellpadding=0 border=0 width="122">
								<tr><td></td></tr>
							<tr>
                        <td bgcolor=#dce5ea></td>
                      </tr>
                      <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                        	<tr>
                        		<td class="desc_cell" nowrap width="132"><bean:message key ="emvprofile.name"/> *</td>
                        		<td valign="top" width="242"><html:text property="emvProfileName" size="20" /></td>
                        	</tr>
                            <tr>
                              <td class="desc_cell" nowrap width="132"><bean:message key ="emvprofile.apptype"/> *</td>
                              <td valign="top" width="242" style="vertical-align: middle;">
							  	<html:select style="width:145px;" property="applType" styleId="applType" onchange="dropboxChange()">
							  	  <html:option value=""> </html:option>	
								  <html:optionsCollection property="applicationTypeList" value="key" label="value" />
								</html:select>
							  </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap width="132"><bean:message key ="emvprofile.issuerscript"/></td>
                              <td valign="top" width="242" style="vertical-align: middle;">
                              	<html:checkbox property ="scriptadviceSupported" value="Y" />
                              </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap width="132"><bean:message key ="emvprofile.cryver"/> *</td>
                              <td valign="top" width="242" style="vertical-align: middle;">
                              	<html:select style="width:145px;" property="applCryptogram">
                              	  <html:option value=""> </html:option>		
								  <html:optionsCollection property="applCryptogramList" value="key" label="value" />
								</html:select>
                              </td>
                              <td nowrap height="22">&nbsp;&nbsp;</td>
                              <td class="desc_cell" nowrap width="132"><bean:message key ="emvprofile.offalow"/></td>
                              <td valign="top" width="242" style="vertical-align: middle;">
                              	<html:checkbox property ="offlineAllowed" value="Y" styleId="offlineAllowed" onchange="enableField()" />
                              </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap width="132"><bean:message key ="emvprofile.issurauth"/></td>
                              <td valign="top" width="242" style="vertical-align: middle; font-family: verdana;font-size: 11px;">
                              	<html:radio property ="issuerAuthentication" value ="Y"><bean:message key ="applicationform.immediateprocess.yes"/></html:radio>
                                <html:radio property ="issuerAuthentication" value ="N"><bean:message key ="applicationform.immediateprocess.no"/></html:radio>
                              </td>
                              <td nowrap height="22">&nbsp;&nbsp;</td>
                              <td class="desc_cell" nowrap width="132"><bean:message key ="emvprofile.offpin"/></td>
                              <td valign="top" width="242" style="vertical-align: middle;">
                              	<html:text property="offlineMaxPin" size="20" maxlength="1" readonly="true"/>
                              </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap width="132"><bean:message key ="emvprofile.falback"/></td>
                              <td valign="top" width="242">
                              	<html:checkbox property ="fallbackAllowed" value="Y" />
                              </td>
                              <td nowrap height="22">&nbsp;&nbsp;</td>
                              <td class="desc_cell" nowrap width="132"><bean:message key ="emvprofile.offfloor"/></td>
                              <td valign="top" width="242">
                              	<html:text property="offlineFloorLimit" size="20" maxlength="5" readonly="true" />
                              </td>
                            </tr>
                        </table></td>
                      </tr>
                    </tbody>
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
        					<html:button property="submitbutton" onclick="deleteAction('delete')"><bean:message key ="common.delete"/></html:button>
        					<html:button property="submitbutton" onclick="go('print')"><bean:message key ="common.export"/></html:button>
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
</html:form>
</body>
</html:html>