<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

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
		document.forms[0].action= "applvalidationlist.do?method=List";
		document.forms[0].submit();
   }
</script>
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<body bgcolor="ffffff">
  <html:form action="applvalidationprocess.do">
  <input type="hidden" name="method"/>
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  <html:hidden property="status"/>

  <table style="width:805px;" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> <td class="titreSection" width="504"><bean:message key ="applvalidation.titlenew"/></td>
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
									<bean:message key ="applvalidation.title"/></td>
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
								<tr>
                      				<td style="padding: 20px 20px 10px 20px;" valign="top"><table border="0" cellpadding="0" cellspacing="0">
                        				<tr>
                        					<td class="desc_cell" nowrap="" width="200"><b><bean:message key ="applvalidation.name"/></b></td>
                        					<td>
                        						<html:text property="applValName" maxlength="20" size="30"/>
                        						<html:hidden property="applValId"/>
                        					</td>
                        				</tr>
                        				
                        			</table></td>
                        		</tr>
                        		<tr>
                        			<td style="padding: 20px 20px 10px 20px;" valign="top">
                        				<table border="0" cellpadding="0" cellspacing="2">
                        					<tr>
											    <th align="center" class="desc_cell">Criteria</th>
											    <th align="center" class="desc_cell">Operator</th> 
											    <th align="center" class="desc_cell">Value1</th>
											    <th align="center" class="desc_cell">Value2</th>
											    <th align="center" class="desc_cell">Ref/Fail</th>
											    <th align="center" class="desc_cell">Condition</th>
											    <th align="center" class="desc_cell">Criteria</th>
											    <th align="center" class="desc_cell">Operator</th>
											    <th align="center" class="desc_cell">Value</th>
											</tr>
											<c:forEach var="i" begin="1" end="5">
                        					<tr>
                        						<td>
                        							<html:select property="criteria${i}" style="width:80px;">
					                              	  <html:option value=""> </html:option>		
													  <html:optionsCollection property="criteriaList" value="key" label="value" />
													</html:select>
												</td>
	                        					<td>
	                        						<html:select property="operator${i}" style="width:80px;" >
					                              	  <html:option value=""> </html:option>		
													  <html:optionsCollection property="operatorList" value="key" label="value" />
													</html:select>
	                        					</td>
	                        					<td>
	                        						<html:text property="value1${i}" maxlength="20" size="9"/>
												</td>
	                        					<td>
	                        						<html:text property="value2${i}" maxlength="20" size="9"/>
	                        					</td>
	                        					<td>
                        							<html:select property="refFail${i}" style="width:80px;">
					                              	  <html:option value=""> </html:option>		
													  <html:optionsCollection property="refFailList" value="key" label="value" />
													</html:select>
												</td>
	                        					<td align="center">
	                        						<html:checkbox property ="condition${i}" value="Y" />
	                        					</td>
	                        					<td>
	                        						<html:select property="condCriteria${i}" style="width:80px;" >
					                              	  <html:option value=""> </html:option>		
													  <html:optionsCollection property="criteriaList" value="key" label="value" />
													</html:select>
												</td>
	                        					<td>
	                        						<html:select property="condOperator${i}" style="width:80px;" >
					                              	  <html:option value=""> </html:option>		
													  <html:optionsCollection property="operatorList" value="key" label="value" />
													</html:select>
	                        					</td>
	                        					<td>
	                        						<html:text property="value${i}" maxlength="20" size="9"/>
	                        					</td>
                        					</tr>
                        					</c:forEach>
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