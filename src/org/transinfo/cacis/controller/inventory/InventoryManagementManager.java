package org.transinfo.cacis.controller.inventory;

import java.util.ArrayList;
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
import org.transinfo.cacis.dataacess.dao.inventory.InventoryManagementDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.inventory.InventoryManagementDto;
import org.transinfo.cacis.dto.inventory.InventoryMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;
import org.transinfo.cacis.formbean.inventory.OrderListSearchForm;
import org.transinfo.cacis.formbean.inventory.ViewHistorySearchForm;

public class InventoryManagementManager {
	private Logger logger = Logger.getLogger(InventoryManagementManager.class);

	private InventoryManagementDAO objDAO;

	public InventoryManagementManager() throws Exception {
		objDAO = DAOFactory.getInstance().getInventoryManagementDAO();
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
		InventoryMasterDto objDto;
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

	public boolean update(InventoryManagementDto objDto)  throws TPlusException{
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

	public boolean add(InventoryManagementDto objDto) throws TPlusException{
		boolean addBool;
		try {
			addBool = objDAO.add(objDto);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager update method"
							+ ex);
		}
		return addBool;
	}

	public InventoryManagementDto getInventoryManagement(String orderNo) throws TPlusException{
		InventoryManagementDto objDto = new InventoryManagementDto();
		try {
			objDto = objDAO.getInventoryManagement(orderNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager getInventoryManagement method"
							+ ex);
		}
		return objDto;
	}

	public List<CardsDto> getCardsAvailable(String branchId, String batchId) throws TPlusException{
		List<CardsDto> cardList = new ArrayList<CardsDto>();
		try {
			cardList = objDAO.getCardsAvailable(branchId, batchId);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager getCardsAvailable method"
							+ ex);
		}
		return cardList;
	}

	public boolean updateCardList(List<CardsDto> cardsListUpdate) throws TPlusException{
		boolean updateBool;
		try {
			updateBool = objDAO.updateCardList(cardsListUpdate);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager updateCardList method"
							+ ex);
		}
		return updateBool;
	}

	public boolean validate(InventoryManagementDto objDto) throws TPlusException{
		boolean exist;
		try {
			exist = objDAO.validate(objDto);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager updateCardList method"
							+ ex);
		}
		return exist;
	}

	public Collection searchOrderList(OrderListSearchForm objForm, int pageNo) throws TPlusException{
		Collection orderList = null;
		try {
			orderList = objDAO.searchOrderList(objForm,pageNo);
		} catch (Exception ex) {
			logger.error(ex);
		throw new TPlusException("Error in CardBatchManager searchOrderList method"
					+ ex);
		}	
		return orderList;
	}

	public Collection seachHistory(ViewHistorySearchForm objForm, int pageNo) throws TPlusException{
		Collection historyList = null;
		try {
			historyList = objDAO.seachHistory(objForm,pageNo);
		} catch (Exception ex) {
			logger.error(ex);
		throw new TPlusException("Error in CardBatchManager seachHistory method"
					+ ex);
		}	
		return historyList;
	}

}
