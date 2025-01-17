package org.transinfo.cacis.controller.letters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.letters.LetterTemplateDAO;
import org.transinfo.cacis.dto.letters.LetterCategoryDto;
import org.transinfo.cacis.dto.letters.LetterTemplateDto;
import org.transinfo.cacis.dto.letters.LetterTemplateSearchDto;
import org.transinfo.cacis.dto.settings.EMVProfileDto;
import org.transinfo.cacis.dto.settings.EMVProfileSearchDto;

public class LetterTemplateManager {

	private Logger logger = Logger.getLogger(LetterTemplateManager.class);
	private LetterTemplateDAO objDAO;
	
	public LetterTemplateManager() throws Exception {
		objDAO = DAOFactory.getInstance().getLetterTemplateDAO();
	}

	public LetterTemplateSearchDto search(LetterTemplateSearchDto objDto, int pageNo) throws TPlusException {
		
		LetterTemplateSearchDto emvProfiles = new LetterTemplateSearchDto();
		try {
			emvProfiles = objDAO.search(objDto, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in LetterTemplateManager search method" + ex);
		}
		return emvProfiles;
	}

	public LetterCategoryDto getLetterCategory(String letterCategory) throws TPlusException {
		
		LetterCategoryDto objDto = new LetterCategoryDto();
		try {
			objDto = objDAO.getLetterCategory(letterCategory);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in LetterTemplateManager getLetterCategory method" + ex);
		}
		return objDto;
	}

	public boolean validate(LetterTemplateDto objDto, int mode) throws TPlusException {
		
		boolean recExist = true;
		
		LetterTemplateDto objLetterTemplate = (LetterTemplateDto) objDto;
		
		if (mode == 0 && (objDAO.checkExitsRecord(objLetterTemplate) > 0)) {
			recExist = false;
		}
		if (mode == 1 && (objDAO.checkExitsRecord(objLetterTemplate) == 0)) {
			recExist = false;
		}
		
		return recExist;
	}

	public boolean add(LetterTemplateDto objDto) throws TPlusException {
		
		boolean boolCreate = false;

		try {

			boolCreate = objDAO.create(objDto);
			
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in LetterTemplateManager create method"
					+ e);
		}
		
		return boolCreate;
	}

	public LetterTemplateDto getLetterTemplateDetail(String letterId) throws TPlusException {

		LetterTemplateDto objLetterTemplate = new LetterTemplateDto();
		
		try {
			
			objLetterTemplate = objDAO.getLetterTemplateDetail(letterId);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in LetterTemplateManager getLetterTemplateDetail method" + ex);
		}
		
		return objLetterTemplate;
	}

	public boolean update(LetterTemplateDto objDto) throws TPlusException {
		
		boolean boolUpdate = false;
		
		try {			
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in LetterTemplateManager update method" + e);
		}
		
		return boolUpdate;
	}

	public boolean delete(LetterTemplateDto objDto) throws TPlusException {

		boolean boolDelete = false;
		
		try {			
			boolDelete = objDAO.delete(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in LetterTemplateManager delete method" + e);
		}
		
		return boolDelete;
	}

	public Collection getAllLetterTemplate() throws TPlusException {
		
		Collection letterTemplate = new ArrayList();
		
		try {			
			letterTemplate = objDAO.getAllLetterTemplate();
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in LetterTemplateManager getAllLetterTemplate method" + e);
		}
		
		return letterTemplate;
	}
	
	public Map executeQuery(String sqlQuery) throws TPlusException {
		
		Map listParams = new HashMap();
		
		try {
			listParams = objDAO.executeQuery(sqlQuery);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in LetterDispatchManager execute method" + ex);
		}
		return listParams;
	}
}
