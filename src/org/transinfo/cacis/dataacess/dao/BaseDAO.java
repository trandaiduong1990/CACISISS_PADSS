package org.transinfo.cacis.dataacess.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.batchprocess.SearchInstantCardDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.batchprocess.CardBatchForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyPolicyForm;

@SuppressWarnings("unchecked")
public interface BaseDAO {

	int ADD = 0;
	int UPDATE = 1;
	int DELETE = 2;
	int INQUIRY = 3;
	int CREATE = 4;
	int RECORDS_IN_PAGE = 10;

	// UPLOAD_DOCUMENTS_PATH is used at DocumentUploadDAOImpl. It is a path for
	// saving the
	// files uploaded by the user to the local system. It's format is
	// C:\\upload_documents\\[ClaimNumber]\\[UploadDate]\\request\\[FileName]
	static final String UPLOAD_DOCUMENTS_PATH = "C:\\Tomcat5\\webapps\\cacis\\upload_documents\\";

	// PRINT_DOCUMENTS_FOLDER is used at (Print module) PrintDAOImpl.
	// This folder is used to store the pdf files that required for printing
	// using print screen.
	static final String PRINT_DOCUMENTS_FOLDER = "C:\\Tomcat5\\webapps\\cacis\\printing";

	// The location path of pdftops tool stored. This is used in (Print module)
	// PrintDAOImpl for converting the
	// pdf file to ps file for printing service.

	static final String PDF_TO_PS_TOOL_PATH = "C:\\Tomcat5\\webapps\\cacis\\pdftops";

	public Map dateListData() throws TPlusException;

	public Map monthListData() throws TPlusException;

	public Map yearListData() throws TPlusException;

	public Map expiryYearListData() throws TPlusException;

	public Map genderListData() throws TPlusException;

	public Map maritalListData() throws TPlusException;

	public Map relationshipListData() throws TPlusException;

	public Map educationListData() throws TPlusException;

	public Map jobTypeListData() throws TPlusException;

	public Map jobStatusListData() throws TPlusException;

	public Map cardProcessStatusList() throws TPlusException;

	public Map cardStatusListData(int fromStatusId) throws TPlusException;

	public Map switchTypeList() throws TPlusException;

	public Map statusListData(String groupId) throws TPlusException;

	public Map getUserType() throws TPlusException;

	public Map branchListData(String issuerId) throws TPlusException;

	public Map customerTypeListData(String issuerId) throws TPlusException;

	public Map cardTypeListData(String issuerId) throws TPlusException;

	public Map cardProductListData(String issuerId) throws TPlusException;

	// for ApplicationForm docproofs
	public Map documentProofList(String issuerId) throws TPlusException;

	public Map cardProductTypeListData() throws TPlusException;

	public Map countryListData() throws TPlusException;

	public Map currencyListData() throws TPlusException;

	public Map disputeMotoListData() throws TPlusException;

	public Map issuerCurrencyListData() throws TPlusException;

	public Map letterTypesList(String Category) throws TPlusException;

	public Map merchantCategoryListData() throws TPlusException;

	public Map tranxCodeListData() throws TPlusException;

	public Map issuerListData(String issuerType) throws TPlusException;

	public Map getUserList(String issuerid) throws TPlusException;

	public ArrayList getSearchList(String Sql, int PageNo)
			throws TPlusException;

	public ArrayList getList(String Sql) throws TPlusException;

	// dispute Management
	public Map claimTypeListData() throws TPlusException;

	public Map reasonCodeListData() throws TPlusException;

	public Map nonMandatoryDocumentsListData(String issuerId, int reasonCode)
			throws TPlusException;

	public Map mandatoryDocumentsListData(String issuerId, int reasonCode)
			throws TPlusException;

	public Map requestDocumentsNameListData(String issuerId, String claimNo)
			throws TPlusException;

	public Map responseDocumentsNameListData(String issuerId, String claimNo)
			throws TPlusException;

	public Map cycleNoListData() throws TPlusException;

