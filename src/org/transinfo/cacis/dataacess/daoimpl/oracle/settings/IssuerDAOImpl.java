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
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.IssuerDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.IssuerDto;
import org.transinfo.cacis.dto.settings.IssuerSearchDto;

@SuppressWarnings("unchecked")
public class IssuerDAOImpl extends BaseDAOImpl implements IssuerDAO {

	private Logger logger = Logger.getLogger(IssuerDAOImpl.class);

	/*
	 * This method is used for getting the IsuuerList
	 */
	public Collection search(IssuerSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select iss.issuerId,iss.issuerName,to_char(iss.updatedDate,'dd-MM-yyyy') FROM IssuerDto iss where 1=1 ");

			if (objSearchDto.getIssuerId() != null
					&& !objSearchDto.getIssuerId().equals("")) {
				sbf.append(" AND iss.issuerId='" + objSearchDto.getIssuerId()
						+ "'");
			}

			if (objSearchDto.getIssuerName() != null
					&& !objSearchDto.getIssuerName().equals("")) {
				sbf.append(" AND iss.issuerName = '"
						+ objSearchDto.getIssuerName() + "'");
			}

			if (objSearchDto.getStatus() != null
					&& !objSearchDto.getStatus().equals("")) {
				sbf.append(" AND iss.status = '" + objSearchDto.getStatus()
						+ "' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

			/*
			 * Session session = HibernetFactory.currentSession(); Transaction
			 * tx =session.beginTransaction(); Query qry =
			 * session.createQuery(sbf.toString()); List listIssuers =
			 * qry.list(); for(Iterator it =
			 * listIssuers.iterator();it.hasNext();){ IssuerDto objDto = new
			 * IssuerDto(); objDto = (IssuerDto) it.next();
			 * objSearchCollection.add(objDto); }
			 * 
			 * tx.commit();
			 */

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error in IssuerDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in IssuerDAOImpl search  method :" + e);
		} finally {

		}
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular IsuuerRecord to update
	 */
	public IssuerDto getIssuerDto(String issuerId) throws TPlusException {

		IssuerDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// session.load(objDto,issuerId );
			objDto = (IssuerDto) session.get(IssuerDto.class, issuerId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in IssuerDAOImpl getIssuerDto method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in IssuerDAOImpl getIssuerDto  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;
	}

	/*
	 * This method is used for insert the particular IsuuerRecord to
	 * Issuer_master table
	 */
	public boolean add(IssuerDto objIssdto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objIssdto);

			tx.commit();
			boolAdd = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in IssuerDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in IssuerDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;
	}

	/*
	 * This method is used for updating the particular IsuuerRecord in
	 * Issuer_master table
	 */
	public boolean update(IssuerDto objIssdto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objIssdto);
			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in IssuerDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in IssuerDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular IsuuerRecord in
	 * Issuer_master table
	 */

	public boolean delete(IssuerDto objIssdto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE IssuerDto SET status =:issuerstatus  WHERE issuerId=:issuerid";
			int count = session.createQuery(sql).setString("issuerid",
					objIssdto.getIssuerId()).setString("issuerstatus",
					objIssdto.getStatus()).executeUpdate();

			// session.delete(objIssdto);
			tx.commit();
			if (count > 0)
				boolDelete = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in IssuerDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in IssuerDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolDelete;

	}

	/*
	 * This method is used for the issuersList from Issuer_master table to
	 * search
	 */

	public Map issuerListData() throws TPlusException {

		Map issuerList = new TreeMap();
		try {
			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();
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
			logger.error(e);
			System.out
					.println("Error in IssuerDAOImpl issuerListData method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in IssuerDAOImpl issuerListData  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return issuerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.IssuerDAO
	 * #checkExistrecord(org.transinfo.cacis.dto.settings.IssuerDto) For
	 * checking Record Existed or nor in Issuer_Master Table
	 */
	public int checkExistrecord(IssuerDto objDto) throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from IssuerDto iss where iss.issuerId=:issuerid ");
			qry.setString("issuerid", objDto.getIssuerId());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After IssuerDAOIMPL checkExistrecord() count"
					+ res);

		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in IssuerDAOImpl checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in IssuerDAOImpl checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

}
