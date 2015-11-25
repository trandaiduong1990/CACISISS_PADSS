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
          document.forms[0].action= "collectionageingactionlist.do";
          document.forms[0].submit();
       } 
     var dropdown="";
     $(document).ready(function() {
    	 document.getElementById("startDay").innerHTML = document.forms[0].startDay.value;
    	 document.getElementById("endDay").innerHTML = document.forms[0].endDay.value;
    	 initDropdown();
    	});
     
	function initDropdown() {
		// get the form values
		$.ajax({
			type : "POST",
			url : "collectionageingactionprocess.do?method=getDropdown",
			success : function(response) {
				// we have the response
				dropdown = response;
				var dropdownFirst = "<select name=\"ageingAction[0].remainderType\">" + response + "</select>";
				document.getElementById("initDropdownFirst").innerHTML = dropdownFirst;
			},
			error : function(e) {
				alert('Error initDropdown: ' + e);
			}
		});
	}
	
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

	function addNewRow(key) {
		$.ajax({
			type : "POST",
			url : "collectionageingactionprocess.do?method=addAgeingAction",
			success : function(response) {
				var table = document.getElementById("tableList");
				var rowCount = table.rows.length;
				var row = table.insertRow(rowCount);
				var counts = rowCount - 1;
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				cell2.align = 'center';
				var cell3 = row.insertCell(2);
				cell3.align = 'center';
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				cell5.align = 'center';
				var cell6 = row.insertCell(5);
				cell6.align = 'center';
				var cell7 = row.insertCell(6);
				cell7.align = 'center';
				cell1.innerHTML = '<input type="text" name="ageingAction[' + counts + '].days" size="3" value="">';
				cell2.innerHTML = '<input type="checkbox" value="Y" name ="ageingAction[' + counts + '].phone"/>';
				cell3.innerHTML = '<input type="checkbox" value="Y" name ="ageingAction[' + counts + '].remainder"/>';
				cell4.innerHTML = "<select name=\"ageingAction[" + counts + "].remainderType\">"
						+ dropdown + "</select>";
				//cell4.innerHTML = '<select name="ageingAction[' + counts + '].remainderType"><option value="1"> 1</option><option value="123"> 123</option></select>';
				cell5.innerHTML = '<input type="checkbox" value="Y" name ="ageingAction[' + counts + '].tempCardOff"/>';
				cell6.innerHTML = '<input type="checkbox" value="Y" name ="ageingAction[' + counts + '].writeOff"/>';
				cell7.innerHTML = '<input type="button" onclick="removeRow(this)" value="Remove">';
			},
			error : function(e) {
				alert('Error validation: ' + e);
			}
		});
	}

	function removeRow(r) {
		var i = r.parentNode.parentNode.rowIndex;
		$.ajax({
			type : "POST",
			url : "collectionageingactionprocess.do?method=removeAgeingAction",
			data : "index=" + i,
			success : function(response) {
				document.getElementById("tableList").deleteRow(i);
			},
			error : function(e) {
				alert('Error validation: ' + e);
			}
		});
		
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
            <td class="titreSection" width="504"><bean:message key ="collectionageing.screentitle"/></td>
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
                         <bean:message key ="collectionageing.grouptitle"/></td>
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
        					<html:button property="submitbutton" onclick="validation()" ><bean:message key ="common.save"/></html:button>
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
                      <td style="padding: 20px 20px 10px 20px;" valign="top">
                      	<table border="0" cellpadding="0" cellspacing="0" width="100%" >
                        <tr>
                          <td colspan="2" align="center"  nowrap><span class="desc_cell"><b><bean:message key ="collectionageing.ageingPolicy"/> * </b></span>
                          <%if(request.getAttribute("ACTION")!= null && !((String)request.getAttribute("ACTION")).equals("add"))		
        				    { %>
        				    		<html:hidden property="ageingPolicy" name="collectionAgeingActionSetupForm" />
	                          		<html:select property="ageingPolicy" name="collectionAgeingActionSetupForm" disabled="true">
		                        		<html:option value=""> </html:option>	
							         	<html:optionsCollection property="ageingPolicyList" value="key" label="value" />
							        </html:select>
	                        <%}else{ %>
                          		<html:select property="ageingPolicy" name="collectionAgeingActionSetupForm" onchange="chekAgeingPolicy(this)">
		                        		<html:option value=""> </html:option>	
							         	<html:optionsCollection property="ageingPolicyList" value="key" label="value" />
							        </html:select>
							<%} %>
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
                      			
                      			<%if(request.getAttribute("ACTION")!= null && !((String)request.getAttribute("ACTION")).equals("add"))		
        				    		{ %>
        				    		<tr>
												<td>
													<html:text property="days" size="3"></html:text>
												</td>
												<td align="center">
													<logic:equal name="collectionAgeingActionSetupForm" property="phone" value="Y">
						                    			<input type="checkbox" value="Y" name ="phone" checked="checked"/>
							                    	</logic:equal>
							                    	<logic:notEqual name="collectionAgeingActionSetupForm" property="phone" value="Y">
							                    		<input type="checkbox" value="Y" name ="phone"/>
							                    	</logic:notEqual>
												</td>
												<td align="center">
													<logic:equal name="collectionAgeingActionSetupForm" property="remainder" value="Y">
						                    			<input type="checkbox" value="Y" name ="remainder" checked="checked"/>
							                    	</logic:equal>
							                    	<logic:notEqual name="collectionAgeingActionSetupForm" property="remainder" value="Y">
							                    		<input type="checkbox" value="Y" name ="remainder"/>
							                    	</logic:notEqual>
												</td>
												<td align="center">
													<html:select property="remainderType" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
													<logic:equal name="collectionAgeingActionSetupForm" property="tempCardOff" value="Y">
						                    			<input type="checkbox" value="Y" name ="tempCardOff" checked="checked"/>
							                    	</logic:equal>
							                    	<logic:notEqual name="collectionAgeingActionSetupForm" property="tempCardOff" value="Y">
							                    		<input type="checkbox" value="Y" name ="tempCardOff"/>
							                    	</logic:notEqual>
												</td>
												<td align="center">
													<logic:equal name="collectionAgeingActionSetupForm" property="writeOff" value="Y">
						                    			<input type="checkbox" value="Y" name ="writeOff" checked="checked"/>
							                    	</logic:equal>
							                    	<logic:notEqual name="collectionAgeingActionSetupForm" property="writeOff" value="Y">
							                    		<input type="checkbox" value="Y" name ="writeOff"/>
							                    	</logic:notEqual>
												</td>
											</tr>
										<%}else{ %>	
                      			<%-- <logic:present name="collectionAgeingActionSetupForm" property="ageingAction">
		        					<logic:empty  name ="collectionAgeingActionSetupForm" property="ageingAction">  --%>
		        						<tr>
												<td>
													<input type="text" name="ageingAction[0].days" size="3" >
												</td>
												<td align="center">
													<input type="checkbox" name="ageingAction[0].phone" value="Y"/>
												</td>
												<td align="center">
													<input type="checkbox" name="ageingAction[0].remainder" value="Y"/>
												</td>
												<td id="initDropdownFirst">
													<%-- <html:select property="ageingAction[0].remainderType" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select> --%>
												</td>
												<td align="center">
													<input type="checkbox" name="ageingAction[0].tempCardOff" value="Y"/>
												</td>
												<td align="center">
													<input type="checkbox" name="ageingAction[0].writeOff" value="Y"/>
												</td>
											</tr>
		        					<%--</logic:empty>
								 <logic:iterate id="ageingAction" name="collectionAgeingActionSetupForm" property="ageingAction">
											<tr>
												<td>
													<html:text property="days" name="ageingAction" size="3"></html:text>
												</td>
												<td align="center">
												<bean:define id="phone" name="ageingAction" property="phone"/>
													<html:hidden property="phone" value="<%=(String)phone%>"/>
													<html:checkbox property="phone" name="ageingAction" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property="remainder" name="ageingAction" value="Y"/>
												</td>
												<td align="center">
													<html:select property="remainderType" name="ageingAction" >
						                        		<html:option value=""> </html:option>	
											         	<html:optionsCollection property="remainderTypeList" value="key" label="value" />
											        </html:select>
												</td>
												<td align="center">
													<html:checkbox property="tempCardOff" name="ageingAction" value="Y"/>
												</td>
												<td align="center">
													<html:checkbox property="writeOff" name="ageingAction" value="Y"/>
												</td>
											</tr>
                        			</logic:iterate> 
                        			</logic:present>--%>
                        			<% } %>
                      		</table>
                      		</fieldset>
                      		<div style="width:100%; clean:both; height:5px;"></div>
							<div  style="padding-left: 2px;">
								<html:button property="submitbutton" styleId="buttonAdd" onclick="addNewRow(this)">Add</html:button>
							</div>
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