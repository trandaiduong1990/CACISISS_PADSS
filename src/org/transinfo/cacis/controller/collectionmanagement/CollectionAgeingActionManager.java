package org.transinfo.cacis.controller.collectionmanagement;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionConfigDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;

public class CollectionAgeingActionManager {
	private Logger logger = Logger.getLogger(CollectionAgeingActionManager.class);

	private CollectionAgeingActionDAO objDAO;

	public CollectionAgeingActionManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCollectionAgeingActionDAO();
	}

	public boolean add(CollectionAgeingActionDto objDto) throws TPlusException {
		boolean boolCreate = false;

		try {

			boolCreate = objDAO.add(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CollectionAgeingManager add method" + e);
		}

		return boolCreate;
	}


	public boolean update(CollectionAgeingActionDto objDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CollectionAgeingManager update method" + e);
		}

		return boolUpdate;
	}


	public Collection search(CollectionAgeingActionDto objDto, int pageNo) throws TPlusException{
		Collection feeList = null;
		try {
			feeList = objDAO.search(objDto, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAgeingManager search method"
							+ ex);
		}
		return feeList;
	}


	public CollectionAgeingActionDto getCollectionAgeingAction(String sno) throws TPlusException{
		CollectionAgeingActionDto objCollectionAgeingActionDto = null;
		try {
			objCollectionAgeingActionDto = objDAO.getCollectionAgeingAction(sno);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager search method"
							+ ex);
		}
		return objCollectionAgeingActionDto;
	}


	public boolean checkExist(CollectionAgeingSetupForm objForm)  throws TPlusException{
		boolean check = true;
		try {
			check = objDAO.checkExist(objForm);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager checkExist method"
							+ ex);
		}
		return check;
	}


	public boolean checkOverlap(int day, String issuerId) throws TPlusException {
		boolean check = true;
		try {
			check = objDAO.checkOverlap(day, issuerId);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager checkExist method"
							+ ex);
		}
		return check;
	}


	public boolean delete(CollectionAgeingActionDto objDto) throws TPlusException{
		boolean boolDelete = false;

		try {
			boolDelete = objDAO.delete(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CollectionAgeingManager delete method" + e);
		}

		return boolDelete;
	}

}
