package org.transinfo.cacis.dataacess.daoimpl.oracle.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAccountDetailsDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryManagementDAO;
import org.transinfo.cacis.dataacess.dao.inventory.InventoryMasterDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.common.ApplicationMasterDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.dto.inventory.InventoryManagementDto;
import org.transinfo.cacis.dto.inventory.InventoryMasterDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.formbean.collectionmanagement.AgeingAction;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.inventory.OrderListSearchForm;
import org.transinfo.cacis.formbean.inventory.ViewHistorySearchForm;

public class InventoryManagementDAOImpl extends BaseDAOImpl implements InventoryManagementDAO {
	private Logger logger = Logger.getLogger(InventoryManagementDAOImpl.class);

	@Override
	public Map getSupplierList(String issuerId) throws TPlusException {
		Map ageingList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CollectionAgentDto where agentType='CS' and issuerId = '"
							+ issuerId + "' order by agentName");

			List policyList = qry.list();

			for (Iterator it = policyList.iterator(); it.hasNext();) {
				CollectionAgentDto objDto = new CollectionAgentDto();
				objDto = (CollectionAgentDto) it.next();
				ageingList.put(objDto.getAgentId(),
						objDto.getAgentName() + " " + objDto.getAgentId());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getAgeingList in BaseDAOIMpl "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getAgeingList in BaseDAOIMpl "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return ageingList;
	}

	@Override
	public List<String> getCardProductList() throws TPlusException {
		List<String> cardProductList = new ArrayList<String>();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from InventoryMasterDto ");

			List inventoryMasterList = qry.list();

			for (Iterator it = inventoryMasterList.iterator(); it.hasNext();) {
				InventoryMasterDto objDto = new InventoryMasterDto();
				objDto = (InventoryMasterDto) it.next();
				cardProductList.add(objDto.getCardProductId());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getAgeingList in BaseDAOIMpl "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getAgeingList in BaseDAOIMpl "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return cardProductList;
	}

	@Override
	public boolean add(List<String> extraCardProduct, String userId) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Date now = new Date();
			for(int i=0;i<extraCardProduct.size();i++) {
				InventoryMasterDto objDto = new InventoryMasterDto();
				objDto.setCardProductId(extraCardProduct.get(i));
				objDto.setCreatedBy(userId);
				objDto.setCreatedDate(now);
				objDto.setLastMode("");
				objDto.setLastUpdatedBy(userId);
				objDto.setLastUpdatedDate(now);
				objDto.setSupplierId("");
				
				session.save(objDto);
			}
			tx.commit();
			bolExecute = true;
		}
	
		catch (Exception e) {
			System.out
			.println(" Error while add method in CardReplacementDAOIMPL"
					+ e.getMessage());
	
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while add method in CardReplacementDAOIMPL" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
		}

	@Override
	public Collection search(int pageNo) throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select c.cardProductName, dto.replenishPoint, dto.stockAvailable, ");
			sbf.append("dto.supplierId, dto.stockAvailable, dto.cardProductId ");
			sbf.append("From InventoryMasterDto dto, CardProductDto c ");
			sbf.append("where dto.cardProductId = c.cardProductId ");
			sbf.append("ORDER BY dto.cardProductId ");

			objSearchCollection = getSearchList((sbf).toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in CardBatchDAOImpl search method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in EMVProfileDAOImpl search  method :" + ex);
		}

