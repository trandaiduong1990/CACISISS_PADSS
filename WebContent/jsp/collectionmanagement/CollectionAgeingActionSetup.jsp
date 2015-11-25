<%@page import="java.util.Date"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="cardtypesetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/jquery-1.11.3.min.js"></script>
<script language="JavaScript">
   function go(action) {
       document.forms[0].method.value=action;
       document.forms[0].submit();
    }
  
     function showList(){
          document.forms[0].action= "collectionageingactionlist.do?method=List";
          document.forms[0].submit();
       } 
      
     $(document).ready(function() {
    	 var startDay = document.forms[0].startDay.value;
    	 var endDay = document.forms[0].endDay.value;
    	 if((startDay!=null && startDay!="") || (endDay!=null && endDay !="")) {
    		document.getElementById("startDay").innerHTML = startDay;
    	 	document.getElementById("endDay").innerHTML = endDay;
    	 } else {
    		 document.getElementById("startDay").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;";
     	 	document.getElementById("endDay").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;";
    	 }
    	}); 
     
	
	function chekAgeingPolicy(key) {
		var ageingPolicy = key.value;
		if (ageingPolicy == null || ageingPolicy == "") {
			document.getElementById("startDay").innerHTML = "";
			document.getElementById("endDay").innerHTML = "";
			document.forms[0].startDay.value = "";
			document.forms[0].endDay.value = "";
		} else {
			$
					.ajax({
						type : "POST",
						url : "collectionageingactionprocess.do?method=getAgeingPolicy",
						data : "ageingPolicy=" + ageingPolicy,
						success : function(response) {
							if (response != null) {
								var split = response.split("|");
								var startDay = split[0].trim();
								document.getElementById("startDay").innerHTML = startDay;
								var endDay = split[1].trim();
								document.getElementById("endDay").innerHTML = endDay;
								document.forms[0].startDay.value = startDay;
								document.forms[0].endDay.value = endDay;
							}
						},
						error : function(e) {
							alert('Error chekAgeingPolicy: ' + e);
						}
					});
		}
	}

</script>
<%@ include file="/jsp/common/delete.jsp"%>
<style type="text/css">
.padLeft{padding-left: 4px}
</style>
</head>
<body bgcolor="ffffff">
 <html:form action="collectionageingactionprocess.do">
