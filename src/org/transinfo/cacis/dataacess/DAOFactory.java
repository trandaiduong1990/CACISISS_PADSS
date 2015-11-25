
package org.transinfo.cacis.dataacess;

import javax.servlet.ServletContext;

import org.transinfo.cacis.dataacess.dao.accounting.CardBillingDAO;
import org.transinfo.cacis.dataacess.dao.accounting.CardHolderPaymentDAO;
import org.transinfo.cacis.dataacess.dao.accounting.CardHolderStatementDAO;
import org.transinfo.cacis.dataacess.dao.administration.LicenseMasterDAO;
import org.transinfo.cacis.dataacess.dao.alert.AlertMasterDAO;
import org.transinfo.cacis.dataacess.dao.applicationforms.AddCardProcessDAO;
import org.transinfo.cacis.dataacess.dao.applicationforms.CardGradeFormDAO;
import org.transinfo.cacis.dataacess.dao.applicationforms.CardsRenewalDAO;
import org.transinfo.cacis.dataacess.dao.applicationforms.SupplFormDAO;
import org.transinfo.cacis.dataacess.dao.authorization.BlackListCardDAO;
import org.transinfo.cacis.dataacess.dao.authorization.BlockMerchantDAO;
import org.transinfo.cacis.dataacess.dao.authorization.LoginParamDAO;
import org.transinfo.cacis.dataacess.dao.authorization.SMSServiceDAO;
import org.transinfo.cacis.dataacess.dao.authorization.ServerParamDAO;
import org.transinfo.cacis.dataacess.dao.authorization.SystemParamDAO;
import org.transinfo.cacis.dataacess.dao.authorization.TagFieldFormatDAO;
import org.transinfo.cacis.dataacess.dao.batchprocess.CardBatchProcessADO;
import org.transinfo.cacis.dataacess.dao.bgprocess.BgProcessDAO;
import org.transinfo.cacis.dataacess.dao.billing.BillingDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplValidationDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplicationFormDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplicationProcessDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardBatchDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDeliverDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardEmbossingDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.CreditScoringDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.PinPrintingDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAccountDetailsDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionConfigDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyNotificationSetupDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyPolicyDAO;
import org.transinfo.cacis.dataacess.dao.csr.CsrDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.AccountAdjustmentDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.AddProductDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.CardChangeDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.CardLevelLimitDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.CardReplacementDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.CardStatusDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.CustomerScreenProcessDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.FeeWaiverDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.IntWaiverDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.NonReconTransactionEnquiryDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.PayWaiverDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.PinResendDAO;
import org.transinfo.cacis.dataacess.dao.customerservice.TransactionEnquiryDAO;
import org.transinfo.cacis.dataacess.dao.disputemanagement.CTFProcessDAO;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeClaimFormDAO;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeConfigDAO;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeDocumentsDAO;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeManagementDAO;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DocumentUploadDAO;
import org.transinfo.cacis.dataacess.dao.disputemanagement.StatisticReportsDAO;
import org.transinfo.cacis.dataacess.dao.disputemanagement.WorkItemDAO;
import org.transinfo.cacis.dataacess.dao.excell.UploadDAO;
import org.transinfo.cacis.dataacess.dao.help.HelpDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryManagementDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;
import org.transinfo.cacis.dataacess.dao.key.KeyIndexDAO;
import org.transinfo.cacis.dataacess.dao.letters.ApplicationProcessSearchDAO;
import org.transinfo.cacis.dataacess.dao.letters.ChLetterHistDAO;
import org.transinfo.cacis.dataacess.dao.letters.DispatchLetterDAO;
import org.transinfo.cacis.dataacess.dao.letters.LetterApplMapDAO;
import org.transinfo.cacis.dataacess.dao.letters.LetterDispatchDAO;
import org.transinfo.cacis.dataacess.dao.letters.LetterTemplateDAO;
import org.transinfo.cacis.dataacess.dao.log.ErrorlogDAO;
import org.transinfo.cacis.dataacess.dao.log.UserActivitiesDAO;
import org.transinfo.cacis.dataacess.dao.printing.PrintDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.CardActivityDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.CardholderLimitAdjustmentDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.ChargeBack1DAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.ChargeBack2DAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.OriginalRequestDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.PhotocopyRequestDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskCountryDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxActionDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxPeriodDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxSaleCashDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.UserActivityDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.WithdrawalLimitRulesDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.WriteOffCardsDAO;
import org.transinfo.cacis.dataacess.dao.riskmanagement.WriteOffDAO;
import org.transinfo.cacis.dataacess.dao.settings.BranchDAO;
import org.transinfo.cacis.dataacess.dao.settings.CardProductDAO;
import org.transinfo.cacis.dataacess.dao.settings.CardProductFeeDAO;
import org.transinfo.cacis.dataacess.dao.settings.CardProductLimitDAO;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRateDAO;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRulesDAO;
import org.transinfo.cacis.dataacess.dao.settings.CardPromotionDAO;
import org.transinfo.cacis.dataacess.dao.settings.CardTypeDAO;
import org.transinfo.cacis.dataacess.dao.settings.CreditLimitProfileDAO;
import org.transinfo.cacis.dataacess.dao.settings.CurrencyRateDAO;
import org.transinfo.cacis.dataacess.dao.settings.CustomerGroupFeeDAO;
import org.transinfo.cacis.dataacess.dao.settings.CustomerTypeDAO;
import org.transinfo.cacis.dataacess.dao.settings.CycleDAO;
import org.transinfo.cacis.dataacess.dao.settings.EMVProfileDAO;
import org.transinfo.cacis.dataacess.dao.settings.IssuerDAO;
import org.transinfo.cacis.dataacess.dao.settings.MCCDAO;
import org.transinfo.cacis.dataacess.dao.settings.ProductTranxDAO;
import org.transinfo.cacis.dataacess.dao.settings.SalaryProfileDAO;
import org.transinfo.cacis.dataacess.dao.switching.SwitchDAO;
import org.transinfo.cacis.dataacess.dao.transaction.TransactionDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.AdminLoginDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.AssignUserDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.PasswordChangeDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.RoleFunctionSetupDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.UserSetupDAO;

