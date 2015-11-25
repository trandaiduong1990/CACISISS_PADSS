<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<html:html>
<head>
<title><bean:message key ="inventory.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script src="inc/js/jquery-1.11.3.min.js"></script>
</head>
<script>
function go(method) 
{
  document.forms[0].method.value=method;	
  document.forms[0].submit();
}

function showList(method) 
{
  document.forms[0].method.value=method;	
  document.forms[0].action= "inventorysearch.do";
  document.forms[0].submit();
}

var shipBy='';
function checkShipBy(event) {
	shipBy = event.value;
	if(shipBy=='O') {
		$("#otherShipId").show();
	} else {
		$("#otherShipId").hide();
	}
}

$( document ).ready(function() {
	shipBy = $('#shipById option:selected').text();
	if(shipBy=='O') {
		$("#otherShipId").show();
	} else {
		$("#otherShipId").hide();
	}
	});

</script>
<body>
 <html:form action="inventorysetup.do">
 <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
 <input type=hidden name="method"/>
 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="550"><bean:message key ="inventory.screentitle"/></td>
            <td class="titreSection" width="525">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td class="titreSection" width="569">&nbsp;</td>
            <td class="titreSection" width="548"> 
              <table border="0" cellspacing="2" cellpadding="2" align="right">
              <tr> 
                <td class="texteMenuGauche"> 
                    <p class="titreSection">

                    &nbsp;&nbsp;</p>
                </td>
                <td>&nbsp;</td> 
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
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
						<bean:message key ="inventory.grouptitle"/>                        </td>
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
					    if(((String)request.getAttribute("ACTION")).equals("add"))
					    {%>
        					<html:button property="submitbutton" onclick="go('order')" ><bean:message key ="common.order"/></html:button>
        				    <html:button property="submitbutton" onclick ="showList('order')"><bean:message key ="common.cancel"/></html:button>
        				 <% } else if(((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.update"/></html:button>    
        					<html:button property="submitbutton" onclick ="showList('order')"><bean:message key ="common.cancel"/></html:button>         					
        				  <% } else if(((String)request.getAttribute("ACTION")).equals("searchAuthorized"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('authorized')"><bean:message key ="inventory.authorized"/></html:button>    
        					<html:button property="submitbutton" onclick ="go('cancelOrder')"><bean:message key ="inventory.cancelorder"/></html:button>         					
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="common.cancel"/></html:button>         					
        				  <% } else if(((String)request.getAttribute("ACTION")).equals("searchReceipt"))		
        				    { %>
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="inventory.receipt"/></html:button>         					
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="common.cancel"/></html:button>         					
        				  <% } else if(((String)request.getAttribute("ACTION")).equals("searchDispatch"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('dispatch')"><bean:message key ="inventory.dispatch"/></html:button>    
        					<html:button property="submitbutton" onclick ="showList('dispatch')"><bean:message key ="common.cancel"/></html:button>   
        				  <% } else if(((String)request.getAttribute("ACTION")).equals("searchReceived"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('received')"><bean:message key ="inventory.receivedorder"/></html:button>    
        					<html:button property="submitbutton" onclick ="showList('received')"><bean:message key ="common.cancel"/></html:button>          					
        				  <% } else if(((String)request.getAttribute("ACTION")).equals("searchReturn"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('returnOrder')"><bean:message key ="inventory.return"/></html:button>    
        					<html:button property="submitbutton" onclick ="showList('return')"><bean:message key ="common.cancel"/></html:button>         	     					
        				  <%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromOrder")) {%>		
        					<html:button property="submitbutton" onclick ="showList('order')"><bean:message key ="common.cancel"/></html:button>             					
  					  	  <%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromAuthorized")) {%>		
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="common.cancel"/></html:button>             					
  					  	  <%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromDispatch")) {%>		
        					<html:button property="submitbutton" onclick ="showList('dispatch')"><bean:message key ="common.cancel"/></html:button>             					
  					  	  <%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromReceived")) {%>		
        					<html:button property="submitbutton" onclick ="showList('received')"><bean:message key ="common.cancel"/></html:button>     					
  					  	  <%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromReturn")) {%>		
    						<html:button property="submitbutton" onclick ="showList('return')"><bean:message key ="common.cancel"/></html:button>     					
					  	  <%}else if(((String)request.getAttribute("ACTION")).equals("cancelOrder")) {%>		
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="common.cancel"/></html:button>     					
  					  		<%} %>		
                    </div>                  
			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2"  class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0 >
                    <tbody>
                    <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="inventory.grouptitle1"/></td>
                                <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                      </tr> 
                    <% if(((String)request.getAttribute("ACTION")).equals("add")
                    		||((String)request.getAttribute("ACTION")).equals("update")) {%>
                     <tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                        	<% if(((String)request.getAttribute("ACTION")).equals("add")) { %>
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.orderno"/></b></td>
                              <td  colspan="4"><html:text property="orderNo" size="10" maxlength="10"/></td>
                            </tr> 
                            <% } else { %>
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.orderno"/></b></td>
                              <td  class="label">
                              	<html:hidden property="orderNo" name="inventorySetupForm" />
                              	<bean:write name="inventorySetupForm" property="orderNo" />
                              </td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.orderdate"/></b></td>
                              <td   class="label">
                              	<html:hidden property="orderdate" name="inventorySetupForm" />
                              	<bean:write name="inventorySetupForm" property="orderdate" />
                              </td>                           
                            </tr> 	
                            <% } %>
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.branch"/></b></td>
                              <td >
									<html:select property="branchId" >
						      	       <html:option value=""></html:option>
						      	       <html:optionsCollection property="branchIdList" value="key" label="value" />
						      	     </html:select>
							  </td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.orderexpected"/></b></td>
                              <td ><html:text property="orderExpectedDate" size="10" onblur="javascript:isValidDate(document.inventorySetupForm.orderExpectedDate)"/></td>                           
                            </tr>  
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.cardproduct"/></b></td>
                              <td >
									<html:select property="cardproductId" >
						      	       <html:option value=""></html:option>
						      	       <html:optionsCollection property="cardProductIdList" value="key" label="value" />
						      	     </html:select>
							  </td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.orderqty"/></b></td>
                              <td ><html:text property="orderQty" size="5"/></td>                           
                            </tr>   
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.ordernote"/></b></td>
                              <td  colspan="4">
									<html:textarea property="orderNote" cols="30" rows="3" ></html:textarea>
							  </td>
                            </tr>                         
                        </table></td>
                      </tr> 
                     <% } else { %>       
                     	<tr>
                        <td><table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.orderno"/></b></td>
                              <td  class="label">
                              	<html:hidden property="orderNo" name="inventorySetupForm" />
                              	<bean:write name="inventorySetupForm" property="orderNo" />
                              </td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.orderdate"/></b></td>
                              <td   class="label"><bean:write name="inventorySetupForm" property="orderdate" /></td>                           
                            </tr> 
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.branch"/></b></td>
                              <td  class="label">
                              		<html:hidden property="branchId" name="inventorySetupForm" />
									<html:select property="branchId" disabled="true">
						      	       <html:option value=""></html:option>
						      	       <html:optionsCollection property="branchIdList" value="key" label="value" />
						      	     </html:select>
							  </td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.orderexpected"/></b></td>
                              <td  class="label">
                              	<html:hidden property="orderExpectedDate" name="inventorySetupForm" />
                              	<bean:write name="inventorySetupForm" property="orderExpectedDate" /></td>                           
                            </tr>  
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.cardproduct"/></b></td>
                              <td  class="label">
                              		<html:hidden property="cardproductId" name="inventorySetupForm" />
									<html:select property="cardproductId" name="inventorySetupForm" disabled="true">
						      	       <html:option value=""></html:option>
						      	       <html:optionsCollection property="cardProductIdList" value="key" label="value" />
						      	     </html:select>
							  </td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.orderqty"/></b></td>
                              <td  class="label">
                              	<html:hidden property="orderQty" name="inventorySetupForm" />
                              	<bean:write name="inventorySetupForm" property="orderQty"/></td>                           
                            </tr>   
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.ordernote"/></b></td>
                              <td  colspan="4" class="label">
                              		<html:hidden property="orderNote" name="inventorySetupForm" />
									<html:textarea property="orderNote" cols="30" rows="3" disabled="true" ></html:textarea>
							  </td>
                            </tr>                         
                        </table></td>
                      </tr> 
                     <% } %>
                     <!-- ----------- Show Authorized ----------------- -->
                     <% if(!((String)request.getAttribute("ACTION")).equals("add")&&
                    		 !((String)request.getAttribute("ACTION")).equals("update")&&
                    		 !((String)request.getAttribute("ACTION")).equals("cancelFromOrder")&&
                    		 !((String)request.getAttribute("ACTION")).equals("cancelOrder")&&
                    		 !((String)request.getAttribute("ACTION")).equals("searchAuthorized")&& 
                    		 !((String)request.getAttribute("ACTION")).equals("searchReceipt")) {%>
                     	<tr>
	                        <td><table cellspacing=0 cellpadding=0 border=0 style="padding-top: 20px;">
	                            <tbody>
	                              <tr>
	                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
	                                <td class=group_title background=images/tab_fond.gif><bean:message key ="inventory.grouptitle2"/></td>
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
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.batchid"/></b></td>
                              <td  class="label"><bean:write name="inventorySetupForm" property="batchId" /></td>
                              <td colspan="3"></td>
                            </tr> 
                             <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.authorizeddate"/></b></td>
                              <td  class="label"><bean:write name="inventorySetupForm" property="authorizedDate" /></td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.authorizedby"/></b></td>
                              <td   class="label"><bean:write name="inventorySetupForm" property="authorizedBy" /></td>                           
                            </tr> 
                            </table></td>
                        </tr>
                      <% } %>
                      <!-- ----------- Search Dispatch ---------------------- -->
                       <% if(((String)request.getAttribute("ACTION")).equals("searchDispatch")) { %>
                       <tr>
	                        <td><table cellspacing=0 cellpadding=0 border=0 style="padding-top: 20px;">
	                            <tbody>
	                              <tr>
	                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
	                                <td class=group_title background=images/tab_fond.gif><bean:message key ="inventory.grouptitle3"/></td>
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
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.deliverydate"/></b></td>
                              <td  class="label" colspan="4"><html:text property="delieverydate" size="10"  onblur="javascript:isValidDate(document.inventorySetupForm.delieverydate)"/></td>
                            </tr> 
                            <tr>
                            	<td colspan="2"><table>
									<tr>
										<td class="desc_cell" nowrap><b><bean:message key ="inventory.shipby"/></b></td>
                              			<td  class="label">
											<html:select property="shipBy" styleId="shipById" onchange="checkShipBy(this)">
								      	       <html:option value=""></html:option>
								      	       <html:optionsCollection property="shipByList" value="key" label="value" />
								      	     </html:select>
										</td>
										<td class="label"><div id="otherShipId" style="display: none;" ><html:text property="otherShip" /></div></td>
									</tr>  
                            	</table>
                            	</td>
                            	 <td style="width: 30px;"></td>   
                            	 <td class="desc_cell" nowrap><b><bean:message key ="inventory.trackingno"/></b></td>
                              	 <td  class="label"><html:text property="trackingNo" /></td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.cardrangefrom"/></b></td>
                              <td  class="label">
                              	<html:hidden property="cardrangeFrom" name="inventorySetupForm" />
                              	<bean:write name="inventorySetupForm" property="cardrangeFrom" /></td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.cardrangeto"/></b></td>
                              <td  class="label">
                              	<html:hidden property="cardrangeTo" name="inventorySetupForm" />
                              	<bean:write name="inventorySetupForm" property="cardrangeTo" /></td>                           
                            </tr> 
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.delieverynote"/></b></td>
                              <td  colspan="4">
									<html:textarea property="dispatchNote" cols="30" rows="3" ></html:textarea>
							  </td>
                            </tr>     
                            </table></td>
                        </tr>
                       <!-- ------------------ Show Dispatch ------------------- -->
                       <% } else if(((String)request.getAttribute("ACTION")).equals("cancelFromDispatch") ||
                    		   ((String)request.getAttribute("ACTION")).equals("searchReceived") ||
                    		   ((String)request.getAttribute("ACTION")).equals("cancelFromReceived") ||
                    		   ((String)request.getAttribute("ACTION")).equals("searchReturn") ||
                    		   ((String)request.getAttribute("ACTION")).equals("cancelFromReturn")) { %>
                       <tr>
	                        <td><table cellspacing=0 cellpadding=0 border=0 style="padding-top: 20px;">
	                            <tbody>
	                              <tr>
	                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
	                                <td class=group_title background=images/tab_fond.gif><bean:message key ="inventory.grouptitle3"/></td>
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
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.deliverydate"/></b></td>
                              <td  class="label" colspan="4"><bean:write name="inventorySetupForm" property="delieverydate" /></td>
                            </tr> 
                            <tr>
                            	<td colspan="2"><table>
									<tr>
										<td class="desc_cell" nowrap><b><bean:message key ="inventory.shipby"/></b></td>
                              			<td  class="label">
											<html:select property="shipBy" disabled="true">
								      	       <html:option value=""></html:option>
								      	       <html:optionsCollection property="shipByList" value="key" label="value" />
								      	     </html:select>
										</td>
										<td class="label"><div id="otherShipId" ><bean:write name="inventorySetupForm" property="otherShip" /></div></td>
									</tr>  
                            	</table>
                            	</td>
                            	 <td style="width: 30px;"></td>   
                            	 <td class="desc_cell" nowrap><b><bean:message key ="inventory.trackingno"/></b></td>
                              	 <td  class="label"><bean:write name="inventorySetupForm" property="trackingNo" /></td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.cardrangefrom"/></b></td>
                              <td  class="label"><bean:write name="inventorySetupForm" property="cardrangeFrom" /></td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.cardrangeto"/></b></td>
                              <td  class="label"><bean:write name="inventorySetupForm" property="cardrangeTo" /></td>                           
                            </tr> 
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.delieverynote"/></b></td>
                              <td  colspan="4">
									<html:textarea property="dispatchNote" cols="30" rows="3" disabled="true"></html:textarea>
							  </td>
                            </tr>     
                            </table></td>
                        </tr>
                       <% } %>
                       <% if(((String)request.getAttribute("ACTION")).equals("searchReceived")) { %>
                       <tr>
	                        <td><table cellspacing=0 cellpadding=0 border=0 style="padding-top: 20px;">
	                            <tbody>
	                              <tr>
	                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
	                                <td class=group_title background=images/tab_fond.gif><bean:message key ="inventory.grouptitle4"/></td>
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
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.receivedquantity"/></b></td>
                              <td colspan="4"><html:text property="receivedQty" size="5"/></td>
                            </tr>   
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.receivednote"/></b></td>
                              <td  colspan="4">
									<html:textarea property="receivedNote" cols="30" rows="3" ></html:textarea>
							  </td>
                            </tr> 
                            </table></td>
                            </tr>
                       <% } else if(((String)request.getAttribute("ACTION")).equals("cancelFromReceived") ||
                       			((String)request.getAttribute("ACTION")).equals("searchReturn") ||
                       			((String)request.getAttribute("ACTION")).equals("cancelFromReturn")) { %>
                       	<tr>
	                        <td><table cellspacing=0 cellpadding=0 border=0 style="padding-top: 20px;">
	                            <tbody>
	                              <tr>
	                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
	                                <td class=group_title background=images/tab_fond.gif><bean:message key ="inventory.grouptitle4"/></td>
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
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.receiveddate"/></b></td>
                              <td class="label" colspan="4"><bean:write name="inventorySetupForm" property="receiveddate" /></td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.receivedby"/></b></td>
                              <td  class="label"><bean:write name="inventorySetupForm" property="receivedBy" /></td>  
                            </tr>   
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.receivedquantity"/></b></td>
                              <td class="label" colspan="4"><bean:write name="inventorySetupForm" property="receivedQty"/></td>
                            </tr>   
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.receivednote"/></b></td>
                              <td  colspan="4">
									<html:textarea name="inventorySetupForm" property="receivedNote" cols="30" rows="3" disabled="true"></html:textarea>
							  </td>
                            </tr> 
                            </table></td>
                            </tr>
                       <% } %>
                       <% if(((String)request.getAttribute("ACTION")).equals("searchReturn")) { %>
                       <tr>
	                        <td><table cellspacing=0 cellpadding=0 border=0 style="padding-top: 20px;">
	                            <tbody>
	                              <tr>
	                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
	                                <td class=group_title background=images/tab_fond.gif><bean:message key ="inventory.grouptitle5"/></td>
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
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.returnquantity"/></b></td>
                              <td colspan="4"><html:text property="returnQty" size="5"/></td>
                            </tr>   
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.returnnote"/></b></td>
                              <td  colspan="4">
									<html:textarea property="returnNote" cols="30" rows="3" ></html:textarea>
							  </td>
                            </tr> 
                            </table></td>
                            </tr>
                        <% } else if(((String)request.getAttribute("ACTION")).equals("cancelFromReturn")) { %>
                       	<tr>
	                        <td><table cellspacing=0 cellpadding=0 border=0 style="padding-top: 20px;">
	                            <tbody>
	                              <tr>
	                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
	                                <td class=group_title background=images/tab_fond.gif><bean:message key ="inventory.grouptitle5"/></td>
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
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.returndate"/></b></td>
                              <td class="label" colspan="4"><bean:write name="inventorySetupForm" property="returndate" /></td>
                              <td style="width: 30px;"></td>   
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.returnby"/></b></td>
                              <td  class="label"><bean:write name="inventorySetupForm" property="returnBy" /></td>  
                            </tr>   
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.returnquantity"/></b></td>
                              <td class="label" colspan="4"><bean:write name="inventorySetupForm" property="returnQty"/></td>
                            </tr>   
                            <tr>
                              <td class="desc_cell" nowrap><b><bean:message key ="inventory.returnnote"/></b></td>
                              <td  colspan="4">
									<html:textarea name="inventorySetupForm" property="returnNote" cols="30" rows="3" disabled="true"></html:textarea>
							  </td>
                            </tr> 
                            </table></td>
                            </tr>
                       <% } %>
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
        					<html:button property="submitbutton" onclick="go('order')" ><bean:message key ="common.order"/></html:button>
        				    <html:button property="submitbutton" onclick ="showList('order')"><bean:message key ="common.cancel"/></html:button>
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.update"/></html:button>             					
        					<html:button property="submitbutton" onclick ="showList('order')"><bean:message key ="common.cancel"/></html:button>    
        				<% } else if(((String)request.getAttribute("ACTION")).equals("searchAuthorized"))		
        				   { %>
        					<html:button property="submitbutton" onclick="go('authorized')"><bean:message key ="inventory.authorized"/></html:button>    
        					<html:button property="submitbutton" onclick ="go('cancelOrder')"><bean:message key ="inventory.cancelorder"/></html:button>         					
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="common.cancel"/></html:button>    
        				<% } else if(((String)request.getAttribute("ACTION")).equals("searchReceipt"))		
        				    { %>
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="inventory.receipt"/></html:button>         					
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="common.cancel"/></html:button>         					
        				<% } else if(((String)request.getAttribute("ACTION")).equals("searchDispatch"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('dispatch')"><bean:message key ="inventory.dispatch"/></html:button>    
        					<html:button property="submitbutton" onclick ="showList('dispatch')"><bean:message key ="common.cancel"/></html:button>  
        				<% } else if(((String)request.getAttribute("ACTION")).equals("searchReceived"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('received')"><bean:message key ="inventory.receivedorder"/></html:button>    
        					<html:button property="submitbutton" onclick ="showList('received')"><bean:message key ="common.cancel"/></html:button>         	     					
       					<% } else if(((String)request.getAttribute("ACTION")).equals("searchReturn"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('returnOrder')"><bean:message key ="inventory.return"/></html:button>    
        					<html:button property="submitbutton" onclick ="showList('return')"><bean:message key ="common.cancel"/></html:button>         	     					
       					<%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromOrder")) {%>		
        					<html:button property="submitbutton" onclick ="showList('order')"><bean:message key ="common.cancel"/></html:button>             					
  					    <%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromAuthorized")) {%>		
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="common.cancel"/></html:button>             					
  					    <%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromDispatch")) {%>		
        					<html:button property="submitbutton" onclick ="showList('dispatch')"><bean:message key ="common.cancel"/></html:button>         
        				<%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromReceived")) {%>		
        					<html:button property="submitbutton" onclick ="showList('received')"><bean:message key ="common.cancel"/></html:button>     					
  					  	<%} else if(((String)request.getAttribute("ACTION")).equals("cancelFromReturn")) {%>		
    						<html:button property="submitbutton" onclick ="showList('return')"><bean:message key ="common.cancel"/></html:button>     					
					  	<%} else if(((String)request.getAttribute("ACTION")).equals("cancelOrder")) {%>		
        					<html:button property="submitbutton" onclick ="showList('authorized')"><bean:message key ="common.cancel"/></html:button>     					
  					  		<%} %>	

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