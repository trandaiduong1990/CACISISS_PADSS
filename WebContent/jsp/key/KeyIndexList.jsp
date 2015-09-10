<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="keyindexsearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>
<script>
function addNew()
{
 
 document.forms[0].action="keyindexprocess.do?method=addNew";
 document.forms[0].submit();
}

	function go(action){
		document.forms[0].mode.value=action;
	 	//document.forms[0].action ="keyindexlist.do?method=search";
	 	document.forms[0].action ="keyindexlist.do";
		document.forms[0].submit();
	}

	function searchlist(){
	 	document.forms[0].action ="keyindexlist.do";
		document.forms[0].submit();
	}
</script>

<body bgcolor="ffffff">

<html:form action ="keyindexlist.do">
 <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
 <html:hidden property="userId" value="<%=(String)session.getAttribute("USERID")%>"/>
<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="keyindexsearch.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
	  <td class="titreSection"><table border="0" cellspacing="0" cellpadding="4">
	   <tr>
	     <td>
	     	<html:button property="submitbutton" onclick="searchlist()"><bean:message key ="common.search"/></html:button>
	     	<html:button property="submitbutton" onclick="addNew()"><bean:message key ="common.addnew"/></html:button>
	     </td>	      
	   </tr>
	 </table></td>
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
                    <table cellpadding="0" cellspacing="4">
                    
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="keyindexsetup.cardproductid"/> </td>
                        <td>
	                        <html:select property="cardProductId" >
					      	     <html:option value=""></html:option>
					      	     <html:optionsCollection property="cardProductList" value="key" label="value" />
					      	</html:select>
				      	</td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="keyindexsetup.keytype"/></td>
                        <td>
                        	<html:select property="keyType" styleId="keyType" onchange="disTransactionChannel();">
				      	     	<html:option value=""></html:option>
				      	     	<html:optionsCollection property="keyTypeList" value="key" label="value" />
			      	   		</html:select>
                        </td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="keyindexsetup.tranxchannel"/></td>
                        <td>
                        	<html:select property="transactionalChannel" styleId="transactionChannel">
				      	     	<html:option value=""></html:option>
				      	    	<html:optionsCollection property="tranxChannelList" value="key" label="value" />
				      	   	</html:select>
				      	</td>
                      </tr>
                      <tr>
                        <td nowrap class="ColumnFONT"><bean:message key ="keyindexsetup.active"/></td>
                        <td>
                        	<html:select property="status" >
				      	     <html:option value=""></html:option>
				      	     <html:optionsCollection property="statusList" value="key" label="value" />
				      	   </html:select>
                        </td>
                      </tr>

                    </table>
                 </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td valign="top">
          		  
   <%@ include file="/jsp/common/Buttons.jsp" %>
 </html:form>
           
 <br><logic:present name="SEARCHLIST" scope="request"> 
   <display:table  id="appProcess" name="requestScope.SEARCHLIST"  
     class="simple" width="80%">
   <display:column property="column2" title="Card Product Name" class="label" />
   <display:column property="column3" title="Key Type" class="label" />
   <display:column property="column8" title="Transaction Channel" class="label" />  
   <display:column property="column4" title="Key Index" class="label" />   
   <display:column property="column5" title="Active" class="label" />   
   <display:column property="column6" title="Updated Date" class="label" />
   <display:column property="column7" title="Updated By" class="label" />   
   <display:caption><font class="titreSection">Key Index Records</font></display:caption>
   <font class="label"><display:column title="Details" align="center" width="80" ></font>
  	
  	  
  	   <form name ="listForm" action="keyindexprocess.do">	 
	
               <html:hidden property="cardProductId" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn1()%>" />
               <html:hidden property="keyType" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn3()%>" />   
               <html:hidden property="transactionChannel" value="<%= ((CommonDataBean)pageContext.getAttribute("appProcess")).getColumn8()%>" />           
               <html:hidden property="issuerId" value="<%=(String)session.getAttribute("ISSUER")%>"/>
               <html:hidden property="method" value ="change"/>
               <html:submit value="Update"/>
              
	   </form>
	  
   </display:column>	
   </display:table>
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

</body>
</html:html>