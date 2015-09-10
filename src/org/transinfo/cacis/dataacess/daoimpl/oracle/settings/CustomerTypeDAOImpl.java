package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CustomerTypeDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CustomerTypeDto;
import org.transinfo.cacis.dto.settings.CustomerTypeSearchDto;

@SuppressWarnings("unchecked")
public class CustomerTypeDAOImpl extends BaseDAOImpl implements CustomerTypeDAO {

	private Logger logger = Logger.getLogger(CustomerTypeDAOImpl.class);

	/*
	 * This method is used for getting the CustomerTypeList
	 */
	public Collection search(CustomerTypeSearchDto objSearchDto, int PageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select cst.customerTypeId,cst.customerType,to_char(cst.updatedDate,'dd-MM-yyyy') From CustomerTypeDto cst where cst.status='"
							+ CommonCodes.STATUS_ACTIVE + "'");

			// if(objSearchDto.getIssuerId()!=null &&
			// !objSearchDto.getIssuerId().equals("")){
			sbf.append("and cst.issuerId = '" + objSearchDto.getIssuerId()
					+ "' ");
			// }
			if (objSearchDto.getCustomerTypeId() != null
					&& !objSearchDto.getCustomerTypeId().equals("")) {
				sbf.append("and cst.customerTypeId ='"
						+ objSearchDto.getCustomerTypeId() + "'");
			}

			// sbf.append("and cst.status='"+CommonCodes.STATUS_ACTIVE+"' ");

			objSearchCollection = getSearchList(sbf.toString(), PageNo);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error in CustomerTypeDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CustomerTypeDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public CustomerTypeDto getCustomerTypeDto(String customerTypeId)
			throws TPlusException {

		CustomerTypeDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CustomerTypeDto) session.get(CustomerTypeDto.class,
					customerTypeId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CustomerTypeDAOImpl getCardTypeDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CustomerTypeDAOImpl getCustomerTypeDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	/*
	 * This method is used for insert the particular Record to Cust_Type table
	 */
	public boolean add(CustomerTypeDto objDto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDto);

			tx.commit();
			boolAdd = true;

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CustomerTypeDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CustomerTypeDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in cust_type table
	 */
	public boolean update(CustomerTypeDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objDto);
			session.flush();
			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CustomerTypeDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CustomerTypeDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in cust_type table
	 */
	public boolean delete(CustomerTypeDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE CustomerTypeDto SET status =:status  WHERE customerTypeId=:customertypeid";
			int count = session.createQuery(sql).setString("customertypeid",
					objDto.getCustomerTypeId()).setString("status",
					objDto.getStatus()).executeUpdate();

			// session.delete(objIssdto);
			tx.commit();
			if (count > 0)
				boolDelete = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CustomerTypeDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CustomerTypeDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolDelete;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.CustomerTypeDAO
	 * #checkExistrecord(org.transinfo.cacis.dto.settings.CustomerTypeDto) For
	 * checking Record Existed or nor in Cust_type Table
	 */
	public int checkExistrecord(CustomerTypeDto objDto) throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from CustomerTypeDto cst where cst.customerTypeId=:customertypeid ");
			qry.setString("customertypeid", objDto.getCustomerTypeId());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out
					.println("After CustomerTypeDAOIMPL checkExistrecord() count"
							+ res);

		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in CustomerTypeDAOIMPL checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CustomerTypeDAOIMPL checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

	public boolean hasActiveCustomers(String customerTypeId)
			throws TPlusException {

		boolean boolhas = false;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from ApplicationProcessDto apdto ");
			sbf.append("where apdto.customerStatus <> 4 ");
			sbf.append("and  apdto.customerTypeId = '" + customerTypeId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			int count = ((Integer) list.get(0)).intValue();
			if (count > 0) {
				boolhas = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CustomerTypeDAOImpl hasActiveCustomers  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolhas;

	}

	public boolean hasWithdrawalLimitRules(String customerTypeId)
			throws TPlusException {

		boolean boolhas = false;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from WithdrawalLimitRulesDto wdlr ");
			sbf.append("where wdlr.custTypeId = '" + customerTypeId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			int count = ((Integer) list.get(0)).intValue();
			if (count > 0) {
				boolhas = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CustomerTypeDAOImpl hasWithdrawalLimitRules  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolhas;

	}

}
