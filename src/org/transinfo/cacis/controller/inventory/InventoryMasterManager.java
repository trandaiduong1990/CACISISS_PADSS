package org.transinfo.cacis.controller.inventory;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAccountDetailsDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionConfigDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.inventory.InventoryMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;

public class InventoryMasterManager {
	private Logger logger = Logger.getLogger(InventoryMasterManager.class);

	private InventoryMasterDAO objDAO;

	public InventoryMasterManager() throws Exception {
		objDAO = DAOFactory.getInstance().getInventoryMasterDAO();
	}

	public Collection search(int pageNo) throws TPlusException{
		Collection inventoryMaster = null;
		try {
			inventoryMaster = objDAO.search(pageNo);
		} catch (Exception ex) {
			logger.error(ex);
		throw new TPlusException("Error in CardBatchManager search method"
					+ ex);
		}	
		return inventoryMaster;
	}

	public List<String> getCardProductList() throws TPlusException{
		List<String> cardList = null;
		try {
			cardList = objDAO.getCardProductList();

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager getCardProductList method"
							+ ex);
		}
		return cardList;

	}

	public boolean add(List<String> extraCardProduct, String userId) throws TPlusException{
		boolean addCheck;
		try {
			addCheck = objDAO.add(extraCardProduct, userId);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager add method"
							+ ex);
		}
		return addCheck;
	}

	public InventoryMasterDto getInventoryMaster(String cardProductId) throws TPlusException{
		InventoryMasterDto objDto = new InventoryMasterDto();
		try {
			objDto = objDAO.getInventoryMaster(cardProductId);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager getInventoryMaster method"
							+ ex);
		}
		return objDto;
	}

	public boolean update(InventoryMasterDto objDto)  throws TPlusException{
		boolean updateBool;
		try {
			updateBool = objDAO.update(objDto);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager update method"
							+ ex);
		}
		return updateBool;
	}

	public Collection searchLowLevelQty(int pageNo)  throws TPlusException{
		Collection inventoryMaster = null;
		try {
			inventoryMaster = objDAO.searchLowLevelQty(pageNo);
		} catch (Exception ex) {
			logger.error(ex);
		throw new TPlusException("Error in CardBatchManager searchLowLevelQty method"
					+ ex);
		}	
		return inventoryMaster;
	}

}
