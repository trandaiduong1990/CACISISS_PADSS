package org.transinfo.cacis.controller.cardproduction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CreditScoringDAO;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;
import org.transinfo.cacis.formbean.cardproduction.CreditScoringSetupForm;

public class CreditScoringManager {
 
	private Logger logger = Logger.getLogger(ApplValidationManager.class);
	private CreditScoringDAO objDAO;
	
	public CreditScoringManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCreditScoringDAO();
	}
	
//	public Collection search(CreditScoringSearchDto objDto, int pageNo) throws TPlusException {
//
//		Collection scoreNames = null;
//		try {
//			scoreNames = objDAO.search(objDto, pageNo);
//
//		} catch (Exception ex) {
//			logger.error(ex);
//			throw new TPlusException(
//					"Error in CreditScoringManager search method" + ex);
//		}
//		
//		return scoreNames;
//	}
	
	public List getAllCreditScoring() throws TPlusException {

		List scoreNames = new ArrayList<CreditScoringDto>();
		List scoreNamesForm = new ArrayList<CreditScoringSetupForm>();
		try {
			scoreNames = objDAO.getAllCreditScoring();
			for(Iterator<CreditScoringDto> i = scoreNames.iterator(); i.hasNext();) {
				CreditScoringDto objDto = i.next();
				CreditScoringSetupForm objForm = new CreditScoringSetupForm();
				BeanUtils.copyProperties(objForm, objDto);
				scoreNamesForm.add(objForm);
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CreditScoringManager getAllCreditScoring method" + ex);
		}
		
		return scoreNamesForm;
	}

	public CreditScoringDto getCreditScoringDetail(String scoreId) throws TPlusException {
		
		CreditScoringDto objCreditScoring = new CreditScoringDto();
		
		try {
			
			objCreditScoring = objDAO.getCreditScoringDetail(scoreId);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CreditScoringManager getCreditScoringDetail method" + ex);
		}
		
		return objCreditScoring;
	}

	public boolean validate(CreditScoringDto objDto, int mode) throws TPlusException {

		boolean recExist = true;
		
		CreditScoringDto objCreditScoringDto = (CreditScoringDto) objDto;
		
		if (mode == 0 && (objDAO.checkExitsRecord(objCreditScoringDto) > 0)) {
			recExist = false;
		}
		if (mode == 1 && (objDAO.checkExitsRecord(objCreditScoringDto) == 0)) {
			recExist = false;
		}
		
		return recExist;
	}

	public boolean update(CreditScoringDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		
		try {			
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in CreditScoringManager update method" + e);
		}
		
		return boolUpdate;
	}
}
