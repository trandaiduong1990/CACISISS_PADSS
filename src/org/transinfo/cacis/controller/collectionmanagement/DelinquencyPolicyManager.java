package org.transinfo.cacis.controller.collectionmanagement;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyFeeSetupDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyPolicyDAO;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyFeeSetupSearchDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicySearchDto;

public class DelinquencyPolicyManager {
	private Logger logger = Logger.getLogger(DelinquencyPolicyManager.class);

	private DelinquencyPolicyDAO objDAO;

	public DelinquencyPolicyManager() throws Exception {
		objDAO = DAOFactory.getInstance().getDelinquencyPolicyDAO();
	}

	public Collection search(DelinquencyPolicySearchDto objDto, int pageNo)
			throws TPlusException {

		Collection policyList = null;
		try {
			policyList = objDAO.search(objDto, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyPolicyManager search method" + ex);
		}
		return policyList;
	}

	public boolean validate(DelinquencyPolicyDto objDto) throws TPlusException {
		DelinquencyPolicyDto objPolicy = (DelinquencyPolicyDto) objDto;
		boolean check = false;

		try {

			check = objDAO.checkExitsRecord(objPolicy) == 0;

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyPolicyManager validate method" + e);
		}

		return check;
	}

	public boolean add(DelinquencyPolicyDto objDto) throws TPlusException {
		boolean boolCreate = false;

		try {

			boolCreate = objDAO.create(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in DelinquencyPolicyManager create method" + e);
		}

		return boolCreate;
	}

	public DelinquencyPolicyDto getPolicyDetail(String id)
			throws TPlusException {

		DelinquencyPolicyDto objDto = new DelinquencyPolicyDto();

		try {

			objDto = objDAO.getPolicyDetail(id);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in DelinquencyPolicyManager getPolicyDetail method" + ex);
		}

		return objDto;
	}
	
	public boolean update(DelinquencyPolicyDto objDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in DelinquencyPolicyManager update method"
					+ e);
		}

		return boolUpdate;
	}
	
	public boolean delete(DelinquencyPolicyDto objDto) throws TPlusException {

		boolean boolDelete = false;

		try {
			objDto.setStatus("D");
			boolDelete = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in DelinquencyPolicyManager delete method"
					+ e);
		}

		return boolDelete;
	}
}
