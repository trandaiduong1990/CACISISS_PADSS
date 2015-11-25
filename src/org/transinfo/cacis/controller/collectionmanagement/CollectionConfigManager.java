package org.transinfo.cacis.controller.collectionmanagement;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionConfigDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;

public class CollectionConfigManager {
	private Logger logger = Logger.getLogger(CollectionConfigManager.class);

	private CollectionConfigDAO objDAO;

	public CollectionConfigManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCollectionConfigDAO();
	}


	public boolean upload(CollectionConfigDto objDto) throws TPlusException {
		boolean boolCreate = false;

		try {

			boolCreate = objDAO.upload(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager create method" + e);
		}

		return boolCreate;
	}


	public boolean update(CollectionConfigDto objDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager update method" + e);
		}

		return boolUpdate;
	}


	public CollectionConfigDto getCollectionConfig() throws TPlusException {
		CollectionConfigDto objCollectionConfigDto;
		
		try {
			objCollectionConfigDto = objDAO.getCollectionConfig();
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager delete method" + e);
		}
		return objCollectionConfigDto;
	}
}
