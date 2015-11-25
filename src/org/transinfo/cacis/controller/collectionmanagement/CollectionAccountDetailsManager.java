package org.transinfo.cacis.controller.collectionmanagement;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAccountDetailsDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionConfigDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;

public class CollectionAccountDetailsManager {
	private Logger logger = Logger.getLogger(CollectionAccountDetailsManager.class);

	private CollectionAccountDetailsDAO objDAO;

	public CollectionAccountDetailsManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCollectionAccountDetailsDAO();
	}

	public boolean update(CollectionDto objDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager update method" + e);
		}

		return boolUpdate;
	}


	public Collection search(CollectionDto objDto, String customerName, int pageNo) throws TPlusException{
		Collection feeList = null;
		try {
			feeList = objDAO.search(objDto, customerName, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager search method"
							+ ex);
		}
		return feeList;
	}


	public CollectionDto getCollection(String colectId) throws TPlusException{
		CollectionDto objCollectionDto = null;
		try {
			objCollectionDto = objDAO.getCollection(colectId);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAccountDetailsManager getCollection method"
							+ ex);
		}
		return objCollectionDto;
	}

}
