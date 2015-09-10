<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key ="customertypesetup.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script src="inc/js/jquery-1.6.3.js"></script>
<style type="text/css">
	.menu_item {
	PADDING-RIGHT: 5px; PADDING-LEFT: 5px; FONT-WEIGHT:bold; FONT-SIZE: 11px; BACKGROUND: #dce5ea; PADDING-BOTTOM: 2px; COLOR: #002d45; PADDING-TOP: 2px; BORDER-BOTTOM: #ffffff 1px solid; FONT-FAMILY: verdana
}
	.desc_cell2 {font-family: verdana;font-size: 11px;}
</style>
<script language="JavaScript">
   
	
  //function: Assignment
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
<%@ include file="/jsp/common/delete.jsp"%>
</head>
<script>
function go(action) {
    document.forms[0].method.value=action;
    for (var intIndex = 0; intIndex < document.forms[0].selMccList.options.length; intIndex++)
    {
    	document.forms[0].selMccList.options[intIndex].selected = true;
    }
    document.forms[0].submit();
 }
 
function addNew() {
	document.forms[0].action="cardproductrulesprocess.do?method=addNew";
   	document.forms[0].submit();
}

 function showList(){
       document.forms[0].action= "cardproductruleslist.do?method=List";
       document.forms[0].submit();
 }
</script>
<body bgcolor="ffffff">
<html:form action="cardproductrulesprocess.do">
<input type="hidden" name="method"/>
<html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
<html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
<logic:present scope="request" name="ACTION">
	<logic:equal name="ACTION" value="update">
		<html:hidden property="cardProductId" name="cardProductRulesSetupForm" />
	</logic:equal>