public abstract class DAOFactory {
	

   /******* Settings*******/
    public abstract IssuerDAO getIssuerDAO ();
    public abstract BranchDAO getBranchDAO();
    public abstract CardTypeDAO getCardTypeDAO();
    public abstract CustomerTypeDAO getCustomerTypeDAO();
    public abstract CardProductDAO getCardProductDAO();
    public abstract CustomerGroupFeeDAO getCustomeGroupFeeDAO();
    public abstract CardLevelLimitDAO getCardLevelLimitDAO();
    public abstract CardProductRateDAO getCardProductRateDAO();
    public abstract CardProductFeeDAO getCardProductFeeDAO();
    public abstract CycleDAO getCycleDAO();
    public abstract MCCDAO getMCCDAO();
    public abstract CardPromotionDAO getCardPromotionDAO();
    public abstract CardProductLimitDAO getCardProductLimitDAO();
    public abstract CurrencyRateDAO getCurrencyRateDAO();
    public abstract SalaryProfileDAO getSalaryProfileDAO();
    public abstract EMVProfileDAO getEMVProfileDAO();
    public abstract CreditLimitProfileDAO getCreditLimitProfileDAO();
    
    /*******  Card Production  *******/
    public abstract ApplicationFormDAO getApplicationFormDAO();
    public abstract  ApplicationProcessDAO getApplicationProcessDAO();
    public abstract CardEmbossingDAO getCardEmbossingDAO();
    public abstract PinPrintingDAO getPinPrintingDAO();
    public abstract CardDeliverDAO getCardDeliverDAO();
    public abstract CardDAO getCardDAO();
    public abstract ProductTranxDAO getProductTranxDAO();
	public abstract CardProductRulesDAO getCardProductRulesDAO();
	public abstract ApplValidationDAO getApplValidationDAO();
	public abstract CreditScoringDAO getCreditScoringDAO();

