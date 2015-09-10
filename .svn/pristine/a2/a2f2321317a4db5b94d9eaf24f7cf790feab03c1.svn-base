package org.transinfo.cacis.controller.settings;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.EMVProfileDAO;
import org.transinfo.cacis.dto.settings.EMVProfileDto;
import org.transinfo.cacis.dto.settings.EMVProfileSearchDto;

/**
 * EMVProfileManager
 * 
 * @author hoang-vu
 * 
 */
public class EMVProfileManager {
	
	private Logger logger = Logger.getLogger(CardProductManager.class);
	
	private EMVProfileDAO objDAO;
	
	public EMVProfileManager() throws Exception {
		objDAO = DAOFactory.getInstance().getEMVProfileDAO();
	}
	
	public boolean add(EMVProfileDto objDto) throws TPlusException {
		
		boolean boolCreate = false;

		try {

			boolCreate = objDAO.create(objDto);
			
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in EMVProfileManager create method"
					+ e);
		}
		
		return boolCreate;
	}

	public boolean validate(Object objDto, int mode) throws TPlusException {
		
		boolean recExist = true;
		
		EMVProfileDto objEmvProfile = (EMVProfileDto) objDto;
		
		if (mode == 0 && (objDAO.checkExitsRecord(objEmvProfile) > 0)) {
			recExist = false;
		}
		if (mode == 1 && (objDAO.checkExitsRecord(objEmvProfile) == 0)) {
			recExist = false;
		}
		
		return recExist;
	}

	public Collection search(EMVProfileSearchDto objDto, int pageNo) throws TPlusException {
		
		Collection emvProfiles = null;
		try {
			emvProfiles = objDAO.search(objDto, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in EMVProfileManager search method" + ex);
		}
		return emvProfiles;
	}

	public EMVProfileDto getEMVProfileDetail(String emvProfileName) throws TPlusException {


		EMVProfileDto objEMVProfile = new EMVProfileDto();
		
		try {
			
			objEMVProfile = objDAO.getEMVProfileDto(emvProfileName);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in EMVProfileManager getEMVProfileDetail method" + ex);
		}
		
		return objEMVProfile;
	}

	public boolean update(EMVProfileDto objDto) throws TPlusException {
		
		boolean boolUpdate = false;
	
		try {			
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in EMVProfileManager update method" + e);
		}
		
		return boolUpdate;
	}

	public boolean delete(EMVProfileDto objDto) throws TPlusException {
		
		boolean boolDelete = false;
		
		try {			
			boolDelete = objDAO.delete(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in EMVProfileManager delete method" + e);
		}
		
		return boolDelete;
	}

	public boolean checkEmvApplTypeCryto(EMVProfileDto objDto, int mode) throws TPlusException {

		boolean recordExist = true;
		
		try {	
			if (mode == 0 && (objDAO.checkEmvApplTypeCryto(objDto) > 0)) {
				recordExist = false;
			}
			EMVProfileDto objDtoDb = objDAO.getEMVProfileDto(objDto.getEmvProfileName());
			if (mode == 1 && (objDAO.checkEmvApplTypeCryto(objDto) == 0 || (objDto.getApplType().equals(objDtoDb.getApplType()) ) 
					&& objDto.getApplCryptogram().equals(objDtoDb.getApplCryptogram()))) {
				recordExist = false;
			}
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in EMVProfileManager checkEmvApplTypeCryto method" + e);
		}
		
		return recordExist;
	}

	public Map cryptogramList(String applType) throws TPlusException {

		Map crytogramList = null;
		
		try {
			crytogramList = objDAO.getCryptogramList(applType);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in EMVProfileManager cryptogramList method" + e);
		}
		
		return crytogramList;
	}
}
