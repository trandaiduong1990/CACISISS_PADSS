package org.transinfo.cacis.controller.settings;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CreditScoringDAO;
import org.transinfo.cacis.dataacess.dao.settings.CreditLimitProfileDAO;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CreditLimitProfileDto;
import org.transinfo.cacis.dto.settings.CreditLimitProfileSearchDto;
import org.transinfo.cacis.dto.settings.EMVProfileDto;

public class CreditLimitProfileManager {

	private Logger logger = Logger.getLogger(CardProductManager.class);
	private CreditLimitProfileDAO objDAO;
	private CreditScoringDAO objCreditScoringDAO;
	
	public CreditLimitProfileManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCreditLimitProfileDAO();
		objCreditScoringDAO = DAOFactory.getInstance().getCreditScoringDAO();
	}

//	public String getTotalScoringPoint() throws TPlusException {
//		
//		String result;
//
//		try {
//
//			result = objDAO.getTotalScoringPoint();
//			
//		} catch (Exception e) {
//			logger.error(e);
//			throw new TPlusException("Error in CreditLimitProfileManager getTotalScoringPoint method"
//					+ e);
//		}
//		
//		return result;
//	}
	
	public CardProductDto getLowerUpperLimit(String cardProductId) throws TPlusException {
		
		CardProductDto objDto = null;

		try {

			objDto = objDAO.getLowerUpperLimit(cardProductId);
			
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CreditLimitProfileManager getUpperLimit method"
					+ e);
		}
		
		return objDto;
	}

	public boolean validate(CreditLimitProfileDto objDto, int mode) throws TPlusException {

		boolean recExist = true;
		
		CreditLimitProfileDto objCreditLimitProfile = (CreditLimitProfileDto) objDto;
		
		if (mode == 0 && (objDAO.checkExitsRecord(objCreditLimitProfile) > 0)) {
			recExist = false;
		}
		if (mode == 1 && (objDAO.checkExitsRecord(objCreditLimitProfile) == 0)) {
			recExist = false;
		}
		
		return recExist;
	}

	public boolean add(CreditLimitProfileDto objDto) throws TPlusException {

		boolean boolCreate = false;

		try {

			boolCreate = objDAO.create(objDto);
			
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CreditLimitProfileManager create method"
					+ e);
		}
		
		return boolCreate;
	}

	public CreditLimitProfileSearchDto search(CreditLimitProfileSearchDto objDto, int pageNo) throws TPlusException {

		try {
			objDto = objDAO.search(objDto, pageNo);

		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException("Error in CreditLimitProfileManager search method" + ex);
		}
		
		return objDto;
	}

	public CreditLimitProfileDto getCreditLimitProfileDetail(String sNo) throws TPlusException {
		
		CreditLimitProfileDto objCreditLimitProfile = new CreditLimitProfileDto();
		
		try {
			
			objCreditLimitProfile = objDAO.getCardRiskProfileDetail(sNo);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CreditLimitProfileManager getCreditLimitProfileDetail method" + ex);
		}
		
		return objCreditLimitProfile;
	}

	public boolean update(CreditLimitProfileDto objDto) throws TPlusException {
		
		boolean boolUpdate = false;
		
		try {			
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in CreditLimitProfileManager update method" + e);
		}
		
		return boolUpdate;
	}
	
	public boolean delete(CreditLimitProfileDto objDto) throws TPlusException {
		
		boolean boolDelete = false;
		
		try {			
			boolDelete = objDAO.delete(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in CreditLimitProfileManager delete method" + e);
		}
		
		return boolDelete;
	}

	public Integer getSumCreditScoring(String scoreId) throws TPlusException {

		int total = 0;
		
		try {
			total = objCreditScoringDAO.getTotalCreditScoring(scoreId);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
						"Error in CreditScoringManager getSumCreditScoring method" + e);
		}
		
		return total;
	}
	
	public boolean validateScoreAndProduct(CreditLimitProfileDto objDto, int mode) throws TPlusException {

		boolean recExist = true;
		
		if (mode == 0 && (objDAO.checkUniqueRecord(objDto) > 0)) {
			recExist = false;
		}
		CreditLimitProfileDto objDtoDb = objDAO.getCardRiskProfileDetail(objDto.getsNo());
		if (mode == 1 && (objDAO.checkUniqueRecord(objDto) == 0 || (objDto.getScoreId().equals(objDtoDb.getScoreId()) 
				&& objDto.getCardProductId().equals(objDtoDb.getCardProduct().getCardProductId())))) {
			recExist = false;
		}
		
		return recExist;
	}
	
}
