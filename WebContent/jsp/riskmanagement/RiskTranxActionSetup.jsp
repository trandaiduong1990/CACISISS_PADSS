<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title><bean:message key ="risktranxactionsetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/cacis.js"></script>
</head>
<script>
function go(method) 
{
  
  document.forms[0].method.value=method;
  document.forms[0].submit();
  
}

function addNew()
{
 
 document.forms[0].action="risktranxactionprocess.do?method=addNew";
 document.forms[0].submit();
}

function load(param)
{
  
  //alert(document.forms[0].riskId.selectedIndex);  
  if(document.forms[0].riskId.selectedIndex == 0){
  for (intIndex = 1; intIndex < document.forms[0].smsAlert.options.length; intIndex++)
  {
        
        //alert(document.forms[0].smsAlert.options[intIndex]);
  	var IndVal = document.forms[0].smsAlert.options[intIndex].value;
  	if(IndVal=='N')
  	document.forms[0].smsAlert.options[intIndex].selected = true;  	
  	
  }
  }
  show(document.forms[0].action);
}

function show(obj)
{
  
  var st = obj.options[obj.selectedIndex].value  
  //alert(st);
  if(st==3){
  document.getElementById('content1').style.display='inline';
  }else{
  document.forms[0].email.value = "";
  document.getElementById('content1').style.display='none';
  }

}

</script>

<body onLoad="load(this)">
 <html:form action="risktranxactionprocess.do"> 
 <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
 <input type=hidden name="method"/>
 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="risktranxactionsetup.screentitle"/></td>
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
						<bean:message key ="risktranxactionsetup.grouptitle"/>                        </td>
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
        					<html:button property="submitbutton" onclick="go('add')"><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick="go('search')"><bean:message key ="common.cancel"/></html:button>
        					
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>        					
       						<html:button property="submitbutton" onclick="go('search')"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick="go('search')"><bean:message key ="common.cancel"/></html:button>
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
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;">
                  <table cellspacing=0 cellpadding=0 
                              border=0>
                    <tbody> 
                    <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
                                <td nowrap class=group_title background=images/tab_fond.gif><bean:message key ="risktranxactionsetup.grouptitle"/></td>
                                <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td bgcolor=#dce5ea><div style="FONT-SIZE: 1px">&nbsp;</div></td>
                      </tr>                     
                      <tr> 
		        <td class="desc_cell" nowrap><b><bean:message key ="risktranxactionsetup.riskid"/></b></td>
		          <td valign="top">
		      	   <html:select property="riskId" >
		      	     <html:option value=""></html:option>
		      	     <html:optionsCollection property="riskIdList" value="key" label="value" />
		      	   </html:select>
		        </td>
                      </tr>   
                     <tr> 
		        <td class="desc_cell" nowrap><b><bean:message key ="risktranxactionsetup.action"/></b></td>
		          <td valign="top">
		      	   <html:select property="action" onchange="show(this)">		      	    
		      	     <html:option value=""></html:option>
		      	     <html:optionsCollection property="actionList" value="key" label="value" />
		      	   </html:select>
		        </td>
                      </tr>                                                             
                     <tbody id ="content1" style="display:none">			                            
		      <tr>
		        <td class="desc_cell" nowrap><b><bean:message key ="risktranxactionsetup.email"/></b></td>
		        <td valign="top"><html:text property="email"  size="20" onkeypress="return disableEnterKey(event)"/></td>
		        <td>&nbsp;</td>                              
		      </tr>                            		                                 
		     </tbody>   		    		   
                     <tr> 
		        <td class="desc_cell" nowrap><b><bean:message key ="risktranxactionsetup.status"/></b></td>
		          <td valign="top">
		      	   <html:select property="status" >
		      	     <html:option value=""></html:option>
		      	     <html:optionsCollection property="statusList" value="key" label="value" />
		      	   </html:select>
		        </td>
                      </tr>    
                     <tr> 
		        <td class="desc_cell" nowrap><b><bean:message key ="risktranxactionsetup.smsalert"/></b></td>
		          <td valign="top">
		      	   <html:select property="smsAlert">		      	     
		      	     <html:optionsCollection property="smsAlertList" value="key" label="value" />
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
        					<html:button property="submitbutton" onclick="go('add')"><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick="go('search')"><bean:message key ="common.cancel"/></html:button>
        					
        				 <% }else if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>        					
       						<html:button property="submitbutton" onclick="go('search')"><bean:message key ="common.cancel"/></html:button>
        				   <% }else if(((String)request.getAttribute("ACTION")).equals("cancel"))		
        				    { %>  	
        					<html:button property="submitbutton" onclick="go('search')"><bean:message key ="common.cancel"/></html:button>
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