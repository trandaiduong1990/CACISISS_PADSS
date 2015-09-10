package org.transinfo.cacis.controller.settings;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.BranchDAO;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.BranchSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class BranchManager {

	private Logger logger = Logger.getLogger(BranchManager.class);

	private BranchDAO objDAO;

	public BranchManager() throws Exception {
		objDAO = DAOFactory.getInstance().getBranchDAO();

	}

	/*
	 * This method is used for getting the BranchList
	 */
	public Collection search(BranchSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection branch = null;
		try {
			branch = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in BranchManager search method" + e);
		}
		return branch;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public BranchDto getBranchDto(String branchId) throws TPlusException {
		BranchDto objDto = null;

		try {
			objDto = objDAO.getBranchDto(branchId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in BranchManager getBranchDto method" + e);
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular Record to Baranch table
	 */
	/*public boolean add(BranchDto objDto) throws TPlusException {

		boolean boolAdd = false;

		try {

			if (!validate(objDto, objDAO.ADD)) {
				System.out.println("\n\n Record Already Exists");
			} else {

				boolAdd = objDAO.add(objDto);
			}

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in BranchManager add method" + e);
		}
		return boolAdd;
	}*/
	
	public boolean add(BranchDto objDto) throws TPlusException {

		boolean boolAdd = false;

		try {

			boolAdd = objDAO.add(objDto);
			
		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in BranchManager add method" + e);
		}
		
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular Record in Branch table
	 */
	public boolean update(BranchDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */
			boolUpdate = objDAO.update(objDto);
			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in BranchManager update method" + e);
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in Branch table
	 */
	public boolean delete(BranchDto objDto) throws TPlusException {

		boolean boolDelete = false;
		// to deactivte the branch
		objDto.setStatus(CommonCodes.STATUS_INACTIVE);
		try {
			/*
			 * if(!validate(objDto,objDAO.UPDATE)) {
			 * System.out.println("\n\n Record  not Existed"); }else{
			 */

			boolDelete = objDAO.delete(objDto);

			// }

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in BranchManager delete method" + e);
		}
		return boolDelete;
	}

	public boolean hasActiveCustomers(String branchId) throws TPlusException {
		boolean hasCus = false;

		try {

			hasCus = objDAO.hasActiveCustomers(branchId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in BranchManager hasActiveCustomers method" + e);
		}

		return hasCus;
	}

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		BranchDto objDto = (BranchDto) obj;

		if (mode == 0 && objDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(objDto) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public static void main(String s[]) throws Exception {/*
														 * 
														 * // BranchSetup Test
														 * BranchManager
														 * objManager = new
														 * BranchManager();
														 * BranchDto objDto =
														 * new BranchDto();
														 * objDto
														 * .setBranchId("2321");
														 * objDto
														 * .setIssuerId("Issuer1"
														 * );
														 * objDto.setBranchName
														 * ("MayBank1");
														 * objDto.setAddress1
														 * ("mainsreet1");
														 * objDto
														 * .setAddress2("pandaindha"
														 * );
														 * objDto.setCity("nzb"
														 * );objDto.setState(
														 * "kualumpur");
														 * objDto.setCountry
														 * ("sd");
														 * objDto.setContactPerson
														 * ("satyamreddy");
														 * objDto
														 * .setContactFax("843275"
														 * );
														 * objDto.setContactPhone
														 * ("83274983");
														 * objDto.setPostalCode
														 * (4389);
														 * objDto.setContactEmail
														 * (
														 * "reddy1_reddy@yahoo.com"
														 * );
														 * objDto.setStatus("A"
														 * );
														 * objDto.setUpdatedDate
														 * (new Date());
														 * objDto.setUserId
														 * ("IssuerAdmin");
														 * boolean b =
														 * objManager
														 * .add(objDto); //
														 * boolean b =
														 * objManager
														 * .update(objDto); //
														 * boolean b =
														 * objManager
														 * .delete(objDto);
														 * 
														 * // for Branch
														 * searching //
														 * BranchSearchDto
														 * searchbchdto = new
														 * BranchSearchDto(); //
														 * searchbchdto
														 * .setBranchId("2"); //
														 * objManager
														 * .search(searchbchdto
														 * ,2);
														 * 
														 * // for getting Branch
														 * dto
														 * 
														 * // BranchDto bchdto =
														 * objManager
														 * .getBranchDto("2");
														 * //
														 * System.out.println(
														 * "BranchId:"
														 * +bchdto.getBranchId
														 * ()); //
														 * System.out.println
														 * ("BranchName:"
														 * +bchdto.
														 * getBranchName());
														 */
	}

}