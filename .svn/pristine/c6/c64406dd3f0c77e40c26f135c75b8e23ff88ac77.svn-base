package org.transinfo.cacis.controller.collectionmanagement;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyNotificationSetupDAO;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyNotificationSetupSearchDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyNotificationSetupForm;

public class DelinquencyNotificationSetupManager {
	private Logger logger = Logger
			.getLogger(DelinquencyNotificationSetupManager.class);

	private DelinquencyNotificationSetupDAO objDAO;

	public DelinquencyNotificationSetupManager() throws Exception {
		objDAO = DAOFactory.getInstance().getDelinquencyNotificationSetupDAO();
	}

	public Collection search(DelinquencyNotificationSetupSearchDto objDto,
			int pageNo) throws TPlusException {

		Collection feeList = null;
		try {
			feeList = objDAO.search(objDto, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager search method"
							+ ex);
		}
		return feeList;
	}
	
	public boolean checkExist(DelinquencyNotificationSetupForm objForm,
			int pageNo) throws TPlusException {

		boolean check = true;
		try {
			check = objDAO.checkExitsRecord(objForm, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager checkExist method"
							+ ex);
		}
		return check;
	}
	
	public boolean add(DelinquencyNotificationSetupDto objDto) throws TPlusException {

		boolean check = true;
		try {
			check = objDAO.create(objDto);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager add method"
							+ ex);
		}
		return check;
	}
	
	public String getPolicyName(String policyId)
			throws TPlusException {
		String name = "";
		try {

			name = objDAO.getPolicyName(policyId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager create method" + e);
		}
		return name;
	}
	
	public DelinquencyNotificationSetupDto getNotificationDetail(String id) throws TPlusException {

		DelinquencyNotificationSetupDto objDto = new DelinquencyNotificationSetupDto();

		try {

			objDto = objDAO.getNotificationDetail(id);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager getNotificationDetail method"
							+ ex);
		}

		return objDto;
	}
	
	public boolean update(DelinquencyNotificationSetupDto objDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager update method" + e);
		}

		return boolUpdate;
	}

	public boolean delete(DelinquencyNotificationSetupDto objDto) throws TPlusException {

		boolean boolDelete = false;

		try {

			boolDelete = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyNotificationSetupManager delete method" + e);
		}

		return boolDelete;
	}
}
