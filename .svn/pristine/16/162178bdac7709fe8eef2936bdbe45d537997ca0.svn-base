<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.transinfo.cacis.common.CommonDataBean" %>
   	<% 
   	
   	  String pageNo="0";
      String mode="S";
	 //out.println((String)request.getAttribute("PAGENO"));
         ArrayList  objSearchList =(ArrayList)request.getAttribute("SEARCHLIST");
         
         if(objSearchList!=null) {  %>
         
         <% if(request.getAttribute("PAGENO") !=null && Integer.parseInt((String)request.getAttribute("PAGENO"))>0) {%>
        	<html:button property="submitbutton" onclick="go('PREV')" ><bean:message key ="common.previous"/></html:button>&nbsp;&nbsp;
         <% }  %>
         
         <% if(objSearchList.size()>=CommonDataBean.RECORDS_IN_TRANX_PAGE) {%>
         	<html:button property="submitbutton" onclick="go('NEXT')" ><bean:message key ="common.next"/></html:button>&nbsp;&nbsp;
         <%} %>
        
       <% } %>


    <logic:notPresent  name="PAGENO" scope="request">
    <input type="hidden" name="pageNo" id="pageNo" value ="<%=pageNo%>" >
     </logic:notPresent>   
     
     <logic:notPresent  name="MODE" scope="request">
       <input type="hidden" name="mode" id="mode" value ="<%=mode%>" >
     </logic:notPresent>   
  
     <logic:present  name="PAGENO" scope="request">
       <input type="hidden" name="pageNo" id="pageNo" value ="<%=(String)request.getAttribute("PAGENO")%>">
     </logic:present>   
     
     <logic:present  name="MODE" scope="request">
       <input type="hidden" name="mode" id="mode" value ="<%=(String)request.getAttribute("MODE")%>">
     </logic:present>   
