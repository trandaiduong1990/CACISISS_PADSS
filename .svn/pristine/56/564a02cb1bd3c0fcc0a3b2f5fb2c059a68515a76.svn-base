<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
<head>
<title><bean:message key ="cardcardembossingsearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/ChargeBackSearch.js"></script>

<script>

function view(dicCaseNo){
	document.forms[0].method.value="viewChargeBack";
	document.forms[0].disCaseNo.value=dicCaseNo;
	document.forms[0].action ="chargeBackProcess.do";
	document.forms[0].submit();
	
}

function go(action) {
    if(action =="save"){

        if(document.forms[0].serialNosToEmboss.value.length == 0){
        	alert('Please select card to Emboss');
			return false;
        }else{
        	var r=confirm("<bean:message key ='areyousure'/>");
			if (r==true)
			{
				document.forms[0].method.value=action;
				document.forms[0].action ="cardembossingprocess.do";
				//document.forms[0].submit();
			}
        }
     }else
     {
    	document.forms[0].mode.value=action;
     	document.forms[0].action ="chargeBackList.do";
     	if(dateValidationOnlist(document.forms[0])){
 	 	  	document.forms[0].submit();
 	 	}
     }
}

function searchlist(){

	var objStartDate = document.getElementsByName("startDate")[0];
	var startDate = objStartDate.value;

	var objEndDate = document.getElementsByName("endDate")[0];
	var endDate = objEndDate.value;


	var isFormSubmit = false;

	if(startDate != '' && endDate != ''){
		if(!validateFromToDate(startDate,endDate)){
			alert("End Date must be greater than Start Date");
			objEndDate.focus();
			isFormSubmit = false;
		}else{
			isFormSubmit = true;
		}
	}else{
		if(startDate == '' && endDate == ''){
			isFormSubmit = true;
		}else{
			if(startDate != '' && endDate == ''){
				var r=confirm('End Date also will be SAME as Start Date. \nAre you sure?');
			    if (r==true){
			    	isFormSubmit = true;
			    }else{
			    	objEndDate.focus();
			    }
			}else{
				var r=confirm('Start Date also will be SAME as End Date. \nAre you sure?');
			    if (r==true){
			    	isFormSubmit = true;
			    }else{
			    	objStartDate.focus();
			    }
			}
		}
	}

	if(isFormSubmit){
		if(dateValidationOnlist(document.forms[0])){
			document.forms[0].submit();
		}
	}
}
</script>

</head>

