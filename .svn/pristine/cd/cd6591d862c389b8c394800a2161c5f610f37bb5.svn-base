<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page import="org.apache.struts.Globals"%>
<html:html locale="true">

<HEAD>
<TITLE>MainMenu </TITLE> 
</HEAD>

<BODY bgcolor=#dddddd>
<%
   session.setAttribute("ISSUER","Issuer1");
   session.setAttribute("USERID","ASPSUPERADMIN");
   session.setAttribute("USERTYPE", "ISSADMIN");
   
  
if (session.getAttribute(Globals.LOCALE_KEY) != null) {
session.setAttribute(Globals.LOCALE_KEY, request.getLocale());
}   
	
 %>
   <center><h3>Welcome to CardIssuing Administration System</h3></center>
    <h2><u>MAIN-MENU<u></h2>
      <h4>#Settings#</h4>
     <html:link action ="issuerlist?method=List">IssuerSetup</html:link><br>
     <html:link action ="branchlist.do?method=List">BranchSetup</html:link><br>
     <html:link action ="customertypelist.do?method=List">CustomerTypeSetup</html:link><br>
    <html:link action ="cardtypelist.do?method=List">CardTypeSetup</html:link><br>
    <html:link action ="cardproductlist?method=List">CardProductSetup</html:link><br>
    <html:link action ="cardpromotionlist.do?method=List">CardPromotionSetup</html:link><br>
    <html:link action ="cardproductratelist?method=List">CardProductRateSetup</html:link><br>
    <html:link action ="cardproductfeelist?method=List">CardProductFeeSetup</html:link><br>
    <html:link action ="cyclelist.do?method=List">CycleSetup</html:link><br>
    <html:link action ="mcclistpage.do">MCCSetup</html:link><br>
   	
				           <h4>#Card Production#</h4>
     <html:link action ="applicationformlistpage">ApplicationForm</html:link><br>
     <html:link action ="applicationprocesslist">ApplicationProcess</html:link><br> 
     <html:link action ="cardembossinglistpage">CardEmbossing</html:link><br> 
     <html:link action ="pinprintinglistpage">PinPrinting</html:link><br> 
     <html:link action ="carddeliverlistpage">CardDeliver</html:link><br>  
     <html:link action ="carddeliverlistpage.do?status"  paramScope ="request">CardReceivedConfirm</html:link><br> 
     		           <h4>#Application Forms Setup#</h4>
   <!--  <html:link action ="supplementaryformsetup">SupplementaryFormAdd</html:link><br> -->
    <html:link action ="supplementaryformprocess.do?method=addNew">SupplementaryForm</html:link><br>
    <html:link action ="supplementaryformlistpage">SupplementaryFormProcess</html:link><br>
     <html:link action ="cardsrenewallist">CardsRenewalProcess</html:link><br>    
    <html:link action ="cardrepprocesslist?method=processSearch">CardReplacementFormProcess</html:link><br>    
    <html:link action ="cardcloseprocess?method=cardCloseProcessSearch">CardCloseFormProcess</html:link><br> 
    <html:link action ="pinresendprocess?method=pinResendProcessSearch">PinResendFormProcess</html:link><br> 
    <html:link action ="cardgradeformsetup">CardGradeForm</html:link><br>          
    <html:link action ="cardgradeformlist">CardGradeFormProcess</html:link><br> 
    <html:link action ="cardlimitprocess.do?method=cardLimitProcessSearch">CardLimitAdjustmentFormProcess</html:link><br>          
              
                 <h4>#CustomerService#</h4> 
   <html:link action ="cardreceivedprocess?method=checkSession">CardReceived</html:link><br>                         
   <html:link action ="cardreplacementlist?method=checkSession">CardReplacement</html:link><br>                
   <html:link action ="cardstopprocess?method=checkSession">CardStop</html:link><br>       	
   <html:link action ="cardactivateprocess?method=checkSession">CardActivate</html:link><br>  
   <html:link action ="resetpincountprocess?method=checkSession">ResetPinCount</html:link><br>      
   <html:link action ="billingaddprocess?method=checkSession">BillingAddressChange</html:link><br>  
   <html:link action ="cardcloselist?method=checkSession">CardClose</html:link><br>  
   <html:link action ="pinresendlist?method=checkSession">PinResend</html:link><br>  
   <html:link action ="cardlimitlist?method=checkSession">CardLimitAdjustment</html:link><br>  
   <html:link action ="creditsplitpage?method=checkSession">CreditSplitting</html:link><br> 
                         <h4>#Card Accounting#</h4> 
    <html:link action ="cardpaymentprocess?method=checkSession">CardHolderPayment</html:link><br> 
     <html:link action ="cardbillingprocess?method=checkBllingCycle">Card Billing Process </html:link><br> 
                        <h4>#Call Center#</h4>
    <html:link action ="csrsearch">CallCenterSearch</html:link><br> 
    <html:link action ="csrcallrecord.do?method=openCalls">OpenCalls</html:link><br>
                          <h4>#Upload#</h4>
    <html:link action ="uploadprocess.do?method=List&uploadType=1">ApplicationForm</html:link><br> 
    <html:link action ="uploadprocess.do?method=List&uploadType=2">CurrencyRate</html:link><br>
    <html:link action ="uploadprocess.do?method=List&uploadType=3">BlackListCards</html:link><br>
    <html:link action ="uploadprocess.do?method=List&uploadType=4">CardEmossing</html:link><br>
    <html:link action ="uploadprocess.do?method=List&uploadType=5">PinPrinting</html:link><br>
    <html:link action ="uploadprocess.do?method=List&uploadType=6">CardDeliver</html:link><br>
                          <h4>#DisputeManagement#</h4>
     <html:link action ="claimformlistpage.do">DisputeClaimForm</html:link><br> 
     <html:link action ="workitemlistpage.do">WorkItem</html:link><br> 
     <html:link action="disputeconfigsetup?action=change">Dispute Config Setup</html:link><br>
     <html:link action="disputedocumentslist?action=preList">Dispute Documents Setup</html:link><br>
     <html:link action="documentuploadlistpage">Document Upload Setup</html:link><br>
     <html:link action="statisticreportslistpage">Statistic Reports Setup</html:link>
  <!--  ##########   newly added links 1 ##################--->  
      				   <h4>#Authorization#</h4>
    <html:link action ="blacklistcardlistpage">Black List Cards</html:link><br>
    <html:link action ="tagfieldformatlistpage">TagField Format </html:link><br>
   <html:link action ="loginparamprocess.do?method=get">Login Param Setup</html:link><br>
  <html:link action="smsservicelistpage">SMS Service</html:link>
       				   	   <h4>#AlertMasterSetup#</h4>
    <html:link action ="alertmasterlist.do?method=search">Alert Master Form</html:link><br>
           				   <h4>#SwitchSetup#</h4>
    <html:link action ="switchlist.do?method=get">Switches List</html:link><br>
             				   <h4>#KeyIndexSetup#</h4>
    <html:link action ="keyindexlist.do?method=search">Key Index List</html:link><br>
     				   	   <h4>#Administration#</h4>
    <html:link action ="licensemasterprocess.do?method=get">License Setup</html:link><br>
      				   	   <h4>User Access</h4>
      <html:link action ="usersetuplistpage.do?userType=ISSADMIN&method=List">UserSetupFormSearch</html:link><br>
    <html:link action ="FtlPasswordchangesetup">FTL Password Change Setup</html:link><br>
    <html:link action ="passwordchangesetup">Password Change Setup</html:link><br>
    <html:link action ="rolefunctionlist.do?method=search&userType=ISSADMIN">Role Function Form</html:link><br>
    <html:link action ="adminloginsetup">AdminLoginForm</html:link><br>
            				<h4>#Log#</h4>
      <html:link action ="useractivitieslist.do?method=List">UserActivitiesSearch</html:link><br>
      <html:link action ="errorloglist.do?method=List">ErrorlogSearch</html:link><br>
    	 
  		       <h4>#Risk Management#</h4>
   <html:link action ="risktranxsalelist.do?tranxCode=SALE&method=search">Risk Tranx Sales</html:link><br>
   <html:link action ="risktranxsalelist.do?tranxCode=CASH&method=search">Risk Tranx Cash</html:link><br>
   <html:link action ="risktranxperiodlist.do">Risk Tranx Period</html:link><br>
   <html:link action ="riskcountrylistpage.do">Risk Country</html:link><br>
   <html:link action ="risktranxactionlist.do?method=search">Risk Tranx Action</html:link><br>
	
	  <!--  ##########   newly added links 2 ##################---> 
	
	<html:link action="withdrawallimitruleslistpage">Withdrawal Limit Rules</html:link>	<br>
	<html:link action="cardholderlimitadjustmentlistpage">Cardholder Limit Adjustment</html:link>	<br>
	<html:link action="originalrequestlist?action=preList">Original Request</html:link>	<br>
	<html:link action="photocopyrequestlist?action=preList">Photocopy Request</html:link>	<br>
	<html:link action="chargeback1list?action=preList">Charge Back 1</html:link>	<br>
	<html:link action="chargeback2list?action=preList">Charge Back 2</html:link><br>
	<html:link action="cardactivitylistpage">Card Activity</html:link>	<br>
	<html:link action="useractivitylistpage">User Activity</html:link>	<br>
	<html:link action="writeoffsetup?action=change">Write Off Setup</html:link>	<br>
	<html:link action="writeoffcardslistpage">Write Off Cards</html:link>	<br>

	<h4>#Letters#</h4>
	<html:link action="dispatchletterlist?action=preList">Dispatch Letter</html:link>	<br>
	<html:link action="dispatchlettermainpage">Dispatch Letters Management</html:link>	<br>
	<html:link action="chletterhistlist?action=preList">Cardholder Letter History</html:link>	<br>
  
      <h4>#Print#</h4>
    <html:link action="printlistpage">Print</html:link>

	<h4>#Reports#</h4>
	<html:link	action="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TR">Transaction Report</html:link>	<br>
	<html:link	action="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TNL">Transaction Listing</html:link>	<br>
	<html:link	action="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=STL">Settlement Report</html:link>	<br>
	<html:link	action="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=CRV">Card volume</html:link>	<br>
	<html:link	action="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=CBS">Cardholder Billing Statement</html:link>	<br>
	<html:link	action="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=BLR">Black List Report</html:link>	<br>
	<html:link	action="GenerateReportServlet?hdOpCode=3&mode=List&REPORT_CODE=TND">TransactionDetailReport</html:link>	<br>
	                                  
</BODY>
</html:html>