    /*******  Card Batch Process  *******/
    public abstract CardBatchProcessADO getCardBatchProcessADO();

    /*******  Transaction  *******/
    public abstract TransactionDAO getTransactionDAO();
    
    /******* Application Forms ******/
    public abstract SupplFormDAO getSupplFormDAO();
    public abstract CardReplacementDAO getCardReplacementDAO();
    public abstract CardGradeFormDAO getCardGradeFormDAO ();
    public abstract CardsRenewalDAO getCardsRenewalDAO();
    public abstract CustomerScreenProcessDAO getCustomerScreenProcessDAO();
    public abstract CardChangeDAO getCardChangeDAO();
    public abstract AccountAdjustmentDAO getAccountAdjustmentDAO();
    public abstract PinResendDAO getPinResendDAO();
    
    public abstract FeeWaiverDAO getFeeWaiverDAO();
    public abstract IntWaiverDAO getIntWaiverDAO();
    public abstract PayWaiverDAO getPayWaiverDAO();
    
    public abstract AddCardProcessDAO getAddCardProcessDAO();
 
    /******* Card Accounting  *******/
     public abstract CardHolderPaymentDAO getCardHolderPaymentDAO();
     public abstract CardBillingDAO getCardBillingDAO();
     public abstract CardHolderStatementDAO getCardHolderStatementDAO();
     
     /******* CustomerRelation(CSR) Screens *******/	
    public abstract CsrDAO getCsrDAO();
    
    
    /******* Upload (Excell) Screens *******/
    public abstract UploadDAO getUploadDAO();
    
    /******* Dispute Management *******/
	public abstract DisputeConfigDAO getDisputeConfigDAO();
	public abstract DisputeDocumentsDAO getDisputeDocumentsDAO();
	public abstract DocumentUploadDAO getDocumentUploadDAO();
	public abstract DisputeClaimFormDAO getDisputeClaimFormDAO();
	public abstract WorkItemDAO getWorkItemDAO();
	public abstract StatisticReportsDAO getStatisticReportsDAO();

	public abstract DisputeManagementDAO getDisputeManagementDAO();
	public abstract TransactionEnquiryDAO getTransactionEnquiryDAO();
	public abstract NonReconTransactionEnquiryDAO getNonReconTransactionEnquiryDAO();
	public abstract CTFProcessDAO getCTFProcessDAO();
    
    //****************************//
      //  Authorization
	public abstract BlackListCardDAO getBlackListDAO();
	public abstract BlockMerchantDAO getBlockMerchantDAO();
	public abstract SMSServiceDAO getSMSServiceDAO();
	  
    public abstract TagFieldFormatDAO getTagFieldDAO();
    public abstract ServerParamDAO getServerParamDAO();
    public abstract SystemParamDAO getSystemParamDAO();
    public abstract LoginParamDAO getLoginParamDAO();
    public abstract AlertMasterDAO getAlertMasterDAO();
    public abstract SwitchDAO getSwitchDAO();
    public abstract KeyIndexDAO getKeyIndexDAO();  
    public abstract LicenseMasterDAO getLicenseMasterDAO(); 
    public abstract PasswordChangeDAO getPasswordChangeDAO();
    public abstract UserSetupDAO getUserSetupDAO();
    public abstract AssignUserDAO getAssignUserDAO();
    public abstract UserActivitiesDAO getUserActivitiesDAO();
    public abstract ErrorlogDAO getErrorlogDAO();
    public abstract RoleFunctionSetupDAO getRoleFunctionSetupDAO();
    public abstract AdminLoginDAO getAdminLoginDAO();
    public abstract RiskTranxSaleCashDAO getRiskTranxSaleCashDAO();
    public abstract RiskTranxPeriodDAO getRiskTranxPeriodDAO();
    public abstract RiskCountryDAO getRiskCountryDAO();
    public abstract RiskTranxActionDAO getRiskTranxActionDAO();
    
