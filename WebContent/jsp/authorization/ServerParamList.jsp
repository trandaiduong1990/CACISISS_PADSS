<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display" %>
<%@page import="org.transinfo.cacis.common.CommonDataBean" %>
<%@page import="java.util.ArrayList" %>

<html:html>
<head>
<title><bean:message key ="serverparamsearch.screentitle"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="inc/css/style.css" type="text/css">
</head>
<script src="inc/js/cacis.js"></script>

<body bgcolor="ffffff">
<html:form  action ="serverparameterprocess">	  	  
<logic:present name="SEARCHLIST" scope="request"> 
<bean:size id ="size" name ="SEARCHLIST"/>

<table border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" width="100%">
  <tr>
    <td> 
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="titreSection"><bean:message key ="serverparamsearch.screentitle"/></td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
        <tr>
          <td class="titreSection">
              <table border="0" cellspacing="2" cellpadding="2" width="157">
                <tr> 
                <td class="texteMenuGauche"> 
                    <p class="titreSection">

                    &nbsp;&nbsp;</p>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td background="images/ligne.gif" colspan="2"> <img src="images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>    
      <% 
        if(request.getAttribute("SEARCHLIST")!=null){
        
          ArrayList arl = (ArrayList)request.getAttribute("SEARCHLIST");
          for(int i=0;i<arl.size();i++){
          CommonDataBean objComm = (CommonDataBean)arl.get(i);
          System.out.println("the column2"+ objComm.getColumn2());
          request.setAttribute("commObj",objComm);
         }
          
        }
      %>
             
<logic:iterate id ="searchObj" name ="SEARCHLIST"> 
<bean:define id ="currobj" name ="commObj" />
              <tr> 
               <td width="3%" nowrap class="texts"> 
                   
                    <bean:write name = "searchObj"  property="column1"/>
                    <bean:write name = "searchObj"  property="column2"/>
                   <%--<html:text property="paramValue" value="<%= (String)((CommonDataBean)currobj).getColumn2()%>"/> --%> 
               </td>                  
              </tr>
</logic:iterate>
</table>
<html:hidden property="method" value ="update"/>
 <html:submit value="Update"/>       
</logic:present>
</html:form> 

</html:html>