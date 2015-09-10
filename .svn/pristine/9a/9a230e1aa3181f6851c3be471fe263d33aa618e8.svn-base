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
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRateDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductRateDto;
import org.transinfo.cacis.dto.settings.CardProductRateSearchDto;

@SuppressWarnings("unchecked")
public class CardProductRateDAOImpl extends BaseDAOImpl implements
		CardProductRateDAO {

	private Logger logger = Logger.getLogger(CardProductRateDAOImpl.class);

	/*
	 * This method is used for getting the CardProductRateList
	 */

	public Collection search(CardProductRateSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			if (objSearchDto.getCardProductId() != null
					&& !objSearchDto.getCardProductId().equals("")) {

				sbf
						.append("select cr.id, to_char(cr.startDt,'dd-MM-yyyy'), to_char(cr.endDt,'dd-MM-yyyy'),cr.displayStatus , to_char(cr.updatedDate,'dd-MM-yyyy') From CardProductRateDto cr where card_product_id='"
								+ objSearchDto.getCardProductId() + "' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			logger.error(e);

			System.out
					.println("Error in CardProductRateDAOImpl search method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductRateDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular cardproduct Rate Record to
	 * update
	 */
	public CardProductRateDto getCardProductRateDto(int rateId)
			throws TPlusException {

		CardProductRateDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardProductRateDto) session.get(CardProductRateDto.class,
					new Integer(rateId));

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardProductRateDAOImpl getCardProductRateDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductRateDAOImpl getCardProductRateDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	/*
	 * This method is used for insert the particular Record to card
	 * _product_rate table
	 */

	public boolean add(CardProductRateDto objDto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CardProductDto prod where prod.cardProductId= '"
							+ objDto.getCardProductId() + "'");
			List res = qry.list();
			CardProductDto cpd = (CardProductDto) res.get(0);
			objDto.setCardProduct(cpd);
			session.save(objDto);
			session.flush();
			tx.commit();
			boolAdd = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardProductRateDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductRateDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in card
	 * _product_rate table
	 */
	public boolean update(CardProductRateDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CardProductDto prod where prod.cardProductId= '"
							+ objDto.getCardProductId() + "'");
			List res = qry.list();
			CardProductDto cpd = (CardProductDto) res.get(0);
			objDto.setCardProduct(cpd);
			session.update(objDto);
			session.flush();
			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardProductRateDAOImpl update method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductRateDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	public List getAllRates(String cardProductId) throws TPlusException {

		List res = new ArrayList();;

		try {
			Session session = HibernetFactory.currentSession();

			Query qry = session
					.createQuery("from CardProductRateDto prodrate where prodrate.cardProduct= '"
							+ cardProductId + "'");

			res = qry.list();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductRateDAOImpl getAllRates  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return res;
	}

	public List getAllRatesExceptOne(String cardProductId, String serialNo) throws TPlusException {

		List res = new ArrayList();;

		try {
			Session session = HibernetFactory.currentSession();

			Query qry = session
					.createQuery("from CardProductRateDto prodrate where prodrate.cardProduct= '"
							+ cardProductId + "' and prodrate.id <> " + serialNo + " ");

			res = qry.list();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductRateDAOImpl getAllRates  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return res;
	}

	/*
	 * This method is used for deleting the particular Record in ard
	 * _product_rate table
	 */
	/*
	 * public boolean delete(CardProductRateDto objDto)throws TPlusException {
	 * 
	 * boolean bolDelete=false; Transaction tx = null; try { Session session
	 * =HibernetFactory.currentSession(); tx =session.beginTransaction();
	 * 
	 * session.delete(objDto); session.flush(); tx.commit(); bolDelete=true; }
	 * 
	 * catch (Exception e) {
	 * 
	 * if(tx!=null) { tx.rollback(); }
	 * System.out.println("Error in CardProductRateDAOImpl delete method : "
	 * +e.getMessage()); throw newTPlusException(TPlusCodes.APPLICATION_ERROR,
	 * "Error: in CardProductRateDAOImpl delete  method :"+e);
	 * 
	 * } finally { HibernetFactory.closeSession(); } return bolDelete;
	 * 
	 * }
	 */

	/*
	 * public int checkExistrecord(CardProductRateDto objDto)throws
	 * TPlusException {
	 * 
	 * int result=0; try { Session session =HibernetFactory.currentSession();
	 * Query qry =session.createQuery(
	 * "select count(*) from CardProductRateDto cr where cr.id=:rateid ");
	 * qry.setInteger("rateid",objDto.getId()); List list = qry.list(); result =
	 * ((Integer)list.get(0)).intValue();
	 * System.out.println("After CardPromotionDAOIMPL checkExistrecord()"
	 * +result);
	 * 
	 * } catch (Exception e) {
	 * System.out.println("Error in CardPromotionDAOImpl checkExist record method:"
	 * +e.getMessage()); throw newTPlusException(TPlusCodes.APPLICATION_ERROR,
	 * "Error:  Error in CardPromotionDAOImpl checkExistrecord method:"+e); }
	 * 
	 * finally { HibernetFactory.closeSession(); }
	 * 
	 * 
	 * return result;
	 * 
	 * }
	 */

}