    //********* End ***********// 

    //  ****************************//
    //Letters
	public abstract DispatchLetterDAO getDispatchLetterDAO();
	public abstract ChLetterHistDAO getChLetterHistDAO();
	public abstract ApplicationProcessSearchDAO getApplicationProcessSearchDAO();
	public abstract LetterApplMapDAO getLetterApplMapDAO();
	public abstract LetterTemplateDAO getLetterTemplateDAO();
	public abstract LetterDispatchDAO getLetterDispatchDAO();

   // Risk Management
	public abstract WithdrawalLimitRulesDAO getWithdrawalLimitRulesDAO();

	public abstract CardholderLimitAdjustmentDAO getCardholderLimitAdjustmentDAO();

	public abstract CardActivityDAO getCardActivityDAO();

	public abstract UserActivityDAO getUserActivityDAO();
	
	public abstract OriginalRequestDAO getOriginalRequestDAO();
	
	public abstract PhotocopyRequestDAO getPhotocopyRequestDAO();
	
	public abstract ChargeBack1DAO getChargeBack1DAO();
	
	public abstract ChargeBack2DAO getChargeBack2DAO();
	
	public abstract WriteOffDAO getWriteOffDAO();
	
	public abstract WriteOffCardsDAO getWriteOffCardsDAO();
   
	//Help  
	public abstract HelpDAO getHelpDAO();
	
	//printing
	public abstract PrintDAO getPrintDAO();
	
	// billing
	public abstract BillingDAO getBillingDAO();

	// back ground process
	public abstract BgProcessDAO getBgProcessDAO();
	
	// customer service
	public abstract CardStatusDAO getCardStatusDAO();
	
	// add product
	public abstract AddProductDAO getAddProductDAO();
	
    private static DAOFactory instance;
    private static int accessConfig=1; //by default access DB
    protected static ServletContext servletCtxt;

    public static void setAccessTypeConfig(int access){

        accessConfig = access;

    }
    
    /*******  Card Batch Process  *******/
    public abstract CardBatchDAO getCardBatchDAO();
    
    // collection management
    public abstract DelinquencyPolicyDAO getDelinquencyPolicyDAO();
    public abstract DelinquencyFeeSetupDAO getDelinquencyFeeSetupDAO();
    public abstract DelinquencyNotificationSetupDAO getDelinquencyNotificationSetupDAO();
    public abstract CollectionConfigDAO getCollectionConfigDAO();
    public abstract CollectionAgentDAO getCollectionAgentDAO();
    public abstract CollectionAgeingDAO getCollectionAgeingDAO();
    public abstract CollectionAgeingActionDAO getCollectionAgeingActionDAO();
    public abstract CollectionAccountDetailsDAO getCollectionAccountDetailsDAO();

    //Inventory
    public abstract InventoryMasterDAO getInventoryMasterDAO();
    public abstract InventoryManagementDAO getInventoryManagementDAO();
    
    public static void setCtxt(ServletContext ctxt){

            servletCtxt = ctxt;

    }

    public static DAOFactory getInstance () {

        if (instance == null) {
            instance = loadInstance (accessConfig);
        }
        System.out.println("***********DAOFactory : Reurning Instance &&&&&&&&&&&&&&" );
        return instance;
    }

   /* private static DAOFactory loadInstance (int accessConfig){

        System.out.println("***********DAOFactory : LOADING &&&&&&&&&&&&&&" + accessConfig);


        switch (accessConfig) {

            default:
                return new org.transinfo.cacis.dataacess.daoimpl.oracle.DAOFactoryImpl();

        }
    }*/

    public static DAOFactory loadInstance (int accessConfig){

        System.out.println("***********DAOFactory : LOADING &&&&&&&&&&&&&&" + accessConfig);


        switch (accessConfig) {

            default:
                return new org.transinfo.cacis.dataacess.daoimpl.oracle.DAOFactoryImpl();

        }
    }
	
}