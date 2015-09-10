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
import org.transinfo.cacis.dataacess.dao.settings.BranchDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.BranchSearchDto;

@SuppressWarnings("unchecked")
public class BranchDAOImpl extends BaseDAOImpl implements BranchDAO {

	private Logger logger = Logger.getLogger(BranchDAOImpl.class);

	/*
	 * This method is used for getting the BranchList
	 */
	public Collection search(BranchSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select bch.branchId,bch.branchName,to_char(bch.updatedDate,'dd-MM-yyyy') From BranchDto bch where bch.status='"
							+ CommonCodes.STATUS_ACTIVE + "' ");

			// if(objSearchDto.getIssuerId()!=null &&
			// !objSearchDto.getIssuerId().equals("")){
			sbf
					.append("and bch.issuerId ='" + objSearchDto.getIssuerId()
							+ "'");
			// }
			if (objSearchDto.getBranchId() != null
					&& !objSearchDto.getBranchId().equals("")) {
				sbf.append(" and bch.branchId = '" + objSearchDto.getBranchId()
						+ "' ");
			}

			// sbf.append("and bch.status='"+CommonCodes.STATUS_ACTIVE+"' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error in BranchDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BranchDAOImpl search  method :" + e);

		}
		return objSearchCollection;

	}

	/*
	 * This method is used for getting the particular BranchRecord to update
	 */
	public BranchDto getBranchDto(String branchId) throws TPlusException {

		BranchDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objDto = (BranchDto) session.get(BranchDto.class, branchId);
			// session.load(objDto,branchId );
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in BranchDAOImpl getBranchDto method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BranchDAOImpl getBranchDto  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	/*
	 * This method is used for insert the particular Record to Branch table
	 */
	public boolean add(BranchDto objDto) throws TPlusException {

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
			System.out.println("Error in BranchDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BranchDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in Branch table
	 */
	public boolean update(BranchDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objDto);
			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in BranchDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BranchDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in Branch table
	 */
	public boolean delete(BranchDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// session.delete(objDto);
			String sql = "UPDATE BranchDto SET status =:branchstatus  WHERE branchId=:branchid";
			int count = session.createQuery(sql).setString("branchid",
					objDto.getBranchId()).setString("branchstatus",
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
			System.out.println("Error in BracnhDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BracnhDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolDelete;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.BranchDAO
	 * #checkExistrecord(org.transinfo.cacis.dto.settings.IssuerDto) For
	 * checking Record Existed or nor in Branch Table
	 */
	public int checkExistrecord(BranchDto objDto) throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from BranchDto bch where bch.branchId=:branchid ");
			qry.setString("branchid", objDto.getBranchId());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After BranchDAOIMPL checkExistrecord() count"
					+ res);

		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in BranchDAOImpl checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in BranchDAOImpl checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

	public boolean hasActiveCustomers(String branchId) throws TPlusException {

		boolean boolhas = false;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from ApplicationProcessDto apdto ");
			sbf.append("where apdto.customerStatus <> 4 ");
			sbf.append("and  apdto.branchId = '" + branchId + "' ");

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
			System.out
					.println("Error in BranchDAOImpl hasActiveCustomers method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BranchDAOImpl hasActiveCustomers  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolhas;

	}

}
