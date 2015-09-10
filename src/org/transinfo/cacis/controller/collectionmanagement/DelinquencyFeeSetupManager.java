package org.transinfo.cacis.controller.collectionmanagement;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.formbean.collectionmanagement.DelinquencyFeeSetupForm;

public class DelinquencyFeeSetupManager {
	private Logger logger = Logger.getLogger(DelinquencyFeeSetupManager.class);

	private DelinquencyFeeSetupDAO objDAO;

	public DelinquencyFeeSetupManager() throws Exception {
		objDAO = DAOFactory.getInstance().getDelinquencyFeeSetupDAO();
	}

	public Collection search(DelinquencyFeeSetupSearchDto objDto, int pageNo)
			throws TPlusException {

		Collection feeList = null;
		try {
			feeList = objDAO.search(objDto, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager search method" + ex);
		}
		return feeList;
	}

	public boolean validate(DelinquencyFeeSetupDto objDto)
			throws TPlusException {
		DelinquencyFeeSetupDto objFee = (DelinquencyFeeSetupDto) objDto;
		boolean check = false;

		try {
			check = objDAO.checkExitsRecord(objFee) == 0;
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager validate method" + e);
		}

		return check;
	}

	public boolean add(DelinquencyFeeSetupDto objDto) throws TPlusException {
		boolean boolCreate = false;

		try {

			boolCreate = objDAO.create(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager create method" + e);
		}

		return boolCreate;
	}

	public boolean checkOverlap(DelinquencyFeeSetupForm objForm, Integer no)
			throws TPlusException {
		boolean check = false;
		try {

			check = objDAO.checkUpdateUser(objForm) ? true : objDAO
					.checkOverlap(objForm, no);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager create method" + e);
		}
		return check;
	}

	public String getPolicyName(DelinquencyFeeSetupForm objForm)
			throws TPlusException {
		String name = "";
		try {

			name = objDAO.getPolicyName(objForm);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager create method" + e);
		}
		return name;
	}

	public String agingBeginEndRange(DelinquencyFeeSetupForm objForm)
			throws TPlusException {
		String check;
		try {

			check = objDAO.agingBeginEndRange(objForm);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager create method" + e);
		}
		return check;
	}

	public DelinquencyFeeSetupDto getFeeDetail(String id) throws TPlusException {

		DelinquencyFeeSetupDto objDto = new DelinquencyFeeSetupDto();

		try {

			objDto = objDAO.getFeeDetail(id);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager getFeeDetail method"
							+ ex);
		}

		return objDto;
	}

	public boolean update(DelinquencyFeeSetupDto objDto) throws TPlusException {

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

	public boolean delete(DelinquencyFeeSetupDto objDto) throws TPlusException {

		boolean boolDelete = false;

		try {

			boolDelete = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyFeeSetupManager delete method" + e);
		}

		return boolDelete;
	}
}