<input type="hidden" name="method"/>
<bean:define id="sno" name="collectionAgeingActionSetupForm" property="sno"/>
<html:hidden property="sno" value="<%=(String)sno%>"/>
<bean:define id="originalDay" name="collectionAgeingActionSetupForm" property="originalDay"/>
<html:hidden property="originalDay" value="<%=(String)originalDay%>"/>
<bean:define id="startDay" name="collectionAgeingActionSetupForm" property="startDay"/>
<html:hidden property="startDay" value="<%=(String)startDay%>"/>
<bean:define id="endDay" name="collectionAgeingActionSetupForm" property="endDay"/>
<html:hidden property="endDay" value="<%=(String)endDay%>"/>
<%-- <html:hidden property="countRow" name="collectionAgeingActionSetupForm" value=""/> --%>
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="504"><bean:message key ="collectionageingaction.screentitle"/></td>
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
      <td height="444" valign="top"><br>
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
                         <bean:message key ="collectionageingaction.screentitle"/></td>
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
       						<html:button property="submitbutton" onclick="deleteAction('delete')"><bean:message key ="common.delete"/></html:button>
       						<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancelA") || ((String)request.getAttribute("ACTION")).equals("cancelU"))		
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
                      <td style="padding: 20px 20px 10px 20px;" valign="top">
                      	<table border="0" cellpadding="0" cellspacing="0" width="100%" >
                      	<%if(request.getAttribute("ACTION")!= null && (((String)request.getAttribute("ACTION")).equals("update")
                      			|| ((String)request.getAttribute("ACTION")).equals("cancelU")))		
        				    { %>
                        <tr>
                          <td colspan="2" align="center"  nowrap><span class="desc_cell"><b><bean:message key ="collectionageing.ageingPolicy"/> * </b></span>
                          
        				    		<html:hidden property="ageingPolicy" name="collectionAgeingActionSetupForm" />
	                          		<html:select property="ageingPolicy" name="collectionAgeingActionSetupForm" disabled="true">
		                        		<html:option value=""> </html:option>	
							         	<html:optionsCollection property="ageingPolicyList" value="key" label="value" />
							        </html:select>
							</td>
                        </tr>
                        <tr>
                        	<td nowrap align="right" width="50%">
                        		<span class="desc_cell"><b><bean:message key ="collectionageing.startDay"/></b></span>
                        		<span class="label">
	                        		<html:hidden property="startDay" name="collectionAgeingActionSetupForm" />
								    <bean:write name="collectionAgeingActionSetupForm" property="startDay"/>
								</span>
                        	</td>
                        	<td nowrap>
                        		<span class="desc_cell"><b><bean:message key ="collectionageing.endDay"/></b></span>
                        		<span class="label">
	                        		<html:hidden property="endDay" name="collectionAgeingActionSetupForm" />
								    <bean:write name="collectionAgeingActionSetupForm" property="endDay"/>
								</span> 
                        	</td>
                        </tr>
                        <%}else{ %>
	                        <tr>
	                          <td colspan="2" align="center"  nowrap><span class="desc_cell"><b><bean:message key ="collectionageing.ageingPolicy"/> * </b></span>
                          		<html:select property="ageingPolicy" name="collectionAgeingActionSetupForm" onchange="chekAgeingPolicy(this)">
		                        		<html:option value=""> </html:option>	
							         	<html:optionsCollection property="ageingPolicyList" value="key" label="value" />
							    </html:select>
							  </td>
                        	</tr>
                        	<tr>
	                        	<td nowrap align="right" width="50%">
	                        		<span class="desc_cell"><b><bean:message key ="collectionageing.startDay"/></b></span>
	                        		<span id="startDay" class="label"></span>
	                        	</td>
	                        	<td nowrap>
	                        		<span class="desc_cell"><b><bean:message key ="collectionageing.endDay"/></b></span>
	                        		<span id="endDay" class="label"></span>
	                        	</td>
	                        </tr>
							<%} %>
                      </table>
                     </td>
                      </tr>
                      <tr>
                      	<td style="padding: 10px 10px 10px 10px;" valign="top">
                      	<fieldset class="group_field_set">
						<legend class="group_title">Collection Ageing Action</legend>
                      		<table border="0" cellpadding="3" cellspacing="3" id="tableList">
                      			<tr>
                      				<td class="desc_cell" nowrap><b>Days</b></td>
                      				<td class="desc_cell" nowrap><b>Phone</b></td>
                      				<td class="desc_cell" nowrap><b>Remainder</b></td>
                      				<td class="desc_cell" nowrap><b>Remainder Type</b></td>
                      				<td class="desc_cell" nowrap><b>Card Block</b></td>
                      				<td class="desc_cell" nowrap><b>Write-off</b></td>
                      				<td nowrap><b></b></td>
                      			</tr>
                      			
                      			<%if(request.getAttribute("ACTION")!= null && (((String)request.getAttribute("ACTION")).equals("update")
                      					|| ((String)request.getAttribute("ACTION")).equals("cancelU")))			
        				    		{ %>
        				    		<tr>
												<td>
													<html:text property="days" size="3"></html:text>
												</td>
												<td align="center">
							                    	<html:checkbox property ="phone" value="Y"/>
												</td>
												<td align="center">
							                    	<html:checkbox property ="remainder" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
													<html:checkbox property ="tempCardOff" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff" value="Y"/>
												</td>
											</tr>
										<%}else{ %>	
		        							<tr>
												<td>
													<html:text property="days" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff" value="Y"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<html:text property="days2" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone2" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder2" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType2" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff2" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff2" value="Y"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<html:text property="days3" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone3" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder3" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType3" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff3" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff3" value="Y"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<html:text property="days4" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone4" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder4" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType4" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff4" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff4" value="Y"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<html:text property="days5" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone5" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder5" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType5" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff5" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff5" value="Y"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<html:text property="days6" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone6" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder6" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType6" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff6" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff6" value="Y"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<html:text property="days7" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone7" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder7" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType7" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff7" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff7" value="Y"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<html:text property="days8" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone8" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder8" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType8" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff8" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff8" value="Y"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<html:text property="days9" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone9" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder9" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType9" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff9" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff9" value="Y"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<html:text property="days10" size="3"></html:text>
												</td>
												<td align="center">
													<html:checkbox property ="phone10" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="remainder10" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType10" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
							                    	<html:checkbox property ="tempCardOff10" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property ="writeOff10" value="Y"/>
												</td>
											</tr>
                        			<% } %>
                      		</table>
                      		</fieldset>
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
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
       						<html:button property="submitbutton" onclick="deleteAction('delete')"><bean:message key ="common.delete"/></html:button>
       						<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancelA") || ((String)request.getAttribute("ACTION")).equals("cancelU"))		
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