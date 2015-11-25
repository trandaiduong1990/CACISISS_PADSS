<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title><bean:message key ="usersetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css"><script language="JavaScript" src="js/Checking.js"></script>
<script language="JavaScript" src="js/Transfer.js"></script>
<script language="JavaScript" src="js/IssuerUserSetup.js"></script>
<script src="inc/js/jquery-1.11.3.min.js"></script>
<script language="JavaScript">
function go(method) 
{
  document.forms[0].method.value=method;	
  document.forms[0].submit();
}

function showList()
{ 
 //document.forms[0].action="usersetuplistpage.do?userType=ISSADMIN&method=List";	
 document.forms[0].action="usersetuplistpage.do?method=List";	
 document.forms[0].submit();
}

function userTypeChange(event) {
	var userType = "";
	if(typeof event != 'undefined' && event != null && event != "") {
		userType = event.value;
	}
	$.ajax({
        type: "POST",
        url: "usersetupprocess.do?method=setRole",
        data: "userType=" + userType,
        success: function(response){
            // we have the response
           $('#roleIdDiv').html(response);
        },
        error: function(e){
            alert('Error: ' + e);
        }
    });
}

/* $(document).ready(function() {
	userTypeChange();
}); */
</script>
</head>

<body>
 <html:form action="usersetupprocess.do">
 <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
 <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>                                                  
 <%-- <html:hidden property="userType"/> --%>
 <input type=hidden name="method"/>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="600"><bean:message key ="usersetup.screentitle"/></td>
            <td class="titreSection" width="550">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
	<tr>
	  <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
	   <tr>
	    <td><b><html:button property="submitbutton" onclick="go('addNew')"><bean:message key ="common.addnew"/></html:button></b></td>	    	    
	   </tr>
	</table></td>
          <td class="texteMenuGauche"> 
              <p class="titreSection"></p>
          </td>                
              <td class="texteMenuGauche">&nbsp;<a href="#" target="">Help</a></td>                        
        </tr>	
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
      
        <br>
        <tr>
          <td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
        </tr>
      
  <tr>
      <td valign="top" height="481"><br>
        <table>
          <tr>
            <td valign="top" height="525">
              <table border="0" cellpadding="0" cellspacing="0" align="left">
                <tr>
                  <td width="5"> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2">
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px">
                          <bean:message key="usersetup.usersetupgrouptitle"/></td>
                        <td> <img height=22 alt="" src="images/form_tab_btw_on_off.gif" width=25 border=0></td>
                        <td background="images/form_tab_bg_off.gif" nowrap class="form_tab_off">
                        	<% String wtAction = (String)request.getAttribute("ACTION"); %>
                        		
                       		<%if(wtAction != null && ("addNew".equals(wtAction) || "add".equals(wtAction))){%>
                       			<html:link href="#"><bean:message key ="usersetup.activation"/></html:link>
                       		<%}else{%>
                       			<html:link action="useractivationsetup.do"><bean:message key ="usersetup.activation"/></html:link>
                       		<%}%>
                        	
                        </td>             
                        <td> <img src="images/form_tab_end_off.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>
                  </td>
                  <td width="5"> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right"> 
                    			
					 <%
					    if(wtAction == null || wtAction.equals("add") || wtAction.equals("addNew"))
					    {%>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>
        					        					
        				 <% }else if(wtAction.equals("change") || wtAction.equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>       						
        					<!-- <html:button property="submitbutton" onclick="go('delete')"><bean:message key ="common.delete"/></html:button> -->       					        					        					        				      
       						<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>
       						
        				   <% }else if(wtAction.equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>
        				    <%}%>		
  					  
                    </div>                  
		   </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif" width="5"></td>
                  <td class="form_bgcolor"  colspan="2">
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td rowspan="2" valign="top">
                          <table width="100%" border="0" cellspacing="0" cellpadding="0">

                            <tr>
                              <td style="padding: 20px 20px 10px 20px;" valign="top">
                                <table border="0" cellpadding="0" cellspacing="0">

                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key="usersetup.issuerid"/> :</td>
                                    <td style="width:250px" class="label"><bean:write name="userSetupForm" property="issuerId"/></td>
                                  </tr>
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key="usersetup.issuername"/>:</td>
                                    <td style="width:250px" class="label"><bean:write name="userSetupForm" property="issuerName"/>
                                    </td>
                                  </tr>
                                  
                                  
                                  <tr>
                                    <td class="desc_cell" nowrap><bean:message key="usersetup.userid"/></td>
                                    <td style="width:250px" class="label"><bean:write name="userSetupForm" property="adminUserId"/></td>
                                  </tr>
                                  
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td style="padding: 20px 20px 10px 20px;" valign="top">
                                <table cellspacing=0 cellpadding=0 border=0 width="122">
                                  <tbody>
                                  <tr>
                                    <td>
                                      <table cellspacing=0 cellpadding=0 border=0>
                                        <tbody>
                                        <tr>
                                          <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                          <td class=group_title background=images/tab_fond.gif><bean:message key="usersetup.usersetupgrouptitle"/> </td>
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
                                      <table border="0" cellpadding="0" cellspacing="0">
                      	               <tr>
                        		 <td class="desc_cell" nowrap width="132"><b><bean:message key ="usersetup.userid"/>*</b></td>
                        		 <td valign="top" width="242"><html:text property="frmUserId" size="20" maxlength="32"/></td>
                        		 <td>&nbsp;</td>
                      		       </tr>
                      		       
                                     </td>   
                                        <tr>
                                          <td class="desc_cell" nowrap><b><bean:message key="usersetup.username"/>*</b></td>
                                          <td style="width:250px">
                                            <table border="0" cellspacing="0" cellpadding="1" width="165">
                                              <tr>
                                                <td class="desc_cell"><bean:message key ="usersetup.firstname"/></td>
                                                <td>
                                                  <html:text property="firstName" size="20" maxlength="100"/></td>                                                </td>
                                              </tr>
                                              <tr>
                                                <td class="desc_cell"><bean:message key ="usersetup.lastname"/></td>
                                                <td>
                                                  <html:text property="lastName" size="20" maxlength="100"/></td>                                               </td>
                                              </tr>
                                            </table>                                          
                                          </td>
                                        </tr>                                        
                      			<tr>
		        		  <td class="desc_cell" nowrap><b><bean:message key ="usersetup.usertype"/>*</b></td>
		          		  <td valign="top">
		      	   		  <html:select property="userType" onchange="userTypeChange(this)">
		      	     		     <html:option value=""></html:option>
		      	     		     <html:optionsCollection property="userTypeList" value="key" label="value" />
		      	   		  </html:select>
		        		  </td>
                      		        </tr>                                        
                      			<tr>
		        		  <td class="desc_cell" nowrap><b><bean:message key ="usersetup.role"/>*</b></td>
		          		  <td valign="top">
		          		  	<div id="roleIdDiv">
		      	   		  <html:select property="roleId" >
		      	     		     <html:option value=""></html:option>
		      	     		     <html:optionsCollection property="roleList" value="key" label="value" />
		      	   		  </html:select>
		      	   		  </div>
		        		  </td>
                      		        </tr>                                        
                      			<tr>
		        		  <td class="desc_cell" nowrap><b><bean:message key ="usersetup.branch"/>*</b></td>
		          		  <td valign="top">
		      	   		  <html:select property="branchId" >
		      	     		     <html:option value=""></html:option>
		      	     		     <html:optionsCollection property="branchList" value="key" label="value" />
		      	   		  </html:select>
		        		  </td>
                      		        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td style="padding: 20px 20px 10px 20px;" valign="top" height="2">
                                <table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                  <tr>
                                    <td>
                                      <table cellspacing=0 cellpadding=0 border=0>
                                        <tbody>
                                        <tr>
                                          <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                          <td class=group_title background=images/tab_fond.gif><bean:message key="usersetup.lockunlockgrouptitle"/></td>
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
                                    <td height="2">
                                      <table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0 width="100%">
                      			<tr>
		        		  <td class="desc_cell" nowrap><b><bean:message key="usersetup.lockstatus"/>*</b></td>
		          		  <td valign="top">
		      	   		  <html:select property="status" >
		      	     		     <html:option value=""></html:option>
		      	     		     <html:optionsCollection property="userStatusList" value="key" label="value" />
		      	   		  </html:select>
		        		  </td>
                      		        </tr>
                                        <tbody> </tbody>
                                      </table>
                                    </td>
                                  </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                          </table>
                        </td>
                        <td rowspan="2">
                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td style="padding: 20px 20px 10px 20px;" valign="top">
                                <table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                  <tr>
                                    <td>
                                      <table cellspacing=0 cellpadding=0 border=0>
                                        <tbody>
                                        <tr>
                                          <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                          <td class=group_title background=images/tab_fond.gif><bean:message key="usersetup.addressgrouptitle"/></td>
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
                                      <table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0 width="100%">
                                        <tr>
                                          <td class=desc_cell noWrap><b><bean:message key ="usersetup.permanentaddress"/>*</b></td>
                                          <td>
                                            <html:textarea property="permanentAddress" rows="3"></html:textarea>                                          </td>
                                        </tr>
                                        <tr>
                                          <td class=desc_cell noWrap><bean:message key ="usersetup.otheraddress"/></td>
                                          <td>
                                            <html:textarea property="otherAddress" rows="3"></html:textarea>                                          </td>
                                        </tr>
      					<tr>
		      			  <td class="desc_cell" nowrap width="132"><b><bean:message key ="usersetup.phonenumber"/>*</b></td>
		      			  <td valign="top" width="242"><html:text property="phone" size="20" maxlength="100"/></td>
		      			  <td>&nbsp;</td>
                      			</tr>
                      			<tr>
		      			  <td class="desc_cell" nowrap width="132"><b><bean:message key ="usersetup.emailaddress"/>*</b></td>
		      			  <td valign="top" width="242"><html:text property="emailId" size="20" maxlength="50"/></td>
		      			<td>&nbsp;</td>
                      		        </tr>
                                        <tbody> </tbody>
                                      </table>
                                    </td>
                                  </tr>
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            <tr>
                              <td style="padding: 20px 20px 10px 20px;" valign="top">
                                <table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                  <tr>
                                    <td>
                                      <table cellspacing=0 cellpadding=0 border=0>
                                        <tbody>
                                        <tr>
                                          <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                          <td class=group_title background=images/tab_fond.gif><bean:message key="usersetup.securitygrouptitle"/></td>
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
                                    <td height="2">
                                      <table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0 width="100%">
                      			  <tr>
		      			    <td class="desc_cell" nowrap width="132"><b><bean:message key ="usersetup.ipaddress"/>*</b></td>
		      			    <td valign="top" width="242"><html:text property="userStationIp" size="20" maxlength="15"/></td>
		      			    <td>&nbsp;</td>
                      			  </tr>
                                        <tbody> </tbody>
                                      </table>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td height="2">
                                      <table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0 width="100%">
                      			  <tr>
		      			    <td class="desc_cell" nowrap width="132"><b><bean:message key ="usersetup.hintquestion"/>*</b></td>
		      			    <td valign="top" width="242"><html:text property="hintQuestion" size="20" maxlength="100"/></td>
		      			    <td>&nbsp;</td>
                      			  </tr>
                                        <tbody> </tbody>
                                      </table>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td height="2">
                                      <table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0 width="100%">
                      			  <tr>
		      			    <td class="desc_cell" nowrap width="132"><b><bean:message key ="usersetup.hintanswer"/>*</b></td>
		      			    <td valign="top" width="242"><html:text property="hintAnswer" size="20" maxlength="100"/></td>
		      			    <td>&nbsp;</td>
                      			  </tr>
                                        <tbody> </tbody>
                                      </table>
                                    </td>
                                  </tr>                                  
                                  </tbody>
                                </table>
                              </td>
                            </tr>
                            <tr>                          
                              <td style="padding: 20px 20px 10px 20px;" valign="top">
                                <table cellspacing=0 cellpadding=0 border=0>
                                  <tbody>
                                  <tr>
                                    <td>
                                      <table cellspacing=0 cellpadding=0 border=0>
                                        <tbody>
                                        <tr>
                                          <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                          <td nowrap class=group_title background=images/tab_fond.gif>
                                            <bean:message key="usersetup.ftlgrouptitle"/> </td>
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
                                    <td height="2">
                                      <table style="BORDER-COLLAPSE: collapse" bordercolor=#111111 cellspacing=0 cellpadding=0 border=0 width="100%">
                      			<tr>
		        		  <td class="desc_cell" nowrap><bean:message key="usersetup.ftlflag"/></td>
		          		  <td valign="top">
		      	   		  <html:select property="firstTimeLogin" >
		      	   		     <html:option value=""></html:option>
		      	     		     <html:optionsCollection property="ftlOptionList" value="key" label="value" />
		      	   		  </html:select>
		        		  </td>
                      		        </tr>
                                   <tr>
                                     <td class="desc_cell"><bean:message key ="usersetup.ftlexpdate"/></td>
                                      <td>
                                          <html:text property="ftlExpDate" size="20"/></td>                                                
                                      </td>
                                   </tr>
                                      </table>
                                    </td>
                                  </tr>
                                  </tbody>
                                </table>
                              </td>                              
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <tr> </tr>
                    </table>
                  </td>
                  <td background="images/tbl_d.gif" width="5"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif" width="5"></td>
                  <td class="form_bgcolor" colspan="2">&nbsp; </td>
                  <td background="images/tbl_d.gif" width="5"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right"> 
                    			
					 <%
					    if(wtAction == null || wtAction.equals("add"))
					    {%>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>
        					        					
        				 <% }else if(wtAction.equals("change") || wtAction.equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>       						
        					<!-- <html:button property="submitbutton" onclick="go('delete')"><bean:message key ="common.delete"/></html:button> -->       					        					        					        				      
       						<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>
       						
        				   <% }else if(wtAction.equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>
        				    <%}%>		
  					  
                    </div>                  
		   </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td width="5"> <img src="images/tbl_bas_g.gif" border="0" alt="" width="5" height="5"></td>
                  <td width="383" background="images/tbl_bas.gif"></td>
                  <td width="295" background="images/tbl_bas.gif"></td>
                  <td width="5"> <img src="images/tbl_bas_d.gif" border="0" alt="" width="5" height="5"></td>
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