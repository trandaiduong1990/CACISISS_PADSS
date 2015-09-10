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
		document.forms[0].action= "lettertemplatelist.do?method=List";
		document.forms[0].submit();
   }
   
</script>
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<body bgcolor="ffffff">
  <html:form action="lettertemplateprocess.do" method="post"
	enctype="multipart/form-data" >
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <html:hidden property="status" />

  <table style="width:805px;" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> <td class="titreSection" width="504"><bean:message key ="lettertemplate.titlesetup"/></td>
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
									<bean:message key ="lettertemplate.title"/>                        </td>
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
                        		<td class="desc_cell" nowrap width="132"><bean:message key ="lettertemplate.id"/> *</td>
                        		<td valign="top" width="242"><html:text property="letterId" size="26" maxlength="6" /></td>
                        	</tr>
                            <tr>
                            	<td class="desc_cell" nowrap width="132"><bean:message key ="lettertemplate.desc"/></td>
                        		<td valign="top" width="242"><html:text property="description" size="26" maxlength="32" /></td>  
                            </tr>
                            <tr>
                            	<td class="desc_cell" nowrap width="132"><bean:message key ="lettertemplate.manualallow"/> *</td>
                        		<td valign="top" width="242">
                        			<html:checkbox property ="manualEntry" value="Y" />
                        		</td>    
                            </tr>
                            <tr>
                             	<td class="desc_cell" nowrap width="132"><bean:message key ="lettertemplate.noofcopy"/> *</td>
                        		<td valign="top" width="242"><html:text property="noOfCopies" maxlength="1" size="26" /></td>    
                            </tr>
                            <tr>
                             	<td class="desc_cell" nowrap width="132"><bean:message key ="lettertemplate.signame"/> *</td>
                        		<td valign="top" width="242"><html:text property="signatoryName" maxlength="30" size="26" /></td>
                            </tr>
                            <tr>
                             	<td class="desc_cell" nowrap width="132"><bean:message key ="lettertemplate.department"/> *</td>
                        		<td valign="top" width="242"><html:text property="department" maxlength="30" size="26" /></td>
                            </tr>
                            <tr>
                             	<td class="desc_cell" nowrap width="132"><bean:message key ="lettertemplate.category"/></td>
                        		<td valign="top" width="242">
                        			<html:select property="letterCategory" >
		                        		<html:option value=""> </html:option>	
							         	<html:optionsCollection property="letterCategoryList" value="key" label="value" />
							        </html:select>
                        		</td>
                            </tr>
                            <tr>
                             	<td class="desc_cell" nowrap width="132"><bean:message key ="lettertemplate.template"/> *</td>
                        		<td valign="top" width="242"><html:file property="fileUpload" style="width:185px" /></td>
                        		<%
									if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add"))
								{ %>
									<td width="175" nowrap></td>
                        		<%
								} else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))
							    { %>
	                        		<td>&nbsp; </td>
	                        		<td class="desc_cell">
	                        			<bean:write name="letterTemplateSetupForm" property="applicationSource" />
										<html:hidden property="applicationSource" />
	                        		</td>
                        		<% } else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
		        				{ %>  	
		        					<td>&nbsp; </td>
	                        		<td class="desc_cell">
	                        			<bean:write name="letterTemplateSetupForm" property="applicationSource" />
										<html:hidden property="applicationSource" />
	                        		</td>
		        				<%}%>	
                            </tr>
                            <tr>
                             	<td class="desc_cell" nowrap width="132"><bean:message key ="lettertemplate.sql"/></td>
                        		<td valign="top" width="242"><html:textarea property="sqlQuery" style="width:100%;" rows="3"></html:textarea>
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