</logic:present>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> <td class="titreSection" width="504"><bean:message key ="cardproductrules.screentitle"/></td>
          <td class="titreSection" width="455">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td colspan="2" class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
              <tr>
               <td><html:button property="submitbutton"  onclick ="addNew()"><bean:message key ="common.addnew"/></html:button><br></td>
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
                          <bean:message key ="cardproductrules.group"/></td>
                        <td><img src="images/form_tab_end_on.gif" border="0" alt="" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                    	<% if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add")) { %>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } else if(((String)request.getAttribute("ACTION")).equals("update")) { %>
        					<html:button property="submitbutton" onclick="go('update')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } else if(((String)request.getAttribute("ACTION")).equals("cancel")) { %>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } %>
                    </div>                 
                  </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td style="padding: 15px 5px 5px 5px;" valign="top">
					  	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse;" >
							<tr>
								<% if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add")) { %>
								<td nowrap><span class="menu_item"><bean:message	key="cardproductrules.cardproduct" /> </span>
									<html:select property="cardProductId">
										<html:option value=""></html:option>	
										<html:optionsCollection property="cardProductList" value="key"
											label="value" />
									</html:select>
								</td>
								<% } else if(((String)request.getAttribute("ACTION")).equals("update") || ((String)request.getAttribute("ACTION")).equals("cancel")) { %>
								<td><span class="menu_item"><bean:message	key="cardproductrules.cardproduct" /></span>
									<html:select property="cardProductId"  disabled="true">
										<html:option value=""></html:option>	
										<html:optionsCollection property="cardProductList" value="key"
											label="value" />
									</html:select>
								</td>
								<% } %>
							</tr>
							<tr>
								<td>
									<span class="menu_item"><bean:message key="cardproductrules.cardactivation"/></span>
									<span class="desc_cell2">
									<logic:equal name="cardProductRulesSetupForm" property="caDelivery" value="Y">
					                   	<input type="checkbox" value="Y" name ="caDelivery" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="caDelivery" value="Y">
					                    <input type="checkbox" value="Y" name ="caDelivery"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.delivery"/>
									<logic:equal name="cardProductRulesSetupForm" property="caTerminal" value="Y">
					                   	<input type="checkbox" value="Y" name ="caTerminal" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="caTerminal" value="Y">
					                    <input type="checkbox" value="Y" name ="caTerminal"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.terminal"/>
									<logic:equal name="cardProductRulesSetupForm" property="caEmbPINGen" value="Y">
					                   	<input type="checkbox" value="Y" name ="caEmbPINGen" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="caEmbPINGen" value="Y">
					                    <input type="checkbox" value="Y" name ="caEmbPINGen"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.emboss"/>
									<logic:equal name="cardProductRulesSetupForm" property="ca3rdInterface" value="Y">
					                   	<input type="checkbox" value="Y" name ="ca3rdInterface" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="ca3rdInterface" value="Y">
					                    <input type="checkbox" value="Y" name ="ca3rdInterface"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.web"/></span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="desc_cell"><bean:message key="cardproductrules.deactive"/></span>
									<html:text property="caDaDays" size="10"/>
								</td>
							</tr>
							<tr>
								<td>
									<span class="menu_item"><bean:message key="cardproductrules.carddeliverymode"/></span>
									<span class="desc_cell2">
									<logic:equal name="cardProductRulesSetupForm" property="cdBranch" value="Y">
					                   	<input type="checkbox" value="Y" name ="cdBranch" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="cdBranch" value="Y">
					                    <input type="checkbox" value="Y" name ="cdBranch"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.branch"/>
									
									<logic:equal name="cardProductRulesSetupForm" property="cdMail" value="Y">
					                   	<input type="checkbox" value="Y" name ="cdMail" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="cdMail" value="Y">
					                    <input type="checkbox" value="Y" name ="cdMail"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.mail"/></span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="menu_item"><bean:message key="cardproductrules.cardblocking"/></span>
									<span class="desc_cell2">
									<logic:equal name="cardProductRulesSetupForm" property="cbLost" value="Y">
					                   	<input type="checkbox" value="Y" name ="cbLost" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="cbLost" value="Y">
					                    <input type="checkbox" value="Y" name ="cbLost"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.lostcard"/>

									<logic:equal name="cardProductRulesSetupForm" property="cbStolen" value="Y">
					                   	<input type="checkbox" value="Y" name ="cbStolen" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="cbStolen" value="Y">
					                    <input type="checkbox" value="Y" name ="cbStolen"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.stolencard"/></span>
								</td>
							</tr>
							<tr>
								<td class="menu_item">
									<bean:message key="cardproductrules.cardreplacement"/>
								</td>
							</tr>
							<tr>
								<td>
									<div style="padding-left: 30px;"><span class="desc_cell2" >
									<logic:equal name="cardProductRulesSetupForm" property="crOcUsage" value="Y">
					                   	<input type="checkbox" value="Y" name ="crOcUsage" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="crOcUsage" value="Y">
					                    <input type="checkbox" value="Y" name ="crOcUsage"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.crocusage"/> <br>
									
									<logic:equal name="cardProductRulesSetupForm" property="crOcRep" value="Y">
					                   	<input type="checkbox" value="Y" name ="crOcRep" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="crOcRep" value="Y">
					                    <input type="checkbox" value="Y" name ="crOcRep"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.crocrep"/> <br>
									
									<logic:equal name="cardProductRulesSetupForm" property="crOcNc" value="Y">
					                   	<input type="checkbox" value="Y" name ="crOcNc" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="crOcNc" value="Y">
					                    <input type="checkbox" value="Y" name ="crOcNc"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.crnodays"/>
									<html:text  property="crNodays" size="10"></html:text></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<span class="menu_item"><bean:message key="cardproductrules.pinmailerdelivery"/></span>
									<span class="desc_cell2">
									<logic:equal name="cardProductRulesSetupForm" property="pdDelivery" value="Y">
					                   	<input type="checkbox" value="Y" name ="pdDelivery" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="pdDelivery" value="Y">
					                    <input type="checkbox" value="Y" name ="pdDelivery"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.mailer"/> 
									
									<logic:equal name="cardProductRulesSetupForm" property="pdTerminal" value="Y">
					                   	<input type="checkbox" value="Y" name ="pdTerminal" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="pdTerminal" value="Y">
					                    <input type="checkbox" value="Y" name ="pdTerminal"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.terminal"/> 
									
									<logic:equal name="cardProductRulesSetupForm" property="pdBranch" value="Y">
					                   	<input type="checkbox" value="Y" name ="pdBranch" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="pdBranch" value="Y">
					                    <input type="checkbox" value="Y" name ="pdBranch"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.branch"/> </span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="desc_cell"><bean:message key="cardproductrules.pddadays"/></span>
									<html:text property="pdDaDays" size="10"/>
								</td>
							</tr>
							<tr>
								<td class="menu_item">
									<bean:message key="cardproductrules.fallbackrules"/>
								</td>
							</tr>
							<tr>
								<td>
									<span class="desc_cell"><bean:message key="cardproductrules.fallbackallowed"/></span>
									<span class="desc_cell2">
									<logic:equal name="cardProductRulesSetupForm" property="fbDom" value="Y">
					                   	<input type="checkbox" value="Y" name ="fbDom" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="fbDom" value="Y">
					                    <input type="checkbox" value="Y" name ="fbDom"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.domestic"/> 
									
									<logic:equal name="cardProductRulesSetupForm" property="fbIntl" value="Y">
					                   	<input type="checkbox" value="Y" name ="fbIntl" checked="checked"/>
					                </logic:equal>
					                <logic:notEqual name="cardProductRulesSetupForm" property="fbIntl" value="Y">
					                    <input type="checkbox" value="Y" name ="fbIntl"/>
					                 </logic:notEqual>
									<bean:message key="cardproductrules.international"/></span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="desc_cell"><bean:message key="cardproductrules.nofballowed"/></span>
									<html:text property="fbNoTranx" size="10"/>
									<span class="desc_cell"><bean:message key="cardproductrules.maxtranxamt"/></span>
									<html:text property="fbTranxMaxAmt" size="10"/>
								</td>
							</tr>
							<tr>
								<td>
									<span class="desc_cell"><bean:message key="cardproductrules.sendsms"/></span>
					                <html:radio property="fbSms" value="Y" /> 
					                <span class="desc_cell2"><bean:message key="cardproductrules.sendsmsyes"/></span>   	
					                <html:radio property="fbSms" value="N" /> 
					                <span class="desc_cell2"><bean:message key="cardproductrules.sendsmsno"/></span>   	
								</td>
							</tr>
							<tr>
								<td class="menu_item">
									<bean:message key="cardproductrules.offlinerules"/>
								</td>
							</tr>
							<tr>
								<td>
									<span class="desc_cell"><bean:message key="cardproductrules.minofflineamt"/></span>
									<html:text property="olMinAmt" size="10"/> 
								</td>
							</tr>
							<tr>
								<td>
									<span class="desc_cell"><bean:message key="cardproductrules.morethanmin"/></span>
								</td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
				                            <td valign="top">
				                            	<html:select property="mccMasterList" multiple="multiple" style="width:250px" size="15" styleId="firstList">
					                                <html:optionsCollection property="mccMasterList" value="key" label="value" /> 
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
				                            	<html:select property="selMccList" multiple="multiple" style="width:250px" size="15" styleId="secondList">
					                                <html:optionsCollection property="selectedListSet" value="key" label="value" /> 
				                              	</html:select></td>
				                          </tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
                 </tr>
                    
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
        				<% if(request.getAttribute("ACTION") == null || ((String)request.getAttribute("ACTION")).equals("add")) { %>
        					<html:button property="submitbutton" onclick="go('add')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } else if(((String)request.getAttribute("ACTION")).equals("update")) { %>
        					<html:button property="submitbutton" onclick="go('update')" ><bean:message key ="common.save"/></html:button>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } else if(((String)request.getAttribute("ACTION")).equals("cancel")) { %>
        					<html:button property="submitbutton" onclick ="showList()"><bean:message key ="common.cancel"/></html:button>
        				<% } %>
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

