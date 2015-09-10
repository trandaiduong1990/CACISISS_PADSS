
package org.transinfo.cacis.dataacess.daoimpl.oracle;

import org.transinfo.cacis.dataacess.DAOFactory;
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
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDeliverDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardEmbossingDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.CreditScoringDAO;
import org.transinfo.cacis.dataacess.dao.cardproduction.PinPrintingDAO;
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
import org.transinfo.cacis.dataacess.dao.useraccess.PasswordChangeDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.RoleFunctionSetupDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.UserSetupDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.accounting.CardBillingDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.accounting.CardHolderPaymentDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.accounting.CardHolderStatementDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.administration.LicenseMasterDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.alert.AlertMasterDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms.AddCardProcessDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms.CardGradeFormDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms.CardsRenewalDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms.SupplFormDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.authorization.BlackListCardDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.authorization.BlockMerchantDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.authorization.LoginParamDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.authorization.SMSServiceDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.authorization.ServerParamDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.authorization.SystemParamDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.authorization.TagFieldFormatDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.batchprocess.CardbatchProcessDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.bgprocess.BgProcessDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.billing.BillingDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction.ApplValidationDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction.ApplicationFormDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction.ApplicationProcessDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction.CardDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction.CardDeliverDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction.CardEmbossingDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction.CreditScoringDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction.PinPrintingDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.csr.CsrDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.AccountAdjustmentDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.AddProductDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.CardChangeDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.CardLevelLimitDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.CardReplacementDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.CardStatusDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.CustomerScreenProcessDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.FeeWaiverDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.IntWaiverDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.NonReconTransactionEnquiryDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.PayWaiverDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.PinResendDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice.TransactionEnquiryDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement.CTFProcessDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement.DisputeClaimFormDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement.DisputeConfigDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement.DisputeDocumentsDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement.DisputeManagementDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement.DocumentUploadDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement.StatisticReportsDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement.WorkItemDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.excell.UploadDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.help.HelpDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.key.KeyIndexDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.letters.ApplicationProcessSearchDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.letters.ChLetterHistDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.letters.DispatchLetterDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.letters.LetterApplMapDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.letters.LetterDispatchDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.letters.LetterTemplateDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.log.ErrorlogDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.log.UserActivitiesDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.printing.PrintDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.CardActivityDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.CardholderLimitAdjustmentDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.ChargeBack1DAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.ChargeBack2DAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.OriginalRequestDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.PhotocopyRequestDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.RiskCountryDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.RiskTranxActionDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.RiskTranxCashSalesDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.RiskTranxPeriodDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.UserActivityDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.WithdrawalLimitRulesDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.WriteOffCardsDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement.WriteOffDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.BranchDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CardProductDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CardProductFeeDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CardProductLimitDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CardProductRateDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CardProductRulesDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CardPromotionDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CardTypeDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CreditLimitProfileDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CurrencyRateDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CustomerGroupFeeDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CustomerTypeDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.CycleDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.EMVProfileDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.IssuerDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.MCCDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.ProductTranxDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.settings.SalaryProfileDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.switching.SwitchDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.transaction.TransactionDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.useraccess.AdminLoginDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.useraccess.PasswordChangeDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.useraccess.RoleFunctionDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.useraccess.UserSetupDAOImpl;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardBatchDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyNotificationSetupDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyPolicyDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.batchprocess.CardBatchDAOImpl;

import org.transinfo.cacis.dataacess.daoimpl.oracle.collectionmanagement.DelinquencyFeeSetupDAOImpt;
import org.transinfo.cacis.dataacess.daoimpl.oracle.collectionmanagement.DelinquencyNotificationSetupDAOImpl;
import org.transinfo.cacis.dataacess.daoimpl.oracle.collectionmanagement.DelinquencyPolicyDAOImpl;

//************* End *************//

public class DAOFactoryImpl extends DAOFactory {


	//******* For Setting Screens ******* // 

	public IssuerDAO getIssuerDAO(){
		return issuerDAO;
	}
	private IssuerDAO issuerDAO = new IssuerDAOImpl();

	public BranchDAO getBranchDAO(){
		return branchDAO;
	}
	private BranchDAO branchDAO = new BranchDAOImpl();

