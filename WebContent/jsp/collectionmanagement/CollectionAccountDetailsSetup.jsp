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
          document.forms[0].action= "collectionaccountdetailslist.do?method=List";
          document.forms[0].submit();
       } 
      
    function checkInstallmentPayment() {
    	var installmentPayment = document.forms[0].installmentPayment.checked;
		if(installmentPayment==true) {
			$("#noOfInstallmentE").show();
			$("#noOfInstallmentD").hide();
			$("#minPaymentAmtE").show();
			$("#minPaymentAmtD").hide();
			$("#interestRateE").show();
			$("#interestRateD").hide();
			$("#currentInstallmentNoE").show();
			$("#currentInstallmentNoD").hide();
			$("#noteE").show();
			$("#noteD").hide();
		} else {
			$("#noOfInstallmentE").hide();
			$("#noOfInstallmentD").show();
			$("#minPaymentAmtE").hide();
			$("#minPaymentAmtD").show();
			$("#interestRateE").hide();
			$("#interestRateD").show();
			$("#currentInstallmentNoE").hide();
			$("#currentInstallmentNoD").show();
			$("#noteE").hide();
			$("#noteD").show();
			
			
		} 	
    }
    
    function changeAssign(assigned) {
    	var colectRef = document.forms[0].colectRef.value;
		if (colectRef == "B") {
			$("#agentId").hide();
			$("#userId").show();
		} else {
			$("#agentId").show();
			$("#userId").hide();
		}
    }
    
	$(document).ready(function() {
		changeAssign();
		checkInstallmentPayment();
	});

	function countCharacters(id,myelement){
		var fieldLength = 200;
		
		field = document.getElementById(myelement);
		fieldfield_length = field.value.length;
		
		counter = document.getElementById(id);
		
		if (parseInt(fieldfield_length) <= fieldLength){
			remaining_characters = fieldLength-fieldfield_length; 
			counter.innerHTML = '(Left. '+remaining_characters+')';;  
		}else{
			field.value = field.value.substring(0, fieldLength);
		}
	 }
</script>
<%@ include file="/jsp/common/delete.jsp"%>
<style type="text/css">
.padLeft{padding-left: 4px}
</style>
</head>
<body bgcolor="ffffff">
 <html:form action="collectionaccountdetailsprocess.do">
