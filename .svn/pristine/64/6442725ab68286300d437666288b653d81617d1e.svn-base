
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title><bean:message key ="risktranxsalecashsetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/Transfer.js"></script>
<script src="inc/js/jquery-1.6.3.js"></script>

<script type="text/javascript">

//function: UnAssignment
function assignList()
{
	// loop through first listbox and append to second listbox
	$('#firstList :selected').each(function(i, selected){
	// append to second list box
	$('#secondList').append('<option value="'+selected.value+'">'+ selected.text+'</option>');
	// remove from first list box
	$("#firstList option[value='"+ selected.value +"']").remove();
	});
}

// function: UnAssignment
function unassignList()
{
	// loop through second listbox and append to first listbox
	$('#secondList :selected').each(function(i, selected){
	// append to first list box
	$('#firstList').append('<option value="'+selected.value+'">'+ selected.text+'</option>');
	// remove from second list box
	$("#secondList option[value='"+ selected.value +"']").remove();
	});
}

$(document).ready(function(){
	$('#firstList').dblclick(function() {
	assignList();
	});
	 
	$('#secondList').dblclick(function() {
	unassignList();
	 });
	 
	$('#to2').click(function()
	{
	assignList();
	});
	 
	$('#to1').click(function()
	{
	unassignList();
	});
	 });

</script>

<script src="inc/js/cacis.js"></script>
</head>


<script>
function go(action) 
{
	document.forms[0].action.value=action;	
	document.forms[0].submit();
}

function helpLink(screenId) {
	aBase = '<html:rewrite forward="callHelpWin"/>'; 
	HC_doOpenRemote(aBase + '?screenId=' + screenId,'Help'); 
}
// Open window 
function HC_openWin(newURL, newName, newFeatures, orgName) { 
    var newWin = open(newURL, newName, newFeatures); 
    if (newWin.opener == null) newWin.opener = window; 
    newWin.opener.name = orgName; 
    return newWin 
} 
// Open centered remote 
function HC_doOpenRemote(aURL, newName){ 
   var newWin = HC_openWin(aURL, newName); newWin.focus();
    return newWin 
}
</script>


<script>
function go(method) 
{
  document.forms[0].method.value=method;	
  
for (intIndex = 1; intIndex < document.forms[0].selMccList.options.length; intIndex++)
{
  document.forms[0].selMccList.options[intIndex].selected = true;
}	    	

document.forms[0].submit();
}
</script>
<body>
  
 <html:form action="risktranxsaleprocess.do">
 <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
 <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
 <input type=hidden name="method"/>
 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="risktranxsalecashsetup.screentitle"/></td>
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
              <td class="texteMenuGauche">&nbsp;<a href='javascript:helpLink("SPENDING")'>Help</a></td>                        
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
          <td width="482" valign="top"> 
              <table width="482" border="0" align="left" cellpadding="0" cellspacing="0">
                <tr> 
                  <td> <img src="images/form_tab_start_on.gif" border="0" alt="" width="5" height="22"></td>
                  <td background="images/tbl_haut.gif" colspan="2"> 
                    <table border="0" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td class="form_tab_on" background="images/form_tab_bg_on.gif" style="padding-left:15px"> 
						<bean:message key ="risktranxsalecashsetup.grouptitle"/></td>
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
        	  </div> </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><table cellspacing=0 cellpadding=0 border=0>
                       <tbody>
                         <tr>
                          <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
                          <td class=group_title background=images/tab_fond.gif><bean:message key ="risktranxsalecashsetup.grouptitle"/></td>
                          <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                         </tr>
                       </tbody>
                    </table></td>
                  <tr>
                      <td><html:hidden property="id"/></td> 
                  </tr>
                  <tr>
                      <td><html:hidden property="tranxCode" value="<%=request.getParameter("tranxCode")%>"/></td>
                  </tr>  
                  </tr> 
                    <tr>
                      <td class="desc_cell" nowrap width="132"><b><bean:message key ="risktranxsalecashsetup.nooftranx"/></b></td>
                      <td valign="top" width="242"><html:text property="tranxNo" size="20" onkeypress="return disableEnterKey(event)"/></td>
                      <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                      <td colspan="2" nowrap><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td class="desc_cell"><div align="center"><strong><bean:message key ="risktranxsalecashsetup.availablemcc"/></strong></div></td>
                            <td><div align="center"></div></td>
                            <td class="desc_cell"><div align="center"><strong><bean:message key ="risktranxsalecashsetup.selectedmcc"/></strong></div></td>
                          </tr>
                          <tr>
                            <td valign="top">
                            <html:select property="mccList" multiple="multiple" style="width:200px" size="5" styleId="firstList">
                                <html:option value=""></html:option>
                                <html:optionsCollection property="mccList" value="key" label="value" /> 
                            </html:select>
                            </td>
                            <td>
                            <table border="0" cellspacing="4" cellpadding="0">
                                <tr>
                                  <td class="desc_cell">
	                                  <input id="to2" type="button" name="to2"  title='assign' value="&gt;" /><br/><br/>
									  <input id="to1" type="button" name="to1" title='unassign' value="&lt;" />
								  </td>
                                </tr>
                            </table>
                            </td>
                            <td valign="top">
                            <html:select property="selMccList" multiple="multiple" style="width:200px" size="5" styleId="secondList">
                                <html:option value=""></html:option>
                                <html:optionsCollection property="selectedMcc" value="key" label="value" /> 
                            </html:select>
                            </td>
                          </tr>
                      </table></td>
                      <td>&nbsp;</td>
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
        	   </div> </td>
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