	public CardTypeDAO getCardTypeDAO(){
		return cardTypeDAO;
	}
	private CardTypeDAO cardTypeDAO = new CardTypeDAOImpl();

	public CustomerTypeDAO getCustomerTypeDAO(){
		return customerTypeDAO;
	}
	private CustomerTypeDAO customerTypeDAO = new CustomerTypeDAOImpl();

	public CycleDAO getCycleDAO(){
		return cycleDAO;
	}
	private CycleDAO cycleDAO = new CycleDAOImpl();

	public MCCDAO getMCCDAO(){
		return mccDAO;
	}
	private MCCDAO mccDAO = new MCCDAOImpl();

	public CardProductDAO getCardProductDAO (){
		return cardProductDAO;
	}
	private CardProductDAO  cardProductDAO = new CardProductDAOImpl();

	public CustomerGroupFeeDAO getCustomeGroupFeeDAO (){
		return customeGroupFeeDAO;
	}
	private CustomerGroupFeeDAO  customeGroupFeeDAO = new CustomerGroupFeeDAOImpl();

	public CardLevelLimitDAO getCardLevelLimitDAO (){
		return cardLevelLimitDAO;
	}
	private CardLevelLimitDAO  cardLevelLimitDAO = new CardLevelLimitDAOImpl();

	public CardProductLimitDAO getCardProductLimitDAO(){
		return cardProductLimitDAO;
	}
	private CardProductLimitDAO  cardProductLimitDAO = new CardProductLimitDAOImpl();

	public CardProductRateDAO getCardProductRateDAO (){
		return cardProductRateDAO;
	}
	private CardProductRateDAO  cardProductRateDAO = new CardProductRateDAOImpl();


	public CardProductFeeDAO getCardProductFeeDAO (){
		return cardProductFeeDAO;
	}
	private CardProductFeeDAO  cardProductFeeDAO = new CardProductFeeDAOImpl();


	public CardPromotionDAO getCardPromotionDAO (){
		return cardPromotionDAO;
	}
	private CardPromotionDAO  cardPromotionDAO = new CardPromotionDAOImpl();

	public CurrencyRateDAO getCurrencyRateDAO(){
		return currencyRateDAO;
	}
	private CurrencyRateDAO  currencyRateDAO = new CurrencyRateDAOImpl();

	public CardProductRulesDAO getCardProductRulesDAO() {
		return objCardProductRulesDAO;
	}
	private CardProductRulesDAO objCardProductRulesDAO = new CardProductRulesDAOImpl();

	//******* Card Production Screens******* // 

	public ApplicationFormDAO getApplicationFormDAO (){
		return applicationFormDAO;
	}
	private ApplicationFormDAO  applicationFormDAO = new ApplicationFormDAOImpl();


	public ApplicationProcessDAO getApplicationProcessDAO (){
		return applicationProcessDAO;
	}
	private ApplicationProcessDAO  applicationProcessDAO = new ApplicationProcessDAOImpl();


	public CardEmbossingDAO getCardEmbossingDAO(){
		return cardEmbossingDAO;
	}
	private CardEmbossingDAO  cardEmbossingDAO = new CardEmbossingDAOImpl();


	public PinPrintingDAO getPinPrintingDAO(){
		return pinPrintingDAO;
	}
	private PinPrintingDAO  pinPrintingDAO = new PinPrintingDAOImpl();

	public CardDeliverDAO getCardDeliverDAO(){
		return cardDeliverDAO;
	}
	private CardDeliverDAO  cardDeliverDAO = new CardDeliverDAOImpl();

	public CardDAO getCardDAO(){
		return cardDAO;
	}
	private CardDAO cardDAO = new CardDAOImpl();

	//******* Card Batch Process ******* //

	public CardBatchProcessADO getCardBatchProcessADO(){
		return cardBatchProcessDAO;
	}
	private CardBatchProcessADO cardBatchProcessDAO = new CardbatchProcessDAOImpl();

	//******* Transaction ******* //

	public TransactionDAO getTransactionDAO(){
		return transactionDAO;
	}
	private TransactionDAO transactionDAO = new TransactionDAOImpl();


	//******* Application Forms screens ******* //

	public SupplFormDAO getSupplFormDAO(){
		return supplementaryFormDAO;
	}
	private SupplFormDAO supplementaryFormDAO = new SupplFormDAOImpl();

