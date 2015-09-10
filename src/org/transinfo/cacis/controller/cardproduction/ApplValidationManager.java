package org.transinfo.cacis.controller.cardproduction;

import java.util.Collection;

import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplValidationDAO;
import org.transinfo.cacis.dto.cardproduction.ApplValidationDto;
import org.transinfo.cacis.dto.cardproduction.ApplValidationSearchDto;

@SuppressWarnings("unchecked")
public class ApplValidationManager {
	
	private Logger logger = Logger.getLogger(ApplValidationManager.class);
	private ApplValidationDAO objDAO;
	
	public ApplValidationManager() throws Exception {
		objDAO = DAOFactory.getInstance().getApplValidationDAO();
	}
	
	public boolean add(ApplValidationDto objDto) throws TPlusException {
		
		boolean boolCreate = false;

		try {

			boolCreate = objDAO.create(objDto);
			
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in ApplValidationManager create method"
					+ e);
		}
		
		return boolCreate;
	}

	public boolean validate(ApplValidationDto objDto, int mode) throws TPlusException {

		boolean recExist = true;
		
		ApplValidationDto objApplValidationDto = (ApplValidationDto) objDto;
		
		if (mode == 0 && (objDAO.checkExitsRecord(objApplValidationDto) > 0)) {
			recExist = false;
		}
		if (mode == 1 && (objDAO.checkExitsRecord(objApplValidationDto) == 0)) {
			recExist = false;
		}
		
		return recExist;
	}

	public Collection search(ApplValidationSearchDto objDto, int pageNo) throws TPlusException {

		Collection applValidations = null;
		try {
			applValidations = objDAO.search(objDto, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in ApplValidationManager search method" + ex);
		}
		
		return applValidations;
	}

	public ApplValidationDto getApplValidationDetail(String applValId) throws TPlusException {

		ApplValidationDto objApplValidation = new ApplValidationDto();
		
		try {
			
			objApplValidation = objDAO.getApplValidationDetail(applValId);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in ApplValidationManager getApplValidationDetail method" + ex);
		}
		
		return objApplValidation;
	}
	
	public boolean update(ApplValidationDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		
		try {			
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in ApplValidationManager update method" + e);
		}
		
		return boolUpdate;
	}

	public boolean delete(ApplValidationDto objDto) throws TPlusException {

		boolean boolDelete = false;
		
		try {			
			boolDelete = objDAO.delete(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in ApplValidationManager delete method" + e);
		}
		
		return boolDelete;
	}
	
}
