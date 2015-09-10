<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>

<html:html>
<head>
<title><bean:message key ="intwaiver.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
<script language="JavaScript" src="inc/js/cacis.js"></script>
<script language="JavaScript" src="inc/js/IntWaiver.js"></script>

<script>

function view(tranxId){
	document.forms[0].method.value="view";
	document.forms[0].tranxId.value=tranxId;
	document.forms[0].action ="tranxEnquiryProcess.do";
	document.forms[0].submit();
	
}

function intwaive() {

	
	alert(document.getElementById("sno"));
	alert(document.getElementById("cardNo"));
	document.forms[0].cardNo.value=cardno;
	document.forms[0].method.value="intWave";
	document.forms[0].action ="intwaiverprocess.do";
	//document.forms[0].submit();
	
}

function go(action) {
	//alert(action);
    if(action =="intwaive"){

        if(document.forms[0].serialNosTowaive.value.length == 0){
        	alert('Please select Records');
			return false;
        }else{
        	var r=confirm("<bean:message key ='areyousure'/>");
			if (r==true)
			{
				document.forms[0].method.value=action;
				document.forms[0].action ="intwaiverprocess.do";
				document.forms[0].submit();
			}
        }

        /*
	 	if(document.forms[0].selectedEmbossSerialNos != undefined){
		 	var len = document.forms[0].selectedEmbossSerialNos.length;
		 	if (len == undefined) {
		 		if(!document.forms[0].selectedEmbossSerialNos.checked){
		 			alert('Please select card to Emboss');
					return false;
		     	}else{
	     		  var r=confirm("<bean:message key ='areyousure'/>");
	     		  if (r==true)
	     		  {
		     		document.forms[0].method.value=action;
			   		document.forms[0].action ="cardembossingprocess.do";
			   		document.forms[0].submit();
	     		  }
		     	}
		 	}else{
			 	var res = false;
		 		for(var i=0;i<len;i++){
			     	if(document.forms[0].selectedEmbossSerialNos[i].checked){
			     		res = true;
			     		break;
			     	}
			 	}
			 	if (!res) {
					alert('Please select card to Emboss');
					return false;
				}else{
					var r=confirm("<bean:message key ='areyousure'/>");
		     		  if (r==true)
		     		  {
			     		document.forms[0].method.value=action;
				   		document.forms[0].action ="cardembossingprocess.do";
				   		document.forms[0].submit();
		     		  }
				}
		 	}
	 	}else{
	 		//document.forms[0].onSubmit=false;
			return false;
	 	}*/
     }else
     {
   	 	document.forms[0].method.value=action;
   		document.forms[0].action ="intwaiverprocess.do";
   		document.forms[0].submit();
     }
}
</script>

</head>

<body bgcolor="ffffff" >
<html:form action ="intwaiverprocess.do">

<input type="hidden" name="method" value ="">
<bean:define id="snos" name="intWaiverForm" property="serialNosTowaive"/>
<html:hidden property="serialNosTowaive" value="<%=(String)snos%>"/>

<bean:define id="ct" name="intWaiverForm" property="cardType"/>
<html:hidden property="cardType" value="<%=(String)ct%>"/>

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="intwaiver.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td>
              	<html:button property="submitbutton" onclick="go('search')"><bean:message key ="common.search"/></html:button>
           		<logic:equal value="Y" property="butEnable" name="intWaiverForm">
           			<html:button property="intwaive" onclick="go('intwaive')"><bean:message key ="common.intwaive"/></html:button>
           		</logic:equal>
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
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="intwaiver.cardno"/> </td>
                        <td><html:text property="cardNo" size="20"/></td>
                        <!-- <td style="width: 30px;"></td>
                        <td nowrap class="ColumnFONT"><bean:message key ="intwaiver.csn"/> </td>
                        <td><html:text property="csn" size="20"/></td>
                         -->
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
		     	<bean:size id ="size" name ="intWaiverForm" property="feeList"/>
		        	<logic:greaterThan  name ="size" value ="0"> 
			           <tr> 
			              <td>
			                <table border="0" cellspacing="0" cellpadding="0" width="60%">
			                	<tr>
			                      <td style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
			                      <table class="wrapper" border="0" cellspacing="1" cellpadding="2">
	                                <tr>
	                                    <td class="title" nowrap></td>
										<td class="title" nowrap><bean:message key ="intwaiver.cardno"/></td>
	                                    <td class="title" nowrap><bean:message key ="intwaiver.date"/></td>
										<td class="title" nowrap><bean:message key ="intwaiver.des"/></td>
										<td class="title" nowrap><bean:message key ="intwaiver.amt"/></td>
										<td class="title" nowrap><bean:message key ="intwaiver.intwaived"/></td>
	                                 </tr>
		                               <logic:iterate id ="commObj" name ="intWaiverForm" property="feeList">
										<bean:define id="sno" name="commObj" property="column1"/>
		                                  <tr>
		                                  	<td class="DataTD" nowrap>
		                                  	<logic:equal name="commObj" property="column6" value="N">
			                                  	<html:multibox property="selectedSerialNos" onclick='<%= "javascript:checkCommon(document.intWaiverForm.selectedSerialNos,this,document.intWaiverForm.serialNosTowaive, '"+sno+"');"%>' >
		                                          <bean:write name = "commObj"  property="column1" />
		                                        </html:multibox>
		                                    </logic:equal>
		                                  	</td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj"  property="column2" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column3" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column4" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column5" /></td>
		                                    <td class="DataTD" nowrap><bean:write name ="commObj" property="column6" /></td>
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