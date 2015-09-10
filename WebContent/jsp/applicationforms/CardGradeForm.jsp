<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
<head>
<title><bean:message key ="cardgradeformsetup.screentitle"/></title>
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

///to call csrDispatchAction to show all the data as popup window
	function viewAllInfo(urlToCall){
	  window.open(urlToCall, "CustServiceAllData",'resizable =yes, status=yes,scrollbars=yes,menubar=no height=400,width=400,location=no');
	  }
 ///to call different actions when click on cancel button
  function showList(url){
    document.forms[0].action = url;
    document.forms[0].submit();
   } 		  
</script>
<body bgcolor="ffffff">
<html:form  action ="cardgradeformprocess" >
<input type ="hidden" name ="method" />
  <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
   <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
  
 <!-- this for showing the search condition-->
   <logic:notPresent name ="ACTION">
  <table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="cardgradeformsetup.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157" align="left">
                <tr> 
                <td class="texteMenuGauche"> 
                  <html:button property="submitbutton" onclick="go('search')" ><bean:message key ="common.search"/></html:button> 
                 </td>
                
               </tr>
            </table>
            </td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
     <br>
      <table width="100%">
        <tr>
          <td valign="top" class="ErrFONT"><font color="#FF0000"><html:errors /></font></td>
        </tr>
        <tr> 
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="0">
             
                      <tr> 
                        <td> <font class="label"><b><bean:message key ="customerservice.cardnumber"/></b></font> 
                          <html:text property="cardNumber" maxlength ="16" size="20" onkeypress="return disableEnterKey(event)"/>
                       
                          </td>
                      </tr>
            
                 </table>
                </td>
              </tr>
            </table>
      	  </td>
        </tr>
  		<tr> 
     		<td valign="top">&nbsp; </td>
  		</tr>
	</table>
   </td>
  </tr>
