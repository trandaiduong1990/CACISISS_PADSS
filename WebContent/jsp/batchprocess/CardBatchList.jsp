<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@page import="org.transinfo.cacis.common.CommonDataBean"%>
<%@page import="java.util.ArrayList"%>
<html:html>
   <head>
      <title>
         <bean:message key="blacklistcardsetup.screentitle" />
      </title>
      <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv="expires" content="0">
      <meta http-equiv="cache-control" content="no-cache">
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link rel="stylesheet" href="inc/css/style.css" type="text/css">
   </head>
   <script language="JavaScript" src="inc/js/jquery-1.6.3.js"></script>
   <script src="inc/js/cacis.js"></script>
   <script>
      function addNew() {
      	document.forms[0].action = "cardbatchdispatchaction.do?method=addNew";
      	document.forms[0].submit();
      }
      
      function searchlist() {
      	document.forms[0].submit();
      }
      
      function go(action) {
      	document.forms[0].mode.value = action;
      	searchlist();
      }
   </script>
   <body bgcolor="ffffff">
      <html:form action="searchcardbatchaction">
         <table border="0" cellspacing="0" cellpadding="0"
            style="border-collapse: collapse" bordercolor="#111111" width="100%">
            <tr>
               <td>
                  <table border="0" cellspacing="0" cellpadding="0" width="100%">
                     <tr>
                        <td class="titreSection">
                           <bean:message
                              key="cardBatch.searchScreen" />
                        </td>
                     </tr>
                     <tr>
                        <td background="images/ligne.gif" colspan="2"><img
                           src="images/spacer.gif" width="1" height="1"></td>
                     </tr>
                     <tr>
                        <td class="titreSection">
                           <table border="0" cellspacing="0" cellpadding="4">
                              <tr>
                                 <td>
                                    <b>
                                       <html:button property="submitbutton"
                                          onclick="addNew()">
                                          <bean:message key="common.addnew" />
                                       </html:button>
                                    </b>
                                 </td>
                                 <td>
                                    <html:button property="submitbutton"
                                       onclick="searchlist()">
                                       <bean:message key="common.search" />
                                    </html:button>
                                 </td>
                              </tr>
                           </table>
                        </td>
                     </tr>
                     <tr>
                        <td background="images/ligne.gif" colspan="2"><img
                           src="images/spacer.gif" width="1" height="1"></td>
                     </tr>
                  </table>
               </td>
            </tr>
            <tr>
               <td valign="top">
                  <br>
                  <table width="100%">
                     <tr>
                        <td valign="top" class="ErrFONT">
                           <font color="#FF0000">
                              <html:errors />
                           </font>
                        </td>
                     </tr>
                     <tr>
                        <td valign="top">
                           <table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                 <td>
                                    <table cellpadding="0" cellspacing="0" style="width: 53%;">
                                       <tr>
                                          <td colspan=2 style="padding: 3px;">
                                             <label
                                                class="label" style="width: 115px; float: left">
                                                <b>
                                                   <bean:message key="cardBatch.cardBatchName" />
                                                </b>
                                             </label>
                                             <div>
                                                <html:text property="batchName" size="20" />
                                             </div>
                                          </td>
                                       <tr>
                                          <td style="padding: 3px;">
                                             <label class="label"
                                                style="width: 115px; float: left;">
                                                <b>
                                                   <bean:message
                                                      key="cardBatch.from" />
                                                </b>
                                             </label>
                                             <div>
                                                <html:text property="fromDate" size="20" maxlength="10"/>
                                             </div>
                                          </td>
                                          <td style="padding: 3px;">
                                             <label class="label"
                                                style="width: 60px; float: left;">
                                                <b>
                                                   <bean:message
                                                      key="cardBatch.to" />
                                                </b>
                                             </label>
                                             <div>
                                                <html:text property="toDate" size="20"  maxlength="10"/>
                                             </div>
                                          </td>
                                       </tr>
      </html:form>
      </table>
      </td>
      </tr>
      </table><logic:present name="SEARCHLIST" scope="request">
      <logic:present name="CHKTOTALRECS" scope="request">
      <div style="padding-top: 5px;"><b><bean:write name="cardBatchSearchForm" property="total" /></b>
      <bean:message key="cardBatch.total" /></div>
      </logic:present>
      <div style="margin-top: 10px; margin-bottom: -12px;"><%@ include
         file="/jsp/common/Buttons.jsp"%></div>
      <br />
      <display:table id="appProcess" name="requestScope.SEARCHLIST"
         class="simple" width="80%">
      <display:column property="column1" title="Batch Name"
         class="label" />
      <display:column property="column2" title="Card Product"
         class="label" />
      <display:column property="column3" title="Status"
         class="label" />
      <display:column property="column4" title="Cards Generated"
         class="label" />
      <display:column property="column5" title="Error Msg"
         class="label" />
      <display:column property="column6" title="Updated Date"
         class="label" />
      <display:column property="column7" title="Updated By"
         class="label" />
      <font class="label"><display:column title=""
         align="center" width="80"></font>
      <form name="listForm" action="cardbatchdispatchaction.do">
      <html:hidden property="id"
         value="<%=((CommonDataBean) pageContext
            .getAttribute("appProcess")).getColumn8()%>" />
      <html:hidden property="issuerId"
         value="<%=(String) session.getAttribute("ISSUER")%>" /> <html:hidden
         property="userId"
         value="<%=(String) session.getAttribute("USERID")%>" /> <html:hidden
         property="method" value="change" /> <html:hidden
         property="screenType" value="list" /> <html:submit
         value="Update" />
      </form>
      </display:column>
      </display:table>
      </logic:present>
      </td>
      </tr>
      <tr>
      <td valign="top">&nbsp;</td>
      </tr>
      </table></td>
      </tr>
      </table>
   </body>
</html:html>