	public Map cardPINProcessStatusList() throws TPlusException;

	public Map cardDeliverProcessStatusList() throws TPlusException;

	public Map disputeGroupListData() throws TPlusException;

	public Map disputeGroupListData(String scheme) throws TPlusException;

	public Map disputeGroupReasonListData(String groupId) throws TPlusException;

	public Map responseCodesList() throws TPlusException;

	public Map stateList() throws TPlusException;

	public Map cityList(String state) throws TPlusException;

	public Map townshipList(String city) throws TPlusException;

	public ArrayList tranxTypeList(String tranxGroup) throws TPlusException;

	public Map codeMasterList() throws TPlusException;

	public Map empProfileNameListData() throws TPlusException;
	
	public Map getApplNameList() throws TPlusException;
	
	public Map getCodeMasterListByGroup(String groupName) throws TPlusException;
	
	public Map getScoreNameList() throws TPlusException;
	
	public Map getScreditNameList() throws TPlusException;
	
	public Map getCardProductNameList() throws TPlusException;
	
	public Map getLetterCodeList() throws TPlusException;
	
	public Map getLetterCategoryList(String issuerId) throws TPlusException;
	
	public Map cardProductList(String issuerId) throws TPlusException;

	Map branchList(String issuerId) throws TPlusException;

	boolean create(DelinquencyPolicyDto objDto) throws TPlusException;

	Collection search(SearchInstantCardDto objDto, int pageNo)
			throws TPlusException;

	InstantCardDto getCardBatchDetail(String batchId) throws TPlusException;

	org.transinfo.cacis.dto.useraccess.CodeMasterDto getStatusDesc(String status)
			throws TPlusException;

	boolean update(InstantCardDto objDto) throws TPlusException;

	boolean delete(InstantCardDto objDto) throws TPlusException;

	CardProductDto getCardProductName(String id) throws TPlusException;

	BranchDto getBranchName(String id) throws TPlusException;

	boolean process(InstantCardDto objDto) throws TPlusException;

	boolean authorize(InstantCardDto objDto) throws TPlusException;

	int checkExitsUser(CardBatchForm objForm) throws TPlusException;

	int checkBatchAuthUser(CardBatchForm objForm) throws TPlusException;

	public Map delinquencyPolicyList(String issuerId) throws TPlusException;

	int checkExitsRecord(DelinquencyPolicyDto objPolicy) throws TPlusException;

	boolean create(InstantCardDto objCardBatchDto) throws TPlusException;

	DelinquencyPolicyDto getPolicyDetail(String id) throws TPlusException;

	boolean update(DelinquencyPolicyDto objDto) throws TPlusException;

	int checkExitsRecord(DelinquencyFeeSetupDto objPolicy)
			throws TPlusException;

	boolean create(DelinquencyFeeSetupDto objDto) throws TPlusException;

	boolean checkOverlap(DelinquencyFeeSetupForm objForm, Integer no) throws TPlusException;

	String agingBeginEndRange(DelinquencyFeeSetupForm objForm)
			throws TPlusException;

	DelinquencyFeeSetupDto getFeeDetail(String id) throws TPlusException;

	boolean update(DelinquencyFeeSetupDto objDto) throws TPlusException;

	boolean checkUpdateUser(DelinquencyFeeSetupForm objForm)
			throws TPlusException;

	String getPolicyName(DelinquencyFeeSetupForm objForm) throws TPlusException;

	Collection search(DelinquencyNotificationSetupSearchDto objDto, int pageNo)
			throws TPlusException;

	boolean checkExitsRecord(DelinquencyNotificationSetupForm objForm,
			Integer no) throws TPlusException;

	boolean create(DelinquencyNotificationSetupDto objDto)
			throws TPlusException;

	String getPolicyName(String policyId) throws TPlusException;

	DelinquencyNotificationSetupDto getNotificationDetail(String id)
			throws TPlusException;
	
	boolean update(DelinquencyNotificationSetupDto objDto) throws TPlusException;
}
