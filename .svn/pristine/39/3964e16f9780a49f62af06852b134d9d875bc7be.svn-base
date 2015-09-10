<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title><bean:message key ="keyindexsetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/keyindexsetup.js"></script>
<script src="inc/js/cacis.js"></script>
</head>
<script>
function go(method) 
{
  document.forms[0].method.value=method;
  document.forms[0].submit();	
}
function showList()
{
 
 //document.forms[0].action="keyindexlist.do?method=search";
 document.forms[0].action="keyindexlist.do";
 document.forms[0].submit();
}

</script>
<body onload="disTransactionChannel();">
 <html:form action="keyindexprocess.do">
 <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
 <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
 <input type=hidden name="method"/>
 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="keyindexsetup.screentitle"/></td>
            <td class="titreSection" width="484">&nbsp; </td>
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
						<bean:message key ="keyindexsetup.grouptitle1"/>                        </td>
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
        					<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>        					
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>     
        					<!-- <html:button property="submitbutton" onclick="go('delete')"><bean:message key ="common.delete"/></html:button> -->
       						<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>        					
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>        					
        				    <%}%>		
  					  
                    </div>                  
			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
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
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="keyindexsetup.grouptitle1"/></td>
                                <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                      </tr>                      
                      <tr> 
		        <td class="desc_cell" nowrap ><b><bean:message key ="keyindexsetup.cardproductid"/> * </b></td>
		          <td valign="top">
		      	   <logic:present scope="request" name="ACTION">
                    	<logic:equal name="ACTION" value="change">
                    		<html:hidden property="cardProductId" name="keyIndexForm" />
                    		<html:hidden property="cardProductName" name="keyIndexForm" />
		      	   			<bean:write name="keyIndexForm" property="cardProductName"/>
                    	</logic:equal>
                    	<logic:equal name="ACTION" value="update">
                    		<html:hidden property="cardProductId" name="keyIndexForm" />
                    		<html:hidden property="cardProductName" name="keyIndexForm" />
		      	   			<bean:write name="keyIndexForm" property="cardProductName"/>
                    	</logic:equal>
                    	<logic:equal name="ACTION" value="add">
                    		<html:select property="cardProductId" >
				      	     <html:option value=""></html:option>
				      	     <html:optionsCollection property="cardProductList" value="key" label="value" />
				      	   </html:select>
                    	</logic:equal>
                    </logic:present>
                    <logic:present scope="request" name="ACTION">
                    	<logic:equal name="ACTION" value="cancel">
                    		<html:hidden property="cardProductId" name="keyIndexForm" />
                    		<html:hidden property="cardProductName" name="keyIndexForm" />
		      	   			<bean:write name="keyIndexForm" property="cardProductName"/>
                    	</logic:equal>
	                </logic:present>
	                <logic:notPresent scope="request" name="ACTION">
	                	<html:select property="cardProductId" >
				      	     <html:option value=""></html:option>
				      	     <html:optionsCollection property="cardProductList" value="key" label="value" />
				      	</html:select>
                    </logic:notPresent>
		        </td>
                      </tr>                     
                     <tr>
                       <td class="desc_cell" nowrap><b><bean:message key ="keyindexsetup.keytype"/> * </b></td>
                       <td valign="top">
			      	   <logic:present scope="request" name="ACTION">
                    	<logic:equal name="ACTION" value="change">
                    		<html:hidden property="keyType" name="keyIndexForm" />
		      	   			<bean:write name="keyIndexForm" property="keyType"/>
                    	</logic:equal>
                    	<logic:equal name="ACTION" value="update">
                    		<html:hidden property="keyType" name="keyIndexForm" />
		      	   			<bean:write name="keyIndexForm" property="keyType"/>
                    	</logic:equal>
                    	<logic:equal name="ACTION" value="add">
                    		<html:select property="keyType" styleId="keyType" onchange="disTransactionChannel();">
				      	     	<html:option value=""></html:option>
				      	    <html:optionsCollection property="keyTypeList" value="key" label="value" />
			      	   </html:select>
                    	</logic:equal>
                    </logic:present>
                    <logic:present scope="request" name="ACTION">
                    	<logic:equal name="ACTION" value="cancel">
                    		<html:hidden property="keyType" name="keyIndexForm" />
		      	   			<bean:write name="keyIndexForm" property="keyType"/>
                    	</logic:equal>
	                </logic:present>
	                <logic:notPresent scope="request" name="ACTION">
	                	<html:select property="keyType" styleId="keyType" onchange="disTransactionChannel();">
			      	     	<html:option value=""></html:option>
			      	     <html:optionsCollection property="keyTypeList" value="key" label="value" />
			      	   </html:select>
                    </logic:notPresent>
                       </td>                            
                     </tr>
                     <tr>
                       <td class="desc_cell" nowrap><b><bean:message key ="keyindexsetup.tranxchannel"/></b></td>
                       <td valign="top">
				      	<logic:present scope="request" name="ACTION">
	                    	<logic:equal name="ACTION" value="change">
	                    		<html:hidden property="transactionChannel" name="keyIndexForm" />
			      	   			<bean:write name="keyIndexForm" property="transactionChannel"/>
	                    	</logic:equal>
	                    	<logic:equal name="ACTION" value="update">
	                    		<html:hidden property="transactionChannel" name="keyIndexForm" />
			      	   			<bean:write name="keyIndexForm" property="transactionChannel"/>
	                    	</logic:equal>
	                    	<logic:equal name="ACTION" value="add">
	                    		<html:select property="transactionChannel" styleId="transactionChannel">
				      	     		<html:option value=""></html:option>
					      	    	<html:optionsCollection property="tranxChannelList" value="key" label="value" />
				      	   		</html:select>
	                    	</logic:equal>
	                    </logic:present>
	                    <logic:present scope="request" name="ACTION">
	                    	<logic:equal name="ACTION" value="cancel">
	                    		<html:hidden property="transactionChannel" name="keyIndexForm" />
			      	   			<bean:write name="keyIndexForm" property="transactionChannel"/>
	                    	</logic:equal>
		                </logic:present>
		                <logic:notPresent scope="request" name="ACTION">
		                	<html:select property="transactionChannel" styleId="transactionChannel">
				      	     	<html:option value=""></html:option>
				      	    	<html:optionsCollection property="tranxChannelList" value="key" label="value" />
				      	   	</html:select>
	                    </logic:notPresent>
                       </td>                            
                     </tr>                             
                     <tr>
                       <td class="desc_cell" nowrap width="132"><b><bean:message key ="keyindexsetup.keyindex"/> * </b></td>
                       <td valign="top"><html:text property="keyIndex" maxlength="2" size="20" onkeypress="return disableEnterKey(event)"/></td>
                       <td>&nbsp;</td>                              
                     </tr>                                                                                              
                     <tr> 
		        <td class="desc_cell" nowrap><b><bean:message key ="keyindexsetup.active"/> * </b></td>
		          <td valign="top">
		      	   <html:select property="status" >
		      	     <html:option value=""></html:option>
		      	     <html:optionsCollection property="statusList" value="key" label="value" />
		      	   </html:select>
		        </td>
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
        					<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>        					
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>     
        					<!-- <html:button property="submitbutton" onclick="go('delete')"><bean:message key ="common.delete"/></html:button> -->
       						<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>        					
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick="showList()" ><bean:message key ="common.cancel"/></html:button>        					
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