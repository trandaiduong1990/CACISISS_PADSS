package org.transinfo.cacis.dataacess.dao.inventory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.inventory.InventoryManagementDto;
import org.transinfo.cacis.dto.inventory.InventoryMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.inventory.OrderListSearchForm;
import org.transinfo.cacis.formbean.inventory.ViewHistorySearchForm;

public interface InventoryManagementDAO extends BaseDAO{

	Map getSupplierList(String issuerId)  throws TPlusException;

	List<String> getCardProductList()throws TPlusException;

	boolean add(List<String> extraCardProduct, String userId)throws TPlusException;

	Collection search(int pageNo) throws TPlusException;

	InventoryMasterDto getInventoryMaster(String cardProductId)throws TPlusException;

	boolean update(InventoryManagementDto objDto)throws TPlusException;

	Map getOrderNoList(String status)throws TPlusException;

	boolean add(InventoryManagementDto objDto) throws TPlusException;

	InventoryManagementDto getInventoryManagement(String orderNo)throws TPlusException;

	List<CardsDto> getCardsAvailable(String branchId, String batchId) throws TPlusException;

	boolean updateCardList(List<CardsDto> cardsListUpdate) throws TPlusException;

	boolean validate(InventoryManagementDto objDto)throws TPlusException;

	Map shipByListData()throws TPlusException;

	Collection searchOrderList(OrderListSearchForm objForm, int pageNo)throws TPlusException;

	Collection seachHistory(ViewHistorySearchForm objForm, int pageNo)throws TPlusException;

}