</table>
</logic:notPresent>
   <!--  hiddeen fields to insert the data in varioues tables-->
  <logic:present name="ACTION" scope="request">
     <html:hidden property="cardNumber"/>
       <html:hidden property="applicationId" />
        <html:hidden property="branchId" /> 
           
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td> 
        <table border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td class="titreSection" width="519"><bean:message key ="cardgradeformsetup.screentitle"/> </td>
            <td class="titreSection" width="484">&nbsp; </td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr> 
            <td class="titreSection" width="519">&nbsp;</td>
            <td class="texteMenuGauche"><div align="right"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div></td>
        </tr>
        <tr> 
            <td background="images/ligne.gif" colspan="3"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr><td valign="top" class="ErrFONT"><font color="#FF0000"></font></td></tr>
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
						<bean:message key ="cardgradeformsetup.carddetails"/>                         </td>
                        <td><img src="images/form_tab_btw_on_off.gif" width="25" height="22"></td>
                        <td background="images/form_tab_bg_off.gif" class="form_tab_off"><a href="javascript:viewAllInfo('csrprocess.do?method=csrSummary&CustServCardNo=<bean:write name ="cardGradeForm"  property="cardNumber" />')"><bean:message key ="cardgradeformsetup.customerinfo"/></a>   </td>
                        <td><img src="images/form_tab_end_off.gif" width="25" height="22"></td>
                      </tr>
                    </table>                  </td>
                  <td> <img src="images/tbl_haut_d.gif" border="0" alt="" width="5" height="22"></td>
                </tr>
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right"> 
                       <%if(request.getAttribute("ACTION")== null || ((String)request.getAttribute("ACTION")).equals("search")){%>
        		        	   <html:button property="submitbutton" onclick="go('add')" > <bean:message key ="common.save"/></html:button>
        		        	   <html:button property="submitbutton" onclick="showList('cardgradeformsetup.do')"><bean:message key ="common.cancel"/></html:button>
        		        	  
        		      	<%}if(request.getAttribute("ACTION")== null || ((String)request.getAttribute("ACTION")).equals("update")){ %>
        		            <html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.update"/></html:button>
                    		<html:button property="submitbutton" onclick="go('accept')"><bean:message key ="common.accept"/></html:button>
       						<html:button property="submitbutton" onclick="go('reject')"><bean:message key ="common.reject"/></html:button>
       						<html:button property="submitbutton" onclick="showList('cardgradeformlistpage.do')"><bean:message key ="common.cancel"/></html:button>
       				   <%}if(request.getAttribute("ACTION")!= null && ((String)request.getAttribute("ACTION")).equals("cancel")){ %> 
       				  	   <html:button property="submitbutton" onclick="showList('cardgradeformsetup.do')"><bean:message key ="common.cancel"/></html:button>
       				  	     
       				    <%} %>  
                    </div>			      </td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                </tr>
                <tr>
                  <td background="images/tbl_g.gif"></td>
                  <td colspan="2" valign="top" class="form_bgcolor" style="padding: 20px 20px 10px 20px;"><table width="100%" border=0 cellpadding=0 cellspacing=0>
                    <tbody>
                      <tr>
                        <td><img height=22 alt="" src="images/form_tab_start_off.gif" width=5 border=0></td>
                        <td background=images/tbl_haut.gif><table cellspacing=0 cellpadding=0 border=0>
                            <tbody>
                              <tr>
                                <td class=form_tab_off style="PADDING-LEFT: 15px" noWrap background=images/form_tab_bg_off.gif><a href="#" onClick="expandcontent('tab1', this)"><bean:message key ="cardgradeformsetup.customer"/></a> </td>
                                <td><img height=22 alt="" src="images/form_tab_btw_off_off.gif" width=25 border=0></td>
                                <td class="form_tab_off" background="images/form_tab_bg_off.gif"> <a href="#" onClick="expandcontent('tab2', this)"><bean:message key ="cardgradeformsetup.card"/></a> </td>
                                <td><img src="images/form_tab_end_off.gif" width="25" height="22"></td>
                              </tr>
                            </tbody>
                        </table></td>
                        <td><img height=22 alt="" src="images/tbl_haut_d.gif" width=5 border=0></td>
                      </tr>
                      <tr>
                        <td background=images/tbl_g.gif></td>
                        <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td>
								  <div id="tab1">
								  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td valign="top" style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table cellspacing=0 cellpadding=0 
                              border=0>
                                        <tbody>
                                          <tr>
                                            <td><table cellspacing=0 cellpadding=0 border=0>
                                                <tbody>
                                                  <tr>
                                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardgradeform.personalinfo"/> </td>
                                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                                  </tr>
                                                </tbody>
                                            </table></td>
                                          </tr>
                                          <tr>
                                            <td bgcolor=#dce5ea><div 
                                style="FONT-SIZE: 1px">&nbsp;</div></td>
                                          </tr>
                                          <tr>
                                            <td height="21">
                                   <table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                                           <tr> 
			                      <td class="desc_cell" nowrap><bean:message key ="cardgradeform.customername"/></td>
			                      <td class="label"><html:text  property="customerName" /> </td>
			                  </tr>
			                  <tr> 
			                    <td class="desc_cell"><bean:message key ="cardgradeform.embossingName"/></td>
			                    <td class="label"><html:text  property="embossingName" /> </td>
			                </tr>
			                 <tr>
							  <td class="desc_cell"><bean:message key ="cardgradeform.gender"/></td>
							  <td class="label">
							  <html:select property="gender" >
                                  <html:option value=""></html:option>
								  <html:optionsCollection property="genderList" value="key" label="value" />
								</html:select>
							       </td>
			                </tr> 
			                <tr>
							 <td class="desc_cell" ><bean:message key ="cardgradeform.maritalstatus"/></td>
							 <td class="label">
							    <html:select property="maritalStatus" >
                                  <html:option value=""></html:option>
                                  <html:optionsCollection property="maritalStatusList" value="key" label="value" />
								</html:select>
							         </td>
							</tr>
			                <tr> 
			                   <td class="desc_cell"><bean:message key ="cardgradeform.dob"/></td>
			                   <td class="label"><html:text property="strDob" /></td>
			               </tr>  
			                <tr> 
			                  <td class="desc_cell"><bean:message key ="cardgradeform.nricnumber"/></td>
			                  <td class="label"><html:text  property="idNumber" /></td>
			                 </tr>
			                 <tr>
			                   <td class="desc_cell"><bean:message key ="cardgradeform.expdate"/></td>
			                  <td class="label"><html:text  property="strExpDate" /></td>
			                 </tr>
			                 <tr>
							   <td class="desc_cell"><bean:message key ="cardgradeform.jobtype"/></td>
							   <td class="label"><html:text property="jobType" /></td>
							 </tr>
			                <tr> 
			                  <td class="desc_cell"><bean:message key ="cardgradeform.income"/></td>
			                  <td class="label"><html:text  property="income" /></td>
			                </tr>
		                 <tr> 
		                 <td class="desc_cell"><bean:message key ="cardgradeform.email"/></td>
		                 <td class="label"><html:text  property="email" /></td>
		                </tr>
		                <tr> 
		                 <td class="desc_cell"><bean:message key ="cardgradeform.nationality"/> </td>
		                 <td class="label"><html:text  property="nationality" /> </td>
		                </tr>
                     </table></td>
                     </tr>
                      </tbody>
                         </table></td>
                         <td valign="top" style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table cellspacing=0 cellpadding=0 
                              border=0>
                                        <tbody>
                                          <tr>
                                            <td><table cellspacing=0 cellpadding=0 border=0>
                                                <tbody>
                                                  <tr>
                                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardgradeform.appdocrequired"/></td>
                                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                                  </tr>
                                                </tbody>
                                            </table></td>
                                          </tr>
                                          <tr>
                                            <td bgcolor=#dce5ea><div  style="FONT-SIZE: 1px">&nbsp;</div></td>
                                          </tr>
                                          <tr>
                                            <td height="109"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                                   <logic:iterate id="appdocproof" name ="cardGradeForm" property="appDocProofList">
										   <tr>
										       <td class="desc_cell" nowrap width="132">
										        <html:multibox property="selectedAppDocuments">
										            <bean:write name="appdocproof" property ="key"/>
										        </html:multibox>
										         <bean:write name="appdocproof" property ="value"/>
										       </td>
										    </tr>
						        </logic:iterate>
                                            </table></td>
                                          </tr>
                                        </tbody>
                                      </table></td>
                                    </tr>
                                    <tr>
                                <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table cellspacing=0 cellpadding=0 
                              border=0>
                                        <tbody>
                                          <tr>
                                            <td><table cellspacing=0 cellpadding=0 border=0>
                                                <tbody>
                                                  <tr>
                                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardgradeform.homeaddressinfo"/></td>
                                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                                  </tr>
                                                </tbody>
                                            </table></td>
                                          </tr>
                                          <tr>
                                            <td bgcolor=#dce5ea><div   style="FONT-SIZE: 1px">&nbsp;</div></td>
                                          </tr>
                                          <tr>
                                            <td height="21"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                                                 <tr> 
				                              <td class="desc_cell"><bean:message key ="cardgradeform.address1"/></td>
				                               <td class="label"><html:text  property="homeAddress.address1" size="20"/></td>
				                            </tr>
											
											<tr> 
				                              <td class="desc_cell"><bean:message key ="cardgradeform.address2"/></td>
				                              <td class="label"><html:text   property="homeAddress.address2" size="20" /></td>
				                            </tr>
				                            
				                            <tr> 
				                              <td class="desc_cell"><bean:message key ="cardgradeform.city"/></td>
				                               <td class="label"><html:text   property="homeAddress.city" size="20"/></td>
				                            </tr>
											
											<tr> 
				                              <td class="desc_cell"><bean:message key ="cardgradeform.state"/></td>
				                               <td class="label"><html:text  property="homeAddress.state" size="20"/></td>
				                            </tr>
				                                            
				                            <tr> 
				                              <td class="desc_cell"><bean:message key ="cardgradeform.country"/></td>
				                               <td class="label">
				                                <html:select  property = "homeAddress.country" >
				                               <html:option value=""> </html:option>	
											      	 <html:optionsCollection property="countryList" value="key" label="value" /> 	
											    </html:select>
											    </td>
				                                 </tr>
				                             <tr> 
				                              <td class="desc_cell"><bean:message key ="cardgradeform.postalcode"/></td>
				                               <td class="label"><html:text  property="homeAddress.postalCode" size="20" /></td>
				                            </tr>
											<tr>
											   <td class="desc_cell"><bean:message key ="cardgradeform.phone"/></td>
											    <td class="label"><html:text property="homeAddress.phone" size="20" /></td>
				                            </tr>
				                            
				                            <tr> 
				                                <td class="desc_cell"><bean:message key ="cardgradeform.fax"/></td>
				                                 <td class="label"><html:text  property="homeAddress.fax" size="20"/></td>
											</tr>
			                             </table></td>
		                                </tr>
	                                   </tbody>
                                     </table></td>
                                      <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table cellspacing=0 cellpadding=0 
                              border=0>
                                        <tbody>
                                          <tr>
                                            <td><table cellspacing=0 cellpadding=0 border=0>
                                                <tbody>
                                                  <tr>
                                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardgradeform.companyaddressinfo"/></td>
                                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                                  </tr>
                                                </tbody>
                                            </table></td>
                                          </tr>
                                          <tr>
                                            <td bgcolor=#dce5ea><div     style="FONT-SIZE: 1px">&nbsp;</div></td>
                                          </tr>
                                          <tr>
                                            <td height="109"><table border="0" cellpadding="0" cellspacing="0" width="264" height="109">
                                         <tr> 
			                              <td class="desc_cell"><bean:message key ="cardgradeform.address1"/></td>
			                               <td class="label"><html:text  property="homeAddress.address1" size="20"/></td>
			                              </tr>
									
									<tr> 
		                              <td class="desc_cell"><bean:message key ="cardgradeform.address2"/></td>
		                              <td class="label"><html:text   property="homeAddress.address2" size="20" /></td>
		                            </tr>
		                            
		                            <tr> 
		                              <td class="desc_cell"><bean:message key ="cardgradeform.city"/></td>
		                               <td class="label"><html:text   property="homeAddress.city" size="20"/></td>
		                            </tr>
									
									<tr> 
		                              <td class="desc_cell"><bean:message key ="cardgradeform.state"/></td>
		                               <td class="label"><html:text  property="homeAddress.state" size="20"/></td>
		                            </tr>
		                                            
		                            <tr> 
		                              <td class="desc_cell"><bean:message key ="cardgradeform.country"/></td>
		                               <td class="label">
		                                <html:select  property = "homeAddress.country" >
		                               <html:option value=""> </html:option>	
									      	 <html:optionsCollection property="countryList" value="key" label="value" /> 	
									    </html:select>
									    </td>
		                                 </tr>
		                             <tr> 
		                              <td class="desc_cell"><bean:message key ="cardgradeform.postalcode"/></td>
		                               <td class="label"><html:text  property="homeAddress.postalCode" size="20" /></td>
		                            </tr>
									<tr>
									   <td class="desc_cell"><bean:message key ="cardgradeform.phone"/></td>
									    <td class="label"><html:text property="homeAddress.phone" size="20" /></td>
		                            </tr>
		                            
		                            <tr> 
		                                <td class="desc_cell"><bean:message key ="cardgradeform.fax"/></td>
		                                 <td class="label"><html:text  property="homeAddress.fax" size="20"/></td>
									</tr>
	                                     </table></td>
                                          </tr>
                                        </tbody>
                                      </table></td>
                                    </tr>
                                  </table>
								  </div>
								  <div id="tab2">
								  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="25%" valign="top" style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table cellspacing=0 cellpadding=0 
                              border=0>
                                        <tbody>
                                          <tr>
                                            <td><table cellspacing=0 cellpadding=0 border=0>
                                                <tbody>
                                                  <tr>
                                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardgradeform.newcards"/></td>
                                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                                  </tr>
                                                </tbody>
                                              </table>
                                                <table border="0" cellpadding="0" cellspacing="0" width="50" height="50" >
                                        
                                         <logic:iterate id="appcardprods" name ="cardGradeForm" property="appCardProductList">
				                              <tr>
						                          <td class="desc_cell" nowrap>
						                            <html:multibox property="selectedAppCardProducts">
										               <bean:write name="appcardprods" property ="key"/>
										           </html:multibox>
										            <bean:write name="appcardprods" property ="value"/> 
						                           </td>
						                     </tr>
						               </logic:iterate>
					                      </table></td>
		                                 </tr>
                                        </tbody>
                                      </table></td>
                                      <td width="75%" style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table cellspacing=0 cellpadding=0    border=0>
                                        <tbody>
                                          <tr>
                                            <td><table cellspacing=0 cellpadding=0 border=0>
                                                <tbody>
                                                  <tr>
                                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardgradeform.cardscreditsplit"/></td>
                                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                                  </tr>
                                                </tbody>
                                              </table>
                                                <table border="0" cellpadding="0" cellspacing="0" width="100%" height="90">

                                                  <tr>
                                                    <td nowrap><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                      <tr>
                                                        <td width="6%" nowrap class="desc_cell"><html:multibox property="cardsCancelOrSplit">Cancel</html:multibox>                                                       </td>
                                                        <td width="94%" nowrap class="desc_cell"> <bean:message key ="cardgradeform.checkcaclecards"/></td>
                                                      </tr>
                                                      <tr>
                                                        <td class="desc_cell" nowrap>  <html:multibox property="cardsCancelOrSplit" >split</html:multibox>                                                       </td>
                                                        <td class="desc_cell" nowrap>   <bean:message key ="cardgradeform.checksplitscards"/>  </td>
                                                      </tr>
                                                    </table></td>
                                                  </tr>
                                                  <tr>
                                                    <td nowrap><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                      <tr>
                                                        <td width="37%" nowrap class="desc_cell"><bean:message key ="cardgradeform.currentcard"/> </td>
                                                        <td width="63%"><span class="label">
                                                         <html:text property ="currCardCreditLimitPer"/>
                                                        </span></td>
                                                      </tr>
                                                      <tr>
                                                        <td height="20" nowrap class="desc_cell"><bean:message key ="cardgradeform.newcard"/></td>
                                                        <td><span class="label">
                                                          <html:text property ="newCardCreditLimitPer"/>
                                                        </span></td>
                                                      </tr>
                                                      
                                                    </table></td>
                                                    </tr>
                                                </table></td>
                                          </tr>
                                        </tbody>
                                      </table></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2" style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 20px"><table cellspacing=0 cellpadding=0 
                              border=0>
                                        <tbody>
                                          <tr>
                                            <td><table cellspacing=0 cellpadding=0 border=0>
                                                <tbody>
                                                  <tr>
                                                    <td><img height=19 alt="" src="images/tab_g.gif" width=8 border=0></td>
                                                    <td class=group_title background=images/tab_fond.gif><bean:message key ="cardgradeform.accesstoaccount"/></td>
                                                    <td><img height=19 alt="" src="images/tab_d.gif" width=8 border=0></td>
                                                  </tr>
                                                </tbody>
                                              </table>
                                                <table border="0" cellpadding="0" cellspacing="0" width="264" height="40">
                                                  <tr>
                                                    <td height="20" nowrap class="desc_cell"><bean:message key ="cardgradeform.atmlinkedacc1"/></td>
                                                    <td class="label"><html:text property="savingAccount"  /></td>
                                                  </tr>
                                                  <tr>
                                                    <td nowrap class="desc_cell"><bean:message key ="cardgradeform.atmlinkedacc2"/></td>
                                                    <td class="label"><html:text property="checkingAccount" /></td>
                                                  </tr>
                                                </table></td>
                                          </tr>
                                        </tbody>
                                      </table></td>
                                      </tr>
                                  </table>
								  </div>
								  </td>
                                </tr>
                              </table></td>
                            </tr>
                         </table></td>
                        <td background=images/tbl_d.gif></td>
                      </tr>
                      <tr>
                        <td background=images/tbl_g.gif></td>
                        <td background=images/tbl_d.gif></td>
                      </tr>
                      <tr>
                        <td><img height=5 alt="" src="images/tbl_bas_g.gif" width=5 border=0></td>
                        <td background=images/tbl_bas.gif></td>
                        <td><img height=5 alt="" src="images/tbl_bas_d.gif" width=5 border=0></td>
                      </tr>
                    </tbody>
                  </table></td>
                  <td background="images/tbl_d.gif"></td>
                </tr>
                                
                <tr> 
                  <td background="images/tbl_g.gif"></td>
                  <td class="form_bgcolor"  colspan="2"> 
                    <div align="right">
                     <%if(request.getAttribute("ACTION")== null || ((String)request.getAttribute("ACTION")).equals("search")){%>
        		        	   <html:button property="submitbutton" onclick="go('add')" > <bean:message key ="common.save"/></html:button>
        		        	    <html:button property="submitbutton" onclick="showList('cardgradeformsetup.do')"><bean:message key ="common.cancel"/></html:button>
        		        	<%}if(request.getAttribute("ACTION")== null || ((String)request.getAttribute("ACTION")).equals("update")){ %>
        		            <html:button property="submitbutton" onclick="go('update')"><bean:message key ="common.update"/></html:button>
                    		<html:button property="submitbutton" onclick="go('accept')"><bean:message key ="common.accept"/></html:button>
       						<html:button property="submitbutton" onclick="go('reject')"><bean:message key ="common.reject"/></html:button>
       						 <html:button property="submitbutton" onclick="showList('cardgradeformlistpage.do')"><bean:message key ="common.cancel"/></html:button>
       						
       				  	   <%}if(request.getAttribute("ACTION")!= null && ((String)request.getAttribute("ACTION")).equals("cancel")){ %> 
       				  	   <html:button property="submitbutton" onclick="showList('cardgradeformsetup.do')"><bean:message key ="common.cancel"/></html:button>
       				  	      
       				    <%} %>  
                       </div>				  </td>
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
      </logic:present>  	       			
        </html:form>     
    </body>
</html:html>        