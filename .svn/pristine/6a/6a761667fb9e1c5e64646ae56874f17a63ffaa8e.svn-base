package org.transinfo.cacis.controller.letters;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.controller.cardproduction.ApplValidationManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CreditScoringDAO;
import org.transinfo.cacis.dataacess.dao.letters.LetterApplMapDAO;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;
import org.transinfo.cacis.dto.letters.LetterApplMapDto;
import org.transinfo.cacis.formbean.cardproduction.CreditScoringSetupForm;
import org.transinfo.cacis.formbean.letters.LetterApplMapSetupForm;

public class LetterApplMapManager {

	private Logger logger = Logger.getLogger(LetterApplMapManager.class);
	private LetterApplMapDAO objDAO;
	
	public LetterApplMapManager() throws Exception {
		objDAO = DAOFactory.getInstance().getLetterApplMapDAO();
	}

	public List getAllLetterApplMap() throws TPlusException {
		
		List letterApplMapDtoList = new ArrayList<LetterApplMapDto>();
		List letterApplMapFormList = new ArrayList<LetterApplMapSetupForm>();
		try {
			letterApplMapDtoList = objDAO.getAllLetterApplMap();
			for(Iterator<LetterApplMapDto> i = letterApplMapDtoList.iterator(); i.hasNext();) {
				LetterApplMapDto objDto = i.next();
				LetterApplMapSetupForm objForm = new LetterApplMapSetupForm();
				BeanUtils.copyProperties(objForm, objDto);
				objForm.setApplAction(objDto.getApplActionDesc());
				objForm.setIssuerId(objDto.getId().getIssuerId());
				letterApplMapFormList.add(objForm);
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in LetterApplMapManager getAllLetterApplMap method" + ex);
		}
		
		return letterApplMapFormList;
	}

	public boolean update(List letterApplMapList) throws TPlusException {
		
		List letterApplMapDtoList = new ArrayList<LetterApplMapDto>();
		Boolean boolUpdate = false;
		
		for(Iterator<LetterApplMapSetupForm> i = letterApplMapList.iterator(); i.hasNext();) {
			
			LetterApplMapSetupForm objForm = i.next();
			LetterApplMapDto objDto = new LetterApplMapDto();
			
			try {
				//objDto.id.setIssuerId(objForm.getIssuerId());
				//objDto.id.setApplAction(objForm.getApplAction());
				objDto.setId(new LetterApplMapDto.Id(objForm.getIssuerId(), objForm.getApplAction()));
				objDto.setLetterTemplate(objForm.getLetterTemplate());
				objDto.setStatus(objForm.getStatus());
			} catch (Exception ex) {
				logger.error(ex);
				throw new TPlusException(
						"Error in LetterApplMapManager update method" + ex);
			} 
			letterApplMapDtoList.add(objDto);
		}
		boolUpdate = objDAO.boolUpdate(letterApplMapDtoList);
		
		return boolUpdate;
	}
	
}
