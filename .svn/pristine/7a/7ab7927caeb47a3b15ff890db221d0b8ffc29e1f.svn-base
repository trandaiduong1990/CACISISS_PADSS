<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>

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

<script language="JavaScript">
   function go(action) {
       document.forms[0].method.value=action;
       document.forms[0].submit();
   }
     
   function showList(){
		document.forms[0].action= "creditlimitprofilelist.do?method=List";
		document.forms[0].submit();
   }
   
   function selectBoxChange() {
	   var e = document.getElementById("cardProductId");
	   var strBox = e.options[e.selectedIndex].value;
	   var screen = document.forms[0].screen.value;
	   go(screen);
   }
</script>
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<body bgcolor="ffffff">
  <html:form action="creditlimitprofileprocess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <html:hidden property="status"/>
  <html:hidden property="sNo"/>
  <html:hidden property="screen"/>

  <table style="width:805px;" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> <td class="titreSection" width="504"><bean:message key ="creditlimitprofile.title.search"/></td>
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
									<bean:message key ="creditlimitprofile.title"/></td>
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
						<td class="form_bgcolor" colspan="2">
							<table border="0" cellspacing="0" cellpadding="0">
<%-- 								<tr>
                      				<td style="padding: 20px 20px 10px 20px;" valign="top"><table border="0" cellpadding="0" cellspacing="0">
                        				<tr>
                        					<td class="desc_cell" nowrap="" width="200"><b><bean:message key ="creditscoring.name"/></b></td>
                        					<td>
                        						<html:text property="scoreName" maxlength="20" size="30"/>
                        						<html:hidden property="scoreId"/>
                        					</td>
                        				</tr>
                        				
                        			</table></td>
                        		</tr> --%>
                        		<tr>
                        			<td style="padding: 20px 20px 10px 20px;" valign="top">
                        				<table border="0" cellpadding="0" cellspacing="2">
                        					<tr>
												<td class="desc_cell" nowrap="">
													<bean:message key ="creditlimitprofile.name"/> *
												</td>
												<td colspan="2">
													<html:text property="description" maxlength="20" size="20" />
												</td>
												<td></td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap="">
													<bean:message key ="creditlimitprofile.score"/> *
												</td>
												<td>
													<html:select property="scoreId" style="width:80px;" onchange="selectBoxChange()" >
					                              	  <html:option value=""> </html:option>		
													  <html:optionsCollection property="creditLimitProfileList" value="key" label="value" /> 
													</html:select>
												</td>
												<td>&nbsp;</td>
												<td class="desc_cell" nowrap="">
													<bean:message key ="creditlimitprofile.productname"/> *
												</td>
												<td>
													<html:select styleId="cardProductId" property="cardProductId" onchange="selectBoxChange()" >
					                              	  <html:option value=""> </html:option>		
													  <html:optionsCollection property="cardProductList" value="key" label="value" /> 
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="desc_cell" nowrap="">
													<bean:message key ="creditlimitprofile.total"/>
												</td>
												<td class="label">
													<bean:write name="creditLimitProfileSetupForm" property="totalScoringPoint" />
												</td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr>
												<td class="" nowrap="">
													
												</td>
											</tr>
											<tr>
												<td colspan="5">
													<fieldset class="group_field_set">
													<legend class="group_title"><bean:message key ="creditlimitprofile.range"/>:</legend>
													<table border="0" cellpadding="0" cellspacing="2">
														<tr>
															<td></td>
														    <td class="label" class="desc_cell"><bean:message key ="creditlimitprofile.lower"/></td>
														    <td></td>
														    <td class="label" class="desc_cell"><bean:message key ="creditlimitprofile.upper"/></td>
														    <td></td> 
														</tr>
														<tr>
															<td width="60"></td>
															<td class="label" align="center">
																<bean:write name="creditLimitProfileSetupForm" property="lowerLimit" />
																<html:hidden property="lowerLimit"/>
															</td>
															<td>&nbsp;&nbsp;</td>
															<td class="label" align="center">
																<bean:write name="creditLimitProfileSetupForm" property="upperLimit" />
																<html:hidden property="upperLimit"/>
															</td>
															<td width="60"></td>
														</tr>
													</table>
													</fieldset>
												</td>
											</tr>
											<tr>
												<td nowrap="">
													
												</td>
											</tr>
											<tr>
												<td colspan="5">
													<fieldset class="group_field_set">
													<legend class="group_title"><bean:message key ="creditlimitprofile.scoringrange"/>:</legend>
													<table border="0" cellpadding="0" cellspacing="2">
														<tr>
															<td></td>
														    <td class="label" align="center"><bean:message key ="creditlimitprofile.from"/></td>
														    <td></td>
														    <td class="label" align="center"><bean:message key ="creditlimitprofile.to"/></td> 
														    <td></td>
														    <td class="label" align="center"><bean:message key ="creditlimitprofile.creditlimit"/></td>
														    <td></td>
														</tr>
														<tr>
															<td width="60"></td>
			                        						<td>
			                        							<html:text property="spf1" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="spt1" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="creditLimit1" size="11" />
															</td>
															<td width="60"></td>
														</tr>
														<tr>
															<td width="60"></td>
			                        						<td>
			                        							<html:text property="spf2" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="spt2" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="creditLimit2" size="11" />
															</td>
															<td width="60"></td>
														</tr>
														<tr>
															<td width="60"></td>
			                        						<td>
			                        							<html:text property="spf3" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="spt3" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="creditLimit3" size="11" />
															</td>
															<td width="60"></td>
														</tr>
														<tr>
															<td width="60"></td>
			                        						<td>
			                        							<html:text property="spf4" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="spt4" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="creditLimit4" size="11" />
															</td>
															<td width="60"></td>
														</tr>
														<tr>
															<td width="60"></td>
			                        						<td>
			                        							<html:text property="spf5" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="spt5" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="creditLimit5" size="11" />
															</td>
															<td width="60"></td>
														</tr>
														<tr>
															<td width="60"></td>
			                        						<td>
			                        							<html:text property="spf6" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="spt6" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="creditLimit6" size="11" />
															</td>
															<td width="60"></td>
														</tr>
														<tr>
															<td width="60"></td>
			                        						<td>
			                        							<html:text property="spf7" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="spt7" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="creditLimit7" size="11" />
															</td>
															<td width="60"></td>
														</tr>
														<tr>
															<td width="60"></td>
			                        						<td>
			                        							<html:text property="spf8" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="spt8" size="11" />
															</td>
															<td>&nbsp;&nbsp;</td>
															<td>
			                        							<html:text property="creditLimit8" size="11" />
															</td>
															<td width="60"></td>
														</tr>
													</table>
													</fieldset>		
												</td>
											</tr>
                        				</table>
                        			</td>
                        		</tr>
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