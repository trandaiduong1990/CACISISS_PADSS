package org.transinfo.cacis.controller.settings;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.settings.CustomerTypeDAO;
import org.transinfo.cacis.dto.settings.CustomerTypeDto;
import org.transinfo.cacis.dto.settings.CustomerTypeSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class CustomerTypeManager {

	private Logger logger = Logger.getLogger(CustomerTypeManager.class);

	private CustomerTypeDAO objDAO;

	public CustomerTypeManager() throws Exception {
		objDAO = DAOFactory.getInstance().getCustomerTypeDAO();

	}

	/*
	 * This method is used for getting the Custometypelist
	 */
	public Collection search(CustomerTypeSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection customerType = null;
		try {
			customerType = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CustomerTypeManager search method" + e);
		}
		return customerType;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public CustomerTypeDto getCustomerTypeDto(String custTypeId)
			throws TPlusException {
		CustomerTypeDto objDto = null;

		try {
			objDto = objDAO.getCustomerTypeDto(custTypeId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in CustomerTypeManager getCustomerTypeDto method"
							+ e);
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular Record to Cust_type table
	 */
	/*public boolean add(CustomerTypeDto objDto) throws TPlusException {

		boolean boolAdd = false;

		try {

			if (!validate(objDto, objDAO.ADD)) {
				System.out.println("\n\n Record Already Exists");
			} else {

				boolAdd = objDAO.add(objDto);
			}

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CustomerTypeManager add method"
					+ e);
		}
		return boolAdd;
	}*/
	
	public boolean add(CustomerTypeDto objDto) throws TPlusException {

		boolean boolAdd = false;

		try {

			boolAdd = objDAO.add(objDto);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException("Error in CustomerTypeManager add method"
					+ e);
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular Record in cust_type table
	 */
	public boolean update(CustomerTypeDto objDto) throws TPlusException {

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
			throw new TPlusException(
					"Error in CustomerTypeManager update method" + e);
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in cust_type table
	 */
	public boolean delete(CustomerTypeDto objDto) throws TPlusException {

		boolean boolDelete = false;
		// setting the status value
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
			throw new TPlusException(
					"Error in CustomerTypeManager delete method" + e);
		}
		return boolDelete;
	}

	public boolean hasActiveCustomers(String customerTypeId) throws TPlusException {
		boolean hasCus = false;

		try {

			hasCus = objDAO.hasActiveCustomers(customerTypeId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in BranchManager hasActiveCustomers method" + e);
		}

		return hasCus;
	}
	
	public boolean hasWithdrawalLimitRules(String customerTypeId) throws TPlusException {
		boolean hasCus = false;

		try {

			hasCus = objDAO.hasWithdrawalLimitRules(customerTypeId);

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(
					"Error in BranchManager hasWithdrawalLimitRules method" + e);
		}

		return hasCus;
	}

	/*
	 * this method is used for calling the checkexistrecord method in Impl class
	 */
	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = true;

		CustomerTypeDto objDto = (CustomerTypeDto) obj;

		if (mode == 0 && objDAO.checkExistrecord(objDto) > 0) {
			rtnMessage = false;
		}
		if (mode == 1 && objDAO.checkExistrecord(objDto) == 0) {
			rtnMessage = false;
		}

		return rtnMessage;
	}

	public static void main(String s[]) throws Exception {

		// CustomerType Test
		CustomerTypeManager objManager = new CustomerTypeManager();
		/*
		 * CustomerTypeDto objDto = new CustomerTypeDto();
		 * objDto.setCustomerTypeId("1"); objDto.setIssuerId("Issuer1");
		 * objDto.setCustomerType("VIP"); objDto.setStatus("Active");
		 * objDto.setLastUpdatedDate(new Date());
		 * objDto.setLastUpdatedBy("IssuerAdmin"); //boolean b =
		 * objManager.add(objDto); boolean b = objManager.update(objDto); //
		 * boolean b = objManager.delete(objDto);
		 */

		// for Branch seaching
		CustomerTypeSearchDto searchcustdto = new CustomerTypeSearchDto();
		searchcustdto.setCustomerTypeId("VIP");
		objManager.search(searchcustdto, 1);

		// for getting Branch dto

		CustomerTypeDto custdto = objManager.getCustomerTypeDto("1");
		System.out.println("CusomerTypeId:" + custdto.getCustomerTypeId());
		System.out.println("CusomerType:" + custdto.getCustomerType());

	}

}