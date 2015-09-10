package org.transinfo.cacis.controller.settings;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.IssuerDAO;
import org.transinfo.cacis.dto.settings.IssuerDto;
import org.transinfo.cacis.dto.settings.IssuerSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class IssuerManager {

	private Logger logger = Logger.getLogger(IssuerManager.class);

	private IssuerDAO objDAO;

	public IssuerManager() throws Exception {
		objDAO = DAOFactory.getInstance().getIssuerDAO();

	}

	/*
	 * This method is used for getting the IsuuerList
	 */

	public Collection search(IssuerSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection issuers = null;
		try {
			issuers = objDAO.search(objSearchDto, pageNo);
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in IssuerManager search method" + e);
		}
		return issuers;
	}

	/*
	 * This method is used for getting the particular IsuuerRecord to update
	 */
	public IssuerDto getIssuerDto(String issuerId) throws TPlusException {

		IssuerDto issuerDto = null;

		try {

			issuerDto = objDAO.getIssuerDto(issuerId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in IssuerManager getIssuerDto method" + e);
		}
		return issuerDto;
	}

	/*
	 * This method is used for insert the particular IsuuerRecord to
	 * Issuer_master table
	 */
	public boolean add(IssuerDto objDto) throws TPlusException {

		boolean boolAdd = false;
		try {

			if (!validate(objDto, objDAO.ADD)) {
				System.out.println("\n\n Record Already Exists");
			} else {
				boolAdd = objDAO.add(objDto);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in IssuerManager add method" + e);
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular IsuuerRecord in
	 * Issuer_master table
	 */
	public boolean update(IssuerDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		// to deactivte the issuer
		// objDto.setStatus(CommonCodes.STATUS_INACTIVE);
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */
			boolUpdate = objDAO.update(objDto);
			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in IssuerManager update method" + e);
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular IsuuerRecord in
	 * Issuer_master table
	 */
	public boolean delete(IssuerDto objDto) throws TPlusException {

		boolean boolDelete = false;
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */
			boolDelete = objDAO.delete(objDto);
			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in IssuerManager delete method" + e);
		}
		return boolDelete;
	}

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		IssuerDto objDto = (IssuerDto) obj;

		if (mode == 0 && objDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(objDto) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public static void main(String s[]) throws Exception {

		// IssuerSetup Test
		IssuerManager issMgr = new IssuerManager();
		// IssuerDto issdto= issMgr.getIssuerDto("Issuer1");
		IssuerDto issdto = new IssuerDto();
		issdto.setIssuerId("Issuer1");
		issdto.setIssuerName("MayBank");
		issdto.setAddress1("mainsreet");
		issdto.setAddress2("pandaindha");
		issdto.setCity("Arr");
		issdto.setState("kualumpur");
		issdto.setCountry("sd");
		issdto.setContactName("satyamreddy");
		issdto.setContactPhone("9843345454");
		issdto.setContactMobile("6543453543");
		issdto.setContactFax("4385532345");
		issdto.setContactEmail("sayam_java@hotmail.com");
		issdto.setRegistrationDate(new Date());
		issdto.setStatus("b");
		// issdto.setLastUpdatedDate(new Date());
		// issdto.setLastUpdatedBy("IssuerAdmin");
		// boolean b = issMgr.add(issdto);
		// boolean b = issMgr.update(issdto);
		// boolean b = issMgr.deleteIssuer(issdto);

		// for Issuer seaching
		/*
		 * IssuerSearchDto searchissdto = new IssuerSearchDto();
		 * searchissdto.setIssuerId("Issuer1"); searchissdto.setStatus("A");
		 * issMgr.search(searchissdto);
		 */

		// for getting issuerDto

		issdto = issMgr.getIssuerDto("Issuer1");
		System.out.println("IssuerId:" + issdto.getIssuerId());
		System.out.println("IssuerName:" + issdto.getIssuerName());
		System.out.println("lastupdatedDate:" + issdto.getUpdatedDate());

		// for conversion test testing
		/*
		 * IssuerSearchForm objForm = new IssuerSearchForm(); IssuerSearchDto
		 * searchissdto = new IssuerSearchDto(); objForm.setIssuerId("Issuer1");
		 * objForm.setIssuerName("Maybank");
		 * BeanUtils.copyProperties(searchissdto, objForm);
		 * issMgr.search(searchissdto);
		 * System.out.println("IssuerId:"+searchissdto.getIssuerId());
		 * System.out.println("IssuerName:"+searchissdto.getIssuerName());
		 */

		// for prelist data test
		// IssuerSetupForm objForm = new IssuerSetupForm();
		// issMgr.countryListData(objForm);

	}

}