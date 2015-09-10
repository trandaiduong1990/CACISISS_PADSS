package org.transinfo.cacis.dataacess.dao.batchprocess;

import java.util.ArrayList;
import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.dto.applicationforms.SupplementaryFormDto;
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.SupplementaryCardHolderDto;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;
import org.transinfo.cacis.dto.customerservice.CardReplacementDto;
import org.transinfo.cacis.dto.settings.CustomerGroupFeeDto;

@SuppressWarnings("unchecked")
public interface CardBatchProcessADO extends BaseDAO {

	public Collection list(String issuerId, int pageNo) throws TPlusException;

	public Collection listNewProduct(String issuerId, int pageNo) throws TPlusException;

	public int getTotalCardsApps(String issuerId) throws TPlusException;

	public int getTotalCardsAppsNewProduct(String issuerId) throws TPlusException;

	public ArrayList getAllApplication(String issuerId) throws TPlusException;

	public ArrayList getAllApplicationNewProduct(String issuerId) throws TPlusException;

	public boolean embooActivityLimitSet(
			ApplicationProcessDto objApplicationProcessDto)
	throws TPlusException;

	public boolean addBatch(CardBatchDto objBatchDto) throws TPlusException;

	public ApplicationProcessDto getCustomer(String appId)
	throws TPlusException;

	public ApplicationProcessDto getPrimaryCustomer(String appId)
	throws TPlusException;

	public ApplicationProcessDto getSupplCustomer(String appId)
	throws TPlusException;

	public boolean updateApplication(ApplicationFormDto objApplicationFormDto)
	throws TPlusException;

	public boolean batchProcess(ApplicationProcessDto objApplicationProcessDto, ApplicationProcessDto objApplicationProcessDtoSuppl,
			String batchId, String userId, String isUrgent, CustomerGroupFeeDto objCustomerGroupFeeDto) throws TPlusException;

	public boolean batchProcessNewProduct(ApplicationProcessDto objApplicationProcessDto, AddProductDto objAddProductDto, String batchId, 
			String userId, CustomerGroupFeeDto objCustomerGroupFeeDtoo) throws TPlusException;

	public SupplementaryCardHolderDto getSuppCardHolder(String appId,
			String suppNric) throws TPlusException;

	public Collection listReplacementCardApps(String issuerId, int pageNo)
	throws TPlusException;

	public int getTotalReplacementCardsApps(String issuerId)
	throws TPlusException;

	public ArrayList getAllReplacementApplication(String issuerId)
	throws TPlusException;

	public ApplicationProcessDto getCustomerById(String customerID)
	throws TPlusException;

	public boolean replacementBatchProcessForSameCardNo(
			ApplicationProcessDto objApplicationProcessDto,
			CardReplacementDto objCardReplacementDto, String batchId, String userId, CardsDto objCardsDto) throws TPlusException;

	public boolean replacementBatchProcessForNewCardNo(
			ApplicationProcessDto objApplicationProcessDto,
			CardReplacementDto objCardReplacementDto, String batchId,
			CardsDto objCardsDto2, String userId, ApplicationProcessDto objApplicationProcessDtoOriginal) throws TPlusException;

	public Collection listProductChange(String issuerId, int pageNo)
	throws TPlusException;

	public int getTotalProductChangeCardsApps(String issuerId)
	throws TPlusException;

	public ArrayList getAllProductChangeApplication(String issuerId)
	throws TPlusException;

	public ApplicationProcessDto getCustomerByCard(String cardNo)
	throws TPlusException;

	public boolean productChangeBatchProcess(
			ApplicationProcessDto objApplicationProcessDto,
			CardChangeDto objCardChangeDto, String batchId, String userId, ApplicationProcessDto objApplicationProcessDtoOriginal)
	throws TPlusException;

	public Collection listSupplementaryCardApps(String issuerId, int pageNo)
	throws TPlusException;

	public int getTotalSupllementaryCardsApps(String issuerId)
	throws TPlusException;

	public ArrayList getAllSupplementaryApplication(String issuerId)
	throws TPlusException;

	public boolean supplementaryBatchProcess(
			ApplicationProcessDto objApplicationProcessDto,
			SupplementaryFormDto objSupplementaryFormDto, String batchId,
			CardsDto objCardsDto2, String userId, ApplicationProcessDto objApplicationProcessDtoOriginal, CustomerGroupFeeDto objCustomerGroupFeeDto) throws TPlusException;

	public Collection listRenewalCardApps(String issuerId, int pageNo)
	throws TPlusException;

	public int getTotalRenewalCardsApps(String issuerId) throws TPlusException;

	public ArrayList getAllRenewalApplication(String issuerId)
	throws TPlusException;

	public boolean renewalBatchProcess(CardsRenewalDto objCardsRenewalDto, String batchId, CardsDto objCardsDto, ApplicationProcessDto objApplicationProcessDto, String userId)
	throws TPlusException;

	public ArrayList getCustomerList(String appId)
	throws TPlusException;

	public ArrayList getAllSupplCardsList(String accId)
	throws TPlusException;

	public CustomerGroupFeeDto getCustomerGF(String cardProductId, String custType)
	throws TPlusException;
}