<body bgcolor="ffffff" >
<html:form action ="chargeBackList.do">
<input type="hidden" name="method" value ="save">
<input type="hidden" name="disCaseNo" value ="">

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="disputemanagement.cb.transactionsearch"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td>
              	<html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button>
              </td>
              <td>&nbsp;</td>
            </tr>
          </table> <td class="texteMenuGauche"><div align="right"><a href='javascript:helpLink("WRITEOFF")'>Help</a></div></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td valign="top">
      <table width="100%">

        <tr>
          <td valign="top" class="ErrFONT"><table width="100%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td class="ErrFONT"><font color="#FF0000"><html:errors/></font></td>
            </tr>
          </table></td>
        </tr>
       <tr> 
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td> 
                    <table cellpadding="0" cellspacing="4">
                      <!-- <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.cb.cardno"/> </td>
                        <td><html:text property="cardNo" size="20"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.cb.terminalId"/> </td>
                        <td><html:text property="terminalId" size="20"/></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.cb.mcc"/> </td>
                        <td><html:text property="mcc" size="20"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.cb.refno"/> </td>
                        <td><html:text property="refNo" size="20"/></td>
                      </tr>
                       -->
                      <tr>
                        <!-- <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.cb.authcode"/> </td>
                        <td><html:text property="authCode" size="20"/></td>
                        <td style="width: 30px;"></td>
                         -->
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.cb.arn"/> </td>
                        <td><html:text property="arn" size="25"/></td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.cb.startdate"/><bean:message key ="common.dateformat"/></td>
                        <td><html:text property="startDate" size="25" onblur="javascript:isValidDate(document.chargeBcakSearchForm.startDate)"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT"><bean:message key ="disputemanagement.cb.enddate"/><bean:message key ="common.dateformat"/></td>
                        <td><html:text property="endDate" size="25" onblur="javascript:isValidDate(document.chargeBcakSearchForm.endDate)"/></td>
                      </tr>
                      <tr>
                      	<td style="padding-top: 10px;" colspan="5" nowrap class="ColumnFONT"><bean:message key ="disputemanagement.cb.ttar"/>:</td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT" align="right"><bean:message key ="disputemanagement.cb.ttarfrom"/> </td>
                        <td><html:text property="amtFrom" size="25"/></td>
                        <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT" align="right"><bean:message key ="disputemanagement.cb.ttarto"/> </td>
                        <td><html:text property="amtTo" size="25"/></td>
                      </tr>
                    </table>
                 </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td valign="top">
		    <!-- to Show the List Data -->   
			<logic:present name="SEARCHLIST" scope="request"> 
		     	<bean:size id ="size" name ="SEARCHLIST"/>
		        	<logic:greaterThan  name ="size" value ="0"> 
			           <tr> 
			              <td>
			                <table border="0" cellspacing="0" cellpadding="0" width="60%">
			                <tr>
							     <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							     	<b><bean:write name="chargeBcakSearchForm" property="totalCount"/></b>
							     	<bean:message key ="disputemanagement.cb.totcountmsg"/>
							     </td>
							     <td></td>	
			                 </tr>
			                 <tr>
							     <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-TOP: 10px">
							     	<%@ include file="/jsp/common/Buttons.jsp"%>
							     </td>
							     <td></td>	
			                 </tr>
			                 <tr>
			                      <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
			                      <table class="wrapper" border="0" cellspacing="1" cellpadding="2">
	                                <tr>
										<td class="title" nowrap><bean:message key ="disputemanagement.cb.list.chargecaseno"/></td>
										<td class="title" nowrap><bean:message key ="disputemanagement.cb.list.distype"/></td>
	                                    <td class="title" nowrap style="width: 250px;"><bean:message key ="disputemanagement.cb.list.disreason"/></td>
	                                    <td class="title" nowrap><bean:message key ="disputemanagement.cb.list.disamt"/></td>
	                                    <td class="title" nowrap><bean:message key ="disputemanagement.cb.list.discurr"/> </td>
	                                    <td class="title" nowrap><bean:message key ="disputemanagement.cb.list.arn"/> </td>
	                                    <td class="title" nowrap><bean:message key ="disputemanagement.cb.list.cdate"/> </td>
	                                    <td class="title" nowrap><bean:message key ="disputemanagement.cb.list.daystoact"/> </td>
	                                    <td class="title" nowrap></td>
	                                 </tr>
		                                <bean:define id="disMngAlrtBuff" name="chargeBcakSearchForm" property="disMngAltBuff" />
										<% int disMngAlt = Integer.valueOf((String)disMngAlrtBuff).intValue(); %>
		                               <logic:iterate id ="commObj" name ="SEARCHLIST">
											<bean:define id="sno" name="commObj" property="column1"/>
											<bean:define id="cDays" name="commObj" property="column8" />
											<bean:define id="rtLimit" name="commObj" property="column9" />
											<% 
											String css = "DataTD";
											int daysToAct = Integer.valueOf((String)rtLimit).intValue() - Integer.valueOf((String)cDays).intValue();
											if(daysToAct <= disMngAlt) css = "expiresoon";
											%>
		                                  <tr>
		                                    <td class="<%=css %>" nowrap><bean:write name ="commObj"  property="column1" /></td>
		                                    <td class="<%=css %>" nowrap><bean:write name ="commObj" property="column2" /></td>
		                                    <td class="<%=css %>" nowrap style="width: 250px;"><bean:write name ="commObj" property="column3" /></td>
		                                    <td class="<%=css %>" nowrap><bean:write name ="commObj" property="column4" /></td>
		                                    <td class="<%=css %>" nowrap><bean:write name ="commObj"  property="column5" /></td>
		                                    <td class="<%=css %>" nowrap><bean:write name ="commObj" property="column6" /></td>
		                                    <td class="<%=css %>" nowrap><bean:write name ="commObj" property="column7" /></td>
		                                    <td class="<%=css %>" nowrap><%= daysToAct %></td>
	                                    	<td class="title" nowrap>
	                                    		<input type="button" value="View" onclick="<%= "javascript:view('"+sno+"');"%>">
	                                    	</td>
		                                  </tr>
		                               </logic:iterate>                        
			                        </table>
			                       </td>
			                       </tr>
		                  	</table>
		                  </td>
			              <td background=images/tbl_d.gif></td>
					</tr>
				</logic:greaterThan>
			</logic:present>
         </td>
        </tr>
        <tr> 
          <td valign="top">&nbsp; </td>
        </tr>
      </table>
    </td>
  </tr>
</table>               
</html:form>
</body>
</html:html>