	public CardGradeFormDAO getCardGradeFormDAO(){
		return cardGradeFormDAO;
	}
	private CardGradeFormDAO cardGradeFormDAO = new CardGradeFormDAOImpl();

	public CardsRenewalDAO getCardsRenewalDAO(){
		return cardsRenewalDAO;
	}
	private CardsRenewalDAO cardsRenewalDAO = new CardsRenewalDAOImpl();


	public AddCardProcessDAO getAddCardProcessDAO(){
		return addCardProcessDAO;
	}
	private AddCardProcessDAO addCardProcessDAO = new AddCardProcessDAOImpl();



	//******* For CustomerService Screens******* // 

	public CardReplacementDAO getCardReplacementDAO(){
		return cardReplace;
	}
	private CardReplacementDAO cardReplace = new CardReplacementDAOImpl();

	public CustomerScreenProcessDAO getCustomerScreenProcessDAO(){
		return customerScreen;
	}
	private CustomerScreenProcessDAO customerScreen = new CustomerScreenProcessDAOImpl();

	public CardChangeDAO getCardChangeDAO(){
		return cardChange;
	}
	private CardChangeDAO cardChange = new CardChangeDAOImpl();

	public FeeWaiverDAO getFeeWaiverDAO(){
		return feeWaiver;
	}
	private FeeWaiverDAO feeWaiver = new FeeWaiverDAOImpl();

	public IntWaiverDAO getIntWaiverDAO(){
		return intWaiver;
	}
	private IntWaiverDAO intWaiver = new IntWaiverDAOImpl();

	public PayWaiverDAO getPayWaiverDAO(){
		return payWaiver;
	}
	private PayWaiverDAO payWaiver = new PayWaiverDAOImpl();

	public PinResendDAO getPinResendDAO(){
		return pinResend;
	}
	private PinResendDAO pinResend = new PinResendDAOImpl();


	//******* Card Accounting Screens ******* //
	public CardHolderPaymentDAO getCardHolderPaymentDAO(){
		return objCardHolderPay;
	}
	private CardHolderPaymentDAO objCardHolderPay = new CardHolderPaymentDAOImpl();

	public CardBillingDAO getCardBillingDAO(){
		return objCardBilling;
	}
	private CardBillingDAO objCardBilling = new CardBillingDAOImpl();

	public CardHolderStatementDAO getCardHolderStatementDAO(){
		return objCardHolderStatement;
	}
	private CardHolderStatementDAO objCardHolderStatement = new CardHolderStatementDAOImpl();

	/* ******* for CustomerRelation(CSR) Screens	********* */
	public CsrDAO getCsrDAO(){
		return objCsrDAOimpl;
	}
	private CsrDAO objCsrDAOimpl = new CsrDAOImpl();


	/* ******* for upolad (Excell) Screens	********** */
	public UploadDAO getUploadDAO(){
		return objUploadDAOImpl;
	}
	private UploadDAO objUploadDAOImpl = new UploadDAOImpl();

	/* ******* for Dispute Management	********** */
	public DisputeConfigDAO getDisputeConfigDAO() {
		return objDisputeConfigDAO;
	}
	private DisputeConfigDAO objDisputeConfigDAO = new DisputeConfigDAOImpl();

	public DisputeClaimFormDAO getDisputeClaimFormDAO(){
		return objDisputeClaimImpl;
	}
	private DisputeClaimFormDAO objDisputeClaimImpl = new DisputeClaimFormDAOImpl();

	public WorkItemDAO getWorkItemDAO(){
		return objWorkItemDAOImpl;
	}
	private WorkItemDAO objWorkItemDAOImpl = new WorkItemDAOImpl();

	public DisputeDocumentsDAO getDisputeDocumentsDAO() {
		return objDisputeDocumentsDAO;
	}

	private DisputeDocumentsDAO objDisputeDocumentsDAO = new DisputeDocumentsDAOImpl();

	public DocumentUploadDAO getDocumentUploadDAO() {
		return objDocumentUploadDAO;
	}

	private DocumentUploadDAO objDocumentUploadDAO = new DocumentUploadDAOImpl();

