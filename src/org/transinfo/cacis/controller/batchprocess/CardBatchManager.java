package org.transinfo.cacis.controller.batchprocess;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardBatchDAO;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.batchprocess.SearchInstantCardDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.formbean.batchprocess.CardBatchForm;

public class CardBatchManager {
	private Logger logger = Logger.getLogger(CardBatchManager.class);
	private CardBatchDAO objDAO;

	public CardBatchManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCardBatchDAO();
	}

	public boolean add(InstantCardDto objDto) throws TPlusException {

		boolean boolCreate = false;

		try {

			boolCreate = objDAO.create(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CardBatchManager create method"
					+ e);
		}

		return boolCreate;
	}

	public boolean validate(Object objDto, int mode) throws TPlusException {

		boolean recExist = true;

		InstantCardDto objCardBatch = (InstantCardDto) objDto;

		if (mode == 0 && (objDAO.checkExitsRecord(objCardBatch) > 0)) {
			recExist = false;
		}

		if (mode == 1 && (objDAO.checkExitsRecord(objCardBatch) == 0)) {
			recExist = false;
		}

		return recExist;
	}

	public boolean validateUser(Object objForm) throws TPlusException {

		boolean isValid = true;

		CardBatchForm objInstantCard = (CardBatchForm) objForm;

		isValid = (!objInstantCard.getUserId().equals("ASPSUPERADMIN") && objInstantCard
				.getUserName().equals("ASPSUPERADMIN")) ? false : objDAO
				.checkExitsUser(objInstantCard) == 1;

		return isValid;
	}

	public Collection search(SearchInstantCardDto objDto, int pageNo)
			throws TPlusException {

		Collection catdBatchs = null;
		try {
			catdBatchs = objDAO.search(objDto, pageNo);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException("Error in CardBatchManager search method"
					+ ex);
		}
		return catdBatchs;
	}

	public InstantCardDto getCardBatchDetail(String batchId)
			throws TPlusException {

		InstantCardDto objDto = new InstantCardDto();

		try {

			objDto = objDAO.getCardBatchDetail(batchId);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CardBatchManager getCardBatchDetail method" + ex);
		}

		return objDto;
	}

	public CodeMasterDto getStatusDesc(String status) throws TPlusException {

		CodeMasterDto objDto = new CodeMasterDto();

		try {

			objDto = objDAO.getStatusDesc(status);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CardBatchManager getStatusDesc method" + ex);
		}

		return objDto;
	}

	public CardProductDto getCardProductName(String id) throws TPlusException {

		CardProductDto objDto = new CardProductDto();

		try {

			objDto = objDAO.getCardProductName(id);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CardBatchManager getCardProductName method" + ex);
		}

		return objDto;
	}

	public BranchDto getBranchName(String id) throws TPlusException {

		BranchDto objDto = new BranchDto();

		try {

			objDto = objDAO.getBranchName(id);
		} catch (Exception ex) {
			logger.error(ex);
			throw new TPlusException(
					"Error in CardBatchManager getBranchName method" + ex);
		}

		return objDto;
	}

	public boolean update(InstantCardDto objDto) throws TPlusException {

		boolean boolUpdate = false;

		try {
			boolUpdate = objDAO.update(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in EMVProfileManager update method"
					+ e);
		}

		return boolUpdate;
	}

	public boolean delete(InstantCardDto objDto) throws TPlusException {

		boolean boolDelete = false;

		try {
			boolDelete = objDAO.delete(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in EMVProfileManager delete method"
					+ e);
		}

		return boolDelete;
	}

	public boolean process(InstantCardDto objDto) throws TPlusException {

		boolean boolProcess = false;

		try {
			boolProcess = objDAO.process(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in EMVProfileManager update method"
					+ e);
		}

		return boolProcess;
	}

	public boolean authorize(InstantCardDto objDto) throws TPlusException {

		boolean boolProcess = false;

		try {
			boolProcess = objDAO.authorize(objDto);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CardBatchManager authorize method" + e);
		}

		return boolProcess;
	}
}
