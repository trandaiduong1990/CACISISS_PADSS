package org.transinfo.cacis.controller.collectionmanagement;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionConfigDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;

public class CollectionAgeingManager {
	private Logger logger = Logger.getLogger(CollectionAgeingManager.class);

	private CollectionAgeingDAO objDAO;

	public CollectionAgeingManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCollectionAgeingDAO();
	}


	public boolean add(CollectionAgeingDto objDto) throws TPlusException {
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


	public boolean update(CollectionAgeingDto objDto) throws TPlusException {

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


	public Collection search(CollectionAgeingDto objDto, int pageNo) throws TPlusException{
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


	public CollectionAgeingDto getCollectionAgeing(String ageingPolicy) throws TPlusException{
		CollectionAgeingDto objCollectionAgeingDto = null;
		try {
			objCollectionAgeingDto = objDAO.getCollectionAgeing(ageingPolicy);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager search method"
							+ ex);
		}
		return objCollectionAgeingDto;
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


	public boolean checkOverlap(CollectionAgeingSetupForm objForm) throws TPlusException {
		boolean check = true;
		try {
			check = objDAO.checkOverlap(objForm);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager checkExist method"
							+ ex);
		}
		return check;
	}


	public boolean delete(CollectionAgeingDto objDto) throws TPlusException{
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
