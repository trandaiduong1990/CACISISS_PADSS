package org.transinfo.cacis.controller.applicationforms;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.SupplFormDAO;
import org.transinfo.cacis.dto.applicationforms.SupplementaryFormDto;
import org.transinfo.cacis.dto.applicationforms.SupplementaryFormSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.common.ApplicationMasterDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings( { "static-access", "unchecked" })
public class SupplFormManager {

	private SupplFormDAO objSupplFormDAO;

	public SupplFormManager() throws Exception {
		objSupplFormDAO = DAOFactory.getInstance().getSupplFormDAO();
	}

	public Collection search(SupplementaryFormSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objSupplFormDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in SupplementaryForm search method"
					+ e);
		}
		return searchLst;

	}

	public SupplementaryFormDto getSupplementaryForm(String applicationid)
			throws TPlusException {

		SupplementaryFormDto supplFormDto = null;
		try {
			supplFormDto = objSupplFormDAO.getSupplementaryForm(applicationid);
		} catch (Exception e) {
			throw new TPlusException("Error in SupplementaryForm get method"
					+ e);
		}
		return supplFormDto;
	}

	public boolean add(SupplementaryFormDto objSupplDto) throws TPlusException {
		boolean success = false;

		objSupplDto.setApplicationId(IdsGenartion.GenerateApplicationId());
		objSupplDto.setApplStatus(CommonCodes.APPLICATIONSTATUS_NEW);
		objSupplDto
				.setApplType(CommonCodes.APPLICATIONTYPE_NEWSUPPLEMENTARYCARD);

		// this is to validate whether this type of application already existed
		// or not in application_master
		// if exists show message this type of record already opend.
		ApplicationMasterDto objMstDto = new ApplicationMasterDto();
		objMstDto.setApplicationType(objSupplDto.getApplType());
		objMstDto.setIdNumber(objSupplDto.getIdNumber());

		try {
			if (!validate(objMstDto, objSupplFormDAO.ADD)) {
				System.out.println("Record Already Exists");
			} else {
				success = objSupplFormDAO.add(objSupplDto);
			}

		} catch (Exception e) {
			throw new TPlusException("Error in SupplementaryForm Add method"
					+ e);
		}
		return success;
	}

	public boolean update(SupplementaryFormDto objSupplDto)
			throws TPlusException {
		boolean success = false;
		try {
			if (!validate(objSupplDto, objSupplFormDAO.UPDATE)) {

				System.out.println("Record Not Exists");
			} else {
				success = objSupplFormDAO.update(objSupplDto);
				;
			}

		} catch (Exception e) {
			throw new TPlusException("Error in SupplementaryForm update method"
					+ e);
		}
		return success;
	}

	public boolean accept(SupplementaryFormDto objSupplDto)
			throws TPlusException {
		boolean accept = false;
		Date today = new Date();
		objSupplDto.setApplStatus(CommonCodes.APPLICATIONSTATUS_ACCEPTED);
		objSupplDto.setCloseDate(today);
		objSupplDto.setCardProcessStatus(CommonCodes.CARD_PROCESS_NEW);

		try {
			if (!validate(objSupplDto, objSupplFormDAO.UPDATE)) {

				System.out.println("Record Not Exists");
			} else {
				accept = objSupplFormDAO.accept(objSupplDto);
				;
			}
		} catch (Exception e) {
			throw new TPlusException("Error in SupplementaryForm accept method"
					+ e);
		}
		return accept;
	}

	public boolean reject(SupplementaryFormDto objSupplDto)
			throws TPlusException {
		boolean reject = false;
		Date today = new Date();
		objSupplDto.setApplStatus(CommonCodes.APPLICATIONSTATUS_CLOSED);
		objSupplDto.setCloseDate(today);
		try {
			if (!validate(objSupplDto, objSupplFormDAO.UPDATE)) {

				System.out.println("Record Not Exists");
			} else {
				reject = objSupplFormDAO.reject(objSupplDto);
				;
			}

		} catch (Exception e) {
			throw new TPlusException("Error in SupplementaryForm reject method"
					+ e);
		}
		return reject;
	}

	public CardsDto getCardsDto(long primaryCardNo) throws TPlusException {

		CardsDto cardDto = null;
		try {
			cardDto = objSupplFormDAO.getCardsDto(primaryCardNo);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in SupplementaryFormManagers  getCardsDto method"
							+ e);
		}
		return cardDto;
	}

	/*
	 * for validating the records existed or not
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;
		if (mode == 0 && objSupplFormDAO.checkExistrecord(obj) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objSupplFormDAO.checkExistrecord(obj) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public static void main(String s[]) throws Exception {/*

		SupplFormManager supplFormMgr = new SupplFormManager();
		SupplementaryForm objSupplForm = new SupplementaryForm();

		SupplementaryFormSearchDto objSupplDto = null;
		// SupplementarySearchForm objSupplSearchForm = new
		// SupplementarySearchForm();
		try {
			// System.out.println("Error converting to form bean: ");
			SupplementaryFormDto objSupplFormDto = new SupplementaryFormDto();
			objSupplFormDto.setApplStatus(CommonCodes.APPLICATIONSTATUS_CLOSED);
			objSupplFormDto.setCloseDate(new Date());
			objSupplFormDto.setApplicationId("A060411893");

			// supplFormMgr.reject(objSupplFormDto);
			// System.out.println(objSupplFormDto.getIssuerId());
		} catch (Exception e) {
			System.out.println("Error converting to form bean: " + e);
			throw e;
		}
	*/}

}