	public StatisticReportsDAO getStatisticReportsDAO() {
		return objStatisticReportsImpl;
	}
	private StatisticReportsDAO objStatisticReportsImpl = new StatisticReportsDAOImpl();

	public DisputeManagementDAO getDisputeManagementDAO() {
		return objDisputeManagementDAO;
	}
	private DisputeManagementDAO objDisputeManagementDAO = new DisputeManagementDAOImpl();

	public TransactionEnquiryDAO getTransactionEnquiryDAO() {
		return objTransactionEnquiryDAO;
	}
	private TransactionEnquiryDAO objTransactionEnquiryDAO = new TransactionEnquiryDAOImpl();

	public NonReconTransactionEnquiryDAO getNonReconTransactionEnquiryDAO() {
		return objNonReconTransactionEnquiryDAO;
	}
	private NonReconTransactionEnquiryDAO objNonReconTransactionEnquiryDAO = new NonReconTransactionEnquiryDAOImpl();

	public AddProductDAO getAddProductDAO() {
		return objAddProductDAO;
	}
	private AddProductDAO objAddProductDAO = new AddProductDAOImpl();

	public CTFProcessDAO getCTFProcessDAO() {
		return objCtfProcessDAO;
	}
	private CTFProcessDAO objCtfProcessDAO = new CTFProcessDAOImpl();

	//**** ***********newlyadded 1 ****/
	public BlackListCardDAO getBlackListDAO() {
		return objBlackListCardDAO;
	}
	private BlackListCardDAO objBlackListCardDAO = new BlackListCardDAOImpl();

	public TagFieldFormatDAO getTagFieldDAO() {
		return objTagFieldFormatDAO;
	}
	private TagFieldFormatDAO objTagFieldFormatDAO = new TagFieldFormatDAOImpl();

	public ServerParamDAO getServerParamDAO() {
		return objServerParamDAO;
	}
	private ServerParamDAO objServerParamDAO = new ServerParamDAOImpl();

	public SystemParamDAO getSystemParamDAO() {
		return objSystemParamDAO;
	}
	private SystemParamDAO objSystemParamDAO = new SystemParamDAOImpl();

	public LoginParamDAO getLoginParamDAO() {
		return objLoginParamDAO;
	}
	private LoginParamDAO objLoginParamDAO = new LoginParamDAOImpl();

	public UserActivitiesDAO getUserActivitiesDAO() {
		return objUserActivitiesDAO;
	}
	private UserActivitiesDAO objUserActivitiesDAO = new UserActivitiesDAOImpl();

	public ErrorlogDAO getErrorlogDAO() {
		return objErrorlogDAO;
	}
	private ErrorlogDAO objErrorlogDAO = new ErrorlogDAOImpl();

	public AlertMasterDAO getAlertMasterDAO() {
		return objAlertMasterDAO;
	}
	private AlertMasterDAO objAlertMasterDAO = new AlertMasterDAOImpl();

	public SwitchDAO getSwitchDAO() {
		return objSwitchDAO;
	}
	private SwitchDAO objSwitchDAO = new SwitchDAOImpl();

	public KeyIndexDAO getKeyIndexDAO() {
		return objKeyIndexDAO;
	}
	private KeyIndexDAO objKeyIndexDAO = new KeyIndexDAOImpl();

	public LicenseMasterDAO getLicenseMasterDAO() {
		return objLicenseMasterDAO;
	}
	private LicenseMasterDAO objLicenseMasterDAO = new LicenseMasterDAOImpl();

	public PasswordChangeDAO getPasswordChangeDAO() {
		return objPasswordChangeDAO;
	}
	private PasswordChangeDAO objPasswordChangeDAO = new PasswordChangeDAOImpl();

	public UserSetupDAO getUserSetupDAO() {
		return objUserSetupDAO;
	}
	private UserSetupDAO objUserSetupDAO = new UserSetupDAOImpl();

	public RoleFunctionSetupDAO getRoleFunctionSetupDAO() {
		return objRoleFunctionSetupDAO;        
	}
	private RoleFunctionSetupDAO objRoleFunctionSetupDAO = new RoleFunctionDAOImpl();

	public AdminLoginDAO getAdminLoginDAO(){
		return objAdminLoginDAO;
	}
	private AdminLoginDAO objAdminLoginDAO = new AdminLoginDAOImpl();

