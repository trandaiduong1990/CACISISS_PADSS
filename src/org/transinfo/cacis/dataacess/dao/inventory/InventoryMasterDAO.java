package org.transinfo.cacis.dataacess.dao.inventory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.inventory.InventoryMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;

public interface InventoryMasterDAO extends BaseDAO{

	Map getSupplierList(String issuerId)  throws TPlusException;

	List<String> getCardProductList()throws TPlusException;

	boolean add(List<String> extraCardProduct, String userId)throws TPlusException;

	Collection search(int pageNo) throws TPlusException;

	InventoryMasterDto getInventoryMaster(String cardProductId)throws TPlusException;

	boolean update(InventoryMasterDto objDto)throws TPlusException;

	Collection searchLowLevelQty(int pageNo)throws TPlusException;

}
