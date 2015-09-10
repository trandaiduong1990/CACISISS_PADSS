package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CurrencyRateDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.dto.settings.CurrencyRateDto;

@SuppressWarnings("unchecked")
public class CurrencyRateDAOImpl extends BaseDAOImpl implements CurrencyRateDAO {

	private Logger logger = Logger.getLogger(CurrencyRateDAOImpl.class);

	public Collection search(CurrencyRateDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();

		try {
			sbf
					.append(" select crd.id.currCode.currencyCode, crd.id.currCode.currencySymbol, ");
			sbf
					.append(" crd.id.currCode.currencyName,  to_char(crd.updatedDate,'dd-MM-yyyy'), crd.rate ");
			sbf.append(" FROM CurrencyRateDto crd ");
			sbf.append(" where crd.id.issuerId = '"
					+ objSearchDto.id.getIssuerId() + "' ");

			if (objSearchDto.id.getCurrCode().getCurrencyCode() != null
					&& !objSearchDto.id.getCurrCode().getCurrencyCode().equals(
							"")) {
				sbf.append(" and crd.id.currCode = '"
						+ objSearchDto.id.getCurrCode().getCurrencyCode()
						+ "' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

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

	public int checkExistrecord(CurrencyRateDto objDto) throws TPlusException {

		int res = 0;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append(" select count(*) ");
			sbf.append(" FROM  CurrencyRateDto crd ");
			sbf.append(" where ");
			sbf.append(" crd.id.issuerId = '" + objDto.id.getIssuerId() + "' ");
			sbf.append(" and crd.id.currCode = '"
					+ objDto.id.getCurrCode().getCurrencyCode() + "' ");

			Query qry = session.createQuery(sbf.toString());
			List listDocs = qry.list();
			res = ((Integer) listDocs.get(0)).intValue();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);

			System.out.println("Error in checkExistrecord method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the checkExistrecord" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

	public boolean add(CurrencyRateDto objDto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDto);

			tx.commit();
			boolAdd = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CurrencyRateDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CurrencyRateDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	public CurrencyRateDto getCurrencyRateDto(String currID, String issuerId)
			throws TPlusException {

		CurrencyRateDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			CurrencyDto obCurrencyDto = new CurrencyDto();
			obCurrencyDto.setCurrencyCode(currID);

			objDto = (CurrencyRateDto) session.get(CurrencyRateDto.class,
					new CurrencyRateDto.Id(issuerId, obCurrencyDto));
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CurrencyRateDAOImpl getCurrencyRateDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CurrencyRateDAOImpl getCurrencyRateDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	public boolean update(CurrencyRateDto objDto) throws TPlusException {

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
			System.out.println("Error in CurrencyRateDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CurrencyRateDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	public boolean delete(CurrencyRateDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.delete(objDto);
			tx.commit();
			bolExecute = true;

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(ex);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while deleting" + ex.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public CurrencyDto getCurrencyDto(String currID)
			throws TPlusException {

		CurrencyDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objDto = (CurrencyDto) session.get(CurrencyDto.class,currID);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CurrencyRateDAOImpl getCurrencyDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CurrencyRateDAOImpl getCurrencyDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}
}