	public RiskTranxSaleCashDAO getRiskTranxSaleCashDAO(){
		return objRiskTranxSaleCashDAO;
	}
	private RiskTranxSaleCashDAO objRiskTranxSaleCashDAO = new RiskTranxCashSalesDAOImpl();

	public RiskTranxPeriodDAO getRiskTranxPeriodDAO(){
		return objRiskTranxPeriodDAO;
	}
	private RiskTranxPeriodDAO objRiskTranxPeriodDAO = new RiskTranxPeriodDAOImpl();

	public RiskCountryDAO getRiskCountryDAO(){
		return objRiskCountryDAO;
	}
	private RiskCountryDAO objRiskCountryDAO = new RiskCountryDAOImpl();

	public RiskTranxActionDAO getRiskTranxActionDAO(){
		return objRiskActionDAO;
	}
	private RiskTranxActionDAO objRiskActionDAO = new RiskTranxActionDAOImpl();

	//************* End *************//

	/************newly added2********/
	// letters module 
	public DispatchLetterDAO getDispatchLetterDAO() {
		return dispatchLetterDAO;
	}

	private DispatchLetterDAO dispatchLetterDAO = new DispatchLetterDAOImpl();

	public ChLetterHistDAO getChLetterHistDAO() {
		return chLetterHistDAO;
	}

	private ChLetterHistDAO chLetterHistDAO = new ChLetterHistDAOImpl();

	public ApplicationProcessSearchDAO getApplicationProcessSearchDAO() {
		return applicationProcessSearchDAO;
	}

	private ApplicationProcessSearchDAO applicationProcessSearchDAO = new ApplicationProcessSearchDAOImpl();

	// riskmanagement module 
	public CardActivityDAO getCardActivityDAO() {
		return cardActivityDAO;
	}

	private CardActivityDAO cardActivityDAO = new CardActivityDAOImpl();

	public CardholderLimitAdjustmentDAO getCardholderLimitAdjustmentDAO() {
		return cardholderLimitAdjustmentDAO;
	}

	private CardholderLimitAdjustmentDAO cardholderLimitAdjustmentDAO = new CardholderLimitAdjustmentDAOImpl();

	public OriginalRequestDAO getOriginalRequestDAO() {
		return originalRequestDAO;
	}

	private OriginalRequestDAO originalRequestDAO = new OriginalRequestDAOImpl();

	public PhotocopyRequestDAO getPhotocopyRequestDAO() {
		return photocopyRequestDAO;
	}

	private PhotocopyRequestDAO photocopyRequestDAO = new PhotocopyRequestDAOImpl();

	public ChargeBack1DAO getChargeBack1DAO() {
		return chargeBack1DAO;
	}

	private ChargeBack1DAO chargeBack1DAO = new ChargeBack1DAOImpl();

	public ChargeBack2DAO getChargeBack2DAO() {
		return chargeBack2DAO;
	}

	private ChargeBack2DAO chargeBack2DAO = new ChargeBack2DAOImpl();

	public UserActivityDAO getUserActivityDAO() {
		return userActivityDAO;
	}

	private UserActivityDAO userActivityDAO = new UserActivityDAOImpl();

	public WithdrawalLimitRulesDAO getWithdrawalLimitRulesDAO() {
		return withdrawalLimitRulesDAO;
	}

	private WithdrawalLimitRulesDAO withdrawalLimitRulesDAO = new WithdrawalLimitRulesDAOImpl();

	public WriteOffDAO getWriteOffDAO() {
		return writeOffDAO;
	}

	private WriteOffDAO writeOffDAO = new WriteOffDAOImpl();

	public WriteOffCardsDAO getWriteOffCardsDAO() {
		return writeOffCardsDAO;
	}

	private WriteOffCardsDAO writeOffCardsDAO = new WriteOffCardsDAOImpl();	

	//	 Authorization Screens
	public SMSServiceDAO getSMSServiceDAO() {
		return objSMSServiceDAO;
	}

	private SMSServiceDAO objSMSServiceDAO = new SMSServiceDAOImpl();

	// Help Screens 
	public HelpDAO getHelpDAO() {
		return objHelp;
	}

	private HelpDAO objHelp = new HelpDAOImpl();

