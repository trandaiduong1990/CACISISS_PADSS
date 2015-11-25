package org.transinfo.cacis.controller.collectionmanagement;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgentDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionConfigDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;

public class CollectionAgentManager {
	private Logger logger = Logger.getLogger(CollectionAgentManager.class);

	private CollectionAgentDAO objDAO;

	public CollectionAgentManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCollectionAgentDAO();
	}


	public boolean add(CollectionAgentDto objDto) throws TPlusException {
		boolean boolCreate = false;

		try {

			boolCreate = objDAO.add(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CollectionAgentManager create method" + e);
		}

		return boolCreate;
	}


	public boolean update(CollectionAgentDto objDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CollectionAgentManager update method" + e);
		}

		return boolUpdate;
	}


	public Collection search(CollectionAgentDto objDto, int pageNo) throws TPlusException{
		Collection feeList = null;
		try {
			feeList = objDAO.search(objDto, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAgentManager search method"
							+ ex);
		}
		return feeList;
	}


	public CollectionAgentDto getCollectionAgent(String agentId) throws TPlusException{
		CollectionAgentDto objCollectionAgentDto = null;
		try {
			objCollectionAgentDto = objDAO.getCollectionAgent(agentId);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAgentManager search method"
							+ ex);
		}
		return objCollectionAgentDto;
	}


	public boolean checkExist(CollectionAgentSetupForm objForm) throws TPlusException{
		boolean check = true;
		try {
			check = objDAO.checkExitsRecord(objForm);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CollectionAgentManager checkExist method"
							+ ex);
		}
		return check;
	}
}
