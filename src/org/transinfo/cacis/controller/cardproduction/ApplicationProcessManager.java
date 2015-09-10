package org.transinfo.cacis.controller.cardproduction;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplicationProcessDAO;
import org.transinfo.cacis.dto.cardproduction.ApplicationCardProductsDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationDocProofDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.cardproduction.SupplementaryCardHolderDto;

@SuppressWarnings( { "unchecked", "unused" })
public class ApplicationProcessManager {

	private static ApplicationProcessDAO objDAO;

	public ApplicationProcessManager() throws Exception {
		objDAO = DAOFactory.getInstance().getApplicationProcessDAO();

	}

	public Collection search(ApplicationProcessSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in ApplicationProcessManager search method" + e);
		}
		return searchLst;

	}

	public ApplicationProcessDto getApplicationProcessDto(String applicaionId)
			throws TPlusException {

		ApplicationProcessDto objProcessDto = null;

		try {
			objProcessDto = objDAO.getApplicationProcessDto(applicaionId);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in ApplicationProcessManager getApplicationProcessDto method"
							+ e);
		}
		return objProcessDto;
	}

	/* to upadte in application forms table during application process */
	public boolean update(ApplicationFormDto objDto) throws TPlusException {

		boolean success = false;
		try {
			success = objDAO.update(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in ApplicationProcessManager save method" + e);
		}
		return success;
	}

	public boolean accept(ApplicationProcessDto objAppProcessDto, CustomerAddressDto supplAddressDto)
			throws TPlusException {

		boolean accept = false;
		Date today = new Date();
		objAppProcessDto
				.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_ACCEPTED);
		objAppProcessDto.setCloseDate(today);

		try {
			// for Card Number Generation
			// CardGeneration objCardGen = CardGeneration.getInstance();
			// objCardGen.PrimaryCardGeneration(objAppProcessDto);

			System.out
					.println("****************************************************************************");
			System.out
					.println("************************** DISPATCH CONTROLLER *************************************");
			System.out
					.println("****************************************************************************");

			accept = objDAO.accept(objAppProcessDto, supplAddressDto);

		} catch (Exception e) {
			throw new TPlusException(
					"Error in ApplicationProcessManager accept method" + e);
		}
		return accept;
	}

	public boolean reject(ApplicationProcessDto objDto) throws TPlusException {
		boolean reject = false;
		Date today = new Date();
		objDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_REJECTED);
		objDto.setCloseDate(today);

		try {
			reject = objDAO.reject(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in ApplicationProcessManager Reject method" + e);
		}
		return reject;
	}
	
	public boolean pending(ApplicationProcessDto objDto) throws TPlusException {
		boolean reject = false;
		Date today = new Date();
		objDto.setApplicationStatus(CommonCodes.APPLICATIONSTATUS_PENDING);
		objDto.setCloseDate(today);

		try {
			reject = objDAO.pending(objDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in ApplicationProcessManager pending method" + e);
		}
		return reject;
	}

	public Collection customerHistory(String custIdNumber, String appId)
			throws TPlusException {

		Collection historyList = null;

		try {
			historyList = objDAO.customerHistory(custIdNumber, appId);

		} catch (Exception e) {

			throw new TPlusException(
					"Error in ApplicationProcessManager customerHistory method"
							+ e);
		}
		return historyList;

	}

	public static void main(String args[]) throws Exception {

		ApplicationProcessManager objManager = new ApplicationProcessManager();
		ApplicationProcessDto objAppProcessDto = new ApplicationProcessDto();
		// objDto = objDAO.getApplicationProcessDto("3");

		CustomerAddressDto objAddress = new CustomerAddressDto();
		objAppProcessDto.getApplicationAddress().add(objAddress);

		// for CustomerCardproducts test
		String custcardproducts[] = { "3" };
		for (int i = 0; i < custcardproducts.length; i++) {
			// objAppProcessDto.getAppCardProducts().put(custcardproducts[i],new
			// CustomerCardProductsDto(objAppProcessDto.getUpdatedDate(),objAppProcessDto.getLastUpdatedBy()));
			objAppProcessDto.getAppCardProducts().put(
					custcardproducts[i],
					new ApplicationCardProductsDto(objAppProcessDto
							.getUpdatedDate(), objAppProcessDto.getUserId()));
		}

		// for Customer Doc proofs test

		String custdocproofs[] = { "3" };
		for (int i = 0; i < custdocproofs.length; i++) {
			// objAppProcessDto.getAppDocProofs().put(custdocproofs[i],new
			// CustomerDocProofDto(objAppProcessDto.getUpdatedDate(),objAppProcessDto.getLastUpdatedBy()));
			objAppProcessDto.getAppDocProofs().put(
					custdocproofs[i],
					new ApplicationDocProofDto(objAppProcessDto
							.getUpdatedDate(), objAppProcessDto.getUserId()));
		}

		// for CustomerSuppCardHolderTest test

		SupplementaryCardHolderDto objSuppDto = new SupplementaryCardHolderDto();
		objSuppDto.setSupplementaryId("1");
		BeanUtils.copyProperties(objSuppDto, objAppProcessDto);
		BeanUtils.copyProperties(objSuppDto, objAddress);

		// for CustomerAccount test
		CustomerAccountDto objCutAccountDto = new CustomerAccountDto();
		// BeanUtils.copyProperties(objCutAccountDto, objAppProcessDto);
		objCutAccountDto.setAppProcessDto(objAppProcessDto);
		// for customer to cards

		CardsDto objCardsDto = new CardsDto();
		objCardsDto.setCustAccountDto(objCutAccountDto);
		// BeanUtils.copyProperties(objCardsDto, objAppProcessDto);

		objAppProcessDto.getCustomerSuppAddress().add(objSuppDto);
		objAppProcessDto.getCustomerAccount().add(objCutAccountDto);
		objCutAccountDto.getCustomerCards().add(objCardsDto);

		// objManager.save(objAppProcessDto);
		// objManager.update(objCustDto);
		// objManager.delete(objCustDto);

	}

	public boolean isDuplicateNRC(String nrc, String suppNrc, String appId) throws TPlusException {
		boolean rtnMessage = false;

		/*if (objDAO.isDuplicateNRC(nrc, suppNrc, appId) > 0) {
			rtnMessage = true;
		}*/
		
		rtnMessage = objDAO.isDuplicateNRCNewMethod(nrc, suppNrc, appId);

		return rtnMessage;
	}

}