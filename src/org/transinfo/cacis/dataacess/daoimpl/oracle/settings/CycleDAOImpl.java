package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CycleDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CycleDto;
import org.transinfo.cacis.dto.settings.CycleSearchDto;
import org.transinfo.cacis.dto.settings.IssuerDto;

@SuppressWarnings("unchecked")
public class CycleDAOImpl extends BaseDAOImpl implements CycleDAO {

	private Logger logger = Logger.getLogger(CycleDAOImpl.class);

	/*
	 * This method is used for getting the CycleList
	 */
	public Collection search(CycleSearchDto objSearchDto, int PageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			// if(objSearchDto.getIssuerId()!=null &&
			// !objSearchDto.getIssuerId().equals("")){

			sbf
					.append("select cn.cycleNo,cn.issuerId,cn.dayOfMonth,to_char(cn.updatedDate,'dd-MM-yyyy') From CycleDto cn where cn.issuerId ='"
							+ objSearchDto.getIssuerId() + "' ");
			// }
			sbf.append("and cn.status='" + CommonCodes.STATUS_ACTIVE + "' ");

			objSearchCollection = getSearchList(sbf.toString(), PageNo);

		} catch (Exception e) {
			logger.error(e);

			System.out.println("Error in CycleDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CycleDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public CycleDto getCycleDto(int cycleNo) throws TPlusException {

		CycleDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CycleDto) session.get(CycleDto.class,
					new Integer(cycleNo));
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CycleDAOImpl getCycleDto method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CycleDAOImpl getCycleDto  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	/*
	 * This method is used for insert the particular Record to Cycles table
	 */
	public boolean add(CycleDto objDto) throws TPlusException {

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
			System.out.println("Error in CycleDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CycleDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in Cycles table
	 */
	public boolean update(CycleDto objDto) throws TPlusException {

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
			System.out.println("Error in CycleDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CycleDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in Cycles table
	 */
	public boolean delete(CycleDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE CycleDto SET status =:status  WHERE cycleNo=:cycleno";
			int count = session.createQuery(sql).setInteger("cycleno",
					objDto.getCycleNo())
					.setString("status", objDto.getStatus()).executeUpdate();

			tx.commit();
			if (count > 0)
				boolDelete = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CycleDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CycleDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolDelete;
	}

	public Map issuerListData() throws TPlusException {

		Map issuerList = new TreeMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From IssuerDto");
			List listIssuers = qry.list();
			for (Iterator it = listIssuers.iterator(); it.hasNext();) {
				IssuerDto objDto = new IssuerDto();
				objDto = (IssuerDto) it.next();
				issuerList.put(objDto.getIssuerId(), objDto.getIssuerId() + "-"
						+ objDto.getIssuerName());
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CycleDAOImpl issuerListData method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CycleDAOImpl issuerListData  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return issuerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.CycleDAO
	 * #checkExistrecord(org.transinfo.cacis.dto.settings.CycleDto) For checking
	 * Record Existed or nor in Cyles Table
	 */
	public int checkExistrecord(CycleDto objDto) throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from CycleDto cc where cc.cycleNo=:cycleno ");
			qry.setInteger("cycleno", objDto.getCycleNo());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After CycleDAOImpl checkExistrecord() count"
					+ res);

		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in CycleDAOImpl checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CycleDAOImpl checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

	public int checkExistrecordCycleNo(String cycleNo) throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from CycleDto cc where cc.cycleNo=:cycleno ");
			qry.setInteger("cycleno", Integer.valueOf(cycleNo));
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After CycleDAOImpl checkExistrecord() count"
					+ res);

		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in CycleDAOImpl checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CycleDAOImpl checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

	public int checkExistrecordDayOfMonth(String dayOfMonth)
			throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from CycleDto cc where cc.dayOfMonth=:dayOfMonth ");
			qry.setInteger("dayOfMonth", Integer.valueOf(dayOfMonth));
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After CycleDAOImpl checkExistrecord() count"
					+ res);

		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in CycleDAOImpl checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CycleDAOImpl checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

	public boolean hasCustomerAccounts(String cycleNo) throws TPlusException {

		boolean boolhas = false;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from CustomerAccountDto cadto ");
			sbf.append("where  cadto.cycleNo = " + cycleNo + " ");

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
					.println("Error in CycleDAOImpl hasCustomerAccounts method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CycleDAOImpl hasCustomerAccounts  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolhas;

	}

}