	//printing
	public PrintDAO getPrintDAO() {
		return objPrintImpl;
	}

	private PrintDAO objPrintImpl = new PrintDAOImpl();

	@Override
	public SalaryProfileDAO getSalaryProfileDAO() {
		return objSalaryProfileDAO;
	}

	private SalaryProfileDAO objSalaryProfileDAO = new SalaryProfileDAOImpl();

	// billing
	public BillingDAO getBillingDAO(){
		return billingDAO;
	}
	private BillingDAO billingDAO = new BillingDAOImpl();

	// back ground process
	public BgProcessDAO getBgProcessDAO(){
		return bgProcessDAO;
	}
	private BgProcessDAO bgProcessDAO = new BgProcessDAOImpl();

	// customer service
	public CardStatusDAO getCardStatusDAO(){
		return cardStatusDAO;
	}
	private CardStatusDAO cardStatusDAO = new CardStatusDAOImpl();

	//@Override
	public BlockMerchantDAO getBlockMerchantDAO() {
		return objBlockMerchantDAO;
	}
	private BlockMerchantDAO objBlockMerchantDAO = new BlockMerchantDAOImpl();


	public AccountAdjustmentDAO getAccountAdjustmentDAO() {

		return objAccountAdjustmentDAO;
	}
	private  AccountAdjustmentDAO objAccountAdjustmentDAO = new  AccountAdjustmentDAOImpl();

	public ProductTranxDAO getProductTranxDAO() {
		return objProductTranxDAO;
	}
	private ProductTranxDAO objProductTranxDAO = new ProductTranxDAOImpl();
	
	@Override
	public EMVProfileDAO getEMVProfileDAO() {
		
		return objEmvProfileDAO;
	}
	private EMVProfileDAO objEmvProfileDAO = new EMVProfileDAOImpl();

	@Override
	public ApplValidationDAO getApplValidationDAO() {
		
		return objApplValidation;
	}
	private ApplValidationDAO objApplValidation = new ApplValidationDAOImpl();

	@Override
	public CreditScoringDAO getCreditScoringDAO() {

		return objCreditScoring;
	}
	public CreditScoringDAO objCreditScoring = new CreditScoringDAOImpl();

	@Override
	public CreditLimitProfileDAO getCreditLimitProfileDAO() {
		
		return objCreditLimitProfile;
	}
	public CreditLimitProfileDAO objCreditLimitProfile = new CreditLimitProfileDAOImpl();

	@Override
	public LetterApplMapDAO getLetterApplMapDAO() {
		
		return objLetterApplMap;
	}
	public LetterApplMapDAO objLetterApplMap = new LetterApplMapDAOImpl();

	@Override
	public LetterTemplateDAO getLetterTemplateDAO() {
		
		return objLetterTemplate;
	}
	public LetterTemplateDAO objLetterTemplate = new LetterTemplateDAOImpl();

	@Override
	public LetterDispatchDAO getLetterDispatchDAO() {

		return objLetterDispatch;
	}
	public LetterDispatchDAO objLetterDispatch = new LetterDispatchDAOImpl();

	@Override
	public CardBatchDAO getCardBatchDAO() {
		// TODO Auto-generated method stub
		return objCardBatchDAO;
	}

	private CardBatchDAO objCardBatchDAO = new CardBatchDAOImpl();

	@Override
	public DelinquencyPolicyDAO getDelinquencyPolicyDAO() {
		// TODO Auto-generated method stub
		return objDelinquencyPolicyDAO;
	}

	private DelinquencyPolicyDAO objDelinquencyPolicyDAO = new DelinquencyPolicyDAOImpl();

	@Override
	public DelinquencyFeeSetupDAO getDelinquencyFeeSetupDAO() {
		// TODO Auto-generated method stub
		return objDelinquencyFeeSetupDAO;
	}

	private DelinquencyFeeSetupDAO objDelinquencyFeeSetupDAO = new DelinquencyFeeSetupDAOImpt();

	@Override
	public DelinquencyNotificationSetupDAO getDelinquencyNotificationSetupDAO() {
		// TODO Auto-generated method stub
		return objDelinquencyNotificationSetupDAO;
	}

	private DelinquencyNotificationSetupDAO objDelinquencyNotificationSetupDAO = new DelinquencyNotificationSetupDAOImpl();
}