		return objSearchCollection;
	}

	@Override
	public InventoryMasterDto getInventoryMaster(String cardProductId)
			throws TPlusException {
		InventoryMasterDto inventoryMaster = new InventoryMasterDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			inventoryMaster = (InventoryMasterDto) session.get(
					InventoryMasterDto.class, cardProductId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getInventoryMaster method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getInventoryMaster" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return inventoryMaster;
	}

	@Override
	public boolean update(InventoryManagementDto objDto) throws TPlusException {
		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objDto);

			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardBatchDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}

	@Override
	public Map getOrderNoList(String status) throws TPlusException {
		Map orderNoList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from InventoryManagementDto where status='" + status + "' ");

			List inventoryManagementList = qry.list();

			for (Iterator it = inventoryManagementList.iterator(); it.hasNext();) {
				InventoryManagementDto objDto = new InventoryManagementDto();
				objDto = (InventoryManagementDto) it.next();
				orderNoList.put(objDto.getOrderNo(), objDto.getOrderNo());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving getAgeingList in BaseDAOIMpl "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getAgeingList in BaseDAOIMpl "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return orderNoList;
	}

	@Override
	public boolean add(InventoryManagementDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDto);
			tx.commit();
			bolExecute = true;
		}
	
		catch (Exception e) {
			System.out
			.println(" Error while add method in CardReplacementDAOIMPL"
					+ e.getMessage());
	
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while add method in CardReplacementDAOIMPL" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}

	@Override
	public InventoryManagementDto getInventoryManagement(String orderNo)
			throws TPlusException {
		InventoryManagementDto inventoryManagement = new InventoryManagementDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			inventoryManagement = (InventoryManagementDto) session.get(
					InventoryManagementDto.class, orderNo);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getInventoryManagement method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getInventoryManagement" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return inventoryManagement;
	}

	@Override
	public List<CardsDto> getCardsAvailable(String branchId, String batchId) throws TPlusException {
		ArrayList<CardsDto> objSearchCollection = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardsDto dto ");
			sbf.append("where dto.branchId='" + branchId + "' ");
			if(null!=batchId && !batchId.equals("")) {
				sbf.append("and dto.batchId='" + batchId + "' ");
			} else {
				sbf.append("and dto.batchId is null ");
			}
			sbf.append("order by SUBSTR(dto.maskedCardNo, 11, 5)");
			
			Query qry = session.createQuery(sbf.toString());
			objSearchCollection = (ArrayList<CardsDto>) qry.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while retrieving the CardbatchProcessDAOImpl getCardsAvailable Info"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardbatchProcessDAOImpl getCardsAvailable Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	@Override
	public boolean updateCardList(List<CardsDto> cardsListUpdate)
			throws TPlusException {
		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			for(int i=0;i<cardsListUpdate.size();i++) {
				session.update(cardsListUpdate.get(i));
			}
			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardBatchDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}

	@Override
	public boolean validate(InventoryManagementDto objDto)
			throws TPlusException {
		boolean resCord ;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From InventoryManagementDto dto where dto.orderNo='" + objDto.getOrderNo() + "' ");
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue()>0?true:false;
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in EMVProfileDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in EMVProfileDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}

		return resCord;
	}

	@Override
	public Map shipByListData() throws TPlusException {
		Map shipByList = new LinkedHashMap();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CodeMasterDto dto where dto.id.groupId='SHIPBY' ");

			List inventoryManagementList = qry.list();

			for (Iterator it = inventoryManagementList.iterator(); it.hasNext();) {
				CodeMasterDto objDto = new CodeMasterDto();
				objDto = (CodeMasterDto) it.next();
				shipByList.put(objDto.getId().getCodeId(), objDto.getCodeDesc());
			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving shipByListData in BaseDAOIMpl "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving shipByListData in BaseDAOIMpl "
							+ e);

		} finally {

			HibernetFactory.closeSession();
		}

		return shipByList;
	}

	@Override
	public Collection searchOrderList(OrderListSearchForm objForm, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select dto.orderNo, c.cardProductName, dto.orderQty, b.branchName, dto.status, to_char(dto.orderDate,'dd-MM-yyyy'), to_char(dto.dispatchDate,'dd-MM-yyyy') ");
			sbf.append("From InventoryManagementDto dto, CardProductDto c, BranchDto b ");
			sbf.append("where dto.cardproductId = c.cardProductId ");
			sbf.append("and dto.branchId = b.branchId ");
			
			if(null!=objForm.getStatus() && !objForm.getStatus().equals("")) {
				String status = objForm.getStatus();
				if(status.equals("O")) {
					sbf.append("and dto.status = 'O' ");
				} else if(status.equals("A")) {
					sbf.append("and dto.status = 'A' ");
				} else if(status.equals("D")) {
					sbf.append("and dto.status = 'D' ");
				} else {
					sbf.append("and dto.status in ('O','A','D') ");
				}
			}
			
			if(null!=objForm.getBranchId() && !objForm.getBranchId().equals("")) {
				sbf.append("and dto.branchId = '" + objForm.getBranchId() + "' ");
			}
			
			objSearchCollection = getSearchList((sbf).toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in CardBatchDAOImpl search method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in EMVProfileDAOImpl search  method :" + ex);
		}

		return objSearchCollection;
	}

	@Override
	public Collection seachHistory(ViewHistorySearchForm objForm, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("Select dto.orderNo, c.cardProductName, dto.orderQty, b.branchName, dto.status, ");
			sbf.append("to_char(dto.orderDate,'dd-MM-yyyy'), to_char(dto.dispatchDate,'dd-MM-yyyy'), to_char(dto.receivedDate,'dd-MM-yyyy') ");
			sbf.append("From InventoryManagementDto dto, CardProductDto c, BranchDto b ");
			sbf.append("where dto.cardproductId = c.cardProductId ");
			sbf.append("and dto.branchId = b.branchId ");
			
			if(null!=objForm.getStatus() && !objForm.getStatus().equals("")) {
				String status = objForm.getStatus();
				if(status.equals("A")) {
					sbf.append("and dto.status in('R','C','B') ");
				} else {
					sbf.append("and dto.status = '" + status + "' ");
				}
			}
			
			if(null!=objForm.getBranchId() && !objForm.getBranchId().equals("")) {
				sbf.append("and dto.branchId = '" + objForm.getBranchId() + "' ");
			}
			
			objSearchCollection = getSearchList((sbf).toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in CardBatchDAOImpl search method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in EMVProfileDAOImpl search  method :" + ex);
		}

		return objSearchCollection;
	}

}