<input type="hidden" name="method"/>
<%-- <bean:define id="colectRef" name="collectionAccountDetailsSetupForm" property="colectRef"/>
<html:hidden property="colectRef" value="<%=(String)colectRef%>"/> --%>
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
            <td class="texteMenuGauche" colspan="3"><div align="right">&nbsp;<a href='javascript:helpLink("WRITEOFF")'>Help</a></div>
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
					    if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
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
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0 width="122">
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="codetail.grouptitle1"/></td>
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
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.collectionId"/></b></td>
                              <td class="label" >
                              	<html:hidden property="colectId" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="colectId"/>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.orgCollectionAmt"/></b></td>
                              <td class="label">
                              	<html:hidden property="orgColectionAmt" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="orgColectionAmt"/>
                              </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.cardnumber"/></b></td>
                              <td class="label" >
                              	<html:hidden property="cardNo" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="cardNo"/>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.customername"/></b></td>
                              <td class="label" >
                              	<html:hidden property="customerName" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="customerName"/>
                              </td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.creditLimit"/></b></td>
                              <td class="label" >
                              	<html:hidden property="creditLimit" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="creditLimit"/>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.dueAmt"/></b></td>
                              <td class="label" >
                              	<html:hidden property="dueAmt" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="dueAmt"/>
                              </td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.status"/></b></td>
                              <td class="label" >
                              	<html:hidden property="status" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="status"/>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.reclassification"/></b></td>
                              <td class="label">
                              	<html:hidden property="reclassification" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="reclassification"/>
                              </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.asignedTo"/></b></td>
                              <td valign="middle">
                              	<table>
                              		<tr><td class="label" >
                              			<html:radio property="colectRef" value="A" onclick="changeAssign('A')" /> 
										<bean:message key="codetail.agent" />
                              		</td></tr>
                              		<tr><td class="label" >
                              			<html:radio property="colectRef" value="B" onclick="changeAssign('B')" /> 
										<bean:message key="codetail.branch" />
                              		</td></tr>
                              	</table>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.collectorAssigned"/></b></td>
                              <td class="label">
                              	<div id="agentId">
                              		<html:select property="currentCollector1">
				      	       			<html:option value=""></html:option>
				      	       			<html:optionsCollection property="agentList" value="key" label="value" />
				      	     		</html:select>
                              	</div>
                              	<div id="userId">
                              		<html:select property="currentCollector2">
				      	       			<html:option value=""></html:option>
				      	       			<html:optionsCollection property="userList" value="key" label="value" />
				      	     		</html:select>
                              	</div>
                              </td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.dateAssigned"/></b></td>
                              <td class="label" >
                              	<html:hidden property="dateAssigned" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="dateAssigned"/>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.amountAssigned"/></b></td>
                              <td class="label" >
                              	<html:hidden property="amountAssigned" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="amountAssigned"/>
                              </td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.writeOffAmt"/></b></td>
                              <td class="label" >
                              	<html:hidden property="writeOffAmt" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="writeOffAmt"/>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.writeOffDate"/></b></td>
                              <td class="label" >
                              	<html:hidden property="writeOffDate" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="writeOffDate"/>
                              </td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.recoveryAmt"/></b></td>
                              <td class="label" >
                              	<html:hidden property="recoveryAmt" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="recoveryAmt"/>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.lastRecoveryDate"/></b></td>
                              <td class="label">
                              	<html:hidden property="lastRecoveryDate" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="lastRecoveryDate"/>
                              </td>
                            </tr>
                             <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.amountToRecover"/></b></td>
                              <td class="label" >
                              	<html:hidden property="amountToRecover" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="amountToRecover"/>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.recovedFullDate"/></b></td>
                              <td class="label">
                              	<html:hidden property="recovedFullDate" name="collectionAccountDetailsSetupForm" />
							    <bean:write name="collectionAccountDetailsSetupForm" property="recovedFullDate"/>
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
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table cellspacing=0 cellpadding=0 
                              border=0 width="122">
                    <tbody>
                      <tr>
                        <td><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td><img height=19 alt="" src="images/tab_g.gif" border=0></td>
                                <td class=group_title background=images/tab_fond.gif><bean:message key ="codetail.grouptitle2"/></td>
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
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.installmentPayment"/></b></td>
                              <td class="label">
                              	<html:checkbox property ="installmentPayment" value="Y" onclick="checkInstallmentPayment()"/>
                              </td>
                              <td colspan="3"></td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.noOfInstallment"/></b></td>
                              <td class="label">
                              	<div id="noOfInstallmentE">
							    	<html:text property="noOfInstallment" size="8"></html:text>
							    </div>
							    <div id="noOfInstallmentD">
							    	<html:text property="noOfInstallment" size="8" disabled="true"></html:text>
							    </div>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.minPaymentAmt"/></b></td>
                              <td class="label">
							    <div id="minPaymentAmtE">
							    	<html:text property="minPaymentAmt" size="8"></html:text>
							    </div>
							    <div id="minPaymentAmtD">
							    	<html:text property="minPaymentAmt" size="8" disabled="true"></html:text>
							    </div>
                              </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.interestRate"/></b></td>
                              <td class="label">
							    <div id="interestRateE">
							    	<html:text property="interestRate" size="8"></html:text>
							    </div>
							    <div id="interestRateD">
							    	<html:text property="interestRate" size="8" disabled="true"></html:text>
							    </div>
                              </td>
                              <td>&nbsp;</td>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.currentInstallmentNo"/></b></td>
                              <td class="label">
                              	<div id="currentInstallmentNoE">
							    	<html:text property="currentInstallmentNo" size="8"></html:text>
							    </div>
							    <div id="currentInstallmentNoD">
							    	<html:text property="currentInstallmentNo" size="8" disabled="true"></html:text>
							    </div>
                              </td>
                            </tr>
                            <tr>
                              <td class="desc_cell" nowrap ><b><bean:message key ="codetail.note"/></b></td>
                              <td class="label" colspan="4">
                              	<div style="float: left;">
                              		<div id="noteE">
									<html:textarea styleId="remarks" style="resize:none;" property="note" cols="30" rows="3" onkeyup="javascript:countCharacters('demoReply3','remarks');" />
							    </div>
							    <div id="noteD">
									<html:textarea disabled="true" styleId="remarks" style="resize:none;" property="note" cols="30" rows="3" onkeyup="javascript:countCharacters('demoReply3','remarks');" />
							    </div>
								</div>
                                <div style="color: green;width: 70px;float: left;">
                                    <bean:message key ="tranxenquiry.view.mr.remarks.help"/>
                                    <div id="demoReply3" style="color: green;width: 70px;padding-top: 20px;"></div>
                                </div>
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
					    if(((String)request.getAttribute("ACTION")).equals("change") || ((String)request.getAttribute("ACTION")).equals("update"))		
        				    { %>
        					<html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.save"/></html:button>
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