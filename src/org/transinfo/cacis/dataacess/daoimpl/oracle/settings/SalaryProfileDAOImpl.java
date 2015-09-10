package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.SalaryProfileDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.SalaryProfileDto;

@SuppressWarnings("unchecked")
public class SalaryProfileDAOImpl extends BaseDAOImpl implements
		SalaryProfileDAO {

	private Logger logger = Logger.getLogger(SalaryProfileDAOImpl.class);

	public Collection search(String cardProdcuct) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();

		try {
			sbf.append("select spdo.id.creditLimit, ");
			sbf.append("spdo.id.cashAdvanceLimit, ");
			sbf.append("spdo.minSalary, spdo.maxSalary ");
			sbf.append("FROM SalaryProfileDto spdo ");
			sbf.append("where spdo.id.cardProduct = '" + cardProdcuct + "' ");

			objSearchCollection = getSearchList(sbf.toString(), 0);

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error while retrieving the Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public Map getAddproductList(String issuerId) throws TPlusException {

		Map cardProductList = new TreeMap();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfdistinctProducts = new StringBuffer();

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbfdistinctProducts
					.append("select distinct spdo.id.cardProduct.cardProductId ");
			sbfdistinctProducts.append("from SalaryProfileDto spdo ");

			sbf.append("From CardProductDto cpdo ");
			sbf.append("where cpdo.issuerId ='" + issuerId + "' ");
			sbf.append("and cpdo.status='A' ");
			sbf.append("and cpdo.cardProductId NOT IN ("
					+ sbfdistinctProducts.toString() + ")");

			Query qry = session.createQuery(sbf.toString());
			List listCardTypes = qry.list();
			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {
				CardProductDto objDto = new CardProductDto();
				objDto = (CardProductDto) it.next();
				cardProductList.put(objDto.getCardProductId(), objDto
						.getCardProductName());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving cardProductListData in BaseDAOIMpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProductListData in BaseDAOIMpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cardProductList;
	}

	public Map getSearchproductList(String issuerId) throws TPlusException {

		Map cardProductList = new TreeMap();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfdistinctProducts = new StringBuffer();

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbfdistinctProducts
					.append("select distinct spdo.id.cardProduct.cardProductId ");
			sbfdistinctProducts.append("from SalaryProfileDto spdo ");

			sbf.append("From CardProductDto cpdo ");
			sbf.append("where cpdo.issuerId ='" + issuerId + "' ");
			sbf.append("and cpdo.status='A' ");
			sbf.append("and cpdo.cardProductId IN ("
					+ sbfdistinctProducts.toString() + ")");

			Query qry = session.createQuery(sbf.toString());
			List listCardTypes = qry.list();
			for (Iterator it = listCardTypes.iterator(); it.hasNext();) {
				CardProductDto objDto = new CardProductDto();
				objDto = (CardProductDto) it.next();
				cardProductList.put(objDto.getCardProductId(), objDto
						.getCardProductName());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("while retrieving cardProductListData in BaseDAOIMpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving cardProductListData in BaseDAOIMpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cardProductList;
	}

	public boolean add(SalaryProfileDto objSalaryProfileDto)
			throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objSalaryProfileDto);

			tx.commit();
			boolAdd = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardTypeDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardTypeDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;
	}

	public List getSalaryProfileListUpdate(String cardProdcuct)
			throws TPlusException {

		List listProfile = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("FROM SalaryProfileDto spdo ");
			sbf.append("where spdo.id.cardProduct = '" + cardProdcuct + "' ");

			Query qry = session.createQuery(sbf.toString());
			listProfile = qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error while retrieving the Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return listProfile;
	}

	public boolean delete(String cardProdcuct) throws TPlusException {

		boolean delRes = false;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		List listProfile = new ArrayList();
		SalaryProfileDto objSalaryProfileDto = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("FROM SalaryProfileDto spdo ");
			sbf.append("where spdo.id.cardProduct = '" + cardProdcuct + "' ");

			Query qry = session.createQuery(sbf.toString());
			listProfile = qry.list();

			ListIterator litr = listProfile.listIterator();
			while (litr.hasNext()) {
				objSalaryProfileDto = (SalaryProfileDto) litr.next();
				session.delete(objSalaryProfileDto);
			}

			tx.commit();
			delRes = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error while retrieving the Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return delRes;
	}

	public List getSalaryProfileListAppProcess(String cardProdcuct,
			String income) throws TPlusException {

		List listProfile = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("FROM SalaryProfileDto spdo ");
			sbf.append("where spdo.id.cardProduct = '" + cardProdcuct + "' ");
			sbf.append("and spdo.minSalary < " + Float.valueOf(income) + " ");
			sbf.append("and spdo.maxSalary > " + Float.valueOf(income));

			Query qry = session.createQuery(sbf.toString());
			listProfile = qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error while retrieving the Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return listProfile;
	}
}
