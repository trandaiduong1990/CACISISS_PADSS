package org.transinfo.cacis.controller.batchprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.batchprocess.CardBatchProcessADO;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.dto.applicationforms.SupplementaryFormDto;
import org.transinfo.cacis.dto.batchprocess.CardApplLinkDto;
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.SupplementaryCardHolderDto;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CustomerGroupFeeDto;
import org.transinfo.cacis.model.CardGeneration;

@SuppressWarnings("unchecked")
public class CardBatchProcessManager {

	private CardBatchProcessADO objBatchProcessADO;

	public CardBatchProcessManager() throws Exception {
		objBatchProcessADO = DAOFactory.getInstance().getCardBatchProcessADO();
	}

	public Collection list(String issuerID, int pageNo, BranchDto objBranchDto, String getAll) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objBatchProcessADO.list(issuerID, pageNo, objBranchDto, getAll);

		} catch (Exception e) {
			System.out.println("Error while search operation - list" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager list method" + e);
		}
		return searchLst;

	}

	public Collection listNewProduct(String issuerID, int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objBatchProcessADO.listNewProduct(issuerID, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation - list" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager listNewProduct method" + e);
		}
		return searchLst;

	}

	public int getTotalCardsApps(String issuerID) throws TPlusException {

		int noOfApps = 0;

		try {
			noOfApps = objBatchProcessADO.getTotalCardsApps(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation - getTotalCardsApps" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getTotalCardsApps method" + e);
		}
		return noOfApps;

	}

	public int getTotalCardsAppsNewProduct(String issuerID) throws TPlusException {

		int noOfApps = 0;

		try {
			noOfApps = objBatchProcessADO.getTotalCardsAppsNewProduct(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation - getTotalCardsApps" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getTotalCardsApps method" + e);
		}
		return noOfApps;

	}

	public ArrayList getAllApplication(String issuerID) throws TPlusException {

		ArrayList appList = null;

		try {
			appList = objBatchProcessADO.getAllApplication(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation - getAllApplication" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getAllApplication method"
							+ e);
		}
		return appList;

	}

	public ArrayList getAllApplicationNewProduct(String issuerID) throws TPlusException {

		ArrayList appList = null;

		try {
			appList = objBatchProcessADO.getAllApplicationNewProduct(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation - getAllApplication" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getAllAgetAllApplicationNewProductpplication method"
							+ e);
		}
		return appList;

	}

	public boolean embooActivityLimitSet(
			ApplicationProcessDto objApplicationProcessDto)
			throws TPlusException {

		boolean res = false;

		try {
			res = objBatchProcessADO
					.embooActivityLimitSet(objApplicationProcessDto);

		} catch (Exception e) {
			System.out.println("Error while search operation - embooActivityLimitSet" + e);
		}
		return res;

	}

	public boolean addBatch(CardBatchDto objBatchDto) throws TPlusException {

		boolean boolAdd = false;
		try {

			boolAdd = objBatchProcessADO.addBatch(objBatchDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager addBatch method" + e);
		}
		return boolAdd;
	}

	public ApplicationProcessDto getCustomer(String appId)
			throws TPlusException {

		ApplicationProcessDto objApplicationProcessDto = null;
		try {
			
			objApplicationProcessDto = objBatchProcessADO.getCustomer(appId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getCustomer method" + e);
		}
		return objApplicationProcessDto;
	}

	public CustomerGroupFeeDto getCustomerGF(String cardProductId, String custType)
			throws TPlusException {

		CustomerGroupFeeDto objCustomerGroupFeeDto = null;
		try {

			objCustomerGroupFeeDto = objBatchProcessADO.getCustomerGF(cardProductId, custType);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getCustomerGF method" + e);
		}
		return objCustomerGroupFeeDto;
	}

	public ApplicationProcessDto getPrimaryCustomer(String appId)
			throws TPlusException {

		ApplicationProcessDto objApplicationProcessDto = null;
		try {

			objApplicationProcessDto = objBatchProcessADO
					.getPrimaryCustomer(appId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getPrimaryCustomer method" + e);
		}
		return objApplicationProcessDto;
	}

	public ApplicationProcessDto getSupplCustomer(String appId)
			throws TPlusException {

		ApplicationProcessDto objApplicationProcessDto = null;
		try {

			objApplicationProcessDto = objBatchProcessADO
					.getSupplCustomer(appId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getSupplCustomer method" + e);
		}
		return objApplicationProcessDto;
	}

	public ArrayList getCustomerList(String appId) throws TPlusException {

		ArrayList custList = new ArrayList();
		try {

			custList = objBatchProcessADO.getCustomerList(appId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getCustomerList method" + e);
		}
		return custList;
	}

	public boolean updateApplication(ApplicationFormDto objApplicationFormDto)
			throws TPlusException {

		boolean boolAdd = false;
		try {

			boolAdd = objBatchProcessADO
					.updateApplication(objApplicationFormDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager updateApplication method"
							+ e);
		}
		return boolAdd;
	}

	public boolean batchProcess(ApplicationProcessDto objApplicationProcessDto,
			ApplicationProcessDto objApplicationProcessDtoSuppl,
			ApplicationFormDto objApplicationFormDto, Map indexList,
			String batchId, String userID, String issuerID, int embossNameLength, String isUrgent, CustomerGroupFeeDto objCustomerGroupFeeDto) throws TPlusException {

		boolean boolAdd = false;
		try {
			CardGeneration objCardGen = CardGeneration.getInstance();
			objCardGen.PrimaryCardGeneration(objApplicationProcessDto, objApplicationProcessDtoSuppl, objApplicationFormDto, indexList, issuerID, embossNameLength);

			boolAdd = objBatchProcessADO.batchProcess(objApplicationProcessDto, objApplicationProcessDtoSuppl, batchId, userID, isUrgent, objCustomerGroupFeeDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager batchProcess method"
							+ e);
		}
		return boolAdd;
	}

	public boolean batchProcessNewProduct(ApplicationProcessDto objApplicationProcessDto,
			AddProductDto objAddProductDto, Map indexList,
			String batchId, String userID, String issuerID, int embossNameLength, CustomerGroupFeeDto objCustomerGroupFeeDto) throws TPlusException {

		boolean boolAdd = false;
		
		try {
			
			CardGeneration objCardGen = CardGeneration.getInstance();
			objCardGen.NewProductCardGeneration(objApplicationProcessDto, objAddProductDto, indexList, issuerID, embossNameLength);

			boolAdd = objBatchProcessADO.batchProcessNewProduct(objApplicationProcessDto, objAddProductDto, batchId, userID, objCustomerGroupFeeDto);

		} catch (Exception e) {
			throw new TPlusException("Error in CardBatchProcessManager batchProcess method" + e);
		}
		return boolAdd;
	}

	public SupplementaryCardHolderDto getSuppCardHolder(String appId,
			String suppNric) throws TPlusException {

		SupplementaryCardHolderDto objSupplementaryCardHolderDto = null;
		try {

			objSupplementaryCardHolderDto = objBatchProcessADO
					.getSuppCardHolder(appId, suppNric);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getSuppCardHolder method"
							+ e);
		}
		return objSupplementaryCardHolderDto;
	}

	public Collection listReplacementCardApps(String issuerID, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objBatchProcessADO.listReplacementCardApps(issuerID,
					pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager listReplacementCardApps method" + e);
		}
		return searchLst;

	}

	public int getTotalReplacementCardsApps(String issuerID)
			throws TPlusException {

		int noOfApps = 0;

		try {
			noOfApps = objBatchProcessADO
					.getTotalReplacementCardsApps(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation -getTotalReplacementCardsApps" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getTotalReplacementCardsApps method" + e);
		}
		return noOfApps;

	}

	public ArrayList getAllReplacementApplication(String issuerID)
			throws TPlusException {

		ArrayList appList = null;

		try {
			appList = objBatchProcessADO.getAllReplacementApplication(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation- getAllReplacementApplication" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getAllReplacementApplication method"
							+ e);
		}
		return appList;

	}

	public ApplicationProcessDto getCustomerById(String customerID)
			throws TPlusException {

		ApplicationProcessDto objApplicationProcessDto = null;
		try {
			System.out.println("+++++Manager+++====="+ customerID);
			objApplicationProcessDto = objBatchProcessADO
					.getCustomerById(customerID);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getCustomerById method" + e);
		}
		return objApplicationProcessDto;
	}

	public boolean replacementBatchProcessForSameCardNo(
			ApplicationProcessDto objApplicationProcessDto,
			CardsDto objCardsDto, Map indexList,
			CardProductDto objCardProductDto,
			CardReplacementDto objCardReplacementDto, String batchId,
			String userId, String expExtendInterval, String oldExp)
			throws TPlusException {

		boolean boolAdd = false;
		try {
			String cardNo = String.valueOf(objCardReplacementDto
					.getCardNumber());

			CardGeneration objCardGen = CardGeneration.getInstance();
			objCardGen.RenewalCardGeneration(objApplicationProcessDto,
					objCardsDto, indexList, objCardProductDto, cardNo,
					expExtendInterval, oldExp);

			boolAdd = objBatchProcessADO.replacementBatchProcessForSameCardNo(
					objApplicationProcessDto, objCardReplacementDto, batchId,
					userId, objCardsDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager replacementBatchProcessForSameCardNo method"
							+ e);
		}
		return boolAdd;
	}

	public boolean replacementBatchProcessForNewCardNo(
			ApplicationProcessDto objApplicationProcessDto,
			CardReplacementDto objCardReplacementDto, Map indexList,
			String batchId, int cardHolderType, CardsDto objCardsDto,
			String userId, String expExtendInterval, String oldExp,
			ApplicationProcessDto objApplicationProcessDtoOriginal, 
			String issuerId, CardProductDto objCardProductDto)
			throws TPlusException {

		boolean boolAdd = false;
		try {
			CardGeneration objCardGen = CardGeneration.getInstance();
			objCardGen.ReplacementCardGeneration(objApplicationProcessDto, indexList, cardHolderType, expExtendInterval, oldExp, objApplicationProcessDtoOriginal, objCardsDto.getCardProductId(), issuerId, objCardProductDto, objCardsDto);

			boolAdd = objBatchProcessADO.replacementBatchProcessForNewCardNo(objApplicationProcessDto, objCardReplacementDto, batchId, objCardsDto, userId, objApplicationProcessDtoOriginal);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager replacementBatchProcessForNewCardNo method"
							+ e);
		}
		return boolAdd;
	}

	public Collection listProductChange(String issuerID, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objBatchProcessADO.listProductChange(issuerID, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager listProductChange method" + e);
		}
		return searchLst;

	}

	public int getTotalProductChangeCardsApps(String issuerID)
			throws TPlusException {

		int noOfApps = 0;

		try {
			noOfApps = objBatchProcessADO
					.getTotalProductChangeCardsApps(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation - getTotalProductChangeCardsApps" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getTotalProductChangeCardsApps method" + e);
		}
		return noOfApps;

	}

	public ArrayList getAllProductChangeApplication(String issuerID)
			throws TPlusException {

		ArrayList appList = null;

		try {
			appList = objBatchProcessADO
					.getAllProductChangeApplication(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation - getAllProductChangeApplication" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getAllProductChangeApplication method"
							+ e);
		}
		return appList;

	}

	public ApplicationProcessDto getCustomerByCard(String cardNo)
			throws TPlusException {

		ApplicationProcessDto objApplicationProcessDto = null;
		try {

			objApplicationProcessDto = objBatchProcessADO
					.getCustomerByCard(cardNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getCustomerByCard method" + e);
		}
		return objApplicationProcessDto;
	}

	public boolean productChangeBatchProcess(
			ApplicationProcessDto objApplicationProcessDto,
			CardChangeDto objCardChangeDto, Map indexList, String batchId,
			String userId, String expExtendInterval, String oldExp, 
			ApplicationProcessDto objApplicationProcessDtoOriginal, 
			CardsDto objCardsDto, String issuerId, int embossNameLength) throws TPlusException {

		boolean boolAdd = false;
		try {
			
			// get the supplementary list
			ArrayList suppList = getAllSupplCardsList(objCardsDto.getAccountId());
			
			CardGeneration objCardGen = CardGeneration.getInstance();
			objCardGen.ProductChangeCardGeneration(objApplicationProcessDto, indexList, objCardChangeDto, expExtendInterval, oldExp, objCardsDto, suppList, objApplicationProcessDtoOriginal, issuerId, embossNameLength);

			boolAdd = objBatchProcessADO.productChangeBatchProcess(objApplicationProcessDto, objCardChangeDto, batchId, userId, objApplicationProcessDtoOriginal);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager productChangeBatchProcess method"
							+ e);
		}
		return boolAdd;
	}

	public Collection listSupplementaryCardApps(String issuerID, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objBatchProcessADO.listSupplementaryCardApps(issuerID,
					pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation -listSupplementaryCardApps" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager listSupplementaryCardApps method" + e);
		}
		return searchLst;

	}

	public int getTotalSupllementaryCardsApps(String issuerID)
			throws TPlusException {

		int noOfApps = 0;

		try {
			noOfApps = objBatchProcessADO
					.getTotalSupllementaryCardsApps(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation - getTotalSupllementaryCardsApps" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getTotalSupllementaryCardsApps method" + e);
		}
		return noOfApps;

	}

	public ArrayList getAllSupplementaryApplication(String issuerID)
			throws TPlusException {

		ArrayList appList = null;

		try {
			appList = objBatchProcessADO
					.getAllSupplementaryApplication(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation -getAllSupplementaryApplication" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getAllSupplementaryApplication method"
							+ e);
		}
		return appList;

	}

	public boolean supplementaryBatchProcess(
			ApplicationProcessDto objApplicationProcessDto,
			SupplementaryFormDto objSupplementaryFormDto, Map indexList,
			String batchId, int cardHolderType, CardsDto objCardsDto,
			String userId, ApplicationProcessDto objApplicationProcessDtoOriginal, 
			String cardProduct, String issuerID, int embossNameLength, CustomerGroupFeeDto objCustomerGroupFeeDto)
			throws TPlusException {

		boolean boolAdd = false;
		try {
			CardGeneration objCardGen = CardGeneration.getInstance();
			objCardGen.SupplementaryCardGeneration(objApplicationProcessDto, indexList, cardHolderType, objApplicationProcessDtoOriginal, cardProduct, issuerID, embossNameLength);

			boolAdd = objBatchProcessADO.supplementaryBatchProcess(objApplicationProcessDto, objSupplementaryFormDto, batchId,objCardsDto, userId, objApplicationProcessDtoOriginal, objCustomerGroupFeeDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager supplementaryBatchProcess method"
							+ e);
		}
		return boolAdd;
	}

	public Collection listRenewalCardApps(String issuerID, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objBatchProcessADO
					.listRenewalCardApps(issuerID, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation - listRenewalCardApps" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager listRenewalCardApps method" + e);
		}
		return searchLst;

	}

	public int getTotalRenewalCardsApps(String issuerID) throws TPlusException {

		int noOfApps = 0;

		try {
			noOfApps = objBatchProcessADO.getTotalRenewalCardsApps(issuerID);

		} catch (Exception e) {
			System.out.println("Error while getTotalRenewalCardsApps operation"
					+ e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getTotalRenewalCardsApps method"
							+ e);
		}
		return noOfApps;

	}

	public ArrayList getAllRenewalApplication(String issuerID)
			throws TPlusException {

		ArrayList appList = null;

		try {
			appList = objBatchProcessADO.getAllRenewalApplication(issuerID);

		} catch (Exception e) {
			System.out.println("Error while search operation - getAllRenewalApplication" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager getAllRenewalApplication method"
							+ e);
		}
		return appList;

	}

	public boolean renewalBatchProcess(
			ApplicationProcessDto objApplicationProcessDto,
			CardsDto objCardsDto, Map indexList,
			CardProductDto objCardProductDto,
			CardsRenewalDto objCardsRenewalDto, String batchId, String userId,
			String expExtendInterval, String oldExp) throws TPlusException {

		boolean boolAdd = false;
		try {
			String cardNo = String.valueOf(objCardsRenewalDto.getCardNumber());

			CardGeneration objCardGen = CardGeneration.getInstance();
			objCardGen.RenewalCardGeneration(objApplicationProcessDto, objCardsDto, indexList, objCardProductDto, cardNo, expExtendInterval, oldExp);

			boolAdd = objBatchProcessADO.renewalBatchProcess(objCardsRenewalDto, batchId, objCardsDto, objApplicationProcessDto, userId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager renewalBatchProcess method"
							+ e);
		}
		return boolAdd;
	}

	public ArrayList getAllSupplCardsList(String accId) throws TPlusException {

		ArrayList custList = new ArrayList();
		try {

			custList = objBatchProcessADO.getAllSupplCardsList(accId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getAllSupplCardsList method"
							+ e);
		}
		return custList;
	}

	public ApplicationFormDto getApplicationForm(String applicationId) throws TPlusException {
		ApplicationFormDto objDto = new ApplicationFormDto();
		try {

			objDto = objBatchProcessADO.getApplicationForm(applicationId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getApplicationForm method"
							+ e);
		}
		return objDto;
	}

	public boolean addCardApplLink(CardApplLinkDto objCardApplLinkDto) throws TPlusException {
		boolean boolAdd = false;
		try {

			boolAdd = objBatchProcessADO.addCardApplLink(objCardApplLinkDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager addCardApplLink method" + e);
		}
		return boolAdd;
	}

	public ArrayList<CardBatchDto> getCardBatch() throws TPlusException {
		ArrayList<CardBatchDto> list;
		try {
			list = objBatchProcessADO.getCardBatch();
		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getCardBatch method" + e);
		}
		return list;
	}

	public ArrayList<ApplicationFormDto> getApplicationFormByBatchId(String batchId) throws TPlusException {
				ArrayList<ApplicationFormDto> list;
				try {

					list = objBatchProcessADO.getApplicationFormByBatchId(batchId);

				} catch (Exception e) {
					throw new TPlusException(
							"Error in CardBatchProcessManager getApplicationFormByBatchId method" + e);
				}
				return list;
	}

	public CardBatchDto getCardBatchDto(String batchId) throws TPlusException {
		CardBatchDto objDto = new CardBatchDto();
		try {

			objDto = objBatchProcessADO.getCardBatchDto(batchId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getCardBatchDto method"
							+ e);
		}
		return objDto;
	}

	public String getUserType(String userId) throws TPlusException {
		String userType;
		try {

			userType = objBatchProcessADO.getUserType(userId);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getUserType method"
							+ e);
		}
		return userType;
	}

	public String getApplicationType(int applicationType) throws TPlusException {
		String applType;
		try {

			applType = objBatchProcessADO.getApplicationType(applicationType);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getApplicationType method"
							+ e);
		}
		return applType;
	}

	public Map getBranch(BranchDto objBranchDto, String getAll) 
			throws TPlusException {
		Map branchList;
		try {

			branchList = objBatchProcessADO.getBranch(objBranchDto, getAll);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in CardBatchProcessManager getBranch method"
							+ e);
		}
		return branchList;
	}

	public Collection list(String issuerId, int pageNo) throws TPlusException {
		Collection searchLst = null;

		try {
			searchLst = objBatchProcessADO.list(issuerId, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation - list" + e);
			throw new TPlusException(
					"Error in CardBatchProcessManager list method" + e);
		}
		return searchLst;
	}

}
